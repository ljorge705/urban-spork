package io.sentry.react;

import com.facebook.react.TurboReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.module.model.ReactModuleInfo;
import com.facebook.react.module.model.ReactModuleInfoProvider;
import com.facebook.react.uimanager.ViewManager;
import io.sentry.react.replay.RNSentryReplayMaskManager;
import io.sentry.react.replay.RNSentryReplayMaskManagerImpl;
import io.sentry.react.replay.RNSentryReplayUnmaskManager;
import io.sentry.react.replay.RNSentryReplayUnmaskManagerImpl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class RNSentryPackage extends TurboReactPackage {
    private static final boolean isTurboModule = false;

    @Override // com.facebook.react.BaseReactPackage, com.facebook.react.ReactPackage
    public NativeModule getModule(String str, ReactApplicationContext reactApplicationContext) {
        if (RNSentryModuleImpl.NAME.equals(str)) {
            return new RNSentryModule(reactApplicationContext);
        }
        return null;
    }

    private NativeModule getFabricComponentNativeModule(String str) {
        if (RNSentryReplayMaskManagerImpl.REACT_CLASS.equals(str)) {
            return new RNSentryReplayMaskManager();
        }
        if (RNSentryReplayUnmaskManagerImpl.REACT_CLASS.equals(str)) {
            return new RNSentryReplayUnmaskManager();
        }
        return null;
    }

    @Override // com.facebook.react.BaseReactPackage
    public ReactModuleInfoProvider getReactModuleInfoProvider() {
        return new ReactModuleInfoProvider() { // from class: io.sentry.react.RNSentryPackage$$ExternalSyntheticLambda2
            @Override // com.facebook.react.module.model.ReactModuleInfoProvider
            public final Map getReactModuleInfos() {
                return RNSentryPackage.lambda$getReactModuleInfoProvider$0();
            }
        };
    }

    static /* synthetic */ Map lambda$getReactModuleInfoProvider$0() {
        HashMap map = new HashMap();
        map.put(RNSentryModuleImpl.NAME, new ReactModuleInfo(RNSentryModuleImpl.NAME, RNSentryModuleImpl.NAME, false, false, true, false, false));
        return map;
    }

    @Override // com.facebook.react.BaseReactPackage, com.facebook.react.ReactPackage
    public List<ViewManager> createViewManagers(ReactApplicationContext reactApplicationContext) {
        return RNSentryPackage$$ExternalSyntheticBackport0.m(new Object[]{new RNSentryOnDrawReporterManager(reactApplicationContext), new RNSentryReplayMaskManager(), new RNSentryReplayUnmaskManager()});
    }
}
