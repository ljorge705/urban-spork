package com.onfido.workflow.internal.di;

import com.onfido.api.client.OnfidoFetcher;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;
import com.onfido.javax.inject.Provider;
import com.onfido.workflow.internal.network.WorkflowApi;

/* loaded from: classes6.dex */
public final class WorkflowModule_Companion_ProvideWorkflowApiFactory implements Factory<WorkflowApi> {
    private final Provider<OnfidoFetcher> onfidoFetcherProvider;

    public WorkflowModule_Companion_ProvideWorkflowApiFactory(Provider<OnfidoFetcher> provider) {
        this.onfidoFetcherProvider = provider;
    }

    @Override // com.onfido.javax.inject.Provider
    public WorkflowApi get() {
        return provideWorkflowApi(this.onfidoFetcherProvider.get());
    }

    public static WorkflowModule_Companion_ProvideWorkflowApiFactory create(Provider<OnfidoFetcher> provider) {
        return new WorkflowModule_Companion_ProvideWorkflowApiFactory(provider);
    }

    public static WorkflowApi provideWorkflowApi(OnfidoFetcher onfidoFetcher) {
        return (WorkflowApi) Preconditions.checkNotNullFromProvides(WorkflowModule.INSTANCE.provideWorkflowApi(onfidoFetcher));
    }
}
