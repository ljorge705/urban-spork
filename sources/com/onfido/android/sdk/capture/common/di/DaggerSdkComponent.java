package com.onfido.android.sdk.capture.common.di;

import android.content.Context;
import android.media.AudioManager;
import android.nfc.NfcAdapter;
import android.os.ResultReceiver;
import android.telephony.TelephonyManager;
import androidx.camera.lifecycle.ProcessCameraProvider;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.mlkit.vision.barcode.BarcodeScanner;
import com.onfido.android.sdk.FlowConfig;
import com.onfido.android.sdk.capture.OnfidoConfig;
import com.onfido.android.sdk.capture.analytics.IdentityInteractor;
import com.onfido.android.sdk.capture.analytics.IdentityInteractor_Factory;
import com.onfido.android.sdk.capture.antifraud.SignalExtractor;
import com.onfido.android.sdk.capture.antifraud.SignalExtractor_Factory;
import com.onfido.android.sdk.capture.antifraud.SignalFactory;
import com.onfido.android.sdk.capture.antifraud.SignalFactory_Factory;
import com.onfido.android.sdk.capture.audio.VolumeManager;
import com.onfido.android.sdk.capture.common.cryptography.Cryptography;
import com.onfido.android.sdk.capture.common.cryptography.CryptographyHelper;
import com.onfido.android.sdk.capture.common.cryptography.CryptographyHelper_Factory;
import com.onfido.android.sdk.capture.common.cryptography.Cryptography_Factory;
import com.onfido.android.sdk.capture.common.cryptography.PayloadHelper;
import com.onfido.android.sdk.capture.common.cryptography.PayloadHelper_Factory;
import com.onfido.android.sdk.capture.common.di.network.NetworkModule;
import com.onfido.android.sdk.capture.common.di.network.NetworkModule_ProvideInternalOnfidoNetworkInterceptorsProvider$onfido_capture_sdk_core_releaseFactory;
import com.onfido.android.sdk.capture.common.di.network.NetworkModule_ProvideJson$onfido_capture_sdk_core_releaseFactory;
import com.onfido.android.sdk.capture.common.di.network.NetworkModule_ProvideLivenessIntroVideoAPI$onfido_capture_sdk_core_releaseFactory;
import com.onfido.android.sdk.capture.common.di.network.NetworkModule_ProvideOkHttpClient$onfido_capture_sdk_core_releaseFactory;
import com.onfido.android.sdk.capture.common.di.network.NetworkModule_ProvideOnfidoAPI$onfido_capture_sdk_core_releaseFactory;
import com.onfido.android.sdk.capture.common.di.network.NetworkModule_ProvideOnfidoAnalyticsApi$onfido_capture_sdk_core_releaseFactory;
import com.onfido.android.sdk.capture.common.di.network.NetworkModule_ProvideOnfidoFetcher$onfido_capture_sdk_core_releaseFactory;
import com.onfido.android.sdk.capture.common.di.network.NetworkModule_ProvideOnfidoLoggerApi$onfido_capture_sdk_core_releaseFactory;
import com.onfido.android.sdk.capture.common.di.network.NetworkModule_ProvidePerformanceAnalyticsApi$onfido_capture_sdk_core_releaseFactory;
import com.onfido.android.sdk.capture.common.di.network.NetworkModule_ProvideProofOfAddressApi$onfido_capture_sdk_core_releaseFactory;
import com.onfido.android.sdk.capture.common.di.network.NetworkModule_ProvideRetrofit$onfido_capture_sdk_core_releaseFactory;
import com.onfido.android.sdk.capture.common.di.network.NetworkModule_ProvideSupportedDocumentsApi$onfido_capture_sdk_core_releaseFactory;
import com.onfido.android.sdk.capture.common.di.network.NetworkModule_ProvideUserConsentApi$onfido_capture_sdk_core_releaseFactory;
import com.onfido.android.sdk.capture.common.permissions.PermissionsManagementFragment;
import com.onfido.android.sdk.capture.common.permissions.PermissionsManagementFragment_MembersInjector;
import com.onfido.android.sdk.capture.common.permissions.PermissionsManagementPresenter;
import com.onfido.android.sdk.capture.common.permissions.RuntimePermissionsManager;
import com.onfido.android.sdk.capture.common.permissions.RuntimePermissionsManager_Factory;
import com.onfido.android.sdk.capture.common.preferences.SharedPreferencesDataSource;
import com.onfido.android.sdk.capture.common.preferences.SharedPreferencesDataSource_Factory;
import com.onfido.android.sdk.capture.common.result.HapticFeedback;
import com.onfido.android.sdk.capture.config.EnterpriseConfig;
import com.onfido.android.sdk.capture.detector.barcode.BarcodeDetector;
import com.onfido.android.sdk.capture.detector.barcode.BarcodeDetectorEmpty_Factory;
import com.onfido.android.sdk.capture.detector.barcode.BarcodeDetectorGoogle;
import com.onfido.android.sdk.capture.detector.barcode.BarcodeDetectorGoogle_Factory;
import com.onfido.android.sdk.capture.detector.face.FaceDetector;
import com.onfido.android.sdk.capture.detector.face.FaceDetectorEmpty_Factory;
import com.onfido.android.sdk.capture.detector.face.FaceDetectorGoogle;
import com.onfido.android.sdk.capture.detector.face.FaceDetectorGoogle_Factory;
import com.onfido.android.sdk.capture.detector.face.FaceOnDocumentDetector;
import com.onfido.android.sdk.capture.detector.face.FaceOnDocumentDetectorEmpty_Factory;
import com.onfido.android.sdk.capture.detector.face.FaceOnDocumentDetectorGoogle;
import com.onfido.android.sdk.capture.detector.face.FaceOnDocumentDetectorGoogle_Factory;
import com.onfido.android.sdk.capture.detector.mrz.MRZDetector;
import com.onfido.android.sdk.capture.detector.mrz.MRZDetectorEmpty_Factory;
import com.onfido.android.sdk.capture.detector.mrz.MRZDetectorGoogle_Factory;
import com.onfido.android.sdk.capture.detector.mrzextraction.MRZDocumentExtractor;
import com.onfido.android.sdk.capture.detector.mrzextraction.MRZDocumentExtractorEmpty_Factory;
import com.onfido.android.sdk.capture.detector.mrzextraction.MRZDocumentExtractorGoogle;
import com.onfido.android.sdk.capture.detector.mrzextraction.MRZDocumentExtractorGoogle_Factory;
import com.onfido.android.sdk.capture.detector.mrzextraction.TextRecognizerProviderImpl_Factory;
import com.onfido.android.sdk.capture.detector.rectangle.RectangleDetector;
import com.onfido.android.sdk.capture.detector.rectangle.RectangleDetectorEmpty_Factory;
import com.onfido.android.sdk.capture.detector.rectangle.RectangleDetectorGoogle;
import com.onfido.android.sdk.capture.detector.rectangle.RectangleDetectorGoogle_Factory;
import com.onfido.android.sdk.capture.detector.rectangle.RectangleTransformer;
import com.onfido.android.sdk.capture.detector.rectangle.RectangleTransformer_Factory;
import com.onfido.android.sdk.capture.document.DocumentConfigurationManager;
import com.onfido.android.sdk.capture.document.DocumentConfigurationManager_Factory;
import com.onfido.android.sdk.capture.document.supported.data.SupportedDocumentMapper_Factory;
import com.onfido.android.sdk.capture.document.supported.data.remote.datasource.AllDocumentsDataSource;
import com.onfido.android.sdk.capture.document.supported.data.remote.datasource.AllDocumentsRemoteDataSourceImpl;
import com.onfido.android.sdk.capture.document.supported.data.remote.datasource.AllDocumentsRemoteDataSourceImpl_Factory;
import com.onfido.android.sdk.capture.document.supported.data.remote.datasource.SupportedDocumentsApi;
import com.onfido.android.sdk.capture.document.supported.data.remote.datasource.SupportedDocumentsLocalDataSource_Factory;
import com.onfido.android.sdk.capture.document.supported.data.repository.RemoteSupportedDocumentsRepository;
import com.onfido.android.sdk.capture.document.supported.data.repository.RemoteSupportedDocumentsRepository_Factory;
import com.onfido.android.sdk.capture.document.supported.domain.usecase.GetSupportedDocumentsUseCase;
import com.onfido.android.sdk.capture.document.supported.domain.usecase.GetSupportedDocumentsUseCase_Factory;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.AnalyticsMetadataProvider_Factory;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.InhouseAnalyticsRepository;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.InhouseAnalyticsRepository_Factory;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.OnfidoAnalytics;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.OnfidoAnalyticsMapper;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.OnfidoAnalyticsMapper_Factory;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.SdkTokenParser;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.SdkTokenParser_Factory;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.mappers.PublicEventMapper;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.network.OnfidoAnalyticsApi;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.CaptureTracker;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.CaptureTracker_Factory;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.FlowTracker;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.FlowTracker_Factory;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.LivenessTracker;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.LivenessTracker_Factory;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.NfcTracker;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.NfcTracker_Factory;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.PermissionsTracker;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.ScreenTracker;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.ScreenTracker_Factory;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.WaitingScreenTracker;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.WaitingScreenTracker_Factory;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.utils.EventCache_Factory;
import com.onfido.android.sdk.capture.internal.camera.OnfidoImage;
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
import com.onfido.android.sdk.capture.internal.camera.factory.CameraUseCaseConfigProvider;
import com.onfido.android.sdk.capture.internal.camera.factory.DocCaptureCameraConfigProvider;
import com.onfido.android.sdk.capture.internal.camera.factory.OnfidoCameraFactory;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.config.SDKConfigRepository;
import com.onfido.android.sdk.capture.internal.config.SDKConfigRepository_Factory;
import com.onfido.android.sdk.capture.internal.metadata.SdkUploadMetadataHelper;
import com.onfido.android.sdk.capture.internal.metadata.SdkUploadMetadataHelper_Factory;
import com.onfido.android.sdk.capture.internal.navigation.Navigator;
import com.onfido.android.sdk.capture.internal.navigation.OnfidoNavigation;
import com.onfido.android.sdk.capture.internal.navigation.navigator.NavigationManagerHolder;
import com.onfido.android.sdk.capture.internal.nfc.NfcInteractor;
import com.onfido.android.sdk.capture.internal.nfc.NfcInteractorImpl;
import com.onfido.android.sdk.capture.internal.nfc.NfcInteractorImpl_Factory;
import com.onfido.android.sdk.capture.internal.nfc.NfcService;
import com.onfido.android.sdk.capture.internal.nfc.NfcService_MembersInjector;
import com.onfido.android.sdk.capture.internal.nfc.NfcTimeouts;
import com.onfido.android.sdk.capture.internal.nfc.NfcTimeouts_Factory;
import com.onfido.android.sdk.capture.internal.nfc.PassportNfcReader;
import com.onfido.android.sdk.capture.internal.nfc.ShouldLaunchNfcFlowUseCase;
import com.onfido.android.sdk.capture.internal.nfc.ShouldLaunchNfcFlowUseCase_Factory;
import com.onfido.android.sdk.capture.internal.performance.AggregatedPerformanceAnalytics;
import com.onfido.android.sdk.capture.internal.performance.AggregatedPerformanceAnalytics_Factory;
import com.onfido.android.sdk.capture.internal.performance.PerformanceAnalyticsImpl;
import com.onfido.android.sdk.capture.internal.performance.PerformanceAnalyticsImpl_Factory;
import com.onfido.android.sdk.capture.internal.performance.repository.PerformanceAnalyticsApi;
import com.onfido.android.sdk.capture.internal.performance.repository.PerformanceAnalyticsNetwork;
import com.onfido.android.sdk.capture.internal.performance.repository.PerformanceAnalyticsRepository;
import com.onfido.android.sdk.capture.internal.performance.repository.PerformanceAnalyticsRepository_Factory;
import com.onfido.android.sdk.capture.internal.performance.trackers.ScreenLoadTracker;
import com.onfido.android.sdk.capture.internal.performance.trackers.ScreenLoadTracker_Factory;
import com.onfido.android.sdk.capture.internal.service.AnnouncementService;
import com.onfido.android.sdk.capture.internal.service.AnnouncementService_Factory;
import com.onfido.android.sdk.capture.internal.service.VibratorService;
import com.onfido.android.sdk.capture.internal.service.VibratorService_Factory;
import com.onfido.android.sdk.capture.internal.token.OnfidoTokenProvider;
import com.onfido.android.sdk.capture.internal.token.OnfidoTokenProvider_Factory;
import com.onfido.android.sdk.capture.internal.ui.countryselection.CountrySelectionFragment;
import com.onfido.android.sdk.capture.internal.ui.countryselection.CountrySelectionFragment_MembersInjector;
import com.onfido.android.sdk.capture.internal.ui.countryselection.CountrySelectionPresenter;
import com.onfido.android.sdk.capture.internal.ui.countryselection.GetCurrentCountryCodeUseCase;
import com.onfido.android.sdk.capture.internal.ui.countryselection.GetCurrentCountryCodeUseCase_Factory;
import com.onfido.android.sdk.capture.internal.ui.countryselection.OnfidoSupportedDocumentsRepository;
import com.onfido.android.sdk.capture.internal.ui.countryselection.OnfidoSupportedDocumentsRepository_Factory;
import com.onfido.android.sdk.capture.internal.ui.countryselection.SupportedDocumentsRepository;
import com.onfido.android.sdk.capture.internal.usecase.AccessibleDocumentCaptureUseCase;
import com.onfido.android.sdk.capture.internal.usecase.AccessibleDocumentCaptureUseCase_Factory;
import com.onfido.android.sdk.capture.internal.usecase.DocumentPositionUseCase;
import com.onfido.android.sdk.capture.internal.usecase.DocumentPositionUseCase_Factory;
import com.onfido.android.sdk.capture.internal.usecase.DocumentProcessingUseCase;
import com.onfido.android.sdk.capture.internal.usecase.DocumentProcessingUseCase_Factory;
import com.onfido.android.sdk.capture.internal.usecase.FaceProcessingUseCase;
import com.onfido.android.sdk.capture.internal.usecase.FaceProcessingUseCase_Factory;
import com.onfido.android.sdk.capture.internal.usecase.MediaCallbacksUseCase;
import com.onfido.android.sdk.capture.internal.usecase.MediaCallbacksUseCase_Factory;
import com.onfido.android.sdk.capture.internal.usecase.NfcUseCase;
import com.onfido.android.sdk.capture.internal.usecase.NfcUseCase_Factory;
import com.onfido.android.sdk.capture.internal.usecase.validation.DocumentValidationUseCase;
import com.onfido.android.sdk.capture.internal.usecase.validation.DocumentValidationUseCase_Factory;
import com.onfido.android.sdk.capture.internal.util.DispatchersProvider;
import com.onfido.android.sdk.capture.internal.util.FileCache;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.android.sdk.capture.internal.util.SessionIdProvider;
import com.onfido.android.sdk.capture.internal.util.SessionIdProvider_Factory;
import com.onfido.android.sdk.capture.internal.util.environment.EnvironmentIntegrityCheckerImpl;
import com.onfido.android.sdk.capture.internal.util.environment.EnvironmentIntegrityCheckerImpl_Factory;
import com.onfido.android.sdk.capture.internal.util.logging.CrashHandlerWorker;
import com.onfido.android.sdk.capture.internal.util.logging.CrashHandlerWorker_MembersInjector;
import com.onfido.android.sdk.capture.internal.util.logging.LoggerMemoryCachingDataSource_Factory;
import com.onfido.android.sdk.capture.internal.util.logging.LoggerRepository;
import com.onfido.android.sdk.capture.internal.util.logging.LoggerRepository_Factory;
import com.onfido.android.sdk.capture.internal.util.logging.OnfidoLogMapper;
import com.onfido.android.sdk.capture.internal.util.logging.OnfidoLogMapper_Factory;
import com.onfido.android.sdk.capture.internal.util.logging.RemoteLoggerTree;
import com.onfido.android.sdk.capture.internal.util.logging.RemoteLoggerTree_Factory;
import com.onfido.android.sdk.capture.internal.util.logging.network.OnfidoLoggerApi;
import com.onfido.android.sdk.capture.internal.validation.DocumentProcessingResultsFailureAnalyzerImpl;
import com.onfido.android.sdk.capture.internal.validation.DocumentProcessingResultsFailureAnalyzerImpl_Factory;
import com.onfido.android.sdk.capture.internal.validation.RetainableValidationsResult;
import com.onfido.android.sdk.capture.internal.validation.backend.DefaultBackendValidations;
import com.onfido.android.sdk.capture.internal.validation.backend.DefaultBackendValidations_Factory;
import com.onfido.android.sdk.capture.internal.validation.backend.IQSValidations;
import com.onfido.android.sdk.capture.internal.validation.backend.IQSValidations_Factory;
import com.onfido.android.sdk.capture.native_detector.NativeDetector;
import com.onfido.android.sdk.capture.native_detector.NativeDetector_Factory;
import com.onfido.android.sdk.capture.network.OnfidoApiService;
import com.onfido.android.sdk.capture.network.OnfidoApiService_Factory;
import com.onfido.android.sdk.capture.network.error.ErrorHandler;
import com.onfido.android.sdk.capture.token.TokenExpirationServiceConnector;
import com.onfido.android.sdk.capture.token.TokenExpirationServiceConnector_Factory;
import com.onfido.android.sdk.capture.ui.BaseActivity;
import com.onfido.android.sdk.capture.ui.BaseActivity_MembersInjector;
import com.onfido.android.sdk.capture.ui.C0588OnfidoPresenter_Factory;
import com.onfido.android.sdk.capture.ui.OnfidoActivity;
import com.onfido.android.sdk.capture.ui.OnfidoActivity_MembersInjector;
import com.onfido.android.sdk.capture.ui.OnfidoPresenter;
import com.onfido.android.sdk.capture.ui.OnfidoPresenterInitializer;
import com.onfido.android.sdk.capture.ui.OnfidoPresenterInitializer_Factory;
import com.onfido.android.sdk.capture.ui.OnfidoPresenter_PresenterAssistedFactory_Impl;
import com.onfido.android.sdk.capture.ui.camera.C0612CaptureUploadService_Factory;
import com.onfido.android.sdk.capture.ui.camera.CaptureActivity;
import com.onfido.android.sdk.capture.ui.camera.CaptureActivity_MembersInjector;
import com.onfido.android.sdk.capture.ui.camera.CapturePresenter;
import com.onfido.android.sdk.capture.ui.camera.CaptureUploadService;
import com.onfido.android.sdk.capture.ui.camera.CaptureUploadService_Factory_Impl;
import com.onfido.android.sdk.capture.ui.camera.DocumentService;
import com.onfido.android.sdk.capture.ui.camera.DocumentService_Factory;
import com.onfido.android.sdk.capture.ui.camera.NfcPropertiesService;
import com.onfido.android.sdk.capture.ui.camera.NfcPropertiesService_Factory;
import com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment;
import com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment_MembersInjector;
import com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureTracker;
import com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureTracker_Factory;
import com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureViewModel;
import com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureViewModel_Factory;
import com.onfido.android.sdk.capture.ui.camera.face.FaceConfirmationFragment;
import com.onfido.android.sdk.capture.ui.camera.face.FaceConfirmationFragment_MembersInjector;
import com.onfido.android.sdk.capture.ui.camera.liveness.LivenessConfirmationFragment;
import com.onfido.android.sdk.capture.ui.camera.liveness.LivenessConfirmationFragment_MembersInjector;
import com.onfido.android.sdk.capture.ui.camera.liveness.LivenessConfirmationPresenter;
import com.onfido.android.sdk.capture.ui.camera.liveness.LivenessIntroFragment;
import com.onfido.android.sdk.capture.ui.camera.liveness.LivenessIntroFragment_MembersInjector;
import com.onfido.android.sdk.capture.ui.camera.liveness.LivenessIntroPresenter;
import com.onfido.android.sdk.capture.ui.camera.liveness.LivenessOverlayPresenter;
import com.onfido.android.sdk.capture.ui.camera.liveness.LivenessOverlayView;
import com.onfido.android.sdk.capture.ui.camera.liveness.LivenessOverlayView_MembersInjector;
import com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureFragment;
import com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureFragment_MembersInjector;
import com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureViewModel;
import com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureViewModel_Factory;
import com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessInteractor;
import com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessInteractor_Factory;
import com.onfido.android.sdk.capture.ui.camera.liveness.challenges.C0675LivenessChallengesDrawer_Factory;
import com.onfido.android.sdk.capture.ui.camera.liveness.challenges.LivenessChallengesDrawer;
import com.onfido.android.sdk.capture.ui.camera.liveness.challenges.LivenessChallengesDrawer_Factory_Impl;
import com.onfido.android.sdk.capture.ui.camera.liveness.challenges.LivenessChallengesRepository;
import com.onfido.android.sdk.capture.ui.camera.liveness.challenges.LivenessChallengesTransformer;
import com.onfido.android.sdk.capture.ui.camera.liveness.challenges.loading.LivenessChallengesLoadingPresenter;
import com.onfido.android.sdk.capture.ui.camera.liveness.challenges.loading.LivenessChallengesLoadingView;
import com.onfido.android.sdk.capture.ui.camera.liveness.challenges.loading.LivenessChallengesLoadingView_MembersInjector;
import com.onfido.android.sdk.capture.ui.camera.liveness.intro.LivenessIntroVideoApi;
import com.onfido.android.sdk.capture.ui.camera.liveness.intro.LivenessIntroVideoRepository;
import com.onfido.android.sdk.capture.ui.camera.liveness.intro.LivenessIntroVideoUrlProvider;
import com.onfido.android.sdk.capture.ui.camera.liveness.turn.LivenessProgressManager;
import com.onfido.android.sdk.capture.ui.camera.rx.DocumentCaptureDelayTransformer;
import com.onfido.android.sdk.capture.ui.camera.rx.DocumentCaptureDelayTransformer_Factory;
import com.onfido.android.sdk.capture.ui.camera.selfie.C0688SelfieCaptureViewModel_Factory;
import com.onfido.android.sdk.capture.ui.camera.selfie.SelfieCaptureFragment;
import com.onfido.android.sdk.capture.ui.camera.selfie.SelfieCaptureFragment_MembersInjector;
import com.onfido.android.sdk.capture.ui.camera.selfie.SelfieCaptureTracker;
import com.onfido.android.sdk.capture.ui.camera.selfie.SelfieCaptureTracker_Factory;
import com.onfido.android.sdk.capture.ui.camera.selfie.SelfieCaptureViewModel;
import com.onfido.android.sdk.capture.ui.camera.selfie.SelfieCaptureViewModel_Factory_Impl;
import com.onfido.android.sdk.capture.ui.camera.util.CameraPermissionsUtils;
import com.onfido.android.sdk.capture.ui.camera.util.CameraPermissionsUtils_MembersInjector;
import com.onfido.android.sdk.capture.ui.documentselection.DocumentTypeSelectionFragment;
import com.onfido.android.sdk.capture.ui.documentselection.DocumentTypeSelectionFragment_MembersInjector;
import com.onfido.android.sdk.capture.ui.documentselection.DocumentTypeSelectionViewModel;
import com.onfido.android.sdk.capture.ui.documentselection.DocumentTypeSelectionViewModel_Factory;
import com.onfido.android.sdk.capture.ui.documentselection.di.RestrictedDocumentSelectionHostComponent;
import com.onfido.android.sdk.capture.ui.documentselection.di.RestrictedDocumentSelectionHostNavigationModule;
import com.onfido.android.sdk.capture.ui.documentselection.di.RestrictedDocumentSelectionHostNavigationModule_ProvideNavigationManagerHolderFactory;
import com.onfido.android.sdk.capture.ui.documentselection.di.RestrictedDocumentSelectionHostNavigationModule_ProvideNavigatorFactory;
import com.onfido.android.sdk.capture.ui.documentselection.di.RestrictedDocumentSelectionHostNavigationModule_ProvideOnfidoNavigationFactory;
import com.onfido.android.sdk.capture.ui.documentselection.host.C0695DocumentSelectionHostViewModel_Factory;
import com.onfido.android.sdk.capture.ui.documentselection.host.DocumentSelectionHostFragment;
import com.onfido.android.sdk.capture.ui.documentselection.host.DocumentSelectionHostFragment_MembersInjector;
import com.onfido.android.sdk.capture.ui.documentselection.host.DocumentSelectionHostViewModel;
import com.onfido.android.sdk.capture.ui.documentselection.host.DocumentSelectionHostViewModel_Factory_Impl;
import com.onfido.android.sdk.capture.ui.faceintro.FaceIntroFragment;
import com.onfido.android.sdk.capture.ui.faceintro.FaceIntroFragment_MembersInjector;
import com.onfido.android.sdk.capture.ui.faceintro.FaceIntroPresenter;
import com.onfido.android.sdk.capture.ui.finalscreen.FinalScreenFragment;
import com.onfido.android.sdk.capture.ui.finalscreen.FinalScreenFragment_MembersInjector;
import com.onfido.android.sdk.capture.ui.finalscreen.FinalScreenPresenter;
import com.onfido.android.sdk.capture.ui.nfc.C0697NfcHostViewModel_Factory;
import com.onfido.android.sdk.capture.ui.nfc.NfcCanMaxAttemptsFragment;
import com.onfido.android.sdk.capture.ui.nfc.NfcCanMaxAttemptsFragment_MembersInjector;
import com.onfido.android.sdk.capture.ui.nfc.NfcDataRepository;
import com.onfido.android.sdk.capture.ui.nfc.NfcDataRepository_Factory;
import com.onfido.android.sdk.capture.ui.nfc.NfcDeviceNotSupportedFragment;
import com.onfido.android.sdk.capture.ui.nfc.NfcDeviceNotSupportedFragment_MembersInjector;
import com.onfido.android.sdk.capture.ui.nfc.NfcDocumentNotSupportedFragment;
import com.onfido.android.sdk.capture.ui.nfc.NfcDocumentNotSupportedFragment_MembersInjector;
import com.onfido.android.sdk.capture.ui.nfc.NfcHostFragment;
import com.onfido.android.sdk.capture.ui.nfc.NfcHostFragment_MembersInjector;
import com.onfido.android.sdk.capture.ui.nfc.NfcHostViewModel;
import com.onfido.android.sdk.capture.ui.nfc.NfcHostViewModel_Factory_Impl;
import com.onfido.android.sdk.capture.ui.nfc.NfcScanFailFragment;
import com.onfido.android.sdk.capture.ui.nfc.NfcScanFailFragment_MembersInjector;
import com.onfido.android.sdk.capture.ui.nfc.permission.NfcPermissionFragment;
import com.onfido.android.sdk.capture.ui.nfc.permission.NfcPermissionFragment_MembersInjector;
import com.onfido.android.sdk.capture.ui.nfc.scan.C0698NfcCanEntryViewModel_Factory;
import com.onfido.android.sdk.capture.ui.nfc.scan.C0707NfcScanPresenter_Factory;
import com.onfido.android.sdk.capture.ui.nfc.scan.NfcCanEntryFragment;
import com.onfido.android.sdk.capture.ui.nfc.scan.NfcCanEntryFragment_MembersInjector;
import com.onfido.android.sdk.capture.ui.nfc.scan.NfcCanEntryViewModel;
import com.onfido.android.sdk.capture.ui.nfc.scan.NfcCanEntryViewModel_Factory_Impl;
import com.onfido.android.sdk.capture.ui.nfc.scan.NfcScanFragment;
import com.onfido.android.sdk.capture.ui.nfc.scan.NfcScanFragment_MembersInjector;
import com.onfido.android.sdk.capture.ui.nfc.scan.NfcScanPresenter;
import com.onfido.android.sdk.capture.ui.nfc.scan.NfcScanPresenter_Factory_Impl;
import com.onfido.android.sdk.capture.ui.proofOfAddress.PoaUtils;
import com.onfido.android.sdk.capture.ui.proofOfAddress.di.PoaComponent;
import com.onfido.android.sdk.capture.ui.proofOfAddress.documentDetails.PoaDocumentDetailsFragment;
import com.onfido.android.sdk.capture.ui.proofOfAddress.documentDetails.PoaDocumentDetailsFragment_MembersInjector;
import com.onfido.android.sdk.capture.ui.proofOfAddress.documentDetails.PoaDocumentDetailsViewModel;
import com.onfido.android.sdk.capture.ui.proofOfAddress.documentDetails.PoaDocumentDetailsViewModel_Factory;
import com.onfido.android.sdk.capture.ui.proofOfAddress.documentSelection.PoaDocumentSelectionFragment;
import com.onfido.android.sdk.capture.ui.proofOfAddress.documentSelection.PoaDocumentSelectionFragment_MembersInjector;
import com.onfido.android.sdk.capture.ui.proofOfAddress.documentSelection.PoaDocumentSelectionViewModel;
import com.onfido.android.sdk.capture.ui.proofOfAddress.documentSelection.PoaDocumentSelectionViewModel_Factory;
import com.onfido.android.sdk.capture.ui.proofOfAddress.documentSubmission.CompressPoaDocumentUseCase_Factory;
import com.onfido.android.sdk.capture.ui.proofOfAddress.documentSubmission.PoaDocumentSubmissionFragment;
import com.onfido.android.sdk.capture.ui.proofOfAddress.documentSubmission.PoaDocumentSubmissionFragment_MembersInjector;
import com.onfido.android.sdk.capture.ui.proofOfAddress.documentSubmission.PoaDocumentSubmissionViewModel;
import com.onfido.android.sdk.capture.ui.proofOfAddress.documentSubmission.PoaDocumentSubmissionViewModel_Factory;
import com.onfido.android.sdk.capture.ui.proofOfAddress.documentSubmission.UploadPoaDocumentUseCase;
import com.onfido.android.sdk.capture.ui.proofOfAddress.documentSubmission.UploadPoaDocumentUseCase_Factory;
import com.onfido.android.sdk.capture.ui.proofOfAddress.host.C0714PoaHostViewModel_Factory;
import com.onfido.android.sdk.capture.ui.proofOfAddress.host.PoaHostFragment;
import com.onfido.android.sdk.capture.ui.proofOfAddress.host.PoaHostFragment_MembersInjector;
import com.onfido.android.sdk.capture.ui.proofOfAddress.host.PoaHostViewModel;
import com.onfido.android.sdk.capture.ui.proofOfAddress.host.PoaHostViewModel_Factory_Impl;
import com.onfido.android.sdk.capture.ui.proofOfAddress.network.PoaRepository;
import com.onfido.android.sdk.capture.ui.proofOfAddress.network.PoaRepository_Factory;
import com.onfido.android.sdk.capture.ui.proofOfAddress.network.ProofOfAddressApi;
import com.onfido.android.sdk.capture.ui.proofOfAddress.verifyAddress.PoaVerifyAddressFragment;
import com.onfido.android.sdk.capture.ui.proofOfAddress.verifyAddress.PoaVerifyAddressFragment_MembersInjector;
import com.onfido.android.sdk.capture.ui.proofOfAddress.verifyAddress.PoaVerifyAddressViewModel;
import com.onfido.android.sdk.capture.ui.proofOfAddress.verifyAddress.PoaVerifyAddressViewModel_Factory;
import com.onfido.android.sdk.capture.ui.userconsent.UserConsentFragment;
import com.onfido.android.sdk.capture.ui.userconsent.UserConsentFragment_MembersInjector;
import com.onfido.android.sdk.capture.ui.userconsent.UserConsentRepository;
import com.onfido.android.sdk.capture.ui.userconsent.UserConsentRepository_Factory;
import com.onfido.android.sdk.capture.ui.userconsent.UserConsentViewModel;
import com.onfido.android.sdk.capture.ui.userconsent.UserConsentViewModel_Factory;
import com.onfido.android.sdk.capture.ui.userconsent.network.UserConsentApi;
import com.onfido.android.sdk.capture.ui.welcome.C0719WelcomePresenter_Factory;
import com.onfido.android.sdk.capture.ui.welcome.WelcomeFragment;
import com.onfido.android.sdk.capture.ui.welcome.WelcomeFragment_MembersInjector;
import com.onfido.android.sdk.capture.ui.welcome.WelcomePresenter;
import com.onfido.android.sdk.capture.ui.welcome.WelcomePresenter_Factory_Impl;
import com.onfido.android.sdk.capture.ui.widget.C0720LoadingFragmentViewModel_Factory;
import com.onfido.android.sdk.capture.ui.widget.LoadingFragmentViewModel;
import com.onfido.android.sdk.capture.ui.widget.LoadingFragmentViewModel_Factory_Impl;
import com.onfido.android.sdk.capture.utils.DefaultTimeProvider;
import com.onfido.android.sdk.capture.utils.DefaultTimeProvider_Factory;
import com.onfido.android.sdk.capture.utils.DeviceMetadataProvider;
import com.onfido.android.sdk.capture.utils.DeviceMetadataProvider_Factory;
import com.onfido.android.sdk.capture.utils.DeviceUtils_Factory;
import com.onfido.android.sdk.capture.utils.ImageUtils;
import com.onfido.android.sdk.capture.utils.ImageUtils_Factory;
import com.onfido.android.sdk.capture.utils.LoadingFragment;
import com.onfido.android.sdk.capture.utils.LoadingFragment_MembersInjector;
import com.onfido.android.sdk.capture.utils.RawResourceReader;
import com.onfido.android.sdk.capture.utils.RawResourceReader_Factory;
import com.onfido.android.sdk.capture.utils.TimeProvider;
import com.onfido.android.sdk.capture.utils.UuidProvider;
import com.onfido.android.sdk.capture.utils.UuidProvider_Factory;
import com.onfido.android.sdk.capture.utils.mlmodel.OnfidoDocumentDetectorFactory;
import com.onfido.android.sdk.capture.utils.mlmodel.OnfidoMlModelProviderImpl;
import com.onfido.android.sdk.capture.utils.mlmodel.OnfidoMlModelProviderImpl_Factory;
import com.onfido.android.sdk.capture.validation.BackendDocumentValidationsManager;
import com.onfido.android.sdk.capture.validation.BackendDocumentValidationsManager_Factory;
import com.onfido.android.sdk.capture.validation.BarcodeValidationSuspender;
import com.onfido.android.sdk.capture.validation.C0735CaptureValidationBubblePresenter_Factory;
import com.onfido.android.sdk.capture.validation.CaptureValidationBubblePresenter;
import com.onfido.android.sdk.capture.validation.CaptureValidationBubblePresenter_Factory_Impl;
import com.onfido.android.sdk.capture.validation.OnDeviceValidationTransformer;
import com.onfido.android.sdk.capture.validation.OnDeviceValidationTransformer_Factory;
import com.onfido.android.sdk.capture.validation.OnfidoCaptureValidationBubble;
import com.onfido.android.sdk.capture.validation.OnfidoCaptureValidationBubble_MembersInjector;
import com.onfido.android.sdk.capture.validation.PostCaptureDocumentValidationsManager;
import com.onfido.android.sdk.capture.validation.PostCaptureDocumentValidationsManager_Factory;
import com.onfido.android.sdk.capture.validation.RealTimeDocumentValidationsManager;
import com.onfido.android.sdk.capture.validation.RealTimeDocumentValidationsManager_Factory;
import com.onfido.android.sdk.workflow.internal.workflow.tasks.documentupload.WorkflowSupportedDocumentsRepository;
import com.onfido.android.sdk.workflow.internal.workflow.tasks.documentupload.WorkflowSupportedDocumentsRepository_Factory;
import com.onfido.android.sdk.workflow.internal.workflow.tasks.documentupload.WorkflowSupportedDocumentsStore;
import com.onfido.api.client.ErrorParser;
import com.onfido.api.client.OnfidoAPI;
import com.onfido.api.client.OnfidoFetcher;
import com.onfido.api.client.data.SdkConfiguration;
import com.onfido.api.client.token.Token;
import com.onfido.api.client.token.sdk.ApplicantId;
import com.onfido.dagger.internal.DoubleCheck;
import com.onfido.dagger.internal.Preconditions;
import com.onfido.javax.inject.Provider;
import kotlinx.serialization.json.Json;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/* loaded from: classes2.dex */
public final class DaggerSdkComponent {

