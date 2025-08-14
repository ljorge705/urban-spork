package com.onfido.android.sdk.capture.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.ui.widget.WatermarkView;

/* loaded from: classes2.dex */
public final class OnfidoWatermarkLayoutBinding implements ViewBinding {
    private final FrameLayout rootView;
    public final WatermarkView watermark;
    public final FrameLayout watermarkLayout;

    private OnfidoWatermarkLayoutBinding(FrameLayout frameLayout, WatermarkView watermarkView, FrameLayout frameLayout2) {
        this.rootView = frameLayout;
        this.watermark = watermarkView;
        this.watermarkLayout = frameLayout2;
    }

    public static OnfidoWatermarkLayoutBinding bind(View view) {
        int i = R.id.watermark;
        WatermarkView watermarkView = (WatermarkView) ViewBindings.findChildViewById(view, i);
        if (watermarkView == null) {
            throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
        }
        FrameLayout frameLayout = (FrameLayout) view;
        return new OnfidoWatermarkLayoutBinding(frameLayout, watermarkView, frameLayout);
    }

    public static OnfidoWatermarkLayoutBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static OnfidoWatermarkLayoutBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_watermark_layout, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
