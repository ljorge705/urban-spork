package com.google.android.play.core.splitcompat;

import java.util.concurrent.ThreadFactory;

/* compiled from: com.google.android.play:feature-delivery@@2.1.0 */
/* loaded from: classes3.dex */
final class zzc implements ThreadFactory {
    zzc() {
    }

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable runnable) {
        return new Thread(runnable, "SplitCompatBackgroundThread");
    }
}
