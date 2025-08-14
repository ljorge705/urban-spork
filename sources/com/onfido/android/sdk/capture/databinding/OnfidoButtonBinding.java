package com.onfido.android.sdk.capture.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.appcompat.widget.AppCompatButton;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.onfido.android.sdk.capture.R;

/* loaded from: classes2.dex */
public final class OnfidoButtonBinding implements ViewBinding {
    public final AppCompatButton appCompatButton;
    public final FrameLayout root;
    private final FrameLayout rootView;

    private OnfidoButtonBinding(FrameLayout frameLayout, AppCompatButton appCompatButton, FrameLayout frameLayout2) {
        this.rootView = frameLayout;
        this.appCompatButton = appCompatButton;
        this.root = frameLayout2;
    }

    public static OnfidoButtonBinding bind(View view) {
        int i = R.id.appCompatButton;
        AppCompatButton appCompatButton = (AppCompatButton) ViewBindings.findChildViewById(view, i);
        if (appCompatButton == null) {
            throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
        }
        FrameLayout frameLayout = (FrameLayout) view;
        return new OnfidoButtonBinding(frameLayout, appCompatButton, frameLayout);
    }

    public static OnfidoButtonBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static OnfidoButtonBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_button, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
