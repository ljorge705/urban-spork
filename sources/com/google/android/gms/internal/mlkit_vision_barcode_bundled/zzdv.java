package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes3.dex */
final class zzdv implements zzfj {
    private static final zzdv zza = new zzdv();

    private zzdv() {
    }

    public static zzdv zza() {
        return zza;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfj
    public final zzfi zzb(Class cls) {
        if (!zzec.class.isAssignableFrom(cls)) {
            String strValueOf = String.valueOf(cls.getName());
            throw new IllegalArgumentException(strValueOf.length() != 0 ? "Unsupported message type: ".concat(strValueOf) : new String("Unsupported message type: "));
        }
        try {
            return (zzfi) zzec.zzI(cls.asSubclass(zzec.class)).zzg(3, null, null);
        } catch (Exception e) {
            String strValueOf2 = String.valueOf(cls.getName());
            throw new RuntimeException(strValueOf2.length() != 0 ? "Unable to get message info for ".concat(strValueOf2) : new String("Unable to get message info for "), e);
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfj
    public final boolean zzc(Class cls) {
        return zzec.class.isAssignableFrom(cls);
    }
}
