package com.microsoft.codepush.react;

/* loaded from: classes2.dex */
public enum CodePushUpdateState {
    RUNNING(0),
    PENDING(1),
    LATEST(2);

    private final int value;

    public int getValue() {
        return this.value;
    }

    CodePushUpdateState(int i) {
        this.value = i;
    }
}
