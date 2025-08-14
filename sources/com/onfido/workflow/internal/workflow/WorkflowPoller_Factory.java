package com.onfido.workflow.internal.workflow;

import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.api.client.token.sdk.ApplicantId;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;
import com.onfido.workflow.WorkflowConfig;
import com.onfido.workflow.internal.network.WorkflowApi;

/* loaded from: classes6.dex */
public final class WorkflowPoller_Factory implements Factory<WorkflowPoller> {
    private final Provider<ApplicantId> applicantIdProvider;
    private final Provider<SchedulersProvider> schedulersProvider;
    private final Provider<WorkflowApi> workflowApiProvider;
    private final Provider<WorkflowConfig> workflowConfigProvider;
    private final Provider<WorkflowPollerLocaleProvider> workflowPollerLocaleProvider;
    private final Provider<WorkflowTaskMapper> workflowTaskMapperProvider;

    public WorkflowPoller_Factory(Provider<WorkflowApi> provider, Provider<WorkflowConfig> provider2, Provider<WorkflowTaskMapper> provider3, Provider<SchedulersProvider> provider4, Provider<WorkflowPollerLocaleProvider> provider5, Provider<ApplicantId> provider6) {
        this.workflowApiProvider = provider;
        this.workflowConfigProvider = provider2;
        this.workflowTaskMapperProvider = provider3;
        this.schedulersProvider = provider4;
        this.workflowPollerLocaleProvider = provider5;
        this.applicantIdProvider = provider6;
    }

    @Override // com.onfido.javax.inject.Provider
    public WorkflowPoller get() {
        return newInstance(this.workflowApiProvider.get(), this.workflowConfigProvider.get(), this.workflowTaskMapperProvider.get(), this.schedulersProvider.get(), this.workflowPollerLocaleProvider.get(), this.applicantIdProvider.get());
    }

    public static WorkflowPoller_Factory create(Provider<WorkflowApi> provider, Provider<WorkflowConfig> provider2, Provider<WorkflowTaskMapper> provider3, Provider<SchedulersProvider> provider4, Provider<WorkflowPollerLocaleProvider> provider5, Provider<ApplicantId> provider6) {
        return new WorkflowPoller_Factory(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static WorkflowPoller newInstance(WorkflowApi workflowApi, WorkflowConfig workflowConfig, WorkflowTaskMapper workflowTaskMapper, SchedulersProvider schedulersProvider, WorkflowPollerLocaleProvider workflowPollerLocaleProvider, ApplicantId applicantId) {
        return new WorkflowPoller(workflowApi, workflowConfig, workflowTaskMapper, schedulersProvider, workflowPollerLocaleProvider, applicantId);
    }
}
