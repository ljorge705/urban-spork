package com.onfido.android.sdk.capture.databinding;

import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.ui.camera.liveness.intro.error.LoadingErrorState;
import com.onfido.android.sdk.capture.ui.widget.LoadingView;

/* loaded from: classes2.dex */
public final class OnfidoAutoPlayerVideoViewBinding implements ViewBinding {
    public final LoadingErrorState loadingErrorState;
    public final LoadingView progressBar;
    public final FrameLayout root;
    private final FrameLayout rootView;
    public final ImageButton videoPlayButton;
    public final TextureView videoPlayer;

    private OnfidoAutoPlayerVideoViewBinding(FrameLayout frameLayout, LoadingErrorState loadingErrorState, LoadingView loadingView, FrameLayout frameLayout2, ImageButton imageButton, TextureView textureView) {
        this.rootView = frameLayout;
        this.loadingErrorState = loadingErrorState;
        this.progressBar = loadingView;
        this.root = frameLayout2;
        this.videoPlayButton = imageButton;
        this.videoPlayer = textureView;
    }

    public static OnfidoAutoPlayerVideoViewBinding bind(View view) {
        int i = R.id.loadingErrorState;
        LoadingErrorState loadingErrorState = (LoadingErrorState) ViewBindings.findChildViewById(view, i);
        if (loadingErrorState != null) {
            i = R.id.progressBar;
            LoadingView loadingView = (LoadingView) ViewBindings.findChildViewById(view, i);
            if (loadingView != null) {
                FrameLayout frameLayout = (FrameLayout) view;
                i = R.id.videoPlayButton;
                ImageButton imageButton = (ImageButton) ViewBindings.findChildViewById(view, i);
                if (imageButton != null) {
                    i = R.id.videoPlayer;
                    TextureView textureView = (TextureView) ViewBindings.findChildViewById(view, i);
                    if (textureView != null) {
                        return new OnfidoAutoPlayerVideoViewBinding(frameLayout, loadingErrorState, loadingView, frameLayout, imageButton, textureView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static OnfidoAutoPlayerVideoViewBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static OnfidoAutoPlayerVideoViewBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_auto_player_video_view, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
