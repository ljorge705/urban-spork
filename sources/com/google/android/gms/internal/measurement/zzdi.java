package com.google.android.gms.internal.measurement;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@21.3.0 */
/* loaded from: classes3.dex */
final class zzdi implements ThreadFactory {
    private final ThreadFactory zza = Executors.defaultThreadFactory();

    zzdi(zzef zzefVar) {
    }

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable runnable) {
        Thread threadNewThread = this.zza.newThread(runnable);
        threadNewThread.setName("ScionFrontendApi");
        return threadNewThread;
    }
}
