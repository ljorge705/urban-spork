package com.onfido.android.sdk.capture.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewbinding.ViewBinding;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.ui.camera.OverlayView;

/* loaded from: classes2.dex */
public final class OnfidoViewOverlayFrenchDlBinding implements ViewBinding {
    private final OverlayView rootView;

    private OnfidoViewOverlayFrenchDlBinding(OverlayView overlayView) {
        this.rootView = overlayView;
    }

    public static OnfidoViewOverlayFrenchDlBinding bind(View view) {
        if (view != null) {
            return new OnfidoViewOverlayFrenchDlBinding((OverlayView) view);
        }
        throw new NullPointerException("rootView");
    }

    public static OnfidoViewOverlayFrenchDlBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public OverlayView getRoot() {
        return this.rootView;
    }

    public static OnfidoViewOverlayFrenchDlBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_view_overlay_french_dl, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
