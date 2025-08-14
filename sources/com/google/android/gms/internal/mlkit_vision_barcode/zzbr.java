package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.0.0 */
/* loaded from: classes3.dex */
final class zzbr extends AbstractCollection {
    final /* synthetic */ zzbs zza;

    zzbr(zzbs zzbsVar) {
        this.zza = zzbsVar;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final void clear() {
        this.zza.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        zzbs zzbsVar = this.zza;
        Map mapZzl = zzbsVar.zzl();
        return mapZzl != null ? mapZzl.values().iterator() : new zzbm(zzbsVar);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final int size() {
        return this.zza.size();
    }
}
