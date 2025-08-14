package io.sentry.react.replay;

import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;

@ReactModule(name = RNSentryReplayMaskManagerImpl.REACT_CLASS)
/* loaded from: classes6.dex */
public class RNSentryReplayMaskManager extends ViewGroupManager<RNSentryReplayMask> {
    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return RNSentryReplayMaskManagerImpl.REACT_CLASS;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public RNSentryReplayMask createViewInstance(ThemedReactContext themedReactContext) {
        return new RNSentryReplayMask(themedReactContext);
    }
}
