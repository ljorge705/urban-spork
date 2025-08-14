package com.onfido.android.sdk.capture.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.ui.camera.liveness.challenges.loading.LivenessChallengesLoadingErrorView;
import com.onfido.android.sdk.capture.ui.widget.LoadingView;

/* loaded from: classes2.dex */
public final class OnfidoLivenessChallengesLoadingBinding implements ViewBinding {
    public final LivenessChallengesLoadingErrorView errorView;
    public final LoadingView loadingView;
    private final RelativeLayout rootView;

    private OnfidoLivenessChallengesLoadingBinding(RelativeLayout relativeLayout, LivenessChallengesLoadingErrorView livenessChallengesLoadingErrorView, LoadingView loadingView) {
        this.rootView = relativeLayout;
        this.errorView = livenessChallengesLoadingErrorView;
        this.loadingView = loadingView;
    }

    public static OnfidoLivenessChallengesLoadingBinding bind(View view) {
        int i = R.id.errorView;
        LivenessChallengesLoadingErrorView livenessChallengesLoadingErrorView = (LivenessChallengesLoadingErrorView) ViewBindings.findChildViewById(view, i);
        if (livenessChallengesLoadingErrorView != null) {
            i = R.id.loadingView;
            LoadingView loadingView = (LoadingView) ViewBindings.findChildViewById(view, i);
            if (loadingView != null) {
                return new OnfidoLivenessChallengesLoadingBinding((RelativeLayout) view, livenessChallengesLoadingErrorView, loadingView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static OnfidoLivenessChallengesLoadingBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static OnfidoLivenessChallengesLoadingBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_liveness_challenges_loading, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
