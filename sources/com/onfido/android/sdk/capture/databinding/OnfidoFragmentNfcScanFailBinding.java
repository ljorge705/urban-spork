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
import com.onfido.android.sdk.capture.ui.widget.ShadowedScrollView;

/* loaded from: classes2.dex */
public final class OnfidoFragmentNfcScanFailBinding implements ViewBinding {
    public final LinearLayout descriptionContainer;
    public final OnfidoButton primaryAction;
    private final RelativeLayout rootView;
    public final ShadowedScrollView scrollView;
    public final OnfidoButton secondaryAction;
    public final LinearLayout stepsContainer;
    public final TextView title;
    public final PlaybackControlsVideoPlayerView videoView;

    private OnfidoFragmentNfcScanFailBinding(RelativeLayout relativeLayout, LinearLayout linearLayout, OnfidoButton onfidoButton, ShadowedScrollView shadowedScrollView, OnfidoButton onfidoButton2, LinearLayout linearLayout2, TextView textView, PlaybackControlsVideoPlayerView playbackControlsVideoPlayerView) {
        this.rootView = relativeLayout;
        this.descriptionContainer = linearLayout;
        this.primaryAction = onfidoButton;
        this.scrollView = shadowedScrollView;
        this.secondaryAction = onfidoButton2;
        this.stepsContainer = linearLayout2;
        this.title = textView;
        this.videoView = playbackControlsVideoPlayerView;
    }

    public static OnfidoFragmentNfcScanFailBinding bind(View view) {
        int i = R.id.descriptionContainer;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
        if (linearLayout != null) {
            i = R.id.primaryAction;
            OnfidoButton onfidoButton = (OnfidoButton) ViewBindings.findChildViewById(view, i);
            if (onfidoButton != null) {
                i = R.id.scrollView;
                ShadowedScrollView shadowedScrollView = (ShadowedScrollView) ViewBindings.findChildViewById(view, i);
                if (shadowedScrollView != null) {
                    i = R.id.secondaryAction;
                    OnfidoButton onfidoButton2 = (OnfidoButton) ViewBindings.findChildViewById(view, i);
                    if (onfidoButton2 != null) {
                        i = R.id.stepsContainer;
                        LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                        if (linearLayout2 != null) {
                            i = R.id.title;
                            TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                            if (textView != null) {
                                i = R.id.videoView;
                                PlaybackControlsVideoPlayerView playbackControlsVideoPlayerView = (PlaybackControlsVideoPlayerView) ViewBindings.findChildViewById(view, i);
                                if (playbackControlsVideoPlayerView != null) {
                                    return new OnfidoFragmentNfcScanFailBinding((RelativeLayout) view, linearLayout, onfidoButton, shadowedScrollView, onfidoButton2, linearLayout2, textView, playbackControlsVideoPlayerView);
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static OnfidoFragmentNfcScanFailBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static OnfidoFragmentNfcScanFailBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_fragment_nfc_scan_fail, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
