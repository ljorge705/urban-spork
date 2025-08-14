package com.onfido.workflow.internal.ui.processor.nfc;

import com.onfido.android.sdk.capture.internal.navigation.Navigator;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes6.dex */
public final class NfcFlowHelper_Factory implements Factory<NfcFlowHelper> {
    private final Provider<WorkflowNfcCreateDocumentUseCase> createDocumentUseCaseProvider;
    private final Provider<Navigator> navigatorProvider;

    public NfcFlowHelper_Factory(Provider<Navigator> provider, Provider<WorkflowNfcCreateDocumentUseCase> provider2) {
        this.navigatorProvider = provider;
        this.createDocumentUseCaseProvider = provider2;
    }

    @Override // com.onfido.javax.inject.Provider
    public NfcFlowHelper get() {
        return newInstance(this.navigatorProvider.get(), this.createDocumentUseCaseProvider.get());
    }

    public static NfcFlowHelper_Factory create(Provider<Navigator> provider, Provider<WorkflowNfcCreateDocumentUseCase> provider2) {
        return new NfcFlowHelper_Factory(provider, provider2);
    }

    public static NfcFlowHelper newInstance(Navigator navigator, WorkflowNfcCreateDocumentUseCase workflowNfcCreateDocumentUseCase) {
        return new NfcFlowHelper(navigator, workflowNfcCreateDocumentUseCase);
    }
}
