package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.0.0 */
/* loaded from: classes3.dex */
abstract class zzck extends zzcx {
    zzck() {
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
        Object objZza = zzco.zza(zza(), key);
        if (zzam.zza(objZza, entry.getValue())) {
            return objZza != null || zzco.zzb(((zzaw) zza()).zza, key);
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
            return ((zzaw) zza()).zzb.zzq().remove(((Map.Entry) obj).getKey());
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return zza().size();
    }

    abstract Map zza();

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzcx, java.util.AbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean removeAll(Collection collection) {
        collection.getClass();
        try {
            return zzcy.zzb(this, collection);
        } catch (UnsupportedOperationException unused) {
            return zzcy.zzc(this, collection.iterator());
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzcx, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean retainAll(Collection collection) {
        int i;
        collection.getClass();
        try {
            return super.retainAll(collection);
        } catch (UnsupportedOperationException unused) {
            int size = collection.size();
            if (size < 3) {
                zzbj.zza(size, "expectedSize");
                i = size + 1;
            } else {
                i = size < 1073741824 ? (int) ((size / 0.75f) + 1.0f) : Integer.MAX_VALUE;
            }
            HashSet hashSet = new HashSet(i);
            for (Object obj : collection) {
                if (contains(obj) && (obj instanceof Map.Entry)) {
                    hashSet.add(((Map.Entry) obj).getKey());
                }
            }
            return ((zzaw) zza()).zzb.zzq().retainAll(hashSet);
        }
    }
}
