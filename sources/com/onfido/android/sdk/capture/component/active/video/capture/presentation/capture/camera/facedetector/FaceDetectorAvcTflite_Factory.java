package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector;

import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.tfmodel.BlazeFaceModel;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.tfmodel.Detection;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class FaceDetectorAvcTflite_Factory implements Factory<FaceDetectorAvcTflite> {
    private final Provider<BlazeFaceModel> blazeFaceModelProvider;
    private final Provider<FaceDetectorAvcResultMapper<Detection>> resultMapperProvider;
    private final Provider<SchedulersProvider> schedulersProvider;

    public FaceDetectorAvcTflite_Factory(Provider<BlazeFaceModel> provider, Provider<SchedulersProvider> provider2, Provider<FaceDetectorAvcResultMapper<Detection>> provider3) {
        this.blazeFaceModelProvider = provider;
        this.schedulersProvider = provider2;
        this.resultMapperProvider = provider3;
    }

    public static FaceDetectorAvcTflite_Factory create(Provider<BlazeFaceModel> provider, Provider<SchedulersProvider> provider2, Provider<FaceDetectorAvcResultMapper<Detection>> provider3) {
        return new FaceDetectorAvcTflite_Factory(provider, provider2, provider3);
    }

    public static FaceDetectorAvcTflite newInstance(BlazeFaceModel blazeFaceModel, SchedulersProvider schedulersProvider, FaceDetectorAvcResultMapper<Detection> faceDetectorAvcResultMapper) {
        return new FaceDetectorAvcTflite(blazeFaceModel, schedulersProvider, faceDetectorAvcResultMapper);
    }

    @Override // com.onfido.javax.inject.Provider
    public FaceDetectorAvcTflite get() {
        return newInstance(this.blazeFaceModelProvider.get(), this.schedulersProvider.get(), this.resultMapperProvider.get());
    }
}
