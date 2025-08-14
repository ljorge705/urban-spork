package com.onfido.android.sdk.capture.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewbinding.ViewBinding;
import com.onfido.android.sdk.capture.R;

/* loaded from: classes2.dex */
public final class OnfidoSeparatorBinding implements ViewBinding {
    private final View rootView;

    private OnfidoSeparatorBinding(View view) {
        this.rootView = view;
    }

    public static OnfidoSeparatorBinding bind(View view) {
        if (view != null) {
            return new OnfidoSeparatorBinding(view);
        }
        throw new NullPointerException("rootView");
    }

    public static OnfidoSeparatorBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public View getRoot() {
        return this.rootView;
    }

    public static OnfidoSeparatorBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_separator, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
