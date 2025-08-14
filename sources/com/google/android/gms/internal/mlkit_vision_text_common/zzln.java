package com.google.android.gms.internal.mlkit_vision_text_common;

import com.google.android.gms.common.internal.Objects;

/* compiled from: com.google.android.gms:play-services-mlkit-text-recognition-common@@17.0.0 */
/* loaded from: classes3.dex */
public final class zzln {
    private final zzll zza;
    private final Boolean zzb = null;
    private final String zzc = null;

    /* synthetic */ zzln(zzlk zzlkVar, zzlm zzlmVar) {
        this.zza = zzlkVar.zza;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzln)) {
            return false;
        }
        zzln zzlnVar = (zzln) obj;
        if (Objects.equal(this.zza, zzlnVar.zza)) {
            Boolean bool = zzlnVar.zzb;
            if (Objects.equal(null, null)) {
                String str = zzlnVar.zzc;
                if (Objects.equal(null, null)) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zza, null, null);
    }

    public final zzll zza() {
        return this.zza;
    }
}
