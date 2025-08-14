package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes3.dex */
public final class zzjh extends zzdy implements zzfm {
    private static final zzjh zze;
    private byte zzf = 2;

    static {
        zzjh zzjhVar = new zzjh();
        zze = zzjhVar;
        zzec.zzS(zzjh.class, zzjhVar);
    }

    private zzjh() {
    }

    public static zzjh zzf() {
        return zze;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec
    protected final Object zzg(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return Byte.valueOf(this.zzf);
        }
        zzjf zzjfVar = null;
        if (i2 == 2) {
            return zzR(zze, "\u0003\u0000", null);
        }
        if (i2 == 3) {
            return new zzjh();
        }
        if (i2 == 4) {
            return new zzjg(zzjfVar);
        }
        if (i2 == 5) {
            return zze;
        }
        this.zzf = obj == null ? (byte) 0 : (byte) 1;
        return null;
    }
}
