package com.google.android.gms.internal.mlkit_vision_text_common;

/* compiled from: com.google.android.gms:play-services-mlkit-text-recognition-common@@17.0.0 */
/* loaded from: classes3.dex */
public final class zzlr {
    private String zza;
    private String zzb;
    private String zzc;
    private String zzd;
    private zzbm zze;
    private String zzf;
    private Boolean zzg;
    private Boolean zzh;
    private Boolean zzi;
    private Integer zzj;
    private Integer zzk;

    public final zzlr zzb(String str) {
        this.zza = str;
        return this;
    }

    public final zzlr zzc(String str) {
        this.zzb = str;
        return this;
    }

    public final zzlr zzd(Integer num) {
        this.zzj = Integer.valueOf(num.intValue() & Integer.MAX_VALUE);
        return this;
    }

    public final zzlr zze(Boolean bool) {
        this.zzg = bool;
        return this;
    }

    public final zzlr zzf(Boolean bool) {
        this.zzi = bool;
        return this;
    }

    public final zzlr zzg(Boolean bool) {
        this.zzh = bool;
        return this;
    }

    public final zzlr zzh(zzbm zzbmVar) {
        this.zze = zzbmVar;
        return this;
    }

    public final zzlr zzi(String str) {
        this.zzf = str;
        return this;
    }

    public final zzlr zzj(String str) {
        this.zzc = str;
        return this;
    }

    public final zzlr zzk(Integer num) {
        this.zzk = num;
        return this;
    }

    public final zzlr zzl(String str) {
        this.zzd = str;
        return this;
    }

    public final zzlt zzm() {
        return new zzlt(this, null);
    }
}
