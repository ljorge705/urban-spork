package com.facebook.react.common;

import com.facebook.infer.annotation.Assertions;

/* loaded from: classes5.dex */
public class SingleThreadAsserter {
    private Thread mThread = null;

    public void assertNow() {
        Thread threadCurrentThread = Thread.currentThread();
        if (this.mThread == null) {
            this.mThread = threadCurrentThread;
        }
        Assertions.assertCondition(this.mThread == threadCurrentThread);
    }
}