    public static final class Builder {
        private SdkModule sdkModule;

        private Builder() {
        }

        public SdkComponent build() {
            Preconditions.checkBuilderRequirement(this.sdkModule, SdkModule.class);
            return new SdkComponentImpl(this.sdkModule);
        }

        @Deprecated
        public Builder networkModule(NetworkModule networkModule) {
            Preconditions.checkNotNull(networkModule);
            return this;
        }

        public Builder sdkModule(SdkModule sdkModule) {
            this.sdkModule = (SdkModule) Preconditions.checkNotNull(sdkModule);
            return this;
        }

        @Deprecated
        public Builder sdkProductionModule(SdkProductionModule sdkProductionModule) {
            Preconditions.checkNotNull(sdkProductionModule);
            return this;
        }
    }

    private static final class PoaComponentFactory implements PoaComponent.Factory {
        private final SdkComponentImpl sdkComponentImpl;

        private PoaComponentFactory(SdkComponentImpl sdkComponentImpl) {
            this.sdkComponentImpl = sdkComponentImpl;
        }

        @Override // com.onfido.android.sdk.capture.ui.proofOfAddress.di.PoaComponent.Factory
        public PoaComponent create() {
            return new PoaComponentImpl(this.sdkComponentImpl);
        }
    }

    private static final class PoaComponentImpl implements PoaComponent {
        private Provider<PoaHostViewModel.Factory> factoryProvider;
        private final PoaComponentImpl poaComponentImpl;
        private Provider<PoaDocumentDetailsViewModel> poaDocumentDetailsViewModelProvider;
        private Provider<PoaDocumentSelectionViewModel> poaDocumentSelectionViewModelProvider;
        private Provider<PoaDocumentSubmissionViewModel> poaDocumentSubmissionViewModelProvider;
        private C0714PoaHostViewModel_Factory poaHostViewModelProvider;
        private Provider<PoaRepository> poaRepositoryProvider;
        private Provider<PoaVerifyAddressViewModel> poaVerifyAddressViewModelProvider;
        private Provider<RawResourceReader> rawResourceReaderProvider;
        private final SdkComponentImpl sdkComponentImpl;
        private Provider<UploadPoaDocumentUseCase> uploadPoaDocumentUseCaseProvider;

        private PoaComponentImpl(SdkComponentImpl sdkComponentImpl) {
            this.poaComponentImpl = this;
            this.sdkComponentImpl = sdkComponentImpl;
            initialize();
        }

