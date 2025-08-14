package com.onfido.android.sdk.capture.ui.proofOfAddress.documentSubmission;

import com.onfido.android.sdk.capture.ui.proofOfAddress.network.PoaRepository;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class UploadPoaDocumentUseCase_Factory implements Factory<UploadPoaDocumentUseCase> {
    private final Provider<PoaRepository> repositoryProvider;

    public UploadPoaDocumentUseCase_Factory(Provider<PoaRepository> provider) {
        this.repositoryProvider = provider;
    }

    public static UploadPoaDocumentUseCase_Factory create(Provider<PoaRepository> provider) {
        return new UploadPoaDocumentUseCase_Factory(provider);
    }

    public static UploadPoaDocumentUseCase newInstance(PoaRepository poaRepository) {
        return new UploadPoaDocumentUseCase(poaRepository);
    }

    @Override // com.onfido.javax.inject.Provider
    public UploadPoaDocumentUseCase get() {
        return newInstance(this.repositoryProvider.get());
    }
}
