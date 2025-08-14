package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes3.dex */
public final class zzdn {
    static final zzdn zza = new zzdn(true);
    private static volatile boolean zzb = false;
    private static volatile zzdn zzc;
    private final Map zzd;

    zzdn() {
        this.zzd = new HashMap();
    }

    public static zzdn zza() {
        zzdn zzdnVar = zzc;
        if (zzdnVar == null) {
            synchronized (zzdn.class) {
                zzdnVar = zzc;
                if (zzdnVar == null) {
                    zzdnVar = zza;
                    zzc = zzdnVar;
                }
            }
        }
        return zzdnVar;
    }

    public final zzea zzb(zzfl zzflVar, int i) {
        return (zzea) this.zzd.get(new zzdm(zzflVar, i));
    }

    zzdn(boolean z) {
        this.zzd = Collections.emptyMap();
    }
}
