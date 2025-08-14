package com.google.barhopper.deeplearning;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeh;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfm;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes3.dex */
public final class zzc extends zzec implements zzfm {
    private static final zzc zza;
    private int zze;
    private zzeh zzf = zzL();
    private zzeh zzg = zzL();
    private int zzh;
    private int zzi;
    private int zzj;
    private int zzk;

    static {
        zzc zzcVar = new zzc();
        zza = zzcVar;
        zzec.zzS(zzc.class, zzcVar);
    }

    private zzc() {
    }

    public static zzb zza() {
        return (zzb) zza.zzF();
    }

    static /* synthetic */ void zzc(zzc zzcVar, int i) {
        zzcVar.zze |= 2;
        zzcVar.zzi = i;
    }

    static /* synthetic */ void zzd(zzc zzcVar, float f) {
        zzeh zzehVar = zzcVar.zzf;
        if (!zzehVar.zzc()) {
            zzcVar.zzf = zzec.zzM(zzehVar);
        }
        zzcVar.zzf.zzg(f);
    }

    static /* synthetic */ void zze(zzc zzcVar, float f) {
        zzeh zzehVar = zzcVar.zzg;
        if (!zzehVar.zzc()) {
            zzcVar.zzg = zzec.zzM(zzehVar);
        }
        zzcVar.zzg.zzg(f);
    }

    static /* synthetic */ void zzf(zzc zzcVar, int i) {
        zzcVar.zze |= 1;
        zzcVar.zzh = i;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec
    protected final Object zzg(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzR(zza, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0002\u0000\u0001\u0013\u0002\u0013\u0003ဋ\u0000\u0004ဋ\u0001\u0005ဋ\u0002\u0006ဋ\u0003", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk"});
        }
        if (i2 == 3) {
            return new zzc();
        }
        zza zzaVar = null;
        if (i2 == 4) {
            return new zzb(zzaVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zza;
    }
}
