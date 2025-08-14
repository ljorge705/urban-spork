package com.google.photos.vision.barhopper;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfm;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes2.dex */
public final class zzp extends zzec implements zzfm {
    private static final zzp zza;
    private int zze;
    private String zzf = "";
    private String zzg = "";
    private String zzh = "";
    private String zzi = "";
    private String zzj = "";
    private zzn zzk;
    private zzn zzl;

    static {
        zzp zzpVar = new zzp();
        zza = zzpVar;
        zzec.zzS(zzp.class, zzpVar);
    }

    private zzp() {
    }

    public static zzp zzd() {
        return zza;
    }

    public final zzn zza() {
        zzn zznVar = this.zzl;
        return zznVar == null ? zzn.zzi() : zznVar;
    }

    public final zzn zzb() {
        zzn zznVar = this.zzk;
        return zznVar == null ? zzn.zzi() : zznVar;
    }

    public final String zze() {
        return this.zzg;
    }

    public final String zzf() {
        return this.zzh;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec
    protected final Object zzg(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzR(zza, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဈ\u0003\u0005ဈ\u0004\u0006ဉ\u0005\u0007ဉ\u0006", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl"});
        }
        if (i2 == 3) {
            return new zzp();
        }
        zza zzaVar = null;
        if (i2 == 4) {
            return new zzo(zzaVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zza;
    }

    public final String zzh() {
        return this.zzi;
    }

    public final String zzi() {
        return this.zzj;
    }

    public final String zzj() {
        return this.zzf;
    }
}
