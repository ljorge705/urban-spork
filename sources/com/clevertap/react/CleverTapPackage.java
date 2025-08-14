package com.clevertap.react;

import com.facebook.react.TurboReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.module.model.ReactModuleInfo;
import com.facebook.react.module.model.ReactModuleInfoProvider;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CleverTapPackage.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"Lcom/clevertap/react/CleverTapPackage;", "Lcom/facebook/react/TurboReactPackage;", "()V", "getModule", "Lcom/facebook/react/bridge/NativeModule;", "name", "", "reactContext", "Lcom/facebook/react/bridge/ReactApplicationContext;", "getReactModuleInfoProvider", "Lcom/facebook/react/module/model/ReactModuleInfoProvider;", "clevertap-react-native_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class CleverTapPackage extends TurboReactPackage {
    @Override // com.facebook.react.BaseReactPackage
    public ReactModuleInfoProvider getReactModuleInfoProvider() {
        return new ReactModuleInfoProvider() { // from class: com.clevertap.react.CleverTapPackage$$ExternalSyntheticLambda0
            @Override // com.facebook.react.module.model.ReactModuleInfoProvider
            public final Map getReactModuleInfos() {
                return CleverTapPackage.getReactModuleInfoProvider$lambda$0();
            }
        };
    }

    @Override // com.facebook.react.BaseReactPackage, com.facebook.react.ReactPackage
    public NativeModule getModule(String name, ReactApplicationContext reactContext) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        if (Intrinsics.areEqual(name, Constants.REACT_MODULE_NAME)) {
            return new CleverTapModule(reactContext);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Map getReactModuleInfoProvider$lambda$0() {
        HashMap map = new HashMap();
        map.put(Constants.REACT_MODULE_NAME, new ReactModuleInfo(Constants.REACT_MODULE_NAME, Constants.REACT_MODULE_NAME, false, false, true, false, false));
        return map;
    }
}
