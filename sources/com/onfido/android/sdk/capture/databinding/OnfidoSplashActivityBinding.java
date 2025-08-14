package com.onfido.android.sdk.capture.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.onfido.android.sdk.capture.R;

/* loaded from: classes2.dex */
public final class OnfidoSplashActivityBinding implements ViewBinding {
    public final RelativeLayout loadingView;
    private final RelativeLayout rootView;
    public final OnfidoProgressLayoutBinding spinner;

    private OnfidoSplashActivityBinding(RelativeLayout relativeLayout, RelativeLayout relativeLayout2, OnfidoProgressLayoutBinding onfidoProgressLayoutBinding) {
        this.rootView = relativeLayout;
        this.loadingView = relativeLayout2;
        this.spinner = onfidoProgressLayoutBinding;
    }

    public static OnfidoSplashActivityBinding bind(View view) {
        RelativeLayout relativeLayout = (RelativeLayout) view;
        int i = R.id.spinner;
        View viewFindChildViewById = ViewBindings.findChildViewById(view, i);
        if (viewFindChildViewById != null) {
            return new OnfidoSplashActivityBinding(relativeLayout, relativeLayout, OnfidoProgressLayoutBinding.bind(viewFindChildViewById));
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static OnfidoSplashActivityBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static OnfidoSplashActivityBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_splash_activity, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
