package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes3.dex */
public final class zzhn extends zzec implements zzfm {
    private static final zzhn zza;
    private int zze;
    private int zzf = -1;

    static {
        zzhn zzhnVar = new zzhn();
        zza = zzhnVar;
        zzec.zzS(zzhn.class, zzhnVar);
    }

    private zzhn() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec
    protected final Object zzg(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzR(zza, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001င\u0000", new Object[]{"zze", "zzf"});
        }
        if (i2 == 3) {
            return new zzhn();
        }
        zzhh zzhhVar = null;
        if (i2 == 4) {
            return new zzhm(zzhhVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zza;
    }
}
