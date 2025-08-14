package io.sentry.android.core;

import android.app.Application;
import android.content.Context;
import android.os.Process;
import android.os.SystemClock;
import io.sentry.IHub;
import io.sentry.ILogger;
import io.sentry.IScope;
import io.sentry.Integration;
import io.sentry.OptionsContainer;
import io.sentry.ScopeCallback;
import io.sentry.Sentry;
import io.sentry.SentryLevel;
import io.sentry.SentryOptions;
import io.sentry.Session;
import io.sentry.android.core.performance.AppStartMetrics;
import io.sentry.android.core.performance.TimeSpan;
import io.sentry.android.fragment.FragmentLifecycleIntegration;
import io.sentry.android.timber.SentryTimberIntegration;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes6.dex */
public final class SentryAndroid {
    private static final String FRAGMENT_CLASS_NAME = "androidx.fragment.app.FragmentManager$FragmentLifecycleCallbacks";
    static final String SENTRY_FRAGMENT_INTEGRATION_CLASS_NAME = "io.sentry.android.fragment.FragmentLifecycleIntegration";
    static final String SENTRY_REPLAY_INTEGRATION_CLASS_NAME = "io.sentry.android.replay.ReplayIntegration";
    static final String SENTRY_TIMBER_INTEGRATION_CLASS_NAME = "io.sentry.android.timber.SentryTimberIntegration";
    private static final String TIMBER_CLASS_NAME = "timber.log.Timber";
    private static final long sdkInitMillis = SystemClock.uptimeMillis();

    static /* synthetic */ void lambda$init$0(SentryAndroidOptions sentryAndroidOptions) {
    }

    private SentryAndroid() {
    }

    public static void init(Context context) {
        init(context, new AndroidLogger());
    }

    public static void init(Context context, ILogger iLogger) {
        init(context, iLogger, new Sentry.OptionsConfiguration() { // from class: io.sentry.android.core.SentryAndroid$$ExternalSyntheticLambda2
            @Override // io.sentry.Sentry.OptionsConfiguration
            public final void configure(SentryOptions sentryOptions) {
                SentryAndroid.lambda$init$0((SentryAndroidOptions) sentryOptions);
            }
        });
    }

    public static void init(Context context, Sentry.OptionsConfiguration<SentryAndroidOptions> optionsConfiguration) {
        init(context, new AndroidLogger(), optionsConfiguration);
    }

    public static synchronized void init(final Context context, final ILogger iLogger, final Sentry.OptionsConfiguration<SentryAndroidOptions> optionsConfiguration) {
        try {
            try {
                Sentry.init(OptionsContainer.create(SentryAndroidOptions.class), new Sentry.OptionsConfiguration() { // from class: io.sentry.android.core.SentryAndroid$$ExternalSyntheticLambda0
                    @Override // io.sentry.Sentry.OptionsConfiguration
                    public final void configure(SentryOptions sentryOptions) {
                        SentryAndroid.lambda$init$1(iLogger, context, optionsConfiguration, (SentryAndroidOptions) sentryOptions);
                    }
                }, true);
                IHub currentHub = Sentry.getCurrentHub();
                if (ContextUtils.isForegroundImportance()) {
                    if (currentHub.getOptions().isEnableAutoSessionTracking()) {
                        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
                        currentHub.configureScope(new ScopeCallback() { // from class: io.sentry.android.core.SentryAndroid$$ExternalSyntheticLambda1
                            @Override // io.sentry.ScopeCallback
                            public final void run(IScope iScope) {
                                SentryAndroid.lambda$init$2(atomicBoolean, iScope);
                            }
                        });
                        if (!atomicBoolean.get()) {
                            currentHub.startSession();
                        }
                    }
                    currentHub.getOptions().getReplayController().start();
                }
            } catch (InstantiationException e) {
                iLogger.log(SentryLevel.FATAL, "Fatal error during SentryAndroid.init(...)", e);
                throw new RuntimeException("Failed to initialize Sentry's SDK", e);
            } catch (NoSuchMethodException e2) {
                iLogger.log(SentryLevel.FATAL, "Fatal error during SentryAndroid.init(...)", e2);
                throw new RuntimeException("Failed to initialize Sentry's SDK", e2);
            }
        } catch (IllegalAccessException e3) {
            iLogger.log(SentryLevel.FATAL, "Fatal error during SentryAndroid.init(...)", e3);
            throw new RuntimeException("Failed to initialize Sentry's SDK", e3);
        } catch (InvocationTargetException e4) {
            iLogger.log(SentryLevel.FATAL, "Fatal error during SentryAndroid.init(...)", e4);
            throw new RuntimeException("Failed to initialize Sentry's SDK", e4);
        }
    }

