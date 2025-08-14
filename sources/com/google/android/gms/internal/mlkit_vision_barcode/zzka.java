package com.google.android.gms.internal.mlkit_vision_barcode;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.0.0 */
/* loaded from: classes3.dex */
public final class zzka {
    private Long zza;
    private zzkj zzb;
    private Boolean zzc;
    private Boolean zzd;
    private Boolean zze;

    public final zzka zza(Boolean bool) {
        this.zzd = bool;
        return this;
    }

    public final zzka zzb(Boolean bool) {
        this.zze = bool;
        return this;
    }

    public final zzka zzc(Long l) {
        this.zza = Long.valueOf(l.longValue() & Long.MAX_VALUE);
        return this;
    }

    public final zzka zzd(zzkj zzkjVar) {
        this.zzb = zzkjVar;
        return this;
    }

    public final zzka zze(Boolean bool) {
        this.zzc = bool;
        return this;
    }

    public final zzkc zzf() {
        return new zzkc(this, null);
    }
}
