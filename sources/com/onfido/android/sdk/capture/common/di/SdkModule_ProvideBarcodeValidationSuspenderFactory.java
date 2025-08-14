package com.onfido.android.sdk.capture.common.di;

import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.android.sdk.capture.validation.BarcodeValidationSuspender;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class SdkModule_ProvideBarcodeValidationSuspenderFactory implements Factory<BarcodeValidationSuspender> {
    private final SdkModule module;
    private final Provider<OnfidoRemoteConfig> remoteConfigProvider;
    private final Provider<SchedulersProvider> schedulersProvider;

    public SdkModule_ProvideBarcodeValidationSuspenderFactory(SdkModule sdkModule, Provider<OnfidoRemoteConfig> provider, Provider<SchedulersProvider> provider2) {
        this.module = sdkModule;
        this.remoteConfigProvider = provider;
        this.schedulersProvider = provider2;
    }

    public static SdkModule_ProvideBarcodeValidationSuspenderFactory create(SdkModule sdkModule, Provider<OnfidoRemoteConfig> provider, Provider<SchedulersProvider> provider2) {
        return new SdkModule_ProvideBarcodeValidationSuspenderFactory(sdkModule, provider, provider2);
    }

    public static BarcodeValidationSuspender provideBarcodeValidationSuspender(SdkModule sdkModule, OnfidoRemoteConfig onfidoRemoteConfig, SchedulersProvider schedulersProvider) {
        return (BarcodeValidationSuspender) Preconditions.checkNotNullFromProvides(sdkModule.provideBarcodeValidationSuspender(onfidoRemoteConfig, schedulersProvider));
    }

    @Override // com.onfido.javax.inject.Provider
    public BarcodeValidationSuspender get() {
        return provideBarcodeValidationSuspender(this.module, this.remoteConfigProvider.get(), this.schedulersProvider.get());
    }
}
