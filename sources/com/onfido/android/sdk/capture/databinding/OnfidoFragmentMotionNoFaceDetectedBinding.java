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
public final class OnfidoFragmentMotionNoFaceDetectedBinding implements ViewBinding {
    public final ImageView noFaceDetectedErrorIcon;
    public final OnfidoButton noFaceDetectedStartRecording;
    private final LinearLayout rootView;

    private OnfidoFragmentMotionNoFaceDetectedBinding(LinearLayout linearLayout, ImageView imageView, OnfidoButton onfidoButton) {
        this.rootView = linearLayout;
        this.noFaceDetectedErrorIcon = imageView;
        this.noFaceDetectedStartRecording = onfidoButton;
    }

    public static OnfidoFragmentMotionNoFaceDetectedBinding bind(View view) {
        int i = R.id.noFaceDetectedErrorIcon;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
        if (imageView != null) {
            i = R.id.noFaceDetectedStartRecording;
            OnfidoButton onfidoButton = (OnfidoButton) ViewBindings.findChildViewById(view, i);
            if (onfidoButton != null) {
                return new OnfidoFragmentMotionNoFaceDetectedBinding((LinearLayout) view, imageView, onfidoButton);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static OnfidoFragmentMotionNoFaceDetectedBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static OnfidoFragmentMotionNoFaceDetectedBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_fragment_motion_no_face_detected, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
