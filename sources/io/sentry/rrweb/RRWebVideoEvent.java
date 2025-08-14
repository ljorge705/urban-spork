package io.sentry.rrweb;

import io.sentry.ILogger;
import io.sentry.JsonDeserializer;
import io.sentry.JsonSerializable;
import io.sentry.JsonUnknown;
import io.sentry.ObjectReader;
import io.sentry.ObjectWriter;
import io.sentry.rrweb.RRWebEvent;
import io.sentry.util.Objects;
import io.sentry.vendor.gson.stream.JsonToken;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes6.dex */
public final class RRWebVideoEvent extends RRWebEvent implements JsonUnknown, JsonSerializable {
    public static final String EVENT_TAG = "video";
    public static final String REPLAY_CONTAINER = "mp4";
    public static final String REPLAY_ENCODING = "h264";
    public static final String REPLAY_FRAME_RATE_TYPE_CONSTANT = "constant";
    public static final String REPLAY_FRAME_RATE_TYPE_VARIABLE = "variable";
    private String container;
    private Map<String, Object> dataUnknown;
    private long durationMs;
    private String encoding;
    private int frameCount;
    private int frameRate;
    private String frameRateType;
    private int height;
    private int left;
    private Map<String, Object> payloadUnknown;
    private int segmentId;
    private long size;
    private String tag;
    private int top;
    private Map<String, Object> unknown;
    private int width;

    public static final class JsonKeys {
        public static final String CONTAINER = "container";
        public static final String DATA = "data";
        public static final String DURATION = "duration";
        public static final String ENCODING = "encoding";
        public static final String FRAME_COUNT = "frameCount";
        public static final String FRAME_RATE = "frameRate";
        public static final String FRAME_RATE_TYPE = "frameRateType";
        public static final String HEIGHT = "height";
        public static final String LEFT = "left";
        public static final String PAYLOAD = "payload";
        public static final String SEGMENT_ID = "segmentId";
        public static final String SIZE = "size";
        public static final String TOP = "top";
        public static final String WIDTH = "width";
    }

    public String getContainer() {
        return this.container;
    }

    public Map<String, Object> getDataUnknown() {
        return this.dataUnknown;
    }

    public long getDurationMs() {
        return this.durationMs;
    }

    public String getEncoding() {
        return this.encoding;
    }

    public int getFrameCount() {
        return this.frameCount;
    }

    public int getFrameRate() {
        return this.frameRate;
    }

    public String getFrameRateType() {
        return this.frameRateType;
    }

    public int getHeight() {
        return this.height;
    }

    public int getLeft() {
        return this.left;
    }

    public Map<String, Object> getPayloadUnknown() {
        return this.payloadUnknown;
    }

    public int getSegmentId() {
        return this.segmentId;
    }

    public long getSize() {
        return this.size;
    }

    public String getTag() {
        return this.tag;
    }

    public int getTop() {
        return this.top;
    }

    @Override // io.sentry.JsonUnknown
    public Map<String, Object> getUnknown() {
        return this.unknown;
    }

    public int getWidth() {
        return this.width;
    }

    public void setContainer(String str) {
        this.container = str;
    }

    public void setDataUnknown(Map<String, Object> map) {
        this.dataUnknown = map;
    }

    public void setDurationMs(long j) {
        this.durationMs = j;
    }

    public void setEncoding(String str) {
        this.encoding = str;
    }

    public void setFrameCount(int i) {
        this.frameCount = i;
    }

    public void setFrameRate(int i) {
        this.frameRate = i;
    }

    public void setFrameRateType(String str) {
        this.frameRateType = str;
    }

    public void setHeight(int i) {
        this.height = i;
    }

    public void setLeft(int i) {
        this.left = i;
    }

    public void setPayloadUnknown(Map<String, Object> map) {
        this.payloadUnknown = map;
    }

    public void setSegmentId(int i) {
        this.segmentId = i;
    }

    public void setSize(long j) {
        this.size = j;
    }

    public void setTag(String str) {
        this.tag = str;
    }

    public void setTop(int i) {
        this.top = i;
    }

    @Override // io.sentry.JsonUnknown
    public void setUnknown(Map<String, Object> map) {
        this.unknown = map;
    }

    public void setWidth(int i) {
        this.width = i;
    }

    public RRWebVideoEvent() {
        super(RRWebEventType.Custom);
        this.encoding = REPLAY_ENCODING;
        this.container = REPLAY_CONTAINER;
        this.frameRateType = REPLAY_FRAME_RATE_TYPE_CONSTANT;
        this.tag = "video";
    }

