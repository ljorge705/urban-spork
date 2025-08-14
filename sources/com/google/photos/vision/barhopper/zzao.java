package com.google.photos.vision.barhopper;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfm;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes2.dex */
public final class zzao extends zzec implements zzfm {
    private static final zzao zza;
    private int zze;
    private int zzg;
    private boolean zzi;
    private byte zzj = 2;
    private String zzf = "";
    private String zzh = "";

    static {
        zzao zzaoVar = new zzao();
        zza = zzaoVar;
        zzec.zzS(zzao.class, zzaoVar);
    }

    private zzao() {
    }

    public static zzao zzb() {
        return zza;
    }

    public final String zzc() {
        return this.zzh;
    }

    public final String zzd() {
        return this.zzf;
    }

    public final int zze() {
        int iZza = zzan.zza(this.zzg);
        if (iZza == 0) {
            return 1;
        }
        return iZza;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec
    protected final Object zzg(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return Byte.valueOf(this.zzj);
        }
        if (i2 == 2) {
            return zzR(zza, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0001\u0001ᔈ\u0000\u0002ဌ\u0001\u0003ဈ\u0002\u0004ဇ\u0003", new Object[]{"zze", "zzf", "zzg", zzam.zza, "zzh", "zzi"});
        }
        if (i2 == 3) {
            return new zzao();
        }
        zza zzaVar = null;
        if (i2 == 4) {
            return new zzak(zzaVar);
        }
        if (i2 == 5) {
            return zza;
        }
        this.zzj = obj == null ? (byte) 0 : (byte) 1;
        return null;
    }
}
