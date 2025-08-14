package io.sentry.android.core;

import android.content.Context;
import android.content.pm.PackageInfo;
import io.sentry.DateUtils;
import io.sentry.HubAdapter;
import io.sentry.IHub;
import io.sentry.ILogger;
import io.sentry.IScope;
import io.sentry.ISerializer;
import io.sentry.ScopeCallback;
import io.sentry.SentryBaseEvent;
import io.sentry.SentryEnvelope;
import io.sentry.SentryEnvelopeItem;
import io.sentry.SentryEvent;
import io.sentry.SentryLevel;
import io.sentry.SentryOptions;
import io.sentry.Session;
import io.sentry.android.core.performance.ActivityLifecycleTimeSpan;
import io.sentry.android.core.performance.AppStartMetrics;
import io.sentry.android.core.performance.TimeSpan;
import io.sentry.cache.EnvelopeCache;
import io.sentry.protocol.App;
import io.sentry.protocol.SentryId;
import io.sentry.protocol.SentryTransaction;
import io.sentry.protocol.User;
import io.sentry.util.MapObjectWriter;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes6.dex */
public final class InternalSentrySdk {
    public static IScope getCurrentScope() {
        final AtomicReference atomicReference = new AtomicReference();
        HubAdapter.getInstance().configureScope(new ScopeCallback() { // from class: io.sentry.android.core.InternalSentrySdk$$ExternalSyntheticLambda2
            @Override // io.sentry.ScopeCallback
            public final void run(IScope iScope) {
                atomicReference.set(iScope.m5982clone());
            }
        });
        return (IScope) atomicReference.get();
    }

    public static Map<String, Object> serializeScope(Context context, SentryAndroidOptions sentryAndroidOptions, IScope iScope) {
        HashMap map = new HashMap();
        if (iScope == null) {
            return map;
        }
        try {
            ILogger logger = sentryAndroidOptions.getLogger();
            MapObjectWriter mapObjectWriter = new MapObjectWriter(map);
            DeviceInfoUtil deviceInfoUtil = DeviceInfoUtil.getInstance(context, sentryAndroidOptions);
            iScope.getContexts().setDevice(deviceInfoUtil.collectDeviceInformation(true, true));
            iScope.getContexts().setOperatingSystem(deviceInfoUtil.getOperatingSystem());
            User user = iScope.getUser();
            if (user == null) {
                user = new User();
                iScope.setUser(user);
            }
            if (user.getId() == null) {
                try {
                    user.setId(Installation.id(context));
                } catch (RuntimeException e) {
                    logger.log(SentryLevel.ERROR, "Could not retrieve installation ID", e);
                }
            }
            App app2 = iScope.getContexts().getApp();
            if (app2 == null) {
                app2 = new App();
            }
            app2.setAppName(ContextUtils.getApplicationName(context, sentryAndroidOptions.getLogger()));
            TimeSpan appStartTimeSpanWithFallback = AppStartMetrics.getInstance().getAppStartTimeSpanWithFallback(sentryAndroidOptions);
            if (appStartTimeSpanWithFallback.hasStarted()) {
                app2.setAppStartTime(DateUtils.toUtilDate(appStartTimeSpanWithFallback.getStartTimestamp()));
            }
            BuildInfoProvider buildInfoProvider = new BuildInfoProvider(sentryAndroidOptions.getLogger());
            PackageInfo packageInfo = ContextUtils.getPackageInfo(context, 4096, sentryAndroidOptions.getLogger(), buildInfoProvider);
            if (packageInfo != null) {
                ContextUtils.setAppPackageInfo(packageInfo, buildInfoProvider, app2);
            }
            iScope.getContexts().setApp(app2);
            mapObjectWriter.name("user").value(logger, iScope.getUser());
            mapObjectWriter.name("contexts").value(logger, iScope.getContexts());
            mapObjectWriter.name("tags").value(logger, iScope.getTags());
            mapObjectWriter.name("extras").value(logger, iScope.getExtras());
            mapObjectWriter.name(SentryEvent.JsonKeys.FINGERPRINT).value(logger, iScope.getFingerprint());
            mapObjectWriter.name("level").value(logger, iScope.getLevel());
            mapObjectWriter.name(SentryBaseEvent.JsonKeys.BREADCRUMBS).value(logger, iScope.getBreadcrumbs());
            return map;
        } catch (Throwable th) {
            sentryAndroidOptions.getLogger().log(SentryLevel.ERROR, "Could not serialize scope.", th);
            return new HashMap();
        }
    }

