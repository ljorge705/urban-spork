package com.clevertap.android.sdk.variables;

import android.text.Editable;
import com.clevertap.android.sdk.Logger;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class JsonUtil {
    /* JADX WARN: Multi-variable type inference failed */
    public static <T> T uncheckedCast(Object obj) {
        return obj;
    }

    public static Map<String, Object> fromJson(String str) {
        if (str == null) {
            return null;
        }
        try {
            return mapFromJson(new JSONObject(str));
        } catch (JSONException e) {
            Logger.v("Error converting " + str + " from JSON", e);
            return null;
        }
    }

    public static JSONArray listToJsonArray(Iterable<?> iterable) throws JSONException {
        if (iterable == null) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        for (Object objListToJsonArray : iterable) {
            if (objListToJsonArray instanceof Map) {
                objListToJsonArray = mapToJsonObject((Map) uncheckedCast(objListToJsonArray));
            } else if (objListToJsonArray instanceof Iterable) {
                objListToJsonArray = listToJsonArray((Iterable) objListToJsonArray);
            } else if (objListToJsonArray == null) {
                objListToJsonArray = JSONObject.NULL;
            }
            jSONArray.put(objListToJsonArray);
        }
        return jSONArray;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0042  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static <T> java.util.Map<java.lang.String, T> mapFromJson(org.json.JSONObject r6) {
        /*
            r0 = 0
            if (r6 != 0) goto L4
            return r0
        L4:
            java.util.HashMap r1 = new java.util.HashMap
            r1.<init>()
            java.util.Iterator r2 = r6.keys()
        Ld:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L4b
            java.lang.Object r3 = r2.next()
            java.lang.String r3 = (java.lang.String) r3
            java.lang.Object r4 = r6.opt(r3)
            if (r4 == 0) goto L42
            java.lang.Object r5 = org.json.JSONObject.NULL
            if (r4 != r5) goto L24
            goto L42
        L24:
            boolean r5 = r4 instanceof org.json.JSONObject
            if (r5 == 0) goto L2f
            org.json.JSONObject r4 = (org.json.JSONObject) r4
            java.util.Map r4 = mapFromJson(r4)
            goto L43
        L2f:
            boolean r5 = r4 instanceof org.json.JSONArray
            if (r5 == 0) goto L3a
            org.json.JSONArray r4 = (org.json.JSONArray) r4
            java.util.List r4 = listFromJson(r4)
            goto L43
        L3a:
            java.lang.Object r5 = org.json.JSONObject.NULL
            boolean r5 = r5.equals(r4)
            if (r5 == 0) goto L43
        L42:
            r4 = r0
        L43:
            java.lang.Object r4 = uncheckedCast(r4)
            r1.put(r3, r4)
            goto Ld
        L4b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.clevertap.android.sdk.variables.JsonUtil.mapFromJson(org.json.JSONObject):java.util.Map");
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x003d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static <T> java.util.List<T> listFromJson(org.json.JSONArray r5) {
        /*
            r0 = 0
            if (r5 != 0) goto L4
            return r0
        L4:
            java.util.ArrayList r1 = new java.util.ArrayList
            int r2 = r5.length()
            r1.<init>(r2)
            r2 = 0
        Le:
            int r3 = r5.length()
            if (r2 >= r3) goto L44
            java.lang.Object r3 = r5.opt(r2)
            if (r3 == 0) goto L3d
            java.lang.Object r4 = org.json.JSONObject.NULL
            if (r3 != r4) goto L1f
            goto L3d
        L1f:
            boolean r4 = r3 instanceof org.json.JSONObject
            if (r4 == 0) goto L2a
            org.json.JSONObject r3 = (org.json.JSONObject) r3
            java.util.Map r3 = mapFromJson(r3)
            goto L3e
        L2a:
            boolean r4 = r3 instanceof org.json.JSONArray
            if (r4 == 0) goto L35
            org.json.JSONArray r3 = (org.json.JSONArray) r3
            java.util.List r3 = listFromJson(r3)
            goto L3e
        L35:
            java.lang.Object r4 = org.json.JSONObject.NULL
            boolean r4 = r4.equals(r3)
            if (r4 == 0) goto L3e
        L3d:
            r3 = r0
        L3e:
            r1.add(r3)
            int r2 = r2 + 1
            goto Le
        L44:
            java.lang.Object r5 = uncheckedCast(r1)
            java.util.List r5 = (java.util.List) r5
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.clevertap.android.sdk.variables.JsonUtil.listFromJson(org.json.JSONArray):java.util.List");
    }

    private static JSONObject mapToJsonObject(Map<String, ?> map) throws JSONException {
        if (map == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry<String, ?> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof Map) {
                value = mapToJsonObject((Map) uncheckedCast(value));
            } else if (value instanceof Iterable) {
                value = listToJsonArray((Iterable) value);
            } else if (value instanceof Editable) {
                value = value.toString();
            } else if (value == null) {
                value = JSONObject.NULL;
            }
            jSONObject.put(key, value);
        }
        return jSONObject;
    }

    public static String toJson(Map<String, ?> map) {
        if (map == null) {
            return null;
        }
        try {
            return mapToJsonObject(map).toString();
        } catch (JSONException e) {
            Logger.v("Error converting " + map + " to JSON", e);
            return null;
        }
    }
}
