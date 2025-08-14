package io.invertase.firebase.messaging;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public abstract class JsonConvert {
    public static JSONObject reactToJSON(ReadableMap readableMap) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        ReadableMapKeySetIterator readableMapKeySetIteratorKeySetIterator = readableMap.keySetIterator();
        while (readableMapKeySetIteratorKeySetIterator.hasNextKey()) {
            String strNextKey = readableMapKeySetIteratorKeySetIterator.nextKey();
            switch (AnonymousClass1.$SwitchMap$com$facebook$react$bridge$ReadableType[readableMap.getType(strNextKey).ordinal()]) {
                case 1:
                    jSONObject.put(strNextKey, JSONObject.NULL);
                    break;
                case 2:
                    jSONObject.put(strNextKey, readableMap.getBoolean(strNextKey));
                    break;
                case 3:
                    try {
                        jSONObject.put(strNextKey, readableMap.getInt(strNextKey));
                        break;
                    } catch (Exception unused) {
                        jSONObject.put(strNextKey, readableMap.getDouble(strNextKey));
                        break;
                    }
                case 4:
                    jSONObject.put(strNextKey, readableMap.getString(strNextKey));
                    break;
                case 5:
                    jSONObject.put(strNextKey, reactToJSON(readableMap.getMap(strNextKey)));
                    break;
                case 6:
                    jSONObject.put(strNextKey, reactToJSON(readableMap.getArray(strNextKey)));
                    break;
            }
        }
        return jSONObject;
    }

    /* renamed from: io.invertase.firebase.messaging.JsonConvert$1, reason: invalid class name */
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

    public static JSONArray reactToJSON(ReadableArray readableArray) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        for (int i = 0; i < readableArray.size(); i++) {
            switch (AnonymousClass1.$SwitchMap$com$facebook$react$bridge$ReadableType[readableArray.getType(i).ordinal()]) {
                case 1:
                    jSONArray.put(JSONObject.NULL);
                    break;
                case 2:
                    jSONArray.put(readableArray.getBoolean(i));
                    break;
                case 3:
                    try {
                        jSONArray.put(readableArray.getInt(i));
                        break;
                    } catch (Exception unused) {
                        jSONArray.put(readableArray.getDouble(i));
                        break;
                    }
                case 4:
                    jSONArray.put(readableArray.getString(i));
                    break;
                case 5:
                    jSONArray.put(reactToJSON(readableArray.getMap(i)));
                    break;
                case 6:
                    jSONArray.put(reactToJSON(readableArray.getArray(i)));
                    break;
            }
        }
        return jSONArray;
    }

    public static WritableMap jsonToReact(JSONObject jSONObject) throws JSONException {
        WritableMap writableMapCreateMap = Arguments.createMap();
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            Object obj = jSONObject.get(next);
            if ((obj instanceof Float) || (obj instanceof Double)) {
                writableMapCreateMap.putDouble(next, jSONObject.getDouble(next));
            } else if (obj instanceof Number) {
                writableMapCreateMap.putInt(next, jSONObject.getInt(next));
            } else if (obj instanceof String) {
                writableMapCreateMap.putString(next, jSONObject.getString(next));
            } else if (obj instanceof JSONObject) {
                writableMapCreateMap.putMap(next, jsonToReact(jSONObject.getJSONObject(next)));
            } else if (obj instanceof JSONArray) {
                writableMapCreateMap.putArray(next, jsonToReact(jSONObject.getJSONArray(next)));
            } else if (obj == JSONObject.NULL) {
                writableMapCreateMap.putNull(next);
            }
        }
        return writableMapCreateMap;
    }

    public static WritableArray jsonToReact(JSONArray jSONArray) throws JSONException {
        WritableArray writableArrayCreateArray = Arguments.createArray();
        for (int i = 0; i < jSONArray.length(); i++) {
            Object obj = jSONArray.get(i);
            if ((obj instanceof Float) || (obj instanceof Double)) {
                writableArrayCreateArray.pushDouble(jSONArray.getDouble(i));
            } else if (obj instanceof Number) {
                writableArrayCreateArray.pushInt(jSONArray.getInt(i));
            } else if (obj instanceof String) {
                writableArrayCreateArray.pushString(jSONArray.getString(i));
            } else if (obj instanceof JSONObject) {
                writableArrayCreateArray.pushMap(jsonToReact(jSONArray.getJSONObject(i)));
            } else if (obj instanceof JSONArray) {
                writableArrayCreateArray.pushArray(jsonToReact(jSONArray.getJSONArray(i)));
            } else if (obj == JSONObject.NULL) {
                writableArrayCreateArray.pushNull();
            }
        }
        return writableArrayCreateArray;
    }
}
