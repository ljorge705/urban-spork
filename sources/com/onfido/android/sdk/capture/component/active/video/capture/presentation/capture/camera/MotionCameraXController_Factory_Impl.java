package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera;

import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.MotionCameraXController;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.FaceDetectorAvc;
import com.onfido.android.sdk.capture.internal.camera.camerax.CameraX;
import com.onfido.dagger.internal.InstanceFactory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class MotionCameraXController_Factory_Impl implements MotionCameraXController.Factory {
    private final C0534MotionCameraXController_Factory delegateFactory;

    MotionCameraXController_Factory_Impl(C0534MotionCameraXController_Factory c0534MotionCameraXController_Factory) {
        this.delegateFactory = c0534MotionCameraXController_Factory;
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.MotionCameraXController.Factory
    public MotionCameraXController create(CameraX cameraX, FaceDetectorAvc faceDetectorAvc) {
        return this.delegateFactory.get(cameraX, faceDetectorAvc);
    }

    public static Provider<MotionCameraXController.Factory> create(C0534MotionCameraXController_Factory c0534MotionCameraXController_Factory) {
        return InstanceFactory.create(new MotionCameraXController_Factory_Impl(c0534MotionCameraXController_Factory));
    }
}
