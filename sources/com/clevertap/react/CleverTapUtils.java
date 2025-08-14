package com.clevertap.react;

import android.util.Log;
import com.clevertap.android.sdk.displayunits.model.CleverTapDisplayUnit;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class CleverTapUtils {
    private static final String TAG = "CleverTapUtils";

    public static WritableMap getWritableMapFromMap(Map<String, ? extends Object> map) {
        String string;
        JSONObject jSONObject = map != null ? new JSONObject(map) : new JSONObject();
        WritableMap writableMapCreateMap = Arguments.createMap();
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String string2 = null;
            try {
                string = itKeys.next().toString();
                try {
                    string2 = jSONObject.get(string).toString();
                } catch (Throwable th) {
                    th = th;
                    Log.e(TAG, th.getLocalizedMessage());
                    if (string == null) {
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                string = null;
            }
            if (string == null && string2 != null) {
                writableMapCreateMap.putString(string, string2);
            }
        }
        return writableMapCreateMap;
    }

    public static WritableArray getWritableArrayFromDisplayUnitList(List<CleverTapDisplayUnit> list) {
        WritableArray writableArrayCreateArray = Arguments.createArray();
        if (list != null) {
            for (CleverTapDisplayUnit cleverTapDisplayUnit : list) {
                if (cleverTapDisplayUnit != null && cleverTapDisplayUnit.getJsonObject() != null) {
                    writableArrayCreateArray.pushMap(convertObjectToWritableMap(cleverTapDisplayUnit.getJsonObject()));
                }
            }
        }
        return writableArrayCreateArray;
    }

    public static WritableMap convertObjectToWritableMap(JSONObject jSONObject) throws JSONException {
        WritableMap writableMapCreateMap = Arguments.createMap();
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            try {
                Object obj = jSONObject.get(next);
                if (obj instanceof String) {
                    writableMapCreateMap.putString(next, (String) obj);
                } else if (obj instanceof Integer) {
                    writableMapCreateMap.putInt(next, ((Integer) obj).intValue());
                } else if (obj instanceof Boolean) {
                    writableMapCreateMap.putBoolean(next, ((Boolean) obj).booleanValue());
                } else if (obj instanceof Long) {
                    writableMapCreateMap.putDouble(next, ((Long) obj).doubleValue());
                } else if ((obj instanceof Float) || (obj instanceof Double)) {
                    writableMapCreateMap.putDouble(next, Double.parseDouble(obj.toString()));
                } else if (obj instanceof JSONObject) {
                    writableMapCreateMap.putMap(next, convertObjectToWritableMap((JSONObject) obj));
                } else if (obj instanceof JSONArray) {
                    writableMapCreateMap.putArray(next, convertArrayToWritableArray((JSONArray) obj));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return writableMapCreateMap;
    }

    private static WritableArray convertArrayToWritableArray(JSONArray jSONArray) throws JSONException {
        WritableArray writableArrayCreateArray = Arguments.createArray();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                Object obj = jSONArray.get(i);
                if (obj instanceof String) {
                    writableArrayCreateArray.pushString((String) obj);
                } else if (obj instanceof Integer) {
                    writableArrayCreateArray.pushInt(((Integer) obj).intValue());
                } else if (obj instanceof Boolean) {
                    writableArrayCreateArray.pushBoolean(((Boolean) obj).booleanValue());
                } else if (obj instanceof Long) {
                    writableArrayCreateArray.pushDouble(((Long) obj).doubleValue());
                } else if ((obj instanceof Float) || (obj instanceof Double)) {
                    writableArrayCreateArray.pushDouble(Double.parseDouble(obj.toString()));
                } else if (obj instanceof JSONObject) {
                    writableArrayCreateArray.pushMap(convertObjectToWritableMap((JSONObject) obj));
                } else if (obj instanceof JSONArray) {
                    writableArrayCreateArray.pushArray(convertArrayToWritableArray((JSONArray) obj));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return writableArrayCreateArray;
    }

    public static class MapUtil {
        public static JSONObject toJSONObject(ReadableMap readableMap) throws JSONException {
            JSONObject jSONObject = new JSONObject();
            ReadableMapKeySetIterator readableMapKeySetIteratorKeySetIterator = readableMap.keySetIterator();
            while (readableMapKeySetIteratorKeySetIterator.hasNextKey()) {
                String strNextKey = readableMapKeySetIteratorKeySetIterator.nextKey();
                switch (AnonymousClass1.$SwitchMap$com$facebook$react$bridge$ReadableType[readableMap.getType(strNextKey).ordinal()]) {
                    case 1:
                        jSONObject.put(strNextKey, (Object) null);
                        break;
                    case 2:
                        jSONObject.put(strNextKey, readableMap.getBoolean(strNextKey));
                        break;
                    case 3:
                        jSONObject.put(strNextKey, readableMap.getDouble(strNextKey));
                        break;
                    case 4:
                        jSONObject.put(strNextKey, readableMap.getString(strNextKey));
                        break;
                    case 5:
                        jSONObject.put(strNextKey, toJSONObject(readableMap.getMap(strNextKey)));
                        break;
                    case 6:
                        jSONObject.put(strNextKey, ArrayUtil.toJSONArray(readableMap.getArray(strNextKey)));
                        break;
                }
            }
            return jSONObject;
        }

        public static Map<String, Object> toMap(ReadableMap readableMap) {
            HashMap map = new HashMap();
            ReadableMapKeySetIterator readableMapKeySetIteratorKeySetIterator = readableMap.keySetIterator();
            while (readableMapKeySetIteratorKeySetIterator.hasNextKey()) {
                String strNextKey = readableMapKeySetIteratorKeySetIterator.nextKey();
                switch (AnonymousClass1.$SwitchMap$com$facebook$react$bridge$ReadableType[readableMap.getType(strNextKey).ordinal()]) {
                    case 1:
                        map.put(strNextKey, null);
                        break;
                    case 2:
                        map.put(strNextKey, Boolean.valueOf(readableMap.getBoolean(strNextKey)));
                        break;
                    case 3:
                        map.put(strNextKey, Double.valueOf(readableMap.getDouble(strNextKey)));
                        break;
                    case 4:
                        map.put(strNextKey, readableMap.getString(strNextKey));
                        break;
                    case 5:
                        map.put(strNextKey, toMap(readableMap.getMap(strNextKey)));
                        break;
                    case 6:
                        map.put(strNextKey, ArrayUtil.toArray(readableMap.getArray(strNextKey)));
                        break;
                }
            }
            return map;
        }

        public static WritableMap toWritableMap(Map<String, Object> map) {
            WritableMap writableMapCreateMap = Arguments.createMap();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                writableMapCreateMap.merge(addValue(entry.getKey(), entry.getValue()));
            }
            return writableMapCreateMap;
        }

        public static WritableMap addValue(String str, Object obj) {
            WritableMap writableMapCreateMap = Arguments.createMap();
            if (obj == null) {
                writableMapCreateMap.putNull(str);
            } else if (obj instanceof Boolean) {
                writableMapCreateMap.putBoolean(str, ((Boolean) obj).booleanValue());
            } else if (obj instanceof Double) {
                writableMapCreateMap.putDouble(str, ((Double) obj).doubleValue());
            } else if (obj instanceof Integer) {
                writableMapCreateMap.putInt(str, ((Integer) obj).intValue());
            } else if (obj instanceof String) {
                writableMapCreateMap.putString(str, (String) obj);
            } else if (obj instanceof Map) {
                writableMapCreateMap.putMap(str, toWritableMap((Map) obj));
            } else if (obj.getClass() != null && (obj.getClass().isArray() || (obj instanceof ArrayList))) {
                writableMapCreateMap.putArray(str, ArrayUtil.toWritableArray((ArrayList) obj));
            }
            return writableMapCreateMap;
        }

        public static class ArrayUtil {
            public static JSONArray toJSONArray(ReadableArray readableArray) throws JSONException {
                JSONArray jSONArray = new JSONArray();
                for (int i = 0; i < readableArray.size(); i++) {
                    switch (AnonymousClass1.$SwitchMap$com$facebook$react$bridge$ReadableType[readableArray.getType(i).ordinal()]) {
                        case 1:
                            jSONArray.put(i, (Object) null);
                            break;
                        case 2:
                            jSONArray.put(i, readableArray.getBoolean(i));
                            break;
                        case 3:
                            jSONArray.put(i, readableArray.getDouble(i));
                            break;
                        case 4:
                            jSONArray.put(i, readableArray.getString(i));
                            break;
                        case 5:
                            jSONArray.put(i, MapUtil.toJSONObject(readableArray.getMap(i)));
                            break;
                        case 6:
                            jSONArray.put(i, toJSONArray(readableArray.getArray(i)));
                            break;
                    }
                }
                return jSONArray;
            }

            public static Object[] toArray(ReadableArray readableArray) {
                Object[] objArr = new Object[readableArray.size()];
                for (int i = 0; i < readableArray.size(); i++) {
                    switch (AnonymousClass1.$SwitchMap$com$facebook$react$bridge$ReadableType[readableArray.getType(i).ordinal()]) {
                        case 1:
                            objArr[i] = null;
                            break;
                        case 2:
                            objArr[i] = Boolean.valueOf(readableArray.getBoolean(i));
                            break;
                        case 3:
                            objArr[i] = Double.valueOf(readableArray.getDouble(i));
                            break;
                        case 4:
                            objArr[i] = readableArray.getString(i);
                            break;
                        case 5:
                            objArr[i] = MapUtil.toMap(readableArray.getMap(i));
                            break;
                        case 6:
                            objArr[i] = toArray(readableArray.getArray(i));
                            break;
                    }
                }
                return objArr;
            }

            public static WritableArray toWritableArray(ArrayList arrayList) {
                WritableArray writableArrayCreateArray = Arguments.createArray();
                for (int i = 0; i < arrayList.size(); i++) {
                    Object obj = arrayList.get(i);
                    if (obj == null) {
                        writableArrayCreateArray.pushNull();
                    }
                    if (obj instanceof Boolean) {
                        writableArrayCreateArray.pushBoolean(((Boolean) obj).booleanValue());
                    }
                    if (obj instanceof Double) {
                        writableArrayCreateArray.pushDouble(((Double) obj).doubleValue());
                    }
                    if (obj instanceof Integer) {
                        writableArrayCreateArray.pushInt(((Integer) obj).intValue());
                    }
                    if (obj instanceof String) {
                        writableArrayCreateArray.pushString((String) obj);
                    }
                    if (obj instanceof Map) {
                        writableArrayCreateArray.pushMap(MapUtil.toWritableMap((Map) obj));
                    }
                    if (obj.getClass().isArray()) {
                        writableArrayCreateArray.pushArray(toWritableArray((ArrayList) obj));
                    }
                }
                return writableArrayCreateArray;
            }
        }
    }

    /* renamed from: com.clevertap.react.CleverTapUtils$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$react$bridge$ReadableType;

        static {
            int[] iArr = new int[ReadableType.values().length];
            $SwitchMap$com$facebook$react$bridge$ReadableType = iArr;
            try {
                iArr[ReadableType.Null.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.Boolean.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.Number.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.String.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.Map.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.Array.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }
}
