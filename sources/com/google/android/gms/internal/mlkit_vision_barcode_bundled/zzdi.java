package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes3.dex */
public abstract class zzdi extends zzcr {
    private static final Logger zzb = Logger.getLogger(zzdi.class.getName());
    private static final boolean zzc = zzgz.zzx();
    zzdj zza;

    private zzdi() {
    }

    /* synthetic */ zzdi(zzdf zzdfVar) {
    }

    static int zzA(zzfl zzflVar, zzgb zzgbVar) {
        zzck zzckVar = (zzck) zzflVar;
        int iZzB = zzckVar.zzB();
        if (iZzB == -1) {
            iZzB = zzgbVar.zza(zzckVar);
            zzckVar.zzD(iZzB);
        }
        return zzD(iZzB) + iZzB;
    }

    public static int zzB(String str) {
        int length;
        try {
            length = zzhe.zze(str);
        } catch (zzhd unused) {
            length = str.getBytes(zzel.zzb).length;
        }
        return zzD(length) + length;
    }

    public static int zzC(int i) {
        return zzD(i << 3);
    }

    public static int zzD(int i) {
        if ((i & (-128)) == 0) {
            return 1;
        }
        if ((i & (-16384)) == 0) {
            return 2;
        }
        if (((-2097152) & i) == 0) {
            return 3;
        }
        return (i & (-268435456)) == 0 ? 4 : 5;
    }

    public static int zzE(long j) {
        int i;
        if (((-128) & j) == 0) {
            return 1;
        }
        if (j < 0) {
            return 10;
        }
        if (((-34359738368L) & j) != 0) {
            j >>>= 28;
            i = 6;
        } else {
            i = 2;
        }
        if (((-2097152) & j) != 0) {
            i += 2;
            j >>>= 14;
        }
        return (j & (-16384)) != 0 ? i + 1 : i;
    }

    public static zzdi zzF(byte[] bArr) {
        return new zzdg(bArr, 0, bArr.length);
    }

    public static int zzt(byte[] bArr) {
        int length = bArr.length;
        return zzD(length) + length;
    }

    public static int zzu(zzdb zzdbVar) {
        int iZzd = zzdbVar.zzd();
        return zzD(iZzd) + iZzd;
    }

    @Deprecated
    static int zzv(int i, zzfl zzflVar, zzgb zzgbVar) {
        int iZzD = zzD(i << 3);
        int i2 = iZzD + iZzD;
        zzck zzckVar = (zzck) zzflVar;
        int iZzB = zzckVar.zzB();
        if (iZzB == -1) {
            iZzB = zzgbVar.zza(zzckVar);
            zzckVar.zzD(iZzB);
        }
        return i2 + iZzB;
    }

    @Deprecated
    public static int zzw(zzfl zzflVar) {
        return zzflVar.zzE();
    }

    public static int zzx(int i) {
        if (i >= 0) {
            return zzD(i);
        }
        return 10;
    }

    public static int zzy(zzet zzetVar) {
        int iZza = zzetVar.zza();
        return zzD(iZza) + iZza;
    }

    public static int zzz(zzfl zzflVar) {
        int iZzE = zzflVar.zzE();
        return zzD(iZzE) + iZzE;
    }

    public final void zzG() {
        if (zza() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    final void zzH(String str, zzhd zzhdVar) throws IOException {
        zzb.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", (Throwable) zzhdVar);
        byte[] bytes = str.getBytes(zzel.zzb);
        try {
            int length = bytes.length;
            zzq(length);
            zzl(bytes, 0, length);
        } catch (zzdh e) {
            throw e;
        } catch (IndexOutOfBoundsException e2) {
            throw new zzdh(e2);
        }
    }

    public abstract int zza();

    public abstract void zzb(byte b) throws IOException;

    public abstract void zzd(int i, boolean z) throws IOException;

    public abstract void zze(int i, zzdb zzdbVar) throws IOException;

    public abstract void zzf(int i, int i2) throws IOException;

    public abstract void zzg(int i) throws IOException;

    public abstract void zzh(int i, long j) throws IOException;

    public abstract void zzi(long j) throws IOException;

    public abstract void zzj(int i, int i2) throws IOException;

    public abstract void zzk(int i) throws IOException;

    public abstract void zzl(byte[] bArr, int i, int i2) throws IOException;

    public abstract void zzm(int i, String str) throws IOException;

    public abstract void zzo(int i, int i2) throws IOException;

    public abstract void zzp(int i, int i2) throws IOException;

    public abstract void zzq(int i) throws IOException;

    public abstract void zzr(int i, long j) throws IOException;

    public abstract void zzs(long j) throws IOException;
}
