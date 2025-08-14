package com.google.android.gms.internal.mlkit_vision_text_common;

/* compiled from: com.google.android.gms:play-services-mlkit-text-recognition-common@@17.0.0 */
/* loaded from: classes3.dex */
public final class zzjq {
    private Long zza;
    private zzjz zzb;
    private Boolean zzc;
    private Boolean zzd;
    private Boolean zze;

    public final zzjq zza(Boolean bool) {
        this.zzd = bool;
        return this;
    }

    public final zzjq zzb(Boolean bool) {
        this.zze = bool;
        return this;
    }

    public final zzjq zzc(Long l) {
        this.zza = Long.valueOf(l.longValue() & Long.MAX_VALUE);
        return this;
    }

    public final zzjq zzd(zzjz zzjzVar) {
        this.zzb = zzjzVar;
        return this;
    }

    public final zzjq zze(Boolean bool) {
        this.zzc = bool;
        return this;
    }

    public final zzjs zzf() {
        return new zzjs(this, null);
    }
}
