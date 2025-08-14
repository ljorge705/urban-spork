package com.google.firebase.storage.internal;

import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.storage.StorageTaskScheduler;
import java.util.concurrent.Executor;

/* loaded from: classes2.dex */
public class SmartHandler {
    static boolean testMode = false;
    private final Executor executor;
    private final Handler handler;

    public SmartHandler(Executor executor) {
        this.executor = executor;
        if (executor != null) {
            this.handler = null;
        } else if (testMode) {
            this.handler = null;
        } else {
            this.handler = new Handler(Looper.getMainLooper());
        }
    }

    public void callBack(Runnable runnable) {
        Preconditions.checkNotNull(runnable);
        Handler handler = this.handler;
        if (handler != null) {
            handler.post(runnable);
            return;
        }
        Executor executor = this.executor;
        if (executor != null) {
            executor.execute(runnable);
        } else {
            StorageTaskScheduler.getInstance().scheduleCallback(runnable);
        }
    }
}