        private void initialize() {
            SdkComponentImpl sdkComponentImpl = this.sdkComponentImpl;
            this.poaRepositoryProvider = PoaRepository_Factory.create(sdkComponentImpl.provideProofOfAddressApi$onfido_capture_sdk_core_releaseProvider, sdkComponentImpl.onfidoApiServiceProvider);
            this.rawResourceReaderProvider = RawResourceReader_Factory.create(this.sdkComponentImpl.provideSdkContext$onfido_capture_sdk_core_releaseProvider);
            C0714PoaHostViewModel_Factory c0714PoaHostViewModel_FactoryCreate = C0714PoaHostViewModel_Factory.create(this.poaRepositoryProvider, SdkProductionModule_ProvideSchedulers$onfido_capture_sdk_core_releaseFactory.create(), this.rawResourceReaderProvider, NetworkModule_ProvideJson$onfido_capture_sdk_core_releaseFactory.create());
            this.poaHostViewModelProvider = c0714PoaHostViewModel_FactoryCreate;
            this.factoryProvider = PoaHostViewModel_Factory_Impl.create(c0714PoaHostViewModel_FactoryCreate);
            this.poaVerifyAddressViewModelProvider = PoaVerifyAddressViewModel_Factory.create(this.sdkComponentImpl.screenTrackerProvider);
            this.poaDocumentSelectionViewModelProvider = PoaDocumentSelectionViewModel_Factory.create(this.sdkComponentImpl.screenTrackerProvider);
            this.poaDocumentDetailsViewModelProvider = PoaDocumentDetailsViewModel_Factory.create(this.sdkComponentImpl.screenTrackerProvider);
            this.uploadPoaDocumentUseCaseProvider = UploadPoaDocumentUseCase_Factory.create(this.poaRepositoryProvider);
            this.poaDocumentSubmissionViewModelProvider = PoaDocumentSubmissionViewModel_Factory.create(this.sdkComponentImpl.screenTrackerProvider, SdkProductionModule_ProvideSchedulers$onfido_capture_sdk_core_releaseFactory.create(), CompressPoaDocumentUseCase_Factory.create(), this.uploadPoaDocumentUseCaseProvider, this.sdkComponentImpl.waitingScreenTrackerProvider);
        }

        private PoaDocumentDetailsFragment injectPoaDocumentDetailsFragment(PoaDocumentDetailsFragment poaDocumentDetailsFragment) {
            PoaDocumentDetailsFragment_MembersInjector.injectPoaViewModelFactory(poaDocumentDetailsFragment, this.poaDocumentDetailsViewModelProvider);
            PoaDocumentDetailsFragment_MembersInjector.injectPoaUtils(poaDocumentDetailsFragment, new PoaUtils());
            return poaDocumentDetailsFragment;
        }

        private PoaDocumentSelectionFragment injectPoaDocumentSelectionFragment(PoaDocumentSelectionFragment poaDocumentSelectionFragment) {
            PoaDocumentSelectionFragment_MembersInjector.injectPoaViewModelFactory(poaDocumentSelectionFragment, this.poaDocumentSelectionViewModelProvider);
            return poaDocumentSelectionFragment;
        }

        private PoaDocumentSubmissionFragment injectPoaDocumentSubmissionFragment(PoaDocumentSubmissionFragment poaDocumentSubmissionFragment) {
            PoaDocumentSubmissionFragment_MembersInjector.injectImageUtils(poaDocumentSubmissionFragment, new ImageUtils());
            PoaDocumentSubmissionFragment_MembersInjector.injectPoaViewModelFactory(poaDocumentSubmissionFragment, this.poaDocumentSubmissionViewModelProvider);
            PoaDocumentSubmissionFragment_MembersInjector.injectPoaUtils(poaDocumentSubmissionFragment, new PoaUtils());
            return poaDocumentSubmissionFragment;
        }

        private PoaHostFragment injectPoaHostFragment(PoaHostFragment poaHostFragment) {
            PoaHostFragment_MembersInjector.injectPoaUtils(poaHostFragment, new PoaUtils());
            PoaHostFragment_MembersInjector.injectRuntimePermissionsManager(poaHostFragment, this.sdkComponentImpl.runtimePermissionsManager());
            PoaHostFragment_MembersInjector.injectPoaViewModelFactory(poaHostFragment, this.factoryProvider.get());
            PoaHostFragment_MembersInjector.injectImageUtils(poaHostFragment, new ImageUtils());
            PoaHostFragment_MembersInjector.injectOnfidoConfig(poaHostFragment, SdkModule_ProvideOnfidoConfigFactory.provideOnfidoConfig(this.sdkComponentImpl.sdkModule));
            PoaHostFragment_MembersInjector.injectOnfidoRemoteConfig(poaHostFragment, SdkProductionModule_ProvideOnfidoRemoteConfig$onfido_capture_sdk_core_releaseFactory.provideOnfidoRemoteConfig$onfido_capture_sdk_core_release());
            return poaHostFragment;
        }

        private PoaVerifyAddressFragment injectPoaVerifyAddressFragment(PoaVerifyAddressFragment poaVerifyAddressFragment) {
            PoaVerifyAddressFragment_MembersInjector.injectPoaViewModelProvider(poaVerifyAddressFragment, this.poaVerifyAddressViewModelProvider);
            return poaVerifyAddressFragment;
        }

        @Override // com.onfido.android.sdk.capture.ui.proofOfAddress.di.PoaComponent
        public void inject(PoaDocumentDetailsFragment poaDocumentDetailsFragment) {
            injectPoaDocumentDetailsFragment(poaDocumentDetailsFragment);
        }

        @Override // com.onfido.android.sdk.capture.ui.proofOfAddress.di.PoaComponent
        public void inject(PoaDocumentSelectionFragment poaDocumentSelectionFragment) {
            injectPoaDocumentSelectionFragment(poaDocumentSelectionFragment);
        }

        @Override // com.onfido.android.sdk.capture.ui.proofOfAddress.di.PoaComponent
        public void inject(PoaDocumentSubmissionFragment poaDocumentSubmissionFragment) {
            injectPoaDocumentSubmissionFragment(poaDocumentSubmissionFragment);
        }

        @Override // com.onfido.android.sdk.capture.ui.proofOfAddress.di.PoaComponent
        public void inject(PoaHostFragment poaHostFragment) {
            injectPoaHostFragment(poaHostFragment);
        }

        @Override // com.onfido.android.sdk.capture.ui.proofOfAddress.di.PoaComponent
        public void inject(PoaVerifyAddressFragment poaVerifyAddressFragment) {
            injectPoaVerifyAddressFragment(poaVerifyAddressFragment);
        }
    }

    private static final class RestrictedDocumentSelectionHostComponentFactory implements RestrictedDocumentSelectionHostComponent.Factory {
        private final SdkComponentImpl sdkComponentImpl;

        private RestrictedDocumentSelectionHostComponentFactory(SdkComponentImpl sdkComponentImpl) {
            this.sdkComponentImpl = sdkComponentImpl;
        }

        @Override // com.onfido.android.sdk.capture.ui.documentselection.di.RestrictedDocumentSelectionHostComponent.Factory
        public RestrictedDocumentSelectionHostComponent create() {
            return new RestrictedDocumentSelectionHostComponentImpl(this.sdkComponentImpl, new RestrictedDocumentSelectionHostNavigationModule());
        }
    }

    private static final class RestrictedDocumentSelectionHostComponentImpl implements RestrictedDocumentSelectionHostComponent {
        private C0695DocumentSelectionHostViewModel_Factory documentSelectionHostViewModelProvider;
        private Provider<DocumentTypeSelectionViewModel> documentTypeSelectionViewModelProvider;
        private Provider<DocumentSelectionHostViewModel.Factory> factoryProvider;
        private Provider<NavigationManagerHolder> provideNavigationManagerHolderProvider;
        private Provider<Navigator> provideNavigatorProvider;
        private Provider<OnfidoNavigation> provideOnfidoNavigationProvider;
        private final RestrictedDocumentSelectionHostComponentImpl restrictedDocumentSelectionHostComponentImpl;
        private final SdkComponentImpl sdkComponentImpl;

        private RestrictedDocumentSelectionHostComponentImpl(SdkComponentImpl sdkComponentImpl, RestrictedDocumentSelectionHostNavigationModule restrictedDocumentSelectionHostNavigationModule) {
            this.restrictedDocumentSelectionHostComponentImpl = this;
            this.sdkComponentImpl = sdkComponentImpl;
            initialize(restrictedDocumentSelectionHostNavigationModule);
        }

        private void initialize(RestrictedDocumentSelectionHostNavigationModule restrictedDocumentSelectionHostNavigationModule) {
            Provider<OnfidoNavigation> provider = DoubleCheck.provider(RestrictedDocumentSelectionHostNavigationModule_ProvideOnfidoNavigationFactory.create(restrictedDocumentSelectionHostNavigationModule, SdkProductionModule_ProvideSchedulers$onfido_capture_sdk_core_releaseFactory.create()));
            this.provideOnfidoNavigationProvider = provider;
            this.provideNavigatorProvider = RestrictedDocumentSelectionHostNavigationModule_ProvideNavigatorFactory.create(restrictedDocumentSelectionHostNavigationModule, provider);
            RestrictedDocumentSelectionHostNavigationModule_ProvideNavigationManagerHolderFactory restrictedDocumentSelectionHostNavigationModule_ProvideNavigationManagerHolderFactoryCreate = RestrictedDocumentSelectionHostNavigationModule_ProvideNavigationManagerHolderFactory.create(restrictedDocumentSelectionHostNavigationModule, this.provideOnfidoNavigationProvider);
            this.provideNavigationManagerHolderProvider = restrictedDocumentSelectionHostNavigationModule_ProvideNavigationManagerHolderFactoryCreate;
            Provider<Navigator> provider2 = this.provideNavigatorProvider;
            SdkComponentImpl sdkComponentImpl = this.sdkComponentImpl;
            C0695DocumentSelectionHostViewModel_Factory c0695DocumentSelectionHostViewModel_FactoryCreate = C0695DocumentSelectionHostViewModel_Factory.create(provider2, restrictedDocumentSelectionHostNavigationModule_ProvideNavigationManagerHolderFactoryCreate, sdkComponentImpl.getCurrentCountryCodeUseCaseProvider, sdkComponentImpl.provideSupportedDocumentsRepository$onfido_capture_sdk_core_releaseProvider, sdkComponentImpl.screenTrackerProvider, sdkComponentImpl.screenLoadTrackerProvider);
            this.documentSelectionHostViewModelProvider = c0695DocumentSelectionHostViewModel_FactoryCreate;
            this.factoryProvider = DocumentSelectionHostViewModel_Factory_Impl.create(c0695DocumentSelectionHostViewModel_FactoryCreate);
            this.documentTypeSelectionViewModelProvider = DocumentTypeSelectionViewModel_Factory.create(this.sdkComponentImpl.provideSupportedDocumentsRepository$onfido_capture_sdk_core_releaseProvider);
        }

        private DocumentSelectionHostFragment injectDocumentSelectionHostFragment(DocumentSelectionHostFragment documentSelectionHostFragment) {
            DocumentSelectionHostFragment_MembersInjector.injectFactory(documentSelectionHostFragment, this.factoryProvider.get());
            return documentSelectionHostFragment;
        }

        private DocumentTypeSelectionFragment injectDocumentTypeSelectionFragment(DocumentTypeSelectionFragment documentTypeSelectionFragment) {
            DocumentTypeSelectionFragment_MembersInjector.injectViewModelProvider(documentTypeSelectionFragment, this.documentTypeSelectionViewModelProvider);
            return documentTypeSelectionFragment;
        }

        @Override // com.onfido.android.sdk.capture.ui.documentselection.di.RestrictedDocumentSelectionHostComponent
        public void inject(DocumentTypeSelectionFragment documentTypeSelectionFragment) {
            injectDocumentTypeSelectionFragment(documentTypeSelectionFragment);
        }

        @Override // com.onfido.android.sdk.capture.ui.documentselection.di.RestrictedDocumentSelectionHostComponent
        public void inject(DocumentSelectionHostFragment documentSelectionHostFragment) {
            injectDocumentSelectionHostFragment(documentSelectionHostFragment);
        }
    }

    private static final class SdkComponentImpl extends SdkComponent {
        private Provider<AccessibleDocumentCaptureUseCase> accessibleDocumentCaptureUseCaseProvider;
        private Provider<AggregatedPerformanceAnalytics> aggregatedPerformanceAnalyticsProvider;
        private Provider<AllDocumentsRemoteDataSourceImpl> allDocumentsRemoteDataSourceImplProvider;
        private Provider<AnnouncementService> announcementServiceProvider;
        private Provider<BackendDocumentValidationsManager> backendDocumentValidationsManagerProvider;
        private Provider<BarcodeDetectorGoogle> barcodeDetectorGoogleProvider;
        private Provider<CameraXImageAnalysisUseCase> cameraXImageAnalysisUseCaseProvider;
        private C0579CameraX_Factory cameraXProvider;
        private Provider<CameraXTakePictureUseCase> cameraXTakePictureUseCaseProvider;
        private Provider<CameraXTakeVideoUseCase> cameraXTakeVideoUseCaseProvider;
        private Provider<CaptureTracker> captureTrackerProvider;
        private C0612CaptureUploadService_Factory captureUploadServiceProvider;
        private C0735CaptureValidationBubblePresenter_Factory captureValidationBubblePresenterProvider;
        private Provider<Cryptography> cryptographyProvider;
        private Provider<DefaultBackendValidations> defaultBackendValidationsProvider;
        private Provider<DeviceMetadataProvider> deviceMetadataProvider;
        private Provider<DocumentCaptureDelayTransformer> documentCaptureDelayTransformerProvider;
        private Provider<DocumentCaptureTracker> documentCaptureTrackerProvider;
        private Provider<DocumentCaptureViewModel> documentCaptureViewModelProvider;
        private Provider<DocumentProcessingUseCase> documentProcessingUseCaseProvider;
        private Provider<DocumentService> documentServiceProvider;
        private Provider<DocumentValidationUseCase> documentValidationUseCaseProvider;
        private Provider<EnvironmentIntegrityCheckerImpl> environmentIntegrityCheckerImplProvider;
        private Provider<FaceDetectorGoogle> faceDetectorGoogleProvider;
        private Provider<FaceOnDocumentDetectorGoogle> faceOnDocumentDetectorGoogleProvider;
        private Provider<FaceProcessingUseCase> faceProcessingUseCaseProvider;
        private Provider<CameraX.Factory> factoryProvider;
        private Provider<SelfieCaptureViewModel.Factory> factoryProvider10;
        private Provider<WelcomePresenter.Factory> factoryProvider2;
        private Provider<LivenessChallengesDrawer.Factory> factoryProvider3;
        private Provider<CaptureValidationBubblePresenter.Factory> factoryProvider4;
        private Provider<NfcHostViewModel.Factory> factoryProvider5;
        private Provider<NfcScanPresenter.Factory> factoryProvider6;
        private Provider<NfcCanEntryViewModel.Factory> factoryProvider7;
        private Provider<LoadingFragmentViewModel.Factory> factoryProvider8;
        private Provider<CaptureUploadService.Factory> factoryProvider9;
        private Provider<FlowTracker> flowTrackerProvider;
        private Provider<GetCurrentCountryCodeUseCase> getCurrentCountryCodeUseCaseProvider;
        private Provider<GetSupportedDocumentsUseCase> getSupportedDocumentsUseCaseProvider;
        private Provider<IQSValidations> iQSValidationsProvider;
        private Provider<IdentityInteractor> identityInteractorProvider;
        private Provider<InhouseAnalyticsRepository> inhouseAnalyticsRepositoryProvider;
        private Provider<LivenessCaptureViewModel> livenessCaptureViewModelProvider;
        private C0675LivenessChallengesDrawer_Factory livenessChallengesDrawerProvider;
        private Provider<LivenessInteractor> livenessInteractorProvider;
        private Provider<LivenessTracker> livenessTrackerProvider;
        private C0720LoadingFragmentViewModel_Factory loadingFragmentViewModelProvider;
        private Provider<LoggerRepository> loggerRepositoryProvider;
        private Provider<MRZDocumentExtractorGoogle> mRZDocumentExtractorGoogleProvider;
        private Provider<MediaCallbacksUseCase> mediaCallbacksUseCaseProvider;
        private Provider<NativeDetector> nativeDetectorProvider;
        private C0698NfcCanEntryViewModel_Factory nfcCanEntryViewModelProvider;
        private Provider<NfcDataRepository> nfcDataRepositoryProvider;
        private C0697NfcHostViewModel_Factory nfcHostViewModelProvider;
        private Provider<NfcInteractorImpl> nfcInteractorImplProvider;
        private Provider<NfcPropertiesService> nfcPropertiesServiceProvider;
        private C0707NfcScanPresenter_Factory nfcScanPresenterProvider;
        private Provider<NfcTracker> nfcTrackerProvider;
        private Provider<NfcUseCase> nfcUseCaseProvider;
        private Provider<OnDeviceValidationTransformer> onDeviceValidationTransformerProvider;
        private Provider<OnfidoAnalyticsMapper> onfidoAnalyticsMapperProvider;
        private Provider<OnfidoApiService> onfidoApiServiceProvider;
        private Provider<OnfidoLogMapper> onfidoLogMapperProvider;
        private Provider<OnfidoMlModelProviderImpl> onfidoMlModelProviderImplProvider;
        private Provider<OnfidoPresenterInitializer> onfidoPresenterInitializerProvider;
        private C0588OnfidoPresenter_Factory onfidoPresenterProvider;
        private Provider<OnfidoSupportedDocumentsRepository> onfidoSupportedDocumentsRepositoryProvider;
        private Provider<OnfidoTokenProvider> onfidoTokenProvider;
        private Provider<PayloadHelper> payloadHelperProvider;
        private Provider<PerformanceAnalyticsImpl> performanceAnalyticsImplProvider;
        private Provider<PerformanceAnalyticsRepository> performanceAnalyticsRepositoryProvider;
        private Provider<PostCaptureDocumentValidationsManager> postCaptureDocumentValidationsManagerProvider;
        private Provider<OnfidoPresenter.PresenterAssistedFactory> presenterAssistedFactoryProvider;
        private Provider<AllDocumentsDataSource> provideAllDocumentDataSourceProvider;
        private Provider<ApplicantId> provideApplicantUuidProvider;
        private Provider<BarcodeDetector> provideBarcodeDetector$onfido_capture_sdk_core_releaseProvider;
        private Provider<BarcodeValidationSuspender> provideBarcodeValidationSuspenderProvider;
        private Provider<ListenableFuture<ProcessCameraProvider>> provideCameraProvider;
        private Provider<EnterpriseConfig> provideEnterpriseConfig$onfido_capture_sdk_core_releaseProvider;
        private Provider<ErrorHandler> provideErrorHandler$onfido_capture_sdk_core_releaseProvider;
        private Provider<ErrorParser> provideErrorParser$onfido_capture_sdk_core_releaseProvider;
        private Provider<FaceDetector> provideFaceDetector$onfido_capture_sdk_core_releaseProvider;
        private Provider<FaceOnDocumentDetector> provideFaceOnDocumentDetector$onfido_capture_sdk_core_releaseProvider;
        private Provider<LivenessIntroVideoApi> provideLivenessIntroVideoAPI$onfido_capture_sdk_core_releaseProvider;
        private Provider<MRZDetector> provideMRZDetector$onfido_capture_sdk_core_releaseProvider;
        private Provider<ResultReceiver> provideMediaCallbackProvider;
        private Provider<BarcodeScanner> provideMlKitBarcodeScanner$onfido_capture_sdk_core_releaseProvider;
        private Provider<NfcAdapter> provideNfcAdapterProvider;
        private Provider<OkHttpClient> provideOkHttpClient$onfido_capture_sdk_core_releaseProvider;
        private Provider<MRZDocumentExtractor> provideOnDeviceMRZDocumentExtractor$onfido_capture_sdk_core_releaseProvider;
        private Provider<OnfidoAPI> provideOnfidoAPI$onfido_capture_sdk_core_releaseProvider;
        private Provider<OnfidoAnalytics> provideOnfidoAnalytics$onfido_capture_sdk_core_releaseProvider;
        private Provider<OnfidoAnalyticsApi> provideOnfidoAnalyticsApi$onfido_capture_sdk_core_releaseProvider;
        private Provider<ResultReceiver> provideOnfidoAnalyticsEventListenerProvider;
        private Provider<OnfidoConfig> provideOnfidoConfigProvider;
        private Provider<OnfidoDocumentDetectorFactory> provideOnfidoDocumentDetectorFactory$onfido_capture_sdk_core_releaseProvider;
        private Provider<OnfidoFetcher> provideOnfidoFetcher$onfido_capture_sdk_core_releaseProvider;
        private Provider<OnfidoLoggerApi> provideOnfidoLoggerApi$onfido_capture_sdk_core_releaseProvider;
        private Provider<Token> provideOnfidoTokenProvider;
        private Provider<PassportNfcReader> providePassportNfcReader$onfido_capture_sdk_core_releaseProvider;
        private Provider<PerformanceAnalyticsApi> providePerformanceAnalyticsApi$onfido_capture_sdk_core_releaseProvider;
        private Provider<PerformanceAnalyticsNetwork> providePerformanceAnalyticsNetwork$onfido_capture_sdk_core_releaseProvider;
        private Provider<ProofOfAddressApi> provideProofOfAddressApi$onfido_capture_sdk_core_releaseProvider;
        private Provider<PublicEventMapper> providePublicEventMapper$onfido_capture_sdk_core_releaseProvider;
        private Provider<RectangleDetector> provideRectangleDetector$onfido_capture_sdk_core_releaseProvider;
        private Provider<RetainableValidationsResult> provideRetainableValidationsResult$onfido_capture_sdk_core_releaseProvider;
        private Provider<Retrofit> provideRetrofit$onfido_capture_sdk_core_releaseProvider;
        private Provider<Context> provideSdkContext$onfido_capture_sdk_core_releaseProvider;
        private Provider<SupportedDocumentsApi> provideSupportedDocumentsApi$onfido_capture_sdk_core_releaseProvider;
        private Provider<SupportedDocumentsRepository> provideSupportedDocumentsRepository$onfido_capture_sdk_core_releaseProvider;
        private Provider<TelephonyManager> provideTelephonyManagerProvider;
        private Provider<UserConsentApi> provideUserConsentApi$onfido_capture_sdk_core_releaseProvider;
        private Provider<RealTimeDocumentValidationsManager> realTimeDocumentValidationsManagerProvider;
        private Provider<RectangleDetectorGoogle> rectangleDetectorGoogleProvider;
        private Provider<RemoteLoggerTree> remoteLoggerTreeProvider;
        private Provider<RemoteSupportedDocumentsRepository> remoteSupportedDocumentsRepositoryProvider;
        private Provider<RuntimePermissionsManager> runtimePermissionsManagerProvider;
        private Provider<SDKConfigRepository> sDKConfigRepositoryProvider;
        private Provider<ScreenLoadTracker> screenLoadTrackerProvider;
        private Provider<ScreenTracker> screenTrackerProvider;
        private final SdkComponentImpl sdkComponentImpl;
        private final SdkModule sdkModule;
        private Provider<SdkTokenParser> sdkTokenParserProvider;
        private Provider<SdkUploadMetadataHelper> sdkUploadMetadataHelperProvider;
        private Provider<SelfieCaptureTracker> selfieCaptureTrackerProvider;
        private C0688SelfieCaptureViewModel_Factory selfieCaptureViewModelProvider;
        private Provider<SessionIdProvider> sessionIdProvider;
        private Provider<SharedPreferencesDataSource> sharedPreferencesDataSourceProvider;
        private Provider<ShouldLaunchNfcFlowUseCase> shouldLaunchNfcFlowUseCaseProvider;
        private Provider<SignalExtractor> signalExtractorProvider;
        private Provider<SignalFactory> signalFactoryProvider;
        private Provider<TokenExpirationServiceConnector> tokenExpirationServiceConnectorProvider;
        private Provider<UserConsentRepository> userConsentRepositoryProvider;
        private Provider<UserConsentViewModel> userConsentViewModelProvider;
        private Provider<UuidProvider> uuidProvider;
        private Provider<VibratorService> vibratorServiceProvider;
        private Provider<WaitingScreenTracker> waitingScreenTrackerProvider;
        private C0719WelcomePresenter_Factory welcomePresenterProvider;
        private Provider<WorkflowSupportedDocumentsRepository> workflowSupportedDocumentsRepositoryProvider;

