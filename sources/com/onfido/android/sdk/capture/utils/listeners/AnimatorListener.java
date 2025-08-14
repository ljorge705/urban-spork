package com.onfido.android.sdk.capture.utils.listeners;

import android.animation.Animator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b`\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\t"}, d2 = {"Lcom/onfido/android/sdk/capture/utils/listeners/AnimatorListener;", "Landroid/animation/Animator$AnimatorListener;", "onAnimationCancel", "", "animation", "Landroid/animation/Animator;", "onAnimationEnd", "onAnimationRepeat", "onAnimationStart", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface AnimatorListener extends Animator.AnimatorListener {

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class DefaultImpls {
        public static void onAnimationCancel(AnimatorListener animatorListener, Animator animation) {
            Intrinsics.checkNotNullParameter(animation, "animation");
        }

        public static void onAnimationEnd(AnimatorListener animatorListener, Animator animation) {
            Intrinsics.checkNotNullParameter(animation, "animation");
        }

        public static void onAnimationRepeat(AnimatorListener animatorListener, Animator animation) {
            Intrinsics.checkNotNullParameter(animation, "animation");
        }

        public static void onAnimationStart(AnimatorListener animatorListener, Animator animation) {
            Intrinsics.checkNotNullParameter(animation, "animation");
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    void onAnimationCancel(Animator animation);

    @Override // android.animation.Animator.AnimatorListener
    void onAnimationEnd(Animator animation);

    @Override // android.animation.Animator.AnimatorListener
    void onAnimationRepeat(Animator animation);

    @Override // android.animation.Animator.AnimatorListener
    void onAnimationStart(Animator animation);
}
