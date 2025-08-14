package com.nimbusds.jose.util;

import net.jcip.annotations.NotThreadSafe;

@NotThreadSafe
/* loaded from: classes2.dex */
public class Container<T> {
    private T item;

    public T get() {
        return this.item;
    }

    public void set(T t) {
        this.item = t;
    }

    public Container() {
    }

    public Container(T t) {
        this.item = t;
    }
}
