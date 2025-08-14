package io.reactivex.rxjava3.internal.util;

import io.reactivex.rxjava3.core.Observer;

/* loaded from: classes6.dex */
public interface ObservableQueueDrain<T, U> {
    void accept(Observer<? super U> a2, T v);

    boolean cancelled();

    boolean done();

    boolean enter();

    Throwable error();

    int leave(int m);
}
