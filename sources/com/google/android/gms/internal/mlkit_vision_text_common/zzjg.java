package com.google.android.gms.internal.mlkit_vision_text_common;

/* compiled from: com.google.android.gms:play-services-mlkit-text-recognition-common@@17.0.0 */
/* loaded from: classes3.dex */
public final class zzjg {
    private Long zza;
    private Long zzb;
    private Long zzc;
    private Long zzd;
    private Long zze;
    private Long zzf;

    public final zzjg zza(Long l) {
        this.zzc = Long.valueOf(l.longValue() & Long.MAX_VALUE);
        return this;
    }

    public final zzjg zzb(Long l) {
        this.zzd = Long.valueOf(l.longValue() & Long.MAX_VALUE);
        return this;
    }

    public final zzjg zzc(Long l) {
        this.zza = Long.valueOf(l.longValue() & Long.MAX_VALUE);
        return this;
    }

    public final zzjg zzd(Long l) {
        this.zze = Long.valueOf(l.longValue() & Long.MAX_VALUE);
        return this;
    }

    public final zzjg zze(Long l) {
        this.zzb = Long.valueOf(l.longValue() & Long.MAX_VALUE);
        return this;
    }

    public final zzjg zzf(Long l) {
        this.zzf = Long.valueOf(l.longValue() & Long.MAX_VALUE);
        return this;
    }

    public final zzji zzg() {
        return new zzji(this, null);
    }
}
