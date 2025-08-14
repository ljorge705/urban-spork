package io.reactivex.rxjava3.core;

@FunctionalInterface
/* loaded from: classes6.dex */
public interface FlowableOnSubscribe<T> {
    void subscribe(FlowableEmitter<T> emitter) throws Throwable;
}
