package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.3.0 */
/* loaded from: classes3.dex */
final class zzam implements Runnable {
    final /* synthetic */ zzgy zza;
    final /* synthetic */ zzan zzb;

    zzam(zzan zzanVar, zzgy zzgyVar) {
        this.zzb = zzanVar;
        this.zza = zzgyVar;
    }

    @Override // java.lang.Runnable
    public final void run() throws IllegalStateException {
        this.zza.zzay();
        if (zzab.zza()) {
            this.zza.zzaB().zzp(this);
            return;
        }
        boolean zZze = this.zzb.zze();
        this.zzb.zzd = 0L;
        if (zZze) {
            this.zzb.zzc();
        }
    }
}
