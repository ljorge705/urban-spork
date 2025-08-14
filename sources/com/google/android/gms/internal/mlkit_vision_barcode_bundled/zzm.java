package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes3.dex */
public final class zzm extends zzec implements zzfm {
    private static final zzm zza;
    private int zze;
    private zzek zzf = zzec.zzO();
    private String zzg = "";

    static {
        zzm zzmVar = new zzm();
        zza = zzmVar;
        zzec.zzS(zzm.class, zzmVar);
    }

    private zzm() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec
    protected final Object zzg(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzR(zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u001a\u0002ဈ\u0000", new Object[]{"zze", "zzf", "zzg"});
        }
        if (i2 == 3) {
            return new zzm();
        }
        zzk zzkVar = null;
        if (i2 == 4) {
            return new zzl(zzkVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zza;
    }
}
