package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import sun.misc.Unsafe;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes3.dex */
final class zzfo<T> implements zzgb<T> {
    private static final int[] zza = new int[0];
    private static final Unsafe zzb = zzgz.zzg();
    private final int[] zzc;
    private final Object[] zzd;
    private final int zze;
    private final int zzf;
    private final zzfl zzg;
    private final boolean zzh;
    private final boolean zzi;
    private final int[] zzj;
    private final int zzk;
    private final int zzl;
    private final zzez zzm;
    private final zzgp zzn;
    private final zzdo zzo;
    private final zzfr zzp;
    private final zzfg zzq;

    private zzfo(int[] iArr, Object[] objArr, int i, int i2, zzfl zzflVar, boolean z, boolean z2, int[] iArr2, int i3, int i4, zzfr zzfrVar, zzez zzezVar, zzgp zzgpVar, zzdo zzdoVar, zzfg zzfgVar, byte[] bArr) {
        this.zzc = iArr;
        this.zzd = objArr;
        this.zze = i;
        this.zzf = i2;
        this.zzi = z;
        boolean z3 = false;
        if (zzdoVar != null && zzdoVar.zzf(zzflVar)) {
            z3 = true;
        }
        this.zzh = z3;
        this.zzj = iArr2;
        this.zzk = i3;
        this.zzl = i4;
        this.zzp = zzfrVar;
        this.zzm = zzezVar;
        this.zzn = zzgpVar;
        this.zzo = zzdoVar;
        this.zzg = zzflVar;
        this.zzq = zzfgVar;
    }

    private static int zzA(int i) {
        return (i >>> 20) & 255;
    }

    private final int zzB(int i) {
        return this.zzc[i + 1];
    }

    private static long zzC(Object obj, long j) {
        return ((Long) zzgz.zzf(obj, j)).longValue();
    }

    private final zzeg zzD(int i) {
        int i2 = i / 3;
        return (zzeg) this.zzd[i2 + i2 + 1];
    }

    private final zzgb zzE(int i) {
        int i2 = i / 3;
        int i3 = i2 + i2;
        zzgb zzgbVar = (zzgb) this.zzd[i3];
        if (zzgbVar != null) {
            return zzgbVar;
        }
        zzgb zzgbVarZzb = zzfu.zza().zzb((Class) this.zzd[i3 + 1]);
        this.zzd[i3] = zzgbVarZzb;
        return zzgbVarZzb;
    }

    private final Object zzF(int i) {
        int i2 = i / 3;
        return this.zzd[i2 + i2];
    }

