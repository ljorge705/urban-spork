package com.clevertap.android.sdk.task;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;

/* loaded from: classes5.dex */
public class MainThreadExecutor implements Executor {
    Handler mainThreadHandler = new Handler(Looper.getMainLooper());

    void setMainThreadHandler(Handler handler) {
        this.mainThreadHandler = handler;
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        this.mainThreadHandler.post(runnable);
    }
}
