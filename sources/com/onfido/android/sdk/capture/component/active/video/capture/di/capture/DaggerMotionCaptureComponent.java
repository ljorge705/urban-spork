package com.onfido.android.sdk.capture.component.active.video.capture.di.capture;

import android.content.Context;
import androidx.camera.lifecycle.ProcessCameraProvider;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.mlkit.vision.face.Face;
import com.onfido.android.sdk.capture.common.result.HapticFeedback;
import com.onfido.android.sdk.capture.component.active.video.capture.di.host.MotionHostComponent;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.C0534MotionCameraXController_Factory;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.MotionCameraControllerFactory;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.MotionCameraXController;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.MotionCameraXController_Factory_Impl;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.MotionImage;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.C0539MotionCamera2Controller_Factory;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.Camera2Wrapper;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.Camera2WrapperImpl;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.Camera2WrapperImpl_Factory;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.MotionCamera2Controller;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.MotionCamera2Controller_Factory_Impl;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.recorder.RecorderWrapper;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.recorder.RecorderWrapperFactory;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.recorder.RecorderWrapperFactory_Factory;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.recorder.persistentsurface.IsPersistentSurfaceSupportedUseCase;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.recorder.persistentsurface.IsPersistentSurfaceSupportedUseCaseImpl;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.recorder.persistentsurface.IsPersistentSurfaceSupportedUseCaseImpl_Factory;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.recorder.persistentsurface.LocalPersistentRecorderSurfaceRepository;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.recorder.persistentsurface.LocalPersistentRecorderSurfaceRepository_Factory;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.recorder.persistentsurface.PersistentRecorderSurfaceRepository;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.util.ApiLevelUtil_Factory;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.util.SurfaceSizeProvider_Factory;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.FaceDetectorAvcMLKit;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.FaceDetectorAvcMLKitResultMapper;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.FaceDetectorAvcMLKitResultMapper_Factory;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.FaceDetectorAvcMLKit_Factory;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.FaceDetectorAvcResultMapper;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.FaceDetectorAvcResultMapperTestImpl;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.FaceDetectorAvcResultMapperTestImpl_Factory;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.FaceDetectorAvcTflite;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.FaceDetectorAvcTfliteResultMapper;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.FaceDetectorAvcTfliteResultMapper_Factory;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.FaceDetectorAvcTflite_Factory;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.MotionFaceDetectorFactory;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.tfmodel.BlazeFaceModel;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.tfmodel.BlazeFaceModel_Factory;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.tfmodel.Detection;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.util.CoordinatesTransformer_Factory;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.util.YawAngleCalculator_Factory;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.MotionCaptureFragment;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.MotionCaptureFragment_MembersInjector;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.preview.PreviewBitmapHelper_Factory;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.C0565MotionCaptureViewModelImpl_Factory;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.MotionCaptureViewModelImpl;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.MotionCaptureViewModelImpl_Factory_Impl;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.headturn.HeadTurnGuidanceViewModel;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.headturn.HeadTurnGuidanceViewModelImpl;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.headturn.HeadTurnGuidanceViewModelImpl_Factory;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.mapper.ToFaceAlignmentFeedbackAccessibilityMapper_Factory;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.mapper.ToFaceAlignmentFeedbackMapper_Factory;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.mapper.ToFaceAlignmentMapper;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.mapper.ToFaceAlignmentMapper_Factory;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.util.AudioFocusHelper;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.util.AudioFocusHelper_Factory;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.util.AvcTimer;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.util.AvcTimer_Factory;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.util.FaceAlignmentChecks_Factory;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.util.MicAvailabilityHelper;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.util.MicAvailabilityHelper_Factory;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.OnfidoAnalytics;
import com.onfido.android.sdk.capture.internal.camera.camerax.C0579CameraX_Factory;
import com.onfido.android.sdk.capture.internal.camera.camerax.CameraX;
import com.onfido.android.sdk.capture.internal.camera.camerax.CameraXImageAnalysisUseCase;
import com.onfido.android.sdk.capture.internal.camera.camerax.CameraXImageAnalysisUseCase_Factory;
import com.onfido.android.sdk.capture.internal.camera.camerax.CameraXTakePictureUseCase;
import com.onfido.android.sdk.capture.internal.camera.camerax.CameraXTakePictureUseCase_Factory;
import com.onfido.android.sdk.capture.internal.camera.camerax.CameraXTakeVideoUseCase;
import com.onfido.android.sdk.capture.internal.camera.camerax.CameraXTakeVideoUseCase_Factory;
import com.onfido.android.sdk.capture.internal.camera.camerax.CameraX_Factory_Impl;
import com.onfido.android.sdk.capture.internal.camera.camerax.FrameSampler;
import com.onfido.android.sdk.capture.internal.camera.camerax.ImageAnalyzer;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.service.AnnouncementService;
import com.onfido.android.sdk.capture.internal.service.AnnouncementService_Factory;
import com.onfido.android.sdk.capture.internal.util.DispatchersProvider;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.android.sdk.capture.ui.options.MotionCaptureVariantOptions;
import com.onfido.android.sdk.capture.utils.DeviceUtils_Factory;
import com.onfido.android.sdk.capture.utils.RawResourceReader;
import com.onfido.android.sdk.capture.utils.RawResourceReader_Factory;
import com.onfido.android.sdk.capture.utils.TimeProvider;
import com.onfido.api.client.data.SdkConfiguration;
import com.onfido.dagger.internal.DoubleCheck;
import com.onfido.dagger.internal.Preconditions;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class DaggerMotionCaptureComponent {

    public static final class Builder {
        private MotionFrameSamplerModule motionFrameSamplerModule;
        private MotionHostComponent motionHostComponent;

        private Builder() {
        }

        public MotionCaptureComponent build() {
            if (this.motionFrameSamplerModule == null) {
                this.motionFrameSamplerModule = new MotionFrameSamplerModule();
            }
            Preconditions.checkBuilderRequirement(this.motionHostComponent, MotionHostComponent.class);
            return new MotionCaptureComponentImpl(this.motionFrameSamplerModule, this.motionHostComponent);
        }

        public Builder motionFrameSamplerModule(MotionFrameSamplerModule motionFrameSamplerModule) {
            this.motionFrameSamplerModule = (MotionFrameSamplerModule) Preconditions.checkNotNull(motionFrameSamplerModule);
            return this;
        }

        public Builder motionHostComponent(MotionHostComponent motionHostComponent) {
            this.motionHostComponent = (MotionHostComponent) Preconditions.checkNotNull(motionHostComponent);
            return this;
        }
    }

    private static final class MotionCaptureComponentImpl implements MotionCaptureComponent {
        private Provider<AnnouncementService> announcementServiceProvider;
        private Provider<Context> applicationContextProvider;
        private Provider<AudioFocusHelper> audioFocusHelperProvider;
        private Provider<AvcTimer> avcTimerProvider;
        private Provider<BlazeFaceModel> blazeFaceModelProvider;
        private Provider<Camera2WrapperImpl> camera2WrapperImplProvider;
        private Provider<CameraXImageAnalysisUseCase> cameraXImageAnalysisUseCaseProvider;
        private C0579CameraX_Factory cameraXProvider;
        private Provider<CameraXTakePictureUseCase> cameraXTakePictureUseCaseProvider;
        private Provider<CameraXTakeVideoUseCase> cameraXTakeVideoUseCaseProvider;
        private Provider<DispatchersProvider> dispatchersProvider;
        private Provider<FaceDetectorAvcMLKit> faceDetectorAvcMLKitProvider;
        private Provider<FaceDetectorAvcMLKitResultMapper> faceDetectorAvcMLKitResultMapperProvider;
        private Provider<FaceDetectorAvcResultMapperTestImpl<Object>> faceDetectorAvcResultMapperTestImplProvider;
        private Provider<FaceDetectorAvcTflite> faceDetectorAvcTfliteProvider;
        private Provider<FaceDetectorAvcTfliteResultMapper> faceDetectorAvcTfliteResultMapperProvider;
        private Provider<CameraX.Factory> factoryProvider;
        private Provider<MotionCameraXController.Factory> factoryProvider2;
        private Provider<MotionCamera2Controller.Factory> factoryProvider3;
        private Provider<MotionCaptureViewModelImpl.Factory> factoryProvider4;
        private Provider<HeadTurnGuidanceViewModelImpl> headTurnGuidanceViewModelImplProvider;
        private Provider<IsPersistentSurfaceSupportedUseCaseImpl> isPersistentSurfaceSupportedUseCaseImplProvider;
        private Provider<LocalPersistentRecorderSurfaceRepository> localPersistentRecorderSurfaceRepositoryProvider;
        private Provider<MicAvailabilityHelper> micAvailabilityHelperProvider;
        private C0539MotionCamera2Controller_Factory motionCamera2ControllerProvider;
        private C0534MotionCameraXController_Factory motionCameraXControllerProvider;
        private final MotionCaptureComponentImpl motionCaptureComponentImpl;
        private C0565MotionCaptureViewModelImpl_Factory motionCaptureViewModelImplProvider;
        private final MotionHostComponent motionHostComponent;
        private Provider<MotionCaptureVariantOptions> motionOptionsProvider;
        private Provider<OnfidoAnalytics> onfidoAnalyticsProvider;
        private Provider<OnfidoRemoteConfig> onfidoRemoteConfigProvider;
        private Provider<Camera2Wrapper> provideCamera2WrapperProvider;
        private Provider<ListenableFuture<ProcessCameraProvider>> provideCameraProvider;
        private Provider<HeadTurnGuidanceViewModel> provideHeadTurnGuidanceViewModelProvider;
        private Provider<IsPersistentSurfaceSupportedUseCase> provideIsPersistentSurfaceSupportedUseCaseProvider;
        private Provider<FaceDetectorAvcResultMapper<Face>> provideMLKitResultMapperProvider;
        private Provider<SdkConfiguration.MotionCapture.MotionVideoSettings> provideMotionVideoSettingsProvider;
        private Provider<PersistentRecorderSurfaceRepository> providePersistentRecorderSurfaceRepositoryProvider;
        private Provider<RecorderWrapper> provideRecorderWrapperProvider;
        private Provider<FaceDetectorAvcResultMapper<Detection>> provideTFLiteResultMapperProvider;
        private Provider<TimeProvider> provideTimeProvider;
        private Provider<FrameSampler<MotionImage>> providesMotionFrameSamplerProvider;
        private Provider<ImageAnalyzer<MotionImage>> providesMotionImageAnalyzerProvider;
        private Provider<RawResourceReader> rawResourceReaderProvider;
        private Provider<RecorderWrapperFactory> recorderWrapperFactoryProvider;
        private Provider<SchedulersProvider> schedulersProvider;
        private Provider<ToFaceAlignmentMapper> toFaceAlignmentMapperProvider;

        private static final class ApplicationContextProvider implements Provider<Context> {
            private final MotionHostComponent motionHostComponent;

            ApplicationContextProvider(MotionHostComponent motionHostComponent) {
                this.motionHostComponent = motionHostComponent;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.onfido.javax.inject.Provider
            public Context get() {
                return (Context) Preconditions.checkNotNullFromComponent(this.motionHostComponent.applicationContext());
            }
        }

        private static final class DispatchersProviderProvider implements Provider<DispatchersProvider> {
            private final MotionHostComponent motionHostComponent;

            DispatchersProviderProvider(MotionHostComponent motionHostComponent) {
                this.motionHostComponent = motionHostComponent;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.onfido.javax.inject.Provider
            public DispatchersProvider get() {
                return (DispatchersProvider) Preconditions.checkNotNullFromComponent(this.motionHostComponent.dispatchersProvider());
            }
        }

        private static final class MotionOptionsProvider implements Provider<MotionCaptureVariantOptions> {
            private final MotionHostComponent motionHostComponent;

            MotionOptionsProvider(MotionHostComponent motionHostComponent) {
                this.motionHostComponent = motionHostComponent;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.onfido.javax.inject.Provider
            public MotionCaptureVariantOptions get() {
                return (MotionCaptureVariantOptions) Preconditions.checkNotNullFromComponent(this.motionHostComponent.motionOptions());
            }
        }

        private static final class OnfidoAnalyticsProvider implements Provider<OnfidoAnalytics> {
            private final MotionHostComponent motionHostComponent;

            OnfidoAnalyticsProvider(MotionHostComponent motionHostComponent) {
                this.motionHostComponent = motionHostComponent;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.onfido.javax.inject.Provider
            public OnfidoAnalytics get() {
                return (OnfidoAnalytics) Preconditions.checkNotNullFromComponent(this.motionHostComponent.onfidoAnalytics());
            }
        }

        private static final class OnfidoRemoteConfigProvider implements Provider<OnfidoRemoteConfig> {
            private final MotionHostComponent motionHostComponent;

            OnfidoRemoteConfigProvider(MotionHostComponent motionHostComponent) {
                this.motionHostComponent = motionHostComponent;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.onfido.javax.inject.Provider
            public OnfidoRemoteConfig get() {
                return (OnfidoRemoteConfig) Preconditions.checkNotNullFromComponent(this.motionHostComponent.onfidoRemoteConfig());
            }
        }

        private static final class ProvideTimeProviderProvider implements Provider<TimeProvider> {
            private final MotionHostComponent motionHostComponent;

            ProvideTimeProviderProvider(MotionHostComponent motionHostComponent) {
                this.motionHostComponent = motionHostComponent;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.onfido.javax.inject.Provider
            public TimeProvider get() {
                return (TimeProvider) Preconditions.checkNotNullFromComponent(this.motionHostComponent.provideTimeProvider());
            }
        }

        private static final class SchedulersProviderProvider implements Provider<SchedulersProvider> {
            private final MotionHostComponent motionHostComponent;

            SchedulersProviderProvider(MotionHostComponent motionHostComponent) {
                this.motionHostComponent = motionHostComponent;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.onfido.javax.inject.Provider
            public SchedulersProvider get() {
                return (SchedulersProvider) Preconditions.checkNotNullFromComponent(this.motionHostComponent.schedulersProvider());
            }
        }

        private MotionCaptureComponentImpl(MotionFrameSamplerModule motionFrameSamplerModule, MotionHostComponent motionHostComponent) {
            this.motionCaptureComponentImpl = this;
            this.motionHostComponent = motionHostComponent;
            initialize(motionFrameSamplerModule, motionHostComponent);
        }

        private AnnouncementService announcementService() {
            return new AnnouncementService((Context) Preconditions.checkNotNullFromComponent(this.motionHostComponent.applicationContext()));
        }

        private AvcTimer avcTimer() {
            return new AvcTimer((SchedulersProvider) Preconditions.checkNotNullFromComponent(this.motionHostComponent.schedulersProvider()));
        }

        private void initialize(MotionFrameSamplerModule motionFrameSamplerModule, MotionHostComponent motionHostComponent) {
            ApplicationContextProvider applicationContextProvider = new ApplicationContextProvider(motionHostComponent);
            this.applicationContextProvider = applicationContextProvider;
            this.cameraXTakeVideoUseCaseProvider = CameraXTakeVideoUseCase_Factory.create(applicationContextProvider);
            this.cameraXTakePictureUseCaseProvider = CameraXTakePictureUseCase_Factory.create(this.applicationContextProvider);
            ProvideTimeProviderProvider provideTimeProviderProvider = new ProvideTimeProviderProvider(motionHostComponent);
            this.provideTimeProvider = provideTimeProviderProvider;
            this.cameraXImageAnalysisUseCaseProvider = CameraXImageAnalysisUseCase_Factory.create(provideTimeProviderProvider);
            this.dispatchersProvider = new DispatchersProviderProvider(motionHostComponent);
            this.provideCameraProvider = MotionCaptureModule_Companion_ProvideCameraProviderFactory.create(this.applicationContextProvider);
            OnfidoRemoteConfigProvider onfidoRemoteConfigProvider = new OnfidoRemoteConfigProvider(motionHostComponent);
            this.onfidoRemoteConfigProvider = onfidoRemoteConfigProvider;
            C0579CameraX_Factory c0579CameraX_FactoryCreate = C0579CameraX_Factory.create(this.applicationContextProvider, this.cameraXTakeVideoUseCaseProvider, this.cameraXTakePictureUseCaseProvider, this.cameraXImageAnalysisUseCaseProvider, this.dispatchersProvider, this.provideCameraProvider, onfidoRemoteConfigProvider);
            this.cameraXProvider = c0579CameraX_FactoryCreate;
            this.factoryProvider = CameraX_Factory_Impl.create(c0579CameraX_FactoryCreate);
            this.schedulersProvider = new SchedulersProviderProvider(motionHostComponent);
            this.providesMotionFrameSamplerProvider = DoubleCheck.provider(MotionFrameSamplerModule_ProvidesMotionFrameSamplerFactory.create(motionFrameSamplerModule, PreviewBitmapHelper_Factory.create(), this.schedulersProvider));
            this.providesMotionImageAnalyzerProvider = DoubleCheck.provider(MotionFrameSamplerModule_ProvidesMotionImageAnalyzerFactory.create(motionFrameSamplerModule));
            C0534MotionCameraXController_Factory c0534MotionCameraXController_FactoryCreate = C0534MotionCameraXController_Factory.create();
            this.motionCameraXControllerProvider = c0534MotionCameraXController_FactoryCreate;
            this.factoryProvider2 = MotionCameraXController_Factory_Impl.create(c0534MotionCameraXController_FactoryCreate);
            this.provideMotionVideoSettingsProvider = MotionCaptureModule_Companion_ProvideMotionVideoSettingsFactory.create(this.onfidoRemoteConfigProvider);
            Camera2WrapperImpl_Factory camera2WrapperImpl_FactoryCreate = Camera2WrapperImpl_Factory.create(this.applicationContextProvider, SurfaceSizeProvider_Factory.create(), this.provideMotionVideoSettingsProvider);
            this.camera2WrapperImplProvider = camera2WrapperImpl_FactoryCreate;
            this.provideCamera2WrapperProvider = DoubleCheck.provider(camera2WrapperImpl_FactoryCreate);
            RawResourceReader_Factory rawResourceReader_FactoryCreate = RawResourceReader_Factory.create(this.applicationContextProvider);
            this.rawResourceReaderProvider = rawResourceReader_FactoryCreate;
            LocalPersistentRecorderSurfaceRepository_Factory localPersistentRecorderSurfaceRepository_FactoryCreate = LocalPersistentRecorderSurfaceRepository_Factory.create(rawResourceReader_FactoryCreate);
            this.localPersistentRecorderSurfaceRepositoryProvider = localPersistentRecorderSurfaceRepository_FactoryCreate;
            this.providePersistentRecorderSurfaceRepositoryProvider = DoubleCheck.provider(localPersistentRecorderSurfaceRepository_FactoryCreate);
            IsPersistentSurfaceSupportedUseCaseImpl_Factory isPersistentSurfaceSupportedUseCaseImpl_FactoryCreate = IsPersistentSurfaceSupportedUseCaseImpl_Factory.create(ApiLevelUtil_Factory.create(), DeviceUtils_Factory.create(), this.providePersistentRecorderSurfaceRepositoryProvider);
            this.isPersistentSurfaceSupportedUseCaseImplProvider = isPersistentSurfaceSupportedUseCaseImpl_FactoryCreate;
            Provider<IsPersistentSurfaceSupportedUseCase> provider = DoubleCheck.provider(isPersistentSurfaceSupportedUseCaseImpl_FactoryCreate);
            this.provideIsPersistentSurfaceSupportedUseCaseProvider = provider;
            RecorderWrapperFactory_Factory recorderWrapperFactory_FactoryCreate = RecorderWrapperFactory_Factory.create(this.applicationContextProvider, provider, SurfaceSizeProvider_Factory.create());
            this.recorderWrapperFactoryProvider = recorderWrapperFactory_FactoryCreate;
            Provider<RecorderWrapper> provider2 = DoubleCheck.provider(MotionCaptureModule_Companion_ProvideRecorderWrapperFactory.create(recorderWrapperFactory_FactoryCreate));
            this.provideRecorderWrapperProvider = provider2;
            C0539MotionCamera2Controller_Factory c0539MotionCamera2Controller_FactoryCreate = C0539MotionCamera2Controller_Factory.create(this.provideCamera2WrapperProvider, provider2, this.provideIsPersistentSurfaceSupportedUseCaseProvider, this.providesMotionFrameSamplerProvider);
            this.motionCamera2ControllerProvider = c0539MotionCamera2Controller_FactoryCreate;
            this.factoryProvider3 = MotionCamera2Controller_Factory_Impl.create(c0539MotionCamera2Controller_FactoryCreate);
            this.onfidoAnalyticsProvider = new OnfidoAnalyticsProvider(motionHostComponent);
            this.motionOptionsProvider = new MotionOptionsProvider(motionHostComponent);
            this.faceDetectorAvcResultMapperTestImplProvider = DoubleCheck.provider(FaceDetectorAvcResultMapperTestImpl_Factory.create());
            FaceDetectorAvcMLKitResultMapper_Factory faceDetectorAvcMLKitResultMapper_FactoryCreate = FaceDetectorAvcMLKitResultMapper_Factory.create(CoordinatesTransformer_Factory.create());
            this.faceDetectorAvcMLKitResultMapperProvider = faceDetectorAvcMLKitResultMapper_FactoryCreate;
            Provider<FaceDetectorAvcResultMapper<Face>> provider3 = DoubleCheck.provider(MotionCaptureModule_Companion_ProvideMLKitResultMapperFactory.create(this.motionOptionsProvider, this.faceDetectorAvcResultMapperTestImplProvider, faceDetectorAvcMLKitResultMapper_FactoryCreate));
            this.provideMLKitResultMapperProvider = provider3;
            this.faceDetectorAvcMLKitProvider = FaceDetectorAvcMLKit_Factory.create(this.onfidoAnalyticsProvider, this.schedulersProvider, provider3);
            this.blazeFaceModelProvider = BlazeFaceModel_Factory.create(this.applicationContextProvider);
            FaceDetectorAvcTfliteResultMapper_Factory faceDetectorAvcTfliteResultMapper_FactoryCreate = FaceDetectorAvcTfliteResultMapper_Factory.create(CoordinatesTransformer_Factory.create(), YawAngleCalculator_Factory.create());
            this.faceDetectorAvcTfliteResultMapperProvider = faceDetectorAvcTfliteResultMapper_FactoryCreate;
            Provider<FaceDetectorAvcResultMapper<Detection>> provider4 = DoubleCheck.provider(MotionCaptureModule_Companion_ProvideTFLiteResultMapperFactory.create(this.motionOptionsProvider, this.faceDetectorAvcResultMapperTestImplProvider, faceDetectorAvcTfliteResultMapper_FactoryCreate));
            this.provideTFLiteResultMapperProvider = provider4;
            this.faceDetectorAvcTfliteProvider = FaceDetectorAvcTflite_Factory.create(this.blazeFaceModelProvider, this.schedulersProvider, provider4);
            HeadTurnGuidanceViewModelImpl_Factory headTurnGuidanceViewModelImpl_FactoryCreate = HeadTurnGuidanceViewModelImpl_Factory.create(this.onfidoAnalyticsProvider, this.schedulersProvider);
            this.headTurnGuidanceViewModelImplProvider = headTurnGuidanceViewModelImpl_FactoryCreate;
            this.provideHeadTurnGuidanceViewModelProvider = DoubleCheck.provider(headTurnGuidanceViewModelImpl_FactoryCreate);
            this.avcTimerProvider = AvcTimer_Factory.create(this.schedulersProvider);
            this.toFaceAlignmentMapperProvider = ToFaceAlignmentMapper_Factory.create(FaceAlignmentChecks_Factory.create());
            this.announcementServiceProvider = AnnouncementService_Factory.create(this.applicationContextProvider);
            this.micAvailabilityHelperProvider = MicAvailabilityHelper_Factory.create(this.applicationContextProvider);
            this.audioFocusHelperProvider = AudioFocusHelper_Factory.create(this.applicationContextProvider);
            C0565MotionCaptureViewModelImpl_Factory c0565MotionCaptureViewModelImpl_FactoryCreate = C0565MotionCaptureViewModelImpl_Factory.create(this.avcTimerProvider, this.provideHeadTurnGuidanceViewModelProvider, this.toFaceAlignmentMapperProvider, ToFaceAlignmentFeedbackMapper_Factory.create(), ToFaceAlignmentFeedbackAccessibilityMapper_Factory.create(), this.announcementServiceProvider, this.onfidoAnalyticsProvider, this.schedulersProvider, this.micAvailabilityHelperProvider, this.audioFocusHelperProvider);
            this.motionCaptureViewModelImplProvider = c0565MotionCaptureViewModelImpl_FactoryCreate;
            this.factoryProvider4 = MotionCaptureViewModelImpl_Factory_Impl.create(c0565MotionCaptureViewModelImpl_FactoryCreate);
        }

        private MotionCaptureFragment injectMotionCaptureFragment(MotionCaptureFragment motionCaptureFragment) {
            MotionCaptureFragment_MembersInjector.injectMotionOptions(motionCaptureFragment, (MotionCaptureVariantOptions) Preconditions.checkNotNullFromComponent(this.motionHostComponent.motionOptions()));
            MotionCaptureFragment_MembersInjector.injectCameraControllerFactory(motionCaptureFragment, motionCameraControllerFactory());
            MotionCaptureFragment_MembersInjector.injectMotionFaceDetectorFactory(motionCaptureFragment, motionFaceDetectorFactory());
            MotionCaptureFragment_MembersInjector.injectHeadTurnGuidanceViewModel(motionCaptureFragment, this.provideHeadTurnGuidanceViewModelProvider.get());
            MotionCaptureFragment_MembersInjector.injectSchedulersProvider(motionCaptureFragment, (SchedulersProvider) Preconditions.checkNotNullFromComponent(this.motionHostComponent.schedulersProvider()));
            MotionCaptureFragment_MembersInjector.injectAnalytics(motionCaptureFragment, (OnfidoAnalytics) Preconditions.checkNotNullFromComponent(this.motionHostComponent.onfidoAnalytics()));
            MotionCaptureFragment_MembersInjector.injectHapticFeedback(motionCaptureFragment, new HapticFeedback());
            MotionCaptureFragment_MembersInjector.injectDelayStartRecordingTimer(motionCaptureFragment, avcTimer());
            MotionCaptureFragment_MembersInjector.injectDelayTimer(motionCaptureFragment, avcTimer());
            MotionCaptureFragment_MembersInjector.injectAnnouncementService(motionCaptureFragment, announcementService());
            MotionCaptureFragment_MembersInjector.injectViewModelFactory(motionCaptureFragment, this.factoryProvider4.get());
            MotionCaptureFragment_MembersInjector.injectFaceDetectorAvcResultMapperTestImpl(motionCaptureFragment, this.faceDetectorAvcResultMapperTestImplProvider.get());
            return motionCaptureFragment;
        }

        private MotionCameraControllerFactory motionCameraControllerFactory() {
            return new MotionCameraControllerFactory(this.factoryProvider.get(), this.providesMotionFrameSamplerProvider.get(), this.providesMotionImageAnalyzerProvider.get(), this.factoryProvider2.get(), this.factoryProvider3.get(), (OnfidoAnalytics) Preconditions.checkNotNullFromComponent(this.motionHostComponent.onfidoAnalytics()));
        }

        private MotionFaceDetectorFactory motionFaceDetectorFactory() {
            return new MotionFaceDetectorFactory((OnfidoAnalytics) Preconditions.checkNotNullFromComponent(this.motionHostComponent.onfidoAnalytics()), this.faceDetectorAvcMLKitProvider, this.faceDetectorAvcTfliteProvider);
        }

        @Override // com.onfido.android.sdk.capture.component.active.video.capture.di.capture.MotionCaptureComponent
        public void inject(MotionCaptureFragment motionCaptureFragment) {
            injectMotionCaptureFragment(motionCaptureFragment);
        }
    }

    private DaggerMotionCaptureComponent() {
    }

    public static Builder builder() {
        return new Builder();
    }
}
