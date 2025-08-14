package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera;

import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.MotionCameraXController;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.MotionCamera2Controller;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.OnfidoAnalytics;
import com.onfido.android.sdk.capture.internal.camera.camerax.CameraX;
import com.onfido.android.sdk.capture.internal.camera.camerax.FrameSampler;
import com.onfido.android.sdk.capture.internal.camera.camerax.ImageAnalyzer;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class MotionCameraControllerFactory_Factory implements Factory<MotionCameraControllerFactory> {
    private final Provider<OnfidoAnalytics> analyticsProvider;
    private final Provider<MotionCamera2Controller.Factory> camera2ControllerFactoryProvider;
    private final Provider<MotionCameraXController.Factory> cameraXControllerFactoryProvider;
    private final Provider<CameraX.Factory> cameraXFactoryProvider;
    private final Provider<FrameSampler<MotionImage>> frameSamplerProvider;
    private final Provider<ImageAnalyzer<MotionImage>> imageAnalyzerProvider;

    public MotionCameraControllerFactory_Factory(Provider<CameraX.Factory> provider, Provider<FrameSampler<MotionImage>> provider2, Provider<ImageAnalyzer<MotionImage>> provider3, Provider<MotionCameraXController.Factory> provider4, Provider<MotionCamera2Controller.Factory> provider5, Provider<OnfidoAnalytics> provider6) {
        this.cameraXFactoryProvider = provider;
        this.frameSamplerProvider = provider2;
        this.imageAnalyzerProvider = provider3;
        this.cameraXControllerFactoryProvider = provider4;
        this.camera2ControllerFactoryProvider = provider5;
        this.analyticsProvider = provider6;
    }

    public static MotionCameraControllerFactory_Factory create(Provider<CameraX.Factory> provider, Provider<FrameSampler<MotionImage>> provider2, Provider<ImageAnalyzer<MotionImage>> provider3, Provider<MotionCameraXController.Factory> provider4, Provider<MotionCamera2Controller.Factory> provider5, Provider<OnfidoAnalytics> provider6) {
        return new MotionCameraControllerFactory_Factory(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static MotionCameraControllerFactory newInstance(CameraX.Factory factory, FrameSampler<MotionImage> frameSampler, ImageAnalyzer<MotionImage> imageAnalyzer, MotionCameraXController.Factory factory2, MotionCamera2Controller.Factory factory3, OnfidoAnalytics onfidoAnalytics) {
        return new MotionCameraControllerFactory(factory, frameSampler, imageAnalyzer, factory2, factory3, onfidoAnalytics);
    }

    @Override // com.onfido.javax.inject.Provider
    public MotionCameraControllerFactory get() {
        return newInstance(this.cameraXFactoryProvider.get(), this.frameSamplerProvider.get(), this.imageAnalyzerProvider.get(), this.cameraXControllerFactoryProvider.get(), this.camera2ControllerFactoryProvider.get(), this.analyticsProvider.get());
    }
}
