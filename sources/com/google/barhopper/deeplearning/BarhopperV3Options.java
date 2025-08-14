package com.google.barhopper.deeplearning;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfm;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes3.dex */
public final class BarhopperV3Options extends zzec<BarhopperV3Options, zzk> implements zzfm {
    private static final BarhopperV3Options zza;
    private int zze;
    private zzi zzf;
    private zzn zzg;

    static {
        BarhopperV3Options barhopperV3Options = new BarhopperV3Options();
        zza = barhopperV3Options;
        zzec.zzS(BarhopperV3Options.class, barhopperV3Options);
    }

    private BarhopperV3Options() {
    }

    public static zzk zza() {
        return (zzk) zza.zzF();
    }

    static /* synthetic */ void zzc(BarhopperV3Options barhopperV3Options, zzi zziVar) {
        zziVar.getClass();
        barhopperV3Options.zzf = zziVar;
        barhopperV3Options.zze |= 1;
    }

    static /* synthetic */ void zzd(BarhopperV3Options barhopperV3Options, zzn zznVar) {
        zznVar.getClass();
        barhopperV3Options.zzg = zznVar;
        barhopperV3Options.zze |= 2;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec
    protected final Object zzg(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzR(zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001", new Object[]{"zze", "zzf", "zzg"});
        }
        if (i2 == 3) {
            return new BarhopperV3Options();
        }
        zzj zzjVar = null;
        if (i2 == 4) {
            return new zzk(zzjVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zza;
    }
}
