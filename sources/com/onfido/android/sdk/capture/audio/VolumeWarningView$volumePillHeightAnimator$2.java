package com.onfido.android.sdk.capture.audio;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.onfido.android.sdk.capture.utils.ViewExtensionsKt;
import com.onfido.android.sdk.capture.utils.listeners.AnimatorListener;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroid/animation/ValueAnimator;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
final class VolumeWarningView$volumePillHeightAnimator$2 extends Lambda implements Function0<ValueAnimator> {
    final /* synthetic */ VolumeWarningView this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    VolumeWarningView$volumePillHeightAnimator$2(VolumeWarningView volumeWarningView) {
        super(0);
        this.this$0 = volumeWarningView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invoke$lambda$1$lambda$0(VolumeWarningView this$0, ValueAnimator it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        Object animatedValue = it.getAnimatedValue();
        Intrinsics.checkNotNull(animatedValue, "null cannot be cast to non-null type kotlin.Int");
        final int iIntValue = ((Integer) animatedValue).intValue();
        int minHeight = this$0.getMinHeight() - iIntValue;
        ViewGroup.LayoutParams layoutParams = this$0.binding.speaker.getLayoutParams();
        Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type android.widget.RelativeLayout.LayoutParams");
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
        layoutParams2.setMargins(minHeight, minHeight, layoutParams2.rightMargin, layoutParams2.bottomMargin);
        ViewExtensionsKt.changeLayoutParams((ViewGroup) this$0, (Function1<? super ViewGroup.LayoutParams, Unit>) new Function1<ViewGroup.LayoutParams, Unit>() { // from class: com.onfido.android.sdk.capture.audio.VolumeWarningView$volumePillHeightAnimator$2$1$1$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ViewGroup.LayoutParams layoutParams3) {
                invoke2(layoutParams3);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ViewGroup.LayoutParams params) {
                Intrinsics.checkNotNullParameter(params, "params");
                RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) params;
                int i = iIntValue;
                layoutParams3.width = i;
                layoutParams3.height = i;
            }
        });
        this$0.binding.speaker.setLayoutParams(layoutParams2);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    public final ValueAnimator invoke() {
        final ValueAnimator valueAnimator = new ValueAnimator();
        final VolumeWarningView volumeWarningView = this.this$0;
        valueAnimator.setIntValues(volumeWarningView.getMinHeight());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.onfido.android.sdk.capture.audio.VolumeWarningView$volumePillHeightAnimator$2$$ExternalSyntheticLambda0
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                VolumeWarningView$volumePillHeightAnimator$2.invoke$lambda$1$lambda$0(volumeWarningView, valueAnimator2);
            }
        });
        valueAnimator.setDuration(200L);
        valueAnimator.addListener(new AnimatorListener() { // from class: com.onfido.android.sdk.capture.audio.VolumeWarningView$volumePillHeightAnimator$2$1$2
            @Override // com.onfido.android.sdk.capture.utils.listeners.AnimatorListener, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                AnimatorListener.DefaultImpls.onAnimationCancel(this, animator);
            }

            @Override // com.onfido.android.sdk.capture.utils.listeners.AnimatorListener, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
                if (volumeWarningView.hasFinishedForwardAnimation(valueAnimator.getAnimatedFraction())) {
                    ViewGroup.LayoutParams layoutParams = volumeWarningView.getLayoutParams();
                    Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type android.widget.RelativeLayout.LayoutParams");
                    RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
                    VolumeWarningView volumeWarningView2 = volumeWarningView;
                    RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, volumeWarningView.getActualTextHeight());
                    layoutParams3.setMargins(layoutParams2.leftMargin, layoutParams2.topMargin, layoutParams2.rightMargin, layoutParams2.bottomMargin);
                    volumeWarningView2.setLayoutParams(layoutParams3);
                    volumeWarningView.binding.pillView.setInitialShape(volumeWarningView.getMinHeight(), volumeWarningView.getMinHeight(), volumeWarningView.binding.speaker.getWidth() / 2.0f, volumeWarningView.binding.speaker.getHeight() / 2.0f);
                    ExpandablePillView expandablePillView = volumeWarningView.binding.pillView;
                    int iMin = Math.min(volumeWarningView.getTextWidth() + volumeWarningView.getSpeakerSize() + volumeWarningView.getTextHorizontalMargin(), volumeWarningView.getMaxTextWidth());
                    int actualTextHeight = volumeWarningView.getActualTextHeight();
                    final VolumeWarningView volumeWarningView3 = volumeWarningView;
                    expandablePillView.grow(iMin, actualTextHeight, new Function0<Unit>() { // from class: com.onfido.android.sdk.capture.audio.VolumeWarningView$volumePillHeightAnimator$2$1$2$onAnimationEnd$2
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public /* bridge */ /* synthetic */ Unit invoke() {
                            invoke2();
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2() {
                            TextView turnSoundOn = volumeWarningView3.binding.turnSoundOn;
                            Intrinsics.checkNotNullExpressionValue(turnSoundOn, "turnSoundOn");
                            ViewExtensionsKt.animateToAlpha$default(turnSoundOn, 1.0f, 0L, 2, null).start();
                        }
                    });
                }
            }

            @Override // com.onfido.android.sdk.capture.utils.listeners.AnimatorListener, android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
                AnimatorListener.DefaultImpls.onAnimationRepeat(this, animator);
            }

            @Override // com.onfido.android.sdk.capture.utils.listeners.AnimatorListener, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                AnimatorListener.DefaultImpls.onAnimationStart(this, animator);
            }
        });
        return valueAnimator;
    }
}
