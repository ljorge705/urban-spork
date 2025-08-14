package com.google.firebase.storage;

import android.app.Activity;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.StorageTask.ProvideError;
import com.google.firebase.storage.internal.ActivityLifecycleListener;
import com.google.firebase.storage.internal.SmartHandler;
import java.util.HashMap;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;

/* loaded from: classes2.dex */
class TaskListenerImpl<ListenerTypeT, ResultT extends StorageTask.ProvideError> {
    private OnRaise<ListenerTypeT, ResultT> onRaise;
    private int targetStates;
    private StorageTask<ResultT> task;
    private final Queue<ListenerTypeT> listenerQueue = new ConcurrentLinkedQueue();
    private final HashMap<ListenerTypeT, SmartHandler> handlerMap = new HashMap<>();

    interface OnRaise<ListenerTypeT, ResultT> {
        void raise(ListenerTypeT listenertypet, ResultT resultt);
    }

    public TaskListenerImpl(StorageTask<ResultT> storageTask, int i, OnRaise<ListenerTypeT, ResultT> onRaise) {
        this.task = storageTask;
        this.targetStates = i;
        this.onRaise = onRaise;
    }

    public int getListenerCount() {
        return Math.max(this.listenerQueue.size(), this.handlerMap.size());
    }

    public void addListener(Activity activity, Executor executor, final ListenerTypeT listenertypet) {
        boolean z;
        SmartHandler smartHandler;
        Preconditions.checkNotNull(listenertypet);
        synchronized (this.task.getSyncObject()) {
            z = (this.task.getInternalState() & this.targetStates) != 0;
            this.listenerQueue.add(listenertypet);
            smartHandler = new SmartHandler(executor);
            this.handlerMap.put(listenertypet, smartHandler);
            if (activity != null) {
                Preconditions.checkArgument(!activity.isDestroyed(), "Activity is already destroyed!");
                ActivityLifecycleListener.getInstance().runOnActivityStopped(activity, listenertypet, new Runnable() { // from class: com.google.firebase.storage.TaskListenerImpl$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.m5400x5521489(listenertypet);
                    }
                });
            }
        }
        if (z) {
            final StorageTask.ProvideError provideErrorSnapState = this.task.snapState();
            smartHandler.callBack(new Runnable() { // from class: com.google.firebase.storage.TaskListenerImpl$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m5401xbec9a228(listenertypet, provideErrorSnapState);
                }
            });
        }
    }

    /* renamed from: lambda$addListener$1$com-google-firebase-storage-TaskListenerImpl, reason: not valid java name */
    /* synthetic */ void m5401xbec9a228(Object obj, StorageTask.ProvideError provideError) {
        this.onRaise.raise(obj, provideError);
    }

    public void onInternalStateChanged() {
        if ((this.task.getInternalState() & this.targetStates) != 0) {
            final StorageTask.ProvideError provideErrorSnapState = this.task.snapState();
            for (final ListenerTypeT listenertypet : this.listenerQueue) {
                SmartHandler smartHandler = this.handlerMap.get(listenertypet);
                if (smartHandler != null) {
                    smartHandler.callBack(new Runnable() { // from class: com.google.firebase.storage.TaskListenerImpl$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.m5402x24929823(listenertypet, provideErrorSnapState);
                        }
                    });
                }
            }
        }
    }

    /* renamed from: lambda$onInternalStateChanged$2$com-google-firebase-storage-TaskListenerImpl, reason: not valid java name */
    /* synthetic */ void m5402x24929823(Object obj, StorageTask.ProvideError provideError) {
        this.onRaise.raise(obj, provideError);
    }

    /* renamed from: removeListener, reason: merged with bridge method [inline-methods] */
    public void m5400x5521489(ListenerTypeT listenertypet) {
        Preconditions.checkNotNull(listenertypet);
        synchronized (this.task.getSyncObject()) {
            this.handlerMap.remove(listenertypet);
            this.listenerQueue.remove(listenertypet);
            ActivityLifecycleListener.getInstance().removeCookie(listenertypet);
        }
    }
}
