package com.google.android.gms.internal.mlkit_vision_barcode;

import java.io.Serializable;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.0.0 */
/* loaded from: classes3.dex */
final class zzby extends zzbf implements Serializable {
    final Object zza;
    final Object zzb;

    zzby(Object obj, Object obj2) {
        this.zza = obj;
        this.zzb = obj2;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzbf, java.util.Map.Entry
    public final Object getKey() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzbf, java.util.Map.Entry
    public final Object getValue() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzbf, java.util.Map.Entry
    public final Object setValue(Object obj) {
        throw new UnsupportedOperationException();
    }
}
