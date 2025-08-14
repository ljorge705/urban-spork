package com.onfido.android.sdk.capture.internal.camera.factory;

import android.util.Size;
import androidx.camera.view.PreviewView;
import com.facebook.imagepipeline.producers.HttpUrlConnectionNetworkFetcher;
import com.onfido.android.sdk.capture.internal.camera.ConfigurationExtKt;
import com.onfido.android.sdk.capture.internal.camera.camerax.ImageAnalysisConfig;
import com.onfido.android.sdk.capture.internal.camera.camerax.ImageCaptureConfig;
import com.onfido.android.sdk.capture.internal.camera.camerax.PreviewConfig;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.ui.camera.capture.VideoCaptureConfig;
import com.onfido.javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006J\u0006\u0010\u0007\u001a\u00020\bJ\b\u0010\t\u001a\u00020\nH\u0002J\b\u0010\u000b\u001a\u00020\nH\u0002J\b\u0010\f\u001a\u00020\rH\u0002J\u0006\u0010\u000e\u001a\u00020\u000fJ\b\u0010\u0010\u001a\u00020\u0011H\u0002J\b\u0010\u0012\u001a\u00020\nH\u0002J\u0006\u0010\u0013\u001a\u00020\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/camera/factory/DocCaptureCameraConfigProvider;", "", "onfidoRemoteConfig", "Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;", "(Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;)V", "getImageAnalysisConfig", "Lcom/onfido/android/sdk/capture/internal/camera/camerax/ImageAnalysisConfig;", "getImageCaptureConfig", "Lcom/onfido/android/sdk/capture/internal/camera/camerax/ImageCaptureConfig;", "getImageCaptureFlashMode", "", "getImageCaptureMode", "getImageCaptureTargetResolution", "Landroid/util/Size;", "getPreviewConfig", "Lcom/onfido/android/sdk/capture/internal/camera/camerax/PreviewConfig;", "getPreviewScaleType", "Landroidx/camera/view/PreviewView$ScaleType;", "getVideoCaptureAspectRatio", "getVideoCaptureConfig", "Lcom/onfido/android/sdk/capture/ui/camera/capture/VideoCaptureConfig;", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DocCaptureCameraConfigProvider {
    public static final float ASPECT_RATIO_16_9 = 0.5625f;
    public static final float ASPECT_RATIO_4_3 = 0.75f;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final long DOC_CAPTURE_FRAME_SAMPLING_DURATION;
    private final OnfidoRemoteConfig onfidoRemoteConfig;

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0019\u0010\u0006\u001a\u00020\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\t\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u000b"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/camera/factory/DocCaptureCameraConfigProvider$Companion;", "", "()V", "ASPECT_RATIO_16_9", "", "ASPECT_RATIO_4_3", "DOC_CAPTURE_FRAME_SAMPLING_DURATION", "Lkotlin/time/Duration;", "getDOC_CAPTURE_FRAME_SAMPLING_DURATION-UwyO8pc", "()J", "J", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        /* renamed from: getDOC_CAPTURE_FRAME_SAMPLING_DURATION-UwyO8pc, reason: not valid java name */
        public final long m5593getDOC_CAPTURE_FRAME_SAMPLING_DURATIONUwyO8pc() {
            return DocCaptureCameraConfigProvider.DOC_CAPTURE_FRAME_SAMPLING_DURATION;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    static {
        Duration.Companion companion = Duration.INSTANCE;
        DOC_CAPTURE_FRAME_SAMPLING_DURATION = DurationKt.toDuration(10, DurationUnit.MILLISECONDS);
    }

    @Inject
    public DocCaptureCameraConfigProvider(OnfidoRemoteConfig onfidoRemoteConfig) {
        Intrinsics.checkNotNullParameter(onfidoRemoteConfig, "onfidoRemoteConfig");
        this.onfidoRemoteConfig = onfidoRemoteConfig;
    }

    private final int getImageCaptureFlashMode() {
        return this.onfidoRemoteConfig.isAutoFlashModeEnabled() ? 0 : 2;
    }

    private final int getImageCaptureMode() {
        return !this.onfidoRemoteConfig.isCameraXHighQualityModeEnabled() ? 1 : 0;
    }

    private final Size getImageCaptureTargetResolution() {
        float f;
        float f2;
        int targetResolutionWidth = this.onfidoRemoteConfig.getDocumentCapture().getTargetResolutionWidth();
        if (this.onfidoRemoteConfig.isFourByThreeEnabled()) {
            f = targetResolutionWidth;
            f2 = 0.75f;
        } else {
            f = targetResolutionWidth;
            f2 = 0.5625f;
        }
        return new Size(targetResolutionWidth, (int) (f * f2));
    }

    private final PreviewView.ScaleType getPreviewScaleType() {
        return this.onfidoRemoteConfig.isFourByThreeEnabled() ? PreviewView.ScaleType.FIT_CENTER : PreviewView.ScaleType.FILL_CENTER;
    }

    private final int getVideoCaptureAspectRatio() {
        return !this.onfidoRemoteConfig.isFourByThreeEnabled() ? 1 : 0;
    }

    public final ImageAnalysisConfig getImageAnalysisConfig() {
        return new ImageAnalysisConfig(DOC_CAPTURE_FRAME_SAMPLING_DURATION, null);
    }

    public final ImageCaptureConfig getImageCaptureConfig() {
        return new ImageCaptureConfig(getImageCaptureTargetResolution(), getImageCaptureMode(), getImageCaptureFlashMode());
    }

    public final PreviewConfig getPreviewConfig() {
        return new PreviewConfig(getPreviewScaleType(), this.onfidoRemoteConfig.getDocumentCapture().isCameraXViewPortEnabled(), DOC_CAPTURE_FRAME_SAMPLING_DURATION, null);
    }

    public final VideoCaptureConfig getVideoCaptureConfig() {
        return new VideoCaptureConfig(null, false, HttpUrlConnectionNetworkFetcher.HTTP_DEFAULT_TIMEOUT, ConfigurationExtKt.getVideoQualityProfile(this.onfidoRemoteConfig.getDocumentCapture()), getVideoCaptureAspectRatio(), this.onfidoRemoteConfig.getDocumentCapture().getVideoBitrate(), 25, 0L, 0L, 385, null);
    }
}
