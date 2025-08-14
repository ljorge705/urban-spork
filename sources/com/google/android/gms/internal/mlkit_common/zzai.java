package com.google.android.gms.internal.mlkit_common;

import java.util.Arrays;

/* compiled from: com.google.mlkit:common@@18.5.0 */
/* loaded from: classes3.dex */
class zzai extends zzaj {
    Object[] zza = new Object[4];
    int zzb = 0;
    boolean zzc;

    zzai(int i) {
    }

    private final void zzc(int i) {
        Object[] objArr = this.zza;
        int length = objArr.length;
        if (length < i) {
            this.zza = Arrays.copyOf(objArr, zzb(length, i));
            this.zzc = false;
        } else if (this.zzc) {
            this.zza = (Object[]) objArr.clone();
            this.zzc = false;
        }
    }

    public final zzai zza(Object obj) {
        obj.getClass();
        zzc(this.zzb + 1);
        Object[] objArr = this.zza;
        int i = this.zzb;
        this.zzb = i + 1;
        objArr[i] = obj;
        return this;
    }
}
