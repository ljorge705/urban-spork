package com.onfido.android.sdk.capture.common.di.network;

import com.onfido.android.sdk.capture.internal.analytics.inhouse.network.OnfidoAnalyticsApi;
import com.onfido.api.client.OnfidoFetcher;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class NetworkModule_ProvideOnfidoAnalyticsApi$onfido_capture_sdk_core_releaseFactory implements Factory<OnfidoAnalyticsApi> {
    private final Provider<OnfidoFetcher> onfidoFetcherProvider;

    public NetworkModule_ProvideOnfidoAnalyticsApi$onfido_capture_sdk_core_releaseFactory(Provider<OnfidoFetcher> provider) {
        this.onfidoFetcherProvider = provider;
    }

    public static NetworkModule_ProvideOnfidoAnalyticsApi$onfido_capture_sdk_core_releaseFactory create(Provider<OnfidoFetcher> provider) {
        return new NetworkModule_ProvideOnfidoAnalyticsApi$onfido_capture_sdk_core_releaseFactory(provider);
    }

    public static OnfidoAnalyticsApi provideOnfidoAnalyticsApi$onfido_capture_sdk_core_release(OnfidoFetcher onfidoFetcher) {
        return (OnfidoAnalyticsApi) Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideOnfidoAnalyticsApi$onfido_capture_sdk_core_release(onfidoFetcher));
    }

    @Override // com.onfido.javax.inject.Provider
    public OnfidoAnalyticsApi get() {
        return provideOnfidoAnalyticsApi$onfido_capture_sdk_core_release(this.onfidoFetcherProvider.get());
    }
}
