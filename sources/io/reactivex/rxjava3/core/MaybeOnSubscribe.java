package io.reactivex.rxjava3.core;

@FunctionalInterface
/* loaded from: classes6.dex */
public interface MaybeOnSubscribe<T> {
    void subscribe(MaybeEmitter<T> emitter) throws Throwable;
}
