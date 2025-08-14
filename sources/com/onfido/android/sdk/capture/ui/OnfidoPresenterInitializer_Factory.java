package com.onfido.android.sdk.capture.ui;

import com.onfido.android.sdk.capture.document.supported.domain.usecase.GetSupportedDocumentsUseCase;
import com.onfido.android.sdk.capture.internal.config.SDKConfigRepository;
import com.onfido.android.sdk.capture.ui.userconsent.UserConsentRepository;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class OnfidoPresenterInitializer_Factory implements Factory<OnfidoPresenterInitializer> {
    private final Provider<GetSupportedDocumentsUseCase> getSupportedDocumentsUseCaseProvider;
    private final Provider<SDKConfigRepository> sdkConfigRepositoryProvider;
    private final Provider<UserConsentRepository> userConsentRepositoryProvider;

    public OnfidoPresenterInitializer_Factory(Provider<SDKConfigRepository> provider, Provider<UserConsentRepository> provider2, Provider<GetSupportedDocumentsUseCase> provider3) {
        this.sdkConfigRepositoryProvider = provider;
        this.userConsentRepositoryProvider = provider2;
        this.getSupportedDocumentsUseCaseProvider = provider3;
    }

    public static OnfidoPresenterInitializer_Factory create(Provider<SDKConfigRepository> provider, Provider<UserConsentRepository> provider2, Provider<GetSupportedDocumentsUseCase> provider3) {
        return new OnfidoPresenterInitializer_Factory(provider, provider2, provider3);
    }

    public static OnfidoPresenterInitializer newInstance(SDKConfigRepository sDKConfigRepository, UserConsentRepository userConsentRepository, GetSupportedDocumentsUseCase getSupportedDocumentsUseCase) {
        return new OnfidoPresenterInitializer(sDKConfigRepository, userConsentRepository, getSupportedDocumentsUseCase);
    }

    @Override // com.onfido.javax.inject.Provider
    public OnfidoPresenterInitializer get() {
        return newInstance(this.sdkConfigRepositoryProvider.get(), this.userConsentRepositoryProvider.get(), this.getSupportedDocumentsUseCaseProvider.get());
    }
}
