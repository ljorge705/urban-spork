package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.3.0 */
/* loaded from: classes3.dex */
final class zzje implements Runnable {
    final /* synthetic */ AtomicReference zza;
    final /* synthetic */ zzq zzb;
    final /* synthetic */ zzjz zzc;

    zzje(zzjz zzjzVar, AtomicReference atomicReference, zzq zzqVar) {
        this.zzc = zzjzVar;
        this.zza = atomicReference;
        this.zzb = zzqVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        AtomicReference atomicReference;
        synchronized (this.zza) {
            try {
                try {
                } catch (RemoteException e) {
                    this.zzc.zzt.zzaA().zzd().zzb("Failed to get app instance id", e);
                    atomicReference = this.zza;
                }
                if (!this.zzc.zzt.zzm().zzc().zzj(zzha.ANALYTICS_STORAGE)) {
                    this.zzc.zzt.zzaA().zzl().zza("Analytics storage consent denied; will not get app instance id");
                    this.zzc.zzt.zzq().zzO(null);
                    this.zzc.zzt.zzm().zze.zzb(null);
                    this.zza.set(null);
                    return;
                }
                zzjz zzjzVar = this.zzc;
                zzej zzejVar = zzjzVar.zzb;
                if (zzejVar == null) {
                    zzjzVar.zzt.zzaA().zzd().zza("Failed to get app instance id");
                    return;
                }
                Preconditions.checkNotNull(this.zzb);
                this.zza.set(zzejVar.zzd(this.zzb));
                String str = (String) this.zza.get();
                if (str != null) {
                    this.zzc.zzt.zzq().zzO(str);
                    this.zzc.zzt.zzm().zze.zzb(str);
                }
                this.zzc.zzQ();
                atomicReference = this.zza;
                atomicReference.notify();
            } finally {
                this.zza.notify();
            }
        }
    }
}
