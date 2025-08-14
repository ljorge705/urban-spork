package io.reactivex.rxjava3.flowables;

import io.reactivex.rxjava3.core.Flowable;

/* loaded from: classes6.dex */
public abstract class GroupedFlowable<K, T> extends Flowable<T> {
    final K key;

    public K getKey() {
        return this.key;
    }

    protected GroupedFlowable(K key) {
        this.key = key;
    }
}
