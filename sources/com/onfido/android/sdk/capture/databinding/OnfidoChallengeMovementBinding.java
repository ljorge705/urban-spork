package com.onfido.android.sdk.capture.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.ui.camera.liveness.turn.LivenessProgressArrow;
import com.onfido.android.sdk.capture.validation.OnfidoCaptureValidationBubble;

/* loaded from: classes2.dex */
public final class OnfidoChallengeMovementBinding implements ViewBinding {
    public final LivenessProgressArrow arrow;
    public final LinearLayout challenge;
    public final OnfidoCaptureValidationBubble livenessErrorsBubble;
    public final TextView movementTitleFirst;
    public final TextView movementTitleSecond;
    private final RelativeLayout rootView;

    private OnfidoChallengeMovementBinding(RelativeLayout relativeLayout, LivenessProgressArrow livenessProgressArrow, LinearLayout linearLayout, OnfidoCaptureValidationBubble onfidoCaptureValidationBubble, TextView textView, TextView textView2) {
        this.rootView = relativeLayout;
        this.arrow = livenessProgressArrow;
        this.challenge = linearLayout;
        this.livenessErrorsBubble = onfidoCaptureValidationBubble;
        this.movementTitleFirst = textView;
        this.movementTitleSecond = textView2;
    }

    public static OnfidoChallengeMovementBinding bind(View view) {
        int i = R.id.arrow;
        LivenessProgressArrow livenessProgressArrow = (LivenessProgressArrow) ViewBindings.findChildViewById(view, i);
        if (livenessProgressArrow != null) {
            i = R.id.challenge;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
            if (linearLayout != null) {
                i = R.id.livenessErrorsBubble;
                OnfidoCaptureValidationBubble onfidoCaptureValidationBubble = (OnfidoCaptureValidationBubble) ViewBindings.findChildViewById(view, i);
                if (onfidoCaptureValidationBubble != null) {
                    i = R.id.movementTitleFirst;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                    if (textView != null) {
                        i = R.id.movementTitleSecond;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                        if (textView2 != null) {
                            return new OnfidoChallengeMovementBinding((RelativeLayout) view, livenessProgressArrow, linearLayout, onfidoCaptureValidationBubble, textView, textView2);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static OnfidoChallengeMovementBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static OnfidoChallengeMovementBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_challenge_movement, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
