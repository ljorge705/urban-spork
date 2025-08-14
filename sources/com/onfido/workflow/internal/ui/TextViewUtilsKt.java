package com.onfido.workflow.internal.ui;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TextViewUtils.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u001a\u0016\u0010\u0002\u001a\u00020\u0003*\u00020\u00042\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"ANIMATION_DURATION", "", "setTextWithAnimation", "", "Landroid/widget/TextView;", "resId", "", "onfido-workflow_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class TextViewUtilsKt {
    private static final long ANIMATION_DURATION = 200;

    public static final void setTextWithAnimation(final TextView textView, final int i) {
        Intrinsics.checkNotNullParameter(textView, "<this>");
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(200L);
        alphaAnimation.setRepeatCount(1);
        alphaAnimation.setRepeatMode(2);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.onfido.workflow.internal.ui.TextViewUtilsKt.setTextWithAnimation.1
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                TextView textView2 = textView;
                textView2.setText(textView2.getContext().getString(i));
            }
        });
        textView.startAnimation(alphaAnimation);
    }
}
