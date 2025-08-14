package com.google.android.gms.measurement.internal;

import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-measurement@@21.3.0 */
/* loaded from: classes3.dex */
final class zzgj implements Callable {
    final /* synthetic */ String zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ String zzc;
    final /* synthetic */ zzgv zzd;

    zzgj(zzgv zzgvVar, String str, String str2, String str3) {
        this.zzd = zzgvVar;
        this.zza = str;
        this.zzb = str2;
        this.zzc = str3;
    }

    @Override // java.util.concurrent.Callable
    public final /* bridge */ /* synthetic */ Object call() throws Exception {
        this.zzd.zza.zzA();
        return this.zzd.zza.zzh().zzs(this.zza, this.zzb, this.zzc);
    }
}
