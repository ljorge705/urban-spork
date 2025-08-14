package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@21.3.0 */
/* loaded from: classes3.dex */
final class zzgf implements Runnable {
    final /* synthetic */ zzac zza;
    final /* synthetic */ zzq zzb;
    final /* synthetic */ zzgv zzc;

    zzgf(zzgv zzgvVar, zzac zzacVar, zzq zzqVar) {
        this.zzc = zzgvVar;
        this.zza = zzacVar;
        this.zzb = zzqVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzc.zza.zzA();
        if (this.zza.zzc.zza() == null) {
            this.zzc.zza.zzO(this.zza, this.zzb);
        } else {
            this.zzc.zza.zzU(this.zza, this.zzb);
        }
    }
}
