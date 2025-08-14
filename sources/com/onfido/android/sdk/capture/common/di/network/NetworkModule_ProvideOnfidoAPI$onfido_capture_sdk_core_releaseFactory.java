package com.onfido.android.sdk.capture.common.di.network;

import com.onfido.android.sdk.capture.analytics.IdentityInteractor;
import com.onfido.android.sdk.capture.internal.token.OnfidoTokenProvider;
import com.onfido.api.client.OnfidoAPI;
import com.onfido.api.client.OnfidoFetcher;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;
import com.onfido.javax.inject.Provider;
import kotlinx.serialization.json.Json;

/* loaded from: classes2.dex */
public final class NetworkModule_ProvideOnfidoAPI$onfido_capture_sdk_core_releaseFactory implements Factory<OnfidoAPI> {
    private final Provider<IdentityInteractor> identityInteractorProvider;
    private final Provider<Json> jsonProvider;
    private final Provider<OnfidoFetcher> onfidoFetcherProvider;
    private final Provider<OnfidoTokenProvider> tokenProvider;

    public NetworkModule_ProvideOnfidoAPI$onfido_capture_sdk_core_releaseFactory(Provider<OnfidoTokenProvider> provider, Provider<OnfidoFetcher> provider2, Provider<IdentityInteractor> provider3, Provider<Json> provider4) {
        this.tokenProvider = provider;
        this.onfidoFetcherProvider = provider2;
        this.identityInteractorProvider = provider3;
        this.jsonProvider = provider4;
    }

    public static NetworkModule_ProvideOnfidoAPI$onfido_capture_sdk_core_releaseFactory create(Provider<OnfidoTokenProvider> provider, Provider<OnfidoFetcher> provider2, Provider<IdentityInteractor> provider3, Provider<Json> provider4) {
        return new NetworkModule_ProvideOnfidoAPI$onfido_capture_sdk_core_releaseFactory(provider, provider2, provider3, provider4);
    }

    public static OnfidoAPI provideOnfidoAPI$onfido_capture_sdk_core_release(OnfidoTokenProvider onfidoTokenProvider, OnfidoFetcher onfidoFetcher, IdentityInteractor identityInteractor, Json json) {
        return (OnfidoAPI) Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideOnfidoAPI$onfido_capture_sdk_core_release(onfidoTokenProvider, onfidoFetcher, identityInteractor, json));
    }

    @Override // com.onfido.javax.inject.Provider
    public OnfidoAPI get() {
        return provideOnfidoAPI$onfido_capture_sdk_core_release(this.tokenProvider.get(), this.onfidoFetcherProvider.get(), this.identityInteractorProvider.get(), this.jsonProvider.get());
    }
}
