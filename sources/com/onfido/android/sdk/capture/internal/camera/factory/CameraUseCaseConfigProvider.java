package com.onfido.android.sdk.capture.internal.camera.factory;

import androidx.camera.view.PreviewView;
import com.onfido.android.sdk.capture.internal.camera.ConfigurationExtKt;
import com.onfido.android.sdk.capture.internal.camera.camerax.ImageAnalysisConfig;
import com.onfido.android.sdk.capture.internal.camera.camerax.ImageCaptureConfig;
import com.onfido.android.sdk.capture.internal.camera.camerax.PreviewConfig;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.ui.CaptureType;
import com.onfido.android.sdk.capture.ui.camera.capture.VideoCaptureConfig;
import com.onfido.javax.inject.Inject;
import com.onfido.reactnative.sdk.ReactNativeBridgeUtiles;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\nJ\u0010\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\nJ\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u00102\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/camera/factory/CameraUseCaseConfigProvider;", "", "onfidoRemoteConfig", "Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;", "docCaptureCameraConfigProvider", "Lcom/onfido/android/sdk/capture/internal/camera/factory/DocCaptureCameraConfigProvider;", "(Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;Lcom/onfido/android/sdk/capture/internal/camera/factory/DocCaptureCameraConfigProvider;)V", "getImageAnalysisConfig", "Lcom/onfido/android/sdk/capture/internal/camera/camerax/ImageAnalysisConfig;", ReactNativeBridgeUtiles.KEY_CAPTURE_TYPE, "Lcom/onfido/android/sdk/capture/ui/CaptureType;", "getImageCaptureConfig", "Lcom/onfido/android/sdk/capture/internal/camera/camerax/ImageCaptureConfig;", "getPreviewConfig", "Lcom/onfido/android/sdk/capture/internal/camera/camerax/PreviewConfig;", "getVideoCaptureConfig", "Lcom/onfido/android/sdk/capture/ui/camera/capture/VideoCaptureConfig;", "getVideoCaptureConfigForCameraX", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class CameraUseCaseConfigProvider {
    private final DocCaptureCameraConfigProvider docCaptureCameraConfigProvider;
    private final OnfidoRemoteConfig onfidoRemoteConfig;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[CaptureType.values().length];
            try {
                iArr[CaptureType.FACE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[CaptureType.VIDEO.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[CaptureType.DOCUMENT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Inject
    public CameraUseCaseConfigProvider(OnfidoRemoteConfig onfidoRemoteConfig, DocCaptureCameraConfigProvider docCaptureCameraConfigProvider) {
        Intrinsics.checkNotNullParameter(onfidoRemoteConfig, "onfidoRemoteConfig");
        Intrinsics.checkNotNullParameter(docCaptureCameraConfigProvider, "docCaptureCameraConfigProvider");
        this.onfidoRemoteConfig = onfidoRemoteConfig;
        this.docCaptureCameraConfigProvider = docCaptureCameraConfigProvider;
    }

    public final ImageAnalysisConfig getImageAnalysisConfig(CaptureType captureType) {
        Intrinsics.checkNotNullParameter(captureType, "captureType");
        int i = WhenMappings.$EnumSwitchMapping$0[captureType.ordinal()];
        DefaultConstructorMarker defaultConstructorMarker = null;
        int i2 = 1;
        if (i == 1) {
            return null;
        }
        if (i == 2) {
            return new ImageAnalysisConfig(0L, i2, defaultConstructorMarker);
        }
        if (i == 3) {
            return this.docCaptureCameraConfigProvider.getImageAnalysisConfig();
        }
        throw new NoWhenBranchMatchedException();
    }

    public final ImageCaptureConfig getImageCaptureConfig(CaptureType captureType) {
        Intrinsics.checkNotNullParameter(captureType, "captureType");
        int i = WhenMappings.$EnumSwitchMapping$0[captureType.ordinal()];
        if (i == 1) {
            return new ImageCaptureConfig(ImageCaptureConfig.INSTANCE.getTARGET_RESOLUTION_FULL_HD(), 0, 0, 6, null);
        }
        if (i == 2) {
            return null;
        }
        if (i == 3) {
            return this.docCaptureCameraConfigProvider.getImageCaptureConfig();
        }
        throw new NoWhenBranchMatchedException();
    }

    public final PreviewConfig getPreviewConfig(CaptureType captureType) {
        Intrinsics.checkNotNullParameter(captureType, "captureType");
        int i = WhenMappings.$EnumSwitchMapping$0[captureType.ordinal()];
        if (i == 1 || i == 2) {
            return new PreviewConfig(PreviewView.ScaleType.FILL_CENTER, false, 0L, 4, null);
        }
        if (i == 3) {
            return this.docCaptureCameraConfigProvider.getPreviewConfig();
        }
        throw new NoWhenBranchMatchedException();
    }

    public final VideoCaptureConfig getVideoCaptureConfig(CaptureType captureType) {
        Intrinsics.checkNotNullParameter(captureType, "captureType");
        return captureType == CaptureType.VIDEO ? new VideoCaptureConfig(null, true, this.onfidoRemoteConfig.getLivenessCapture().getMaxVideoLengthMs(), ConfigurationExtKt.getVideoQualityProfile(this.onfidoRemoteConfig.getLivenessCapture()), 1, this.onfidoRemoteConfig.getLivenessCapture().getVideoBitrate(), 25, 0L, 0L, 385, null) : this.docCaptureCameraConfigProvider.getVideoCaptureConfig();
    }

    public final VideoCaptureConfig getVideoCaptureConfigForCameraX(CaptureType captureType) {
        Intrinsics.checkNotNullParameter(captureType, "captureType");
        if (captureType == CaptureType.FACE) {
            return null;
        }
        return getVideoCaptureConfig(captureType);
    }
}
