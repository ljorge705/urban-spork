package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.io.IOException;
import java.util.Arrays;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes3.dex */
public final class zzgq {
    private static final zzgq zza = new zzgq(0, new int[0], new Object[0], false);
    private int zzb;
    private int[] zzc;
    private Object[] zzd;
    private int zze;
    private boolean zzf;

    private zzgq() {
        this(0, new int[8], new Object[8], true);
    }

    private zzgq(int i, int[] iArr, Object[] objArr, boolean z) {
        this.zze = -1;
        this.zzb = i;
        this.zzc = iArr;
        this.zzd = objArr;
        this.zzf = z;
    }

    public static zzgq zzc() {
        return zza;
    }

    static zzgq zzd(zzgq zzgqVar, zzgq zzgqVar2) {
        int i = zzgqVar.zzb + zzgqVar2.zzb;
        int[] iArrCopyOf = Arrays.copyOf(zzgqVar.zzc, i);
        System.arraycopy(zzgqVar2.zzc, 0, iArrCopyOf, zzgqVar.zzb, zzgqVar2.zzb);
        Object[] objArrCopyOf = Arrays.copyOf(zzgqVar.zzd, i);
        System.arraycopy(zzgqVar2.zzd, 0, objArrCopyOf, zzgqVar.zzb, zzgqVar2.zzb);
        return new zzgq(i, iArrCopyOf, objArrCopyOf, true);
    }

    static zzgq zze() {
        return new zzgq(0, new int[8], new Object[8], true);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof zzgq)) {
            return false;
        }
        zzgq zzgqVar = (zzgq) obj;
        int i = this.zzb;
        if (i == zzgqVar.zzb) {
            int[] iArr = this.zzc;
            int[] iArr2 = zzgqVar.zzc;
            int i2 = 0;
            while (true) {
                if (i2 >= i) {
                    Object[] objArr = this.zzd;
                    Object[] objArr2 = zzgqVar.zzd;
                    int i3 = this.zzb;
                    for (int i4 = 0; i4 < i3; i4++) {
                        if (objArr[i4].equals(objArr2[i4])) {
                        }
                    }
                    return true;
                }
                if (iArr[i2] != iArr2[i2]) {
                    break;
                }
                i2++;
            }
        }
        return false;
    }

    public final int hashCode() {
        int i = this.zzb;
        int i2 = (i + 527) * 31;
        int[] iArr = this.zzc;
        int iHashCode = 17;
        int i3 = 17;
        for (int i4 = 0; i4 < i; i4++) {
            i3 = (i3 * 31) + iArr[i4];
        }
        int i5 = (i2 + i3) * 31;
        Object[] objArr = this.zzd;
        int i6 = this.zzb;
        for (int i7 = 0; i7 < i6; i7++) {
            iHashCode = (iHashCode * 31) + objArr[i7].hashCode();
        }
        return i5 + iHashCode;
    }

    public final int zza() {
        int iZzD;
        int iZzE;
        int iZzD2;
        int i = this.zze;
        if (i != -1) {
            return i;
        }
        int iZzD3 = 0;
        for (int i2 = 0; i2 < this.zzb; i2++) {
            int i3 = this.zzc[i2];
            int i4 = i3 >>> 3;
            int i5 = i3 & 7;
            if (i5 != 0) {
                if (i5 == 1) {
                    ((Long) this.zzd[i2]).longValue();
                    iZzD2 = zzdi.zzD(i4 << 3) + 8;
                } else if (i5 == 2) {
                    zzdb zzdbVar = (zzdb) this.zzd[i2];
                    int iZzD4 = zzdi.zzD(i4 << 3);
                    int iZzd = zzdbVar.zzd();
                    iZzD3 += iZzD4 + zzdi.zzD(iZzd) + iZzd;
                } else if (i5 == 3) {
                    int iZzC = zzdi.zzC(i4);
                    iZzD = iZzC + iZzC;
                    iZzE = ((zzgq) this.zzd[i2]).zza();
                } else {
                    if (i5 != 5) {
                        throw new IllegalStateException(zzen.zza());
                    }
                    ((Integer) this.zzd[i2]).intValue();
                    iZzD2 = zzdi.zzD(i4 << 3) + 4;
                }
                iZzD3 += iZzD2;
            } else {
                long jLongValue = ((Long) this.zzd[i2]).longValue();
                iZzD = zzdi.zzD(i4 << 3);
                iZzE = zzdi.zzE(jLongValue);
            }
            iZzD2 = iZzD + iZzE;
            iZzD3 += iZzD2;
        }
        this.zze = iZzD3;
        return iZzD3;
    }

    public final int zzb() {
        int i = this.zze;
        if (i != -1) {
            return i;
        }
        int iZzD = 0;
        for (int i2 = 0; i2 < this.zzb; i2++) {
            int i3 = this.zzc[i2];
            zzdb zzdbVar = (zzdb) this.zzd[i2];
            int iZzD2 = zzdi.zzD(8);
            int iZzd = zzdbVar.zzd();
            iZzD += iZzD2 + iZzD2 + zzdi.zzD(16) + zzdi.zzD(i3 >>> 3) + zzdi.zzD(24) + zzdi.zzD(iZzd) + iZzd;
        }
        this.zze = iZzD;
        return iZzD;
    }

    public final void zzf() {
        this.zzf = false;
    }

    final void zzg(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < this.zzb; i2++) {
            zzfn.zzb(sb, i, String.valueOf(this.zzc[i2] >>> 3), this.zzd[i2]);
        }
    }

    final void zzh(int i, Object obj) {
        if (!this.zzf) {
            throw new UnsupportedOperationException();
        }
        int i2 = this.zzb;
        int[] iArr = this.zzc;
        if (i2 == iArr.length) {
            int i3 = i2 + (i2 < 4 ? 8 : i2 >> 1);
            this.zzc = Arrays.copyOf(iArr, i3);
            this.zzd = Arrays.copyOf(this.zzd, i3);
        }
        int[] iArr2 = this.zzc;
        int i4 = this.zzb;
        iArr2[i4] = i;
        this.zzd[i4] = obj;
        this.zzb = i4 + 1;
    }

    final void zzi(zzdj zzdjVar) throws IOException {
        for (int i = 0; i < this.zzb; i++) {
            zzdjVar.zzw(this.zzc[i] >>> 3, this.zzd[i]);
        }
    }

    public final void zzj(zzdj zzdjVar) throws IOException {
        if (this.zzb != 0) {
            for (int i = 0; i < this.zzb; i++) {
                int i2 = this.zzc[i];
                Object obj = this.zzd[i];
                int i3 = i2 >>> 3;
                int i4 = i2 & 7;
                if (i4 == 0) {
                    zzdjVar.zzt(i3, ((Long) obj).longValue());
                } else if (i4 == 1) {
                    zzdjVar.zzm(i3, ((Long) obj).longValue());
                } else if (i4 == 2) {
                    zzdjVar.zzd(i3, (zzdb) obj);
                } else if (i4 == 3) {
                    zzdjVar.zzF(i3);
                    ((zzgq) obj).zzj(zzdjVar);
                    zzdjVar.zzh(i3);
                } else {
                    if (i4 != 5) {
                        throw new RuntimeException(zzen.zza());
                    }
                    zzdjVar.zzk(i3, ((Integer) obj).intValue());
                }
            }
        }
    }
}
