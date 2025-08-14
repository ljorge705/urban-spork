package com.onfido.android.sdk.capture.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewbinding.ViewBinding;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.ui.camera.OverlayView;

/* loaded from: classes2.dex */
public final class OnfidoViewOverlayPoaA4pageBinding implements ViewBinding {
    private final OverlayView rootView;

    private OnfidoViewOverlayPoaA4pageBinding(OverlayView overlayView) {
        this.rootView = overlayView;
    }

    public static OnfidoViewOverlayPoaA4pageBinding bind(View view) {
        if (view != null) {
            return new OnfidoViewOverlayPoaA4pageBinding((OverlayView) view);
        }
        throw new NullPointerException("rootView");
    }

    public static OnfidoViewOverlayPoaA4pageBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public OverlayView getRoot() {
        return this.rootView;
    }

    public static OnfidoViewOverlayPoaA4pageBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_view_overlay_poa_a4page, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
