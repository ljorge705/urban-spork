package com.onfido.android.sdk.capture.common.di.network;

import com.onfido.android.sdk.capture.internal.util.logging.network.OnfidoLoggerApi;
import com.onfido.api.client.OnfidoFetcher;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class NetworkModule_ProvideOnfidoLoggerApi$onfido_capture_sdk_core_releaseFactory implements Factory<OnfidoLoggerApi> {
    private final Provider<OnfidoFetcher> onfidoFetcherProvider;

    public NetworkModule_ProvideOnfidoLoggerApi$onfido_capture_sdk_core_releaseFactory(Provider<OnfidoFetcher> provider) {
        this.onfidoFetcherProvider = provider;
    }

    public static NetworkModule_ProvideOnfidoLoggerApi$onfido_capture_sdk_core_releaseFactory create(Provider<OnfidoFetcher> provider) {
        return new NetworkModule_ProvideOnfidoLoggerApi$onfido_capture_sdk_core_releaseFactory(provider);
    }

    public static OnfidoLoggerApi provideOnfidoLoggerApi$onfido_capture_sdk_core_release(OnfidoFetcher onfidoFetcher) {
        return (OnfidoLoggerApi) Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideOnfidoLoggerApi$onfido_capture_sdk_core_release(onfidoFetcher));
    }

    @Override // com.onfido.javax.inject.Provider
    public OnfidoLoggerApi get() {
        return provideOnfidoLoggerApi$onfido_capture_sdk_core_release(this.onfidoFetcherProvider.get());
    }
}
