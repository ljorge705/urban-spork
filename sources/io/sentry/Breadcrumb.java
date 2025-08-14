package io.sentry;

import androidx.core.app.NotificationCompat;
import app.notifee.core.event.LogEvent;
import com.ReactNativeBlobUtil.ReactNativeBlobUtilConst;
import com.drew.metadata.mov.QuickTimeAtomTypes;
import com.google.android.gms.actions.SearchIntents;
import com.google.firebase.messaging.Constants;
import io.sentry.SentryLevel;
import io.sentry.protocol.Response;
import io.sentry.util.CollectionUtils;
import io.sentry.util.Objects;
import io.sentry.util.UrlUtils;
import io.sentry.vendor.gson.stream.JsonToken;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes6.dex */
public final class Breadcrumb implements JsonUnknown, JsonSerializable {
    private String category;
    private Map<String, Object> data;
    private SentryLevel level;
    private String message;
    private String origin;
    private Date timestamp;
    private final Long timestampMs;
    private String type;
    private Map<String, Object> unknown;

    public static final class JsonKeys {
        public static final String CATEGORY = "category";
        public static final String DATA = "data";
        public static final String LEVEL = "level";
        public static final String MESSAGE = "message";
        public static final String ORIGIN = "origin";
        public static final String TIMESTAMP = "timestamp";
        public static final String TYPE = "type";
    }

    public String getCategory() {
        return this.category;
    }

    public Map<String, Object> getData() {
        return this.data;
    }

    public SentryLevel getLevel() {
        return this.level;
    }

    public String getMessage() {
        return this.message;
    }

    public String getOrigin() {
        return this.origin;
    }

    public String getType() {
        return this.type;
    }

    @Override // io.sentry.JsonUnknown
    public Map<String, Object> getUnknown() {
        return this.unknown;
    }

    public void setCategory(String str) {
        this.category = str;
    }

    public void setLevel(SentryLevel sentryLevel) {
        this.level = sentryLevel;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setOrigin(String str) {
        this.origin = str;
    }

    public void setType(String str) {
        this.type = str;
    }

    @Override // io.sentry.JsonUnknown
    public void setUnknown(Map<String, Object> map) {
        this.unknown = map;
    }

    public Breadcrumb(Date date) {
        this.data = new ConcurrentHashMap();
        this.timestamp = date;
        this.timestampMs = null;
    }

    public Breadcrumb(long j) {
        this.data = new ConcurrentHashMap();
        this.timestampMs = Long.valueOf(j);
        this.timestamp = null;
    }

    Breadcrumb(Breadcrumb breadcrumb) {
        this.data = new ConcurrentHashMap();
        this.timestamp = breadcrumb.timestamp;
        this.timestampMs = breadcrumb.timestampMs;
        this.message = breadcrumb.message;
        this.type = breadcrumb.type;
        this.category = breadcrumb.category;
        this.origin = breadcrumb.origin;
        Map<String, Object> mapNewConcurrentHashMap = CollectionUtils.newConcurrentHashMap(breadcrumb.data);
        if (mapNewConcurrentHashMap != null) {
            this.data = mapNewConcurrentHashMap;
        }
        this.unknown = CollectionUtils.newConcurrentHashMap(breadcrumb.unknown);
        this.level = breadcrumb.level;
    }

    public static Breadcrumb fromMap(Map<String, Object> map, SentryOptions sentryOptions) {
        Object value;
        Date dateDateOrNull;
        Date currentDateTime = DateUtils.getCurrentDateTime();
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        SentryLevel sentryLevelValueOf = null;
        ConcurrentHashMap concurrentHashMap2 = null;
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            value = entry.getValue();
            String key = entry.getKey();
            key.hashCode();
            switch (key) {
                case "origin":
                    if (value instanceof String) {
                        str4 = (String) value;
                        break;
                    } else {
                        str4 = null;
                        break;
                    }
                case "data":
                    Map map2 = value instanceof Map ? (Map) value : null;
                    if (map2 != null) {
                        for (Map.Entry entry2 : map2.entrySet()) {
                            if ((entry2.getKey() instanceof String) && entry2.getValue() != null) {
                                concurrentHashMap.put((String) entry2.getKey(), entry2.getValue());
                            } else {
                                sentryOptions.getLogger().log(SentryLevel.WARNING, "Invalid key or null value in data map.", new Object[0]);
                            }
                        }
                        break;
                    } else {
                        break;
                    }
                    break;
                case "type":
                    if (value instanceof String) {
                        str2 = (String) value;
                        break;
                    } else {
                        str2 = null;
                        break;
                    }
                case "category":
                    if (value instanceof String) {
                        str3 = (String) value;
                        break;
                    } else {
                        str3 = null;
                        break;
                    }
                case "timestamp":
                    if (!(value instanceof String) || (dateDateOrNull = ObjectReader.dateOrNull((String) value, sentryOptions.getLogger())) == null) {
                        break;
                    } else {
                        currentDateTime = dateDateOrNull;
                        break;
                    }
                case "level":
                    String str5 = value instanceof String ? (String) value : null;
                    if (str5 != null) {
                        try {
                            sentryLevelValueOf = SentryLevel.valueOf(str5.toUpperCase(Locale.ROOT));
                            break;
                        } catch (Exception unused) {
                            break;
                        }
                    } else {
                        break;
                    }
                case "message":
                    if (value instanceof String) {
                        str = (String) value;
                        break;
                    } else {
                        str = null;
                        break;
                    }
                default:
                    if (concurrentHashMap2 == null) {
                        concurrentHashMap2 = new ConcurrentHashMap();
                    }
                    concurrentHashMap2.put(entry.getKey(), entry.getValue());
                    break;
            }
        }
        Breadcrumb breadcrumb = new Breadcrumb(currentDateTime);
        breadcrumb.message = str;
        breadcrumb.type = str2;
        breadcrumb.data = concurrentHashMap;
        breadcrumb.category = str3;
        breadcrumb.origin = str4;
        breadcrumb.level = sentryLevelValueOf;
        breadcrumb.setUnknown(concurrentHashMap2);
        return breadcrumb;
    }

