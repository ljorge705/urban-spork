package io.sentry.android.core;

import android.os.Looper;
import io.sentry.EventProcessor;
import io.sentry.Hint;
import io.sentry.MeasurementUnit;
import io.sentry.SentryEvent;
import io.sentry.SpanContext;
import io.sentry.SpanDataConvention;
import io.sentry.SpanId;
import io.sentry.SpanStatus;
import io.sentry.android.core.performance.ActivityLifecycleTimeSpan;
import io.sentry.android.core.performance.AppStartMetrics;
import io.sentry.android.core.performance.TimeSpan;
import io.sentry.protocol.App;
import io.sentry.protocol.MeasurementValue;
import io.sentry.protocol.SentryId;
import io.sentry.protocol.SentrySpan;
import io.sentry.protocol.SentryThread;
import io.sentry.protocol.SentryTransaction;
import io.sentry.util.Objects;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes6.dex */
final class PerformanceAndroidEventProcessor implements EventProcessor {
    private static final String APP_METRICS_ACTIVITIES_OP = "activity.load";
    private static final String APP_METRICS_APPLICATION_OP = "application.load";
    private static final String APP_METRICS_CONTENT_PROVIDER_OP = "contentprovider.load";
    private static final String APP_METRICS_ORIGIN = "auto.ui";
    private static final String APP_METRICS_PROCESS_INIT_OP = "process.load";
    private static final long MAX_PROCESS_INIT_APP_START_DIFF_MS = 10000;
    private final ActivityFramesTracker activityFramesTracker;
    private final SentryAndroidOptions options;
    private boolean sentStartMeasurement = false;

    @Override // io.sentry.EventProcessor
    public SentryEvent process(SentryEvent sentryEvent, Hint hint) {
        return sentryEvent;
    }

    PerformanceAndroidEventProcessor(SentryAndroidOptions sentryAndroidOptions, ActivityFramesTracker activityFramesTracker) {
        this.options = (SentryAndroidOptions) Objects.requireNonNull(sentryAndroidOptions, "SentryAndroidOptions is required");
        this.activityFramesTracker = (ActivityFramesTracker) Objects.requireNonNull(activityFramesTracker, "ActivityFramesTracker is required");
    }

    @Override // io.sentry.EventProcessor
    public synchronized SentryTransaction process(SentryTransaction sentryTransaction, Hint hint) {
        Map<String, MeasurementValue> mapTakeMetrics;
        if (!this.options.isTracingEnabled()) {
            return sentryTransaction;
        }
        AppStartMetrics appStartMetrics = AppStartMetrics.getInstance();
        if (hasAppStartSpan(sentryTransaction)) {
            if (appStartMetrics.shouldSendStartMeasurements()) {
                long durationMs = appStartMetrics.getAppStartTimeSpanWithFallback(this.options).getDurationMs();
                if (durationMs != 0) {
                    sentryTransaction.getMeasurements().put(appStartMetrics.getAppStartType() == AppStartMetrics.AppStartType.COLD ? MeasurementValue.KEY_APP_START_COLD : MeasurementValue.KEY_APP_START_WARM, new MeasurementValue(Float.valueOf(durationMs), MeasurementUnit.Duration.MILLISECOND.apiName()));
                    attachAppStartSpans(appStartMetrics, sentryTransaction);
                    appStartMetrics.onAppStartSpansSent();
                }
            }
            App app2 = sentryTransaction.getContexts().getApp();
            if (app2 == null) {
                app2 = new App();
                sentryTransaction.getContexts().setApp(app2);
            }
            app2.setStartType(appStartMetrics.getAppStartType() == AppStartMetrics.AppStartType.COLD ? "cold" : "warm");
        }
        setContributingFlags(sentryTransaction);
        SentryId eventId = sentryTransaction.getEventId();
        SpanContext trace = sentryTransaction.getContexts().getTrace();
        if (eventId != null && trace != null && trace.getOperation().contentEquals("ui.load") && (mapTakeMetrics = this.activityFramesTracker.takeMetrics(eventId)) != null) {
            sentryTransaction.getMeasurements().putAll(mapTakeMetrics);
        }
        return sentryTransaction;
    }

    private void setContributingFlags(SentryTransaction sentryTransaction) {
        Object obj;
        SentrySpan sentrySpan = null;
        SentrySpan sentrySpan2 = null;
        for (SentrySpan sentrySpan3 : sentryTransaction.getSpans()) {
            if ("ui.load.initial_display".equals(sentrySpan3.getOp())) {
                sentrySpan = sentrySpan3;
            } else if ("ui.load.full_display".equals(sentrySpan3.getOp())) {
                sentrySpan2 = sentrySpan3;
            }
            if (sentrySpan != null && sentrySpan2 != null) {
                break;
            }
        }
        if (sentrySpan == null && sentrySpan2 == null) {
            return;
        }
        for (SentrySpan sentrySpan4 : sentryTransaction.getSpans()) {
            if (sentrySpan4 != sentrySpan && sentrySpan4 != sentrySpan2) {
                Map<String, Object> data = sentrySpan4.getData();
                boolean z = false;
                boolean z2 = sentrySpan != null && isTimestampWithinSpan(sentrySpan4.getStartTimestamp().doubleValue(), sentrySpan) && (data == null || (obj = data.get(SpanDataConvention.THREAD_NAME)) == null || SentryThread.JsonKeys.MAIN.equals(obj));
                if (sentrySpan2 != null && isTimestampWithinSpan(sentrySpan4.getStartTimestamp().doubleValue(), sentrySpan2)) {
                    z = true;
                }
                if (z2 || z) {
                    Map<String, Object> data2 = sentrySpan4.getData();
                    if (data2 == null) {
                        data2 = new ConcurrentHashMap<>();
                        sentrySpan4.setData(data2);
                    }
                    if (z2) {
                        data2.put(SpanDataConvention.CONTRIBUTES_TTID, true);
                    }
                    if (z) {
                        data2.put(SpanDataConvention.CONTRIBUTES_TTFD, true);
                    }
                }
            }
        }
    }

