package com.onfido.android.sdk.capture.ui.proofOfAddress.documentSelection;

import com.onfido.dagger.MembersInjector;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class PoaDocumentSelectionFragment_MembersInjector implements MembersInjector<PoaDocumentSelectionFragment> {
    private final Provider<PoaDocumentSelectionViewModel> poaViewModelFactoryProvider;

    public PoaDocumentSelectionFragment_MembersInjector(Provider<PoaDocumentSelectionViewModel> provider) {
        this.poaViewModelFactoryProvider = provider;
    }

    public static MembersInjector<PoaDocumentSelectionFragment> create(Provider<PoaDocumentSelectionViewModel> provider) {
        return new PoaDocumentSelectionFragment_MembersInjector(provider);
    }

    public static void injectPoaViewModelFactory(PoaDocumentSelectionFragment poaDocumentSelectionFragment, Provider<PoaDocumentSelectionViewModel> provider) {
        poaDocumentSelectionFragment.poaViewModelFactory = provider;
    }

    @Override // com.onfido.dagger.MembersInjector
    public void injectMembers(PoaDocumentSelectionFragment poaDocumentSelectionFragment) {
        injectPoaViewModelFactory(poaDocumentSelectionFragment, this.poaViewModelFactoryProvider);
    }
}
