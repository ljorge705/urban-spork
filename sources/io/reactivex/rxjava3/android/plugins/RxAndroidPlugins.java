package io.reactivex.rxjava3.android.plugins;

import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Function;
import java.util.concurrent.Callable;

/* loaded from: classes6.dex */
public final class RxAndroidPlugins {
    private static volatile Function<Callable<Scheduler>, Scheduler> onInitMainThreadHandler;
    private static volatile Function<Scheduler, Scheduler> onMainThreadHandler;

    public static Function<Callable<Scheduler>, Scheduler> getInitMainThreadSchedulerHandler() {
        return onInitMainThreadHandler;
    }

    public static Function<Scheduler, Scheduler> getOnMainThreadSchedulerHandler() {
        return onMainThreadHandler;
    }

    public static void setInitMainThreadSchedulerHandler(Function<Callable<Scheduler>, Scheduler> function) {
        onInitMainThreadHandler = function;
    }

    public static void setMainThreadSchedulerHandler(Function<Scheduler, Scheduler> function) {
        onMainThreadHandler = function;
    }

    public static Scheduler initMainThreadScheduler(Callable<Scheduler> callable) {
        if (callable == null) {
            throw new NullPointerException("scheduler == null");
        }
        Function<Callable<Scheduler>, Scheduler> function = onInitMainThreadHandler;
        if (function == null) {
            return callRequireNonNull(callable);
        }
        return applyRequireNonNull(function, callable);
    }

    public static Scheduler onMainThreadScheduler(Scheduler scheduler) {
        if (scheduler == null) {
            throw new NullPointerException("scheduler == null");
        }
        Function<Scheduler, Scheduler> function = onMainThreadHandler;
        return function == null ? scheduler : (Scheduler) apply(function, scheduler);
    }

    public static void reset() {
        setInitMainThreadSchedulerHandler(null);
        setMainThreadSchedulerHandler(null);
    }

    static Scheduler callRequireNonNull(Callable<Scheduler> callable) {
        try {
            Scheduler schedulerCall = callable.call();
            if (schedulerCall != null) {
                return schedulerCall;
            }
            throw new NullPointerException("Scheduler Callable returned null");
        } catch (Throwable th) {
            throw Exceptions.propagate(th);
        }
    }

    static Scheduler applyRequireNonNull(Function<Callable<Scheduler>, Scheduler> function, Callable<Scheduler> callable) {
        Scheduler scheduler = (Scheduler) apply(function, callable);
        if (scheduler != null) {
            return scheduler;
        }
        throw new NullPointerException("Scheduler Callable returned null");
    }

    static <T, R> R apply(Function<T, R> function, T t) {
        try {
            return function.apply(t);
        } catch (Throwable th) {
            throw Exceptions.propagate(th);
        }
    }

    private RxAndroidPlugins() {
        throw new AssertionError("No instances.");
    }
}
