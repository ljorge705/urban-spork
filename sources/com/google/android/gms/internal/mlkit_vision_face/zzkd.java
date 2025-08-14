package com.google.android.gms.internal.mlkit_vision_face;

import com.google.android.gms.common.internal.Objects;

/* compiled from: com.google.android.gms:play-services-mlkit-face-detection@@17.1.0 */
/* loaded from: classes3.dex */
public final class zzkd {
    private final zzka zza;
    private final zzjy zzb;
    private final zzkb zzc;
    private final zzjz zzd;
    private final Boolean zze;
    private final Float zzf;

    /* synthetic */ zzkd(zzjx zzjxVar, zzkc zzkcVar) {
        this.zza = zzjxVar.zza;
        this.zzb = zzjxVar.zzb;
        this.zzc = zzjxVar.zzc;
        this.zzd = zzjxVar.zzd;
        this.zze = zzjxVar.zze;
        this.zzf = zzjxVar.zzf;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzkd)) {
            return false;
        }
        zzkd zzkdVar = (zzkd) obj;
        return Objects.equal(this.zza, zzkdVar.zza) && Objects.equal(this.zzb, zzkdVar.zzb) && Objects.equal(this.zzc, zzkdVar.zzc) && Objects.equal(this.zzd, zzkdVar.zzd) && Objects.equal(this.zze, zzkdVar.zze) && Objects.equal(this.zzf, zzkdVar.zzf);
    }

    public final int hashCode() {
        return Objects.hashCode(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf);
    }

    public final zzjy zza() {
        return this.zzb;
    }

    public final zzjz zzb() {
        return this.zzd;
    }

    public final zzka zzc() {
        return this.zza;
    }

    public final zzkb zzd() {
        return this.zzc;
    }

    public final Boolean zze() {
        return this.zze;
    }

    public final Float zzf() {
        return this.zzf;
    }
}
