package io.reactivex.rxjava3.internal.schedulers;

import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.disposables.DisposableContainer;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/* loaded from: classes6.dex */
public class NewThreadWorker extends Scheduler.Worker implements Disposable {
    volatile boolean disposed;
    private final ScheduledExecutorService executor;

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.disposed;
    }

    public NewThreadWorker(ThreadFactory threadFactory) {
        this.executor = SchedulerPoolFactory.create(threadFactory);
    }

    @Override // io.reactivex.rxjava3.core.Scheduler.Worker
    public Disposable schedule(final Runnable run) {
        return schedule(run, 0L, null);
    }

    @Override // io.reactivex.rxjava3.core.Scheduler.Worker
    public Disposable schedule(final Runnable action, long delayTime, TimeUnit unit) {
        if (this.disposed) {
            return EmptyDisposable.INSTANCE;
        }
        return scheduleActual(action, delayTime, unit, null);
    }

    public Disposable scheduleDirect(final Runnable run, long delayTime, TimeUnit unit) {
        Future<?> futureSchedule;
        ScheduledDirectTask scheduledDirectTask = new ScheduledDirectTask(RxJavaPlugins.onSchedule(run), true);
        try {
            if (delayTime <= 0) {
                futureSchedule = this.executor.submit(scheduledDirectTask);
            } else {
                futureSchedule = this.executor.schedule(scheduledDirectTask, delayTime, unit);
            }
            scheduledDirectTask.setFuture(futureSchedule);
            return scheduledDirectTask;
        } catch (RejectedExecutionException e) {
            RxJavaPlugins.onError(e);
            return EmptyDisposable.INSTANCE;
        }
    }

    public Disposable schedulePeriodicallyDirect(Runnable run, long initialDelay, long period, TimeUnit unit) {
        Future<?> futureSchedule;
        Runnable runnableOnSchedule = RxJavaPlugins.onSchedule(run);
        if (period <= 0) {
            InstantPeriodicTask instantPeriodicTask = new InstantPeriodicTask(runnableOnSchedule, this.executor);
            try {
                if (initialDelay <= 0) {
                    futureSchedule = this.executor.submit(instantPeriodicTask);
                } else {
                    futureSchedule = this.executor.schedule(instantPeriodicTask, initialDelay, unit);
                }
                instantPeriodicTask.setFirst(futureSchedule);
                return instantPeriodicTask;
            } catch (RejectedExecutionException e) {
                RxJavaPlugins.onError(e);
                return EmptyDisposable.INSTANCE;
            }
        }
        ScheduledDirectPeriodicTask scheduledDirectPeriodicTask = new ScheduledDirectPeriodicTask(runnableOnSchedule, true);
        try {
            scheduledDirectPeriodicTask.setFuture(this.executor.scheduleAtFixedRate(scheduledDirectPeriodicTask, initialDelay, period, unit));
            return scheduledDirectPeriodicTask;
        } catch (RejectedExecutionException e2) {
            RxJavaPlugins.onError(e2);
            return EmptyDisposable.INSTANCE;
        }
    }

    public ScheduledRunnable scheduleActual(final Runnable run, long delayTime, TimeUnit unit, DisposableContainer parent) {
        Future<?> futureSchedule;
        ScheduledRunnable scheduledRunnable = new ScheduledRunnable(RxJavaPlugins.onSchedule(run), parent);
        if (parent != null && !parent.add(scheduledRunnable)) {
            return scheduledRunnable;
        }
        try {
            if (delayTime <= 0) {
                futureSchedule = this.executor.submit((Callable) scheduledRunnable);
            } else {
                futureSchedule = this.executor.schedule((Callable) scheduledRunnable, delayTime, unit);
            }
            scheduledRunnable.setFuture(futureSchedule);
        } catch (RejectedExecutionException e) {
            if (parent != null) {
                parent.remove(scheduledRunnable);
            }
            RxJavaPlugins.onError(e);
        }
        return scheduledRunnable;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        if (this.disposed) {
            return;
        }
        this.disposed = true;
        this.executor.shutdownNow();
    }

    public void shutdown() {
        if (this.disposed) {
            return;
        }
        this.disposed = true;
        this.executor.shutdown();
    }
}
