package com.google.android.play.core.splitinstall.testing;

import java.util.Map;

/* compiled from: com.google.android.play:feature-delivery@@2.1.0 */
/* loaded from: classes3.dex */
final class zza extends zzu {
    private Integer zza;
    private Map zzb;

    zza() {
    }

    @Override // com.google.android.play.core.splitinstall.testing.zzu
    final zzu zza(int i) {
        this.zza = Integer.valueOf(i);
        return this;
    }

    @Override // com.google.android.play.core.splitinstall.testing.zzu
    final zzu zzb(Map map) {
        if (map == null) {
            throw new NullPointerException("Null splitInstallErrorCodeByModule");
        }
        this.zzb = map;
        return this;
    }

    @Override // com.google.android.play.core.splitinstall.testing.zzu
    final Map zzd() {
        Map map = this.zzb;
        if (map != null) {
            return map;
        }
        throw new IllegalStateException("Property \"splitInstallErrorCodeByModule\" has not been set");
    }

    @Override // com.google.android.play.core.splitinstall.testing.zzu
    final zzv zzc() {
        if (this.zzb != null) {
            return new zzc(this.zza, this.zzb, null);
        }
        throw new IllegalStateException("Missing required properties: splitInstallErrorCodeByModule");
    }
}
