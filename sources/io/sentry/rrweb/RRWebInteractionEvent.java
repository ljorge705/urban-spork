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
import java.util.Map;

/* loaded from: classes6.dex */
public final class RRWebInteractionEvent extends RRWebIncrementalSnapshotEvent implements JsonSerializable, JsonUnknown {
    private static final int POINTER_TYPE_TOUCH = 2;
    private Map<String, Object> dataUnknown;
    private int id;
    private InteractionType interactionType;
    private int pointerId;
    private int pointerType;
    private Map<String, Object> unknown;
    private float x;
    private float y;

    public static final class JsonKeys {
        public static final String DATA = "data";
        public static final String ID = "id";
        public static final String POINTER_ID = "pointerId";
        public static final String POINTER_TYPE = "pointerType";
        public static final String TYPE = "type";
        public static final String X = "x";
        public static final String Y = "y";
    }

    public Map<String, Object> getDataUnknown() {
        return this.dataUnknown;
    }

    public int getId() {
        return this.id;
    }

    public InteractionType getInteractionType() {
        return this.interactionType;
    }

    public int getPointerId() {
        return this.pointerId;
    }

    public int getPointerType() {
        return this.pointerType;
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

    public void setDataUnknown(Map<String, Object> map) {
        this.dataUnknown = map;
    }

    public void setId(int i) {
        this.id = i;
    }

    public void setInteractionType(InteractionType interactionType) {
        this.interactionType = interactionType;
    }

    public void setPointerId(int i) {
        this.pointerId = i;
    }

    public void setPointerType(int i) {
        this.pointerType = i;
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

    public enum InteractionType implements JsonSerializable {
        MouseUp,
        MouseDown,
        Click,
        ContextMenu,
        DblClick,
        Focus,
        Blur,
        TouchStart,
        TouchMove_Departed,
        TouchEnd,
        TouchCancel;

        @Override // io.sentry.JsonSerializable
        public void serialize(ObjectWriter objectWriter, ILogger iLogger) throws IOException {
            objectWriter.value(ordinal());
        }

        public static final class Deserializer implements JsonDeserializer<InteractionType> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.sentry.JsonDeserializer
            public InteractionType deserialize(ObjectReader objectReader, ILogger iLogger) throws Exception {
                return InteractionType.values()[objectReader.nextInt()];
            }
        }
    }

    public RRWebInteractionEvent() {
        super(RRWebIncrementalSnapshotEvent.IncrementalSource.MouseInteraction);
        this.pointerType = 2;
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
        objectWriter.name("type").value(iLogger, this.interactionType);
        objectWriter.name("id").value(this.id);
        objectWriter.name("x").value(this.x);
        objectWriter.name("y").value(this.y);
        objectWriter.name(JsonKeys.POINTER_TYPE).value(this.pointerType);
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

    public static final class Deserializer implements JsonDeserializer<RRWebInteractionEvent> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.sentry.JsonDeserializer
        public RRWebInteractionEvent deserialize(ObjectReader objectReader, ILogger iLogger) throws Exception {
            objectReader.beginObject();
            RRWebInteractionEvent rRWebInteractionEvent = new RRWebInteractionEvent();
            RRWebEvent.Deserializer deserializer = new RRWebEvent.Deserializer();
            HashMap map = null;
            while (objectReader.peek() == JsonToken.NAME) {
                String strNextName = objectReader.nextName();
                strNextName.hashCode();
                if (strNextName.equals("data")) {
                    deserializeData(rRWebInteractionEvent, objectReader, iLogger);
                } else if (!deserializer.deserializeValue(rRWebInteractionEvent, strNextName, objectReader, iLogger)) {
                    if (map == null) {
                        map = new HashMap();
                    }
                    objectReader.nextUnknown(iLogger, map, strNextName);
                }
            }
            rRWebInteractionEvent.setUnknown(map);
            objectReader.endObject();
            return rRWebInteractionEvent;
        }

        private void deserializeData(RRWebInteractionEvent rRWebInteractionEvent, ObjectReader objectReader, ILogger iLogger) throws Exception {
            String strNextName;
            RRWebIncrementalSnapshotEvent.Deserializer deserializer = new RRWebIncrementalSnapshotEvent.Deserializer();
            objectReader.beginObject();
            HashMap map = null;
            while (objectReader.peek() == JsonToken.NAME) {
                strNextName = objectReader.nextName();
                strNextName.hashCode();
                switch (strNextName) {
                    case "x":
                        rRWebInteractionEvent.x = objectReader.nextFloat();
                        break;
                    case "y":
                        rRWebInteractionEvent.y = objectReader.nextFloat();
                        break;
                    case "id":
                        rRWebInteractionEvent.id = objectReader.nextInt();
                        break;
                    case "type":
                        rRWebInteractionEvent.interactionType = (InteractionType) objectReader.nextOrNull(iLogger, new InteractionType.Deserializer());
                        break;
                    case "pointerType":
                        rRWebInteractionEvent.pointerType = objectReader.nextInt();
                        break;
                    case "pointerId":
                        rRWebInteractionEvent.pointerId = objectReader.nextInt();
                        break;
                    default:
                        if (!deserializer.deserializeValue(rRWebInteractionEvent, strNextName, objectReader, iLogger)) {
                            if (map == null) {
                                map = new HashMap();
                            }
                            objectReader.nextUnknown(iLogger, map, strNextName);
                            break;
                        } else {
                            break;
                        }
                }
            }
            rRWebInteractionEvent.setDataUnknown(map);
            objectReader.endObject();
        }
    }
}
