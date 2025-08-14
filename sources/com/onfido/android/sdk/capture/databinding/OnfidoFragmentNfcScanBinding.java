package com.onfido.android.sdk.capture.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.ui.camera.view.PlaybackControlsVideoPlayerView;
import com.onfido.android.sdk.capture.ui.widget.OnfidoButton;

/* loaded from: classes2.dex */
public final class OnfidoFragmentNfcScanBinding implements ViewBinding {
    public final OnfidoButton btnStartScanning;
    public final OnfidoWatermarkLayoutBinding onfidoInclude3;
    private final LinearLayout rootView;
    public final LinearLayout stepsContainer;
    public final TextView title;
    public final TextView tvCanDisclaimer;
    public final PlaybackControlsVideoPlayerView videoView;

    private OnfidoFragmentNfcScanBinding(LinearLayout linearLayout, OnfidoButton onfidoButton, OnfidoWatermarkLayoutBinding onfidoWatermarkLayoutBinding, LinearLayout linearLayout2, TextView textView, TextView textView2, PlaybackControlsVideoPlayerView playbackControlsVideoPlayerView) {
        this.rootView = linearLayout;
        this.btnStartScanning = onfidoButton;
        this.onfidoInclude3 = onfidoWatermarkLayoutBinding;
        this.stepsContainer = linearLayout2;
        this.title = textView;
        this.tvCanDisclaimer = textView2;
        this.videoView = playbackControlsVideoPlayerView;
    }

    public static OnfidoFragmentNfcScanBinding bind(View view) {
        View viewFindChildViewById;
        int i = R.id.btnStartScanning;
        OnfidoButton onfidoButton = (OnfidoButton) ViewBindings.findChildViewById(view, i);
        if (onfidoButton != null && (viewFindChildViewById = ViewBindings.findChildViewById(view, (i = R.id.onfidoInclude3))) != null) {
            OnfidoWatermarkLayoutBinding onfidoWatermarkLayoutBindingBind = OnfidoWatermarkLayoutBinding.bind(viewFindChildViewById);
            i = R.id.stepsContainer;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
            if (linearLayout != null) {
                i = R.id.title;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                if (textView != null) {
                    i = R.id.tvCanDisclaimer;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                    if (textView2 != null) {
                        i = R.id.videoView;
                        PlaybackControlsVideoPlayerView playbackControlsVideoPlayerView = (PlaybackControlsVideoPlayerView) ViewBindings.findChildViewById(view, i);
                        if (playbackControlsVideoPlayerView != null) {
                            return new OnfidoFragmentNfcScanBinding((LinearLayout) view, onfidoButton, onfidoWatermarkLayoutBindingBind, linearLayout, textView, textView2, playbackControlsVideoPlayerView);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static OnfidoFragmentNfcScanBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static OnfidoFragmentNfcScanBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_fragment_nfc_scan, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
