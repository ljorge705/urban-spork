package com.onfido.android.sdk.capture.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.facealignment.FaceAlignmentFrameView;
import com.onfido.android.sdk.capture.ui.widget.WatermarkView;

/* loaded from: classes2.dex */
public final class OnfidoAvcViewFaceAlignmentBinding implements ViewBinding {
    public final FaceAlignmentFrameView faceAlignmentViewFrameView;
    public final TextView faceAlignmentViewInstruction;
    private final View rootView;
    public final WatermarkView watermark;

    private OnfidoAvcViewFaceAlignmentBinding(View view, FaceAlignmentFrameView faceAlignmentFrameView, TextView textView, WatermarkView watermarkView) {
        this.rootView = view;
        this.faceAlignmentViewFrameView = faceAlignmentFrameView;
        this.faceAlignmentViewInstruction = textView;
        this.watermark = watermarkView;
    }

    public static OnfidoAvcViewFaceAlignmentBinding bind(View view) {
        int i = R.id.faceAlignmentViewFrameView;
        FaceAlignmentFrameView faceAlignmentFrameView = (FaceAlignmentFrameView) ViewBindings.findChildViewById(view, i);
        if (faceAlignmentFrameView != null) {
            i = R.id.faceAlignmentViewInstruction;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
            if (textView != null) {
                i = R.id.watermark;
                WatermarkView watermarkView = (WatermarkView) ViewBindings.findChildViewById(view, i);
                if (watermarkView != null) {
                    return new OnfidoAvcViewFaceAlignmentBinding(view, faceAlignmentFrameView, textView, watermarkView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static OnfidoAvcViewFaceAlignmentBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        if (viewGroup == null) {
            throw new NullPointerException("parent");
        }
        layoutInflater.inflate(R.layout.onfido_avc_view_face_alignment, viewGroup);
        return bind(viewGroup);
    }

    @Override // androidx.viewbinding.ViewBinding
    public View getRoot() {
        return this.rootView;
    }
}
