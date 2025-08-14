package com.google.android.play.core.splitinstall.internal;

import com.nimbusds.jose.HeaderParameterNames;
import java.io.File;
import java.util.Set;

/* compiled from: com.google.android.play:feature-delivery@@2.1.0 */
/* loaded from: classes3.dex */
final class zzau implements zzan {
    zzau() {
    }

    @Override // com.google.android.play.core.splitinstall.internal.zzan
    public final void zza(ClassLoader classLoader, Set set) {
        zzat.zzd(classLoader, set);
    }

    @Override // com.google.android.play.core.splitinstall.internal.zzan
    public final boolean zzb(ClassLoader classLoader, File file, File file2, boolean z) {
        return zzat.zze(classLoader, file, file2, z, new zzap(), HeaderParameterNames.COMPRESSION_ALGORITHM, new zzaq());
    }
}
