package com.uxcam.internals;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public final class ig {
    public static boolean a(JSONObject jSONObject, boolean z) throws JSONException {
        int i;
        if (jSONObject == null) {
            return false;
        }
        try {
            i = jSONObject.getInt("resultCode");
        } catch (JSONException unused) {
            i = 1;
        }
        if (i != 2 && i != 3 && i != 13 && i != 15) {
            switch (i) {
            }
            return false;
        }
        return true;
    }
}
