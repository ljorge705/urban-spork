package com.google.android.play.core.splitinstall;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.play.core.splitinstall.internal.zzbp;
import java.util.List;

/* compiled from: com.google.android.play:feature-delivery@@2.1.0 */
/* loaded from: classes3.dex */
class zzbb extends zzbp {
    final TaskCompletionSource zza;
    final /* synthetic */ zzbc zzb;

    zzbb(zzbc zzbcVar, TaskCompletionSource taskCompletionSource) {
        this.zzb = zzbcVar;
        this.zza = taskCompletionSource;
    }

    public void zzb(int i, Bundle bundle) throws RemoteException {
        this.zzb.zza.zzu(this.zza);
        zzbc.zzb.zzd("onCancelInstall(%d)", Integer.valueOf(i));
    }

    public void zzc(Bundle bundle) throws RemoteException {
        this.zzb.zza.zzu(this.zza);
        zzbc.zzb.zzd("onDeferredInstall", new Object[0]);
    }

    public void zzd(Bundle bundle) throws RemoteException {
        this.zzb.zza.zzu(this.zza);
        zzbc.zzb.zzd("onDeferredLanguageInstall", new Object[0]);
    }

    public void zze(Bundle bundle) throws RemoteException {
        this.zzb.zza.zzu(this.zza);
        zzbc.zzb.zzd("onDeferredLanguageUninstall", new Object[0]);
    }

    public void zzf(Bundle bundle) throws RemoteException {
        this.zzb.zza.zzu(this.zza);
        zzbc.zzb.zzd("onDeferredUninstall", new Object[0]);
    }

    public void zzg(int i, Bundle bundle) throws RemoteException {
        this.zzb.zza.zzu(this.zza);
        zzbc.zzb.zzd("onGetSession(%d)", Integer.valueOf(i));
    }

    public void zzh(List list) throws RemoteException {
        this.zzb.zza.zzu(this.zza);
        zzbc.zzb.zzd("onGetSessionStates", new Object[0]);
    }

    public void zzi(int i, Bundle bundle) throws RemoteException {
        this.zzb.zza.zzu(this.zza);
        zzbc.zzb.zzd("onStartInstall(%d)", Integer.valueOf(i));
    }

    @Override // com.google.android.play.core.splitinstall.internal.zzbq
    public final void zzj(int i, Bundle bundle) throws RemoteException {
        this.zzb.zza.zzu(this.zza);
        zzbc.zzb.zzd("onCompleteInstall(%d)", Integer.valueOf(i));
    }

    @Override // com.google.android.play.core.splitinstall.internal.zzbq
    public final void zzk(Bundle bundle) throws RemoteException {
        this.zzb.zza.zzu(this.zza);
        zzbc.zzb.zzd("onCompleteInstallForAppUpdate", new Object[0]);
    }

    @Override // com.google.android.play.core.splitinstall.internal.zzbq
    public final void zzl(Bundle bundle) throws RemoteException {
        this.zzb.zza.zzu(this.zza);
        int i = bundle.getInt("error_code");
        zzbc.zzb.zzb("onError(%d)", Integer.valueOf(i));
        this.zza.trySetException(new SplitInstallException(i));
    }

    @Override // com.google.android.play.core.splitinstall.internal.zzbq
    public final void zzm(Bundle bundle) throws RemoteException {
        this.zzb.zza.zzu(this.zza);
        zzbc.zzb.zzd("onGetSplitsForAppUpdate", new Object[0]);
    }
}
