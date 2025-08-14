package com.onfido.android.sdk.capture.internal.config;

import com.onfido.android.sdk.capture.internal.config.locale.SdkConfigHelper;
import com.onfido.android.sdk.workflow.internal.WaitingScreenThreshold;
import com.onfido.api.client.data.SdkConfiguration;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;

@Metadata(d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b5\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0014\u0010\u0081\u0001\u001a\u00030\u0082\u00012\b\u0010\u0083\u0001\u001a\u00030\u0084\u0001H\u0002J\u0012\u0010\u0085\u0001\u001a\u00030\u0082\u00012\u0006\u0010u\u001a\u00020tH\u0002J\u0012\u0010\u0086\u0001\u001a\u00030\u0082\u00012\u0006\u0010u\u001a\u00020tH\u0017R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u00020\tX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\u000fX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u0015X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001a\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u001a\u0010\u001d\u001a\u00020\u001eX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001a\u0010#\u001a\u00020\u001eX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010 \"\u0004\b$\u0010\"R\u001a\u0010%\u001a\u00020\u001eX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010 \"\u0004\b&\u0010\"R\u001a\u0010'\u001a\u00020\u001eX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010 \"\u0004\b(\u0010\"R\u001a\u0010)\u001a\u00020\u001eX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010 \"\u0004\b*\u0010\"R\u001a\u0010+\u001a\u00020\u001eX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010 \"\u0004\b,\u0010\"R\u001a\u0010-\u001a\u00020\u001eX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010 \"\u0004\b.\u0010\"R\u001a\u0010/\u001a\u00020\u001eX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010 \"\u0004\b0\u0010\"R\u001a\u00101\u001a\u00020\u001eX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010 \"\u0004\b2\u0010\"R\u001a\u00103\u001a\u00020\u001eX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010 \"\u0004\b4\u0010\"R\u001a\u00105\u001a\u00020\u001eX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u0010 \"\u0004\b6\u0010\"R\u001a\u00107\u001a\u00020\u001eX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u0010 \"\u0004\b8\u0010\"R\u001a\u00109\u001a\u00020\u001eX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010 \"\u0004\b:\u0010\"R\u001a\u0010;\u001a\u00020\u001eX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010 \"\u0004\b<\u0010\"R\u001a\u0010=\u001a\u00020\u001eX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010 \"\u0004\b>\u0010\"R\u001a\u0010?\u001a\u00020\u001eX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b?\u0010 \"\u0004\b@\u0010\"R\u001a\u0010A\u001a\u00020\u001eX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bA\u0010 \"\u0004\bB\u0010\"R\u001a\u0010C\u001a\u00020\u001eX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u0010 \"\u0004\bD\u0010\"R\u001a\u0010E\u001a\u00020\u001eX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bE\u0010 \"\u0004\bF\u0010\"R\u001a\u0010G\u001a\u00020\u001eX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bG\u0010 \"\u0004\bH\u0010\"R\u001a\u0010I\u001a\u00020\u001eX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bI\u0010 \"\u0004\bJ\u0010\"R\u001a\u0010K\u001a\u00020\u001eX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bK\u0010 \"\u0004\bL\u0010\"R\u001a\u0010M\u001a\u00020\u001eX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bM\u0010 \"\u0004\bN\u0010\"R\u001a\u0010O\u001a\u00020\u001eX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bO\u0010 \"\u0004\bP\u0010\"R\u001a\u0010Q\u001a\u00020\u001eX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bQ\u0010 \"\u0004\bR\u0010\"R\u001a\u0010S\u001a\u00020TX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bU\u0010V\"\u0004\bW\u0010XR\u001a\u0010Y\u001a\u00020ZX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b[\u0010\\\"\u0004\b]\u0010^R&\u0010_\u001a\u000e\u0012\u0004\u0012\u00020a\u0012\u0004\u0012\u00020b0`X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bc\u0010d\"\u0004\be\u0010fR\u001a\u0010g\u001a\u00020hX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bi\u0010j\"\u0004\bk\u0010lR\u001a\u0010m\u001a\u00020nX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bo\u0010p\"\u0004\bq\u0010rR\u001e\u0010u\u001a\u00020t2\u0006\u0010s\u001a\u00020t@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\bv\u0010wR\u001a\u0010x\u001a\u00020yX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bz\u0010{\"\u0004\b|\u0010}R'\u0010~\u001a\u000e\u0012\u0004\u0012\u00020a\u0012\u0004\u0012\u00020b0`X\u0096\u000e¢\u0006\u000f\n\u0000\u001a\u0004\b\u007f\u0010d\"\u0005\b\u0080\u0001\u0010f¨\u0006\u0087\u0001"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/config/DefaultOnfidoRemoteConfig;", "Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;", "Lcom/onfido/android/sdk/capture/internal/config/MutableOnfidoRemoteConfig;", "()V", "DEFAULT_BLUR_MAX_RETRY", "", "DEFAULT_POST_CAPTURE_VALIDATION_RESOLUTION", "blurValidationMaxRetry", "cameraXConfiguration", "Lcom/onfido/api/client/data/SdkConfiguration$CameraXConfiguration;", "getCameraXConfiguration", "()Lcom/onfido/api/client/data/SdkConfiguration$CameraXConfiguration;", "setCameraXConfiguration", "(Lcom/onfido/api/client/data/SdkConfiguration$CameraXConfiguration;)V", "documentCapture", "Lcom/onfido/api/client/data/SdkConfiguration$DocumentCapture;", "getDocumentCapture", "()Lcom/onfido/api/client/data/SdkConfiguration$DocumentCapture;", "setDocumentCapture", "(Lcom/onfido/api/client/data/SdkConfiguration$DocumentCapture;)V", "documentDetectionExperiment", "Lcom/onfido/api/client/data/SdkConfiguration$ExperimentalFeatures$DocumentDetectionExperiment;", "getDocumentDetectionExperiment", "()Lcom/onfido/api/client/data/SdkConfiguration$ExperimentalFeatures$DocumentDetectionExperiment;", "setDocumentDetectionExperiment", "(Lcom/onfido/api/client/data/SdkConfiguration$ExperimentalFeatures$DocumentDetectionExperiment;)V", "documentPostCaptureValidationTargetWidth", "getDocumentPostCaptureValidationTargetWidth", "()I", "enableWebModuleBasedPoa", "", "getEnableWebModuleBasedPoa", "()Z", "setEnableWebModuleBasedPoa", "(Z)V", "isApplicantConsentRequired", "setApplicantConsentRequired", "isAutoFlashModeEnabled", "setAutoFlashModeEnabled", "isCameraXEnabled", "setCameraXEnabled", "isCameraXHighQualityModeEnabled", "setCameraXHighQualityModeEnabled", "isCameraxStreamSharingEnabled", "setCameraxStreamSharingEnabled", "isCutoffImprovementsEnabled", "setCutoffImprovementsEnabled", "isDocumentCropDisabled", "setDocumentCropDisabled", "isDocumentSupportRulesEnabled", "setDocumentSupportRulesEnabled", "isEnvironmentIntegrityCheckEnabled", "setEnvironmentIntegrityCheckEnabled", "isFocusImprovementsEnabled", "setFocusImprovementsEnabled", "isFourByThreeEnabled", "setFourByThreeEnabled", "isGenericMrzValidatorEnabled", "setGenericMrzValidatorEnabled", "isImageQualityServiceEnabled", "setImageQualityServiceEnabled", "isIncreasedCompressionQualityEnabled", "setIncreasedCompressionQualityEnabled", "isInhouseAnalyticsEnabled", "setInhouseAnalyticsEnabled", "isMediaCallbackCroppingDisabled", "setMediaCallbackCroppingDisabled", "isMotionCameraXEnabled", "setMotionCameraXEnabled", "isMotionTensorFlowLiteEnabled", "setMotionTensorFlowLiteEnabled", "isMultiImageCaptureEnabled", "setMultiImageCaptureEnabled", "isOnDeviceMRZExtractionEnabled", "setOnDeviceMRZExtractionEnabled", "isPerformanceAnalyticsEnabled", "setPerformanceAnalyticsEnabled", "isRefactoredCaptureEnabled", "setRefactoredCaptureEnabled", "isResolutionImprovementsEnabled", "setResolutionImprovementsEnabled", "isStudioUserFlowExitEnabled", "setStudioUserFlowExitEnabled", "livenessCapture", "Lcom/onfido/api/client/data/SdkConfiguration$LivenessCapture;", "getLivenessCapture", "()Lcom/onfido/api/client/data/SdkConfiguration$LivenessCapture;", "setLivenessCapture", "(Lcom/onfido/api/client/data/SdkConfiguration$LivenessCapture;)V", "loggerConfiguration", "Lcom/onfido/api/client/data/SdkConfiguration$LoggerConfiguration;", "getLoggerConfiguration", "()Lcom/onfido/api/client/data/SdkConfiguration$LoggerConfiguration;", "setLoggerConfiguration", "(Lcom/onfido/api/client/data/SdkConfiguration$LoggerConfiguration;)V", "mediaWaitingScreensTimeThresholds", "", "Lcom/onfido/android/sdk/workflow/internal/WaitingScreenThreshold;", "", "getMediaWaitingScreensTimeThresholds", "()Ljava/util/Map;", "setMediaWaitingScreensTimeThresholds", "(Ljava/util/Map;)V", "motionCapture", "Lcom/onfido/api/client/data/SdkConfiguration$MotionCapture;", "getMotionCapture", "()Lcom/onfido/api/client/data/SdkConfiguration$MotionCapture;", "setMotionCapture", "(Lcom/onfido/api/client/data/SdkConfiguration$MotionCapture;)V", "motionExperiment", "Lcom/onfido/api/client/data/SdkConfiguration$ExperimentalFeatures$MotionExperiment;", "getMotionExperiment", "()Lcom/onfido/api/client/data/SdkConfiguration$ExperimentalFeatures$MotionExperiment;", "setMotionExperiment", "(Lcom/onfido/api/client/data/SdkConfiguration$ExperimentalFeatures$MotionExperiment;)V", "<set-?>", "Lcom/onfido/api/client/data/SdkConfiguration;", "sdkConfiguration", "getSdkConfiguration", "()Lcom/onfido/api/client/data/SdkConfiguration;", "selfieCapture", "Lcom/onfido/api/client/data/SdkConfiguration$SelfieCapture;", "getSelfieCapture", "()Lcom/onfido/api/client/data/SdkConfiguration$SelfieCapture;", "setSelfieCapture", "(Lcom/onfido/api/client/data/SdkConfiguration$SelfieCapture;)V", "studioWaitingScreensTimeThresholds", "getStudioWaitingScreensTimeThresholds", "setStudioWaitingScreensTimeThresholds", "extractWaitingScreenThresholds", "", "experimentalFeatures", "Lcom/onfido/api/client/data/SdkConfiguration$ExperimentalFeatures;", "syncSdkConfiguration", "updateFromSdkConfiguration", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DefaultOnfidoRemoteConfig implements OnfidoRemoteConfig, MutableOnfidoRemoteConfig {
    private static final int DEFAULT_BLUR_MAX_RETRY = 2;
    private static final int DEFAULT_POST_CAPTURE_VALIDATION_RESOLUTION = 720;
    public static final DefaultOnfidoRemoteConfig INSTANCE;
    private static int blurValidationMaxRetry;
    private static SdkConfiguration.CameraXConfiguration cameraXConfiguration;
    private static SdkConfiguration.DocumentCapture documentCapture;
    private static SdkConfiguration.ExperimentalFeatures.DocumentDetectionExperiment documentDetectionExperiment;
    private static boolean enableWebModuleBasedPoa;
    private static boolean isApplicantConsentRequired;
    private static boolean isAutoFlashModeEnabled;
    private static boolean isCameraXEnabled;
    private static boolean isCameraXHighQualityModeEnabled;
    private static boolean isCameraxStreamSharingEnabled;
    private static boolean isCutoffImprovementsEnabled;
    private static boolean isDocumentCropDisabled;
    private static boolean isDocumentSupportRulesEnabled;
    private static boolean isEnvironmentIntegrityCheckEnabled;
    private static boolean isFocusImprovementsEnabled;
    private static boolean isFourByThreeEnabled;
    private static boolean isGenericMrzValidatorEnabled;
    private static boolean isImageQualityServiceEnabled;
    private static boolean isIncreasedCompressionQualityEnabled;
    private static boolean isInhouseAnalyticsEnabled;
    private static boolean isMediaCallbackCroppingDisabled;
    private static boolean isMotionCameraXEnabled;
    private static boolean isMotionTensorFlowLiteEnabled;
    private static boolean isMultiImageCaptureEnabled;
    private static boolean isOnDeviceMRZExtractionEnabled;
    private static boolean isPerformanceAnalyticsEnabled;
    private static boolean isRefactoredCaptureEnabled;
    private static boolean isResolutionImprovementsEnabled;
    private static boolean isStudioUserFlowExitEnabled;
    private static SdkConfiguration.LivenessCapture livenessCapture;
    private static SdkConfiguration.LoggerConfiguration loggerConfiguration;
    private static Map<WaitingScreenThreshold, Long> mediaWaitingScreensTimeThresholds;
    private static SdkConfiguration.MotionCapture motionCapture;
    private static SdkConfiguration.ExperimentalFeatures.MotionExperiment motionExperiment;
    private static SdkConfiguration sdkConfiguration;
    private static SdkConfiguration.SelfieCapture selfieCapture;
    private static Map<WaitingScreenThreshold, Long> studioWaitingScreensTimeThresholds;

    /* JADX WARN: Multi-variable type inference failed */
    static {
        DefaultOnfidoRemoteConfig defaultOnfidoRemoteConfig = new DefaultOnfidoRemoteConfig();
        INSTANCE = defaultOnfidoRemoteConfig;
        motionExperiment = SdkConfiguration.ExperimentalFeatures.MotionExperiment.INSTANCE.getDISABLED();
        motionCapture = SdkConfiguration.MotionCapture.INSTANCE.getDEFAULT();
        livenessCapture = SdkConfiguration.LivenessCapture.INSTANCE.getDEFAULT();
        selfieCapture = SdkConfiguration.SelfieCapture.INSTANCE.getDEFAULT();
        documentCapture = SdkConfiguration.DocumentCapture.INSTANCE.getDEFAULT();
        isApplicantConsentRequired = true;
        isCameraXHighQualityModeEnabled = true;
        loggerConfiguration = new SdkConfiguration.LoggerConfiguration(false, (List) null, (List) null, 7, (DefaultConstructorMarker) null);
        blurValidationMaxRetry = 2;
        WaitingScreenThreshold.ShortWaiting1 shortWaiting1 = WaitingScreenThreshold.ShortWaiting1.INSTANCE;
        Duration.Companion companion = Duration.INSTANCE;
        DurationUnit durationUnit = DurationUnit.SECONDS;
        Pair pair = TuplesKt.to(shortWaiting1, Long.valueOf(Duration.m7440getInWholeMillisecondsimpl(DurationKt.toDuration(4, durationUnit))));
        WaitingScreenThreshold.ShortWaiting2 shortWaiting2 = WaitingScreenThreshold.ShortWaiting2.INSTANCE;
        Pair pair2 = TuplesKt.to(shortWaiting2, Long.valueOf(Duration.m7440getInWholeMillisecondsimpl(DurationKt.toDuration(11, durationUnit))));
        WaitingScreenThreshold.MediumWaiting1 mediumWaiting1 = WaitingScreenThreshold.MediumWaiting1.INSTANCE;
        Pair pair3 = TuplesKt.to(mediumWaiting1, Long.valueOf(Duration.m7440getInWholeMillisecondsimpl(DurationKt.toDuration(20, durationUnit))));
        WaitingScreenThreshold.MediumWaiting2 mediumWaiting2 = WaitingScreenThreshold.MediumWaiting2.INSTANCE;
        Pair pair4 = TuplesKt.to(mediumWaiting2, Long.valueOf(Duration.m7440getInWholeMillisecondsimpl(DurationKt.toDuration(25, durationUnit))));
        WaitingScreenThreshold.LongWaiting1 longWaiting1 = WaitingScreenThreshold.LongWaiting1.INSTANCE;
        studioWaitingScreensTimeThresholds = MapsKt.mapOf(pair, pair2, pair3, pair4, TuplesKt.to(longWaiting1, Long.valueOf(Duration.m7440getInWholeMillisecondsimpl(DurationKt.toDuration(60, durationUnit)))), TuplesKt.to(WaitingScreenThreshold.LongWaiting2.INSTANCE, Long.valueOf(Duration.m7440getInWholeMillisecondsimpl(DurationKt.toDuration(60, durationUnit)))));
        mediaWaitingScreensTimeThresholds = MapsKt.mapOf(TuplesKt.to(shortWaiting1, Long.valueOf(Duration.m7440getInWholeMillisecondsimpl(DurationKt.toDuration(4, durationUnit)))), TuplesKt.to(shortWaiting2, Long.valueOf(Duration.m7440getInWholeMillisecondsimpl(DurationKt.toDuration(11, durationUnit)))), TuplesKt.to(mediumWaiting1, Long.valueOf(Duration.m7440getInWholeMillisecondsimpl(DurationKt.toDuration(20, durationUnit)))), TuplesKt.to(mediumWaiting2, Long.valueOf(Duration.m7440getInWholeMillisecondsimpl(DurationKt.toDuration(25, durationUnit)))), TuplesKt.to(longWaiting1, Long.valueOf(Duration.m7440getInWholeMillisecondsimpl(DurationKt.toDuration(60, durationUnit)))));
        cameraXConfiguration = new SdkConfiguration.CameraXConfiguration(false, false, false, false, false, false, false, 127, (DefaultConstructorMarker) null);
        sdkConfiguration = SdkConfigHelper.INSTANCE.createSDKConfiguration(blurValidationMaxRetry, defaultOnfidoRemoteConfig, "local");
        isOnDeviceMRZExtractionEnabled = true;
        documentDetectionExperiment = new SdkConfiguration.ExperimentalFeatures.DocumentDetectionExperiment(false, false, (int) (0 == true ? 1 : 0), 0.0f, 15, (DefaultConstructorMarker) null);
    }

    private DefaultOnfidoRemoteConfig() {
    }

    private final void extractWaitingScreenThresholds(SdkConfiguration.ExperimentalFeatures experimentalFeatures) {
        List<Long> waitingTimes = experimentalFeatures.getWaitingScreens().getStudioTaskSubmission().getWaitingTimes();
        int size = waitingTimes.size();
        DefaultOnfidoRemoteConfig defaultOnfidoRemoteConfig = INSTANCE;
        if (size == defaultOnfidoRemoteConfig.getStudioWaitingScreensTimeThresholds().size()) {
            defaultOnfidoRemoteConfig.setStudioWaitingScreensTimeThresholds(MapsKt.mapOf(TuplesKt.to(WaitingScreenThreshold.ShortWaiting1.INSTANCE, waitingTimes.get(0)), TuplesKt.to(WaitingScreenThreshold.ShortWaiting2.INSTANCE, waitingTimes.get(1)), TuplesKt.to(WaitingScreenThreshold.MediumWaiting1.INSTANCE, waitingTimes.get(2)), TuplesKt.to(WaitingScreenThreshold.MediumWaiting2.INSTANCE, waitingTimes.get(3)), TuplesKt.to(WaitingScreenThreshold.LongWaiting1.INSTANCE, waitingTimes.get(4)), TuplesKt.to(WaitingScreenThreshold.LongWaiting2.INSTANCE, waitingTimes.get(5))));
        }
        List<Long> waitingTimes2 = experimentalFeatures.getWaitingScreens().getMediaUploadSubmission().getWaitingTimes();
        if (waitingTimes2.size() == defaultOnfidoRemoteConfig.getMediaWaitingScreensTimeThresholds().size()) {
            defaultOnfidoRemoteConfig.setMediaWaitingScreensTimeThresholds(MapsKt.mapOf(TuplesKt.to(WaitingScreenThreshold.ShortWaiting1.INSTANCE, waitingTimes2.get(0)), TuplesKt.to(WaitingScreenThreshold.ShortWaiting2.INSTANCE, waitingTimes2.get(1)), TuplesKt.to(WaitingScreenThreshold.MediumWaiting1.INSTANCE, waitingTimes2.get(2)), TuplesKt.to(WaitingScreenThreshold.MediumWaiting2.INSTANCE, waitingTimes2.get(3)), TuplesKt.to(WaitingScreenThreshold.LongWaiting1.INSTANCE, waitingTimes2.get(4))));
        }
    }

    private final void syncSdkConfiguration(SdkConfiguration sdkConfiguration2) {
        sdkConfiguration = sdkConfiguration2;
    }

    @Override // com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig
    public SdkConfiguration.CameraXConfiguration getCameraXConfiguration() {
        return cameraXConfiguration;
    }

    @Override // com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig
    public SdkConfiguration.DocumentCapture getDocumentCapture() {
        return documentCapture;
    }

    @Override // com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig
    public SdkConfiguration.ExperimentalFeatures.DocumentDetectionExperiment getDocumentDetectionExperiment() {
        return documentDetectionExperiment;
    }

    @Override // com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig
    public int getDocumentPostCaptureValidationTargetWidth() {
        return DEFAULT_POST_CAPTURE_VALIDATION_RESOLUTION;
    }

    @Override // com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig
    public boolean getEnableWebModuleBasedPoa() {
        return enableWebModuleBasedPoa;
    }

    @Override // com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig
    public SdkConfiguration.LivenessCapture getLivenessCapture() {
        return livenessCapture;
    }

    @Override // com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig
    public SdkConfiguration.LoggerConfiguration getLoggerConfiguration() {
        return loggerConfiguration;
    }

    @Override // com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig
    public Map<WaitingScreenThreshold, Long> getMediaWaitingScreensTimeThresholds() {
        return mediaWaitingScreensTimeThresholds;
    }

    @Override // com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig
    public SdkConfiguration.MotionCapture getMotionCapture() {
        return motionCapture;
    }

    @Override // com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig
    public SdkConfiguration.ExperimentalFeatures.MotionExperiment getMotionExperiment() {
        return motionExperiment;
    }

    @Override // com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig
    public SdkConfiguration getSdkConfiguration() {
        return sdkConfiguration;
    }

    @Override // com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig
    public SdkConfiguration.SelfieCapture getSelfieCapture() {
        return selfieCapture;
    }

    @Override // com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig
    public Map<WaitingScreenThreshold, Long> getStudioWaitingScreensTimeThresholds() {
        return studioWaitingScreensTimeThresholds;
    }

    @Override // com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig
    public boolean isApplicantConsentRequired() {
        return isApplicantConsentRequired;
    }

    @Override // com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig
    public boolean isAutoFlashModeEnabled() {
        return isAutoFlashModeEnabled;
    }

    @Override // com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig
    public boolean isCameraXEnabled() {
        return isCameraXEnabled;
    }

    @Override // com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig
    public boolean isCameraXHighQualityModeEnabled() {
        return isCameraXHighQualityModeEnabled;
    }

    @Override // com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig
    public boolean isCameraxStreamSharingEnabled() {
        return isCameraxStreamSharingEnabled;
    }

    @Override // com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig
    public boolean isCutoffImprovementsEnabled() {
        return isCutoffImprovementsEnabled;
    }

    @Override // com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig
    public boolean isDocumentCropDisabled() {
        return isDocumentCropDisabled;
    }

    @Override // com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig
    public boolean isDocumentSupportRulesEnabled() {
        return isDocumentSupportRulesEnabled;
    }

    @Override // com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig
    public boolean isEnvironmentIntegrityCheckEnabled() {
        return isEnvironmentIntegrityCheckEnabled;
    }

    @Override // com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig
    public boolean isFocusImprovementsEnabled() {
        return isFocusImprovementsEnabled;
    }

    @Override // com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig
    public boolean isFourByThreeEnabled() {
        return isFourByThreeEnabled;
    }

    @Override // com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig
    public boolean isGenericMrzValidatorEnabled() {
        return isGenericMrzValidatorEnabled;
    }

    @Override // com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig
    public boolean isImageQualityServiceEnabled() {
        return isImageQualityServiceEnabled;
    }

    @Override // com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig
    public boolean isIncreasedCompressionQualityEnabled() {
        return isIncreasedCompressionQualityEnabled;
    }

    @Override // com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig
    public boolean isInhouseAnalyticsEnabled() {
        return isInhouseAnalyticsEnabled;
    }

    @Override // com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig
    public boolean isMediaCallbackCroppingDisabled() {
        return isMediaCallbackCroppingDisabled;
    }

    @Override // com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig
    public boolean isMotionCameraXEnabled() {
        return isMotionCameraXEnabled;
    }

    @Override // com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig
    public boolean isMotionTensorFlowLiteEnabled() {
        return isMotionTensorFlowLiteEnabled;
    }

    @Override // com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig
    public boolean isMultiImageCaptureEnabled() {
        return isMultiImageCaptureEnabled;
    }

    @Override // com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig
    public boolean isOnDeviceMRZExtractionEnabled() {
        return isOnDeviceMRZExtractionEnabled;
    }

    @Override // com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig
    public boolean isPerformanceAnalyticsEnabled() {
        return isPerformanceAnalyticsEnabled;
    }

    @Override // com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig
    public boolean isRefactoredCaptureEnabled() {
        return isRefactoredCaptureEnabled;
    }

    @Override // com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig
    public boolean isResolutionImprovementsEnabled() {
        return isResolutionImprovementsEnabled;
    }

    @Override // com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig
    public boolean isStudioUserFlowExitEnabled() {
        return isStudioUserFlowExitEnabled;
    }

    public void setApplicantConsentRequired(boolean z) {
        isApplicantConsentRequired = z;
    }

    public void setAutoFlashModeEnabled(boolean z) {
        isAutoFlashModeEnabled = z;
    }

    public void setCameraXConfiguration(SdkConfiguration.CameraXConfiguration cameraXConfiguration2) {
        Intrinsics.checkNotNullParameter(cameraXConfiguration2, "<set-?>");
        cameraXConfiguration = cameraXConfiguration2;
    }

    public void setCameraXEnabled(boolean z) {
        isCameraXEnabled = z;
    }

    public void setCameraXHighQualityModeEnabled(boolean z) {
        isCameraXHighQualityModeEnabled = z;
    }

    public void setCameraxStreamSharingEnabled(boolean z) {
        isCameraxStreamSharingEnabled = z;
    }

    public void setCutoffImprovementsEnabled(boolean z) {
        isCutoffImprovementsEnabled = z;
    }

    public void setDocumentCapture(SdkConfiguration.DocumentCapture documentCapture2) {
        Intrinsics.checkNotNullParameter(documentCapture2, "<set-?>");
        documentCapture = documentCapture2;
    }

    public void setDocumentCropDisabled(boolean z) {
        isDocumentCropDisabled = z;
    }

    public void setDocumentDetectionExperiment(SdkConfiguration.ExperimentalFeatures.DocumentDetectionExperiment documentDetectionExperiment2) {
        Intrinsics.checkNotNullParameter(documentDetectionExperiment2, "<set-?>");
        documentDetectionExperiment = documentDetectionExperiment2;
    }

    public void setDocumentSupportRulesEnabled(boolean z) {
        isDocumentSupportRulesEnabled = z;
    }

    public void setEnableWebModuleBasedPoa(boolean z) {
        enableWebModuleBasedPoa = z;
    }

    public void setEnvironmentIntegrityCheckEnabled(boolean z) {
        isEnvironmentIntegrityCheckEnabled = z;
    }

    public void setFocusImprovementsEnabled(boolean z) {
        isFocusImprovementsEnabled = z;
    }

    public void setFourByThreeEnabled(boolean z) {
        isFourByThreeEnabled = z;
    }

    public void setGenericMrzValidatorEnabled(boolean z) {
        isGenericMrzValidatorEnabled = z;
    }

    public void setImageQualityServiceEnabled(boolean z) {
        isImageQualityServiceEnabled = z;
    }

    public void setIncreasedCompressionQualityEnabled(boolean z) {
        isIncreasedCompressionQualityEnabled = z;
    }

    public void setInhouseAnalyticsEnabled(boolean z) {
        isInhouseAnalyticsEnabled = z;
    }

    public void setLivenessCapture(SdkConfiguration.LivenessCapture livenessCapture2) {
        Intrinsics.checkNotNullParameter(livenessCapture2, "<set-?>");
        livenessCapture = livenessCapture2;
    }

    public void setLoggerConfiguration(SdkConfiguration.LoggerConfiguration loggerConfiguration2) {
        Intrinsics.checkNotNullParameter(loggerConfiguration2, "<set-?>");
        loggerConfiguration = loggerConfiguration2;
    }

    public void setMediaCallbackCroppingDisabled(boolean z) {
        isMediaCallbackCroppingDisabled = z;
    }

    public void setMediaWaitingScreensTimeThresholds(Map<WaitingScreenThreshold, Long> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        mediaWaitingScreensTimeThresholds = map;
    }

    public void setMotionCameraXEnabled(boolean z) {
        isMotionCameraXEnabled = z;
    }

    public void setMotionCapture(SdkConfiguration.MotionCapture motionCapture2) {
        Intrinsics.checkNotNullParameter(motionCapture2, "<set-?>");
        motionCapture = motionCapture2;
    }

    public void setMotionExperiment(SdkConfiguration.ExperimentalFeatures.MotionExperiment motionExperiment2) {
        Intrinsics.checkNotNullParameter(motionExperiment2, "<set-?>");
        motionExperiment = motionExperiment2;
    }

    public void setMotionTensorFlowLiteEnabled(boolean z) {
        isMotionTensorFlowLiteEnabled = z;
    }

    public void setMultiImageCaptureEnabled(boolean z) {
        isMultiImageCaptureEnabled = z;
    }

    public void setOnDeviceMRZExtractionEnabled(boolean z) {
        isOnDeviceMRZExtractionEnabled = z;
    }

    public void setPerformanceAnalyticsEnabled(boolean z) {
        isPerformanceAnalyticsEnabled = z;
    }

    @Override // com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig
    public void setRefactoredCaptureEnabled(boolean z) {
        isRefactoredCaptureEnabled = z;
    }

    public void setResolutionImprovementsEnabled(boolean z) {
        isResolutionImprovementsEnabled = z;
    }

    public void setSelfieCapture(SdkConfiguration.SelfieCapture selfieCapture2) {
        Intrinsics.checkNotNullParameter(selfieCapture2, "<set-?>");
        selfieCapture = selfieCapture2;
    }

    public void setStudioUserFlowExitEnabled(boolean z) {
        isStudioUserFlowExitEnabled = z;
    }

    public void setStudioWaitingScreensTimeThresholds(Map<WaitingScreenThreshold, Long> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        studioWaitingScreensTimeThresholds = map;
    }

    @Override // com.onfido.android.sdk.capture.internal.config.MutableOnfidoRemoteConfig
    public void updateFromSdkConfiguration(SdkConfiguration sdkConfiguration2) {
        SdkConfiguration.Validations.OnDevice onDevice;
        SdkConfiguration.Validations.OnDevice.ValidationType blur;
        Intrinsics.checkNotNullParameter(sdkConfiguration2, "sdkConfiguration");
        SdkConfiguration.Validations validations = sdkConfiguration2.getValidations();
        if (validations != null && (onDevice = validations.getOnDevice()) != null && (blur = onDevice.getBlur()) != null) {
            blurValidationMaxRetry = blur.getMaxTotalRetries();
        }
        SdkConfiguration.ExperimentalFeatures experimentalFeatures = sdkConfiguration2.getExperimentalFeatures();
        if (experimentalFeatures != null) {
            DefaultOnfidoRemoteConfig defaultOnfidoRemoteConfig = INSTANCE;
            defaultOnfidoRemoteConfig.setImageQualityServiceEnabled(experimentalFeatures.isEnableIqs());
            defaultOnfidoRemoteConfig.setMultiImageCaptureEnabled(experimentalFeatures.isEnableMultiFrameFeature());
            defaultOnfidoRemoteConfig.setMotionExperiment(experimentalFeatures.getMotionExperiment());
            defaultOnfidoRemoteConfig.setMotionTensorFlowLiteEnabled(experimentalFeatures.isMotionTensorFlowLiteEnabled());
            defaultOnfidoRemoteConfig.setMotionCameraXEnabled(experimentalFeatures.isMotionCameraXEnabled());
            defaultOnfidoRemoteConfig.setCameraXEnabled(experimentalFeatures.isEnableCameraX());
            defaultOnfidoRemoteConfig.setCameraxStreamSharingEnabled(experimentalFeatures.isCameraxStreamSharingEnabled());
            defaultOnfidoRemoteConfig.setAutoFlashModeEnabled(experimentalFeatures.isAutoFlashModeEnabled());
            defaultOnfidoRemoteConfig.setCameraXHighQualityModeEnabled(experimentalFeatures.isCameraXHighQualityModeEnabled());
            defaultOnfidoRemoteConfig.setResolutionImprovementsEnabled(experimentalFeatures.isResolutionImprovementsEnabled());
            defaultOnfidoRemoteConfig.setFocusImprovementsEnabled(experimentalFeatures.isFocusImprovementsEnabled());
            defaultOnfidoRemoteConfig.setCutoffImprovementsEnabled(experimentalFeatures.isCutoffImprovementsEnabled());
            defaultOnfidoRemoteConfig.setIncreasedCompressionQualityEnabled(experimentalFeatures.isIncreasedCompressionQualityEnabled());
            defaultOnfidoRemoteConfig.setDocumentCropDisabled(experimentalFeatures.isDocumentCropDisabled());
            defaultOnfidoRemoteConfig.setFourByThreeEnabled(experimentalFeatures.isFourByThreeEnabled());
            defaultOnfidoRemoteConfig.setGenericMrzValidatorEnabled(experimentalFeatures.isGenericMrzValidatorEnabled());
            defaultOnfidoRemoteConfig.setEnvironmentIntegrityCheckEnabled(experimentalFeatures.isEnvironmentIntegrityCheckEnabled());
            defaultOnfidoRemoteConfig.setStudioUserFlowExitEnabled(experimentalFeatures.isStudioUserFlowExitEnabled());
            defaultOnfidoRemoteConfig.setCameraXConfiguration(experimentalFeatures.getCameraXConfiguration());
            defaultOnfidoRemoteConfig.setMediaCallbackCroppingDisabled(experimentalFeatures.isMediaCallbackCroppingDisabled());
            defaultOnfidoRemoteConfig.setOnDeviceMRZExtractionEnabled(experimentalFeatures.isOnDeviceMRZExtractionEnabled());
            defaultOnfidoRemoteConfig.setDocumentDetectionExperiment(experimentalFeatures.getDocumentDetectionExperiment());
            defaultOnfidoRemoteConfig.extractWaitingScreenThresholds(experimentalFeatures);
        }
        SdkConfiguration.DocumentCapture documentCapture2 = sdkConfiguration2.getDocumentCapture();
        if (documentCapture2 == null) {
            documentCapture2 = getDocumentCapture();
        }
        setDocumentCapture(documentCapture2);
        SdkConfiguration.SelfieCapture selfieCapture2 = sdkConfiguration2.getSelfieCapture();
        if (selfieCapture2 == null) {
            selfieCapture2 = getSelfieCapture();
        }
        setSelfieCapture(selfieCapture2);
        SdkConfiguration.LivenessCapture livenessCapture2 = sdkConfiguration2.getLivenessCapture();
        if (livenessCapture2 == null) {
            livenessCapture2 = getLivenessCapture();
        }
        setLivenessCapture(livenessCapture2);
        SdkConfiguration.MotionCapture motionCapture2 = sdkConfiguration2.getMotionCapture();
        if (motionCapture2 == null) {
            motionCapture2 = getMotionCapture();
        }
        setMotionCapture(motionCapture2);
        SdkConfiguration.SdkFeatures sdkFeatures = sdkConfiguration2.getSdkFeatures();
        if (sdkFeatures != null) {
            DefaultOnfidoRemoteConfig defaultOnfidoRemoteConfig2 = INSTANCE;
            defaultOnfidoRemoteConfig2.setInhouseAnalyticsEnabled(sdkFeatures.isInhouseAnalyticsEnabled());
            defaultOnfidoRemoteConfig2.setPerformanceAnalyticsEnabled(sdkFeatures.isPerformanceAnalyticsEnabled());
            defaultOnfidoRemoteConfig2.setApplicantConsentRequired(sdkFeatures.isApplicantConsentRequired());
            defaultOnfidoRemoteConfig2.setLoggerConfiguration(sdkFeatures.getLoggerConfiguration());
            defaultOnfidoRemoteConfig2.setDocumentSupportRulesEnabled(sdkFeatures.isDocumentSupportRulesEnabled());
            defaultOnfidoRemoteConfig2.setRefactoredCaptureEnabled(sdkFeatures.isRefactoredCaptureEnabled());
            defaultOnfidoRemoteConfig2.setEnableWebModuleBasedPoa(sdkFeatures.getEnableWebModuleBasedPoa());
        }
        syncSdkConfiguration(sdkConfiguration2);
    }
}
