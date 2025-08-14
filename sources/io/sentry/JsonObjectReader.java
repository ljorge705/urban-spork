package io.sentry;

import io.sentry.vendor.gson.stream.JsonReader;
import io.sentry.vendor.gson.stream.JsonToken;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/* loaded from: classes6.dex */
public final class JsonObjectReader implements ObjectReader {
    private final JsonReader jsonReader;

    public JsonObjectReader(Reader reader) {
        this.jsonReader = new JsonReader(reader);
    }

    @Override // io.sentry.ObjectReader
    public String nextStringOrNull() throws IOException {
        if (this.jsonReader.peek() == JsonToken.NULL) {
            this.jsonReader.nextNull();
            return null;
        }
        return this.jsonReader.nextString();
    }

    @Override // io.sentry.ObjectReader
    public Double nextDoubleOrNull() throws IOException {
        if (this.jsonReader.peek() == JsonToken.NULL) {
            this.jsonReader.nextNull();
            return null;
        }
        return Double.valueOf(this.jsonReader.nextDouble());
    }

    @Override // io.sentry.ObjectReader
    public Float nextFloatOrNull() throws IOException {
        if (this.jsonReader.peek() == JsonToken.NULL) {
            this.jsonReader.nextNull();
            return null;
        }
        return Float.valueOf(nextFloat());
    }

    @Override // io.sentry.ObjectReader
    public float nextFloat() throws IOException {
        return (float) this.jsonReader.nextDouble();
    }

    @Override // io.sentry.ObjectReader
    public Long nextLongOrNull() throws IOException {
        if (this.jsonReader.peek() == JsonToken.NULL) {
            this.jsonReader.nextNull();
            return null;
        }
        return Long.valueOf(this.jsonReader.nextLong());
    }

    @Override // io.sentry.ObjectReader
    public Integer nextIntegerOrNull() throws IOException {
        if (this.jsonReader.peek() == JsonToken.NULL) {
            this.jsonReader.nextNull();
            return null;
        }
        return Integer.valueOf(this.jsonReader.nextInt());
    }

    @Override // io.sentry.ObjectReader
    public Boolean nextBooleanOrNull() throws IOException {
        if (this.jsonReader.peek() == JsonToken.NULL) {
            this.jsonReader.nextNull();
            return null;
        }
        return Boolean.valueOf(this.jsonReader.nextBoolean());
    }

    @Override // io.sentry.ObjectReader
    public void nextUnknown(ILogger iLogger, Map<String, Object> map, String str) {
        try {
            map.put(str, nextObjectOrNull());
        } catch (Exception e) {
            iLogger.log(SentryLevel.ERROR, e, "Error deserializing unknown key: %s", str);
        }
    }

    @Override // io.sentry.ObjectReader
    public <T> List<T> nextListOrNull(ILogger iLogger, JsonDeserializer<T> jsonDeserializer) throws IOException {
        if (this.jsonReader.peek() == JsonToken.NULL) {
            this.jsonReader.nextNull();
            return null;
        }
        this.jsonReader.beginArray();
        ArrayList arrayList = new ArrayList();
        if (this.jsonReader.hasNext()) {
            do {
                try {
                    arrayList.add(jsonDeserializer.deserialize(this, iLogger));
                } catch (Exception e) {
                    iLogger.log(SentryLevel.WARNING, "Failed to deserialize object in list.", e);
                }
            } while (this.jsonReader.peek() == JsonToken.BEGIN_OBJECT);
        }
        this.jsonReader.endArray();
        return arrayList;
    }

    @Override // io.sentry.ObjectReader
    public <T> Map<String, T> nextMapOrNull(ILogger iLogger, JsonDeserializer<T> jsonDeserializer) throws IOException {
        if (this.jsonReader.peek() == JsonToken.NULL) {
            this.jsonReader.nextNull();
            return null;
        }
        this.jsonReader.beginObject();
        HashMap map = new HashMap();
        if (this.jsonReader.hasNext()) {
            while (true) {
                try {
                    map.put(this.jsonReader.nextName(), jsonDeserializer.deserialize(this, iLogger));
                } catch (Exception e) {
                    iLogger.log(SentryLevel.WARNING, "Failed to deserialize object in map.", e);
                }
                if (this.jsonReader.peek() != JsonToken.BEGIN_OBJECT && this.jsonReader.peek() != JsonToken.NAME) {
                    break;
                }
            }
        }
        this.jsonReader.endObject();
        return map;
    }

