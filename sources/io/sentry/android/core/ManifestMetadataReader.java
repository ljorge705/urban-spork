package io.sentry.android.core;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.clevertap.android.sdk.Constants;
import io.sentry.ILogger;
import io.sentry.SentryIntegrationPackageStorage;
import io.sentry.SentryLevel;
import io.sentry.protocol.SdkVersion;
import io.sentry.util.Objects;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes6.dex */
final class ManifestMetadataReader {
    static final String ANR_ATTACH_THREAD_DUMPS = "io.sentry.anr.attach-thread-dumps";
    static final String ANR_ENABLE = "io.sentry.anr.enable";
    static final String ANR_REPORT_DEBUG = "io.sentry.anr.report-debug";
    static final String ANR_TIMEOUT_INTERVAL_MILLIS = "io.sentry.anr.timeout-interval-millis";
    static final String ATTACH_SCREENSHOT = "io.sentry.attach-screenshot";
    static final String ATTACH_THREADS = "io.sentry.attach-threads";
    static final String ATTACH_VIEW_HIERARCHY = "io.sentry.attach-view-hierarchy";
    static final String AUTO_INIT = "io.sentry.auto-init";
    static final String AUTO_SESSION_TRACKING_ENABLE = "io.sentry.auto-session-tracking.enable";
    static final String BREADCRUMBS_ACTIVITY_LIFECYCLE_ENABLE = "io.sentry.breadcrumbs.activity-lifecycle";
    static final String BREADCRUMBS_APP_COMPONENTS_ENABLE = "io.sentry.breadcrumbs.app-components";
    static final String BREADCRUMBS_APP_LIFECYCLE_ENABLE = "io.sentry.breadcrumbs.app-lifecycle";
    static final String BREADCRUMBS_NETWORK_EVENTS_ENABLE = "io.sentry.breadcrumbs.network-events";
    static final String BREADCRUMBS_SYSTEM_EVENTS_ENABLE = "io.sentry.breadcrumbs.system-events";
    static final String BREADCRUMBS_USER_INTERACTION_ENABLE = "io.sentry.breadcrumbs.user-interaction";
    static final String CLIENT_REPORTS_ENABLE = "io.sentry.send-client-reports";
    static final String COLLECT_ADDITIONAL_CONTEXT = "io.sentry.additional-context";
    static final String DEBUG = "io.sentry.debug";
    static final String DEBUG_LEVEL = "io.sentry.debug.level";
    static final String DSN = "io.sentry.dsn";
    static final String ENABLE_APP_START_PROFILING = "io.sentry.profiling.enable-app-start";
    static final String ENABLE_METRICS = "io.sentry.enable-metrics";
    static final String ENABLE_PERFORMANCE_V2 = "io.sentry.performance-v2.enable";
    static final String ENABLE_ROOT_CHECK = "io.sentry.enable-root-check";
    static final String ENABLE_SCOPE_PERSISTENCE = "io.sentry.enable-scope-persistence";
    static final String ENABLE_SENTRY = "io.sentry.enabled";
    static final String ENVIRONMENT = "io.sentry.environment";
    static final String IDLE_TIMEOUT = "io.sentry.traces.idle-timeout";
    static final String MAX_BREADCRUMBS = "io.sentry.max-breadcrumbs";
    static final String NDK_ENABLE = "io.sentry.ndk.enable";
    static final String NDK_SCOPE_SYNC_ENABLE = "io.sentry.ndk.scope-sync.enable";
    static final String PERFORM_FRAMES_TRACKING = "io.sentry.traces.frames-tracking";
    static final String PROFILES_SAMPLE_RATE = "io.sentry.traces.profiling.sample-rate";
    static final String PROGUARD_UUID = "io.sentry.proguard-uuid";
    static final String RELEASE = "io.sentry.release";
    static final String REPLAYS_ERROR_SAMPLE_RATE = "io.sentry.session-replay.on-error-sample-rate";
    static final String REPLAYS_MASK_ALL_IMAGES = "io.sentry.session-replay.mask-all-images";
    static final String REPLAYS_MASK_ALL_TEXT = "io.sentry.session-replay.mask-all-text";
    static final String REPLAYS_SESSION_SAMPLE_RATE = "io.sentry.session-replay.session-sample-rate";
    static final String SAMPLE_RATE = "io.sentry.sample-rate";
    static final String SDK_NAME = "io.sentry.sdk.name";
    static final String SDK_VERSION = "io.sentry.sdk.version";
    static final String SEND_DEFAULT_PII = "io.sentry.send-default-pii";
    static final String SEND_MODULES = "io.sentry.send-modules";
    static final String SENTRY_GRADLE_PLUGIN_INTEGRATIONS = "io.sentry.gradle-plugin-integrations";
    static final String SESSION_TRACKING_ENABLE = "io.sentry.session-tracking.enable";
    static final String SESSION_TRACKING_TIMEOUT_INTERVAL_MILLIS = "io.sentry.session-tracking.timeout-interval-millis";
    static final String TRACES_ACTIVITY_AUTO_FINISH_ENABLE = "io.sentry.traces.activity.auto-finish.enable";
    static final String TRACES_ACTIVITY_ENABLE = "io.sentry.traces.activity.enable";
    static final String TRACES_PROFILING_ENABLE = "io.sentry.traces.profiling.enable";
    static final String TRACES_SAMPLE_RATE = "io.sentry.traces.sample-rate";
    static final String TRACES_UI_ENABLE = "io.sentry.traces.user-interaction.enable";
    static final String TRACE_PROPAGATION_TARGETS = "io.sentry.traces.trace-propagation-targets";
    static final String TRACE_SAMPLING = "io.sentry.traces.trace-sampling";

