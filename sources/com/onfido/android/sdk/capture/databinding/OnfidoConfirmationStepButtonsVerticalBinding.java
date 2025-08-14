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
public final class OnfidoConfirmationStepButtonsVerticalBinding implements ViewBinding {
    public final OnfidoButton buttonPrimary;
    public final OnfidoButton buttonSecondary;
    private final LinearLayout rootView;
    public final LinearLayout verticalContainer;

    private OnfidoConfirmationStepButtonsVerticalBinding(LinearLayout linearLayout, OnfidoButton onfidoButton, OnfidoButton onfidoButton2, LinearLayout linearLayout2) {
        this.rootView = linearLayout;
        this.buttonPrimary = onfidoButton;
        this.buttonSecondary = onfidoButton2;
        this.verticalContainer = linearLayout2;
    }

    public static OnfidoConfirmationStepButtonsVerticalBinding bind(View view) {
        int i = R.id.button_primary;
        OnfidoButton onfidoButton = (OnfidoButton) ViewBindings.findChildViewById(view, i);
        if (onfidoButton != null) {
            i = R.id.button_secondary;
            OnfidoButton onfidoButton2 = (OnfidoButton) ViewBindings.findChildViewById(view, i);
            if (onfidoButton2 != null) {
                LinearLayout linearLayout = (LinearLayout) view;
                return new OnfidoConfirmationStepButtonsVerticalBinding(linearLayout, onfidoButton, onfidoButton2, linearLayout);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static OnfidoConfirmationStepButtonsVerticalBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static OnfidoConfirmationStepButtonsVerticalBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_confirmation_step_buttons_vertical, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
