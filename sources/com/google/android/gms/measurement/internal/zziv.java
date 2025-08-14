package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.3.0 */
/* loaded from: classes3.dex */
final class zziv implements Runnable {
    final /* synthetic */ zziz zza;

    zziv(zziz zzizVar) {
        this.zza = zzizVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zziz zzizVar = this.zza;
        zzizVar.zza = zzizVar.zzh;
    }
}
