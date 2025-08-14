package io.reactivex.rxjava3.internal.operators.mixed;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.fuseable.SimpleQueue;
import io.reactivex.rxjava3.internal.util.AtomicThrowable;
import io.reactivex.rxjava3.internal.util.ErrorMode;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes6.dex */
public final class FlowableConcatMapCompletable<T> extends Completable {
    final ErrorMode errorMode;
    final Function<? super T, ? extends CompletableSource> mapper;
    final int prefetch;
    final Flowable<T> source;

    public FlowableConcatMapCompletable(Flowable<T> source, Function<? super T, ? extends CompletableSource> mapper, ErrorMode errorMode, int prefetch) {
        this.source = source;
        this.mapper = mapper;
        this.errorMode = errorMode;
        this.prefetch = prefetch;
    }

    @Override // io.reactivex.rxjava3.core.Completable
    protected void subscribeActual(CompletableObserver observer) {
        this.source.subscribe((FlowableSubscriber) new ConcatMapCompletableObserver(observer, this.mapper, this.errorMode, this.prefetch));
    }

    static final class ConcatMapCompletableObserver<T> extends ConcatMapXMainSubscriber<T> implements Disposable {
        private static final long serialVersionUID = 3610901111000061034L;
        volatile boolean active;
        int consumed;
        final CompletableObserver downstream;
        final ConcatMapInnerObserver inner;
        final Function<? super T, ? extends CompletableSource> mapper;

        ConcatMapCompletableObserver(CompletableObserver downstream, Function<? super T, ? extends CompletableSource> mapper, ErrorMode errorMode, int prefetch) {
            super(prefetch, errorMode);
            this.downstream = downstream;
            this.mapper = mapper;
            this.inner = new ConcatMapInnerObserver(this);
        }

        @Override // io.reactivex.rxjava3.internal.operators.mixed.ConcatMapXMainSubscriber
        void onSubscribeDownstream() {
            this.downstream.onSubscribe(this);
        }

        @Override // io.reactivex.rxjava3.internal.operators.mixed.ConcatMapXMainSubscriber
        void disposeInner() {
            this.inner.dispose();
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            stop();
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.cancelled;
        }

        void innerError(Throwable ex) {
            if (this.errors.tryAddThrowableOrReport(ex)) {
                if (this.errorMode == ErrorMode.IMMEDIATE) {
                    this.upstream.cancel();
                    this.errors.tryTerminateConsumer(this.downstream);
                    if (getAndIncrement() == 0) {
                        this.queue.clear();
                        return;
                    }
                    return;
                }
                this.active = false;
                drain();
            }
        }

        void innerComplete() {
            this.active = false;
            drain();
        }

        @Override // io.reactivex.rxjava3.internal.operators.mixed.ConcatMapXMainSubscriber
        void drain() {
            if (getAndIncrement() != 0) {
                return;
            }
            ErrorMode errorMode = this.errorMode;
            SimpleQueue<T> simpleQueue = this.queue;
            AtomicThrowable atomicThrowable = this.errors;
            boolean z = this.syncFused;
            while (!this.cancelled) {
                if (atomicThrowable.get() != null && (errorMode == ErrorMode.IMMEDIATE || (errorMode == ErrorMode.BOUNDARY && !this.active))) {
                    simpleQueue.clear();
                    atomicThrowable.tryTerminateConsumer(this.downstream);
                    return;
                }
                if (!this.active) {
                    boolean z2 = this.done;
                    try {
                        T tPoll = simpleQueue.poll();
                        boolean z3 = tPoll == null;
                        if (z2 && z3) {
                            atomicThrowable.tryTerminateConsumer(this.downstream);
                            return;
                        }
                        if (!z3) {
                            int i = this.prefetch - (this.prefetch >> 1);
                            if (!z) {
                                int i2 = this.consumed + 1;
                                if (i2 == i) {
                                    this.consumed = 0;
                                    this.upstream.request(i);
                                } else {
                                    this.consumed = i2;
                                }
                            }
                            try {
                                CompletableSource completableSource = (CompletableSource) Objects.requireNonNull(this.mapper.apply(tPoll), "The mapper returned a null CompletableSource");
                                this.active = true;
                                completableSource.subscribe(this.inner);
                            } catch (Throwable th) {
                                Exceptions.throwIfFatal(th);
                                simpleQueue.clear();
                                this.upstream.cancel();
                                atomicThrowable.tryAddThrowableOrReport(th);
                                atomicThrowable.tryTerminateConsumer(this.downstream);
                                return;
                            }
                        }
                    } catch (Throwable th2) {
                        Exceptions.throwIfFatal(th2);
                        this.upstream.cancel();
                        atomicThrowable.tryAddThrowableOrReport(th2);
                        atomicThrowable.tryTerminateConsumer(this.downstream);
                        return;
                    }
                }
                if (decrementAndGet() == 0) {
                    return;
                }
            }
            simpleQueue.clear();
        }

        static final class ConcatMapInnerObserver extends AtomicReference<Disposable> implements CompletableObserver {
            private static final long serialVersionUID = 5638352172918776687L;
            final ConcatMapCompletableObserver<?> parent;

            ConcatMapInnerObserver(ConcatMapCompletableObserver<?> parent) {
                this.parent = parent;
            }

            @Override // io.reactivex.rxjava3.core.CompletableObserver
            public void onSubscribe(Disposable d) {
                DisposableHelper.replace(this, d);
            }

            @Override // io.reactivex.rxjava3.core.CompletableObserver
            public void onError(Throwable e) {
                this.parent.innerError(e);
            }

            @Override // io.reactivex.rxjava3.core.CompletableObserver
            public void onComplete() {
                this.parent.innerComplete();
            }

            void dispose() {
                DisposableHelper.dispose(this);
            }
        }
    }
}
