package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes3.dex */
public final class zzhw extends zzec implements zzfm {
    private static final zzhw zza;
    private int zze;
    private int zzf;
    private long zzg;

    static {
        zzhw zzhwVar = new zzhw();
        zza = zzhwVar;
        zzec.zzS(zzhw.class, zzhwVar);
    }

    private zzhw() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec
    protected final Object zzg(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzR(zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဂ\u0001", new Object[]{"zze", "zzf", zzhx.zza, "zzg"});
        }
        if (i2 == 3) {
            return new zzhw();
        }
        zzhh zzhhVar = null;
        if (i2 == 4) {
            return new zzhv(zzhhVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zza;
    }
}
