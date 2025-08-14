package io.sentry.react.replay;

import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;

@ReactModule(name = RNSentryReplayUnmaskManagerImpl.REACT_CLASS)
/* loaded from: classes6.dex */
public class RNSentryReplayUnmaskManager extends ViewGroupManager<RNSentryReplayUnmask> {
    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return RNSentryReplayUnmaskManagerImpl.REACT_CLASS;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public RNSentryReplayUnmask createViewInstance(ThemedReactContext themedReactContext) {
        return new RNSentryReplayUnmask(themedReactContext);
    }
}
