package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes3.dex */
final class zzgd {
    private static final Class zza;
    private static final zzgp zzb;
    private static final zzgp zzc;
    private static final zzgp zzd;

    static {
        Class<?> cls;
        try {
            cls = Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable unused) {
            cls = null;
        }
        zza = cls;
        zzb = zzab(false);
        zzc = zzab(true);
        zzd = new zzgr();
    }

    public static zzgp zzA() {
        return zzc;
    }

    public static zzgp zzB() {
        return zzd;
    }

    static Object zzC(int i, List list, zzeg zzegVar, Object obj, zzgp zzgpVar) {
        if (zzegVar == null) {
            return obj;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                int iIntValue = ((Integer) list.get(i3)).intValue();
                if (zzegVar.zza(iIntValue)) {
                    if (i3 != i2) {
                        list.set(i2, Integer.valueOf(iIntValue));
                    }
                    i2++;
                } else {
                    obj = zzD(i, iIntValue, obj, zzgpVar);
                }
            }
            if (i2 != size) {
                list.subList(i2, size).clear();
                return obj;
            }
        } else {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                int iIntValue2 = ((Integer) it.next()).intValue();
                if (!zzegVar.zza(iIntValue2)) {
                    obj = zzD(i, iIntValue2, obj, zzgpVar);
                    it.remove();
                }
            }
        }
        return obj;
    }

    static Object zzD(int i, int i2, Object obj, zzgp zzgpVar) {
        if (obj == null) {
            obj = zzgpVar.zze();
        }
        zzgpVar.zzf(obj, i, i2);
        return obj;
    }

    static void zzE(zzdo zzdoVar, Object obj, Object obj2) {
        zzds zzdsVarZzb = zzdoVar.zzb(obj2);
        if (zzdsVarZzb.zza.isEmpty()) {
            return;
        }
        zzdoVar.zzc(obj).zzh(zzdsVarZzb);
    }

    static void zzF(zzgp zzgpVar, Object obj, Object obj2) {
        zzgpVar.zzh(obj, zzgpVar.zzd(zzgpVar.zzc(obj), zzgpVar.zzc(obj2)));
    }

    public static void zzG(Class cls) {
        Class cls2;
        if (!zzec.class.isAssignableFrom(cls) && (cls2 = zza) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    static boolean zzH(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }

    static void zzI(zzfg zzfgVar, Object obj, Object obj2, long j) {
        zzgz.zzs(obj, j, zzfg.zzb(zzgz.zzf(obj, j), zzgz.zzf(obj2, j)));
    }

    public static void zzJ(int i, List list, zzdj zzdjVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzdjVar.zzc(i, list, z);
    }

    public static void zzK(int i, List list, zzdj zzdjVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzdjVar.zze(i, list);
    }

    public static void zzL(int i, List list, zzdj zzdjVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzdjVar.zzg(i, list, z);
    }

    public static void zzM(int i, List list, zzdj zzdjVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzdjVar.zzj(i, list, z);
    }

    public static void zzN(int i, List list, zzdj zzdjVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzdjVar.zzl(i, list, z);
    }

    public static void zzO(int i, List list, zzdj zzdjVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzdjVar.zzn(i, list, z);
    }

    public static void zzP(int i, List list, zzdj zzdjVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzdjVar.zzp(i, list, z);
    }

    public static void zzQ(int i, List list, zzdj zzdjVar, zzgb zzgbVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzdjVar.zzq(i, list.get(i2), zzgbVar);
        }
    }

    public static void zzR(int i, List list, zzdj zzdjVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzdjVar.zzs(i, list, z);
    }

    public static void zzS(int i, List list, zzdj zzdjVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzdjVar.zzu(i, list, z);
    }

    public static void zzT(int i, List list, zzdj zzdjVar, zzgb zzgbVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzdjVar.zzv(i, list.get(i2), zzgbVar);
        }
    }

    public static void zzU(int i, List list, zzdj zzdjVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzdjVar.zzy(i, list, z);
    }

    public static void zzV(int i, List list, zzdj zzdjVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzdjVar.zzA(i, list, z);
    }

    public static void zzW(int i, List list, zzdj zzdjVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzdjVar.zzC(i, list, z);
    }

    public static void zzX(int i, List list, zzdj zzdjVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzdjVar.zzE(i, list, z);
    }

    public static void zzY(int i, List list, zzdj zzdjVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzdjVar.zzH(i, list);
    }

    public static void zzZ(int i, List list, zzdj zzdjVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzdjVar.zzJ(i, list, z);
    }

    static int zza(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzdi.zzD(i << 3) + 1);
    }

    public static void zzaa(int i, List list, zzdj zzdjVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzdjVar.zzL(i, list, z);
    }

    private static zzgp zzab(boolean z) {
        Class<?> cls;
        try {
            cls = Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused) {
            cls = null;
        }
        if (cls == null) {
            return null;
        }
        try {
            return (zzgp) cls.getConstructor(Boolean.TYPE).newInstance(Boolean.valueOf(z));
        } catch (Throwable unused2) {
            return null;
        }
    }

    static int zzb(List list) {
        return list.size();
    }

    static int zzc(int i, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iZzC = size * zzdi.zzC(i);
        for (int i2 = 0; i2 < list.size(); i2++) {
            iZzC += zzdi.zzu((zzdb) list.get(i2));
        }
        return iZzC;
    }

    static int zzd(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zze(list) + (size * zzdi.zzC(i));
    }

    static int zze(List list) {
        int iZzx;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzed) {
            zzed zzedVar = (zzed) list;
            iZzx = 0;
            while (i < size) {
                iZzx += zzdi.zzx(zzedVar.zze(i));
                i++;
            }
        } else {
            iZzx = 0;
            while (i < size) {
                iZzx += zzdi.zzx(((Integer) list.get(i)).intValue());
                i++;
            }
        }
        return iZzx;
    }

    static int zzf(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzdi.zzD(i << 3) + 4);
    }

    static int zzg(List list) {
        return list.size() * 4;
    }

    static int zzh(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzdi.zzD(i << 3) + 8);
    }

    static int zzi(List list) {
        return list.size() * 8;
    }

    static int zzj(int i, List list, zzgb zzgbVar) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iZzv = 0;
        for (int i2 = 0; i2 < size; i2++) {
            iZzv += zzdi.zzv(i, (zzfl) list.get(i2), zzgbVar);
        }
        return iZzv;
    }

    static int zzk(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzl(list) + (size * zzdi.zzC(i));
    }

    static int zzl(List list) {
        int iZzx;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzed) {
            zzed zzedVar = (zzed) list;
            iZzx = 0;
            while (i < size) {
                iZzx += zzdi.zzx(zzedVar.zze(i));
                i++;
            }
        } else {
            iZzx = 0;
            while (i < size) {
                iZzx += zzdi.zzx(((Integer) list.get(i)).intValue());
                i++;
            }
        }
        return iZzx;
    }

    static int zzm(int i, List list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return zzn(list) + (list.size() * zzdi.zzC(i));
    }

    static int zzn(List list) {
        int iZzE;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzfa) {
            zzfa zzfaVar = (zzfa) list;
            iZzE = 0;
            while (i < size) {
                iZzE += zzdi.zzE(zzfaVar.zze(i));
                i++;
            }
        } else {
            iZzE = 0;
            while (i < size) {
                iZzE += zzdi.zzE(((Long) list.get(i)).longValue());
                i++;
            }
        }
        return iZzE;
    }

    static int zzo(int i, Object obj, zzgb zzgbVar) {
        if (!(obj instanceof zzet)) {
            return zzdi.zzD(i << 3) + zzdi.zzA((zzfl) obj, zzgbVar);
        }
        int iZzD = zzdi.zzD(i << 3);
        int iZza = ((zzet) obj).zza();
        return iZzD + zzdi.zzD(iZza) + iZza;
    }

    static int zzp(int i, List list, zzgb zzgbVar) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iZzC = zzdi.zzC(i) * size;
        for (int i2 = 0; i2 < size; i2++) {
            Object obj = list.get(i2);
            iZzC += obj instanceof zzet ? zzdi.zzy((zzet) obj) : zzdi.zzA((zzfl) obj, zzgbVar);
        }
        return iZzC;
    }

    static int zzq(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzr(list) + (size * zzdi.zzC(i));
    }

    static int zzr(List list) {
        int iZzD;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzed) {
            zzed zzedVar = (zzed) list;
            iZzD = 0;
            while (i < size) {
                int iZze = zzedVar.zze(i);
                iZzD += zzdi.zzD((iZze >> 31) ^ (iZze + iZze));
                i++;
            }
        } else {
            iZzD = 0;
            while (i < size) {
                int iIntValue = ((Integer) list.get(i)).intValue();
                iZzD += zzdi.zzD((iIntValue >> 31) ^ (iIntValue + iIntValue));
                i++;
            }
        }
        return iZzD;
    }

    static int zzs(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzt(list) + (size * zzdi.zzC(i));
    }

    static int zzt(List list) {
        int iZzE;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzfa) {
            zzfa zzfaVar = (zzfa) list;
            iZzE = 0;
            while (i < size) {
                long jZze = zzfaVar.zze(i);
                iZzE += zzdi.zzE((jZze >> 63) ^ (jZze + jZze));
                i++;
            }
        } else {
            iZzE = 0;
            while (i < size) {
                long jLongValue = ((Long) list.get(i)).longValue();
                iZzE += zzdi.zzE((jLongValue >> 63) ^ (jLongValue + jLongValue));
                i++;
            }
        }
        return iZzE;
    }

    static int zzu(int i, List list) {
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        int iZzC = zzdi.zzC(i) * size;
        if (list instanceof zzev) {
            zzev zzevVar = (zzev) list;
            while (i2 < size) {
                Object objZzf = zzevVar.zzf(i2);
                iZzC += objZzf instanceof zzdb ? zzdi.zzu((zzdb) objZzf) : zzdi.zzB((String) objZzf);
                i2++;
            }
        } else {
            while (i2 < size) {
                Object obj = list.get(i2);
                iZzC += obj instanceof zzdb ? zzdi.zzu((zzdb) obj) : zzdi.zzB((String) obj);
                i2++;
            }
        }
        return iZzC;
    }

    static int zzv(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzw(list) + (size * zzdi.zzC(i));
    }

    static int zzw(List list) {
        int iZzD;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzed) {
            zzed zzedVar = (zzed) list;
            iZzD = 0;
            while (i < size) {
                iZzD += zzdi.zzD(zzedVar.zze(i));
                i++;
            }
        } else {
            iZzD = 0;
            while (i < size) {
                iZzD += zzdi.zzD(((Integer) list.get(i)).intValue());
                i++;
            }
        }
        return iZzD;
    }

    static int zzx(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzy(list) + (size * zzdi.zzC(i));
    }

    static int zzy(List list) {
        int iZzE;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzfa) {
            zzfa zzfaVar = (zzfa) list;
            iZzE = 0;
            while (i < size) {
                iZzE += zzdi.zzE(zzfaVar.zze(i));
                i++;
            }
        } else {
            iZzE = 0;
            while (i < size) {
                iZzE += zzdi.zzE(((Long) list.get(i)).longValue());
                i++;
            }
        }
        return iZzE;
    }

    public static zzgp zzz() {
        return zzb;
    }
}
