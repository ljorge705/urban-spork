package com.onfido.android.sdk.capture.common.di.network;

import com.onfido.android.sdk.capture.document.supported.data.remote.datasource.SupportedDocumentsApi;
import com.onfido.api.client.OnfidoFetcher;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class NetworkModule_ProvideSupportedDocumentsApi$onfido_capture_sdk_core_releaseFactory implements Factory<SupportedDocumentsApi> {
    private final Provider<OnfidoFetcher> onfidoFetcherProvider;

    public NetworkModule_ProvideSupportedDocumentsApi$onfido_capture_sdk_core_releaseFactory(Provider<OnfidoFetcher> provider) {
        this.onfidoFetcherProvider = provider;
    }

    public static NetworkModule_ProvideSupportedDocumentsApi$onfido_capture_sdk_core_releaseFactory create(Provider<OnfidoFetcher> provider) {
        return new NetworkModule_ProvideSupportedDocumentsApi$onfido_capture_sdk_core_releaseFactory(provider);
    }

    public static SupportedDocumentsApi provideSupportedDocumentsApi$onfido_capture_sdk_core_release(OnfidoFetcher onfidoFetcher) {
        return (SupportedDocumentsApi) Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideSupportedDocumentsApi$onfido_capture_sdk_core_release(onfidoFetcher));
    }

    @Override // com.onfido.javax.inject.Provider
    public SupportedDocumentsApi get() {
        return provideSupportedDocumentsApi$onfido_capture_sdk_core_release(this.onfidoFetcherProvider.get());
    }
}
