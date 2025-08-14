package io.reactivex.rxjava3.internal.schedulers;

import io.reactivex.rxjava3.core.Scheduler;

/* loaded from: classes6.dex */
public interface SchedulerMultiWorkerSupport {

    public interface WorkerCallback {
        void onWorker(int index, Scheduler.Worker worker);
    }

    void createWorkers(int number, WorkerCallback callback);
}
