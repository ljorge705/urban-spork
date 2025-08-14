package com.google.android.gms.internal.mlkit_vision_text_common;

/* compiled from: com.google.android.gms:play-services-mlkit-text-recognition-common@@17.0.0 */
/* loaded from: classes3.dex */
final class zzcg extends zzbm {
    static final zzbm zza = new zzcg(new Object[0], 0);
    final transient Object[] zzb;
    private final transient int zzc;

    zzcg(Object[] objArr, int i) {
        this.zzb = objArr;
        this.zzc = i;
    }

    @Override // java.util.List
    public final Object get(int i) {
        zzaa.zza(i, this.zzc, "index");
        Object obj = this.zzb[i];
        obj.getClass();
        return obj;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzbm, com.google.android.gms.internal.mlkit_vision_text_common.zzbh
    final int zza(Object[] objArr, int i) {
        System.arraycopy(this.zzb, 0, objArr, 0, this.zzc);
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzbh
    final int zzb() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzbh
    final int zzc() {
        return 0;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzbh
    final Object[] zze() {
        return this.zzb;
    }
}
