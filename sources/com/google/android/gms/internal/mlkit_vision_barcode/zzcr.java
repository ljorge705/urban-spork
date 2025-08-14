package com.google.android.gms.internal.mlkit_vision_barcode;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.0.0 */
/* loaded from: classes3.dex */
final class zzcr extends zzcc {
    static final zzcc zza = new zzcr(new Object[0], 0);
    final transient Object[] zzb;
    private final transient int zzc;

    zzcr(Object[] objArr, int i) {
        this.zzb = objArr;
        this.zzc = i;
    }

    @Override // java.util.List
    public final Object get(int i) {
        zzaq.zza(i, this.zzc, "index");
        Object obj = this.zzb[i];
        obj.getClass();
        return obj;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzcc, com.google.android.gms.internal.mlkit_vision_barcode.zzbx
    final int zza(Object[] objArr, int i) {
        System.arraycopy(this.zzb, 0, objArr, i, this.zzc);
        return i + this.zzc;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzbx
    final int zzb() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzbx
    final int zzc() {
        return 0;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzbx
    final Object[] zze() {
        return this.zzb;
    }
}
