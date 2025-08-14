package com.onfido.workflow.internal.ui.processor;

import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.navigation.Navigator;
import com.onfido.android.sdk.capture.internal.nfc.NfcInteractor;
import com.onfido.android.sdk.workflow.internal.workflow.tasks.documentupload.WorkflowSupportedDocumentsStore;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes6.dex */
public final class DisplayDocumentCaptureFlowProcessor_Factory implements Factory<DisplayDocumentCaptureFlowProcessor> {
    private final Provider<CaptureDocumentHelper> captureDocumentHelperProvider;
    private final Provider<Navigator> navigatorProvider;
    private final Provider<NfcInteractor> nfcInteractorProvider;
    private final Provider<OnfidoRemoteConfig> onfidoRemoteConfigProvider;
    private final Provider<PermissionsFlowHelper> permissionsFlowHelperProvider;
    private final Provider<WorkflowSupportedDocumentsStore> workflowSupportedDocumentsStoreProvider;

    public DisplayDocumentCaptureFlowProcessor_Factory(Provider<Navigator> provider, Provider<PermissionsFlowHelper> provider2, Provider<CaptureDocumentHelper> provider3, Provider<WorkflowSupportedDocumentsStore> provider4, Provider<NfcInteractor> provider5, Provider<OnfidoRemoteConfig> provider6) {
        this.navigatorProvider = provider;
        this.permissionsFlowHelperProvider = provider2;
        this.captureDocumentHelperProvider = provider3;
        this.workflowSupportedDocumentsStoreProvider = provider4;
        this.nfcInteractorProvider = provider5;
        this.onfidoRemoteConfigProvider = provider6;
    }

    @Override // com.onfido.javax.inject.Provider
    public DisplayDocumentCaptureFlowProcessor get() {
        return newInstance(this.navigatorProvider.get(), this.permissionsFlowHelperProvider.get(), this.captureDocumentHelperProvider.get(), this.workflowSupportedDocumentsStoreProvider.get(), this.nfcInteractorProvider.get(), this.onfidoRemoteConfigProvider.get());
    }

    public static DisplayDocumentCaptureFlowProcessor_Factory create(Provider<Navigator> provider, Provider<PermissionsFlowHelper> provider2, Provider<CaptureDocumentHelper> provider3, Provider<WorkflowSupportedDocumentsStore> provider4, Provider<NfcInteractor> provider5, Provider<OnfidoRemoteConfig> provider6) {
        return new DisplayDocumentCaptureFlowProcessor_Factory(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static DisplayDocumentCaptureFlowProcessor newInstance(Navigator navigator, PermissionsFlowHelper permissionsFlowHelper, CaptureDocumentHelper captureDocumentHelper, WorkflowSupportedDocumentsStore workflowSupportedDocumentsStore, NfcInteractor nfcInteractor, OnfidoRemoteConfig onfidoRemoteConfig) {
        return new DisplayDocumentCaptureFlowProcessor(navigator, permissionsFlowHelper, captureDocumentHelper, workflowSupportedDocumentsStore, nfcInteractor, onfidoRemoteConfig);
    }
}
