package com.onfido.android.sdk.capture.ui.proofOfAddress.network;

import com.onfido.android.sdk.capture.network.OnfidoApiService;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class PoaRepository_Factory implements Factory<PoaRepository> {
    private final Provider<OnfidoApiService> onfidoApiServiceProvider;
    private final Provider<ProofOfAddressApi> proofOfAddressApiProvider;

    public PoaRepository_Factory(Provider<ProofOfAddressApi> provider, Provider<OnfidoApiService> provider2) {
        this.proofOfAddressApiProvider = provider;
        this.onfidoApiServiceProvider = provider2;
    }

    public static PoaRepository_Factory create(Provider<ProofOfAddressApi> provider, Provider<OnfidoApiService> provider2) {
        return new PoaRepository_Factory(provider, provider2);
    }

    public static PoaRepository newInstance(ProofOfAddressApi proofOfAddressApi, OnfidoApiService onfidoApiService) {
        return new PoaRepository(proofOfAddressApi, onfidoApiService);
    }

    @Override // com.onfido.javax.inject.Provider
    public PoaRepository get() {
        return newInstance(this.proofOfAddressApiProvider.get(), this.onfidoApiServiceProvider.get());
    }
}
