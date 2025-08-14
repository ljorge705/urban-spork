package com.google.android.play.core.splitinstall;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.android.play:feature-delivery@@2.1.0 */
/* loaded from: classes3.dex */
final class zzaz extends zzbb {
    zzaz(zzbc zzbcVar, TaskCompletionSource taskCompletionSource) {
        super(zzbcVar, taskCompletionSource);
    }

    @Override // com.google.android.play.core.splitinstall.zzbb, com.google.android.play.core.splitinstall.internal.zzbq
    public final void zzh(List list) throws RemoteException {
        super.zzh(list);
        ArrayList arrayList = new ArrayList(list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(SplitInstallSessionState.zzd((Bundle) it.next()));
        }
        this.zza.trySetResult(arrayList);
    }
}