        private SdkComponentImpl(SdkModule sdkModule) {
            this.sdkComponentImpl = this;
            this.sdkModule = sdkModule;
            initialize(sdkModule);
            initialize2(sdkModule);
        }

        private AccessibleDocumentCaptureUseCase accessibleDocumentCaptureUseCase() {
            return new AccessibleDocumentCaptureUseCase(new DocumentPositionUseCase(), SdkProductionModule_ProvideSchedulers$onfido_capture_sdk_core_releaseFactory.provideSchedulers$onfido_capture_sdk_core_release(), this.nativeDetectorProvider.get(), vibratorService(), this.provideSdkContext$onfido_capture_sdk_core_releaseProvider.get());
        }

        private AnnouncementService announcementService() {
            return new AnnouncementService(this.provideSdkContext$onfido_capture_sdk_core_releaseProvider.get());
        }

        private AudioManager audioManager() {
            return SdkModule_ProvideAudioManagerFactory.provideAudioManager(this.sdkModule, this.provideSdkContext$onfido_capture_sdk_core_releaseProvider.get());
        }

        private BackendDocumentValidationsManager backendDocumentValidationsManager() {
            return new BackendDocumentValidationsManager(defaultBackendValidations(), iQSValidations(), SdkProductionModule_ProvideOnfidoRemoteConfig$onfido_capture_sdk_core_releaseFactory.provideOnfidoRemoteConfig$onfido_capture_sdk_core_release());
        }

        private CameraUseCaseConfigProvider cameraUseCaseConfigProvider() {
            return new CameraUseCaseConfigProvider(SdkProductionModule_ProvideOnfidoRemoteConfig$onfido_capture_sdk_core_releaseFactory.provideOnfidoRemoteConfig$onfido_capture_sdk_core_release(), docCaptureCameraConfigProvider());
        }

        private CapturePresenter capturePresenter() {
            return new CapturePresenter(this.nativeDetectorProvider.get(), rectangleDetector(), accessibleDocumentCaptureUseCase(), livenessInteractor(), backendDocumentValidationsManager(), documentProcessingUseCase(), postCaptureDocumentValidationsManager(), runtimePermissionsManager(), faceDetector(), identityInteractor(), new DocumentConfigurationManager(), new DefaultTimeProvider(), provideSdkUploadMetadataHelper$onfido_capture_sdk_core_release(), documentService(), nfcPropertiesService(), nfcInteractorImpl(), documentCaptureDelayTransformer(), SdkProductionModule_ProvideSchedulers$onfido_capture_sdk_core_releaseFactory.provideSchedulers$onfido_capture_sdk_core_release(), faceProcessingUseCase(), SdkProductionModule_ProvideOnfidoRemoteConfig$onfido_capture_sdk_core_releaseFactory.provideOnfidoRemoteConfig$onfido_capture_sdk_core_release(), announcementService(), new DocumentProcessingResultsFailureAnalyzerImpl(), this.provideRetainableValidationsResult$onfido_capture_sdk_core_releaseProvider.get(), this.provideBarcodeValidationSuspenderProvider.get(), new ImageUtils(), captureTracker(), nfcTracker(), livenessTracker(), this.waitingScreenTrackerProvider.get(), flowTracker(), mediaCallbackResultReceiverResultReceiver(), mRZDocumentExtractor(), documentValidationUseCase(), SdkProductionModule_ProvideDispatchersProvider$onfido_capture_sdk_core_releaseFactory.provideDispatchersProvider$onfido_capture_sdk_core_release());
        }

        private CaptureTracker captureTracker() {
            return new CaptureTracker(this.provideOnfidoAnalytics$onfido_capture_sdk_core_releaseProvider.get(), nfcInteractorImpl());
        }

        private CountrySelectionPresenter countrySelectionPresenter() {
            return new CountrySelectionPresenter(supportedDocumentsRepository(), screenTracker(), getCurrentCountryCodeUseCase(), SdkProductionModule_ProvideSchedulers$onfido_capture_sdk_core_releaseFactory.provideSchedulers$onfido_capture_sdk_core_release());
        }

        private Cryptography cryptography() {
            return new Cryptography(new CryptographyHelper());
        }

        private DefaultBackendValidations defaultBackendValidations() {
            return new DefaultBackendValidations(this.nativeDetectorProvider.get());
        }

        private DocCaptureCameraConfigProvider docCaptureCameraConfigProvider() {
            return new DocCaptureCameraConfigProvider(SdkProductionModule_ProvideOnfidoRemoteConfig$onfido_capture_sdk_core_releaseFactory.provideOnfidoRemoteConfig$onfido_capture_sdk_core_release());
        }

        private DocumentCaptureDelayTransformer documentCaptureDelayTransformer() {
            return new DocumentCaptureDelayTransformer(this.nativeDetectorProvider.get(), new DocumentConfigurationManager(), SdkProductionModule_ProvideSchedulers$onfido_capture_sdk_core_releaseFactory.provideSchedulers$onfido_capture_sdk_core_release(), documentProcessingUseCase());
        }

        private DocumentProcessingUseCase documentProcessingUseCase() {
            return new DocumentProcessingUseCase(this.nativeDetectorProvider.get(), realTimeDocumentValidationsManager(), onDeviceValidationTransformer(), this.provideRetainableValidationsResult$onfido_capture_sdk_core_releaseProvider.get(), this.provideBarcodeValidationSuspenderProvider.get(), SdkProductionModule_ProvideSchedulers$onfido_capture_sdk_core_releaseFactory.provideSchedulers$onfido_capture_sdk_core_release(), SdkProductionModule_ProvideOnfidoRemoteConfig$onfido_capture_sdk_core_releaseFactory.provideOnfidoRemoteConfig$onfido_capture_sdk_core_release());
        }

        private DocumentService documentService() {
            return new DocumentService(this.onfidoApiServiceProvider.get());
        }

        private DocumentValidationUseCase documentValidationUseCase() {
            return new DocumentValidationUseCase(onfidoMlModelProviderImpl(), new RectangleTransformer(), new DocumentPositionUseCase(), this.nativeDetectorProvider.get(), SdkProductionModule_ProvideDispatchersProvider$onfido_capture_sdk_core_releaseFactory.provideDispatchersProvider$onfido_capture_sdk_core_release(), new DefaultTimeProvider());
        }

        private EnvironmentIntegrityCheckerImpl environmentIntegrityCheckerImpl() {
            return new EnvironmentIntegrityCheckerImpl(this.provideSdkContext$onfido_capture_sdk_core_releaseProvider.get(), this.nativeDetectorProvider.get(), SdkProductionModule_ProvideOnfidoRemoteConfig$onfido_capture_sdk_core_releaseFactory.provideOnfidoRemoteConfig$onfido_capture_sdk_core_release());
        }

        private FaceDetector faceDetector() {
            return SdkModule_ProvideFaceDetector$onfido_capture_sdk_core_releaseFactory.provideFaceDetector$onfido_capture_sdk_core_release(this.sdkModule, this.provideSdkContext$onfido_capture_sdk_core_releaseProvider.get(), DoubleCheck.lazy(this.faceDetectorGoogleProvider), DoubleCheck.lazy(FaceDetectorEmpty_Factory.create()));
        }

        private FaceIntroPresenter faceIntroPresenter() {
            return new FaceIntroPresenter(livenessTracker(), this.screenLoadTrackerProvider.get());
        }

        private FaceProcessingUseCase faceProcessingUseCase() {
            return new FaceProcessingUseCase(faceDetector(), SdkProductionModule_ProvideSchedulers$onfido_capture_sdk_core_releaseFactory.provideSchedulers$onfido_capture_sdk_core_release());
        }

        private FileCache fileCache() {
            return new FileCache(this.provideSdkContext$onfido_capture_sdk_core_releaseProvider.get());
        }

        private FinalScreenPresenter finalScreenPresenter() {
            return new FinalScreenPresenter(screenTracker());
        }

        private FrameSampler<OnfidoImage> frameSamplerOfOnfidoImage() {
            return SdkModule_ProvideFrameSampler$onfido_capture_sdk_core_releaseFactory.provideFrameSampler$onfido_capture_sdk_core_release(this.sdkModule, SdkProductionModule_ProvideSchedulers$onfido_capture_sdk_core_releaseFactory.provideSchedulers$onfido_capture_sdk_core_release());
        }

        private GetCurrentCountryCodeUseCase getCurrentCountryCodeUseCase() {
            return new GetCurrentCountryCodeUseCase(telephonyManager());
        }

        private IQSValidations iQSValidations() {
            return new IQSValidations(this.nativeDetectorProvider.get(), SdkProductionModule_ProvideOnfidoRemoteConfig$onfido_capture_sdk_core_releaseFactory.provideOnfidoRemoteConfig$onfido_capture_sdk_core_release());
        }

        private IdentityInteractor identityInteractor() {
            return new IdentityInteractor(this.provideSdkContext$onfido_capture_sdk_core_releaseProvider.get(), this.nativeDetectorProvider.get(), this.deviceMetadataProvider.get());
        }

