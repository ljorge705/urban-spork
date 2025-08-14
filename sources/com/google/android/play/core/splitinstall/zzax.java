package com.google.android.play.core.splitinstall;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.play:feature-delivery@@2.1.0 */
/* loaded from: classes3.dex */
final class zzax extends zzbb {
    zzax(zzbc zzbcVar, TaskCompletionSource taskCompletionSource) {
        super(zzbcVar, taskCompletionSource);
    }

    @Override // com.google.android.play.core.splitinstall.zzbb, com.google.android.play.core.splitinstall.internal.zzbq
    public final void zzf(Bundle bundle) throws RemoteException {
        super.zzf(bundle);
        this.zza.trySetResult(null);
    }
}
