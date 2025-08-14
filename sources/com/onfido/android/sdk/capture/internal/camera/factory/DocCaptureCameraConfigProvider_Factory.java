package com.onfido.android.sdk.capture.internal.camera.factory;

import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class DocCaptureCameraConfigProvider_Factory implements Factory<DocCaptureCameraConfigProvider> {
    private final Provider<OnfidoRemoteConfig> onfidoRemoteConfigProvider;

    public DocCaptureCameraConfigProvider_Factory(Provider<OnfidoRemoteConfig> provider) {
        this.onfidoRemoteConfigProvider = provider;
    }

    public static DocCaptureCameraConfigProvider_Factory create(Provider<OnfidoRemoteConfig> provider) {
        return new DocCaptureCameraConfigProvider_Factory(provider);
    }

    public static DocCaptureCameraConfigProvider newInstance(OnfidoRemoteConfig onfidoRemoteConfig) {
        return new DocCaptureCameraConfigProvider(onfidoRemoteConfig);
    }

    @Override // com.onfido.javax.inject.Provider
    public DocCaptureCameraConfigProvider get() {
        return newInstance(this.onfidoRemoteConfigProvider.get());
    }
}
