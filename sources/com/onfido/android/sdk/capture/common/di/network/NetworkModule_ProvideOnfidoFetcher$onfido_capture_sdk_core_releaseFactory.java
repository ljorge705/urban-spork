package com.onfido.android.sdk.capture.common.di.network;

import com.onfido.android.sdk.capture.OnfidoConfig;
import com.onfido.android.sdk.capture.analytics.IdentityInteractor;
import com.onfido.android.sdk.capture.internal.token.OnfidoTokenProvider;
import com.onfido.api.client.OnfidoFetcher;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;
import com.onfido.javax.inject.Provider;
import kotlinx.serialization.json.Json;
import okhttp3.OkHttpClient;

/* loaded from: classes2.dex */
public final class NetworkModule_ProvideOnfidoFetcher$onfido_capture_sdk_core_releaseFactory implements Factory<OnfidoFetcher> {
    private final Provider<IdentityInteractor> identityInteractorProvider;
    private final Provider<Json> jsonProvider;
    private final Provider<OkHttpClient> okHttpClientProvider;
    private final Provider<OnfidoConfig> onfidoConfigProvider;
    private final Provider<OnfidoTokenProvider> tokenProvider;

    public NetworkModule_ProvideOnfidoFetcher$onfido_capture_sdk_core_releaseFactory(Provider<OkHttpClient> provider, Provider<OnfidoTokenProvider> provider2, Provider<OnfidoConfig> provider3, Provider<IdentityInteractor> provider4, Provider<Json> provider5) {
        this.okHttpClientProvider = provider;
        this.tokenProvider = provider2;
        this.onfidoConfigProvider = provider3;
        this.identityInteractorProvider = provider4;
        this.jsonProvider = provider5;
    }

    public static NetworkModule_ProvideOnfidoFetcher$onfido_capture_sdk_core_releaseFactory create(Provider<OkHttpClient> provider, Provider<OnfidoTokenProvider> provider2, Provider<OnfidoConfig> provider3, Provider<IdentityInteractor> provider4, Provider<Json> provider5) {
        return new NetworkModule_ProvideOnfidoFetcher$onfido_capture_sdk_core_releaseFactory(provider, provider2, provider3, provider4, provider5);
    }

    public static OnfidoFetcher provideOnfidoFetcher$onfido_capture_sdk_core_release(OkHttpClient okHttpClient, OnfidoTokenProvider onfidoTokenProvider, OnfidoConfig onfidoConfig, IdentityInteractor identityInteractor, Json json) {
        return (OnfidoFetcher) Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideOnfidoFetcher$onfido_capture_sdk_core_release(okHttpClient, onfidoTokenProvider, onfidoConfig, identityInteractor, json));
    }

    @Override // com.onfido.javax.inject.Provider
    public OnfidoFetcher get() {
        return provideOnfidoFetcher$onfido_capture_sdk_core_release(this.okHttpClientProvider.get(), this.tokenProvider.get(), this.onfidoConfigProvider.get(), this.identityInteractorProvider.get(), this.jsonProvider.get());
    }
}
