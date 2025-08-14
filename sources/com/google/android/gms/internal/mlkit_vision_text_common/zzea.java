package com.google.android.gms.internal.mlkit_vision_text_common;

import com.google.android.gms.common.internal.Objects;

/* compiled from: com.google.android.gms:play-services-mlkit-text-recognition-common@@17.0.0 */
/* loaded from: classes3.dex */
public final class zzea {
    private final zzjz zza;
    private final Boolean zzc;
    private final zzln zze;
    private final Boolean zzb = null;
    private final zzjo zzd = null;

    /* synthetic */ zzea(zzdy zzdyVar, zzdz zzdzVar) {
        this.zza = zzdyVar.zza;
        this.zzc = zzdyVar.zzb;
        this.zze = zzdyVar.zzc;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzea)) {
            return false;
        }
        zzea zzeaVar = (zzea) obj;
        if (Objects.equal(this.zza, zzeaVar.zza)) {
            Boolean bool = zzeaVar.zzb;
            if (Objects.equal(null, null) && Objects.equal(this.zzc, zzeaVar.zzc)) {
                zzjo zzjoVar = zzeaVar.zzd;
                if (Objects.equal(null, null) && Objects.equal(this.zze, zzeaVar.zze)) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zza, null, this.zzc, null, this.zze);
    }

    public final zzjz zza() {
        return this.zza;
    }

    public final zzln zzb() {
        return this.zze;
    }

    public final Boolean zzc() {
        return this.zzc;
    }
}
