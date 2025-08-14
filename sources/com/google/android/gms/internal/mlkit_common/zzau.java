package com.google.android.gms.internal.mlkit_common;

/* compiled from: com.google.mlkit:common@@18.5.0 */
/* loaded from: classes3.dex */
final class zzau extends zzao {
    static final zzao zza = new zzau(new Object[0], 0);
    final transient Object[] zzb;
    private final transient int zzc;

    zzau(Object[] objArr, int i) {
        this.zzb = objArr;
        this.zzc = i;
    }

    @Override // java.util.List
    public final Object get(int i) {
        zzac.zza(i, this.zzc, "index");
        Object obj = this.zzb[i];
        obj.getClass();
        return obj;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzao, com.google.android.gms.internal.mlkit_common.zzak
    final int zza(Object[] objArr, int i) {
        System.arraycopy(this.zzb, 0, objArr, 0, this.zzc);
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzak
    final int zzb() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzak
    final int zzc() {
        return 0;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzak
    final Object[] zze() {
        return this.zzb;
    }
}
