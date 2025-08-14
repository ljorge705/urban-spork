package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.android.gms.common.internal.Objects;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.0.0 */
/* loaded from: classes3.dex */
public final class zzmy {
    private final zzcc zza;

    /* synthetic */ zzmy(zzmw zzmwVar, zzmx zzmxVar) {
        this.zza = zzmwVar.zza;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzmy) {
            return Objects.equal(this.zza, ((zzmy) obj).zza);
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zza);
    }

    public final zzcc zza() {
        return this.zza;
    }
}
