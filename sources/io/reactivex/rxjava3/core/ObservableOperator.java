package io.reactivex.rxjava3.core;

@FunctionalInterface
/* loaded from: classes6.dex */
public interface ObservableOperator<Downstream, Upstream> {
    Observer<? super Upstream> apply(Observer<? super Downstream> observer) throws Throwable;
}