        private void initialize(SdkModule sdkModule) {
            this.provideOkHttpClient$onfido_capture_sdk_core_releaseProvider = DoubleCheck.provider(NetworkModule_ProvideOkHttpClient$onfido_capture_sdk_core_releaseFactory.create(NetworkModule_ProvideInternalOnfidoNetworkInterceptorsProvider$onfido_capture_sdk_core_releaseFactory.create()));
            SdkModule_ProvideOnfidoTokenFactory sdkModule_ProvideOnfidoTokenFactoryCreate = SdkModule_ProvideOnfidoTokenFactory.create(sdkModule);
            this.provideOnfidoTokenProvider = sdkModule_ProvideOnfidoTokenFactoryCreate;
            this.onfidoTokenProvider = DoubleCheck.provider(OnfidoTokenProvider_Factory.create(sdkModule_ProvideOnfidoTokenFactoryCreate));
            this.provideOnfidoConfigProvider = SdkModule_ProvideOnfidoConfigFactory.create(sdkModule);
            this.provideSdkContext$onfido_capture_sdk_core_releaseProvider = DoubleCheck.provider(SdkModule_ProvideSdkContext$onfido_capture_sdk_core_releaseFactory.create(sdkModule));
            this.nativeDetectorProvider = DoubleCheck.provider(NativeDetector_Factory.create());
            SharedPreferencesDataSource_Factory sharedPreferencesDataSource_FactoryCreate = SharedPreferencesDataSource_Factory.create(this.provideSdkContext$onfido_capture_sdk_core_releaseProvider, NetworkModule_ProvideJson$onfido_capture_sdk_core_releaseFactory.create());
            this.sharedPreferencesDataSourceProvider = sharedPreferencesDataSource_FactoryCreate;
            UuidProvider_Factory uuidProvider_FactoryCreate = UuidProvider_Factory.create(sharedPreferencesDataSource_FactoryCreate);
            this.uuidProvider = uuidProvider_FactoryCreate;
            SignalFactory_Factory signalFactory_FactoryCreate = SignalFactory_Factory.create(uuidProvider_FactoryCreate);
            this.signalFactoryProvider = signalFactory_FactoryCreate;
            SignalExtractor_Factory signalExtractor_FactoryCreate = SignalExtractor_Factory.create(signalFactory_FactoryCreate);
            this.signalExtractorProvider = signalExtractor_FactoryCreate;
            Provider<DeviceMetadataProvider> provider = DoubleCheck.provider(DeviceMetadataProvider_Factory.create(signalExtractor_FactoryCreate));
            this.deviceMetadataProvider = provider;
            IdentityInteractor_Factory identityInteractor_FactoryCreate = IdentityInteractor_Factory.create(this.provideSdkContext$onfido_capture_sdk_core_releaseProvider, this.nativeDetectorProvider, provider);
            this.identityInteractorProvider = identityInteractor_FactoryCreate;
            Provider<OnfidoFetcher> provider2 = DoubleCheck.provider(NetworkModule_ProvideOnfidoFetcher$onfido_capture_sdk_core_releaseFactory.create(this.provideOkHttpClient$onfido_capture_sdk_core_releaseProvider, this.onfidoTokenProvider, this.provideOnfidoConfigProvider, identityInteractor_FactoryCreate, NetworkModule_ProvideJson$onfido_capture_sdk_core_releaseFactory.create()));
            this.provideOnfidoFetcher$onfido_capture_sdk_core_releaseProvider = provider2;
            this.provideOnfidoLoggerApi$onfido_capture_sdk_core_releaseProvider = NetworkModule_ProvideOnfidoLoggerApi$onfido_capture_sdk_core_releaseFactory.create(provider2);
            SessionIdProvider_Factory sessionIdProvider_FactoryCreate = SessionIdProvider_Factory.create(this.uuidProvider, DefaultTimeProvider_Factory.create(), this.sharedPreferencesDataSourceProvider);
            this.sessionIdProvider = sessionIdProvider_FactoryCreate;
            this.onfidoLogMapperProvider = OnfidoLogMapper_Factory.create(this.uuidProvider, this.provideOnfidoConfigProvider, sessionIdProvider_FactoryCreate, this.deviceMetadataProvider);
            SdkModule_ProvideErrorParser$onfido_capture_sdk_core_releaseFactory sdkModule_ProvideErrorParser$onfido_capture_sdk_core_releaseFactoryCreate = SdkModule_ProvideErrorParser$onfido_capture_sdk_core_releaseFactory.create(sdkModule);
            this.provideErrorParser$onfido_capture_sdk_core_releaseProvider = sdkModule_ProvideErrorParser$onfido_capture_sdk_core_releaseFactoryCreate;
            SdkModule_ProvideErrorHandler$onfido_capture_sdk_core_releaseFactory sdkModule_ProvideErrorHandler$onfido_capture_sdk_core_releaseFactoryCreate = SdkModule_ProvideErrorHandler$onfido_capture_sdk_core_releaseFactory.create(sdkModule, sdkModule_ProvideErrorParser$onfido_capture_sdk_core_releaseFactoryCreate);
            this.provideErrorHandler$onfido_capture_sdk_core_releaseProvider = sdkModule_ProvideErrorHandler$onfido_capture_sdk_core_releaseFactoryCreate;
            LoggerRepository_Factory loggerRepository_FactoryCreate = LoggerRepository_Factory.create(this.provideOnfidoLoggerApi$onfido_capture_sdk_core_releaseProvider, this.onfidoLogMapperProvider, sdkModule_ProvideErrorHandler$onfido_capture_sdk_core_releaseFactoryCreate, LoggerMemoryCachingDataSource_Factory.create());
            this.loggerRepositoryProvider = loggerRepository_FactoryCreate;
            this.remoteLoggerTreeProvider = DoubleCheck.provider(RemoteLoggerTree_Factory.create(loggerRepository_FactoryCreate, DefaultTimeProvider_Factory.create(), SdkProductionModule_ProvideOnfidoRemoteConfig$onfido_capture_sdk_core_releaseFactory.create()));
            this.providePerformanceAnalyticsApi$onfido_capture_sdk_core_releaseProvider = NetworkModule_ProvidePerformanceAnalyticsApi$onfido_capture_sdk_core_releaseFactory.create(this.provideOnfidoFetcher$onfido_capture_sdk_core_releaseProvider);
            this.sdkTokenParserProvider = SdkTokenParser_Factory.create(NetworkModule_ProvideJson$onfido_capture_sdk_core_releaseFactory.create());
            this.environmentIntegrityCheckerImplProvider = EnvironmentIntegrityCheckerImpl_Factory.create(this.provideSdkContext$onfido_capture_sdk_core_releaseProvider, this.nativeDetectorProvider, SdkProductionModule_ProvideOnfidoRemoteConfig$onfido_capture_sdk_core_releaseFactory.create());
            OnfidoAnalyticsMapper_Factory onfidoAnalyticsMapper_FactoryCreate = OnfidoAnalyticsMapper_Factory.create(DefaultTimeProvider_Factory.create(), this.uuidProvider, AnalyticsMetadataProvider_Factory.create(), this.provideOnfidoConfigProvider, this.sessionIdProvider, this.sdkTokenParserProvider, this.deviceMetadataProvider, NetworkModule_ProvideJson$onfido_capture_sdk_core_releaseFactory.create(), this.environmentIntegrityCheckerImplProvider, this.identityInteractorProvider);
            this.onfidoAnalyticsMapperProvider = onfidoAnalyticsMapper_FactoryCreate;
            this.performanceAnalyticsRepositoryProvider = PerformanceAnalyticsRepository_Factory.create(this.providePerformanceAnalyticsApi$onfido_capture_sdk_core_releaseProvider, onfidoAnalyticsMapper_FactoryCreate, this.provideErrorHandler$onfido_capture_sdk_core_releaseProvider);
            this.providePublicEventMapper$onfido_capture_sdk_core_releaseProvider = DoubleCheck.provider(SdkModule_ProvidePublicEventMapper$onfido_capture_sdk_core_releaseFactory.create(sdkModule));
            this.provideOnfidoAnalyticsEventListenerProvider = SdkModule_ProvideOnfidoAnalyticsEventListenerFactory.create(sdkModule, this.provideOnfidoConfigProvider);
            SdkModule_ProvidePerformanceAnalyticsNetwork$onfido_capture_sdk_core_releaseFactory sdkModule_ProvidePerformanceAnalyticsNetwork$onfido_capture_sdk_core_releaseFactoryCreate = SdkModule_ProvidePerformanceAnalyticsNetwork$onfido_capture_sdk_core_releaseFactory.create(sdkModule, this.performanceAnalyticsRepositoryProvider, SdkProductionModule_ProvideSchedulers$onfido_capture_sdk_core_releaseFactory.create(), EventCache_Factory.create(), SdkProductionModule_ProvideOnfidoRemoteConfig$onfido_capture_sdk_core_releaseFactory.create(), this.providePublicEventMapper$onfido_capture_sdk_core_releaseProvider, this.provideOnfidoAnalyticsEventListenerProvider);
            this.providePerformanceAnalyticsNetwork$onfido_capture_sdk_core_releaseProvider = sdkModule_ProvidePerformanceAnalyticsNetwork$onfido_capture_sdk_core_releaseFactoryCreate;
            PerformanceAnalyticsImpl_Factory performanceAnalyticsImpl_FactoryCreate = PerformanceAnalyticsImpl_Factory.create(sdkModule_ProvidePerformanceAnalyticsNetwork$onfido_capture_sdk_core_releaseFactoryCreate, SdkProductionModule_ProvideOnfidoRemoteConfig$onfido_capture_sdk_core_releaseFactory.create());
            this.performanceAnalyticsImplProvider = performanceAnalyticsImpl_FactoryCreate;
            this.aggregatedPerformanceAnalyticsProvider = DoubleCheck.provider(AggregatedPerformanceAnalytics_Factory.create(performanceAnalyticsImpl_FactoryCreate, this.provideSdkContext$onfido_capture_sdk_core_releaseProvider));
            NetworkModule_ProvideOnfidoAnalyticsApi$onfido_capture_sdk_core_releaseFactory networkModule_ProvideOnfidoAnalyticsApi$onfido_capture_sdk_core_releaseFactoryCreate = NetworkModule_ProvideOnfidoAnalyticsApi$onfido_capture_sdk_core_releaseFactory.create(this.provideOnfidoFetcher$onfido_capture_sdk_core_releaseProvider);
            this.provideOnfidoAnalyticsApi$onfido_capture_sdk_core_releaseProvider = networkModule_ProvideOnfidoAnalyticsApi$onfido_capture_sdk_core_releaseFactoryCreate;
            this.inhouseAnalyticsRepositoryProvider = InhouseAnalyticsRepository_Factory.create(networkModule_ProvideOnfidoAnalyticsApi$onfido_capture_sdk_core_releaseFactoryCreate, this.onfidoAnalyticsMapperProvider, this.provideErrorHandler$onfido_capture_sdk_core_releaseProvider);
            this.provideEnterpriseConfig$onfido_capture_sdk_core_releaseProvider = SdkModule_ProvideEnterpriseConfig$onfido_capture_sdk_core_releaseFactory.create(sdkModule);
            Provider<OnfidoAnalytics> provider3 = DoubleCheck.provider(SdkModule_ProvideOnfidoAnalytics$onfido_capture_sdk_core_releaseFactory.create(sdkModule, this.inhouseAnalyticsRepositoryProvider, this.provideOnfidoConfigProvider, SdkProductionModule_ProvideSchedulers$onfido_capture_sdk_core_releaseFactory.create(), EventCache_Factory.create(), this.provideEnterpriseConfig$onfido_capture_sdk_core_releaseProvider, SdkProductionModule_ProvideOnfidoRemoteConfig$onfido_capture_sdk_core_releaseFactory.create(), this.providePublicEventMapper$onfido_capture_sdk_core_releaseProvider, this.provideOnfidoAnalyticsEventListenerProvider));
            this.provideOnfidoAnalytics$onfido_capture_sdk_core_releaseProvider = provider3;
            this.waitingScreenTrackerProvider = DoubleCheck.provider(WaitingScreenTracker_Factory.create(provider3, DefaultTimeProvider_Factory.create()));
            this.flowTrackerProvider = FlowTracker_Factory.create(this.provideOnfidoAnalytics$onfido_capture_sdk_core_releaseProvider);
            this.livenessTrackerProvider = LivenessTracker_Factory.create(this.provideOnfidoAnalytics$onfido_capture_sdk_core_releaseProvider);
            this.screenLoadTrackerProvider = DoubleCheck.provider(ScreenLoadTracker_Factory.create(this.aggregatedPerformanceAnalyticsProvider, DefaultTimeProvider_Factory.create()));
            this.nfcTrackerProvider = NfcTracker_Factory.create(this.provideOnfidoAnalytics$onfido_capture_sdk_core_releaseProvider);
            this.runtimePermissionsManagerProvider = RuntimePermissionsManager_Factory.create(this.provideSdkContext$onfido_capture_sdk_core_releaseProvider, this.sharedPreferencesDataSourceProvider);
            this.provideOnfidoAPI$onfido_capture_sdk_core_releaseProvider = NetworkModule_ProvideOnfidoAPI$onfido_capture_sdk_core_releaseFactory.create(this.onfidoTokenProvider, this.provideOnfidoFetcher$onfido_capture_sdk_core_releaseProvider, this.identityInteractorProvider, NetworkModule_ProvideJson$onfido_capture_sdk_core_releaseFactory.create());
            this.sdkUploadMetadataHelperProvider = SdkUploadMetadataHelper_Factory.create(this.identityInteractorProvider, SdkProductionModule_ProvideOnfidoRemoteConfig$onfido_capture_sdk_core_releaseFactory.create(), this.environmentIntegrityCheckerImplProvider);
            TokenExpirationServiceConnector_Factory tokenExpirationServiceConnector_FactoryCreate = TokenExpirationServiceConnector_Factory.create(this.onfidoTokenProvider, this.provideSdkContext$onfido_capture_sdk_core_releaseProvider);
            this.tokenExpirationServiceConnectorProvider = tokenExpirationServiceConnector_FactoryCreate;
            Provider<OnfidoApiService> provider4 = DoubleCheck.provider(OnfidoApiService_Factory.create(this.provideOnfidoAPI$onfido_capture_sdk_core_releaseProvider, this.identityInteractorProvider, this.provideOnfidoConfigProvider, this.sdkUploadMetadataHelperProvider, tokenExpirationServiceConnector_FactoryCreate));
            this.onfidoApiServiceProvider = provider4;
            this.sDKConfigRepositoryProvider = DoubleCheck.provider(SDKConfigRepository_Factory.create(provider4, SdkProductionModule_ProvideSchedulers$onfido_capture_sdk_core_releaseFactory.create(), SdkProductionModule_ProvideOnfidoRemoteConfig$onfido_capture_sdk_core_releaseFactory.create(), SdkProductionModule_ProvideMutableOnfidoRemoteConfig$onfido_capture_sdk_core_releaseFactory.create()));
            NetworkModule_ProvideUserConsentApi$onfido_capture_sdk_core_releaseFactory networkModule_ProvideUserConsentApi$onfido_capture_sdk_core_releaseFactoryCreate = NetworkModule_ProvideUserConsentApi$onfido_capture_sdk_core_releaseFactory.create(this.provideOnfidoFetcher$onfido_capture_sdk_core_releaseProvider);
            this.provideUserConsentApi$onfido_capture_sdk_core_releaseProvider = networkModule_ProvideUserConsentApi$onfido_capture_sdk_core_releaseFactoryCreate;
            this.userConsentRepositoryProvider = UserConsentRepository_Factory.create(this.provideOkHttpClient$onfido_capture_sdk_core_releaseProvider, networkModule_ProvideUserConsentApi$onfido_capture_sdk_core_releaseFactoryCreate, this.onfidoTokenProvider);
            this.provideSupportedDocumentsApi$onfido_capture_sdk_core_releaseProvider = NetworkModule_ProvideSupportedDocumentsApi$onfido_capture_sdk_core_releaseFactory.create(this.provideOnfidoFetcher$onfido_capture_sdk_core_releaseProvider);
            Provider<AllDocumentsRemoteDataSourceImpl> provider5 = DoubleCheck.provider(AllDocumentsRemoteDataSourceImpl_Factory.create(this.onfidoApiServiceProvider, SupportedDocumentMapper_Factory.create()));
            this.allDocumentsRemoteDataSourceImplProvider = provider5;
            this.provideAllDocumentDataSourceProvider = SdkProductionModule_ProvideAllDocumentDataSourceFactory.create(provider5);
            Provider<RemoteSupportedDocumentsRepository> provider6 = DoubleCheck.provider(RemoteSupportedDocumentsRepository_Factory.create(this.provideSupportedDocumentsApi$onfido_capture_sdk_core_releaseProvider, SupportedDocumentsLocalDataSource_Factory.create(), this.provideAllDocumentDataSourceProvider));
            this.remoteSupportedDocumentsRepositoryProvider = provider6;
            GetSupportedDocumentsUseCase_Factory getSupportedDocumentsUseCase_FactoryCreate = GetSupportedDocumentsUseCase_Factory.create(provider6);
            this.getSupportedDocumentsUseCaseProvider = getSupportedDocumentsUseCase_FactoryCreate;
            this.onfidoPresenterInitializerProvider = OnfidoPresenterInitializer_Factory.create(this.sDKConfigRepositoryProvider, this.userConsentRepositoryProvider, getSupportedDocumentsUseCase_FactoryCreate);
            this.nfcDataRepositoryProvider = NfcDataRepository_Factory.create(this.onfidoApiServiceProvider, NetworkModule_ProvideJson$onfido_capture_sdk_core_releaseFactory.create());
            this.provideNfcAdapterProvider = SdkCaptureProductionModule_Companion_ProvideNfcAdapterFactory.create(this.provideSdkContext$onfido_capture_sdk_core_releaseProvider);
            SdkModule_ProvidePassportNfcReader$onfido_capture_sdk_core_releaseFactory sdkModule_ProvidePassportNfcReader$onfido_capture_sdk_core_releaseFactoryCreate = SdkModule_ProvidePassportNfcReader$onfido_capture_sdk_core_releaseFactory.create(sdkModule, SdkProductionModule_ProvideOnfidoRemoteConfig$onfido_capture_sdk_core_releaseFactory.create(), this.nfcTrackerProvider);
            this.providePassportNfcReader$onfido_capture_sdk_core_releaseProvider = sdkModule_ProvidePassportNfcReader$onfido_capture_sdk_core_releaseFactoryCreate;
            NfcInteractorImpl_Factory nfcInteractorImpl_FactoryCreate = NfcInteractorImpl_Factory.create(this.provideNfcAdapterProvider, sdkModule_ProvidePassportNfcReader$onfido_capture_sdk_core_releaseFactoryCreate, this.nfcTrackerProvider, NfcTimeouts_Factory.create(), DefaultTimeProvider_Factory.create());
            this.nfcInteractorImplProvider = nfcInteractorImpl_FactoryCreate;
            this.shouldLaunchNfcFlowUseCaseProvider = ShouldLaunchNfcFlowUseCase_Factory.create(nfcInteractorImpl_FactoryCreate, this.provideOnfidoConfigProvider);
            C0588OnfidoPresenter_Factory c0588OnfidoPresenter_FactoryCreate = C0588OnfidoPresenter_Factory.create(this.flowTrackerProvider, this.livenessTrackerProvider, this.screenLoadTrackerProvider, this.nfcTrackerProvider, this.runtimePermissionsManagerProvider, DocumentConfigurationManager_Factory.create(), this.onfidoPresenterInitializerProvider, this.nfcDataRepositoryProvider, SdkProductionModule_ProvideSchedulers$onfido_capture_sdk_core_releaseFactory.create(), SdkProductionModule_ProvideOnfidoRemoteConfig$onfido_capture_sdk_core_releaseFactory.create(), this.remoteLoggerTreeProvider, DeviceUtils_Factory.create(), this.waitingScreenTrackerProvider, this.nfcInteractorImplProvider, this.shouldLaunchNfcFlowUseCaseProvider);
            this.onfidoPresenterProvider = c0588OnfidoPresenter_FactoryCreate;
            this.presenterAssistedFactoryProvider = OnfidoPresenter_PresenterAssistedFactory_Impl.create(c0588OnfidoPresenter_FactoryCreate);
            this.rectangleDetectorGoogleProvider = RectangleDetectorGoogle_Factory.create(RectangleTransformer_Factory.create(), SdkProductionModule_ProvideSchedulers$onfido_capture_sdk_core_releaseFactory.create());
            this.provideMRZDetector$onfido_capture_sdk_core_releaseProvider = SdkModule_ProvideMRZDetector$onfido_capture_sdk_core_releaseFactory.create(sdkModule, this.provideSdkContext$onfido_capture_sdk_core_releaseProvider, MRZDetectorGoogle_Factory.create(), MRZDetectorEmpty_Factory.create());
            this.provideMlKitBarcodeScanner$onfido_capture_sdk_core_releaseProvider = SdkModule_ProvideMlKitBarcodeScanner$onfido_capture_sdk_core_releaseFactory.create(sdkModule);
            BarcodeDetectorGoogle_Factory barcodeDetectorGoogle_FactoryCreate = BarcodeDetectorGoogle_Factory.create(SdkProductionModule_ProvideOnfidoRemoteConfig$onfido_capture_sdk_core_releaseFactory.create(), this.provideMlKitBarcodeScanner$onfido_capture_sdk_core_releaseProvider);
            this.barcodeDetectorGoogleProvider = barcodeDetectorGoogle_FactoryCreate;
            this.provideBarcodeDetector$onfido_capture_sdk_core_releaseProvider = SdkModule_ProvideBarcodeDetector$onfido_capture_sdk_core_releaseFactory.create(sdkModule, this.provideSdkContext$onfido_capture_sdk_core_releaseProvider, barcodeDetectorGoogle_FactoryCreate, BarcodeDetectorEmpty_Factory.create());
            FaceOnDocumentDetectorGoogle_Factory faceOnDocumentDetectorGoogle_FactoryCreate = FaceOnDocumentDetectorGoogle_Factory.create(SdkProductionModule_ProvideOnfidoRemoteConfig$onfido_capture_sdk_core_releaseFactory.create());
            this.faceOnDocumentDetectorGoogleProvider = faceOnDocumentDetectorGoogle_FactoryCreate;
            this.provideFaceOnDocumentDetector$onfido_capture_sdk_core_releaseProvider = DoubleCheck.provider(SdkModule_ProvideFaceOnDocumentDetector$onfido_capture_sdk_core_releaseFactory.create(sdkModule, this.provideSdkContext$onfido_capture_sdk_core_releaseProvider, faceOnDocumentDetectorGoogle_FactoryCreate, FaceOnDocumentDetectorEmpty_Factory.create()));
            this.provideRetainableValidationsResult$onfido_capture_sdk_core_releaseProvider = DoubleCheck.provider(SdkModule_ProvideRetainableValidationsResult$onfido_capture_sdk_core_releaseFactory.create(sdkModule));
            this.provideBarcodeValidationSuspenderProvider = DoubleCheck.provider(SdkModule_ProvideBarcodeValidationSuspenderFactory.create(sdkModule, SdkProductionModule_ProvideOnfidoRemoteConfig$onfido_capture_sdk_core_releaseFactory.create(), SdkProductionModule_ProvideSchedulers$onfido_capture_sdk_core_releaseFactory.create()));
            this.faceDetectorGoogleProvider = DoubleCheck.provider(FaceDetectorGoogle_Factory.create());
            this.mRZDocumentExtractorGoogleProvider = MRZDocumentExtractorGoogle_Factory.create(TextRecognizerProviderImpl_Factory.create());
            this.cameraXTakeVideoUseCaseProvider = CameraXTakeVideoUseCase_Factory.create(this.provideSdkContext$onfido_capture_sdk_core_releaseProvider);
            this.cameraXTakePictureUseCaseProvider = CameraXTakePictureUseCase_Factory.create(this.provideSdkContext$onfido_capture_sdk_core_releaseProvider);
            this.cameraXImageAnalysisUseCaseProvider = CameraXImageAnalysisUseCase_Factory.create(DefaultTimeProvider_Factory.create());
            this.provideCameraProvider = SdkModule_ProvideCameraProviderFactory.create(sdkModule, this.provideSdkContext$onfido_capture_sdk_core_releaseProvider);
            C0579CameraX_Factory c0579CameraX_FactoryCreate = C0579CameraX_Factory.create(this.provideSdkContext$onfido_capture_sdk_core_releaseProvider, this.cameraXTakeVideoUseCaseProvider, this.cameraXTakePictureUseCaseProvider, this.cameraXImageAnalysisUseCaseProvider, SdkProductionModule_ProvideDispatchersProvider$onfido_capture_sdk_core_releaseFactory.create(), this.provideCameraProvider, SdkProductionModule_ProvideOnfidoRemoteConfig$onfido_capture_sdk_core_releaseFactory.create());
            this.cameraXProvider = c0579CameraX_FactoryCreate;
            this.factoryProvider = CameraX_Factory_Impl.create(c0579CameraX_FactoryCreate);
            this.screenTrackerProvider = ScreenTracker_Factory.create(this.provideOnfidoAnalytics$onfido_capture_sdk_core_releaseProvider);
            C0719WelcomePresenter_Factory c0719WelcomePresenter_FactoryCreate = C0719WelcomePresenter_Factory.create(SdkProductionModule_ProvideOnfidoRemoteConfig$onfido_capture_sdk_core_releaseFactory.create(), this.screenTrackerProvider, this.screenLoadTrackerProvider);
            this.welcomePresenterProvider = c0719WelcomePresenter_FactoryCreate;
            this.factoryProvider2 = WelcomePresenter_Factory_Impl.create(c0719WelcomePresenter_FactoryCreate);
            this.onfidoSupportedDocumentsRepositoryProvider = DoubleCheck.provider(OnfidoSupportedDocumentsRepository_Factory.create(this.provideOnfidoConfigProvider, this.provideAllDocumentDataSourceProvider));
            this.workflowSupportedDocumentsRepositoryProvider = DoubleCheck.provider(WorkflowSupportedDocumentsRepository_Factory.create());
            Provider<Retrofit> provider7 = DoubleCheck.provider(NetworkModule_ProvideRetrofit$onfido_capture_sdk_core_releaseFactory.create(this.provideOkHttpClient$onfido_capture_sdk_core_releaseProvider, NetworkModule_ProvideJson$onfido_capture_sdk_core_releaseFactory.create()));
            this.provideRetrofit$onfido_capture_sdk_core_releaseProvider = provider7;
            this.provideLivenessIntroVideoAPI$onfido_capture_sdk_core_releaseProvider = DoubleCheck.provider(NetworkModule_ProvideLivenessIntroVideoAPI$onfido_capture_sdk_core_releaseFactory.create(provider7));
            AnnouncementService_Factory announcementService_FactoryCreate = AnnouncementService_Factory.create(this.provideSdkContext$onfido_capture_sdk_core_releaseProvider);
            this.announcementServiceProvider = announcementService_FactoryCreate;
            C0675LivenessChallengesDrawer_Factory c0675LivenessChallengesDrawer_FactoryCreate = C0675LivenessChallengesDrawer_Factory.create(announcementService_FactoryCreate);
            this.livenessChallengesDrawerProvider = c0675LivenessChallengesDrawer_FactoryCreate;
            this.factoryProvider3 = LivenessChallengesDrawer_Factory_Impl.create(c0675LivenessChallengesDrawer_FactoryCreate);
            C0735CaptureValidationBubblePresenter_Factory c0735CaptureValidationBubblePresenter_FactoryCreate = C0735CaptureValidationBubblePresenter_Factory.create(this.announcementServiceProvider, SdkProductionModule_ProvideSchedulers$onfido_capture_sdk_core_releaseFactory.create());
            this.captureValidationBubblePresenterProvider = c0735CaptureValidationBubblePresenter_FactoryCreate;
            this.factoryProvider4 = CaptureValidationBubblePresenter_Factory_Impl.create(c0735CaptureValidationBubblePresenter_FactoryCreate);
            this.userConsentViewModelProvider = UserConsentViewModel_Factory.create(this.userConsentRepositoryProvider, this.screenTrackerProvider, SdkProductionModule_ProvideSchedulers$onfido_capture_sdk_core_releaseFactory.create(), this.waitingScreenTrackerProvider, SdkProductionModule_ProvideOnfidoRemoteConfig$onfido_capture_sdk_core_releaseFactory.create(), this.provideOnfidoConfigProvider, this.flowTrackerProvider);
            C0697NfcHostViewModel_Factory c0697NfcHostViewModel_FactoryCreate = C0697NfcHostViewModel_Factory.create(SdkProductionModule_ProvideSchedulers$onfido_capture_sdk_core_releaseFactory.create(), this.nfcInteractorImplProvider, this.nfcTrackerProvider, this.screenTrackerProvider, SdkProductionModule_ProvideOnfidoRemoteConfig$onfido_capture_sdk_core_releaseFactory.create(), this.sharedPreferencesDataSourceProvider);
            this.nfcHostViewModelProvider = c0697NfcHostViewModel_FactoryCreate;
            this.factoryProvider5 = NfcHostViewModel_Factory_Impl.create(c0697NfcHostViewModel_FactoryCreate);
            C0707NfcScanPresenter_Factory c0707NfcScanPresenter_FactoryCreate = C0707NfcScanPresenter_Factory.create(this.nfcInteractorImplProvider, SdkProductionModule_ProvideSchedulers$onfido_capture_sdk_core_releaseFactory.create(), this.nfcTrackerProvider, NfcTimeouts_Factory.create(), this.sharedPreferencesDataSourceProvider, SdkProductionModule_ProvideOnfidoRemoteConfig$onfido_capture_sdk_core_releaseFactory.create());
            this.nfcScanPresenterProvider = c0707NfcScanPresenter_FactoryCreate;
            this.factoryProvider6 = NfcScanPresenter_Factory_Impl.create(c0707NfcScanPresenter_FactoryCreate);
            C0698NfcCanEntryViewModel_Factory c0698NfcCanEntryViewModel_FactoryCreate = C0698NfcCanEntryViewModel_Factory.create(this.screenTrackerProvider, this.nfcTrackerProvider);
            this.nfcCanEntryViewModelProvider = c0698NfcCanEntryViewModel_FactoryCreate;
            this.factoryProvider7 = NfcCanEntryViewModel_Factory_Impl.create(c0698NfcCanEntryViewModel_FactoryCreate);
            C0720LoadingFragmentViewModel_Factory c0720LoadingFragmentViewModel_FactoryCreate = C0720LoadingFragmentViewModel_Factory.create(SdkProductionModule_ProvideOnfidoRemoteConfig$onfido_capture_sdk_core_releaseFactory.create(), SdkProductionModule_ProvideSchedulers$onfido_capture_sdk_core_releaseFactory.create(), this.waitingScreenTrackerProvider);
            this.loadingFragmentViewModelProvider = c0720LoadingFragmentViewModel_FactoryCreate;
            this.factoryProvider8 = LoadingFragmentViewModel_Factory_Impl.create(c0720LoadingFragmentViewModel_FactoryCreate);
            this.documentServiceProvider = DocumentService_Factory.create(this.onfidoApiServiceProvider);
            this.cryptographyProvider = Cryptography_Factory.create(CryptographyHelper_Factory.create());
        }

