package io.reactivex.rxjava3.schedulers;

import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes6.dex */
public final class TestScheduler extends Scheduler {
    long counter;
    final Queue<TimedRunnable> queue;
    volatile long time;
    final boolean useOnScheduleHook;

    public TestScheduler() {
        this(false);
    }

    public TestScheduler(boolean useOnScheduleHook) {
        this.queue = new PriorityBlockingQueue(11);
        this.useOnScheduleHook = useOnScheduleHook;
    }

    public TestScheduler(long delayTime, TimeUnit unit) {
        this(delayTime, unit, false);
    }

    public TestScheduler(long delayTime, TimeUnit unit, boolean useOnScheduleHook) {
        this.queue = new PriorityBlockingQueue(11);
        this.time = unit.toNanos(delayTime);
        this.useOnScheduleHook = useOnScheduleHook;
    }

    static final class TimedRunnable implements Comparable<TimedRunnable> {
        final long count;
        final Runnable run;
        final TestWorker scheduler;
        final long time;

        TimedRunnable(TestWorker scheduler, long time, Runnable run, long count) {
            this.time = time;
            this.run = run;
            this.scheduler = scheduler;
            this.count = count;
        }

        public String toString() {
            return String.format("TimedRunnable(time = %d, run = %s)", Long.valueOf(this.time), this.run.toString());
        }

        @Override // java.lang.Comparable
        public int compareTo(TimedRunnable o) {
            long j = this.time;
            long j2 = o.time;
            if (j == j2) {
                return Long.compare(this.count, o.count);
            }
            return Long.compare(j, j2);
        }
    }

    @Override // io.reactivex.rxjava3.core.Scheduler
    public long now(TimeUnit unit) {
        return unit.convert(this.time, TimeUnit.NANOSECONDS);
    }

    public void advanceTimeBy(long delayTime, TimeUnit unit) {
        advanceTimeTo(this.time + unit.toNanos(delayTime), TimeUnit.NANOSECONDS);
    }

    public void advanceTimeTo(long delayTime, TimeUnit unit) {
        triggerActions(unit.toNanos(delayTime));
    }

    public void triggerActions() {
        triggerActions(this.time);
    }

    private void triggerActions(long targetTimeInNanoseconds) {
        while (true) {
            TimedRunnable timedRunnablePeek = this.queue.peek();
            if (timedRunnablePeek == null || timedRunnablePeek.time > targetTimeInNanoseconds) {
                break;
            }
            this.time = timedRunnablePeek.time == 0 ? this.time : timedRunnablePeek.time;
            this.queue.remove(timedRunnablePeek);
            if (!timedRunnablePeek.scheduler.disposed) {
                timedRunnablePeek.run.run();
            }
        }
        this.time = targetTimeInNanoseconds;
    }

    @Override // io.reactivex.rxjava3.core.Scheduler
    public Scheduler.Worker createWorker() {
        return new TestWorker();
    }

    final class TestWorker extends Scheduler.Worker {
        volatile boolean disposed;

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            this.disposed = true;
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.disposed;
        }

        TestWorker() {
        }

        @Override // io.reactivex.rxjava3.core.Scheduler.Worker
        public Disposable schedule(Runnable run, long delayTime, TimeUnit unit) {
            if (this.disposed) {
                return EmptyDisposable.INSTANCE;
            }
            if (TestScheduler.this.useOnScheduleHook) {
                run = RxJavaPlugins.onSchedule(run);
            }
            long nanos = TestScheduler.this.time + unit.toNanos(delayTime);
            TestScheduler testScheduler = TestScheduler.this;
            long j = testScheduler.counter;
            testScheduler.counter = 1 + j;
            TimedRunnable timedRunnable = new TimedRunnable(this, nanos, run, j);
            TestScheduler.this.queue.add(timedRunnable);
            return new QueueRemove(timedRunnable);
        }

        @Override // io.reactivex.rxjava3.core.Scheduler.Worker
        public Disposable schedule(Runnable run) {
            if (this.disposed) {
                return EmptyDisposable.INSTANCE;
            }
            if (TestScheduler.this.useOnScheduleHook) {
                run = RxJavaPlugins.onSchedule(run);
            }
            TestScheduler testScheduler = TestScheduler.this;
            long j = testScheduler.counter;
            testScheduler.counter = 1 + j;
            TimedRunnable timedRunnable = new TimedRunnable(this, 0L, run, j);
            TestScheduler.this.queue.add(timedRunnable);
            return new QueueRemove(timedRunnable);
        }

        @Override // io.reactivex.rxjava3.core.Scheduler.Worker
        public long now(TimeUnit unit) {
            return TestScheduler.this.now(unit);
        }

        final class QueueRemove extends AtomicReference<TimedRunnable> implements Disposable {
            private static final long serialVersionUID = -7874968252110604360L;

            QueueRemove(TimedRunnable timedAction) {
                lazySet(timedAction);
            }

            @Override // io.reactivex.rxjava3.disposables.Disposable
            public void dispose() {
                TimedRunnable andSet = getAndSet(null);
                if (andSet != null) {
                    TestScheduler.this.queue.remove(andSet);
                }
            }

            @Override // io.reactivex.rxjava3.disposables.Disposable
            public boolean isDisposed() {
                return get() == null;
            }
        }
    }
}
