package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Locale;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes3.dex */
public abstract class zzdb implements Iterable, Serializable {
    private static final Comparator zza;
    public static final zzdb zzb = new zzcz(zzel.zzd);
    private static final zzda zzd;
    private int zzc = 0;

    static {
        int i = zzcn.zza;
        zzd = new zzda(null);
        zza = new zzcu();
    }

    zzdb() {
    }

    public static zzdb zzr(byte[] bArr, int i, int i2) {
        zzo(i, i + i2, bArr.length);
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        return new zzcz(bArr2);
    }

    public static zzdb zzs(String str) {
        return new zzcz(str.getBytes(zzel.zzb));
    }

    public static zzdb zzt(InputStream inputStream) throws IOException {
        ArrayList arrayList = new ArrayList();
        int iMin = 256;
        while (true) {
            byte[] bArr = new byte[iMin];
            int i = 0;
            while (i < iMin) {
                int i2 = inputStream.read(bArr, i, iMin - i);
                if (i2 == -1) {
                    break;
                }
                i += i2;
            }
            zzdb zzdbVarZzr = i == 0 ? null : zzr(bArr, 0, i);
            if (zzdbVarZzr == null) {
                break;
            }
            arrayList.add(zzdbVarZzr);
            iMin = Math.min(iMin + iMin, 8192);
        }
        int size = arrayList.size();
        return size == 0 ? zzb : zzc(arrayList.iterator(), size);
    }

    static void zzw(int i, int i2) {
        if (((i2 - (i + 1)) | i) < 0) {
            if (i < 0) {
                StringBuilder sb = new StringBuilder(22);
                sb.append("Index < 0: ");
                sb.append(i);
                throw new ArrayIndexOutOfBoundsException(sb.toString());
            }
            StringBuilder sb2 = new StringBuilder(40);
            sb2.append("Index > length: ");
            sb2.append(i);
            sb2.append(", ");
            sb2.append(i2);
            throw new ArrayIndexOutOfBoundsException(sb2.toString());
        }
    }

    public abstract boolean equals(Object obj);

    public final int hashCode() {
        int iZzi = this.zzc;
        if (iZzi == 0) {
            int iZzd = zzd();
            iZzi = zzi(iZzd, 0, iZzd);
            if (iZzi == 0) {
                iZzi = 1;
            }
            this.zzc = iZzi;
        }
        return iZzi;
    }

    public final String toString() {
        Locale locale = Locale.ROOT;
        Object[] objArr = new Object[3];
        objArr[0] = Integer.toHexString(System.identityHashCode(this));
        objArr[1] = Integer.valueOf(zzd());
        objArr[2] = zzd() <= 50 ? zzgn.zza(this) : zzgn.zza(zzk(0, 47)).concat("...");
        return String.format(locale, "<ByteString@%s size=%d contents=\"%s\">", objArr);
    }

    public abstract byte zza(int i);

    abstract byte zzb(int i);

    public abstract int zzd();

    protected abstract void zze(byte[] bArr, int i, int i2, int i3);

    protected abstract int zzf();

    protected abstract boolean zzh();

    protected abstract int zzi(int i, int i2, int i3);

    protected abstract int zzj(int i, int i2, int i3);

    public abstract zzdb zzk(int i, int i2);

    protected abstract String zzl(Charset charset);

    abstract void zzm(zzcr zzcrVar) throws IOException;

    public abstract boolean zzn();

    protected final int zzp() {
        return this.zzc;
    }

    @Override // java.lang.Iterable
    /* renamed from: zzq, reason: merged with bridge method [inline-methods] */
    public zzcx iterator() {
        return new zzcs(this);
    }

    public final String zzu(Charset charset) {
        return zzd() == 0 ? "" : zzl(charset);
    }

    public final String zzv() {
        return zzu(zzel.zzb);
    }

    @Deprecated
    public final void zzx(byte[] bArr, int i, int i2, int i3) {
        zzo(0, i3, zzd());
        zzo(i2, i2 + i3, bArr.length);
        if (i3 > 0) {
            zze(bArr, 0, i2, i3);
        }
    }

    public final byte[] zzy() {
        int iZzd = zzd();
        if (iZzd == 0) {
            return zzel.zzd;
        }
        byte[] bArr = new byte[iZzd];
        zze(bArr, 0, 0, iZzd);
        return bArr;
    }

    private static zzdb zzc(Iterator it, int i) {
        if (i <= 0) {
            throw new IllegalArgumentException(String.format("length (%s) must be >= 1", Integer.valueOf(i)));
        }
        if (i == 1) {
            return (zzdb) it.next();
        }
        int i2 = i >>> 1;
        zzdb zzdbVarZzc = zzc(it, i2);
        zzdb zzdbVarZzc2 = zzc(it, i - i2);
        if (Integer.MAX_VALUE - zzdbVarZzc.zzd() >= zzdbVarZzc2.zzd()) {
            return zzga.zzA(zzdbVarZzc, zzdbVarZzc2);
        }
        int iZzd = zzdbVarZzc.zzd();
        int iZzd2 = zzdbVarZzc2.zzd();
        StringBuilder sb = new StringBuilder(53);
        sb.append("ByteString would be too long: ");
        sb.append(iZzd);
        sb.append("+");
        sb.append(iZzd2);
        throw new IllegalArgumentException(sb.toString());
    }

    static int zzo(int i, int i2, int i3) {
        int i4 = i2 - i;
        if ((i | i2 | i4 | (i3 - i2)) >= 0) {
            return i4;
        }
        if (i < 0) {
            StringBuilder sb = new StringBuilder(32);
            sb.append("Beginning index: ");
            sb.append(i);
            sb.append(" < 0");
            throw new IndexOutOfBoundsException(sb.toString());
        }
        if (i2 < i) {
            StringBuilder sb2 = new StringBuilder(66);
            sb2.append("Beginning index larger than ending index: ");
            sb2.append(i);
            sb2.append(", ");
            sb2.append(i2);
            throw new IndexOutOfBoundsException(sb2.toString());
        }
        StringBuilder sb3 = new StringBuilder(37);
        sb3.append("End index: ");
        sb3.append(i2);
        sb3.append(" >= ");
        sb3.append(i3);
        throw new IndexOutOfBoundsException(sb3.toString());
    }
}
