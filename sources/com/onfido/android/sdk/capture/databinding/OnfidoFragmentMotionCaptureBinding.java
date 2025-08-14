package com.onfido.android.sdk.capture.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.camera.view.PreviewView;
import androidx.camera.viewfinder.CameraViewfinder;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.facealignment.FaceAlignmentView;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.facealignment.FeedbackLabelView;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.headturn.HeadTurnCompletedView;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.headturn.HeadTurnGuidanceView;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.util.RectDebuggingView;

/* loaded from: classes2.dex */
public final class OnfidoFragmentMotionCaptureBinding implements ViewBinding {
    public final Toolbar avcCaptureToolbar;
    public final CameraViewfinder camera2PreviewView;
    public final PreviewView cameraXPreviewView;
    public final FaceAlignmentView faceAlignmentView;
    public final RectDebuggingView faceRectDebuggingView;
    public final TextView faceYawAngleDebuggingView;
    public final FeedbackLabelView feedbackLabelView;
    public final HeadTurnCompletedView headTurnCompletedView;
    public final HeadTurnGuidanceView headTurnGuidanceView;
    public final FrameLayout previewContainer;
    private final FrameLayout rootView;

    private OnfidoFragmentMotionCaptureBinding(FrameLayout frameLayout, Toolbar toolbar, CameraViewfinder cameraViewfinder, PreviewView previewView, FaceAlignmentView faceAlignmentView, RectDebuggingView rectDebuggingView, TextView textView, FeedbackLabelView feedbackLabelView, HeadTurnCompletedView headTurnCompletedView, HeadTurnGuidanceView headTurnGuidanceView, FrameLayout frameLayout2) {
        this.rootView = frameLayout;
        this.avcCaptureToolbar = toolbar;
        this.camera2PreviewView = cameraViewfinder;
        this.cameraXPreviewView = previewView;
        this.faceAlignmentView = faceAlignmentView;
        this.faceRectDebuggingView = rectDebuggingView;
        this.faceYawAngleDebuggingView = textView;
        this.feedbackLabelView = feedbackLabelView;
        this.headTurnCompletedView = headTurnCompletedView;
        this.headTurnGuidanceView = headTurnGuidanceView;
        this.previewContainer = frameLayout2;
    }

    public static OnfidoFragmentMotionCaptureBinding bind(View view) {
        int i = R.id.avcCaptureToolbar;
        Toolbar toolbar = (Toolbar) ViewBindings.findChildViewById(view, i);
        if (toolbar != null) {
            i = R.id.camera2PreviewView;
            CameraViewfinder cameraViewfinder = (CameraViewfinder) ViewBindings.findChildViewById(view, i);
            if (cameraViewfinder != null) {
                i = R.id.cameraXPreviewView;
                PreviewView previewView = (PreviewView) ViewBindings.findChildViewById(view, i);
                if (previewView != null) {
                    i = R.id.faceAlignmentView;
                    FaceAlignmentView faceAlignmentView = (FaceAlignmentView) ViewBindings.findChildViewById(view, i);
                    if (faceAlignmentView != null) {
                        i = R.id.faceRectDebuggingView;
                        RectDebuggingView rectDebuggingView = (RectDebuggingView) ViewBindings.findChildViewById(view, i);
                        if (rectDebuggingView != null) {
                            i = R.id.faceYawAngleDebuggingView;
                            TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                            if (textView != null) {
                                i = R.id.feedbackLabelView;
                                FeedbackLabelView feedbackLabelView = (FeedbackLabelView) ViewBindings.findChildViewById(view, i);
                                if (feedbackLabelView != null) {
                                    i = R.id.headTurnCompletedView;
                                    HeadTurnCompletedView headTurnCompletedView = (HeadTurnCompletedView) ViewBindings.findChildViewById(view, i);
                                    if (headTurnCompletedView != null) {
                                        i = R.id.headTurnGuidanceView;
                                        HeadTurnGuidanceView headTurnGuidanceView = (HeadTurnGuidanceView) ViewBindings.findChildViewById(view, i);
                                        if (headTurnGuidanceView != null) {
                                            i = R.id.previewContainer;
                                            FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, i);
                                            if (frameLayout != null) {
                                                return new OnfidoFragmentMotionCaptureBinding((FrameLayout) view, toolbar, cameraViewfinder, previewView, faceAlignmentView, rectDebuggingView, textView, feedbackLabelView, headTurnCompletedView, headTurnGuidanceView, frameLayout);
                                            }
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

    public static OnfidoFragmentMotionCaptureBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static OnfidoFragmentMotionCaptureBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_fragment_motion_capture, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
