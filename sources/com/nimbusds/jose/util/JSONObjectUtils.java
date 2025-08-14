package com.nimbusds.jose.util;

import com.nimbusds.jose.shaded.gson.Gson;
import com.nimbusds.jose.shaded.gson.GsonBuilder;
import com.nimbusds.jose.shaded.gson.ToNumberPolicy;
import com.nimbusds.jose.shaded.gson.internal.LinkedTreeMap;
import com.nimbusds.jose.shaded.gson.reflect.TypeToken;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public class JSONObjectUtils {
    private static final Gson GSON = new GsonBuilder().serializeNulls().setObjectToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE).disableHtmlEscaping().create();

    public static Map<String, Object> parse(String str) throws ParseException {
        return parse(str, -1);
    }

    public static Map<String, Object> parse(String str, int i) throws ParseException {
        if (str.trim().isEmpty()) {
            throw new ParseException("Invalid JSON object", 0);
        }
        if (i >= 0 && str.length() > i) {
            throw new ParseException("The parsed string is longer than the max accepted size of " + i + " characters", 0);
        }
        try {
            return (Map) GSON.fromJson(str, TypeToken.getParameterized(Map.class, String.class, Object.class).getType());
        } catch (Exception e) {
            throw new ParseException("Invalid JSON: " + e.getMessage(), 0);
        } catch (StackOverflowError unused) {
            throw new ParseException("Excessive JSON object and / or array nesting", 0);
        }
    }

    @Deprecated
    public static Map<String, Object> parseJSONObject(String str) throws ParseException {
        return parse(str);
    }

    private static <T> T getGeneric(Map<String, Object> map, String str, Class<T> cls) throws ParseException {
        if (map.get(str) == null) {
            return null;
        }
        T t = (T) map.get(str);
        if (cls.isAssignableFrom(t.getClass())) {
            return t;
        }
        throw new ParseException("Unexpected type of JSON object member with key " + str + "", 0);
    }

    public static boolean getBoolean(Map<String, Object> map, String str) throws ParseException {
        Boolean bool = (Boolean) getGeneric(map, str, Boolean.class);
        if (bool == null) {
            throw new ParseException("JSON object member with key " + str + " is missing or null", 0);
        }
        return bool.booleanValue();
    }

    public static int getInt(Map<String, Object> map, String str) throws ParseException {
        Number number = (Number) getGeneric(map, str, Number.class);
        if (number == null) {
            throw new ParseException("JSON object member with key " + str + " is missing or null", 0);
        }
        return number.intValue();
    }

    public static long getLong(Map<String, Object> map, String str) throws ParseException {
        Number number = (Number) getGeneric(map, str, Number.class);
        if (number == null) {
            throw new ParseException("JSON object member with key " + str + " is missing or null", 0);
        }
        return number.longValue();
    }

    public static float getFloat(Map<String, Object> map, String str) throws ParseException {
        Number number = (Number) getGeneric(map, str, Number.class);
        if (number == null) {
            throw new ParseException("JSON object member with key " + str + " is missing or null", 0);
        }
        return number.floatValue();
    }

    public static double getDouble(Map<String, Object> map, String str) throws ParseException {
        Number number = (Number) getGeneric(map, str, Number.class);
        if (number == null) {
            throw new ParseException("JSON object member with key " + str + " is missing or null", 0);
        }
        return number.doubleValue();
    }

    public static String getString(Map<String, Object> map, String str) throws ParseException {
        return (String) getGeneric(map, str, String.class);
    }

    public static URI getURI(Map<String, Object> map, String str) throws ParseException {
        String string = getString(map, str);
        if (string == null) {
            return null;
        }
        try {
            return new URI(string);
        } catch (URISyntaxException e) {
            throw new ParseException(e.getMessage(), 0);
        }
    }

    public static List<Object> getJSONArray(Map<String, Object> map, String str) throws ParseException {
        return (List) getGeneric(map, str, List.class);
    }

    public static String[] getStringArray(Map<String, Object> map, String str) throws ParseException {
        List<Object> jSONArray = getJSONArray(map, str);
        if (jSONArray == null) {
            return null;
        }
        try {
            return (String[]) jSONArray.toArray(new String[0]);
        } catch (ArrayStoreException unused) {
            throw new ParseException("JSON object member with key \"" + str + "\" is not an array of strings", 0);
        }
    }

    public static Map<String, Object>[] getJSONObjectArray(Map<String, Object> map, String str) throws ParseException {
        List<Object> jSONArray = getJSONArray(map, str);
        if (jSONArray == null) {
            return null;
        }
        if (jSONArray.isEmpty()) {
            return new HashMap[0];
        }
        for (Object obj : jSONArray) {
            if (obj != null) {
                try {
                    if (obj instanceof HashMap) {
                        return (Map[]) jSONArray.toArray(new HashMap[0]);
                    }
                    if (obj instanceof LinkedTreeMap) {
                        return (Map[]) jSONArray.toArray(new LinkedTreeMap[0]);
                    }
                } catch (ArrayStoreException unused) {
                }
            }
        }
        throw new ParseException("JSON object member with key \"" + str + "\" is not an array of JSON objects", 0);
    }

    public static List<String> getStringList(Map<String, Object> map, String str) throws ParseException {
        String[] stringArray = getStringArray(map, str);
        if (stringArray == null) {
            return null;
        }
        return Arrays.asList(stringArray);
    }

    public static Map<String, Object> getJSONObject(Map<String, Object> map, String str) throws ParseException {
        Map<String, Object> map2 = (Map) getGeneric(map, str, Map.class);
        if (map2 == null) {
            return null;
        }
        Iterator<String> it = map2.keySet().iterator();
        while (it.hasNext()) {
            if (!(it.next() instanceof String)) {
                throw new ParseException("JSON object member with key " + str + " not a JSON object", 0);
            }
        }
        return map2;
    }

    public static Base64URL getBase64URL(Map<String, Object> map, String str) throws ParseException {
        String string = getString(map, str);
        if (string == null) {
            return null;
        }
        return new Base64URL(string);
    }

    public static String toJSONString(Map<String, ?> map) {
        return GSON.toJson(map);
    }

    public static Map<String, Object> newJSONObject() {
        return new HashMap();
    }

    private JSONObjectUtils() {
    }
}
