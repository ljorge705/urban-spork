package com.onfido.android.sdk.capture.ui.camera.document;

import com.onfido.android.sdk.capture.common.preferences.SharedPreferencesDataSource;
import com.onfido.android.sdk.capture.detector.mrzextraction.MRZDocumentExtractor;
import com.onfido.android.sdk.capture.detector.rectangle.RectangleDetector;
import com.onfido.android.sdk.capture.document.DocumentConfigurationManager;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.metadata.SdkUploadMetadataHelper;
import com.onfido.android.sdk.capture.internal.service.AnnouncementService;
import com.onfido.android.sdk.capture.internal.usecase.AccessibleDocumentCaptureUseCase;
import com.onfido.android.sdk.capture.internal.usecase.MediaCallbacksUseCase;
import com.onfido.android.sdk.capture.internal.usecase.NfcUseCase;
import com.onfido.android.sdk.capture.internal.usecase.validation.DocumentValidationUseCase;
import com.onfido.android.sdk.capture.internal.util.DispatchersProvider;
import com.onfido.android.sdk.capture.internal.util.environment.EnvironmentIntegrityChecker;
import com.onfido.android.sdk.capture.internal.validation.DocumentProcessingResultsFailureAnalyzer;
import com.onfido.android.sdk.capture.internal.validation.RetainableValidationsResult;
import com.onfido.android.sdk.capture.native_detector.NativeDetector;
import com.onfido.android.sdk.capture.ui.camera.CaptureUploadService;
import com.onfido.android.sdk.capture.ui.camera.DocumentService;
import com.onfido.android.sdk.capture.ui.camera.rx.DocumentCaptureDelayTransformer;
import com.onfido.android.sdk.capture.utils.ImageUtils;
import com.onfido.android.sdk.capture.utils.TimeProvider;
import com.onfido.android.sdk.capture.validation.BackendDocumentValidationsManager;
import com.onfido.android.sdk.capture.validation.BarcodeValidationSuspender;
import com.onfido.android.sdk.capture.validation.PostCaptureDocumentValidationsManager;
import com.onfido.api.client.token.TokenProvider;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class DocumentCaptureViewModel_Factory implements Factory<DocumentCaptureViewModel> {
    private final Provider<AccessibleDocumentCaptureUseCase> accessibleDocumentCaptureUseCaseProvider;
    private final Provider<AnnouncementService> announcementServiceProvider;
    private final Provider<BackendDocumentValidationsManager> backendDocumentValidationsManagerProvider;
    private final Provider<BarcodeValidationSuspender> barcodeValidationSuspenderProvider;
    private final Provider<DispatchersProvider> dispatchersProvider;
    private final Provider<DocumentCaptureTracker> documentCaptureTrackerProvider;
    private final Provider<DocumentConfigurationManager> documentConfigurationManagerProvider;
    private final Provider<DocumentCaptureDelayTransformer> documentDelayTransformerProvider;
    private final Provider<DocumentProcessingResultsFailureAnalyzer> documentProcessingFailureAnalyzerProvider;
    private final Provider<DocumentService> documentServiceProvider;
    private final Provider<DocumentValidationUseCase> documentValidationUseCaseProvider;
    private final Provider<EnvironmentIntegrityChecker> environmentIntegrityCheckerProvider;
    private final Provider<ImageUtils> imageUtilsProvider;
    private final Provider<MediaCallbacksUseCase> mediaCallbacksUseCaseProvider;
    private final Provider<MRZDocumentExtractor> mrzDocumentExtractorProvider;
    private final Provider<NativeDetector> nativeDetectorProvider;
    private final Provider<NfcUseCase> nfcUseCaseProvider;
    private final Provider<OnfidoRemoteConfig> onfidoRemoteConfigProvider;
    private final Provider<PostCaptureDocumentValidationsManager> postCaptureDocumentValidationsManagerProvider;
    private final Provider<RectangleDetector> rectangleDetectorProvider;
    private final Provider<RetainableValidationsResult> retainableValidationsResultProvider;
    private final Provider<SdkUploadMetadataHelper> sdkUploadMetaDataHelperProvider;
    private final Provider<SharedPreferencesDataSource> storageProvider;
    private final Provider<TimeProvider> timeProvider;
    private final Provider<TokenProvider> tokenProvider;
    private final Provider<CaptureUploadService.Factory> uploadServiceFactoryProvider;

    public DocumentCaptureViewModel_Factory(Provider<DocumentConfigurationManager> provider, Provider<NativeDetector> provider2, Provider<AnnouncementService> provider3, Provider<OnfidoRemoteConfig> provider4, Provider<SdkUploadMetadataHelper> provider5, Provider<DocumentService> provider6, Provider<CaptureUploadService.Factory> provider7, Provider<TokenProvider> provider8, Provider<RectangleDetector> provider9, Provider<SharedPreferencesDataSource> provider10, Provider<BackendDocumentValidationsManager> provider11, Provider<PostCaptureDocumentValidationsManager> provider12, Provider<DocumentProcessingResultsFailureAnalyzer> provider13, Provider<DocumentCaptureDelayTransformer> provider14, Provider<MediaCallbacksUseCase> provider15, Provider<NfcUseCase> provider16, Provider<AccessibleDocumentCaptureUseCase> provider17, Provider<TimeProvider> provider18, Provider<DocumentCaptureTracker> provider19, Provider<BarcodeValidationSuspender> provider20, Provider<RetainableValidationsResult> provider21, Provider<DispatchersProvider> provider22, Provider<EnvironmentIntegrityChecker> provider23, Provider<ImageUtils> provider24, Provider<MRZDocumentExtractor> provider25, Provider<DocumentValidationUseCase> provider26) {
        this.documentConfigurationManagerProvider = provider;
        this.nativeDetectorProvider = provider2;
        this.announcementServiceProvider = provider3;
        this.onfidoRemoteConfigProvider = provider4;
        this.sdkUploadMetaDataHelperProvider = provider5;
        this.documentServiceProvider = provider6;
        this.uploadServiceFactoryProvider = provider7;
        this.tokenProvider = provider8;
        this.rectangleDetectorProvider = provider9;
        this.storageProvider = provider10;
        this.backendDocumentValidationsManagerProvider = provider11;
        this.postCaptureDocumentValidationsManagerProvider = provider12;
        this.documentProcessingFailureAnalyzerProvider = provider13;
        this.documentDelayTransformerProvider = provider14;
        this.mediaCallbacksUseCaseProvider = provider15;
        this.nfcUseCaseProvider = provider16;
        this.accessibleDocumentCaptureUseCaseProvider = provider17;
        this.timeProvider = provider18;
        this.documentCaptureTrackerProvider = provider19;
        this.barcodeValidationSuspenderProvider = provider20;
        this.retainableValidationsResultProvider = provider21;
        this.dispatchersProvider = provider22;
        this.environmentIntegrityCheckerProvider = provider23;
        this.imageUtilsProvider = provider24;
        this.mrzDocumentExtractorProvider = provider25;
        this.documentValidationUseCaseProvider = provider26;
    }

    public static DocumentCaptureViewModel_Factory create(Provider<DocumentConfigurationManager> provider, Provider<NativeDetector> provider2, Provider<AnnouncementService> provider3, Provider<OnfidoRemoteConfig> provider4, Provider<SdkUploadMetadataHelper> provider5, Provider<DocumentService> provider6, Provider<CaptureUploadService.Factory> provider7, Provider<TokenProvider> provider8, Provider<RectangleDetector> provider9, Provider<SharedPreferencesDataSource> provider10, Provider<BackendDocumentValidationsManager> provider11, Provider<PostCaptureDocumentValidationsManager> provider12, Provider<DocumentProcessingResultsFailureAnalyzer> provider13, Provider<DocumentCaptureDelayTransformer> provider14, Provider<MediaCallbacksUseCase> provider15, Provider<NfcUseCase> provider16, Provider<AccessibleDocumentCaptureUseCase> provider17, Provider<TimeProvider> provider18, Provider<DocumentCaptureTracker> provider19, Provider<BarcodeValidationSuspender> provider20, Provider<RetainableValidationsResult> provider21, Provider<DispatchersProvider> provider22, Provider<EnvironmentIntegrityChecker> provider23, Provider<ImageUtils> provider24, Provider<MRZDocumentExtractor> provider25, Provider<DocumentValidationUseCase> provider26) {
        return new DocumentCaptureViewModel_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16, provider17, provider18, provider19, provider20, provider21, provider22, provider23, provider24, provider25, provider26);
    }

    public static DocumentCaptureViewModel newInstance(DocumentConfigurationManager documentConfigurationManager, NativeDetector nativeDetector, AnnouncementService announcementService, OnfidoRemoteConfig onfidoRemoteConfig, SdkUploadMetadataHelper sdkUploadMetadataHelper, DocumentService documentService, CaptureUploadService.Factory factory, TokenProvider tokenProvider, RectangleDetector rectangleDetector, SharedPreferencesDataSource sharedPreferencesDataSource, BackendDocumentValidationsManager backendDocumentValidationsManager, PostCaptureDocumentValidationsManager postCaptureDocumentValidationsManager, DocumentProcessingResultsFailureAnalyzer documentProcessingResultsFailureAnalyzer, DocumentCaptureDelayTransformer documentCaptureDelayTransformer, MediaCallbacksUseCase mediaCallbacksUseCase, NfcUseCase nfcUseCase, AccessibleDocumentCaptureUseCase accessibleDocumentCaptureUseCase, TimeProvider timeProvider, DocumentCaptureTracker documentCaptureTracker, BarcodeValidationSuspender barcodeValidationSuspender, RetainableValidationsResult retainableValidationsResult, DispatchersProvider dispatchersProvider, EnvironmentIntegrityChecker environmentIntegrityChecker, ImageUtils imageUtils, MRZDocumentExtractor mRZDocumentExtractor, DocumentValidationUseCase documentValidationUseCase) {
        return new DocumentCaptureViewModel(documentConfigurationManager, nativeDetector, announcementService, onfidoRemoteConfig, sdkUploadMetadataHelper, documentService, factory, tokenProvider, rectangleDetector, sharedPreferencesDataSource, backendDocumentValidationsManager, postCaptureDocumentValidationsManager, documentProcessingResultsFailureAnalyzer, documentCaptureDelayTransformer, mediaCallbacksUseCase, nfcUseCase, accessibleDocumentCaptureUseCase, timeProvider, documentCaptureTracker, barcodeValidationSuspender, retainableValidationsResult, dispatchersProvider, environmentIntegrityChecker, imageUtils, mRZDocumentExtractor, documentValidationUseCase);
    }

    @Override // com.onfido.javax.inject.Provider
    public DocumentCaptureViewModel get() {
        return newInstance(this.documentConfigurationManagerProvider.get(), this.nativeDetectorProvider.get(), this.announcementServiceProvider.get(), this.onfidoRemoteConfigProvider.get(), this.sdkUploadMetaDataHelperProvider.get(), this.documentServiceProvider.get(), this.uploadServiceFactoryProvider.get(), this.tokenProvider.get(), this.rectangleDetectorProvider.get(), this.storageProvider.get(), this.backendDocumentValidationsManagerProvider.get(), this.postCaptureDocumentValidationsManagerProvider.get(), this.documentProcessingFailureAnalyzerProvider.get(), this.documentDelayTransformerProvider.get(), this.mediaCallbacksUseCaseProvider.get(), this.nfcUseCaseProvider.get(), this.accessibleDocumentCaptureUseCaseProvider.get(), this.timeProvider.get(), this.documentCaptureTrackerProvider.get(), this.barcodeValidationSuspenderProvider.get(), this.retainableValidationsResultProvider.get(), this.dispatchersProvider.get(), this.environmentIntegrityCheckerProvider.get(), this.imageUtilsProvider.get(), this.mrzDocumentExtractorProvider.get(), this.documentValidationUseCaseProvider.get());
    }
}
