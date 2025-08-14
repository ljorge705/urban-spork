package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes3.dex */
public final class zzjc extends zzec implements zzfm {
    private static final zzjc zza;
    private int zze;
    private int zzf;
    private zziy zzg;
    private zzih zzh;
    private zzhn zzi;
    private zzio zzj;
    private zzib zzk;
    private zzhq zzl;
    private zzje zzm;
    private zzim zzn;
    private zzim zzo;
    private zzim zzp;
    private boolean zzq;
    private zzie zzr;
    private int zzs = -1;

    static {
        zzjc zzjcVar = new zzjc();
        zza = zzjcVar;
        zzec.zzS(zzjc.class, zzjcVar);
    }

    private zzjc() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec
    protected final Object zzg(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzR(zza, "\u0001\u000e\u0000\u0001\u0001\u000e\u000e\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဉ\u0001\u0003ဉ\u0002\u0004ဉ\u0003\u0005ဉ\u0004\u0006ဉ\b\u0007ဉ\t\bဉ\n\tဇ\u000b\nဉ\u0005\u000bဉ\f\fဉ\u0006\rဉ\u0007\u000eင\r", new Object[]{"zze", "zzf", zzhr.zza, "zzg", "zzh", "zzi", "zzj", "zzn", "zzo", "zzp", "zzq", "zzk", "zzr", "zzl", "zzm", "zzs"});
        }
        if (i2 == 3) {
            return new zzjc();
        }
        zzhh zzhhVar = null;
        if (i2 == 4) {
            return new zzjb(zzhhVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zza;
    }
}
