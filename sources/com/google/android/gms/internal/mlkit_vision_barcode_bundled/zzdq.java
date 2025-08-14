package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes3.dex */
final class zzdq {
    private static final zzdo zza = new zzdp();
    private static final zzdo zzb;

    static {
        zzdo zzdoVar;
        try {
            zzdoVar = (zzdo) Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            zzdoVar = null;
        }
        zzb = zzdoVar;
    }

    static zzdo zza() {
        zzdo zzdoVar = zzb;
        if (zzdoVar != null) {
            return zzdoVar;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }

    static zzdo zzb() {
        return zza;
    }
}
