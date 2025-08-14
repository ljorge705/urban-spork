package com.clevertap.android.sdk.task;

import java.util.concurrent.Executor;

/* loaded from: classes5.dex */
class FailureExecutable<TResult> extends Executable<TResult> {
    private final OnFailureListener<TResult> failureListener;

    public OnFailureListener<TResult> getFailureListener() {
        return this.failureListener;
    }

    public FailureExecutable(Executor executor, OnFailureListener<TResult> onFailureListener) {
        super(executor);
        this.failureListener = onFailureListener;
    }

    @Override // com.clevertap.android.sdk.task.Executable
    void execute(final TResult tresult) {
        this.executor.execute(new Runnable() { // from class: com.clevertap.android.sdk.task.FailureExecutable.1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.lang.Runnable
            public void run() {
                FailureExecutable.this.failureListener.onFailure(tresult);
            }
        });
    }
}
