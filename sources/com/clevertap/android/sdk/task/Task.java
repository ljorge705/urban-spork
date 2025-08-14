package com.clevertap.android.sdk.task;

import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.Logger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/* loaded from: classes5.dex */
public class Task<TResult> {
    protected final CleverTapInstanceConfig config;
    protected final Executor defaultCallbackExecutor;
    protected final Executor executor;
    protected TResult result;
    private final String taskName;
    protected final List<FailureExecutable<Exception>> failureExecutables = new ArrayList();
    protected final List<SuccessExecutable<TResult>> successExecutables = new ArrayList();
    protected STATE taskState = STATE.READY_TO_RUN;

    protected enum STATE {
        FAILED,
        SUCCESS,
        READY_TO_RUN,
        RUNNING
    }

    void setResult(TResult tresult) {
        this.result = tresult;
    }

    void setState(STATE state) {
        this.taskState = state;
    }

    Task(CleverTapInstanceConfig cleverTapInstanceConfig, Executor executor, Executor executor2, String str) {
        this.executor = executor;
        this.defaultCallbackExecutor = executor2;
        this.config = cleverTapInstanceConfig;
        this.taskName = str;
    }

    public synchronized Task<TResult> addOnFailureListener(Executor executor, OnFailureListener<Exception> onFailureListener) {
        if (onFailureListener != null) {
            this.failureExecutables.add(new FailureExecutable<>(executor, onFailureListener));
        }
        return this;
    }

    public Task<TResult> addOnFailureListener(OnFailureListener<Exception> onFailureListener) {
        return addOnFailureListener(this.defaultCallbackExecutor, onFailureListener);
    }

    public Task<TResult> addOnSuccessListener(Executor executor, OnSuccessListener<TResult> onSuccessListener) {
        if (onSuccessListener != null) {
            this.successExecutables.add(new SuccessExecutable<>(executor, onSuccessListener));
        }
        return this;
    }

    public Task<TResult> addOnSuccessListener(OnSuccessListener<TResult> onSuccessListener) {
        return addOnSuccessListener(this.defaultCallbackExecutor, onSuccessListener);
    }

    public void execute(String str, Callable<TResult> callable) {
        this.executor.execute(newRunnableForTask(str, callable));
    }

    public boolean isSuccess() {
        return this.taskState == STATE.SUCCESS;
    }

    public Task<TResult> removeOnFailureListener(OnFailureListener<Exception> onFailureListener) {
        Iterator<FailureExecutable<Exception>> it = this.failureExecutables.iterator();
        while (it.hasNext()) {
            if (it.next().getFailureListener() == onFailureListener) {
                it.remove();
            }
        }
        return this;
    }

    public Task<TResult> removeOnSuccessListener(OnSuccessListener<TResult> onSuccessListener) {
        Iterator<SuccessExecutable<TResult>> it = this.successExecutables.iterator();
        while (it.hasNext()) {
            if (it.next().getSuccessListener() == onSuccessListener) {
                it.remove();
            }
        }
        return this;
    }

    public Future<?> submit(String str, Callable<TResult> callable) {
        Executor executor = this.executor;
        if (!(executor instanceof ExecutorService)) {
            throw new UnsupportedOperationException("Can't use this method without ExecutorService, Use Execute alternatively ");
        }
        return ((ExecutorService) executor).submit(newRunnableForTask(str, callable));
    }

    public TResult submitAndGetResult(String str, Callable<TResult> callable, long j) {
        Future futureSubmit;
        Executor executor = this.executor;
        if (!(executor instanceof ExecutorService)) {
            throw new UnsupportedOperationException("Can't use this method without ExecutorService, Use Execute alternatively ");
        }
        try {
            futureSubmit = ((ExecutorService) executor).submit(callable);
        } catch (Exception e) {
            e = e;
            futureSubmit = null;
        }
        try {
            return (TResult) futureSubmit.get(j, TimeUnit.MILLISECONDS);
        } catch (Exception e2) {
            e = e2;
            e.printStackTrace();
            if (futureSubmit != null && !futureSubmit.isCancelled()) {
                futureSubmit.cancel(true);
            }
            Logger.v("submitAndGetResult :: " + str + " task timed out");
            return null;
        }
    }

    void onFailure(Exception exc) {
        setState(STATE.FAILED);
        Iterator<FailureExecutable<Exception>> it = this.failureExecutables.iterator();
        while (it.hasNext()) {
            it.next().execute(exc);
        }
    }

    void onSuccess(TResult tresult) {
        setState(STATE.SUCCESS);
        setResult(tresult);
        Iterator<SuccessExecutable<TResult>> it = this.successExecutables.iterator();
        while (it.hasNext()) {
            it.next().execute(this.result);
        }
    }

    private Runnable newRunnableForTask(final String str, final Callable<TResult> callable) {
        return new Runnable() { // from class: com.clevertap.android.sdk.task.Task.1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.lang.Runnable
            public void run() throws Exception {
                try {
                    Task.this.setState(STATE.RUNNING);
                    Task.this.logProperly(Task.this.taskName + " Task: " + str + " starting on..." + Thread.currentThread().getName(), null);
                    Object objCall = callable.call();
                    Task.this.logProperly(Task.this.taskName + " Task: " + str + " executed successfully on..." + Thread.currentThread().getName(), null);
                    Task.this.onSuccess(objCall);
                } catch (Exception e) {
                    Task.this.onFailure(e);
                    Task.this.logProperly(Task.this.taskName + " Task: " + str + " failed to execute on..." + Thread.currentThread().getName(), e);
                    e.printStackTrace();
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logProperly(String str, Exception exc) {
        CleverTapInstanceConfig cleverTapInstanceConfig = this.config;
        if (cleverTapInstanceConfig != null) {
            cleverTapInstanceConfig.getLogger().verbose(str, exc);
        } else {
            Logger.v(str, exc);
        }
    }
}
