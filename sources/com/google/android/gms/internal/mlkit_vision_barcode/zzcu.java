package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.Iterator;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.0.0 */
/* loaded from: classes3.dex */
final class zzcu extends zzcf {
    private final transient zzce zza;
    private final transient zzcc zzb;

    zzcu(zzce zzceVar, zzcc zzccVar) {
        this.zza = zzceVar;
        this.zzb = zzccVar;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzbx, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(@CheckForNull Object obj) {
        return this.zza.get(obj) != null;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzcf, com.google.android.gms.internal.mlkit_vision_barcode.zzbx, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final /* synthetic */ Iterator iterator() {
        return this.zzb.listIterator(0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zza.size();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzbx
    final int zza(Object[] objArr, int i) {
        return this.zzb.zza(objArr, i);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzcf, com.google.android.gms.internal.mlkit_vision_barcode.zzbx
    /* renamed from: zzd */
    public final zzda iterator() {
        return this.zzb.listIterator(0);
    }
}
