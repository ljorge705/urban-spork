package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.3.0 */
/* loaded from: classes3.dex */
final class zzkl {
    final /* synthetic */ zzkp zza;
    private zzkk zzb;

    zzkl(zzkp zzkpVar) {
        this.zza = zzkpVar;
    }

    final void zza(long j) {
        this.zzb = new zzkk(this, this.zza.zzt.zzax().currentTimeMillis(), j);
        this.zza.zzd.postDelayed(this.zzb, 2000L);
    }

    final void zzb() {
        this.zza.zzg();
        zzkk zzkkVar = this.zzb;
        if (zzkkVar != null) {
            this.zza.zzd.removeCallbacks(zzkkVar);
        }
        this.zza.zzt.zzm().zzm.zza(false);
        this.zza.zzm(false);
    }
}
