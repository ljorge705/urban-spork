package io.sentry.android.core;

/* loaded from: classes6.dex */
public enum NdkHandlerStrategy {
    SENTRY_HANDLER_STRATEGY_DEFAULT(0),
    SENTRY_HANDLER_STRATEGY_CHAIN_AT_START(1);

    private final int value;

    public int getValue() {
        return this.value;
    }

    NdkHandlerStrategy(int i) {
        this.value = i;
    }
}
