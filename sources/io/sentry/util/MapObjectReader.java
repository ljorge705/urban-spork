package io.sentry.util;

import io.sentry.ILogger;
import io.sentry.JsonDeserializer;
import io.sentry.ObjectReader;
import io.sentry.SentryLevel;
import io.sentry.vendor.gson.stream.JsonToken;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Date;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/* loaded from: classes6.dex */
public final class MapObjectReader implements ObjectReader {
    private final Deque<Map.Entry<String, Object>> stack;

    @Override // io.sentry.ObjectReader
    public void setLenient(boolean z) {
    }

    @Override // io.sentry.ObjectReader
    public void skipValue() throws IOException {
    }

    public MapObjectReader(Map<String, Object> map) {
        ArrayDeque arrayDeque = new ArrayDeque();
        this.stack = arrayDeque;
        arrayDeque.addLast(new AbstractMap.SimpleEntry(null, map));
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
        if (peek() == JsonToken.NULL) {
            nextNull();
            return null;
        }
        try {
            beginArray();
            ArrayList arrayList = new ArrayList();
            if (hasNext()) {
                do {
                    try {
                        arrayList.add(jsonDeserializer.deserialize(this, iLogger));
                    } catch (Exception e) {
                        iLogger.log(SentryLevel.WARNING, "Failed to deserialize object in list.", e);
                    }
                } while (peek() == JsonToken.BEGIN_OBJECT);
            }
            endArray();
            return arrayList;
        } catch (Exception e2) {
            throw new IOException(e2);
        }
    }

    @Override // io.sentry.ObjectReader
    public <T> Map<String, T> nextMapOrNull(ILogger iLogger, JsonDeserializer<T> jsonDeserializer) throws IOException {
        if (peek() == JsonToken.NULL) {
            nextNull();
            return null;
        }
        try {
            beginObject();
            HashMap map = new HashMap();
            if (hasNext()) {
                while (true) {
                    try {
                        map.put(nextName(), jsonDeserializer.deserialize(this, iLogger));
                    } catch (Exception e) {
                        iLogger.log(SentryLevel.WARNING, "Failed to deserialize object in map.", e);
                    }
                    if (peek() != JsonToken.BEGIN_OBJECT && peek() != JsonToken.NAME) {
                        break;
                    }
                }
            }
            endObject();
            return map;
        } catch (Exception e2) {
            throw new IOException(e2);
        }
    }

