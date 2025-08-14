package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes3.dex */
public final class zzcd extends zzec implements zzfm {
    private static final zzcd zza;
    private int zze;
    private String zzf = "";
    private String zzg = "";
    private String zzh = "";
    private String zzi = "";
    private String zzj = "";
    private String zzk = "";
    private String zzl = "";

    static {
        zzcd zzcdVar = new zzcd();
        zza = zzcdVar;
        zzec.zzS(zzcd.class, zzcdVar);
    }

    private zzcd() {
    }

    public static zzcd zzb() {
        return zza;
    }

    public final String zzc() {
        return this.zzi;
    }

    public final String zzd() {
        return this.zzf;
    }

    public final String zze() {
        return this.zzk;
    }

    public final String zzf() {
        return this.zzj;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec
    protected final Object zzg(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzR(zza, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဈ\u0003\u0005ဈ\u0004\u0006ဈ\u0005\u0007ဈ\u0006", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl"});
        }
        if (i2 == 3) {
            return new zzcd();
        }
        zzbw zzbwVar = null;
        if (i2 == 4) {
            return new zzcc(zzbwVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zza;
    }

    public final String zzh() {
        return this.zzh;
    }

    public final String zzi() {
        return this.zzg;
    }

    public final String zzj() {
        return this.zzl;
    }
}
