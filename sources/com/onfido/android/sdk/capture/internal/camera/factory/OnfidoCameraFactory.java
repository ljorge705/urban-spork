package com.onfido.android.sdk.capture.internal.camera.factory;

import android.content.Context;
import androidx.camera.view.PreviewView;
import androidx.lifecycle.LifecycleOwner;
import com.onfido.android.sdk.capture.internal.camera.Camera1Api;
import com.onfido.android.sdk.capture.internal.camera.OnfidoCamera;
import com.onfido.android.sdk.capture.internal.camera.OnfidoImage;
import com.onfido.android.sdk.capture.internal.camera.camerax.CameraX;
import com.onfido.android.sdk.capture.internal.camera.camerax.FrameSampler;
import com.onfido.android.sdk.capture.internal.camera.camerax.ImageAnalyzer;
import com.onfido.android.sdk.capture.internal.camera.usecase.GetOptimalFourByThreePictureResolutionUseCase;
import com.onfido.android.sdk.capture.internal.camera.usecase.GetOptimalPictureResolutionUseCase;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import com.onfido.android.sdk.capture.ui.CaptureType;
import com.onfido.android.sdk.capture.ui.camera.OverlayView;
import com.onfido.android.sdk.capture.ui.camera.view.CameraSourcePreview;
import com.onfido.javax.inject.Inject;
import com.onfido.reactnative.sdk.ReactNativeBridgeUtiles;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001BE\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000e¢\u0006\u0002\u0010\u000fJ4\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J*\u0010\u001c\u001a\u00020\u001d2\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\"\u0010\u001e\u001a\u00020\u001f2\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u001bH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/onfido/android/sdk/capture/internal/camera/factory/OnfidoCameraFactory;", "Lcom/onfido/android/sdk/capture/internal/camera/factory/CameraFactory;", "applicationContext", "Landroid/content/Context;", "onfidoRemoteConfig", "Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;", "cameraxFactory", "Lcom/onfido/android/sdk/capture/internal/camera/camerax/CameraX$Factory;", "useCaseConfigProvider", "Lcom/onfido/android/sdk/capture/internal/camera/factory/CameraUseCaseConfigProvider;", "frameSampler", "Lcom/onfido/android/sdk/capture/internal/camera/camerax/FrameSampler;", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoImage;", "imageAnalyzer", "Lcom/onfido/android/sdk/capture/internal/camera/camerax/ImageAnalyzer;", "(Landroid/content/Context;Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;Lcom/onfido/android/sdk/capture/internal/camera/camerax/CameraX$Factory;Lcom/onfido/android/sdk/capture/internal/camera/factory/CameraUseCaseConfigProvider;Lcom/onfido/android/sdk/capture/internal/camera/camerax/FrameSampler;Lcom/onfido/android/sdk/capture/internal/camera/camerax/ImageAnalyzer;)V", "create", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "camera1PreviewView", "Lcom/onfido/android/sdk/capture/ui/camera/view/CameraSourcePreview;", "cameraXPreviewView", "Landroidx/camera/view/PreviewView;", "overlayView", "Lcom/onfido/android/sdk/capture/ui/camera/OverlayView;", ReactNativeBridgeUtiles.KEY_CAPTURE_TYPE, "Lcom/onfido/android/sdk/capture/ui/CaptureType;", "createCamera1", "Lcom/onfido/android/sdk/capture/internal/camera/Camera1Api;", "createCameraX", "Lcom/onfido/android/sdk/capture/internal/camera/camerax/CameraX;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class OnfidoCameraFactory implements CameraFactory {
    private final Context applicationContext;
    private final CameraX.Factory cameraxFactory;
    private final FrameSampler<OnfidoImage> frameSampler;
    private final ImageAnalyzer<OnfidoImage> imageAnalyzer;
    private final OnfidoRemoteConfig onfidoRemoteConfig;
    private final CameraUseCaseConfigProvider useCaseConfigProvider;

    @Inject
    public OnfidoCameraFactory(Context applicationContext, OnfidoRemoteConfig onfidoRemoteConfig, CameraX.Factory cameraxFactory, CameraUseCaseConfigProvider useCaseConfigProvider, FrameSampler<OnfidoImage> frameSampler, ImageAnalyzer<OnfidoImage> imageAnalyzer) {
        Intrinsics.checkNotNullParameter(applicationContext, "applicationContext");
        Intrinsics.checkNotNullParameter(onfidoRemoteConfig, "onfidoRemoteConfig");
        Intrinsics.checkNotNullParameter(cameraxFactory, "cameraxFactory");
        Intrinsics.checkNotNullParameter(useCaseConfigProvider, "useCaseConfigProvider");
        Intrinsics.checkNotNullParameter(frameSampler, "frameSampler");
        Intrinsics.checkNotNullParameter(imageAnalyzer, "imageAnalyzer");
        this.applicationContext = applicationContext;
        this.onfidoRemoteConfig = onfidoRemoteConfig;
        this.cameraxFactory = cameraxFactory;
        this.useCaseConfigProvider = useCaseConfigProvider;
        this.frameSampler = frameSampler;
        this.imageAnalyzer = imageAnalyzer;
    }

    private final Camera1Api createCamera1(CameraSourcePreview camera1PreviewView, CaptureType captureType, LifecycleOwner lifecycleOwner, OverlayView overlayView) {
        if (camera1PreviewView == null) {
            throw new IllegalArgumentException("camera1PreviewView is required when CameraX is disabled (and Camera1 API is enabled)".toString());
        }
        Timber.INSTANCE.d("Camera1 API has been enabled", new Object[0]);
        if (captureType == CaptureType.DOCUMENT) {
            camera1PreviewView.setFocusImprovementsEnabled(this.onfidoRemoteConfig.isFocusImprovementsEnabled());
            if (this.onfidoRemoteConfig.isResolutionImprovementsEnabled()) {
                camera1PreviewView.setCameraResolutionProviderFactory(new GetOptimalPictureResolutionUseCase.Companion.Factory());
            }
            if (this.onfidoRemoteConfig.isFourByThreeEnabled()) {
                camera1PreviewView.setCameraResolutionProviderFactory(new GetOptimalFourByThreePictureResolutionUseCase.Companion.Factory());
            }
        }
        return new Camera1Api(this.applicationContext, camera1PreviewView, lifecycleOwner, overlayView, this.useCaseConfigProvider.getVideoCaptureConfig(captureType), this.onfidoRemoteConfig);
    }

    private final CameraX createCameraX(PreviewView cameraXPreviewView, LifecycleOwner lifecycleOwner, CaptureType captureType) {
        if (cameraXPreviewView == null) {
            throw new IllegalArgumentException("cameraXPreviewView is required when CameraX is enabled".toString());
        }
        Timber.INSTANCE.d("CameraX has been enabled", new Object[0]);
        return this.cameraxFactory.create(lifecycleOwner, cameraXPreviewView, this.useCaseConfigProvider.getPreviewConfig(captureType), this.useCaseConfigProvider.getImageCaptureConfig(captureType), this.useCaseConfigProvider.getVideoCaptureConfigForCameraX(captureType), this.useCaseConfigProvider.getImageAnalysisConfig(captureType), this.imageAnalyzer, this.frameSampler);
    }

    @Override // com.onfido.android.sdk.capture.internal.camera.factory.CameraFactory
    public OnfidoCamera create(LifecycleOwner lifecycleOwner, CameraSourcePreview camera1PreviewView, PreviewView cameraXPreviewView, OverlayView overlayView, CaptureType captureType) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        Intrinsics.checkNotNullParameter(overlayView, "overlayView");
        Intrinsics.checkNotNullParameter(captureType, "captureType");
        return this.onfidoRemoteConfig.isCameraXEnabled() ? createCameraX(cameraXPreviewView, lifecycleOwner, captureType) : createCamera1(camera1PreviewView, captureType, lifecycleOwner, overlayView);
    }
}
