package com.onfido.android.sdk.capture.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.headturn.HeadTurnCompletedOvalView;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.headturn.HeadTurnCompletedTickIconView;
import com.onfido.android.sdk.capture.ui.widget.WatermarkView;

/* loaded from: classes2.dex */
public final class OnfidoAvcViewHeadTurnCompletedBinding implements ViewBinding {
    public final HeadTurnCompletedOvalView headTurnCompletedOvalView;
    public final HeadTurnCompletedTickIconView headTurnCompletedTickIconView;
    public final TextView instructionTextView;
    private final FrameLayout rootView;
    public final WatermarkView watermark;

    private OnfidoAvcViewHeadTurnCompletedBinding(FrameLayout frameLayout, HeadTurnCompletedOvalView headTurnCompletedOvalView, HeadTurnCompletedTickIconView headTurnCompletedTickIconView, TextView textView, WatermarkView watermarkView) {
        this.rootView = frameLayout;
        this.headTurnCompletedOvalView = headTurnCompletedOvalView;
        this.headTurnCompletedTickIconView = headTurnCompletedTickIconView;
        this.instructionTextView = textView;
        this.watermark = watermarkView;
    }

    public static OnfidoAvcViewHeadTurnCompletedBinding bind(View view) {
        int i = R.id.headTurnCompletedOvalView;
        HeadTurnCompletedOvalView headTurnCompletedOvalView = (HeadTurnCompletedOvalView) ViewBindings.findChildViewById(view, i);
        if (headTurnCompletedOvalView != null) {
            i = R.id.headTurnCompletedTickIconView;
            HeadTurnCompletedTickIconView headTurnCompletedTickIconView = (HeadTurnCompletedTickIconView) ViewBindings.findChildViewById(view, i);
            if (headTurnCompletedTickIconView != null) {
                i = R.id.instructionTextView;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                if (textView != null) {
                    i = R.id.watermark;
                    WatermarkView watermarkView = (WatermarkView) ViewBindings.findChildViewById(view, i);
                    if (watermarkView != null) {
                        return new OnfidoAvcViewHeadTurnCompletedBinding((FrameLayout) view, headTurnCompletedOvalView, headTurnCompletedTickIconView, textView, watermarkView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static OnfidoAvcViewHeadTurnCompletedBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static OnfidoAvcViewHeadTurnCompletedBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_avc_view_head_turn_completed, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
