package com.clevertap.android.sdk.response;

import android.content.Context;
import android.util.Log;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public abstract class CleverTapResponse {
    public boolean isFullResponse = false;

    public void processResponse(JSONObject jSONObject, String str, Context context) {
        Log.i("CleverTapResponse", "Done processing response!");
    }
}
