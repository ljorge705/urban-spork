package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel;

import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.MotionCameraController;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.FaceDetectorAvc;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.MotionCaptureViewModelImpl;
import com.onfido.dagger.internal.InstanceFactory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class MotionCaptureViewModelImpl_Factory_Impl implements MotionCaptureViewModelImpl.Factory {
    private final C0565MotionCaptureViewModelImpl_Factory delegateFactory;

    MotionCaptureViewModelImpl_Factory_Impl(C0565MotionCaptureViewModelImpl_Factory c0565MotionCaptureViewModelImpl_Factory) {
        this.delegateFactory = c0565MotionCaptureViewModelImpl_Factory;
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.MotionCaptureViewModelImpl.Factory
    public MotionCaptureViewModelImpl create(boolean z, MotionCameraController motionCameraController, FaceDetectorAvc faceDetectorAvc) {
        return this.delegateFactory.get(z, motionCameraController, faceDetectorAvc);
    }

    public static Provider<MotionCaptureViewModelImpl.Factory> create(C0565MotionCaptureViewModelImpl_Factory c0565MotionCaptureViewModelImpl_Factory) {
        return InstanceFactory.create(new MotionCaptureViewModelImpl_Factory_Impl(c0565MotionCaptureViewModelImpl_Factory));
    }
}
