package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.3.0 */
/* loaded from: classes3.dex */
final class zzid implements Runnable {
    final /* synthetic */ AtomicReference zza;
    final /* synthetic */ zzik zzb;

    zzid(zzik zzikVar, AtomicReference atomicReference) {
        this.zzb = zzikVar;
        this.zza = atomicReference;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.zza) {
            try {
                this.zza.set(Double.valueOf(this.zzb.zzt.zzf().zza(this.zzb.zzt.zzh().zzl(), zzeg.zzO)));
            } finally {
                this.zza.notify();
            }
        }
    }
}
