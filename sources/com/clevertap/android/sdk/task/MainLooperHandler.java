package com.clevertap.android.sdk.task;

import android.os.Handler;
import android.os.Looper;

/* loaded from: classes5.dex */
public class MainLooperHandler extends Handler {
    private Runnable pendingRunnable;

    public Runnable getPendingRunnable() {
        return this.pendingRunnable;
    }

    public void setPendingRunnable(Runnable runnable) {
        this.pendingRunnable = runnable;
    }

    public MainLooperHandler() {
        super(Looper.getMainLooper());
        this.pendingRunnable = null;
    }
}
