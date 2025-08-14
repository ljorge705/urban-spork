package androidx.work;

/* loaded from: classes5.dex */
public interface RunnableScheduler {
    void cancel(Runnable runnable);

    void scheduleWithDelay(long delayInMillis, Runnable runnable);
}
