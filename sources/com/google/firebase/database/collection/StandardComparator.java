package com.google.firebase.database.collection;

import java.lang.Comparable;
import java.util.Comparator;

/* loaded from: classes5.dex */
public class StandardComparator<A extends Comparable<A>> implements Comparator<A> {
    private static StandardComparator INSTANCE = new StandardComparator();

    public static <T extends Comparable<T>> StandardComparator<T> getComparator(Class<T> cls) {
        return INSTANCE;
    }

    private StandardComparator() {
    }

    @Override // java.util.Comparator
    public int compare(A a2, A a3) {
        return a2.compareTo(a3);
    }
}
