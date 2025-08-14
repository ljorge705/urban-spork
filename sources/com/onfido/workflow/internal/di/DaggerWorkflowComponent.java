package com.onfido.workflow.internal.di;

import android.content.Context;
import android.os.ResultReceiver;
import com.onfido.android.sdk.capture.OnfidoConfig;
import com.onfido.android.sdk.capture.common.data.ThemeDataSource;
import com.onfido.android.sdk.capture.common.data.ThemeDataSource_Factory;
import com.onfido.android.sdk.capture.common.di.SdkComponent;
import com.onfido.android.sdk.capture.common.permissions.RuntimePermissionsManager;
import com.onfido.android.sdk.capture.common.permissions.RuntimePermissionsManager_Factory;
import com.onfido.android.sdk.capture.common.preferences.SharedPreferencesDataSource;
import com.onfido.android.sdk.capture.common.preferences.SharedPreferencesDataSource_Factory;
import com.onfido.android.sdk.capture.document.DocumentConfigurationManager_Factory;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.OnfidoAnalytics;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.FlowTracker;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.NfcTracker;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.NfcTracker_Factory;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.WaitingScreenTracker;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.navigation.Navigator;
import com.onfido.android.sdk.capture.internal.navigation.OnfidoNavigation;
import com.onfido.android.sdk.capture.internal.navigation.navigator.NavigationManagerHolder;
import com.onfido.android.sdk.capture.internal.nfc.NfcInteractor;
import com.onfido.android.sdk.capture.internal.token.OnfidoTokenProvider;
import com.onfido.android.sdk.capture.internal.ui.countryselection.OnfidoSupportedDocumentsRepository;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.android.sdk.capture.network.OnfidoApiService;
import com.onfido.android.sdk.capture.ui.nfc.NfcDataRepository;
import com.onfido.android.sdk.capture.ui.nfc.NfcDataRepository_Factory;
import com.onfido.android.sdk.workflow.internal.workflow.WorkflowHttpExceptionMapper;
import com.onfido.android.sdk.workflow.internal.workflow.WorkflowHttpExceptionMapper_Factory;
import com.onfido.android.sdk.workflow.internal.workflow.tasks.documentupload.WorkflowSupportedDocumentsStore;
import com.onfido.api.client.OnfidoFetcher;
import com.onfido.api.client.token.sdk.ApplicantId;
import com.onfido.dagger.internal.DoubleCheck;
import com.onfido.dagger.internal.Preconditions;
import com.onfido.javax.inject.Provider;
import com.onfido.workflow.WorkflowConfig;
import com.onfido.workflow.internal.data.WorkflowTaskDataSource;
import com.onfido.workflow.internal.data.WorkflowTaskDataSource_Factory;
import com.onfido.workflow.internal.di.WorkflowComponent;
import com.onfido.workflow.internal.network.BiometricTokenApi;
import com.onfido.workflow.internal.network.WorkflowApi;
import com.onfido.workflow.internal.ui.WorkflowFragment;
import com.onfido.workflow.internal.ui.WorkflowFragment_MembersInjector;
import com.onfido.workflow.internal.ui.WorkflowLoadingFragment;
import com.onfido.workflow.internal.ui.WorkflowLoadingFragment_MembersInjector;
import com.onfido.workflow.internal.ui.WorkflowLoadingViewModel;
import com.onfido.workflow.internal.ui.WorkflowLoadingViewModel_Factory;
import com.onfido.workflow.internal.ui.WorkflowViewModel;
import com.onfido.workflow.internal.ui.WorkflowViewModel_Factory;
import com.onfido.workflow.internal.ui.processor.BackstackEventsProcessor;
import com.onfido.workflow.internal.ui.processor.BackstackEventsProcessor_Factory;
import com.onfido.workflow.internal.ui.processor.CaptureDocumentHelper;
import com.onfido.workflow.internal.ui.processor.CaptureDocumentHelper_Factory;
import com.onfido.workflow.internal.ui.processor.DisplayDocumentCaptureFlowProcessor;
import com.onfido.workflow.internal.ui.processor.DisplayDocumentCaptureFlowProcessor_Factory;
import com.onfido.workflow.internal.ui.processor.DisplayFaceCaptureFlowProcessor;
import com.onfido.workflow.internal.ui.processor.DisplayFaceCaptureFlowProcessor_Factory;
import com.onfido.workflow.internal.ui.processor.DisplayMotionFlowProcessor;
import com.onfido.workflow.internal.ui.processor.DisplayMotionFlowProcessor_Factory;
import com.onfido.workflow.internal.ui.processor.FaceLivenessFlowHelper;
import com.onfido.workflow.internal.ui.processor.FaceLivenessFlowHelper_Factory;
import com.onfido.workflow.internal.ui.processor.HostedWebModuleFlowProcessor;
import com.onfido.workflow.internal.ui.processor.HostedWebModuleFlowProcessor_Factory;
import com.onfido.workflow.internal.ui.processor.PermissionsFlowHelper;
import com.onfido.workflow.internal.ui.processor.PermissionsFlowHelper_Factory;
import com.onfido.workflow.internal.ui.processor.PoaFlowProcessor;
import com.onfido.workflow.internal.ui.processor.PoaFlowProcessor_Factory;
import com.onfido.workflow.internal.ui.processor.RetryTaskProcessor;
import com.onfido.workflow.internal.ui.processor.RetryTaskProcessor_Factory;
import com.onfido.workflow.internal.ui.processor.biometric.token.BiometricTokenExternalRepository;
import com.onfido.workflow.internal.ui.processor.biometric.token.BiometricTokenExternalRepository_Factory;
import com.onfido.workflow.internal.ui.processor.biometric.token.BiometricTokenInternalRepository;
import com.onfido.workflow.internal.ui.processor.biometric.token.BiometricTokenInternalRepository_Factory;
import com.onfido.workflow.internal.ui.processor.biometric.token.BiometricTokenRepository;
import com.onfido.workflow.internal.ui.processor.biometric.token.BiometricTokenRetrievalFlowProcessor;
import com.onfido.workflow.internal.ui.processor.biometric.token.BiometricTokenRetrievalFlowProcessor_Factory;
import com.onfido.workflow.internal.ui.processor.biometric.token.BiometricTokenStorageFlowProcessor;
import com.onfido.workflow.internal.ui.processor.biometric.token.BiometricTokenStorageFlowProcessor_Factory;
import com.onfido.workflow.internal.ui.processor.nfc.NfcFlowHelper;
import com.onfido.workflow.internal.ui.processor.nfc.NfcFlowHelper_Factory;
import com.onfido.workflow.internal.ui.processor.nfc.WorkflowNfcCreateDocumentUseCase;
import com.onfido.workflow.internal.ui.processor.nfc.WorkflowNfcCreateDocumentUseCase_Factory;
import com.onfido.workflow.internal.utils.WorkflowIntentLauncherFactory;
import com.onfido.workflow.internal.utils.WorkflowIntentLauncherFactory_Impl;
import com.onfido.workflow.internal.utils.WorkflowIntentLauncherImpl_Factory;
import com.onfido.workflow.internal.workflow.SubmitDocumentTaskCompletionUseCase;
import com.onfido.workflow.internal.workflow.SubmitDocumentTaskCompletionUseCase_Factory;
import com.onfido.workflow.internal.workflow.SubmitTaskCompletionUseCase;
import com.onfido.workflow.internal.workflow.SubmitTaskCompletionUseCase_Factory;
import com.onfido.workflow.internal.workflow.WorkflowPoller;
import com.onfido.workflow.internal.workflow.WorkflowPollerLocaleProvider;
import com.onfido.workflow.internal.workflow.WorkflowPollerLocaleProvider_Factory;
import com.onfido.workflow.internal.workflow.WorkflowPoller_Factory;
import com.onfido.workflow.internal.workflow.WorkflowTaskMapper;
import com.onfido.workflow.internal.workflow.WorkflowTaskMapper_Factory;
import kotlinx.serialization.json.Json;

