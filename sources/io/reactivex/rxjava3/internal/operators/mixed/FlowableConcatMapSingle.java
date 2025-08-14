package io.reactivex.rxjava3.internal.operators.mixed;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.util.BackpressureHelper;
import io.reactivex.rxjava3.internal.util.ErrorMode;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* loaded from: classes6.dex */
public final class FlowableConcatMapSingle<T, R> extends Flowable<R> {
    final ErrorMode errorMode;
    final Function<? super T, ? extends SingleSource<? extends R>> mapper;
    final int prefetch;
    final Flowable<T> source;

    public FlowableConcatMapSingle(Flowable<T> source, Function<? super T, ? extends SingleSource<? extends R>> mapper, ErrorMode errorMode, int prefetch) {
        this.source = source;
        this.mapper = mapper;
        this.errorMode = errorMode;
        this.prefetch = prefetch;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    protected void subscribeActual(Subscriber<? super R> s) {
        this.source.subscribe((FlowableSubscriber) new ConcatMapSingleSubscriber(s, this.mapper, this.prefetch, this.errorMode));
    }

    static final class ConcatMapSingleSubscriber<T, R> extends ConcatMapXMainSubscriber<T> implements Subscription {
        static final int STATE_ACTIVE = 1;
        static final int STATE_INACTIVE = 0;
        static final int STATE_RESULT_VALUE = 2;
        private static final long serialVersionUID = -9140123220065488293L;
        int consumed;
        final Subscriber<? super R> downstream;
        long emitted;
        final ConcatMapSingleObserver<R> inner;
        R item;
        final Function<? super T, ? extends SingleSource<? extends R>> mapper;
        final AtomicLong requested;
        volatile int state;

        @Override // io.reactivex.rxjava3.internal.operators.mixed.ConcatMapXMainSubscriber
        void clearValue() {
            this.item = null;
        }

        ConcatMapSingleSubscriber(Subscriber<? super R> downstream, Function<? super T, ? extends SingleSource<? extends R>> mapper, int prefetch, ErrorMode errorMode) {
            super(prefetch, errorMode);
            this.downstream = downstream;
            this.mapper = mapper;
            this.requested = new AtomicLong();
            this.inner = new ConcatMapSingleObserver<>(this);
        }

        @Override // io.reactivex.rxjava3.internal.operators.mixed.ConcatMapXMainSubscriber
        void onSubscribeDownstream() {
            this.downstream.onSubscribe(this);
        }

        @Override // org.reactivestreams.Subscription
        public void request(long n2) {
            BackpressureHelper.add(this.requested, n2);
            drain();
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            stop();
        }

        @Override // io.reactivex.rxjava3.internal.operators.mixed.ConcatMapXMainSubscriber
        void disposeInner() {
            this.inner.dispose();
        }

        void innerSuccess(R item) {
            this.item = item;
            this.state = 2;
            drain();
        }

        void innerError(Throwable ex) {
            if (this.errors.tryAddThrowableOrReport(ex)) {
                if (this.errorMode != ErrorMode.END) {
                    this.upstream.cancel();
                }
                this.state = 0;
                drain();
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:16:0x003b, code lost:
        
            r3.clear();
            r17.item = null;
            r4.tryTerminateConsumer(r2);
         */
        /* JADX WARN: Code restructure failed: missing block: B:17:0x0043, code lost:
        
            return;
         */
        @Override // io.reactivex.rxjava3.internal.operators.mixed.ConcatMapXMainSubscriber
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        void drain() {
            /*
                Method dump skipped, instructions count: 206
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.rxjava3.internal.operators.mixed.FlowableConcatMapSingle.ConcatMapSingleSubscriber.drain():void");
        }

        static final class ConcatMapSingleObserver<R> extends AtomicReference<Disposable> implements SingleObserver<R> {
            private static final long serialVersionUID = -3051469169682093892L;
            final ConcatMapSingleSubscriber<?, R> parent;

            ConcatMapSingleObserver(ConcatMapSingleSubscriber<?, R> parent) {
                this.parent = parent;
            }

            @Override // io.reactivex.rxjava3.core.SingleObserver, io.reactivex.rxjava3.core.CompletableObserver
            public void onSubscribe(Disposable d) {
                DisposableHelper.replace(this, d);
            }

            @Override // io.reactivex.rxjava3.core.SingleObserver
            public void onSuccess(R t) {
                this.parent.innerSuccess(t);
            }

            @Override // io.reactivex.rxjava3.core.SingleObserver, io.reactivex.rxjava3.core.CompletableObserver
            public void onError(Throwable e) {
                this.parent.innerError(e);
            }

            void dispose() {
                DisposableHelper.dispose(this);
            }
        }
    }
}
