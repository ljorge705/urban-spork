package io.sentry.protocol;

import io.sentry.DateUtils;
import io.sentry.ILogger;
import io.sentry.JsonDeserializer;
import io.sentry.JsonSerializable;
import io.sentry.JsonUnknown;
import io.sentry.ObjectReader;
import io.sentry.ObjectWriter;
import io.sentry.SentryLevel;
import io.sentry.Span;
import io.sentry.SpanId;
import io.sentry.SpanStatus;
import io.sentry.metrics.LocalMetricsAggregator;
import io.sentry.protocol.MeasurementValue;
import io.sentry.protocol.MetricSummary;
import io.sentry.protocol.SentryId;
import io.sentry.util.CollectionUtils;
import io.sentry.util.Objects;
import io.sentry.vendor.gson.stream.JsonToken;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes6.dex */
public final class SentrySpan implements JsonUnknown, JsonSerializable {
    private Map<String, Object> data;
    private final String description;
    private final Map<String, MeasurementValue> measurements;
    private final Map<String, List<MetricSummary>> metricsSummaries;
    private final String op;
    private final String origin;
    private final SpanId parentSpanId;
    private final SpanId spanId;
    private final Double startTimestamp;
    private final SpanStatus status;
    private final Map<String, String> tags;
    private final Double timestamp;
    private final SentryId traceId;
    private Map<String, Object> unknown;

    public static final class JsonKeys {
        public static final String DATA = "data";
        public static final String DESCRIPTION = "description";
        public static final String MEASUREMENTS = "measurements";
        public static final String METRICS_SUMMARY = "_metrics_summary";
        public static final String OP = "op";
        public static final String ORIGIN = "origin";
        public static final String PARENT_SPAN_ID = "parent_span_id";
        public static final String SPAN_ID = "span_id";
        public static final String START_TIMESTAMP = "start_timestamp";
        public static final String STATUS = "status";
        public static final String TAGS = "tags";
        public static final String TIMESTAMP = "timestamp";
        public static final String TRACE_ID = "trace_id";
    }

    public Map<String, Object> getData() {
        return this.data;
    }

    public String getDescription() {
        return this.description;
    }

    public Map<String, MeasurementValue> getMeasurements() {
        return this.measurements;
    }

    public Map<String, List<MetricSummary>> getMetricsSummaries() {
        return this.metricsSummaries;
    }

    public String getOp() {
        return this.op;
    }

    public String getOrigin() {
        return this.origin;
    }

    public SpanId getParentSpanId() {
        return this.parentSpanId;
    }

    public SpanId getSpanId() {
        return this.spanId;
    }

    public Double getStartTimestamp() {
        return this.startTimestamp;
    }

    public SpanStatus getStatus() {
        return this.status;
    }

    public Map<String, String> getTags() {
        return this.tags;
    }

    public Double getTimestamp() {
        return this.timestamp;
    }

    public SentryId getTraceId() {
        return this.traceId;
    }

    @Override // io.sentry.JsonUnknown
    public Map<String, Object> getUnknown() {
        return this.unknown;
    }

    public boolean isFinished() {
        return this.timestamp != null;
    }

    public void setData(Map<String, Object> map) {
        this.data = map;
    }

    @Override // io.sentry.JsonUnknown
    public void setUnknown(Map<String, Object> map) {
        this.unknown = map;
    }

    public SentrySpan(Span span) {
        this(span, span.getData());
    }

    public SentrySpan(Span span, Map<String, Object> map) {
        Objects.requireNonNull(span, "span is required");
        this.description = span.getDescription();
        this.op = span.getOperation();
        this.spanId = span.getSpanId();
        this.parentSpanId = span.getParentSpanId();
        this.traceId = span.getTraceId();
        this.status = span.getStatus();
        this.origin = span.getSpanContext().getOrigin();
        Map<String, String> mapNewConcurrentHashMap = CollectionUtils.newConcurrentHashMap(span.getTags());
        this.tags = mapNewConcurrentHashMap == null ? new ConcurrentHashMap<>() : mapNewConcurrentHashMap;
        Map<String, MeasurementValue> mapNewConcurrentHashMap2 = CollectionUtils.newConcurrentHashMap(span.getMeasurements());
        this.measurements = mapNewConcurrentHashMap2 == null ? new ConcurrentHashMap<>() : mapNewConcurrentHashMap2;
        this.timestamp = span.getFinishDate() == null ? null : Double.valueOf(DateUtils.nanosToSeconds(span.getStartDate().laterDateNanosTimestampByDiff(span.getFinishDate())));
        this.startTimestamp = Double.valueOf(DateUtils.nanosToSeconds(span.getStartDate().nanoTimestamp()));
        this.data = map;
        LocalMetricsAggregator localMetricsAggregator = span.getLocalMetricsAggregator();
        if (localMetricsAggregator != null) {
            this.metricsSummaries = localMetricsAggregator.getSummaries();
        } else {
            this.metricsSummaries = null;
        }
    }

