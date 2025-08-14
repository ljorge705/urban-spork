package com.google.android.play.core.splitinstall;

import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.play:feature-delivery@@2.1.0 */
/* loaded from: classes3.dex */
final class zzas extends com.google.android.play.core.splitinstall.internal.zzv {
    final /* synthetic */ int zza;
    final /* synthetic */ TaskCompletionSource zzb;
    final /* synthetic */ zzbc zzc;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzas(zzbc zzbcVar, TaskCompletionSource taskCompletionSource, int i, TaskCompletionSource taskCompletionSource2) {
        super(taskCompletionSource);
        this.zzc = zzbcVar;
        this.zza = i;
        this.zzb = taskCompletionSource2;
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [android.os.IInterface, com.google.android.play.core.splitinstall.internal.zzbo] */
    @Override // com.google.android.play.core.splitinstall.internal.zzv
    protected final void zzc() {
        try {
            this.zzc.zza.zze().zzc(this.zzc.zzd, this.zza, zzbc.zzo(), new zzat(this.zzc, this.zzb));
        } catch (RemoteException e) {
            zzbc.zzb.zzc(e, "cancelInstall(%d)", Integer.valueOf(this.zza));
            this.zzb.trySetException(new RuntimeException(e));
        }
    }
}