    private static boolean isTimestampWithinSpan(double d, SentrySpan sentrySpan) {
        return d >= sentrySpan.getStartTimestamp().doubleValue() && (sentrySpan.getTimestamp() == null || d <= sentrySpan.getTimestamp().doubleValue());
    }

    private boolean hasAppStartSpan(SentryTransaction sentryTransaction) {
        for (SentrySpan sentrySpan : sentryTransaction.getSpans()) {
            if (sentrySpan.getOp().contentEquals("app.start.cold") || sentrySpan.getOp().contentEquals("app.start.warm")) {
                return true;
            }
        }
        SpanContext trace = sentryTransaction.getContexts().getTrace();
        return trace != null && (trace.getOperation().equals("app.start.cold") || trace.getOperation().equals("app.start.warm"));
    }

    private void attachAppStartSpans(AppStartMetrics appStartMetrics, SentryTransaction sentryTransaction) {
        SpanContext trace;
        SpanId spanId;
        if (appStartMetrics.getAppStartType() == AppStartMetrics.AppStartType.UNKNOWN || (trace = sentryTransaction.getContexts().getTrace()) == null) {
            return;
        }
        SentryId traceId = trace.getTraceId();
        for (SentrySpan sentrySpan : sentryTransaction.getSpans()) {
            if (sentrySpan.getOp().contentEquals("app.start.cold") || sentrySpan.getOp().contentEquals("app.start.warm")) {
                spanId = sentrySpan.getSpanId();
                break;
            }
        }
        spanId = null;
        if (appStartMetrics.getAppStartType() == AppStartMetrics.AppStartType.COLD) {
            long classLoadedUptimeMs = appStartMetrics.getClassLoadedUptimeMs();
            TimeSpan appStartTimeSpan = appStartMetrics.getAppStartTimeSpan();
            if (appStartTimeSpan.hasStarted() && Math.abs(classLoadedUptimeMs - appStartTimeSpan.getStartUptimeMs()) <= 10000) {
                TimeSpan timeSpan = new TimeSpan();
                timeSpan.setStartedAt(appStartTimeSpan.getStartUptimeMs());
                timeSpan.setStartUnixTimeMs(appStartTimeSpan.getStartTimestampMs());
                timeSpan.setStoppedAt(classLoadedUptimeMs);
                timeSpan.setDescription("Process Initialization");
                sentryTransaction.getSpans().add(timeSpanToSentrySpan(timeSpan, spanId, traceId, APP_METRICS_PROCESS_INIT_OP));
            }
            List<TimeSpan> contentProviderOnCreateTimeSpans = appStartMetrics.getContentProviderOnCreateTimeSpans();
            if (!contentProviderOnCreateTimeSpans.isEmpty()) {
                Iterator<TimeSpan> it = contentProviderOnCreateTimeSpans.iterator();
                while (it.hasNext()) {
                    sentryTransaction.getSpans().add(timeSpanToSentrySpan(it.next(), spanId, traceId, APP_METRICS_CONTENT_PROVIDER_OP));
                }
            }
            TimeSpan applicationOnCreateTimeSpan = appStartMetrics.getApplicationOnCreateTimeSpan();
            if (applicationOnCreateTimeSpan.hasStopped()) {
                sentryTransaction.getSpans().add(timeSpanToSentrySpan(applicationOnCreateTimeSpan, spanId, traceId, APP_METRICS_APPLICATION_OP));
            }
        }
        for (ActivityLifecycleTimeSpan activityLifecycleTimeSpan : appStartMetrics.getActivityLifecycleTimeSpans()) {
            if (activityLifecycleTimeSpan.getOnCreate().hasStarted() && activityLifecycleTimeSpan.getOnCreate().hasStopped()) {
                sentryTransaction.getSpans().add(timeSpanToSentrySpan(activityLifecycleTimeSpan.getOnCreate(), spanId, traceId, APP_METRICS_ACTIVITIES_OP));
            }
            if (activityLifecycleTimeSpan.getOnStart().hasStarted() && activityLifecycleTimeSpan.getOnStart().hasStopped()) {
                sentryTransaction.getSpans().add(timeSpanToSentrySpan(activityLifecycleTimeSpan.getOnStart(), spanId, traceId, APP_METRICS_ACTIVITIES_OP));
            }
        }
    }

    private static SentrySpan timeSpanToSentrySpan(TimeSpan timeSpan, SpanId spanId, SentryId sentryId, String str) {
        HashMap map = new HashMap(2);
        map.put(SpanDataConvention.THREAD_ID, Long.valueOf(Looper.getMainLooper().getThread().getId()));
        map.put(SpanDataConvention.THREAD_NAME, SentryThread.JsonKeys.MAIN);
        map.put(SpanDataConvention.CONTRIBUTES_TTID, true);
        map.put(SpanDataConvention.CONTRIBUTES_TTFD, true);
        return new SentrySpan(Double.valueOf(timeSpan.getStartTimestampSecs()), Double.valueOf(timeSpan.getProjectedStopTimestampSecs()), sentryId, new SpanId(), spanId, str, timeSpan.getDescription(), SpanStatus.OK, APP_METRICS_ORIGIN, new ConcurrentHashMap(), new ConcurrentHashMap(), null, map);
    }
}
