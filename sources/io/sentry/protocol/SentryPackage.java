package io.sentry.protocol;

import io.sentry.ILogger;
import io.sentry.JsonDeserializer;
import io.sentry.JsonSerializable;
import io.sentry.JsonUnknown;
import io.sentry.ObjectReader;
import io.sentry.ObjectWriter;
import io.sentry.SentryLevel;
import io.sentry.util.Objects;
import io.sentry.vendor.gson.stream.JsonToken;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes6.dex */
public final class SentryPackage implements JsonUnknown, JsonSerializable {
    private String name;
    private Map<String, Object> unknown;
    private String version;

    public static final class JsonKeys {
        public static final String NAME = "name";
        public static final String VERSION = "version";
    }

    public String getName() {
        return this.name;
    }

    @Override // io.sentry.JsonUnknown
    public Map<String, Object> getUnknown() {
        return this.unknown;
    }

    public String getVersion() {
        return this.version;
    }

    @Override // io.sentry.JsonUnknown
    public void setUnknown(Map<String, Object> map) {
        this.unknown = map;
    }

    public SentryPackage(String str, String str2) {
        this.name = (String) Objects.requireNonNull(str, "name is required.");
        this.version = (String) Objects.requireNonNull(str2, "version is required.");
    }

    public void setName(String str) {
        this.name = (String) Objects.requireNonNull(str, "name is required.");
    }

    public void setVersion(String str) {
        this.version = (String) Objects.requireNonNull(str, "version is required.");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SentryPackage sentryPackage = (SentryPackage) obj;
        return java.util.Objects.equals(this.name, sentryPackage.name) && java.util.Objects.equals(this.version, sentryPackage.version);
    }

    public int hashCode() {
        return java.util.Objects.hash(this.name, this.version);
    }

    @Override // io.sentry.JsonSerializable
    public void serialize(ObjectWriter objectWriter, ILogger iLogger) throws IOException {
        objectWriter.beginObject();
        objectWriter.name("name").value(this.name);
        objectWriter.name("version").value(this.version);
        Map<String, Object> map = this.unknown;
        if (map != null) {
            for (String str : map.keySet()) {
                objectWriter.name(str).value(iLogger, this.unknown.get(str));
            }
        }
        objectWriter.endObject();
    }

    public static final class Deserializer implements JsonDeserializer<SentryPackage> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.sentry.JsonDeserializer
        public SentryPackage deserialize(ObjectReader objectReader, ILogger iLogger) throws Exception {
            objectReader.beginObject();
            String strNextString = null;
            String strNextString2 = null;
            HashMap map = null;
            while (objectReader.peek() == JsonToken.NAME) {
                String strNextName = objectReader.nextName();
                strNextName.hashCode();
                if (strNextName.equals("name")) {
                    strNextString = objectReader.nextString();
                } else if (strNextName.equals("version")) {
                    strNextString2 = objectReader.nextString();
                } else {
                    if (map == null) {
                        map = new HashMap();
                    }
                    objectReader.nextUnknown(iLogger, map, strNextName);
                }
            }
            objectReader.endObject();
            if (strNextString == null) {
                IllegalStateException illegalStateException = new IllegalStateException("Missing required field \"name\"");
                iLogger.log(SentryLevel.ERROR, "Missing required field \"name\"", illegalStateException);
                throw illegalStateException;
            }
            if (strNextString2 == null) {
                IllegalStateException illegalStateException2 = new IllegalStateException("Missing required field \"version\"");
                iLogger.log(SentryLevel.ERROR, "Missing required field \"version\"", illegalStateException2);
                throw illegalStateException2;
            }
            SentryPackage sentryPackage = new SentryPackage(strNextString, strNextString2);
            sentryPackage.setUnknown(map);
            return sentryPackage;
        }
    }
}
