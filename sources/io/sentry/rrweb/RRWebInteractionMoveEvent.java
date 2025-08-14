package io.sentry.rrweb;

import io.sentry.ILogger;
import io.sentry.JsonDeserializer;
import io.sentry.JsonSerializable;
import io.sentry.JsonUnknown;
import io.sentry.ObjectReader;
import io.sentry.ObjectWriter;
import io.sentry.rrweb.RRWebEvent;
import io.sentry.rrweb.RRWebIncrementalSnapshotEvent;
import io.sentry.vendor.gson.stream.JsonToken;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public final class RRWebInteractionMoveEvent extends RRWebIncrementalSnapshotEvent implements JsonSerializable, JsonUnknown {
    private Map<String, Object> dataUnknown;
    private int pointerId;
    private List<Position> positions;
    private Map<String, Object> unknown;

    public static final class JsonKeys {
        public static final String DATA = "data";
        public static final String POINTER_ID = "pointerId";
        public static final String POSITIONS = "positions";
    }

    public Map<String, Object> getDataUnknown() {
        return this.dataUnknown;
    }

    public int getPointerId() {
        return this.pointerId;
    }

    public List<Position> getPositions() {
        return this.positions;
    }

    @Override // io.sentry.JsonUnknown
    public Map<String, Object> getUnknown() {
        return this.unknown;
    }

    public void setDataUnknown(Map<String, Object> map) {
        this.dataUnknown = map;
    }

    public void setPointerId(int i) {
        this.pointerId = i;
    }

    public void setPositions(List<Position> list) {
        this.positions = list;
    }

    @Override // io.sentry.JsonUnknown
    public void setUnknown(Map<String, Object> map) {
        this.unknown = map;
    }

    public static final class Position implements JsonSerializable, JsonUnknown {
        private int id;
        private long timeOffset;
        private Map<String, Object> unknown;
        private float x;
        private float y;

        public static final class JsonKeys {
            public static final String ID = "id";
            public static final String TIME_OFFSET = "timeOffset";
            public static final String X = "x";
            public static final String Y = "y";
        }

        public int getId() {
            return this.id;
        }

        public long getTimeOffset() {
            return this.timeOffset;
        }

        @Override // io.sentry.JsonUnknown
        public Map<String, Object> getUnknown() {
            return this.unknown;
        }

        public float getX() {
            return this.x;
        }

        public float getY() {
            return this.y;
        }

        public void setId(int i) {
            this.id = i;
        }

        public void setTimeOffset(long j) {
            this.timeOffset = j;
        }

        @Override // io.sentry.JsonUnknown
        public void setUnknown(Map<String, Object> map) {
            this.unknown = map;
        }

        public void setX(float f) {
            this.x = f;
        }

        public void setY(float f) {
            this.y = f;
        }

        @Override // io.sentry.JsonSerializable
        public void serialize(ObjectWriter objectWriter, ILogger iLogger) throws IOException {
            objectWriter.beginObject();
            objectWriter.name("id").value(this.id);
            objectWriter.name("x").value(this.x);
            objectWriter.name("y").value(this.y);
            objectWriter.name(JsonKeys.TIME_OFFSET).value(this.timeOffset);
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

        public static final class Deserializer implements JsonDeserializer<Position> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.sentry.JsonDeserializer
            public Position deserialize(ObjectReader objectReader, ILogger iLogger) throws Exception {
                String strNextName;
                objectReader.beginObject();
                Position position = new Position();
                HashMap map = null;
                while (objectReader.peek() == JsonToken.NAME) {
                    strNextName = objectReader.nextName();
                    strNextName.hashCode();
                    switch (strNextName) {
                        case "x":
                            position.x = objectReader.nextFloat();
                            break;
                        case "y":
                            position.y = objectReader.nextFloat();
                            break;
                        case "id":
                            position.id = objectReader.nextInt();
                            break;
                        case "timeOffset":
                            position.timeOffset = objectReader.nextLong();
                            break;
                        default:
                            if (map == null) {
                                map = new HashMap();
                            }
                            objectReader.nextUnknown(iLogger, map, strNextName);
                            break;
                    }
                }
                position.setUnknown(map);
                objectReader.endObject();
                return position;
            }
        }
    }

    public RRWebInteractionMoveEvent() {
        super(RRWebIncrementalSnapshotEvent.IncrementalSource.TouchMove);
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
        new RRWebIncrementalSnapshotEvent.Serializer().serialize(this, objectWriter, iLogger);
        List<Position> list = this.positions;
        if (list != null && !list.isEmpty()) {
            objectWriter.name(JsonKeys.POSITIONS).value(iLogger, this.positions);
        }
        objectWriter.name("pointerId").value(this.pointerId);
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

    public static final class Deserializer implements JsonDeserializer<RRWebInteractionMoveEvent> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.sentry.JsonDeserializer
        public RRWebInteractionMoveEvent deserialize(ObjectReader objectReader, ILogger iLogger) throws Exception {
            objectReader.beginObject();
            RRWebInteractionMoveEvent rRWebInteractionMoveEvent = new RRWebInteractionMoveEvent();
            RRWebEvent.Deserializer deserializer = new RRWebEvent.Deserializer();
            HashMap map = null;
            while (objectReader.peek() == JsonToken.NAME) {
                String strNextName = objectReader.nextName();
                strNextName.hashCode();
                if (strNextName.equals("data")) {
                    deserializeData(rRWebInteractionMoveEvent, objectReader, iLogger);
                } else if (!deserializer.deserializeValue(rRWebInteractionMoveEvent, strNextName, objectReader, iLogger)) {
                    if (map == null) {
                        map = new HashMap();
                    }
                    objectReader.nextUnknown(iLogger, map, strNextName);
                }
            }
            rRWebInteractionMoveEvent.setUnknown(map);
            objectReader.endObject();
            return rRWebInteractionMoveEvent;
        }

        private void deserializeData(RRWebInteractionMoveEvent rRWebInteractionMoveEvent, ObjectReader objectReader, ILogger iLogger) throws Exception {
            RRWebIncrementalSnapshotEvent.Deserializer deserializer = new RRWebIncrementalSnapshotEvent.Deserializer();
            objectReader.beginObject();
            HashMap map = null;
            while (objectReader.peek() == JsonToken.NAME) {
                String strNextName = objectReader.nextName();
                strNextName.hashCode();
                if (strNextName.equals("pointerId")) {
                    rRWebInteractionMoveEvent.pointerId = objectReader.nextInt();
                } else if (strNextName.equals(JsonKeys.POSITIONS)) {
                    rRWebInteractionMoveEvent.positions = objectReader.nextListOrNull(iLogger, new Position.Deserializer());
                } else if (!deserializer.deserializeValue(rRWebInteractionMoveEvent, strNextName, objectReader, iLogger)) {
                    if (map == null) {
                        map = new HashMap();
                    }
                    objectReader.nextUnknown(iLogger, map, strNextName);
                }
            }
            rRWebInteractionMoveEvent.setDataUnknown(map);
            objectReader.endObject();
        }
    }
}
