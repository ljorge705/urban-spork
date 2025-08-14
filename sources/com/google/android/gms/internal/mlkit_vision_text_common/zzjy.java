package com.google.android.gms.internal.mlkit_vision_text_common;

/* compiled from: com.google.android.gms:play-services-mlkit-text-recognition-common@@17.0.0 */
/* loaded from: classes3.dex */
public enum zzjy implements zzcx {
    TYPE_UNKNOWN(0),
    TYPE_THIN(1),
    TYPE_THICK(2),
    TYPE_GMV(3);

    private final int zzf;

    zzjy(int i) {
        this.zzf = i;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzcx
    public final int zza() {
        return this.zzf;
    }
}
