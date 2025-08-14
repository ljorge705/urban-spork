package com.onfido.android.sdk.capture.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewbinding.ViewBinding;
import com.onfido.android.sdk.capture.R;

/* loaded from: classes2.dex */
public final class OnfidoDummyAccessibilityViewBinding implements ViewBinding {
    public final View dummyAccessibility;
    private final View rootView;

    private OnfidoDummyAccessibilityViewBinding(View view, View view2) {
        this.rootView = view;
        this.dummyAccessibility = view2;
    }

    public static OnfidoDummyAccessibilityViewBinding bind(View view) {
        if (view != null) {
            return new OnfidoDummyAccessibilityViewBinding(view, view);
        }
        throw new NullPointerException("rootView");
    }

    public static OnfidoDummyAccessibilityViewBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public View getRoot() {
        return this.rootView;
    }

    public static OnfidoDummyAccessibilityViewBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_dummy_accessibility_view, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