/* loaded from: classes6.dex */
public final class DaggerWorkflowComponent {
    private DaggerWorkflowComponent() {
    }

    public static WorkflowComponent.Factory factory() {
        return new Factory();
    }

    private static final class Factory implements WorkflowComponent.Factory {
        private Factory() {
        }

        @Override // com.onfido.workflow.internal.di.WorkflowComponent.Factory
        public WorkflowComponent create(SdkComponent sdkComponent) {
            Preconditions.checkNotNull(sdkComponent);
            return new WorkflowComponentImpl(sdkComponent);
        }
    }

    private static final class WorkflowComponentImpl implements WorkflowComponent {
        private Provider<Context> applicationContextProvider;
        private Provider<BackstackEventsProcessor> backstackEventsProcessorProvider;
        private Provider<BiometricTokenExternalRepository> biometricTokenExternalRepositoryProvider;
        private Provider<BiometricTokenInternalRepository> biometricTokenInternalRepositoryProvider;
        private Provider<BiometricTokenRetrievalFlowProcessor> biometricTokenRetrievalFlowProcessorProvider;
        private Provider<BiometricTokenStorageFlowProcessor> biometricTokenStorageFlowProcessorProvider;
        private Provider<CaptureDocumentHelper> captureDocumentHelperProvider;
        private Provider<DisplayDocumentCaptureFlowProcessor> displayDocumentCaptureFlowProcessorProvider;
        private Provider<DisplayFaceCaptureFlowProcessor> displayFaceCaptureFlowProcessorProvider;
        private Provider<DisplayMotionFlowProcessor> displayMotionFlowProcessorProvider;
        private Provider<FaceLivenessFlowHelper> faceLivenessFlowHelperProvider;
        private Provider<FlowTracker> flowTrackerProvider;
        private Provider<Json> getJsonParserProvider;
        private Provider<HostedWebModuleFlowProcessor> hostedWebModuleFlowProcessorProvider;
        private Provider<NfcDataRepository> nfcDataRepositoryProvider;
        private Provider<NfcFlowHelper> nfcFlowHelperProvider;
        private Provider<NfcTracker> nfcTrackerProvider;
        private Provider<OnfidoAnalytics> onfidoAnalyticsProvider;
        private Provider<OnfidoApiService> onfidoApiServiceProvider;
        private Provider<OnfidoConfig> onfidoConfigProvider;
        private Provider<OnfidoFetcher> onfidoFetcherProvider;
        private Provider<OnfidoRemoteConfig> onfidoRemoteConfigProvider;
        private Provider<OnfidoSupportedDocumentsRepository> onfidoSupportedDocumentsRepositoryProvider;
        private Provider<OnfidoTokenProvider> onfidoTokenProvider;
        private Provider<PermissionsFlowHelper> permissionsFlowHelperProvider;
        private Provider<PoaFlowProcessor> poaFlowProcessorProvider;
        private Provider<ApplicantId> provideApplicantIdProvider;
        private Provider<BiometricTokenApi> provideBiometricTokenApiProvider;
        private Provider<ResultReceiver> provideBiometricTokenCallbackProvider;
        private Provider<BiometricTokenRepository> provideBiometricTokenRepositoryProvider;
        private Provider<String> provideCustomerUserHashProvider;
        private Provider<Navigator> provideNavigatorProvider;
        private Provider<NfcInteractor> provideNfcInteractorProvider;
        private Provider<OnfidoNavigation> provideOnfidoNavigationProvider;
        private Provider<WorkflowApi> provideWorkflowApiProvider;
        private Provider<WorkflowConfig> provideWorkflowConfigProvider;
        private Provider<RetryTaskProcessor> retryTaskProcessorProvider;
        private Provider<RuntimePermissionsManager> runtimePermissionsManagerProvider;
        private Provider<SchedulersProvider> schedulersProvider;
        private final SdkComponent sdkComponent;
        private Provider<SharedPreferencesDataSource> sharedPreferencesDataSourceProvider;
        private Provider<SubmitDocumentTaskCompletionUseCase> submitDocumentTaskCompletionUseCaseProvider;
        private Provider<SubmitTaskCompletionUseCase> submitTaskCompletionUseCaseProvider;
        private Provider<ThemeDataSource> themeDataSourceProvider;
        private Provider<WaitingScreenTracker> waitingScreenTrackerProvider;
        private final WorkflowComponentImpl workflowComponentImpl;
        private Provider<WorkflowHttpExceptionMapper> workflowHttpExceptionMapperProvider;
        private Provider<WorkflowIntentLauncherFactory> workflowIntentLauncherFactoryProvider;
        private WorkflowIntentLauncherImpl_Factory workflowIntentLauncherImplProvider;
        private Provider<WorkflowLoadingViewModel> workflowLoadingViewModelProvider;
        private Provider<WorkflowNfcCreateDocumentUseCase> workflowNfcCreateDocumentUseCaseProvider;
        private Provider<WorkflowPollerLocaleProvider> workflowPollerLocaleProvider;
        private Provider<WorkflowPoller> workflowPollerProvider;
        private Provider<WorkflowSupportedDocumentsStore> workflowSupportedDocumentsStoreProvider;
        private Provider<WorkflowTaskDataSource> workflowTaskDataSourceProvider;
        private Provider<WorkflowTaskMapper> workflowTaskMapperProvider;
        private Provider<WorkflowViewModel> workflowViewModelProvider;