    @Deprecated
    static final String TRACING_ENABLE = "io.sentry.traces.enable";

    @Deprecated
    static final String TRACING_ORIGINS = "io.sentry.traces.tracing-origins";
    static final String TTFD_ENABLE = "io.sentry.traces.time-to-full-display.enable";
    static final String UNCAUGHT_EXCEPTION_HANDLER_ENABLE = "io.sentry.uncaught-exception-handler.enable";

    private ManifestMetadataReader() {
    }

    static void applyMetadata(Context context, SentryAndroidOptions sentryAndroidOptions, BuildInfoProvider buildInfoProvider) {
        String string;
        Objects.requireNonNull(context, "The application context is required.");
        Objects.requireNonNull(sentryAndroidOptions, "The options object is required.");
        try {
            Bundle metadata = getMetadata(context, sentryAndroidOptions.getLogger(), buildInfoProvider);
            ILogger logger = sentryAndroidOptions.getLogger();
            if (metadata != null) {
                sentryAndroidOptions.setDebug(readBool(metadata, logger, DEBUG, sentryAndroidOptions.isDebug()));
                if (sentryAndroidOptions.isDebug() && (string = readString(metadata, logger, DEBUG_LEVEL, sentryAndroidOptions.getDiagnosticLevel().name().toLowerCase(Locale.ROOT))) != null) {
                    sentryAndroidOptions.setDiagnosticLevel(SentryLevel.valueOf(string.toUpperCase(Locale.ROOT)));
                }
                sentryAndroidOptions.setAnrEnabled(readBool(metadata, logger, ANR_ENABLE, sentryAndroidOptions.isAnrEnabled()));
                sentryAndroidOptions.setEnableAutoSessionTracking(readBool(metadata, logger, AUTO_SESSION_TRACKING_ENABLE, readBool(metadata, logger, SESSION_TRACKING_ENABLE, sentryAndroidOptions.isEnableAutoSessionTracking())));
                if (sentryAndroidOptions.getSampleRate() == null) {
                    Double d = readDouble(metadata, logger, SAMPLE_RATE);
                    if (d.doubleValue() != -1.0d) {
                        sentryAndroidOptions.setSampleRate(d);
                    }
                }
                sentryAndroidOptions.setAnrReportInDebug(readBool(metadata, logger, ANR_REPORT_DEBUG, sentryAndroidOptions.isAnrReportInDebug()));
                sentryAndroidOptions.setAnrTimeoutIntervalMillis(readLong(metadata, logger, ANR_TIMEOUT_INTERVAL_MILLIS, sentryAndroidOptions.getAnrTimeoutIntervalMillis()));
                sentryAndroidOptions.setAttachAnrThreadDump(readBool(metadata, logger, ANR_ATTACH_THREAD_DUMPS, sentryAndroidOptions.isAttachAnrThreadDump()));
                String string2 = readString(metadata, logger, DSN, sentryAndroidOptions.getDsn());
                boolean bool = readBool(metadata, logger, ENABLE_SENTRY, sentryAndroidOptions.isEnabled());
                if (!bool || (string2 != null && string2.isEmpty())) {
                    sentryAndroidOptions.getLogger().log(SentryLevel.DEBUG, "Sentry enabled flag set to false or DSN is empty: disabling sentry-android", new Object[0]);
                } else if (string2 == null) {
                    sentryAndroidOptions.getLogger().log(SentryLevel.FATAL, "DSN is required. Use empty string to disable SDK.", new Object[0]);
                }
                sentryAndroidOptions.setEnabled(bool);
                sentryAndroidOptions.setDsn(string2);
                sentryAndroidOptions.setEnableNdk(readBool(metadata, logger, NDK_ENABLE, sentryAndroidOptions.isEnableNdk()));
                sentryAndroidOptions.setEnableScopeSync(readBool(metadata, logger, NDK_SCOPE_SYNC_ENABLE, sentryAndroidOptions.isEnableScopeSync()));
                sentryAndroidOptions.setRelease(readString(metadata, logger, RELEASE, sentryAndroidOptions.getRelease()));
                sentryAndroidOptions.setEnvironment(readString(metadata, logger, ENVIRONMENT, sentryAndroidOptions.getEnvironment()));
                sentryAndroidOptions.setSessionTrackingIntervalMillis(readLong(metadata, logger, SESSION_TRACKING_TIMEOUT_INTERVAL_MILLIS, sentryAndroidOptions.getSessionTrackingIntervalMillis()));
                sentryAndroidOptions.setMaxBreadcrumbs((int) readLong(metadata, logger, MAX_BREADCRUMBS, sentryAndroidOptions.getMaxBreadcrumbs()));
                sentryAndroidOptions.setEnableActivityLifecycleBreadcrumbs(readBool(metadata, logger, BREADCRUMBS_ACTIVITY_LIFECYCLE_ENABLE, sentryAndroidOptions.isEnableActivityLifecycleBreadcrumbs()));
                sentryAndroidOptions.setEnableAppLifecycleBreadcrumbs(readBool(metadata, logger, BREADCRUMBS_APP_LIFECYCLE_ENABLE, sentryAndroidOptions.isEnableAppLifecycleBreadcrumbs()));
                sentryAndroidOptions.setEnableSystemEventBreadcrumbs(readBool(metadata, logger, BREADCRUMBS_SYSTEM_EVENTS_ENABLE, sentryAndroidOptions.isEnableSystemEventBreadcrumbs()));
                sentryAndroidOptions.setEnableAppComponentBreadcrumbs(readBool(metadata, logger, BREADCRUMBS_APP_COMPONENTS_ENABLE, sentryAndroidOptions.isEnableAppComponentBreadcrumbs()));
                sentryAndroidOptions.setEnableUserInteractionBreadcrumbs(readBool(metadata, logger, BREADCRUMBS_USER_INTERACTION_ENABLE, sentryAndroidOptions.isEnableUserInteractionBreadcrumbs()));
                sentryAndroidOptions.setEnableNetworkEventBreadcrumbs(readBool(metadata, logger, BREADCRUMBS_NETWORK_EVENTS_ENABLE, sentryAndroidOptions.isEnableNetworkEventBreadcrumbs()));
                sentryAndroidOptions.setEnableUncaughtExceptionHandler(readBool(metadata, logger, UNCAUGHT_EXCEPTION_HANDLER_ENABLE, sentryAndroidOptions.isEnableUncaughtExceptionHandler()));
                sentryAndroidOptions.setAttachThreads(readBool(metadata, logger, ATTACH_THREADS, sentryAndroidOptions.isAttachThreads()));
                sentryAndroidOptions.setAttachScreenshot(readBool(metadata, logger, ATTACH_SCREENSHOT, sentryAndroidOptions.isAttachScreenshot()));
                sentryAndroidOptions.setAttachViewHierarchy(readBool(metadata, logger, ATTACH_VIEW_HIERARCHY, sentryAndroidOptions.isAttachViewHierarchy()));
                sentryAndroidOptions.setSendClientReports(readBool(metadata, logger, CLIENT_REPORTS_ENABLE, sentryAndroidOptions.isSendClientReports()));
                sentryAndroidOptions.setCollectAdditionalContext(readBool(metadata, logger, COLLECT_ADDITIONAL_CONTEXT, sentryAndroidOptions.isCollectAdditionalContext()));
                if (sentryAndroidOptions.getEnableTracing() == null) {
                    sentryAndroidOptions.setEnableTracing(readBoolNullable(metadata, logger, TRACING_ENABLE, null));
                }
                if (sentryAndroidOptions.getTracesSampleRate() == null) {
                    Double d2 = readDouble(metadata, logger, TRACES_SAMPLE_RATE);
                    if (d2.doubleValue() != -1.0d) {
                        sentryAndroidOptions.setTracesSampleRate(d2);
                    }
                }
                sentryAndroidOptions.setTraceSampling(readBool(metadata, logger, TRACE_SAMPLING, sentryAndroidOptions.isTraceSampling()));
                sentryAndroidOptions.setEnableAutoActivityLifecycleTracing(readBool(metadata, logger, TRACES_ACTIVITY_ENABLE, sentryAndroidOptions.isEnableAutoActivityLifecycleTracing()));
                sentryAndroidOptions.setEnableActivityLifecycleTracingAutoFinish(readBool(metadata, logger, TRACES_ACTIVITY_AUTO_FINISH_ENABLE, sentryAndroidOptions.isEnableActivityLifecycleTracingAutoFinish()));
                sentryAndroidOptions.setProfilingEnabled(readBool(metadata, logger, TRACES_PROFILING_ENABLE, sentryAndroidOptions.isProfilingEnabled()));
                if (sentryAndroidOptions.getProfilesSampleRate() == null) {
                    Double d3 = readDouble(metadata, logger, PROFILES_SAMPLE_RATE);
                    if (d3.doubleValue() != -1.0d) {
                        sentryAndroidOptions.setProfilesSampleRate(d3);
                    }
                }
                sentryAndroidOptions.setEnableUserInteractionTracing(readBool(metadata, logger, TRACES_UI_ENABLE, sentryAndroidOptions.isEnableUserInteractionTracing()));
                sentryAndroidOptions.setEnableTimeToFullDisplayTracing(readBool(metadata, logger, TTFD_ENABLE, sentryAndroidOptions.isEnableTimeToFullDisplayTracing()));
                long j = readLong(metadata, logger, IDLE_TIMEOUT, -1L);
                if (j != -1) {
                    sentryAndroidOptions.setIdleTimeout(Long.valueOf(j));
                }
                List<String> list = readList(metadata, logger, TRACE_PROPAGATION_TARGETS);
                if (!metadata.containsKey(TRACE_PROPAGATION_TARGETS) && (list == null || list.isEmpty())) {
                    list = readList(metadata, logger, TRACING_ORIGINS);
                }
                if ((metadata.containsKey(TRACE_PROPAGATION_TARGETS) || metadata.containsKey(TRACING_ORIGINS)) && list == null) {
                    sentryAndroidOptions.setTracePropagationTargets(Collections.emptyList());
                } else if (list != null) {
                    sentryAndroidOptions.setTracePropagationTargets(list);
                }
                sentryAndroidOptions.setEnableFramesTracking(readBool(metadata, logger, PERFORM_FRAMES_TRACKING, true));
                sentryAndroidOptions.setProguardUuid(readString(metadata, logger, PROGUARD_UUID, sentryAndroidOptions.getProguardUuid()));
                SdkVersion sdkVersion = sentryAndroidOptions.getSdkVersion();
                if (sdkVersion == null) {
                    sdkVersion = new SdkVersion("", "");
                }
                sdkVersion.setName(readStringNotNull(metadata, logger, SDK_NAME, sdkVersion.getName()));
                sdkVersion.setVersion(readStringNotNull(metadata, logger, SDK_VERSION, sdkVersion.getVersion()));
                sentryAndroidOptions.setSdkVersion(sdkVersion);
                sentryAndroidOptions.setSendDefaultPii(readBool(metadata, logger, SEND_DEFAULT_PII, sentryAndroidOptions.isSendDefaultPii()));
                List<String> list2 = readList(metadata, logger, SENTRY_GRADLE_PLUGIN_INTEGRATIONS);
                if (list2 != null) {
                    Iterator<String> it = list2.iterator();
                    while (it.hasNext()) {
                        SentryIntegrationPackageStorage.getInstance().addIntegration(it.next());
                    }
                }
                sentryAndroidOptions.setEnableRootCheck(readBool(metadata, logger, ENABLE_ROOT_CHECK, sentryAndroidOptions.isEnableRootCheck()));
                sentryAndroidOptions.setSendModules(readBool(metadata, logger, SEND_MODULES, sentryAndroidOptions.isSendModules()));
                sentryAndroidOptions.setEnablePerformanceV2(readBool(metadata, logger, ENABLE_PERFORMANCE_V2, sentryAndroidOptions.isEnablePerformanceV2()));
                sentryAndroidOptions.setEnableAppStartProfiling(readBool(metadata, logger, ENABLE_APP_START_PROFILING, sentryAndroidOptions.isEnableAppStartProfiling()));
                sentryAndroidOptions.setEnableScopePersistence(readBool(metadata, logger, ENABLE_SCOPE_PERSISTENCE, sentryAndroidOptions.isEnableScopePersistence()));
                sentryAndroidOptions.setEnableMetrics(readBool(metadata, logger, ENABLE_METRICS, sentryAndroidOptions.isEnableMetrics()));
                if (sentryAndroidOptions.getSessionReplay().getSessionSampleRate() == null) {
                    Double d4 = readDouble(metadata, logger, REPLAYS_SESSION_SAMPLE_RATE);
                    if (d4.doubleValue() != -1.0d) {
                        sentryAndroidOptions.getSessionReplay().setSessionSampleRate(d4);
                    }
                }
                if (sentryAndroidOptions.getSessionReplay().getOnErrorSampleRate() == null) {
                    Double d5 = readDouble(metadata, logger, REPLAYS_ERROR_SAMPLE_RATE);
                    if (d5.doubleValue() != -1.0d) {
                        sentryAndroidOptions.getSessionReplay().setOnErrorSampleRate(d5);
                    }
                }
                sentryAndroidOptions.getSessionReplay().setMaskAllText(readBool(metadata, logger, REPLAYS_MASK_ALL_TEXT, true));
                sentryAndroidOptions.getSessionReplay().setMaskAllImages(readBool(metadata, logger, REPLAYS_MASK_ALL_IMAGES, true));
            }
            sentryAndroidOptions.getLogger().log(SentryLevel.INFO, "Retrieving configuration from AndroidManifest.xml", new Object[0]);
        } catch (Throwable th) {
            sentryAndroidOptions.getLogger().log(SentryLevel.ERROR, "Failed to read configuration from android manifest metadata.", th);
        }
    }

