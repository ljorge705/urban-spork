package io.reactivex.rxjava3.core;

import io.reactivex.rxjava3.disposables.Disposable;

/* loaded from: classes6.dex */
public interface SingleObserver<T> {
    void onError(Throwable e);

    void onSubscribe(Disposable d);

    void onSuccess(T t);
}
