package com.onfido.android.sdk.capture.internal.ui.overlay;

import android.animation.ArgbEvaluator;
import android.animation.FloatEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Paint;
import android.view.View;
import com.google.firebase.messaging.Constants;
import com.onfido.android.sdk.capture.utils.ContextUtilsKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J6\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\b2\b\b\u0001\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00060\rJ*\u0010\u000e\u001a\u00020\u00062\b\b\u0001\u0010\u000f\u001a\u00020\b2\b\b\u0001\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\n\u001a\u00020\u000bJ*\u0010\u0013\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\b2\b\b\u0001\u0010\t\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\n\u001a\u00020\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/ui/overlay/OverlayViewAnimations;", "", "view", "Landroid/view/View;", "(Landroid/view/View;)V", "animateBackgroundColor", "", Constants.MessagePayloadKeys.FROM, "", "to", "animDuration", "", "colorUpdateListener", "Lkotlin/Function1;", "animatePaintColorChange", "fromColorAttr", "toColorAttr", "paint", "Landroid/graphics/Paint;", "animateStrokeWidth", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class OverlayViewAnimations {
    private final View view;

    public OverlayViewAnimations(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        this.view = view;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void animateBackgroundColor$lambda$1$lambda$0(Function1 colorUpdateListener, OverlayViewAnimations this$0, ValueAnimator it) {
        Intrinsics.checkNotNullParameter(colorUpdateListener, "$colorUpdateListener");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        Object animatedValue = it.getAnimatedValue();
        Intrinsics.checkNotNull(animatedValue, "null cannot be cast to non-null type kotlin.Int");
        colorUpdateListener.invoke((Integer) animatedValue);
        this$0.view.invalidate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void animatePaintColorChange$lambda$3$lambda$2(Paint paint, OverlayViewAnimations this$0, ValueAnimator it) {
        Intrinsics.checkNotNullParameter(paint, "$paint");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        Object animatedValue = it.getAnimatedValue();
        Intrinsics.checkNotNull(animatedValue, "null cannot be cast to non-null type kotlin.Int");
        paint.setColor(((Integer) animatedValue).intValue());
        this$0.view.invalidate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void animateStrokeWidth$lambda$5$lambda$4(Paint paint, OverlayViewAnimations this$0, ValueAnimator it) {
        Intrinsics.checkNotNullParameter(paint, "$paint");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        Object animatedValue = it.getAnimatedValue();
        Intrinsics.checkNotNull(animatedValue, "null cannot be cast to non-null type kotlin.Float");
        paint.setStrokeWidth(((Float) animatedValue).floatValue());
        this$0.view.invalidate();
    }

    public final void animateBackgroundColor(int from, int to, long animDuration, final Function1<? super Integer, Unit> colorUpdateListener) {
        Intrinsics.checkNotNullParameter(colorUpdateListener, "colorUpdateListener");
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setIntValues(from, to);
        valueAnimator.setEvaluator(new ArgbEvaluator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.onfido.android.sdk.capture.internal.ui.overlay.OverlayViewAnimations$$ExternalSyntheticLambda2
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                OverlayViewAnimations.animateBackgroundColor$lambda$1$lambda$0(colorUpdateListener, this, valueAnimator2);
            }
        });
        valueAnimator.setDuration(animDuration);
        valueAnimator.start();
    }

    public final void animatePaintColorChange(int fromColorAttr, int toColorAttr, final Paint paint, long animDuration) {
        Intrinsics.checkNotNullParameter(paint, "paint");
        Context context = this.view.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        int iColorFromAttr = ContextUtilsKt.colorFromAttr(context, fromColorAttr);
        Context context2 = this.view.getContext();
        Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
        int iColorFromAttr2 = ContextUtilsKt.colorFromAttr(context2, toColorAttr);
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setIntValues(iColorFromAttr, iColorFromAttr2);
        valueAnimator.setEvaluator(new ArgbEvaluator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.onfido.android.sdk.capture.internal.ui.overlay.OverlayViewAnimations$$ExternalSyntheticLambda0
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                OverlayViewAnimations.animatePaintColorChange$lambda$3$lambda$2(paint, this, valueAnimator2);
            }
        });
        valueAnimator.setDuration(animDuration);
        valueAnimator.start();
    }

    public final void animateStrokeWidth(int from, int to, final Paint paint, long animDuration) throws Resources.NotFoundException {
        Intrinsics.checkNotNullParameter(paint, "paint");
        float dimension = this.view.getContext().getResources().getDimension(from);
        float dimension2 = this.view.getContext().getResources().getDimension(to);
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setFloatValues(dimension, dimension2);
        valueAnimator.setEvaluator(new FloatEvaluator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.onfido.android.sdk.capture.internal.ui.overlay.OverlayViewAnimations$$ExternalSyntheticLambda1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                OverlayViewAnimations.animateStrokeWidth$lambda$5$lambda$4(paint, this, valueAnimator2);
            }
        });
        valueAnimator.setDuration(animDuration);
        valueAnimator.start();
    }
}
