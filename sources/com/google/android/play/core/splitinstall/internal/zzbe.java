package com.google.android.play.core.splitinstall.internal;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

/* compiled from: com.google.android.play:feature-delivery@@2.1.0 */
/* loaded from: classes3.dex */
final class zzbe implements zzan {
    zzbe() {
    }

    @Override // com.google.android.play.core.splitinstall.internal.zzan
    public final void zza(ClassLoader classLoader, Set set) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        zzbd.zzc(classLoader, set);
    }

    @Override // com.google.android.play.core.splitinstall.internal.zzan
    public final boolean zzb(ClassLoader classLoader, File file, File file2, boolean z) {
        return zzbd.zzd(classLoader, file, file2, z);
    }
}
