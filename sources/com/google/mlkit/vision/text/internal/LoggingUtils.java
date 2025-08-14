package com.google.mlkit.vision.text.internal;

import com.google.android.gms.internal.mlkit_vision_text_common.zzll;

/* compiled from: com.google.android.gms:play-services-mlkit-text-recognition-common@@17.0.0 */
/* loaded from: classes2.dex */
public final class LoggingUtils {
    private LoggingUtils() {
    }

    static zzll zza(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? zzll.TYPE_UNKNOWN : zzll.LATIN_AND_KOREAN : zzll.LATIN_AND_JAPANESE : zzll.LATIN_AND_DEVANAGARI : zzll.LATIN_AND_CHINESE : zzll.LATIN;
    }
}
