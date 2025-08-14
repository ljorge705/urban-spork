package com.google.android.gms.internal.measurement;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-measurement-base@@21.3.0 */
/* loaded from: classes3.dex */
public abstract class zzjb extends zziw implements Set {

    @CheckForNull
    private transient zzja zza;

    zzjb() {
    }

    static int zzf(int i) {
        int iMax = Math.max(i, 2);
        if (iMax >= 751619276) {
            if (iMax < 1073741824) {
                return 1073741824;
            }
            throw new IllegalArgumentException("collection too large");
        }
        int iHighestOneBit = Integer.highestOneBit(iMax - 1);
        do {
            iHighestOneBit += iHighestOneBit;
        } while (iHighestOneBit * 0.7d < iMax);
        return iHighestOneBit;
    }

    @SafeVarargs
    public static zzjb zzi(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object... objArr) {
        Object[] objArr2 = new Object[15];
        objArr2[0] = "_in";
        objArr2[1] = "_xa";
        objArr2[2] = "_xu";
        objArr2[3] = "_aq";
        objArr2[4] = "_aa";
        objArr2[5] = "_ai";
        System.arraycopy(objArr, 0, objArr2, 6, 9);
        return zzk(15, objArr2);
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean equals(@CheckForNull Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof zzjb) && zzj() && ((zzjb) obj).zzj() && hashCode() != obj.hashCode()) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj instanceof Set) {
            Set set = (Set) obj;
            try {
                if (size() == set.size()) {
                    if (containsAll(set)) {
                        return true;
                    }
                }
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    @Override // java.util.Collection, java.util.Set
    public int hashCode() {
        Iterator it = iterator();
        int iHashCode = 0;
        while (it.hasNext()) {
            Object next = it.next();
            iHashCode += next != null ? next.hashCode() : 0;
        }
        return iHashCode;
    }

    @Override // com.google.android.gms.internal.measurement.zziw, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    /* renamed from: zzd */
    public abstract zzjh iterator();

    public final zzja zzg() {
        zzja zzjaVar = this.zza;
        if (zzjaVar != null) {
            return zzjaVar;
        }
        zzja zzjaVarZzh = zzh();
        this.zza = zzjaVarZzh;
        return zzjaVarZzh;
    }

    zzja zzh() {
        Object[] array = toArray();
        int i = zzja.zzd;
        return zzja.zzg(array, array.length);
    }

    boolean zzj() {
        return false;
    }

    private static zzjb zzk(int i, Object... objArr) {
        if (i == 0) {
            return zzjf.zza;
        }
        if (i == 1) {
            Object obj = objArr[0];
            obj.getClass();
            return new zzjg(obj);
        }
        int iZzf = zzf(i);
        Object[] objArr2 = new Object[iZzf];
        int i2 = iZzf - 1;
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < i; i5++) {
            Object obj2 = objArr[i5];
            zzjd.zza(obj2, i5);
            int iHashCode = obj2.hashCode();
            int iZza = zzit.zza(iHashCode);
            while (true) {
                int i6 = iZza & i2;
                Object obj3 = objArr2[i6];
                if (obj3 == null) {
                    objArr[i4] = obj2;
                    objArr2[i6] = obj2;
                    i3 += iHashCode;
                    i4++;
                    break;
                }
                if (!obj3.equals(obj2)) {
                    iZza++;
                }
            }
        }
        Arrays.fill(objArr, i4, i, (Object) null);
        if (i4 == 1) {
            Object obj4 = objArr[0];
            obj4.getClass();
            return new zzjg(obj4);
        }
        if (zzf(i4) < iZzf / 2) {
            return zzk(i4, objArr);
        }
        if (i4 < 10) {
            objArr = Arrays.copyOf(objArr, i4);
        }
        return new zzjf(objArr, i3, objArr2, i2, i4);
    }
}
