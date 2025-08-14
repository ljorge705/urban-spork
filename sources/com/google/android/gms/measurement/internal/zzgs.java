package com.google.android.gms.measurement.internal;

import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-measurement@@21.3.0 */
/* loaded from: classes3.dex */
final class zzgs implements Callable {
    final /* synthetic */ String zza;
    final /* synthetic */ zzgv zzb;

    zzgs(zzgv zzgvVar, String str) {
        this.zzb = zzgvVar;
        this.zza = str;
    }

    @Override // java.util.concurrent.Callable
    public final /* bridge */ /* synthetic */ Object call() throws Exception {
        this.zzb.zza.zzA();
        return this.zzb.zza.zzh().zzu(this.zza);
    }
}
