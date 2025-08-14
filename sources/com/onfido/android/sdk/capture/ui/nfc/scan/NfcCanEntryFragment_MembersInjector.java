package com.onfido.android.sdk.capture.ui.nfc.scan;

import com.onfido.android.sdk.capture.ui.nfc.scan.NfcCanEntryViewModel;
import com.onfido.dagger.MembersInjector;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class NfcCanEntryFragment_MembersInjector implements MembersInjector<NfcCanEntryFragment> {
    private final Provider<NfcCanEntryViewModel.Factory> nfcViewModelFactoryProvider;

    public NfcCanEntryFragment_MembersInjector(Provider<NfcCanEntryViewModel.Factory> provider) {
        this.nfcViewModelFactoryProvider = provider;
    }

    public static MembersInjector<NfcCanEntryFragment> create(Provider<NfcCanEntryViewModel.Factory> provider) {
        return new NfcCanEntryFragment_MembersInjector(provider);
    }

    public static void injectNfcViewModelFactory(NfcCanEntryFragment nfcCanEntryFragment, NfcCanEntryViewModel.Factory factory) {
        nfcCanEntryFragment.nfcViewModelFactory = factory;
    }

    @Override // com.onfido.dagger.MembersInjector
    public void injectMembers(NfcCanEntryFragment nfcCanEntryFragment) {
        injectNfcViewModelFactory(nfcCanEntryFragment, this.nfcViewModelFactoryProvider.get());
    }
}
