package com.onfido.android.sdk.capture.utils;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import io.reactivex.rxjava3.disposables.Disposable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0007\u001a\u0014\u0010\u0005\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0007¨\u0006\u0006"}, d2 = {"disposeOnDestroy", "", "Lio/reactivex/rxjava3/disposables/Disposable;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "disposeOnStop", "onfido-capture-sdk-core_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class LifecycleDisposableKt {
    public static final void disposeOnDestroy(Disposable disposable, LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(disposable, "<this>");
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        lifecycleOwner.getLifecycle().addObserver(new LifecycleDisposable(disposable, Lifecycle.Event.ON_DESTROY));
    }

    public static final void disposeOnStop(Disposable disposable, LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(disposable, "<this>");
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        lifecycleOwner.getLifecycle().addObserver(new LifecycleDisposable(disposable, Lifecycle.Event.ON_STOP));
    }
}
