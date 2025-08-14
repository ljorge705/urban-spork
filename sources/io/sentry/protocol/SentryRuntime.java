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
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes6.dex */
public final class SentryRuntime implements JsonUnknown, JsonSerializable {
    public static final String TYPE = "runtime";
    private String name;
    private String rawDescription;
    private Map<String, Object> unknown;
    private String version;

    public static final class JsonKeys {
        public static final String NAME = "name";
        public static final String RAW_DESCRIPTION = "raw_description";
        public static final String VERSION = "version";
    }

    public String getName() {
        return this.name;
    }

    public String getRawDescription() {
        return this.rawDescription;
    }

    @Override // io.sentry.JsonUnknown
    public Map<String, Object> getUnknown() {
        return this.unknown;
    }

    public String getVersion() {
        return this.version;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setRawDescription(String str) {
        this.rawDescription = str;
    }

    @Override // io.sentry.JsonUnknown
    public void setUnknown(Map<String, Object> map) {
        this.unknown = map;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public SentryRuntime() {
    }

    SentryRuntime(SentryRuntime sentryRuntime) {
        this.name = sentryRuntime.name;
        this.version = sentryRuntime.version;
        this.rawDescription = sentryRuntime.rawDescription;
        this.unknown = CollectionUtils.newConcurrentHashMap(sentryRuntime.unknown);
    }

    @Override // io.sentry.JsonSerializable
    public void serialize(ObjectWriter objectWriter, ILogger iLogger) throws IOException {
        objectWriter.beginObject();
        if (this.name != null) {
            objectWriter.name("name").value(this.name);
        }
        if (this.version != null) {
            objectWriter.name("version").value(this.version);
        }
        if (this.rawDescription != null) {
            objectWriter.name("raw_description").value(this.rawDescription);
        }
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

    public static final class Deserializer implements JsonDeserializer<SentryRuntime> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.sentry.JsonDeserializer
        public SentryRuntime deserialize(ObjectReader objectReader, ILogger iLogger) throws Exception {
            String strNextName;
            objectReader.beginObject();
            SentryRuntime sentryRuntime = new SentryRuntime();
            ConcurrentHashMap concurrentHashMap = null;
            while (objectReader.peek() == JsonToken.NAME) {
                strNextName = objectReader.nextName();
                strNextName.hashCode();
                switch (strNextName) {
                    case "raw_description":
                        sentryRuntime.rawDescription = objectReader.nextStringOrNull();
                        break;
                    case "name":
                        sentryRuntime.name = objectReader.nextStringOrNull();
                        break;
                    case "version":
                        sentryRuntime.version = objectReader.nextStringOrNull();
                        break;
                    default:
                        if (concurrentHashMap == null) {
                            concurrentHashMap = new ConcurrentHashMap();
                        }
                        objectReader.nextUnknown(iLogger, concurrentHashMap, strNextName);
                        break;
                }
            }
            sentryRuntime.setUnknown(concurrentHashMap);
            objectReader.endObject();
            return sentryRuntime;
        }
    }
}
