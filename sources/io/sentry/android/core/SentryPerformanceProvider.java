package io.sentry.android.core;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.os.SystemClock;
import io.sentry.ILogger;
import io.sentry.ITransactionProfiler;
import io.sentry.JsonSerializer;
import io.sentry.Sentry;
import io.sentry.SentryAppStartProfilingOptions;
import io.sentry.SentryExecutorService;
import io.sentry.SentryLevel;
import io.sentry.SentryOptions;
import io.sentry.TracesSamplingDecision;
import io.sentry.android.core.internal.util.FirstDrawDoneListener;
import io.sentry.android.core.internal.util.SentryFrameMetricsCollector;
import io.sentry.android.core.performance.ActivityLifecycleCallbacksAdapter;
import io.sentry.android.core.performance.AppStartMetrics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes6.dex */
public final class SentryPerformanceProvider extends EmptySecureContentProvider {
    private static final long sdkInitMillis = SystemClock.uptimeMillis();
    private Application.ActivityLifecycleCallbacks activityCallback;

    /* renamed from: app, reason: collision with root package name */
    private Application f300app;
    private final BuildInfoProvider buildInfoProvider;
    private final ILogger logger;

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        return null;
    }

    SentryPerformanceProvider(ILogger iLogger, BuildInfoProvider buildInfoProvider) {
        this.logger = iLogger;
        this.buildInfoProvider = buildInfoProvider;
    }

    public SentryPerformanceProvider() {
        AndroidLogger androidLogger = new AndroidLogger();
        this.logger = androidLogger;
        this.buildInfoProvider = new BuildInfoProvider(androidLogger);
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        AppStartMetrics appStartMetrics = AppStartMetrics.getInstance();
        onAppLaunched(getContext(), appStartMetrics);
        launchAppStartProfiler(appStartMetrics);
        return true;
    }

    @Override // android.content.ContentProvider
    public void attachInfo(Context context, ProviderInfo providerInfo) {
        if (SentryPerformanceProvider.class.getName().equals(providerInfo.authority)) {
            throw new IllegalStateException("An applicationId is required to fulfill the manifest placeholder.");
        }
        super.attachInfo(context, providerInfo);
    }

    @Override // android.content.ContentProvider
    public void shutdown() {
        synchronized (AppStartMetrics.getInstance()) {
            ITransactionProfiler appStartProfiler = AppStartMetrics.getInstance().getAppStartProfiler();
            if (appStartProfiler != null) {
                appStartProfiler.close();
            }
        }
    }

    private void launchAppStartProfiler(AppStartMetrics appStartMetrics) {
        Context context = getContext();
        if (context == null) {
            this.logger.log(SentryLevel.FATAL, "App. Context from ContentProvider is null", new Object[0]);
            return;
        }
        if (this.buildInfoProvider.getSdkInfoVersion() < 21) {
            return;
        }
        File file = new File(AndroidOptionsInitializer.getCacheDir(context), Sentry.APP_START_PROFILING_CONFIG_FILE_NAME);
        if (file.exists() && file.canRead()) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                try {
                    SentryAppStartProfilingOptions sentryAppStartProfilingOptions = (SentryAppStartProfilingOptions) new JsonSerializer(SentryOptions.empty()).deserialize(bufferedReader, SentryAppStartProfilingOptions.class);
                    if (sentryAppStartProfilingOptions == null) {
                        this.logger.log(SentryLevel.WARNING, "Unable to deserialize the SentryAppStartProfilingOptions. App start profiling will not start.", new Object[0]);
                        bufferedReader.close();
                        return;
                    }
                    if (!sentryAppStartProfilingOptions.isProfilingEnabled()) {
                        this.logger.log(SentryLevel.INFO, "Profiling is not enabled. App start profiling will not start.", new Object[0]);
                        bufferedReader.close();
                        return;
                    }
                    TracesSamplingDecision tracesSamplingDecision = new TracesSamplingDecision(Boolean.valueOf(sentryAppStartProfilingOptions.isTraceSampled()), sentryAppStartProfilingOptions.getTraceSampleRate(), Boolean.valueOf(sentryAppStartProfilingOptions.isProfileSampled()), sentryAppStartProfilingOptions.getProfileSampleRate());
                    appStartMetrics.setAppStartSamplingDecision(tracesSamplingDecision);
                    if (tracesSamplingDecision.getProfileSampled().booleanValue() && tracesSamplingDecision.getSampled().booleanValue()) {
                        this.logger.log(SentryLevel.DEBUG, "App start profiling started.", new Object[0]);
                        AndroidTransactionProfiler androidTransactionProfiler = new AndroidTransactionProfiler(context, this.buildInfoProvider, new SentryFrameMetricsCollector(context, this.logger, this.buildInfoProvider), this.logger, sentryAppStartProfilingOptions.getProfilingTracesDirPath(), sentryAppStartProfilingOptions.isProfilingEnabled(), sentryAppStartProfilingOptions.getProfilingTracesHz(), new SentryExecutorService());
                        appStartMetrics.setAppStartProfiler(androidTransactionProfiler);
                        androidTransactionProfiler.start();
                        bufferedReader.close();
                        return;
                    }
                    this.logger.log(SentryLevel.DEBUG, "App start profiling was not sampled. It will not start.", new Object[0]);
                    bufferedReader.close();
                } catch (Throwable th) {
                    try {
                        bufferedReader.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            } catch (FileNotFoundException e) {
                this.logger.log(SentryLevel.ERROR, "App start profiling config file not found. ", e);
            } catch (Throwable th3) {
                this.logger.log(SentryLevel.ERROR, "Error reading app start profiling config file. ", th3);
            }
        }
    }

    private void onAppLaunched(Context context, AppStartMetrics appStartMetrics) {
        appStartMetrics.getSdkInitTimeSpan().setStartedAt(sdkInitMillis);
        if (this.buildInfoProvider.getSdkInfoVersion() < 24) {
            return;
        }
        if (context instanceof Application) {
            this.f300app = (Application) context;
        }
        if (this.f300app == null) {
            return;
        }
        appStartMetrics.getAppStartTimeSpan().setStartedAt(Process.getStartUptimeMillis());
        appStartMetrics.registerApplicationForegroundCheck(this.f300app);
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(new AtomicBoolean(false));
        this.activityCallback = anonymousClass1;
        this.f300app.registerActivityLifecycleCallbacks(anonymousClass1);
    }

    /* renamed from: io.sentry.android.core.SentryPerformanceProvider$1, reason: invalid class name */
    class AnonymousClass1 extends ActivityLifecycleCallbacksAdapter {
        final /* synthetic */ AtomicBoolean val$firstDrawDone;

        AnonymousClass1(AtomicBoolean atomicBoolean) {
            this.val$firstDrawDone = atomicBoolean;
        }

        @Override // io.sentry.android.core.performance.ActivityLifecycleCallbacksAdapter, android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            if (this.val$firstDrawDone.get()) {
                return;
            }
            if (activity.getWindow() != null) {
                FirstDrawDoneListener.registerForNextDraw(activity, new Runnable() { // from class: io.sentry.android.core.SentryPerformanceProvider$1$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.m6025x609c87a8();
                    }
                }, SentryPerformanceProvider.this.buildInfoProvider);
            } else {
                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: io.sentry.android.core.SentryPerformanceProvider$1$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.m6026x8e752207();
                    }
                });
            }
        }

        /* renamed from: lambda$onActivityStarted$0$io-sentry-android-core-SentryPerformanceProvider$1, reason: not valid java name */
        /* synthetic */ void m6025x609c87a8() {
            SentryPerformanceProvider.this.onAppStartDone();
        }

        /* renamed from: lambda$onActivityStarted$1$io-sentry-android-core-SentryPerformanceProvider$1, reason: not valid java name */
        /* synthetic */ void m6026x8e752207() {
            SentryPerformanceProvider.this.onAppStartDone();
        }
    }

    synchronized void onAppStartDone() {
        Application.ActivityLifecycleCallbacks activityLifecycleCallbacks;
        AppStartMetrics appStartMetrics = AppStartMetrics.getInstance();
        appStartMetrics.getSdkInitTimeSpan().stop();
        appStartMetrics.getAppStartTimeSpan().stop();
        Application application = this.f300app;
        if (application != null && (activityLifecycleCallbacks = this.activityCallback) != null) {
            application.unregisterActivityLifecycleCallbacks(activityLifecycleCallbacks);
        }
    }
}
