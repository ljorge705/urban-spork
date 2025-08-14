package com.onfido.workflow.internal.ui.processor;

import com.onfido.android.sdk.capture.document.DocumentConfigurationManager;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.navigation.Navigator;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;
import com.onfido.workflow.internal.ui.processor.nfc.NfcFlowHelper;
import com.onfido.workflow.internal.workflow.SubmitDocumentTaskCompletionUseCase;

/* loaded from: classes6.dex */
public final class CaptureDocumentHelper_Factory implements Factory<CaptureDocumentHelper> {
    private final Provider<DocumentConfigurationManager> documentConfigurationManagerProvider;
    private final Provider<Navigator> navigatorProvider;
    private final Provider<NfcFlowHelper> nfcFlowHelperProvider;
    private final Provider<OnfidoRemoteConfig> onfidoRemoteConfigProvider;
    private final Provider<SubmitDocumentTaskCompletionUseCase> submitTaskCompletionUseCaseProvider;

    public CaptureDocumentHelper_Factory(Provider<DocumentConfigurationManager> provider, Provider<NfcFlowHelper> provider2, Provider<Navigator> provider3, Provider<SubmitDocumentTaskCompletionUseCase> provider4, Provider<OnfidoRemoteConfig> provider5) {
        this.documentConfigurationManagerProvider = provider;
        this.nfcFlowHelperProvider = provider2;
        this.navigatorProvider = provider3;
        this.submitTaskCompletionUseCaseProvider = provider4;
        this.onfidoRemoteConfigProvider = provider5;
    }

    @Override // com.onfido.javax.inject.Provider
    public CaptureDocumentHelper get() {
        return newInstance(this.documentConfigurationManagerProvider.get(), this.nfcFlowHelperProvider.get(), this.navigatorProvider.get(), this.submitTaskCompletionUseCaseProvider.get(), this.onfidoRemoteConfigProvider.get());
    }

    public static CaptureDocumentHelper_Factory create(Provider<DocumentConfigurationManager> provider, Provider<NfcFlowHelper> provider2, Provider<Navigator> provider3, Provider<SubmitDocumentTaskCompletionUseCase> provider4, Provider<OnfidoRemoteConfig> provider5) {
        return new CaptureDocumentHelper_Factory(provider, provider2, provider3, provider4, provider5);
    }

    public static CaptureDocumentHelper newInstance(DocumentConfigurationManager documentConfigurationManager, NfcFlowHelper nfcFlowHelper, Navigator navigator, SubmitDocumentTaskCompletionUseCase submitDocumentTaskCompletionUseCase, OnfidoRemoteConfig onfidoRemoteConfig) {
        return new CaptureDocumentHelper(documentConfigurationManager, nfcFlowHelper, navigator, submitDocumentTaskCompletionUseCase, onfidoRemoteConfig);
    }
}
