package com.onfido.android.sdk.capture.component.active.video.capture.di.capture;

import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.FaceDetectorAvcResultMapper;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.FaceDetectorAvcResultMapperTestImpl;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.FaceDetectorAvcTfliteResultMapper;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.tfmodel.Detection;
import com.onfido.android.sdk.capture.ui.options.MotionCaptureVariantOptions;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class MotionCaptureModule_Companion_ProvideTFLiteResultMapperFactory implements Factory<FaceDetectorAvcResultMapper<Detection>> {
    private final Provider<MotionCaptureVariantOptions> motionOptionsProvider;
    private final Provider<FaceDetectorAvcTfliteResultMapper> tFLiteResultMapperProvider;
    private final Provider<FaceDetectorAvcResultMapperTestImpl<Object>> testImplProvider;

    public MotionCaptureModule_Companion_ProvideTFLiteResultMapperFactory(Provider<MotionCaptureVariantOptions> provider, Provider<FaceDetectorAvcResultMapperTestImpl<Object>> provider2, Provider<FaceDetectorAvcTfliteResultMapper> provider3) {
        this.motionOptionsProvider = provider;
        this.testImplProvider = provider2;
        this.tFLiteResultMapperProvider = provider3;
    }

    public static MotionCaptureModule_Companion_ProvideTFLiteResultMapperFactory create(Provider<MotionCaptureVariantOptions> provider, Provider<FaceDetectorAvcResultMapperTestImpl<Object>> provider2, Provider<FaceDetectorAvcTfliteResultMapper> provider3) {
        return new MotionCaptureModule_Companion_ProvideTFLiteResultMapperFactory(provider, provider2, provider3);
    }

    public static FaceDetectorAvcResultMapper<Detection> provideTFLiteResultMapper(MotionCaptureVariantOptions motionCaptureVariantOptions, FaceDetectorAvcResultMapperTestImpl<Object> faceDetectorAvcResultMapperTestImpl, Provider<FaceDetectorAvcTfliteResultMapper> provider) {
        return (FaceDetectorAvcResultMapper) Preconditions.checkNotNullFromProvides(MotionCaptureModule.INSTANCE.provideTFLiteResultMapper(motionCaptureVariantOptions, faceDetectorAvcResultMapperTestImpl, provider));
    }

    @Override // com.onfido.javax.inject.Provider
    public FaceDetectorAvcResultMapper<Detection> get() {
        return provideTFLiteResultMapper(this.motionOptionsProvider.get(), this.testImplProvider.get(), this.tFLiteResultMapperProvider);
    }
}