    public static Breadcrumb http(String str, String str2) {
        Breadcrumb breadcrumb = new Breadcrumb();
        UrlUtils.UrlDetails urlDetails = UrlUtils.parse(str);
        breadcrumb.setType("http");
        breadcrumb.setCategory("http");
        if (urlDetails.getUrl() != null) {
            breadcrumb.setData("url", urlDetails.getUrl());
        }
        breadcrumb.setData("method", str2.toUpperCase(Locale.ROOT));
        if (urlDetails.getQuery() != null) {
            breadcrumb.setData(SpanDataConvention.HTTP_QUERY_KEY, urlDetails.getQuery());
        }
        if (urlDetails.getFragment() != null) {
            breadcrumb.setData(SpanDataConvention.HTTP_FRAGMENT_KEY, urlDetails.getFragment());
        }
        return breadcrumb;
    }

    public static Breadcrumb http(String str, String str2, Integer num) {
        Breadcrumb breadcrumbHttp = http(str, str2);
        if (num != null) {
            breadcrumbHttp.setData(Response.JsonKeys.STATUS_CODE, num);
        }
        return breadcrumbHttp;
    }

    public static Breadcrumb graphqlOperation(String str, String str2, String str3) {
        Breadcrumb breadcrumb = new Breadcrumb();
        breadcrumb.setType("graphql");
        if (str != null) {
            breadcrumb.setData("operation_name", str);
        }
        if (str2 != null) {
            breadcrumb.setData("operation_type", str2);
            breadcrumb.setCategory(str2);
        } else {
            breadcrumb.setCategory("graphql.operation");
        }
        if (str3 != null) {
            breadcrumb.setData("operation_id", str3);
        }
        return breadcrumb;
    }

    public static Breadcrumb graphqlDataFetcher(String str, String str2, String str3, String str4) {
        Breadcrumb breadcrumb = new Breadcrumb();
        breadcrumb.setType("graphql");
        breadcrumb.setCategory("graphql.fetcher");
        if (str != null) {
            breadcrumb.setData(ReactNativeBlobUtilConst.RNFB_RESPONSE_PATH, str);
        }
        if (str2 != null) {
            breadcrumb.setData("field", str2);
        }
        if (str3 != null) {
            breadcrumb.setData("type", str3);
        }
        if (str4 != null) {
            breadcrumb.setData("object_type", str4);
        }
        return breadcrumb;
    }

