package com.google.android.play.core.splitinstall.testing;

import java.util.Collections;
import java.util.Map;

/* compiled from: com.google.android.play:feature-delivery@@2.1.0 */
/* loaded from: classes3.dex */
abstract class zzu {
    zzu() {
    }

    abstract zzu zza(int i);

    abstract zzu zzb(Map map);

    abstract zzv zzc();

    abstract Map zzd();

    final zzv zze() {
        zzb(Collections.unmodifiableMap(zzd()));
        return zzc();
    }
}
