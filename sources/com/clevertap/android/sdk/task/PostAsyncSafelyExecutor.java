package com.clevertap.android.sdk.task;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/* loaded from: classes5.dex */
class PostAsyncSafelyExecutor implements ExecutorService {
    private long EXECUTOR_THREAD_ID = 0;
    ExecutorService executor = Executors.newSingleThreadExecutor();

    void setExecutor(ExecutorService executorService) {
        this.executor = executorService;
    }

    PostAsyncSafelyExecutor() {
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        return this.executor.awaitTermination(j, timeUnit);
    }

    @Override // java.util.concurrent.Executor
    public void execute(final Runnable runnable) {
        if (runnable == null) {
            throw new NullPointerException("PostAsyncSafelyExecutor#execute: task can't ne null");
        }
        if (Thread.currentThread().getId() == this.EXECUTOR_THREAD_ID) {
            runnable.run();
        } else {
            this.executor.execute(new Runnable() { // from class: com.clevertap.android.sdk.task.PostAsyncSafelyExecutor.1
                @Override // java.lang.Runnable
                public void run() {
                    PostAsyncSafelyExecutor.this.EXECUTOR_THREAD_ID = Thread.currentThread().getId();
                    runnable.run();
                }
            });
        }
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("PostAsyncSafelyExecutor#invokeAll: This method is not supported");
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection, long j, TimeUnit timeUnit) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("PostAsyncSafelyExecutor#invokeAll: This method is not supported");
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> T invokeAny(Collection<? extends Callable<T>> collection) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("PostAsyncSafelyExecutor#invokeAny: This method is not supported");
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> T invokeAny(Collection<? extends Callable<T>> collection, long j, TimeUnit timeUnit) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("PostAsyncSafelyExecutor#invokeAny: This method is not supported");
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isShutdown() {
        return this.executor.isShutdown();
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isTerminated() {
        return this.executor.isTerminated();
    }

    @Override // java.util.concurrent.ExecutorService
    public void shutdown() {
        this.executor.shutdown();
    }

    @Override // java.util.concurrent.ExecutorService
    public List<Runnable> shutdownNow() {
        return this.executor.shutdownNow();
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> Future<T> submit(final Callable<T> callable) throws Exception {
        if (callable == null) {
            throw new NullPointerException("PostAsyncSafelyExecutor#submit: task can't ne null");
        }
        if (Thread.currentThread().getId() == this.EXECUTOR_THREAD_ID) {
            try {
                callable.call();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        return this.executor.submit(new Callable<T>() { // from class: com.clevertap.android.sdk.task.PostAsyncSafelyExecutor.2
            @Override // java.util.concurrent.Callable
            public T call() throws Exception {
                PostAsyncSafelyExecutor.this.EXECUTOR_THREAD_ID = Thread.currentThread().getId();
                return (T) callable.call();
            }
        });
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> Future<T> submit(Runnable runnable, T t) {
        if (runnable == null) {
            throw new NullPointerException("PostAsyncSafelyExecutor#submit: task can't ne null");
        }
        FutureTask futureTask = new FutureTask(runnable, t);
        execute(futureTask);
        return futureTask;
    }

    @Override // java.util.concurrent.ExecutorService
    public Future<?> submit(Runnable runnable) {
        if (runnable == null) {
            throw new NullPointerException("PostAsyncSafelyExecutor#submit: task can't ne null");
        }
        FutureTask futureTask = new FutureTask(runnable, null);
        execute(futureTask);
        return futureTask;
    }
}
