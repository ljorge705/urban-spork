package com.onfido.android.sdk.capture.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.ui.widget.OnfidoButton;

/* loaded from: classes2.dex */
public final class OnfidoConfirmationStepButtonsHorizontalBinding implements ViewBinding {
    public final OnfidoButton buttonPrimary;
    public final OnfidoButton buttonSecondary;
    private final LinearLayout rootView;

    private OnfidoConfirmationStepButtonsHorizontalBinding(LinearLayout linearLayout, OnfidoButton onfidoButton, OnfidoButton onfidoButton2) {
        this.rootView = linearLayout;
        this.buttonPrimary = onfidoButton;
        this.buttonSecondary = onfidoButton2;
    }

    public static OnfidoConfirmationStepButtonsHorizontalBinding bind(View view) {
        int i = R.id.button_primary;
        OnfidoButton onfidoButton = (OnfidoButton) ViewBindings.findChildViewById(view, i);
        if (onfidoButton != null) {
            i = R.id.button_secondary;
            OnfidoButton onfidoButton2 = (OnfidoButton) ViewBindings.findChildViewById(view, i);
            if (onfidoButton2 != null) {
                return new OnfidoConfirmationStepButtonsHorizontalBinding((LinearLayout) view, onfidoButton, onfidoButton2);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static OnfidoConfirmationStepButtonsHorizontalBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static OnfidoConfirmationStepButtonsHorizontalBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_confirmation_step_buttons_horizontal, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
