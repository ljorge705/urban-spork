package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes3.dex */
public final class zzv extends zzec implements zzfm {
    public static final zzea zza;
    private static final zzv zze;
    private int zzf;
    private long zzg;
    private long zzh;
    private zzjh zzi;
    private byte zzj = 2;

    static {
        zzv zzvVar = new zzv();
        zze = zzvVar;
        zzec.zzS(zzv.class, zzvVar);
        zza = zzec.zzH(zzjh.zzf(), zzvVar, zzvVar, null, 13258261, zzhf.MESSAGE, zzv.class);
    }

    private zzv() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec
    protected final Object zzg(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return Byte.valueOf(this.zzj);
        }
        if (i2 == 2) {
            return zzR(zze, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0003\u0001ᔅ\u0000\u0002ᔅ\u0001\u0003ᐉ\u0002", new Object[]{"zzf", "zzg", "zzh", "zzi"});
        }
        if (i2 == 3) {
            return new zzv();
        }
        zzt zztVar = null;
        if (i2 == 4) {
            return new zzu(zztVar);
        }
        if (i2 == 5) {
            return zze;
        }
        this.zzj = obj == null ? (byte) 0 : (byte) 1;
        return null;
    }
}
