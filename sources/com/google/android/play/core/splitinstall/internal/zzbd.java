package com.google.android.play.core.splitinstall.internal;

import com.ReactNativeBlobUtil.ReactNativeBlobUtilConst;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

/* compiled from: com.google.android.play:feature-delivery@@2.1.0 */
/* loaded from: classes3.dex */
final class zzbd implements zzan {
    zzbd() {
    }

    static void zzc(ClassLoader classLoader, Set set) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        zzay.zzc(classLoader, set, new zzbb());
    }

    static boolean zzd(ClassLoader classLoader, File file, File file2, boolean z) {
        return zzat.zze(classLoader, file, file2, z, new zzav(), ReactNativeBlobUtilConst.RNFB_RESPONSE_PATH, new zzbc());
    }

    @Override // com.google.android.play.core.splitinstall.internal.zzan
    public final void zza(ClassLoader classLoader, Set set) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        zzc(classLoader, set);
    }

    @Override // com.google.android.play.core.splitinstall.internal.zzan
    public final boolean zzb(ClassLoader classLoader, File file, File file2, boolean z) {
        return zzd(classLoader, file, file2, z);
    }
}
