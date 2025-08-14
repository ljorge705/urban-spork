package com.google.photos.vision.barhopper;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfm;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes2.dex */
public final class zzy extends zzec implements zzfm {
    private static final zzy zza;
    private int zze;
    private int zzf;
    private String zzg = "";
    private String zzh = "";
    private String zzi = "";

    static {
        zzy zzyVar = new zzy();
        zza = zzyVar;
        zzec.zzS(zzy.class, zzyVar);
    }

    private zzy() {
    }

    public static zzy zzb() {
        return zza;
    }

    public final String zzc() {
        return this.zzg;
    }

    public final String zzd() {
        return this.zzi;
    }

    public final String zze() {
        return this.zzh;
    }

    public final int zzf() {
        int iZza = zzx.zza(this.zzf);
        if (iZza == 0) {
            return 1;
        }
        return iZza;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec
    protected final Object zzg(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzR(zza, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဈ\u0003", new Object[]{"zze", "zzf", zzw.zza, "zzg", "zzh", "zzi"});
        }
        if (i2 == 3) {
            return new zzy();
        }
        zza zzaVar = null;
        if (i2 == 4) {
            return new zzu(zzaVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zza;
    }
}
