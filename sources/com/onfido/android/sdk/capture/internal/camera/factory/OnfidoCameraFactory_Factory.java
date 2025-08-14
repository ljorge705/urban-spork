package com.onfido.android.sdk.capture.internal.camera.factory;

import android.content.Context;
import com.onfido.android.sdk.capture.internal.camera.OnfidoImage;
import com.onfido.android.sdk.capture.internal.camera.camerax.CameraX;
import com.onfido.android.sdk.capture.internal.camera.camerax.FrameSampler;
import com.onfido.android.sdk.capture.internal.camera.camerax.ImageAnalyzer;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class OnfidoCameraFactory_Factory implements Factory<OnfidoCameraFactory> {
    private final Provider<Context> applicationContextProvider;
    private final Provider<CameraX.Factory> cameraxFactoryProvider;
    private final Provider<FrameSampler<OnfidoImage>> frameSamplerProvider;
    private final Provider<ImageAnalyzer<OnfidoImage>> imageAnalyzerProvider;
    private final Provider<OnfidoRemoteConfig> onfidoRemoteConfigProvider;
    private final Provider<CameraUseCaseConfigProvider> useCaseConfigProvider;

    public OnfidoCameraFactory_Factory(Provider<Context> provider, Provider<OnfidoRemoteConfig> provider2, Provider<CameraX.Factory> provider3, Provider<CameraUseCaseConfigProvider> provider4, Provider<FrameSampler<OnfidoImage>> provider5, Provider<ImageAnalyzer<OnfidoImage>> provider6) {
        this.applicationContextProvider = provider;
        this.onfidoRemoteConfigProvider = provider2;
        this.cameraxFactoryProvider = provider3;
        this.useCaseConfigProvider = provider4;
        this.frameSamplerProvider = provider5;
        this.imageAnalyzerProvider = provider6;
    }

    public static OnfidoCameraFactory_Factory create(Provider<Context> provider, Provider<OnfidoRemoteConfig> provider2, Provider<CameraX.Factory> provider3, Provider<CameraUseCaseConfigProvider> provider4, Provider<FrameSampler<OnfidoImage>> provider5, Provider<ImageAnalyzer<OnfidoImage>> provider6) {
        return new OnfidoCameraFactory_Factory(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static OnfidoCameraFactory newInstance(Context context, OnfidoRemoteConfig onfidoRemoteConfig, CameraX.Factory factory, CameraUseCaseConfigProvider cameraUseCaseConfigProvider, FrameSampler<OnfidoImage> frameSampler, ImageAnalyzer<OnfidoImage> imageAnalyzer) {
        return new OnfidoCameraFactory(context, onfidoRemoteConfig, factory, cameraUseCaseConfigProvider, frameSampler, imageAnalyzer);
    }

    @Override // com.onfido.javax.inject.Provider
    public OnfidoCameraFactory get() {
        return newInstance(this.applicationContextProvider.get(), this.onfidoRemoteConfigProvider.get(), this.cameraxFactoryProvider.get(), this.useCaseConfigProvider.get(), this.frameSamplerProvider.get(), this.imageAnalyzerProvider.get());
    }
}
