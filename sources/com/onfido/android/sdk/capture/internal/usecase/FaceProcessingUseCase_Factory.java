package com.onfido.android.sdk.capture.internal.usecase;

import com.onfido.android.sdk.capture.detector.face.FaceDetector;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class FaceProcessingUseCase_Factory implements Factory<FaceProcessingUseCase> {
    private final Provider<FaceDetector> faceDetectorGoogleProvider;
    private final Provider<SchedulersProvider> schedulersProvider;

    public FaceProcessingUseCase_Factory(Provider<FaceDetector> provider, Provider<SchedulersProvider> provider2) {
        this.faceDetectorGoogleProvider = provider;
        this.schedulersProvider = provider2;
    }

    public static FaceProcessingUseCase_Factory create(Provider<FaceDetector> provider, Provider<SchedulersProvider> provider2) {
        return new FaceProcessingUseCase_Factory(provider, provider2);
    }

    public static FaceProcessingUseCase newInstance(FaceDetector faceDetector, SchedulersProvider schedulersProvider) {
        return new FaceProcessingUseCase(faceDetector, schedulersProvider);
    }

    @Override // com.onfido.javax.inject.Provider
    public FaceProcessingUseCase get() {
        return newInstance(this.faceDetectorGoogleProvider.get(), this.schedulersProvider.get());
    }
}
