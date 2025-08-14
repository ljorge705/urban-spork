package com.onfido.android.sdk.capture.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewbinding.ViewBinding;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.ui.camera.OverlayView;

/* loaded from: classes2.dex */
public final class OnfidoViewOverlayVideoBinding implements ViewBinding {
    private final OverlayView rootView;

    private OnfidoViewOverlayVideoBinding(OverlayView overlayView) {
        this.rootView = overlayView;
    }

    public static OnfidoViewOverlayVideoBinding bind(View view) {
        if (view != null) {
            return new OnfidoViewOverlayVideoBinding((OverlayView) view);
        }
        throw new NullPointerException("rootView");
    }

    public static OnfidoViewOverlayVideoBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public OverlayView getRoot() {
        return this.rootView;
    }

    public static OnfidoViewOverlayVideoBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_view_overlay_video, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
