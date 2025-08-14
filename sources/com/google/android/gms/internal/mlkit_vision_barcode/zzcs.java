package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.AbstractMap;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.0.0 */
/* loaded from: classes3.dex */
final class zzcs extends zzcc {
    final /* synthetic */ zzct zza;

    zzcs(zzct zzctVar) {
        this.zza = zzctVar;
    }

    @Override // java.util.List
    public final /* bridge */ /* synthetic */ Object get(int i) {
        zzaq.zza(i, this.zza.zzc, "index");
        zzct zzctVar = this.zza;
        int i2 = i + i;
        Object obj = zzctVar.zzb[i2];
        obj.getClass();
        Object obj2 = zzctVar.zzb[i2 + 1];
        obj2.getClass();
        return new AbstractMap.SimpleImmutableEntry(obj, obj2);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zza.zzc;
    }
}
