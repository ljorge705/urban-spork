package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes3.dex */
public final class zziq extends zzec implements zzfm {
    private static final zziq zza;
    private int zze;
    private String zzf = "";
    private zziw zzg;

    static {
        zziq zziqVar = new zziq();
        zza = zziqVar;
        zzec.zzS(zziq.class, zziqVar);
    }

    private zziq() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec
    protected final Object zzg(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzR(zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဉ\u0001", new Object[]{"zze", "zzf", "zzg"});
        }
        if (i2 == 3) {
            return new zziq();
        }
        zzhh zzhhVar = null;
        if (i2 == 4) {
            return new zzip(zzhhVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zza;
    }
}
