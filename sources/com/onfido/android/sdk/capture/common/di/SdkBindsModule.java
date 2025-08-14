package com.onfido.android.sdk.capture.common.di;

import com.onfido.android.sdk.capture.detector.mrzextraction.TextRecognizerProvider;
import com.onfido.android.sdk.capture.detector.mrzextraction.TextRecognizerProviderImpl;
import com.onfido.android.sdk.capture.internal.camera.factory.CameraFactory;
import com.onfido.android.sdk.capture.internal.camera.factory.OnfidoCameraFactory;
import com.onfido.android.sdk.capture.internal.token.OnfidoTokenProvider;
import com.onfido.android.sdk.capture.internal.util.environment.EnvironmentIntegrityChecker;
import com.onfido.android.sdk.capture.internal.util.environment.EnvironmentIntegrityCheckerImpl;
import com.onfido.android.sdk.capture.internal.util.logging.LoggerCachingDataSource;
import com.onfido.android.sdk.capture.internal.util.logging.LoggerMemoryCachingDataSource;
import com.onfido.android.sdk.capture.internal.validation.DocumentProcessingResultsFailureAnalyzer;
import com.onfido.android.sdk.capture.internal.validation.DocumentProcessingResultsFailureAnalyzerImpl;
import com.onfido.android.sdk.capture.ui.OnfidoPresenter;
import com.onfido.android.sdk.capture.utils.DefaultTimeProvider;
import com.onfido.android.sdk.capture.utils.TimeProvider;
import com.onfido.android.sdk.capture.utils.mlmodel.OnfidoMlModelProvider;
import com.onfido.android.sdk.capture.utils.mlmodel.OnfidoMlModelProviderImpl;
import com.onfido.android.sdk.workflow.internal.workflow.tasks.documentupload.WorkflowSupportedDocumentsRepository;
import com.onfido.android.sdk.workflow.internal.workflow.tasks.documentupload.WorkflowSupportedDocumentsStore;
import com.onfido.api.client.token.TokenProvider;
import com.onfido.dagger.Binds;
import com.onfido.dagger.Module;
import kotlin.Metadata;

@Module
@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b!\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H'J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\tH'J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\fH'J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u000fH'J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u0012H'J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H'J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH'J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH'J\u0010\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"H'J\u0010\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&H'¨\u0006'"}, d2 = {"Lcom/onfido/android/sdk/capture/common/di/SdkBindsModule;", "", "()V", "bindsDocumentProcessingResultsFailureAnalyzer", "Lcom/onfido/android/sdk/capture/internal/validation/DocumentProcessingResultsFailureAnalyzer;", "it", "Lcom/onfido/android/sdk/capture/internal/validation/DocumentProcessingResultsFailureAnalyzerImpl;", "bindsLoggerCachingSourceData", "Lcom/onfido/android/sdk/capture/internal/util/logging/LoggerCachingDataSource;", "Lcom/onfido/android/sdk/capture/internal/util/logging/LoggerMemoryCachingDataSource;", "bindsOnfidoPresenterFactory", "Lcom/onfido/android/sdk/capture/ui/OnfidoPresenter$Factory;", "Lcom/onfido/android/sdk/capture/ui/OnfidoPresenter$PresenterAssistedFactory;", "bindsTimeProvider", "Lcom/onfido/android/sdk/capture/utils/TimeProvider;", "Lcom/onfido/android/sdk/capture/utils/DefaultTimeProvider;", "bindsWorkflowSupportedDocumentsStore", "Lcom/onfido/android/sdk/workflow/internal/workflow/tasks/documentupload/WorkflowSupportedDocumentsStore;", "Lcom/onfido/android/sdk/workflow/internal/workflow/tasks/documentupload/WorkflowSupportedDocumentsRepository;", "provideCameraFactory", "Lcom/onfido/android/sdk/capture/internal/camera/factory/CameraFactory;", "onfidoCameraFactory", "Lcom/onfido/android/sdk/capture/internal/camera/factory/OnfidoCameraFactory;", "provideEnvironmentIntegrityChecker", "Lcom/onfido/android/sdk/capture/internal/util/environment/EnvironmentIntegrityChecker;", "checker", "Lcom/onfido/android/sdk/capture/internal/util/environment/EnvironmentIntegrityCheckerImpl;", "provideOnfidoModelProvider", "Lcom/onfido/android/sdk/capture/utils/mlmodel/OnfidoMlModelProvider;", "provider", "Lcom/onfido/android/sdk/capture/utils/mlmodel/OnfidoMlModelProviderImpl;", "provideTextRecognizerProvider", "Lcom/onfido/android/sdk/capture/detector/mrzextraction/TextRecognizerProvider;", "providerImpl", "Lcom/onfido/android/sdk/capture/detector/mrzextraction/TextRecognizerProviderImpl;", "provideTokenProvider", "Lcom/onfido/api/client/token/TokenProvider;", "tokenProvider", "Lcom/onfido/android/sdk/capture/internal/token/OnfidoTokenProvider;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public abstract class SdkBindsModule {
    @Binds
    public abstract DocumentProcessingResultsFailureAnalyzer bindsDocumentProcessingResultsFailureAnalyzer(DocumentProcessingResultsFailureAnalyzerImpl it);

    @Binds
    public abstract LoggerCachingDataSource bindsLoggerCachingSourceData(LoggerMemoryCachingDataSource it);

    @Binds
    public abstract OnfidoPresenter.Factory bindsOnfidoPresenterFactory(OnfidoPresenter.PresenterAssistedFactory it);

    @Binds
    public abstract TimeProvider bindsTimeProvider(DefaultTimeProvider it);

    @Binds
    public abstract WorkflowSupportedDocumentsStore bindsWorkflowSupportedDocumentsStore(WorkflowSupportedDocumentsRepository it);

    @Binds
    public abstract CameraFactory provideCameraFactory(OnfidoCameraFactory onfidoCameraFactory);

    @Binds
    public abstract EnvironmentIntegrityChecker provideEnvironmentIntegrityChecker(EnvironmentIntegrityCheckerImpl checker);

    @Binds
    public abstract OnfidoMlModelProvider provideOnfidoModelProvider(OnfidoMlModelProviderImpl provider);

    @Binds
    public abstract TextRecognizerProvider provideTextRecognizerProvider(TextRecognizerProviderImpl providerImpl);

    @Binds
    public abstract TokenProvider provideTokenProvider(OnfidoTokenProvider tokenProvider);
}