        private WorkflowComponentImpl(SdkComponent sdkComponent) {
            this.workflowComponentImpl = this;
            this.sdkComponent = sdkComponent;
            initialize(sdkComponent);
        }

        private NavigationManagerHolder navigationManagerHolder() {
            return WorkflowNavigationModule_ProvideNavigationManagerHolderFactory.provideNavigationManagerHolder(this.provideOnfidoNavigationProvider.get());
        }

        private void initialize(SdkComponent sdkComponent) {
            OnfidoFetcherProvider onfidoFetcherProvider = new OnfidoFetcherProvider(sdkComponent);
            this.onfidoFetcherProvider = onfidoFetcherProvider;
            this.provideWorkflowApiProvider = WorkflowModule_Companion_ProvideWorkflowApiFactory.create(onfidoFetcherProvider);
            OnfidoConfigProvider onfidoConfigProvider = new OnfidoConfigProvider(sdkComponent);
            this.onfidoConfigProvider = onfidoConfigProvider;
            this.provideWorkflowConfigProvider = WorkflowModule_Companion_ProvideWorkflowConfigFactory.create(onfidoConfigProvider);
            this.onfidoSupportedDocumentsRepositoryProvider = new OnfidoSupportedDocumentsRepositoryProvider(sdkComponent);
            this.onfidoRemoteConfigProvider = new OnfidoRemoteConfigProvider(sdkComponent);
            GetJsonParserProvider getJsonParserProvider = new GetJsonParserProvider(sdkComponent);
            this.getJsonParserProvider = getJsonParserProvider;
            this.workflowTaskMapperProvider = WorkflowTaskMapper_Factory.create(this.onfidoSupportedDocumentsRepositoryProvider, this.onfidoRemoteConfigProvider, getJsonParserProvider);
            this.schedulersProvider = new SchedulersProviderProvider(sdkComponent);
            ApplicationContextProvider applicationContextProvider = new ApplicationContextProvider(sdkComponent);
            this.applicationContextProvider = applicationContextProvider;
            this.workflowPollerLocaleProvider = WorkflowPollerLocaleProvider_Factory.create(applicationContextProvider);
            ProvideApplicantIdProvider provideApplicantIdProvider = new ProvideApplicantIdProvider(sdkComponent);
            this.provideApplicantIdProvider = provideApplicantIdProvider;
            this.workflowPollerProvider = WorkflowPoller_Factory.create(this.provideWorkflowApiProvider, this.provideWorkflowConfigProvider, this.workflowTaskMapperProvider, this.schedulersProvider, this.workflowPollerLocaleProvider, provideApplicantIdProvider);
            Provider<OnfidoNavigation> provider = DoubleCheck.provider(WorkflowNavigationModule_ProvideOnfidoNavigationFactory.create(this.schedulersProvider));
            this.provideOnfidoNavigationProvider = provider;
            WorkflowNavigationModule_ProvideNavigatorFactory workflowNavigationModule_ProvideNavigatorFactoryCreate = WorkflowNavigationModule_ProvideNavigatorFactory.create(provider);
            this.provideNavigatorProvider = workflowNavigationModule_ProvideNavigatorFactoryCreate;
            this.backstackEventsProcessorProvider = BackstackEventsProcessor_Factory.create(this.onfidoRemoteConfigProvider, workflowNavigationModule_ProvideNavigatorFactoryCreate);
            this.submitTaskCompletionUseCaseProvider = SubmitTaskCompletionUseCase_Factory.create(this.provideWorkflowApiProvider, this.provideWorkflowConfigProvider, this.schedulersProvider);
            SharedPreferencesDataSource_Factory sharedPreferencesDataSource_FactoryCreate = SharedPreferencesDataSource_Factory.create(this.applicationContextProvider, this.getJsonParserProvider);
            this.sharedPreferencesDataSourceProvider = sharedPreferencesDataSource_FactoryCreate;
            RuntimePermissionsManager_Factory runtimePermissionsManager_FactoryCreate = RuntimePermissionsManager_Factory.create(this.applicationContextProvider, sharedPreferencesDataSource_FactoryCreate);
            this.runtimePermissionsManagerProvider = runtimePermissionsManager_FactoryCreate;
            PermissionsFlowHelper_Factory permissionsFlowHelper_FactoryCreate = PermissionsFlowHelper_Factory.create(this.provideNavigatorProvider, runtimePermissionsManager_FactoryCreate);
            this.permissionsFlowHelperProvider = permissionsFlowHelper_FactoryCreate;
            FaceLivenessFlowHelper_Factory faceLivenessFlowHelper_FactoryCreate = FaceLivenessFlowHelper_Factory.create(this.provideNavigatorProvider, this.submitTaskCompletionUseCaseProvider, permissionsFlowHelper_FactoryCreate, this.onfidoRemoteConfigProvider);
            this.faceLivenessFlowHelperProvider = faceLivenessFlowHelper_FactoryCreate;
            this.displayFaceCaptureFlowProcessorProvider = DisplayFaceCaptureFlowProcessor_Factory.create(this.applicationContextProvider, faceLivenessFlowHelper_FactoryCreate, this.permissionsFlowHelperProvider, this.provideNavigatorProvider, this.submitTaskCompletionUseCaseProvider, this.onfidoRemoteConfigProvider);
            this.displayMotionFlowProcessorProvider = DisplayMotionFlowProcessor_Factory.create(this.provideNavigatorProvider, this.submitTaskCompletionUseCaseProvider);
            this.provideCustomerUserHashProvider = new ProvideCustomerUserHashProvider(sdkComponent);
            this.biometricTokenInternalRepositoryProvider = BiometricTokenInternalRepository_Factory.create(this.applicationContextProvider, this.getJsonParserProvider);
            WorkflowModule_Companion_ProvideBiometricTokenCallbackFactory workflowModule_Companion_ProvideBiometricTokenCallbackFactoryCreate = WorkflowModule_Companion_ProvideBiometricTokenCallbackFactory.create(this.onfidoConfigProvider);
            this.provideBiometricTokenCallbackProvider = workflowModule_Companion_ProvideBiometricTokenCallbackFactoryCreate;
            BiometricTokenExternalRepository_Factory biometricTokenExternalRepository_FactoryCreate = BiometricTokenExternalRepository_Factory.create(this.applicationContextProvider, workflowModule_Companion_ProvideBiometricTokenCallbackFactoryCreate);
            this.biometricTokenExternalRepositoryProvider = biometricTokenExternalRepository_FactoryCreate;
            this.provideBiometricTokenRepositoryProvider = WorkflowModule_Companion_ProvideBiometricTokenRepositoryFactory.create(this.onfidoConfigProvider, this.biometricTokenInternalRepositoryProvider, biometricTokenExternalRepository_FactoryCreate);
            OnfidoAnalyticsProvider onfidoAnalyticsProvider = new OnfidoAnalyticsProvider(sdkComponent);
            this.onfidoAnalyticsProvider = onfidoAnalyticsProvider;
            this.biometricTokenRetrievalFlowProcessorProvider = BiometricTokenRetrievalFlowProcessor_Factory.create(this.provideNavigatorProvider, this.submitTaskCompletionUseCaseProvider, this.provideCustomerUserHashProvider, this.provideBiometricTokenRepositoryProvider, onfidoAnalyticsProvider);
            WorkflowModule_Companion_ProvideBiometricTokenApiFactory workflowModule_Companion_ProvideBiometricTokenApiFactoryCreate = WorkflowModule_Companion_ProvideBiometricTokenApiFactory.create(this.onfidoFetcherProvider);
            this.provideBiometricTokenApiProvider = workflowModule_Companion_ProvideBiometricTokenApiFactoryCreate;
            this.biometricTokenStorageFlowProcessorProvider = BiometricTokenStorageFlowProcessor_Factory.create(workflowModule_Companion_ProvideBiometricTokenApiFactoryCreate, this.submitTaskCompletionUseCaseProvider, this.provideCustomerUserHashProvider, this.provideBiometricTokenRepositoryProvider, this.onfidoAnalyticsProvider);
            OnfidoApiServiceProvider onfidoApiServiceProvider = new OnfidoApiServiceProvider(sdkComponent);
            this.onfidoApiServiceProvider = onfidoApiServiceProvider;
            this.nfcDataRepositoryProvider = NfcDataRepository_Factory.create(onfidoApiServiceProvider, this.getJsonParserProvider);
            NfcTracker_Factory nfcTracker_FactoryCreate = NfcTracker_Factory.create(this.onfidoAnalyticsProvider);
            this.nfcTrackerProvider = nfcTracker_FactoryCreate;
            WorkflowNfcCreateDocumentUseCase_Factory workflowNfcCreateDocumentUseCase_FactoryCreate = WorkflowNfcCreateDocumentUseCase_Factory.create(this.nfcDataRepositoryProvider, nfcTracker_FactoryCreate);
            this.workflowNfcCreateDocumentUseCaseProvider = workflowNfcCreateDocumentUseCase_FactoryCreate;
            this.nfcFlowHelperProvider = NfcFlowHelper_Factory.create(this.provideNavigatorProvider, workflowNfcCreateDocumentUseCase_FactoryCreate);
            OnfidoTokenProviderProvider onfidoTokenProviderProvider = new OnfidoTokenProviderProvider(sdkComponent);
            this.onfidoTokenProvider = onfidoTokenProviderProvider;
            this.submitDocumentTaskCompletionUseCaseProvider = SubmitDocumentTaskCompletionUseCase_Factory.create(this.provideWorkflowApiProvider, this.provideWorkflowConfigProvider, this.schedulersProvider, onfidoTokenProviderProvider);
            this.captureDocumentHelperProvider = CaptureDocumentHelper_Factory.create(DocumentConfigurationManager_Factory.create(), this.nfcFlowHelperProvider, this.provideNavigatorProvider, this.submitDocumentTaskCompletionUseCaseProvider, this.onfidoRemoteConfigProvider);
            this.workflowSupportedDocumentsStoreProvider = new WorkflowSupportedDocumentsStoreProvider(sdkComponent);
            ProvideNfcInteractorProvider provideNfcInteractorProvider = new ProvideNfcInteractorProvider(sdkComponent);
            this.provideNfcInteractorProvider = provideNfcInteractorProvider;
            this.displayDocumentCaptureFlowProcessorProvider = DisplayDocumentCaptureFlowProcessor_Factory.create(this.provideNavigatorProvider, this.permissionsFlowHelperProvider, this.captureDocumentHelperProvider, this.workflowSupportedDocumentsStoreProvider, provideNfcInteractorProvider, this.onfidoRemoteConfigProvider);
            this.retryTaskProcessorProvider = RetryTaskProcessor_Factory.create(this.provideNavigatorProvider, this.submitTaskCompletionUseCaseProvider);
            this.poaFlowProcessorProvider = PoaFlowProcessor_Factory.create(this.provideNavigatorProvider, this.submitTaskCompletionUseCaseProvider);
            ThemeDataSource_Factory themeDataSource_FactoryCreate = ThemeDataSource_Factory.create(this.sharedPreferencesDataSourceProvider);
            this.themeDataSourceProvider = themeDataSource_FactoryCreate;
            this.hostedWebModuleFlowProcessorProvider = HostedWebModuleFlowProcessor_Factory.create(this.provideNavigatorProvider, this.submitTaskCompletionUseCaseProvider, themeDataSource_FactoryCreate);
            this.flowTrackerProvider = new FlowTrackerProvider(sdkComponent);
            this.workflowHttpExceptionMapperProvider = WorkflowHttpExceptionMapper_Factory.create(this.getJsonParserProvider);
            Provider<WorkflowTaskDataSource> provider2 = DoubleCheck.provider(WorkflowTaskDataSource_Factory.create());
            this.workflowTaskDataSourceProvider = provider2;
            this.workflowViewModelProvider = DoubleCheck.provider(WorkflowViewModel_Factory.create(this.workflowPollerProvider, this.schedulersProvider, this.provideNavigatorProvider, this.backstackEventsProcessorProvider, this.displayFaceCaptureFlowProcessorProvider, this.displayMotionFlowProcessorProvider, this.biometricTokenRetrievalFlowProcessorProvider, this.biometricTokenStorageFlowProcessorProvider, this.displayDocumentCaptureFlowProcessorProvider, this.retryTaskProcessorProvider, this.poaFlowProcessorProvider, this.hostedWebModuleFlowProcessorProvider, this.flowTrackerProvider, this.workflowHttpExceptionMapperProvider, provider2, this.provideWorkflowConfigProvider));
            WorkflowIntentLauncherImpl_Factory workflowIntentLauncherImpl_FactoryCreate = WorkflowIntentLauncherImpl_Factory.create(this.applicationContextProvider, this.provideNavigatorProvider, this.onfidoConfigProvider);
            this.workflowIntentLauncherImplProvider = workflowIntentLauncherImpl_FactoryCreate;
            this.workflowIntentLauncherFactoryProvider = WorkflowIntentLauncherFactory_Impl.create(workflowIntentLauncherImpl_FactoryCreate);
            WaitingScreenTrackerProvider waitingScreenTrackerProvider = new WaitingScreenTrackerProvider(sdkComponent);
            this.waitingScreenTrackerProvider = waitingScreenTrackerProvider;
            this.workflowLoadingViewModelProvider = WorkflowLoadingViewModel_Factory.create(this.workflowTaskDataSourceProvider, this.onfidoRemoteConfigProvider, this.schedulersProvider, waitingScreenTrackerProvider);
        }

