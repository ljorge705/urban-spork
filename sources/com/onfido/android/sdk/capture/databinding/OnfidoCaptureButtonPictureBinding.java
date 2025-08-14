package com.onfido.android.sdk.capture.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.viewbinding.ViewBinding;
import com.onfido.android.sdk.capture.R;

/* loaded from: classes2.dex */
public final class OnfidoCaptureButtonPictureBinding implements ViewBinding {
    public final ImageView captureButton;
    private final ImageView rootView;

    private OnfidoCaptureButtonPictureBinding(ImageView imageView, ImageView imageView2) {
        this.rootView = imageView;
        this.captureButton = imageView2;
    }

    public static OnfidoCaptureButtonPictureBinding bind(View view) {
        if (view == null) {
            throw new NullPointerException("rootView");
        }
        ImageView imageView = (ImageView) view;
        return new OnfidoCaptureButtonPictureBinding(imageView, imageView);
    }

    public static OnfidoCaptureButtonPictureBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public ImageView getRoot() {
        return this.rootView;
    }

    public static OnfidoCaptureButtonPictureBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_capture_button_picture, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
