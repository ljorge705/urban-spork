package com.google.android.gms.internal.mlkit_vision_face;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-mlkit-face-detection@@17.1.0 */
/* loaded from: classes3.dex */
abstract class zzbv extends zzci {
    zzbv() {
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        zza().clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean contains(@CheckForNull Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        Object key = entry.getKey();
        Object objZza = zzbz.zza(zza(), key);
        if (zzx.zza(objZza, entry.getValue())) {
            return objZza != null || zzbz.zzb(((zzah) zza()).zza, key);
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean isEmpty() {
        return zza().isEmpty();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean remove(@CheckForNull Object obj) {
        if (contains(obj) && (obj instanceof Map.Entry)) {
            return ((zzah) zza()).zzb.zzq().remove(((Map.Entry) obj).getKey());
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return zza().size();
    }

    abstract Map zza();

    @Override // com.google.android.gms.internal.mlkit_vision_face.zzci, java.util.AbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean removeAll(Collection collection) {
        collection.getClass();
        try {
            return zzcj.zzb(this, collection);
        } catch (UnsupportedOperationException unused) {
            return zzcj.zzc(this, collection.iterator());
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face.zzci, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean retainAll(Collection collection) {
        int iCeil;
        collection.getClass();
        try {
            return super.retainAll(collection);
        } catch (UnsupportedOperationException unused) {
            int size = collection.size();
            if (size < 3) {
                zzau.zza(size, "expectedSize");
                iCeil = size + 1;
            } else {
                iCeil = size < 1073741824 ? (int) Math.ceil(size / 0.75d) : Integer.MAX_VALUE;
            }
            HashSet hashSet = new HashSet(iCeil);
            for (Object obj : collection) {
                if (contains(obj) && (obj instanceof Map.Entry)) {
                    hashSet.add(((Map.Entry) obj).getKey());
                }
            }
            return ((zzah) zza()).zzb.zzq().retainAll(hashSet);
        }
    }
}
