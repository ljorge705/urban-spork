package com.onfido.android.sdk.capture.internal.config;

import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.android.sdk.capture.network.OnfidoApiService;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class SDKConfigRepository_Factory implements Factory<SDKConfigRepository> {
    private final Provider<MutableOnfidoRemoteConfig> mutableOnfidoRemoteConfigProvider;
    private final Provider<OnfidoApiService> onfidoAPIProvider;
    private final Provider<OnfidoRemoteConfig> onfidoRemoteConfigProvider;
    private final Provider<SchedulersProvider> schedulersProvider;

    public SDKConfigRepository_Factory(Provider<OnfidoApiService> provider, Provider<SchedulersProvider> provider2, Provider<OnfidoRemoteConfig> provider3, Provider<MutableOnfidoRemoteConfig> provider4) {
        this.onfidoAPIProvider = provider;
        this.schedulersProvider = provider2;
        this.onfidoRemoteConfigProvider = provider3;
        this.mutableOnfidoRemoteConfigProvider = provider4;
    }

    public static SDKConfigRepository_Factory create(Provider<OnfidoApiService> provider, Provider<SchedulersProvider> provider2, Provider<OnfidoRemoteConfig> provider3, Provider<MutableOnfidoRemoteConfig> provider4) {
        return new SDKConfigRepository_Factory(provider, provider2, provider3, provider4);
    }

    public static SDKConfigRepository newInstance(OnfidoApiService onfidoApiService, SchedulersProvider schedulersProvider, OnfidoRemoteConfig onfidoRemoteConfig, MutableOnfidoRemoteConfig mutableOnfidoRemoteConfig) {
        return new SDKConfigRepository(onfidoApiService, schedulersProvider, onfidoRemoteConfig, mutableOnfidoRemoteConfig);
    }

    @Override // com.onfido.javax.inject.Provider
    public SDKConfigRepository get() {
        return newInstance(this.onfidoAPIProvider.get(), this.schedulersProvider.get(), this.onfidoRemoteConfigProvider.get(), this.mutableOnfidoRemoteConfigProvider.get());
    }
}
