package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.0.0 */
/* loaded from: classes3.dex */
final class zzbq extends zzbf {
    final /* synthetic */ zzbs zza;
    private final Object zzb;
    private int zzc;

    zzbq(zzbs zzbsVar, int i) {
        this.zza = zzbsVar;
        this.zzb = zzbs.zzg(zzbsVar, i);
        this.zzc = i;
    }

    private final void zza() {
        int i = this.zzc;
        if (i == -1 || i >= this.zza.size() || !zzam.zza(this.zzb, zzbs.zzg(this.zza, this.zzc))) {
            this.zzc = this.zza.zzv(this.zzb);
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzbf, java.util.Map.Entry
    public final Object getKey() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzbf, java.util.Map.Entry
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
        return zzbs.zzj(this.zza, i);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzbf, java.util.Map.Entry
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
        Object objZzj = zzbs.zzj(this.zza, i);
        zzbs.zzm(this.zza, this.zzc, obj);
        return objZzj;
    }
}
