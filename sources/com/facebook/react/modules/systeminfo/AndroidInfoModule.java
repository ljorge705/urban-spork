package com.facebook.react.modules.systeminfo;

import android.app.UiModeManager;
import android.os.Build;
import android.provider.Settings;
import androidx.exifinterface.media.ExifInterface;
import com.clevertap.android.sdk.Constants;
import com.facebook.fbreact.specs.NativePlatformConstantsAndroidSpec;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;
import com.oblador.keychain.KeychainModule;
import java.util.HashMap;
import java.util.Map;

@ReactModule(name = NativePlatformConstantsAndroidSpec.NAME)
/* loaded from: classes5.dex */
public class AndroidInfoModule extends NativePlatformConstantsAndroidSpec implements TurboModule {
    private static final String IS_DISABLE_ANIMATIONS = "IS_DISABLE_ANIMATIONS";
    private static final String IS_TESTING = "IS_TESTING";

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule, com.facebook.react.turbomodule.core.interfaces.TurboModule
    public void invalidate() {
    }

    public AndroidInfoModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    private String uiMode() {
        int currentModeType = ((UiModeManager) getReactApplicationContext().getSystemService("uimode")).getCurrentModeType();
        return currentModeType != 1 ? currentModeType != 2 ? currentModeType != 3 ? currentModeType != 4 ? currentModeType != 6 ? currentModeType != 7 ? "unknown" : "vrheadset" : "watch" : "tv" : "car" : "desk" : "normal";
    }

    @Override // com.facebook.fbreact.specs.NativePlatformConstantsAndroidSpec
    public Map<String, Object> getTypedExportedConstants() {
        HashMap map = new HashMap();
        map.put(Constants.CLTAP_APP_VERSION, Integer.valueOf(Build.VERSION.SDK_INT));
        map.put("Release", Build.VERSION.RELEASE);
        map.put("Serial", Build.SERIAL);
        map.put(KeychainModule.FINGERPRINT_SUPPORTED_NAME, Build.FINGERPRINT);
        map.put(ExifInterface.TAG_MODEL, Build.MODEL);
        map.put("Manufacturer", Build.MANUFACTURER);
        map.put("Brand", Build.BRAND);
        map.put("isTesting", Boolean.valueOf("true".equals(System.getProperty(IS_TESTING)) || isRunningScreenshotTest().booleanValue()));
        String property = System.getProperty(IS_DISABLE_ANIMATIONS);
        if (property != null) {
            map.put("isDisableAnimations", Boolean.valueOf("true".equals(property)));
        }
        map.put("reactNativeVersion", ReactNativeVersion.VERSION);
        map.put("uiMode", uiMode());
        return map;
    }

    @Override // com.facebook.fbreact.specs.NativePlatformConstantsAndroidSpec
    public String getAndroidID() {
        return Settings.Secure.getString(getReactApplicationContext().getContentResolver(), "android_id");
    }

    private Boolean isRunningScreenshotTest() throws ClassNotFoundException {
        try {
            Class.forName("com.facebook.testing.react.screenshots.ReactAppScreenshotTestActivity");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }
}
