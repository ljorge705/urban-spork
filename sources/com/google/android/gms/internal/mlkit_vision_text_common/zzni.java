package com.google.android.gms.internal.mlkit_vision_text_common;

/* compiled from: com.google.android.gms:play-services-mlkit-text-recognition-common@@17.0.0 */
/* loaded from: classes3.dex */
public final class zzni {
    private static zznh zza;

    public static synchronized zzmx zza(zzmq zzmqVar) {
        if (zza == null) {
            zza = new zznh(null);
        }
        return (zzmx) zza.get(zzmqVar);
    }

    public static synchronized zzmx zzb(String str) {
        return zza(zzmq.zzd(str).zzd());
    }
}
