package io.reactivex.rxjava3.internal.operators.mixed;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.Observable;
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
public final class ObservableConcatMapCompletable<T> extends Completable {
    final ErrorMode errorMode;
    final Function<? super T, ? extends CompletableSource> mapper;
    final int prefetch;
    final Observable<T> source;

    public ObservableConcatMapCompletable(Observable<T> source, Function<? super T, ? extends CompletableSource> mapper, ErrorMode errorMode, int prefetch) {
        this.source = source;
        this.mapper = mapper;
        this.errorMode = errorMode;
        this.prefetch = prefetch;
    }

    @Override // io.reactivex.rxjava3.core.Completable
    protected void subscribeActual(CompletableObserver observer) {
        if (ScalarXMapZHelper.tryAsCompletable(this.source, this.mapper, observer)) {
            return;
        }
        this.source.subscribe(new ConcatMapCompletableObserver(observer, this.mapper, this.errorMode, this.prefetch));
    }

    static final class ConcatMapCompletableObserver<T> extends ConcatMapXMainObserver<T> {
        private static final long serialVersionUID = 3610901111000061034L;
        volatile boolean active;
        final CompletableObserver downstream;
        final ConcatMapInnerObserver inner;
        final Function<? super T, ? extends CompletableSource> mapper;

        ConcatMapCompletableObserver(CompletableObserver downstream, Function<? super T, ? extends CompletableSource> mapper, ErrorMode errorMode, int prefetch) {
            super(prefetch, errorMode);
            this.downstream = downstream;
            this.mapper = mapper;
            this.inner = new ConcatMapInnerObserver(this);
        }

        @Override // io.reactivex.rxjava3.internal.operators.mixed.ConcatMapXMainObserver
        void onSubscribeDownstream() {
            this.downstream.onSubscribe(this);
        }

        @Override // io.reactivex.rxjava3.internal.operators.mixed.ConcatMapXMainObserver
        void disposeInner() {
            this.inner.dispose();
        }

        void innerError(Throwable ex) {
            if (this.errors.tryAddThrowableOrReport(ex)) {
                if (this.errorMode != ErrorMode.END) {
                    this.upstream.dispose();
                }
                this.active = false;
                drain();
            }
        }

        void innerComplete() {
            this.active = false;
            drain();
        }

        @Override // io.reactivex.rxjava3.internal.operators.mixed.ConcatMapXMainObserver
        void drain() {
            CompletableSource completableSource;
            boolean z;
            if (getAndIncrement() != 0) {
                return;
            }
            AtomicThrowable atomicThrowable = this.errors;
            ErrorMode errorMode = this.errorMode;
            SimpleQueue<T> simpleQueue = this.queue;
            while (!this.disposed) {
                if (atomicThrowable.get() != null && (errorMode == ErrorMode.IMMEDIATE || (errorMode == ErrorMode.BOUNDARY && !this.active))) {
                    this.disposed = true;
                    simpleQueue.clear();
                    atomicThrowable.tryTerminateConsumer(this.downstream);
                    return;
                }
                if (!this.active) {
                    boolean z2 = this.done;
                    try {
                        T tPoll = simpleQueue.poll();
                        if (tPoll != null) {
                            completableSource = (CompletableSource) Objects.requireNonNull(this.mapper.apply(tPoll), "The mapper returned a null CompletableSource");
                            z = false;
                        } else {
                            completableSource = null;
                            z = true;
                        }
                        if (z2 && z) {
                            this.disposed = true;
                            atomicThrowable.tryTerminateConsumer(this.downstream);
                            return;
                        } else if (!z) {
                            this.active = true;
                            completableSource.subscribe(this.inner);
                        }
                    } catch (Throwable th) {
                        Exceptions.throwIfFatal(th);
                        this.disposed = true;
                        simpleQueue.clear();
                        this.upstream.dispose();
                        atomicThrowable.tryAddThrowableOrReport(th);
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
