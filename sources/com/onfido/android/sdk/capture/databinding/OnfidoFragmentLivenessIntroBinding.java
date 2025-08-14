package com.onfido.android.sdk.capture.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.ui.camera.view.PlaybackControlsVideoPlayerView;
import com.onfido.android.sdk.capture.ui.widget.OnfidoButton;

/* loaded from: classes2.dex */
public final class OnfidoFragmentLivenessIntroBinding implements ViewBinding {
    public final LinearLayout descriptionContainer;
    public final OnfidoButton next;
    private final RelativeLayout rootView;
    public final LinearLayout stepsContainer;
    public final TextView subtitle;
    public final TextView title;
    public final PlaybackControlsVideoPlayerView videoView;

    private OnfidoFragmentLivenessIntroBinding(RelativeLayout relativeLayout, LinearLayout linearLayout, OnfidoButton onfidoButton, LinearLayout linearLayout2, TextView textView, TextView textView2, PlaybackControlsVideoPlayerView playbackControlsVideoPlayerView) {
        this.rootView = relativeLayout;
        this.descriptionContainer = linearLayout;
        this.next = onfidoButton;
        this.stepsContainer = linearLayout2;
        this.subtitle = textView;
        this.title = textView2;
        this.videoView = playbackControlsVideoPlayerView;
    }

    public static OnfidoFragmentLivenessIntroBinding bind(View view) {
        int i = R.id.descriptionContainer;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
        if (linearLayout != null) {
            i = R.id.next;
            OnfidoButton onfidoButton = (OnfidoButton) ViewBindings.findChildViewById(view, i);
            if (onfidoButton != null) {
                i = R.id.stepsContainer;
                LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                if (linearLayout2 != null) {
                    i = R.id.subtitle;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                    if (textView != null) {
                        i = R.id.title;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                        if (textView2 != null) {
                            i = R.id.videoView;
                            PlaybackControlsVideoPlayerView playbackControlsVideoPlayerView = (PlaybackControlsVideoPlayerView) ViewBindings.findChildViewById(view, i);
                            if (playbackControlsVideoPlayerView != null) {
                                return new OnfidoFragmentLivenessIntroBinding((RelativeLayout) view, linearLayout, onfidoButton, linearLayout2, textView, textView2, playbackControlsVideoPlayerView);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static OnfidoFragmentLivenessIntroBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static OnfidoFragmentLivenessIntroBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_fragment_liveness_intro, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
