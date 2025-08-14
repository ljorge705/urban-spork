package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes3.dex */
final class zzey extends zzez {
    private zzey() {
        super(null);
    }

    /* synthetic */ zzey(zzew zzewVar) {
        super(null);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzez
    final void zza(Object obj, long j) {
        ((zzek) zzgz.zzf(obj, j)).zzb();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzez
    final void zzb(Object obj, Object obj2, long j) {
        zzek zzekVarZzd = (zzek) zzgz.zzf(obj, j);
        zzek zzekVar = (zzek) zzgz.zzf(obj2, j);
        int size = zzekVarZzd.size();
        int size2 = zzekVar.size();
        if (size > 0 && size2 > 0) {
            if (!zzekVarZzd.zzc()) {
                zzekVarZzd = zzekVarZzd.zzd(size2 + size);
            }
            zzekVarZzd.addAll(zzekVar);
        }
        if (size > 0) {
            zzekVar = zzekVarZzd;
        }
        zzgz.zzs(obj, j, zzekVar);
    }
}
