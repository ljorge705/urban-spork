package com.onfido.android.sdk.capture.common.di.network;

import com.onfido.android.sdk.capture.internal.analytics.inhouse.network.OnfidoAssetsApi;
import com.onfido.api.client.OnfidoFetcher;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class NetworkModule_ProvideOnfidoAssetsApi$onfido_capture_sdk_core_releaseFactory implements Factory<OnfidoAssetsApi> {
    private final Provider<OnfidoFetcher> onfidoFetcherProvider;

    public NetworkModule_ProvideOnfidoAssetsApi$onfido_capture_sdk_core_releaseFactory(Provider<OnfidoFetcher> provider) {
        this.onfidoFetcherProvider = provider;
    }

    public static NetworkModule_ProvideOnfidoAssetsApi$onfido_capture_sdk_core_releaseFactory create(Provider<OnfidoFetcher> provider) {
        return new NetworkModule_ProvideOnfidoAssetsApi$onfido_capture_sdk_core_releaseFactory(provider);
    }

    public static OnfidoAssetsApi provideOnfidoAssetsApi$onfido_capture_sdk_core_release(OnfidoFetcher onfidoFetcher) {
        return (OnfidoAssetsApi) Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideOnfidoAssetsApi$onfido_capture_sdk_core_release(onfidoFetcher));
    }

    @Override // com.onfido.javax.inject.Provider
    public OnfidoAssetsApi get() {
        return provideOnfidoAssetsApi$onfido_capture_sdk_core_release(this.onfidoFetcherProvider.get());
    }
}
