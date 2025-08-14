package com.onfido.android.sdk.capture.document.supported.data.remote.datasource;

import com.onfido.android.sdk.capture.document.supported.data.SupportedDocumentMapper;
import com.onfido.android.sdk.capture.network.OnfidoApiService;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class AllDocumentsRemoteDataSourceImpl_Factory implements Factory<AllDocumentsRemoteDataSourceImpl> {
    private final Provider<OnfidoApiService> apiServiceProvider;
    private final Provider<SupportedDocumentMapper> documentMapperProvider;

    public AllDocumentsRemoteDataSourceImpl_Factory(Provider<OnfidoApiService> provider, Provider<SupportedDocumentMapper> provider2) {
        this.apiServiceProvider = provider;
        this.documentMapperProvider = provider2;
    }

    public static AllDocumentsRemoteDataSourceImpl_Factory create(Provider<OnfidoApiService> provider, Provider<SupportedDocumentMapper> provider2) {
        return new AllDocumentsRemoteDataSourceImpl_Factory(provider, provider2);
    }

    public static AllDocumentsRemoteDataSourceImpl newInstance(OnfidoApiService onfidoApiService, SupportedDocumentMapper supportedDocumentMapper) {
        return new AllDocumentsRemoteDataSourceImpl(onfidoApiService, supportedDocumentMapper);
    }

    @Override // com.onfido.javax.inject.Provider
    public AllDocumentsRemoteDataSourceImpl get() {
        return newInstance(this.apiServiceProvider.get(), this.documentMapperProvider.get());
    }
}
