package com.onfido.android.sdk.capture.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.ui.widget.OnfidoButton;

/* loaded from: classes2.dex */
public final class OnfidoLivenessChallengesLoadingErrorBinding implements ViewBinding {
    public final OnfidoButton reloadButton;
    private final RelativeLayout rootView;
    public final Toolbar toolbar;

    private OnfidoLivenessChallengesLoadingErrorBinding(RelativeLayout relativeLayout, OnfidoButton onfidoButton, Toolbar toolbar) {
        this.rootView = relativeLayout;
        this.reloadButton = onfidoButton;
        this.toolbar = toolbar;
    }

    public static OnfidoLivenessChallengesLoadingErrorBinding bind(View view) {
        int i = R.id.reloadButton;
        OnfidoButton onfidoButton = (OnfidoButton) ViewBindings.findChildViewById(view, i);
        if (onfidoButton != null) {
            i = R.id.toolbar;
            Toolbar toolbar = (Toolbar) ViewBindings.findChildViewById(view, i);
            if (toolbar != null) {
                return new OnfidoLivenessChallengesLoadingErrorBinding((RelativeLayout) view, onfidoButton, toolbar);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static OnfidoLivenessChallengesLoadingErrorBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static OnfidoLivenessChallengesLoadingErrorBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_liveness_challenges_loading_error, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
