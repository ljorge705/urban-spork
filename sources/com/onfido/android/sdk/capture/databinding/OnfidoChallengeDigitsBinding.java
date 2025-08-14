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

/* loaded from: classes2.dex */
public final class OnfidoChallengeDigitsBinding implements ViewBinding {
    public final LinearLayout challenge;
    public final RelativeLayout challengeContainer;
    private final RelativeLayout rootView;
    public final TextView subtitle;
    public final TextView title;

    private OnfidoChallengeDigitsBinding(RelativeLayout relativeLayout, LinearLayout linearLayout, RelativeLayout relativeLayout2, TextView textView, TextView textView2) {
        this.rootView = relativeLayout;
        this.challenge = linearLayout;
        this.challengeContainer = relativeLayout2;
        this.subtitle = textView;
        this.title = textView2;
    }

    public static OnfidoChallengeDigitsBinding bind(View view) {
        int i = R.id.challenge;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
        if (linearLayout != null) {
            RelativeLayout relativeLayout = (RelativeLayout) view;
            i = R.id.subtitle;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
            if (textView != null) {
                i = R.id.title;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                if (textView2 != null) {
                    return new OnfidoChallengeDigitsBinding(relativeLayout, linearLayout, relativeLayout, textView, textView2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static OnfidoChallengeDigitsBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static OnfidoChallengeDigitsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_challenge_digits, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
