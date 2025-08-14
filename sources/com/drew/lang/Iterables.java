package com.drew.lang;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: classes5.dex */
public class Iterables {
    public static <E> List<E> toList(Iterable<E> iterable) {
        ArrayList arrayList = new ArrayList();
        Iterator<E> it = iterable.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return arrayList;
    }

    public static <E> Set<E> toSet(Iterable<E> iterable) {
        HashSet hashSet = new HashSet();
        Iterator<E> it = iterable.iterator();
        while (it.hasNext()) {
            hashSet.add(it.next());
        }
        return hashSet;
    }
}
