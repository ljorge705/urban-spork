package com.onfido.android.sdk.capture.ui.camera.util;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.window.layout.WindowInfoTracker;
import androidx.window.layout.WindowLayoutInfo;
import androidx.window.rxjava3.layout.WindowInfoTrackerRx;
import com.onfido.android.sdk.capture.ui.camera.OverlayTextView;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\b\u0000\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\rH\u0002J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0010\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0010\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0010\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0010\u001a\u00020\rH\u0002J\u0015\u0010\u0018\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0019\u001a\u00020\u0007¢\u0006\u0002\u0010\u001aJ\u000f\u0010\u001b\u001a\u0004\u0018\u00010\u0012H\u0002¢\u0006\u0002\u0010\u001cJ\u000f\u0010\u001d\u001a\u0004\u0018\u00010\u0012H\u0002¢\u0006\u0002\u0010\u001cR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/util/LivenessCaptureLayoutAdjuster;", "Landroidx/lifecycle/DefaultLifecycleObserver;", "context", "Landroid/content/Context;", "overlayTextContainer", "Lcom/onfido/android/sdk/capture/ui/camera/OverlayTextView;", "dummyAccessibilityView", "Landroid/view/View;", "(Landroid/content/Context;Lcom/onfido/android/sdk/capture/ui/camera/OverlayTextView;Landroid/view/View;)V", "disposable", "Lio/reactivex/rxjava3/disposables/Disposable;", "windowLayoutObservable", "Lio/reactivex/rxjava3/core/Observable;", "Landroidx/window/layout/WindowLayoutInfo;", "isWindowSpannedAcrossDisplays", "", "updatedWindow", "onCreate", "", "owner", "Landroidx/lifecycle/LifecycleOwner;", "onDestroy", "onStop", "onUpdatedWindow", "setCaptureInstructionsAboveView", "view", "(Landroid/view/View;)Lkotlin/Unit;", "setCaptureInstructionsBelowCaptureRect", "()Lkotlin/Unit;", "setCaptureInstructionsFreeToOverlayCaptureRect", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class LivenessCaptureLayoutAdjuster implements DefaultLifecycleObserver {
    private final Context context;
    private Disposable disposable;
    private final View dummyAccessibilityView;
    private OverlayTextView overlayTextContainer;
    private Observable<WindowLayoutInfo> windowLayoutObservable;

    public LivenessCaptureLayoutAdjuster(Context context, OverlayTextView overlayTextView, View view) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.overlayTextContainer = overlayTextView;
        this.dummyAccessibilityView = view;
    }

    private final boolean isWindowSpannedAcrossDisplays(WindowLayoutInfo updatedWindow) {
        return !updatedWindow.getDisplayFeatures().isEmpty();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onUpdatedWindow(WindowLayoutInfo updatedWindow) {
        if (isWindowSpannedAcrossDisplays(updatedWindow)) {
            setCaptureInstructionsFreeToOverlayCaptureRect();
        } else {
            setCaptureInstructionsBelowCaptureRect();
        }
    }

    private final Unit setCaptureInstructionsBelowCaptureRect() {
        OverlayTextView overlayTextView = this.overlayTextContainer;
        if (overlayTextView == null) {
            return null;
        }
        ViewGroup.LayoutParams layoutParams = overlayTextView.getLayoutParams();
        if (layoutParams == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.widget.RelativeLayout.LayoutParams");
        }
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
        View view = this.dummyAccessibilityView;
        if (view != null) {
            layoutParams2.addRule(3, view.getId());
        }
        overlayTextView.setLayoutParams(layoutParams2);
        return Unit.INSTANCE;
    }

    private final Unit setCaptureInstructionsFreeToOverlayCaptureRect() {
        OverlayTextView overlayTextView = this.overlayTextContainer;
        if (overlayTextView == null) {
            return null;
        }
        ViewGroup.LayoutParams layoutParams = overlayTextView.getLayoutParams();
        if (layoutParams == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.widget.RelativeLayout.LayoutParams");
        }
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
        if (this.dummyAccessibilityView != null) {
            layoutParams2.removeRule(3);
        }
        overlayTextView.setLayoutParams(layoutParams2);
        return Unit.INSTANCE;
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public void onCreate(LifecycleOwner owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        this.windowLayoutObservable = WindowInfoTrackerRx.windowLayoutInfoObservable(WindowInfoTracker.INSTANCE.getOrCreate(this.context), this.context);
        Disposable disposable = this.disposable;
        if (disposable != null) {
            disposable.dispose();
        }
        Observable<WindowLayoutInfo> observable = this.windowLayoutObservable;
        if (observable == null) {
            Intrinsics.throwUninitializedPropertyAccessException("windowLayoutObservable");
            observable = null;
        }
        this.disposable = observable.observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.ui.camera.util.LivenessCaptureLayoutAdjuster.onCreate.1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(WindowLayoutInfo p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                LivenessCaptureLayoutAdjuster.this.onUpdatedWindow(p0);
            }
        });
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public void onDestroy(LifecycleOwner owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        this.overlayTextContainer = null;
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public void onStop(LifecycleOwner owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        Disposable disposable = this.disposable;
        if (disposable != null) {
            disposable.dispose();
        }
    }

    public final Unit setCaptureInstructionsAboveView(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        OverlayTextView overlayTextView = this.overlayTextContainer;
        if (overlayTextView == null) {
            return null;
        }
        ViewGroup.LayoutParams layoutParams = overlayTextView.getLayoutParams();
        if (layoutParams == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.widget.RelativeLayout.LayoutParams");
        }
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
        layoutParams2.addRule(2, view.getId());
        overlayTextView.setLayoutParams(layoutParams2);
        return Unit.INSTANCE;
    }
}
