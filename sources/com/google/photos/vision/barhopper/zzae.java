package com.google.photos.vision.barhopper;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfm;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes2.dex */
public final class zzae extends zzec implements zzfm {
    private static final zzae zza;
    private int zze;
    private int zzf;
    private int zzg;
    private byte zzh = 2;

    static {
        zzae zzaeVar = new zzae();
        zza = zzaeVar;
        zzec.zzS(zzae.class, zzaeVar);
    }

    private zzae() {
    }

    public static zzad zzc() {
        return (zzad) zza.zzF();
    }

    static /* synthetic */ void zze(zzae zzaeVar, int i) {
        zzaeVar.zze |= 1;
        zzaeVar.zzf = i;
    }

    static /* synthetic */ void zzf(zzae zzaeVar, int i) {
        zzaeVar.zze |= 2;
        zzaeVar.zzg = i;
    }

    public final int zza() {
        return this.zzf;
    }

    public final int zzb() {
        return this.zzg;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec
    protected final Object zzg(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return Byte.valueOf(this.zzh);
        }
        if (i2 == 2) {
            return zzR(zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0002\u0001ᔄ\u0000\u0002ᔄ\u0001", new Object[]{"zze", "zzf", "zzg"});
        }
        if (i2 == 3) {
            return new zzae();
        }
        zza zzaVar = null;
        if (i2 == 4) {
            return new zzad(zzaVar);
        }
        if (i2 == 5) {
            return zza;
        }
        this.zzh = obj == null ? (byte) 0 : (byte) 1;
        return null;
    }
}
