package io.reactivex.rxjava3.core;

@FunctionalInterface
/* loaded from: classes6.dex */
public interface FlowableConverter<T, R> {
    R apply(Flowable<T> upstream);
}
