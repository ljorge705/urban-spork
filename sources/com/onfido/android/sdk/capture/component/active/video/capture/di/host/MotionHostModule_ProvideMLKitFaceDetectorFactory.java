package com.onfido.android.sdk.capture.component.active.video.capture.di.host;

import com.google.mlkit.vision.face.FaceDetector;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;

/* loaded from: classes2.dex */
public final class MotionHostModule_ProvideMLKitFaceDetectorFactory implements Factory<FaceDetector> {
    private final MotionHostModule module;

    public MotionHostModule_ProvideMLKitFaceDetectorFactory(MotionHostModule motionHostModule) {
        this.module = motionHostModule;
    }

    public static MotionHostModule_ProvideMLKitFaceDetectorFactory create(MotionHostModule motionHostModule) {
        return new MotionHostModule_ProvideMLKitFaceDetectorFactory(motionHostModule);
    }

    public static FaceDetector provideMLKitFaceDetector(MotionHostModule motionHostModule) {
        return (FaceDetector) Preconditions.checkNotNullFromProvides(motionHostModule.provideMLKitFaceDetector());
    }

    @Override // com.onfido.javax.inject.Provider
    public FaceDetector get() {
        return provideMLKitFaceDetector(this.module);
    }
}
