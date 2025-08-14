package com.onfido.android.sdk.capture.internal.nfc;

import com.onfido.android.sdk.capture.ui.nfc.scan.NfcScanPresenter;
import com.onfido.dagger.MembersInjector;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class NfcService_MembersInjector implements MembersInjector<NfcService> {
    private final Provider<NfcInteractor> nfcInteractorProvider;
    private final Provider<NfcScanPresenter.Factory> presenterFactoryProvider;

    public NfcService_MembersInjector(Provider<NfcScanPresenter.Factory> provider, Provider<NfcInteractor> provider2) {
        this.presenterFactoryProvider = provider;
        this.nfcInteractorProvider = provider2;
    }

    public static MembersInjector<NfcService> create(Provider<NfcScanPresenter.Factory> provider, Provider<NfcInteractor> provider2) {
        return new NfcService_MembersInjector(provider, provider2);
    }

    public static void injectNfcInteractor(NfcService nfcService, NfcInteractor nfcInteractor) {
        nfcService.nfcInteractor = nfcInteractor;
    }

    public static void injectPresenterFactory(NfcService nfcService, NfcScanPresenter.Factory factory) {
        nfcService.presenterFactory = factory;
    }

    @Override // com.onfido.dagger.MembersInjector
    public void injectMembers(NfcService nfcService) {
        injectPresenterFactory(nfcService, this.presenterFactoryProvider.get());
        injectNfcInteractor(nfcService, this.nfcInteractorProvider.get());
    }
}
