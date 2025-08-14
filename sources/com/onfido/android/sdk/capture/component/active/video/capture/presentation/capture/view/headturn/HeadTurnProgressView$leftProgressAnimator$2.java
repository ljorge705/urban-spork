package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.headturn;

import android.animation.ValueAnimator;
import android.view.animation.LinearInterpolator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Landroid/animation/ValueAnimator;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
final class HeadTurnProgressView$leftProgressAnimator$2 extends Lambda implements Function0<ValueAnimator> {
    final /* synthetic */ HeadTurnProgressView this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    HeadTurnProgressView$leftProgressAnimator$2(HeadTurnProgressView headTurnProgressView) {
        super(0);
        this.this$0 = headTurnProgressView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invoke$lambda$1$lambda$0(HeadTurnProgressView this$0, ValueAnimator it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        Object animatedValue = it.getAnimatedValue();
        Intrinsics.checkNotNull(animatedValue, "null cannot be cast to non-null type kotlin.Float");
        this$0.currentLeftProgress = ((Float) animatedValue).floatValue();
        this$0.setupLeftProgressForDrawing(this$0.currentLeftProgress);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    public final ValueAnimator invoke() {
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(new float[0]);
        final HeadTurnProgressView headTurnProgressView = this.this$0;
        valueAnimatorOfFloat.setDuration(HeadTurnProgressView.ANIMATION_DURATION);
        valueAnimatorOfFloat.setInterpolator(new LinearInterpolator());
        valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.headturn.HeadTurnProgressView$leftProgressAnimator$2$$ExternalSyntheticLambda0
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                HeadTurnProgressView$leftProgressAnimator$2.invoke$lambda$1$lambda$0(headTurnProgressView, valueAnimator);
            }
        });
        return valueAnimatorOfFloat;
    }
}
