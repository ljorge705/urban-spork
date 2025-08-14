package com.onfido.android.sdk.capture.common.di;

import com.onfido.android.sdk.capture.document.supported.data.remote.datasource.AllDocumentsDataSource;
import com.onfido.android.sdk.capture.document.supported.data.remote.datasource.AllDocumentsRemoteDataSourceImpl;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class SdkProductionModule_ProvideAllDocumentDataSourceFactory implements Factory<AllDocumentsDataSource> {
    private final Provider<AllDocumentsRemoteDataSourceImpl> allDocumentsRemoteDataSourceImplProvider;

    public SdkProductionModule_ProvideAllDocumentDataSourceFactory(Provider<AllDocumentsRemoteDataSourceImpl> provider) {
        this.allDocumentsRemoteDataSourceImplProvider = provider;
    }

    public static SdkProductionModule_ProvideAllDocumentDataSourceFactory create(Provider<AllDocumentsRemoteDataSourceImpl> provider) {
        return new SdkProductionModule_ProvideAllDocumentDataSourceFactory(provider);
    }

    public static AllDocumentsDataSource provideAllDocumentDataSource(AllDocumentsRemoteDataSourceImpl allDocumentsRemoteDataSourceImpl) {
        return (AllDocumentsDataSource) Preconditions.checkNotNullFromProvides(SdkProductionModule.INSTANCE.provideAllDocumentDataSource(allDocumentsRemoteDataSourceImpl));
    }

    @Override // com.onfido.javax.inject.Provider
    public AllDocumentsDataSource get() {
        return provideAllDocumentDataSource(this.allDocumentsRemoteDataSourceImplProvider.get());
    }
}
