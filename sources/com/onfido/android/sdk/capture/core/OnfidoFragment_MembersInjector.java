package com.onfido.android.sdk.capture.core;

import com.onfido.dagger.MembersInjector;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class OnfidoFragment_MembersInjector implements MembersInjector<OnfidoFragment> {
    private final Provider<OnfidoViewModel> viewModelProvider;

    public OnfidoFragment_MembersInjector(Provider<OnfidoViewModel> provider) {
        this.viewModelProvider = provider;
    }

    public static MembersInjector<OnfidoFragment> create(Provider<OnfidoViewModel> provider) {
        return new OnfidoFragment_MembersInjector(provider);
    }

    public static void injectViewModel(OnfidoFragment onfidoFragment, OnfidoViewModel onfidoViewModel) {
        onfidoFragment.viewModel = onfidoViewModel;
    }

    @Override // com.onfido.dagger.MembersInjector
    public void injectMembers(OnfidoFragment onfidoFragment) {
        injectViewModel(onfidoFragment, this.viewModelProvider.get());
    }
}
