package com.onfido.workflow.internal.ui;

import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.FlowTracker;
import com.onfido.android.sdk.capture.internal.navigation.Navigator;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.android.sdk.workflow.internal.workflow.WorkflowHttpExceptionMapper;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;
import com.onfido.workflow.WorkflowConfig;
import com.onfido.workflow.internal.data.WorkflowTaskDataSource;
import com.onfido.workflow.internal.ui.processor.BackstackEventsProcessor;
import com.onfido.workflow.internal.ui.processor.DisplayDocumentCaptureFlowProcessor;
import com.onfido.workflow.internal.ui.processor.DisplayFaceCaptureFlowProcessor;
import com.onfido.workflow.internal.ui.processor.DisplayMotionFlowProcessor;
import com.onfido.workflow.internal.ui.processor.HostedWebModuleFlowProcessor;
import com.onfido.workflow.internal.ui.processor.PoaFlowProcessor;
import com.onfido.workflow.internal.ui.processor.RetryTaskProcessor;
import com.onfido.workflow.internal.ui.processor.biometric.token.BiometricTokenRetrievalFlowProcessor;
import com.onfido.workflow.internal.ui.processor.biometric.token.BiometricTokenStorageFlowProcessor;
import com.onfido.workflow.internal.workflow.WorkflowPoller;

/* loaded from: classes6.dex */
public final class WorkflowViewModel_Factory implements Factory<WorkflowViewModel> {
    private final Provider<BackstackEventsProcessor> backstackEventsProcessorProvider;
    private final Provider<BiometricTokenRetrievalFlowProcessor> biometricTokenRetrievalFlowProcessorProvider;
    private final Provider<BiometricTokenStorageFlowProcessor> biometricTokenStorageFlowProcessorProvider;
    private final Provider<DisplayDocumentCaptureFlowProcessor> documentTaskProcessorProvider;
    private final Provider<DisplayFaceCaptureFlowProcessor> faceTaskProcessorProvider;
    private final Provider<FlowTracker> flowTrackerProvider;
    private final Provider<HostedWebModuleFlowProcessor> hostedWebModuleFlowProcessorProvider;
    private final Provider<DisplayMotionFlowProcessor> motionTaskProcessorProvider;
    private final Provider<Navigator> navigatorProvider;
    private final Provider<PoaFlowProcessor> poaTaskProcessorProvider;
    private final Provider<RetryTaskProcessor> retryTaskProcessorProvider;
    private final Provider<SchedulersProvider> schedulersProvider;
    private final Provider<WorkflowConfig> workflowConfigProvider;
    private final Provider<WorkflowHttpExceptionMapper> workflowHttpExceptionMapperProvider;
    private final Provider<WorkflowPoller> workflowPollerProvider;
    private final Provider<WorkflowTaskDataSource> workflowTaskDataSourceProvider;

    public WorkflowViewModel_Factory(Provider<WorkflowPoller> provider, Provider<SchedulersProvider> provider2, Provider<Navigator> provider3, Provider<BackstackEventsProcessor> provider4, Provider<DisplayFaceCaptureFlowProcessor> provider5, Provider<DisplayMotionFlowProcessor> provider6, Provider<BiometricTokenRetrievalFlowProcessor> provider7, Provider<BiometricTokenStorageFlowProcessor> provider8, Provider<DisplayDocumentCaptureFlowProcessor> provider9, Provider<RetryTaskProcessor> provider10, Provider<PoaFlowProcessor> provider11, Provider<HostedWebModuleFlowProcessor> provider12, Provider<FlowTracker> provider13, Provider<WorkflowHttpExceptionMapper> provider14, Provider<WorkflowTaskDataSource> provider15, Provider<WorkflowConfig> provider16) {
        this.workflowPollerProvider = provider;
        this.schedulersProvider = provider2;
        this.navigatorProvider = provider3;
        this.backstackEventsProcessorProvider = provider4;
        this.faceTaskProcessorProvider = provider5;
        this.motionTaskProcessorProvider = provider6;
        this.biometricTokenRetrievalFlowProcessorProvider = provider7;
        this.biometricTokenStorageFlowProcessorProvider = provider8;
        this.documentTaskProcessorProvider = provider9;
        this.retryTaskProcessorProvider = provider10;
        this.poaTaskProcessorProvider = provider11;
        this.hostedWebModuleFlowProcessorProvider = provider12;
        this.flowTrackerProvider = provider13;
        this.workflowHttpExceptionMapperProvider = provider14;
        this.workflowTaskDataSourceProvider = provider15;
        this.workflowConfigProvider = provider16;
    }

