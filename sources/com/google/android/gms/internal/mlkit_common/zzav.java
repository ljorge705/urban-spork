package com.google.android.gms.internal.mlkit_common;

import java.util.AbstractMap;

/* compiled from: com.google.mlkit:common@@18.5.0 */
/* loaded from: classes3.dex */
final class zzav extends zzao {
    final /* synthetic */ zzaw zza;

    zzav(zzaw zzawVar) {
        this.zza = zzawVar;
    }

    @Override // java.util.List
    public final /* bridge */ /* synthetic */ Object get(int i) {
        zzac.zza(i, this.zza.zzc, "index");
        zzaw zzawVar = this.zza;
        int i2 = i + i;
        Object obj = zzawVar.zzb[i2];
        obj.getClass();
        Object obj2 = zzawVar.zzb[i2 + 1];
        obj2.getClass();
        return new AbstractMap.SimpleImmutableEntry(obj, obj2);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zza.zzc;
    }
}
