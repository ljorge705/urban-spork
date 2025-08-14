package com.mkuczera;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;

/* loaded from: classes2.dex */
public class RNReactNativeHapticFeedbackModule extends ReactContextBaseJavaModule {
    ReactApplicationContext reactContext;

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return RNReactNativeHapticFeedbackModuleImpl.NAME;
    }

    RNReactNativeHapticFeedbackModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.reactContext = reactApplicationContext;
    }

    @ReactMethod
    public void trigger(String str, ReadableMap readableMap) {
        RNReactNativeHapticFeedbackModuleImpl.trigger(this.reactContext, str, readableMap);
    }
}
