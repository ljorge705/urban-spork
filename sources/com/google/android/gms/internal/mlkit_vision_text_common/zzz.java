package com.google.android.gms.internal.mlkit_vision_text_common;

import java.util.logging.Logger;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-mlkit-text-recognition-common@@17.0.0 */
/* loaded from: classes3.dex */
final class zzz {
    private static final Logger zza = Logger.getLogger(zzz.class.getName());
    private static final zzy zzb = new zzy(null);

    private zzz() {
    }

    static boolean zza(@CheckForNull String str) {
        return str == null || str.isEmpty();
    }
}
