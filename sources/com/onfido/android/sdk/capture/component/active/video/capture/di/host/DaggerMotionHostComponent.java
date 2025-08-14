package com.onfido.android.sdk.capture.component.active.video.capture.di.host;

import android.content.Context;
import android.content.res.Resources;
import com.google.android.gms.common.moduleinstall.ModuleInstallClient;
import com.google.mlkit.vision.face.FaceDetector;
import com.onfido.android.sdk.capture.common.cryptography.PayloadHelper;
import com.onfido.android.sdk.capture.common.di.SdkComponent;
import com.onfido.android.sdk.capture.common.permissions.RuntimePermissionsManager;
import com.onfido.android.sdk.capture.common.permissions.RuntimePermissionsManager_Factory;
import com.onfido.android.sdk.capture.common.preferences.SharedPreferencesDataSource;
import com.onfido.android.sdk.capture.common.preferences.SharedPreferencesDataSource_Factory;
import com.onfido.android.sdk.capture.component.active.video.capture.data.demo.MotionDemoDataSource;
import com.onfido.android.sdk.capture.component.active.video.capture.data.demo.MotionDemoDataSource_Factory;
import com.onfido.android.sdk.capture.component.active.video.capture.data.remote.MotionApi;
import com.onfido.android.sdk.capture.component.active.video.capture.data.remote.MotionMetaDataHelper;
import com.onfido.android.sdk.capture.component.active.video.capture.data.remote.MotionMetaDataHelper_Factory;
import com.onfido.android.sdk.capture.component.active.video.capture.data.remote.MotionRemoteDataSource;
import com.onfido.android.sdk.capture.component.active.video.capture.data.remote.MotionRemoteDataSource_Factory;
import com.onfido.android.sdk.capture.component.active.video.capture.data.repository.ActiveVideoCaptureRepositoryImpl;
import com.onfido.android.sdk.capture.component.active.video.capture.data.repository.ActiveVideoCaptureRepositoryImpl_Factory;
import com.onfido.android.sdk.capture.component.active.video.capture.data.repository.MotionDataSource;
import com.onfido.android.sdk.capture.component.active.video.capture.data.repository.mapper.ThrowableToErrorMessageMapper;
import com.onfido.android.sdk.capture.component.active.video.capture.data.repository.mapper.ThrowableToErrorMessageMapper_Factory;
import com.onfido.android.sdk.capture.component.active.video.capture.domain.usecase.GetFaceDetectionModuleAvailabilityUseCase;
import com.onfido.android.sdk.capture.component.active.video.capture.domain.usecase.UploadActiveVideoCaptureUseCase;
import com.onfido.android.sdk.capture.component.active.video.capture.domain.usecase.UploadActiveVideoCaptureUseCase_Factory;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.host.C0569MotionHostViewModel_Factory;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.host.MotionHostFragment;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.host.MotionHostFragment_MembersInjector;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.host.MotionHostViewModel;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.host.MotionHostViewModel_Factory_Impl;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.intro.MotionIntroFragment;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.intro.MotionIntroFragment_MembersInjector;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.nofacedetected.MotionNoFaceDetectedFragment;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.nofacedetected.MotionNoFaceDetectedFragment_MembersInjector;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.upload.MotionUploadFragment;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.upload.MotionUploadFragment_MembersInjector;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.upload.MotionUploadViewModel;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.upload.MotionUploadViewModel_Factory;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.uploaderror.MotionUploadErrorFragment;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.uploaderror.MotionUploadErrorFragment_MembersInjector;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.OnfidoAnalytics;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.WaitingScreenTracker;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.metadata.SdkUploadMetadataHelper;
import com.onfido.android.sdk.capture.internal.navigation.Navigator;
import com.onfido.android.sdk.capture.internal.navigation.OnfidoNavigation;
import com.onfido.android.sdk.capture.internal.navigation.navigator.NavigationManagerHolder;
import com.onfido.android.sdk.capture.internal.token.OnfidoTokenProvider;
import com.onfido.android.sdk.capture.internal.util.DispatchersProvider;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.android.sdk.capture.ui.options.MotionCaptureVariantOptions;
import com.onfido.android.sdk.capture.utils.TimeProvider;
import com.onfido.api.client.OnfidoFetcher;
import com.onfido.api.client.data.SdkUploadMetaData;
import com.onfido.dagger.internal.DoubleCheck;
import com.onfido.dagger.internal.Preconditions;
import com.onfido.javax.inject.Provider;
import kotlinx.serialization.json.Json;

