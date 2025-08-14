package com.onfido.android.sdk.capture.validation;

import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class BarcodeValidationSuspender_Factory implements Factory<BarcodeValidationSuspender> {
    private final Provider<OnfidoRemoteConfig> remoteConfigProvider;
    private final Provider<SchedulersProvider> schedulersProvider;

    public BarcodeValidationSuspender_Factory(Provider<OnfidoRemoteConfig> provider, Provider<SchedulersProvider> provider2) {
        this.remoteConfigProvider = provider;
        this.schedulersProvider = provider2;
    }

    public static BarcodeValidationSuspender_Factory create(Provider<OnfidoRemoteConfig> provider, Provider<SchedulersProvider> provider2) {
        return new BarcodeValidationSuspender_Factory(provider, provider2);
    }

    public static BarcodeValidationSuspender newInstance(OnfidoRemoteConfig onfidoRemoteConfig, SchedulersProvider schedulersProvider) {
        return new BarcodeValidationSuspender(onfidoRemoteConfig, schedulersProvider);
    }

    @Override // com.onfido.javax.inject.Provider
    public BarcodeValidationSuspender get() {
        return newInstance(this.remoteConfigProvider.get(), this.schedulersProvider.get());
    }
}
