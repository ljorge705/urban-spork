package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.android.gms.common.internal.Objects;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.0.0 */
/* loaded from: classes3.dex */
public final class zzjy {
    private final zzjw zza;
    private final Integer zzb;
    private final Integer zzc = null;
    private final Boolean zzd = null;

    /* synthetic */ zzjy(zzjv zzjvVar, zzjx zzjxVar) {
        this.zza = zzjvVar.zza;
        this.zzb = zzjvVar.zzb;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzjy)) {
            return false;
        }
        zzjy zzjyVar = (zzjy) obj;
        if (Objects.equal(this.zza, zzjyVar.zza) && Objects.equal(this.zzb, zzjyVar.zzb)) {
            Integer num = zzjyVar.zzc;
            if (Objects.equal(null, null)) {
                Boolean bool = zzjyVar.zzd;
                if (Objects.equal(null, null)) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zza, this.zzb, null, null);
    }

    public final zzjw zza() {
        return this.zza;
    }

    public final Integer zzb() {
        return this.zzb;
    }
}
