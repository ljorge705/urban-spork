package com.google.barhopper.deeplearning;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeh;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfm;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhj;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes3.dex */
public final class zzi extends zzec implements zzfm {
    private static final zzi zza;
    private int zze;
    private zzf zzk;
    private zzhj zzn;
    private String zzf = "";
    private zzdb zzg = zzdb.zzb;
    private int zzh = 10;
    private float zzi = 0.5f;
    private float zzj = 0.05f;
    private zzeh zzl = zzL();
    private int zzm = 1;
    private int zzo = 320;
    private int zzp = 4;
    private int zzq = 2;

    static {
        zzi zziVar = new zzi();
        zza = zziVar;
        zzec.zzS(zzi.class, zziVar);
    }

    private zzi() {
    }

    public static zzh zza() {
        return (zzh) zza.zzF();
    }

    static /* synthetic */ void zzc(zzi zziVar, zzf zzfVar) {
        zzfVar.getClass();
        zziVar.zzk = zzfVar;
        zziVar.zze |= 32;
    }

    static /* synthetic */ void zzd(zzi zziVar, zzdb zzdbVar) {
        zzdbVar.getClass();
        zziVar.zze |= 2;
        zziVar.zzg = zzdbVar;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec
    protected final Object zzg(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzR(zza, "\u0001\f\u0000\u0001\u0001\f\f\u0000\u0001\u0000\u0001ဈ\u0000\u0002ည\u0001\u0003ဋ\u0002\u0004ခ\u0003\u0005ခ\u0004\u0006ဉ\u0005\u0007\u0013\bင\u0006\tဉ\u0007\nင\b\u000bင\t\fင\n", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", "zzm", "zzn", "zzo", "zzp", "zzq"});
        }
        if (i2 == 3) {
            return new zzi();
        }
        zzg zzgVar = null;
        if (i2 == 4) {
            return new zzh(zzgVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zza;
    }
}
