package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes3.dex */
public final class zzie extends zzec implements zzfm {
    private static final zzie zza;
    private int zze;
    private boolean zzf;
    private boolean zzg;

    static {
        zzie zzieVar = new zzie();
        zza = zzieVar;
        zzec.zzS(zzie.class, zzieVar);
    }

    private zzie() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec
    protected final Object zzg(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzR(zza, "\u0001\u0002\u0000\u0001\u0005\u0006\u0002\u0000\u0000\u0000\u0005ဇ\u0000\u0006ဇ\u0001", new Object[]{"zze", "zzf", "zzg"});
        }
        if (i2 == 3) {
            return new zzie();
        }
        zzhh zzhhVar = null;
        if (i2 == 4) {
            return new zzid(zzhhVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zza;
    }
}
