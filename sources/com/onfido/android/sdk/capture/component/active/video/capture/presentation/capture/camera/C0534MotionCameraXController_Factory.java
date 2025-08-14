package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera;

import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.FaceDetectorAvc;
import com.onfido.android.sdk.capture.internal.camera.camerax.CameraX;

/* renamed from: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.MotionCameraXController_Factory, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0534MotionCameraXController_Factory {
    public static C0534MotionCameraXController_Factory create() {
        return new C0534MotionCameraXController_Factory();
    }

    public static MotionCameraXController newInstance(CameraX cameraX, FaceDetectorAvc faceDetectorAvc) {
        return new MotionCameraXController(cameraX, faceDetectorAvc);
    }

    public MotionCameraXController get(CameraX cameraX, FaceDetectorAvc faceDetectorAvc) {
        return newInstance(cameraX, faceDetectorAvc);
    }
}
