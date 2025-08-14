package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Supplier;
import java.util.Objects;
import org.reactivestreams.Subscriber;

/* loaded from: classes6.dex */
public final class FlowableOnBackpressureReduceWith<T, R> extends AbstractFlowableWithUpstream<T, R> {
    final BiFunction<R, ? super T, R> reducer;
    final Supplier<R> supplier;

    public FlowableOnBackpressureReduceWith(Flowable<T> source, Supplier<R> supplier, BiFunction<R, ? super T, R> reducer) {
        super(source);
        this.reducer = reducer;
        this.supplier = supplier;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    protected void subscribeActual(Subscriber<? super R> s) {
        this.source.subscribe((FlowableSubscriber) new BackpressureReduceWithSubscriber(s, this.supplier, this.reducer));
    }

    static final class BackpressureReduceWithSubscriber<T, R> extends AbstractBackpressureThrottlingSubscriber<T, R> {
        private static final long serialVersionUID = 8255923705960622424L;
        final BiFunction<R, ? super T, R> reducer;
        final Supplier<R> supplier;

        BackpressureReduceWithSubscriber(Subscriber<? super R> downstream, Supplier<R> supplier, BiFunction<R, ? super T, R> reducer) {
            super(downstream);
            this.reducer = reducer;
            this.supplier = supplier;
        }

        @Override // io.reactivex.rxjava3.internal.operators.flowable.AbstractBackpressureThrottlingSubscriber, org.reactivestreams.Subscriber
        public void onNext(T t) {
            R andSet = this.current.get();
            if (andSet != null) {
                andSet = this.current.getAndSet(null);
            }
            try {
                if (andSet == null) {
                    this.current.lazySet(Objects.requireNonNull(this.reducer.apply(Objects.requireNonNull(this.supplier.get(), "The supplier returned a null value"), t), "The reducer returned a null value"));
                } else {
                    this.current.lazySet(Objects.requireNonNull(this.reducer.apply(andSet, t), "The reducer returned a null value"));
                }
                drain();
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.upstream.cancel();
                onError(th);
            }
        }
    }
}
