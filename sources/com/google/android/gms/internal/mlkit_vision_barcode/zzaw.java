package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.0.0 */
/* loaded from: classes3.dex */
final class zzaw extends zzcn {
    final transient Map zza;
    final /* synthetic */ zzbe zzb;

    zzaw(zzbe zzbeVar, Map map) {
        this.zzb = zzbeVar;
        this.zza = map;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void clear() {
        Map map = this.zza;
        zzbe zzbeVar = this.zzb;
        if (map == zzbeVar.zza) {
            zzbeVar.zzn();
        } else {
            zzcg.zza(new zzav(this));
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsKey(@CheckForNull Object obj) {
        return zzco.zzb(this.zza, obj);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean equals(@CheckForNull Object obj) {
        return this == obj || this.zza.equals(obj);
    }

    @Override // java.util.AbstractMap, java.util.Map
    @CheckForNull
    public final /* bridge */ /* synthetic */ Object get(@CheckForNull Object obj) {
        Collection collection = (Collection) zzco.zza(this.zza, obj);
        if (collection == null) {
            return null;
        }
        return this.zzb.zzb(obj, collection);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int hashCode() {
        return this.zza.hashCode();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzcn, java.util.AbstractMap, java.util.Map
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
        zzbe.zzg(this.zzb, collection.size());
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

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzcn
    protected final Set zza() {
        return new zzau(this);
    }
}
