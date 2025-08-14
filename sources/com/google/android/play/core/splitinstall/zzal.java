package com.google.android.play.core.splitinstall;

import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.ArrayList;
import java.util.Collection;

/* compiled from: com.google.android.play:feature-delivery@@2.1.0 */
/* loaded from: classes3.dex */
final class zzal extends com.google.android.play.core.splitinstall.internal.zzv {
    final /* synthetic */ Collection zza;
    final /* synthetic */ Collection zzb;
    final /* synthetic */ com.google.android.play.core.splitinstall.internal.zzn zzc;
    final /* synthetic */ TaskCompletionSource zzd;
    final /* synthetic */ zzbc zze;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzal(zzbc zzbcVar, TaskCompletionSource taskCompletionSource, Collection collection, Collection collection2, com.google.android.play.core.splitinstall.internal.zzn zznVar, TaskCompletionSource taskCompletionSource2) {
        super(taskCompletionSource);
        this.zze = zzbcVar;
        this.zza = collection;
        this.zzb = collection2;
        this.zzc = zznVar;
        this.zzd = taskCompletionSource2;
    }

    /* JADX WARN: Type inference failed for: r1v7, types: [android.os.IInterface, com.google.android.play.core.splitinstall.internal.zzbo] */
    @Override // com.google.android.play.core.splitinstall.internal.zzv
    protected final void zzc() {
        ArrayList arrayListZzn = zzbc.zzn(this.zza);
        arrayListZzn.addAll(zzbc.zzm(this.zzb));
        try {
            this.zzc.zzb(2);
            this.zze.zza.zze().zzj(this.zze.zzd, arrayListZzn, zzbc.zzb(this.zzc), new zzba(this.zze, this.zzd));
        } catch (RemoteException e) {
            zzbc.zzb.zzc(e, "startInstall(%s,%s)", this.zza, this.zzb);
            this.zzd.trySetException(new RuntimeException(e));
        }
    }
}
