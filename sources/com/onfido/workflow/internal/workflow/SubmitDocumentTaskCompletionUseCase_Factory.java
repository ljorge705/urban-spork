package com.onfido.workflow.internal.workflow;

import com.onfido.android.sdk.capture.internal.token.OnfidoTokenProvider;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;
import com.onfido.workflow.WorkflowConfig;
import com.onfido.workflow.internal.network.WorkflowApi;

/* loaded from: classes6.dex */
public final class SubmitDocumentTaskCompletionUseCase_Factory implements Factory<SubmitDocumentTaskCompletionUseCase> {
    private final Provider<SchedulersProvider> schedulersProvider;
    private final Provider<OnfidoTokenProvider> tokenProvider;
    private final Provider<WorkflowApi> workflowApiProvider;
    private final Provider<WorkflowConfig> workflowConfigProvider;

    public SubmitDocumentTaskCompletionUseCase_Factory(Provider<WorkflowApi> provider, Provider<WorkflowConfig> provider2, Provider<SchedulersProvider> provider3, Provider<OnfidoTokenProvider> provider4) {
        this.workflowApiProvider = provider;
        this.workflowConfigProvider = provider2;
        this.schedulersProvider = provider3;
        this.tokenProvider = provider4;
    }

    @Override // com.onfido.javax.inject.Provider
    public SubmitDocumentTaskCompletionUseCase get() {
        return newInstance(this.workflowApiProvider.get(), this.workflowConfigProvider.get(), this.schedulersProvider.get(), this.tokenProvider.get());
    }

    public static SubmitDocumentTaskCompletionUseCase_Factory create(Provider<WorkflowApi> provider, Provider<WorkflowConfig> provider2, Provider<SchedulersProvider> provider3, Provider<OnfidoTokenProvider> provider4) {
        return new SubmitDocumentTaskCompletionUseCase_Factory(provider, provider2, provider3, provider4);
    }

    public static SubmitDocumentTaskCompletionUseCase newInstance(WorkflowApi workflowApi, WorkflowConfig workflowConfig, SchedulersProvider schedulersProvider, OnfidoTokenProvider onfidoTokenProvider) {
        return new SubmitDocumentTaskCompletionUseCase(workflowApi, workflowConfig, schedulersProvider, onfidoTokenProvider);
    }
}
