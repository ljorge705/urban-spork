package com.onfido.android.sdk.capture.internal.config;

import com.onfido.android.sdk.workflow.internal.WaitingScreenThreshold;
import com.onfido.api.client.data.SdkConfiguration;
import java.util.Map;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u001d\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\bg\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u00020\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0012\u0010\u000e\u001a\u00020\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0012\u0010\u0012\u001a\u00020\u0013X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0012\u0010\u0016\u001a\u00020\u0013X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0015R\u0012\u0010\u0017\u001a\u00020\u0013X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0015R\u0012\u0010\u0018\u001a\u00020\u0013X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0015R\u0012\u0010\u0019\u001a\u00020\u0013X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0015R\u0012\u0010\u001a\u001a\u00020\u0013X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0015R\u0012\u0010\u001b\u001a\u00020\u0013X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0015R\u0012\u0010\u001c\u001a\u00020\u0013X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0015R\u0012\u0010\u001d\u001a\u00020\u0013X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u0015R\u0012\u0010\u001e\u001a\u00020\u0013X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u0015R\u0012\u0010\u001f\u001a\u00020\u0013X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u0015R\u0012\u0010 \u001a\u00020\u0013X¦\u0004¢\u0006\u0006\u001a\u0004\b \u0010\u0015R\u0012\u0010!\u001a\u00020\u0013X¦\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\u0015R\u0012\u0010\"\u001a\u00020\u0013X¦\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010\u0015R\u0012\u0010#\u001a\u00020\u0013X¦\u0004¢\u0006\u0006\u001a\u0004\b#\u0010\u0015R\u0012\u0010$\u001a\u00020\u0013X¦\u0004¢\u0006\u0006\u001a\u0004\b$\u0010\u0015R\u0012\u0010%\u001a\u00020\u0013X¦\u0004¢\u0006\u0006\u001a\u0004\b%\u0010\u0015R\u0012\u0010&\u001a\u00020\u0013X¦\u0004¢\u0006\u0006\u001a\u0004\b&\u0010\u0015R\u0012\u0010'\u001a\u00020\u0013X¦\u0004¢\u0006\u0006\u001a\u0004\b'\u0010\u0015R\u0012\u0010(\u001a\u00020\u0013X¦\u0004¢\u0006\u0006\u001a\u0004\b(\u0010\u0015R\u0012\u0010)\u001a\u00020\u0013X¦\u0004¢\u0006\u0006\u001a\u0004\b)\u0010\u0015R\u0012\u0010*\u001a\u00020\u0013X¦\u0004¢\u0006\u0006\u001a\u0004\b*\u0010\u0015R\u0018\u0010+\u001a\u00020\u0013X¦\u000e¢\u0006\f\u001a\u0004\b+\u0010\u0015\"\u0004\b,\u0010-R\u0012\u0010.\u001a\u00020\u0013X¦\u0004¢\u0006\u0006\u001a\u0004\b.\u0010\u0015R\u0012\u0010/\u001a\u00020\u0013X¦\u0004¢\u0006\u0006\u001a\u0004\b/\u0010\u0015R\u0012\u00100\u001a\u000201X¦\u0004¢\u0006\u0006\u001a\u0004\b2\u00103R\u0012\u00104\u001a\u000205X¦\u0004¢\u0006\u0006\u001a\u0004\b6\u00107R\u001e\u00108\u001a\u000e\u0012\u0004\u0012\u00020:\u0012\u0004\u0012\u00020;09X¦\u0004¢\u0006\u0006\u001a\u0004\b<\u0010=R\u0012\u0010>\u001a\u00020?X¦\u0004¢\u0006\u0006\u001a\u0004\b@\u0010AR\u0012\u0010B\u001a\u00020CX¦\u0004¢\u0006\u0006\u001a\u0004\bD\u0010ER\u0012\u0010F\u001a\u00020GX¦\u0004¢\u0006\u0006\u001a\u0004\bH\u0010IR\u0012\u0010J\u001a\u00020KX¦\u0004¢\u0006\u0006\u001a\u0004\bL\u0010MR\u001e\u0010N\u001a\u000e\u0012\u0004\u0012\u00020:\u0012\u0004\u0012\u00020;09X¦\u0004¢\u0006\u0006\u001a\u0004\bO\u0010=¨\u0006P"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;", "", "cameraXConfiguration", "Lcom/onfido/api/client/data/SdkConfiguration$CameraXConfiguration;", "getCameraXConfiguration", "()Lcom/onfido/api/client/data/SdkConfiguration$CameraXConfiguration;", "documentCapture", "Lcom/onfido/api/client/data/SdkConfiguration$DocumentCapture;", "getDocumentCapture", "()Lcom/onfido/api/client/data/SdkConfiguration$DocumentCapture;", "documentDetectionExperiment", "Lcom/onfido/api/client/data/SdkConfiguration$ExperimentalFeatures$DocumentDetectionExperiment;", "getDocumentDetectionExperiment", "()Lcom/onfido/api/client/data/SdkConfiguration$ExperimentalFeatures$DocumentDetectionExperiment;", "documentPostCaptureValidationTargetWidth", "", "getDocumentPostCaptureValidationTargetWidth", "()I", "enableWebModuleBasedPoa", "", "getEnableWebModuleBasedPoa", "()Z", "isApplicantConsentRequired", "isAutoFlashModeEnabled", "isCameraXEnabled", "isCameraXHighQualityModeEnabled", "isCameraxStreamSharingEnabled", "isCutoffImprovementsEnabled", "isDocumentCropDisabled", "isDocumentSupportRulesEnabled", "isEnvironmentIntegrityCheckEnabled", "isFocusImprovementsEnabled", "isFourByThreeEnabled", "isGenericMrzValidatorEnabled", "isImageQualityServiceEnabled", "isIncreasedCompressionQualityEnabled", "isInhouseAnalyticsEnabled", "isMediaCallbackCroppingDisabled", "isMotionCameraXEnabled", "isMotionTensorFlowLiteEnabled", "isMultiImageCaptureEnabled", "isOnDeviceMRZExtractionEnabled", "isPerformanceAnalyticsEnabled", "isRefactoredCaptureEnabled", "setRefactoredCaptureEnabled", "(Z)V", "isResolutionImprovementsEnabled", "isStudioUserFlowExitEnabled", "livenessCapture", "Lcom/onfido/api/client/data/SdkConfiguration$LivenessCapture;", "getLivenessCapture", "()Lcom/onfido/api/client/data/SdkConfiguration$LivenessCapture;", "loggerConfiguration", "Lcom/onfido/api/client/data/SdkConfiguration$LoggerConfiguration;", "getLoggerConfiguration", "()Lcom/onfido/api/client/data/SdkConfiguration$LoggerConfiguration;", "mediaWaitingScreensTimeThresholds", "", "Lcom/onfido/android/sdk/workflow/internal/WaitingScreenThreshold;", "", "getMediaWaitingScreensTimeThresholds", "()Ljava/util/Map;", "motionCapture", "Lcom/onfido/api/client/data/SdkConfiguration$MotionCapture;", "getMotionCapture", "()Lcom/onfido/api/client/data/SdkConfiguration$MotionCapture;", "motionExperiment", "Lcom/onfido/api/client/data/SdkConfiguration$ExperimentalFeatures$MotionExperiment;", "getMotionExperiment", "()Lcom/onfido/api/client/data/SdkConfiguration$ExperimentalFeatures$MotionExperiment;", "sdkConfiguration", "Lcom/onfido/api/client/data/SdkConfiguration;", "getSdkConfiguration", "()Lcom/onfido/api/client/data/SdkConfiguration;", "selfieCapture", "Lcom/onfido/api/client/data/SdkConfiguration$SelfieCapture;", "getSelfieCapture", "()Lcom/onfido/api/client/data/SdkConfiguration$SelfieCapture;", "studioWaitingScreensTimeThresholds", "getStudioWaitingScreensTimeThresholds", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface OnfidoRemoteConfig {
    SdkConfiguration.CameraXConfiguration getCameraXConfiguration();

    SdkConfiguration.DocumentCapture getDocumentCapture();

    SdkConfiguration.ExperimentalFeatures.DocumentDetectionExperiment getDocumentDetectionExperiment();

    int getDocumentPostCaptureValidationTargetWidth();

    boolean getEnableWebModuleBasedPoa();

    SdkConfiguration.LivenessCapture getLivenessCapture();

    SdkConfiguration.LoggerConfiguration getLoggerConfiguration();

    Map<WaitingScreenThreshold, Long> getMediaWaitingScreensTimeThresholds();

    SdkConfiguration.MotionCapture getMotionCapture();

    SdkConfiguration.ExperimentalFeatures.MotionExperiment getMotionExperiment();

    SdkConfiguration getSdkConfiguration();

    SdkConfiguration.SelfieCapture getSelfieCapture();

    Map<WaitingScreenThreshold, Long> getStudioWaitingScreensTimeThresholds();

    boolean isApplicantConsentRequired();

    boolean isAutoFlashModeEnabled();

    boolean isCameraXEnabled();

    boolean isCameraXHighQualityModeEnabled();

    boolean isCameraxStreamSharingEnabled();

    boolean isCutoffImprovementsEnabled();

    boolean isDocumentCropDisabled();

    boolean isDocumentSupportRulesEnabled();

    boolean isEnvironmentIntegrityCheckEnabled();

    boolean isFocusImprovementsEnabled();

    boolean isFourByThreeEnabled();

    boolean isGenericMrzValidatorEnabled();

    boolean isImageQualityServiceEnabled();

    boolean isIncreasedCompressionQualityEnabled();

    boolean isInhouseAnalyticsEnabled();

    boolean isMediaCallbackCroppingDisabled();

    boolean isMotionCameraXEnabled();

    boolean isMotionTensorFlowLiteEnabled();

    boolean isMultiImageCaptureEnabled();

    boolean isOnDeviceMRZExtractionEnabled();

    boolean isPerformanceAnalyticsEnabled();

    boolean isRefactoredCaptureEnabled();

    boolean isResolutionImprovementsEnabled();

    boolean isStudioUserFlowExitEnabled();

    void setRefactoredCaptureEnabled(boolean z);
}
