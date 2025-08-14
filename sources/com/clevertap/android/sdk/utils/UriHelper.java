package com.clevertap.android.sdk.utils;

import android.net.Uri;
import android.net.UrlQuerySanitizer;
import android.os.Bundle;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.Logger;
import java.net.URLDecoder;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public final class UriHelper {
    public static Bundle getAllKeyValuePairs(String str, boolean z) {
        if (str == null) {
            return new Bundle();
        }
        Bundle bundle = new Bundle();
        try {
            UrlQuerySanitizer urlQuerySanitizer = new UrlQuerySanitizer();
            urlQuerySanitizer.setAllowUnregisteredParamaters(true);
            urlQuerySanitizer.setUnregisteredParameterValueSanitizer(UrlQuerySanitizer.getAllButNulLegal());
            urlQuerySanitizer.parseUrl(str);
            for (String str2 : urlQuerySanitizer.getParameterSet()) {
                String valueForKey = getValueForKey(str2, urlQuerySanitizer, false);
                if (valueForKey != null) {
                    if (z || str2.equals(Constants.KEY_C2A)) {
                        bundle.putString(str2, valueForKey);
                    } else {
                        bundle.putString(str2, URLDecoder.decode(valueForKey, "UTF-8"));
                    }
                }
            }
        } catch (Throwable unused) {
        }
        return bundle;
    }

    public static JSONObject getUrchinFromUri(Uri uri) {
        JSONObject jSONObject = new JSONObject();
        try {
            UrlQuerySanitizer urlQuerySanitizer = new UrlQuerySanitizer();
            urlQuerySanitizer.setAllowUnregisteredParamaters(true);
            urlQuerySanitizer.parseUrl(uri.toString());
            String utmOrWzrkValue = getUtmOrWzrkValue("source", urlQuerySanitizer);
            String utmOrWzrkValue2 = getUtmOrWzrkValue("medium", urlQuerySanitizer);
            String utmOrWzrkValue3 = getUtmOrWzrkValue("campaign", urlQuerySanitizer);
            jSONObject.put("us", utmOrWzrkValue);
            jSONObject.put("um", utmOrWzrkValue2);
            jSONObject.put("uc", utmOrWzrkValue3);
            String wzrkValueForKey = getWzrkValueForKey("medium", urlQuerySanitizer);
            if (wzrkValueForKey != null && wzrkValueForKey.matches("^email$|^social$|^search$")) {
                jSONObject.put("wm", wzrkValueForKey);
            }
            Logger.d("Referrer data: " + jSONObject.toString(4));
        } catch (Throwable unused) {
        }
        return jSONObject;
    }

    private static String getUtmOrWzrkValue(String str, UrlQuerySanitizer urlQuerySanitizer) {
        String utmValueForKey = getUtmValueForKey(str, urlQuerySanitizer);
        if (utmValueForKey == null && (utmValueForKey = getWzrkValueForKey(str, urlQuerySanitizer)) == null) {
            return null;
        }
        return utmValueForKey;
    }

    private static String getUtmValueForKey(String str, UrlQuerySanitizer urlQuerySanitizer) {
        return getValueForKey("utm_" + str, urlQuerySanitizer, true);
    }

    private static String getValueForKey(String str, UrlQuerySanitizer urlQuerySanitizer, boolean z) {
        if (str != null && urlQuerySanitizer != null) {
            try {
                String value = urlQuerySanitizer.getValue(str);
                if (value == null) {
                    return null;
                }
                return (!z || value.length() <= 120) ? value : value.substring(0, 120);
            } catch (Throwable th) {
                Logger.v("Couldn't parse the URI", th);
            }
        }
        return null;
    }

    private static String getWzrkValueForKey(String str, UrlQuerySanitizer urlQuerySanitizer) {
        return getValueForKey(Constants.WZRK_PREFIX + str, urlQuerySanitizer, true);
    }
}
