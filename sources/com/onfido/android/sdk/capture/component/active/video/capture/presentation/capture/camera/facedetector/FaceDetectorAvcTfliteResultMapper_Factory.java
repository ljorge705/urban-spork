package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector;

import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.util.CoordinatesTransformer;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.util.YawAngleCalculator;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class FaceDetectorAvcTfliteResultMapper_Factory implements Factory<FaceDetectorAvcTfliteResultMapper> {
    private final Provider<CoordinatesTransformer> coordinatesTransformerProvider;
    private final Provider<YawAngleCalculator> yawAngleCalculatorProvider;

    public FaceDetectorAvcTfliteResultMapper_Factory(Provider<CoordinatesTransformer> provider, Provider<YawAngleCalculator> provider2) {
        this.coordinatesTransformerProvider = provider;
        this.yawAngleCalculatorProvider = provider2;
    }

    public static FaceDetectorAvcTfliteResultMapper_Factory create(Provider<CoordinatesTransformer> provider, Provider<YawAngleCalculator> provider2) {
        return new FaceDetectorAvcTfliteResultMapper_Factory(provider, provider2);
    }

    public static FaceDetectorAvcTfliteResultMapper newInstance(CoordinatesTransformer coordinatesTransformer, YawAngleCalculator yawAngleCalculator) {
        return new FaceDetectorAvcTfliteResultMapper(coordinatesTransformer, yawAngleCalculator);
    }

    @Override // com.onfido.javax.inject.Provider
    public FaceDetectorAvcTfliteResultMapper get() {
        return newInstance(this.coordinatesTransformerProvider.get(), this.yawAngleCalculatorProvider.get());
    }
}
