package io.sentry.rrweb;

import io.sentry.ILogger;
import io.sentry.JsonDeserializer;
import io.sentry.JsonSerializable;
import io.sentry.JsonUnknown;
import io.sentry.ObjectReader;
import io.sentry.ObjectWriter;
import io.sentry.SentryLevel;
import io.sentry.rrweb.RRWebEvent;
import io.sentry.util.CollectionUtils;
import io.sentry.vendor.gson.stream.JsonToken;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes6.dex */
public final class RRWebBreadcrumbEvent extends RRWebEvent implements JsonUnknown, JsonSerializable {
    public static final String EVENT_TAG = "breadcrumb";
    private double breadcrumbTimestamp;
    private String breadcrumbType;
    private String category;
    private Map<String, Object> data;
    private Map<String, Object> dataUnknown;
    private SentryLevel level;
    private String message;
    private Map<String, Object> payloadUnknown;
    private String tag;
    private Map<String, Object> unknown;

    public static final class JsonKeys {
        public static final String CATEGORY = "category";
        public static final String DATA = "data";
        public static final String LEVEL = "level";
        public static final String MESSAGE = "message";
        public static final String PAYLOAD = "payload";
        public static final String TIMESTAMP = "timestamp";
        public static final String TYPE = "type";
    }

    public double getBreadcrumbTimestamp() {
        return this.breadcrumbTimestamp;
    }

    public String getBreadcrumbType() {
        return this.breadcrumbType;
    }

    public String getCategory() {
        return this.category;
    }

    public Map<String, Object> getData() {
        return this.data;
    }

    public Map<String, Object> getDataUnknown() {
        return this.dataUnknown;
    }

    public SentryLevel getLevel() {
        return this.level;
    }

    public String getMessage() {
        return this.message;
    }

    public Map<String, Object> getPayloadUnknown() {
        return this.payloadUnknown;
    }

    public String getTag() {
        return this.tag;
    }

    @Override // io.sentry.JsonUnknown
    public Map<String, Object> getUnknown() {
        return this.unknown;
    }

    public void setBreadcrumbTimestamp(double d) {
        this.breadcrumbTimestamp = d;
    }

    public void setBreadcrumbType(String str) {
        this.breadcrumbType = str;
    }

    public void setCategory(String str) {
        this.category = str;
    }

    public void setDataUnknown(Map<String, Object> map) {
        this.dataUnknown = map;
    }

