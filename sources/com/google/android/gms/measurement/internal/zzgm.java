package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement@@21.3.0 */
/* loaded from: classes3.dex */
final class zzgm implements Runnable {
    final /* synthetic */ zzq zza;
    final /* synthetic */ zzgv zzb;

    zzgm(zzgv zzgvVar, zzq zzqVar) {
        this.zzb = zzgvVar;
        this.zza = zzqVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zza.zzA();
        zzlh zzlhVar = this.zzb.zza;
        zzq zzqVar = this.zza;
        zzlhVar.zzaB().zzg();
        zzlhVar.zzB();
        Preconditions.checkNotEmpty(zzqVar.zza);
        zzlhVar.zzd(zzqVar);
    }
}
