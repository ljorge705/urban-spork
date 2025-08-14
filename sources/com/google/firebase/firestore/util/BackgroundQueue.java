package com.google.firebase.firestore.util;

import java.util.concurrent.Executor;
import java.util.concurrent.Semaphore;

/* loaded from: classes2.dex */
public class BackgroundQueue implements Executor {
    private Semaphore completedTasks = new Semaphore(0);
    private int pendingTaskCount = 0;

    @Override // java.util.concurrent.Executor
    public void execute(final Runnable runnable) {
        this.pendingTaskCount++;
        Executors.BACKGROUND_EXECUTOR.execute(new Runnable() { // from class: com.google.firebase.firestore.util.BackgroundQueue$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m5357x97f26df8(runnable);
            }
        });
    }

    /* renamed from: lambda$execute$0$com-google-firebase-firestore-util-BackgroundQueue, reason: not valid java name */
    /* synthetic */ void m5357x97f26df8(Runnable runnable) {
        runnable.run();
        this.completedTasks.release();
    }

    public void drain() throws InterruptedException {
        try {
            this.completedTasks.acquire(this.pendingTaskCount);
            this.pendingTaskCount = 0;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            Assert.fail("Interrupted while waiting for background task", e);
        }
    }
}