        @Override // com.onfido.workflow.internal.di.WorkflowComponent
        public void inject(WorkflowFragment workflowFragment) {
            injectWorkflowFragment(workflowFragment);
        }

        @Override // com.onfido.workflow.internal.di.WorkflowComponent
        public void inject(WorkflowLoadingFragment workflowLoadingFragment) {
            injectWorkflowLoadingFragment(workflowLoadingFragment);
        }

        private WorkflowFragment injectWorkflowFragment(WorkflowFragment workflowFragment) {
            WorkflowFragment_MembersInjector.injectWorkflowViewModel(workflowFragment, this.workflowViewModelProvider.get());
            WorkflowFragment_MembersInjector.injectWorkflowIntentFactory(workflowFragment, this.workflowIntentLauncherFactoryProvider.get());
            WorkflowFragment_MembersInjector.injectSchedulersProvider(workflowFragment, (SchedulersProvider) Preconditions.checkNotNullFromComponent(this.sdkComponent.schedulersProvider()));
            WorkflowFragment_MembersInjector.injectNavigationManagerHolder(workflowFragment, navigationManagerHolder());
            return workflowFragment;
        }

        private WorkflowLoadingFragment injectWorkflowLoadingFragment(WorkflowLoadingFragment workflowLoadingFragment) {
            WorkflowLoadingFragment_MembersInjector.injectViewModelProvider(workflowLoadingFragment, this.workflowLoadingViewModelProvider);
            return workflowLoadingFragment;
        }