    public static Breadcrumb graphqlDataLoader(Iterable<?> iterable, Class<?> cls, Class<?> cls2, String str) {
        Breadcrumb breadcrumb = new Breadcrumb();
        breadcrumb.setType("graphql");
        breadcrumb.setCategory("graphql.data_loader");
        ArrayList arrayList = new ArrayList();
        Iterator<?> it = iterable.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().toString());
        }
        breadcrumb.setData(QuickTimeAtomTypes.ATOM_KEYS, arrayList);
        if (cls != null) {
            breadcrumb.setData("key_type", cls.getName());
        }
        if (cls2 != null) {
            breadcrumb.setData("value_type", cls2.getName());
        }
        if (str != null) {
            breadcrumb.setData("name", str);
        }
        return breadcrumb;
    }

    public static Breadcrumb navigation(String str, String str2) {
        Breadcrumb breadcrumb = new Breadcrumb();
        breadcrumb.setCategory(NotificationCompat.CATEGORY_NAVIGATION);
        breadcrumb.setType(NotificationCompat.CATEGORY_NAVIGATION);
        breadcrumb.setData(Constants.MessagePayloadKeys.FROM, str);
        breadcrumb.setData("to", str2);
        return breadcrumb;
    }

    public static Breadcrumb transaction(String str) {
        Breadcrumb breadcrumb = new Breadcrumb();
        breadcrumb.setType(com.facebook.hermes.intl.Constants.COLLATION_DEFAULT);
        breadcrumb.setCategory("sentry.transaction");
        breadcrumb.setMessage(str);
        return breadcrumb;
    }

    public static Breadcrumb debug(String str) {
        Breadcrumb breadcrumb = new Breadcrumb();
        breadcrumb.setType(LogEvent.LEVEL_DEBUG);
        breadcrumb.setMessage(str);
        breadcrumb.setLevel(SentryLevel.DEBUG);
        return breadcrumb;
    }

    public static Breadcrumb error(String str) {
        Breadcrumb breadcrumb = new Breadcrumb();
        breadcrumb.setType("error");
        breadcrumb.setMessage(str);
        breadcrumb.setLevel(SentryLevel.ERROR);
        return breadcrumb;
    }

    public static Breadcrumb info(String str) {
        Breadcrumb breadcrumb = new Breadcrumb();
        breadcrumb.setType("info");
        breadcrumb.setMessage(str);
        breadcrumb.setLevel(SentryLevel.INFO);
        return breadcrumb;
    }

    public static Breadcrumb query(String str) {
        Breadcrumb breadcrumb = new Breadcrumb();
        breadcrumb.setType(SearchIntents.EXTRA_QUERY);
        breadcrumb.setMessage(str);
        return breadcrumb;
    }

    public static Breadcrumb ui(String str, String str2) {
        Breadcrumb breadcrumb = new Breadcrumb();
        breadcrumb.setType(com.facebook.hermes.intl.Constants.COLLATION_DEFAULT);
        breadcrumb.setCategory("ui." + str);
        breadcrumb.setMessage(str2);
        return breadcrumb;
    }

    public static Breadcrumb user(String str, String str2) {
        Breadcrumb breadcrumb = new Breadcrumb();
        breadcrumb.setType("user");
        breadcrumb.setCategory(str);
        breadcrumb.setMessage(str2);
        return breadcrumb;
    }

    public static Breadcrumb userInteraction(String str, String str2, String str3) {
        return userInteraction(str, str2, str3, Collections.emptyMap());
    }

    public static Breadcrumb userInteraction(String str, String str2, String str3, String str4, Map<String, Object> map) {
        Breadcrumb breadcrumb = new Breadcrumb();
        breadcrumb.setType("user");
        breadcrumb.setCategory("ui." + str);
        if (str2 != null) {
            breadcrumb.setData("view.id", str2);
        }
        if (str3 != null) {
            breadcrumb.setData("view.class", str3);
        }
        if (str4 != null) {
            breadcrumb.setData("view.tag", str4);
        }
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            breadcrumb.getData().put(entry.getKey(), entry.getValue());
        }
        breadcrumb.setLevel(SentryLevel.INFO);
        return breadcrumb;
    }

    public static Breadcrumb userInteraction(String str, String str2, String str3, Map<String, Object> map) {
        return userInteraction(str, str2, str3, null, map);
    }

    public Breadcrumb() {
        this(System.currentTimeMillis());
    }

    public Breadcrumb(String str) {
        this();
        this.message = str;
    }

    public Date getTimestamp() {
        Date date = this.timestamp;
        if (date != null) {
            return (Date) date.clone();
        }
        Long l = this.timestampMs;
        if (l != null) {
            Date dateTime = DateUtils.getDateTime(l.longValue());
            this.timestamp = dateTime;
            return dateTime;
        }
        throw new IllegalStateException("No timestamp set for breadcrumb");
    }

    public Object getData(String str) {
        return this.data.get(str);
    }

    public void setData(String str, Object obj) {
        this.data.put(str, obj);
    }

    public void removeData(String str) {
        this.data.remove(str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Breadcrumb breadcrumb = (Breadcrumb) obj;
        return getTimestamp().getTime() == breadcrumb.getTimestamp().getTime() && Objects.equals(this.message, breadcrumb.message) && Objects.equals(this.type, breadcrumb.type) && Objects.equals(this.category, breadcrumb.category) && Objects.equals(this.origin, breadcrumb.origin) && this.level == breadcrumb.level;
    }

    public int hashCode() {
        return Objects.hash(this.timestamp, this.message, this.type, this.category, this.origin, this.level);
    }

    @Override // io.sentry.JsonSerializable
    public void serialize(ObjectWriter objectWriter, ILogger iLogger) throws IOException {
        objectWriter.beginObject();
        objectWriter.name("timestamp").value(iLogger, getTimestamp());
        if (this.message != null) {
            objectWriter.name("message").value(this.message);
        }
        if (this.type != null) {
            objectWriter.name("type").value(this.type);
        }
        objectWriter.name("data").value(iLogger, this.data);
        if (this.category != null) {
            objectWriter.name("category").value(this.category);
        }
        if (this.origin != null) {
            objectWriter.name("origin").value(this.origin);
        }
        if (this.level != null) {
            objectWriter.name("level").value(iLogger, this.level);
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

    public static final class Deserializer implements JsonDeserializer<Breadcrumb> {
        /* JADX WARN: Can't rename method to resolve collision */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r8v4, types: [java.util.Map] */
        @Override // io.sentry.JsonDeserializer
        public Breadcrumb deserialize(ObjectReader objectReader, ILogger iLogger) throws Exception {
            String strNextName;
            objectReader.beginObject();
            Date currentDateTime = DateUtils.getCurrentDateTime();
            ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
            String strNextStringOrNull = null;
            String strNextStringOrNull2 = null;
            String strNextStringOrNull3 = null;
            String strNextStringOrNull4 = null;
            SentryLevel sentryLevelDeserialize = null;
            ConcurrentHashMap concurrentHashMap2 = null;
            while (objectReader.peek() == JsonToken.NAME) {
                strNextName = objectReader.nextName();
                strNextName.hashCode();
                switch (strNextName) {
                    case "origin":
                        strNextStringOrNull4 = objectReader.nextStringOrNull();
                        break;
                    case "data":
                        ?? NewConcurrentHashMap = CollectionUtils.newConcurrentHashMap((Map) objectReader.nextObjectOrNull());
                        if (NewConcurrentHashMap == 0) {
                            break;
                        } else {
                            concurrentHashMap = NewConcurrentHashMap;
                            break;
                        }
                    case "type":
                        strNextStringOrNull2 = objectReader.nextStringOrNull();
                        break;
                    case "category":
                        strNextStringOrNull3 = objectReader.nextStringOrNull();
                        break;
                    case "timestamp":
                        Date dateNextDateOrNull = objectReader.nextDateOrNull(iLogger);
                        if (dateNextDateOrNull == null) {
                            break;
                        } else {
                            currentDateTime = dateNextDateOrNull;
                            break;
                        }
                    case "level":
                        try {
                            sentryLevelDeserialize = new SentryLevel.Deserializer().deserialize(objectReader, iLogger);
                            break;
                        } catch (Exception e) {
                            iLogger.log(SentryLevel.ERROR, e, "Error when deserializing SentryLevel", new Object[0]);
                            break;
                        }
                    case "message":
                        strNextStringOrNull = objectReader.nextStringOrNull();
                        break;
                    default:
                        if (concurrentHashMap2 == null) {
                            concurrentHashMap2 = new ConcurrentHashMap();
                        }
                        objectReader.nextUnknown(iLogger, concurrentHashMap2, strNextName);
                        break;
                }
            }
            Breadcrumb breadcrumb = new Breadcrumb(currentDateTime);
            breadcrumb.message = strNextStringOrNull;
            breadcrumb.type = strNextStringOrNull2;
            breadcrumb.data = concurrentHashMap;
            breadcrumb.category = strNextStringOrNull3;
            breadcrumb.origin = strNextStringOrNull4;
            breadcrumb.level = sentryLevelDeserialize;
            breadcrumb.setUnknown(concurrentHashMap2);
            objectReader.endObject();
            return breadcrumb;
        }
    }
}
