package io.reactivex.rxjava3.core;

import io.reactivex.rxjava3.disposables.Disposable;

/* compiled from: D8$$SyntheticClass */
/* loaded from: classes6.dex */
public final /* synthetic */ class Flowable$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ Disposable f$0;

    public /* synthetic */ Flowable$$ExternalSyntheticLambda0(Disposable disposable) {
        this.f$0 = disposable;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.dispose();
    }
}
