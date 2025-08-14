package com.onfido.android.sdk.capture.ui.proofOfAddress.documentDetails;

import com.onfido.android.sdk.capture.ui.proofOfAddress.PoaUtils;
import com.onfido.dagger.MembersInjector;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class PoaDocumentDetailsFragment_MembersInjector implements MembersInjector<PoaDocumentDetailsFragment> {
    private final Provider<PoaUtils> poaUtilsProvider;
    private final Provider<PoaDocumentDetailsViewModel> poaViewModelFactoryProvider;

    public PoaDocumentDetailsFragment_MembersInjector(Provider<PoaDocumentDetailsViewModel> provider, Provider<PoaUtils> provider2) {
        this.poaViewModelFactoryProvider = provider;
        this.poaUtilsProvider = provider2;
    }

    public static MembersInjector<PoaDocumentDetailsFragment> create(Provider<PoaDocumentDetailsViewModel> provider, Provider<PoaUtils> provider2) {
        return new PoaDocumentDetailsFragment_MembersInjector(provider, provider2);
    }

    public static void injectPoaUtils(PoaDocumentDetailsFragment poaDocumentDetailsFragment, PoaUtils poaUtils) {
        poaDocumentDetailsFragment.poaUtils = poaUtils;
    }

    public static void injectPoaViewModelFactory(PoaDocumentDetailsFragment poaDocumentDetailsFragment, Provider<PoaDocumentDetailsViewModel> provider) {
        poaDocumentDetailsFragment.poaViewModelFactory = provider;
    }

    @Override // com.onfido.dagger.MembersInjector
    public void injectMembers(PoaDocumentDetailsFragment poaDocumentDetailsFragment) {
        injectPoaViewModelFactory(poaDocumentDetailsFragment, this.poaViewModelFactoryProvider);
        injectPoaUtils(poaDocumentDetailsFragment, this.poaUtilsProvider.get());
    }
}
