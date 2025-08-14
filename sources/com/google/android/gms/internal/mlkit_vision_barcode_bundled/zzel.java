package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes3.dex */
public final class zzel {
    static final Charset zza = Charset.forName("US-ASCII");
    static final Charset zzb = Charset.forName("UTF-8");
    static final Charset zzc = Charset.forName("ISO-8859-1");
    public static final byte[] zzd;
    public static final ByteBuffer zze;
    public static final zzde zzf;

    static {
        byte[] bArr = new byte[0];
        zzd = bArr;
        zze = ByteBuffer.wrap(bArr);
        int i = zzde.zza;
        zzdd zzddVar = new zzdd(bArr, 0, 0, false, null);
        try {
            zzddVar.zza(0);
            zzf = zzddVar;
        } catch (zzen e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static int zza(boolean z) {
        return z ? 1231 : 1237;
    }

    public static int zzb(byte[] bArr) {
        int length = bArr.length;
        int iZzd = zzd(length, bArr, 0, length);
        if (iZzd == 0) {
            return 1;
        }
        return iZzd;
    }

    public static int zzc(long j) {
        return (int) (j ^ (j >>> 32));
    }

    static int zzd(int i, byte[] bArr, int i2, int i3) {
        for (int i4 = i2; i4 < i2 + i3; i4++) {
            i = (i * 31) + bArr[i4];
        }
        return i;
    }

    static Object zze(Object obj) {
        obj.getClass();
        return obj;
    }

    static Object zzf(Object obj, String str) {
        if (obj != null) {
            return obj;
        }
        throw new NullPointerException(str);
    }

    static Object zzg(Object obj, Object obj2) {
        return ((zzfl) obj).zzV().zzg((zzfl) obj2).zzm();
    }

    public static String zzh(byte[] bArr) {
        return new String(bArr, zzb);
    }

    static boolean zzi(zzfl zzflVar) {
        if (!(zzflVar instanceof zzcl)) {
            return false;
        }
        throw null;
    }

    public static boolean zzj(byte[] bArr) {
        return zzhe.zzh(bArr);
    }
}
