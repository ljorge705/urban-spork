package com.google.android.gms.internal.mlkit_vision_text_common;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-mlkit-text-recognition-common@@17.0.0 */
/* loaded from: classes3.dex */
final class zzai extends zzca {
    final /* synthetic */ zzao zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzai(zzao zzaoVar, Map map) {
        super(map);
        this.zza = zzaoVar;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzca, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        zzbq.zza(iterator());
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean containsAll(Collection collection) {
        return this.zzb.keySet().containsAll(collection);
    }

    @Override // java.util.AbstractSet, java.util.Collection, java.util.Set
    public final boolean equals(@CheckForNull Object obj) {
        return this == obj || this.zzb.keySet().equals(obj);
    }

    @Override // java.util.AbstractSet, java.util.Collection, java.util.Set
    public final int hashCode() {
        return this.zzb.keySet().hashCode();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzca, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new zzah(this, this.zzb.entrySet().iterator());
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzca, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(@CheckForNull Object obj) {
        Collection collection = (Collection) this.zzb.remove(obj);
        if (collection == null) {
            return false;
        }
        int size = collection.size();
        collection.clear();
        zzao.zzg(this.zza, size);
        return size > 0;
    }
}