    public static SentryId captureEnvelope(byte[] bArr, boolean z) {
        HubAdapter hubAdapter = HubAdapter.getInstance();
        SentryOptions options = hubAdapter.getOptions();
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            try {
                ISerializer serializer = options.getSerializer();
                SentryEnvelope sentryEnvelope = options.getEnvelopeReader().read(byteArrayInputStream);
                if (sentryEnvelope != null) {
                    ArrayList arrayList = new ArrayList();
                    Session.State state = null;
                    boolean z2 = false;
                    for (SentryEnvelopeItem sentryEnvelopeItem : sentryEnvelope.getItems()) {
                        arrayList.add(sentryEnvelopeItem);
                        SentryEvent event = sentryEnvelopeItem.getEvent(serializer);
                        if (event != null) {
                            if (event.isCrashed()) {
                                state = Session.State.Crashed;
                            }
                            if (event.isCrashed() || event.isErrored()) {
                                z2 = true;
                            }
                        }
                    }
                    Session sessionUpdateSession = updateSession(hubAdapter, options, state, z2);
                    if (sessionUpdateSession != null) {
                        arrayList.add(SentryEnvelopeItem.fromSession(serializer, sessionUpdateSession));
                        deleteCurrentSessionFile(options, (z && hubAdapter.getOptions().getMainThreadChecker().isMainThread()) ? false : true);
                        if (z) {
                            hubAdapter.startSession();
                        }
                    }
                    SentryId sentryIdCaptureEnvelope = hubAdapter.captureEnvelope(new SentryEnvelope(sentryEnvelope.getHeader(), arrayList));
                    byteArrayInputStream.close();
                    return sentryIdCaptureEnvelope;
                }
                byteArrayInputStream.close();
                return null;
            } finally {
            }
        } catch (Throwable th) {
            options.getLogger().log(SentryLevel.ERROR, "Failed to capture envelope", th);
            return null;
        }
    }

    public static Map<String, Object> getAppStartMeasurement() {
        AppStartMetrics appStartMetrics = AppStartMetrics.getInstance();
        ArrayList arrayList = new ArrayList();
        TimeSpan timeSpan = new TimeSpan();
        timeSpan.setStartedAt(appStartMetrics.getAppStartTimeSpan().getStartUptimeMs());
        timeSpan.setStartUnixTimeMs(appStartMetrics.getAppStartTimeSpan().getStartTimestampMs());
        timeSpan.setStoppedAt(appStartMetrics.getClassLoadedUptimeMs());
        timeSpan.setDescription("Process Initialization");
        addTimeSpanToSerializedSpans(timeSpan, arrayList);
        addTimeSpanToSerializedSpans(appStartMetrics.getApplicationOnCreateTimeSpan(), arrayList);
        Iterator<TimeSpan> it = appStartMetrics.getContentProviderOnCreateTimeSpans().iterator();
        while (it.hasNext()) {
            addTimeSpanToSerializedSpans(it.next(), arrayList);
        }
        for (ActivityLifecycleTimeSpan activityLifecycleTimeSpan : appStartMetrics.getActivityLifecycleTimeSpans()) {
            addTimeSpanToSerializedSpans(activityLifecycleTimeSpan.getOnCreate(), arrayList);
            addTimeSpanToSerializedSpans(activityLifecycleTimeSpan.getOnStart(), arrayList);
        }
        HashMap map = new HashMap();
        map.put(SentryTransaction.JsonKeys.SPANS, arrayList);
        map.put("type", appStartMetrics.getAppStartType().toString().toLowerCase(Locale.ROOT));
        if (appStartMetrics.getAppStartTimeSpan().hasStarted()) {
            map.put("app_start_timestamp_ms", Long.valueOf(appStartMetrics.getAppStartTimeSpan().getStartTimestampMs()));
        }
        return map;
    }

    private static void addTimeSpanToSerializedSpans(TimeSpan timeSpan, List<Map<String, Object>> list) {
        if (timeSpan.hasNotStarted()) {
            HubAdapter.getInstance().getOptions().getLogger().log(SentryLevel.WARNING, "Can not convert not-started TimeSpan to Map for Hybrid SDKs.", new Object[0]);
            return;
        }
        if (timeSpan.hasNotStopped()) {
            HubAdapter.getInstance().getOptions().getLogger().log(SentryLevel.WARNING, "Can not convert not-stopped TimeSpan to Map for Hybrid SDKs.", new Object[0]);
            return;
        }
        HashMap map = new HashMap();
        map.put("description", timeSpan.getDescription());
        map.put("start_timestamp_ms", Long.valueOf(timeSpan.getStartTimestampMs()));
        map.put("end_timestamp_ms", Long.valueOf(timeSpan.getProjectedStopTimestampMs()));
        list.add(map);
    }

    private static void deleteCurrentSessionFile(final SentryOptions sentryOptions, boolean z) {
        if (!z) {
            try {
                sentryOptions.getExecutorService().submit(new Runnable() { // from class: io.sentry.android.core.InternalSentrySdk$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        InternalSentrySdk.deleteCurrentSessionFile(sentryOptions);
                    }
                });
                return;
            } catch (Throwable th) {
                sentryOptions.getLogger().log(SentryLevel.WARNING, "Submission of deletion of the current session file rejected.", th);
                return;
            }
        }
        deleteCurrentSessionFile(sentryOptions);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void deleteCurrentSessionFile(SentryOptions sentryOptions) {
        String cacheDirPath = sentryOptions.getCacheDirPath();
        if (cacheDirPath == null) {
            sentryOptions.getLogger().log(SentryLevel.INFO, "Cache dir is not set, not deleting the current session.", new Object[0]);
        } else if (!sentryOptions.isEnableAutoSessionTracking()) {
            sentryOptions.getLogger().log(SentryLevel.DEBUG, "Session tracking is disabled, bailing from deleting current session file.", new Object[0]);
        } else {
            if (EnvelopeCache.getCurrentSessionFile(cacheDirPath).delete()) {
                return;
            }
            sentryOptions.getLogger().log(SentryLevel.WARNING, "Failed to delete the current session file.", new Object[0]);
        }
    }

    private static Session updateSession(IHub iHub, final SentryOptions sentryOptions, final Session.State state, final boolean z) {
        final AtomicReference atomicReference = new AtomicReference();
        iHub.configureScope(new ScopeCallback() { // from class: io.sentry.android.core.InternalSentrySdk$$ExternalSyntheticLambda1
            @Override // io.sentry.ScopeCallback
            public final void run(IScope iScope) {
                InternalSentrySdk.lambda$updateSession$2(state, z, atomicReference, sentryOptions, iScope);
            }
        });
        return (Session) atomicReference.get();
    }

    static /* synthetic */ void lambda$updateSession$2(Session.State state, boolean z, AtomicReference atomicReference, SentryOptions sentryOptions, IScope iScope) {
        Session session = iScope.getSession();
        if (session != null) {
            if (session.update(state, null, z, null)) {
                if (session.getStatus() == Session.State.Crashed) {
                    session.end();
                    iScope.clearSession();
                }
                atomicReference.set(session);
                return;
            }
            return;
        }
        sentryOptions.getLogger().log(SentryLevel.INFO, "Session is null on updateSession", new Object[0]);
    }
}
