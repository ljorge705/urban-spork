package com.onfido.android.sdk.capture.ui.nfc.scan;

import com.onfido.android.sdk.capture.common.result.HapticFeedback;
import com.onfido.android.sdk.capture.internal.nfc.NfcInteractor;
import com.onfido.android.sdk.capture.internal.service.AnnouncementService;
import com.onfido.android.sdk.capture.internal.util.DispatchersProvider;
import com.onfido.android.sdk.capture.ui.nfc.scan.NfcScanPresenter;
import com.onfido.dagger.MembersInjector;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class NfcScanFragment_MembersInjector implements MembersInjector<NfcScanFragment> {
    private final Provider<AnnouncementService> announcementServiceProvider;
    private final Provider<DispatchersProvider> dispatchersProvider;
    private final Provider<HapticFeedback> hapticFeedbackProvider;
    private final Provider<NfcInteractor> nfcInteractorProvider;
    private final Provider<NfcScanPresenter.Factory> presenterFactoryProvider;

    public NfcScanFragment_MembersInjector(Provider<NfcScanPresenter.Factory> provider, Provider<AnnouncementService> provider2, Provider<NfcInteractor> provider3, Provider<HapticFeedback> provider4, Provider<DispatchersProvider> provider5) {
        this.presenterFactoryProvider = provider;
        this.announcementServiceProvider = provider2;
        this.nfcInteractorProvider = provider3;
        this.hapticFeedbackProvider = provider4;
        this.dispatchersProvider = provider5;
    }

    public static MembersInjector<NfcScanFragment> create(Provider<NfcScanPresenter.Factory> provider, Provider<AnnouncementService> provider2, Provider<NfcInteractor> provider3, Provider<HapticFeedback> provider4, Provider<DispatchersProvider> provider5) {
        return new NfcScanFragment_MembersInjector(provider, provider2, provider3, provider4, provider5);
    }

    public static void injectAnnouncementService(NfcScanFragment nfcScanFragment, AnnouncementService announcementService) {
        nfcScanFragment.announcementService = announcementService;
    }

    public static void injectDispatchersProvider(NfcScanFragment nfcScanFragment, DispatchersProvider dispatchersProvider) {
        nfcScanFragment.dispatchersProvider = dispatchersProvider;
    }

    public static void injectHapticFeedback(NfcScanFragment nfcScanFragment, HapticFeedback hapticFeedback) {
        nfcScanFragment.hapticFeedback = hapticFeedback;
    }

    public static void injectNfcInteractor(NfcScanFragment nfcScanFragment, NfcInteractor nfcInteractor) {
        nfcScanFragment.nfcInteractor = nfcInteractor;
    }

    public static void injectPresenterFactory(NfcScanFragment nfcScanFragment, NfcScanPresenter.Factory factory) {
        nfcScanFragment.presenterFactory = factory;
    }

    @Override // com.onfido.dagger.MembersInjector
    public void injectMembers(NfcScanFragment nfcScanFragment) {
        injectPresenterFactory(nfcScanFragment, this.presenterFactoryProvider.get());
        injectAnnouncementService(nfcScanFragment, this.announcementServiceProvider.get());
        injectNfcInteractor(nfcScanFragment, this.nfcInteractorProvider.get());
        injectHapticFeedback(nfcScanFragment, this.hapticFeedbackProvider.get());
        injectDispatchersProvider(nfcScanFragment, this.dispatchersProvider.get());
    }
}
