package com.google.android.play.core.splitinstall;

import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.List;

/* compiled from: com.google.android.play:feature-delivery@@2.1.0 */
/* loaded from: classes3.dex */
final class zzan extends com.google.android.play.core.splitinstall.internal.zzv {
    final /* synthetic */ List zza;
    final /* synthetic */ TaskCompletionSource zzb;
    final /* synthetic */ zzbc zzc;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzan(zzbc zzbcVar, TaskCompletionSource taskCompletionSource, List list, TaskCompletionSource taskCompletionSource2) {
        super(taskCompletionSource);
        this.zzc = zzbcVar;
        this.zza = list;
        this.zzb = taskCompletionSource2;
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [android.os.IInterface, com.google.android.play.core.splitinstall.internal.zzbo] */
    @Override // com.google.android.play.core.splitinstall.internal.zzv
    protected final void zzc() {
        try {
            this.zzc.zza.zze().zzd(this.zzc.zzd, zzbc.zzn(this.zza), zzbc.zzo(), new zzau(this.zzc, this.zzb));
        } catch (RemoteException e) {
            zzbc.zzb.zzc(e, "deferredInstall(%s)", this.zza);
            this.zzb.trySetException(new RuntimeException(e));
        }
    }
}