    @Override // io.sentry.rrweb.RRWebEvent
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass() || !super.equals(obj)) {
            return false;
        }
        RRWebVideoEvent rRWebVideoEvent = (RRWebVideoEvent) obj;
        return this.segmentId == rRWebVideoEvent.segmentId && this.size == rRWebVideoEvent.size && this.durationMs == rRWebVideoEvent.durationMs && this.height == rRWebVideoEvent.height && this.width == rRWebVideoEvent.width && this.frameCount == rRWebVideoEvent.frameCount && this.frameRate == rRWebVideoEvent.frameRate && this.left == rRWebVideoEvent.left && this.top == rRWebVideoEvent.top && Objects.equals(this.tag, rRWebVideoEvent.tag) && Objects.equals(this.encoding, rRWebVideoEvent.encoding) && Objects.equals(this.container, rRWebVideoEvent.container) && Objects.equals(this.frameRateType, rRWebVideoEvent.frameRateType);
    }

    @Override // io.sentry.rrweb.RRWebEvent
    public int hashCode() {
        return Objects.hash(Integer.valueOf(super.hashCode()), this.tag, Integer.valueOf(this.segmentId), Long.valueOf(this.size), Long.valueOf(this.durationMs), this.encoding, this.container, Integer.valueOf(this.height), Integer.valueOf(this.width), Integer.valueOf(this.frameCount), this.frameRateType, Integer.valueOf(this.frameRate), Integer.valueOf(this.left), Integer.valueOf(this.top));
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
        objectWriter.name(JsonKeys.SEGMENT_ID).value(this.segmentId);
        objectWriter.name(JsonKeys.SIZE).value(this.size);
        objectWriter.name("duration").value(this.durationMs);
        objectWriter.name(JsonKeys.ENCODING).value(this.encoding);
        objectWriter.name(JsonKeys.CONTAINER).value(this.container);
        objectWriter.name("height").value(this.height);
        objectWriter.name("width").value(this.width);
        objectWriter.name(JsonKeys.FRAME_COUNT).value(this.frameCount);
        objectWriter.name(JsonKeys.FRAME_RATE).value(this.frameRate);
        objectWriter.name(JsonKeys.FRAME_RATE_TYPE).value(this.frameRateType);
        objectWriter.name("left").value(this.left);
        objectWriter.name("top").value(this.top);
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

    public static final class Deserializer implements JsonDeserializer<RRWebVideoEvent> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.sentry.JsonDeserializer
        public RRWebVideoEvent deserialize(ObjectReader objectReader, ILogger iLogger) throws Exception {
            objectReader.beginObject();
            RRWebVideoEvent rRWebVideoEvent = new RRWebVideoEvent();
            RRWebEvent.Deserializer deserializer = new RRWebEvent.Deserializer();
            HashMap map = null;
            while (objectReader.peek() == JsonToken.NAME) {
                String strNextName = objectReader.nextName();
                strNextName.hashCode();
                if (strNextName.equals("data")) {
                    deserializeData(rRWebVideoEvent, objectReader, iLogger);
                } else if (!deserializer.deserializeValue(rRWebVideoEvent, strNextName, objectReader, iLogger)) {
                    if (map == null) {
                        map = new HashMap();
                    }
                    objectReader.nextUnknown(iLogger, map, strNextName);
                }
            }
            rRWebVideoEvent.setUnknown(map);
            objectReader.endObject();
            return rRWebVideoEvent;
        }

        private void deserializeData(RRWebVideoEvent rRWebVideoEvent, ObjectReader objectReader, ILogger iLogger) throws Exception {
            objectReader.beginObject();
            ConcurrentHashMap concurrentHashMap = null;
            while (objectReader.peek() == JsonToken.NAME) {
                String strNextName = objectReader.nextName();
                strNextName.hashCode();
                if (strNextName.equals("payload")) {
                    deserializePayload(rRWebVideoEvent, objectReader, iLogger);
                } else if (strNextName.equals("tag")) {
                    String strNextStringOrNull = objectReader.nextStringOrNull();
                    if (strNextStringOrNull == null) {
                        strNextStringOrNull = "";
                    }
                    rRWebVideoEvent.tag = strNextStringOrNull;
                } else {
                    if (concurrentHashMap == null) {
                        concurrentHashMap = new ConcurrentHashMap();
                    }
                    objectReader.nextUnknown(iLogger, concurrentHashMap, strNextName);
                }
            }
            rRWebVideoEvent.setDataUnknown(concurrentHashMap);
            objectReader.endObject();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        private void deserializePayload(RRWebVideoEvent rRWebVideoEvent, ObjectReader objectReader, ILogger iLogger) throws Exception {
            objectReader.beginObject();
            ConcurrentHashMap concurrentHashMap = null;
            while (objectReader.peek() == JsonToken.NAME) {
                String strNextName = objectReader.nextName();
                strNextName.hashCode();
                char c = 65535;
                switch (strNextName.hashCode()) {
                    case -1992012396:
                        if (strNextName.equals("duration")) {
                            c = 0;
                            break;
                        }
                        break;
                    case -1627805778:
                        if (strNextName.equals(JsonKeys.SEGMENT_ID)) {
                            c = 1;
                            break;
                        }
                        break;
                    case -1221029593:
                        if (strNextName.equals("height")) {
                            c = 2;
                            break;
                        }
                        break;
                    case -410956671:
                        if (strNextName.equals(JsonKeys.CONTAINER)) {
                            c = 3;
                            break;
                        }
                        break;
                    case -296512606:
                        if (strNextName.equals(JsonKeys.FRAME_COUNT)) {
                            c = 4;
                            break;
                        }
                        break;
                    case 115029:
                        if (strNextName.equals("top")) {
                            c = 5;
                            break;
                        }
                        break;
                    case 3317767:
                        if (strNextName.equals("left")) {
                            c = 6;
                            break;
                        }
                        break;
                    case 3530753:
                        if (strNextName.equals(JsonKeys.SIZE)) {
                            c = 7;
                            break;
                        }
                        break;
                    case 113126854:
                        if (strNextName.equals("width")) {
                            c = '\b';
                            break;
                        }
                        break;
                    case 545057773:
                        if (strNextName.equals(JsonKeys.FRAME_RATE)) {
                            c = '\t';
                            break;
                        }
                        break;
                    case 1711222099:
                        if (strNextName.equals(JsonKeys.ENCODING)) {
                            c = '\n';
                            break;
                        }
                        break;
                    case 2135109831:
                        if (strNextName.equals(JsonKeys.FRAME_RATE_TYPE)) {
                            c = 11;
                            break;
                        }
                        break;
                }
                switch (c) {
                    case 0:
                        rRWebVideoEvent.durationMs = objectReader.nextLong();
                        break;
                    case 1:
                        rRWebVideoEvent.segmentId = objectReader.nextInt();
                        break;
                    case 2:
                        Integer numNextIntegerOrNull = objectReader.nextIntegerOrNull();
                        rRWebVideoEvent.height = numNextIntegerOrNull != null ? numNextIntegerOrNull.intValue() : 0;
                        break;
                    case 3:
                        String strNextStringOrNull = objectReader.nextStringOrNull();
                        rRWebVideoEvent.container = strNextStringOrNull != null ? strNextStringOrNull : "";
                        break;
                    case 4:
                        Integer numNextIntegerOrNull2 = objectReader.nextIntegerOrNull();
                        rRWebVideoEvent.frameCount = numNextIntegerOrNull2 != null ? numNextIntegerOrNull2.intValue() : 0;
                        break;
                    case 5:
                        Integer numNextIntegerOrNull3 = objectReader.nextIntegerOrNull();
                        rRWebVideoEvent.top = numNextIntegerOrNull3 != null ? numNextIntegerOrNull3.intValue() : 0;
                        break;
                    case 6:
                        Integer numNextIntegerOrNull4 = objectReader.nextIntegerOrNull();
                        rRWebVideoEvent.left = numNextIntegerOrNull4 != null ? numNextIntegerOrNull4.intValue() : 0;
                        break;
                    case 7:
                        Long lNextLongOrNull = objectReader.nextLongOrNull();
                        rRWebVideoEvent.size = lNextLongOrNull == null ? 0L : lNextLongOrNull.longValue();
                        break;
                    case '\b':
                        Integer numNextIntegerOrNull5 = objectReader.nextIntegerOrNull();
                        rRWebVideoEvent.width = numNextIntegerOrNull5 != null ? numNextIntegerOrNull5.intValue() : 0;
                        break;
                    case '\t':
                        Integer numNextIntegerOrNull6 = objectReader.nextIntegerOrNull();
                        rRWebVideoEvent.frameRate = numNextIntegerOrNull6 != null ? numNextIntegerOrNull6.intValue() : 0;
                        break;
                    case '\n':
                        String strNextStringOrNull2 = objectReader.nextStringOrNull();
                        rRWebVideoEvent.encoding = strNextStringOrNull2 != null ? strNextStringOrNull2 : "";
                        break;
                    case 11:
                        String strNextStringOrNull3 = objectReader.nextStringOrNull();
                        rRWebVideoEvent.frameRateType = strNextStringOrNull3 != null ? strNextStringOrNull3 : "";
                        break;
                    default:
                        if (concurrentHashMap == null) {
                            concurrentHashMap = new ConcurrentHashMap();
                        }
                        objectReader.nextUnknown(iLogger, concurrentHashMap, strNextName);
                        break;
                }
            }
            rRWebVideoEvent.setPayloadUnknown(concurrentHashMap);
            objectReader.endObject();
        }
    }
}
