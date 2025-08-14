package io.reactivex.rxjava3.internal.schedulers;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.functions.Functions;
import io.reactivex.rxjava3.schedulers.SchedulerRunnableIntrospection;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes6.dex */
abstract class AbstractDirectTask extends AtomicReference<Future<?>> implements Disposable, SchedulerRunnableIntrospection {
    private static final long serialVersionUID = 1811839108042568751L;
    protected final boolean interruptOnCancel;
    protected final Runnable runnable;
    protected Thread runner;
    protected static final FutureTask<Void> FINISHED = new FutureTask<>(Functions.EMPTY_RUNNABLE, null);
    protected static final FutureTask<Void> DISPOSED = new FutureTask<>(Functions.EMPTY_RUNNABLE, null);

    @Override // io.reactivex.rxjava3.schedulers.SchedulerRunnableIntrospection
    public Runnable getWrappedRunnable() {
        return this.runnable;
    }

    AbstractDirectTask(Runnable runnable, boolean interruptOnCancel) {
        this.runnable = runnable;
        this.interruptOnCancel = interruptOnCancel;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public final void dispose() {
        FutureTask<Void> futureTask;
        Future<?> future = get();
        if (future == FINISHED || future == (futureTask = DISPOSED) || !compareAndSet(future, futureTask) || future == null) {
            return;
        }
        cancelFuture(future);
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public final boolean isDisposed() {
        Future<?> future = get();
        return future == FINISHED || future == DISPOSED;
    }

    public final void setFuture(Future<?> future) {
        Future<?> future2;
        do {
            future2 = get();
            if (future2 == FINISHED) {
                return;
            }
            if (future2 == DISPOSED) {
                cancelFuture(future);
                return;
            }
        } while (!compareAndSet(future2, future));
    }

    private void cancelFuture(Future<?> future) {
        if (this.runner == Thread.currentThread()) {
            future.cancel(false);
        } else {
            future.cancel(this.interruptOnCancel);
        }
    }

    @Override // java.util.concurrent.atomic.AtomicReference
    public String toString() {
        String str;
        Future<?> future = get();
        if (future == FINISHED) {
            str = "Finished";
        } else if (future == DISPOSED) {
            str = "Disposed";
        } else {
            str = this.runner != null ? "Running on " + this.runner : "Waiting";
        }
        return getClass().getSimpleName() + "[" + str + "]";
    }
}
