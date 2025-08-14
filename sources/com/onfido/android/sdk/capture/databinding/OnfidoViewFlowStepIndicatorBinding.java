package com.onfido.android.sdk.capture.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.onfido.android.sdk.capture.R;

/* loaded from: classes2.dex */
public final class OnfidoViewFlowStepIndicatorBinding implements ViewBinding {
    private final FrameLayout rootView;
    public final FrameLayout stepsIndicatorBackground;
    public final TextView stepsIndicatorText;

    private OnfidoViewFlowStepIndicatorBinding(FrameLayout frameLayout, FrameLayout frameLayout2, TextView textView) {
        this.rootView = frameLayout;
        this.stepsIndicatorBackground = frameLayout2;
        this.stepsIndicatorText = textView;
    }

    public static OnfidoViewFlowStepIndicatorBinding bind(View view) {
        FrameLayout frameLayout = (FrameLayout) view;
        int i = R.id.stepsIndicatorText;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
        if (textView != null) {
            return new OnfidoViewFlowStepIndicatorBinding(frameLayout, frameLayout, textView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static OnfidoViewFlowStepIndicatorBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static OnfidoViewFlowStepIndicatorBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_view_flow_step_indicator, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
