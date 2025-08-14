package io.sentry;

import io.sentry.util.Objects;
import io.sentry.vendor.gson.stream.JsonToken;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes6.dex */
public final class ProfilingTransactionData implements JsonUnknown, JsonSerializable {
    private String id;
    private String name;
    private Long relativeEndCpuMs;
    private Long relativeEndNs;
    private Long relativeStartCpuMs;
    private Long relativeStartNs;
    private String traceId;
    private Map<String, Object> unknown;

    public static final class JsonKeys {
        public static final String END_CPU_MS = "relative_cpu_end_ms";
        public static final String END_NS = "relative_end_ns";
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String START_CPU_MS = "relative_cpu_start_ms";
        public static final String START_NS = "relative_start_ns";
        public static final String TRACE_ID = "trace_id";
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Long getRelativeEndCpuMs() {
        return this.relativeEndCpuMs;
    }

    public Long getRelativeEndNs() {
        return this.relativeEndNs;
    }

    public Long getRelativeStartCpuMs() {
        return this.relativeStartCpuMs;
    }

    public Long getRelativeStartNs() {
        return this.relativeStartNs;
    }

    public String getTraceId() {
        return this.traceId;
    }

    @Override // io.sentry.JsonUnknown
    public Map<String, Object> getUnknown() {
        return this.unknown;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setRelativeEndNs(Long l) {
        this.relativeEndNs = l;
    }

    public void setRelativeStartNs(Long l) {
        this.relativeStartNs = l;
    }

    public void setTraceId(String str) {
        this.traceId = str;
    }

    @Override // io.sentry.JsonUnknown
    public void setUnknown(Map<String, Object> map) {
        this.unknown = map;
    }

    public ProfilingTransactionData() {
        this(NoOpTransaction.getInstance(), 0L, 0L);
    }

    public ProfilingTransactionData(ITransaction iTransaction, Long l, Long l2) {
        this.id = iTransaction.getEventId().toString();
        this.traceId = iTransaction.getSpanContext().getTraceId().toString();
        this.name = iTransaction.getName().isEmpty() ? "unknown" : iTransaction.getName();
        this.relativeStartNs = l;
        this.relativeStartCpuMs = l2;
    }

    public void notifyFinish(Long l, Long l2, Long l3, Long l4) {
        if (this.relativeEndNs == null) {
            this.relativeEndNs = Long.valueOf(l.longValue() - l2.longValue());
            this.relativeStartNs = Long.valueOf(this.relativeStartNs.longValue() - l2.longValue());
            this.relativeEndCpuMs = Long.valueOf(l3.longValue() - l4.longValue());
            this.relativeStartCpuMs = Long.valueOf(this.relativeStartCpuMs.longValue() - l4.longValue());
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ProfilingTransactionData profilingTransactionData = (ProfilingTransactionData) obj;
        return this.id.equals(profilingTransactionData.id) && this.traceId.equals(profilingTransactionData.traceId) && this.name.equals(profilingTransactionData.name) && this.relativeStartNs.equals(profilingTransactionData.relativeStartNs) && this.relativeStartCpuMs.equals(profilingTransactionData.relativeStartCpuMs) && Objects.equals(this.relativeEndCpuMs, profilingTransactionData.relativeEndCpuMs) && Objects.equals(this.relativeEndNs, profilingTransactionData.relativeEndNs) && Objects.equals(this.unknown, profilingTransactionData.unknown);
    }

    public int hashCode() {
        return Objects.hash(this.id, this.traceId, this.name, this.relativeStartNs, this.relativeEndNs, this.relativeStartCpuMs, this.relativeEndCpuMs, this.unknown);
    }

    @Override // io.sentry.JsonSerializable
    public void serialize(ObjectWriter objectWriter, ILogger iLogger) throws IOException {
        objectWriter.beginObject();
        objectWriter.name("id").value(iLogger, this.id);
        objectWriter.name("trace_id").value(iLogger, this.traceId);
        objectWriter.name("name").value(iLogger, this.name);
        objectWriter.name(JsonKeys.START_NS).value(iLogger, this.relativeStartNs);
        objectWriter.name(JsonKeys.END_NS).value(iLogger, this.relativeEndNs);
        objectWriter.name(JsonKeys.START_CPU_MS).value(iLogger, this.relativeStartCpuMs);
        objectWriter.name(JsonKeys.END_CPU_MS).value(iLogger, this.relativeEndCpuMs);
        Map<String, Object> map = this.unknown;
        if (map != null) {
            for (String str : map.keySet()) {
                Object obj = this.unknown.get(str);
                objectWriter.name(str);
                objectWriter.value(iLogger, obj);
            }
        }
        objectWriter.endObject();
    }

    public static final class Deserializer implements JsonDeserializer<ProfilingTransactionData> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.sentry.JsonDeserializer
        public ProfilingTransactionData deserialize(ObjectReader objectReader, ILogger iLogger) throws Exception {
            String strNextName;
            objectReader.beginObject();
            ProfilingTransactionData profilingTransactionData = new ProfilingTransactionData();
            ConcurrentHashMap concurrentHashMap = null;
            while (objectReader.peek() == JsonToken.NAME) {
                strNextName = objectReader.nextName();
                strNextName.hashCode();
                switch (strNextName) {
                    case "relative_start_ns":
                        Long lNextLongOrNull = objectReader.nextLongOrNull();
                        if (lNextLongOrNull == null) {
                            break;
                        } else {
                            profilingTransactionData.relativeStartNs = lNextLongOrNull;
                            break;
                        }
                    case "relative_end_ns":
                        Long lNextLongOrNull2 = objectReader.nextLongOrNull();
                        if (lNextLongOrNull2 == null) {
                            break;
                        } else {
                            profilingTransactionData.relativeEndNs = lNextLongOrNull2;
                            break;
                        }
                    case "id":
                        String strNextStringOrNull = objectReader.nextStringOrNull();
                        if (strNextStringOrNull == null) {
                            break;
                        } else {
                            profilingTransactionData.id = strNextStringOrNull;
                            break;
                        }
                    case "name":
                        String strNextStringOrNull2 = objectReader.nextStringOrNull();
                        if (strNextStringOrNull2 == null) {
                            break;
                        } else {
                            profilingTransactionData.name = strNextStringOrNull2;
                            break;
                        }
                    case "trace_id":
                        String strNextStringOrNull3 = objectReader.nextStringOrNull();
                        if (strNextStringOrNull3 == null) {
                            break;
                        } else {
                            profilingTransactionData.traceId = strNextStringOrNull3;
                            break;
                        }
                    case "relative_cpu_end_ms":
                        Long lNextLongOrNull3 = objectReader.nextLongOrNull();
                        if (lNextLongOrNull3 == null) {
                            break;
                        } else {
                            profilingTransactionData.relativeEndCpuMs = lNextLongOrNull3;
                            break;
                        }
                    case "relative_cpu_start_ms":
                        Long lNextLongOrNull4 = objectReader.nextLongOrNull();
                        if (lNextLongOrNull4 == null) {
                            break;
                        } else {
                            profilingTransactionData.relativeStartCpuMs = lNextLongOrNull4;
                            break;
                        }
                    default:
                        if (concurrentHashMap == null) {
                            concurrentHashMap = new ConcurrentHashMap();
                        }
                        objectReader.nextUnknown(iLogger, concurrentHashMap, strNextName);
                        break;
                }
            }
            profilingTransactionData.setUnknown(concurrentHashMap);
            objectReader.endObject();
            return profilingTransactionData;
        }
    }
}
