package com.onfido.android.sdk.capture.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.ui.camera.view.CameraSourcePreview;

/* loaded from: classes2.dex */
public final class OnfidoActivityCaptureRefactoredBinding implements ViewBinding {
    public final CameraSourcePreview cameraPreview;
    public final RelativeLayout content;
    public final FrameLayout contentLayout;
    public final FrameLayout overlayContainer;
    private final RelativeLayout rootView;
    public final Toolbar toolbar;

    private OnfidoActivityCaptureRefactoredBinding(RelativeLayout relativeLayout, CameraSourcePreview cameraSourcePreview, RelativeLayout relativeLayout2, FrameLayout frameLayout, FrameLayout frameLayout2, Toolbar toolbar) {
        this.rootView = relativeLayout;
        this.cameraPreview = cameraSourcePreview;
        this.content = relativeLayout2;
        this.contentLayout = frameLayout;
        this.overlayContainer = frameLayout2;
        this.toolbar = toolbar;
    }

    public static OnfidoActivityCaptureRefactoredBinding bind(View view) {
        int i = R.id.cameraPreview;
        CameraSourcePreview cameraSourcePreview = (CameraSourcePreview) ViewBindings.findChildViewById(view, i);
        if (cameraSourcePreview != null) {
            RelativeLayout relativeLayout = (RelativeLayout) view;
            i = R.id.contentLayout;
            FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, i);
            if (frameLayout != null) {
                i = R.id.overlayContainer;
                FrameLayout frameLayout2 = (FrameLayout) ViewBindings.findChildViewById(view, i);
                if (frameLayout2 != null) {
                    i = R.id.toolbar;
                    Toolbar toolbar = (Toolbar) ViewBindings.findChildViewById(view, i);
                    if (toolbar != null) {
                        return new OnfidoActivityCaptureRefactoredBinding(relativeLayout, cameraSourcePreview, relativeLayout, frameLayout, frameLayout2, toolbar);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }

    public static OnfidoActivityCaptureRefactoredBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static OnfidoActivityCaptureRefactoredBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.onfido_activity_capture_refactored, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
}
