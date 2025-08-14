package com.uxcam.internals;

import android.content.pm.PackageManager;
import com.clevertap.android.sdk.Constants;
import com.uxcam.screenaction.utils.Util;
import java.util.HashMap;

/* loaded from: classes6.dex */
public final class hq {

    /* renamed from: a, reason: collision with root package name */
    public static final HashMap f196a = new HashMap();
    public static final String b = Constants.KEY_ANDROID;

    public static String a() {
        try {
            HashMap map = f196a;
            map.put("production", "https://verify.uxcam.com/v4/verify");
            map.put("staging", "https://verify-staging.uxcam.com/v4/verify");
            map.put("ui_test", "http://192.168.232.2:13277/v4/verify");
            String str = (String) map.get(Util.getCurrentContext().getPackageManager().getApplicationInfo(Util.getCurrentContext().getPackageName(), 128).metaData.getString("UXCamServerRegion"));
            return str != null ? str : (String) map.get("production");
        } catch (PackageManager.NameNotFoundException unused) {
            return (String) f196a.get("production");
        } catch (NullPointerException unused2) {
            return (String) f196a.get("production");
        }
    }
}
