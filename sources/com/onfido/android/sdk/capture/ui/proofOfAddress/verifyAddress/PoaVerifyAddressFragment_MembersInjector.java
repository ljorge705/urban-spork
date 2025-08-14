package com.onfido.android.sdk.capture.ui.proofOfAddress.verifyAddress;

import com.onfido.dagger.MembersInjector;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class PoaVerifyAddressFragment_MembersInjector implements MembersInjector<PoaVerifyAddressFragment> {
    private final Provider<PoaVerifyAddressViewModel> poaViewModelProvider;

    public PoaVerifyAddressFragment_MembersInjector(Provider<PoaVerifyAddressViewModel> provider) {
        this.poaViewModelProvider = provider;
    }

    public static MembersInjector<PoaVerifyAddressFragment> create(Provider<PoaVerifyAddressViewModel> provider) {
        return new PoaVerifyAddressFragment_MembersInjector(provider);
    }

    public static void injectPoaViewModelProvider(PoaVerifyAddressFragment poaVerifyAddressFragment, Provider<PoaVerifyAddressViewModel> provider) {
        poaVerifyAddressFragment.poaViewModelProvider = provider;
    }

    @Override // com.onfido.dagger.MembersInjector
    public void injectMembers(PoaVerifyAddressFragment poaVerifyAddressFragment) {
        injectPoaViewModelProvider(poaVerifyAddressFragment, this.poaViewModelProvider);
    }
}
