package com.google.barhopper.deeplearning;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzek;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfm;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes3.dex */
public final class zzf extends zzec implements zzfm {
    private static final zzf zza;
    private zzek zze = zzO();

    static {
        zzf zzfVar = new zzf();
        zza = zzfVar;
        zzec.zzS(zzf.class, zzfVar);
    }

    private zzf() {
    }

    public static zze zza() {
        return (zze) zza.zzF();
    }

    static /* synthetic */ void zzc(zzf zzfVar, zzc zzcVar) {
        zzcVar.getClass();
        zzek zzekVar = zzfVar.zze;
        if (!zzekVar.zzc()) {
            zzfVar.zze = zzec.zzP(zzekVar);
        }
        zzfVar.zze.add(zzcVar);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec
    protected final Object zzg(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzR(zza, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"zze", zzc.class});
        }
        if (i2 == 3) {
            return new zzf();
        }
        zzd zzdVar = null;
        if (i2 == 4) {
            return new zze(zzdVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zza;
    }
}
