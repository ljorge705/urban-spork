package com.google.android.play.core.splitinstall;

import androidx.camera.view.PreviewView$1$$ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.play:feature-delivery@@2.1.0 */
/* loaded from: classes3.dex */
public final class zzr {
    private static final AtomicReference zza = new AtomicReference(null);

    static zzq zza() {
        return (zzq) zza.get();
    }

    public static void zzb(zzq zzqVar) {
        AtomicReference atomicReference = zza;
        while (!PreviewView$1$$ExternalSyntheticBackportWithForwarding0.m(atomicReference, null, zzqVar) && atomicReference.get() == null) {
        }
    }
}
