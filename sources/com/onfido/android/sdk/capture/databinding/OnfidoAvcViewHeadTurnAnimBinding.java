package com.onfido.android.sdk.capture.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.headturn.OnfidoAnimWebView;

/* loaded from: classes2.dex */
public final class OnfidoAvcViewHeadTurnAnimBinding implements ViewBinding {
    public final OnfidoAnimWebView animWebView;
    private final FrameLayout rootView;

    private OnfidoAvcViewHeadTurnAnimBinding(FrameLayout frameLayout, OnfidoAnimWebView onfidoAnimWebView) {
        this.rootView = frameLayout;
        this.animWebView = onfidoAnimWebView;
    }

    public static OnfidoAvcViewHeadTurnAnimBinding bind(View view) {
        int i = R.id.animWebView;
        OnfidoAnimWebView onfidoAnimWebView = (OnfidoAnimWebView) ViewBindings.findChildViewById(view, i);
        if (onfidoAnimWebView != null) {
            return new OnfidoAvcViewHeadTurnAnimBinding((FrameLayout) view, onfidoAnimWebView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static OnfidoAvcViewHeadTurnAnimBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static OnfidoAvcViewHeadTurnAnimBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_avc_view_head_turn_anim, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