        private void initialize2(SdkModule sdkModule) {
            this.provideApplicantUuidProvider = SdkModule_ProvideApplicantUuidFactory.create(sdkModule, this.onfidoTokenProvider);
            this.payloadHelperProvider = PayloadHelper_Factory.create(this.cryptographyProvider, NetworkModule_ProvideJson$onfido_capture_sdk_core_releaseFactory.create(), this.provideApplicantUuidProvider);
            C0612CaptureUploadService_Factory c0612CaptureUploadService_FactoryCreate = C0612CaptureUploadService_Factory.create(this.onfidoApiServiceProvider, SdkProductionModule_ProvideSchedulers$onfido_capture_sdk_core_releaseFactory.create(), this.cryptographyProvider, this.payloadHelperProvider);
            this.captureUploadServiceProvider = c0612CaptureUploadService_FactoryCreate;
            this.factoryProvider9 = CaptureUploadService_Factory_Impl.create(c0612CaptureUploadService_FactoryCreate);
            this.provideRectangleDetector$onfido_capture_sdk_core_releaseProvider = SdkModule_ProvideRectangleDetector$onfido_capture_sdk_core_releaseFactory.create(sdkModule, this.provideSdkContext$onfido_capture_sdk_core_releaseProvider, this.rectangleDetectorGoogleProvider, RectangleDetectorEmpty_Factory.create());
            this.defaultBackendValidationsProvider = DefaultBackendValidations_Factory.create(this.nativeDetectorProvider);
            IQSValidations_Factory iQSValidations_FactoryCreate = IQSValidations_Factory.create(this.nativeDetectorProvider, SdkProductionModule_ProvideOnfidoRemoteConfig$onfido_capture_sdk_core_releaseFactory.create());
            this.iQSValidationsProvider = iQSValidations_FactoryCreate;
            this.backendDocumentValidationsManagerProvider = BackendDocumentValidationsManager_Factory.create(this.defaultBackendValidationsProvider, iQSValidations_FactoryCreate, SdkProductionModule_ProvideOnfidoRemoteConfig$onfido_capture_sdk_core_releaseFactory.create());
            OnDeviceValidationTransformer_Factory onDeviceValidationTransformer_FactoryCreate = OnDeviceValidationTransformer_Factory.create(this.nativeDetectorProvider, this.provideMRZDetector$onfido_capture_sdk_core_releaseProvider, this.provideBarcodeDetector$onfido_capture_sdk_core_releaseProvider, this.provideFaceOnDocumentDetector$onfido_capture_sdk_core_releaseProvider);
            this.onDeviceValidationTransformerProvider = onDeviceValidationTransformer_FactoryCreate;
            this.postCaptureDocumentValidationsManagerProvider = PostCaptureDocumentValidationsManager_Factory.create(this.identityInteractorProvider, onDeviceValidationTransformer_FactoryCreate, this.provideRetainableValidationsResult$onfido_capture_sdk_core_releaseProvider, this.provideBarcodeValidationSuspenderProvider, SdkProductionModule_ProvideOnfidoRemoteConfig$onfido_capture_sdk_core_releaseFactory.create());
            RealTimeDocumentValidationsManager_Factory realTimeDocumentValidationsManager_FactoryCreate = RealTimeDocumentValidationsManager_Factory.create(this.identityInteractorProvider);
            this.realTimeDocumentValidationsManagerProvider = realTimeDocumentValidationsManager_FactoryCreate;
            this.documentProcessingUseCaseProvider = DocumentProcessingUseCase_Factory.create(this.nativeDetectorProvider, realTimeDocumentValidationsManager_FactoryCreate, this.onDeviceValidationTransformerProvider, this.provideRetainableValidationsResult$onfido_capture_sdk_core_releaseProvider, this.provideBarcodeValidationSuspenderProvider, SdkProductionModule_ProvideSchedulers$onfido_capture_sdk_core_releaseFactory.create(), SdkProductionModule_ProvideOnfidoRemoteConfig$onfido_capture_sdk_core_releaseFactory.create());
            this.documentCaptureDelayTransformerProvider = DocumentCaptureDelayTransformer_Factory.create(this.nativeDetectorProvider, DocumentConfigurationManager_Factory.create(), SdkProductionModule_ProvideSchedulers$onfido_capture_sdk_core_releaseFactory.create(), this.documentProcessingUseCaseProvider);
            SdkModule_ProvideMediaCallbackFactory sdkModule_ProvideMediaCallbackFactoryCreate = SdkModule_ProvideMediaCallbackFactory.create(sdkModule, this.provideOnfidoConfigProvider);
            this.provideMediaCallbackProvider = sdkModule_ProvideMediaCallbackFactoryCreate;
            this.mediaCallbacksUseCaseProvider = MediaCallbacksUseCase_Factory.create(sdkModule_ProvideMediaCallbackFactoryCreate, SdkProductionModule_ProvideSchedulers$onfido_capture_sdk_core_releaseFactory.create(), DefaultTimeProvider_Factory.create());
            this.nfcPropertiesServiceProvider = NfcPropertiesService_Factory.create(this.onfidoApiServiceProvider);
            this.nfcUseCaseProvider = NfcUseCase_Factory.create(this.nfcInteractorImplProvider, DocumentConfigurationManager_Factory.create(), this.nfcPropertiesServiceProvider, this.nfcTrackerProvider);
            this.vibratorServiceProvider = VibratorService_Factory.create(this.provideSdkContext$onfido_capture_sdk_core_releaseProvider);
            this.accessibleDocumentCaptureUseCaseProvider = AccessibleDocumentCaptureUseCase_Factory.create(DocumentPositionUseCase_Factory.create(), SdkProductionModule_ProvideSchedulers$onfido_capture_sdk_core_releaseFactory.create(), this.nativeDetectorProvider, this.vibratorServiceProvider, this.provideSdkContext$onfido_capture_sdk_core_releaseProvider);
            CaptureTracker_Factory captureTracker_FactoryCreate = CaptureTracker_Factory.create(this.provideOnfidoAnalytics$onfido_capture_sdk_core_releaseProvider, this.nfcInteractorImplProvider);
            this.captureTrackerProvider = captureTracker_FactoryCreate;
            this.documentCaptureTrackerProvider = DocumentCaptureTracker_Factory.create(captureTracker_FactoryCreate, this.waitingScreenTrackerProvider, this.nfcTrackerProvider, this.aggregatedPerformanceAnalyticsProvider);
            this.provideOnDeviceMRZDocumentExtractor$onfido_capture_sdk_core_releaseProvider = SdkModule_ProvideOnDeviceMRZDocumentExtractor$onfido_capture_sdk_core_releaseFactory.create(sdkModule, this.provideSdkContext$onfido_capture_sdk_core_releaseProvider, this.mRZDocumentExtractorGoogleProvider, MRZDocumentExtractorEmpty_Factory.create());
            this.provideOnfidoDocumentDetectorFactory$onfido_capture_sdk_core_releaseProvider = SdkModule_ProvideOnfidoDocumentDetectorFactory$onfido_capture_sdk_core_releaseFactory.create(sdkModule);
            OnfidoMlModelProviderImpl_Factory onfidoMlModelProviderImpl_FactoryCreate = OnfidoMlModelProviderImpl_Factory.create(this.provideSdkContext$onfido_capture_sdk_core_releaseProvider, this.captureTrackerProvider, DefaultTimeProvider_Factory.create(), this.provideOnfidoDocumentDetectorFactory$onfido_capture_sdk_core_releaseProvider, SdkProductionModule_ProvideOnfidoRemoteConfig$onfido_capture_sdk_core_releaseFactory.create());
            this.onfidoMlModelProviderImplProvider = onfidoMlModelProviderImpl_FactoryCreate;
            this.documentValidationUseCaseProvider = DocumentValidationUseCase_Factory.create(onfidoMlModelProviderImpl_FactoryCreate, RectangleTransformer_Factory.create(), DocumentPositionUseCase_Factory.create(), this.nativeDetectorProvider, SdkProductionModule_ProvideDispatchersProvider$onfido_capture_sdk_core_releaseFactory.create(), DefaultTimeProvider_Factory.create());
            this.documentCaptureViewModelProvider = DocumentCaptureViewModel_Factory.create(DocumentConfigurationManager_Factory.create(), this.nativeDetectorProvider, this.announcementServiceProvider, SdkProductionModule_ProvideOnfidoRemoteConfig$onfido_capture_sdk_core_releaseFactory.create(), this.sdkUploadMetadataHelperProvider, this.documentServiceProvider, this.factoryProvider9, this.onfidoTokenProvider, this.provideRectangleDetector$onfido_capture_sdk_core_releaseProvider, this.sharedPreferencesDataSourceProvider, this.backendDocumentValidationsManagerProvider, this.postCaptureDocumentValidationsManagerProvider, DocumentProcessingResultsFailureAnalyzerImpl_Factory.create(), this.documentCaptureDelayTransformerProvider, this.mediaCallbacksUseCaseProvider, this.nfcUseCaseProvider, this.accessibleDocumentCaptureUseCaseProvider, DefaultTimeProvider_Factory.create(), this.documentCaptureTrackerProvider, this.provideBarcodeValidationSuspenderProvider, this.provideRetainableValidationsResult$onfido_capture_sdk_core_releaseProvider, SdkProductionModule_ProvideDispatchersProvider$onfido_capture_sdk_core_releaseFactory.create(), this.environmentIntegrityCheckerImplProvider, ImageUtils_Factory.create(), this.provideOnDeviceMRZDocumentExtractor$onfido_capture_sdk_core_releaseProvider, this.documentValidationUseCaseProvider);
            SdkModule_ProvideFaceDetector$onfido_capture_sdk_core_releaseFactory sdkModule_ProvideFaceDetector$onfido_capture_sdk_core_releaseFactoryCreate = SdkModule_ProvideFaceDetector$onfido_capture_sdk_core_releaseFactory.create(sdkModule, this.provideSdkContext$onfido_capture_sdk_core_releaseProvider, this.faceDetectorGoogleProvider, FaceDetectorEmpty_Factory.create());
            this.provideFaceDetector$onfido_capture_sdk_core_releaseProvider = sdkModule_ProvideFaceDetector$onfido_capture_sdk_core_releaseFactoryCreate;
            this.faceProcessingUseCaseProvider = FaceProcessingUseCase_Factory.create(sdkModule_ProvideFaceDetector$onfido_capture_sdk_core_releaseFactoryCreate, SdkProductionModule_ProvideSchedulers$onfido_capture_sdk_core_releaseFactory.create());
            LivenessInteractor_Factory livenessInteractor_FactoryCreate = LivenessInteractor_Factory.create(this.provideSdkContext$onfido_capture_sdk_core_releaseProvider, DefaultTimeProvider_Factory.create());
            this.livenessInteractorProvider = livenessInteractor_FactoryCreate;
            this.livenessCaptureViewModelProvider = LivenessCaptureViewModel_Factory.create(this.faceProcessingUseCaseProvider, this.provideFaceDetector$onfido_capture_sdk_core_releaseProvider, this.identityInteractorProvider, livenessInteractor_FactoryCreate, this.livenessTrackerProvider, this.captureTrackerProvider, DefaultTimeProvider_Factory.create(), SdkProductionModule_ProvideSchedulers$onfido_capture_sdk_core_releaseFactory.create(), this.provideOnfidoConfigProvider, SdkProductionModule_ProvideDispatchersProvider$onfido_capture_sdk_core_releaseFactory.create());
            this.selfieCaptureTrackerProvider = SelfieCaptureTracker_Factory.create(this.captureTrackerProvider, this.livenessTrackerProvider, this.waitingScreenTrackerProvider, SdkProductionModule_ProvideOnfidoRemoteConfig$onfido_capture_sdk_core_releaseFactory.create());
            C0688SelfieCaptureViewModel_Factory c0688SelfieCaptureViewModel_FactoryCreate = C0688SelfieCaptureViewModel_Factory.create(this.factoryProvider9, DefaultTimeProvider_Factory.create(), this.selfieCaptureTrackerProvider, this.sharedPreferencesDataSourceProvider, SdkProductionModule_ProvideOnfidoRemoteConfig$onfido_capture_sdk_core_releaseFactory.create(), this.sdkUploadMetadataHelperProvider, this.mediaCallbacksUseCaseProvider);
            this.selfieCaptureViewModelProvider = c0688SelfieCaptureViewModel_FactoryCreate;
            this.factoryProvider10 = SelfieCaptureViewModel_Factory_Impl.create(c0688SelfieCaptureViewModel_FactoryCreate);
            SdkModule_ProvideTelephonyManagerFactory sdkModule_ProvideTelephonyManagerFactoryCreate = SdkModule_ProvideTelephonyManagerFactory.create(sdkModule, this.provideSdkContext$onfido_capture_sdk_core_releaseProvider);
            this.provideTelephonyManagerProvider = sdkModule_ProvideTelephonyManagerFactoryCreate;
            this.getCurrentCountryCodeUseCaseProvider = GetCurrentCountryCodeUseCase_Factory.create(sdkModule_ProvideTelephonyManagerFactoryCreate);
            this.provideSupportedDocumentsRepository$onfido_capture_sdk_core_releaseProvider = SdkModule_ProvideSupportedDocumentsRepository$onfido_capture_sdk_core_releaseFactory.create(sdkModule, this.onfidoSupportedDocumentsRepositoryProvider, this.workflowSupportedDocumentsRepositoryProvider, this.remoteSupportedDocumentsRepositoryProvider, SdkProductionModule_ProvideOnfidoRemoteConfig$onfido_capture_sdk_core_releaseFactory.create(), this.provideOnfidoConfigProvider);
            this.provideProofOfAddressApi$onfido_capture_sdk_core_releaseProvider = NetworkModule_ProvideProofOfAddressApi$onfido_capture_sdk_core_releaseFactory.create(this.provideOnfidoFetcher$onfido_capture_sdk_core_releaseProvider);
        }

        private BaseActivity injectBaseActivity(BaseActivity baseActivity) {
            BaseActivity_MembersInjector.injectPerformanceAnalytics(baseActivity, this.aggregatedPerformanceAnalyticsProvider.get());
            BaseActivity_MembersInjector.injectOnfidoConfig(baseActivity, SdkModule_ProvideOnfidoConfigFactory.provideOnfidoConfig(this.sdkModule));
            BaseActivity_MembersInjector.injectWaitingScreenTracker(baseActivity, this.waitingScreenTrackerProvider.get());
            BaseActivity_MembersInjector.injectRemoteConfig(baseActivity, SdkProductionModule_ProvideOnfidoRemoteConfig$onfido_capture_sdk_core_releaseFactory.provideOnfidoRemoteConfig$onfido_capture_sdk_core_release());
            BaseActivity_MembersInjector.injectFlowTracker(baseActivity, flowTracker());
            return baseActivity;
        }

        private CameraPermissionsUtils injectCameraPermissionsUtils(CameraPermissionsUtils cameraPermissionsUtils) {
            CameraPermissionsUtils_MembersInjector.injectPermissionsManager(cameraPermissionsUtils, runtimePermissionsManager());
            return cameraPermissionsUtils;
        }