    public SentrySpan(Double d, Double d2, SentryId sentryId, SpanId spanId, SpanId spanId2, String str, String str2, SpanStatus spanStatus, String str3, Map<String, String> map, Map<String, MeasurementValue> map2, Map<String, List<MetricSummary>> map3, Map<String, Object> map4) {
        this.startTimestamp = d;
        this.timestamp = d2;
        this.traceId = sentryId;
        this.spanId = spanId;
        this.parentSpanId = spanId2;
        this.op = str;
        this.description = str2;
        this.status = spanStatus;
        this.origin = str3;
        this.tags = map;
        this.measurements = map2;
        this.metricsSummaries = map3;
        this.data = map4;
    }

    @Override // io.sentry.JsonSerializable
    public void serialize(ObjectWriter objectWriter, ILogger iLogger) throws IOException {
        objectWriter.beginObject();
        objectWriter.name("start_timestamp").value(iLogger, doubleToBigDecimal(this.startTimestamp));
        if (this.timestamp != null) {
            objectWriter.name("timestamp").value(iLogger, doubleToBigDecimal(this.timestamp));
        }
        objectWriter.name("trace_id").value(iLogger, this.traceId);
        objectWriter.name("span_id").value(iLogger, this.spanId);
        if (this.parentSpanId != null) {
            objectWriter.name("parent_span_id").value(iLogger, this.parentSpanId);
        }
        objectWriter.name("op").value(this.op);
        if (this.description != null) {
            objectWriter.name("description").value(this.description);
        }
        if (this.status != null) {
            objectWriter.name("status").value(iLogger, this.status);
        }
        if (this.origin != null) {
            objectWriter.name("origin").value(iLogger, this.origin);
        }
        if (!this.tags.isEmpty()) {
            objectWriter.name("tags").value(iLogger, this.tags);
        }
        if (this.data != null) {
            objectWriter.name("data").value(iLogger, this.data);
        }
        if (!this.measurements.isEmpty()) {
            objectWriter.name("measurements").value(iLogger, this.measurements);
        }
        Map<String, List<MetricSummary>> map = this.metricsSummaries;
        if (map != null && !map.isEmpty()) {
            objectWriter.name("_metrics_summary").value(iLogger, this.metricsSummaries);
        }
        Map<String, Object> map2 = this.unknown;
        if (map2 != null) {
            for (String str : map2.keySet()) {
                Object obj = this.unknown.get(str);
                objectWriter.name(str);
                objectWriter.value(iLogger, obj);
            }
        }
        objectWriter.endObject();
    }

    private BigDecimal doubleToBigDecimal(Double d) {
        return BigDecimal.valueOf(d.doubleValue()).setScale(6, RoundingMode.DOWN);
    }

