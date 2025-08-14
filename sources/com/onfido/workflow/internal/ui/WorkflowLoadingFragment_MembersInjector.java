package com.onfido.workflow.internal.ui;

import com.onfido.dagger.MembersInjector;
import com.onfido.javax.inject.Provider;

/* loaded from: classes6.dex */
public final class WorkflowLoadingFragment_MembersInjector implements MembersInjector<WorkflowLoadingFragment> {
    private final Provider<WorkflowLoadingViewModel> viewModelProvider;

    public WorkflowLoadingFragment_MembersInjector(Provider<WorkflowLoadingViewModel> provider) {
        this.viewModelProvider = provider;
    }

    public static MembersInjector<WorkflowLoadingFragment> create(Provider<WorkflowLoadingViewModel> provider) {
        return new WorkflowLoadingFragment_MembersInjector(provider);
    }

    @Override // com.onfido.dagger.MembersInjector
    public void injectMembers(WorkflowLoadingFragment workflowLoadingFragment) {
        injectViewModelProvider(workflowLoadingFragment, this.viewModelProvider);
    }

    public static void injectViewModelProvider(WorkflowLoadingFragment workflowLoadingFragment, Provider<WorkflowLoadingViewModel> provider) {
        workflowLoadingFragment.viewModelProvider = provider;
    }
}