    private static boolean readBool(Bundle bundle, ILogger iLogger, String str, boolean z) {
        boolean z2 = bundle.getBoolean(str, z);
        iLogger.log(SentryLevel.DEBUG, str + " read: " + z2, new Object[0]);
        return z2;
    }

    private static Boolean readBoolNullable(Bundle bundle, ILogger iLogger, String str, Boolean bool) {
        if (bundle.getSerializable(str) != null) {
            boolean z = bundle.getBoolean(str, bool != null);
            iLogger.log(SentryLevel.DEBUG, str + " read: " + z, new Object[0]);
            return Boolean.valueOf(z);
        }
        iLogger.log(SentryLevel.DEBUG, str + " used default " + bool, new Object[0]);
        return bool;
    }

    private static String readString(Bundle bundle, ILogger iLogger, String str, String str2) {
        String string = bundle.getString(str, str2);
        iLogger.log(SentryLevel.DEBUG, str + " read: " + string, new Object[0]);
        return string;
    }

    private static String readStringNotNull(Bundle bundle, ILogger iLogger, String str, String str2) {
        String string = bundle.getString(str, str2);
        iLogger.log(SentryLevel.DEBUG, str + " read: " + string, new Object[0]);
        return string;
    }

    private static List<String> readList(Bundle bundle, ILogger iLogger, String str) {
        String string = bundle.getString(str);
        iLogger.log(SentryLevel.DEBUG, str + " read: " + string, new Object[0]);
        if (string != null) {
            return Arrays.asList(string.split(Constants.SEPARATOR_COMMA, -1));
        }
        return null;
    }

