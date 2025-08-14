package com.onfido.android.sdk.capture.ui.documentselection.host;

import com.onfido.android.sdk.capture.ui.documentselection.host.DocumentSelectionHostViewModel;
import com.onfido.dagger.MembersInjector;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class DocumentSelectionHostFragment_MembersInjector implements MembersInjector<DocumentSelectionHostFragment> {
    private final Provider<DocumentSelectionHostViewModel.Factory> factoryProvider;

    public DocumentSelectionHostFragment_MembersInjector(Provider<DocumentSelectionHostViewModel.Factory> provider) {
        this.factoryProvider = provider;
    }

    public static MembersInjector<DocumentSelectionHostFragment> create(Provider<DocumentSelectionHostViewModel.Factory> provider) {
        return new DocumentSelectionHostFragment_MembersInjector(provider);
    }

    public static void injectFactory(DocumentSelectionHostFragment documentSelectionHostFragment, DocumentSelectionHostViewModel.Factory factory) {
        documentSelectionHostFragment.factory = factory;
    }

    @Override // com.onfido.dagger.MembersInjector
    public void injectMembers(DocumentSelectionHostFragment documentSelectionHostFragment) {
        injectFactory(documentSelectionHostFragment, this.factoryProvider.get());
    }
}
