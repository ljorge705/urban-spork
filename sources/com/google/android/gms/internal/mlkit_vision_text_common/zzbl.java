package com.google.android.gms.internal.mlkit_vision_text_common;

import java.util.List;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-mlkit-text-recognition-common@@17.0.0 */
/* loaded from: classes3.dex */
final class zzbl extends zzbm {
    final transient int zza;
    final transient int zzb;
    final /* synthetic */ zzbm zzc;

    zzbl(zzbm zzbmVar, int i, int i2) {
        this.zzc = zzbmVar;
        this.zza = i;
        this.zzb = i2;
    }

    @Override // java.util.List
    public final Object get(int i) {
        zzaa.zza(i, this.zzb, "index");
        return this.zzc.get(i + this.zza);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzbm, java.util.List
    public final /* bridge */ /* synthetic */ List subList(int i, int i2) {
        return subList(i, i2);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzbh
    final int zzb() {
        return this.zzc.zzc() + this.zza + this.zzb;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzbh
    final int zzc() {
        return this.zzc.zzc() + this.zza;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzbh
    @CheckForNull
    final Object[] zze() {
        return this.zzc.zze();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzbm
    /* renamed from: zzf */
    public final zzbm subList(int i, int i2) {
        zzaa.zzc(i, i2, this.zzb);
        zzbm zzbmVar = this.zzc;
        int i3 = this.zza;
        return zzbmVar.subList(i + i3, i2 + i3);
    }
}