    @Override // io.sentry.ObjectReader
    public <T> Map<String, List<T>> nextMapOfListOrNull(ILogger iLogger, JsonDeserializer<T> jsonDeserializer) throws IOException {
        if (peek() == JsonToken.NULL) {
            nextNull();
            return null;
        }
        HashMap map = new HashMap();
        beginObject();
        if (hasNext()) {
            while (true) {
                String strNextName = nextName();
                List<T> listNextListOrNull = nextListOrNull(iLogger, jsonDeserializer);
                if (listNextListOrNull != null) {
                    map.put(strNextName, listNextListOrNull);
                }
                if (peek() != JsonToken.BEGIN_OBJECT && peek() != JsonToken.NAME) {
                    break;
                }
            }
        }
        endObject();
        return map;
    }

    @Override // io.sentry.ObjectReader
    public <T> T nextOrNull(ILogger iLogger, JsonDeserializer<T> jsonDeserializer) throws Exception {
        if (this.jsonReader.peek() == JsonToken.NULL) {
            this.jsonReader.nextNull();
            return null;
        }
        return jsonDeserializer.deserialize(this, iLogger);
    }

    @Override // io.sentry.ObjectReader
    public Date nextDateOrNull(ILogger iLogger) throws IOException {
        if (this.jsonReader.peek() == JsonToken.NULL) {
            this.jsonReader.nextNull();
            return null;
        }
        return ObjectReader.dateOrNull(this.jsonReader.nextString(), iLogger);
    }

    @Override // io.sentry.ObjectReader
    public TimeZone nextTimeZoneOrNull(ILogger iLogger) throws IOException {
        if (this.jsonReader.peek() == JsonToken.NULL) {
            this.jsonReader.nextNull();
            return null;
        }
        try {
            return TimeZone.getTimeZone(this.jsonReader.nextString());
        } catch (Exception e) {
            iLogger.log(SentryLevel.ERROR, "Error when deserializing TimeZone", e);
            return null;
        }
    }

    @Override // io.sentry.ObjectReader
    public Object nextObjectOrNull() throws IOException {
        return new JsonObjectDeserializer().deserialize(this);
    }

    @Override // io.sentry.ObjectReader
    public JsonToken peek() throws IOException {
        return this.jsonReader.peek();
    }

    @Override // io.sentry.ObjectReader
    public String nextName() throws IOException {
        return this.jsonReader.nextName();
    }

    @Override // io.sentry.ObjectReader
    public void beginObject() throws IOException {
        this.jsonReader.beginObject();
    }

    @Override // io.sentry.ObjectReader
    public void endObject() throws IOException {
        this.jsonReader.endObject();
    }

    @Override // io.sentry.ObjectReader
    public void beginArray() throws IOException {
        this.jsonReader.beginArray();
    }

    @Override // io.sentry.ObjectReader
    public void endArray() throws IOException {
        this.jsonReader.endArray();
    }

    @Override // io.sentry.ObjectReader
    public boolean hasNext() throws IOException {
        return this.jsonReader.hasNext();
    }

    @Override // io.sentry.ObjectReader
    public int nextInt() throws IOException {
        return this.jsonReader.nextInt();
    }

    @Override // io.sentry.ObjectReader
    public long nextLong() throws IOException {
        return this.jsonReader.nextLong();
    }

    @Override // io.sentry.ObjectReader
    public String nextString() throws IOException {
        return this.jsonReader.nextString();
    }

    @Override // io.sentry.ObjectReader
    public boolean nextBoolean() throws IOException {
        return this.jsonReader.nextBoolean();
    }

    @Override // io.sentry.ObjectReader
    public double nextDouble() throws IOException {
        return this.jsonReader.nextDouble();
    }

    @Override // io.sentry.ObjectReader
    public void nextNull() throws IOException {
        this.jsonReader.nextNull();
    }

    @Override // io.sentry.ObjectReader
    public void setLenient(boolean z) {
        this.jsonReader.setLenient(z);
    }

    @Override // io.sentry.ObjectReader
    public void skipValue() throws IOException {
        this.jsonReader.skipValue();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.jsonReader.close();
    }
}
