package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes3.dex */
public final class zzae extends zzec implements zzfm {
    private static final zzae zza;
    private int zze;
    private int zzf = 4369;
    private String zzg = "";

    static {
        zzae zzaeVar = new zzae();
        zza = zzaeVar;
        zzec.zzS(zzae.class, zzaeVar);
    }

    private zzae() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec
    protected final Object zzg(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzR(zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဈ\u0001", new Object[]{"zze", "zzf", zzs.zzc(), "zzg"});
        }
        if (i2 == 3) {
            return new zzae();
        }
        zzac zzacVar = null;
        if (i2 == 4) {
            return new zzad(zzacVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zza;
    }
}