        private static final class OnfidoFetcherProvider implements Provider<OnfidoFetcher> {
            private final SdkComponent sdkComponent;

            OnfidoFetcherProvider(SdkComponent sdkComponent) {
                this.sdkComponent = sdkComponent;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.onfido.javax.inject.Provider
            public OnfidoFetcher get() {
                return (OnfidoFetcher) Preconditions.checkNotNullFromComponent(this.sdkComponent.onfidoFetcher());
            }
        }

        private static final class OnfidoConfigProvider implements Provider<OnfidoConfig> {
            private final SdkComponent sdkComponent;

            OnfidoConfigProvider(SdkComponent sdkComponent) {
                this.sdkComponent = sdkComponent;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.onfido.javax.inject.Provider
            public OnfidoConfig get() {
                return (OnfidoConfig) Preconditions.checkNotNullFromComponent(this.sdkComponent.onfidoConfig());
            }
        }

        private static final class OnfidoSupportedDocumentsRepositoryProvider implements Provider<OnfidoSupportedDocumentsRepository> {
            private final SdkComponent sdkComponent;

            OnfidoSupportedDocumentsRepositoryProvider(SdkComponent sdkComponent) {
                this.sdkComponent = sdkComponent;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.onfido.javax.inject.Provider
            public OnfidoSupportedDocumentsRepository get() {
                return (OnfidoSupportedDocumentsRepository) Preconditions.checkNotNullFromComponent(this.sdkComponent.onfidoSupportedDocumentsRepository());
            }
        }

