package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import com.google.common.base.Ascii;
import java.io.IOException;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes3.dex */
final class zzcp {
    static int zza(byte[] bArr, int i, zzco zzcoVar) throws zzen {
        int iZzj = zzj(bArr, i, zzcoVar);
        int i2 = zzcoVar.zza;
        if (i2 < 0) {
            throw zzen.zzd();
        }
        if (i2 > bArr.length - iZzj) {
            throw zzen.zzg();
        }
        if (i2 == 0) {
            zzcoVar.zzc = zzdb.zzb;
            return iZzj;
        }
        zzcoVar.zzc = zzdb.zzr(bArr, iZzj, i2);
        return iZzj + i2;
    }

    static int zzb(byte[] bArr, int i) {
        return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
    }

    static int zzc(zzgb zzgbVar, byte[] bArr, int i, int i2, int i3, zzco zzcoVar) throws IOException {
        zzfo zzfoVar = (zzfo) zzgbVar;
        Object objZze = zzfoVar.zze();
        int iZzc = zzfoVar.zzc(objZze, bArr, i, i2, i3, zzcoVar);
        zzfoVar.zzf(objZze);
        zzcoVar.zzc = objZze;
        return iZzc;
    }

    static int zzd(zzgb zzgbVar, byte[] bArr, int i, int i2, zzco zzcoVar) throws IOException {
        int iZzk = i + 1;
        int i3 = bArr[i];
        if (i3 < 0) {
            iZzk = zzk(i3, bArr, iZzk, zzcoVar);
            i3 = zzcoVar.zza;
        }
        int i4 = iZzk;
        if (i3 < 0 || i3 > i2 - i4) {
            throw zzen.zzg();
        }
        Object objZze = zzgbVar.zze();
        int i5 = i3 + i4;
        zzgbVar.zzh(objZze, bArr, i4, i5, zzcoVar);
        zzgbVar.zzf(objZze);
        zzcoVar.zzc = objZze;
        return i5;
    }

    static int zze(zzgb zzgbVar, int i, byte[] bArr, int i2, int i3, zzek zzekVar, zzco zzcoVar) throws IOException {
        int iZzd = zzd(zzgbVar, bArr, i2, i3, zzcoVar);
        zzekVar.add(zzcoVar.zzc);
        while (iZzd < i3) {
            int iZzj = zzj(bArr, iZzd, zzcoVar);
            if (i != zzcoVar.zza) {
                break;
            }
            iZzd = zzd(zzgbVar, bArr, iZzj, i3, zzcoVar);
            zzekVar.add(zzcoVar.zzc);
        }
        return iZzd;
    }

    static int zzf(byte[] bArr, int i, zzek zzekVar, zzco zzcoVar) throws IOException {
        zzed zzedVar = (zzed) zzekVar;
        int iZzj = zzj(bArr, i, zzcoVar);
        int i2 = zzcoVar.zza + iZzj;
        while (iZzj < i2) {
            iZzj = zzj(bArr, iZzj, zzcoVar);
            zzedVar.zzg(zzcoVar.zza);
        }
        if (iZzj == i2) {
            return iZzj;
        }
        throw zzen.zzg();
    }

    static int zzg(byte[] bArr, int i, zzco zzcoVar) throws zzen {
        int iZzj = zzj(bArr, i, zzcoVar);
        int i2 = zzcoVar.zza;
        if (i2 < 0) {
            throw zzen.zzd();
        }
        if (i2 == 0) {
            zzcoVar.zzc = "";
            return iZzj;
        }
        zzcoVar.zzc = new String(bArr, iZzj, i2, zzel.zzb);
        return iZzj + i2;
    }

    static int zzh(byte[] bArr, int i, zzco zzcoVar) throws zzen {
        int iZzj = zzj(bArr, i, zzcoVar);
        int i2 = zzcoVar.zza;
        if (i2 < 0) {
            throw zzen.zzd();
        }
        if (i2 == 0) {
            zzcoVar.zzc = "";
            return iZzj;
        }
        zzcoVar.zzc = zzhe.zzg(bArr, iZzj, i2);
        return iZzj + i2;
    }

    static int zzi(int i, byte[] bArr, int i2, int i3, zzgq zzgqVar, zzco zzcoVar) throws zzen {
        if ((i >>> 3) == 0) {
            throw zzen.zzb();
        }
        int i4 = i & 7;
        if (i4 == 0) {
            int iZzm = zzm(bArr, i2, zzcoVar);
            zzgqVar.zzh(i, Long.valueOf(zzcoVar.zzb));
            return iZzm;
        }
        if (i4 == 1) {
            zzgqVar.zzh(i, Long.valueOf(zzo(bArr, i2)));
            return i2 + 8;
        }
        if (i4 == 2) {
            int iZzj = zzj(bArr, i2, zzcoVar);
            int i5 = zzcoVar.zza;
            if (i5 < 0) {
                throw zzen.zzd();
            }
            if (i5 > bArr.length - iZzj) {
                throw zzen.zzg();
            }
            if (i5 == 0) {
                zzgqVar.zzh(i, zzdb.zzb);
            } else {
                zzgqVar.zzh(i, zzdb.zzr(bArr, iZzj, i5));
            }
            return iZzj + i5;
        }
        if (i4 != 3) {
            if (i4 != 5) {
                throw zzen.zzb();
            }
            zzgqVar.zzh(i, Integer.valueOf(zzb(bArr, i2)));
            return i2 + 4;
        }
        int i6 = (i & (-8)) | 4;
        zzgq zzgqVarZze = zzgq.zze();
        int i7 = 0;
        while (true) {
            if (i2 >= i3) {
                break;
            }
            int iZzj2 = zzj(bArr, i2, zzcoVar);
            int i8 = zzcoVar.zza;
            if (i8 == i6) {
                i7 = i8;
                i2 = iZzj2;
                break;
            }
            i7 = i8;
            i2 = zzi(i8, bArr, iZzj2, i3, zzgqVarZze, zzcoVar);
        }
        if (i2 > i3 || i7 != i6) {
            throw zzen.zze();
        }
        zzgqVar.zzh(i, zzgqVarZze);
        return i2;
    }