    private static Double readDouble(Bundle bundle, ILogger iLogger, String str) {
        Double dValueOf = Double.valueOf(Float.valueOf(bundle.getFloat(str, bundle.getInt(str, -1))).doubleValue());
        iLogger.log(SentryLevel.DEBUG, str + " read: " + dValueOf, new Object[0]);
        return dValueOf;
    }

    private static long readLong(Bundle bundle, ILogger iLogger, String str, long j) {
        long j2 = bundle.getInt(str, (int) j);
        iLogger.log(SentryLevel.DEBUG, str + " read: " + j2, new Object[0]);
        return j2;
    }

    static boolean isAutoInit(Context context, ILogger iLogger) {
        Objects.requireNonNull(context, "The application context is required.");
        try {
            Bundle metadata = getMetadata(context, iLogger, null);
            if (metadata != null) {
                return readBool(metadata, iLogger, AUTO_INIT, true);
            }
            return true;
        } catch (Throwable th) {
            iLogger.log(SentryLevel.ERROR, "Failed to read auto-init from android manifest metadata.", th);
            return true;
        }
    }

    private static Bundle getMetadata(Context context, ILogger iLogger, BuildInfoProvider buildInfoProvider) throws PackageManager.NameNotFoundException {
        if (buildInfoProvider == null) {
            buildInfoProvider = new BuildInfoProvider(iLogger);
        }
        return ContextUtils.getApplicationInfo(context, 128L, buildInfoProvider).metaData;
    }
}
