package com.google.firebase.storage;

import com.google.android.gms.tasks.CancellationTokenSource;
import com.google.android.gms.tasks.OnCanceledListener;

/* compiled from: D8$$SyntheticClass */
/* loaded from: classes2.dex */
public final /* synthetic */ class StorageTask$$ExternalSyntheticLambda5 implements OnCanceledListener {
    public final /* synthetic */ CancellationTokenSource f$0;

    public /* synthetic */ StorageTask$$ExternalSyntheticLambda5(CancellationTokenSource cancellationTokenSource) {
        this.f$0 = cancellationTokenSource;
    }

    @Override // com.google.android.gms.tasks.OnCanceledListener
    public final void onCanceled() {
        this.f$0.cancel();
    }
}
