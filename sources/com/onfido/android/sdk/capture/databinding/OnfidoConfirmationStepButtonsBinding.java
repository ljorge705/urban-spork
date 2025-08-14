package com.onfido.android.sdk.capture.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewbinding.ViewBinding;
import com.onfido.android.sdk.capture.R;

/* loaded from: classes2.dex */
public final class OnfidoConfirmationStepButtonsBinding implements ViewBinding {
    private final View rootView;

    private OnfidoConfirmationStepButtonsBinding(View view) {
        this.rootView = view;
    }

    public static OnfidoConfirmationStepButtonsBinding bind(View view) {
        if (view != null) {
            return new OnfidoConfirmationStepButtonsBinding(view);
        }
        throw new NullPointerException("rootView");
    }

    public static OnfidoConfirmationStepButtonsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        if (viewGroup == null) {
            throw new NullPointerException("parent");
        }
        layoutInflater.inflate(R.layout.onfido_confirmation_step_buttons, viewGroup);
        return bind(viewGroup);
    }

    @Override // androidx.viewbinding.ViewBinding
    public View getRoot() {
        return this.rootView;
    }
}
