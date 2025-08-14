package com.screenprotection;

import android.app.Activity;
import android.view.Window;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.UiThreadUtil;

/* loaded from: classes6.dex */
public class ScreenProtectionModule extends ReactContextBaseJavaModule {
    private Activity mActivity;
    private Runnable mClearRunnable;
    private Runnable mSecureRunnable;

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "ScreenProtectionModule";
    }

    public ScreenProtectionModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mSecureRunnable = new Runnable() { // from class: com.screenprotection.ScreenProtectionModule.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Window window = ScreenProtectionModule.this.mActivity.getWindow();
                    if (window != null) {
                        window.setFlags(8192, 8192);
                    }
                } catch (Exception unused) {
                }
            }
        };
        this.mClearRunnable = new Runnable() { // from class: com.screenprotection.ScreenProtectionModule.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Window window = ScreenProtectionModule.this.mActivity.getWindow();
                    if (window != null) {
                        window.clearFlags(8192);
                    }
                } catch (Exception unused) {
                }
            }
        };
    }

    @ReactMethod
    public void enableScreenProtection() {
        Activity currentActivity = getCurrentActivity();
        this.mActivity = currentActivity;
        if (currentActivity != null) {
            UiThreadUtil.runOnUiThread(this.mSecureRunnable);
        }
    }

    @ReactMethod
    public void disableScreenProtection() {
        Activity currentActivity = getCurrentActivity();
        this.mActivity = currentActivity;
        if (currentActivity != null) {
            UiThreadUtil.runOnUiThread(this.mClearRunnable);
        }
    }
}
