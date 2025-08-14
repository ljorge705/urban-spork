package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes3.dex */
public final class zziu extends zzec implements zzfm {
    private static final zziu zza;
    private int zze;
    private String zzf = "";
    private long zzg;
    private long zzh;
    private long zzi;

    static {
        zziu zziuVar = new zziu();
        zza = zziuVar;
        zzec.zzS(zziu.class, zziuVar);
    }

    private zziu() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec
    protected final Object zzg(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzR(zza, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဂ\u0001\u0003ဂ\u0002\u0004ဂ\u0003", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi"});
        }
        if (i2 == 3) {
            return new zziu();
        }
        zzhh zzhhVar = null;
        if (i2 == 4) {
            return new zzit(zzhhVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zza;
    }
}
