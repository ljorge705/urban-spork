package com.onfido.android.sdk.capture.internal.usecase;

import com.onfido.android.sdk.capture.document.DocumentConfigurationManager;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.NfcTracker;
import com.onfido.android.sdk.capture.internal.nfc.NfcInteractor;
import com.onfido.android.sdk.capture.ui.camera.NfcPropertiesService;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class NfcUseCase_Factory implements Factory<NfcUseCase> {
    private final Provider<DocumentConfigurationManager> documentConfigurationManagerProvider;
    private final Provider<NfcInteractor> nfcInteractorProvider;
    private final Provider<NfcPropertiesService> nfcPropertiesServiceProvider;
    private final Provider<NfcTracker> nfcTrackerProvider;

    public NfcUseCase_Factory(Provider<NfcInteractor> provider, Provider<DocumentConfigurationManager> provider2, Provider<NfcPropertiesService> provider3, Provider<NfcTracker> provider4) {
        this.nfcInteractorProvider = provider;
        this.documentConfigurationManagerProvider = provider2;
        this.nfcPropertiesServiceProvider = provider3;
        this.nfcTrackerProvider = provider4;
    }

    public static NfcUseCase_Factory create(Provider<NfcInteractor> provider, Provider<DocumentConfigurationManager> provider2, Provider<NfcPropertiesService> provider3, Provider<NfcTracker> provider4) {
        return new NfcUseCase_Factory(provider, provider2, provider3, provider4);
    }

    public static NfcUseCase newInstance(NfcInteractor nfcInteractor, DocumentConfigurationManager documentConfigurationManager, NfcPropertiesService nfcPropertiesService, NfcTracker nfcTracker) {
        return new NfcUseCase(nfcInteractor, documentConfigurationManager, nfcPropertiesService, nfcTracker);
    }

    @Override // com.onfido.javax.inject.Provider
    public NfcUseCase get() {
        return newInstance(this.nfcInteractorProvider.get(), this.documentConfigurationManagerProvider.get(), this.nfcPropertiesServiceProvider.get(), this.nfcTrackerProvider.get());
    }
}
