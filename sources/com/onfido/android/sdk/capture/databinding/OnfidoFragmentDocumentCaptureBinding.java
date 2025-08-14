package com.onfido.android.sdk.capture.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.camera.view.PreviewView;
import androidx.fragment.app.FragmentContainerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.imageview.ShapeableImageView;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.detector.rectangle.RectDetectorFrame;
import com.onfido.android.sdk.capture.ui.ZoomImageView;
import com.onfido.android.sdk.capture.ui.camera.ConfirmationStepButtons;
import com.onfido.android.sdk.capture.ui.camera.OverlayTextView;
import com.onfido.android.sdk.capture.ui.camera.view.CameraSourcePreview;
import com.onfido.android.sdk.capture.ui.widget.WatermarkView;
import com.onfido.android.sdk.capture.validation.OnfidoCaptureValidationBubble;

/* loaded from: classes2.dex */
public final class OnfidoFragmentDocumentCaptureBinding implements ViewBinding {
    public final CameraSourcePreview cameraViewCamera1;
    public final PreviewView cameraViewCameraX;
    public final ConfirmationStepButtons confirmationButtons;
    public final ZoomImageView confirmationImage;
    public final RelativeLayout content;
    public final FrameLayout contentLayout;
    public final ImageView flipArrow;
    public final FragmentContainerView fragmentContainer;
    public final ShapeableImageView frenchDLOverlay;
    public final ShapeableImageView germanDLOverlay;
    public final ShapeableImageView italianIDOverlay;
    public final OnfidoCaptureValidationBubble liveValidationBubble;
    public final RectDetectorFrame onfidoAccessibleRectDetectorFrame;
    public final OverlayTextView overlayTextContainer;
    public final ShapeableImageView passportOverlay;
    public final OnfidoCaptureValidationBubble postCaptureValidationBubble;
    private final RelativeLayout rootView;
    public final Toolbar toolbar;
    public final OnfidoViewVideoRecordingIndicatorBinding videoRecordingContainer;
    public final WatermarkView watermark;
    public final FrameLayout watermarkLayout;

    private OnfidoFragmentDocumentCaptureBinding(RelativeLayout relativeLayout, CameraSourcePreview cameraSourcePreview, PreviewView previewView, ConfirmationStepButtons confirmationStepButtons, ZoomImageView zoomImageView, RelativeLayout relativeLayout2, FrameLayout frameLayout, ImageView imageView, FragmentContainerView fragmentContainerView, ShapeableImageView shapeableImageView, ShapeableImageView shapeableImageView2, ShapeableImageView shapeableImageView3, OnfidoCaptureValidationBubble onfidoCaptureValidationBubble, RectDetectorFrame rectDetectorFrame, OverlayTextView overlayTextView, ShapeableImageView shapeableImageView4, OnfidoCaptureValidationBubble onfidoCaptureValidationBubble2, Toolbar toolbar, OnfidoViewVideoRecordingIndicatorBinding onfidoViewVideoRecordingIndicatorBinding, WatermarkView watermarkView, FrameLayout frameLayout2) {
        this.rootView = relativeLayout;
        this.cameraViewCamera1 = cameraSourcePreview;
        this.cameraViewCameraX = previewView;
        this.confirmationButtons = confirmationStepButtons;
        this.confirmationImage = zoomImageView;
        this.content = relativeLayout2;
        this.contentLayout = frameLayout;
        this.flipArrow = imageView;
        this.fragmentContainer = fragmentContainerView;
        this.frenchDLOverlay = shapeableImageView;
        this.germanDLOverlay = shapeableImageView2;
        this.italianIDOverlay = shapeableImageView3;
        this.liveValidationBubble = onfidoCaptureValidationBubble;
        this.onfidoAccessibleRectDetectorFrame = rectDetectorFrame;
        this.overlayTextContainer = overlayTextView;
        this.passportOverlay = shapeableImageView4;
        this.postCaptureValidationBubble = onfidoCaptureValidationBubble2;
        this.toolbar = toolbar;
        this.videoRecordingContainer = onfidoViewVideoRecordingIndicatorBinding;
        this.watermark = watermarkView;
        this.watermarkLayout = frameLayout2;
    }

    public static OnfidoFragmentDocumentCaptureBinding bind(View view) {
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
                            i = R.id.flipArrow;
                            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
                            if (imageView != null) {
                                i = R.id.fragmentContainer;
                                FragmentContainerView fragmentContainerView = (FragmentContainerView) ViewBindings.findChildViewById(view, i);
                                if (fragmentContainerView != null) {
                                    i = R.id.frenchDLOverlay;
                                    ShapeableImageView shapeableImageView = (ShapeableImageView) ViewBindings.findChildViewById(view, i);
                                    if (shapeableImageView != null) {
                                        i = R.id.germanDLOverlay;
                                        ShapeableImageView shapeableImageView2 = (ShapeableImageView) ViewBindings.findChildViewById(view, i);
                                        if (shapeableImageView2 != null) {
                                            i = R.id.italianIDOverlay;
                                            ShapeableImageView shapeableImageView3 = (ShapeableImageView) ViewBindings.findChildViewById(view, i);
                                            if (shapeableImageView3 != null) {
                                                i = R.id.liveValidationBubble;
                                                OnfidoCaptureValidationBubble onfidoCaptureValidationBubble = (OnfidoCaptureValidationBubble) ViewBindings.findChildViewById(view, i);
                                                if (onfidoCaptureValidationBubble != null) {
                                                    i = R.id.onfido_accessible_rect_detector_frame;
                                                    RectDetectorFrame rectDetectorFrame = (RectDetectorFrame) ViewBindings.findChildViewById(view, i);
                                                    if (rectDetectorFrame != null) {
                                                        i = R.id.overlayTextContainer;
                                                        OverlayTextView overlayTextView = (OverlayTextView) ViewBindings.findChildViewById(view, i);
                                                        if (overlayTextView != null) {
                                                            i = R.id.passportOverlay;
                                                            ShapeableImageView shapeableImageView4 = (ShapeableImageView) ViewBindings.findChildViewById(view, i);
                                                            if (shapeableImageView4 != null) {
                                                                i = R.id.postCaptureValidationBubble;
                                                                OnfidoCaptureValidationBubble onfidoCaptureValidationBubble2 = (OnfidoCaptureValidationBubble) ViewBindings.findChildViewById(view, i);
                                                                if (onfidoCaptureValidationBubble2 != null) {
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
                                                                                return new OnfidoFragmentDocumentCaptureBinding(relativeLayout, cameraSourcePreview, previewView, confirmationStepButtons, zoomImageView, relativeLayout, frameLayout, imageView, fragmentContainerView, shapeableImageView, shapeableImageView2, shapeableImageView3, onfidoCaptureValidationBubble, rectDetectorFrame, overlayTextView, shapeableImageView4, onfidoCaptureValidationBubble2, toolbar, onfidoViewVideoRecordingIndicatorBindingBind, watermarkView, frameLayout2);
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
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static OnfidoFragmentDocumentCaptureBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static OnfidoFragmentDocumentCaptureBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_fragment_document_capture, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
