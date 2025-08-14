package io.invertase.notifee;

import android.content.Context;
import app.notifee.core.InitProvider;
import app.notifee.core.Notifee;
import com.facebook.react.modules.systeminfo.ReactNativeVersion;
import java.util.Map;

/* loaded from: classes6.dex */
public class NotifeeInitProvider extends InitProvider {
    @Override // app.notifee.core.InitProvider, android.content.ContentProvider
    public boolean onCreate() {
        boolean zOnCreate = super.onCreate();
        Notifee.initialize(new NotifeeEventSubscriber());
        return zOnCreate;
    }

    private String getApplicationVersionString() {
        Context context = getContext();
        if (context != null) {
            try {
                return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            } catch (Exception unused) {
            }
        }
        return "unknown";
    }

    private String getReactNativeVersionString() {
        Map<String, Object> map = ReactNativeVersion.VERSION;
        int iIntValue = ((Integer) map.get("major")).intValue();
        int iIntValue2 = ((Integer) map.get("minor")).intValue();
        int iIntValue3 = ((Integer) map.get("patch")).intValue();
        String str = (String) map.get("prerelease");
        String str2 = iIntValue + "." + iIntValue2 + "." + iIntValue3;
        return str != null ? str2 + "." + str : str2;
    }
}
