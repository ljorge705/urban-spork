package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.3.0 */
/* loaded from: classes3.dex */
final class zzih implements Runnable {
    final /* synthetic */ boolean zza;
    final /* synthetic */ zzik zzb;

    zzih(zzik zzikVar, boolean z) {
        this.zzb = zzikVar;
        this.zza = z;
    }

    @Override // java.lang.Runnable
    public final void run() throws IllegalStateException {
        boolean zZzJ = this.zzb.zzt.zzJ();
        boolean zZzI = this.zzb.zzt.zzI();
        this.zzb.zzt.zzF(this.zza);
        if (zZzI == this.zza) {
            this.zzb.zzt.zzaA().zzj().zzb("Default data collection state already set to", Boolean.valueOf(this.zza));
        }
        if (this.zzb.zzt.zzJ() == zZzJ || this.zzb.zzt.zzJ() != this.zzb.zzt.zzI()) {
            this.zzb.zzt.zzaA().zzl().zzc("Default data collection is different than actual status", Boolean.valueOf(this.zza), Boolean.valueOf(zZzJ));
        }
        this.zzb.zzab();
    }
}
