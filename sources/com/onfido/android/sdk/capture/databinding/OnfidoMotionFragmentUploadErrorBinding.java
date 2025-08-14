package com.onfido.android.sdk.capture.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.ui.widget.OnfidoButton;

/* loaded from: classes2.dex */
public final class OnfidoMotionFragmentUploadErrorBinding implements ViewBinding {
    public final ImageView motionUploadErrorIcon;
    public final OnfidoButton restartRecordingButton;
    public final OnfidoButton retryUploadButton;
    private final LinearLayout rootView;

    private OnfidoMotionFragmentUploadErrorBinding(LinearLayout linearLayout, ImageView imageView, OnfidoButton onfidoButton, OnfidoButton onfidoButton2) {
        this.rootView = linearLayout;
        this.motionUploadErrorIcon = imageView;
        this.restartRecordingButton = onfidoButton;
        this.retryUploadButton = onfidoButton2;
    }

    public static OnfidoMotionFragmentUploadErrorBinding bind(View view) {
        int i = R.id.motionUploadErrorIcon;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
        if (imageView != null) {
            i = R.id.restartRecordingButton;
            OnfidoButton onfidoButton = (OnfidoButton) ViewBindings.findChildViewById(view, i);
            if (onfidoButton != null) {
                i = R.id.retryUploadButton;
                OnfidoButton onfidoButton2 = (OnfidoButton) ViewBindings.findChildViewById(view, i);
                if (onfidoButton2 != null) {
                    return new OnfidoMotionFragmentUploadErrorBinding((LinearLayout) view, imageView, onfidoButton, onfidoButton2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static OnfidoMotionFragmentUploadErrorBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static OnfidoMotionFragmentUploadErrorBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_motion_fragment_upload_error, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
