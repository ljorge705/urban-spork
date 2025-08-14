package com.google.android.gms.internal.mlkit_vision_text_common;

import java.util.AbstractMap;

/* compiled from: com.google.android.gms:play-services-mlkit-text-recognition-common@@17.0.0 */
/* loaded from: classes3.dex */
final class zzch extends zzbm {
    final /* synthetic */ zzci zza;

    zzch(zzci zzciVar) {
        this.zza = zzciVar;
    }

    @Override // java.util.List
    public final /* bridge */ /* synthetic */ Object get(int i) {
        zzaa.zza(i, this.zza.zzc, "index");
        zzci zzciVar = this.zza;
        int i2 = i + i;
        Object obj = zzciVar.zzb[i2];
        obj.getClass();
        Object obj2 = zzciVar.zzb[i2 + 1];
        obj2.getClass();
        return new AbstractMap.SimpleImmutableEntry(obj, obj2);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zza.zzc;
    }
}
