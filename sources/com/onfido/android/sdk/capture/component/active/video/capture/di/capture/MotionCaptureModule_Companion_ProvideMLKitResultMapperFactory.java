package com.onfido.android.sdk.capture.component.active.video.capture.di.capture;

import com.google.mlkit.vision.face.Face;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.FaceDetectorAvcMLKitResultMapper;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.FaceDetectorAvcResultMapper;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.FaceDetectorAvcResultMapperTestImpl;
import com.onfido.android.sdk.capture.ui.options.MotionCaptureVariantOptions;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class MotionCaptureModule_Companion_ProvideMLKitResultMapperFactory implements Factory<FaceDetectorAvcResultMapper<Face>> {
    private final Provider<FaceDetectorAvcMLKitResultMapper> mLKitResultMapperProvider;
    private final Provider<MotionCaptureVariantOptions> motionOptionsProvider;
    private final Provider<FaceDetectorAvcResultMapperTestImpl<Object>> testImplProvider;

    public MotionCaptureModule_Companion_ProvideMLKitResultMapperFactory(Provider<MotionCaptureVariantOptions> provider, Provider<FaceDetectorAvcResultMapperTestImpl<Object>> provider2, Provider<FaceDetectorAvcMLKitResultMapper> provider3) {
        this.motionOptionsProvider = provider;
        this.testImplProvider = provider2;
        this.mLKitResultMapperProvider = provider3;
    }

    public static MotionCaptureModule_Companion_ProvideMLKitResultMapperFactory create(Provider<MotionCaptureVariantOptions> provider, Provider<FaceDetectorAvcResultMapperTestImpl<Object>> provider2, Provider<FaceDetectorAvcMLKitResultMapper> provider3) {
        return new MotionCaptureModule_Companion_ProvideMLKitResultMapperFactory(provider, provider2, provider3);
    }

    public static FaceDetectorAvcResultMapper<Face> provideMLKitResultMapper(MotionCaptureVariantOptions motionCaptureVariantOptions, FaceDetectorAvcResultMapperTestImpl<Object> faceDetectorAvcResultMapperTestImpl, Provider<FaceDetectorAvcMLKitResultMapper> provider) {
        return (FaceDetectorAvcResultMapper) Preconditions.checkNotNullFromProvides(MotionCaptureModule.INSTANCE.provideMLKitResultMapper(motionCaptureVariantOptions, faceDetectorAvcResultMapperTestImpl, provider));
    }

    @Override // com.onfido.javax.inject.Provider
    public FaceDetectorAvcResultMapper<Face> get() {
        return provideMLKitResultMapper(this.motionOptionsProvider.get(), this.testImplProvider.get(), this.mLKitResultMapperProvider);
    }
}
