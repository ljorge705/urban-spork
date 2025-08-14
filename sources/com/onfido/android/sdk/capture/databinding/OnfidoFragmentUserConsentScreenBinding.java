package com.onfido.android.sdk.capture.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.ui.widget.OnfidoButton;
import com.onfido.android.sdk.capture.ui.widget.ShadowedScrollView;

/* loaded from: classes2.dex */
public final class OnfidoFragmentUserConsentScreenBinding implements ViewBinding {
    private final LinearLayout rootView;
    public final OnfidoButton userConsentAcceptButton;
    public final OnfidoButton userConsentDoNotAcceptButton;
    public final LinearLayout userConsentFooterContainer;
    public final ShadowedScrollView userConsentScrollView;
    public final TextView userConsentTextView;

    private OnfidoFragmentUserConsentScreenBinding(LinearLayout linearLayout, OnfidoButton onfidoButton, OnfidoButton onfidoButton2, LinearLayout linearLayout2, ShadowedScrollView shadowedScrollView, TextView textView) {
        this.rootView = linearLayout;
        this.userConsentAcceptButton = onfidoButton;
        this.userConsentDoNotAcceptButton = onfidoButton2;
        this.userConsentFooterContainer = linearLayout2;
        this.userConsentScrollView = shadowedScrollView;
        this.userConsentTextView = textView;
    }

    public static OnfidoFragmentUserConsentScreenBinding bind(View view) {
        int i = R.id.userConsentAcceptButton;
        OnfidoButton onfidoButton = (OnfidoButton) ViewBindings.findChildViewById(view, i);
        if (onfidoButton != null) {
            i = R.id.userConsentDoNotAcceptButton;
            OnfidoButton onfidoButton2 = (OnfidoButton) ViewBindings.findChildViewById(view, i);
            if (onfidoButton2 != null) {
                i = R.id.userConsentFooterContainer;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
                if (linearLayout != null) {
                    i = R.id.userConsentScrollView;
                    ShadowedScrollView shadowedScrollView = (ShadowedScrollView) ViewBindings.findChildViewById(view, i);
                    if (shadowedScrollView != null) {
                        i = R.id.userConsentTextView;
                        TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                        if (textView != null) {
                            return new OnfidoFragmentUserConsentScreenBinding((LinearLayout) view, onfidoButton, onfidoButton2, linearLayout, shadowedScrollView, textView);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static OnfidoFragmentUserConsentScreenBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static OnfidoFragmentUserConsentScreenBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_fragment_user_consent_screen, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
