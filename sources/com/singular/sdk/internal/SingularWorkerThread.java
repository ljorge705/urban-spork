package com.singular.sdk.internal;

import android.os.Handler;
import android.os.HandlerThread;

/* loaded from: classes6.dex */
public class SingularWorkerThread extends HandlerThread {
    private Handler handler;

    public SingularWorkerThread(String str) {
        super(str);
    }

    Handler getHandler() {
        ensureInit();
        return this.handler;
    }

    public void post(Runnable runnable) {
        getHandler().post(runnable);
    }

    public void postDelayed(Runnable runnable, int i) {
        getHandler().postDelayed(runnable, i);
    }

    public void postAtFront(Runnable runnable) {
        getHandler().postAtFrontOfQueue(runnable);
    }

    private synchronized void ensureInit() {
        if (this.handler == null) {
            this.handler = new Handler(getLooper());
        }
    }
}
