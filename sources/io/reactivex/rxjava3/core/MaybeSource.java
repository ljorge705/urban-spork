package io.reactivex.rxjava3.core;

@FunctionalInterface
/* loaded from: classes6.dex */
public interface MaybeSource<T> {
    void subscribe(MaybeObserver<? super T> observer);
}
