package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector;

import com.onfido.android.sdk.capture.internal.analytics.inhouse.OnfidoAnalytics;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class MotionFaceDetectorFactory_Factory implements Factory<MotionFaceDetectorFactory> {
    private final Provider<OnfidoAnalytics> analyticsProvider;
    private final Provider<FaceDetectorAvcMLKit> faceDetectorAvcMLKitProvider;
    private final Provider<FaceDetectorAvcTflite> faceDetectorAvcTfliteProvider;

    public MotionFaceDetectorFactory_Factory(Provider<OnfidoAnalytics> provider, Provider<FaceDetectorAvcMLKit> provider2, Provider<FaceDetectorAvcTflite> provider3) {
        this.analyticsProvider = provider;
        this.faceDetectorAvcMLKitProvider = provider2;
        this.faceDetectorAvcTfliteProvider = provider3;
    }

    public static MotionFaceDetectorFactory_Factory create(Provider<OnfidoAnalytics> provider, Provider<FaceDetectorAvcMLKit> provider2, Provider<FaceDetectorAvcTflite> provider3) {
        return new MotionFaceDetectorFactory_Factory(provider, provider2, provider3);
    }

    public static MotionFaceDetectorFactory newInstance(OnfidoAnalytics onfidoAnalytics, Provider<FaceDetectorAvcMLKit> provider, Provider<FaceDetectorAvcTflite> provider2) {
        return new MotionFaceDetectorFactory(onfidoAnalytics, provider, provider2);
    }

    @Override // com.onfido.javax.inject.Provider
    public MotionFaceDetectorFactory get() {
        return newInstance(this.analyticsProvider.get(), this.faceDetectorAvcMLKitProvider, this.faceDetectorAvcTfliteProvider);
    }
}