    public static final class Deserializer implements JsonDeserializer<SentrySpan> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.sentry.JsonDeserializer
        public SentrySpan deserialize(ObjectReader objectReader, ILogger iLogger) throws Exception {
            String strNextName;
            objectReader.beginObject();
            Map map = null;
            Double dValueOf = null;
            Double dValueOf2 = null;
            SentryId sentryIdDeserialize = null;
            SpanId spanIdDeserialize = null;
            SpanId spanId = null;
            String strNextStringOrNull = null;
            String strNextStringOrNull2 = null;
            SpanStatus spanStatus = null;
            String strNextStringOrNull3 = null;
            Map mapNextMapOrNull = null;
            ConcurrentHashMap concurrentHashMap = null;
            Map mapNextMapOfListOrNull = null;
            Map map2 = null;
            while (true) {
                String str = strNextStringOrNull3;
                SpanStatus spanStatus2 = spanStatus;
                String str2 = strNextStringOrNull2;
                SpanId spanId2 = spanId;
                if (objectReader.peek() != JsonToken.NAME) {
                    if (dValueOf == null) {
                        throw missingRequiredFieldException("start_timestamp", iLogger);
                    }
                    if (sentryIdDeserialize == null) {
                        throw missingRequiredFieldException("trace_id", iLogger);
                    }
                    if (spanIdDeserialize == null) {
                        throw missingRequiredFieldException("span_id", iLogger);
                    }
                    if (strNextStringOrNull == null) {
                        throw missingRequiredFieldException("op", iLogger);
                    }
                    Map map3 = map == null ? new HashMap() : map;
                    Map map4 = mapNextMapOrNull == null ? new HashMap() : mapNextMapOrNull;
                    SentrySpan sentrySpan = new SentrySpan(dValueOf, dValueOf2, sentryIdDeserialize, spanIdDeserialize, spanId2, strNextStringOrNull, str2, spanStatus2, str, map3, map4, mapNextMapOfListOrNull, map2);
                    sentrySpan.setUnknown(concurrentHashMap);
                    objectReader.endObject();
                    return sentrySpan;
                }
                strNextName = objectReader.nextName();
                strNextName.hashCode();
                switch (strNextName) {
                    case "span_id":
                        spanIdDeserialize = new SpanId.Deserializer().deserialize(objectReader, iLogger);
                        strNextStringOrNull3 = str;
                        spanStatus = spanStatus2;
                        strNextStringOrNull2 = str2;
                        spanId = spanId2;
                        break;
                    case "parent_span_id":
                        spanId = (SpanId) objectReader.nextOrNull(iLogger, new SpanId.Deserializer());
                        strNextStringOrNull3 = str;
                        spanStatus = spanStatus2;
                        strNextStringOrNull2 = str2;
                        break;
                    case "description":
                        strNextStringOrNull2 = objectReader.nextStringOrNull();
                        strNextStringOrNull3 = str;
                        spanStatus = spanStatus2;
                        spanId = spanId2;
                        break;
                    case "start_timestamp":
                        try {
                            dValueOf = objectReader.nextDoubleOrNull();
                        } catch (NumberFormatException unused) {
                            Date dateNextDateOrNull = objectReader.nextDateOrNull(iLogger);
                            dValueOf = dateNextDateOrNull != null ? Double.valueOf(DateUtils.dateToSeconds(dateNextDateOrNull)) : null;
                        }
                        strNextStringOrNull3 = str;
                        spanStatus = spanStatus2;
                        strNextStringOrNull2 = str2;
                        spanId = spanId2;
                        break;
                    case "origin":
                        strNextStringOrNull3 = objectReader.nextStringOrNull();
                        spanStatus = spanStatus2;
                        strNextStringOrNull2 = str2;
                        spanId = spanId2;
                        break;
                    case "status":
                        spanStatus = (SpanStatus) objectReader.nextOrNull(iLogger, new SpanStatus.Deserializer());
                        strNextStringOrNull3 = str;
                        strNextStringOrNull2 = str2;
                        spanId = spanId2;
                        break;
                    case "_metrics_summary":
                        mapNextMapOfListOrNull = objectReader.nextMapOfListOrNull(iLogger, new MetricSummary.Deserializer());
                        strNextStringOrNull3 = str;
                        spanStatus = spanStatus2;
                        strNextStringOrNull2 = str2;
                        spanId = spanId2;
                        break;
                    case "measurements":
                        mapNextMapOrNull = objectReader.nextMapOrNull(iLogger, new MeasurementValue.Deserializer());
                        strNextStringOrNull3 = str;
                        spanStatus = spanStatus2;
                        strNextStringOrNull2 = str2;
                        spanId = spanId2;
                        break;
                    case "op":
                        strNextStringOrNull = objectReader.nextStringOrNull();
                        strNextStringOrNull3 = str;
                        spanStatus = spanStatus2;
                        strNextStringOrNull2 = str2;
                        spanId = spanId2;
                        break;
                    case "data":
                        map2 = (Map) objectReader.nextObjectOrNull();
                        strNextStringOrNull3 = str;
                        spanStatus = spanStatus2;
                        strNextStringOrNull2 = str2;
                        spanId = spanId2;
                        break;
                    case "tags":
                        map = (Map) objectReader.nextObjectOrNull();
                        strNextStringOrNull3 = str;
                        spanStatus = spanStatus2;
                        strNextStringOrNull2 = str2;
                        spanId = spanId2;
                        break;
                    case "timestamp":
                        try {
                            dValueOf2 = objectReader.nextDoubleOrNull();
                        } catch (NumberFormatException unused2) {
                            Date dateNextDateOrNull2 = objectReader.nextDateOrNull(iLogger);
                            dValueOf2 = dateNextDateOrNull2 != null ? Double.valueOf(DateUtils.dateToSeconds(dateNextDateOrNull2)) : null;
                        }
                        strNextStringOrNull3 = str;
                        spanStatus = spanStatus2;
                        strNextStringOrNull2 = str2;
                        spanId = spanId2;
                        break;
                    case "trace_id":
                        sentryIdDeserialize = new SentryId.Deserializer().deserialize(objectReader, iLogger);
                        strNextStringOrNull3 = str;
                        spanStatus = spanStatus2;
                        strNextStringOrNull2 = str2;
                        spanId = spanId2;
                        break;
                    default:
                        if (concurrentHashMap == null) {
                            concurrentHashMap = new ConcurrentHashMap();
                        }
                        objectReader.nextUnknown(iLogger, concurrentHashMap, strNextName);
                        strNextStringOrNull3 = str;
                        spanStatus = spanStatus2;
                        strNextStringOrNull2 = str2;
                        spanId = spanId2;
                        break;
                }
            }
        }

        private Exception missingRequiredFieldException(String str, ILogger iLogger) {
            String str2 = "Missing required field \"" + str + "\"";
            IllegalStateException illegalStateException = new IllegalStateException(str2);
            iLogger.log(SentryLevel.ERROR, str2, illegalStateException);
            return illegalStateException;
        }
    }
}
