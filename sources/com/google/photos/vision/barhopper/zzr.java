package com.google.photos.vision.barhopper;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcb;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcd;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzci;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzek;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfm;
import java.util.List;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes2.dex */
public final class zzr extends zzec implements zzfm {
    private static final zzr zza;
    private int zze;
    private zzcd zzf;
    private byte zzn = 2;
    private String zzg = "";
    private String zzh = "";
    private zzek zzi = zzO();
    private zzek zzj = zzO();
    private zzek zzk = zzec.zzO();
    private zzek zzl = zzO();
    private String zzm = "";

    static {
        zzr zzrVar = new zzr();
        zza = zzrVar;
        zzec.zzS(zzr.class, zzrVar);
    }

    private zzr() {
    }

    public static zzr zzc() {
        return zza;
    }

    public final zzcd zza() {
        zzcd zzcdVar = this.zzf;
        return zzcdVar == null ? zzcd.zzb() : zzcdVar;
    }

    public final String zzd() {
        return this.zzg;
    }

    public final String zze() {
        return this.zzh;
    }

    public final List zzf() {
        return this.zzl;
    }

    public final List zzh() {
        return this.zzj;
    }

    public final List zzi() {
        return this.zzi;
    }

    public final List zzj() {
        return this.zzk;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec
    protected final Object zzg(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return Byte.valueOf(this.zzn);
        }
        if (i2 == 2) {
            return zzR(zza, "\u0001\b\u0000\u0001\u0001\b\b\u0000\u0004\u0001\u0001ဉ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004\u001b\u0005\u001b\u0006\u001a\u0007Л\bဈ\u0003", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", zzci.class, "zzj", zzy.class, "zzk", "zzl", zzcb.class, "zzm"});
        }
        if (i2 == 3) {
            return new zzr();
        }
        zza zzaVar = null;
        if (i2 == 4) {
            return new zzq(zzaVar);
        }
        if (i2 == 5) {
            return zza;
        }
        this.zzn = obj == null ? (byte) 0 : (byte) 1;
        return null;
    }
}
