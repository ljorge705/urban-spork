package com.onfido.android.sdk.capture.internal.ui.countryselection;

import com.onfido.android.sdk.capture.OnfidoConfig;
import com.onfido.android.sdk.capture.document.supported.data.remote.datasource.AllDocumentsDataSource;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class OnfidoSupportedDocumentsRepository_Factory implements Factory<OnfidoSupportedDocumentsRepository> {
    private final Provider<AllDocumentsDataSource> allDocumentsDataSourceProvider;
    private final Provider<OnfidoConfig> onfidoConfigProvider;

    public OnfidoSupportedDocumentsRepository_Factory(Provider<OnfidoConfig> provider, Provider<AllDocumentsDataSource> provider2) {
        this.onfidoConfigProvider = provider;
        this.allDocumentsDataSourceProvider = provider2;
    }

    public static OnfidoSupportedDocumentsRepository_Factory create(Provider<OnfidoConfig> provider, Provider<AllDocumentsDataSource> provider2) {
        return new OnfidoSupportedDocumentsRepository_Factory(provider, provider2);
    }

    public static OnfidoSupportedDocumentsRepository newInstance(OnfidoConfig onfidoConfig, AllDocumentsDataSource allDocumentsDataSource) {
        return new OnfidoSupportedDocumentsRepository(onfidoConfig, allDocumentsDataSource);
    }

    @Override // com.onfido.javax.inject.Provider
    public OnfidoSupportedDocumentsRepository get() {
        return newInstance(this.onfidoConfigProvider.get(), this.allDocumentsDataSourceProvider.get());
    }
}
