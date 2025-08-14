package com.google.android.gms.internal.mlkit_vision_text_common;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-mlkit-text-recognition-common@@17.0.0 */
/* loaded from: classes3.dex */
final class zzba extends zzap {
    final /* synthetic */ zzbc zza;
    private final Object zzb;
    private int zzc;

    zzba(zzbc zzbcVar, int i) {
        this.zza = zzbcVar;
        this.zzb = zzbc.zzg(zzbcVar, i);
        this.zzc = i;
    }

    private final void zza() {
        int i = this.zzc;
        if (i == -1 || i >= this.zza.size() || !zzw.zza(this.zzb, zzbc.zzg(this.zza, this.zzc))) {
            this.zzc = this.zza.zzv(this.zzb);
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzap, java.util.Map.Entry
    public final Object getKey() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzap, java.util.Map.Entry
    public final Object getValue() {
        Map mapZzl = this.zza.zzl();
        if (mapZzl != null) {
            return mapZzl.get(this.zzb);
        }
        zza();
        int i = this.zzc;
        if (i == -1) {
            return null;
        }
        return zzbc.zzj(this.zza, i);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzap, java.util.Map.Entry
    public final Object setValue(Object obj) {
        Map mapZzl = this.zza.zzl();
        if (mapZzl != null) {
            return mapZzl.put(this.zzb, obj);
        }
        zza();
        int i = this.zzc;
        if (i == -1) {
            this.zza.put(this.zzb, obj);
            return null;
        }
        Object objZzj = zzbc.zzj(this.zza, i);
        zzbc.zzm(this.zza, this.zzc, obj);
        return objZzj;
    }
}
