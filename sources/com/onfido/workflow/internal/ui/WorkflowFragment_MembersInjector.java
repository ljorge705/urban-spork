package com.onfido.workflow.internal.ui;

import com.onfido.android.sdk.capture.internal.navigation.navigator.NavigationManagerHolder;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.dagger.MembersInjector;
import com.onfido.javax.inject.Provider;
import com.onfido.workflow.internal.utils.WorkflowIntentLauncher;

/* loaded from: classes6.dex */
public final class WorkflowFragment_MembersInjector implements MembersInjector<WorkflowFragment> {
    private final Provider<NavigationManagerHolder> navigationManagerHolderProvider;
    private final Provider<SchedulersProvider> schedulersProvider;
    private final Provider<WorkflowIntentLauncher.Factory> workflowIntentFactoryProvider;
    private final Provider<WorkflowViewModel> workflowViewModelProvider;

    public WorkflowFragment_MembersInjector(Provider<WorkflowViewModel> provider, Provider<WorkflowIntentLauncher.Factory> provider2, Provider<SchedulersProvider> provider3, Provider<NavigationManagerHolder> provider4) {
        this.workflowViewModelProvider = provider;
        this.workflowIntentFactoryProvider = provider2;
        this.schedulersProvider = provider3;
        this.navigationManagerHolderProvider = provider4;
    }

    public static MembersInjector<WorkflowFragment> create(Provider<WorkflowViewModel> provider, Provider<WorkflowIntentLauncher.Factory> provider2, Provider<SchedulersProvider> provider3, Provider<NavigationManagerHolder> provider4) {
        return new WorkflowFragment_MembersInjector(provider, provider2, provider3, provider4);
    }

    @Override // com.onfido.dagger.MembersInjector
    public void injectMembers(WorkflowFragment workflowFragment) {
        injectWorkflowViewModel(workflowFragment, this.workflowViewModelProvider.get());
        injectWorkflowIntentFactory(workflowFragment, this.workflowIntentFactoryProvider.get());
        injectSchedulersProvider(workflowFragment, this.schedulersProvider.get());
        injectNavigationManagerHolder(workflowFragment, this.navigationManagerHolderProvider.get());
    }

    public static void injectWorkflowViewModel(WorkflowFragment workflowFragment, WorkflowViewModel workflowViewModel) {
        workflowFragment.workflowViewModel = workflowViewModel;
    }

    public static void injectWorkflowIntentFactory(WorkflowFragment workflowFragment, WorkflowIntentLauncher.Factory factory) {
        workflowFragment.workflowIntentFactory = factory;
    }

    public static void injectSchedulersProvider(WorkflowFragment workflowFragment, SchedulersProvider schedulersProvider) {
        workflowFragment.schedulersProvider = schedulersProvider;
    }

    public static void injectNavigationManagerHolder(WorkflowFragment workflowFragment, NavigationManagerHolder navigationManagerHolder) {
        workflowFragment.navigationManagerHolder = navigationManagerHolder;
    }
}
