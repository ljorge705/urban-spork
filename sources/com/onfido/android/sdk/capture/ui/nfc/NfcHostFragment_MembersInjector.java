package com.onfido.android.sdk.capture.ui.nfc;

import com.onfido.android.sdk.capture.ui.nfc.NfcHostViewModel;
import com.onfido.dagger.MembersInjector;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class NfcHostFragment_MembersInjector implements MembersInjector<NfcHostFragment> {
    private final Provider<NfcHostViewModel.Factory> nfcViewModelFactoryProvider;

    public NfcHostFragment_MembersInjector(Provider<NfcHostViewModel.Factory> provider) {
        this.nfcViewModelFactoryProvider = provider;
    }

    public static MembersInjector<NfcHostFragment> create(Provider<NfcHostViewModel.Factory> provider) {
        return new NfcHostFragment_MembersInjector(provider);
    }

    public static void injectNfcViewModelFactory(NfcHostFragment nfcHostFragment, NfcHostViewModel.Factory factory) {
        nfcHostFragment.nfcViewModelFactory = factory;
    }

    @Override // com.onfido.dagger.MembersInjector
    public void injectMembers(NfcHostFragment nfcHostFragment) {
        injectNfcViewModelFactory(nfcHostFragment, this.nfcViewModelFactoryProvider.get());
    }
}
