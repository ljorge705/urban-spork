package com.onfido.android.sdk.capture.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.onfido.android.sdk.capture.R;

/* loaded from: classes2.dex */
public final class OnfidoOverlayViewBinding implements ViewBinding {
    private final FrameLayout rootView;
    public final FrameLayout tickContainer;
    public final ImageView tickIcon;

    private OnfidoOverlayViewBinding(FrameLayout frameLayout, FrameLayout frameLayout2, ImageView imageView) {
        this.rootView = frameLayout;
        this.tickContainer = frameLayout2;
        this.tickIcon = imageView;
    }

    public static OnfidoOverlayViewBinding bind(View view) {
        FrameLayout frameLayout = (FrameLayout) view;
        int i = R.id.tickIcon;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
        if (imageView != null) {
            return new OnfidoOverlayViewBinding(frameLayout, frameLayout, imageView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static OnfidoOverlayViewBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static OnfidoOverlayViewBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_overlay_view, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
