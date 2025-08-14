package io.sentry.react.replay;

import com.facebook.react.uimanager.ThemedReactContext;

/* loaded from: classes6.dex */
public final class RNSentryReplayUnmaskManagerImpl {
    public static final String REACT_CLASS = "RNSentryReplayUnmask";

    private RNSentryReplayUnmaskManagerImpl() {
    }

    public RNSentryReplayUnmask createViewInstance(ThemedReactContext themedReactContext) {
        return new RNSentryReplayUnmask(themedReactContext);
    }
}
