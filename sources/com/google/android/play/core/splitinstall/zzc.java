package com.google.android.play.core.splitinstall;

/* compiled from: com.google.android.play:feature-delivery@@2.1.0 */
/* loaded from: classes3.dex */
public final class zzc {
    private zzac zza;

    private zzc() {
    }

    /* synthetic */ zzc(zzb zzbVar) {
    }

    public final zzc zza(zzac zzacVar) {
        this.zza = zzacVar;
        return this;
    }

    public final zzp zzb() {
        zzac zzacVar = this.zza;
        if (zzacVar != null) {
            return new zze(zzacVar, null);
        }
        throw new IllegalStateException(String.valueOf(zzac.class.getCanonicalName()).concat(" must be set"));
    }
}
