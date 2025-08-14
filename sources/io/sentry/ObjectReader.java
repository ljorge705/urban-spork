package io.sentry;

import io.sentry.vendor.gson.stream.JsonToken;
import java.io.Closeable;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/* loaded from: classes6.dex */
public interface ObjectReader extends Closeable {
    void beginArray() throws IOException;

    void beginObject() throws IOException;

    void endArray() throws IOException;

    void endObject() throws IOException;

    boolean hasNext() throws IOException;

    boolean nextBoolean() throws IOException;

    Boolean nextBooleanOrNull() throws IOException;

    Date nextDateOrNull(ILogger iLogger) throws IOException;

    double nextDouble() throws IOException;

    Double nextDoubleOrNull() throws IOException;

    float nextFloat() throws IOException;

    Float nextFloatOrNull() throws IOException;

    int nextInt() throws IOException;

    Integer nextIntegerOrNull() throws IOException;

    <T> List<T> nextListOrNull(ILogger iLogger, JsonDeserializer<T> jsonDeserializer) throws IOException;

    long nextLong() throws IOException;

    Long nextLongOrNull() throws IOException;

    <T> Map<String, List<T>> nextMapOfListOrNull(ILogger iLogger, JsonDeserializer<T> jsonDeserializer) throws IOException;

    <T> Map<String, T> nextMapOrNull(ILogger iLogger, JsonDeserializer<T> jsonDeserializer) throws IOException;

    String nextName() throws IOException;

    void nextNull() throws IOException;

    Object nextObjectOrNull() throws IOException;

    <T> T nextOrNull(ILogger iLogger, JsonDeserializer<T> jsonDeserializer) throws Exception;

    String nextString() throws IOException;

    String nextStringOrNull() throws IOException;

    TimeZone nextTimeZoneOrNull(ILogger iLogger) throws IOException;

    void nextUnknown(ILogger iLogger, Map<String, Object> map, String str);

    JsonToken peek() throws IOException;

    void setLenient(boolean z);

    void skipValue() throws IOException;

    static Date dateOrNull(String str, ILogger iLogger) {
        if (str == null) {
            return null;
        }
        try {
            try {
                return DateUtils.getDateTime(str);
            } catch (Exception e) {
                iLogger.log(SentryLevel.ERROR, "Error when deserializing millis timestamp format.", e);
                return null;
            }
        } catch (Exception unused) {
            return DateUtils.getDateTimeWithMillisPrecision(str);
        }
    }
}
