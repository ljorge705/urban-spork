package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes3.dex */
public final class zzhq extends zzec implements zzfm {
    private static final zzhq zza;
    private int zze;
    private String zzf = "";
    private int zzg = 1;
    private boolean zzh;
    private int zzi;

    static {
        zzhq zzhqVar = new zzhq();
        zza = zzhqVar;
        zzec.zzS(zzhq.class, zzhqVar);
    }

    private zzhq() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec
    protected final Object zzg(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzR(zza, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဌ\u0001\u0003ဇ\u0002\u0004င\u0003", new Object[]{"zze", "zzf", "zzg", zzhp.zza, "zzh", "zzi"});
        }
        if (i2 == 3) {
            return new zzhq();
        }
        zzhh zzhhVar = null;
        if (i2 == 4) {
            return new zzho(zzhhVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zza;
    }
}
