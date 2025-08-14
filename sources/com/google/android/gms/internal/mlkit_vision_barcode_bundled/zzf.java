package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes3.dex */
public final class zzf extends zzec implements zzfm {
    public static final zzea zza;
    private static final zzf zze;
    private int zzf;
    private zzjh zzk;
    private zzf zzl;
    private zzy zzm;
    private byte zzn = 2;
    private String zzg = "";
    private zzek zzh = zzO();
    private zzek zzi = zzO();
    private zzek zzj = zzO();

    static {
        zzf zzfVar = new zzf();
        zze = zzfVar;
        zzec.zzS(zzf.class, zzfVar);
        zza = zzec.zzH(zzjh.zzf(), zzfVar, zzfVar, null, 12208774, zzhf.MESSAGE, zzf.class);
    }

    private zzf() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec
    protected final Object zzg(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return Byte.valueOf(this.zzn);
        }
        if (i2 == 2) {
            return zzR(zze, "\u0001\u0007\u0000\u0001\u0002Ǵ\u0007\u0000\u0003\u0004\u0002Л\u0005Л\u0006\u001b\bᐉ\u0001\nဈ\u0000\u000bᐉ\u0002Ǵဉ\u0003", new Object[]{"zzf", "zzh", zzj.class, "zzj", zzj.class, "zzi", zzm.class, "zzk", "zzg", "zzl", "zzm"});
        }
        if (i2 == 3) {
            return new zzf();
        }
        zzd zzdVar = null;
        if (i2 == 4) {
            return new zze(zzdVar);
        }
        if (i2 == 5) {
            return zze;
        }
        this.zzn = obj == null ? (byte) 0 : (byte) 1;
        return null;
    }
}