/* loaded from: classes2.dex */
public final class DaggerMotionHostComponent {

    public static final class Builder {
        private MotionHostModule motionHostModule;
        private SdkComponent sdkComponent;

        private Builder() {
        }

        public MotionHostComponent build() {
            Preconditions.checkBuilderRequirement(this.motionHostModule, MotionHostModule.class);
            Preconditions.checkBuilderRequirement(this.sdkComponent, SdkComponent.class);
            return new MotionHostComponentImpl(this.motionHostModule, this.sdkComponent);
        }

        public Builder motionHostModule(MotionHostModule motionHostModule) {
            this.motionHostModule = (MotionHostModule) Preconditions.checkNotNull(motionHostModule);
            return this;
        }

        @Deprecated
        public Builder navigationModule(NavigationModule navigationModule) {
            Preconditions.checkNotNull(navigationModule);
            return this;
        }

        public Builder sdkComponent(SdkComponent sdkComponent) {
            this.sdkComponent = (SdkComponent) Preconditions.checkNotNull(sdkComponent);
            return this;
        }
    }

    private static final class MotionHostComponentImpl implements MotionHostComponent {
        private Provider<ActiveVideoCaptureRepositoryImpl> activeVideoCaptureRepositoryImplProvider;
        private Provider<Context> applicationContextProvider;
        private Provider<MotionHostViewModel.Factory> factoryProvider;
        private Provider<Json> getJsonParserProvider;
        private Provider<MotionDemoDataSource> motionDemoDataSourceProvider;
        private final MotionHostComponentImpl motionHostComponentImpl;
        private final MotionHostModule motionHostModule;
        private C0569MotionHostViewModel_Factory motionHostViewModelProvider;
        private Provider<MotionMetaDataHelper> motionMetaDataHelperProvider;
        private Provider<MotionRemoteDataSource> motionRemoteDataSourceProvider;
        private Provider<MotionUploadViewModel> motionUploadViewModelProvider;
        private Provider<OnfidoAnalytics> onfidoAnalyticsProvider;
        private Provider<OnfidoFetcher> onfidoFetcherProvider;
        private Provider<OnfidoRemoteConfig> onfidoRemoteConfigProvider;
        private Provider<OnfidoTokenProvider> onfidoTokenProvider;
        private Provider<GetFaceDetectionModuleAvailabilityUseCase> provideFaceDetectionModuleAvailabilityUseCaseProvider;
        private Provider<FaceDetector> provideMLKitFaceDetectorProvider;
        private Provider<ModuleInstallClient> provideModuleInstallClientProvider;
        private Provider<MotionApi> provideMotionApiProvider;
        private Provider<MotionDataSource> provideMotionDataSourceProvider;
        private Provider<MotionCaptureVariantOptions> provideMotionOptionsProvider;
        private Provider<NavigationManagerHolder> provideNavigationManagerHolderProvider;
        private Provider<Navigator> provideNavigatorProvider;
        private Provider<OnfidoNavigation> provideOnfidoNavigationProvider;
        private Provider<PayloadHelper> providePayloadHelper$onfido_capture_sdk_core_releaseProvider;
        private Provider<Resources> provideResourcesProvider;
        private Provider<SdkUploadMetadataHelper> provideSdkUploadMetadataHelper$onfido_capture_sdk_core_releaseProvider;
        private Provider<SdkUploadMetaData> provideUploadMetadataProvider;
        private Provider<RuntimePermissionsManager> runtimePermissionsManagerProvider;
        private Provider<SchedulersProvider> schedulersProvider;
        private final SdkComponent sdkComponent;
        private Provider<SharedPreferencesDataSource> sharedPreferencesDataSourceProvider;
        private Provider<ThrowableToErrorMessageMapper> throwableToErrorMessageMapperProvider;
        private Provider<UploadActiveVideoCaptureUseCase> uploadActiveVideoCaptureUseCaseProvider;
        private Provider<WaitingScreenTracker> waitingScreenTrackerProvider;

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

