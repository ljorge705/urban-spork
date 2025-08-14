package com.onfido.android.sdk.capture.ui.documentselection;

import com.onfido.android.sdk.capture.internal.ui.countryselection.SupportedDocumentsRepository;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class DocumentTypeSelectionViewModel_Factory implements Factory<DocumentTypeSelectionViewModel> {
    private final Provider<SupportedDocumentsRepository> supportedDocumentsRepositoryProvider;

    public DocumentTypeSelectionViewModel_Factory(Provider<SupportedDocumentsRepository> provider) {
        this.supportedDocumentsRepositoryProvider = provider;
    }

    public static DocumentTypeSelectionViewModel_Factory create(Provider<SupportedDocumentsRepository> provider) {
        return new DocumentTypeSelectionViewModel_Factory(provider);
    }

    public static DocumentTypeSelectionViewModel newInstance(SupportedDocumentsRepository supportedDocumentsRepository) {
        return new DocumentTypeSelectionViewModel(supportedDocumentsRepository);
    }

    @Override // com.onfido.javax.inject.Provider
    public DocumentTypeSelectionViewModel get() {
        return newInstance(this.supportedDocumentsRepositoryProvider.get());
    }
}
