package com.google.android.gms.internal.mlkit_vision_face;

/* compiled from: com.google.android.gms:play-services-mlkit-face-detection@@17.1.0 */
/* loaded from: classes3.dex */
final class zzcc extends zzbn {
    static final zzbn zza = new zzcc(new Object[0], 0);
    final transient Object[] zzb;
    private final transient int zzc;

    zzcc(Object[] objArr, int i) {
        this.zzb = objArr;
        this.zzc = i;
    }

    @Override // java.util.List
    public final Object get(int i) {
        zzab.zza(i, this.zzc, "index");
        Object obj = this.zzb[i];
        obj.getClass();
        return obj;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face.zzbn, com.google.android.gms.internal.mlkit_vision_face.zzbi
    final int zza(Object[] objArr, int i) {
        System.arraycopy(this.zzb, 0, objArr, 0, this.zzc);
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face.zzbi
    final int zzb() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face.zzbi
    final int zzc() {
        return 0;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face.zzbi
    final Object[] zze() {
        return this.zzb;
    }
}
