package com.google.firebase.storage;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: D8$$SyntheticClass */
/* loaded from: classes2.dex */
public final /* synthetic */ class StorageTask$$ExternalSyntheticLambda4 implements OnFailureListener {
    public final /* synthetic */ TaskCompletionSource f$0;

    public /* synthetic */ StorageTask$$ExternalSyntheticLambda4(TaskCompletionSource taskCompletionSource) {
        this.f$0 = taskCompletionSource;
    }

    @Override // com.google.android.gms.tasks.OnFailureListener
    public final void onFailure(Exception exc) {
        this.f$0.setException(exc);
    }
}
