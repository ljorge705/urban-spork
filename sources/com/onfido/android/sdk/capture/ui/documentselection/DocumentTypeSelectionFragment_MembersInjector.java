package com.onfido.android.sdk.capture.ui.documentselection;

import com.onfido.dagger.MembersInjector;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class DocumentTypeSelectionFragment_MembersInjector implements MembersInjector<DocumentTypeSelectionFragment> {
    private final Provider<DocumentTypeSelectionViewModel> viewModelProvider;

    public DocumentTypeSelectionFragment_MembersInjector(Provider<DocumentTypeSelectionViewModel> provider) {
        this.viewModelProvider = provider;
    }

    public static MembersInjector<DocumentTypeSelectionFragment> create(Provider<DocumentTypeSelectionViewModel> provider) {
        return new DocumentTypeSelectionFragment_MembersInjector(provider);
    }

    public static void injectViewModelProvider(DocumentTypeSelectionFragment documentTypeSelectionFragment, Provider<DocumentTypeSelectionViewModel> provider) {
        documentTypeSelectionFragment.viewModelProvider = provider;
    }

    @Override // com.onfido.dagger.MembersInjector
    public void injectMembers(DocumentTypeSelectionFragment documentTypeSelectionFragment) {
        injectViewModelProvider(documentTypeSelectionFragment, this.viewModelProvider);
    }
}