        private static final class OnfidoRemoteConfigProvider implements Provider<OnfidoRemoteConfig> {
            private final SdkComponent sdkComponent;

            OnfidoRemoteConfigProvider(SdkComponent sdkComponent) {
                this.sdkComponent = sdkComponent;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.onfido.javax.inject.Provider
            public OnfidoRemoteConfig get() {
                return (OnfidoRemoteConfig) Preconditions.checkNotNullFromComponent(this.sdkComponent.onfidoRemoteConfig());
            }
        }

        private static final class GetJsonParserProvider implements Provider<Json> {
            private final SdkComponent sdkComponent;

            GetJsonParserProvider(SdkComponent sdkComponent) {
                this.sdkComponent = sdkComponent;
            }

            @Override // com.onfido.javax.inject.Provider
            public Json get() {
                return (Json) Preconditions.checkNotNullFromComponent(this.sdkComponent.getJsonParser());
            }
        }

        private static final class SchedulersProviderProvider implements Provider<SchedulersProvider> {
            private final SdkComponent sdkComponent;

            SchedulersProviderProvider(SdkComponent sdkComponent) {
                this.sdkComponent = sdkComponent;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.onfido.javax.inject.Provider
            public SchedulersProvider get() {
                return (SchedulersProvider) Preconditions.checkNotNullFromComponent(this.sdkComponent.schedulersProvider());
            }
        }

