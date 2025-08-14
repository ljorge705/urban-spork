package com.onfido.android.sdk.capture.document.supported.data.repository;

import com.onfido.android.sdk.capture.document.supported.data.remote.datasource.AllDocumentsDataSource;
import com.onfido.android.sdk.capture.document.supported.data.remote.datasource.SupportedDocumentsApi;
import com.onfido.android.sdk.capture.document.supported.data.remote.datasource.SupportedDocumentsLocalDataSource;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class RemoteSupportedDocumentsRepository_Factory implements Factory<RemoteSupportedDocumentsRepository> {
    private final Provider<AllDocumentsDataSource> allDocumentsDataSourceProvider;
    private final Provider<SupportedDocumentsApi> supportedDocumentsApiProvider;
    private final Provider<SupportedDocumentsLocalDataSource> supportedDocumentsLocalDataSourceProvider;

    public RemoteSupportedDocumentsRepository_Factory(Provider<SupportedDocumentsApi> provider, Provider<SupportedDocumentsLocalDataSource> provider2, Provider<AllDocumentsDataSource> provider3) {
        this.supportedDocumentsApiProvider = provider;
        this.supportedDocumentsLocalDataSourceProvider = provider2;
        this.allDocumentsDataSourceProvider = provider3;
    }

    public static RemoteSupportedDocumentsRepository_Factory create(Provider<SupportedDocumentsApi> provider, Provider<SupportedDocumentsLocalDataSource> provider2, Provider<AllDocumentsDataSource> provider3) {
        return new RemoteSupportedDocumentsRepository_Factory(provider, provider2, provider3);
    }

    public static RemoteSupportedDocumentsRepository newInstance(SupportedDocumentsApi supportedDocumentsApi, SupportedDocumentsLocalDataSource supportedDocumentsLocalDataSource, AllDocumentsDataSource allDocumentsDataSource) {
        return new RemoteSupportedDocumentsRepository(supportedDocumentsApi, supportedDocumentsLocalDataSource, allDocumentsDataSource);
    }

    @Override // com.onfido.javax.inject.Provider
    public RemoteSupportedDocumentsRepository get() {
        return newInstance(this.supportedDocumentsApiProvider.get(), this.supportedDocumentsLocalDataSourceProvider.get(), this.allDocumentsDataSourceProvider.get());
    }
}
