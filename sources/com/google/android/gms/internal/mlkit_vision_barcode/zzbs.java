package com.google.android.gms.internal.mlkit_vision_barcode;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.0.0 */
/* loaded from: classes3.dex */
final class zzbs extends AbstractMap implements Serializable {
    private static final Object zzd = new Object();

    @CheckForNull
    transient int[] zza;

    @CheckForNull
    transient Object[] zzb;

    @CheckForNull
    transient Object[] zzc;

    @CheckForNull
    private transient Object zze;
    private transient int zzf;
    private transient int zzg;

    @CheckForNull
    private transient Set zzh;

    @CheckForNull
    private transient Set zzi;

    @CheckForNull
    private transient Collection zzj;

    zzbs() {
        zzo(3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object[] zzA() {
        Object[] objArr = this.zzb;
        objArr.getClass();
        return objArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object[] zzB() {
        Object[] objArr = this.zzc;
        objArr.getClass();
        return objArr;
    }

    static /* synthetic */ int zzb(zzbs zzbsVar) {
        int i = zzbsVar.zzg;
        zzbsVar.zzg = i - 1;
        return i;
    }

    static /* synthetic */ Object zzg(zzbs zzbsVar, int i) {
        return zzbsVar.zzA()[i];
    }

    static /* synthetic */ Object zzj(zzbs zzbsVar, int i) {
        return zzbsVar.zzB()[i];
    }

    static /* synthetic */ Object zzk(zzbs zzbsVar) {
        Object obj = zzbsVar.zze;
        obj.getClass();
        return obj;
    }

    static /* synthetic */ void zzm(zzbs zzbsVar, int i, Object obj) {
        zzbsVar.zzB()[i] = obj;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int zzu() {
        return (1 << (this.zzf & 31)) - 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int zzv(@CheckForNull Object obj) {
        if (zzq()) {
            return -1;
        }
        int iZza = zzbu.zza(obj);
        int iZzu = zzu();
        Object obj2 = this.zze;
        obj2.getClass();
        int iZzc = zzbt.zzc(obj2, iZza & iZzu);
        if (iZzc != 0) {
            int i = ~iZzu;
            int i2 = iZza & i;
            do {
                int i3 = iZzc - 1;
                int i4 = zzz()[i3];
                if ((i4 & i) == i2 && zzam.zza(obj, zzA()[i3])) {
                    return i3;
                }
                iZzc = i4 & iZzu;
            } while (iZzc != 0);
        }
        return -1;
    }

    private final int zzw(int i, int i2, int i3, int i4) {
        Object objZzd = zzbt.zzd(i2);
        int i5 = i2 - 1;
        if (i4 != 0) {
            zzbt.zze(objZzd, i3 & i5, i4 + 1);
        }
        Object obj = this.zze;
        obj.getClass();
        int[] iArrZzz = zzz();
        for (int i6 = 0; i6 <= i; i6++) {
            int iZzc = zzbt.zzc(obj, i6);
            while (iZzc != 0) {
                int i7 = iZzc - 1;
                int i8 = iArrZzz[i7];
                int i9 = ((~i) & i8) | i6;
                int i10 = i9 & i5;
                int iZzc2 = zzbt.zzc(objZzd, i10);
                zzbt.zze(objZzd, i10, iZzc);
                iArrZzz[i7] = ((~i5) & i9) | (iZzc2 & i5);
                iZzc = i8 & i;
            }
        }
        this.zze = objZzd;
        zzy(i5);
        return i5;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object zzx(@CheckForNull Object obj) {
        if (zzq()) {
            return zzd;
        }
        int iZzu = zzu();
        Object obj2 = this.zze;
        obj2.getClass();
        int iZzb = zzbt.zzb(obj, null, iZzu, obj2, zzz(), zzA(), null);
        if (iZzb == -1) {
            return zzd;
        }
        Object obj3 = zzB()[iZzb];
        zzp(iZzb, iZzu);
        this.zzg--;
        zzn();
        return obj3;
    }

    private final void zzy(int i) {
        this.zzf = ((32 - Integer.numberOfLeadingZeros(i)) & 31) | (this.zzf & (-32));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int[] zzz() {
        int[] iArr = this.zza;
        iArr.getClass();
        return iArr;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void clear() {
        if (zzq()) {
            return;
        }
        zzn();
        Map mapZzl = zzl();
        if (mapZzl != null) {
            this.zzf = zzdc.zza(size(), 3, LockFreeTaskQueueCore.MAX_CAPACITY_MASK);
            mapZzl.clear();
            this.zze = null;
            this.zzg = 0;
            return;
        }
        Arrays.fill(zzA(), 0, this.zzg, (Object) null);
        Arrays.fill(zzB(), 0, this.zzg, (Object) null);
        Object obj = this.zze;
        obj.getClass();
        if (obj instanceof byte[]) {
            Arrays.fill((byte[]) obj, (byte) 0);
        } else if (obj instanceof short[]) {
            Arrays.fill((short[]) obj, (short) 0);
        } else {
            Arrays.fill((int[]) obj, 0);
        }
        Arrays.fill(zzz(), 0, this.zzg, 0);
        this.zzg = 0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsKey(@CheckForNull Object obj) {
        Map mapZzl = zzl();
        return mapZzl != null ? mapZzl.containsKey(obj) : zzv(obj) != -1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsValue(@CheckForNull Object obj) {
        Map mapZzl = zzl();
        if (mapZzl != null) {
            return mapZzl.containsValue(obj);
        }
        for (int i = 0; i < this.zzg; i++) {
            if (zzam.zza(obj, zzB()[i])) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set entrySet() {
        Set set = this.zzi;
        if (set != null) {
            return set;
        }
        zzbn zzbnVar = new zzbn(this);
        this.zzi = zzbnVar;
        return zzbnVar;
    }

    @Override // java.util.AbstractMap, java.util.Map
    @CheckForNull
    public final Object get(@CheckForNull Object obj) {
        Map mapZzl = zzl();
        if (mapZzl != null) {
            return mapZzl.get(obj);
        }
        int iZzv = zzv(obj);
        if (iZzv == -1) {
            return null;
        }
        return zzB()[iZzv];
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean isEmpty() {
        return size() == 0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set keySet() {
        Set set = this.zzh;
        if (set != null) {
            return set;
        }
        zzbp zzbpVar = new zzbp(this);
        this.zzh = zzbpVar;
        return zzbpVar;
    }

    @Override // java.util.AbstractMap, java.util.Map
    @CheckForNull
    public final Object put(Object obj, Object obj2) {
        int iMin;
        if (zzq()) {
            zzaq.zzd(zzq(), "Arrays already allocated");
            int i = this.zzf;
            int iMax = Math.max(i + 1, 2);
            int iHighestOneBit = Integer.highestOneBit(iMax);
            if (iMax > iHighestOneBit && (iHighestOneBit = iHighestOneBit + iHighestOneBit) <= 0) {
                iHighestOneBit = 1073741824;
            }
            int iMax2 = Math.max(4, iHighestOneBit);
            this.zze = zzbt.zzd(iMax2);
            zzy(iMax2 - 1);
            this.zza = new int[i];
            this.zzb = new Object[i];
            this.zzc = new Object[i];
        }
        Map mapZzl = zzl();
        if (mapZzl != null) {
            return mapZzl.put(obj, obj2);
        }
        int[] iArrZzz = zzz();
        Object[] objArrZzA = zzA();
        Object[] objArrZzB = zzB();
        int i2 = this.zzg;
        int i3 = i2 + 1;
        int iZza = zzbu.zza(obj);
        int iZzu = zzu();
        int i4 = iZza & iZzu;
        Object obj3 = this.zze;
        obj3.getClass();
        int iZzc = zzbt.zzc(obj3, i4);
        if (iZzc != 0) {
            int i5 = ~iZzu;
            int i6 = iZza & i5;
            int i7 = 0;
            while (true) {
                int i8 = iZzc - 1;
                int i9 = iArrZzz[i8];
                int i10 = i9 & i5;
                if (i10 == i6 && zzam.zza(obj, objArrZzA[i8])) {
                    Object obj4 = objArrZzB[i8];
                    objArrZzB[i8] = obj2;
                    return obj4;
                }
                int i11 = i9 & iZzu;
                i7++;
                if (i11 != 0) {
                    iZzc = i11;
                } else {
                    if (i7 >= 9) {
                        LinkedHashMap linkedHashMap = new LinkedHashMap(zzu() + 1, 1.0f);
                        int iZze = zze();
                        while (iZze >= 0) {
                            linkedHashMap.put(zzA()[iZze], zzB()[iZze]);
                            iZze = zzf(iZze);
                        }
                        this.zze = linkedHashMap;
                        this.zza = null;
                        this.zzb = null;
                        this.zzc = null;
                        zzn();
                        return linkedHashMap.put(obj, obj2);
                    }
                    if (i3 > iZzu) {
                        iZzu = zzw(iZzu, zzbt.zza(iZzu), iZza, i2);
                    } else {
                        iArrZzz[i8] = (i3 & iZzu) | i10;
                    }
                }
            }
        } else if (i3 > iZzu) {
            iZzu = zzw(iZzu, zzbt.zza(iZzu), iZza, i2);
        } else {
            Object obj5 = this.zze;
            obj5.getClass();
            zzbt.zze(obj5, i4, i3);
        }
        int length = zzz().length;
        if (i3 > length && (iMin = Math.min(LockFreeTaskQueueCore.MAX_CAPACITY_MASK, (Math.max(1, length >>> 1) + length) | 1)) != length) {
            this.zza = Arrays.copyOf(zzz(), iMin);
            this.zzb = Arrays.copyOf(zzA(), iMin);
            this.zzc = Arrays.copyOf(zzB(), iMin);
        }
        zzz()[i2] = (~iZzu) & iZza;
        zzA()[i2] = obj;
        zzB()[i2] = obj2;
        this.zzg = i3;
        zzn();
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    @CheckForNull
    public final Object remove(@CheckForNull Object obj) {
        Map mapZzl = zzl();
        if (mapZzl != null) {
            return mapZzl.remove(obj);
        }
        Object objZzx = zzx(obj);
        if (objZzx == zzd) {
            return null;
        }
        return objZzx;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int size() {
        Map mapZzl = zzl();
        return mapZzl != null ? mapZzl.size() : this.zzg;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Collection values() {
        Collection collection = this.zzj;
        if (collection != null) {
            return collection;
        }
        zzbr zzbrVar = new zzbr(this);
        this.zzj = zzbrVar;
        return zzbrVar;
    }

    final int zze() {
        return isEmpty() ? -1 : 0;
    }

    final int zzf(int i) {
        int i2 = i + 1;
        if (i2 < this.zzg) {
            return i2;
        }
        return -1;
    }

    @CheckForNull
    final Map zzl() {
        Object obj = this.zze;
        if (obj instanceof Map) {
            return (Map) obj;
        }
        return null;
    }

    final void zzn() {
        this.zzf += 32;
    }

    final void zzo(int i) {
        this.zzf = zzdc.zza(12, 1, LockFreeTaskQueueCore.MAX_CAPACITY_MASK);
    }

    final void zzp(int i, int i2) {
        Object obj = this.zze;
        obj.getClass();
        int[] iArrZzz = zzz();
        Object[] objArrZzA = zzA();
        Object[] objArrZzB = zzB();
        int size = size();
        int i3 = size - 1;
        if (i >= i3) {
            objArrZzA[i] = null;
            objArrZzB[i] = null;
            iArrZzz[i] = 0;
            return;
        }
        Object obj2 = objArrZzA[i3];
        objArrZzA[i] = obj2;
        objArrZzB[i] = objArrZzB[i3];
        objArrZzA[i3] = null;
        objArrZzB[i3] = null;
        iArrZzz[i] = iArrZzz[i3];
        iArrZzz[i3] = 0;
        int iZza = zzbu.zza(obj2) & i2;
        int iZzc = zzbt.zzc(obj, iZza);
        if (iZzc == size) {
            zzbt.zze(obj, iZza, i + 1);
            return;
        }
        while (true) {
            int i4 = iZzc - 1;
            int i5 = iArrZzz[i4];
            int i6 = i5 & i2;
            if (i6 == size) {
                iArrZzz[i4] = ((i + 1) & i2) | (i5 & (~i2));
                return;
            }
            iZzc = i6;
        }
    }

    final boolean zzq() {
        return this.zze == null;
    }

    zzbs(int i) {
        zzo(12);
    }
}
