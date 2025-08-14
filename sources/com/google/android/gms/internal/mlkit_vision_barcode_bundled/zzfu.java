package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes3.dex */
final class zzfu {
    private static final zzfu zza = new zzfu();
    private final ConcurrentMap zzc = new ConcurrentHashMap();
    private final zzgc zzb = new zzfd();

    private zzfu() {
    }

    public static zzfu zza() {
        return zza;
    }

    public final zzgb zzb(Class cls) {
        zzel.zzf(cls, "messageType");
        zzgb zzgbVarZza = (zzgb) this.zzc.get(cls);
        if (zzgbVarZza == null) {
            zzgbVarZza = this.zzb.zza(cls);
            zzel.zzf(cls, "messageType");
            zzel.zzf(zzgbVarZza, "schema");
            zzgb zzgbVar = (zzgb) this.zzc.putIfAbsent(cls, zzgbVarZza);
            if (zzgbVar != null) {
                return zzgbVar;
            }
        }
        return zzgbVarZza;
    }
}
