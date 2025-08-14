package com.onfido.android.sdk.capture.audio;

import android.animation.ValueAnimator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroid/animation/ValueAnimator;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
final class ExpandablePillView$volumePillWidthAnimator$2 extends Lambda implements Function0<ValueAnimator> {
    final /* synthetic */ ExpandablePillView this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ExpandablePillView$volumePillWidthAnimator$2(ExpandablePillView expandablePillView) {
        super(0);
        this.this$0 = expandablePillView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invoke$lambda$1$lambda$0(ExpandablePillView this$0, ValueAnimator it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        Intrinsics.checkNotNull(it.getAnimatedValue(), "null cannot be cast to non-null type kotlin.Int");
        ExpandablePillView.setWithOptionals$default(this$0, this$0.volumePillRect, 0.0f, 0.0f, ((Integer) r9).intValue(), 0.0f, 11, null);
        this$0.invalidate();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    public final ValueAnimator invoke() {
        ValueAnimator valueAnimator = new ValueAnimator();
        final ExpandablePillView expandablePillView = this.this$0;
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.onfido.android.sdk.capture.audio.ExpandablePillView$volumePillWidthAnimator$2$$ExternalSyntheticLambda0
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                ExpandablePillView$volumePillWidthAnimator$2.invoke$lambda$1$lambda$0(expandablePillView, valueAnimator2);
            }
        });
        valueAnimator.setDuration(300L);
        return valueAnimator;
    }
}
