package com.onfido.android.sdk.capture.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.headturn.HeadTurnGuidanceVideoView;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.headturn.HeadTurnProgressView;
import com.onfido.android.sdk.capture.ui.widget.WatermarkView;

/* loaded from: classes2.dex */
public final class OnfidoAvcViewHeadTurnGuidanceBinding implements ViewBinding {
    public final HeadTurnGuidanceVideoView headTurnAnimView;
    public final HeadTurnProgressView headTurnProgressView;
    public final TextView instructionTextView;
    private final FrameLayout rootView;
    public final WatermarkView watermark;

    private OnfidoAvcViewHeadTurnGuidanceBinding(FrameLayout frameLayout, HeadTurnGuidanceVideoView headTurnGuidanceVideoView, HeadTurnProgressView headTurnProgressView, TextView textView, WatermarkView watermarkView) {
        this.rootView = frameLayout;
        this.headTurnAnimView = headTurnGuidanceVideoView;
        this.headTurnProgressView = headTurnProgressView;
        this.instructionTextView = textView;
        this.watermark = watermarkView;
    }

    public static OnfidoAvcViewHeadTurnGuidanceBinding bind(View view) {
        int i = R.id.headTurnAnimView;
        HeadTurnGuidanceVideoView headTurnGuidanceVideoView = (HeadTurnGuidanceVideoView) ViewBindings.findChildViewById(view, i);
        if (headTurnGuidanceVideoView != null) {
            i = R.id.headTurnProgressView;
            HeadTurnProgressView headTurnProgressView = (HeadTurnProgressView) ViewBindings.findChildViewById(view, i);
            if (headTurnProgressView != null) {
                i = R.id.instructionTextView;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                if (textView != null) {
                    i = R.id.watermark;
                    WatermarkView watermarkView = (WatermarkView) ViewBindings.findChildViewById(view, i);
                    if (watermarkView != null) {
                        return new OnfidoAvcViewHeadTurnGuidanceBinding((FrameLayout) view, headTurnGuidanceVideoView, headTurnProgressView, textView, watermarkView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static OnfidoAvcViewHeadTurnGuidanceBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static OnfidoAvcViewHeadTurnGuidanceBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_avc_view_head_turn_guidance, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
