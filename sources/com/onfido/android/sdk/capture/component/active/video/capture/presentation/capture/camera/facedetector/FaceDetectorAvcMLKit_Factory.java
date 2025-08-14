package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector;

import com.google.mlkit.vision.face.Face;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.OnfidoAnalytics;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class FaceDetectorAvcMLKit_Factory implements Factory<FaceDetectorAvcMLKit> {
    private final Provider<OnfidoAnalytics> analyticsProvider;
    private final Provider<FaceDetectorAvcResultMapper<Face>> resultMapperProvider;
    private final Provider<SchedulersProvider> schedulersProvider;

    public FaceDetectorAvcMLKit_Factory(Provider<OnfidoAnalytics> provider, Provider<SchedulersProvider> provider2, Provider<FaceDetectorAvcResultMapper<Face>> provider3) {
        this.analyticsProvider = provider;
        this.schedulersProvider = provider2;
        this.resultMapperProvider = provider3;
    }

    public static FaceDetectorAvcMLKit_Factory create(Provider<OnfidoAnalytics> provider, Provider<SchedulersProvider> provider2, Provider<FaceDetectorAvcResultMapper<Face>> provider3) {
        return new FaceDetectorAvcMLKit_Factory(provider, provider2, provider3);
    }

    public static FaceDetectorAvcMLKit newInstance(OnfidoAnalytics onfidoAnalytics, SchedulersProvider schedulersProvider, FaceDetectorAvcResultMapper<Face> faceDetectorAvcResultMapper) {
        return new FaceDetectorAvcMLKit(onfidoAnalytics, schedulersProvider, faceDetectorAvcResultMapper);
    }

    @Override // com.onfido.javax.inject.Provider
    public FaceDetectorAvcMLKit get() {
        return newInstance(this.analyticsProvider.get(), this.schedulersProvider.get(), this.resultMapperProvider.get());
    }
}
