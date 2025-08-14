package io.reactivex.rxjava3.core;

@FunctionalInterface
/* loaded from: classes6.dex */
public interface ObservableOnSubscribe<T> {
    void subscribe(ObservableEmitter<T> emitter) throws Throwable;
}
