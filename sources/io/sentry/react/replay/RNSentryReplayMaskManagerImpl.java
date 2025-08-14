package io.sentry.react.replay;

import com.facebook.react.uimanager.ThemedReactContext;

/* loaded from: classes6.dex */
public final class RNSentryReplayMaskManagerImpl {
    public static final String REACT_CLASS = "RNSentryReplayMask";

    private RNSentryReplayMaskManagerImpl() {
    }

    public static RNSentryReplayMask createViewInstance(ThemedReactContext themedReactContext) {
        return new RNSentryReplayMask(themedReactContext);
    }
}
