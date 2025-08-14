package com.google.android.gms.internal.mlkit_vision_common;

/* compiled from: com.google.mlkit:vision-common@@17.2.0 */
/* loaded from: classes3.dex */
public final class zzlg {
    private static zzlf zza;

    public static synchronized zzkx zza(zzkr zzkrVar) {
        if (zza == null) {
            zza = new zzlf(null);
        }
        return (zzkx) zza.get(zzkrVar);
    }

    public static synchronized zzkx zzb(String str) {
        return zza(zzkr.zzd("vision-common").zzd());
    }
}
