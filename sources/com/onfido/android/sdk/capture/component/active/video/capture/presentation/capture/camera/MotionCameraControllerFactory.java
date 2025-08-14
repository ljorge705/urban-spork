package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera;

import androidx.camera.view.PreviewView;
import androidx.camera.viewfinder.CameraViewfinder;
import androidx.lifecycle.LifecycleOwner;
import com.onfido.android.sdk.capture.component.active.video.capture.AVCConstants;
import com.onfido.android.sdk.capture.component.active.video.capture.analytics.AvcAnalyticsEvent;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.MotionCameraXController;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.MotionCamera2Controller;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.FaceDetectorAvc;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.OnfidoAnalytics;
import com.onfido.android.sdk.capture.internal.camera.camerax.CameraX;
import com.onfido.android.sdk.capture.internal.camera.camerax.FrameSampler;
import com.onfido.android.sdk.capture.internal.camera.camerax.ImageAnalysisConfig;
import com.onfido.android.sdk.capture.internal.camera.camerax.ImageAnalyzer;
import com.onfido.android.sdk.capture.internal.camera.camerax.PreviewConfig;
import com.onfido.android.sdk.capture.ui.camera.capture.VideoCaptureConfig;
import com.onfido.javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;

@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 #2\u00020\u0001:\u0001#BC\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0002\u0010\u000fJ6\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cJ\b\u0010\u001d\u001a\u00020\u001eH\u0002J\b\u0010\u001f\u001a\u00020 H\u0002J\u0010\u0010!\u001a\u00020\"2\u0006\u0010\u0014\u001a\u00020\u0015H\u0002R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/MotionCameraControllerFactory;", "", "cameraXFactory", "Lcom/onfido/android/sdk/capture/internal/camera/camerax/CameraX$Factory;", "frameSampler", "Lcom/onfido/android/sdk/capture/internal/camera/camerax/FrameSampler;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/MotionImage;", "imageAnalyzer", "Lcom/onfido/android/sdk/capture/internal/camera/camerax/ImageAnalyzer;", "cameraXControllerFactory", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/MotionCameraXController$Factory;", "camera2ControllerFactory", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/camera2/MotionCamera2Controller$Factory;", "analytics", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/OnfidoAnalytics;", "(Lcom/onfido/android/sdk/capture/internal/camera/camerax/CameraX$Factory;Lcom/onfido/android/sdk/capture/internal/camera/camerax/FrameSampler;Lcom/onfido/android/sdk/capture/internal/camera/camerax/ImageAnalyzer;Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/MotionCameraXController$Factory;Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/camera2/MotionCamera2Controller$Factory;Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/OnfidoAnalytics;)V", "create", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/MotionCameraController;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "audioEnabled", "", "isCameraxEnabled", "cameraViewfinder", "Landroidx/camera/viewfinder/CameraViewfinder;", "previewView", "Landroidx/camera/view/PreviewView;", "faceDetectorAvc", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/facedetector/FaceDetectorAvc;", "createImageAnalysisConfig", "Lcom/onfido/android/sdk/capture/internal/camera/camerax/ImageAnalysisConfig;", "createPreviewConfig", "Lcom/onfido/android/sdk/capture/internal/camera/camerax/PreviewConfig;", "createVideoCaptureConfig", "Lcom/onfido/android/sdk/capture/ui/camera/capture/VideoCaptureConfig;", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class MotionCameraControllerFactory {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final long FRAME_SAMPLING_PERIOD_MILLISECONDS;
    private static final int RECORDER_VIDEO_BITRATE = 5000000;
    public static final int RECORDER_VIDEO_FPS = 30;
    private final OnfidoAnalytics analytics;
    private final MotionCamera2Controller.Factory camera2ControllerFactory;
    private final MotionCameraXController.Factory cameraXControllerFactory;
    private final CameraX.Factory cameraXFactory;
    private final FrameSampler<MotionImage> frameSampler;
    private final ImageAnalyzer<MotionImage> imageAnalyzer;

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0019\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\b\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u000b"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/MotionCameraControllerFactory$Companion;", "", "()V", "FRAME_SAMPLING_PERIOD_MILLISECONDS", "Lkotlin/time/Duration;", "getFRAME_SAMPLING_PERIOD_MILLISECONDS-UwyO8pc", "()J", "J", "RECORDER_VIDEO_BITRATE", "", "RECORDER_VIDEO_FPS", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        /* renamed from: getFRAME_SAMPLING_PERIOD_MILLISECONDS-UwyO8pc, reason: not valid java name */
        public final long m5540getFRAME_SAMPLING_PERIOD_MILLISECONDSUwyO8pc() {
            return MotionCameraControllerFactory.FRAME_SAMPLING_PERIOD_MILLISECONDS;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    static {
        Duration.Companion companion = Duration.INSTANCE;
        FRAME_SAMPLING_PERIOD_MILLISECONDS = DurationKt.toDuration(100, DurationUnit.MILLISECONDS);
    }

    @Inject
    public MotionCameraControllerFactory(CameraX.Factory cameraXFactory, FrameSampler<MotionImage> frameSampler, ImageAnalyzer<MotionImage> imageAnalyzer, MotionCameraXController.Factory cameraXControllerFactory, MotionCamera2Controller.Factory camera2ControllerFactory, OnfidoAnalytics analytics) {
        Intrinsics.checkNotNullParameter(cameraXFactory, "cameraXFactory");
        Intrinsics.checkNotNullParameter(frameSampler, "frameSampler");
        Intrinsics.checkNotNullParameter(imageAnalyzer, "imageAnalyzer");
        Intrinsics.checkNotNullParameter(cameraXControllerFactory, "cameraXControllerFactory");
        Intrinsics.checkNotNullParameter(camera2ControllerFactory, "camera2ControllerFactory");
        Intrinsics.checkNotNullParameter(analytics, "analytics");
        this.cameraXFactory = cameraXFactory;
        this.frameSampler = frameSampler;
        this.imageAnalyzer = imageAnalyzer;
        this.cameraXControllerFactory = cameraXControllerFactory;
        this.camera2ControllerFactory = camera2ControllerFactory;
        this.analytics = analytics;
    }

    private final ImageAnalysisConfig createImageAnalysisConfig() {
        return new ImageAnalysisConfig(FRAME_SAMPLING_PERIOD_MILLISECONDS, null);
    }

    private final PreviewConfig createPreviewConfig() {
        return new PreviewConfig(PreviewView.ScaleType.FILL_CENTER, false, FRAME_SAMPLING_PERIOD_MILLISECONDS, null);
    }

    private final VideoCaptureConfig createVideoCaptureConfig(boolean audioEnabled) {
        return new VideoCaptureConfig(AVCConstants.MOTION_RECORDING_FILE_PREFIX, audioEnabled, Integer.MAX_VALUE, 4, 0, 5000000, 30, 0L, 0L, 384, null);
    }

    public final MotionCameraController create(LifecycleOwner lifecycleOwner, boolean audioEnabled, boolean isCameraxEnabled, CameraViewfinder cameraViewfinder, PreviewView previewView, FaceDetectorAvc faceDetectorAvc) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        Intrinsics.checkNotNullParameter(cameraViewfinder, "cameraViewfinder");
        Intrinsics.checkNotNullParameter(previewView, "previewView");
        Intrinsics.checkNotNullParameter(faceDetectorAvc, "faceDetectorAvc");
        if (!isCameraxEnabled) {
            this.analytics.track(new AvcAnalyticsEvent.MotionCamera(AvcAnalyticsEvent.MotionCamera.CAMERA_2));
            return this.camera2ControllerFactory.create(cameraViewfinder, createVideoCaptureConfig(audioEnabled), createPreviewConfig(), faceDetectorAvc);
        }
        this.analytics.track(new AvcAnalyticsEvent.MotionCamera(AvcAnalyticsEvent.MotionCamera.CAMERA_X));
        return this.cameraXControllerFactory.create(CameraX.Factory.DefaultImpls.create$default(this.cameraXFactory, lifecycleOwner, previewView, createPreviewConfig(), null, createVideoCaptureConfig(audioEnabled), createImageAnalysisConfig(), this.imageAnalyzer, this.frameSampler, 8, null), faceDetectorAvc);
    }
}
