package io.reactivex.rxjava3.core;

@FunctionalInterface
/* loaded from: classes6.dex */
public interface SingleOperator<Downstream, Upstream> {
    SingleObserver<? super Upstream> apply(SingleObserver<? super Downstream> observer) throws Throwable;
}
