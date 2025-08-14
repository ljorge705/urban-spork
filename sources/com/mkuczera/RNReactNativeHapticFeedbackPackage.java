package com.mkuczera;

import com.facebook.react.TurboReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.module.model.ReactModuleInfo;
import com.facebook.react.module.model.ReactModuleInfoProvider;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public class RNReactNativeHapticFeedbackPackage extends TurboReactPackage {
    @Override // com.facebook.react.BaseReactPackage, com.facebook.react.ReactPackage
    public NativeModule getModule(String str, ReactApplicationContext reactApplicationContext) {
        if (str.equals(RNReactNativeHapticFeedbackModuleImpl.NAME)) {
            return new RNReactNativeHapticFeedbackModule(reactApplicationContext);
        }
        return null;
    }

    @Override // com.facebook.react.BaseReactPackage
    public ReactModuleInfoProvider getReactModuleInfoProvider() {
        return new ReactModuleInfoProvider() { // from class: com.mkuczera.RNReactNativeHapticFeedbackPackage$$ExternalSyntheticLambda0
            @Override // com.facebook.react.module.model.ReactModuleInfoProvider
            public final Map getReactModuleInfos() {
                return RNReactNativeHapticFeedbackPackage.lambda$getReactModuleInfoProvider$0();
            }
        };
    }

    static /* synthetic */ Map lambda$getReactModuleInfoProvider$0() {
        HashMap map = new HashMap();
        map.put(RNReactNativeHapticFeedbackModuleImpl.NAME, new ReactModuleInfo(RNReactNativeHapticFeedbackModuleImpl.NAME, RNReactNativeHapticFeedbackModuleImpl.NAME, false, false, true, false, false));
        return map;
    }
}
