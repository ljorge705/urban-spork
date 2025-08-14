package com.onfido.android.sdk.capture.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.camera.view.PreviewView;
import androidx.fragment.app.FragmentContainerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.detector.rectangle.RectDetectorFrame;
import com.onfido.android.sdk.capture.ui.ZoomImageView;
import com.onfido.android.sdk.capture.ui.camera.ConfirmationStepButtons;
import com.onfido.android.sdk.capture.ui.camera.OverlayTextView;
import com.onfido.android.sdk.capture.ui.camera.liveness.LivenessOverlayView;
import com.onfido.android.sdk.capture.ui.camera.view.CameraSourcePreview;
import com.onfido.android.sdk.capture.ui.widget.WatermarkView;

/* loaded from: classes2.dex */
public final class OnfidoFragmentLivenessCaptureBinding implements ViewBinding {
    public final CameraSourcePreview cameraViewCamera1;
    public final PreviewView cameraViewCameraX;
    public final ConfirmationStepButtons confirmationButtons;
    public final ZoomImageView confirmationImage;
    public final RelativeLayout content;
    public final FrameLayout contentLayout;
    public final FragmentContainerView fragmentContainer;
    public final LivenessOverlayView livenessOverlayView;
    public final RectDetectorFrame onfidoAccessibleRectDetectorFrame;
    public final OverlayTextView overlayTextContainer;
    private final RelativeLayout rootView;
    public final Toolbar toolbar;
    public final OnfidoViewVideoRecordingIndicatorBinding videoRecordingContainer;
    public final WatermarkView watermark;
    public final FrameLayout watermarkLayout;

    private OnfidoFragmentLivenessCaptureBinding(RelativeLayout relativeLayout, CameraSourcePreview cameraSourcePreview, PreviewView previewView, ConfirmationStepButtons confirmationStepButtons, ZoomImageView zoomImageView, RelativeLayout relativeLayout2, FrameLayout frameLayout, FragmentContainerView fragmentContainerView, LivenessOverlayView livenessOverlayView, RectDetectorFrame rectDetectorFrame, OverlayTextView overlayTextView, Toolbar toolbar, OnfidoViewVideoRecordingIndicatorBinding onfidoViewVideoRecordingIndicatorBinding, WatermarkView watermarkView, FrameLayout frameLayout2) {
        this.rootView = relativeLayout;
        this.cameraViewCamera1 = cameraSourcePreview;
        this.cameraViewCameraX = previewView;
        this.confirmationButtons = confirmationStepButtons;
        this.confirmationImage = zoomImageView;
        this.content = relativeLayout2;
        this.contentLayout = frameLayout;
        this.fragmentContainer = fragmentContainerView;
        this.livenessOverlayView = livenessOverlayView;
        this.onfidoAccessibleRectDetectorFrame = rectDetectorFrame;
        this.overlayTextContainer = overlayTextView;
        this.toolbar = toolbar;
        this.videoRecordingContainer = onfidoViewVideoRecordingIndicatorBinding;
        this.watermark = watermarkView;
        this.watermarkLayout = frameLayout2;
    }

    public static OnfidoFragmentLivenessCaptureBinding bind(View view) {
        View viewFindChildViewById;
        int i = R.id.cameraViewCamera1;
        CameraSourcePreview cameraSourcePreview = (CameraSourcePreview) ViewBindings.findChildViewById(view, i);
        if (cameraSourcePreview != null) {
            i = R.id.cameraViewCameraX;
            PreviewView previewView = (PreviewView) ViewBindings.findChildViewById(view, i);
            if (previewView != null) {
                i = R.id.confirmationButtons;
                ConfirmationStepButtons confirmationStepButtons = (ConfirmationStepButtons) ViewBindings.findChildViewById(view, i);
                if (confirmationStepButtons != null) {
                    i = R.id.confirmationImage;
                    ZoomImageView zoomImageView = (ZoomImageView) ViewBindings.findChildViewById(view, i);
                    if (zoomImageView != null) {
                        RelativeLayout relativeLayout = (RelativeLayout) view;
                        i = R.id.contentLayout;
                        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, i);
                        if (frameLayout != null) {
                            i = R.id.fragmentContainer;
                            FragmentContainerView fragmentContainerView = (FragmentContainerView) ViewBindings.findChildViewById(view, i);
                            if (fragmentContainerView != null) {
                                i = R.id.livenessOverlayView;
                                LivenessOverlayView livenessOverlayView = (LivenessOverlayView) ViewBindings.findChildViewById(view, i);
                                if (livenessOverlayView != null) {
                                    i = R.id.onfido_accessible_rect_detector_frame;
                                    RectDetectorFrame rectDetectorFrame = (RectDetectorFrame) ViewBindings.findChildViewById(view, i);
                                    if (rectDetectorFrame != null) {
                                        i = R.id.overlayTextContainer;
                                        OverlayTextView overlayTextView = (OverlayTextView) ViewBindings.findChildViewById(view, i);
                                        if (overlayTextView != null) {
                                            i = R.id.toolbar;
                                            Toolbar toolbar = (Toolbar) ViewBindings.findChildViewById(view, i);
                                            if (toolbar != null && (viewFindChildViewById = ViewBindings.findChildViewById(view, (i = R.id.videoRecordingContainer))) != null) {
                                                OnfidoViewVideoRecordingIndicatorBinding onfidoViewVideoRecordingIndicatorBindingBind = OnfidoViewVideoRecordingIndicatorBinding.bind(viewFindChildViewById);
                                                i = R.id.watermark;
                                                WatermarkView watermarkView = (WatermarkView) ViewBindings.findChildViewById(view, i);
                                                if (watermarkView != null) {
                                                    i = R.id.watermarkLayout;
                                                    FrameLayout frameLayout2 = (FrameLayout) ViewBindings.findChildViewById(view, i);
                                                    if (frameLayout2 != null) {
                                                        return new OnfidoFragmentLivenessCaptureBinding(relativeLayout, cameraSourcePreview, previewView, confirmationStepButtons, zoomImageView, relativeLayout, frameLayout, fragmentContainerView, livenessOverlayView, rectDetectorFrame, overlayTextView, toolbar, onfidoViewVideoRecordingIndicatorBindingBind, watermarkView, frameLayout2);
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
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static OnfidoFragmentLivenessCaptureBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static OnfidoFragmentLivenessCaptureBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_fragment_liveness_capture, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
