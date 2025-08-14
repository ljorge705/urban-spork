package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes3.dex */
public final class zzih extends zzec implements zzfm {
    private static final zzih zza;
    private int zze;
    private boolean zzf;
    private int zzg;
    private int zzi;
    private int zzj;
    private int zzk;
    private int zzl;
    private boolean zzh = true;
    private String zzm = "";
    private String zzn = "";

    static {
        zzih zzihVar = new zzih();
        zza = zzihVar;
        zzec.zzS(zzih.class, zzihVar);
    }

    private zzih() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec
    protected final Object zzg(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            zzeg zzegVar = zzij.zza;
            zzeg zzegVar2 = zzif.zza;
            zzeg zzegVar3 = zzii.zza;
            return zzR(zza, "\u0001\t\u0000\u0001\u0001\t\t\u0000\u0000\u0000\u0001ဇ\u0000\u0002ဌ\u0001\u0003ဇ\u0002\u0004ဌ\u0003\u0005ဌ\u0004\u0006ဌ\u0005\u0007ဌ\u0006\bဈ\u0007\tဈ\b", new Object[]{"zze", "zzf", "zzg", zzegVar, "zzh", "zzi", zzegVar2, "zzj", zzegVar3, "zzk", zzegVar3, "zzl", zzegVar3, "zzm", "zzn"});
        }
        if (i2 == 3) {
            return new zzih();
        }
        zzhh zzhhVar = null;
        if (i2 == 4) {
            return new zzig(zzhhVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zza;
    }
}
