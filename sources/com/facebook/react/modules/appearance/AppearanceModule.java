package com.facebook.react.modules.appearance;

import android.content.Context;
import androidx.appcompat.app.AppCompatDelegate;
import com.facebook.fbreact.specs.NativeAppearanceSpec;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.headturn.OnfidoAnimWebView;

@ReactModule(name = NativeAppearanceSpec.NAME)
/* loaded from: classes5.dex */
public class AppearanceModule extends NativeAppearanceSpec {
    private static final String APPEARANCE_CHANGED_EVENT_NAME = "appearanceChanged";
    private String mColorScheme;
    private final OverrideColorScheme mOverrideColorScheme;

    public interface OverrideColorScheme {
        String getScheme();
    }

    @Override // com.facebook.fbreact.specs.NativeAppearanceSpec
    public void addListener(String str) {
    }

    @Override // com.facebook.fbreact.specs.NativeAppearanceSpec
    public void removeListeners(double d) {
    }

    public AppearanceModule(ReactApplicationContext reactApplicationContext) {
        this(reactApplicationContext, null);
    }

    public AppearanceModule(ReactApplicationContext reactApplicationContext, OverrideColorScheme overrideColorScheme) {
        super(reactApplicationContext);
        this.mColorScheme = OnfidoAnimWebView.THEME_LIGHT;
        this.mOverrideColorScheme = overrideColorScheme;
        this.mColorScheme = colorSchemeForCurrentConfiguration(reactApplicationContext);
    }

    private String colorSchemeForCurrentConfiguration(Context context) {
        OverrideColorScheme overrideColorScheme = this.mOverrideColorScheme;
        if (overrideColorScheme != null) {
            return overrideColorScheme.getScheme();
        }
        return (context.getResources().getConfiguration().uiMode & 48) != 32 ? OnfidoAnimWebView.THEME_LIGHT : OnfidoAnimWebView.THEME_DARK;
    }

    @Override // com.facebook.fbreact.specs.NativeAppearanceSpec
    public void setColorScheme(String str) {
        if (str.equals(OnfidoAnimWebView.THEME_DARK)) {
            AppCompatDelegate.setDefaultNightMode(2);
        } else if (str.equals(OnfidoAnimWebView.THEME_LIGHT)) {
            AppCompatDelegate.setDefaultNightMode(1);
        } else if (str.equals("unspecified")) {
            AppCompatDelegate.setDefaultNightMode(-1);
        }
    }

    @Override // com.facebook.fbreact.specs.NativeAppearanceSpec
    public String getColorScheme() {
        Context currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            currentActivity = getReactApplicationContext();
        }
        String strColorSchemeForCurrentConfiguration = colorSchemeForCurrentConfiguration(currentActivity);
        this.mColorScheme = strColorSchemeForCurrentConfiguration;
        return strColorSchemeForCurrentConfiguration;
    }

    public void onConfigurationChanged(Context context) {
        String strColorSchemeForCurrentConfiguration = colorSchemeForCurrentConfiguration(context);
        if (this.mColorScheme.equals(strColorSchemeForCurrentConfiguration)) {
            return;
        }
        this.mColorScheme = strColorSchemeForCurrentConfiguration;
        emitAppearanceChanged(strColorSchemeForCurrentConfiguration);
    }

    public void emitAppearanceChanged(String str) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putString("colorScheme", str);
        ReactApplicationContext reactApplicationContextIfActiveOrWarn = getReactApplicationContextIfActiveOrWarn();
        if (reactApplicationContextIfActiveOrWarn != null) {
            reactApplicationContextIfActiveOrWarn.emitDeviceEvent(APPEARANCE_CHANGED_EVENT_NAME, writableMapCreateMap);
        }
    }
}
