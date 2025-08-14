package com.clevertap.android.sdk.variables;

import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.Logger;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public final class CTVariableUtils {
    public static final String BOOLEAN = "boolean";
    public static final String DICTIONARY = "group";
    public static final String FILE = "file";
    public static final String NUMBER = "number";
    public static final String STRING = "string";
    public static final String VARS = "vars";

    private static void log(String str) {
        Logger.d("variables", str);
    }

    public static void updateValuesAndKinds(String str, String[] strArr, Object obj, String str2, Map<String, Object> map, Map<String, String> map2) {
        if (strArr != null && strArr.length > 0) {
            int i = 0;
            Object obj2 = map;
            while (i < strArr.length - 1) {
                Object objTraverse = traverse(obj2, strArr[i], true);
                i++;
                obj2 = objTraverse;
            }
            if (obj2 instanceof Map) {
                Map map3 = (Map) JsonUtil.uncheckedCast(obj2);
                Object obj3 = map3.get(strArr[strArr.length - 1]);
                if ((obj3 instanceof Map) && (obj instanceof Map)) {
                    obj = mergeHelper(obj, obj3);
                } else if (obj3 != null && obj3.equals(obj)) {
                    log(String.format("Variable with name %s will override value: %s, with new value: %s.", str, obj3, obj));
                }
                map3.put(strArr[strArr.length - 1], obj);
            }
        }
        if (map2 != null) {
            map2.put(str, str2);
        }
    }

    public static Map<String, Object> convertFlatMapToNestedMaps(Map<String, Object> map) {
        HashMap map2 = new HashMap();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            if (key.contains(".")) {
                String[] nameComponents = getNameComponents(key);
                int length = nameComponents.length - 1;
                Map map3 = map2;
                for (int i = 0; i < nameComponents.length; i++) {
                    String str = nameComponents[i];
                    if (i == length) {
                        map3.put(str, entry.getValue());
                    } else if (!(map3.get(str) instanceof Map)) {
                        HashMap map4 = new HashMap();
                        map3.put(str, map4);
                        map3 = map4;
                    } else {
                        map3 = (Map) JsonUtil.uncheckedCast(map3.get(str));
                    }
                }
            } else {
                map2.put(entry.getKey(), entry.getValue());
            }
        }
        return map2;
    }

    public static Object mergeHelper(Object obj, Object obj2) {
        if (obj2 == null) {
            return obj;
        }
        if ((obj2 instanceof Number) || (obj2 instanceof Boolean) || (obj2 instanceof String) || (obj2 instanceof Character) || (obj instanceof Number) || (obj instanceof Boolean) || (obj instanceof String) || (obj instanceof Character)) {
            return obj2;
        }
        boolean z = obj2 instanceof Map;
        Iterable iterableKeySet = z ? ((Map) obj2).keySet() : (Iterable) obj2;
        boolean z2 = obj instanceof Map;
        Iterable iterableKeySet2 = z2 ? ((Map) obj).keySet() : (Iterable) obj;
        Map map = z ? (Map) obj2 : null;
        Map map2 = z2 ? (Map) obj : null;
        if (!z2 && !z) {
            return null;
        }
        HashMap map3 = new HashMap();
        if (iterableKeySet2 != null) {
            for (Object obj3 : iterableKeySet2) {
                if (map != null && map2 != null) {
                    Object obj4 = map.get(obj3);
                    Object obj5 = map2.get(obj3);
                    if (obj4 == null && obj5 != null) {
                        map3.put(obj3, obj5);
                    }
                }
            }
        }
        for (Object obj6 : iterableKeySet) {
            map3.put(obj6, mergeHelper(map2 != null ? map2.get(obj6) : null, map != null ? map.get(obj6) : null));
        }
        return map3;
    }

    public static Object traverse(Object obj, Object obj2, boolean z) {
        if (obj == null || !(obj instanceof Map)) {
            return null;
        }
        Map map = (Map) JsonUtil.uncheckedCast(obj);
        Object obj3 = map.get(obj2);
        if (!z || obj3 != null || !(obj2 instanceof String)) {
            return obj3;
        }
        HashMap map2 = new HashMap();
        map.put(obj2, map2);
        return map2;
    }

    public static <T> String kindFromValue(T t) {
        if ((t instanceof Integer) || (t instanceof Long) || (t instanceof Short) || (t instanceof Character) || (t instanceof Byte) || (t instanceof BigInteger) || (t instanceof Float) || (t instanceof Double) || (t instanceof BigDecimal)) {
            return NUMBER;
        }
        if (t instanceof String) {
            return STRING;
        }
        if (t instanceof Map) {
            return "group";
        }
        if (t instanceof Boolean) {
            return BOOLEAN;
        }
        return null;
    }

    public static String[] getNameComponents(String str) {
        try {
            return str.split("\\.");
        } catch (Throwable th) {
            th.printStackTrace();
            return new String[0];
        }
    }

    static void convertNestedMapsToFlatMap(String str, Map<String, Object> map, Map<String, Object> map2) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof Map) {
                convertNestedMapsToFlatMap(str + key + ".", (Map) JsonUtil.uncheckedCast(value), map2);
            } else {
                map2.put(str + key, value);
            }
        }
    }

    public static JSONObject getFlatVarsJson(Map<String, Object> map, Map<String, String> map2) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", Constants.variablePayloadType);
            JSONObject jSONObject2 = new JSONObject();
            for (String str : map.keySet()) {
                String str2 = map2.get(str);
                Object obj = map.get(str);
                if (obj instanceof Map) {
                    HashMap map3 = new HashMap();
                    map3.put(str, obj);
                    HashMap map4 = new HashMap();
                    convertNestedMapsToFlatMap("", map3, map4);
                    for (Map.Entry entry : map4.entrySet()) {
                        String str3 = (String) entry.getKey();
                        Object value = entry.getValue();
                        String strKindFromValue = "file".equals(map2.get(str3)) ? "file" : kindFromValue(value);
                        JSONObject jSONObject3 = new JSONObject();
                        jSONObject3.put("type", strKindFromValue);
                        jSONObject3.put("defaultValue", value);
                        jSONObject2.put(str3, jSONObject3);
                    }
                } else {
                    JSONObject jSONObject4 = new JSONObject();
                    jSONObject4.put("type", str2);
                    jSONObject4.put("defaultValue", obj);
                    jSONObject2.put(str, jSONObject4);
                }
            }
            jSONObject.put("vars", jSONObject2);
            return jSONObject;
        } catch (Throwable th) {
            th.printStackTrace();
            return new JSONObject();
        }
    }

    public static Map<Object, Object> deepCopyMap(Map<Object, Object> map) {
        HashMap map2 = new HashMap();
        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof Map) {
                map2.put(key, deepCopyMap((Map) JsonUtil.uncheckedCast(value)));
            } else {
                map2.put(key, value);
            }
        }
        return map2;
    }
}