        private CaptureActivity injectCaptureActivity(CaptureActivity captureActivity) {
            BaseActivity_MembersInjector.injectPerformanceAnalytics(captureActivity, this.aggregatedPerformanceAnalyticsProvider.get());
            BaseActivity_MembersInjector.injectOnfidoConfig(captureActivity, SdkModule_ProvideOnfidoConfigFactory.provideOnfidoConfig(this.sdkModule));
            BaseActivity_MembersInjector.injectWaitingScreenTracker(captureActivity, this.waitingScreenTrackerProvider.get());
            BaseActivity_MembersInjector.injectRemoteConfig(captureActivity, SdkProductionModule_ProvideOnfidoRemoteConfig$onfido_capture_sdk_core_releaseFactory.provideOnfidoRemoteConfig$onfido_capture_sdk_core_release());
            BaseActivity_MembersInjector.injectFlowTracker(captureActivity, flowTracker());
            CaptureActivity_MembersInjector.injectPresenter(captureActivity, capturePresenter());
            CaptureActivity_MembersInjector.injectImageUtils(captureActivity, new ImageUtils());
            CaptureActivity_MembersInjector.injectOnfidoApiService(captureActivity, this.onfidoApiServiceProvider.get());
            CaptureActivity_MembersInjector.injectAnnouncementService(captureActivity, announcementService());
            CaptureActivity_MembersInjector.injectVibratorService(captureActivity, vibratorService());
            CaptureActivity_MembersInjector.injectSchedulersProvider(captureActivity, SdkProductionModule_ProvideSchedulers$onfido_capture_sdk_core_releaseFactory.provideSchedulers$onfido_capture_sdk_core_release());
            CaptureActivity_MembersInjector.injectScreenLoadTracker(captureActivity, this.screenLoadTrackerProvider.get());
            CaptureActivity_MembersInjector.injectOnfidoRemoteConfig(captureActivity, SdkProductionModule_ProvideOnfidoRemoteConfig$onfido_capture_sdk_core_releaseFactory.provideOnfidoRemoteConfig$onfido_capture_sdk_core_release());
            CaptureActivity_MembersInjector.injectCryptography(captureActivity, cryptography());
            CaptureActivity_MembersInjector.injectPayloadHelper(captureActivity, providePayloadHelper$onfido_capture_sdk_core_release());
            CaptureActivity_MembersInjector.injectCameraFactory(captureActivity, onfidoCameraFactory());
            CaptureActivity_MembersInjector.injectTokenProvider(captureActivity, this.onfidoTokenProvider.get());
            CaptureActivity_MembersInjector.injectEnvironmentIntegrityChecker(captureActivity, environmentIntegrityCheckerImpl());
            CaptureActivity_MembersInjector.injectDispatchersProvider(captureActivity, SdkProductionModule_ProvideDispatchersProvider$onfido_capture_sdk_core_releaseFactory.provideDispatchersProvider$onfido_capture_sdk_core_release());
            return captureActivity;
        }

        private CountrySelectionFragment injectCountrySelectionFragment(CountrySelectionFragment countrySelectionFragment) {
            CountrySelectionFragment_MembersInjector.injectPresenter(countrySelectionFragment, countrySelectionPresenter());
            return countrySelectionFragment;
        }

        private CrashHandlerWorker injectCrashHandlerWorker(CrashHandlerWorker crashHandlerWorker) {
            CrashHandlerWorker_MembersInjector.injectRemoteLoggerTree(crashHandlerWorker, this.remoteLoggerTreeProvider.get());
            CrashHandlerWorker_MembersInjector.injectFlowTracker(crashHandlerWorker, flowTracker());
            return crashHandlerWorker;
        }

        private DocumentCaptureFragment injectDocumentCaptureFragment(DocumentCaptureFragment documentCaptureFragment) {
            DocumentCaptureFragment_MembersInjector.injectViewModelProvider(documentCaptureFragment, this.documentCaptureViewModelProvider);
            DocumentCaptureFragment_MembersInjector.injectPermissionsManager(documentCaptureFragment, runtimePermissionsManager());
            DocumentCaptureFragment_MembersInjector.injectAnnouncementService(documentCaptureFragment, announcementService());
            DocumentCaptureFragment_MembersInjector.injectVibratorService(documentCaptureFragment, vibratorService());
            DocumentCaptureFragment_MembersInjector.injectImageUtils(documentCaptureFragment, new ImageUtils());
            DocumentCaptureFragment_MembersInjector.injectCameraFactory(documentCaptureFragment, onfidoCameraFactory());
            return documentCaptureFragment;
        }

        private FaceConfirmationFragment injectFaceConfirmationFragment(FaceConfirmationFragment faceConfirmationFragment) {
            FaceConfirmationFragment_MembersInjector.injectImageUtils(faceConfirmationFragment, new ImageUtils());
            return faceConfirmationFragment;
        }

        private FaceIntroFragment injectFaceIntroFragment(FaceIntroFragment faceIntroFragment) {
            FaceIntroFragment_MembersInjector.injectPresenter(faceIntroFragment, faceIntroPresenter());
            return faceIntroFragment;
        }

        private FinalScreenFragment injectFinalScreenFragment(FinalScreenFragment finalScreenFragment) {
            FinalScreenFragment_MembersInjector.injectPresenter(finalScreenFragment, finalScreenPresenter());
            return finalScreenFragment;
        }

        private LivenessCaptureFragment injectLivenessCaptureFragment(LivenessCaptureFragment livenessCaptureFragment) {
            LivenessCaptureFragment_MembersInjector.injectViewModelProvider(livenessCaptureFragment, this.livenessCaptureViewModelProvider);
            LivenessCaptureFragment_MembersInjector.injectCameraFactory(livenessCaptureFragment, onfidoCameraFactory());
            LivenessCaptureFragment_MembersInjector.injectAnnouncementService(livenessCaptureFragment, announcementService());
            LivenessCaptureFragment_MembersInjector.injectVibratorService(livenessCaptureFragment, vibratorService());
            LivenessCaptureFragment_MembersInjector.injectImageUtils(livenessCaptureFragment, new ImageUtils());
            return livenessCaptureFragment;
        }

        private LivenessChallengesLoadingView injectLivenessChallengesLoadingView(LivenessChallengesLoadingView livenessChallengesLoadingView) {
            LivenessChallengesLoadingView_MembersInjector.injectPresenter(livenessChallengesLoadingView, livenessChallengesLoadingPresenter());
            return livenessChallengesLoadingView;
        }

        private LivenessConfirmationFragment injectLivenessConfirmationFragment(LivenessConfirmationFragment livenessConfirmationFragment) {
            LivenessConfirmationFragment_MembersInjector.injectPresenter(livenessConfirmationFragment, livenessConfirmationPresenter());
            LivenessConfirmationFragment_MembersInjector.injectLivenessChallengesDrawerFactory(livenessConfirmationFragment, this.factoryProvider3.get());
            return livenessConfirmationFragment;
        }

        private LivenessIntroFragment injectLivenessIntroFragment(LivenessIntroFragment livenessIntroFragment) {
            LivenessIntroFragment_MembersInjector.injectPresenter(livenessIntroFragment, livenessIntroPresenter());
            return livenessIntroFragment;
        }

        private LivenessOverlayView injectLivenessOverlayView(LivenessOverlayView livenessOverlayView) {
            LivenessOverlayView_MembersInjector.injectLivenessChallengesDrawerFactory(livenessOverlayView, this.factoryProvider3.get());
            LivenessOverlayView_MembersInjector.injectPresenter(livenessOverlayView, livenessOverlayPresenter());
            return livenessOverlayView;
        }

        private LoadingFragment injectLoadingFragment(LoadingFragment loadingFragment) {
            LoadingFragment_MembersInjector.injectStorage(loadingFragment, sharedPreferencesDataSource());
            LoadingFragment_MembersInjector.injectLoadingViewModelFactory(loadingFragment, this.factoryProvider8.get());
            return loadingFragment;
        }

        private NfcCanEntryFragment injectNfcCanEntryFragment(NfcCanEntryFragment nfcCanEntryFragment) {
            NfcCanEntryFragment_MembersInjector.injectNfcViewModelFactory(nfcCanEntryFragment, this.factoryProvider7.get());
            return nfcCanEntryFragment;
        }

        private NfcCanMaxAttemptsFragment injectNfcCanMaxAttemptsFragment(NfcCanMaxAttemptsFragment nfcCanMaxAttemptsFragment) {
            NfcCanMaxAttemptsFragment_MembersInjector.injectScreenTracker(nfcCanMaxAttemptsFragment, screenTracker());
            return nfcCanMaxAttemptsFragment;
        }

        private NfcDeviceNotSupportedFragment injectNfcDeviceNotSupportedFragment(NfcDeviceNotSupportedFragment nfcDeviceNotSupportedFragment) {
            NfcDeviceNotSupportedFragment_MembersInjector.injectScreenTracker(nfcDeviceNotSupportedFragment, screenTracker());
            NfcDeviceNotSupportedFragment_MembersInjector.injectNfcTracker(nfcDeviceNotSupportedFragment, nfcTracker());
            return nfcDeviceNotSupportedFragment;
        }

        private NfcDocumentNotSupportedFragment injectNfcDocumentNotSupportedFragment(NfcDocumentNotSupportedFragment nfcDocumentNotSupportedFragment) {
            NfcDocumentNotSupportedFragment_MembersInjector.injectScreenTracker(nfcDocumentNotSupportedFragment, screenTracker());
            return nfcDocumentNotSupportedFragment;
        }

        private NfcHostFragment injectNfcHostFragment(NfcHostFragment nfcHostFragment) {
            NfcHostFragment_MembersInjector.injectNfcViewModelFactory(nfcHostFragment, this.factoryProvider5.get());
            return nfcHostFragment;
        }

        private NfcPermissionFragment injectNfcPermissionFragment(NfcPermissionFragment nfcPermissionFragment) {
            NfcPermissionFragment_MembersInjector.injectScreenTracker(nfcPermissionFragment, screenTracker());
            return nfcPermissionFragment;
        }

        private NfcScanFailFragment injectNfcScanFailFragment(NfcScanFailFragment nfcScanFailFragment) {
            NfcScanFailFragment_MembersInjector.injectScreenTracker(nfcScanFailFragment, screenTracker());
            return nfcScanFailFragment;
        }

        private NfcScanFragment injectNfcScanFragment(NfcScanFragment nfcScanFragment) {
            NfcScanFragment_MembersInjector.injectPresenterFactory(nfcScanFragment, this.factoryProvider6.get());
            NfcScanFragment_MembersInjector.injectAnnouncementService(nfcScanFragment, announcementService());
            NfcScanFragment_MembersInjector.injectNfcInteractor(nfcScanFragment, nfcInteractorImpl());
            NfcScanFragment_MembersInjector.injectHapticFeedback(nfcScanFragment, new HapticFeedback());
            NfcScanFragment_MembersInjector.injectDispatchersProvider(nfcScanFragment, SdkProductionModule_ProvideDispatchersProvider$onfido_capture_sdk_core_releaseFactory.provideDispatchersProvider$onfido_capture_sdk_core_release());
            return nfcScanFragment;
        }

        private NfcService injectNfcService(NfcService nfcService) {
            NfcService_MembersInjector.injectPresenterFactory(nfcService, this.factoryProvider6.get());
            NfcService_MembersInjector.injectNfcInteractor(nfcService, nfcInteractorImpl());
            return nfcService;
        }

        private OnfidoActivity injectOnfidoActivity(OnfidoActivity onfidoActivity) {
            BaseActivity_MembersInjector.injectPerformanceAnalytics(onfidoActivity, this.aggregatedPerformanceAnalyticsProvider.get());
            BaseActivity_MembersInjector.injectOnfidoConfig(onfidoActivity, SdkModule_ProvideOnfidoConfigFactory.provideOnfidoConfig(this.sdkModule));
            BaseActivity_MembersInjector.injectWaitingScreenTracker(onfidoActivity, this.waitingScreenTrackerProvider.get());
            BaseActivity_MembersInjector.injectRemoteConfig(onfidoActivity, SdkProductionModule_ProvideOnfidoRemoteConfig$onfido_capture_sdk_core_releaseFactory.provideOnfidoRemoteConfig$onfido_capture_sdk_core_release());
            BaseActivity_MembersInjector.injectFlowTracker(onfidoActivity, flowTracker());
            OnfidoActivity_MembersInjector.injectPresenterFactory(onfidoActivity, this.presenterAssistedFactoryProvider.get());
            OnfidoActivity_MembersInjector.injectNfcInteractor(onfidoActivity, nfcInteractorImpl());
            OnfidoActivity_MembersInjector.injectOnfidoAnalytics(onfidoActivity, this.provideOnfidoAnalytics$onfido_capture_sdk_core_releaseProvider.get());
            return onfidoActivity;
        }

        private OnfidoCaptureValidationBubble injectOnfidoCaptureValidationBubble(OnfidoCaptureValidationBubble onfidoCaptureValidationBubble) {
            OnfidoCaptureValidationBubble_MembersInjector.injectCaptureValidationBubblePresenterFactory(onfidoCaptureValidationBubble, this.factoryProvider4.get());
            OnfidoCaptureValidationBubble_MembersInjector.injectAnnouncementService(onfidoCaptureValidationBubble, announcementService());
            return onfidoCaptureValidationBubble;
        }

        private PermissionsManagementFragment injectPermissionsManagementFragment(PermissionsManagementFragment permissionsManagementFragment) {
            PermissionsManagementFragment_MembersInjector.injectPresenter(permissionsManagementFragment, permissionsManagementPresenter());
            return permissionsManagementFragment;
        }

        private SelfieCaptureFragment injectSelfieCaptureFragment(SelfieCaptureFragment selfieCaptureFragment) {
            SelfieCaptureFragment_MembersInjector.injectPerformanceAnalytics(selfieCaptureFragment, this.aggregatedPerformanceAnalyticsProvider.get());
            SelfieCaptureFragment_MembersInjector.injectImageUtils(selfieCaptureFragment, new ImageUtils());
            SelfieCaptureFragment_MembersInjector.injectOnfidoRemoteConfig(selfieCaptureFragment, SdkProductionModule_ProvideOnfidoRemoteConfig$onfido_capture_sdk_core_releaseFactory.provideOnfidoRemoteConfig$onfido_capture_sdk_core_release());
            SelfieCaptureFragment_MembersInjector.injectViewModelFactory(selfieCaptureFragment, this.factoryProvider10.get());
            SelfieCaptureFragment_MembersInjector.injectPermissionsManager(selfieCaptureFragment, runtimePermissionsManager());
            SelfieCaptureFragment_MembersInjector.injectAnnouncementService(selfieCaptureFragment, announcementService());
            SelfieCaptureFragment_MembersInjector.injectCameraFactory(selfieCaptureFragment, onfidoCameraFactory());
            return selfieCaptureFragment;
        }

        private UserConsentFragment injectUserConsentFragment(UserConsentFragment userConsentFragment) {
            UserConsentFragment_MembersInjector.injectViewModelProvider(userConsentFragment, this.userConsentViewModelProvider);
            return userConsentFragment;
        }

        private WelcomeFragment injectWelcomeFragment(WelcomeFragment welcomeFragment) {
            WelcomeFragment_MembersInjector.injectPresenterFactory(welcomeFragment, this.factoryProvider2.get());
            return welcomeFragment;
        }

        private SdkConfiguration.LivenessCapture livenessCapture() {
            return SdkModule_ProvideLivenessCaptureSettingsFactory.provideLivenessCaptureSettings(this.sdkModule, SdkProductionModule_ProvideOnfidoRemoteConfig$onfido_capture_sdk_core_releaseFactory.provideOnfidoRemoteConfig$onfido_capture_sdk_core_release());
        }

        private LivenessChallengesLoadingPresenter livenessChallengesLoadingPresenter() {
            return new LivenessChallengesLoadingPresenter(livenessChallengesRepository(), announcementService(), SdkProductionModule_ProvideSchedulers$onfido_capture_sdk_core_releaseFactory.provideSchedulers$onfido_capture_sdk_core_release());
        }

        private LivenessChallengesRepository livenessChallengesRepository() {
            return new LivenessChallengesRepository(this.onfidoApiServiceProvider.get(), new LivenessChallengesTransformer());
        }

        private LivenessConfirmationPresenter livenessConfirmationPresenter() {
            return new LivenessConfirmationPresenter(this.onfidoApiServiceProvider.get(), provideSdkUploadMetadataHelper$onfido_capture_sdk_core_release(), providePayloadHelper$onfido_capture_sdk_core_release(), livenessCapture(), volumeManager(), SdkProductionModule_ProvideSchedulers$onfido_capture_sdk_core_releaseFactory.provideSchedulers$onfido_capture_sdk_core_release(), livenessTracker(), new DefaultTimeProvider(), this.waitingScreenTrackerProvider.get(), mediaCallbackResultReceiverResultReceiver());
        }

        private LivenessInteractor livenessInteractor() {
            return new LivenessInteractor(this.provideSdkContext$onfido_capture_sdk_core_releaseProvider.get(), new DefaultTimeProvider());
        }

        private LivenessIntroPresenter livenessIntroPresenter() {
            return new LivenessIntroPresenter(livenessTracker(), livenessIntroVideoRepository(), SdkProductionModule_ProvideSchedulers$onfido_capture_sdk_core_releaseFactory.provideSchedulers$onfido_capture_sdk_core_release());
        }

        private LivenessIntroVideoRepository livenessIntroVideoRepository() {
            return new LivenessIntroVideoRepository(new LivenessIntroVideoUrlProvider(), fileCache(), this.provideLivenessIntroVideoAPI$onfido_capture_sdk_core_releaseProvider.get());
        }

        private LivenessOverlayPresenter livenessOverlayPresenter() {
            return new LivenessOverlayPresenter(faceDetector(), new LivenessProgressManager(), SdkProductionModule_ProvideSchedulers$onfido_capture_sdk_core_releaseFactory.provideSchedulers$onfido_capture_sdk_core_release(), vibratorService(), announcementService());
        }

        private LivenessTracker livenessTracker() {
            return new LivenessTracker(this.provideOnfidoAnalytics$onfido_capture_sdk_core_releaseProvider.get());
        }

        private MRZDocumentExtractor mRZDocumentExtractor() {
            return SdkModule_ProvideOnDeviceMRZDocumentExtractor$onfido_capture_sdk_core_releaseFactory.provideOnDeviceMRZDocumentExtractor$onfido_capture_sdk_core_release(this.sdkModule, this.provideSdkContext$onfido_capture_sdk_core_releaseProvider.get(), DoubleCheck.lazy(this.mRZDocumentExtractorGoogleProvider), DoubleCheck.lazy(MRZDocumentExtractorEmpty_Factory.create()));
        }

        private ResultReceiver mediaCallbackResultReceiverResultReceiver() {
            SdkModule sdkModule = this.sdkModule;
            return sdkModule.provideMediaCallback(SdkModule_ProvideOnfidoConfigFactory.provideOnfidoConfig(sdkModule));
        }

        private NfcAdapter nfcAdapter() {
            return SdkCaptureProductionModule.INSTANCE.provideNfcAdapter(this.provideSdkContext$onfido_capture_sdk_core_releaseProvider.get());
        }

        private NfcInteractorImpl nfcInteractorImpl() {
            return new NfcInteractorImpl(nfcAdapter(), passportNfcReader(), nfcTracker(), new NfcTimeouts(), new DefaultTimeProvider());
        }

        private NfcPropertiesService nfcPropertiesService() {
            return new NfcPropertiesService(this.onfidoApiServiceProvider.get());
        }

        private NfcTracker nfcTracker() {
            return new NfcTracker(this.provideOnfidoAnalytics$onfido_capture_sdk_core_releaseProvider.get());
        }

