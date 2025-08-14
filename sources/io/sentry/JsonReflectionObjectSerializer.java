package io.sentry;

import io.sentry.util.JsonSerializationUtils;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.InetAddress;
import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Currency;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicIntegerArray;

/* loaded from: classes6.dex */
public final class JsonReflectionObjectSerializer {
    private final int maxDepth;
    private final Set<Object> visiting = new HashSet();

    JsonReflectionObjectSerializer(int i) {
        this.maxDepth = i;
    }

    public Object serialize(Object obj, ILogger iLogger) throws Exception {
        Object string;
        Object obj2 = null;
        if (obj == null) {
            return null;
        }
        if (obj instanceof Character) {
            return obj.toString();
        }
        if ((obj instanceof Number) || (obj instanceof Boolean) || (obj instanceof String)) {
            return obj;
        }
        if (obj instanceof Locale) {
            return obj.toString();
        }
        if (obj instanceof AtomicIntegerArray) {
            return JsonSerializationUtils.atomicIntegerArrayToList((AtomicIntegerArray) obj);
        }
        if (obj instanceof AtomicBoolean) {
            return Boolean.valueOf(((AtomicBoolean) obj).get());
        }
        if (obj instanceof URI) {
            return obj.toString();
        }
        if (obj instanceof InetAddress) {
            return obj.toString();
        }
        if (obj instanceof UUID) {
            return obj.toString();
        }
        if (obj instanceof Currency) {
            return obj.toString();
        }
        if (obj instanceof Calendar) {
            return JsonSerializationUtils.calendarToMap((Calendar) obj);
        }
        if (obj.getClass().isEnum()) {
            return obj.toString();
        }
        if (this.visiting.contains(obj)) {
            iLogger.log(SentryLevel.INFO, "Cyclic reference detected. Calling toString() on object.", new Object[0]);
            return obj.toString();
        }
        this.visiting.add(obj);
        try {
            if (this.visiting.size() > this.maxDepth) {
                this.visiting.remove(obj);
                iLogger.log(SentryLevel.INFO, "Max depth exceeded. Calling toString() on object.", new Object[0]);
                return obj.toString();
            }
            try {
            } catch (Exception e) {
                iLogger.log(SentryLevel.INFO, "Not serializing object due to throwing sub-path.", e);
            }
            if (obj.getClass().isArray()) {
                string = list((Object[]) obj, iLogger);
            } else if (obj instanceof Collection) {
                string = list((Collection<?>) obj, iLogger);
            } else if (obj instanceof Map) {
                string = map((Map) obj, iLogger);
            } else {
                Map<String, Object> mapSerializeObject = serializeObject(obj, iLogger);
                if (!mapSerializeObject.isEmpty()) {
                    obj2 = mapSerializeObject;
                    return obj2;
                }
                string = obj.toString();
            }
            obj2 = string;
            return obj2;
        } finally {
            this.visiting.remove(obj);
        }
    }

    public Map<String, Object> serializeObject(Object obj, ILogger iLogger) throws Exception {
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        HashMap map = new HashMap();
        for (Field field : declaredFields) {
            if (!Modifier.isTransient(field.getModifiers()) && !Modifier.isStatic(field.getModifiers())) {
                String name = field.getName();
                try {
                    field.setAccessible(true);
                    map.put(name, serialize(field.get(obj), iLogger));
                    field.setAccessible(false);
                } catch (Exception unused) {
                    iLogger.log(SentryLevel.INFO, "Cannot access field " + name + ".", new Object[0]);
                }
            }
        }
        return map;
    }

    private List<Object> list(Object[] objArr, ILogger iLogger) throws Exception {
        ArrayList arrayList = new ArrayList();
        for (Object obj : objArr) {
            arrayList.add(serialize(obj, iLogger));
        }
        return arrayList;
    }

    private List<Object> list(Collection<?> collection, ILogger iLogger) throws Exception {
        ArrayList arrayList = new ArrayList();
        Iterator<?> it = collection.iterator();
        while (it.hasNext()) {
            arrayList.add(serialize(it.next(), iLogger));
        }
        return arrayList;
    }

    private Map<String, Object> map(Map<?, ?> map, ILogger iLogger) throws Exception {
        HashMap map2 = new HashMap();
        for (Object obj : map.keySet()) {
            Object obj2 = map.get(obj);
            if (obj2 != null) {
                map2.put(obj.toString(), serialize(obj2, iLogger));
            } else {
                map2.put(obj.toString(), null);
            }
        }
        return map2;
    }
}