        private static final class ApplicationContextProvider implements Provider<Context> {
            private final SdkComponent sdkComponent;

            ApplicationContextProvider(SdkComponent sdkComponent) {
                this.sdkComponent = sdkComponent;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.onfido.javax.inject.Provider
            public Context get() {
                return (Context) Preconditions.checkNotNullFromComponent(this.sdkComponent.applicationContext());
            }
        }

        private static final class ProvideApplicantIdProvider implements Provider<ApplicantId> {
            private final SdkComponent sdkComponent;

            ProvideApplicantIdProvider(SdkComponent sdkComponent) {
                this.sdkComponent = sdkComponent;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.onfido.javax.inject.Provider
            public ApplicantId get() {
                return (ApplicantId) Preconditions.checkNotNullFromComponent(this.sdkComponent.provideApplicantId());
            }
        }

        private static final class ProvideCustomerUserHashProvider implements Provider<String> {
            private final SdkComponent sdkComponent;

            ProvideCustomerUserHashProvider(SdkComponent sdkComponent) {
                this.sdkComponent = sdkComponent;
            }

            @Override // com.onfido.javax.inject.Provider
            public String get() {
                return (String) Preconditions.checkNotNullFromComponent(this.sdkComponent.provideCustomerUserHash());
            }
        }