    private static Field zzG(Class cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            String name = cls.getName();
            String string = Arrays.toString(declaredFields);
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 40 + String.valueOf(name).length() + String.valueOf(string).length());
            sb.append("Field ");
            sb.append(str);
            sb.append(" for ");
            sb.append(name);
            sb.append(" not found. Known fields are ");
            sb.append(string);
            throw new RuntimeException(sb.toString());
        }
    }

    private final void zzH(Object obj, Object obj2, int i) {
        long jZzB = zzB(i) & 1048575;
        if (zzM(obj2, i)) {
            Object objZzf = zzgz.zzf(obj, jZzB);
            Object objZzf2 = zzgz.zzf(obj2, jZzB);
            if (objZzf != null && objZzf2 != null) {
                zzgz.zzs(obj, jZzB, zzel.zzg(objZzf, objZzf2));
                zzJ(obj, i);
            } else if (objZzf2 != null) {
                zzgz.zzs(obj, jZzB, objZzf2);
                zzJ(obj, i);
            }
        }
    }

    private final void zzI(Object obj, Object obj2, int i) {
        int iZzB = zzB(i);
        int i2 = this.zzc[i];
        long j = iZzB & 1048575;
        if (zzP(obj2, i2, i)) {
            Object objZzf = zzP(obj, i2, i) ? zzgz.zzf(obj, j) : null;
            Object objZzf2 = zzgz.zzf(obj2, j);
            if (objZzf != null && objZzf2 != null) {
                zzgz.zzs(obj, j, zzel.zzg(objZzf, objZzf2));
                zzK(obj, i2, i);
            } else if (objZzf2 != null) {
                zzgz.zzs(obj, j, objZzf2);
                zzK(obj, i2, i);
            }
        }
    }

    private final void zzJ(Object obj, int i) {
        int iZzy = zzy(i);
        long j = 1048575 & iZzy;
        if (j == 1048575) {
            return;
        }
        zzgz.zzq(obj, j, (1 << (iZzy >>> 20)) | zzgz.zzc(obj, j));
    }

    private final void zzK(Object obj, int i, int i2) {
        zzgz.zzq(obj, zzy(i2) & 1048575, i);
    }

    private final boolean zzL(Object obj, Object obj2, int i) {
        return zzM(obj, i) == zzM(obj2, i);
    }

    private final boolean zzM(Object obj, int i) {
        int iZzy = zzy(i);
        long j = iZzy & 1048575;
        if (j != 1048575) {
            return (zzgz.zzc(obj, j) & (1 << (iZzy >>> 20))) != 0;
        }
        int iZzB = zzB(i);
        long j2 = iZzB & 1048575;
        switch (zzA(iZzB)) {
            case 0:
                return Double.doubleToRawLongBits(zzgz.zza(obj, j2)) != 0;
            case 1:
                return Float.floatToRawIntBits(zzgz.zzb(obj, j2)) != 0;
            case 2:
                return zzgz.zzd(obj, j2) != 0;
            case 3:
                return zzgz.zzd(obj, j2) != 0;
            case 4:
                return zzgz.zzc(obj, j2) != 0;
            case 5:
                return zzgz.zzd(obj, j2) != 0;
            case 6:
                return zzgz.zzc(obj, j2) != 0;
            case 7:
                return zzgz.zzw(obj, j2);
            case 8:
                Object objZzf = zzgz.zzf(obj, j2);
                if (objZzf instanceof String) {
                    return !((String) objZzf).isEmpty();
                }
                if (objZzf instanceof zzdb) {
                    return !zzdb.zzb.equals(objZzf);
                }
                throw new IllegalArgumentException();
            case 9:
                return zzgz.zzf(obj, j2) != null;
            case 10:
                return !zzdb.zzb.equals(zzgz.zzf(obj, j2));
            case 11:
                return zzgz.zzc(obj, j2) != 0;
            case 12:
                return zzgz.zzc(obj, j2) != 0;
            case 13:
                return zzgz.zzc(obj, j2) != 0;
            case 14:
                return zzgz.zzd(obj, j2) != 0;
            case 15:
                return zzgz.zzc(obj, j2) != 0;
            case 16:
                return zzgz.zzd(obj, j2) != 0;
            case 17:
                return zzgz.zzf(obj, j2) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    private final boolean zzN(Object obj, int i, int i2, int i3, int i4) {
        return i2 == 1048575 ? zzM(obj, i) : (i3 & i4) != 0;
    }

    private static boolean zzO(Object obj, int i, zzgb zzgbVar) {
        return zzgbVar.zzj(zzgz.zzf(obj, i & 1048575));
    }

    private final boolean zzP(Object obj, int i, int i2) {
        return zzgz.zzc(obj, (long) (zzy(i2) & 1048575)) == i;
    }

    private static boolean zzQ(Object obj, long j) {
        return ((Boolean) zzgz.zzf(obj, j)).booleanValue();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void zzR(java.lang.Object r17, com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj r18) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1334
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfo.zzR(java.lang.Object, com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj):void");
    }

    private final void zzS(zzdj zzdjVar, int i, Object obj, int i2) throws IOException {
        if (obj == null) {
            return;
        }
        throw null;
    }

    private static final void zzT(int i, Object obj, zzdj zzdjVar) throws IOException {
        if (obj instanceof String) {
            zzdjVar.zzG(i, (String) obj);
        } else {
            zzdjVar.zzd(i, (zzdb) obj);
        }
    }

    static zzgq zzd(Object obj) {
        zzec zzecVar = (zzec) obj;
        zzgq zzgqVar = zzecVar.zzc;
        if (zzgqVar != zzgq.zzc()) {
            return zzgqVar;
        }
        zzgq zzgqVarZze = zzgq.zze();
        zzecVar.zzc = zzgqVarZze;
        return zzgqVarZze;
    }

    static zzfo zzk(Class cls, zzfi zzfiVar, zzfr zzfrVar, zzez zzezVar, zzgp zzgpVar, zzdo zzdoVar, zzfg zzfgVar) {
        if (zzfiVar instanceof zzfw) {
            return zzl((zzfw) zzfiVar, zzfrVar, zzezVar, zzgpVar, zzdoVar, zzfgVar);
        }
        throw null;
    }

    /* JADX WARN: Removed duplicated region for block: B:123:0x025e  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0261  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0279  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x027c  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x0330  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x037f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfo zzl(com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfw r34, com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfr r35, com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzez r36, com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgp r37, com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdo r38, com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfg r39) {
        /*
            Method dump skipped, instructions count: 1020
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfo.zzl(com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfw, com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfr, com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzez, com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgp, com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdo, com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfg):com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfo");
    }

    private static double zzn(Object obj, long j) {
        return ((Double) zzgz.zzf(obj, j)).doubleValue();
    }

    private static float zzo(Object obj, long j) {
        return ((Float) zzgz.zzf(obj, j)).floatValue();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private final int zzp(Object obj) {
        int i;
        int iZzD;
        int iZzD2;
        int iZzD3;
        int iZzE;
        int iZzD4;
        int iZzx;
        int iZzD5;
        int iZzD6;
        int iZzd;
        int iZzD7;
        int i2;
        int iZzu;
        boolean z;
        int iZzd2;
        int iZzi;
        int iZzC;
        int iZzD8;
        int iZzD9;
        int iZzD10;
        int iZzD11;
        int iZzD12;
        int iZzE2;
        int iZzD13;
        int iZzd3;
        int iZzD14;
        int i3;
        Unsafe unsafe = zzb;
        int i4 = 1048575;
        int i5 = 1048575;
        int i6 = 0;
        int iZzD15 = 0;
        int i7 = 0;
        while (i6 < this.zzc.length) {
            int iZzB = zzB(i6);
            int[] iArr = this.zzc;
            int i8 = iArr[i6];
            int iZzA = zzA(iZzB);
            if (iZzA <= 17) {
                int i9 = iArr[i6 + 2];
                int i10 = i9 & i4;
                i = 1 << (i9 >>> 20);
                if (i10 != i5) {
                    i7 = unsafe.getInt(obj, i10);
                    i5 = i10;
                }
            } else {
                i = 0;
            }
            long j = iZzB & i4;
            switch (iZzA) {
                case 0:
                    if ((i7 & i) != 0) {
                        iZzD = zzdi.zzD(i8 << 3);
                        iZzD5 = iZzD + 8;
                        iZzD15 += iZzD5;
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if ((i7 & i) != 0) {
                        iZzD2 = zzdi.zzD(i8 << 3);
                        iZzD5 = iZzD2 + 4;
                        iZzD15 += iZzD5;
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if ((i7 & i) != 0) {
                        long j2 = unsafe.getLong(obj, j);
                        iZzD3 = zzdi.zzD(i8 << 3);
                        iZzE = zzdi.zzE(j2);
                        iZzD15 += iZzD3 + iZzE;
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if ((i7 & i) != 0) {
                        long j3 = unsafe.getLong(obj, j);
                        iZzD3 = zzdi.zzD(i8 << 3);
                        iZzE = zzdi.zzE(j3);
                        iZzD15 += iZzD3 + iZzE;
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if ((i7 & i) != 0) {
                        int i11 = unsafe.getInt(obj, j);
                        iZzD4 = zzdi.zzD(i8 << 3);
                        iZzx = zzdi.zzx(i11);
                        i2 = iZzD4 + iZzx;
                        iZzD15 += i2;
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if ((i7 & i) != 0) {
                        iZzD = zzdi.zzD(i8 << 3);
                        iZzD5 = iZzD + 8;
                        iZzD15 += iZzD5;
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if ((i7 & i) != 0) {
                        iZzD2 = zzdi.zzD(i8 << 3);
                        iZzD5 = iZzD2 + 4;
                        iZzD15 += iZzD5;
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if ((i7 & i) != 0) {
                        iZzD5 = zzdi.zzD(i8 << 3) + 1;
                        iZzD15 += iZzD5;
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if ((i7 & i) == 0) {
                        break;
                    } else {
                        Object object = unsafe.getObject(obj, j);
                        if (object instanceof zzdb) {
                            iZzD6 = zzdi.zzD(i8 << 3);
                            iZzd = ((zzdb) object).zzd();
                            iZzD7 = zzdi.zzD(iZzd);
                            i2 = iZzD6 + iZzD7 + iZzd;
                            iZzD15 += i2;
                            break;
                        } else {
                            iZzD4 = zzdi.zzD(i8 << 3);
                            iZzx = zzdi.zzB((String) object);
                            i2 = iZzD4 + iZzx;
                            iZzD15 += i2;
                        }
                    }
                case 9:
                    if ((i7 & i) != 0) {
                        iZzD5 = zzgd.zzo(i8, unsafe.getObject(obj, j), zzE(i6));
                        iZzD15 += iZzD5;
                        break;
                    } else {
                        break;
                    }
                case 10:
                    if ((i7 & i) != 0) {
                        zzdb zzdbVar = (zzdb) unsafe.getObject(obj, j);
                        iZzD6 = zzdi.zzD(i8 << 3);
                        iZzd = zzdbVar.zzd();
                        iZzD7 = zzdi.zzD(iZzd);
                        i2 = iZzD6 + iZzD7 + iZzd;
                        iZzD15 += i2;
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if ((i7 & i) != 0) {
                        int i12 = unsafe.getInt(obj, j);
                        iZzD4 = zzdi.zzD(i8 << 3);
                        iZzx = zzdi.zzD(i12);
                        i2 = iZzD4 + iZzx;
                        iZzD15 += i2;
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if ((i7 & i) != 0) {
                        int i13 = unsafe.getInt(obj, j);
                        iZzD4 = zzdi.zzD(i8 << 3);
                        iZzx = zzdi.zzx(i13);
                        i2 = iZzD4 + iZzx;
                        iZzD15 += i2;
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if ((i7 & i) != 0) {
                        iZzD2 = zzdi.zzD(i8 << 3);
                        iZzD5 = iZzD2 + 4;
                        iZzD15 += iZzD5;
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if ((i7 & i) != 0) {
                        iZzD = zzdi.zzD(i8 << 3);
                        iZzD5 = iZzD + 8;
                        iZzD15 += iZzD5;
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if ((i7 & i) != 0) {
                        int i14 = unsafe.getInt(obj, j);
                        iZzD4 = zzdi.zzD(i8 << 3);
                        iZzx = zzdi.zzD((i14 >> 31) ^ (i14 + i14));
                        i2 = iZzD4 + iZzx;
                        iZzD15 += i2;
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if ((i & i7) != 0) {
                        long j4 = unsafe.getLong(obj, j);
                        iZzD15 += zzdi.zzD(i8 << 3) + zzdi.zzE((j4 >> 63) ^ (j4 + j4));
                        break;
                    } else {
                        break;
                    }
                case 17:
                    if ((i7 & i) != 0) {
                        iZzD5 = zzdi.zzv(i8, (zzfl) unsafe.getObject(obj, j), zzE(i6));
                        iZzD15 += iZzD5;
                        break;
                    } else {
                        break;
                    }
                case 18:
                    iZzD5 = zzgd.zzh(i8, (List) unsafe.getObject(obj, j), false);
                    iZzD15 += iZzD5;
                    break;
                case 19:
                    iZzD5 = zzgd.zzf(i8, (List) unsafe.getObject(obj, j), false);
                    iZzD15 += iZzD5;
                    break;
                case 20:
                    iZzD5 = zzgd.zzm(i8, (List) unsafe.getObject(obj, j), false);
                    iZzD15 += iZzD5;
                    break;
                case 21:
                    iZzD5 = zzgd.zzx(i8, (List) unsafe.getObject(obj, j), false);
                    iZzD15 += iZzD5;
                    break;
                case 22:
                    iZzD5 = zzgd.zzk(i8, (List) unsafe.getObject(obj, j), false);
                    iZzD15 += iZzD5;
                    break;
                case 23:
                    iZzD5 = zzgd.zzh(i8, (List) unsafe.getObject(obj, j), false);
                    iZzD15 += iZzD5;
                    break;
                case 24:
                    iZzD5 = zzgd.zzf(i8, (List) unsafe.getObject(obj, j), false);
                    iZzD15 += iZzD5;
                    break;
                case 25:
                    iZzD5 = zzgd.zza(i8, (List) unsafe.getObject(obj, j), false);
                    iZzD15 += iZzD5;
                    break;
                case 26:
                    iZzu = zzgd.zzu(i8, (List) unsafe.getObject(obj, j));
                    iZzD15 += iZzu;
                    break;
                case 27:
                    iZzu = zzgd.zzp(i8, (List) unsafe.getObject(obj, j), zzE(i6));
                    iZzD15 += iZzu;
                    break;
                case 28:
                    iZzu = zzgd.zzc(i8, (List) unsafe.getObject(obj, j));
                    iZzD15 += iZzu;
                    break;
                case 29:
                    iZzu = zzgd.zzv(i8, (List) unsafe.getObject(obj, j), false);
                    iZzD15 += iZzu;
                    break;
                case 30:
                    z = false;
                    iZzd2 = zzgd.zzd(i8, (List) unsafe.getObject(obj, j), false);
                    iZzD15 += iZzd2;
                    break;
                case 31:
                    z = false;
                    iZzd2 = zzgd.zzf(i8, (List) unsafe.getObject(obj, j), false);
                    iZzD15 += iZzd2;
                    break;
                case 32:
                    z = false;
                    iZzd2 = zzgd.zzh(i8, (List) unsafe.getObject(obj, j), false);
                    iZzD15 += iZzd2;
                    break;
                case 33:
                    z = false;
                    iZzd2 = zzgd.zzq(i8, (List) unsafe.getObject(obj, j), false);
                    iZzD15 += iZzd2;
                    break;
                case 34:
                    z = false;
                    iZzd2 = zzgd.zzs(i8, (List) unsafe.getObject(obj, j), false);
                    iZzD15 += iZzd2;
                    break;
                case 35:
                    iZzi = zzgd.zzi((List) unsafe.getObject(obj, j));
                    if (iZzi > 0) {
                        iZzC = zzdi.zzC(i8);
                        iZzD8 = zzdi.zzD(iZzi);
                        iZzD9 = iZzC + iZzD8;
                        i3 = iZzD9 + iZzi;
                        iZzD15 += i3;
                    }
                    break;
                case 36:
                    iZzi = zzgd.zzg((List) unsafe.getObject(obj, j));
                    if (iZzi > 0) {
                        iZzC = zzdi.zzC(i8);
                        iZzD8 = zzdi.zzD(iZzi);
                        iZzD9 = iZzC + iZzD8;
                        i3 = iZzD9 + iZzi;
                        iZzD15 += i3;
                    }
                    break;
                case 37:
                    iZzi = zzgd.zzn((List) unsafe.getObject(obj, j));
                    if (iZzi > 0) {
                        iZzC = zzdi.zzC(i8);
                        iZzD8 = zzdi.zzD(iZzi);
                        iZzD9 = iZzC + iZzD8;
                        i3 = iZzD9 + iZzi;
                        iZzD15 += i3;
                    }
                    break;
                case 38:
                    iZzi = zzgd.zzy((List) unsafe.getObject(obj, j));
                    if (iZzi > 0) {
                        iZzC = zzdi.zzC(i8);
                        iZzD8 = zzdi.zzD(iZzi);
                        iZzD9 = iZzC + iZzD8;
                        i3 = iZzD9 + iZzi;
                        iZzD15 += i3;
                    }
                    break;
                case 39:
                    iZzi = zzgd.zzl((List) unsafe.getObject(obj, j));
                    if (iZzi > 0) {
                        iZzC = zzdi.zzC(i8);
                        iZzD8 = zzdi.zzD(iZzi);
                        iZzD9 = iZzC + iZzD8;
                        i3 = iZzD9 + iZzi;
                        iZzD15 += i3;
                    }
                    break;
                case 40:
                    iZzi = zzgd.zzi((List) unsafe.getObject(obj, j));
                    if (iZzi > 0) {
                        iZzC = zzdi.zzC(i8);
                        iZzD8 = zzdi.zzD(iZzi);
                        iZzD9 = iZzC + iZzD8;
                        i3 = iZzD9 + iZzi;
                        iZzD15 += i3;
                    }
                    break;
                case 41:
                    iZzi = zzgd.zzg((List) unsafe.getObject(obj, j));
                    if (iZzi > 0) {
                        iZzC = zzdi.zzC(i8);
                        iZzD8 = zzdi.zzD(iZzi);
                        iZzD9 = iZzC + iZzD8;
                        i3 = iZzD9 + iZzi;
                        iZzD15 += i3;
                    }
                    break;
                case 42:
                    iZzi = zzgd.zzb((List) unsafe.getObject(obj, j));
                    if (iZzi > 0) {
                        iZzC = zzdi.zzC(i8);
                        iZzD8 = zzdi.zzD(iZzi);
                        iZzD9 = iZzC + iZzD8;
                        i3 = iZzD9 + iZzi;
                        iZzD15 += i3;
                    }
                    break;
                case 43:
                    iZzi = zzgd.zzw((List) unsafe.getObject(obj, j));
                    if (iZzi > 0) {
                        iZzC = zzdi.zzC(i8);
                        iZzD8 = zzdi.zzD(iZzi);
                        iZzD9 = iZzC + iZzD8;
                        i3 = iZzD9 + iZzi;
                        iZzD15 += i3;
                    }
                    break;
                case 44:
                    iZzi = zzgd.zze((List) unsafe.getObject(obj, j));
                    if (iZzi > 0) {
                        iZzC = zzdi.zzC(i8);
                        iZzD8 = zzdi.zzD(iZzi);
                        iZzD9 = iZzC + iZzD8;
                        i3 = iZzD9 + iZzi;
                        iZzD15 += i3;
                    }
                    break;
                case 45:
                    iZzi = zzgd.zzg((List) unsafe.getObject(obj, j));
                    if (iZzi > 0) {
                        iZzC = zzdi.zzC(i8);
                        iZzD8 = zzdi.zzD(iZzi);
                        iZzD9 = iZzC + iZzD8;
                        i3 = iZzD9 + iZzi;
                        iZzD15 += i3;
                    }
                    break;
                case 46:
                    iZzi = zzgd.zzi((List) unsafe.getObject(obj, j));
                    if (iZzi > 0) {
                        iZzC = zzdi.zzC(i8);
                        iZzD8 = zzdi.zzD(iZzi);
                        iZzD9 = iZzC + iZzD8;
                        i3 = iZzD9 + iZzi;
                        iZzD15 += i3;
                    }
                    break;
                case 47:
                    iZzi = zzgd.zzr((List) unsafe.getObject(obj, j));
                    if (iZzi > 0) {
                        iZzC = zzdi.zzC(i8);
                        iZzD8 = zzdi.zzD(iZzi);
                        iZzD9 = iZzC + iZzD8;
                        i3 = iZzD9 + iZzi;
                        iZzD15 += i3;
                    }
                    break;
                case 48:
                    iZzi = zzgd.zzt((List) unsafe.getObject(obj, j));
                    if (iZzi > 0) {
                        iZzC = zzdi.zzC(i8);
                        iZzD8 = zzdi.zzD(iZzi);
                        iZzD9 = iZzC + iZzD8;
                        i3 = iZzD9 + iZzi;
                        iZzD15 += i3;
                    }
                    break;
                case 49:
                    iZzu = zzgd.zzj(i8, (List) unsafe.getObject(obj, j), zzE(i6));
                    iZzD15 += iZzu;
                    break;
                case 50:
                    zzfg.zza(i8, unsafe.getObject(obj, j), zzF(i6));
                    break;
                case 51:
                    if (zzP(obj, i8, i6)) {
                        iZzD10 = zzdi.zzD(i8 << 3);
                        iZzu = iZzD10 + 8;
                        iZzD15 += iZzu;
                    }
                    break;
                case 52:
                    if (zzP(obj, i8, i6)) {
                        iZzD11 = zzdi.zzD(i8 << 3);
                        iZzu = iZzD11 + 4;
                        iZzD15 += iZzu;
                    }
                    break;
                case 53:
                    if (zzP(obj, i8, i6)) {
                        long jZzC = zzC(obj, j);
                        iZzD12 = zzdi.zzD(i8 << 3);
                        iZzE2 = zzdi.zzE(jZzC);
                        iZzD15 += iZzD12 + iZzE2;
                    }
                    break;
                case 54:
                    if (zzP(obj, i8, i6)) {
                        long jZzC2 = zzC(obj, j);
                        iZzD12 = zzdi.zzD(i8 << 3);
                        iZzE2 = zzdi.zzE(jZzC2);
                        iZzD15 += iZzD12 + iZzE2;
                    }
                    break;
                case 55:
                    if (zzP(obj, i8, i6)) {
                        int iZzr = zzr(obj, j);
                        iZzD9 = zzdi.zzD(i8 << 3);
                        iZzi = zzdi.zzx(iZzr);
                        i3 = iZzD9 + iZzi;
                        iZzD15 += i3;
                    }
                    break;
                case 56:
                    if (zzP(obj, i8, i6)) {
                        iZzD10 = zzdi.zzD(i8 << 3);
                        iZzu = iZzD10 + 8;
                        iZzD15 += iZzu;
                    }
                    break;
                case 57:
                    if (zzP(obj, i8, i6)) {
                        iZzD11 = zzdi.zzD(i8 << 3);
                        iZzu = iZzD11 + 4;
                        iZzD15 += iZzu;
                    }
                    break;
                case 58:
                    if (zzP(obj, i8, i6)) {
                        iZzu = zzdi.zzD(i8 << 3) + 1;
                        iZzD15 += iZzu;
                    }
                    break;
                case 59:
                    if (zzP(obj, i8, i6)) {
                        Object object2 = unsafe.getObject(obj, j);
                        if (object2 instanceof zzdb) {
                            iZzD13 = zzdi.zzD(i8 << 3);
                            iZzd3 = ((zzdb) object2).zzd();
                            iZzD14 = zzdi.zzD(iZzd3);
                            i3 = iZzD13 + iZzD14 + iZzd3;
                            iZzD15 += i3;
                        } else {
                            iZzD9 = zzdi.zzD(i8 << 3);
                            iZzi = zzdi.zzB((String) object2);
                            i3 = iZzD9 + iZzi;
                            iZzD15 += i3;
                        }
                    }
                    break;
                case 60:
                    if (zzP(obj, i8, i6)) {
                        iZzu = zzgd.zzo(i8, unsafe.getObject(obj, j), zzE(i6));
                        iZzD15 += iZzu;
                    }
                    break;
                case 61:
                    if (zzP(obj, i8, i6)) {
                        zzdb zzdbVar2 = (zzdb) unsafe.getObject(obj, j);
                        iZzD13 = zzdi.zzD(i8 << 3);
                        iZzd3 = zzdbVar2.zzd();
                        iZzD14 = zzdi.zzD(iZzd3);
                        i3 = iZzD13 + iZzD14 + iZzd3;
                        iZzD15 += i3;
                    }
                    break;
                case 62:
                    if (zzP(obj, i8, i6)) {
                        int iZzr2 = zzr(obj, j);
                        iZzD9 = zzdi.zzD(i8 << 3);
                        iZzi = zzdi.zzD(iZzr2);
                        i3 = iZzD9 + iZzi;
                        iZzD15 += i3;
                    }
                    break;
                case 63:
                    if (zzP(obj, i8, i6)) {
                        int iZzr3 = zzr(obj, j);
                        iZzD9 = zzdi.zzD(i8 << 3);
                        iZzi = zzdi.zzx(iZzr3);
                        i3 = iZzD9 + iZzi;
                        iZzD15 += i3;
                    }
                    break;
                case 64:
                    if (zzP(obj, i8, i6)) {
                        iZzD11 = zzdi.zzD(i8 << 3);
                        iZzu = iZzD11 + 4;
                        iZzD15 += iZzu;
                    }
                    break;
                case 65:
                    if (zzP(obj, i8, i6)) {
                        iZzD10 = zzdi.zzD(i8 << 3);
                        iZzu = iZzD10 + 8;
                        iZzD15 += iZzu;
                    }
                    break;
                case 66:
                    if (zzP(obj, i8, i6)) {
                        int iZzr4 = zzr(obj, j);
                        iZzD9 = zzdi.zzD(i8 << 3);
                        iZzi = zzdi.zzD((iZzr4 >> 31) ^ (iZzr4 + iZzr4));
                        i3 = iZzD9 + iZzi;
                        iZzD15 += i3;
                    }
                    break;
                case 67:
                    if (zzP(obj, i8, i6)) {
                        long jZzC3 = zzC(obj, j);
                        iZzD15 += zzdi.zzD(i8 << 3) + zzdi.zzE((jZzC3 >> 63) ^ (jZzC3 + jZzC3));
                    }
                    break;
                case 68:
                    if (zzP(obj, i8, i6)) {
                        iZzu = zzdi.zzv(i8, (zzfl) unsafe.getObject(obj, j), zzE(i6));
                        iZzD15 += iZzu;
                    }
                    break;
            }
            i6 += 3;
            i4 = 1048575;
        }
        int iZza = 0;
        zzgp zzgpVar = this.zzn;
        int iZza2 = iZzD15 + zzgpVar.zza(zzgpVar.zzc(obj));
        if (!this.zzh) {
            return iZza2;
        }
        zzds zzdsVarZzb = this.zzo.zzb(obj);
        for (int i15 = 0; i15 < zzdsVarZzb.zza.zzb(); i15++) {
            Map.Entry entryZzg = zzdsVarZzb.zza.zzg(i15);
            iZza += zzds.zza((zzdr) entryZzg.getKey(), entryZzg.getValue());
        }
        for (Map.Entry entry : zzdsVarZzb.zza.zzc()) {
            iZza += zzds.zza((zzdr) entry.getKey(), entry.getValue());
        }
        return iZza2 + iZza;
    }

    private final int zzq(Object obj) {
        int iZzD;
        int iZzD2;
        int iZzD3;
        int iZzE;
        int iZzD4;
        int iZzx;
        int iZzD5;
        int iZzD6;
        int iZzd;
        int iZzD7;
        int iZzo;
        int iZzC;
        int iZzD8;
        int i;
        Unsafe unsafe = zzb;
        int i2 = 0;
        for (int i3 = 0; i3 < this.zzc.length; i3 += 3) {
            int iZzB = zzB(i3);
            int iZzA = zzA(iZzB);
            int i4 = this.zzc[i3];
            long j = iZzB & 1048575;
            if (iZzA >= zzdt.DOUBLE_LIST_PACKED.zza() && iZzA <= zzdt.SINT64_LIST_PACKED.zza()) {
                int i5 = this.zzc[i3 + 2];
            }
            switch (iZzA) {
                case 0:
                    if (zzM(obj, i3)) {
                        iZzD = zzdi.zzD(i4 << 3);
                        iZzo = iZzD + 8;
                        i2 += iZzo;
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (zzM(obj, i3)) {
                        iZzD2 = zzdi.zzD(i4 << 3);
                        iZzo = iZzD2 + 4;
                        i2 += iZzo;
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (zzM(obj, i3)) {
                        long jZzd = zzgz.zzd(obj, j);
                        iZzD3 = zzdi.zzD(i4 << 3);
                        iZzE = zzdi.zzE(jZzd);
                        i2 += iZzD3 + iZzE;
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (zzM(obj, i3)) {
                        long jZzd2 = zzgz.zzd(obj, j);
                        iZzD3 = zzdi.zzD(i4 << 3);
                        iZzE = zzdi.zzE(jZzd2);
                        i2 += iZzD3 + iZzE;
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (zzM(obj, i3)) {
                        int iZzc = zzgz.zzc(obj, j);
                        iZzD4 = zzdi.zzD(i4 << 3);
                        iZzx = zzdi.zzx(iZzc);
                        i = iZzD4 + iZzx;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (zzM(obj, i3)) {
                        iZzD = zzdi.zzD(i4 << 3);
                        iZzo = iZzD + 8;
                        i2 += iZzo;
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (zzM(obj, i3)) {
                        iZzD2 = zzdi.zzD(i4 << 3);
                        iZzo = iZzD2 + 4;
                        i2 += iZzo;
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if (zzM(obj, i3)) {
                        iZzD5 = zzdi.zzD(i4 << 3);
                        iZzo = iZzD5 + 1;
                        i2 += iZzo;
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (!zzM(obj, i3)) {
                        break;
                    } else {
                        Object objZzf = zzgz.zzf(obj, j);
                        if (objZzf instanceof zzdb) {
                            iZzD6 = zzdi.zzD(i4 << 3);
                            iZzd = ((zzdb) objZzf).zzd();
                            iZzD7 = zzdi.zzD(iZzd);
                            i = iZzD6 + iZzD7 + iZzd;
                            i2 += i;
                            break;
                        } else {
                            iZzD4 = zzdi.zzD(i4 << 3);
                            iZzx = zzdi.zzB((String) objZzf);
                            i = iZzD4 + iZzx;
                            i2 += i;
                        }
                    }
                case 9:
                    if (zzM(obj, i3)) {
                        iZzo = zzgd.zzo(i4, zzgz.zzf(obj, j), zzE(i3));
                        i2 += iZzo;
                        break;
                    } else {
                        break;
                    }
                case 10:
                    if (zzM(obj, i3)) {
                        zzdb zzdbVar = (zzdb) zzgz.zzf(obj, j);
                        iZzD6 = zzdi.zzD(i4 << 3);
                        iZzd = zzdbVar.zzd();
                        iZzD7 = zzdi.zzD(iZzd);
                        i = iZzD6 + iZzD7 + iZzd;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if (zzM(obj, i3)) {
                        int iZzc2 = zzgz.zzc(obj, j);
                        iZzD4 = zzdi.zzD(i4 << 3);
                        iZzx = zzdi.zzD(iZzc2);
                        i = iZzD4 + iZzx;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if (zzM(obj, i3)) {
                        int iZzc3 = zzgz.zzc(obj, j);
                        iZzD4 = zzdi.zzD(i4 << 3);
                        iZzx = zzdi.zzx(iZzc3);
                        i = iZzD4 + iZzx;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if (zzM(obj, i3)) {
                        iZzD2 = zzdi.zzD(i4 << 3);
                        iZzo = iZzD2 + 4;
                        i2 += iZzo;
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if (zzM(obj, i3)) {
                        iZzD = zzdi.zzD(i4 << 3);
                        iZzo = iZzD + 8;
                        i2 += iZzo;
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if (zzM(obj, i3)) {
                        int iZzc4 = zzgz.zzc(obj, j);
                        iZzD4 = zzdi.zzD(i4 << 3);
                        iZzx = zzdi.zzD((iZzc4 >> 31) ^ (iZzc4 + iZzc4));
                        i = iZzD4 + iZzx;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if (zzM(obj, i3)) {
                        long jZzd3 = zzgz.zzd(obj, j);
                        iZzD4 = zzdi.zzD(i4 << 3);
                        iZzx = zzdi.zzE((jZzd3 >> 63) ^ (jZzd3 + jZzd3));
                        i = iZzD4 + iZzx;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 17:
                    if (zzM(obj, i3)) {
                        iZzo = zzdi.zzv(i4, (zzfl) zzgz.zzf(obj, j), zzE(i3));
                        i2 += iZzo;
                        break;
                    } else {
                        break;
                    }
                case 18:
                    iZzo = zzgd.zzh(i4, (List) zzgz.zzf(obj, j), false);
                    i2 += iZzo;
                    break;
                case 19:
                    iZzo = zzgd.zzf(i4, (List) zzgz.zzf(obj, j), false);
                    i2 += iZzo;
                    break;
                case 20:
                    iZzo = zzgd.zzm(i4, (List) zzgz.zzf(obj, j), false);
                    i2 += iZzo;
                    break;
                case 21:
                    iZzo = zzgd.zzx(i4, (List) zzgz.zzf(obj, j), false);
                    i2 += iZzo;
                    break;
                case 22:
                    iZzo = zzgd.zzk(i4, (List) zzgz.zzf(obj, j), false);
                    i2 += iZzo;
                    break;
                case 23:
                    iZzo = zzgd.zzh(i4, (List) zzgz.zzf(obj, j), false);
                    i2 += iZzo;
                    break;
                case 24:
                    iZzo = zzgd.zzf(i4, (List) zzgz.zzf(obj, j), false);
                    i2 += iZzo;
                    break;
                case 25:
                    iZzo = zzgd.zza(i4, (List) zzgz.zzf(obj, j), false);
                    i2 += iZzo;
                    break;
                case 26:
                    iZzo = zzgd.zzu(i4, (List) zzgz.zzf(obj, j));
                    i2 += iZzo;
                    break;
                case 27:
                    iZzo = zzgd.zzp(i4, (List) zzgz.zzf(obj, j), zzE(i3));
                    i2 += iZzo;
                    break;
                case 28:
                    iZzo = zzgd.zzc(i4, (List) zzgz.zzf(obj, j));
                    i2 += iZzo;
                    break;
                case 29:
                    iZzo = zzgd.zzv(i4, (List) zzgz.zzf(obj, j), false);
                    i2 += iZzo;
                    break;
                case 30:
                    iZzo = zzgd.zzd(i4, (List) zzgz.zzf(obj, j), false);
                    i2 += iZzo;
                    break;
                case 31:
                    iZzo = zzgd.zzf(i4, (List) zzgz.zzf(obj, j), false);
                    i2 += iZzo;
                    break;
                case 32:
                    iZzo = zzgd.zzh(i4, (List) zzgz.zzf(obj, j), false);
                    i2 += iZzo;
                    break;
                case 33:
                    iZzo = zzgd.zzq(i4, (List) zzgz.zzf(obj, j), false);
                    i2 += iZzo;
                    break;
                case 34:
                    iZzo = zzgd.zzs(i4, (List) zzgz.zzf(obj, j), false);
                    i2 += iZzo;
                    break;
                case 35:
                    iZzx = zzgd.zzi((List) unsafe.getObject(obj, j));
                    if (iZzx > 0) {
                        iZzC = zzdi.zzC(i4);
                        iZzD8 = zzdi.zzD(iZzx);
                        iZzD4 = iZzC + iZzD8;
                        i = iZzD4 + iZzx;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 36:
                    iZzx = zzgd.zzg((List) unsafe.getObject(obj, j));
                    if (iZzx > 0) {
                        iZzC = zzdi.zzC(i4);
                        iZzD8 = zzdi.zzD(iZzx);
                        iZzD4 = iZzC + iZzD8;
                        i = iZzD4 + iZzx;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 37:
                    iZzx = zzgd.zzn((List) unsafe.getObject(obj, j));
                    if (iZzx > 0) {
                        iZzC = zzdi.zzC(i4);
                        iZzD8 = zzdi.zzD(iZzx);
                        iZzD4 = iZzC + iZzD8;
                        i = iZzD4 + iZzx;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 38:
                    iZzx = zzgd.zzy((List) unsafe.getObject(obj, j));
                    if (iZzx > 0) {
                        iZzC = zzdi.zzC(i4);
                        iZzD8 = zzdi.zzD(iZzx);
                        iZzD4 = iZzC + iZzD8;
                        i = iZzD4 + iZzx;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 39:
                    iZzx = zzgd.zzl((List) unsafe.getObject(obj, j));
                    if (iZzx > 0) {
                        iZzC = zzdi.zzC(i4);
                        iZzD8 = zzdi.zzD(iZzx);
                        iZzD4 = iZzC + iZzD8;
                        i = iZzD4 + iZzx;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 40:
                    iZzx = zzgd.zzi((List) unsafe.getObject(obj, j));
                    if (iZzx > 0) {
                        iZzC = zzdi.zzC(i4);
                        iZzD8 = zzdi.zzD(iZzx);
                        iZzD4 = iZzC + iZzD8;
                        i = iZzD4 + iZzx;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 41:
                    iZzx = zzgd.zzg((List) unsafe.getObject(obj, j));
                    if (iZzx > 0) {
                        iZzC = zzdi.zzC(i4);
                        iZzD8 = zzdi.zzD(iZzx);
                        iZzD4 = iZzC + iZzD8;
                        i = iZzD4 + iZzx;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 42:
                    iZzx = zzgd.zzb((List) unsafe.getObject(obj, j));
                    if (iZzx > 0) {
                        iZzC = zzdi.zzC(i4);
                        iZzD8 = zzdi.zzD(iZzx);
                        iZzD4 = iZzC + iZzD8;
                        i = iZzD4 + iZzx;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 43:
                    iZzx = zzgd.zzw((List) unsafe.getObject(obj, j));
                    if (iZzx > 0) {
                        iZzC = zzdi.zzC(i4);
                        iZzD8 = zzdi.zzD(iZzx);
                        iZzD4 = iZzC + iZzD8;
                        i = iZzD4 + iZzx;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 44:
                    iZzx = zzgd.zze((List) unsafe.getObject(obj, j));
                    if (iZzx > 0) {
                        iZzC = zzdi.zzC(i4);
                        iZzD8 = zzdi.zzD(iZzx);
                        iZzD4 = iZzC + iZzD8;
                        i = iZzD4 + iZzx;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 45:
                    iZzx = zzgd.zzg((List) unsafe.getObject(obj, j));
                    if (iZzx > 0) {
                        iZzC = zzdi.zzC(i4);
                        iZzD8 = zzdi.zzD(iZzx);
                        iZzD4 = iZzC + iZzD8;
                        i = iZzD4 + iZzx;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 46:
                    iZzx = zzgd.zzi((List) unsafe.getObject(obj, j));
                    if (iZzx > 0) {
                        iZzC = zzdi.zzC(i4);
                        iZzD8 = zzdi.zzD(iZzx);
                        iZzD4 = iZzC + iZzD8;
                        i = iZzD4 + iZzx;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 47:
                    iZzx = zzgd.zzr((List) unsafe.getObject(obj, j));
                    if (iZzx > 0) {
                        iZzC = zzdi.zzC(i4);
                        iZzD8 = zzdi.zzD(iZzx);
                        iZzD4 = iZzC + iZzD8;
                        i = iZzD4 + iZzx;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 48:
                    iZzx = zzgd.zzt((List) unsafe.getObject(obj, j));
                    if (iZzx > 0) {
                        iZzC = zzdi.zzC(i4);
                        iZzD8 = zzdi.zzD(iZzx);
                        iZzD4 = iZzC + iZzD8;
                        i = iZzD4 + iZzx;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 49:
                    iZzo = zzgd.zzj(i4, (List) zzgz.zzf(obj, j), zzE(i3));
                    i2 += iZzo;
                    break;
                case 50:
                    zzfg.zza(i4, zzgz.zzf(obj, j), zzF(i3));
                    break;
                case 51:
                    if (zzP(obj, i4, i3)) {
                        iZzD = zzdi.zzD(i4 << 3);
                        iZzo = iZzD + 8;
                        i2 += iZzo;
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (zzP(obj, i4, i3)) {
                        iZzD2 = zzdi.zzD(i4 << 3);
                        iZzo = iZzD2 + 4;
                        i2 += iZzo;
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (zzP(obj, i4, i3)) {
                        long jZzC = zzC(obj, j);
                        iZzD3 = zzdi.zzD(i4 << 3);
                        iZzE = zzdi.zzE(jZzC);
                        i2 += iZzD3 + iZzE;
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (zzP(obj, i4, i3)) {
                        long jZzC2 = zzC(obj, j);
                        iZzD3 = zzdi.zzD(i4 << 3);
                        iZzE = zzdi.zzE(jZzC2);
                        i2 += iZzD3 + iZzE;
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (zzP(obj, i4, i3)) {
                        int iZzr = zzr(obj, j);
                        iZzD4 = zzdi.zzD(i4 << 3);
                        iZzx = zzdi.zzx(iZzr);
                        i = iZzD4 + iZzx;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (zzP(obj, i4, i3)) {
                        iZzD = zzdi.zzD(i4 << 3);
                        iZzo = iZzD + 8;
                        i2 += iZzo;
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (zzP(obj, i4, i3)) {
                        iZzD2 = zzdi.zzD(i4 << 3);
                        iZzo = iZzD2 + 4;
                        i2 += iZzo;
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (zzP(obj, i4, i3)) {
                        iZzD5 = zzdi.zzD(i4 << 3);
                        iZzo = iZzD5 + 1;
                        i2 += iZzo;
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (!zzP(obj, i4, i3)) {
                        break;
                    } else {
                        Object objZzf2 = zzgz.zzf(obj, j);
                        if (objZzf2 instanceof zzdb) {
                            iZzD6 = zzdi.zzD(i4 << 3);
                            iZzd = ((zzdb) objZzf2).zzd();
                            iZzD7 = zzdi.zzD(iZzd);
                            i = iZzD6 + iZzD7 + iZzd;
                            i2 += i;
                            break;
                        } else {
                            iZzD4 = zzdi.zzD(i4 << 3);
                            iZzx = zzdi.zzB((String) objZzf2);
                            i = iZzD4 + iZzx;
                            i2 += i;
                        }
                    }
                case 60:
                    if (zzP(obj, i4, i3)) {
                        iZzo = zzgd.zzo(i4, zzgz.zzf(obj, j), zzE(i3));
                        i2 += iZzo;
                        break;
                    } else {
                        break;
                    }
                case 61:
                    if (zzP(obj, i4, i3)) {
                        zzdb zzdbVar2 = (zzdb) zzgz.zzf(obj, j);
                        iZzD6 = zzdi.zzD(i4 << 3);
                        iZzd = zzdbVar2.zzd();
                        iZzD7 = zzdi.zzD(iZzd);
                        i = iZzD6 + iZzD7 + iZzd;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (zzP(obj, i4, i3)) {
                        int iZzr2 = zzr(obj, j);
                        iZzD4 = zzdi.zzD(i4 << 3);
                        iZzx = zzdi.zzD(iZzr2);
                        i = iZzD4 + iZzx;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (zzP(obj, i4, i3)) {
                        int iZzr3 = zzr(obj, j);
                        iZzD4 = zzdi.zzD(i4 << 3);
                        iZzx = zzdi.zzx(iZzr3);
                        i = iZzD4 + iZzx;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (zzP(obj, i4, i3)) {
                        iZzD2 = zzdi.zzD(i4 << 3);
                        iZzo = iZzD2 + 4;
                        i2 += iZzo;
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (zzP(obj, i4, i3)) {
                        iZzD = zzdi.zzD(i4 << 3);
                        iZzo = iZzD + 8;
                        i2 += iZzo;
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (zzP(obj, i4, i3)) {
                        int iZzr4 = zzr(obj, j);
                        iZzD4 = zzdi.zzD(i4 << 3);
                        iZzx = zzdi.zzD((iZzr4 >> 31) ^ (iZzr4 + iZzr4));
                        i = iZzD4 + iZzx;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (zzP(obj, i4, i3)) {
                        long jZzC3 = zzC(obj, j);
                        iZzD4 = zzdi.zzD(i4 << 3);
                        iZzx = zzdi.zzE((jZzC3 >> 63) ^ (jZzC3 + jZzC3));
                        i = iZzD4 + iZzx;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (zzP(obj, i4, i3)) {
                        iZzo = zzdi.zzv(i4, (zzfl) zzgz.zzf(obj, j), zzE(i3));
                        i2 += iZzo;
                        break;
                    } else {
                        break;
                    }
            }
        }
        zzgp zzgpVar = this.zzn;
        return i2 + zzgpVar.zza(zzgpVar.zzc(obj));
    }

    private static int zzr(Object obj, long j) {
        return ((Integer) zzgz.zzf(obj, j)).intValue();
    }

    private final int zzs(Object obj, byte[] bArr, int i, int i2, int i3, long j, zzco zzcoVar) throws IOException {
        Unsafe unsafe = zzb;
        Object objZzF = zzF(i3);
        Object object = unsafe.getObject(obj, j);
        if (!((zzff) object).zze()) {
            zzff zzffVarZzb = zzff.zza().zzb();
            zzfg.zzb(zzffVarZzb, object);
            unsafe.putObject(obj, j, zzffVarZzb);
        }
        throw null;
    }

    private final int zzt(Object obj, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7, long j, int i8, zzco zzcoVar) throws IOException {
        Unsafe unsafe = zzb;
        long j2 = this.zzc[i8 + 2] & 1048575;
        switch (i7) {
            case 51:
                if (i5 == 1) {
                    unsafe.putObject(obj, j, Double.valueOf(Double.longBitsToDouble(zzcp.zzo(bArr, i))));
                    unsafe.putInt(obj, j2, i4);
                    return i + 8;
                }
                break;
            case 52:
                if (i5 == 5) {
                    unsafe.putObject(obj, j, Float.valueOf(Float.intBitsToFloat(zzcp.zzb(bArr, i))));
                    unsafe.putInt(obj, j2, i4);
                    return i + 4;
                }
                break;
            case 53:
            case 54:
                if (i5 == 0) {
                    int iZzm = zzcp.zzm(bArr, i, zzcoVar);
                    unsafe.putObject(obj, j, Long.valueOf(zzcoVar.zzb));
                    unsafe.putInt(obj, j2, i4);
                    return iZzm;
                }
                break;
            case 55:
            case 62:
                if (i5 == 0) {
                    int iZzj = zzcp.zzj(bArr, i, zzcoVar);
                    unsafe.putObject(obj, j, Integer.valueOf(zzcoVar.zza));
                    unsafe.putInt(obj, j2, i4);
                    return iZzj;
                }
                break;
            case 56:
            case 65:
                if (i5 == 1) {
                    unsafe.putObject(obj, j, Long.valueOf(zzcp.zzo(bArr, i)));
                    unsafe.putInt(obj, j2, i4);
                    return i + 8;
                }
                break;
            case 57:
            case 64:
                if (i5 == 5) {
                    unsafe.putObject(obj, j, Integer.valueOf(zzcp.zzb(bArr, i)));
                    unsafe.putInt(obj, j2, i4);
                    return i + 4;
                }
                break;
            case 58:
                if (i5 == 0) {
                    int iZzm2 = zzcp.zzm(bArr, i, zzcoVar);
                    unsafe.putObject(obj, j, Boolean.valueOf(zzcoVar.zzb != 0));
                    unsafe.putInt(obj, j2, i4);
                    return iZzm2;
                }
                break;
            case 59:
                if (i5 == 2) {
                    int iZzj2 = zzcp.zzj(bArr, i, zzcoVar);
                    int i9 = zzcoVar.zza;
                    if (i9 == 0) {
                        unsafe.putObject(obj, j, "");
                    } else {
                        if ((i6 & 536870912) != 0 && !zzhe.zzi(bArr, iZzj2, iZzj2 + i9)) {
                            throw zzen.zzc();
                        }
                        unsafe.putObject(obj, j, new String(bArr, iZzj2, i9, zzel.zzb));
                        iZzj2 += i9;
                    }
                    unsafe.putInt(obj, j2, i4);
                    return iZzj2;
                }
                break;
            case 60:
                if (i5 == 2) {
                    int iZzd = zzcp.zzd(zzE(i8), bArr, i, i2, zzcoVar);
                    Object object = unsafe.getInt(obj, j2) == i4 ? unsafe.getObject(obj, j) : null;
                    if (object == null) {
                        unsafe.putObject(obj, j, zzcoVar.zzc);
                    } else {
                        unsafe.putObject(obj, j, zzel.zzg(object, zzcoVar.zzc));
                    }
                    unsafe.putInt(obj, j2, i4);
                    return iZzd;
                }
                break;
            case 61:
                if (i5 == 2) {
                    int iZza = zzcp.zza(bArr, i, zzcoVar);
                    unsafe.putObject(obj, j, zzcoVar.zzc);
                    unsafe.putInt(obj, j2, i4);
                    return iZza;
                }
                break;
            case 63:
                if (i5 == 0) {
                    int iZzj3 = zzcp.zzj(bArr, i, zzcoVar);
                    int i10 = zzcoVar.zza;
                    zzeg zzegVarZzD = zzD(i8);
                    if (zzegVarZzD == null || zzegVarZzD.zza(i10)) {
                        unsafe.putObject(obj, j, Integer.valueOf(i10));
                        unsafe.putInt(obj, j2, i4);
                    } else {
                        zzd(obj).zzh(i3, Long.valueOf(i10));
                    }
                    return iZzj3;
                }
                break;
            case 66:
                if (i5 == 0) {
                    int iZzj4 = zzcp.zzj(bArr, i, zzcoVar);
                    unsafe.putObject(obj, j, Integer.valueOf(zzde.zzb(zzcoVar.zza)));
                    unsafe.putInt(obj, j2, i4);
                    return iZzj4;
                }
                break;
            case 67:
                if (i5 == 0) {
                    int iZzm3 = zzcp.zzm(bArr, i, zzcoVar);
                    unsafe.putObject(obj, j, Long.valueOf(zzde.zzc(zzcoVar.zzb)));
                    unsafe.putInt(obj, j2, i4);
                    return iZzm3;
                }
                break;
            case 68:
                if (i5 == 3) {
                    int iZzc = zzcp.zzc(zzE(i8), bArr, i, i2, (i3 & (-8)) | 4, zzcoVar);
                    Object object2 = unsafe.getInt(obj, j2) == i4 ? unsafe.getObject(obj, j) : null;
                    if (object2 == null) {
                        unsafe.putObject(obj, j, zzcoVar.zzc);
                    } else {
                        unsafe.putObject(obj, j, zzel.zzg(object2, zzcoVar.zzc));
                    }
                    unsafe.putInt(obj, j2, i4);
                    return iZzc;
                }
                break;
        }
        return i;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:25:0x0080. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v22, types: [int] */
    private final int zzu(Object obj, byte[] bArr, int i, int i2, zzco zzcoVar) throws IOException {
        byte b;
        int iZzk;
        int i3;
        int i4;
        int i5;
        Unsafe unsafe;
        int i6;
        int i7;
        int i8;
        int i9;
        int iZzm;
        int iZzd;
        int i10;
        int i11;
        int i12;
        zzfo<T> zzfoVar = this;
        Object obj2 = obj;
        byte[] bArr2 = bArr;
        int i13 = i2;
        zzco zzcoVar2 = zzcoVar;
        Unsafe unsafe2 = zzb;
        int i14 = 1048575;
        int i15 = -1;
        int iZzi = i;
        int i16 = -1;
        int i17 = 1048575;
        int i18 = 0;
        int i19 = 0;
        while (iZzi < i13) {
            int i20 = iZzi + 1;
            byte b2 = bArr2[iZzi];
            if (b2 < 0) {
                iZzk = zzcp.zzk(b2, bArr2, i20, zzcoVar2);
                b = zzcoVar2.zza;
            } else {
                b = b2;
                iZzk = i20;
            }
            int i21 = b >>> 3;
            int i22 = b & 7;
            int iZzx = i21 > i16 ? zzfoVar.zzx(i21, i18 / 3) : zzfoVar.zzw(i21);
            if (iZzx == i15) {
                i3 = iZzk;
                i4 = i21;
                i5 = i15;
                unsafe = unsafe2;
                i6 = 0;
            } else {
                int[] iArr = zzfoVar.zzc;
                int i23 = iArr[iZzx + 1];
                int iZzA = zzA(i23);
                long j = i23 & i14;
                if (iZzA <= 17) {
                    int i24 = iArr[iZzx + 2];
                    int i25 = 1 << (i24 >>> 20);
                    int i26 = i24 & 1048575;
                    if (i26 != i17) {
                        if (i17 != 1048575) {
                            unsafe2.putInt(obj2, i17, i19);
                        }
                        if (i26 != 1048575) {
                            i19 = unsafe2.getInt(obj2, i26);
                        }
                        i17 = i26;
                    }
                    switch (iZzA) {
                        case 0:
                            zzcoVar2 = zzcoVar;
                            i7 = iZzx;
                            i8 = iZzk;
                            i9 = 1048575;
                            i4 = i21;
                            if (i22 != 1) {
                                i3 = i8;
                                unsafe = unsafe2;
                                i6 = i7;
                                i5 = -1;
                                break;
                            } else {
                                zzgz.zzo(obj2, j, Double.longBitsToDouble(zzcp.zzo(bArr2, i8)));
                                iZzi = i8 + 8;
                                i19 |= i25;
                                i18 = i7;
                                i16 = i4;
                                i14 = i9;
                                i15 = -1;
                                break;
                            }
                        case 1:
                            zzcoVar2 = zzcoVar;
                            i7 = iZzx;
                            i8 = iZzk;
                            i9 = 1048575;
                            i4 = i21;
                            if (i22 != 5) {
                                i3 = i8;
                                unsafe = unsafe2;
                                i6 = i7;
                                i5 = -1;
                                break;
                            } else {
                                zzgz.zzp(obj2, j, Float.intBitsToFloat(zzcp.zzb(bArr2, i8)));
                                iZzi = i8 + 4;
                                i19 |= i25;
                                i18 = i7;
                                i16 = i4;
                                i14 = i9;
                                i15 = -1;
                                break;
                            }
                        case 2:
                        case 3:
                            zzcoVar2 = zzcoVar;
                            i7 = iZzx;
                            i8 = iZzk;
                            i9 = 1048575;
                            i4 = i21;
                            if (i22 != 0) {
                                i3 = i8;
                                unsafe = unsafe2;
                                i6 = i7;
                                i5 = -1;
                                break;
                            } else {
                                iZzm = zzcp.zzm(bArr2, i8, zzcoVar2);
                                unsafe2.putLong(obj, j, zzcoVar2.zzb);
                                i19 |= i25;
                                iZzi = iZzm;
                                i18 = i7;
                                i16 = i4;
                                i14 = i9;
                                i15 = -1;
                                break;
                            }
                        case 4:
                        case 11:
                            zzcoVar2 = zzcoVar;
                            i7 = iZzx;
                            i8 = iZzk;
                            i9 = 1048575;
                            i4 = i21;
                            if (i22 != 0) {
                                i3 = i8;
                                unsafe = unsafe2;
                                i6 = i7;
                                i5 = -1;
                                break;
                            } else {
                                iZzi = zzcp.zzj(bArr2, i8, zzcoVar2);
                                unsafe2.putInt(obj2, j, zzcoVar2.zza);
                                i19 |= i25;
                                i18 = i7;
                                i16 = i4;
                                i14 = i9;
                                i15 = -1;
                                break;
                            }
                        case 5:
                        case 14:
                            zzcoVar2 = zzcoVar;
                            i7 = iZzx;
                            i9 = 1048575;
                            i4 = i21;
                            if (i22 != 1) {
                                i8 = iZzk;
                                i3 = i8;
                                unsafe = unsafe2;
                                i6 = i7;
                                i5 = -1;
                                break;
                            } else {
                                i8 = iZzk;
                                unsafe2.putLong(obj, j, zzcp.zzo(bArr2, iZzk));
                                iZzi = i8 + 8;
                                i19 |= i25;
                                i18 = i7;
                                i16 = i4;
                                i14 = i9;
                                i15 = -1;
                                break;
                            }
                        case 6:
                        case 13:
                            zzcoVar2 = zzcoVar;
                            i7 = iZzx;
                            i9 = 1048575;
                            i4 = i21;
                            if (i22 != 5) {
                                i8 = iZzk;
                                i3 = i8;
                                unsafe = unsafe2;
                                i6 = i7;
                                i5 = -1;
                                break;
                            } else {
                                unsafe2.putInt(obj2, j, zzcp.zzb(bArr2, iZzk));
                                iZzi = iZzk + 4;
                                i19 |= i25;
                                i18 = i7;
                                i16 = i4;
                                i14 = i9;
                                i15 = -1;
                                break;
                            }
                        case 7:
                            zzcoVar2 = zzcoVar;
                            i7 = iZzx;
                            i9 = 1048575;
                            i4 = i21;
                            if (i22 != 0) {
                                i8 = iZzk;
                                i3 = i8;
                                unsafe = unsafe2;
                                i6 = i7;
                                i5 = -1;
                                break;
                            } else {
                                iZzi = zzcp.zzm(bArr2, iZzk, zzcoVar2);
                                zzgz.zzm(obj2, j, zzcoVar2.zzb != 0);
                                i19 |= i25;
                                i18 = i7;
                                i16 = i4;
                                i14 = i9;
                                i15 = -1;
                                break;
                            }
                        case 8:
                            zzcoVar2 = zzcoVar;
                            i7 = iZzx;
                            i9 = 1048575;
                            i4 = i21;
                            if (i22 != 2) {
                                i8 = iZzk;
                                i3 = i8;
                                unsafe = unsafe2;
                                i6 = i7;
                                i5 = -1;
                                break;
                            } else {
                                iZzi = (536870912 & i23) == 0 ? zzcp.zzg(bArr2, iZzk, zzcoVar2) : zzcp.zzh(bArr2, iZzk, zzcoVar2);
                                unsafe2.putObject(obj2, j, zzcoVar2.zzc);
                                i19 |= i25;
                                i18 = i7;
                                i16 = i4;
                                i14 = i9;
                                i15 = -1;
                                break;
                            }
                        case 9:
                            zzcoVar2 = zzcoVar;
                            i7 = iZzx;
                            i9 = 1048575;
                            i4 = i21;
                            if (i22 != 2) {
                                i8 = iZzk;
                                i3 = i8;
                                unsafe = unsafe2;
                                i6 = i7;
                                i5 = -1;
                                break;
                            } else {
                                iZzd = zzcp.zzd(zzfoVar.zzE(i7), bArr2, iZzk, i13, zzcoVar2);
                                Object object = unsafe2.getObject(obj2, j);
                                if (object == null) {
                                    unsafe2.putObject(obj2, j, zzcoVar2.zzc);
                                } else {
                                    unsafe2.putObject(obj2, j, zzel.zzg(object, zzcoVar2.zzc));
                                }
                                i19 |= i25;
                                iZzi = iZzd;
                                i18 = i7;
                                i16 = i4;
                                i14 = i9;
                                i15 = -1;
                                break;
                            }
                        case 10:
                            zzcoVar2 = zzcoVar;
                            i7 = iZzx;
                            i9 = 1048575;
                            i4 = i21;
                            if (i22 != 2) {
                                i8 = iZzk;
                                i3 = i8;
                                unsafe = unsafe2;
                                i6 = i7;
                                i5 = -1;
                                break;
                            } else {
                                iZzd = zzcp.zza(bArr2, iZzk, zzcoVar2);
                                unsafe2.putObject(obj2, j, zzcoVar2.zzc);
                                i19 |= i25;
                                iZzi = iZzd;
                                i18 = i7;
                                i16 = i4;
                                i14 = i9;
                                i15 = -1;
                                break;
                            }
                        case 12:
                            zzcoVar2 = zzcoVar;
                            i7 = iZzx;
                            i9 = 1048575;
                            i4 = i21;
                            if (i22 != 0) {
                                i8 = iZzk;
                                i3 = i8;
                                unsafe = unsafe2;
                                i6 = i7;
                                i5 = -1;
                                break;
                            } else {
                                iZzd = zzcp.zzj(bArr2, iZzk, zzcoVar2);
                                unsafe2.putInt(obj2, j, zzcoVar2.zza);
                                i19 |= i25;
                                iZzi = iZzd;
                                i18 = i7;
                                i16 = i4;
                                i14 = i9;
                                i15 = -1;
                                break;
                            }
                        case 15:
                            zzcoVar2 = zzcoVar;
                            i7 = iZzx;
                            i9 = 1048575;
                            i4 = i21;
                            if (i22 != 0) {
                                i8 = iZzk;
                                i3 = i8;
                                unsafe = unsafe2;
                                i6 = i7;
                                i5 = -1;
                                break;
                            } else {
                                iZzd = zzcp.zzj(bArr2, iZzk, zzcoVar2);
                                unsafe2.putInt(obj2, j, zzde.zzb(zzcoVar2.zza));
                                i19 |= i25;
                                iZzi = iZzd;
                                i18 = i7;
                                i16 = i4;
                                i14 = i9;
                                i15 = -1;
                                break;
                            }
                        case 16:
                            if (i22 != 0) {
                                i4 = i21;
                                i7 = iZzx;
                                i8 = iZzk;
                                i3 = i8;
                                unsafe = unsafe2;
                                i6 = i7;
                                i5 = -1;
                                break;
                            } else {
                                zzcoVar2 = zzcoVar;
                                iZzm = zzcp.zzm(bArr2, iZzk, zzcoVar2);
                                i7 = iZzx;
                                i4 = i21;
                                i9 = 1048575;
                                unsafe2.putLong(obj, j, zzde.zzc(zzcoVar2.zzb));
                                i19 |= i25;
                                iZzi = iZzm;
                                i18 = i7;
                                i16 = i4;
                                i14 = i9;
                                i15 = -1;
                                break;
                            }
                        default:
                            i4 = i21;
                            i7 = iZzx;
                            i8 = iZzk;
                            i3 = i8;
                            unsafe = unsafe2;
                            i6 = i7;
                            i5 = -1;
                            break;
                    }
                } else {
                    zzcoVar2 = zzcoVar;
                    i7 = iZzx;
                    int i27 = iZzk;
                    i9 = 1048575;
                    i4 = i21;
                    if (iZzA == 27) {
                        if (i22 == 2) {
                            zzek zzekVarZzd = (zzek) unsafe2.getObject(obj2, j);
                            if (!zzekVarZzd.zzc()) {
                                int size = zzekVarZzd.size();
                                zzekVarZzd = zzekVarZzd.zzd(size == 0 ? 10 : size + size);
                                unsafe2.putObject(obj2, j, zzekVarZzd);
                            }
                            iZzi = zzcp.zze(zzfoVar.zzE(i7), b, bArr, i27, i2, zzekVarZzd, zzcoVar);
                            i19 = i19;
                            i18 = i7;
                            i16 = i4;
                            i14 = i9;
                            i15 = -1;
                        } else {
                            i10 = i27;
                            i11 = i19;
                            i12 = i17;
                            unsafe = unsafe2;
                            i6 = i7;
                            i5 = -1;
                        }
                    } else if (iZzA <= 49) {
                        i11 = i19;
                        i12 = i17;
                        i5 = -1;
                        unsafe = unsafe2;
                        i6 = i7;
                        iZzi = zzv(obj, bArr, i27, i2, b, i4, i22, i7, i23, iZzA, j, zzcoVar);
                        if (iZzi != i27) {
                            obj2 = obj;
                            bArr2 = bArr;
                            i13 = i2;
                            zzcoVar2 = zzcoVar;
                            i17 = i12;
                            i15 = i5;
                            i16 = i4;
                            i19 = i11;
                            i18 = i6;
                            unsafe2 = unsafe;
                            i14 = 1048575;
                            zzfoVar = this;
                        } else {
                            i3 = iZzi;
                            i17 = i12;
                            i19 = i11;
                        }
                    } else {
                        i10 = i27;
                        i11 = i19;
                        i12 = i17;
                        unsafe = unsafe2;
                        i6 = i7;
                        i5 = -1;
                        if (iZzA != 50) {
                            iZzi = zzt(obj, bArr, i10, i2, b, i4, i22, i23, iZzA, j, i6, zzcoVar);
                            if (iZzi != i10) {
                                obj2 = obj;
                                bArr2 = bArr;
                                i13 = i2;
                                zzcoVar2 = zzcoVar;
                                i17 = i12;
                                i15 = i5;
                                i16 = i4;
                                i19 = i11;
                                i18 = i6;
                                unsafe2 = unsafe;
                                i14 = 1048575;
                                zzfoVar = this;
                            } else {
                                i3 = iZzi;
                                i17 = i12;
                                i19 = i11;
                            }
                        } else if (i22 == 2) {
                            iZzi = zzs(obj, bArr, i10, i2, i6, j, zzcoVar);
                            if (iZzi != i10) {
                                obj2 = obj;
                                bArr2 = bArr;
                                i13 = i2;
                                zzcoVar2 = zzcoVar;
                                i17 = i12;
                                i15 = i5;
                                i16 = i4;
                                i19 = i11;
                                i18 = i6;
                                unsafe2 = unsafe;
                                i14 = 1048575;
                                zzfoVar = this;
                            } else {
                                i3 = iZzi;
                                i17 = i12;
                                i19 = i11;
                            }
                        }
                    }
                    i3 = i10;
                    i17 = i12;
                    i19 = i11;
                }
            }
            iZzi = zzcp.zzi(b, bArr, i3, i2, zzd(obj), zzcoVar);
            zzfoVar = this;
            obj2 = obj;
            bArr2 = bArr;
            i13 = i2;
            zzcoVar2 = zzcoVar;
            i15 = i5;
            i16 = i4;
            i18 = i6;
            unsafe2 = unsafe;
            i14 = 1048575;
        }
        int i28 = i19;
        int i29 = i17;
        Unsafe unsafe3 = unsafe2;
        if (i29 != i14) {
            unsafe3.putInt(obj, i29, i28);
        }
        if (iZzi == i2) {
            return iZzi;
        }
        throw zzen.zze();
    }

    private final int zzv(Object obj, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, long j, int i7, long j2, zzco zzcoVar) throws IOException {
        int i8;
        int i9;
        int i10;
        int i11;
        int iZzj;
        int iZzj2 = i;
        Unsafe unsafe = zzb;
        zzek zzekVarZzd = (zzek) unsafe.getObject(obj, j2);
        if (!zzekVarZzd.zzc()) {
            int size = zzekVarZzd.size();
            zzekVarZzd = zzekVarZzd.zzd(size == 0 ? 10 : size + size);
            unsafe.putObject(obj, j2, zzekVarZzd);
        }
        switch (i7) {
            case 18:
            case 35:
                if (i5 == 2) {
                    zzdk zzdkVar = (zzdk) zzekVarZzd;
                    int iZzj3 = zzcp.zzj(bArr, iZzj2, zzcoVar);
                    int i12 = zzcoVar.zza + iZzj3;
                    while (iZzj3 < i12) {
                        zzdkVar.zze(Double.longBitsToDouble(zzcp.zzo(bArr, iZzj3)));
                        iZzj3 += 8;
                    }
                    if (iZzj3 == i12) {
                        return iZzj3;
                    }
                    throw zzen.zzg();
                }
                if (i5 == 1) {
                    zzdk zzdkVar2 = (zzdk) zzekVarZzd;
                    zzdkVar2.zze(Double.longBitsToDouble(zzcp.zzo(bArr, i)));
                    while (true) {
                        i8 = iZzj2 + 8;
                        if (i8 < i2) {
                            iZzj2 = zzcp.zzj(bArr, i8, zzcoVar);
                            if (i3 == zzcoVar.zza) {
                                zzdkVar2.zze(Double.longBitsToDouble(zzcp.zzo(bArr, iZzj2)));
                            }
                        }
                    }
                    return i8;
                }
                return iZzj2;
            case 19:
            case 36:
                if (i5 == 2) {
                    zzdu zzduVar = (zzdu) zzekVarZzd;
                    int iZzj4 = zzcp.zzj(bArr, iZzj2, zzcoVar);
                    int i13 = zzcoVar.zza + iZzj4;
                    while (iZzj4 < i13) {
                        zzduVar.zzg(Float.intBitsToFloat(zzcp.zzb(bArr, iZzj4)));
                        iZzj4 += 4;
                    }
                    if (iZzj4 == i13) {
                        return iZzj4;
                    }
                    throw zzen.zzg();
                }
                if (i5 == 5) {
                    zzdu zzduVar2 = (zzdu) zzekVarZzd;
                    zzduVar2.zzg(Float.intBitsToFloat(zzcp.zzb(bArr, i)));
                    while (true) {
                        i9 = iZzj2 + 4;
                        if (i9 < i2) {
                            iZzj2 = zzcp.zzj(bArr, i9, zzcoVar);
                            if (i3 == zzcoVar.zza) {
                                zzduVar2.zzg(Float.intBitsToFloat(zzcp.zzb(bArr, iZzj2)));
                            }
                        }
                    }
                    return i9;
                }
                return iZzj2;
            case 20:
            case 21:
            case 37:
            case 38:
                if (i5 == 2) {
                    zzfa zzfaVar = (zzfa) zzekVarZzd;
                    int iZzj5 = zzcp.zzj(bArr, iZzj2, zzcoVar);
                    int i14 = zzcoVar.zza + iZzj5;
                    while (iZzj5 < i14) {
                        iZzj5 = zzcp.zzm(bArr, iZzj5, zzcoVar);
                        zzfaVar.zzf(zzcoVar.zzb);
                    }
                    if (iZzj5 == i14) {
                        return iZzj5;
                    }
                    throw zzen.zzg();
                }
                if (i5 == 0) {
                    zzfa zzfaVar2 = (zzfa) zzekVarZzd;
                    int iZzm = zzcp.zzm(bArr, iZzj2, zzcoVar);
                    zzfaVar2.zzf(zzcoVar.zzb);
                    while (iZzm < i2) {
                        int iZzj6 = zzcp.zzj(bArr, iZzm, zzcoVar);
                        if (i3 != zzcoVar.zza) {
                            return iZzm;
                        }
                        iZzm = zzcp.zzm(bArr, iZzj6, zzcoVar);
                        zzfaVar2.zzf(zzcoVar.zzb);
                    }
                    return iZzm;
                }
                return iZzj2;
            case 22:
            case 29:
            case 39:
            case 43:
                if (i5 == 2) {
                    return zzcp.zzf(bArr, iZzj2, zzekVarZzd, zzcoVar);
                }
                if (i5 == 0) {
                    return zzcp.zzl(i3, bArr, i, i2, zzekVarZzd, zzcoVar);
                }
                return iZzj2;
            case 23:
            case 32:
            case 40:
            case 46:
                if (i5 == 2) {
                    zzfa zzfaVar3 = (zzfa) zzekVarZzd;
                    int iZzj7 = zzcp.zzj(bArr, iZzj2, zzcoVar);
                    int i15 = zzcoVar.zza + iZzj7;
                    while (iZzj7 < i15) {
                        zzfaVar3.zzf(zzcp.zzo(bArr, iZzj7));
                        iZzj7 += 8;
                    }
                    if (iZzj7 == i15) {
                        return iZzj7;
                    }
                    throw zzen.zzg();
                }
                if (i5 == 1) {
                    zzfa zzfaVar4 = (zzfa) zzekVarZzd;
                    zzfaVar4.zzf(zzcp.zzo(bArr, i));
                    while (true) {
                        i10 = iZzj2 + 8;
                        if (i10 < i2) {
                            iZzj2 = zzcp.zzj(bArr, i10, zzcoVar);
                            if (i3 == zzcoVar.zza) {
                                zzfaVar4.zzf(zzcp.zzo(bArr, iZzj2));
                            }
                        }
                    }
                    return i10;
                }
                return iZzj2;
            case 24:
            case 31:
            case 41:
            case 45:
                if (i5 == 2) {
                    zzed zzedVar = (zzed) zzekVarZzd;
                    int iZzj8 = zzcp.zzj(bArr, iZzj2, zzcoVar);
                    int i16 = zzcoVar.zza + iZzj8;
                    while (iZzj8 < i16) {
                        zzedVar.zzg(zzcp.zzb(bArr, iZzj8));
                        iZzj8 += 4;
                    }
                    if (iZzj8 == i16) {
                        return iZzj8;
                    }
                    throw zzen.zzg();
                }
                if (i5 == 5) {
                    zzed zzedVar2 = (zzed) zzekVarZzd;
                    zzedVar2.zzg(zzcp.zzb(bArr, i));
                    while (true) {
                        i11 = iZzj2 + 4;
                        if (i11 < i2) {
                            iZzj2 = zzcp.zzj(bArr, i11, zzcoVar);
                            if (i3 == zzcoVar.zza) {
                                zzedVar2.zzg(zzcp.zzb(bArr, iZzj2));
                            }
                        }
                    }
                    return i11;
                }
                return iZzj2;
            case 25:
            case 42:
                if (i5 == 2) {
                    zzcq zzcqVar = (zzcq) zzekVarZzd;
                    iZzj = zzcp.zzj(bArr, iZzj2, zzcoVar);
                    int i17 = zzcoVar.zza + iZzj;
                    while (iZzj < i17) {
                        iZzj = zzcp.zzm(bArr, iZzj, zzcoVar);
                        zzcqVar.zze(zzcoVar.zzb != 0);
                    }
                    if (iZzj != i17) {
                        throw zzen.zzg();
                    }
                    return iZzj;
                }
                if (i5 == 0) {
                    zzcq zzcqVar2 = (zzcq) zzekVarZzd;
                    int iZzm2 = zzcp.zzm(bArr, iZzj2, zzcoVar);
                    zzcqVar2.zze(zzcoVar.zzb != 0);
                    while (iZzm2 < i2) {
                        int iZzj9 = zzcp.zzj(bArr, iZzm2, zzcoVar);
                        if (i3 != zzcoVar.zza) {
                            return iZzm2;
                        }
                        iZzm2 = zzcp.zzm(bArr, iZzj9, zzcoVar);
                        zzcqVar2.zze(zzcoVar.zzb != 0);
                    }
                    return iZzm2;
                }
                return iZzj2;
            case 26:
                if (i5 == 2) {
                    if ((j & 536870912) == 0) {
                        int iZzj10 = zzcp.zzj(bArr, iZzj2, zzcoVar);
                        int i18 = zzcoVar.zza;
                        if (i18 < 0) {
                            throw zzen.zzd();
                        }
                        if (i18 == 0) {
                            zzekVarZzd.add("");
                        } else {
                            zzekVarZzd.add(new String(bArr, iZzj10, i18, zzel.zzb));
                            iZzj10 += i18;
                        }
                        while (iZzj10 < i2) {
                            int iZzj11 = zzcp.zzj(bArr, iZzj10, zzcoVar);
                            if (i3 != zzcoVar.zza) {
                                return iZzj10;
                            }
                            iZzj10 = zzcp.zzj(bArr, iZzj11, zzcoVar);
                            int i19 = zzcoVar.zza;
                            if (i19 < 0) {
                                throw zzen.zzd();
                            }
                            if (i19 == 0) {
                                zzekVarZzd.add("");
                            } else {
                                zzekVarZzd.add(new String(bArr, iZzj10, i19, zzel.zzb));
                                iZzj10 += i19;
                            }
                        }
                        return iZzj10;
                    }
                    int iZzj12 = zzcp.zzj(bArr, iZzj2, zzcoVar);
                    int i20 = zzcoVar.zza;
                    if (i20 < 0) {
                        throw zzen.zzd();
                    }
                    if (i20 == 0) {
                        zzekVarZzd.add("");
                    } else {
                        int i21 = iZzj12 + i20;
                        if (!zzhe.zzi(bArr, iZzj12, i21)) {
                            throw zzen.zzc();
                        }
                        zzekVarZzd.add(new String(bArr, iZzj12, i20, zzel.zzb));
                        iZzj12 = i21;
                    }
                    while (iZzj12 < i2) {
                        int iZzj13 = zzcp.zzj(bArr, iZzj12, zzcoVar);
                        if (i3 != zzcoVar.zza) {
                            return iZzj12;
                        }
                        iZzj12 = zzcp.zzj(bArr, iZzj13, zzcoVar);
                        int i22 = zzcoVar.zza;
                        if (i22 < 0) {
                            throw zzen.zzd();
                        }
                        if (i22 == 0) {
                            zzekVarZzd.add("");
                        } else {
                            int i23 = iZzj12 + i22;
                            if (!zzhe.zzi(bArr, iZzj12, i23)) {
                                throw zzen.zzc();
                            }
                            zzekVarZzd.add(new String(bArr, iZzj12, i22, zzel.zzb));
                            iZzj12 = i23;
                        }
                    }
                    return iZzj12;
                }
                return iZzj2;
            case 27:
                if (i5 == 2) {
                    return zzcp.zze(zzE(i6), i3, bArr, i, i2, zzekVarZzd, zzcoVar);
                }
                return iZzj2;
            case 28:
                if (i5 == 2) {
                    int iZzj14 = zzcp.zzj(bArr, iZzj2, zzcoVar);
                    int i24 = zzcoVar.zza;
                    if (i24 < 0) {
                        throw zzen.zzd();
                    }
                    if (i24 > bArr.length - iZzj14) {
                        throw zzen.zzg();
                    }
                    if (i24 == 0) {
                        zzekVarZzd.add(zzdb.zzb);
                    } else {
                        zzekVarZzd.add(zzdb.zzr(bArr, iZzj14, i24));
                        iZzj14 += i24;
                    }
                    while (iZzj14 < i2) {
                        int iZzj15 = zzcp.zzj(bArr, iZzj14, zzcoVar);
                        if (i3 != zzcoVar.zza) {
                            return iZzj14;
                        }
                        iZzj14 = zzcp.zzj(bArr, iZzj15, zzcoVar);
                        int i25 = zzcoVar.zza;
                        if (i25 < 0) {
                            throw zzen.zzd();
                        }
                        if (i25 > bArr.length - iZzj14) {
                            throw zzen.zzg();
                        }
                        if (i25 == 0) {
                            zzekVarZzd.add(zzdb.zzb);
                        } else {
                            zzekVarZzd.add(zzdb.zzr(bArr, iZzj14, i25));
                            iZzj14 += i25;
                        }
                    }
                    return iZzj14;
                }
                return iZzj2;
            case 30:
            case 44:
                if (i5 != 2) {
                    if (i5 == 0) {
                        iZzj = zzcp.zzl(i3, bArr, i, i2, zzekVarZzd, zzcoVar);
                    }
                    return iZzj2;
                }
                iZzj = zzcp.zzf(bArr, iZzj2, zzekVarZzd, zzcoVar);
                zzec zzecVar = (zzec) obj;
                zzgq zzgqVar = zzecVar.zzc;
                if (zzgqVar == zzgq.zzc()) {
                    zzgqVar = null;
                }
                Object objZzC = zzgd.zzC(i4, zzekVarZzd, zzD(i6), zzgqVar, this.zzn);
                if (objZzC != null) {
                    zzecVar.zzc = (zzgq) objZzC;
                    return iZzj;
                }
                return iZzj;
            case 33:
            case 47:
                if (i5 == 2) {
                    zzed zzedVar3 = (zzed) zzekVarZzd;
                    int iZzj16 = zzcp.zzj(bArr, iZzj2, zzcoVar);
                    int i26 = zzcoVar.zza + iZzj16;
                    while (iZzj16 < i26) {
                        iZzj16 = zzcp.zzj(bArr, iZzj16, zzcoVar);
                        zzedVar3.zzg(zzde.zzb(zzcoVar.zza));
                    }
                    if (iZzj16 == i26) {
                        return iZzj16;
                    }
                    throw zzen.zzg();
                }
                if (i5 == 0) {
                    zzed zzedVar4 = (zzed) zzekVarZzd;
                    int iZzj17 = zzcp.zzj(bArr, iZzj2, zzcoVar);
                    zzedVar4.zzg(zzde.zzb(zzcoVar.zza));
                    while (iZzj17 < i2) {
                        int iZzj18 = zzcp.zzj(bArr, iZzj17, zzcoVar);
                        if (i3 != zzcoVar.zza) {
                            return iZzj17;
                        }
                        iZzj17 = zzcp.zzj(bArr, iZzj18, zzcoVar);
                        zzedVar4.zzg(zzde.zzb(zzcoVar.zza));
                    }
                    return iZzj17;
                }
                return iZzj2;
            case 34:
            case 48:
                if (i5 == 2) {
                    zzfa zzfaVar5 = (zzfa) zzekVarZzd;
                    int iZzj19 = zzcp.zzj(bArr, iZzj2, zzcoVar);
                    int i27 = zzcoVar.zza + iZzj19;
                    while (iZzj19 < i27) {
                        iZzj19 = zzcp.zzm(bArr, iZzj19, zzcoVar);
                        zzfaVar5.zzf(zzde.zzc(zzcoVar.zzb));
                    }
                    if (iZzj19 == i27) {
                        return iZzj19;
                    }
                    throw zzen.zzg();
                }
                if (i5 == 0) {
                    zzfa zzfaVar6 = (zzfa) zzekVarZzd;
                    int iZzm3 = zzcp.zzm(bArr, iZzj2, zzcoVar);
                    zzfaVar6.zzf(zzde.zzc(zzcoVar.zzb));
                    while (iZzm3 < i2) {
                        int iZzj20 = zzcp.zzj(bArr, iZzm3, zzcoVar);
                        if (i3 != zzcoVar.zza) {
                            return iZzm3;
                        }
                        iZzm3 = zzcp.zzm(bArr, iZzj20, zzcoVar);
                        zzfaVar6.zzf(zzde.zzc(zzcoVar.zzb));
                    }
                    return iZzm3;
                }
                return iZzj2;
            default:
                if (i5 == 3) {
                    zzgb zzgbVarZzE = zzE(i6);
                    int i28 = (i3 & (-8)) | 4;
                    int iZzc = zzcp.zzc(zzgbVarZzE, bArr, i, i2, i28, zzcoVar);
                    zzekVarZzd.add(zzcoVar.zzc);
                    while (iZzc < i2) {
                        int iZzj21 = zzcp.zzj(bArr, iZzc, zzcoVar);
                        if (i3 != zzcoVar.zza) {
                            return iZzc;
                        }
                        iZzc = zzcp.zzc(zzgbVarZzE, bArr, iZzj21, i2, i28, zzcoVar);
                        zzekVarZzd.add(zzcoVar.zzc);
                    }
                    return iZzc;
                }
                return iZzj2;
        }
    }

    private final int zzw(int i) {
        if (i < this.zze || i > this.zzf) {
            return -1;
        }
        return zzz(i, 0);
    }

    private final int zzx(int i, int i2) {
        if (i < this.zze || i > this.zzf) {
            return -1;
        }
        return zzz(i, i2);
    }

    private final int zzy(int i) {
        return this.zzc[i + 2];
    }

    private final int zzz(int i, int i2) {
        int length = (this.zzc.length / 3) - 1;
        while (i2 <= length) {
            int i3 = (length + i2) >>> 1;
            int i4 = i3 * 3;
            int i5 = this.zzc[i4];
            if (i == i5) {
                return i4;
            }
            if (i < i5) {
                length = i3 - 1;
            } else {
                i2 = i3 + 1;
            }
        }
        return -1;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgb
    public final int zza(Object obj) {
        return this.zzi ? zzq(obj) : zzp(obj);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgb
    public final int zzb(Object obj) {
        int i;
        int iZzc;
        int length = this.zzc.length;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3 += 3) {
            int iZzB = zzB(i3);
            int i4 = this.zzc[i3];
            long j = 1048575 & iZzB;
            int iHashCode = 37;
            switch (zzA(iZzB)) {
                case 0:
                    i = i2 * 53;
                    iZzc = zzel.zzc(Double.doubleToLongBits(zzgz.zza(obj, j)));
                    i2 = i + iZzc;
                    break;
                case 1:
                    i = i2 * 53;
                    iZzc = Float.floatToIntBits(zzgz.zzb(obj, j));
                    i2 = i + iZzc;
                    break;
                case 2:
                    i = i2 * 53;
                    iZzc = zzel.zzc(zzgz.zzd(obj, j));
                    i2 = i + iZzc;
                    break;
                case 3:
                    i = i2 * 53;
                    iZzc = zzel.zzc(zzgz.zzd(obj, j));
                    i2 = i + iZzc;
                    break;
                case 4:
                    i = i2 * 53;
                    iZzc = zzgz.zzc(obj, j);
                    i2 = i + iZzc;
                    break;
                case 5:
                    i = i2 * 53;
                    iZzc = zzel.zzc(zzgz.zzd(obj, j));
                    i2 = i + iZzc;
                    break;
                case 6:
                    i = i2 * 53;
                    iZzc = zzgz.zzc(obj, j);
                    i2 = i + iZzc;
                    break;
                case 7:
                    i = i2 * 53;
                    iZzc = zzel.zza(zzgz.zzw(obj, j));
                    i2 = i + iZzc;
                    break;
                case 8:
                    i = i2 * 53;
                    iZzc = ((String) zzgz.zzf(obj, j)).hashCode();
                    i2 = i + iZzc;
                    break;
                case 9:
                    Object objZzf = zzgz.zzf(obj, j);
                    if (objZzf != null) {
                        iHashCode = objZzf.hashCode();
                    }
                    i2 = (i2 * 53) + iHashCode;
                    break;
                case 10:
                    i = i2 * 53;
                    iZzc = zzgz.zzf(obj, j).hashCode();
                    i2 = i + iZzc;
                    break;
                case 11:
                    i = i2 * 53;
                    iZzc = zzgz.zzc(obj, j);
                    i2 = i + iZzc;
                    break;
                case 12:
                    i = i2 * 53;
                    iZzc = zzgz.zzc(obj, j);
                    i2 = i + iZzc;
                    break;
                case 13:
                    i = i2 * 53;
                    iZzc = zzgz.zzc(obj, j);
                    i2 = i + iZzc;
                    break;
                case 14:
                    i = i2 * 53;
                    iZzc = zzel.zzc(zzgz.zzd(obj, j));
                    i2 = i + iZzc;
                    break;
                case 15:
                    i = i2 * 53;
                    iZzc = zzgz.zzc(obj, j);
                    i2 = i + iZzc;
                    break;
                case 16:
                    i = i2 * 53;
                    iZzc = zzel.zzc(zzgz.zzd(obj, j));
                    i2 = i + iZzc;
                    break;
                case 17:
                    Object objZzf2 = zzgz.zzf(obj, j);
                    if (objZzf2 != null) {
                        iHashCode = objZzf2.hashCode();
                    }
                    i2 = (i2 * 53) + iHashCode;
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    i = i2 * 53;
                    iZzc = zzgz.zzf(obj, j).hashCode();
                    i2 = i + iZzc;
                    break;
                case 50:
                    i = i2 * 53;
                    iZzc = zzgz.zzf(obj, j).hashCode();
                    i2 = i + iZzc;
                    break;
                case 51:
                    if (zzP(obj, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzel.zzc(Double.doubleToLongBits(zzn(obj, j)));
                        i2 = i + iZzc;
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (zzP(obj, i4, i3)) {
                        i = i2 * 53;
                        iZzc = Float.floatToIntBits(zzo(obj, j));
                        i2 = i + iZzc;
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (zzP(obj, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzel.zzc(zzC(obj, j));
                        i2 = i + iZzc;
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (zzP(obj, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzel.zzc(zzC(obj, j));
                        i2 = i + iZzc;
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (zzP(obj, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzr(obj, j);
                        i2 = i + iZzc;
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (zzP(obj, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzel.zzc(zzC(obj, j));
                        i2 = i + iZzc;
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (zzP(obj, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzr(obj, j);
                        i2 = i + iZzc;
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (zzP(obj, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzel.zza(zzQ(obj, j));
                        i2 = i + iZzc;
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (zzP(obj, i4, i3)) {
                        i = i2 * 53;
                        iZzc = ((String) zzgz.zzf(obj, j)).hashCode();
                        i2 = i + iZzc;
                        break;
                    } else {
                        break;
                    }
                case 60:
                    if (zzP(obj, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzgz.zzf(obj, j).hashCode();
                        i2 = i + iZzc;
                        break;
                    } else {
                        break;
                    }
                case 61:
                    if (zzP(obj, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzgz.zzf(obj, j).hashCode();
                        i2 = i + iZzc;
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (zzP(obj, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzr(obj, j);
                        i2 = i + iZzc;
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (zzP(obj, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzr(obj, j);
                        i2 = i + iZzc;
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (zzP(obj, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzr(obj, j);
                        i2 = i + iZzc;
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (zzP(obj, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzel.zzc(zzC(obj, j));
                        i2 = i + iZzc;
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (zzP(obj, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzr(obj, j);
                        i2 = i + iZzc;
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (zzP(obj, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzel.zzc(zzC(obj, j));
                        i2 = i + iZzc;
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (zzP(obj, i4, i3)) {
                        i = i2 * 53;
                        iZzc = zzgz.zzf(obj, j).hashCode();
                        i2 = i + iZzc;
                        break;
                    } else {
                        break;
                    }
            }
        }
        int iHashCode2 = (i2 * 53) + this.zzn.zzc(obj).hashCode();
        return this.zzh ? (iHashCode2 * 53) + this.zzo.zzb(obj).zza.hashCode() : iHashCode2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:196:0x0637, code lost:
    
        if (r2 == r3) goto L198;
     */
    /* JADX WARN: Code restructure failed: missing block: B:197:0x0639, code lost:
    
        r29.putInt(r13, r2, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:198:0x063f, code lost:
    
        r2 = r9.zzk;
     */
    /* JADX WARN: Code restructure failed: missing block: B:200:0x0643, code lost:
    
        if (r2 >= r9.zzl) goto L285;
     */
    /* JADX WARN: Code restructure failed: missing block: B:201:0x0645, code lost:
    
        r4 = r9.zzj[r2];
        r5 = r9.zzc[r4];
        r5 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgz.zzf(r13, r9.zzB(r4) & r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:202:0x0657, code lost:
    
        if (r5 != null) goto L204;
     */
    /* JADX WARN: Code restructure failed: missing block: B:205:0x065e, code lost:
    
        if (r9.zzD(r4) != null) goto L286;
     */
    /* JADX WARN: Code restructure failed: missing block: B:206:0x0660, code lost:
    
        r2 = r2 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:207:0x0663, code lost:
    
        r5 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzff) r5;
        r0 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfe) r9.zzF(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:208:0x066b, code lost:
    
        throw null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:209:0x066c, code lost:
    
        if (r7 != 0) goto L214;
     */
    /* JADX WARN: Code restructure failed: missing block: B:210:0x066e, code lost:
    
        if (r0 != r6) goto L212;
     */
    /* JADX WARN: Code restructure failed: missing block: B:213:0x0675, code lost:
    
        throw com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzen.zze();
     */
    /* JADX WARN: Code restructure failed: missing block: B:214:0x0676, code lost:
    
        if (r0 > r6) goto L217;
     */
    /* JADX WARN: Code restructure failed: missing block: B:215:0x0678, code lost:
    
        if (r1 != r7) goto L217;
     */
    /* JADX WARN: Code restructure failed: missing block: B:216:0x067a, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:218:0x067f, code lost:
    
        throw com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzen.zze();
     */
    /* JADX WARN: Removed duplicated region for block: B:182:0x05d6  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x05db  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    final int zzc(java.lang.Object r31, byte[] r32, int r33, int r34, int r35, com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzco r36) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1742
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfo.zzc(java.lang.Object, byte[], int, int, int, com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzco):int");
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgb
    public final Object zze() {
        return ((zzec) this.zzg).zzg(4, null, null);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgb
    public final void zzf(Object obj) {
        int i;
        int i2 = this.zzk;
        while (true) {
            i = this.zzl;
            if (i2 >= i) {
                break;
            }
            long jZzB = zzB(this.zzj[i2]) & 1048575;
            Object objZzf = zzgz.zzf(obj, jZzB);
            if (objZzf != null) {
                ((zzff) objZzf).zzc();
                zzgz.zzs(obj, jZzB, objZzf);
            }
            i2++;
        }
        int length = this.zzj.length;
        while (i < length) {
            this.zzm.zza(obj, this.zzj[i]);
            i++;
        }
        this.zzn.zzg(obj);
        if (this.zzh) {
            this.zzo.zze(obj);
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgb
    public final void zzh(Object obj, byte[] bArr, int i, int i2, zzco zzcoVar) throws IOException {
        if (this.zzi) {
            zzu(obj, bArr, i, i2, zzcoVar);
        } else {
            zzc(obj, bArr, i, i2, 0, zzcoVar);
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgb
    public final boolean zzi(Object obj, Object obj2) {
        boolean zZzH;
        int length = this.zzc.length;
        for (int i = 0; i < length; i += 3) {
            int iZzB = zzB(i);
            long j = iZzB & 1048575;
            switch (zzA(iZzB)) {
                case 0:
                    if (!zzL(obj, obj2, i) || Double.doubleToLongBits(zzgz.zza(obj, j)) != Double.doubleToLongBits(zzgz.zza(obj2, j))) {
                        return false;
                    }
                    continue;
                    break;
                case 1:
                    if (!zzL(obj, obj2, i) || Float.floatToIntBits(zzgz.zzb(obj, j)) != Float.floatToIntBits(zzgz.zzb(obj2, j))) {
                        return false;
                    }
                    continue;
                    break;
                case 2:
                    if (!zzL(obj, obj2, i) || zzgz.zzd(obj, j) != zzgz.zzd(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                case 3:
                    if (!zzL(obj, obj2, i) || zzgz.zzd(obj, j) != zzgz.zzd(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                case 4:
                    if (!zzL(obj, obj2, i) || zzgz.zzc(obj, j) != zzgz.zzc(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                case 5:
                    if (!zzL(obj, obj2, i) || zzgz.zzd(obj, j) != zzgz.zzd(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                case 6:
                    if (!zzL(obj, obj2, i) || zzgz.zzc(obj, j) != zzgz.zzc(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                case 7:
                    if (!zzL(obj, obj2, i) || zzgz.zzw(obj, j) != zzgz.zzw(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                case 8:
                    if (!zzL(obj, obj2, i) || !zzgd.zzH(zzgz.zzf(obj, j), zzgz.zzf(obj2, j))) {
                        return false;
                    }
                    continue;
                    break;
                case 9:
                    if (!zzL(obj, obj2, i) || !zzgd.zzH(zzgz.zzf(obj, j), zzgz.zzf(obj2, j))) {
                        return false;
                    }
                    continue;
                    break;
                case 10:
                    if (!zzL(obj, obj2, i) || !zzgd.zzH(zzgz.zzf(obj, j), zzgz.zzf(obj2, j))) {
                        return false;
                    }
                    continue;
                    break;
                case 11:
                    if (!zzL(obj, obj2, i) || zzgz.zzc(obj, j) != zzgz.zzc(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                case 12:
                    if (!zzL(obj, obj2, i) || zzgz.zzc(obj, j) != zzgz.zzc(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                case 13:
                    if (!zzL(obj, obj2, i) || zzgz.zzc(obj, j) != zzgz.zzc(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                case 14:
                    if (!zzL(obj, obj2, i) || zzgz.zzd(obj, j) != zzgz.zzd(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                case 15:
                    if (!zzL(obj, obj2, i) || zzgz.zzc(obj, j) != zzgz.zzc(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                case 16:
                    if (!zzL(obj, obj2, i) || zzgz.zzd(obj, j) != zzgz.zzd(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                case 17:
                    if (!zzL(obj, obj2, i) || !zzgd.zzH(zzgz.zzf(obj, j), zzgz.zzf(obj2, j))) {
                        return false;
                    }
                    continue;
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    zZzH = zzgd.zzH(zzgz.zzf(obj, j), zzgz.zzf(obj2, j));
                    break;
                case 50:
                    zZzH = zzgd.zzH(zzgz.zzf(obj, j), zzgz.zzf(obj2, j));
                    break;
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                case 60:
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                case 68:
                    long jZzy = zzy(i) & 1048575;
                    if (zzgz.zzc(obj, jZzy) != zzgz.zzc(obj2, jZzy) || !zzgd.zzH(zzgz.zzf(obj, j), zzgz.zzf(obj2, j))) {
                        return false;
                    }
                    continue;
                    break;
                default:
            }
            if (!zZzH) {
                return false;
            }
        }
        if (!this.zzn.zzc(obj).equals(this.zzn.zzc(obj2))) {
            return false;
        }
        if (this.zzh) {
            return this.zzo.zzb(obj).equals(this.zzo.zzb(obj2));
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x009b  */
    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgb
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean zzj(java.lang.Object r18) {
        /*
            Method dump skipped, instructions count: 246
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfo.zzj(java.lang.Object):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0022  */
    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgb
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void zzm(java.lang.Object r13, com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj r14) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1464
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfo.zzm(java.lang.Object, com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj):void");
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgb
    public final void zzg(Object obj, Object obj2) {
        obj2.getClass();
        for (int i = 0; i < this.zzc.length; i += 3) {
            int iZzB = zzB(i);
            long j = 1048575 & iZzB;
            int i2 = this.zzc[i];
            switch (zzA(iZzB)) {
                case 0:
                    if (zzM(obj2, i)) {
                        zzgz.zzo(obj, j, zzgz.zza(obj2, j));
                        zzJ(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (zzM(obj2, i)) {
                        zzgz.zzp(obj, j, zzgz.zzb(obj2, j));
                        zzJ(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (zzM(obj2, i)) {
                        zzgz.zzr(obj, j, zzgz.zzd(obj2, j));
                        zzJ(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (zzM(obj2, i)) {
                        zzgz.zzr(obj, j, zzgz.zzd(obj2, j));
                        zzJ(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (zzM(obj2, i)) {
                        zzgz.zzq(obj, j, zzgz.zzc(obj2, j));
                        zzJ(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (zzM(obj2, i)) {
                        zzgz.zzr(obj, j, zzgz.zzd(obj2, j));
                        zzJ(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (zzM(obj2, i)) {
                        zzgz.zzq(obj, j, zzgz.zzc(obj2, j));
                        zzJ(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if (zzM(obj2, i)) {
                        zzgz.zzm(obj, j, zzgz.zzw(obj2, j));
                        zzJ(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (zzM(obj2, i)) {
                        zzgz.zzs(obj, j, zzgz.zzf(obj2, j));
                        zzJ(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 9:
                    zzH(obj, obj2, i);
                    break;
                case 10:
                    if (zzM(obj2, i)) {
                        zzgz.zzs(obj, j, zzgz.zzf(obj2, j));
                        zzJ(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if (zzM(obj2, i)) {
                        zzgz.zzq(obj, j, zzgz.zzc(obj2, j));
                        zzJ(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if (zzM(obj2, i)) {
                        zzgz.zzq(obj, j, zzgz.zzc(obj2, j));
                        zzJ(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if (zzM(obj2, i)) {
                        zzgz.zzq(obj, j, zzgz.zzc(obj2, j));
                        zzJ(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if (zzM(obj2, i)) {
                        zzgz.zzr(obj, j, zzgz.zzd(obj2, j));
                        zzJ(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if (zzM(obj2, i)) {
                        zzgz.zzq(obj, j, zzgz.zzc(obj2, j));
                        zzJ(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if (zzM(obj2, i)) {
                        zzgz.zzr(obj, j, zzgz.zzd(obj2, j));
                        zzJ(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 17:
                    zzH(obj, obj2, i);
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    this.zzm.zzb(obj, obj2, j);
                    break;
                case 50:
                    zzgd.zzI(this.zzq, obj, obj2, j);
                    break;
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                    if (zzP(obj2, i2, i)) {
                        zzgz.zzs(obj, j, zzgz.zzf(obj2, j));
                        zzK(obj, i2, i);
                        break;
                    } else {
                        break;
                    }
                case 60:
                    zzI(obj, obj2, i);
                    break;
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                    if (zzP(obj2, i2, i)) {
                        zzgz.zzs(obj, j, zzgz.zzf(obj2, j));
                        zzK(obj, i2, i);
                        break;
                    } else {
                        break;
                    }
                case 68:
                    zzI(obj, obj2, i);
                    break;
            }
        }
        zzgd.zzF(this.zzn, obj, obj2);
        if (this.zzh) {
            zzgd.zzE(this.zzo, obj, obj2);
        }
    }
}
