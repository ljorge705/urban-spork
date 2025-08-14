package com.google.firebase.firestore.util;

import com.google.firebase.firestore.util.AsyncQueue;
import java.util.Date;

/* loaded from: classes2.dex */
public class ExponentialBackoff {
    public static final double DEFAULT_BACKOFF_FACTOR = 1.5d;
    public static final long DEFAULT_BACKOFF_INITIAL_DELAY_MS = 1000;
    public static final long DEFAULT_BACKOFF_MAX_DELAY_MS = 60000;
    private final double backoffFactor;
    private long currentBaseMs;
    private final long initialDelayMs;
    private long lastAttemptTime;
    private final long maxDelayMs;
    private long nextMaxDelayMs;
    private final AsyncQueue queue;
    private final AsyncQueue.TimerId timerId;
    private AsyncQueue.DelayedTask timerTask;

    public void reset() {
        this.currentBaseMs = 0L;
    }

    public void resetToMax() {
        this.currentBaseMs = this.nextMaxDelayMs;
    }

    public void setTemporaryMaxDelay(long j) {
        this.nextMaxDelayMs = j;
    }

    public ExponentialBackoff(AsyncQueue asyncQueue, AsyncQueue.TimerId timerId, long j, double d, long j2) {
        this.queue = asyncQueue;
        this.timerId = timerId;
        this.initialDelayMs = j;
        this.backoffFactor = d;
        this.maxDelayMs = j2;
        this.nextMaxDelayMs = j2;
        this.lastAttemptTime = new Date().getTime();
        reset();
    }

    public ExponentialBackoff(AsyncQueue asyncQueue, AsyncQueue.TimerId timerId) {
        this(asyncQueue, timerId, 1000L, 1.5d, 60000L);
    }

    public void backoffAndRun(final Runnable runnable) {
        cancel();
        long jJitterDelayMs = this.currentBaseMs + jitterDelayMs();
        long jMax = Math.max(0L, new Date().getTime() - this.lastAttemptTime);
        long jMax2 = Math.max(0L, jJitterDelayMs - jMax);
        if (this.currentBaseMs > 0) {
            Logger.debug(getClass().getSimpleName(), "Backing off for %d ms (base delay: %d ms, delay with jitter: %d ms, last attempt: %d ms ago)", Long.valueOf(jMax2), Long.valueOf(this.currentBaseMs), Long.valueOf(jJitterDelayMs), Long.valueOf(jMax));
        }
        this.timerTask = this.queue.enqueueAfterDelay(this.timerId, jMax2, new Runnable() { // from class: com.google.firebase.firestore.util.ExponentialBackoff$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m5358x589b7455(runnable);
            }
        });
        long j = (long) (this.currentBaseMs * this.backoffFactor);
        this.currentBaseMs = j;
        long j2 = this.initialDelayMs;
        if (j < j2) {
            this.currentBaseMs = j2;
        } else {
            long j3 = this.nextMaxDelayMs;
            if (j > j3) {
                this.currentBaseMs = j3;
            }
        }
        this.nextMaxDelayMs = this.maxDelayMs;
    }

    /* renamed from: lambda$backoffAndRun$0$com-google-firebase-firestore-util-ExponentialBackoff, reason: not valid java name */
    /* synthetic */ void m5358x589b7455(Runnable runnable) {
        this.lastAttemptTime = new Date().getTime();
        runnable.run();
    }

    public void cancel() {
        AsyncQueue.DelayedTask delayedTask = this.timerTask;
        if (delayedTask != null) {
            delayedTask.cancel();
            this.timerTask = null;
        }
    }

    private long jitterDelayMs() {
        return (long) ((Math.random() - 0.5d) * this.currentBaseMs);
    }
}
