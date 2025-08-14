package com.onfido.android.sdk.capture.ui.camera.util;

import android.content.Context;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewTreeObserver;
import androidx.appcompat.R;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import com.onfido.android.sdk.capture.ui.CaptureType;
import com.onfido.android.sdk.capture.utils.ContextUtilsKt;
import com.onfido.reactnative.sdk.ReactNativeBridgeUtiles;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0007\b\u0000\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u000bJ\b\u0010\u0016\u001a\u00020\u0014H\u0007J\b\u0010\u0017\u001a\u00020\u0014H\u0007J\b\u0010\u0018\u001a\u00020\u0014H\u0007J\b\u0010\u0019\u001a\u00020\u0014H\u0007R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u00020\r8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/util/ValidationBubblesOffsetDelegate;", "Landroidx/lifecycle/LifecycleObserver;", "rootView", "Landroid/view/View;", "validationBubblesIds", "", "", ReactNativeBridgeUtiles.KEY_CAPTURE_TYPE, "Lcom/onfido/android/sdk/capture/ui/CaptureType;", "(Landroid/view/View;Ljava/util/List;Lcom/onfido/android/sdk/capture/ui/CaptureType;)V", "captureRect", "Landroid/graphics/RectF;", "isTablet", "", "()Z", "onDrawListener", "Landroid/view/ViewTreeObserver$OnPreDrawListener;", "toolbarSize", "validationBubblesViews", "onCaptureRegionSet", "", "rect", "onCreate", "onDestroy", "onStart", "onStop", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ValidationBubblesOffsetDelegate implements LifecycleObserver {
    private static final Companion Companion = new Companion(null);
    private static final int DEFAULT_TOOLBAR_HEIGHT_DP = 56;
    private RectF captureRect;
    private final CaptureType captureType;
    private ViewTreeObserver.OnPreDrawListener onDrawListener;
    private final View rootView;
    private final int toolbarSize;
    private final List<Integer> validationBubblesIds;
    private List<? extends View> validationBubblesViews;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/util/ValidationBubblesOffsetDelegate$Companion;", "", "()V", "DEFAULT_TOOLBAR_HEIGHT_DP", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public ValidationBubblesOffsetDelegate(View rootView, List<Integer> validationBubblesIds, CaptureType captureType) {
        Intrinsics.checkNotNullParameter(rootView, "rootView");
        Intrinsics.checkNotNullParameter(validationBubblesIds, "validationBubblesIds");
        Intrinsics.checkNotNullParameter(captureType, "captureType");
        this.rootView = rootView;
        this.validationBubblesIds = validationBubblesIds;
        this.captureType = captureType;
        this.captureRect = new RectF();
        this.validationBubblesViews = CollectionsKt.emptyList();
        Context context = rootView.getContext();
        Intrinsics.checkNotNull(context);
        Integer numResolveDimensionFromAttr$default = ContextUtilsKt.resolveDimensionFromAttr$default(context, R.attr.actionBarSize, null, false, 6, null);
        this.toolbarSize = numResolveDimensionFromAttr$default != null ? numResolveDimensionFromAttr$default.intValue() : (int) ContextUtilsKt.dpToPixel(context, 56);
        this.onDrawListener = new ViewTreeObserver.OnPreDrawListener() { // from class: com.onfido.android.sdk.capture.ui.camera.util.ValidationBubblesOffsetDelegate$$ExternalSyntheticLambda0
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public final boolean onPreDraw() {
                return ValidationBubblesOffsetDelegate.onDrawListener$lambda$2(this.f$0);
            }
        };
    }

    private final boolean isTablet() {
        return this.rootView.getResources().getBoolean(com.onfido.android.sdk.capture.R.bool.onfido_is_tablet);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onDrawListener$lambda$2(ValidationBubblesOffsetDelegate this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.captureType != CaptureType.DOCUMENT) {
            return true;
        }
        Iterator<T> it = this$0.validationBubblesViews.iterator();
        while (it.hasNext()) {
            ((View) it.next()).setTranslationY(this$0.isTablet() ? (this$0.captureRect.top - r1.getHeight()) - this$0.rootView.getResources().getDimension(com.onfido.android.sdk.capture.R.dimen.onfido_spacing_1x) : this$0.toolbarSize);
        }
        return true;
    }

    public final void onCaptureRegionSet(RectF rect) {
        Intrinsics.checkNotNullParameter(rect, "rect");
        this.captureRect = rect;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public final void onCreate() {
        List<Integer> list = this.validationBubblesIds;
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            View viewFindViewById = this.rootView.findViewById(((Number) it.next()).intValue());
            if (viewFindViewById != null) {
                arrayList.add(viewFindViewById);
            }
        }
        this.validationBubblesViews = arrayList;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public final void onDestroy() {
        this.validationBubblesViews = CollectionsKt.emptyList();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public final void onStart() {
        this.rootView.getViewTreeObserver().addOnPreDrawListener(this.onDrawListener);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public final void onStop() {
        this.rootView.getViewTreeObserver().removeOnPreDrawListener(this.onDrawListener);
    }
}
