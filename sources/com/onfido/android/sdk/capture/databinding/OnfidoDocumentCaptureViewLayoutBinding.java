package com.onfido.android.sdk.capture.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.edge_detector.EdgeDetectorFrame;
import com.onfido.android.sdk.capture.ui.camera.view.CameraSourcePreview;

/* loaded from: classes2.dex */
public final class OnfidoDocumentCaptureViewLayoutBinding implements ViewBinding {
    public final CameraSourcePreview cameraView;
    public final ImageView documentOverlayImage;
    public final EdgeDetectorFrame onfidoCaptureFrame;
    public final ViewStub overlayViewStub;
    private final View rootView;

    private OnfidoDocumentCaptureViewLayoutBinding(View view, CameraSourcePreview cameraSourcePreview, ImageView imageView, EdgeDetectorFrame edgeDetectorFrame, ViewStub viewStub) {
        this.rootView = view;
        this.cameraView = cameraSourcePreview;
        this.documentOverlayImage = imageView;
        this.onfidoCaptureFrame = edgeDetectorFrame;
        this.overlayViewStub = viewStub;
    }

    public static OnfidoDocumentCaptureViewLayoutBinding bind(View view) {
        int i = R.id.cameraView;
        CameraSourcePreview cameraSourcePreview = (CameraSourcePreview) ViewBindings.findChildViewById(view, i);
        if (cameraSourcePreview != null) {
            i = R.id.document_overlay_image;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
            if (imageView != null) {
                i = R.id.onfido_capture_frame;
                EdgeDetectorFrame edgeDetectorFrame = (EdgeDetectorFrame) ViewBindings.findChildViewById(view, i);
                if (edgeDetectorFrame != null) {
                    i = R.id.overlay_view_stub;
                    ViewStub viewStub = (ViewStub) ViewBindings.findChildViewById(view, i);
                    if (viewStub != null) {
                        return new OnfidoDocumentCaptureViewLayoutBinding(view, cameraSourcePreview, imageView, edgeDetectorFrame, viewStub);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static OnfidoDocumentCaptureViewLayoutBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        if (viewGroup == null) {
            throw new NullPointerException("parent");
        }
        layoutInflater.inflate(R.layout.onfido_document_capture_view_layout, viewGroup);
        return bind(viewGroup);
    }

    @Override // androidx.viewbinding.ViewBinding
    public View getRoot() {
        return this.rootView;
    }
}
