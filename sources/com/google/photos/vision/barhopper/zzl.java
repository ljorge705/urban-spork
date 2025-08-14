package com.google.photos.vision.barhopper;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzek;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfm;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes2.dex */
public final class zzl extends zzec implements zzfm {
    private static final zzl zza;
    private int zze;
    private String zzf = "";
    private zzek zzg = zzO();

    static {
        zzl zzlVar = new zzl();
        zza = zzlVar;
        zzec.zzS(zzl.class, zzlVar);
    }

    private zzl() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec
    protected final Object zzg(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzR(zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001ဈ\u0000\u0002\u001b", new Object[]{"zze", "zzf", "zzg", zzaa.class});
        }
        if (i2 == 3) {
            return new zzl();
        }
        zza zzaVar = null;
        if (i2 == 4) {
            return new zzk(zzaVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zza;
    }
}
