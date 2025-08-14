package com.onfido.workflow.internal.ui;

import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.WaitingScreenTracker;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;
import com.onfido.workflow.internal.data.WorkflowTaskDataSource;

/* loaded from: classes6.dex */
public final class WorkflowLoadingViewModel_Factory implements Factory<WorkflowLoadingViewModel> {
    private final Provider<OnfidoRemoteConfig> remoteConfigProvider;
    private final Provider<SchedulersProvider> schedulersProvider;
    private final Provider<WaitingScreenTracker> waitingScreenTrackerProvider;
    private final Provider<WorkflowTaskDataSource> workflowTaskDataSourceProvider;

    public WorkflowLoadingViewModel_Factory(Provider<WorkflowTaskDataSource> provider, Provider<OnfidoRemoteConfig> provider2, Provider<SchedulersProvider> provider3, Provider<WaitingScreenTracker> provider4) {
        this.workflowTaskDataSourceProvider = provider;
        this.remoteConfigProvider = provider2;
        this.schedulersProvider = provider3;
        this.waitingScreenTrackerProvider = provider4;
    }

    @Override // com.onfido.javax.inject.Provider
    public WorkflowLoadingViewModel get() {
        return newInstance(this.workflowTaskDataSourceProvider.get(), this.remoteConfigProvider.get(), this.schedulersProvider.get(), this.waitingScreenTrackerProvider.get());
    }

    public static WorkflowLoadingViewModel_Factory create(Provider<WorkflowTaskDataSource> provider, Provider<OnfidoRemoteConfig> provider2, Provider<SchedulersProvider> provider3, Provider<WaitingScreenTracker> provider4) {
        return new WorkflowLoadingViewModel_Factory(provider, provider2, provider3, provider4);
    }

    public static WorkflowLoadingViewModel newInstance(WorkflowTaskDataSource workflowTaskDataSource, OnfidoRemoteConfig onfidoRemoteConfig, SchedulersProvider schedulersProvider, WaitingScreenTracker waitingScreenTracker) {
        return new WorkflowLoadingViewModel(workflowTaskDataSource, onfidoRemoteConfig, schedulersProvider, waitingScreenTracker);
    }
}
