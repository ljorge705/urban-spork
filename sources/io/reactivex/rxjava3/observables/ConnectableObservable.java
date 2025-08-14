package io.reactivex.rxjava3.observables;

import io.reactivex.rxjava3.annotations.CheckReturnValue;
import io.reactivex.rxjava3.annotations.SchedulerSupport;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.internal.functions.Functions;
import io.reactivex.rxjava3.internal.functions.ObjectHelper;
import io.reactivex.rxjava3.internal.operators.observable.ObservableAutoConnect;
import io.reactivex.rxjava3.internal.operators.observable.ObservableRefCount;
import io.reactivex.rxjava3.internal.util.ConnectConsumer;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/* loaded from: classes6.dex */
public abstract class ConnectableObservable<T> extends Observable<T> {
    @SchedulerSupport("none")
    public abstract void connect(Consumer<? super Disposable> connection);

    @SchedulerSupport("none")
    public abstract void reset();

    @SchedulerSupport("none")
    public final Disposable connect() {
        ConnectConsumer connectConsumer = new ConnectConsumer();
        connect(connectConsumer);
        return connectConsumer.disposable;
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public Observable<T> refCount() {
        return RxJavaPlugins.onAssembly(new ObservableRefCount(this));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<T> refCount(int observerCount) {
        return refCount(observerCount, 0L, TimeUnit.NANOSECONDS, Schedulers.trampoline());
    }

    @CheckReturnValue
    @SchedulerSupport(SchedulerSupport.COMPUTATION)
    public final Observable<T> refCount(long timeout, TimeUnit unit) {
        return refCount(1, timeout, unit, Schedulers.computation());
    }

    @CheckReturnValue
    @SchedulerSupport(SchedulerSupport.CUSTOM)
    public final Observable<T> refCount(long timeout, TimeUnit unit, Scheduler scheduler) {
        return refCount(1, timeout, unit, scheduler);
    }

    @CheckReturnValue
    @SchedulerSupport(SchedulerSupport.COMPUTATION)
    public final Observable<T> refCount(int observerCount, long timeout, TimeUnit unit) {
        return refCount(observerCount, timeout, unit, Schedulers.computation());
    }

    @CheckReturnValue
    @SchedulerSupport(SchedulerSupport.CUSTOM)
    public final Observable<T> refCount(int observerCount, long timeout, TimeUnit unit, Scheduler scheduler) {
        ObjectHelper.verifyPositive(observerCount, "observerCount");
        Objects.requireNonNull(unit, "unit is null");
        Objects.requireNonNull(scheduler, "scheduler is null");
        return RxJavaPlugins.onAssembly(new ObservableRefCount(this, observerCount, timeout, unit, scheduler));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public Observable<T> autoConnect() {
        return autoConnect(1);
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public Observable<T> autoConnect(int numberOfObservers) {
        return autoConnect(numberOfObservers, Functions.emptyConsumer());
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public Observable<T> autoConnect(int numberOfObservers, Consumer<? super Disposable> connection) {
        Objects.requireNonNull(connection, "connection is null");
        if (numberOfObservers <= 0) {
            connect(connection);
            return RxJavaPlugins.onAssembly((ConnectableObservable) this);
        }
        return RxJavaPlugins.onAssembly(new ObservableAutoConnect(this, numberOfObservers, connection));
    }
}
