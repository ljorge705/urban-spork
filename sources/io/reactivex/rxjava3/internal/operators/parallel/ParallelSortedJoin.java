package io.reactivex.rxjava3.internal.operators.parallel;

import androidx.camera.view.PreviewView$1$$ExternalSyntheticBackportWithForwarding0;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.internal.util.BackpressureHelper;
import io.reactivex.rxjava3.parallel.ParallelFlowable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* loaded from: classes6.dex */
public final class ParallelSortedJoin<T> extends Flowable<T> {
    final Comparator<? super T> comparator;
    final ParallelFlowable<List<T>> source;

    public ParallelSortedJoin(ParallelFlowable<List<T>> source, Comparator<? super T> comparator) {
        this.source = source;
        this.comparator = comparator;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    protected void subscribeActual(Subscriber<? super T> s) {
        SortedJoinSubscription sortedJoinSubscription = new SortedJoinSubscription(s, this.source.parallelism(), this.comparator);
        s.onSubscribe(sortedJoinSubscription);
        this.source.subscribe(sortedJoinSubscription.subscribers);
    }

    static final class SortedJoinSubscription<T> extends AtomicInteger implements Subscription {
        private static final long serialVersionUID = 3481980673745556697L;
        volatile boolean cancelled;
        final Comparator<? super T> comparator;
        final Subscriber<? super T> downstream;
        final int[] indexes;
        final List<T>[] lists;
        final SortedJoinInnerSubscriber<T>[] subscribers;
        final AtomicLong requested = new AtomicLong();
        final AtomicInteger remaining = new AtomicInteger();
        final AtomicReference<Throwable> error = new AtomicReference<>();

        SortedJoinSubscription(Subscriber<? super T> actual, int n2, Comparator<? super T> comparator) {
            this.downstream = actual;
            this.comparator = comparator;
            SortedJoinInnerSubscriber<T>[] sortedJoinInnerSubscriberArr = new SortedJoinInnerSubscriber[n2];
            for (int i = 0; i < n2; i++) {
                sortedJoinInnerSubscriberArr[i] = new SortedJoinInnerSubscriber<>(this, i);
            }
            this.subscribers = sortedJoinInnerSubscriberArr;
            this.lists = new List[n2];
            this.indexes = new int[n2];
            this.remaining.lazySet(n2);
        }

        @Override // org.reactivestreams.Subscription
        public void request(long n2) {
            if (SubscriptionHelper.validate(n2)) {
                BackpressureHelper.add(this.requested, n2);
                if (this.remaining.get() == 0) {
                    drain();
                }
            }
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            if (this.cancelled) {
                return;
            }
            this.cancelled = true;
            cancelAll();
            if (getAndIncrement() == 0) {
                Arrays.fill(this.lists, (Object) null);
            }
        }

        void cancelAll() {
            for (SortedJoinInnerSubscriber<T> sortedJoinInnerSubscriber : this.subscribers) {
                sortedJoinInnerSubscriber.cancel();
            }
        }

        void innerNext(List<T> value, int index) {
            this.lists[index] = value;
            if (this.remaining.decrementAndGet() == 0) {
                drain();
            }
        }

        void innerError(Throwable e) {
            if (PreviewView$1$$ExternalSyntheticBackportWithForwarding0.m(this.error, null, e)) {
                drain();
            } else if (e != this.error.get()) {
                RxJavaPlugins.onError(e);
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:40:0x00a0, code lost:
        
            if (r16.cancelled == false) goto L43;
         */
        /* JADX WARN: Code restructure failed: missing block: B:41:0x00a2, code lost:
        
            java.util.Arrays.fill(r3, (java.lang.Object) null);
         */
        /* JADX WARN: Code restructure failed: missing block: B:42:0x00a6, code lost:
        
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:43:0x00a7, code lost:
        
            r7 = r16.error.get();
         */
        /* JADX WARN: Code restructure failed: missing block: B:44:0x00b0, code lost:
        
            if (r7 == null) goto L47;
         */
        /* JADX WARN: Code restructure failed: missing block: B:45:0x00b2, code lost:
        
            cancelAll();
            java.util.Arrays.fill(r3, (java.lang.Object) null);
            r2.onError(r7);
         */
        /* JADX WARN: Code restructure failed: missing block: B:46:0x00bb, code lost:
        
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:47:0x00bc, code lost:
        
            if (r14 >= r4) goto L66;
         */
        /* JADX WARN: Code restructure failed: missing block: B:49:0x00c6, code lost:
        
            if (r0[r14] == r3[r14].size()) goto L56;
         */
        /* JADX WARN: Code restructure failed: missing block: B:51:0x00cc, code lost:
        
            if (r11 == 0) goto L53;
         */
        /* JADX WARN: Code restructure failed: missing block: B:52:0x00ce, code lost:
        
            io.reactivex.rxjava3.internal.util.BackpressureHelper.produced(r16.requested, r11);
         */
        /* JADX WARN: Code restructure failed: missing block: B:53:0x00d3, code lost:
        
            r6 = addAndGet(-r6);
         */
        /* JADX WARN: Code restructure failed: missing block: B:56:0x00db, code lost:
        
            r14 = r14 + 1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:57:0x00e0, code lost:
        
            java.util.Arrays.fill(r3, (java.lang.Object) null);
            r2.onComplete();
         */
        /* JADX WARN: Code restructure failed: missing block: B:58:0x00e7, code lost:
        
            return;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        void drain() {
            /*
                Method dump skipped, instructions count: 232
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.rxjava3.internal.operators.parallel.ParallelSortedJoin.SortedJoinSubscription.drain():void");
        }
    }

    static final class SortedJoinInnerSubscriber<T> extends AtomicReference<Subscription> implements FlowableSubscriber<List<T>> {
        private static final long serialVersionUID = 6751017204873808094L;
        final int index;
        final SortedJoinSubscription<T> parent;

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
        }

        SortedJoinInnerSubscriber(SortedJoinSubscription<T> parent, int index) {
            this.parent = parent;
            this.index = index;
        }

        @Override // io.reactivex.rxjava3.core.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription s) {
            SubscriptionHelper.setOnce(this, s, Long.MAX_VALUE);
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(List<T> t) {
            this.parent.innerNext(t, this.index);
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable t) {
            this.parent.innerError(t);
        }

        void cancel() {
            SubscriptionHelper.cancel(this);
        }
    }
}
