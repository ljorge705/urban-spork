package io.invertase.firebase.common;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class ReactNativeFirebaseJSON {
    private static ReactNativeFirebaseJSON sharedInstance = new ReactNativeFirebaseJSON();
    private JSONObject jsonObject;

    public static ReactNativeFirebaseJSON getSharedInstance() {
        return sharedInstance;
    }

    public String getRawJSON() {
        return "{\"messaging_android_notification_channel_id\":\"general\",\"messaging_android_notification_color\":\"@color/whitesmoke\"}";
    }

    private ReactNativeFirebaseJSON() {
        try {
            this.jsonObject = new JSONObject("{\"messaging_android_notification_channel_id\":\"general\",\"messaging_android_notification_color\":\"@color/whitesmoke\"}");
        } catch (JSONException unused) {
        }
    }

    public boolean contains(String str) {
        JSONObject jSONObject = this.jsonObject;
        if (jSONObject == null) {
            return false;
        }
        return jSONObject.has(str);
    }

    public boolean getBooleanValue(String str, boolean z) {
        JSONObject jSONObject = this.jsonObject;
        return jSONObject == null ? z : jSONObject.optBoolean(str, z);
    }

    public int getIntValue(String str, int i) {
        JSONObject jSONObject = this.jsonObject;
        return jSONObject == null ? i : jSONObject.optInt(str, i);
    }

    public long getLongValue(String str, long j) {
        JSONObject jSONObject = this.jsonObject;
        return jSONObject == null ? j : jSONObject.optLong(str, j);
    }

    public String getStringValue(String str, String str2) {
        JSONObject jSONObject = this.jsonObject;
        return jSONObject == null ? str2 : jSONObject.optString(str, str2);
    }

    public ArrayList<String> getArrayValue(String str) {
        ArrayList<String> arrayList = new ArrayList<>();
        JSONObject jSONObject = this.jsonObject;
        if (jSONObject == null) {
            return arrayList;
        }
        try {
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray(str);
            if (jSONArrayOptJSONArray != null) {
                for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                    arrayList.add(jSONArrayOptJSONArray.getString(i));
                }
            }
        } catch (JSONException unused) {
        }
        return arrayList;
    }

    public WritableMap getAll() throws JSONException {
        WritableMap writableMapCreateMap = Arguments.createMap();
        JSONArray jSONArrayNames = this.jsonObject.names();
        for (int i = 0; i < jSONArrayNames.length(); i++) {
            try {
                String string = jSONArrayNames.getString(i);
                SharedUtils.mapPutValue(string, this.jsonObject.get(string), writableMapCreateMap);
            } catch (JSONException unused) {
            }
        }
        return writableMapCreateMap;
    }
}
