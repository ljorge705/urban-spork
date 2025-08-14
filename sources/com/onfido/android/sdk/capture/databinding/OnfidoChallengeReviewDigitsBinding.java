package com.onfido.android.sdk.capture.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.onfido.android.sdk.capture.R;

/* loaded from: classes2.dex */
public final class OnfidoChallengeReviewDigitsBinding implements ViewBinding {
    public final LinearLayout reviewChallenge;
    public final TextView reviewDigitsSubtitle;
    public final TextView reviewDigitsTitle;
    private final LinearLayout rootView;

    private OnfidoChallengeReviewDigitsBinding(LinearLayout linearLayout, LinearLayout linearLayout2, TextView textView, TextView textView2) {
        this.rootView = linearLayout;
        this.reviewChallenge = linearLayout2;
        this.reviewDigitsSubtitle = textView;
        this.reviewDigitsTitle = textView2;
    }

    public static OnfidoChallengeReviewDigitsBinding bind(View view) {
        LinearLayout linearLayout = (LinearLayout) view;
        int i = R.id.reviewDigitsSubtitle;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
        if (textView != null) {
            i = R.id.reviewDigitsTitle;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
            if (textView2 != null) {
                return new OnfidoChallengeReviewDigitsBinding(linearLayout, linearLayout, textView, textView2);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static OnfidoChallengeReviewDigitsBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static OnfidoChallengeReviewDigitsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_challenge_review_digits, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
