package com.onfido.android.sdk.capture.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.ui.widget.CircularImageView;
import com.onfido.android.sdk.capture.ui.widget.OnfidoButton;

/* loaded from: classes2.dex */
public final class OnfidoFragmentFinalBinding implements ViewBinding {
    private final RelativeLayout rootView;
    public final OnfidoButton submit;
    public final TextView subtitle;
    public final TextView title;
    public final CircularImageView videoConfirmationIcon;

    private OnfidoFragmentFinalBinding(RelativeLayout relativeLayout, OnfidoButton onfidoButton, TextView textView, TextView textView2, CircularImageView circularImageView) {
        this.rootView = relativeLayout;
        this.submit = onfidoButton;
        this.subtitle = textView;
        this.title = textView2;
        this.videoConfirmationIcon = circularImageView;
    }

    public static OnfidoFragmentFinalBinding bind(View view) {
        int i = R.id.submit;
        OnfidoButton onfidoButton = (OnfidoButton) ViewBindings.findChildViewById(view, i);
        if (onfidoButton != null) {
            i = R.id.subtitle;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
            if (textView != null) {
                i = R.id.title;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                if (textView2 != null) {
                    i = R.id.videoConfirmationIcon;
                    CircularImageView circularImageView = (CircularImageView) ViewBindings.findChildViewById(view, i);
                    if (circularImageView != null) {
                        return new OnfidoFragmentFinalBinding((RelativeLayout) view, onfidoButton, textView, textView2, circularImageView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static OnfidoFragmentFinalBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static OnfidoFragmentFinalBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_fragment_final, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
