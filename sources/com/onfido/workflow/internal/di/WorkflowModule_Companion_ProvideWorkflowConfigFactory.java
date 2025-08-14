package com.onfido.workflow.internal.di;

import com.onfido.android.sdk.capture.OnfidoConfig;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;
import com.onfido.javax.inject.Provider;
import com.onfido.workflow.WorkflowConfig;

/* loaded from: classes6.dex */
public final class WorkflowModule_Companion_ProvideWorkflowConfigFactory implements Factory<WorkflowConfig> {
    private final Provider<OnfidoConfig> onfidoConfigProvider;

    public WorkflowModule_Companion_ProvideWorkflowConfigFactory(Provider<OnfidoConfig> provider) {
        this.onfidoConfigProvider = provider;
    }

    @Override // com.onfido.javax.inject.Provider
    public WorkflowConfig get() {
        return provideWorkflowConfig(this.onfidoConfigProvider.get());
    }

    public static WorkflowModule_Companion_ProvideWorkflowConfigFactory create(Provider<OnfidoConfig> provider) {
        return new WorkflowModule_Companion_ProvideWorkflowConfigFactory(provider);
    }

    public static WorkflowConfig provideWorkflowConfig(OnfidoConfig onfidoConfig) {
        return (WorkflowConfig) Preconditions.checkNotNullFromProvides(WorkflowModule.INSTANCE.provideWorkflowConfig(onfidoConfig));
    }
}
