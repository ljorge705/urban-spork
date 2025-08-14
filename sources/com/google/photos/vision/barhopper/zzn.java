package com.google.photos.vision.barhopper;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfm;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes2.dex */
public final class zzn extends zzec implements zzfm {
    private static final zzn zza;
    private int zze;
    private int zzf;
    private int zzg;
    private int zzh;
    private int zzi;
    private int zzj;
    private int zzk;
    private boolean zzl;

    static {
        zzn zznVar = new zzn();
        zza = zznVar;
        zzec.zzS(zzn.class, zznVar);
    }

    private zzn() {
    }

    public static zzn zzi() {
        return zza;
    }

    public final int zza() {
        return this.zzh;
    }

    public final int zzb() {
        return this.zzi;
    }

    public final int zzc() {
        return this.zzj;
    }

    public final int zzd() {
        return this.zzg;
    }

    public final int zze() {
        return this.zzk;
    }

    public final int zzf() {
        return this.zzf;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec
    protected final Object zzg(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzR(zza, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0000\u0000\u0001င\u0000\u0002င\u0001\u0003င\u0002\u0004င\u0003\u0005င\u0004\u0006င\u0005\u0007ဇ\u0006", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl"});
        }
        if (i2 == 3) {
            return new zzn();
        }
        zza zzaVar = null;
        if (i2 == 4) {
            return new zzm(zzaVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zza;
    }

    public final boolean zzj() {
        return this.zzl;
    }
}
