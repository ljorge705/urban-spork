package com.onfido.android.sdk.capture.ui.camera;

import android.os.ResultReceiver;
import com.onfido.android.sdk.capture.analytics.IdentityInteractor;
import com.onfido.android.sdk.capture.common.permissions.RuntimePermissionsManager;
import com.onfido.android.sdk.capture.detector.face.FaceDetector;
import com.onfido.android.sdk.capture.detector.mrzextraction.MRZDocumentExtractor;
import com.onfido.android.sdk.capture.detector.rectangle.RectangleDetector;
import com.onfido.android.sdk.capture.document.DocumentConfigurationManager;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.CaptureTracker;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.FlowTracker;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.LivenessTracker;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.NfcTracker;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.WaitingScreenTracker;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.metadata.SdkUploadMetadataHelper;
import com.onfido.android.sdk.capture.internal.nfc.NfcInteractor;
import com.onfido.android.sdk.capture.internal.service.AnnouncementService;
import com.onfido.android.sdk.capture.internal.usecase.AccessibleDocumentCaptureUseCase;
import com.onfido.android.sdk.capture.internal.usecase.DocumentProcessingUseCase;
import com.onfido.android.sdk.capture.internal.usecase.FaceProcessingUseCase;
import com.onfido.android.sdk.capture.internal.usecase.validation.DocumentValidationUseCase;
import com.onfido.android.sdk.capture.internal.util.DispatchersProvider;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.android.sdk.capture.internal.validation.DocumentProcessingResultsFailureAnalyzer;
import com.onfido.android.sdk.capture.internal.validation.RetainableValidationsResult;
import com.onfido.android.sdk.capture.native_detector.NativeDetector;
import com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessInteractor;
import com.onfido.android.sdk.capture.ui.camera.rx.DocumentCaptureDelayTransformer;
import com.onfido.android.sdk.capture.utils.ImageUtils;
import com.onfido.android.sdk.capture.utils.TimeProvider;
import com.onfido.android.sdk.capture.validation.BackendDocumentValidationsManager;
import com.onfido.android.sdk.capture.validation.BarcodeValidationSuspender;
import com.onfido.android.sdk.capture.validation.PostCaptureDocumentValidationsManager;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class CapturePresenter_Factory implements Factory<CapturePresenter> {
    private final Provider<AccessibleDocumentCaptureUseCase> accessibleDocumentCaptureUseCaseProvider;
    private final Provider<AnnouncementService> announcementServiceProvider;
    private final Provider<BackendDocumentValidationsManager> backendDocumentValidationsManagerProvider;
    private final Provider<BarcodeValidationSuspender> barcodeValidationSuspenderProvider;
    private final Provider<CaptureTracker> captureTrackerProvider;
    private final Provider<DispatchersProvider> dispatchersProvider;
    private final Provider<DocumentConfigurationManager> documentConfigurationManagerProvider;
    private final Provider<DocumentCaptureDelayTransformer> documentDelayTransformerProvider;
    private final Provider<DocumentProcessingResultsFailureAnalyzer> documentProcessingFailureAnalyzerProvider;
    private final Provider<DocumentProcessingUseCase> documentProcessingUseCaseProvider;
    private final Provider<DocumentService> documentServiceProvider;
    private final Provider<DocumentValidationUseCase> documentValidationUseCaseProvider;
    private final Provider<FaceDetector> faceDetectorProvider;
    private final Provider<FaceProcessingUseCase> faceProcessingUseCaseProvider;
    private final Provider<FlowTracker> flowTrackerProvider;
    private final Provider<IdentityInteractor> identityInteractorProvider;
    private final Provider<ImageUtils> imageUtilsProvider;
    private final Provider<LivenessInteractor> livenessInteractorProvider;
    private final Provider<LivenessTracker> livenessTrackerProvider;
    private final Provider<ResultReceiver> mediaCallbackProvider;
    private final Provider<MRZDocumentExtractor> mrzDocumentExtractorProvider;
    private final Provider<NativeDetector> nativeDetectorProvider;
    private final Provider<NfcInteractor> nfcInteractorProvider;
    private final Provider<NfcPropertiesService> nfcPropertiesServiceProvider;
    private final Provider<NfcTracker> nfcTrackerProvider;
    private final Provider<OnfidoRemoteConfig> onfidoRemoteConfigProvider;
    private final Provider<RuntimePermissionsManager> permissionsManagerProvider;
    private final Provider<PostCaptureDocumentValidationsManager> postCaptureDocumentValidationsManagerProvider;
    private final Provider<RectangleDetector> rectangleDetectorProvider;
    private final Provider<RetainableValidationsResult> retainableValidationsResultProvider;
    private final Provider<SchedulersProvider> schedulersProvider;
    private final Provider<SdkUploadMetadataHelper> sdkUploadMetaDataHelperProvider;
    private final Provider<TimeProvider> timeProvider;
    private final Provider<WaitingScreenTracker> waitingScreenTrackerProvider;

    public CapturePresenter_Factory(Provider<NativeDetector> provider, Provider<RectangleDetector> provider2, Provider<AccessibleDocumentCaptureUseCase> provider3, Provider<LivenessInteractor> provider4, Provider<BackendDocumentValidationsManager> provider5, Provider<DocumentProcessingUseCase> provider6, Provider<PostCaptureDocumentValidationsManager> provider7, Provider<RuntimePermissionsManager> provider8, Provider<FaceDetector> provider9, Provider<IdentityInteractor> provider10, Provider<DocumentConfigurationManager> provider11, Provider<TimeProvider> provider12, Provider<SdkUploadMetadataHelper> provider13, Provider<DocumentService> provider14, Provider<NfcPropertiesService> provider15, Provider<NfcInteractor> provider16, Provider<DocumentCaptureDelayTransformer> provider17, Provider<SchedulersProvider> provider18, Provider<FaceProcessingUseCase> provider19, Provider<OnfidoRemoteConfig> provider20, Provider<AnnouncementService> provider21, Provider<DocumentProcessingResultsFailureAnalyzer> provider22, Provider<RetainableValidationsResult> provider23, Provider<BarcodeValidationSuspender> provider24, Provider<ImageUtils> provider25, Provider<CaptureTracker> provider26, Provider<NfcTracker> provider27, Provider<LivenessTracker> provider28, Provider<WaitingScreenTracker> provider29, Provider<FlowTracker> provider30, Provider<ResultReceiver> provider31, Provider<MRZDocumentExtractor> provider32, Provider<DocumentValidationUseCase> provider33, Provider<DispatchersProvider> provider34) {
        this.nativeDetectorProvider = provider;
        this.rectangleDetectorProvider = provider2;
        this.accessibleDocumentCaptureUseCaseProvider = provider3;
        this.livenessInteractorProvider = provider4;
        this.backendDocumentValidationsManagerProvider = provider5;
        this.documentProcessingUseCaseProvider = provider6;
        this.postCaptureDocumentValidationsManagerProvider = provider7;
        this.permissionsManagerProvider = provider8;
        this.faceDetectorProvider = provider9;
        this.identityInteractorProvider = provider10;
        this.documentConfigurationManagerProvider = provider11;
        this.timeProvider = provider12;
        this.sdkUploadMetaDataHelperProvider = provider13;
        this.documentServiceProvider = provider14;
        this.nfcPropertiesServiceProvider = provider15;
        this.nfcInteractorProvider = provider16;
        this.documentDelayTransformerProvider = provider17;
        this.schedulersProvider = provider18;
        this.faceProcessingUseCaseProvider = provider19;
        this.onfidoRemoteConfigProvider = provider20;
        this.announcementServiceProvider = provider21;
        this.documentProcessingFailureAnalyzerProvider = provider22;
        this.retainableValidationsResultProvider = provider23;
        this.barcodeValidationSuspenderProvider = provider24;
        this.imageUtilsProvider = provider25;
        this.captureTrackerProvider = provider26;
        this.nfcTrackerProvider = provider27;
        this.livenessTrackerProvider = provider28;
        this.waitingScreenTrackerProvider = provider29;
        this.flowTrackerProvider = provider30;
        this.mediaCallbackProvider = provider31;
        this.mrzDocumentExtractorProvider = provider32;
        this.documentValidationUseCaseProvider = provider33;
        this.dispatchersProvider = provider34;
    }

    public static CapturePresenter_Factory create(Provider<NativeDetector> provider, Provider<RectangleDetector> provider2, Provider<AccessibleDocumentCaptureUseCase> provider3, Provider<LivenessInteractor> provider4, Provider<BackendDocumentValidationsManager> provider5, Provider<DocumentProcessingUseCase> provider6, Provider<PostCaptureDocumentValidationsManager> provider7, Provider<RuntimePermissionsManager> provider8, Provider<FaceDetector> provider9, Provider<IdentityInteractor> provider10, Provider<DocumentConfigurationManager> provider11, Provider<TimeProvider> provider12, Provider<SdkUploadMetadataHelper> provider13, Provider<DocumentService> provider14, Provider<NfcPropertiesService> provider15, Provider<NfcInteractor> provider16, Provider<DocumentCaptureDelayTransformer> provider17, Provider<SchedulersProvider> provider18, Provider<FaceProcessingUseCase> provider19, Provider<OnfidoRemoteConfig> provider20, Provider<AnnouncementService> provider21, Provider<DocumentProcessingResultsFailureAnalyzer> provider22, Provider<RetainableValidationsResult> provider23, Provider<BarcodeValidationSuspender> provider24, Provider<ImageUtils> provider25, Provider<CaptureTracker> provider26, Provider<NfcTracker> provider27, Provider<LivenessTracker> provider28, Provider<WaitingScreenTracker> provider29, Provider<FlowTracker> provider30, Provider<ResultReceiver> provider31, Provider<MRZDocumentExtractor> provider32, Provider<DocumentValidationUseCase> provider33, Provider<DispatchersProvider> provider34) {
        return new CapturePresenter_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16, provider17, provider18, provider19, provider20, provider21, provider22, provider23, provider24, provider25, provider26, provider27, provider28, provider29, provider30, provider31, provider32, provider33, provider34);
    }

    public static CapturePresenter newInstance(NativeDetector nativeDetector, RectangleDetector rectangleDetector, AccessibleDocumentCaptureUseCase accessibleDocumentCaptureUseCase, LivenessInteractor livenessInteractor, BackendDocumentValidationsManager backendDocumentValidationsManager, DocumentProcessingUseCase documentProcessingUseCase, PostCaptureDocumentValidationsManager postCaptureDocumentValidationsManager, RuntimePermissionsManager runtimePermissionsManager, FaceDetector faceDetector, IdentityInteractor identityInteractor, DocumentConfigurationManager documentConfigurationManager, TimeProvider timeProvider, SdkUploadMetadataHelper sdkUploadMetadataHelper, DocumentService documentService, NfcPropertiesService nfcPropertiesService, NfcInteractor nfcInteractor, DocumentCaptureDelayTransformer documentCaptureDelayTransformer, SchedulersProvider schedulersProvider, FaceProcessingUseCase faceProcessingUseCase, OnfidoRemoteConfig onfidoRemoteConfig, AnnouncementService announcementService, DocumentProcessingResultsFailureAnalyzer documentProcessingResultsFailureAnalyzer, RetainableValidationsResult retainableValidationsResult, BarcodeValidationSuspender barcodeValidationSuspender, ImageUtils imageUtils, CaptureTracker captureTracker, NfcTracker nfcTracker, LivenessTracker livenessTracker, WaitingScreenTracker waitingScreenTracker, FlowTracker flowTracker, ResultReceiver resultReceiver, MRZDocumentExtractor mRZDocumentExtractor, DocumentValidationUseCase documentValidationUseCase, DispatchersProvider dispatchersProvider) {
        return new CapturePresenter(nativeDetector, rectangleDetector, accessibleDocumentCaptureUseCase, livenessInteractor, backendDocumentValidationsManager, documentProcessingUseCase, postCaptureDocumentValidationsManager, runtimePermissionsManager, faceDetector, identityInteractor, documentConfigurationManager, timeProvider, sdkUploadMetadataHelper, documentService, nfcPropertiesService, nfcInteractor, documentCaptureDelayTransformer, schedulersProvider, faceProcessingUseCase, onfidoRemoteConfig, announcementService, documentProcessingResultsFailureAnalyzer, retainableValidationsResult, barcodeValidationSuspender, imageUtils, captureTracker, nfcTracker, livenessTracker, waitingScreenTracker, flowTracker, resultReceiver, mRZDocumentExtractor, documentValidationUseCase, dispatchersProvider);
    }

    @Override // com.onfido.javax.inject.Provider
    public CapturePresenter get() {
        return newInstance(this.nativeDetectorProvider.get(), this.rectangleDetectorProvider.get(), this.accessibleDocumentCaptureUseCaseProvider.get(), this.livenessInteractorProvider.get(), this.backendDocumentValidationsManagerProvider.get(), this.documentProcessingUseCaseProvider.get(), this.postCaptureDocumentValidationsManagerProvider.get(), this.permissionsManagerProvider.get(), this.faceDetectorProvider.get(), this.identityInteractorProvider.get(), this.documentConfigurationManagerProvider.get(), this.timeProvider.get(), this.sdkUploadMetaDataHelperProvider.get(), this.documentServiceProvider.get(), this.nfcPropertiesServiceProvider.get(), this.nfcInteractorProvider.get(), this.documentDelayTransformerProvider.get(), this.schedulersProvider.get(), this.faceProcessingUseCaseProvider.get(), this.onfidoRemoteConfigProvider.get(), this.announcementServiceProvider.get(), this.documentProcessingFailureAnalyzerProvider.get(), this.retainableValidationsResultProvider.get(), this.barcodeValidationSuspenderProvider.get(), this.imageUtilsProvider.get(), this.captureTrackerProvider.get(), this.nfcTrackerProvider.get(), this.livenessTrackerProvider.get(), this.waitingScreenTrackerProvider.get(), this.flowTrackerProvider.get(), this.mediaCallbackProvider.get(), this.mrzDocumentExtractorProvider.get(), this.documentValidationUseCaseProvider.get(), this.dispatchersProvider.get());
    }
}
