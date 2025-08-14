package com.onfido.android.sdk.capture.ui.base;

import androidx.exifinterface.media.ExifInterface;
import com.onfido.android.sdk.capture.ui.base.BaseView;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0002\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0015\u0010\u000f\u001a\u00020\u00102\u0006\u0010\t\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\rJ\b\u0010\u0011\u001a\u00020\u0010H\u0016R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u00028\u0000X\u0086.¢\u0006\u0010\n\u0002\u0010\u000e\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u0012"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/base/BasePresenter;", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "Lcom/onfido/android/sdk/capture/ui/base/BaseView;", "", "()V", "compositeDisposable", "Lio/reactivex/rxjava3/disposables/CompositeDisposable;", "getCompositeDisposable", "()Lio/reactivex/rxjava3/disposables/CompositeDisposable;", "view", "getView", "()Lcom/onfido/android/sdk/capture/ui/base/BaseView;", "setView", "(Lcom/onfido/android/sdk/capture/ui/base/BaseView;)V", "Lcom/onfido/android/sdk/capture/ui/base/BaseView;", "attachView", "", "detachView", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public abstract class BasePresenter<V extends BaseView> {
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    public V view;

    public void attachView(V view) {
        Intrinsics.checkNotNullParameter(view, "view");
        setView(view);
    }

    public void detachView() {
        this.compositeDisposable.clear();
    }

    public final CompositeDisposable getCompositeDisposable() {
        return this.compositeDisposable;
    }

    public final V getView() {
        V v = this.view;
        if (v != null) {
            return v;
        }
        Intrinsics.throwUninitializedPropertyAccessException("view");
        return null;
    }

    public final void setView(V v) {
        Intrinsics.checkNotNullParameter(v, "<set-?>");
        this.view = v;
    }
}
