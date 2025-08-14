package com.onfido.reactnative.sdk;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.onfido.android.sdk.capture.config.BiometricTokenCallback;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* loaded from: classes6.dex */
public class BiometricTokenCallbackBridge implements BiometricTokenCallback {
    private Function1<? super String, Unit> provideTokenFunction;
    private final ReactContext reactContext;

    public BiometricTokenCallbackBridge(ReactContext reactContext) {
        this.reactContext = reactContext;
    }

    @Override // com.onfido.android.sdk.capture.config.BiometricTokenCallback
    public void onTokenGenerated(String str, String str2) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putString("customerUserHash", str);
        writableMapCreateMap.putString("biometricToken", str2);
        ((DeviceEventManagerModule.RCTDeviceEventEmitter) this.reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("onTokenGenerated", writableMapCreateMap);
    }

    @Override // com.onfido.android.sdk.capture.config.BiometricTokenCallback
    public void onTokenRequested(String str, Function1<? super String, Unit> function1) {
        this.provideTokenFunction = function1;
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putString("customerUserHash", str);
        ((DeviceEventManagerModule.RCTDeviceEventEmitter) this.reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("onTokenRequested", writableMapCreateMap);
    }

    public void provideToken(String str) {
        Function1<? super String, Unit> function1 = this.provideTokenFunction;
        if (function1 != null) {
            function1.invoke(str);
        }
    }
}
