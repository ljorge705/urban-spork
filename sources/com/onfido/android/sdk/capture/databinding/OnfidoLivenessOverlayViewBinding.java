package com.onfido.android.sdk.capture.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import com.onfido.android.sdk.capture.R;

/* loaded from: classes2.dex */
public final class OnfidoLivenessOverlayViewBinding implements ViewBinding {
    private final RelativeLayout rootView;

    private OnfidoLivenessOverlayViewBinding(RelativeLayout relativeLayout) {
        this.rootView = relativeLayout;
    }

    public static OnfidoLivenessOverlayViewBinding bind(View view) {
        if (view != null) {
            return new OnfidoLivenessOverlayViewBinding((RelativeLayout) view);
        }
        throw new NullPointerException("rootView");
    }

    public static OnfidoLivenessOverlayViewBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static OnfidoLivenessOverlayViewBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_liveness_overlay_view, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
