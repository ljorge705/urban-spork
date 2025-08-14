package com.google.android.gms.internal.maps;

/* compiled from: com.google.android.gms:play-services-maps@@18.2.0 */
/* loaded from: classes3.dex */
final class zzbc extends zzaz {
    static final zzaz zza = new zzbc(new Object[0], 0);
    final transient Object[] zzb;
    private final transient int zzc;

    zzbc(Object[] objArr, int i) {
        this.zzb = objArr;
        this.zzc = i;
    }

    @Override // java.util.List
    public final Object get(int i) {
        zzas.zza(i, this.zzc, "index");
        Object obj = this.zzb[i];
        obj.getClass();
        return obj;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.maps.zzaz, com.google.android.gms.internal.maps.zzaw
    final int zza(Object[] objArr, int i) {
        System.arraycopy(this.zzb, 0, objArr, 0, this.zzc);
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.maps.zzaw
    final int zzb() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.maps.zzaw
    final int zzc() {
        return 0;
    }

    @Override // com.google.android.gms.internal.maps.zzaw
    final Object[] zze() {
        return this.zzb;
    }
}
