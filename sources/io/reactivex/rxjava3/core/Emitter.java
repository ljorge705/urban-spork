package io.reactivex.rxjava3.core;

/* loaded from: classes6.dex */
public interface Emitter<T> {
    void onComplete();

    void onError(Throwable error);

    void onNext(T value);
}
