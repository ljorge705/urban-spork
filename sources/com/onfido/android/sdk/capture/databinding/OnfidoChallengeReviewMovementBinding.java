package com.onfido.android.sdk.capture.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.onfido.android.sdk.capture.R;

/* loaded from: classes2.dex */
public final class OnfidoChallengeReviewMovementBinding implements ViewBinding {
    public final ImageView reviewArrow;
    public final LinearLayout reviewChallenge;
    public final TextView reviewMovementTitle;
    private final LinearLayout rootView;

    private OnfidoChallengeReviewMovementBinding(LinearLayout linearLayout, ImageView imageView, LinearLayout linearLayout2, TextView textView) {
        this.rootView = linearLayout;
        this.reviewArrow = imageView;
        this.reviewChallenge = linearLayout2;
        this.reviewMovementTitle = textView;
    }

    public static OnfidoChallengeReviewMovementBinding bind(View view) {
        int i = R.id.reviewArrow;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
        if (imageView != null) {
            LinearLayout linearLayout = (LinearLayout) view;
            int i2 = R.id.reviewMovementTitle;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, i2);
            if (textView != null) {
                return new OnfidoChallengeReviewMovementBinding(linearLayout, imageView, linearLayout, textView);
            }
            i = i2;
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static OnfidoChallengeReviewMovementBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static OnfidoChallengeReviewMovementBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_challenge_review_movement, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
