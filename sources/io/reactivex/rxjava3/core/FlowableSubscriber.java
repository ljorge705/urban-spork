package io.reactivex.rxjava3.core;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* loaded from: classes6.dex */
public interface FlowableSubscriber<T> extends Subscriber<T> {
    @Override // org.reactivestreams.Subscriber
    void onSubscribe(Subscription s);
}
