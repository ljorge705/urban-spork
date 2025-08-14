package com.google.android.gms.internal.mlkit_vision_text_common;

/* compiled from: com.google.android.gms:play-services-mlkit-text-recognition-common@@17.0.0 */
/* loaded from: classes3.dex */
public enum zzll implements zzcx {
    TYPE_UNKNOWN(0),
    LATIN(1),
    LATIN_AND_CHINESE(2),
    LATIN_AND_DEVANAGARI(3),
    LATIN_AND_JAPANESE(4),
    LATIN_AND_KOREAN(5);

    private final int zzh;

    zzll(int i) {
        this.zzh = i;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzcx
    public final int zza() {
        return this.zzh;
    }
}
