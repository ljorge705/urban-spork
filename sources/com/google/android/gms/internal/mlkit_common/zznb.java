package com.google.android.gms.internal.mlkit_common;

/* compiled from: com.google.mlkit:common@@18.5.0 */
/* loaded from: classes3.dex */
public final class zznb {
    private static zzna zza;

    public static synchronized zzmq zza(zzmj zzmjVar) {
        if (zza == null) {
            zza = new zzna(null);
        }
        return (zzmq) zza.get(zzmjVar);
    }

    public static synchronized zzmq zzb(String str) {
        return zza(zzmj.zzd("common").zzd());
    }
}