        private MotionHostComponentImpl(MotionHostModule motionHostModule, SdkComponent sdkComponent) {
            this.motionHostComponentImpl = this;
            this.sdkComponent = sdkComponent;
            this.motionHostModule = motionHostModule;
            initialize(motionHostModule, sdkComponent);
        }

        private ActiveVideoCaptureRepositoryImpl activeVideoCaptureRepositoryImpl() {
            return new ActiveVideoCaptureRepositoryImpl(this.provideMotionDataSourceProvider.get(), throwableToErrorMessageMapper(), (Context) Preconditions.checkNotNullFromComponent(this.sdkComponent.applicationContext()));
        }

        private void initialize(MotionHostModule motionHostModule, final SdkComponent sdkComponent) {
            SchedulersProviderProvider schedulersProviderProvider = new SchedulersProviderProvider(sdkComponent);
            this.schedulersProvider = schedulersProviderProvider;
            Provider<OnfidoNavigation> provider = DoubleCheck.provider(NavigationModule_ProvideOnfidoNavigationFactory.create(schedulersProviderProvider));
            this.provideOnfidoNavigationProvider = provider;
            this.provideNavigatorProvider = NavigationModule_ProvideNavigatorFactory.create(provider);
            this.provideNavigationManagerHolderProvider = NavigationModule_ProvideNavigationManagerHolderFactory.create(this.provideOnfidoNavigationProvider);
            this.applicationContextProvider = new ApplicationContextProvider(sdkComponent);
            GetJsonParserProvider getJsonParserProvider = new GetJsonParserProvider(sdkComponent);
            this.getJsonParserProvider = getJsonParserProvider;
            SharedPreferencesDataSource_Factory sharedPreferencesDataSource_FactoryCreate = SharedPreferencesDataSource_Factory.create(this.applicationContextProvider, getJsonParserProvider);
            this.sharedPreferencesDataSourceProvider = sharedPreferencesDataSource_FactoryCreate;
            this.runtimePermissionsManagerProvider = RuntimePermissionsManager_Factory.create(this.applicationContextProvider, sharedPreferencesDataSource_FactoryCreate);
            this.onfidoAnalyticsProvider = new OnfidoAnalyticsProvider(sdkComponent);
            this.onfidoRemoteConfigProvider = new OnfidoRemoteConfigProvider(sdkComponent);
            this.provideModuleInstallClientProvider = MotionHostModule_ProvideModuleInstallClientFactory.create(motionHostModule, this.applicationContextProvider);
            MotionHostModule_ProvideMLKitFaceDetectorFactory motionHostModule_ProvideMLKitFaceDetectorFactoryCreate = MotionHostModule_ProvideMLKitFaceDetectorFactory.create(motionHostModule);
            this.provideMLKitFaceDetectorProvider = motionHostModule_ProvideMLKitFaceDetectorFactoryCreate;
            MotionHostModule_ProvideFaceDetectionModuleAvailabilityUseCaseFactory motionHostModule_ProvideFaceDetectionModuleAvailabilityUseCaseFactoryCreate = MotionHostModule_ProvideFaceDetectionModuleAvailabilityUseCaseFactory.create(motionHostModule, this.provideModuleInstallClientProvider, motionHostModule_ProvideMLKitFaceDetectorFactoryCreate);
            this.provideFaceDetectionModuleAvailabilityUseCaseProvider = motionHostModule_ProvideFaceDetectionModuleAvailabilityUseCaseFactoryCreate;
            C0569MotionHostViewModel_Factory c0569MotionHostViewModel_FactoryCreate = C0569MotionHostViewModel_Factory.create(this.provideNavigatorProvider, this.provideNavigationManagerHolderProvider, this.runtimePermissionsManagerProvider, this.onfidoAnalyticsProvider, this.onfidoRemoteConfigProvider, motionHostModule_ProvideFaceDetectionModuleAvailabilityUseCaseFactoryCreate, this.schedulersProvider);
            this.motionHostViewModelProvider = c0569MotionHostViewModel_FactoryCreate;
            this.factoryProvider = MotionHostViewModel_Factory_Impl.create(c0569MotionHostViewModel_FactoryCreate);
            this.onfidoTokenProvider = new OnfidoTokenProviderProvider(sdkComponent);
            this.motionDemoDataSourceProvider = MotionDemoDataSource_Factory.create(this.getJsonParserProvider);
            this.provideMotionOptionsProvider = MotionHostModule_ProvideMotionOptionsFactory.create(motionHostModule);
            OnfidoFetcherProvider onfidoFetcherProvider = new OnfidoFetcherProvider(sdkComponent);
            this.onfidoFetcherProvider = onfidoFetcherProvider;
            this.provideMotionApiProvider = DoubleCheck.provider(MotionHostModule_ProvideMotionApiFactory.create(motionHostModule, onfidoFetcherProvider));
            this.providePayloadHelper$onfido_capture_sdk_core_releaseProvider = new Provider<PayloadHelper>(sdkComponent) { // from class: com.onfido.android.sdk.capture.component.active.video.capture.di.host.DaggerMotionHostComponent$MotionHostComponentImpl$ProvidePayloadHelper$onfido_capture_sdk_core_releaseProvider
                private final SdkComponent sdkComponent;

                {
                    this.sdkComponent = sdkComponent;
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.onfido.javax.inject.Provider
                public PayloadHelper get() {
                    return (PayloadHelper) Preconditions.checkNotNullFromComponent(this.sdkComponent.providePayloadHelper$onfido_capture_sdk_core_release());
                }
            };
            Provider<SdkUploadMetadataHelper> provider2 = new Provider<SdkUploadMetadataHelper>(sdkComponent) { // from class: com.onfido.android.sdk.capture.component.active.video.capture.di.host.DaggerMotionHostComponent$MotionHostComponentImpl$ProvideSdkUploadMetadataHelper$onfido_capture_sdk_core_releaseProvider
                private final SdkComponent sdkComponent;

                {
                    this.sdkComponent = sdkComponent;
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.onfido.javax.inject.Provider
                public SdkUploadMetadataHelper get() {
                    return (SdkUploadMetadataHelper) Preconditions.checkNotNullFromComponent(this.sdkComponent.provideSdkUploadMetadataHelper$onfido_capture_sdk_core_release());
                }
            };
            this.provideSdkUploadMetadataHelper$onfido_capture_sdk_core_releaseProvider = provider2;
            MotionHostModule_ProvideUploadMetadataFactory motionHostModule_ProvideUploadMetadataFactoryCreate = MotionHostModule_ProvideUploadMetadataFactory.create(motionHostModule, provider2);
            this.provideUploadMetadataProvider = motionHostModule_ProvideUploadMetadataFactoryCreate;
            MotionMetaDataHelper_Factory motionMetaDataHelper_FactoryCreate = MotionMetaDataHelper_Factory.create(this.applicationContextProvider, motionHostModule_ProvideUploadMetadataFactoryCreate, this.getJsonParserProvider);
            this.motionMetaDataHelperProvider = motionMetaDataHelper_FactoryCreate;
            MotionRemoteDataSource_Factory motionRemoteDataSource_FactoryCreate = MotionRemoteDataSource_Factory.create(this.provideMotionOptionsProvider, this.provideMotionApiProvider, this.providePayloadHelper$onfido_capture_sdk_core_releaseProvider, motionMetaDataHelper_FactoryCreate);
            this.motionRemoteDataSourceProvider = motionRemoteDataSource_FactoryCreate;
            this.provideMotionDataSourceProvider = DoubleCheck.provider(MotionHostModule_ProvideMotionDataSourceFactory.create(motionHostModule, this.onfidoTokenProvider, this.motionDemoDataSourceProvider, motionRemoteDataSource_FactoryCreate));
            MotionHostModule_ProvideResourcesFactory motionHostModule_ProvideResourcesFactoryCreate = MotionHostModule_ProvideResourcesFactory.create(motionHostModule, this.applicationContextProvider);
            this.provideResourcesProvider = motionHostModule_ProvideResourcesFactoryCreate;
            ThrowableToErrorMessageMapper_Factory throwableToErrorMessageMapper_FactoryCreate = ThrowableToErrorMessageMapper_Factory.create(this.getJsonParserProvider, motionHostModule_ProvideResourcesFactoryCreate);
            this.throwableToErrorMessageMapperProvider = throwableToErrorMessageMapper_FactoryCreate;
            ActiveVideoCaptureRepositoryImpl_Factory activeVideoCaptureRepositoryImpl_FactoryCreate = ActiveVideoCaptureRepositoryImpl_Factory.create(this.provideMotionDataSourceProvider, throwableToErrorMessageMapper_FactoryCreate, this.applicationContextProvider);
            this.activeVideoCaptureRepositoryImplProvider = activeVideoCaptureRepositoryImpl_FactoryCreate;
            this.uploadActiveVideoCaptureUseCaseProvider = UploadActiveVideoCaptureUseCase_Factory.create(activeVideoCaptureRepositoryImpl_FactoryCreate, this.schedulersProvider);
            WaitingScreenTrackerProvider waitingScreenTrackerProvider = new WaitingScreenTrackerProvider(sdkComponent);
            this.waitingScreenTrackerProvider = waitingScreenTrackerProvider;
            this.motionUploadViewModelProvider = MotionUploadViewModel_Factory.create(this.uploadActiveVideoCaptureUseCaseProvider, this.schedulersProvider, this.onfidoAnalyticsProvider, waitingScreenTrackerProvider);
        }

        private MotionHostFragment injectMotionHostFragment(MotionHostFragment motionHostFragment) {
            MotionHostFragment_MembersInjector.injectRuntimePermissionsManager(motionHostFragment, runtimePermissionsManager());
            MotionHostFragment_MembersInjector.injectMotionHostViewModelFactory(motionHostFragment, this.factoryProvider.get());
            return motionHostFragment;
        }

        private MotionIntroFragment injectMotionIntroFragment(MotionIntroFragment motionIntroFragment) {
            MotionIntroFragment_MembersInjector.injectAnalytics(motionIntroFragment, (OnfidoAnalytics) Preconditions.checkNotNullFromComponent(this.sdkComponent.onfidoAnalytics()));
            MotionIntroFragment_MembersInjector.injectActiveVideoCaptureRepository(motionIntroFragment, activeVideoCaptureRepositoryImpl());
            MotionIntroFragment_MembersInjector.injectSchedulersProvider(motionIntroFragment, (SchedulersProvider) Preconditions.checkNotNullFromComponent(this.sdkComponent.schedulersProvider()));
            MotionIntroFragment_MembersInjector.injectStorage(motionIntroFragment, sharedPreferencesDataSource());
            return motionIntroFragment;
        }

        private MotionNoFaceDetectedFragment injectMotionNoFaceDetectedFragment(MotionNoFaceDetectedFragment motionNoFaceDetectedFragment) {
            MotionNoFaceDetectedFragment_MembersInjector.injectAnalytics(motionNoFaceDetectedFragment, (OnfidoAnalytics) Preconditions.checkNotNullFromComponent(this.sdkComponent.onfidoAnalytics()));
            return motionNoFaceDetectedFragment;
        }

        private MotionUploadErrorFragment injectMotionUploadErrorFragment(MotionUploadErrorFragment motionUploadErrorFragment) {
            MotionUploadErrorFragment_MembersInjector.injectAnalytics(motionUploadErrorFragment, (OnfidoAnalytics) Preconditions.checkNotNullFromComponent(this.sdkComponent.onfidoAnalytics()));
            return motionUploadErrorFragment;
        }

        private MotionUploadFragment injectMotionUploadFragment(MotionUploadFragment motionUploadFragment) {
            MotionUploadFragment_MembersInjector.injectViewModelProvider(motionUploadFragment, this.motionUploadViewModelProvider);
            return motionUploadFragment;
        }

        private Resources resources() {
            return MotionHostModule_ProvideResourcesFactory.provideResources(this.motionHostModule, (Context) Preconditions.checkNotNullFromComponent(this.sdkComponent.applicationContext()));
        }

        private RuntimePermissionsManager runtimePermissionsManager() {
            return new RuntimePermissionsManager((Context) Preconditions.checkNotNullFromComponent(this.sdkComponent.applicationContext()), sharedPreferencesDataSource());
        }

        private SharedPreferencesDataSource sharedPreferencesDataSource() {
            return new SharedPreferencesDataSource((Context) Preconditions.checkNotNullFromComponent(this.sdkComponent.applicationContext()), (Json) Preconditions.checkNotNullFromComponent(this.sdkComponent.getJsonParser()));
        }

        private ThrowableToErrorMessageMapper throwableToErrorMessageMapper() {
            return new ThrowableToErrorMessageMapper((Json) Preconditions.checkNotNullFromComponent(this.sdkComponent.getJsonParser()), resources());
        }

        @Override // com.onfido.android.sdk.capture.component.active.video.capture.di.host.MotionHostComponent
        public Context applicationContext() {
            return (Context) Preconditions.checkNotNullFromComponent(this.sdkComponent.applicationContext());
        }

        @Override // com.onfido.android.sdk.capture.component.active.video.capture.di.host.MotionHostComponent
        public DispatchersProvider dispatchersProvider() {
            return (DispatchersProvider) Preconditions.checkNotNullFromComponent(this.sdkComponent.dispatchersProvider());
        }

        @Override // com.onfido.android.sdk.capture.component.active.video.capture.di.host.MotionHostComponent
        public void inject(MotionHostFragment motionHostFragment) {
            injectMotionHostFragment(motionHostFragment);
        }

        @Override // com.onfido.android.sdk.capture.component.active.video.capture.di.host.MotionHostComponent
        public MotionCaptureVariantOptions motionOptions() {
            return MotionHostModule_ProvideMotionOptionsFactory.provideMotionOptions(this.motionHostModule);
        }

        @Override // com.onfido.android.sdk.capture.component.active.video.capture.di.host.MotionHostComponent
        public OnfidoAnalytics onfidoAnalytics() {
            return (OnfidoAnalytics) Preconditions.checkNotNullFromComponent(this.sdkComponent.onfidoAnalytics());
        }

        @Override // com.onfido.android.sdk.capture.component.active.video.capture.di.host.MotionHostComponent
        public OnfidoRemoteConfig onfidoRemoteConfig() {
            return (OnfidoRemoteConfig) Preconditions.checkNotNullFromComponent(this.sdkComponent.onfidoRemoteConfig());
        }

        @Override // com.onfido.android.sdk.capture.component.active.video.capture.di.host.MotionHostComponent
        public TimeProvider provideTimeProvider() {
            return (TimeProvider) Preconditions.checkNotNullFromComponent(this.sdkComponent.provideTimeProvider$onfido_capture_sdk_core_release());
        }

        @Override // com.onfido.android.sdk.capture.component.active.video.capture.di.host.MotionHostComponent
        public SchedulersProvider schedulersProvider() {
            return (SchedulersProvider) Preconditions.checkNotNullFromComponent(this.sdkComponent.schedulersProvider());
        }

        @Override // com.onfido.android.sdk.capture.component.active.video.capture.di.host.MotionHostComponent
        public SdkUploadMetadataHelper sdkUploadMetadataHelper() {
            return (SdkUploadMetadataHelper) Preconditions.checkNotNullFromComponent(this.sdkComponent.provideSdkUploadMetadataHelper$onfido_capture_sdk_core_release());
        }

        @Override // com.onfido.android.sdk.capture.component.active.video.capture.di.host.MotionHostComponent
        public void inject(MotionIntroFragment motionIntroFragment) {
            injectMotionIntroFragment(motionIntroFragment);
        }

        @Override // com.onfido.android.sdk.capture.component.active.video.capture.di.host.MotionHostComponent
        public void inject(MotionNoFaceDetectedFragment motionNoFaceDetectedFragment) {
            injectMotionNoFaceDetectedFragment(motionNoFaceDetectedFragment);
        }

        @Override // com.onfido.android.sdk.capture.component.active.video.capture.di.host.MotionHostComponent
        public void inject(MotionUploadFragment motionUploadFragment) {
            injectMotionUploadFragment(motionUploadFragment);
        }

        @Override // com.onfido.android.sdk.capture.component.active.video.capture.di.host.MotionHostComponent
        public void inject(MotionUploadErrorFragment motionUploadErrorFragment) {
            injectMotionUploadErrorFragment(motionUploadErrorFragment);
        }
    }

    private DaggerMotionHostComponent() {
    }

    public static Builder builder() {
        return new Builder();
    }
}