    @Override // io.sentry.ObjectReader
    public <T> Map<String, List<T>> nextMapOfListOrNull(ILogger iLogger, JsonDeserializer<T> jsonDeserializer) throws IOException {
        if (peek() == JsonToken.NULL) {
            nextNull();
            return null;
        }
        HashMap map = new HashMap();
        try {
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
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    @Override // io.sentry.ObjectReader
    public <T> T nextOrNull(ILogger iLogger, JsonDeserializer<T> jsonDeserializer) throws Exception {
        return (T) nextValueOrNull(iLogger, jsonDeserializer);
    }

    @Override // io.sentry.ObjectReader
    public Date nextDateOrNull(ILogger iLogger) throws IOException {
        return ObjectReader.dateOrNull(nextStringOrNull(), iLogger);
    }

    @Override // io.sentry.ObjectReader
    public TimeZone nextTimeZoneOrNull(ILogger iLogger) throws IOException {
        String strNextStringOrNull = nextStringOrNull();
        if (strNextStringOrNull != null) {
            return TimeZone.getTimeZone(strNextStringOrNull);
        }
        return null;
    }

    @Override // io.sentry.ObjectReader
    public Object nextObjectOrNull() throws IOException {
        return nextValueOrNull();
    }

    @Override // io.sentry.ObjectReader
    public JsonToken peek() throws IOException {
        if (this.stack.isEmpty()) {
            return JsonToken.END_DOCUMENT;
        }
        Map.Entry<String, Object> entryPeekLast = this.stack.peekLast();
        if (entryPeekLast == null) {
            return JsonToken.END_DOCUMENT;
        }
        if (entryPeekLast.getKey() != null) {
            return JsonToken.NAME;
        }
        Object value = entryPeekLast.getValue();
        if (value instanceof Map) {
            return JsonToken.BEGIN_OBJECT;
        }
        if (value instanceof List) {
            return JsonToken.BEGIN_ARRAY;
        }
        if (value instanceof String) {
            return JsonToken.STRING;
        }
        if (value instanceof Number) {
            return JsonToken.NUMBER;
        }
        if (value instanceof Boolean) {
            return JsonToken.BOOLEAN;
        }
        if (value instanceof JsonToken) {
            return (JsonToken) value;
        }
        return JsonToken.END_DOCUMENT;
    }

    @Override // io.sentry.ObjectReader
    public String nextName() throws IOException {
        Map.Entry<String, Object> entryPeekLast = this.stack.peekLast();
        if (entryPeekLast != null && entryPeekLast.getKey() != null) {
            return entryPeekLast.getKey();
        }
        throw new IOException("Expected a name but was " + peek());
    }

    @Override // io.sentry.ObjectReader
    public void beginObject() throws IOException {
        Map.Entry<String, Object> entryRemoveLast = this.stack.removeLast();
        if (entryRemoveLast == null) {
            throw new IOException("No more entries");
        }
        Object value = entryRemoveLast.getValue();
        if (value instanceof Map) {
            this.stack.addLast(new AbstractMap.SimpleEntry(null, JsonToken.END_OBJECT));
            Iterator it = ((Map) value).entrySet().iterator();
            while (it.hasNext()) {
                this.stack.addLast((Map.Entry) it.next());
            }
            return;
        }
        throw new IOException("Current token is not an object");
    }

    @Override // io.sentry.ObjectReader
    public void endObject() throws IOException {
        if (this.stack.size() > 1) {
            this.stack.removeLast();
        }
    }

    @Override // io.sentry.ObjectReader
    public void beginArray() throws IOException {
        Map.Entry<String, Object> entryRemoveLast = this.stack.removeLast();
        if (entryRemoveLast == null) {
            throw new IOException("No more entries");
        }
        Object value = entryRemoveLast.getValue();
        if (value instanceof List) {
            this.stack.addLast(new AbstractMap.SimpleEntry(null, JsonToken.END_ARRAY));
            List list = (List) value;
            for (int size = list.size() - 1; size >= 0; size--) {
                this.stack.addLast(new AbstractMap.SimpleEntry(null, list.get(size)));
            }
            return;
        }
        throw new IOException("Current token is not an object");
    }

    @Override // io.sentry.ObjectReader
    public void endArray() throws IOException {
        if (this.stack.size() > 1) {
            this.stack.removeLast();
        }
    }

    @Override // io.sentry.ObjectReader
    public boolean hasNext() throws IOException {
        return !this.stack.isEmpty();
    }

    @Override // io.sentry.ObjectReader
    public int nextInt() throws IOException {
        Object objNextValueOrNull = nextValueOrNull();
        if (objNextValueOrNull instanceof Number) {
            return ((Number) objNextValueOrNull).intValue();
        }
        throw new IOException("Expected int");
    }

    @Override // io.sentry.ObjectReader
    public Integer nextIntegerOrNull() throws IOException {
        Object objNextValueOrNull = nextValueOrNull();
        if (objNextValueOrNull instanceof Number) {
            return Integer.valueOf(((Number) objNextValueOrNull).intValue());
        }
        return null;
    }

    @Override // io.sentry.ObjectReader
    public long nextLong() throws IOException {
        Object objNextValueOrNull = nextValueOrNull();
        if (objNextValueOrNull instanceof Number) {
            return ((Number) objNextValueOrNull).longValue();
        }
        throw new IOException("Expected long");
    }

    @Override // io.sentry.ObjectReader
    public Long nextLongOrNull() throws IOException {
        Object objNextValueOrNull = nextValueOrNull();
        if (objNextValueOrNull instanceof Number) {
            return Long.valueOf(((Number) objNextValueOrNull).longValue());
        }
        return null;
    }

    @Override // io.sentry.ObjectReader
    public String nextString() throws IOException {
        String str = (String) nextValueOrNull();
        if (str != null) {
            return str;
        }
        throw new IOException("Expected string");
    }

    @Override // io.sentry.ObjectReader
    public String nextStringOrNull() throws IOException {
        return (String) nextValueOrNull();
    }

    @Override // io.sentry.ObjectReader
    public boolean nextBoolean() throws IOException {
        Boolean bool = (Boolean) nextValueOrNull();
        if (bool != null) {
            return bool.booleanValue();
        }
        throw new IOException("Expected boolean");
    }

    @Override // io.sentry.ObjectReader
    public Boolean nextBooleanOrNull() throws IOException {
        return (Boolean) nextValueOrNull();
    }

    @Override // io.sentry.ObjectReader
    public double nextDouble() throws IOException {
        Object objNextValueOrNull = nextValueOrNull();
        if (objNextValueOrNull instanceof Number) {
            return ((Number) objNextValueOrNull).doubleValue();
        }
        throw new IOException("Expected double");
    }

    @Override // io.sentry.ObjectReader
    public Double nextDoubleOrNull() throws IOException {
        Object objNextValueOrNull = nextValueOrNull();
        if (objNextValueOrNull instanceof Number) {
            return Double.valueOf(((Number) objNextValueOrNull).doubleValue());
        }
        return null;
    }

    @Override // io.sentry.ObjectReader
    public Float nextFloatOrNull() throws IOException {
        Object objNextValueOrNull = nextValueOrNull();
        if (objNextValueOrNull instanceof Number) {
            return Float.valueOf(((Number) objNextValueOrNull).floatValue());
        }
        return null;
    }

    @Override // io.sentry.ObjectReader
    public float nextFloat() throws IOException {
        Object objNextValueOrNull = nextValueOrNull();
        if (objNextValueOrNull instanceof Number) {
            return ((Number) objNextValueOrNull).floatValue();
        }
        throw new IOException("Expected float");
    }

    @Override // io.sentry.ObjectReader
    public void nextNull() throws IOException {
        if (nextValueOrNull() != null) {
            throw new IOException("Expected null but was " + peek());
        }
    }

    private <T> T nextValueOrNull() throws IOException {
        try {
            return (T) nextValueOrNull(null, null);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    private <T> T nextValueOrNull(ILogger iLogger, JsonDeserializer<T> jsonDeserializer) throws Exception {
        Map.Entry<String, Object> entryPeekLast = this.stack.peekLast();
        if (entryPeekLast == null) {
            return null;
        }
        T t = (T) entryPeekLast.getValue();
        if (jsonDeserializer != null && iLogger != null) {
            return jsonDeserializer.deserialize(this, iLogger);
        }
        this.stack.removeLast();
        return t;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.stack.clear();
    }
}
