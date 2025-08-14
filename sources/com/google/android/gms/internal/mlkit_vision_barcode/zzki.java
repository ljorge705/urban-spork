package com.google.android.gms.internal.mlkit_vision_barcode;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.0.0 */
/* loaded from: classes3.dex */
public enum zzki implements zzdh {
    TYPE_UNKNOWN(0),
    TYPE_THIN(1),
    TYPE_THICK(2),
    TYPE_GMV(3);

    private final int zzf;

    zzki(int i) {
        this.zzf = i;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzdh
    public final int zza() {
        return this.zzf;
    }
}