    static int zzj(byte[] bArr, int i, zzco zzcoVar) {
        int i2 = i + 1;
        byte b = bArr[i];
        if (b < 0) {
            return zzk(b, bArr, i2, zzcoVar);
        }
        zzcoVar.zza = b;
        return i2;
    }

    static int zzk(int i, byte[] bArr, int i2, zzco zzcoVar) {
        int i3 = i & 127;
        int i4 = i2 + 1;
        byte b = bArr[i2];
        if (b >= 0) {
            zzcoVar.zza = i3 | (b << 7);
            return i4;
        }
        int i5 = i3 | ((b & Byte.MAX_VALUE) << 7);
        int i6 = i2 + 2;
        byte b2 = bArr[i4];
        if (b2 >= 0) {
            zzcoVar.zza = i5 | (b2 << 14);
            return i6;
        }
        int i7 = i5 | ((b2 & Byte.MAX_VALUE) << 14);
        int i8 = i2 + 3;
        byte b3 = bArr[i6];
        if (b3 >= 0) {
            zzcoVar.zza = i7 | (b3 << Ascii.NAK);
            return i8;
        }
        int i9 = i7 | ((b3 & Byte.MAX_VALUE) << 21);
        int i10 = i2 + 4;
        byte b4 = bArr[i8];
        if (b4 >= 0) {
            zzcoVar.zza = i9 | (b4 << 28);
            return i10;
        }
        int i11 = i9 | ((b4 & Byte.MAX_VALUE) << 28);
        while (true) {
            int i12 = i10 + 1;
            if (bArr[i10] >= 0) {
                zzcoVar.zza = i11;
                return i12;
            }
            i10 = i12;
        }
    }

    static int zzl(int i, byte[] bArr, int i2, int i3, zzek zzekVar, zzco zzcoVar) {
        zzed zzedVar = (zzed) zzekVar;
        int iZzj = zzj(bArr, i2, zzcoVar);
        zzedVar.zzg(zzcoVar.zza);
        while (iZzj < i3) {
            int iZzj2 = zzj(bArr, iZzj, zzcoVar);
            if (i != zzcoVar.zza) {
                break;
            }
            iZzj = zzj(bArr, iZzj2, zzcoVar);
            zzedVar.zzg(zzcoVar.zza);
        }
        return iZzj;
    }

    static int zzm(byte[] bArr, int i, zzco zzcoVar) {
        int i2 = i + 1;
        long j = bArr[i];
        if (j >= 0) {
            zzcoVar.zzb = j;
            return i2;
        }
        int i3 = i + 2;
        byte b = bArr[i2];
        long j2 = (j & 127) | ((b & Byte.MAX_VALUE) << 7);
        int i4 = 7;
        while (b < 0) {
            int i5 = i3 + 1;
            i4 += 7;
            j2 |= (r10 & Byte.MAX_VALUE) << i4;
            b = bArr[i3];
            i3 = i5;
        }
        zzcoVar.zzb = j2;
        return i3;
    }

    static long zzo(byte[] bArr, int i) {
        return ((bArr[i + 7] & 255) << 56) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 3] & 255) << 24) | ((bArr[i + 4] & 255) << 32) | ((bArr[i + 5] & 255) << 40) | ((bArr[i + 6] & 255) << 48);
    }

    static int zzn(int i, byte[] bArr, int i2, int i3, zzco zzcoVar) throws zzen {
        if ((i >>> 3) == 0) {
            throw zzen.zzb();
        }
        int i4 = i & 7;
        if (i4 == 0) {
            return zzm(bArr, i2, zzcoVar);
        }
        if (i4 == 1) {
            return i2 + 8;
        }
        if (i4 == 2) {
            return zzj(bArr, i2, zzcoVar) + zzcoVar.zza;
        }
        if (i4 != 3) {
            if (i4 == 5) {
                return i2 + 4;
            }
            throw zzen.zzb();
        }
        int i5 = (i & (-8)) | 4;
        int i6 = 0;
        while (i2 < i3) {
            i2 = zzj(bArr, i2, zzcoVar);
            i6 = zzcoVar.zza;
            if (i6 == i5) {
                break;
            }
            i2 = zzn(i6, bArr, i2, i3, zzcoVar);
        }
        if (i2 > i3 || i6 != i5) {
            throw zzen.zze();
        }
        return i2;
    }
}