    static /* synthetic */ void lambda$init$1(ILogger iLogger, Context context, Sentry.OptionsConfiguration optionsConfiguration, SentryAndroidOptions sentryAndroidOptions) {
        LoadClass loadClass = new LoadClass();
        boolean zIsClassAvailable = loadClass.isClassAvailable(TIMBER_CLASS_NAME, sentryAndroidOptions);
        boolean z = loadClass.isClassAvailable(FRAGMENT_CLASS_NAME, sentryAndroidOptions) && loadClass.isClassAvailable(SENTRY_FRAGMENT_INTEGRATION_CLASS_NAME, sentryAndroidOptions);
        boolean z2 = zIsClassAvailable && loadClass.isClassAvailable(SENTRY_TIMBER_INTEGRATION_CLASS_NAME, sentryAndroidOptions);
        boolean zIsClassAvailable2 = loadClass.isClassAvailable(SENTRY_REPLAY_INTEGRATION_CLASS_NAME, sentryAndroidOptions);
        BuildInfoProvider buildInfoProvider = new BuildInfoProvider(iLogger);
        LoadClass loadClass2 = new LoadClass();
        ActivityFramesTracker activityFramesTracker = new ActivityFramesTracker(loadClass2, sentryAndroidOptions);
        AndroidOptionsInitializer.loadDefaultAndMetadataOptions(sentryAndroidOptions, context, iLogger, buildInfoProvider);
        AndroidOptionsInitializer.installDefaultIntegrations(context, sentryAndroidOptions, buildInfoProvider, loadClass2, activityFramesTracker, z, z2, zIsClassAvailable2);
        try {
            optionsConfiguration.configure(sentryAndroidOptions);
        } catch (Throwable th) {
            sentryAndroidOptions.getLogger().log(SentryLevel.ERROR, "Error in the 'OptionsConfiguration.configure' callback.", th);
        }
        AppStartMetrics appStartMetrics = AppStartMetrics.getInstance();
        if (sentryAndroidOptions.isEnablePerformanceV2() && buildInfoProvider.getSdkInfoVersion() >= 24) {
            TimeSpan appStartTimeSpan = appStartMetrics.getAppStartTimeSpan();
            if (appStartTimeSpan.hasNotStarted()) {
                appStartTimeSpan.setStartedAt(Process.getStartUptimeMillis());
            }
        }
        if (context.getApplicationContext() instanceof Application) {
            appStartMetrics.registerApplicationForegroundCheck((Application) context.getApplicationContext());
        }
        TimeSpan sdkInitTimeSpan = appStartMetrics.getSdkInitTimeSpan();
        if (sdkInitTimeSpan.hasNotStarted()) {
            sdkInitTimeSpan.setStartedAt(sdkInitMillis);
        }
        AndroidOptionsInitializer.initializeIntegrationsAndProcessors(sentryAndroidOptions, context, buildInfoProvider, loadClass2, activityFramesTracker);
        deduplicateIntegrations(sentryAndroidOptions, z, z2);
    }

    static /* synthetic */ void lambda$init$2(AtomicBoolean atomicBoolean, IScope iScope) {
        Session session = iScope.getSession();
        if (session == null || session.getStarted() == null) {
            return;
        }
        atomicBoolean.set(true);
    }

    private static void deduplicateIntegrations(SentryOptions sentryOptions, boolean z, boolean z2) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (Integration integration : sentryOptions.getIntegrations()) {
            if (z && (integration instanceof FragmentLifecycleIntegration)) {
                arrayList2.add(integration);
            }
            if (z2 && (integration instanceof SentryTimberIntegration)) {
                arrayList.add(integration);
            }
        }
        if (arrayList2.size() > 1) {
            for (int i = 0; i < arrayList2.size() - 1; i++) {
                sentryOptions.getIntegrations().remove((Integration) arrayList2.get(i));
            }
        }
        if (arrayList.size() > 1) {
            for (int i2 = 0; i2 < arrayList.size() - 1; i2++) {
                sentryOptions.getIntegrations().remove((Integration) arrayList.get(i2));
            }
        }
    }
}
