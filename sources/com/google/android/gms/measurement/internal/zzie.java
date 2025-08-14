package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.3.0 */
/* loaded from: classes3.dex */
final class zzie implements Runnable {
    final /* synthetic */ Boolean zza;
    final /* synthetic */ zzik zzb;

    zzie(zzik zzikVar, Boolean bool) {
        this.zzb = zzikVar;
        this.zza = bool;
    }

    @Override // java.lang.Runnable
    public final void run() throws IllegalStateException {
        this.zzb.zzaa(this.zza, true);
    }
}
