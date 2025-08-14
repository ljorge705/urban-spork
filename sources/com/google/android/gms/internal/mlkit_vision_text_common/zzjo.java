package com.google.android.gms.internal.mlkit_vision_text_common;

import com.google.android.gms.common.internal.Objects;

/* compiled from: com.google.android.gms:play-services-mlkit-text-recognition-common@@17.0.0 */
/* loaded from: classes3.dex */
public final class zzjo {
    private final zzjm zza;
    private final Integer zzb;
    private final Integer zzc = null;
    private final Boolean zzd = null;

    /* synthetic */ zzjo(zzjl zzjlVar, zzjn zzjnVar) {
        this.zza = zzjlVar.zza;
        this.zzb = zzjlVar.zzb;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzjo)) {
            return false;
        }
        zzjo zzjoVar = (zzjo) obj;
        if (Objects.equal(this.zza, zzjoVar.zza) && Objects.equal(this.zzb, zzjoVar.zzb)) {
            Integer num = zzjoVar.zzc;
            if (Objects.equal(null, null)) {
                Boolean bool = zzjoVar.zzd;
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

    public final zzjm zza() {
        return this.zza;
    }

    public final Integer zzb() {
        return this.zzb;
    }
}
