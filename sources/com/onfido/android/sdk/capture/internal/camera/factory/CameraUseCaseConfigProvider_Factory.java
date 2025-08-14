package com.onfido.android.sdk.capture.internal.camera.factory;

import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class CameraUseCaseConfigProvider_Factory implements Factory<CameraUseCaseConfigProvider> {
    private final Provider<DocCaptureCameraConfigProvider> docCaptureCameraConfigProvider;
    private final Provider<OnfidoRemoteConfig> onfidoRemoteConfigProvider;

    public CameraUseCaseConfigProvider_Factory(Provider<OnfidoRemoteConfig> provider, Provider<DocCaptureCameraConfigProvider> provider2) {
        this.onfidoRemoteConfigProvider = provider;
        this.docCaptureCameraConfigProvider = provider2;
    }

    public static CameraUseCaseConfigProvider_Factory create(Provider<OnfidoRemoteConfig> provider, Provider<DocCaptureCameraConfigProvider> provider2) {
        return new CameraUseCaseConfigProvider_Factory(provider, provider2);
    }

    public static CameraUseCaseConfigProvider newInstance(OnfidoRemoteConfig onfidoRemoteConfig, DocCaptureCameraConfigProvider docCaptureCameraConfigProvider) {
        return new CameraUseCaseConfigProvider(onfidoRemoteConfig, docCaptureCameraConfigProvider);
    }

    @Override // com.onfido.javax.inject.Provider
    public CameraUseCaseConfigProvider get() {
        return newInstance(this.onfidoRemoteConfigProvider.get(), this.docCaptureCameraConfigProvider.get());
    }
}
