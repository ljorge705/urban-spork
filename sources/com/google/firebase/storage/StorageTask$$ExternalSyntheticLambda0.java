package com.google.firebase.storage;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: D8$$SyntheticClass */
/* loaded from: classes2.dex */
public final /* synthetic */ class StorageTask$$ExternalSyntheticLambda0 implements OnSuccessListener {
    public final /* synthetic */ TaskCompletionSource f$0;

    public /* synthetic */ StorageTask$$ExternalSyntheticLambda0(TaskCompletionSource taskCompletionSource) {
        this.f$0 = taskCompletionSource;
    }

    @Override // com.google.android.gms.tasks.OnSuccessListener
    public final void onSuccess(Object obj) {
        this.f$0.setResult(obj);
    }
}