        private static final class OnfidoAnalyticsProvider implements Provider<OnfidoAnalytics> {
            private final SdkComponent sdkComponent;

            OnfidoAnalyticsProvider(SdkComponent sdkComponent) {
                this.sdkComponent = sdkComponent;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.onfido.javax.inject.Provider
            public OnfidoAnalytics get() {
                return (OnfidoAnalytics) Preconditions.checkNotNullFromComponent(this.sdkComponent.onfidoAnalytics());
            }
        }

        private static final class OnfidoApiServiceProvider implements Provider<OnfidoApiService> {
            private final SdkComponent sdkComponent;

            OnfidoApiServiceProvider(SdkComponent sdkComponent) {
                this.sdkComponent = sdkComponent;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.onfido.javax.inject.Provider
            public OnfidoApiService get() {
                return (OnfidoApiService) Preconditions.checkNotNullFromComponent(this.sdkComponent.onfidoApiService());
            }
        }

        private static final class OnfidoTokenProviderProvider implements Provider<OnfidoTokenProvider> {
            private final SdkComponent sdkComponent;

            OnfidoTokenProviderProvider(SdkComponent sdkComponent) {
                this.sdkComponent = sdkComponent;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.onfido.javax.inject.Provider
            public OnfidoTokenProvider get() {
                return (OnfidoTokenProvider) Preconditions.checkNotNullFromComponent(this.sdkComponent.onfidoTokenProvider());
            }
        }

        private static final class WorkflowSupportedDocumentsStoreProvider implements Provider<WorkflowSupportedDocumentsStore> {
            private final SdkComponent sdkComponent;

            WorkflowSupportedDocumentsStoreProvider(SdkComponent sdkComponent) {
                this.sdkComponent = sdkComponent;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.onfido.javax.inject.Provider
            public WorkflowSupportedDocumentsStore get() {
                return (WorkflowSupportedDocumentsStore) Preconditions.checkNotNullFromComponent(this.sdkComponent.workflowSupportedDocumentsStore());
            }
        }

        private static final class ProvideNfcInteractorProvider implements Provider<NfcInteractor> {
            private final SdkComponent sdkComponent;

            ProvideNfcInteractorProvider(SdkComponent sdkComponent) {
                this.sdkComponent = sdkComponent;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.onfido.javax.inject.Provider
            public NfcInteractor get() {
                return (NfcInteractor) Preconditions.checkNotNullFromComponent(this.sdkComponent.provideNfcInteractor());
            }
        }

        private static final class FlowTrackerProvider implements Provider<FlowTracker> {
            private final SdkComponent sdkComponent;

            FlowTrackerProvider(SdkComponent sdkComponent) {
                this.sdkComponent = sdkComponent;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.onfido.javax.inject.Provider
            public FlowTracker get() {
                return (FlowTracker) Preconditions.checkNotNullFromComponent(this.sdkComponent.flowTracker());
            }
        }

        private static final class WaitingScreenTrackerProvider implements Provider<WaitingScreenTracker> {
            private final SdkComponent sdkComponent;

            WaitingScreenTrackerProvider(SdkComponent sdkComponent) {
                this.sdkComponent = sdkComponent;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.onfido.javax.inject.Provider
            public WaitingScreenTracker get() {
                return (WaitingScreenTracker) Preconditions.checkNotNullFromComponent(this.sdkComponent.waitingScreenTracker());
            }
        }
    }
}
