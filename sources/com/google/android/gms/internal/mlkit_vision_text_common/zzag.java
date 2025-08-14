package com.google.android.gms.internal.mlkit_vision_text_common;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-mlkit-text-recognition-common@@17.0.0 */
/* loaded from: classes3.dex */
final class zzag extends zzcc {
    final transient Map zza;
    final /* synthetic */ zzao zzb;

    zzag(zzao zzaoVar, Map map) {
        this.zzb = zzaoVar;
        this.zza = map;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void clear() {
        Map map = this.zza;
        zzao zzaoVar = this.zzb;
        if (map == zzaoVar.zza) {
            zzaoVar.zzn();
        } else {
            zzbq.zza(new zzaf(this));
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsKey(@CheckForNull Object obj) {
        return zzcd.zzb(this.zza, obj);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean equals(@CheckForNull Object obj) {
        return this == obj || this.zza.equals(obj);
    }

    @Override // java.util.AbstractMap, java.util.Map
    @CheckForNull
    public final /* bridge */ /* synthetic */ Object get(@CheckForNull Object obj) {
        Collection collection = (Collection) zzcd.zza(this.zza, obj);
        if (collection == null) {
            return null;
        }
        return this.zzb.zzb(obj, collection);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int hashCode() {
        return this.zza.hashCode();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzcc, java.util.AbstractMap, java.util.Map
    public final Set keySet() {
        return this.zzb.zzq();
    }

    @Override // java.util.AbstractMap, java.util.Map
    @CheckForNull
    public final /* bridge */ /* synthetic */ Object remove(@CheckForNull Object obj) {
        Collection collection = (Collection) this.zza.remove(obj);
        if (collection == null) {
            return null;
        }
        Collection collectionZza = this.zzb.zza();
        collectionZza.addAll(collection);
        zzao.zzg(this.zzb, collection.size());
        collection.clear();
        return collectionZza;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int size() {
        return this.zza.size();
    }

    @Override // java.util.AbstractMap
    public final String toString() {
        return this.zza.toString();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzcc
    protected final Set zza() {
        return new zzae(this);
    }
}
