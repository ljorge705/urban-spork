package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2;

import androidx.camera.viewfinder.CameraViewfinder;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.MotionCamera2Controller;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.FaceDetectorAvc;
import com.onfido.android.sdk.capture.internal.camera.camerax.PreviewConfig;
import com.onfido.android.sdk.capture.ui.camera.capture.VideoCaptureConfig;
import com.onfido.dagger.internal.InstanceFactory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class MotionCamera2Controller_Factory_Impl implements MotionCamera2Controller.Factory {
    private final C0539MotionCamera2Controller_Factory delegateFactory;

    MotionCamera2Controller_Factory_Impl(C0539MotionCamera2Controller_Factory c0539MotionCamera2Controller_Factory) {
        this.delegateFactory = c0539MotionCamera2Controller_Factory;
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.MotionCamera2Controller.Factory
    public MotionCamera2Controller create(CameraViewfinder cameraViewfinder, VideoCaptureConfig videoCaptureConfig, PreviewConfig previewConfig, FaceDetectorAvc faceDetectorAvc) {
        return this.delegateFactory.get(cameraViewfinder, videoCaptureConfig, previewConfig, faceDetectorAvc);
    }

    public static Provider<MotionCamera2Controller.Factory> create(C0539MotionCamera2Controller_Factory c0539MotionCamera2Controller_Factory) {
        return InstanceFactory.create(new MotionCamera2Controller_Factory_Impl(c0539MotionCamera2Controller_Factory));
    }
}
