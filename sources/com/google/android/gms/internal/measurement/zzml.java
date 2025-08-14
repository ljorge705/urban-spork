package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;
import sun.misc.Unsafe;

/* compiled from: com.google.android.gms:play-services-measurement-base@@21.3.0 */
/* loaded from: classes3.dex */
final class zzml<T> implements zzmt<T> {
    private static final int[] zza = new int[0];
    private static final Unsafe zzb = zznu.zzg();
    private final int[] zzc;
    private final Object[] zzd;
    private final int zze;
    private final int zzf;
    private final zzmi zzg;
    private final boolean zzh;
    private final boolean zzi;
    private final int[] zzj;
    private final int zzk;
    private final int zzl;
    private final zzlw zzm;
    private final zznk zzn;
    private final zzko zzo;
    private final zzmn zzp;
    private final zzmd zzq;

    private zzml(int[] iArr, Object[] objArr, int i, int i2, zzmi zzmiVar, boolean z, boolean z2, int[] iArr2, int i3, int i4, zzmn zzmnVar, zzlw zzlwVar, zznk zznkVar, zzko zzkoVar, zzmd zzmdVar) {
        this.zzc = iArr;
        this.zzd = objArr;
        this.zze = i;
        this.zzf = i2;
        this.zzi = z;
        boolean z3 = false;
        if (zzkoVar != null && zzkoVar.zzc(zzmiVar)) {
            z3 = true;
        }
        this.zzh = z3;
        this.zzj = iArr2;
        this.zzk = i3;
        this.zzl = i4;
        this.zzp = zzmnVar;
        this.zzm = zzlwVar;
        this.zzn = zznkVar;
        this.zzo = zzkoVar;
        this.zzg = zzmiVar;
        this.zzq = zzmdVar;
    }

    private final zzlf zzA(int i) {
        int i2 = i / 3;
        return (zzlf) this.zzd[i2 + i2 + 1];
    }

    private final zzmt zzB(int i) {
        int i2 = i / 3;
        int i3 = i2 + i2;
        zzmt zzmtVar = (zzmt) this.zzd[i3];
        if (zzmtVar != null) {
            return zzmtVar;
        }
        zzmt zzmtVarZzb = zzmq.zza().zzb((Class) this.zzd[i3 + 1]);
        this.zzd[i3] = zzmtVarZzb;
        return zzmtVarZzb;
    }

    private final Object zzC(int i) {
        int i2 = i / 3;
        return this.zzd[i2 + i2];
    }

    private final Object zzD(Object obj, int i) {
        zzmt zzmtVarZzB = zzB(i);
        int iZzy = zzy(i) & 1048575;
        if (!zzP(obj, i)) {
            return zzmtVarZzB.zze();
        }
        Object object = zzb.getObject(obj, iZzy);
        if (zzS(object)) {
            return object;
        }
        Object objZze = zzmtVarZzB.zze();
        if (object != null) {
            zzmtVarZzB.zzg(objZze, object);
        }
        return objZze;
    }

    private final Object zzE(Object obj, int i, int i2) {
        zzmt zzmtVarZzB = zzB(i2);
        if (!zzT(obj, i, i2)) {
            return zzmtVarZzB.zze();
        }
        Object object = zzb.getObject(obj, zzy(i2) & 1048575);
        if (zzS(object)) {
            return object;
        }
        Object objZze = zzmtVarZzB.zze();
        if (object != null) {
            zzmtVarZzB.zzg(objZze, object);
        }
        return objZze;
    }

