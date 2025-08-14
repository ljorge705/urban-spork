package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes3.dex */
public final class zzis extends zzec implements zzfm {
    private static final zzis zza;
    private int zze;
    private zzek zzf = zzO();
    private zziu zzg;
    private zzhl zzh;

    static {
        zzis zzisVar = new zzis();
        zza = zzisVar;
        zzec.zzS(zzis.class, zzisVar);
    }

    private zzis() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec
    protected final Object zzg(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzR(zza, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0001\u0000\u0001\u001b\u0002ဉ\u0000\u0003ဉ\u0001", new Object[]{"zze", "zzf", zzjc.class, "zzg", "zzh"});
        }
        if (i2 == 3) {
            return new zzis();
        }
        zzhh zzhhVar = null;
        if (i2 == 4) {
            return new zzir(zzhhVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zza;
    }
}
