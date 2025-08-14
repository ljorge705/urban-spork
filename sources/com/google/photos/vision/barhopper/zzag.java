package com.google.photos.vision.barhopper;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfm;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes2.dex */
public final class zzag extends zzec implements zzfm {
    private static final zzag zza;
    private int zze;
    private byte zzh = 2;
    private String zzf = "";
    private String zzg = "";

    static {
        zzag zzagVar = new zzag();
        zza = zzagVar;
        zzec.zzS(zzag.class, zzagVar);
    }

    private zzag() {
    }

    public static zzag zzb() {
        return zza;
    }

    public final String zzc() {
        return this.zzf;
    }

    public final String zzd() {
        return this.zzg;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec
    protected final Object zzg(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return Byte.valueOf(this.zzh);
        }
        if (i2 == 2) {
            return zzR(zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0001\u0001ဈ\u0000\u0002ᔈ\u0001", new Object[]{"zze", "zzf", "zzg"});
        }
        if (i2 == 3) {
            return new zzag();
        }
        zza zzaVar = null;
        if (i2 == 4) {
            return new zzaf(zzaVar);
        }
        if (i2 == 5) {
            return zza;
        }
        this.zzh = obj == null ? (byte) 0 : (byte) 1;
        return null;
    }
}
