package com.onfido.android.sdk.capture.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.viewbinding.ViewBinding;
import com.onfido.android.sdk.capture.R;

/* loaded from: classes2.dex */
public final class OnfidoMotionFragmentUploadBinding implements ViewBinding {
    private final FrameLayout rootView;

    private OnfidoMotionFragmentUploadBinding(FrameLayout frameLayout) {
        this.rootView = frameLayout;
    }

    public static OnfidoMotionFragmentUploadBinding bind(View view) {
        if (view != null) {
            return new OnfidoMotionFragmentUploadBinding((FrameLayout) view);
        }
        throw new NullPointerException("rootView");
    }

    public static OnfidoMotionFragmentUploadBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static OnfidoMotionFragmentUploadBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_motion_fragment_upload, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
