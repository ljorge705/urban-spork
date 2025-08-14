package com.clevertap.android.sdk.task;

import java.util.concurrent.Executor;

/* loaded from: classes5.dex */
class SuccessExecutable<TResult> extends Executable<TResult> {
    private final OnSuccessListener<TResult> successListener;

    public OnSuccessListener<TResult> getSuccessListener() {
        return this.successListener;
    }

    protected SuccessExecutable(Executor executor, OnSuccessListener<TResult> onSuccessListener) {
        super(executor);
        this.successListener = onSuccessListener;
    }

    @Override // com.clevertap.android.sdk.task.Executable
    void execute(final TResult tresult) {
        this.executor.execute(new Runnable() { // from class: com.clevertap.android.sdk.task.SuccessExecutable$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m4806x4e6c24d3(tresult);
            }
        });
    }

    /* renamed from: lambda$execute$0$com-clevertap-android-sdk-task-SuccessExecutable, reason: not valid java name */
    /* synthetic */ void m4806x4e6c24d3(Object obj) {
        this.successListener.onSuccess(obj);
    }
}
