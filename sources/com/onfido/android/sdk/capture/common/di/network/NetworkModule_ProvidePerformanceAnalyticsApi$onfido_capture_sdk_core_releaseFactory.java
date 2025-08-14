package com.onfido.android.sdk.capture.common.di.network;

import com.onfido.android.sdk.capture.internal.performance.repository.PerformanceAnalyticsApi;
import com.onfido.api.client.OnfidoFetcher;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class NetworkModule_ProvidePerformanceAnalyticsApi$onfido_capture_sdk_core_releaseFactory implements Factory<PerformanceAnalyticsApi> {
    private final Provider<OnfidoFetcher> onfidoFetcherProvider;

    public NetworkModule_ProvidePerformanceAnalyticsApi$onfido_capture_sdk_core_releaseFactory(Provider<OnfidoFetcher> provider) {
        this.onfidoFetcherProvider = provider;
    }

    public static NetworkModule_ProvidePerformanceAnalyticsApi$onfido_capture_sdk_core_releaseFactory create(Provider<OnfidoFetcher> provider) {
        return new NetworkModule_ProvidePerformanceAnalyticsApi$onfido_capture_sdk_core_releaseFactory(provider);
    }

    public static PerformanceAnalyticsApi providePerformanceAnalyticsApi$onfido_capture_sdk_core_release(OnfidoFetcher onfidoFetcher) {
        return (PerformanceAnalyticsApi) Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.providePerformanceAnalyticsApi$onfido_capture_sdk_core_release(onfidoFetcher));
    }

    @Override // com.onfido.javax.inject.Provider
    public PerformanceAnalyticsApi get() {
        return providePerformanceAnalyticsApi$onfido_capture_sdk_core_release(this.onfidoFetcherProvider.get());
    }
}
