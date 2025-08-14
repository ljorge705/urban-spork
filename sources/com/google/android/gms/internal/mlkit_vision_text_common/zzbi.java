package com.google.android.gms.internal.mlkit_vision_text_common;

import java.io.Serializable;

/* compiled from: com.google.android.gms:play-services-mlkit-text-recognition-common@@17.0.0 */
/* loaded from: classes3.dex */
final class zzbi extends zzap implements Serializable {
    final Object zza;
    final Object zzb;

    zzbi(Object obj, Object obj2) {
        this.zza = obj;
        this.zzb = obj2;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzap, java.util.Map.Entry
    public final Object getKey() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzap, java.util.Map.Entry
    public final Object getValue() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzap, java.util.Map.Entry
    public final Object setValue(Object obj) {
        throw new UnsupportedOperationException();
    }
}
