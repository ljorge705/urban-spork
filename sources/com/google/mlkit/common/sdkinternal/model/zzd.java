package com.google.mlkit.common.sdkinternal.model;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.internal.mlkit_common.zziy;
import com.google.android.gms.internal.mlkit_common.zzje;
import com.google.android.gms.internal.mlkit_common.zzmh;
import com.google.android.gms.internal.mlkit_common.zzmq;
import com.google.android.gms.internal.mlkit_common.zzmr;
import com.google.android.gms.internal.mlkit_common.zzms;
import com.google.android.gms.internal.mlkit_common.zzmt;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.model.RemoteModel;

/* compiled from: com.google.mlkit:common@@18.5.0 */
/* loaded from: classes2.dex */
final class zzd extends BroadcastReceiver {
    final /* synthetic */ RemoteModelDownloadManager zza;
    private final long zzb;
    private final TaskCompletionSource zzc;

    /* synthetic */ zzd(RemoteModelDownloadManager remoteModelDownloadManager, long j, TaskCompletionSource taskCompletionSource, zzc zzcVar) {
        this.zza = remoteModelDownloadManager;
        this.zzb = j;
        this.zzc = taskCompletionSource;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        long longExtra = intent.getLongExtra("extra_download_id", -1L);
        if (longExtra != this.zzb) {
            return;
        }
        Integer downloadingModelStatusCode = this.zza.getDownloadingModelStatusCode();
        synchronized (this.zza) {
            try {
                this.zza.zze.getApplicationContext().unregisterReceiver(this);
            } catch (IllegalArgumentException e) {
                RemoteModelDownloadManager.zza.w("ModelDownloadManager", "Exception thrown while trying to unregister the broadcast receiver for the download", e);
            }
            this.zza.zzc.remove(this.zzb);
            this.zza.zzd.remove(this.zzb);
        }
        if (downloadingModelStatusCode != null) {
            if (downloadingModelStatusCode.intValue() == 16) {
                zzmq zzmqVar = this.zza.zzi;
                zzmh zzmhVarZzg = zzmt.zzg();
                RemoteModelDownloadManager remoteModelDownloadManager = this.zza;
                RemoteModel remoteModel = remoteModelDownloadManager.zzg;
                Long lValueOf = Long.valueOf(longExtra);
                zzmqVar.zze(zzmhVarZzg, remoteModel, false, remoteModelDownloadManager.getFailureReason(lValueOf));
                this.zzc.setException(this.zza.zzl(lValueOf));
                return;
            }
            if (downloadingModelStatusCode.intValue() == 8) {
                zzmq zzmqVar2 = this.zza.zzi;
                zzmh zzmhVarZzg2 = zzmt.zzg();
                RemoteModel remoteModel2 = this.zza.zzg;
                zzmr zzmrVarZzh = zzms.zzh();
                zzmrVarZzh.zzb(zziy.NO_ERROR);
                zzmrVarZzh.zze(true);
                zzmrVarZzh.zzd(this.zza.zzg.getModelType());
                zzmrVarZzh.zza(zzje.SUCCEEDED);
                zzmqVar2.zzg(zzmhVarZzg2, remoteModel2, zzmrVarZzh.zzh());
                this.zzc.setResult(null);
                return;
            }
        }
        this.zza.zzi.zze(zzmt.zzg(), this.zza.zzg, false, 0);
        this.zzc.setException(new MlKitException("Model downloading failed", 13));
    }
}
