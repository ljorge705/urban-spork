package com.onfido.workflow.internal.workflow;

import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;
import com.onfido.workflow.WorkflowConfig;
import com.onfido.workflow.internal.network.WorkflowApi;

/* loaded from: classes6.dex */
public final class SubmitTaskCompletionUseCase_Factory implements Factory<SubmitTaskCompletionUseCase> {
    private final Provider<SchedulersProvider> schedulersProvider;
    private final Provider<WorkflowApi> workflowApiProvider;
    private final Provider<WorkflowConfig> workflowConfigProvider;

    public SubmitTaskCompletionUseCase_Factory(Provider<WorkflowApi> provider, Provider<WorkflowConfig> provider2, Provider<SchedulersProvider> provider3) {
        this.workflowApiProvider = provider;
        this.workflowConfigProvider = provider2;
        this.schedulersProvider = provider3;
    }

    @Override // com.onfido.javax.inject.Provider
    public SubmitTaskCompletionUseCase get() {
        return newInstance(this.workflowApiProvider.get(), this.workflowConfigProvider.get(), this.schedulersProvider.get());
    }

    public static SubmitTaskCompletionUseCase_Factory create(Provider<WorkflowApi> provider, Provider<WorkflowConfig> provider2, Provider<SchedulersProvider> provider3) {
        return new SubmitTaskCompletionUseCase_Factory(provider, provider2, provider3);
    }

    public static SubmitTaskCompletionUseCase newInstance(WorkflowApi workflowApi, WorkflowConfig workflowConfig, SchedulersProvider schedulersProvider) {
        return new SubmitTaskCompletionUseCase(workflowApi, workflowConfig, schedulersProvider);
    }
}
