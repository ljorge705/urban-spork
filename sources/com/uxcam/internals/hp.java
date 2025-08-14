package com.uxcam.internals;

import android.webkit.JavascriptInterface;
import com.uxcam.UXCam;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes6.dex */
public final class hp {
    @JavascriptInterface
    public void occludeRectsOnNextFrame(String str) {
        try {
            UXCam.occludeRectsOnNextFrame(new JSONArray(str));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
