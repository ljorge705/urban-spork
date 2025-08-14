package com.onfido.android.sdk.capture.internal.camera.factory;

import androidx.camera.view.PreviewView;
import androidx.lifecycle.LifecycleOwner;
import com.onfido.android.sdk.capture.internal.camera.OnfidoCamera;
import com.onfido.android.sdk.capture.ui.CaptureType;
import com.onfido.android.sdk.capture.ui.camera.OverlayView;
import com.onfido.android.sdk.capture.ui.camera.view.CameraSourcePreview;
import com.onfido.reactnative.sdk.ReactNativeBridgeUtiles;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bá\u0080\u0001\u0018\u00002\u00020\u0001J4\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH&¨\u0006\u000e"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/camera/factory/CameraFactory;", "", "create", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "camera1PreviewView", "Lcom/onfido/android/sdk/capture/ui/camera/view/CameraSourcePreview;", "cameraXPreviewView", "Landroidx/camera/view/PreviewView;", "overlayView", "Lcom/onfido/android/sdk/capture/ui/camera/OverlayView;", ReactNativeBridgeUtiles.KEY_CAPTURE_TYPE, "Lcom/onfido/android/sdk/capture/ui/CaptureType;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface CameraFactory {
    OnfidoCamera create(LifecycleOwner lifecycleOwner, CameraSourcePreview camera1PreviewView, PreviewView cameraXPreviewView, OverlayView overlayView, CaptureType captureType);
}
