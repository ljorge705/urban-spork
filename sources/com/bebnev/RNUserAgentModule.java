package com.bebnev;

import android.content.pm.PackageInfo;
import android.os.Build;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.module.annotations.ReactModule;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.ClassUtils;

@ReactModule(name = RNUserAgentModule.NAME)
/* loaded from: classes5.dex */
public class RNUserAgentModule extends ReactContextBaseJavaModule {
    public static final String NAME = "RNUserAgent";

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    public RNUserAgentModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    protected String getUserAgent() {
        try {
            return WebSettings.getDefaultUserAgent(getReactApplicationContext());
        } catch (RuntimeException unused) {
            return System.getProperty("http.agent");
        }
    }

    @ReactMethod
    protected void getWebViewUserAgent(final Promise promise) {
        promise.resolve(WebSettings.getDefaultUserAgent(getReactApplicationContext()));
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.bebnev.RNUserAgentModule.1
            @Override // java.lang.Runnable
            public void run() {
                promise.resolve(new WebView(RNUserAgentModule.this.getReactApplicationContext()).getSettings().getUserAgentString());
            }
        });
    }

    private PackageInfo getPackageInfo() throws Exception {
        return getReactApplicationContext().getPackageManager().getPackageInfo(getReactApplicationContext().getPackageName(), 0);
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    public Map<String, Object> getConstants() {
        String string;
        String str;
        String string2;
        String packageName = getReactApplicationContext().getPackageName();
        String strSubstring = packageName.substring(packageName.lastIndexOf(".") + 1);
        String str2 = "";
        try {
            string = getReactApplicationContext().getApplicationInfo().loadLabel(getReactApplicationContext().getPackageManager()).toString();
        } catch (Exception e) {
            e = e;
            string = "";
            str = string;
        }
        try {
            str = getPackageInfo().versionName;
            try {
                string2 = Integer.toString(getPackageInfo().versionCode);
            } catch (Exception e2) {
                e = e2;
                string2 = "";
            }
        } catch (Exception e3) {
            e = e3;
            str = "";
            string2 = str;
            e.printStackTrace();
            HashMap map = new HashMap();
            map.put("systemName", "Android");
            map.put("systemVersion", Build.VERSION.RELEASE);
            map.put("packageName", packageName);
            map.put("shortPackageName", strSubstring);
            map.put("applicationName", string);
            map.put("applicationVersion", str);
            map.put("buildNumber", string2);
            map.put("userAgent", str2);
            return map;
        }
        try {
            str2 = strSubstring + '/' + str + ClassUtils.PACKAGE_SEPARATOR_CHAR + string2.toString() + ' ' + getUserAgent();
        } catch (Exception e4) {
            e = e4;
            e.printStackTrace();
            HashMap map2 = new HashMap();
            map2.put("systemName", "Android");
            map2.put("systemVersion", Build.VERSION.RELEASE);
            map2.put("packageName", packageName);
            map2.put("shortPackageName", strSubstring);
            map2.put("applicationName", string);
            map2.put("applicationVersion", str);
            map2.put("buildNumber", string2);
            map2.put("userAgent", str2);
            return map2;
        }
        HashMap map22 = new HashMap();
        map22.put("systemName", "Android");
        map22.put("systemVersion", Build.VERSION.RELEASE);
        map22.put("packageName", packageName);
        map22.put("shortPackageName", strSubstring);
        map22.put("applicationName", string);
        map22.put("applicationVersion", str);
        map22.put("buildNumber", string2);
        map22.put("userAgent", str2);
        return map22;
    }
}
