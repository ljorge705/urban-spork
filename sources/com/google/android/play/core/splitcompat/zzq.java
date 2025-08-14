package com.google.android.play.core.splitcompat;

import android.util.Log;

/* compiled from: com.google.android.play:feature-delivery@@2.1.0 */
/* loaded from: classes3.dex */
final class zzq implements Runnable {
    final /* synthetic */ SplitCompat zza;

    zzq(SplitCompat splitCompat) {
        this.zza = splitCompat;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            this.zza.zzc.zzk();
        } catch (Exception e) {
            Log.e("SplitCompat", "Failed to cleanup splitcompat storage", e);
        }
    }
}
