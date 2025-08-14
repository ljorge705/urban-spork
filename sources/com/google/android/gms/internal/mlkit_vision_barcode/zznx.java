package com.google.android.gms.internal.mlkit_vision_barcode;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.0.0 */
/* loaded from: classes3.dex */
public final class zznx {
    private static zznw zza;

    public static synchronized zznm zza(zzne zzneVar) {
        if (zza == null) {
            zza = new zznw(null);
        }
        return (zznm) zza.get(zzneVar);
    }

    public static synchronized zznm zzb(String str) {
        return zza(zzne.zzd(str).zzd());
    }
}
