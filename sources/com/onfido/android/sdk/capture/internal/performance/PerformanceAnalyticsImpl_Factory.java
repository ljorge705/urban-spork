package com.onfido.android.sdk.capture.internal.performance;

import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.performance.repository.PerformanceAnalyticsNetwork;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class PerformanceAnalyticsImpl_Factory implements Factory<PerformanceAnalyticsImpl> {
    private final Provider<PerformanceAnalyticsNetwork> performanceAnalyticsNetworkProvider;
    private final Provider<OnfidoRemoteConfig> remoteConfigProvider;

    public PerformanceAnalyticsImpl_Factory(Provider<PerformanceAnalyticsNetwork> provider, Provider<OnfidoRemoteConfig> provider2) {
        this.performanceAnalyticsNetworkProvider = provider;
        this.remoteConfigProvider = provider2;
    }

    public static PerformanceAnalyticsImpl_Factory create(Provider<PerformanceAnalyticsNetwork> provider, Provider<OnfidoRemoteConfig> provider2) {
        return new PerformanceAnalyticsImpl_Factory(provider, provider2);
    }

    public static PerformanceAnalyticsImpl newInstance(PerformanceAnalyticsNetwork performanceAnalyticsNetwork, OnfidoRemoteConfig onfidoRemoteConfig) {
        return new PerformanceAnalyticsImpl(performanceAnalyticsNetwork, onfidoRemoteConfig);
    }

    @Override // com.onfido.javax.inject.Provider
    public PerformanceAnalyticsImpl get() {
        return newInstance(this.performanceAnalyticsNetworkProvider.get(), this.remoteConfigProvider.get());
    }
}
