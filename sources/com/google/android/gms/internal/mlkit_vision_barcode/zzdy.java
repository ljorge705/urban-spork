package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.android.gms.common.internal.Objects;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.0.0 */
/* loaded from: classes3.dex */
public final class zzdy {
    private final zzkj zza;
    private final Boolean zzc;
    private final zzmy zze;
    private final zzcc zzf;
    private final zzcc zzg;
    private final Boolean zzb = null;
    private final zzjy zzd = null;

    /* synthetic */ zzdy(zzdw zzdwVar, zzdx zzdxVar) {
        this.zza = zzdwVar.zza;
        this.zzc = zzdwVar.zzb;
        this.zze = zzdwVar.zzc;
        this.zzf = zzdwVar.zzd;
        this.zzg = zzdwVar.zze;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzdy)) {
            return false;
        }
        zzdy zzdyVar = (zzdy) obj;
        if (Objects.equal(this.zza, zzdyVar.zza)) {
            Boolean bool = zzdyVar.zzb;
            if (Objects.equal(null, null) && Objects.equal(this.zzc, zzdyVar.zzc)) {
                zzjy zzjyVar = zzdyVar.zzd;
                if (Objects.equal(null, null) && Objects.equal(this.zze, zzdyVar.zze) && Objects.equal(this.zzf, zzdyVar.zzf) && Objects.equal(this.zzg, zzdyVar.zzg)) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zza, null, this.zzc, null, this.zze, this.zzf, this.zzg);
    }

    public final zzcc zza() {
        return this.zzf;
    }

    public final zzcc zzb() {
        return this.zzg;
    }

    public final zzkj zzc() {
        return this.zza;
    }

    public final zzmy zzd() {
        return this.zze;
    }

    public final Boolean zze() {
        return this.zzc;
    }
}
