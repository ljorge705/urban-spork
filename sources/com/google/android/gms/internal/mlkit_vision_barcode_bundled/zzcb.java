package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.List;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes3.dex */
public final class zzcb extends zzec implements zzfm {
    private static final zzcb zza;
    private int zze;
    private int zzf;
    private zzf zzh;
    private byte zzi = 2;
    private zzek zzg = zzec.zzO();

    static {
        zzcb zzcbVar = new zzcb();
        zza = zzcbVar;
        zzec.zzS(zzcb.class, zzcbVar);
    }

    private zzcb() {
    }

    public final List zzb() {
        return this.zzg;
    }

    public final int zzc() {
        int iZza = zzca.zza(this.zzf);
        if (iZza == 0) {
            return 1;
        }
        return iZza;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec
    protected final Object zzg(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return Byte.valueOf(this.zzi);
        }
        if (i2 == 2) {
            return zzR(zza, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0001\u0001\u0001ဌ\u0000\u0002\u001a\u0003ᐉ\u0001", new Object[]{"zze", "zzf", zzbz.zza, "zzg", "zzh"});
        }
        if (i2 == 3) {
            return new zzcb();
        }
        zzbw zzbwVar = null;
        if (i2 == 4) {
            return new zzbx(zzbwVar);
        }
        if (i2 == 5) {
            return zza;
        }
        this.zzi = obj == null ? (byte) 0 : (byte) 1;
        return null;
    }
}
