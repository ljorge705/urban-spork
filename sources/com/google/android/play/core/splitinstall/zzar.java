package com.google.android.play.core.splitinstall;

import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.play:feature-delivery@@2.1.0 */
/* loaded from: classes3.dex */
final class zzar extends com.google.android.play.core.splitinstall.internal.zzv {
    final /* synthetic */ TaskCompletionSource zza;
    final /* synthetic */ zzbc zzb;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzar(zzbc zzbcVar, TaskCompletionSource taskCompletionSource, TaskCompletionSource taskCompletionSource2) {
        super(taskCompletionSource);
        this.zzb = zzbcVar;
        this.zza = taskCompletionSource2;
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [android.os.IInterface, com.google.android.play.core.splitinstall.internal.zzbo] */
    @Override // com.google.android.play.core.splitinstall.internal.zzv
    protected final void zzc() {
        try {
            ?? Zze = this.zzb.zza.zze();
            zzbc zzbcVar = this.zzb;
            Zze.zzi(zzbcVar.zzd, new zzaz(zzbcVar, this.zza));
        } catch (RemoteException e) {
            zzbc.zzb.zzc(e, "getSessionStates", new Object[0]);
            this.zza.trySetException(new RuntimeException(e));
        }
    }
}
