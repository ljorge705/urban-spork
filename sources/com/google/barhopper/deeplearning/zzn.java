package com.google.barhopper.deeplearning;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfm;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes3.dex */
public final class zzn extends zzec implements zzfm {
    private static final zzn zza;
    private int zze;
    private String zzf = "";
    private zzdb zzg = zzdb.zzb;
    private String zzh = "";
    private zzdb zzi = zzdb.zzb;
    private float zzj = 0.25f;
    private float zzk = 0.25f;
    private float zzl = 0.5f;
    private float zzm = 0.85f;
    private int zzn = 1;

    static {
        zzn zznVar = new zzn();
        zza = zznVar;
        zzec.zzS(zzn.class, zznVar);
    }

    private zzn() {
    }

    public static zzm zza() {
        return (zzm) zza.zzF();
    }

    static /* synthetic */ void zzc(zzn zznVar, zzdb zzdbVar) {
        zzdbVar.getClass();
        zznVar.zze |= 2;
        zznVar.zzg = zzdbVar;
    }

    static /* synthetic */ void zzd(zzn zznVar, zzdb zzdbVar) {
        zzdbVar.getClass();
        zznVar.zze |= 8;
        zznVar.zzi = zzdbVar;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec
    protected final Object zzg(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzR(zza, "\u0001\t\u0000\u0001\u0001\t\t\u0000\u0000\u0000\u0001ဈ\u0000\u0002ည\u0001\u0003ဈ\u0002\u0004ည\u0003\u0005ခ\u0004\u0006ခ\u0005\u0007ခ\u0006\bခ\u0007\tင\b", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", "zzm", "zzn"});
        }
        if (i2 == 3) {
            return new zzn();
        }
        zzl zzlVar = null;
        if (i2 == 4) {
            return new zzm(zzlVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zza;
    }
}
