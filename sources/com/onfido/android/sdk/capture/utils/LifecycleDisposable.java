package com.onfido.android.sdk.capture.utils;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import io.reactivex.rxjava3.disposables.Disposable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0005H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/onfido/android/sdk/capture/utils/LifecycleDisposable;", "Landroidx/lifecycle/LifecycleEventObserver;", "disposable", "Lio/reactivex/rxjava3/disposables/Disposable;", "targetEvent", "Landroidx/lifecycle/Lifecycle$Event;", "(Lio/reactivex/rxjava3/disposables/Disposable;Landroidx/lifecycle/Lifecycle$Event;)V", "onStateChanged", "", "source", "Landroidx/lifecycle/LifecycleOwner;", "event", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class LifecycleDisposable implements LifecycleEventObserver {
    private final Disposable disposable;
    private final Lifecycle.Event targetEvent;

    public LifecycleDisposable(Disposable disposable, Lifecycle.Event targetEvent) {
        Intrinsics.checkNotNullParameter(disposable, "disposable");
        Intrinsics.checkNotNullParameter(targetEvent, "targetEvent");
        this.disposable = disposable;
        this.targetEvent = targetEvent;
    }

    @Override // androidx.lifecycle.LifecycleEventObserver
    public void onStateChanged(LifecycleOwner source, Lifecycle.Event event) {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(event, "event");
        if (event == this.targetEvent) {
            this.disposable.dispose();
            source.getLifecycle().removeObserver(this);
        }
    }
}
