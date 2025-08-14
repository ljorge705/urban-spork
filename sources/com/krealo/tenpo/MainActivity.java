package com.krealo.tenpo;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import com.clevertap.android.sdk.CleverTapAPI;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.react.CleverTapModule;
import com.facebook.react.ReactActivity;
import com.facebook.react.ReactActivityDelegate;
import com.facebook.react.defaults.DefaultNewArchitectureEntryPoint;
import com.facebook.react.defaults.DefaultReactActivityDelegate;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.devio.rn.splashscreen.SplashScreen;

/* compiled from: MainActivity.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0014J\u0013\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0002¢\u0006\u0002\u0010\bJ\b\u0010\t\u001a\u00020\u0007H\u0014J\u0012\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0014¨\u0006\u000e"}, d2 = {"Lcom/krealo/tenpo/MainActivity;", "Lcom/facebook/react/ReactActivity;", "()V", "createReactActivityDelegate", "Lcom/facebook/react/ReactActivityDelegate;", "getCleverTapData", "", "", "()[Ljava/lang/String;", "getMainComponentName", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "app_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class MainActivity extends ReactActivity {
    @Override // com.facebook.react.ReactActivity
    protected String getMainComponentName() {
        return "tenpo";
    }

    @Override // com.facebook.react.ReactActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) throws PackageManager.NameNotFoundException {
        SplashScreen.show(this);
        super.onCreate(null);
        String[] cleverTapData = getCleverTapData();
        String str = cleverTapData[0];
        String str2 = cleverTapData[1];
        String str3 = cleverTapData[2];
        String str4 = cleverTapData[3];
        MainActivity mainActivity = this;
        CleverTapInstanceConfig cleverTapInstanceConfigCreateInstance = CleverTapInstanceConfig.createInstance(mainActivity, str, str2);
        CleverTapInstanceConfig cleverTapInstanceConfigCreateInstance2 = CleverTapInstanceConfig.createInstance(mainActivity, str3, str4);
        CleverTapAPI.instanceWithConfig(mainActivity, cleverTapInstanceConfigCreateInstance);
        CleverTapAPI.instanceWithConfig(mainActivity, cleverTapInstanceConfigCreateInstance2);
        CleverTapModule.INSTANCE.setInitialUri(getIntent().getData());
    }

    private final String[] getCleverTapData() throws PackageManager.NameNotFoundException {
        String[] strArr;
        try {
            ApplicationInfo applicationInfo = getBaseContext().getPackageManager().getApplicationInfo(getBaseContext().getPackageName(), 128);
            Intrinsics.checkNotNullExpressionValue(applicationInfo, "getApplicationInfo(...)");
            Bundle bundle = applicationInfo.metaData;
            String string = bundle.getString("PERSONAL_CLEVERTAP_ACCOUNT_ID");
            String string2 = bundle.getString("PERSONAL_CLEVERTAP_TOKEN");
            String string3 = bundle.getString("BUSINESS_CLEVERTAP_ACCOUNT_ID");
            String string4 = bundle.getString("BUSINESS_CLEVERTAP_TOKEN");
            Log.i("clevertap", "clevertap data extracted successful");
            strArr = new String[4];
            if (string == null) {
                string = "";
            }
            strArr[0] = string;
            if (string2 == null) {
                string2 = "";
            }
            strArr[1] = string2;
            if (string3 == null) {
                string3 = "";
            }
            strArr[2] = string3;
            if (string4 == null) {
                string4 = "";
            }
            strArr[3] = string4;
        } catch (PackageManager.NameNotFoundException e) {
            strArr = new String[1];
            String message = e.getMessage();
            strArr[0] = message != null ? message : "";
        }
        return strArr;
    }

    @Override // com.facebook.react.ReactActivity
    protected ReactActivityDelegate createReactActivityDelegate() {
        return new DefaultReactActivityDelegate(this, getMainComponentName(), DefaultNewArchitectureEntryPoint.getFabricEnabled());
    }
}
