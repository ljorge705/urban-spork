package com.onfido.android.sdk.capture.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewbinding.ViewBinding;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.ui.widget.OnfidoButton;

/* loaded from: classes2.dex */
public final class OnfidoCaptureButtonVideoBinding implements ViewBinding {
    public final OnfidoButton livenessControlButton;
    private final OnfidoButton rootView;

    private OnfidoCaptureButtonVideoBinding(OnfidoButton onfidoButton, OnfidoButton onfidoButton2) {
        this.rootView = onfidoButton;
        this.livenessControlButton = onfidoButton2;
    }

    public static OnfidoCaptureButtonVideoBinding bind(View view) {
        if (view == null) {
            throw new NullPointerException("rootView");
        }
        OnfidoButton onfidoButton = (OnfidoButton) view;
        return new OnfidoCaptureButtonVideoBinding(onfidoButton, onfidoButton);
    }

    public static OnfidoCaptureButtonVideoBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public OnfidoButton getRoot() {
        return this.rootView;
    }

    public static OnfidoCaptureButtonVideoBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_capture_button_video, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
