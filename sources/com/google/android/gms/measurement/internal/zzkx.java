package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@21.3.0 */
/* loaded from: classes3.dex */
final class zzkx implements Runnable {
    final /* synthetic */ zzli zza;
    final /* synthetic */ zzlh zzb;

    zzkx(zzlh zzlhVar, zzli zzliVar) {
        this.zzb = zzlhVar;
        this.zza = zzliVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzlh.zzy(this.zzb, this.zza);
        this.zzb.zzS();
    }
}
