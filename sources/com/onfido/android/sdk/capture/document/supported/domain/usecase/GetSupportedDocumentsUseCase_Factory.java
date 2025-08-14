package com.onfido.android.sdk.capture.document.supported.domain.usecase;

import com.onfido.android.sdk.capture.document.supported.data.repository.RemoteSupportedDocumentsRepository;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class GetSupportedDocumentsUseCase_Factory implements Factory<GetSupportedDocumentsUseCase> {
    private final Provider<RemoteSupportedDocumentsRepository> remoteSupportedDocumentsRepositoryProvider;

    public GetSupportedDocumentsUseCase_Factory(Provider<RemoteSupportedDocumentsRepository> provider) {
        this.remoteSupportedDocumentsRepositoryProvider = provider;
    }

    public static GetSupportedDocumentsUseCase_Factory create(Provider<RemoteSupportedDocumentsRepository> provider) {
        return new GetSupportedDocumentsUseCase_Factory(provider);
    }

    public static GetSupportedDocumentsUseCase newInstance(RemoteSupportedDocumentsRepository remoteSupportedDocumentsRepository) {
        return new GetSupportedDocumentsUseCase(remoteSupportedDocumentsRepository);
    }

    @Override // com.onfido.javax.inject.Provider
    public GetSupportedDocumentsUseCase get() {
        return newInstance(this.remoteSupportedDocumentsRepositoryProvider.get());
    }
}
