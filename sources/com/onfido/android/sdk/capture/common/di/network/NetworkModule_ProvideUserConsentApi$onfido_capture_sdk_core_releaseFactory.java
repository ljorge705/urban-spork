package com.onfido.android.sdk.capture.common.di.network;

import com.onfido.android.sdk.capture.ui.userconsent.network.UserConsentApi;
import com.onfido.api.client.OnfidoFetcher;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class NetworkModule_ProvideUserConsentApi$onfido_capture_sdk_core_releaseFactory implements Factory<UserConsentApi> {
    private final Provider<OnfidoFetcher> onfidoFetcherProvider;

    public NetworkModule_ProvideUserConsentApi$onfido_capture_sdk_core_releaseFactory(Provider<OnfidoFetcher> provider) {
        this.onfidoFetcherProvider = provider;
    }

    public static NetworkModule_ProvideUserConsentApi$onfido_capture_sdk_core_releaseFactory create(Provider<OnfidoFetcher> provider) {
        return new NetworkModule_ProvideUserConsentApi$onfido_capture_sdk_core_releaseFactory(provider);
    }

    public static UserConsentApi provideUserConsentApi$onfido_capture_sdk_core_release(OnfidoFetcher onfidoFetcher) {
        return (UserConsentApi) Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideUserConsentApi$onfido_capture_sdk_core_release(onfidoFetcher));
    }

    @Override // com.onfido.javax.inject.Provider
    public UserConsentApi get() {
        return provideUserConsentApi$onfido_capture_sdk_core_release(this.onfidoFetcherProvider.get());
    }
}
