package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes3.dex */
public final class zzy extends zzec implements zzfm {
    private static final zzy zza;
    private int zze;
    private zzab zzf;

    static {
        zzy zzyVar = new zzy();
        zza = zzyVar;
        zzec.zzS(zzy.class, zzyVar);
    }

    private zzy() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec
    protected final Object zzg(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzR(zza, "\u0001\u0001\u0000\u0001\u000f\u000f\u0001\u0000\u0000\u0000\u000fဉ\u0000", new Object[]{"zze", "zzf"});
        }
        if (i2 == 3) {
            return new zzy();
        }
        zzw zzwVar = null;
        if (i2 == 4) {
            return new zzx(zzwVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zza;
    }
}
