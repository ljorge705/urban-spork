package com.onfido.android.sdk.capture.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.audio.VolumeWarningView;
import com.onfido.android.sdk.capture.ui.camera.liveness.video_view.VideoPlayerView;
import com.onfido.android.sdk.capture.ui.widget.CircularImageView;
import com.onfido.android.sdk.capture.ui.widget.OnfidoButton;

/* loaded from: classes2.dex */
public final class OnfidoFragmentLivenessConfirmationBinding implements ViewBinding {
    public final FrameLayout challengesContainer;
    public final OnfidoButton firstAction;
    private final RelativeLayout rootView;
    public final OnfidoButton secondAction;
    public final CircularImageView videoConfirmationIcon;
    public final TextView videoConfirmationText;
    public final FrameLayout videoConfirmationTextContainer;
    public final ConstraintLayout videoContainer;
    public final VideoPlayerView videoPlayerView;
    public final VolumeWarningView volumeView;

    private OnfidoFragmentLivenessConfirmationBinding(RelativeLayout relativeLayout, FrameLayout frameLayout, OnfidoButton onfidoButton, OnfidoButton onfidoButton2, CircularImageView circularImageView, TextView textView, FrameLayout frameLayout2, ConstraintLayout constraintLayout, VideoPlayerView videoPlayerView, VolumeWarningView volumeWarningView) {
        this.rootView = relativeLayout;
        this.challengesContainer = frameLayout;
        this.firstAction = onfidoButton;
        this.secondAction = onfidoButton2;
        this.videoConfirmationIcon = circularImageView;
        this.videoConfirmationText = textView;
        this.videoConfirmationTextContainer = frameLayout2;
        this.videoContainer = constraintLayout;
        this.videoPlayerView = videoPlayerView;
        this.volumeView = volumeWarningView;
    }

    public static OnfidoFragmentLivenessConfirmationBinding bind(View view) {
        int i = R.id.challengesContainer;
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, i);
        if (frameLayout != null) {
            i = R.id.firstAction;
            OnfidoButton onfidoButton = (OnfidoButton) ViewBindings.findChildViewById(view, i);
            if (onfidoButton != null) {
                i = R.id.secondAction;
                OnfidoButton onfidoButton2 = (OnfidoButton) ViewBindings.findChildViewById(view, i);
                if (onfidoButton2 != null) {
                    i = R.id.videoConfirmationIcon;
                    CircularImageView circularImageView = (CircularImageView) ViewBindings.findChildViewById(view, i);
                    if (circularImageView != null) {
                        i = R.id.videoConfirmationText;
                        TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                        if (textView != null) {
                            i = R.id.videoConfirmationTextContainer;
                            FrameLayout frameLayout2 = (FrameLayout) ViewBindings.findChildViewById(view, i);
                            if (frameLayout2 != null) {
                                i = R.id.videoContainer;
                                ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, i);
                                if (constraintLayout != null) {
                                    i = R.id.videoPlayerView;
                                    VideoPlayerView videoPlayerView = (VideoPlayerView) ViewBindings.findChildViewById(view, i);
                                    if (videoPlayerView != null) {
                                        i = R.id.volumeView;
                                        VolumeWarningView volumeWarningView = (VolumeWarningView) ViewBindings.findChildViewById(view, i);
                                        if (volumeWarningView != null) {
                                            return new OnfidoFragmentLivenessConfirmationBinding((RelativeLayout) view, frameLayout, onfidoButton, onfidoButton2, circularImageView, textView, frameLayout2, constraintLayout, videoPlayerView, volumeWarningView);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static OnfidoFragmentLivenessConfirmationBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static OnfidoFragmentLivenessConfirmationBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_fragment_liveness_confirmation, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
