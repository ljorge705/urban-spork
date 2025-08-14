package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector;

import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.util.CoordinatesTransformer;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class FaceDetectorAvcMLKitResultMapper_Factory implements Factory<FaceDetectorAvcMLKitResultMapper> {
    private final Provider<CoordinatesTransformer> coordinatesTransformerProvider;

    public FaceDetectorAvcMLKitResultMapper_Factory(Provider<CoordinatesTransformer> provider) {
        this.coordinatesTransformerProvider = provider;
    }

    public static FaceDetectorAvcMLKitResultMapper_Factory create(Provider<CoordinatesTransformer> provider) {
        return new FaceDetectorAvcMLKitResultMapper_Factory(provider);
    }

    public static FaceDetectorAvcMLKitResultMapper newInstance(CoordinatesTransformer coordinatesTransformer) {
        return new FaceDetectorAvcMLKitResultMapper(coordinatesTransformer);
    }

    @Override // com.onfido.javax.inject.Provider
    public FaceDetectorAvcMLKitResultMapper get() {
        return newInstance(this.coordinatesTransformerProvider.get());
    }
}
