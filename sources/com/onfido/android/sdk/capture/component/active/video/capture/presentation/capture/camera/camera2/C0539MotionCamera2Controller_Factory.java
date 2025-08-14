package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2;

import androidx.camera.viewfinder.CameraViewfinder;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.MotionImage;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.recorder.RecorderWrapper;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.recorder.persistentsurface.IsPersistentSurfaceSupportedUseCase;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.FaceDetectorAvc;
import com.onfido.android.sdk.capture.internal.camera.camerax.FrameSampler;
import com.onfido.android.sdk.capture.internal.camera.camerax.PreviewConfig;
import com.onfido.android.sdk.capture.ui.camera.capture.VideoCaptureConfig;
import com.onfido.javax.inject.Provider;

/* renamed from: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.MotionCamera2Controller_Factory, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0539MotionCamera2Controller_Factory {
    private final Provider<Camera2Wrapper> camera2WrapperProvider;
    private final Provider<FrameSampler<MotionImage>> frameSamplerProvider;
    private final Provider<IsPersistentSurfaceSupportedUseCase> isPersistentSurfaceSupportedUseCaseProvider;
    private final Provider<RecorderWrapper> recorderWrapperProvider;

    public C0539MotionCamera2Controller_Factory(Provider<Camera2Wrapper> provider, Provider<RecorderWrapper> provider2, Provider<IsPersistentSurfaceSupportedUseCase> provider3, Provider<FrameSampler<MotionImage>> provider4) {
        this.camera2WrapperProvider = provider;
        this.recorderWrapperProvider = provider2;
        this.isPersistentSurfaceSupportedUseCaseProvider = provider3;
        this.frameSamplerProvider = provider4;
    }

    public static C0539MotionCamera2Controller_Factory create(Provider<Camera2Wrapper> provider, Provider<RecorderWrapper> provider2, Provider<IsPersistentSurfaceSupportedUseCase> provider3, Provider<FrameSampler<MotionImage>> provider4) {
        return new C0539MotionCamera2Controller_Factory(provider, provider2, provider3, provider4);
    }

    public static MotionCamera2Controller newInstance(CameraViewfinder cameraViewfinder, VideoCaptureConfig videoCaptureConfig, PreviewConfig previewConfig, FaceDetectorAvc faceDetectorAvc, Camera2Wrapper camera2Wrapper, RecorderWrapper recorderWrapper, IsPersistentSurfaceSupportedUseCase isPersistentSurfaceSupportedUseCase, FrameSampler<MotionImage> frameSampler) {
        return new MotionCamera2Controller(cameraViewfinder, videoCaptureConfig, previewConfig, faceDetectorAvc, camera2Wrapper, recorderWrapper, isPersistentSurfaceSupportedUseCase, frameSampler);
    }

    public MotionCamera2Controller get(CameraViewfinder cameraViewfinder, VideoCaptureConfig videoCaptureConfig, PreviewConfig previewConfig, FaceDetectorAvc faceDetectorAvc) {
        return newInstance(cameraViewfinder, videoCaptureConfig, previewConfig, faceDetectorAvc, this.camera2WrapperProvider.get(), this.recorderWrapperProvider.get(), this.isPersistentSurfaceSupportedUseCaseProvider.get(), this.frameSamplerProvider.get());
    }
}