    @Override // com.onfido.javax.inject.Provider
    public WorkflowViewModel get() {
        return newInstance(this.workflowPollerProvider.get(), this.schedulersProvider.get(), this.navigatorProvider.get(), this.backstackEventsProcessorProvider.get(), this.faceTaskProcessorProvider.get(), this.motionTaskProcessorProvider.get(), this.biometricTokenRetrievalFlowProcessorProvider.get(), this.biometricTokenStorageFlowProcessorProvider.get(), this.documentTaskProcessorProvider.get(), this.retryTaskProcessorProvider.get(), this.poaTaskProcessorProvider.get(), this.hostedWebModuleFlowProcessorProvider.get(), this.flowTrackerProvider.get(), this.workflowHttpExceptionMapperProvider.get(), this.workflowTaskDataSourceProvider.get(), this.workflowConfigProvider.get());
    }

    public static WorkflowViewModel_Factory create(Provider<WorkflowPoller> provider, Provider<SchedulersProvider> provider2, Provider<Navigator> provider3, Provider<BackstackEventsProcessor> provider4, Provider<DisplayFaceCaptureFlowProcessor> provider5, Provider<DisplayMotionFlowProcessor> provider6, Provider<BiometricTokenRetrievalFlowProcessor> provider7, Provider<BiometricTokenStorageFlowProcessor> provider8, Provider<DisplayDocumentCaptureFlowProcessor> provider9, Provider<RetryTaskProcessor> provider10, Provider<PoaFlowProcessor> provider11, Provider<HostedWebModuleFlowProcessor> provider12, Provider<FlowTracker> provider13, Provider<WorkflowHttpExceptionMapper> provider14, Provider<WorkflowTaskDataSource> provider15, Provider<WorkflowConfig> provider16) {
        return new WorkflowViewModel_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16);
    }

    public static WorkflowViewModel newInstance(WorkflowPoller workflowPoller, SchedulersProvider schedulersProvider, Navigator navigator, BackstackEventsProcessor backstackEventsProcessor, DisplayFaceCaptureFlowProcessor displayFaceCaptureFlowProcessor, DisplayMotionFlowProcessor displayMotionFlowProcessor, BiometricTokenRetrievalFlowProcessor biometricTokenRetrievalFlowProcessor, BiometricTokenStorageFlowProcessor biometricTokenStorageFlowProcessor, DisplayDocumentCaptureFlowProcessor displayDocumentCaptureFlowProcessor, RetryTaskProcessor retryTaskProcessor, PoaFlowProcessor poaFlowProcessor, HostedWebModuleFlowProcessor hostedWebModuleFlowProcessor, FlowTracker flowTracker, WorkflowHttpExceptionMapper workflowHttpExceptionMapper, WorkflowTaskDataSource workflowTaskDataSource, WorkflowConfig workflowConfig) {
        return new WorkflowViewModel(workflowPoller, schedulersProvider, navigator, backstackEventsProcessor, displayFaceCaptureFlowProcessor, displayMotionFlowProcessor, biometricTokenRetrievalFlowProcessor, biometricTokenStorageFlowProcessor, displayDocumentCaptureFlowProcessor, retryTaskProcessor, poaFlowProcessor, hostedWebModuleFlowProcessor, flowTracker, workflowHttpExceptionMapper, workflowTaskDataSource, workflowConfig);
    }
}
