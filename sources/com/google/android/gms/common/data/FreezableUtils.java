package com.google.android.gms.common.data;

import com.paygilant.PG_FraudDetection_SDK.PaygilantManager;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-base@@18.2.0 */
/* loaded from: classes3.dex */
public final class FreezableUtils {
    public static <T, E extends Freezable<T>> ArrayList<T> freeze(ArrayList<E> arrayList) {
        PaygilantManager.jfrjraouuicuqboknnmi jfrjraouuicuqboknnmiVar = (ArrayList<T>) new ArrayList(arrayList.size());
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            jfrjraouuicuqboknnmiVar.add(arrayList.get(i).freeze());
        }
        return jfrjraouuicuqboknnmiVar;
    }

    public static <T, E extends Freezable<T>> ArrayList<T> freezeIterable(Iterable<E> iterable) {
        PaygilantManager.jfrjraouuicuqboknnmi jfrjraouuicuqboknnmiVar = (ArrayList<T>) new ArrayList();
        Iterator<E> it = iterable.iterator();
        while (it.hasNext()) {
            jfrjraouuicuqboknnmiVar.add(it.next().freeze());
        }
        return jfrjraouuicuqboknnmiVar;
    }

    public static <T, E extends Freezable<T>> ArrayList<T> freeze(E[] eArr) {
        PaygilantManager.jfrjraouuicuqboknnmi jfrjraouuicuqboknnmiVar = (ArrayList<T>) new ArrayList(eArr.length);
        for (E e : eArr) {
            jfrjraouuicuqboknnmiVar.add(e.freeze());
        }
        return jfrjraouuicuqboknnmiVar;
    }
}
