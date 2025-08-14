package com.onfido.android.sdk.capture.internal.nfc;

import com.onfido.android.sdk.capture.OnfidoConfig;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class ShouldLaunchNfcFlowUseCase_Factory implements Factory<ShouldLaunchNfcFlowUseCase> {
    private final Provider<NfcInteractor> nfcInteractorProvider;
    private final Provider<OnfidoConfig> onfidoConfigProvider;

    public ShouldLaunchNfcFlowUseCase_Factory(Provider<NfcInteractor> provider, Provider<OnfidoConfig> provider2) {
        this.nfcInteractorProvider = provider;
        this.onfidoConfigProvider = provider2;
    }

    public static ShouldLaunchNfcFlowUseCase_Factory create(Provider<NfcInteractor> provider, Provider<OnfidoConfig> provider2) {
        return new ShouldLaunchNfcFlowUseCase_Factory(provider, provider2);
    }

    public static ShouldLaunchNfcFlowUseCase newInstance(NfcInteractor nfcInteractor, OnfidoConfig onfidoConfig) {
        return new ShouldLaunchNfcFlowUseCase(nfcInteractor, onfidoConfig);
    }

    @Override // com.onfido.javax.inject.Provider
    public ShouldLaunchNfcFlowUseCase get() {
        return newInstance(this.nfcInteractorProvider.get(), this.onfidoConfigProvider.get());
    }
}
