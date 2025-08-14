package com.google.android.play.core.splitinstall.internal;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Iterator;

/* compiled from: com.google.android.play:feature-delivery@@2.1.0 */
/* loaded from: classes3.dex */
public final class zzbi extends zzbj {
    zzbi(Object obj, Field field, Class cls) {
        super(obj, field, Array.newInstance((Class<?>) cls, 0).getClass());
    }

    private final Class zzf() {
        return zzd().getType().getComponentType();
    }

    public final void zza(Collection collection) throws IllegalAccessException, IllegalArgumentException {
        Object[] objArr = (Object[]) zzc();
        int length = objArr == null ? 0 : objArr.length;
        Object[] objArr2 = (Object[]) Array.newInstance((Class<?>) zzf(), collection.size() + length);
        if (objArr != null) {
            System.arraycopy(objArr, 0, objArr2, 0, objArr.length);
        }
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            objArr2[length] = it.next();
            length++;
        }
        zze(objArr2);
    }

    public final void zzb(Collection collection) throws IllegalAccessException, IllegalArgumentException {
        Object[] objArr = (Object[]) zzc();
        int i = 0;
        Object[] objArr2 = (Object[]) Array.newInstance((Class<?>) zzf(), (objArr == null ? 0 : objArr.length) + collection.size());
        if (objArr != null) {
            System.arraycopy(objArr, 0, objArr2, collection.size(), objArr.length);
        }
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            objArr2[i] = it.next();
            i++;
        }
        zze(objArr2);
    }
}
