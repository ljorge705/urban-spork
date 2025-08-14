package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* loaded from: classes6.dex */
public final class FlowableSkip<T> extends AbstractFlowableWithUpstream<T, T> {

    /* renamed from: n, reason: collision with root package name */
    final long f294n;

    public FlowableSkip(Flowable<T> source, long n2) {
        super(source);
        this.f294n = n2;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    protected void subscribeActual(Subscriber<? super T> s) {
        this.source.subscribe((FlowableSubscriber) new SkipSubscriber(s, this.f294n));
    }

    static final class SkipSubscriber<T> implements FlowableSubscriber<T>, Subscription {
        final Subscriber<? super T> downstream;
        long remaining;
        Subscription upstream;

        SkipSubscriber(Subscriber<? super T> actual, long n2) {
            this.downstream = actual;
            this.remaining = n2;
        }

        @Override // io.reactivex.rxjava3.core.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.upstream, s)) {
                long j = this.remaining;
                this.upstream = s;
                this.downstream.onSubscribe(this);
                s.request(j);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            long j = this.remaining;
            if (j != 0) {
                this.remaining = j - 1;
            } else {
                this.downstream.onNext(t);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable t) {
            this.downstream.onError(t);
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            this.downstream.onComplete();
        }

        @Override // org.reactivestreams.Subscription
        public void request(long n2) {
            this.upstream.request(n2);
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            this.upstream.cancel();
        }
    }
}
