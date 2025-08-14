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
import com.onfido.android.sdk.capture.ui.widget.CircularImageView;
import com.onfido.android.sdk.capture.ui.widget.OnfidoButton;
import com.onfido.android.sdk.capture.ui.widget.ShadowedScrollView;

/* loaded from: classes2.dex */
public final class OnfidoFragmentNfcPermissionBinding implements ViewBinding {
    public final LinearLayout descriptionContainer;
    public final OnfidoButton primaryAction;
    private final RelativeLayout rootView;
    public final ShadowedScrollView scrollView;
    public final CircularImageView stepIcon;
    public final TextView stepTitle;
    public final RelativeLayout stepsContainer;
    public final TextView subtitle;
    public final TextView title;

    private OnfidoFragmentNfcPermissionBinding(RelativeLayout relativeLayout, LinearLayout linearLayout, OnfidoButton onfidoButton, ShadowedScrollView shadowedScrollView, CircularImageView circularImageView, TextView textView, RelativeLayout relativeLayout2, TextView textView2, TextView textView3) {
        this.rootView = relativeLayout;
        this.descriptionContainer = linearLayout;
        this.primaryAction = onfidoButton;
        this.scrollView = shadowedScrollView;
        this.stepIcon = circularImageView;
        this.stepTitle = textView;
        this.stepsContainer = relativeLayout2;
        this.subtitle = textView2;
        this.title = textView3;
    }

    public static OnfidoFragmentNfcPermissionBinding bind(View view) {
        int i = R.id.descriptionContainer;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
        if (linearLayout != null) {
            i = R.id.primaryAction;
            OnfidoButton onfidoButton = (OnfidoButton) ViewBindings.findChildViewById(view, i);
            if (onfidoButton != null) {
                i = R.id.scrollView;
                ShadowedScrollView shadowedScrollView = (ShadowedScrollView) ViewBindings.findChildViewById(view, i);
                if (shadowedScrollView != null) {
                    i = R.id.stepIcon;
                    CircularImageView circularImageView = (CircularImageView) ViewBindings.findChildViewById(view, i);
                    if (circularImageView != null) {
                        i = R.id.stepTitle;
                        TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                        if (textView != null) {
                            i = R.id.stepsContainer;
                            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, i);
                            if (relativeLayout != null) {
                                i = R.id.subtitle;
                                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                                if (textView2 != null) {
                                    i = R.id.title;
                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i);
                                    if (textView3 != null) {
                                        return new OnfidoFragmentNfcPermissionBinding((RelativeLayout) view, linearLayout, onfidoButton, shadowedScrollView, circularImageView, textView, relativeLayout, textView2, textView3);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static OnfidoFragmentNfcPermissionBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static OnfidoFragmentNfcPermissionBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_fragment_nfc_permission, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
