package com.onfido.android.sdk.capture.ui.nfc;

import com.onfido.android.sdk.capture.network.OnfidoApiService;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;
import kotlinx.serialization.json.Json;

/* loaded from: classes2.dex */
public final class NfcDataRepository_Factory implements Factory<NfcDataRepository> {
    private final Provider<Json> jsonParserProvider;
    private final Provider<OnfidoApiService> onfidoApiServiceProvider;

    public NfcDataRepository_Factory(Provider<OnfidoApiService> provider, Provider<Json> provider2) {
        this.onfidoApiServiceProvider = provider;
        this.jsonParserProvider = provider2;
    }

    public static NfcDataRepository_Factory create(Provider<OnfidoApiService> provider, Provider<Json> provider2) {
        return new NfcDataRepository_Factory(provider, provider2);
    }

    public static NfcDataRepository newInstance(OnfidoApiService onfidoApiService, Json json) {
        return new NfcDataRepository(onfidoApiService, json);
    }

    @Override // com.onfido.javax.inject.Provider
    public NfcDataRepository get() {
        return newInstance(this.onfidoApiServiceProvider.get(), this.jsonParserProvider.get());
    }
}
