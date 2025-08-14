package org.spongycastle.util;

/* loaded from: classes7.dex */
public interface Selector<T> extends Cloneable {
    Object clone();

    boolean match(T t);
}
