package io.sentry.protocol;

import io.sentry.ILogger;
import io.sentry.JsonDeserializer;
import io.sentry.JsonSerializable;
import io.sentry.JsonUnknown;
import io.sentry.ObjectReader;
import io.sentry.ObjectWriter;
import io.sentry.util.CollectionUtils;
import io.sentry.vendor.gson.stream.JsonToken;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes6.dex */
public final class Mechanism implements JsonUnknown, JsonSerializable {
    private Map<String, Object> data;
    private String description;
    private Boolean handled;
    private String helpLink;
    private Map<String, Object> meta;
    private Boolean synthetic;
    private final transient Thread thread;
    private String type;
    private Map<String, Object> unknown;

    public static final class JsonKeys {
        public static final String DATA = "data";
        public static final String DESCRIPTION = "description";
        public static final String HANDLED = "handled";
        public static final String HELP_LINK = "help_link";
        public static final String META = "meta";
        public static final String SYNTHETIC = "synthetic";
        public static final String TYPE = "type";
    }

    public Map<String, Object> getData() {
        return this.data;
    }

    public String getDescription() {
        return this.description;
    }

    public String getHelpLink() {
        return this.helpLink;
    }

    public Map<String, Object> getMeta() {
        return this.meta;
    }

    public Boolean getSynthetic() {
        return this.synthetic;
    }

    Thread getThread() {
        return this.thread;
    }

    public String getType() {
        return this.type;
    }

    @Override // io.sentry.JsonUnknown
    public Map<String, Object> getUnknown() {
        return this.unknown;
    }

    public Boolean isHandled() {
        return this.handled;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setHandled(Boolean bool) {
        this.handled = bool;
    }

    public void setHelpLink(String str) {
        this.helpLink = str;
    }

    public void setSynthetic(Boolean bool) {
        this.synthetic = bool;
    }

    public void setType(String str) {
        this.type = str;
    }

    @Override // io.sentry.JsonUnknown
    public void setUnknown(Map<String, Object> map) {
        this.unknown = map;
    }

    public Mechanism() {
        this(null);
    }

    public Mechanism(Thread thread) {
        this.thread = thread;
    }

    public void setMeta(Map<String, Object> map) {
        this.meta = CollectionUtils.newHashMap(map);
    }

    public void setData(Map<String, Object> map) {
        this.data = CollectionUtils.newHashMap(map);
    }

    @Override // io.sentry.JsonSerializable
    public void serialize(ObjectWriter objectWriter, ILogger iLogger) throws IOException {
        objectWriter.beginObject();
        if (this.type != null) {
            objectWriter.name("type").value(this.type);
        }
        if (this.description != null) {
            objectWriter.name("description").value(this.description);
        }
        if (this.helpLink != null) {
            objectWriter.name(JsonKeys.HELP_LINK).value(this.helpLink);
        }
        if (this.handled != null) {
            objectWriter.name(JsonKeys.HANDLED).value(this.handled);
        }
        if (this.meta != null) {
            objectWriter.name("meta").value(iLogger, this.meta);
        }
        if (this.data != null) {
            objectWriter.name("data").value(iLogger, this.data);
        }
        if (this.synthetic != null) {
            objectWriter.name(JsonKeys.SYNTHETIC).value(this.synthetic);
        }
        Map<String, Object> map = this.unknown;
        if (map != null) {
            for (String str : map.keySet()) {
                objectWriter.name(str).value(iLogger, this.unknown.get(str));
            }
        }
        objectWriter.endObject();
    }

    public static final class Deserializer implements JsonDeserializer<Mechanism> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.sentry.JsonDeserializer
        public Mechanism deserialize(ObjectReader objectReader, ILogger iLogger) throws Exception {
            String strNextName;
            Mechanism mechanism = new Mechanism();
            objectReader.beginObject();
            HashMap map = null;
            while (objectReader.peek() == JsonToken.NAME) {
                strNextName = objectReader.nextName();
                strNextName.hashCode();
                switch (strNextName) {
                    case "description":
                        mechanism.description = objectReader.nextStringOrNull();
                        break;
                    case "data":
                        mechanism.data = CollectionUtils.newConcurrentHashMap((Map) objectReader.nextObjectOrNull());
                        break;
                    case "meta":
                        mechanism.meta = CollectionUtils.newConcurrentHashMap((Map) objectReader.nextObjectOrNull());
                        break;
                    case "type":
                        mechanism.type = objectReader.nextStringOrNull();
                        break;
                    case "handled":
                        mechanism.handled = objectReader.nextBooleanOrNull();
                        break;
                    case "synthetic":
                        mechanism.synthetic = objectReader.nextBooleanOrNull();
                        break;
                    case "help_link":
                        mechanism.helpLink = objectReader.nextStringOrNull();
                        break;
                    default:
                        if (map == null) {
                            map = new HashMap();
                        }
                        objectReader.nextUnknown(iLogger, map, strNextName);
                        break;
                }
            }
            objectReader.endObject();
            mechanism.setUnknown(map);
            return mechanism;
        }
    }
}
