package com.onfido.android.sdk.capture.common.di.network;

import com.onfido.android.sdk.capture.ui.proofOfAddress.network.ProofOfAddressApi;
import com.onfido.api.client.OnfidoFetcher;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class NetworkModule_ProvideProofOfAddressApi$onfido_capture_sdk_core_releaseFactory implements Factory<ProofOfAddressApi> {
    private final Provider<OnfidoFetcher> onfidoFetcherProvider;

    public NetworkModule_ProvideProofOfAddressApi$onfido_capture_sdk_core_releaseFactory(Provider<OnfidoFetcher> provider) {
        this.onfidoFetcherProvider = provider;
    }

    public static NetworkModule_ProvideProofOfAddressApi$onfido_capture_sdk_core_releaseFactory create(Provider<OnfidoFetcher> provider) {
        return new NetworkModule_ProvideProofOfAddressApi$onfido_capture_sdk_core_releaseFactory(provider);
    }

    public static ProofOfAddressApi provideProofOfAddressApi$onfido_capture_sdk_core_release(OnfidoFetcher onfidoFetcher) {
        return (ProofOfAddressApi) Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideProofOfAddressApi$onfido_capture_sdk_core_release(onfidoFetcher));
    }

    @Override // com.onfido.javax.inject.Provider
    public ProofOfAddressApi get() {
        return provideProofOfAddressApi$onfido_capture_sdk_core_release(this.onfidoFetcherProvider.get());
    }
}
