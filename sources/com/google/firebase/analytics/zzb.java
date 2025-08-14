package com.google.firebase.analytics;

import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-measurement-api@@21.3.0 */
/* loaded from: classes5.dex */
final class zzb implements Callable {
    final /* synthetic */ FirebaseAnalytics zza;

    zzb(FirebaseAnalytics firebaseAnalytics) {
        this.zza = firebaseAnalytics;
    }

    @Override // java.util.concurrent.Callable
    public final /* bridge */ /* synthetic */ Object call() throws Exception {
        return this.zza.zzb.zzl();
    }
}
