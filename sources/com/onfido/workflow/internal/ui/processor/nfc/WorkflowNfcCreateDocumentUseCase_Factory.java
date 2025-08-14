package com.onfido.workflow.internal.ui.processor.nfc;

import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.NfcTracker;
import com.onfido.android.sdk.capture.ui.nfc.NfcDataRepository;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes6.dex */
public final class WorkflowNfcCreateDocumentUseCase_Factory implements Factory<WorkflowNfcCreateDocumentUseCase> {
    private final Provider<NfcDataRepository> nfcDataRepositoryProvider;
    private final Provider<NfcTracker> nfcTrackerProvider;

    public WorkflowNfcCreateDocumentUseCase_Factory(Provider<NfcDataRepository> provider, Provider<NfcTracker> provider2) {
        this.nfcDataRepositoryProvider = provider;
        this.nfcTrackerProvider = provider2;
    }

    @Override // com.onfido.javax.inject.Provider
    public WorkflowNfcCreateDocumentUseCase get() {
        return newInstance(this.nfcDataRepositoryProvider.get(), this.nfcTrackerProvider.get());
    }

    public static WorkflowNfcCreateDocumentUseCase_Factory create(Provider<NfcDataRepository> provider, Provider<NfcTracker> provider2) {
        return new WorkflowNfcCreateDocumentUseCase_Factory(provider, provider2);
    }

    public static WorkflowNfcCreateDocumentUseCase newInstance(NfcDataRepository nfcDataRepository, NfcTracker nfcTracker) {
        return new WorkflowNfcCreateDocumentUseCase(nfcDataRepository, nfcTracker);
    }
}
