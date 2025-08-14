package io.reactivex.rxjava3.internal.operators.parallel;

import io.reactivex.rxjava3.parallel.ParallelFlowable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

/* loaded from: classes6.dex */
public final class ParallelFromArray<T> extends ParallelFlowable<T> {
    final Publisher<T>[] sources;

    public ParallelFromArray(Publisher<T>[] sources) {
        this.sources = sources;
    }

    @Override // io.reactivex.rxjava3.parallel.ParallelFlowable
    public int parallelism() {
        return this.sources.length;
    }

    @Override // io.reactivex.rxjava3.parallel.ParallelFlowable
    public void subscribe(Subscriber<? super T>[] subscribers) {
        Subscriber<? super T>[] subscriberArrOnSubscribe = RxJavaPlugins.onSubscribe(this, subscribers);
        if (validate(subscriberArrOnSubscribe)) {
            int length = subscriberArrOnSubscribe.length;
            for (int i = 0; i < length; i++) {
                this.sources[i].subscribe(subscriberArrOnSubscribe[i]);
            }
        }
    }
}