    public void setLevel(SentryLevel sentryLevel) {
        this.level = sentryLevel;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setPayloadUnknown(Map<String, Object> map) {
        this.payloadUnknown = map;
    }

    public void setTag(String str) {
        this.tag = str;
    }

    @Override // io.sentry.JsonUnknown
    public void setUnknown(Map<String, Object> map) {
        this.unknown = map;
    }

    public RRWebBreadcrumbEvent() {
        super(RRWebEventType.Custom);
        this.tag = EVENT_TAG;
    }

    public void setData(Map<String, Object> map) {
        this.data = map == null ? null : new ConcurrentHashMap(map);
    }

    @Override // io.sentry.JsonSerializable
    public void serialize(ObjectWriter objectWriter, ILogger iLogger) throws IOException {
        objectWriter.beginObject();
        new RRWebEvent.Serializer().serialize(this, objectWriter, iLogger);
        objectWriter.name("data");
        serializeData(objectWriter, iLogger);
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

    private void serializeData(ObjectWriter objectWriter, ILogger iLogger) throws IOException {
        objectWriter.beginObject();
        objectWriter.name("tag").value(this.tag);
        objectWriter.name("payload");
        serializePayload(objectWriter, iLogger);
        Map<String, Object> map = this.dataUnknown;
        if (map != null) {
            for (String str : map.keySet()) {
                Object obj = this.dataUnknown.get(str);
                objectWriter.name(str);
                objectWriter.value(iLogger, obj);
            }
        }
        objectWriter.endObject();
    }

    private void serializePayload(ObjectWriter objectWriter, ILogger iLogger) throws IOException {
        objectWriter.beginObject();
        if (this.breadcrumbType != null) {
            objectWriter.name("type").value(this.breadcrumbType);
        }
        objectWriter.name("timestamp").value(iLogger, BigDecimal.valueOf(this.breadcrumbTimestamp));
        if (this.category != null) {
            objectWriter.name("category").value(this.category);
        }
        if (this.message != null) {
            objectWriter.name("message").value(this.message);
        }
        if (this.level != null) {
            objectWriter.name("level").value(iLogger, this.level);
        }
        if (this.data != null) {
            objectWriter.name("data").value(iLogger, this.data);
        }
        Map<String, Object> map = this.payloadUnknown;
        if (map != null) {
            for (String str : map.keySet()) {
                Object obj = this.payloadUnknown.get(str);
                objectWriter.name(str);
                objectWriter.value(iLogger, obj);
            }
        }
        objectWriter.endObject();
    }

    public static final class Deserializer implements JsonDeserializer<RRWebBreadcrumbEvent> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.sentry.JsonDeserializer
        public RRWebBreadcrumbEvent deserialize(ObjectReader objectReader, ILogger iLogger) throws Exception {
            objectReader.beginObject();
            RRWebBreadcrumbEvent rRWebBreadcrumbEvent = new RRWebBreadcrumbEvent();
            RRWebEvent.Deserializer deserializer = new RRWebEvent.Deserializer();
            HashMap map = null;
            while (objectReader.peek() == JsonToken.NAME) {
                String strNextName = objectReader.nextName();
                strNextName.hashCode();
                if (strNextName.equals("data")) {
                    deserializeData(rRWebBreadcrumbEvent, objectReader, iLogger);
                } else if (!deserializer.deserializeValue(rRWebBreadcrumbEvent, strNextName, objectReader, iLogger)) {
                    if (map == null) {
                        map = new HashMap();
                    }
                    objectReader.nextUnknown(iLogger, map, strNextName);
                }
            }
            rRWebBreadcrumbEvent.setUnknown(map);
            objectReader.endObject();
            return rRWebBreadcrumbEvent;
        }

        private void deserializeData(RRWebBreadcrumbEvent rRWebBreadcrumbEvent, ObjectReader objectReader, ILogger iLogger) throws Exception {
            objectReader.beginObject();
            ConcurrentHashMap concurrentHashMap = null;
            while (objectReader.peek() == JsonToken.NAME) {
                String strNextName = objectReader.nextName();
                strNextName.hashCode();
                if (strNextName.equals("payload")) {
                    deserializePayload(rRWebBreadcrumbEvent, objectReader, iLogger);
                } else if (strNextName.equals("tag")) {
                    String strNextStringOrNull = objectReader.nextStringOrNull();
                    if (strNextStringOrNull == null) {
                        strNextStringOrNull = "";
                    }
                    rRWebBreadcrumbEvent.tag = strNextStringOrNull;
                } else {
                    if (concurrentHashMap == null) {
                        concurrentHashMap = new ConcurrentHashMap();
                    }
                    objectReader.nextUnknown(iLogger, concurrentHashMap, strNextName);
                }
            }
            rRWebBreadcrumbEvent.setDataUnknown(concurrentHashMap);
            objectReader.endObject();
        }

        private void deserializePayload(RRWebBreadcrumbEvent rRWebBreadcrumbEvent, ObjectReader objectReader, ILogger iLogger) throws Exception {
            String strNextName;
            objectReader.beginObject();
            ConcurrentHashMap concurrentHashMap = null;
            while (objectReader.peek() == JsonToken.NAME) {
                strNextName = objectReader.nextName();
                strNextName.hashCode();
                switch (strNextName) {
                    case "data":
                        Map mapNewConcurrentHashMap = CollectionUtils.newConcurrentHashMap((Map) objectReader.nextObjectOrNull());
                        if (mapNewConcurrentHashMap == null) {
                            break;
                        } else {
                            rRWebBreadcrumbEvent.data = mapNewConcurrentHashMap;
                            break;
                        }
                    case "type":
                        rRWebBreadcrumbEvent.breadcrumbType = objectReader.nextStringOrNull();
                        break;
                    case "category":
                        rRWebBreadcrumbEvent.category = objectReader.nextStringOrNull();
                        break;
                    case "timestamp":
                        rRWebBreadcrumbEvent.breadcrumbTimestamp = objectReader.nextDouble();
                        break;
                    case "level":
                        try {
                            rRWebBreadcrumbEvent.level = new SentryLevel.Deserializer().deserialize(objectReader, iLogger);
                            break;
                        } catch (Exception e) {
                            iLogger.log(SentryLevel.DEBUG, e, "Error when deserializing SentryLevel", new Object[0]);
                            break;
                        }
                    case "message":
                        rRWebBreadcrumbEvent.message = objectReader.nextStringOrNull();
                        break;
                    default:
                        if (concurrentHashMap == null) {
                            concurrentHashMap = new ConcurrentHashMap();
                        }
                        objectReader.nextUnknown(iLogger, concurrentHashMap, strNextName);
                        break;
                }
            }
            rRWebBreadcrumbEvent.setPayloadUnknown(concurrentHashMap);
            objectReader.endObject();
        }
    }
}
