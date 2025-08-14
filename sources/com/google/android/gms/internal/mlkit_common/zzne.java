package com.google.android.gms.internal.mlkit_common;

/* compiled from: com.google.mlkit:common@@18.5.0 */
/* loaded from: classes3.dex */
public final class zzne {
    private static zzne zza;

    private zzne() {
    }

    public static synchronized zzne zza() {
        if (zza == null) {
            zza = new zzne();
        }
        return zza;
    }

    public static void zzb() {
        zznd.zza();
    }
}