    private static Field zzF(Class cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            throw new RuntimeException("Field " + str + " for " + cls.getName() + " not found. Known fields are " + Arrays.toString(declaredFields));
        }
    }

    private static void zzG(Object obj) {
        if (!zzS(obj)) {
            throw new IllegalArgumentException("Mutating immutable message: ".concat(String.valueOf(String.valueOf(obj))));
        }
    }

    private final void zzH(Object obj, Object obj2, int i) {
        if (zzP(obj2, i)) {
            int iZzy = zzy(i) & 1048575;
            Unsafe unsafe = zzb;
            long j = iZzy;
            Object object = unsafe.getObject(obj2, j);
            if (object == null) {
                throw new IllegalStateException("Source subfield " + this.zzc[i] + " is present but null: " + obj2.toString());
            }
            zzmt zzmtVarZzB = zzB(i);
            if (!zzP(obj, i)) {
                if (zzS(object)) {
                    Object objZze = zzmtVarZzB.zze();
                    zzmtVarZzB.zzg(objZze, object);
                    unsafe.putObject(obj, j, objZze);
                } else {
                    unsafe.putObject(obj, j, object);
                }
                zzJ(obj, i);
                return;
            }
            Object object2 = unsafe.getObject(obj, j);
            if (!zzS(object2)) {
                Object objZze2 = zzmtVarZzB.zze();
                zzmtVarZzB.zzg(objZze2, object2);
                unsafe.putObject(obj, j, objZze2);
                object2 = objZze2;
            }
            zzmtVarZzB.zzg(object2, object);
        }
    }

    private final void zzI(Object obj, Object obj2, int i) {
        int i2 = this.zzc[i];
        if (zzT(obj2, i2, i)) {
            int iZzy = zzy(i) & 1048575;
            Unsafe unsafe = zzb;
            long j = iZzy;
            Object object = unsafe.getObject(obj2, j);
            if (object == null) {
                throw new IllegalStateException("Source subfield " + this.zzc[i] + " is present but null: " + obj2.toString());
            }
            zzmt zzmtVarZzB = zzB(i);
            if (!zzT(obj, i2, i)) {
                if (zzS(object)) {
                    Object objZze = zzmtVarZzB.zze();
                    zzmtVarZzB.zzg(objZze, object);
                    unsafe.putObject(obj, j, objZze);
                } else {
                    unsafe.putObject(obj, j, object);
                }
                zzK(obj, i2, i);
                return;
            }
            Object object2 = unsafe.getObject(obj, j);
            if (!zzS(object2)) {
                Object objZze2 = zzmtVarZzB.zze();
                zzmtVarZzB.zzg(objZze2, object2);
                unsafe.putObject(obj, j, objZze2);
                object2 = objZze2;
            }
            zzmtVarZzB.zzg(object2, object);
        }
    }

    private final void zzJ(Object obj, int i) {
        int iZzv = zzv(i);
        long j = 1048575 & iZzv;
        if (j == 1048575) {
            return;
        }
        zznu.zzq(obj, j, (1 << (iZzv >>> 20)) | zznu.zzc(obj, j));
    }

    private final void zzK(Object obj, int i, int i2) {
        zznu.zzq(obj, zzv(i2) & 1048575, i);
    }

    private final void zzL(Object obj, int i, Object obj2) {
        zzb.putObject(obj, zzy(i) & 1048575, obj2);
        zzJ(obj, i);
    }

    private final void zzM(Object obj, int i, int i2, Object obj2) {
        zzb.putObject(obj, zzy(i2) & 1048575, obj2);
        zzK(obj, i, i2);
    }

    private final void zzN(zzoc zzocVar, int i, Object obj, int i2) throws IOException {
        if (obj == null) {
            return;
        }
        throw null;
    }

    private final boolean zzO(Object obj, Object obj2, int i) {
        return zzP(obj, i) == zzP(obj2, i);
    }

    private final boolean zzP(Object obj, int i) {
        int iZzv = zzv(i);
        long j = iZzv & 1048575;
        if (j != 1048575) {
            return (zznu.zzc(obj, j) & (1 << (iZzv >>> 20))) != 0;
        }
        int iZzy = zzy(i);
        long j2 = iZzy & 1048575;
        switch (zzx(iZzy)) {
            case 0:
                return Double.doubleToRawLongBits(zznu.zza(obj, j2)) != 0;
            case 1:
                return Float.floatToRawIntBits(zznu.zzb(obj, j2)) != 0;
            case 2:
                return zznu.zzd(obj, j2) != 0;
            case 3:
                return zznu.zzd(obj, j2) != 0;
            case 4:
                return zznu.zzc(obj, j2) != 0;
            case 5:
                return zznu.zzd(obj, j2) != 0;
            case 6:
                return zznu.zzc(obj, j2) != 0;
            case 7:
                return zznu.zzw(obj, j2);
            case 8:
                Object objZzf = zznu.zzf(obj, j2);
                if (objZzf instanceof String) {
                    return !((String) objZzf).isEmpty();
                }
                if (objZzf instanceof zzka) {
                    return !zzka.zzb.equals(objZzf);
                }
                throw new IllegalArgumentException();
            case 9:
                return zznu.zzf(obj, j2) != null;
            case 10:
                return !zzka.zzb.equals(zznu.zzf(obj, j2));
            case 11:
                return zznu.zzc(obj, j2) != 0;
            case 12:
                return zznu.zzc(obj, j2) != 0;
            case 13:
                return zznu.zzc(obj, j2) != 0;
            case 14:
                return zznu.zzd(obj, j2) != 0;
            case 15:
                return zznu.zzc(obj, j2) != 0;
            case 16:
                return zznu.zzd(obj, j2) != 0;
            case 17:
                return zznu.zzf(obj, j2) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    private final boolean zzQ(Object obj, int i, int i2, int i3, int i4) {
        return i2 == 1048575 ? zzP(obj, i) : (i3 & i4) != 0;
    }

    private static boolean zzR(Object obj, int i, zzmt zzmtVar) {
        return zzmtVar.zzk(zznu.zzf(obj, i & 1048575));
    }

    private static boolean zzS(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof zzlb) {
            return ((zzlb) obj).zzbR();
        }
        return true;
    }

    private final boolean zzT(Object obj, int i, int i2) {
        return zznu.zzc(obj, (long) (zzv(i2) & 1048575)) == i;
    }

    private static boolean zzU(Object obj, long j) {
        return ((Boolean) zznu.zzf(obj, j)).booleanValue();
    }

    private static final void zzV(int i, Object obj, zzoc zzocVar) throws IOException {
        if (obj instanceof String) {
            zzocVar.zzF(i, (String) obj);
        } else {
            zzocVar.zzd(i, (zzka) obj);
        }
    }

    static zznl zzd(Object obj) {
        zzlb zzlbVar = (zzlb) obj;
        zznl zznlVar = zzlbVar.zzc;
        if (zznlVar != zznl.zzc()) {
            return zznlVar;
        }
        zznl zznlVarZzf = zznl.zzf();
        zzlbVar.zzc = zznlVarZzf;
        return zznlVarZzf;
    }

    /* JADX WARN: Removed duplicated region for block: B:124:0x026d  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0270  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0285  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0288  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x033b  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x0384  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static com.google.android.gms.internal.measurement.zzml zzl(java.lang.Class r32, com.google.android.gms.internal.measurement.zzmf r33, com.google.android.gms.internal.measurement.zzmn r34, com.google.android.gms.internal.measurement.zzlw r35, com.google.android.gms.internal.measurement.zznk r36, com.google.android.gms.internal.measurement.zzko r37, com.google.android.gms.internal.measurement.zzmd r38) {
        /*
            Method dump skipped, instructions count: 1012
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzml.zzl(java.lang.Class, com.google.android.gms.internal.measurement.zzmf, com.google.android.gms.internal.measurement.zzmn, com.google.android.gms.internal.measurement.zzlw, com.google.android.gms.internal.measurement.zznk, com.google.android.gms.internal.measurement.zzko, com.google.android.gms.internal.measurement.zzmd):com.google.android.gms.internal.measurement.zzml");
    }

    private static double zzm(Object obj, long j) {
        return ((Double) zznu.zzf(obj, j)).doubleValue();
    }

    private static float zzn(Object obj, long j) {
        return ((Float) zznu.zzf(obj, j)).floatValue();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private final int zzo(Object obj) {
        int i;
        int iZzx;
        int iZzx2;
        int iZzy;
        int iZzx3;
        int iZzx4;
        int iZzx5;
        int iZzx6;
        int iZzt;
        boolean z;
        int iZzc;
        int iZzh;
        int iZzx7;
        int iZzx8;
        int iZzx9;
        int iZzx10;
        int iZzx11;
        int iZzx12;
        int iZzx13;
        Unsafe unsafe = zzb;
        int i2 = 1048575;
        int i3 = 1048575;
        int i4 = 0;
        int iZzx14 = 0;
        int i5 = 0;
        while (i4 < this.zzc.length) {
            int iZzy2 = zzy(i4);
            int[] iArr = this.zzc;
            int i6 = iArr[i4];
            int iZzx15 = zzx(iZzy2);
            if (iZzx15 <= 17) {
                int i7 = iArr[i4 + 2];
                int i8 = i7 & i2;
                int i9 = i7 >>> 20;
                if (i8 != i3) {
                    i5 = unsafe.getInt(obj, i8);
                    i3 = i8;
                }
                i = 1 << i9;
            } else {
                i = 0;
            }
            long j = iZzy2 & i2;
            switch (iZzx15) {
                case 0:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        iZzx = zzki.zzx(i6 << 3);
                        iZzx4 = iZzx + 8;
                        iZzx14 += iZzx4;
                        break;
                    }
                case 1:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        iZzx2 = zzki.zzx(i6 << 3);
                        iZzx4 = iZzx2 + 4;
                        iZzx14 += iZzx4;
                        break;
                    }
                case 2:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        iZzy = zzki.zzy(unsafe.getLong(obj, j));
                        iZzx3 = zzki.zzx(i6 << 3);
                        iZzx14 += iZzx3 + iZzy;
                        break;
                    }
                case 3:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        iZzy = zzki.zzy(unsafe.getLong(obj, j));
                        iZzx3 = zzki.zzx(i6 << 3);
                        iZzx14 += iZzx3 + iZzy;
                        break;
                    }
                case 4:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        iZzy = zzki.zzu(unsafe.getInt(obj, j));
                        iZzx3 = zzki.zzx(i6 << 3);
                        iZzx14 += iZzx3 + iZzy;
                        break;
                    }
                case 5:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        iZzx = zzki.zzx(i6 << 3);
                        iZzx4 = iZzx + 8;
                        iZzx14 += iZzx4;
                        break;
                    }
                case 6:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        iZzx2 = zzki.zzx(i6 << 3);
                        iZzx4 = iZzx2 + 4;
                        iZzx14 += iZzx4;
                        break;
                    }
                case 7:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        iZzx4 = zzki.zzx(i6 << 3) + 1;
                        iZzx14 += iZzx4;
                        break;
                    }
                case 8:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        Object object = unsafe.getObject(obj, j);
                        if (!(object instanceof zzka)) {
                            iZzy = zzki.zzw((String) object);
                            iZzx3 = zzki.zzx(i6 << 3);
                            iZzx14 += iZzx3 + iZzy;
                            break;
                        } else {
                            int i10 = zzki.zzb;
                            int iZzd = ((zzka) object).zzd();
                            iZzx5 = zzki.zzx(iZzd) + iZzd;
                            iZzx6 = zzki.zzx(i6 << 3);
                            iZzx4 = iZzx6 + iZzx5;
                            iZzx14 += iZzx4;
                            break;
                        }
                    }
                case 9:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        iZzx4 = zzmv.zzn(i6, unsafe.getObject(obj, j), zzB(i4));
                        iZzx14 += iZzx4;
                        break;
                    }
                case 10:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzka zzkaVar = (zzka) unsafe.getObject(obj, j);
                        int i11 = zzki.zzb;
                        int iZzd2 = zzkaVar.zzd();
                        iZzx5 = zzki.zzx(iZzd2) + iZzd2;
                        iZzx6 = zzki.zzx(i6 << 3);
                        iZzx4 = iZzx6 + iZzx5;
                        iZzx14 += iZzx4;
                        break;
                    }
                case 11:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        iZzy = zzki.zzx(unsafe.getInt(obj, j));
                        iZzx3 = zzki.zzx(i6 << 3);
                        iZzx14 += iZzx3 + iZzy;
                        break;
                    }
                case 12:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        iZzy = zzki.zzu(unsafe.getInt(obj, j));
                        iZzx3 = zzki.zzx(i6 << 3);
                        iZzx14 += iZzx3 + iZzy;
                        break;
                    }
                case 13:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        iZzx2 = zzki.zzx(i6 << 3);
                        iZzx4 = iZzx2 + 4;
                        iZzx14 += iZzx4;
                        break;
                    }
                case 14:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        iZzx = zzki.zzx(i6 << 3);
                        iZzx4 = iZzx + 8;
                        iZzx14 += iZzx4;
                        break;
                    }
                case 15:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        int i12 = unsafe.getInt(obj, j);
                        iZzx3 = zzki.zzx(i6 << 3);
                        iZzy = zzki.zzx((i12 >> 31) ^ (i12 + i12));
                        iZzx14 += iZzx3 + iZzy;
                        break;
                    }
                case 16:
                    if ((i & i5) == 0) {
                        break;
                    } else {
                        long j2 = unsafe.getLong(obj, j);
                        iZzx14 += zzki.zzx(i6 << 3) + zzki.zzy((j2 >> 63) ^ (j2 + j2));
                        break;
                    }
                case 17:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        iZzx4 = zzki.zzt(i6, (zzmi) unsafe.getObject(obj, j), zzB(i4));
                        iZzx14 += iZzx4;
                        break;
                    }
                case 18:
                    iZzx4 = zzmv.zzg(i6, (List) unsafe.getObject(obj, j), false);
                    iZzx14 += iZzx4;
                    break;
                case 19:
                    iZzx4 = zzmv.zze(i6, (List) unsafe.getObject(obj, j), false);
                    iZzx14 += iZzx4;
                    break;
                case 20:
                    iZzx4 = zzmv.zzl(i6, (List) unsafe.getObject(obj, j), false);
                    iZzx14 += iZzx4;
                    break;
                case 21:
                    iZzx4 = zzmv.zzw(i6, (List) unsafe.getObject(obj, j), false);
                    iZzx14 += iZzx4;
                    break;
                case 22:
                    iZzx4 = zzmv.zzj(i6, (List) unsafe.getObject(obj, j), false);
                    iZzx14 += iZzx4;
                    break;
                case 23:
                    iZzx4 = zzmv.zzg(i6, (List) unsafe.getObject(obj, j), false);
                    iZzx14 += iZzx4;
                    break;
                case 24:
                    iZzx4 = zzmv.zze(i6, (List) unsafe.getObject(obj, j), false);
                    iZzx14 += iZzx4;
                    break;
                case 25:
                    iZzx4 = zzmv.zza(i6, (List) unsafe.getObject(obj, j), false);
                    iZzx14 += iZzx4;
                    break;
                case 26:
                    iZzt = zzmv.zzt(i6, (List) unsafe.getObject(obj, j));
                    iZzx14 += iZzt;
                    break;
                case 27:
                    iZzt = zzmv.zzo(i6, (List) unsafe.getObject(obj, j), zzB(i4));
                    iZzx14 += iZzt;
                    break;
                case 28:
                    iZzt = zzmv.zzb(i6, (List) unsafe.getObject(obj, j));
                    iZzx14 += iZzt;
                    break;
                case 29:
                    iZzt = zzmv.zzu(i6, (List) unsafe.getObject(obj, j), false);
                    iZzx14 += iZzt;
                    break;
                case 30:
                    z = false;
                    iZzc = zzmv.zzc(i6, (List) unsafe.getObject(obj, j), false);
                    iZzx14 += iZzc;
                    break;
                case 31:
                    z = false;
                    iZzc = zzmv.zze(i6, (List) unsafe.getObject(obj, j), false);
                    iZzx14 += iZzc;
                    break;
                case 32:
                    z = false;
                    iZzc = zzmv.zzg(i6, (List) unsafe.getObject(obj, j), false);
                    iZzx14 += iZzc;
                    break;
                case 33:
                    z = false;
                    iZzc = zzmv.zzp(i6, (List) unsafe.getObject(obj, j), false);
                    iZzx14 += iZzc;
                    break;
                case 34:
                    z = false;
                    iZzc = zzmv.zzr(i6, (List) unsafe.getObject(obj, j), false);
                    iZzx14 += iZzc;
                    break;
                case 35:
                    iZzh = zzmv.zzh((List) unsafe.getObject(obj, j));
                    if (iZzh > 0) {
                        iZzx7 = zzki.zzx(iZzh);
                        iZzx8 = zzki.zzx(i6 << 3);
                        iZzx9 = iZzx8 + iZzx7;
                        iZzx14 += iZzx9 + iZzh;
                    }
                    break;
                case 36:
                    iZzh = zzmv.zzf((List) unsafe.getObject(obj, j));
                    if (iZzh > 0) {
                        iZzx7 = zzki.zzx(iZzh);
                        iZzx8 = zzki.zzx(i6 << 3);
                        iZzx9 = iZzx8 + iZzx7;
                        iZzx14 += iZzx9 + iZzh;
                    }
                    break;
                case 37:
                    iZzh = zzmv.zzm((List) unsafe.getObject(obj, j));
                    if (iZzh > 0) {
                        iZzx7 = zzki.zzx(iZzh);
                        iZzx8 = zzki.zzx(i6 << 3);
                        iZzx9 = iZzx8 + iZzx7;
                        iZzx14 += iZzx9 + iZzh;
                    }
                    break;
                case 38:
                    iZzh = zzmv.zzx((List) unsafe.getObject(obj, j));
                    if (iZzh > 0) {
                        iZzx7 = zzki.zzx(iZzh);
                        iZzx8 = zzki.zzx(i6 << 3);
                        iZzx9 = iZzx8 + iZzx7;
                        iZzx14 += iZzx9 + iZzh;
                    }
                    break;
                case 39:
                    iZzh = zzmv.zzk((List) unsafe.getObject(obj, j));
                    if (iZzh > 0) {
                        iZzx7 = zzki.zzx(iZzh);
                        iZzx8 = zzki.zzx(i6 << 3);
                        iZzx9 = iZzx8 + iZzx7;
                        iZzx14 += iZzx9 + iZzh;
                    }
                    break;
                case 40:
                    iZzh = zzmv.zzh((List) unsafe.getObject(obj, j));
                    if (iZzh > 0) {
                        iZzx7 = zzki.zzx(iZzh);
                        iZzx8 = zzki.zzx(i6 << 3);
                        iZzx9 = iZzx8 + iZzx7;
                        iZzx14 += iZzx9 + iZzh;
                    }
                    break;
                case 41:
                    iZzh = zzmv.zzf((List) unsafe.getObject(obj, j));
                    if (iZzh > 0) {
                        iZzx7 = zzki.zzx(iZzh);
                        iZzx8 = zzki.zzx(i6 << 3);
                        iZzx9 = iZzx8 + iZzx7;
                        iZzx14 += iZzx9 + iZzh;
                    }
                    break;
                case 42:
                    List list = (List) unsafe.getObject(obj, j);
                    int i13 = zzmv.zza;
                    iZzh = list.size();
                    if (iZzh > 0) {
                        iZzx7 = zzki.zzx(iZzh);
                        iZzx8 = zzki.zzx(i6 << 3);
                        iZzx9 = iZzx8 + iZzx7;
                        iZzx14 += iZzx9 + iZzh;
                    }
                    break;
                case 43:
                    iZzh = zzmv.zzv((List) unsafe.getObject(obj, j));
                    if (iZzh > 0) {
                        iZzx7 = zzki.zzx(iZzh);
                        iZzx8 = zzki.zzx(i6 << 3);
                        iZzx9 = iZzx8 + iZzx7;
                        iZzx14 += iZzx9 + iZzh;
                    }
                    break;
                case 44:
                    iZzh = zzmv.zzd((List) unsafe.getObject(obj, j));
                    if (iZzh > 0) {
                        iZzx7 = zzki.zzx(iZzh);
                        iZzx8 = zzki.zzx(i6 << 3);
                        iZzx9 = iZzx8 + iZzx7;
                        iZzx14 += iZzx9 + iZzh;
                    }
                    break;
                case 45:
                    iZzh = zzmv.zzf((List) unsafe.getObject(obj, j));
                    if (iZzh > 0) {
                        iZzx7 = zzki.zzx(iZzh);
                        iZzx8 = zzki.zzx(i6 << 3);
                        iZzx9 = iZzx8 + iZzx7;
                        iZzx14 += iZzx9 + iZzh;
                    }
                    break;
                case 46:
                    iZzh = zzmv.zzh((List) unsafe.getObject(obj, j));
                    if (iZzh > 0) {
                        iZzx7 = zzki.zzx(iZzh);
                        iZzx8 = zzki.zzx(i6 << 3);
                        iZzx9 = iZzx8 + iZzx7;
                        iZzx14 += iZzx9 + iZzh;
                    }
                    break;
                case 47:
                    iZzh = zzmv.zzq((List) unsafe.getObject(obj, j));
                    if (iZzh > 0) {
                        iZzx7 = zzki.zzx(iZzh);
                        iZzx8 = zzki.zzx(i6 << 3);
                        iZzx9 = iZzx8 + iZzx7;
                        iZzx14 += iZzx9 + iZzh;
                    }
                    break;
                case 48:
                    iZzh = zzmv.zzs((List) unsafe.getObject(obj, j));
                    if (iZzh > 0) {
                        iZzx7 = zzki.zzx(iZzh);
                        iZzx8 = zzki.zzx(i6 << 3);
                        iZzx9 = iZzx8 + iZzx7;
                        iZzx14 += iZzx9 + iZzh;
                    }
                    break;
                case 49:
                    iZzt = zzmv.zzi(i6, (List) unsafe.getObject(obj, j), zzB(i4));
                    iZzx14 += iZzt;
                    break;
                case 50:
                    zzmd.zza(i6, unsafe.getObject(obj, j), zzC(i4));
                    break;
                case 51:
                    if (zzT(obj, i6, i4)) {
                        iZzx10 = zzki.zzx(i6 << 3);
                        iZzt = iZzx10 + 8;
                        iZzx14 += iZzt;
                    }
                    break;
                case 52:
                    if (zzT(obj, i6, i4)) {
                        iZzx11 = zzki.zzx(i6 << 3);
                        iZzt = iZzx11 + 4;
                        iZzx14 += iZzt;
                    }
                    break;
                case 53:
                    if (zzT(obj, i6, i4)) {
                        iZzh = zzki.zzy(zzz(obj, j));
                        iZzx9 = zzki.zzx(i6 << 3);
                        iZzx14 += iZzx9 + iZzh;
                    }
                    break;
                case 54:
                    if (zzT(obj, i6, i4)) {
                        iZzh = zzki.zzy(zzz(obj, j));
                        iZzx9 = zzki.zzx(i6 << 3);
                        iZzx14 += iZzx9 + iZzh;
                    }
                    break;
                case 55:
                    if (zzT(obj, i6, i4)) {
                        iZzh = zzki.zzu(zzp(obj, j));
                        iZzx9 = zzki.zzx(i6 << 3);
                        iZzx14 += iZzx9 + iZzh;
                    }
                    break;
                case 56:
                    if (zzT(obj, i6, i4)) {
                        iZzx10 = zzki.zzx(i6 << 3);
                        iZzt = iZzx10 + 8;
                        iZzx14 += iZzt;
                    }
                    break;
                case 57:
                    if (zzT(obj, i6, i4)) {
                        iZzx11 = zzki.zzx(i6 << 3);
                        iZzt = iZzx11 + 4;
                        iZzx14 += iZzt;
                    }
                    break;
                case 58:
                    if (zzT(obj, i6, i4)) {
                        iZzt = zzki.zzx(i6 << 3) + 1;
                        iZzx14 += iZzt;
                    }
                    break;
                case 59:
                    if (zzT(obj, i6, i4)) {
                        Object object2 = unsafe.getObject(obj, j);
                        if (object2 instanceof zzka) {
                            int i14 = zzki.zzb;
                            int iZzd3 = ((zzka) object2).zzd();
                            iZzx12 = zzki.zzx(iZzd3) + iZzd3;
                            iZzx13 = zzki.zzx(i6 << 3);
                            iZzt = iZzx13 + iZzx12;
                            iZzx14 += iZzt;
                        } else {
                            iZzh = zzki.zzw((String) object2);
                            iZzx9 = zzki.zzx(i6 << 3);
                            iZzx14 += iZzx9 + iZzh;
                        }
                    }
                    break;
                case 60:
                    if (zzT(obj, i6, i4)) {
                        iZzt = zzmv.zzn(i6, unsafe.getObject(obj, j), zzB(i4));
                        iZzx14 += iZzt;
                    }
                    break;
                case 61:
                    if (zzT(obj, i6, i4)) {
                        zzka zzkaVar2 = (zzka) unsafe.getObject(obj, j);
                        int i15 = zzki.zzb;
                        int iZzd4 = zzkaVar2.zzd();
                        iZzx12 = zzki.zzx(iZzd4) + iZzd4;
                        iZzx13 = zzki.zzx(i6 << 3);
                        iZzt = iZzx13 + iZzx12;
                        iZzx14 += iZzt;
                    }
                    break;
                case 62:
                    if (zzT(obj, i6, i4)) {
                        iZzh = zzki.zzx(zzp(obj, j));
                        iZzx9 = zzki.zzx(i6 << 3);
                        iZzx14 += iZzx9 + iZzh;
                    }
                    break;
                case 63:
                    if (zzT(obj, i6, i4)) {
                        iZzh = zzki.zzu(zzp(obj, j));
                        iZzx9 = zzki.zzx(i6 << 3);
                        iZzx14 += iZzx9 + iZzh;
                    }
                    break;
                case 64:
                    if (zzT(obj, i6, i4)) {
                        iZzx11 = zzki.zzx(i6 << 3);
                        iZzt = iZzx11 + 4;
                        iZzx14 += iZzt;
                    }
                    break;
                case 65:
                    if (zzT(obj, i6, i4)) {
                        iZzx10 = zzki.zzx(i6 << 3);
                        iZzt = iZzx10 + 8;
                        iZzx14 += iZzt;
                    }
                    break;
                case 66:
                    if (zzT(obj, i6, i4)) {
                        int iZzp = zzp(obj, j);
                        iZzx9 = zzki.zzx(i6 << 3);
                        iZzh = zzki.zzx((iZzp >> 31) ^ (iZzp + iZzp));
                        iZzx14 += iZzx9 + iZzh;
                    }
                    break;
                case 67:
                    if (zzT(obj, i6, i4)) {
                        long jZzz = zzz(obj, j);
                        iZzx14 += zzki.zzx(i6 << 3) + zzki.zzy((jZzz >> 63) ^ (jZzz + jZzz));
                    }
                    break;
                case 68:
                    if (zzT(obj, i6, i4)) {
                        iZzt = zzki.zzt(i6, (zzmi) unsafe.getObject(obj, j), zzB(i4));
                        iZzx14 += iZzt;
                    }
                    break;
            }
            i4 += 3;
            i2 = 1048575;
        }
        zznk zznkVar = this.zzn;
        int iZza = iZzx14 + zznkVar.zza(zznkVar.zzd(obj));
        if (!this.zzh) {
            return iZza;
        }
        this.zzo.zza(obj);
        throw null;
    }

    private static int zzp(Object obj, long j) {
        return ((Integer) zznu.zzf(obj, j)).intValue();
    }

    private final int zzq(Object obj, byte[] bArr, int i, int i2, int i3, long j, zzjn zzjnVar) throws IOException {
        Unsafe unsafe = zzb;
        Object objZzC = zzC(i3);
        Object object = unsafe.getObject(obj, j);
        if (!((zzmc) object).zze()) {
            zzmc zzmcVarZzb = zzmc.zza().zzb();
            zzmd.zzb(zzmcVarZzb, object);
            unsafe.putObject(obj, j, zzmcVarZzb);
        }
        throw null;
    }

    private final int zzr(Object obj, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7, long j, int i8, zzjn zzjnVar) throws IOException {
        Unsafe unsafe = zzb;
        long j2 = this.zzc[i8 + 2] & 1048575;
        switch (i7) {
            case 51:
                if (i5 == 1) {
                    unsafe.putObject(obj, j, Double.valueOf(Double.longBitsToDouble(zzjo.zzp(bArr, i))));
                    int i9 = i + 8;
                    unsafe.putInt(obj, j2, i4);
                    return i9;
                }
                break;
            case 52:
                if (i5 == 5) {
                    unsafe.putObject(obj, j, Float.valueOf(Float.intBitsToFloat(zzjo.zzb(bArr, i))));
                    int i10 = i + 4;
                    unsafe.putInt(obj, j2, i4);
                    return i10;
                }
                break;
            case 53:
            case 54:
                if (i5 == 0) {
                    int iZzm = zzjo.zzm(bArr, i, zzjnVar);
                    unsafe.putObject(obj, j, Long.valueOf(zzjnVar.zzb));
                    unsafe.putInt(obj, j2, i4);
                    return iZzm;
                }
                break;
            case 55:
            case 62:
                if (i5 == 0) {
                    int iZzj = zzjo.zzj(bArr, i, zzjnVar);
                    unsafe.putObject(obj, j, Integer.valueOf(zzjnVar.zza));
                    unsafe.putInt(obj, j2, i4);
                    return iZzj;
                }
                break;
            case 56:
            case 65:
                if (i5 == 1) {
                    unsafe.putObject(obj, j, Long.valueOf(zzjo.zzp(bArr, i)));
                    int i11 = i + 8;
                    unsafe.putInt(obj, j2, i4);
                    return i11;
                }
                break;
            case 57:
            case 64:
                if (i5 == 5) {
                    unsafe.putObject(obj, j, Integer.valueOf(zzjo.zzb(bArr, i)));
                    int i12 = i + 4;
                    unsafe.putInt(obj, j2, i4);
                    return i12;
                }
                break;
            case 58:
                if (i5 == 0) {
                    int iZzm2 = zzjo.zzm(bArr, i, zzjnVar);
                    unsafe.putObject(obj, j, Boolean.valueOf(zzjnVar.zzb != 0));
                    unsafe.putInt(obj, j2, i4);
                    return iZzm2;
                }
                break;
            case 59:
                if (i5 == 2) {
                    int iZzj2 = zzjo.zzj(bArr, i, zzjnVar);
                    int i13 = zzjnVar.zza;
                    if (i13 == 0) {
                        unsafe.putObject(obj, j, "");
                    } else {
                        if ((i6 & 536870912) != 0 && !zznz.zze(bArr, iZzj2, iZzj2 + i13)) {
                            throw zzll.zzc();
                        }
                        unsafe.putObject(obj, j, new String(bArr, iZzj2, i13, zzlj.zzb));
                        iZzj2 += i13;
                    }
                    unsafe.putInt(obj, j2, i4);
                    return iZzj2;
                }
                break;
            case 60:
                if (i5 == 2) {
                    Object objZzE = zzE(obj, i4, i8);
                    int iZzo = zzjo.zzo(objZzE, zzB(i8), bArr, i, i2, zzjnVar);
                    zzM(obj, i4, i8, objZzE);
                    return iZzo;
                }
                break;
            case 61:
                if (i5 == 2) {
                    int iZza = zzjo.zza(bArr, i, zzjnVar);
                    unsafe.putObject(obj, j, zzjnVar.zzc);
                    unsafe.putInt(obj, j2, i4);
                    return iZza;
                }
                break;
            case 63:
                if (i5 == 0) {
                    int iZzj3 = zzjo.zzj(bArr, i, zzjnVar);
                    int i14 = zzjnVar.zza;
                    zzlf zzlfVarZzA = zzA(i8);
                    if (zzlfVarZzA == null || zzlfVarZzA.zza(i14)) {
                        unsafe.putObject(obj, j, Integer.valueOf(i14));
                        unsafe.putInt(obj, j2, i4);
                    } else {
                        zzd(obj).zzj(i3, Long.valueOf(i14));
                    }
                    return iZzj3;
                }
                break;
            case 66:
                if (i5 == 0) {
                    int iZzj4 = zzjo.zzj(bArr, i, zzjnVar);
                    unsafe.putObject(obj, j, Integer.valueOf(zzke.zzb(zzjnVar.zza)));
                    unsafe.putInt(obj, j2, i4);
                    return iZzj4;
                }
                break;
            case 67:
                if (i5 == 0) {
                    int iZzm3 = zzjo.zzm(bArr, i, zzjnVar);
                    unsafe.putObject(obj, j, Long.valueOf(zzke.zzc(zzjnVar.zzb)));
                    unsafe.putInt(obj, j2, i4);
                    return iZzm3;
                }
                break;
            case 68:
                if (i5 == 3) {
                    Object objZzE2 = zzE(obj, i4, i8);
                    int iZzn = zzjo.zzn(objZzE2, zzB(i8), bArr, i, i2, (i3 & (-8)) | 4, zzjnVar);
                    zzM(obj, i4, i8, objZzE2);
                    return iZzn;
                }
                break;
        }
        return i;
    }

    private final int zzs(Object obj, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, long j, int i7, long j2, zzjn zzjnVar) throws IOException {
        int i8;
        int i9;
        int i10;
        int i11;
        int iZzl;
        int iZzj = i;
        Unsafe unsafe = zzb;
        zzli zzliVarZzd = (zzli) unsafe.getObject(obj, j2);
        if (!zzliVarZzd.zzc()) {
            int size = zzliVarZzd.size();
            zzliVarZzd = zzliVarZzd.zzd(size == 0 ? 10 : size + size);
            unsafe.putObject(obj, j2, zzliVarZzd);
        }
        switch (i7) {
            case 18:
            case 35:
                if (i5 == 2) {
                    zzkk zzkkVar = (zzkk) zzliVarZzd;
                    int iZzj2 = zzjo.zzj(bArr, iZzj, zzjnVar);
                    int i12 = zzjnVar.zza + iZzj2;
                    while (iZzj2 < i12) {
                        zzkkVar.zze(Double.longBitsToDouble(zzjo.zzp(bArr, iZzj2)));
                        iZzj2 += 8;
                    }
                    if (iZzj2 == i12) {
                        return iZzj2;
                    }
                    throw zzll.zzf();
                }
                if (i5 == 1) {
                    zzkk zzkkVar2 = (zzkk) zzliVarZzd;
                    zzkkVar2.zze(Double.longBitsToDouble(zzjo.zzp(bArr, i)));
                    while (true) {
                        i8 = iZzj + 8;
                        if (i8 < i2) {
                            iZzj = zzjo.zzj(bArr, i8, zzjnVar);
                            if (i3 == zzjnVar.zza) {
                                zzkkVar2.zze(Double.longBitsToDouble(zzjo.zzp(bArr, iZzj)));
                            }
                        }
                    }
                    return i8;
                }
                break;
            case 19:
            case 36:
                if (i5 == 2) {
                    zzku zzkuVar = (zzku) zzliVarZzd;
                    int iZzj3 = zzjo.zzj(bArr, iZzj, zzjnVar);
                    int i13 = zzjnVar.zza + iZzj3;
                    while (iZzj3 < i13) {
                        zzkuVar.zze(Float.intBitsToFloat(zzjo.zzb(bArr, iZzj3)));
                        iZzj3 += 4;
                    }
                    if (iZzj3 == i13) {
                        return iZzj3;
                    }
                    throw zzll.zzf();
                }
                if (i5 == 5) {
                    zzku zzkuVar2 = (zzku) zzliVarZzd;
                    zzkuVar2.zze(Float.intBitsToFloat(zzjo.zzb(bArr, i)));
                    while (true) {
                        i9 = iZzj + 4;
                        if (i9 < i2) {
                            iZzj = zzjo.zzj(bArr, i9, zzjnVar);
                            if (i3 == zzjnVar.zza) {
                                zzkuVar2.zze(Float.intBitsToFloat(zzjo.zzb(bArr, iZzj)));
                            }
                        }
                    }
                    return i9;
                }
                break;
            case 20:
            case 21:
            case 37:
            case 38:
                if (i5 == 2) {
                    zzlx zzlxVar = (zzlx) zzliVarZzd;
                    int iZzj4 = zzjo.zzj(bArr, iZzj, zzjnVar);
                    int i14 = zzjnVar.zza + iZzj4;
                    while (iZzj4 < i14) {
                        iZzj4 = zzjo.zzm(bArr, iZzj4, zzjnVar);
                        zzlxVar.zzg(zzjnVar.zzb);
                    }
                    if (iZzj4 == i14) {
                        return iZzj4;
                    }
                    throw zzll.zzf();
                }
                if (i5 == 0) {
                    zzlx zzlxVar2 = (zzlx) zzliVarZzd;
                    int iZzm = zzjo.zzm(bArr, iZzj, zzjnVar);
                    zzlxVar2.zzg(zzjnVar.zzb);
                    while (iZzm < i2) {
                        int iZzj5 = zzjo.zzj(bArr, iZzm, zzjnVar);
                        if (i3 != zzjnVar.zza) {
                            return iZzm;
                        }
                        iZzm = zzjo.zzm(bArr, iZzj5, zzjnVar);
                        zzlxVar2.zzg(zzjnVar.zzb);
                    }
                    return iZzm;
                }
                break;
            case 22:
            case 29:
            case 39:
            case 43:
                if (i5 == 2) {
                    return zzjo.zzf(bArr, iZzj, zzliVarZzd, zzjnVar);
                }
                if (i5 == 0) {
                    return zzjo.zzl(i3, bArr, i, i2, zzliVarZzd, zzjnVar);
                }
                break;
            case 23:
            case 32:
            case 40:
            case 46:
                if (i5 == 2) {
                    zzlx zzlxVar3 = (zzlx) zzliVarZzd;
                    int iZzj6 = zzjo.zzj(bArr, iZzj, zzjnVar);
                    int i15 = zzjnVar.zza + iZzj6;
                    while (iZzj6 < i15) {
                        zzlxVar3.zzg(zzjo.zzp(bArr, iZzj6));
                        iZzj6 += 8;
                    }
                    if (iZzj6 == i15) {
                        return iZzj6;
                    }
                    throw zzll.zzf();
                }
                if (i5 == 1) {
                    zzlx zzlxVar4 = (zzlx) zzliVarZzd;
                    zzlxVar4.zzg(zzjo.zzp(bArr, i));
                    while (true) {
                        i10 = iZzj + 8;
                        if (i10 < i2) {
                            iZzj = zzjo.zzj(bArr, i10, zzjnVar);
                            if (i3 == zzjnVar.zza) {
                                zzlxVar4.zzg(zzjo.zzp(bArr, iZzj));
                            }
                        }
                    }
                    return i10;
                }
                break;
            case 24:
            case 31:
            case 41:
            case 45:
                if (i5 == 2) {
                    zzlc zzlcVar = (zzlc) zzliVarZzd;
                    int iZzj7 = zzjo.zzj(bArr, iZzj, zzjnVar);
                    int i16 = zzjnVar.zza + iZzj7;
                    while (iZzj7 < i16) {
                        zzlcVar.zzh(zzjo.zzb(bArr, iZzj7));
                        iZzj7 += 4;
                    }
                    if (iZzj7 == i16) {
                        return iZzj7;
                    }
                    throw zzll.zzf();
                }
                if (i5 == 5) {
                    zzlc zzlcVar2 = (zzlc) zzliVarZzd;
                    zzlcVar2.zzh(zzjo.zzb(bArr, i));
                    while (true) {
                        i11 = iZzj + 4;
                        if (i11 < i2) {
                            iZzj = zzjo.zzj(bArr, i11, zzjnVar);
                            if (i3 == zzjnVar.zza) {
                                zzlcVar2.zzh(zzjo.zzb(bArr, iZzj));
                            }
                        }
                    }
                    return i11;
                }
                break;
            case 25:
            case 42:
                if (i5 == 2) {
                    zzjp zzjpVar = (zzjp) zzliVarZzd;
                    int iZzj8 = zzjo.zzj(bArr, iZzj, zzjnVar);
                    int i17 = zzjnVar.zza + iZzj8;
                    while (iZzj8 < i17) {
                        iZzj8 = zzjo.zzm(bArr, iZzj8, zzjnVar);
                        zzjpVar.zze(zzjnVar.zzb != 0);
                    }
                    if (iZzj8 == i17) {
                        return iZzj8;
                    }
                    throw zzll.zzf();
                }
                if (i5 == 0) {
                    zzjp zzjpVar2 = (zzjp) zzliVarZzd;
                    int iZzm2 = zzjo.zzm(bArr, iZzj, zzjnVar);
                    zzjpVar2.zze(zzjnVar.zzb != 0);
                    while (iZzm2 < i2) {
                        int iZzj9 = zzjo.zzj(bArr, iZzm2, zzjnVar);
                        if (i3 != zzjnVar.zza) {
                            return iZzm2;
                        }
                        iZzm2 = zzjo.zzm(bArr, iZzj9, zzjnVar);
                        zzjpVar2.zze(zzjnVar.zzb != 0);
                    }
                    return iZzm2;
                }
                break;
            case 26:
                if (i5 == 2) {
                    if ((j & 536870912) == 0) {
                        int iZzj10 = zzjo.zzj(bArr, iZzj, zzjnVar);
                        int i18 = zzjnVar.zza;
                        if (i18 < 0) {
                            throw zzll.zzd();
                        }
                        if (i18 == 0) {
                            zzliVarZzd.add("");
                        } else {
                            zzliVarZzd.add(new String(bArr, iZzj10, i18, zzlj.zzb));
                            iZzj10 += i18;
                        }
                        while (iZzj10 < i2) {
                            int iZzj11 = zzjo.zzj(bArr, iZzj10, zzjnVar);
                            if (i3 != zzjnVar.zza) {
                                return iZzj10;
                            }
                            iZzj10 = zzjo.zzj(bArr, iZzj11, zzjnVar);
                            int i19 = zzjnVar.zza;
                            if (i19 < 0) {
                                throw zzll.zzd();
                            }
                            if (i19 == 0) {
                                zzliVarZzd.add("");
                            } else {
                                zzliVarZzd.add(new String(bArr, iZzj10, i19, zzlj.zzb));
                                iZzj10 += i19;
                            }
                        }
                        return iZzj10;
                    }
                    int iZzj12 = zzjo.zzj(bArr, iZzj, zzjnVar);
                    int i20 = zzjnVar.zza;
                    if (i20 < 0) {
                        throw zzll.zzd();
                    }
                    if (i20 == 0) {
                        zzliVarZzd.add("");
                    } else {
                        int i21 = iZzj12 + i20;
                        if (!zznz.zze(bArr, iZzj12, i21)) {
                            throw zzll.zzc();
                        }
                        zzliVarZzd.add(new String(bArr, iZzj12, i20, zzlj.zzb));
                        iZzj12 = i21;
                    }
                    while (iZzj12 < i2) {
                        int iZzj13 = zzjo.zzj(bArr, iZzj12, zzjnVar);
                        if (i3 != zzjnVar.zza) {
                            return iZzj12;
                        }
                        iZzj12 = zzjo.zzj(bArr, iZzj13, zzjnVar);
                        int i22 = zzjnVar.zza;
                        if (i22 < 0) {
                            throw zzll.zzd();
                        }
                        if (i22 == 0) {
                            zzliVarZzd.add("");
                        } else {
                            int i23 = iZzj12 + i22;
                            if (!zznz.zze(bArr, iZzj12, i23)) {
                                throw zzll.zzc();
                            }
                            zzliVarZzd.add(new String(bArr, iZzj12, i22, zzlj.zzb));
                            iZzj12 = i23;
                        }
                    }
                    return iZzj12;
                }
                break;
            case 27:
                if (i5 == 2) {
                    return zzjo.zze(zzB(i6), i3, bArr, i, i2, zzliVarZzd, zzjnVar);
                }
                break;
            case 28:
                if (i5 == 2) {
                    int iZzj14 = zzjo.zzj(bArr, iZzj, zzjnVar);
                    int i24 = zzjnVar.zza;
                    if (i24 < 0) {
                        throw zzll.zzd();
                    }
                    if (i24 > bArr.length - iZzj14) {
                        throw zzll.zzf();
                    }
                    if (i24 == 0) {
                        zzliVarZzd.add(zzka.zzb);
                    } else {
                        zzliVarZzd.add(zzka.zzl(bArr, iZzj14, i24));
                        iZzj14 += i24;
                    }
                    while (iZzj14 < i2) {
                        int iZzj15 = zzjo.zzj(bArr, iZzj14, zzjnVar);
                        if (i3 != zzjnVar.zza) {
                            return iZzj14;
                        }
                        iZzj14 = zzjo.zzj(bArr, iZzj15, zzjnVar);
                        int i25 = zzjnVar.zza;
                        if (i25 < 0) {
                            throw zzll.zzd();
                        }
                        if (i25 > bArr.length - iZzj14) {
                            throw zzll.zzf();
                        }
                        if (i25 == 0) {
                            zzliVarZzd.add(zzka.zzb);
                        } else {
                            zzliVarZzd.add(zzka.zzl(bArr, iZzj14, i25));
                            iZzj14 += i25;
                        }
                    }
                    return iZzj14;
                }
                break;
            case 30:
            case 44:
                if (i5 == 2) {
                    iZzl = zzjo.zzf(bArr, iZzj, zzliVarZzd, zzjnVar);
                } else if (i5 == 0) {
                    iZzl = zzjo.zzl(i3, bArr, i, i2, zzliVarZzd, zzjnVar);
                }
                zzlf zzlfVarZzA = zzA(i6);
                zznk zznkVar = this.zzn;
                int i26 = zzmv.zza;
                if (zzlfVarZzA != null) {
                    Object objZzA = null;
                    if (zzliVarZzd instanceof RandomAccess) {
                        int size2 = zzliVarZzd.size();
                        int i27 = 0;
                        for (int i28 = 0; i28 < size2; i28++) {
                            int iIntValue = ((Integer) zzliVarZzd.get(i28)).intValue();
                            if (zzlfVarZzA.zza(iIntValue)) {
                                if (i28 != i27) {
                                    zzliVarZzd.set(i27, Integer.valueOf(iIntValue));
                                }
                                i27++;
                            } else {
                                objZzA = zzmv.zzA(obj, i4, iIntValue, objZzA, zznkVar);
                            }
                        }
                        if (i27 != size2) {
                            zzliVarZzd.subList(i27, size2).clear();
                            return iZzl;
                        }
                    } else {
                        Iterator it = zzliVarZzd.iterator();
                        while (it.hasNext()) {
                            int iIntValue2 = ((Integer) it.next()).intValue();
                            if (!zzlfVarZzA.zza(iIntValue2)) {
                                objZzA = zzmv.zzA(obj, i4, iIntValue2, objZzA, zznkVar);
                                it.remove();
                            }
                        }
                    }
                }
                return iZzl;
            case 33:
            case 47:
                if (i5 == 2) {
                    zzlc zzlcVar3 = (zzlc) zzliVarZzd;
                    int iZzj16 = zzjo.zzj(bArr, iZzj, zzjnVar);
                    int i29 = zzjnVar.zza + iZzj16;
                    while (iZzj16 < i29) {
                        iZzj16 = zzjo.zzj(bArr, iZzj16, zzjnVar);
                        zzlcVar3.zzh(zzke.zzb(zzjnVar.zza));
                    }
                    if (iZzj16 == i29) {
                        return iZzj16;
                    }
                    throw zzll.zzf();
                }
                if (i5 == 0) {
                    zzlc zzlcVar4 = (zzlc) zzliVarZzd;
                    int iZzj17 = zzjo.zzj(bArr, iZzj, zzjnVar);
                    zzlcVar4.zzh(zzke.zzb(zzjnVar.zza));
                    while (iZzj17 < i2) {
                        int iZzj18 = zzjo.zzj(bArr, iZzj17, zzjnVar);
                        if (i3 != zzjnVar.zza) {
                            return iZzj17;
                        }
                        iZzj17 = zzjo.zzj(bArr, iZzj18, zzjnVar);
                        zzlcVar4.zzh(zzke.zzb(zzjnVar.zza));
                    }
                    return iZzj17;
                }
                break;
            case 34:
            case 48:
                if (i5 == 2) {
                    zzlx zzlxVar5 = (zzlx) zzliVarZzd;
                    int iZzj19 = zzjo.zzj(bArr, iZzj, zzjnVar);
                    int i30 = zzjnVar.zza + iZzj19;
                    while (iZzj19 < i30) {
                        iZzj19 = zzjo.zzm(bArr, iZzj19, zzjnVar);
                        zzlxVar5.zzg(zzke.zzc(zzjnVar.zzb));
                    }
                    if (iZzj19 == i30) {
                        return iZzj19;
                    }
                    throw zzll.zzf();
                }
                if (i5 == 0) {
                    zzlx zzlxVar6 = (zzlx) zzliVarZzd;
                    int iZzm3 = zzjo.zzm(bArr, iZzj, zzjnVar);
                    zzlxVar6.zzg(zzke.zzc(zzjnVar.zzb));
                    while (iZzm3 < i2) {
                        int iZzj20 = zzjo.zzj(bArr, iZzm3, zzjnVar);
                        if (i3 != zzjnVar.zza) {
                            return iZzm3;
                        }
                        iZzm3 = zzjo.zzm(bArr, iZzj20, zzjnVar);
                        zzlxVar6.zzg(zzke.zzc(zzjnVar.zzb));
                    }
                    return iZzm3;
                }
                break;
            default:
                if (i5 == 3) {
                    zzmt zzmtVarZzB = zzB(i6);
                    int i31 = (i3 & (-8)) | 4;
                    int iZzc = zzjo.zzc(zzmtVarZzB, bArr, i, i2, i31, zzjnVar);
                    zzliVarZzd.add(zzjnVar.zzc);
                    while (iZzc < i2) {
                        int iZzj21 = zzjo.zzj(bArr, iZzc, zzjnVar);
                        if (i3 != zzjnVar.zza) {
                            return iZzc;
                        }
                        iZzc = zzjo.zzc(zzmtVarZzB, bArr, iZzj21, i2, i31, zzjnVar);
                        zzliVarZzd.add(zzjnVar.zzc);
                    }
                    return iZzc;
                }
                break;
        }
        return iZzj;
    }

    private final int zzt(int i) {
        if (i < this.zze || i > this.zzf) {
            return -1;
        }
        return zzw(i, 0);
    }

    private final int zzu(int i, int i2) {
        if (i < this.zze || i > this.zzf) {
            return -1;
        }
        return zzw(i, i2);
    }

    private final int zzv(int i) {
        return this.zzc[i + 2];
    }

    private final int zzw(int i, int i2) {
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

    private static int zzx(int i) {
        return (i >>> 20) & 255;
    }

    private final int zzy(int i) {
        return this.zzc[i + 1];
    }

    private static long zzz(Object obj, long j) {
        return ((Long) zznu.zzf(obj, j)).longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzmt
    public final int zza(Object obj) {
        int iZzx;
        int iZzx2;
        int iZzy;
        int iZzx3;
        int iZzx4;
        int iZzx5;
        int iZzx6;
        int iZzn;
        int iZzx7;
        int iZzy2;
        int iZzx8;
        int iZzx9;
        if (!this.zzi) {
            return zzo(obj);
        }
        Unsafe unsafe = zzb;
        int i = 0;
        for (int i2 = 0; i2 < this.zzc.length; i2 += 3) {
            int iZzy3 = zzy(i2);
            int iZzx10 = zzx(iZzy3);
            int i3 = this.zzc[i2];
            int i4 = iZzy3 & 1048575;
            if (iZzx10 >= zzkt.DOUBLE_LIST_PACKED.zza() && iZzx10 <= zzkt.SINT64_LIST_PACKED.zza()) {
                int i5 = this.zzc[i2 + 2];
            }
            long j = i4;
            switch (iZzx10) {
                case 0:
                    if (zzP(obj, i2)) {
                        iZzx = zzki.zzx(i3 << 3);
                        iZzn = iZzx + 8;
                        i += iZzn;
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (zzP(obj, i2)) {
                        iZzx2 = zzki.zzx(i3 << 3);
                        iZzn = iZzx2 + 4;
                        i += iZzn;
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (zzP(obj, i2)) {
                        iZzy = zzki.zzy(zznu.zzd(obj, j));
                        iZzx3 = zzki.zzx(i3 << 3);
                        i += iZzx3 + iZzy;
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (zzP(obj, i2)) {
                        iZzy = zzki.zzy(zznu.zzd(obj, j));
                        iZzx3 = zzki.zzx(i3 << 3);
                        i += iZzx3 + iZzy;
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (zzP(obj, i2)) {
                        iZzy = zzki.zzu(zznu.zzc(obj, j));
                        iZzx3 = zzki.zzx(i3 << 3);
                        i += iZzx3 + iZzy;
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (zzP(obj, i2)) {
                        iZzx = zzki.zzx(i3 << 3);
                        iZzn = iZzx + 8;
                        i += iZzn;
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (zzP(obj, i2)) {
                        iZzx2 = zzki.zzx(i3 << 3);
                        iZzn = iZzx2 + 4;
                        i += iZzn;
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if (zzP(obj, i2)) {
                        iZzx4 = zzki.zzx(i3 << 3);
                        iZzn = iZzx4 + 1;
                        i += iZzn;
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (zzP(obj, i2)) {
                        Object objZzf = zznu.zzf(obj, j);
                        if (objZzf instanceof zzka) {
                            int i6 = i3 << 3;
                            int i7 = zzki.zzb;
                            int iZzd = ((zzka) objZzf).zzd();
                            iZzx5 = zzki.zzx(iZzd) + iZzd;
                            iZzx6 = zzki.zzx(i6);
                            iZzn = iZzx6 + iZzx5;
                            i += iZzn;
                            break;
                        } else {
                            iZzy = zzki.zzw((String) objZzf);
                            iZzx3 = zzki.zzx(i3 << 3);
                            i += iZzx3 + iZzy;
                            break;
                        }
                    } else {
                        break;
                    }
                case 9:
                    if (zzP(obj, i2)) {
                        iZzn = zzmv.zzn(i3, zznu.zzf(obj, j), zzB(i2));
                        i += iZzn;
                        break;
                    } else {
                        break;
                    }
                case 10:
                    if (zzP(obj, i2)) {
                        zzka zzkaVar = (zzka) zznu.zzf(obj, j);
                        int i8 = i3 << 3;
                        int i9 = zzki.zzb;
                        int iZzd2 = zzkaVar.zzd();
                        iZzx5 = zzki.zzx(iZzd2) + iZzd2;
                        iZzx6 = zzki.zzx(i8);
                        iZzn = iZzx6 + iZzx5;
                        i += iZzn;
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if (zzP(obj, i2)) {
                        iZzy = zzki.zzx(zznu.zzc(obj, j));
                        iZzx3 = zzki.zzx(i3 << 3);
                        i += iZzx3 + iZzy;
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if (zzP(obj, i2)) {
                        iZzy = zzki.zzu(zznu.zzc(obj, j));
                        iZzx3 = zzki.zzx(i3 << 3);
                        i += iZzx3 + iZzy;
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if (zzP(obj, i2)) {
                        iZzx2 = zzki.zzx(i3 << 3);
                        iZzn = iZzx2 + 4;
                        i += iZzn;
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if (zzP(obj, i2)) {
                        iZzx = zzki.zzx(i3 << 3);
                        iZzn = iZzx + 8;
                        i += iZzn;
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if (zzP(obj, i2)) {
                        int iZzc = zznu.zzc(obj, j);
                        iZzx3 = zzki.zzx(i3 << 3);
                        iZzy = zzki.zzx((iZzc >> 31) ^ (iZzc + iZzc));
                        i += iZzx3 + iZzy;
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if (zzP(obj, i2)) {
                        long jZzd = zznu.zzd(obj, j);
                        iZzx7 = zzki.zzx(i3 << 3);
                        iZzy2 = zzki.zzy((jZzd + jZzd) ^ (jZzd >> 63));
                        iZzn = iZzx7 + iZzy2;
                        i += iZzn;
                        break;
                    } else {
                        break;
                    }
                case 17:
                    if (zzP(obj, i2)) {
                        iZzn = zzki.zzt(i3, (zzmi) zznu.zzf(obj, j), zzB(i2));
                        i += iZzn;
                        break;
                    } else {
                        break;
                    }
                case 18:
                    iZzn = zzmv.zzg(i3, (List) zznu.zzf(obj, j), false);
                    i += iZzn;
                    break;
                case 19:
                    iZzn = zzmv.zze(i3, (List) zznu.zzf(obj, j), false);
                    i += iZzn;
                    break;
                case 20:
                    iZzn = zzmv.zzl(i3, (List) zznu.zzf(obj, j), false);
                    i += iZzn;
                    break;
                case 21:
                    iZzn = zzmv.zzw(i3, (List) zznu.zzf(obj, j), false);
                    i += iZzn;
                    break;
                case 22:
                    iZzn = zzmv.zzj(i3, (List) zznu.zzf(obj, j), false);
                    i += iZzn;
                    break;
                case 23:
                    iZzn = zzmv.zzg(i3, (List) zznu.zzf(obj, j), false);
                    i += iZzn;
                    break;
                case 24:
                    iZzn = zzmv.zze(i3, (List) zznu.zzf(obj, j), false);
                    i += iZzn;
                    break;
                case 25:
                    iZzn = zzmv.zza(i3, (List) zznu.zzf(obj, j), false);
                    i += iZzn;
                    break;
                case 26:
                    iZzn = zzmv.zzt(i3, (List) zznu.zzf(obj, j));
                    i += iZzn;
                    break;
                case 27:
                    iZzn = zzmv.zzo(i3, (List) zznu.zzf(obj, j), zzB(i2));
                    i += iZzn;
                    break;
                case 28:
                    iZzn = zzmv.zzb(i3, (List) zznu.zzf(obj, j));
                    i += iZzn;
                    break;
                case 29:
                    iZzn = zzmv.zzu(i3, (List) zznu.zzf(obj, j), false);
                    i += iZzn;
                    break;
                case 30:
                    iZzn = zzmv.zzc(i3, (List) zznu.zzf(obj, j), false);
                    i += iZzn;
                    break;
                case 31:
                    iZzn = zzmv.zze(i3, (List) zznu.zzf(obj, j), false);
                    i += iZzn;
                    break;
                case 32:
                    iZzn = zzmv.zzg(i3, (List) zznu.zzf(obj, j), false);
                    i += iZzn;
                    break;
                case 33:
                    iZzn = zzmv.zzp(i3, (List) zznu.zzf(obj, j), false);
                    i += iZzn;
                    break;
                case 34:
                    iZzn = zzmv.zzr(i3, (List) zznu.zzf(obj, j), false);
                    i += iZzn;
                    break;
                case 35:
                    iZzy = zzmv.zzh((List) unsafe.getObject(obj, j));
                    if (iZzy > 0) {
                        int i10 = i3 << 3;
                        iZzx8 = zzki.zzx(iZzy);
                        iZzx9 = zzki.zzx(i10);
                        iZzx3 = iZzx9 + iZzx8;
                        i += iZzx3 + iZzy;
                        break;
                    } else {
                        break;
                    }
                case 36:
                    iZzy = zzmv.zzf((List) unsafe.getObject(obj, j));
                    if (iZzy > 0) {
                        int i11 = i3 << 3;
                        iZzx8 = zzki.zzx(iZzy);
                        iZzx9 = zzki.zzx(i11);
                        iZzx3 = iZzx9 + iZzx8;
                        i += iZzx3 + iZzy;
                        break;
                    } else {
                        break;
                    }
                case 37:
                    iZzy = zzmv.zzm((List) unsafe.getObject(obj, j));
                    if (iZzy > 0) {
                        int i12 = i3 << 3;
                        iZzx8 = zzki.zzx(iZzy);
                        iZzx9 = zzki.zzx(i12);
                        iZzx3 = iZzx9 + iZzx8;
                        i += iZzx3 + iZzy;
                        break;
                    } else {
                        break;
                    }
                case 38:
                    iZzy = zzmv.zzx((List) unsafe.getObject(obj, j));
                    if (iZzy > 0) {
                        int i13 = i3 << 3;
                        iZzx8 = zzki.zzx(iZzy);
                        iZzx9 = zzki.zzx(i13);
                        iZzx3 = iZzx9 + iZzx8;
                        i += iZzx3 + iZzy;
                        break;
                    } else {
                        break;
                    }
                case 39:
                    iZzy = zzmv.zzk((List) unsafe.getObject(obj, j));
                    if (iZzy > 0) {
                        int i14 = i3 << 3;
                        iZzx8 = zzki.zzx(iZzy);
                        iZzx9 = zzki.zzx(i14);
                        iZzx3 = iZzx9 + iZzx8;
                        i += iZzx3 + iZzy;
                        break;
                    } else {
                        break;
                    }
                case 40:
                    iZzy = zzmv.zzh((List) unsafe.getObject(obj, j));
                    if (iZzy > 0) {
                        int i15 = i3 << 3;
                        iZzx8 = zzki.zzx(iZzy);
                        iZzx9 = zzki.zzx(i15);
                        iZzx3 = iZzx9 + iZzx8;
                        i += iZzx3 + iZzy;
                        break;
                    } else {
                        break;
                    }
                case 41:
                    iZzy = zzmv.zzf((List) unsafe.getObject(obj, j));
                    if (iZzy > 0) {
                        int i16 = i3 << 3;
                        iZzx8 = zzki.zzx(iZzy);
                        iZzx9 = zzki.zzx(i16);
                        iZzx3 = iZzx9 + iZzx8;
                        i += iZzx3 + iZzy;
                        break;
                    } else {
                        break;
                    }
                case 42:
                    List list = (List) unsafe.getObject(obj, j);
                    int i17 = zzmv.zza;
                    iZzy = list.size();
                    if (iZzy > 0) {
                        int i18 = i3 << 3;
                        iZzx8 = zzki.zzx(iZzy);
                        iZzx9 = zzki.zzx(i18);
                        iZzx3 = iZzx9 + iZzx8;
                        i += iZzx3 + iZzy;
                        break;
                    } else {
                        break;
                    }
                case 43:
                    iZzy = zzmv.zzv((List) unsafe.getObject(obj, j));
                    if (iZzy > 0) {
                        int i19 = i3 << 3;
                        iZzx8 = zzki.zzx(iZzy);
                        iZzx9 = zzki.zzx(i19);
                        iZzx3 = iZzx9 + iZzx8;
                        i += iZzx3 + iZzy;
                        break;
                    } else {
                        break;
                    }
                case 44:
                    iZzy = zzmv.zzd((List) unsafe.getObject(obj, j));
                    if (iZzy > 0) {
                        int i20 = i3 << 3;
                        iZzx8 = zzki.zzx(iZzy);
                        iZzx9 = zzki.zzx(i20);
                        iZzx3 = iZzx9 + iZzx8;
                        i += iZzx3 + iZzy;
                        break;
                    } else {
                        break;
                    }
                case 45:
                    iZzy = zzmv.zzf((List) unsafe.getObject(obj, j));
                    if (iZzy > 0) {
                        int i21 = i3 << 3;
                        iZzx8 = zzki.zzx(iZzy);
                        iZzx9 = zzki.zzx(i21);
                        iZzx3 = iZzx9 + iZzx8;
                        i += iZzx3 + iZzy;
                        break;
                    } else {
                        break;
                    }
                case 46:
                    iZzy = zzmv.zzh((List) unsafe.getObject(obj, j));
                    if (iZzy > 0) {
                        int i22 = i3 << 3;
                        iZzx8 = zzki.zzx(iZzy);
                        iZzx9 = zzki.zzx(i22);
                        iZzx3 = iZzx9 + iZzx8;
                        i += iZzx3 + iZzy;
                        break;
                    } else {
                        break;
                    }
                case 47:
                    iZzy = zzmv.zzq((List) unsafe.getObject(obj, j));
                    if (iZzy > 0) {
                        int i23 = i3 << 3;
                        iZzx8 = zzki.zzx(iZzy);
                        iZzx9 = zzki.zzx(i23);
                        iZzx3 = iZzx9 + iZzx8;
                        i += iZzx3 + iZzy;
                        break;
                    } else {
                        break;
                    }
                case 48:
                    iZzy = zzmv.zzs((List) unsafe.getObject(obj, j));
                    if (iZzy > 0) {
                        int i24 = i3 << 3;
                        iZzx8 = zzki.zzx(iZzy);
                        iZzx9 = zzki.zzx(i24);
                        iZzx3 = iZzx9 + iZzx8;
                        i += iZzx3 + iZzy;
                        break;
                    } else {
                        break;
                    }
                case 49:
                    iZzn = zzmv.zzi(i3, (List) zznu.zzf(obj, j), zzB(i2));
                    i += iZzn;
                    break;
                case 50:
                    zzmd.zza(i3, zznu.zzf(obj, j), zzC(i2));
                    break;
                case 51:
                    if (zzT(obj, i3, i2)) {
                        iZzx = zzki.zzx(i3 << 3);
                        iZzn = iZzx + 8;
                        i += iZzn;
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (zzT(obj, i3, i2)) {
                        iZzx2 = zzki.zzx(i3 << 3);
                        iZzn = iZzx2 + 4;
                        i += iZzn;
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (zzT(obj, i3, i2)) {
                        iZzy = zzki.zzy(zzz(obj, j));
                        iZzx3 = zzki.zzx(i3 << 3);
                        i += iZzx3 + iZzy;
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (zzT(obj, i3, i2)) {
                        iZzy = zzki.zzy(zzz(obj, j));
                        iZzx3 = zzki.zzx(i3 << 3);
                        i += iZzx3 + iZzy;
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (zzT(obj, i3, i2)) {
                        iZzy = zzki.zzu(zzp(obj, j));
                        iZzx3 = zzki.zzx(i3 << 3);
                        i += iZzx3 + iZzy;
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (zzT(obj, i3, i2)) {
                        iZzx = zzki.zzx(i3 << 3);
                        iZzn = iZzx + 8;
                        i += iZzn;
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (zzT(obj, i3, i2)) {
                        iZzx2 = zzki.zzx(i3 << 3);
                        iZzn = iZzx2 + 4;
                        i += iZzn;
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (zzT(obj, i3, i2)) {
                        iZzx4 = zzki.zzx(i3 << 3);
                        iZzn = iZzx4 + 1;
                        i += iZzn;
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (zzT(obj, i3, i2)) {
                        Object objZzf2 = zznu.zzf(obj, j);
                        if (objZzf2 instanceof zzka) {
                            int i25 = i3 << 3;
                            int i26 = zzki.zzb;
                            int iZzd3 = ((zzka) objZzf2).zzd();
                            iZzx5 = zzki.zzx(iZzd3) + iZzd3;
                            iZzx6 = zzki.zzx(i25);
                            iZzn = iZzx6 + iZzx5;
                            i += iZzn;
                            break;
                        } else {
                            iZzy = zzki.zzw((String) objZzf2);
                            iZzx3 = zzki.zzx(i3 << 3);
                            i += iZzx3 + iZzy;
                            break;
                        }
                    } else {
                        break;
                    }
                case 60:
                    if (zzT(obj, i3, i2)) {
                        iZzn = zzmv.zzn(i3, zznu.zzf(obj, j), zzB(i2));
                        i += iZzn;
                        break;
                    } else {
                        break;
                    }
                case 61:
                    if (zzT(obj, i3, i2)) {
                        zzka zzkaVar2 = (zzka) zznu.zzf(obj, j);
                        int i27 = i3 << 3;
                        int i28 = zzki.zzb;
                        int iZzd4 = zzkaVar2.zzd();
                        iZzx5 = zzki.zzx(iZzd4) + iZzd4;
                        iZzx6 = zzki.zzx(i27);
                        iZzn = iZzx6 + iZzx5;
                        i += iZzn;
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (zzT(obj, i3, i2)) {
                        iZzy = zzki.zzx(zzp(obj, j));
                        iZzx3 = zzki.zzx(i3 << 3);
                        i += iZzx3 + iZzy;
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (zzT(obj, i3, i2)) {
                        iZzy = zzki.zzu(zzp(obj, j));
                        iZzx3 = zzki.zzx(i3 << 3);
                        i += iZzx3 + iZzy;
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (zzT(obj, i3, i2)) {
                        iZzx2 = zzki.zzx(i3 << 3);
                        iZzn = iZzx2 + 4;
                        i += iZzn;
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (zzT(obj, i3, i2)) {
                        iZzx = zzki.zzx(i3 << 3);
                        iZzn = iZzx + 8;
                        i += iZzn;
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (zzT(obj, i3, i2)) {
                        int iZzp = zzp(obj, j);
                        iZzx3 = zzki.zzx(i3 << 3);
                        iZzy = zzki.zzx((iZzp >> 31) ^ (iZzp + iZzp));
                        i += iZzx3 + iZzy;
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (zzT(obj, i3, i2)) {
                        long jZzz = zzz(obj, j);
                        iZzx7 = zzki.zzx(i3 << 3);
                        iZzy2 = zzki.zzy((jZzz + jZzz) ^ (jZzz >> 63));
                        iZzn = iZzx7 + iZzy2;
                        i += iZzn;
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (zzT(obj, i3, i2)) {
                        iZzn = zzki.zzt(i3, (zzmi) zznu.zzf(obj, j), zzB(i2));
                        i += iZzn;
                        break;
                    } else {
                        break;
                    }
            }
        }
        zznk zznkVar = this.zzn;
        return i + zznkVar.zza(zznkVar.zzd(obj));
    }

    @Override // com.google.android.gms.internal.measurement.zzmt
    public final int zzb(Object obj) {
        int i;
        long jDoubleToLongBits;
        int iFloatToIntBits;
        int length = this.zzc.length;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3 += 3) {
            int iZzy = zzy(i3);
            int i4 = this.zzc[i3];
            long j = 1048575 & iZzy;
            int iHashCode = 37;
            switch (zzx(iZzy)) {
                case 0:
                    i = i2 * 53;
                    jDoubleToLongBits = Double.doubleToLongBits(zznu.zza(obj, j));
                    byte[] bArr = zzlj.zzd;
                    iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                    i2 = i + iFloatToIntBits;
                    break;
                case 1:
                    i = i2 * 53;
                    iFloatToIntBits = Float.floatToIntBits(zznu.zzb(obj, j));
                    i2 = i + iFloatToIntBits;
                    break;
                case 2:
                    i = i2 * 53;
                    jDoubleToLongBits = zznu.zzd(obj, j);
                    byte[] bArr2 = zzlj.zzd;
                    iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                    i2 = i + iFloatToIntBits;
                    break;
                case 3:
                    i = i2 * 53;
                    jDoubleToLongBits = zznu.zzd(obj, j);
                    byte[] bArr3 = zzlj.zzd;
                    iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                    i2 = i + iFloatToIntBits;
                    break;
                case 4:
                    i = i2 * 53;
                    iFloatToIntBits = zznu.zzc(obj, j);
                    i2 = i + iFloatToIntBits;
                    break;
                case 5:
                    i = i2 * 53;
                    jDoubleToLongBits = zznu.zzd(obj, j);
                    byte[] bArr4 = zzlj.zzd;
                    iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                    i2 = i + iFloatToIntBits;
                    break;
                case 6:
                    i = i2 * 53;
                    iFloatToIntBits = zznu.zzc(obj, j);
                    i2 = i + iFloatToIntBits;
                    break;
                case 7:
                    i = i2 * 53;
                    iFloatToIntBits = zzlj.zza(zznu.zzw(obj, j));
                    i2 = i + iFloatToIntBits;
                    break;
                case 8:
                    i = i2 * 53;
                    iFloatToIntBits = ((String) zznu.zzf(obj, j)).hashCode();
                    i2 = i + iFloatToIntBits;
                    break;
                case 9:
                    Object objZzf = zznu.zzf(obj, j);
                    if (objZzf != null) {
                        iHashCode = objZzf.hashCode();
                    }
                    i2 = (i2 * 53) + iHashCode;
                    break;
                case 10:
                    i = i2 * 53;
                    iFloatToIntBits = zznu.zzf(obj, j).hashCode();
                    i2 = i + iFloatToIntBits;
                    break;
                case 11:
                    i = i2 * 53;
                    iFloatToIntBits = zznu.zzc(obj, j);
                    i2 = i + iFloatToIntBits;
                    break;
                case 12:
                    i = i2 * 53;
                    iFloatToIntBits = zznu.zzc(obj, j);
                    i2 = i + iFloatToIntBits;
                    break;
                case 13:
                    i = i2 * 53;
                    iFloatToIntBits = zznu.zzc(obj, j);
                    i2 = i + iFloatToIntBits;
                    break;
                case 14:
                    i = i2 * 53;
                    jDoubleToLongBits = zznu.zzd(obj, j);
                    byte[] bArr5 = zzlj.zzd;
                    iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                    i2 = i + iFloatToIntBits;
                    break;
                case 15:
                    i = i2 * 53;
                    iFloatToIntBits = zznu.zzc(obj, j);
                    i2 = i + iFloatToIntBits;
                    break;
                case 16:
                    i = i2 * 53;
                    jDoubleToLongBits = zznu.zzd(obj, j);
                    byte[] bArr6 = zzlj.zzd;
                    iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                    i2 = i + iFloatToIntBits;
                    break;
                case 17:
                    Object objZzf2 = zznu.zzf(obj, j);
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
                    iFloatToIntBits = zznu.zzf(obj, j).hashCode();
                    i2 = i + iFloatToIntBits;
                    break;
                case 50:
                    i = i2 * 53;
                    iFloatToIntBits = zznu.zzf(obj, j).hashCode();
                    i2 = i + iFloatToIntBits;
                    break;
                case 51:
                    if (zzT(obj, i4, i3)) {
                        i = i2 * 53;
                        jDoubleToLongBits = Double.doubleToLongBits(zzm(obj, j));
                        byte[] bArr7 = zzlj.zzd;
                        iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                        i2 = i + iFloatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (zzT(obj, i4, i3)) {
                        i = i2 * 53;
                        iFloatToIntBits = Float.floatToIntBits(zzn(obj, j));
                        i2 = i + iFloatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (zzT(obj, i4, i3)) {
                        i = i2 * 53;
                        jDoubleToLongBits = zzz(obj, j);
                        byte[] bArr8 = zzlj.zzd;
                        iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                        i2 = i + iFloatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (zzT(obj, i4, i3)) {
                        i = i2 * 53;
                        jDoubleToLongBits = zzz(obj, j);
                        byte[] bArr9 = zzlj.zzd;
                        iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                        i2 = i + iFloatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (zzT(obj, i4, i3)) {
                        i = i2 * 53;
                        iFloatToIntBits = zzp(obj, j);
                        i2 = i + iFloatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (zzT(obj, i4, i3)) {
                        i = i2 * 53;
                        jDoubleToLongBits = zzz(obj, j);
                        byte[] bArr10 = zzlj.zzd;
                        iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                        i2 = i + iFloatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (zzT(obj, i4, i3)) {
                        i = i2 * 53;
                        iFloatToIntBits = zzp(obj, j);
                        i2 = i + iFloatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (zzT(obj, i4, i3)) {
                        i = i2 * 53;
                        iFloatToIntBits = zzlj.zza(zzU(obj, j));
                        i2 = i + iFloatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (zzT(obj, i4, i3)) {
                        i = i2 * 53;
                        iFloatToIntBits = ((String) zznu.zzf(obj, j)).hashCode();
                        i2 = i + iFloatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 60:
                    if (zzT(obj, i4, i3)) {
                        i = i2 * 53;
                        iFloatToIntBits = zznu.zzf(obj, j).hashCode();
                        i2 = i + iFloatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 61:
                    if (zzT(obj, i4, i3)) {
                        i = i2 * 53;
                        iFloatToIntBits = zznu.zzf(obj, j).hashCode();
                        i2 = i + iFloatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (zzT(obj, i4, i3)) {
                        i = i2 * 53;
                        iFloatToIntBits = zzp(obj, j);
                        i2 = i + iFloatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (zzT(obj, i4, i3)) {
                        i = i2 * 53;
                        iFloatToIntBits = zzp(obj, j);
                        i2 = i + iFloatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (zzT(obj, i4, i3)) {
                        i = i2 * 53;
                        iFloatToIntBits = zzp(obj, j);
                        i2 = i + iFloatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (zzT(obj, i4, i3)) {
                        i = i2 * 53;
                        jDoubleToLongBits = zzz(obj, j);
                        byte[] bArr11 = zzlj.zzd;
                        iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                        i2 = i + iFloatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (zzT(obj, i4, i3)) {
                        i = i2 * 53;
                        iFloatToIntBits = zzp(obj, j);
                        i2 = i + iFloatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (zzT(obj, i4, i3)) {
                        i = i2 * 53;
                        jDoubleToLongBits = zzz(obj, j);
                        byte[] bArr12 = zzlj.zzd;
                        iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                        i2 = i + iFloatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (zzT(obj, i4, i3)) {
                        i = i2 * 53;
                        iFloatToIntBits = zznu.zzf(obj, j).hashCode();
                        i2 = i + iFloatToIntBits;
                        break;
                    } else {
                        break;
                    }
            }
        }
        int iHashCode2 = (i2 * 53) + this.zzn.zzd(obj).hashCode();
        if (!this.zzh) {
            return iHashCode2;
        }
        this.zzo.zza(obj);
        throw null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:109:0x030e, code lost:
    
        if (r0 != r22) goto L110;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x0310, code lost:
    
        r15 = r28;
        r14 = r29;
        r12 = r30;
        r1 = r31;
        r13 = r32;
        r11 = r33;
        r9 = r34;
        r8 = r19;
        r5 = r20;
        r3 = r21;
        r2 = r22;
        r6 = r24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x032c, code lost:
    
        r2 = r0;
        r7 = r21;
        r6 = r24;
        r0 = r33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:118:0x0360, code lost:
    
        if (r0 != r15) goto L110;
     */
    /* JADX WARN: Code restructure failed: missing block: B:122:0x0388, code lost:
    
        if (r0 != r15) goto L110;
     */
    /* JADX WARN: Code restructure failed: missing block: B:142:0x0403, code lost:
    
        if (r6 == 1048575) goto L144;
     */
    /* JADX WARN: Code restructure failed: missing block: B:143:0x0405, code lost:
    
        r27.putInt(r12, r6, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:144:0x040b, code lost:
    
        r2 = r8.zzk;
     */
    /* JADX WARN: Code restructure failed: missing block: B:146:0x040f, code lost:
    
        if (r2 >= r8.zzl) goto L208;
     */
    /* JADX WARN: Code restructure failed: missing block: B:147:0x0411, code lost:
    
        r4 = r8.zzj[r2];
        r5 = r8.zzc[r4];
        r5 = com.google.android.gms.internal.measurement.zznu.zzf(r12, r8.zzy(r4) & 1048575);
     */
    /* JADX WARN: Code restructure failed: missing block: B:148:0x0423, code lost:
    
        if (r5 != null) goto L150;
     */
    /* JADX WARN: Code restructure failed: missing block: B:151:0x042a, code lost:
    
        if (r8.zzA(r4) != null) goto L209;
     */
    /* JADX WARN: Code restructure failed: missing block: B:152:0x042c, code lost:
    
        r2 = r2 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:153:0x042f, code lost:
    
        r5 = (com.google.android.gms.internal.measurement.zzmc) r5;
        r0 = (com.google.android.gms.internal.measurement.zzmb) r8.zzC(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:154:0x0437, code lost:
    
        throw null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:155:0x0438, code lost:
    
        if (r9 != 0) goto L161;
     */
    /* JADX WARN: Code restructure failed: missing block: B:157:0x043c, code lost:
    
        if (r0 != r32) goto L159;
     */
    /* JADX WARN: Code restructure failed: missing block: B:160:0x0443, code lost:
    
        throw com.google.android.gms.internal.measurement.zzll.zze();
     */
    /* JADX WARN: Code restructure failed: missing block: B:162:0x0446, code lost:
    
        if (r0 > r32) goto L165;
     */
    /* JADX WARN: Code restructure failed: missing block: B:163:0x0448, code lost:
    
        if (r3 != r9) goto L165;
     */
    /* JADX WARN: Code restructure failed: missing block: B:164:0x044a, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:166:0x044f, code lost:
    
        throw com.google.android.gms.internal.measurement.zzll.zze();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    final int zzc(java.lang.Object r29, byte[] r30, int r31, int r32, int r33, com.google.android.gms.internal.measurement.zzjn r34) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1142
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzml.zzc(java.lang.Object, byte[], int, int, int, com.google.android.gms.internal.measurement.zzjn):int");
    }

    @Override // com.google.android.gms.internal.measurement.zzmt
    public final Object zze() {
        return ((zzlb) this.zzg).zzbD();
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x006c  */
    @Override // com.google.android.gms.internal.measurement.zzmt
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void zzf(java.lang.Object r8) {
        /*
            r7 = this;
            boolean r0 = zzS(r8)
            if (r0 != 0) goto L7
            return
        L7:
            boolean r0 = r8 instanceof com.google.android.gms.internal.measurement.zzlb
            r1 = 0
            if (r0 == 0) goto L1a
            r0 = r8
            com.google.android.gms.internal.measurement.zzlb r0 = (com.google.android.gms.internal.measurement.zzlb) r0
            r2 = 2147483647(0x7fffffff, float:NaN)
            r0.zzbP(r2)
            r0.zzb = r1
            r0.zzbN()
        L1a:
            int[] r0 = r7.zzc
            int r0 = r0.length
        L1d:
            if (r1 >= r0) goto L82
            int r2 = r7.zzy(r1)
            r3 = 1048575(0xfffff, float:1.469367E-39)
            r3 = r3 & r2
            int r2 = zzx(r2)
            long r3 = (long) r3
            r5 = 9
            if (r2 == r5) goto L6c
            r5 = 60
            if (r2 == r5) goto L54
            r5 = 68
            if (r2 == r5) goto L54
            switch(r2) {
                case 17: goto L6c;
                case 18: goto L4e;
                case 19: goto L4e;
                case 20: goto L4e;
                case 21: goto L4e;
                case 22: goto L4e;
                case 23: goto L4e;
                case 24: goto L4e;
                case 25: goto L4e;
                case 26: goto L4e;
                case 27: goto L4e;
                case 28: goto L4e;
                case 29: goto L4e;
                case 30: goto L4e;
                case 31: goto L4e;
                case 32: goto L4e;
                case 33: goto L4e;
                case 34: goto L4e;
                case 35: goto L4e;
                case 36: goto L4e;
                case 37: goto L4e;
                case 38: goto L4e;
                case 39: goto L4e;
                case 40: goto L4e;
                case 41: goto L4e;
                case 42: goto L4e;
                case 43: goto L4e;
                case 44: goto L4e;
                case 45: goto L4e;
                case 46: goto L4e;
                case 47: goto L4e;
                case 48: goto L4e;
                case 49: goto L4e;
                case 50: goto L3c;
                default: goto L3b;
            }
        L3b:
            goto L7f
        L3c:
            sun.misc.Unsafe r2 = com.google.android.gms.internal.measurement.zzml.zzb
            java.lang.Object r5 = r2.getObject(r8, r3)
            if (r5 == 0) goto L7f
            r6 = r5
            com.google.android.gms.internal.measurement.zzmc r6 = (com.google.android.gms.internal.measurement.zzmc) r6
            r6.zzc()
            r2.putObject(r8, r3, r5)
            goto L7f
        L4e:
            com.google.android.gms.internal.measurement.zzlw r2 = r7.zzm
            r2.zza(r8, r3)
            goto L7f
        L54:
            int[] r2 = r7.zzc
            r2 = r2[r1]
            boolean r2 = r7.zzT(r8, r2, r1)
            if (r2 == 0) goto L7f
            com.google.android.gms.internal.measurement.zzmt r2 = r7.zzB(r1)
            sun.misc.Unsafe r5 = com.google.android.gms.internal.measurement.zzml.zzb
            java.lang.Object r3 = r5.getObject(r8, r3)
            r2.zzf(r3)
            goto L7f
        L6c:
            boolean r2 = r7.zzP(r8, r1)
            if (r2 == 0) goto L7f
            com.google.android.gms.internal.measurement.zzmt r2 = r7.zzB(r1)
            sun.misc.Unsafe r5 = com.google.android.gms.internal.measurement.zzml.zzb
            java.lang.Object r3 = r5.getObject(r8, r3)
            r2.zzf(r3)
        L7f:
            int r1 = r1 + 3
            goto L1d
        L82:
            com.google.android.gms.internal.measurement.zznk r0 = r7.zzn
            r0.zzg(r8)
            boolean r0 = r7.zzh
            if (r0 == 0) goto L90
            com.google.android.gms.internal.measurement.zzko r0 = r7.zzo
            r0.zzb(r8)
        L90:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzml.zzf(java.lang.Object):void");
    }

    @Override // com.google.android.gms.internal.measurement.zzmt
    public final void zzg(Object obj, Object obj2) {
        zzG(obj);
        obj2.getClass();
        for (int i = 0; i < this.zzc.length; i += 3) {
            int iZzy = zzy(i);
            int i2 = this.zzc[i];
            long j = 1048575 & iZzy;
            switch (zzx(iZzy)) {
                case 0:
                    if (zzP(obj2, i)) {
                        zznu.zzo(obj, j, zznu.zza(obj2, j));
                        zzJ(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (zzP(obj2, i)) {
                        zznu.zzp(obj, j, zznu.zzb(obj2, j));
                        zzJ(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (zzP(obj2, i)) {
                        zznu.zzr(obj, j, zznu.zzd(obj2, j));
                        zzJ(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (zzP(obj2, i)) {
                        zznu.zzr(obj, j, zznu.zzd(obj2, j));
                        zzJ(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (zzP(obj2, i)) {
                        zznu.zzq(obj, j, zznu.zzc(obj2, j));
                        zzJ(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (zzP(obj2, i)) {
                        zznu.zzr(obj, j, zznu.zzd(obj2, j));
                        zzJ(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (zzP(obj2, i)) {
                        zznu.zzq(obj, j, zznu.zzc(obj2, j));
                        zzJ(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if (zzP(obj2, i)) {
                        zznu.zzm(obj, j, zznu.zzw(obj2, j));
                        zzJ(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (zzP(obj2, i)) {
                        zznu.zzs(obj, j, zznu.zzf(obj2, j));
                        zzJ(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 9:
                    zzH(obj, obj2, i);
                    break;
                case 10:
                    if (zzP(obj2, i)) {
                        zznu.zzs(obj, j, zznu.zzf(obj2, j));
                        zzJ(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if (zzP(obj2, i)) {
                        zznu.zzq(obj, j, zznu.zzc(obj2, j));
                        zzJ(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if (zzP(obj2, i)) {
                        zznu.zzq(obj, j, zznu.zzc(obj2, j));
                        zzJ(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if (zzP(obj2, i)) {
                        zznu.zzq(obj, j, zznu.zzc(obj2, j));
                        zzJ(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if (zzP(obj2, i)) {
                        zznu.zzr(obj, j, zznu.zzd(obj2, j));
                        zzJ(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if (zzP(obj2, i)) {
                        zznu.zzq(obj, j, zznu.zzc(obj2, j));
                        zzJ(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if (zzP(obj2, i)) {
                        zznu.zzr(obj, j, zznu.zzd(obj2, j));
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
                    int i3 = zzmv.zza;
                    zznu.zzs(obj, j, zzmd.zzb(zznu.zzf(obj, j), zznu.zzf(obj2, j)));
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
                    if (zzT(obj2, i2, i)) {
                        zznu.zzs(obj, j, zznu.zzf(obj2, j));
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
                    if (zzT(obj2, i2, i)) {
                        zznu.zzs(obj, j, zznu.zzf(obj2, j));
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
        zzmv.zzB(this.zzn, obj, obj2);
        if (this.zzh) {
            this.zzo.zza(obj2);
            throw null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:101:0x02e8, code lost:
    
        if (r0 != r24) goto L102;
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x02ea, code lost:
    
        r14 = r31;
        r12 = r32;
        r13 = r34;
        r11 = r35;
        r2 = r15;
        r10 = r18;
        r1 = r23;
        r6 = r25;
        r7 = r26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x02fd, code lost:
    
        r2 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x0329, code lost:
    
        if (r0 != r14) goto L102;
     */
    /* JADX WARN: Code restructure failed: missing block: B:114:0x034c, code lost:
    
        if (r0 != r14) goto L102;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:29:0x0095. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v12, types: [int] */
    @Override // com.google.android.gms.internal.measurement.zzmt
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void zzh(java.lang.Object r31, byte[] r32, int r33, int r34, com.google.android.gms.internal.measurement.zzjn r35) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 970
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzml.zzh(java.lang.Object, byte[], int, int, com.google.android.gms.internal.measurement.zzjn):void");
    }

    @Override // com.google.android.gms.internal.measurement.zzmt
    public final boolean zzj(Object obj, Object obj2) {
        boolean zZzV;
        int length = this.zzc.length;
        for (int i = 0; i < length; i += 3) {
            int iZzy = zzy(i);
            long j = iZzy & 1048575;
            switch (zzx(iZzy)) {
                case 0:
                    if (!zzO(obj, obj2, i) || Double.doubleToLongBits(zznu.zza(obj, j)) != Double.doubleToLongBits(zznu.zza(obj2, j))) {
                        return false;
                    }
                    continue;
                    break;
                case 1:
                    if (!zzO(obj, obj2, i) || Float.floatToIntBits(zznu.zzb(obj, j)) != Float.floatToIntBits(zznu.zzb(obj2, j))) {
                        return false;
                    }
                    continue;
                    break;
                case 2:
                    if (!zzO(obj, obj2, i) || zznu.zzd(obj, j) != zznu.zzd(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                case 3:
                    if (!zzO(obj, obj2, i) || zznu.zzd(obj, j) != zznu.zzd(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                case 4:
                    if (!zzO(obj, obj2, i) || zznu.zzc(obj, j) != zznu.zzc(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                case 5:
                    if (!zzO(obj, obj2, i) || zznu.zzd(obj, j) != zznu.zzd(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                case 6:
                    if (!zzO(obj, obj2, i) || zznu.zzc(obj, j) != zznu.zzc(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                case 7:
                    if (!zzO(obj, obj2, i) || zznu.zzw(obj, j) != zznu.zzw(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                case 8:
                    if (!zzO(obj, obj2, i) || !zzmv.zzV(zznu.zzf(obj, j), zznu.zzf(obj2, j))) {
                        return false;
                    }
                    continue;
                    break;
                case 9:
                    if (!zzO(obj, obj2, i) || !zzmv.zzV(zznu.zzf(obj, j), zznu.zzf(obj2, j))) {
                        return false;
                    }
                    continue;
                    break;
                case 10:
                    if (!zzO(obj, obj2, i) || !zzmv.zzV(zznu.zzf(obj, j), zznu.zzf(obj2, j))) {
                        return false;
                    }
                    continue;
                    break;
                case 11:
                    if (!zzO(obj, obj2, i) || zznu.zzc(obj, j) != zznu.zzc(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                case 12:
                    if (!zzO(obj, obj2, i) || zznu.zzc(obj, j) != zznu.zzc(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                case 13:
                    if (!zzO(obj, obj2, i) || zznu.zzc(obj, j) != zznu.zzc(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                case 14:
                    if (!zzO(obj, obj2, i) || zznu.zzd(obj, j) != zznu.zzd(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                case 15:
                    if (!zzO(obj, obj2, i) || zznu.zzc(obj, j) != zznu.zzc(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                case 16:
                    if (!zzO(obj, obj2, i) || zznu.zzd(obj, j) != zznu.zzd(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                case 17:
                    if (!zzO(obj, obj2, i) || !zzmv.zzV(zznu.zzf(obj, j), zznu.zzf(obj2, j))) {
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
                    zZzV = zzmv.zzV(zznu.zzf(obj, j), zznu.zzf(obj2, j));
                    break;
                case 50:
                    zZzV = zzmv.zzV(zznu.zzf(obj, j), zznu.zzf(obj2, j));
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
                    long jZzv = zzv(i) & 1048575;
                    if (zznu.zzc(obj, jZzv) != zznu.zzc(obj2, jZzv) || !zzmv.zzV(zznu.zzf(obj, j), zznu.zzf(obj2, j))) {
                        return false;
                    }
                    continue;
                    break;
                default:
            }
            if (!zZzV) {
                return false;
            }
        }
        if (!this.zzn.zzd(obj).equals(this.zzn.zzd(obj2))) {
            return false;
        }
        if (!this.zzh) {
            return true;
        }
        this.zzo.zza(obj);
        this.zzo.zza(obj2);
        throw null;
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x009e  */
    @Override // com.google.android.gms.internal.measurement.zzmt
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean zzk(java.lang.Object r19) {
        /*
            Method dump skipped, instructions count: 244
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzml.zzk(java.lang.Object):boolean");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.google.android.gms.internal.measurement.zzmt
    public final void zzi(Object obj, zzoc zzocVar) throws IOException {
        int i;
        int i2;
        int i3;
        int i4 = 0;
        int i5 = 1048575;
        if (this.zzi) {
            if (this.zzh) {
                this.zzo.zza(obj);
                throw null;
            }
            int length = this.zzc.length;
            for (int i6 = 0; i6 < length; i6 += 3) {
                int iZzy = zzy(i6);
                int i7 = this.zzc[i6];
                switch (zzx(iZzy)) {
                    case 0:
                        if (zzP(obj, i6)) {
                            zzocVar.zzf(i7, zznu.zza(obj, iZzy & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 1:
                        if (zzP(obj, i6)) {
                            zzocVar.zzo(i7, zznu.zzb(obj, iZzy & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 2:
                        if (zzP(obj, i6)) {
                            zzocVar.zzt(i7, zznu.zzd(obj, iZzy & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 3:
                        if (zzP(obj, i6)) {
                            zzocVar.zzJ(i7, zznu.zzd(obj, iZzy & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 4:
                        if (zzP(obj, i6)) {
                            zzocVar.zzr(i7, zznu.zzc(obj, iZzy & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 5:
                        if (zzP(obj, i6)) {
                            zzocVar.zzm(i7, zznu.zzd(obj, iZzy & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 6:
                        if (zzP(obj, i6)) {
                            zzocVar.zzk(i7, zznu.zzc(obj, iZzy & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 7:
                        if (zzP(obj, i6)) {
                            zzocVar.zzb(i7, zznu.zzw(obj, iZzy & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 8:
                        if (zzP(obj, i6)) {
                            zzV(i7, zznu.zzf(obj, iZzy & 1048575), zzocVar);
                            break;
                        } else {
                            break;
                        }
                    case 9:
                        if (zzP(obj, i6)) {
                            zzocVar.zzv(i7, zznu.zzf(obj, iZzy & 1048575), zzB(i6));
                            break;
                        } else {
                            break;
                        }
                    case 10:
                        if (zzP(obj, i6)) {
                            zzocVar.zzd(i7, (zzka) zznu.zzf(obj, iZzy & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 11:
                        if (zzP(obj, i6)) {
                            zzocVar.zzH(i7, zznu.zzc(obj, iZzy & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 12:
                        if (zzP(obj, i6)) {
                            zzocVar.zzi(i7, zznu.zzc(obj, iZzy & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 13:
                        if (zzP(obj, i6)) {
                            zzocVar.zzw(i7, zznu.zzc(obj, iZzy & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 14:
                        if (zzP(obj, i6)) {
                            zzocVar.zzy(i7, zznu.zzd(obj, iZzy & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 15:
                        if (zzP(obj, i6)) {
                            zzocVar.zzA(i7, zznu.zzc(obj, iZzy & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 16:
                        if (zzP(obj, i6)) {
                            zzocVar.zzC(i7, zznu.zzd(obj, iZzy & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 17:
                        if (zzP(obj, i6)) {
                            zzocVar.zzq(i7, zznu.zzf(obj, iZzy & 1048575), zzB(i6));
                            break;
                        } else {
                            break;
                        }
                    case 18:
                        zzmv.zzF(i7, (List) zznu.zzf(obj, iZzy & 1048575), zzocVar, false);
                        break;
                    case 19:
                        zzmv.zzJ(i7, (List) zznu.zzf(obj, iZzy & 1048575), zzocVar, false);
                        break;
                    case 20:
                        zzmv.zzM(i7, (List) zznu.zzf(obj, iZzy & 1048575), zzocVar, false);
                        break;
                    case 21:
                        zzmv.zzU(i7, (List) zznu.zzf(obj, iZzy & 1048575), zzocVar, false);
                        break;
                    case 22:
                        zzmv.zzL(i7, (List) zznu.zzf(obj, iZzy & 1048575), zzocVar, false);
                        break;
                    case 23:
                        zzmv.zzI(i7, (List) zznu.zzf(obj, iZzy & 1048575), zzocVar, false);
                        break;
                    case 24:
                        zzmv.zzH(i7, (List) zznu.zzf(obj, iZzy & 1048575), zzocVar, false);
                        break;
                    case 25:
                        zzmv.zzD(i7, (List) zznu.zzf(obj, iZzy & 1048575), zzocVar, false);
                        break;
                    case 26:
                        zzmv.zzS(i7, (List) zznu.zzf(obj, iZzy & 1048575), zzocVar);
                        break;
                    case 27:
                        zzmv.zzN(i7, (List) zznu.zzf(obj, iZzy & 1048575), zzocVar, zzB(i6));
                        break;
                    case 28:
                        zzmv.zzE(i7, (List) zznu.zzf(obj, iZzy & 1048575), zzocVar);
                        break;
                    case 29:
                        zzmv.zzT(i7, (List) zznu.zzf(obj, iZzy & 1048575), zzocVar, false);
                        break;
                    case 30:
                        zzmv.zzG(i7, (List) zznu.zzf(obj, iZzy & 1048575), zzocVar, false);
                        break;
                    case 31:
                        zzmv.zzO(i7, (List) zznu.zzf(obj, iZzy & 1048575), zzocVar, false);
                        break;
                    case 32:
                        zzmv.zzP(i7, (List) zznu.zzf(obj, iZzy & 1048575), zzocVar, false);
                        break;
                    case 33:
                        zzmv.zzQ(i7, (List) zznu.zzf(obj, iZzy & 1048575), zzocVar, false);
                        break;
                    case 34:
                        zzmv.zzR(i7, (List) zznu.zzf(obj, iZzy & 1048575), zzocVar, false);
                        break;
                    case 35:
                        zzmv.zzF(i7, (List) zznu.zzf(obj, iZzy & 1048575), zzocVar, true);
                        break;
                    case 36:
                        zzmv.zzJ(i7, (List) zznu.zzf(obj, iZzy & 1048575), zzocVar, true);
                        break;
                    case 37:
                        zzmv.zzM(i7, (List) zznu.zzf(obj, iZzy & 1048575), zzocVar, true);
                        break;
                    case 38:
                        zzmv.zzU(i7, (List) zznu.zzf(obj, iZzy & 1048575), zzocVar, true);
                        break;
                    case 39:
                        zzmv.zzL(i7, (List) zznu.zzf(obj, iZzy & 1048575), zzocVar, true);
                        break;
                    case 40:
                        zzmv.zzI(i7, (List) zznu.zzf(obj, iZzy & 1048575), zzocVar, true);
                        break;
                    case 41:
                        zzmv.zzH(i7, (List) zznu.zzf(obj, iZzy & 1048575), zzocVar, true);
                        break;
                    case 42:
                        zzmv.zzD(i7, (List) zznu.zzf(obj, iZzy & 1048575), zzocVar, true);
                        break;
                    case 43:
                        zzmv.zzT(i7, (List) zznu.zzf(obj, iZzy & 1048575), zzocVar, true);
                        break;
                    case 44:
                        zzmv.zzG(i7, (List) zznu.zzf(obj, iZzy & 1048575), zzocVar, true);
                        break;
                    case 45:
                        zzmv.zzO(i7, (List) zznu.zzf(obj, iZzy & 1048575), zzocVar, true);
                        break;
                    case 46:
                        zzmv.zzP(i7, (List) zznu.zzf(obj, iZzy & 1048575), zzocVar, true);
                        break;
                    case 47:
                        zzmv.zzQ(i7, (List) zznu.zzf(obj, iZzy & 1048575), zzocVar, true);
                        break;
                    case 48:
                        zzmv.zzR(i7, (List) zznu.zzf(obj, iZzy & 1048575), zzocVar, true);
                        break;
                    case 49:
                        zzmv.zzK(i7, (List) zznu.zzf(obj, iZzy & 1048575), zzocVar, zzB(i6));
                        break;
                    case 50:
                        zzN(zzocVar, i7, zznu.zzf(obj, iZzy & 1048575), i6);
                        break;
                    case 51:
                        if (zzT(obj, i7, i6)) {
                            zzocVar.zzf(i7, zzm(obj, iZzy & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 52:
                        if (zzT(obj, i7, i6)) {
                            zzocVar.zzo(i7, zzn(obj, iZzy & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 53:
                        if (zzT(obj, i7, i6)) {
                            zzocVar.zzt(i7, zzz(obj, iZzy & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 54:
                        if (zzT(obj, i7, i6)) {
                            zzocVar.zzJ(i7, zzz(obj, iZzy & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 55:
                        if (zzT(obj, i7, i6)) {
                            zzocVar.zzr(i7, zzp(obj, iZzy & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 56:
                        if (zzT(obj, i7, i6)) {
                            zzocVar.zzm(i7, zzz(obj, iZzy & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 57:
                        if (zzT(obj, i7, i6)) {
                            zzocVar.zzk(i7, zzp(obj, iZzy & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 58:
                        if (zzT(obj, i7, i6)) {
                            zzocVar.zzb(i7, zzU(obj, iZzy & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 59:
                        if (zzT(obj, i7, i6)) {
                            zzV(i7, zznu.zzf(obj, iZzy & 1048575), zzocVar);
                            break;
                        } else {
                            break;
                        }
                    case 60:
                        if (zzT(obj, i7, i6)) {
                            zzocVar.zzv(i7, zznu.zzf(obj, iZzy & 1048575), zzB(i6));
                            break;
                        } else {
                            break;
                        }
                    case 61:
                        if (zzT(obj, i7, i6)) {
                            zzocVar.zzd(i7, (zzka) zznu.zzf(obj, iZzy & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 62:
                        if (zzT(obj, i7, i6)) {
                            zzocVar.zzH(i7, zzp(obj, iZzy & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 63:
                        if (zzT(obj, i7, i6)) {
                            zzocVar.zzi(i7, zzp(obj, iZzy & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 64:
                        if (zzT(obj, i7, i6)) {
                            zzocVar.zzw(i7, zzp(obj, iZzy & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 65:
                        if (zzT(obj, i7, i6)) {
                            zzocVar.zzy(i7, zzz(obj, iZzy & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 66:
                        if (zzT(obj, i7, i6)) {
                            zzocVar.zzA(i7, zzp(obj, iZzy & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 67:
                        if (zzT(obj, i7, i6)) {
                            zzocVar.zzC(i7, zzz(obj, iZzy & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 68:
                        if (zzT(obj, i7, i6)) {
                            zzocVar.zzq(i7, zznu.zzf(obj, iZzy & 1048575), zzB(i6));
                            break;
                        } else {
                            break;
                        }
                }
            }
            zznk zznkVar = this.zzn;
            zznkVar.zzi(zznkVar.zzd(obj), zzocVar);
            return;
        }
        if (this.zzh) {
            this.zzo.zza(obj);
            throw null;
        }
        int length2 = this.zzc.length;
        Unsafe unsafe = zzb;
        int i8 = 0;
        int i9 = 0;
        int i10 = 1048575;
        while (i8 < length2) {
            int iZzy2 = zzy(i8);
            int[] iArr = this.zzc;
            int i11 = iArr[i8];
            int iZzx = zzx(iZzy2);
            if (iZzx <= 17) {
                int i12 = iArr[i8 + 2];
                int i13 = i12 & i5;
                if (i13 != i10) {
                    i9 = unsafe.getInt(obj, i13);
                    i10 = i13;
                }
                i = 1 << (i12 >>> 20);
            } else {
                i = i4;
            }
            long j = iZzy2 & i5;
            switch (iZzx) {
                case 0:
                    i2 = 0;
                    if ((i9 & i) != 0) {
                        zzocVar.zzf(i11, zznu.zza(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 1:
                    i2 = 0;
                    if ((i9 & i) != 0) {
                        zzocVar.zzo(i11, zznu.zzb(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 2:
                    i2 = 0;
                    if ((i9 & i) != 0) {
                        zzocVar.zzt(i11, unsafe.getLong(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 3:
                    i2 = 0;
                    if ((i9 & i) != 0) {
                        zzocVar.zzJ(i11, unsafe.getLong(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 4:
                    i2 = 0;
                    if ((i9 & i) != 0) {
                        zzocVar.zzr(i11, unsafe.getInt(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 5:
                    i2 = 0;
                    if ((i9 & i) != 0) {
                        zzocVar.zzm(i11, unsafe.getLong(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 6:
                    i2 = 0;
                    if ((i9 & i) != 0) {
                        zzocVar.zzk(i11, unsafe.getInt(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 7:
                    i2 = 0;
                    if ((i9 & i) != 0) {
                        zzocVar.zzb(i11, zznu.zzw(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 8:
                    i2 = 0;
                    if ((i9 & i) != 0) {
                        zzV(i11, unsafe.getObject(obj, j), zzocVar);
                        break;
                    } else {
                        break;
                    }
                case 9:
                    i2 = 0;
                    if ((i9 & i) != 0) {
                        zzocVar.zzv(i11, unsafe.getObject(obj, j), zzB(i8));
                        break;
                    } else {
                        break;
                    }
                case 10:
                    i2 = 0;
                    if ((i9 & i) != 0) {
                        zzocVar.zzd(i11, (zzka) unsafe.getObject(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 11:
                    i2 = 0;
                    if ((i9 & i) != 0) {
                        zzocVar.zzH(i11, unsafe.getInt(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 12:
                    i2 = 0;
                    if ((i9 & i) != 0) {
                        zzocVar.zzi(i11, unsafe.getInt(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 13:
                    i2 = 0;
                    if ((i9 & i) != 0) {
                        zzocVar.zzw(i11, unsafe.getInt(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 14:
                    i2 = 0;
                    if ((i9 & i) != 0) {
                        zzocVar.zzy(i11, unsafe.getLong(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 15:
                    i2 = 0;
                    if ((i9 & i) != 0) {
                        zzocVar.zzA(i11, unsafe.getInt(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 16:
                    i2 = 0;
                    if ((i9 & i) != 0) {
                        zzocVar.zzC(i11, unsafe.getLong(obj, j));
                        break;
                    } else {
                        break;
                    }
                case 17:
                    i2 = 0;
                    if ((i9 & i) != 0) {
                        zzocVar.zzq(i11, unsafe.getObject(obj, j), zzB(i8));
                        break;
                    } else {
                        break;
                    }
                case 18:
                    i2 = 0;
                    zzmv.zzF(this.zzc[i8], (List) unsafe.getObject(obj, j), zzocVar, false);
                    break;
                case 19:
                    i2 = 0;
                    zzmv.zzJ(this.zzc[i8], (List) unsafe.getObject(obj, j), zzocVar, false);
                    break;
                case 20:
                    i2 = 0;
                    zzmv.zzM(this.zzc[i8], (List) unsafe.getObject(obj, j), zzocVar, false);
                    break;
                case 21:
                    i2 = 0;
                    zzmv.zzU(this.zzc[i8], (List) unsafe.getObject(obj, j), zzocVar, false);
                    break;
                case 22:
                    i2 = 0;
                    zzmv.zzL(this.zzc[i8], (List) unsafe.getObject(obj, j), zzocVar, false);
                    break;
                case 23:
                    i2 = 0;
                    zzmv.zzI(this.zzc[i8], (List) unsafe.getObject(obj, j), zzocVar, false);
                    break;
                case 24:
                    i2 = 0;
                    zzmv.zzH(this.zzc[i8], (List) unsafe.getObject(obj, j), zzocVar, false);
                    break;
                case 25:
                    i2 = 0;
                    zzmv.zzD(this.zzc[i8], (List) unsafe.getObject(obj, j), zzocVar, false);
                    break;
                case 26:
                    zzmv.zzS(this.zzc[i8], (List) unsafe.getObject(obj, j), zzocVar);
                    i2 = 0;
                    break;
                case 27:
                    zzmv.zzN(this.zzc[i8], (List) unsafe.getObject(obj, j), zzocVar, zzB(i8));
                    i2 = 0;
                    break;
                case 28:
                    zzmv.zzE(this.zzc[i8], (List) unsafe.getObject(obj, j), zzocVar);
                    i2 = 0;
                    break;
                case 29:
                    i3 = 0;
                    zzmv.zzT(this.zzc[i8], (List) unsafe.getObject(obj, j), zzocVar, false);
                    i2 = i3;
                    break;
                case 30:
                    i3 = 0;
                    zzmv.zzG(this.zzc[i8], (List) unsafe.getObject(obj, j), zzocVar, false);
                    i2 = i3;
                    break;
                case 31:
                    i3 = 0;
                    zzmv.zzO(this.zzc[i8], (List) unsafe.getObject(obj, j), zzocVar, false);
                    i2 = i3;
                    break;
                case 32:
                    i3 = 0;
                    zzmv.zzP(this.zzc[i8], (List) unsafe.getObject(obj, j), zzocVar, false);
                    i2 = i3;
                    break;
                case 33:
                    i3 = 0;
                    zzmv.zzQ(this.zzc[i8], (List) unsafe.getObject(obj, j), zzocVar, false);
                    i2 = i3;
                    break;
                case 34:
                    i3 = 0;
                    zzmv.zzR(this.zzc[i8], (List) unsafe.getObject(obj, j), zzocVar, false);
                    i2 = i3;
                    break;
                case 35:
                    zzmv.zzF(this.zzc[i8], (List) unsafe.getObject(obj, j), zzocVar, true);
                    i2 = 0;
                    break;
                case 36:
                    zzmv.zzJ(this.zzc[i8], (List) unsafe.getObject(obj, j), zzocVar, true);
                    i2 = 0;
                    break;
                case 37:
                    zzmv.zzM(this.zzc[i8], (List) unsafe.getObject(obj, j), zzocVar, true);
                    i2 = 0;
                    break;
                case 38:
                    zzmv.zzU(this.zzc[i8], (List) unsafe.getObject(obj, j), zzocVar, true);
                    i2 = 0;
                    break;
                case 39:
                    zzmv.zzL(this.zzc[i8], (List) unsafe.getObject(obj, j), zzocVar, true);
                    i2 = 0;
                    break;
                case 40:
                    zzmv.zzI(this.zzc[i8], (List) unsafe.getObject(obj, j), zzocVar, true);
                    i2 = 0;
                    break;
                case 41:
                    zzmv.zzH(this.zzc[i8], (List) unsafe.getObject(obj, j), zzocVar, true);
                    i2 = 0;
                    break;
                case 42:
                    zzmv.zzD(this.zzc[i8], (List) unsafe.getObject(obj, j), zzocVar, true);
                    i2 = 0;
                    break;
                case 43:
                    zzmv.zzT(this.zzc[i8], (List) unsafe.getObject(obj, j), zzocVar, true);
                    i2 = 0;
                    break;
                case 44:
                    zzmv.zzG(this.zzc[i8], (List) unsafe.getObject(obj, j), zzocVar, true);
                    i2 = 0;
                    break;
                case 45:
                    zzmv.zzO(this.zzc[i8], (List) unsafe.getObject(obj, j), zzocVar, true);
                    i2 = 0;
                    break;
                case 46:
                    zzmv.zzP(this.zzc[i8], (List) unsafe.getObject(obj, j), zzocVar, true);
                    i2 = 0;
                    break;
                case 47:
                    zzmv.zzQ(this.zzc[i8], (List) unsafe.getObject(obj, j), zzocVar, true);
                    i2 = 0;
                    break;
                case 48:
                    zzmv.zzR(this.zzc[i8], (List) unsafe.getObject(obj, j), zzocVar, true);
                    i2 = 0;
                    break;
                case 49:
                    zzmv.zzK(this.zzc[i8], (List) unsafe.getObject(obj, j), zzocVar, zzB(i8));
                    i2 = 0;
                    break;
                case 50:
                    zzN(zzocVar, i11, unsafe.getObject(obj, j), i8);
                    i2 = 0;
                    break;
                case 51:
                    if (zzT(obj, i11, i8)) {
                        zzocVar.zzf(i11, zzm(obj, j));
                    }
                    i2 = 0;
                    break;
                case 52:
                    if (zzT(obj, i11, i8)) {
                        zzocVar.zzo(i11, zzn(obj, j));
                    }
                    i2 = 0;
                    break;
                case 53:
                    if (zzT(obj, i11, i8)) {
                        zzocVar.zzt(i11, zzz(obj, j));
                    }
                    i2 = 0;
                    break;
                case 54:
                    if (zzT(obj, i11, i8)) {
                        zzocVar.zzJ(i11, zzz(obj, j));
                    }
                    i2 = 0;
                    break;
                case 55:
                    if (zzT(obj, i11, i8)) {
                        zzocVar.zzr(i11, zzp(obj, j));
                    }
                    i2 = 0;
                    break;
                case 56:
                    if (zzT(obj, i11, i8)) {
                        zzocVar.zzm(i11, zzz(obj, j));
                    }
                    i2 = 0;
                    break;
                case 57:
                    if (zzT(obj, i11, i8)) {
                        zzocVar.zzk(i11, zzp(obj, j));
                    }
                    i2 = 0;
                    break;
                case 58:
                    if (zzT(obj, i11, i8)) {
                        zzocVar.zzb(i11, zzU(obj, j));
                    }
                    i2 = 0;
                    break;
                case 59:
                    if (zzT(obj, i11, i8)) {
                        zzV(i11, unsafe.getObject(obj, j), zzocVar);
                    }
                    i2 = 0;
                    break;
                case 60:
                    if (zzT(obj, i11, i8)) {
                        zzocVar.zzv(i11, unsafe.getObject(obj, j), zzB(i8));
                    }
                    i2 = 0;
                    break;
                case 61:
                    if (zzT(obj, i11, i8)) {
                        zzocVar.zzd(i11, (zzka) unsafe.getObject(obj, j));
                    }
                    i2 = 0;
                    break;
                case 62:
                    if (zzT(obj, i11, i8)) {
                        zzocVar.zzH(i11, zzp(obj, j));
                    }
                    i2 = 0;
                    break;
                case 63:
                    if (zzT(obj, i11, i8)) {
                        zzocVar.zzi(i11, zzp(obj, j));
                    }
                    i2 = 0;
                    break;
                case 64:
                    if (zzT(obj, i11, i8)) {
                        zzocVar.zzw(i11, zzp(obj, j));
                    }
                    i2 = 0;
                    break;
                case 65:
                    if (zzT(obj, i11, i8)) {
                        zzocVar.zzy(i11, zzz(obj, j));
                    }
                    i2 = 0;
                    break;
                case 66:
                    if (zzT(obj, i11, i8)) {
                        zzocVar.zzA(i11, zzp(obj, j));
                    }
                    i2 = 0;
                    break;
                case 67:
                    if (zzT(obj, i11, i8)) {
                        zzocVar.zzC(i11, zzz(obj, j));
                    }
                    i2 = 0;
                    break;
                case 68:
                    if (zzT(obj, i11, i8)) {
                        zzocVar.zzq(i11, unsafe.getObject(obj, j), zzB(i8));
                    }
                    i2 = 0;
                    break;
                default:
                    i2 = 0;
                    break;
            }
            i8 += 3;
            i4 = i2;
            i5 = 1048575;
        }
        zznk zznkVar2 = this.zzn;
        zznkVar2.zzi(zznkVar2.zzd(obj), zzocVar);
    }
}
