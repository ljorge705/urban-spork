package com.singular.sdk;

import android.content.Context;
import android.webkit.JavascriptInterface;
import com.singular.sdk.internal.SingularLog;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class SingularJSInterface {
    private static final SingularLog logger = SingularLog.getLogger("SingularJSInterface");
    Context mContext;
    int webViewId;

    public SingularJSInterface(Context context) {
        this.mContext = context;
    }

    @JavascriptInterface
    public void setWebViewId(int i) {
        logger.debug("setWebViewId(id=" + i + ")");
        this.webViewId = i;
    }

    @JavascriptInterface
    public boolean event(String str) {
        logger.debug("event(name=" + str + ")");
        return Singular.event(str);
    }

    @JavascriptInterface
    public void event(String str, String str2) throws JSONException {
        logger.debug("event(name=" + str + ", JSONString=" + str2 + ")");
        Singular.eventJSON(str, new JSONObject(str2));
    }

    @JavascriptInterface
    public void revenue(String str, double d) throws JSONException {
        logger.debug("revenue(currency=" + str + ", amount=" + d + ")");
        Singular.revenue(str, d);
    }

    @JavascriptInterface
    public void setCustomUserId(String str) throws JSONException {
        logger.debug("setCustomUserId(customUserId=" + str + ")");
        Singular.setCustomUserId(str);
    }

    @JavascriptInterface
    public void unsetCustomUserId() throws JSONException {
        logger.debug("unsetCustomUserId()");
        Singular.unsetCustomUserId();
    }
}
