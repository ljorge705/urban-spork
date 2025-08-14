package com.google.photos.vision.barhopper;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfm;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes2.dex */
public final class zzac extends zzec implements zzfm {
    private static final zzac zza;
    private int zze;
    private double zzf;
    private double zzg;
    private byte zzh = 2;

    static {
        zzac zzacVar = new zzac();
        zza = zzacVar;
        zzec.zzS(zzac.class, zzacVar);
    }

    private zzac() {
    }

    public static zzac zzd() {
        return zza;
    }

    public final double zza() {
        return this.zzf;
    }

    public final double zzb() {
        return this.zzg;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec
    protected final Object zzg(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return Byte.valueOf(this.zzh);
        }
        if (i2 == 2) {
            return zzR(zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0002\u0001ᔀ\u0000\u0002ᔀ\u0001", new Object[]{"zze", "zzf", "zzg"});
        }
        if (i2 == 3) {
            return new zzac();
        }
        zza zzaVar = null;
        if (i2 == 4) {
            return new zzab(zzaVar);
        }
        if (i2 == 5) {
            return zza;
        }
        this.zzh = obj == null ? (byte) 0 : (byte) 1;
        return null;
    }
}
