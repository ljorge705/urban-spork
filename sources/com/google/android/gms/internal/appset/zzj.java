package com.google.android.gms.internal.appset;

import com.google.android.gms.common.util.DefaultClock;

/* compiled from: com.google.android.gms:play-services-appset@@16.0.0 */
/* loaded from: classes3.dex */
final class zzj implements Runnable {
    final /* synthetic */ zzl zza;

    /* synthetic */ zzj(zzl zzlVar, zzi zziVar) {
        this.zza = zzlVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        long jZza = this.zza.zza();
        if (jZza == -1 || DefaultClock.getInstance().currentTimeMillis() <= jZza) {
            return;
        }
        zzl.zze(this.zza.zzb);
    }
}
