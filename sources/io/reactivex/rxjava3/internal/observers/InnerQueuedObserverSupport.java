package io.reactivex.rxjava3.internal.observers;

/* loaded from: classes6.dex */
public interface InnerQueuedObserverSupport<T> {
    void drain();

    void innerComplete(InnerQueuedObserver<T> inner);

    void innerError(InnerQueuedObserver<T> inner, Throwable e);

    void innerNext(InnerQueuedObserver<T> inner, T value);
}
