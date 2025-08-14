package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes3.dex */
public final class zziy extends zzec implements zzfm {
    private static final zziy zza;
    private int zze;
    private String zzf = "";
    private String zzg = "";
    private String zzh = "";
    private int zzi;
    private int zzj;
    private zzie zzk;
    private boolean zzl;
    private int zzm;
    private boolean zzn;
    private boolean zzo;
    private boolean zzp;
    private long zzq;

    static {
        zziy zziyVar = new zziy();
        zza = zziyVar;
        zzec.zzS(zziy.class, zziyVar);
    }

    private zziy() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec
    protected final Object zzg(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzR(zza, "\u0001\f\u0000\u0001\u0001\f\f\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဌ\u0003\u0005င\u0004\u0006ဉ\u0005\u0007ဇ\u0006\bဌ\u0007\tဇ\b\nဇ\t\u000bဇ\n\fဂ\u000b", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", zziz.zza, "zzj", "zzk", "zzl", "zzm", zzja.zza, "zzn", "zzo", "zzp", "zzq"});
        }
        if (i2 == 3) {
            return new zziy();
        }
        zzhh zzhhVar = null;
        if (i2 == 4) {
            return new zzix(zzhhVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zza;
    }
}
