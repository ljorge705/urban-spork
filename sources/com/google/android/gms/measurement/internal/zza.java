package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.3.0 */
/* loaded from: classes3.dex */
final class zza implements Runnable {
    final /* synthetic */ String zza;
    final /* synthetic */ long zzb;
    final /* synthetic */ zzd zzc;

    zza(zzd zzdVar, String str, long j) {
        this.zzc = zzdVar;
        this.zza = str;
        this.zzb = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzd.zza(this.zzc, this.zza, this.zzb);
    }
}
