package com.google.android.gms.internal.mlkit_vision_face;

/* compiled from: com.google.android.gms:play-services-mlkit-face-detection@@17.1.0 */
/* loaded from: classes3.dex */
public final class zzon {
    private static zzom zza;

    public static synchronized zzoc zza(zznt zzntVar) {
        if (zza == null) {
            zza = new zzom(null);
        }
        return (zzoc) zza.get(zzntVar);
    }

    public static synchronized zzoc zzb(String str) {
        return zza(zznt.zzd(str).zzd());
    }
}