        private OnDeviceValidationTransformer onDeviceValidationTransformer() {
            return new OnDeviceValidationTransformer(this.nativeDetectorProvider.get(), DoubleCheck.lazy(this.provideMRZDetector$onfido_capture_sdk_core_releaseProvider), DoubleCheck.lazy(this.provideBarcodeDetector$onfido_capture_sdk_core_releaseProvider), DoubleCheck.lazy(this.provideFaceOnDocumentDetector$onfido_capture_sdk_core_releaseProvider));
        }

        private OnfidoCameraFactory onfidoCameraFactory() {
            return new OnfidoCameraFactory(this.provideSdkContext$onfido_capture_sdk_core_releaseProvider.get(), SdkProductionModule_ProvideOnfidoRemoteConfig$onfido_capture_sdk_core_releaseFactory.provideOnfidoRemoteConfig$onfido_capture_sdk_core_release(), this.factoryProvider.get(), cameraUseCaseConfigProvider(), frameSamplerOfOnfidoImage(), SdkModule_ProvideImageAnalyzer$onfido_capture_sdk_core_releaseFactory.provideImageAnalyzer$onfido_capture_sdk_core_release(this.sdkModule));
        }

        private OnfidoMlModelProviderImpl onfidoMlModelProviderImpl() {
            return new OnfidoMlModelProviderImpl(this.provideSdkContext$onfido_capture_sdk_core_releaseProvider.get(), captureTracker(), new DefaultTimeProvider(), SdkModule_ProvideOnfidoDocumentDetectorFactory$onfido_capture_sdk_core_releaseFactory.provideOnfidoDocumentDetectorFactory$onfido_capture_sdk_core_release(this.sdkModule), SdkProductionModule_ProvideOnfidoRemoteConfig$onfido_capture_sdk_core_releaseFactory.provideOnfidoRemoteConfig$onfido_capture_sdk_core_release());
        }

        private PassportNfcReader passportNfcReader() {
            return SdkModule_ProvidePassportNfcReader$onfido_capture_sdk_core_releaseFactory.providePassportNfcReader$onfido_capture_sdk_core_release(this.sdkModule, SdkProductionModule_ProvideOnfidoRemoteConfig$onfido_capture_sdk_core_releaseFactory.provideOnfidoRemoteConfig$onfido_capture_sdk_core_release(), nfcTracker());
        }

        private PermissionsManagementPresenter permissionsManagementPresenter() {
            return new PermissionsManagementPresenter(runtimePermissionsManager(), permissionsTracker());
        }

        private PermissionsTracker permissionsTracker() {
            return new PermissionsTracker(this.provideOnfidoAnalytics$onfido_capture_sdk_core_releaseProvider.get());
        }

        private PostCaptureDocumentValidationsManager postCaptureDocumentValidationsManager() {
            return new PostCaptureDocumentValidationsManager(identityInteractor(), onDeviceValidationTransformer(), this.provideRetainableValidationsResult$onfido_capture_sdk_core_releaseProvider.get(), this.provideBarcodeValidationSuspenderProvider.get(), SdkProductionModule_ProvideOnfidoRemoteConfig$onfido_capture_sdk_core_releaseFactory.provideOnfidoRemoteConfig$onfido_capture_sdk_core_release());
        }

        private RealTimeDocumentValidationsManager realTimeDocumentValidationsManager() {
            return new RealTimeDocumentValidationsManager(identityInteractor());
        }

        private RectangleDetector rectangleDetector() {
            return SdkModule_ProvideRectangleDetector$onfido_capture_sdk_core_releaseFactory.provideRectangleDetector$onfido_capture_sdk_core_release(this.sdkModule, this.provideSdkContext$onfido_capture_sdk_core_releaseProvider.get(), DoubleCheck.lazy(this.rectangleDetectorGoogleProvider), DoubleCheck.lazy(RectangleDetectorEmpty_Factory.create()));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public RuntimePermissionsManager runtimePermissionsManager() {
            return new RuntimePermissionsManager(this.provideSdkContext$onfido_capture_sdk_core_releaseProvider.get(), sharedPreferencesDataSource());
        }

        private ScreenTracker screenTracker() {
            return new ScreenTracker(this.provideOnfidoAnalytics$onfido_capture_sdk_core_releaseProvider.get());
        }

        private SharedPreferencesDataSource sharedPreferencesDataSource() {
            return new SharedPreferencesDataSource(this.provideSdkContext$onfido_capture_sdk_core_releaseProvider.get(), NetworkModule_ProvideJson$onfido_capture_sdk_core_releaseFactory.provideJson$onfido_capture_sdk_core_release());
        }

        private SupportedDocumentsRepository supportedDocumentsRepository() {
            return SdkModule_ProvideSupportedDocumentsRepository$onfido_capture_sdk_core_releaseFactory.provideSupportedDocumentsRepository$onfido_capture_sdk_core_release(this.sdkModule, this.onfidoSupportedDocumentsRepositoryProvider, this.workflowSupportedDocumentsRepositoryProvider, this.remoteSupportedDocumentsRepositoryProvider, SdkProductionModule_ProvideOnfidoRemoteConfig$onfido_capture_sdk_core_releaseFactory.provideOnfidoRemoteConfig$onfido_capture_sdk_core_release(), SdkModule_ProvideOnfidoConfigFactory.provideOnfidoConfig(this.sdkModule));
        }

        private TelephonyManager telephonyManager() {
            return SdkModule_ProvideTelephonyManagerFactory.provideTelephonyManager(this.sdkModule, this.provideSdkContext$onfido_capture_sdk_core_releaseProvider.get());
        }

        private VibratorService vibratorService() {
            return new VibratorService(this.provideSdkContext$onfido_capture_sdk_core_releaseProvider.get());
        }

        private VolumeManager volumeManager() {
            return new VolumeManager(audioManager());
        }

        @Override // com.onfido.android.sdk.capture.common.di.SdkComponent
        public Context applicationContext() {
            return this.provideSdkContext$onfido_capture_sdk_core_releaseProvider.get();
        }

        @Override // com.onfido.android.sdk.capture.common.di.SdkComponent
        public DispatchersProvider dispatchersProvider() {
            return SdkProductionModule_ProvideDispatchersProvider$onfido_capture_sdk_core_releaseFactory.provideDispatchersProvider$onfido_capture_sdk_core_release();
        }

        @Override // com.onfido.android.sdk.capture.common.di.SdkComponent
        public FlowConfig flowConfig() {
            return this.sdkModule.getFlowConfig();
        }

        @Override // com.onfido.android.sdk.capture.common.di.SdkComponent
        public FlowTracker flowTracker() {
            return new FlowTracker(this.provideOnfidoAnalytics$onfido_capture_sdk_core_releaseProvider.get());
        }

        @Override // com.onfido.android.sdk.capture.common.di.SdkComponent
        public Json getJsonParser() {
            return NetworkModule_ProvideJson$onfido_capture_sdk_core_releaseFactory.provideJson$onfido_capture_sdk_core_release();
        }

        @Override // com.onfido.android.sdk.capture.common.di.SdkComponent
        public OkHttpClient getOkHttpClient() {
            return this.provideOkHttpClient$onfido_capture_sdk_core_releaseProvider.get();
        }

        @Override // com.onfido.android.sdk.capture.common.di.SdkComponent
        public Retrofit getRetrofit() {
            return this.provideRetrofit$onfido_capture_sdk_core_releaseProvider.get();
        }

        @Override // com.onfido.android.sdk.capture.common.di.SdkComponent
        public void inject$onfido_capture_sdk_core_release(PermissionsManagementFragment permissionsManagementFragment) {
            injectPermissionsManagementFragment(permissionsManagementFragment);
        }

        @Override // com.onfido.android.sdk.capture.common.di.SdkComponent
        public NativeDetector nativeDetector() {
            return this.nativeDetectorProvider.get();
        }

        @Override // com.onfido.android.sdk.capture.common.di.SdkComponent
        public OnfidoAnalytics onfidoAnalytics() {
            return this.provideOnfidoAnalytics$onfido_capture_sdk_core_releaseProvider.get();
        }

        @Override // com.onfido.android.sdk.capture.common.di.SdkComponent
        public OnfidoApiService onfidoApiService() {
            return this.onfidoApiServiceProvider.get();
        }

        @Override // com.onfido.android.sdk.capture.common.di.SdkComponent
        public OnfidoConfig onfidoConfig() {
            return SdkModule_ProvideOnfidoConfigFactory.provideOnfidoConfig(this.sdkModule);
        }

        @Override // com.onfido.android.sdk.capture.common.di.SdkComponent
        public OnfidoFetcher onfidoFetcher() {
            return this.provideOnfidoFetcher$onfido_capture_sdk_core_releaseProvider.get();
        }

        @Override // com.onfido.android.sdk.capture.common.di.SdkComponent
        public OnfidoRemoteConfig onfidoRemoteConfig() {
            return SdkProductionModule_ProvideOnfidoRemoteConfig$onfido_capture_sdk_core_releaseFactory.provideOnfidoRemoteConfig$onfido_capture_sdk_core_release();
        }

        @Override // com.onfido.android.sdk.capture.common.di.SdkComponent
        public OnfidoSupportedDocumentsRepository onfidoSupportedDocumentsRepository() {
            return this.onfidoSupportedDocumentsRepositoryProvider.get();
        }

        @Override // com.onfido.android.sdk.capture.common.di.SdkComponent
        public OnfidoTokenProvider onfidoTokenProvider() {
            return this.onfidoTokenProvider.get();
        }

        @Override // com.onfido.android.sdk.capture.common.di.SdkComponent
        public PoaComponent.Factory poaComponentFactory$onfido_capture_sdk_core_release() {
            return new PoaComponentFactory(this.sdkComponentImpl);
        }

        @Override // com.onfido.android.sdk.capture.common.di.SdkComponent
        public ApplicantId provideApplicantId() {
            return SdkModule_ProvideApplicantUuidFactory.provideApplicantUuid(this.sdkModule, this.onfidoTokenProvider.get());
        }

        @Override // com.onfido.android.sdk.capture.common.di.SdkComponent
        public String provideCustomerUserHash() {
            return SdkModule_ProvideCustomerUserHashFactory.provideCustomerUserHash(this.sdkModule, this.onfidoTokenProvider.get());
        }

        @Override // com.onfido.android.sdk.capture.common.di.SdkComponent
        public NfcInteractor provideNfcInteractor() {
            return nfcInteractorImpl();
        }

        @Override // com.onfido.android.sdk.capture.common.di.SdkComponent
        public PayloadHelper providePayloadHelper$onfido_capture_sdk_core_release() {
            return new PayloadHelper(cryptography(), NetworkModule_ProvideJson$onfido_capture_sdk_core_releaseFactory.provideJson$onfido_capture_sdk_core_release(), provideApplicantId());
        }

        @Override // com.onfido.android.sdk.capture.common.di.SdkComponent
        public SdkUploadMetadataHelper provideSdkUploadMetadataHelper$onfido_capture_sdk_core_release() {
            return new SdkUploadMetadataHelper(identityInteractor(), SdkProductionModule_ProvideOnfidoRemoteConfig$onfido_capture_sdk_core_releaseFactory.provideOnfidoRemoteConfig$onfido_capture_sdk_core_release(), environmentIntegrityCheckerImpl());
        }

        @Override // com.onfido.android.sdk.capture.common.di.SdkComponent
        public TimeProvider provideTimeProvider$onfido_capture_sdk_core_release() {
            return new DefaultTimeProvider();
        }

        @Override // com.onfido.android.sdk.capture.common.di.SdkComponent
        public RestrictedDocumentSelectionHostComponent.Factory rdsHostComponentFactory$onfido_capture_sdk_core_release() {
            return new RestrictedDocumentSelectionHostComponentFactory(this.sdkComponentImpl);
        }

        @Override // com.onfido.android.sdk.capture.common.di.SdkComponent
        public SchedulersProvider schedulersProvider() {
            return SdkProductionModule_ProvideSchedulers$onfido_capture_sdk_core_releaseFactory.provideSchedulers$onfido_capture_sdk_core_release();
        }

        @Override // com.onfido.android.sdk.capture.common.di.SdkComponent
        public WaitingScreenTracker waitingScreenTracker() {
            return this.waitingScreenTrackerProvider.get();
        }

        @Override // com.onfido.android.sdk.capture.common.di.SdkComponent
        public WorkflowSupportedDocumentsStore workflowSupportedDocumentsStore() {
            return this.workflowSupportedDocumentsRepositoryProvider.get();
        }

        @Override // com.onfido.android.sdk.capture.common.di.SdkComponent
        public void inject$onfido_capture_sdk_core_release(NfcService nfcService) {
            injectNfcService(nfcService);
        }

        @Override // com.onfido.android.sdk.capture.common.di.SdkComponent
        public void inject$onfido_capture_sdk_core_release(CountrySelectionFragment countrySelectionFragment) {
            injectCountrySelectionFragment(countrySelectionFragment);
        }

        @Override // com.onfido.android.sdk.capture.common.di.SdkComponent
        public void inject$onfido_capture_sdk_core_release(CrashHandlerWorker crashHandlerWorker) {
            injectCrashHandlerWorker(crashHandlerWorker);
        }

        @Override // com.onfido.android.sdk.capture.common.di.SdkComponent
        public void inject$onfido_capture_sdk_core_release(BaseActivity baseActivity) {
            injectBaseActivity(baseActivity);
        }

        @Override // com.onfido.android.sdk.capture.common.di.SdkComponent
        public void inject$onfido_capture_sdk_core_release(OnfidoActivity onfidoActivity) {
            injectOnfidoActivity(onfidoActivity);
        }

        @Override // com.onfido.android.sdk.capture.common.di.SdkComponent
        public void inject$onfido_capture_sdk_core_release(CaptureActivity captureActivity) {
            injectCaptureActivity(captureActivity);
        }

        @Override // com.onfido.android.sdk.capture.common.di.SdkComponent
        public void inject$onfido_capture_sdk_core_release(DocumentCaptureFragment documentCaptureFragment) {
            injectDocumentCaptureFragment(documentCaptureFragment);
        }

        @Override // com.onfido.android.sdk.capture.common.di.SdkComponent
        public void inject$onfido_capture_sdk_core_release(FaceConfirmationFragment faceConfirmationFragment) {
            injectFaceConfirmationFragment(faceConfirmationFragment);
        }

        @Override // com.onfido.android.sdk.capture.common.di.SdkComponent
        public void inject$onfido_capture_sdk_core_release(LivenessConfirmationFragment livenessConfirmationFragment) {
            injectLivenessConfirmationFragment(livenessConfirmationFragment);
        }

        @Override // com.onfido.android.sdk.capture.common.di.SdkComponent
        public void inject$onfido_capture_sdk_core_release(LivenessIntroFragment livenessIntroFragment) {
            injectLivenessIntroFragment(livenessIntroFragment);
        }

        @Override // com.onfido.android.sdk.capture.common.di.SdkComponent
        public void inject$onfido_capture_sdk_core_release(LivenessOverlayView livenessOverlayView) {
            injectLivenessOverlayView(livenessOverlayView);
        }

        @Override // com.onfido.android.sdk.capture.common.di.SdkComponent
        public void inject$onfido_capture_sdk_core_release(LivenessCaptureFragment livenessCaptureFragment) {
            injectLivenessCaptureFragment(livenessCaptureFragment);
        }

        @Override // com.onfido.android.sdk.capture.common.di.SdkComponent
        public void inject$onfido_capture_sdk_core_release(LivenessChallengesLoadingView livenessChallengesLoadingView) {
            injectLivenessChallengesLoadingView(livenessChallengesLoadingView);
        }

        @Override // com.onfido.android.sdk.capture.common.di.SdkComponent
        public void inject$onfido_capture_sdk_core_release(SelfieCaptureFragment selfieCaptureFragment) {
            injectSelfieCaptureFragment(selfieCaptureFragment);
        }

        @Override // com.onfido.android.sdk.capture.common.di.SdkComponent
        public void inject$onfido_capture_sdk_core_release(CameraPermissionsUtils cameraPermissionsUtils) {
            injectCameraPermissionsUtils(cameraPermissionsUtils);
        }

        @Override // com.onfido.android.sdk.capture.common.di.SdkComponent
        public void inject$onfido_capture_sdk_core_release(FaceIntroFragment faceIntroFragment) {
            injectFaceIntroFragment(faceIntroFragment);
        }

        @Override // com.onfido.android.sdk.capture.common.di.SdkComponent
        public void inject$onfido_capture_sdk_core_release(FinalScreenFragment finalScreenFragment) {
            injectFinalScreenFragment(finalScreenFragment);
        }

        @Override // com.onfido.android.sdk.capture.common.di.SdkComponent
        public void inject$onfido_capture_sdk_core_release(NfcCanMaxAttemptsFragment nfcCanMaxAttemptsFragment) {
            injectNfcCanMaxAttemptsFragment(nfcCanMaxAttemptsFragment);
        }

        @Override // com.onfido.android.sdk.capture.common.di.SdkComponent
        public void inject$onfido_capture_sdk_core_release(NfcDeviceNotSupportedFragment nfcDeviceNotSupportedFragment) {
            injectNfcDeviceNotSupportedFragment(nfcDeviceNotSupportedFragment);
        }

        @Override // com.onfido.android.sdk.capture.common.di.SdkComponent
        public void inject$onfido_capture_sdk_core_release(NfcDocumentNotSupportedFragment nfcDocumentNotSupportedFragment) {
            injectNfcDocumentNotSupportedFragment(nfcDocumentNotSupportedFragment);
        }

        @Override // com.onfido.android.sdk.capture.common.di.SdkComponent
        public void inject$onfido_capture_sdk_core_release(NfcHostFragment nfcHostFragment) {
            injectNfcHostFragment(nfcHostFragment);
        }

        @Override // com.onfido.android.sdk.capture.common.di.SdkComponent
        public void inject$onfido_capture_sdk_core_release(NfcScanFailFragment nfcScanFailFragment) {
            injectNfcScanFailFragment(nfcScanFailFragment);
        }

        @Override // com.onfido.android.sdk.capture.common.di.SdkComponent
        public void inject$onfido_capture_sdk_core_release(NfcPermissionFragment nfcPermissionFragment) {
            injectNfcPermissionFragment(nfcPermissionFragment);
        }

        @Override // com.onfido.android.sdk.capture.common.di.SdkComponent
        public void inject$onfido_capture_sdk_core_release(NfcCanEntryFragment nfcCanEntryFragment) {
            injectNfcCanEntryFragment(nfcCanEntryFragment);
        }

        @Override // com.onfido.android.sdk.capture.common.di.SdkComponent
        public void inject$onfido_capture_sdk_core_release(NfcScanFragment nfcScanFragment) {
            injectNfcScanFragment(nfcScanFragment);
        }

        @Override // com.onfido.android.sdk.capture.common.di.SdkComponent
        public void inject$onfido_capture_sdk_core_release(UserConsentFragment userConsentFragment) {
            injectUserConsentFragment(userConsentFragment);
        }

        @Override // com.onfido.android.sdk.capture.common.di.SdkComponent
        public void inject$onfido_capture_sdk_core_release(WelcomeFragment welcomeFragment) {
            injectWelcomeFragment(welcomeFragment);
        }

        @Override // com.onfido.android.sdk.capture.common.di.SdkComponent
        public void inject$onfido_capture_sdk_core_release(LoadingFragment loadingFragment) {
            injectLoadingFragment(loadingFragment);
        }

        @Override // com.onfido.android.sdk.capture.common.di.SdkComponent
        public void inject$onfido_capture_sdk_core_release(OnfidoCaptureValidationBubble onfidoCaptureValidationBubble) {
            injectOnfidoCaptureValidationBubble(onfidoCaptureValidationBubble);
        }
    }

    private DaggerSdkComponent() {
    }

    public static Builder builder() {
        return new Builder();
    }
}
