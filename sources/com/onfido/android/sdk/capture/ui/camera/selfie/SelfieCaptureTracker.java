package com.onfido.android.sdk.capture.ui.camera.selfie;

import com.onfido.android.sdk.capture.flow.CaptureStepDataBundle;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.CaptureTracker;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.LivenessTracker;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.WaitingScreenTracker;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.ui.CaptureType;
import com.onfido.android.sdk.capture.upload.ErrorType;
import com.onfido.javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ6\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u0013J \u0010\u0015\u001a\u00020\f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013J\u000e\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u0018J \u0010\u0019\u001a\u00020\f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013J \u0010\u001a\u001a\u00020\f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013J(\u0010\u001b\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\u001d2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013J(\u0010\u001e\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\u001d2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013J\u0016\u0010\u001f\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0012\u001a\u00020\u0013J\u0016\u0010 \u001a\u00020\f2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\"R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/selfie/SelfieCaptureTracker;", "", "captureTracker", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/CaptureTracker;", "livenessTracker", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/LivenessTracker;", "waitingScreenTracker", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/WaitingScreenTracker;", "onfidoRemoteConfig", "Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;", "(Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/CaptureTracker;Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/LivenessTracker;Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/WaitingScreenTracker;Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;)V", "trackCapture", "", "isConfirmation", "", "isPortrait", "errorType", "Lcom/onfido/android/sdk/capture/upload/ErrorType;", "takenPhotoCount", "", "rejectionCount", "trackCaptureButtonClicked", "trackCaptureFlowCompleted", "documentData", "Lcom/onfido/android/sdk/capture/flow/CaptureStepDataBundle;", "trackConfirmationRetakeButtonClicked", "trackConfirmationUploadButtonClicked", "trackSelfieConfirmationUploadStatus", "duration", "", "trackSelfieUploadCompleted", "trackUploadStarted", "trackWaitingScreenCompletion", "taskType", "", "reason", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class SelfieCaptureTracker {
    private final CaptureTracker captureTracker;
    private final LivenessTracker livenessTracker;
    private final OnfidoRemoteConfig onfidoRemoteConfig;
    private final WaitingScreenTracker waitingScreenTracker;

    @Inject
    public SelfieCaptureTracker(CaptureTracker captureTracker, LivenessTracker livenessTracker, WaitingScreenTracker waitingScreenTracker, OnfidoRemoteConfig onfidoRemoteConfig) {
        Intrinsics.checkNotNullParameter(captureTracker, "captureTracker");
        Intrinsics.checkNotNullParameter(livenessTracker, "livenessTracker");
        Intrinsics.checkNotNullParameter(waitingScreenTracker, "waitingScreenTracker");
        Intrinsics.checkNotNullParameter(onfidoRemoteConfig, "onfidoRemoteConfig");
        this.captureTracker = captureTracker;
        this.livenessTracker = livenessTracker;
        this.waitingScreenTracker = waitingScreenTracker;
        this.onfidoRemoteConfig = onfidoRemoteConfig;
    }

    public static /* synthetic */ void trackCapture$default(SelfieCaptureTracker selfieCaptureTracker, boolean z, boolean z2, ErrorType errorType, int i, int i2, int i3, Object obj) {
        if ((i3 & 4) != 0) {
            errorType = null;
        }
        selfieCaptureTracker.trackCapture(z, z2, errorType, (i3 & 8) != 0 ? 0 : i, (i3 & 16) != 0 ? 0 : i2);
    }

    public final void trackCapture(boolean isConfirmation, boolean isPortrait, ErrorType errorType, int takenPhotoCount, int rejectionCount) {
        this.livenessTracker.trackFaceCapture$onfido_capture_sdk_core_release(isConfirmation, isPortrait, CaptureType.FACE, errorType, takenPhotoCount, rejectionCount);
    }

    public final void trackCaptureButtonClicked(ErrorType errorType, int takenPhotoCount, int rejectionCount) {
        this.captureTracker.trackCaptureButtonClicked$onfido_capture_sdk_core_release(CaptureType.FACE, errorType, takenPhotoCount + 1, rejectionCount);
    }

    public final void trackCaptureFlowCompleted(CaptureStepDataBundle documentData) {
        Intrinsics.checkNotNullParameter(documentData, "documentData");
        this.captureTracker.trackDocumentCaptureFlowCompleted$onfido_capture_sdk_core_release(documentData);
    }

    public final void trackConfirmationRetakeButtonClicked(ErrorType errorType, int takenPhotoCount, int rejectionCount) {
        this.livenessTracker.trackFaceConfirmationRetakeButtonClicked$onfido_capture_sdk_core_release(CaptureType.FACE, errorType, takenPhotoCount, rejectionCount);
    }

    public final void trackConfirmationUploadButtonClicked(ErrorType errorType, int takenPhotoCount, int rejectionCount) {
        this.livenessTracker.trackFaceConfirmationUploadButtonClicked$onfido_capture_sdk_core_release(CaptureType.FACE, errorType, takenPhotoCount, rejectionCount);
    }

    public final void trackSelfieConfirmationUploadStatus(long duration, ErrorType errorType, int takenPhotoCount, int rejectionCount) {
        this.captureTracker.trackFaceSelfieConfirmationUploadStatus$onfido_capture_sdk_core_release(duration, errorType, takenPhotoCount, rejectionCount);
    }

    public final void trackSelfieUploadCompleted(long duration, ErrorType errorType, int takenPhotoCount, int rejectionCount) {
        this.captureTracker.trackFaceSelfieUploadCompleted$onfido_capture_sdk_core_release(duration, errorType, takenPhotoCount, rejectionCount);
    }

    public final void trackUploadStarted(CaptureStepDataBundle documentData, int takenPhotoCount) {
        Intrinsics.checkNotNullParameter(documentData, "documentData");
        this.captureTracker.trackUploadStarted(documentData, takenPhotoCount, this.onfidoRemoteConfig.getDocumentCapture().getMaxTotalRetries(), false, false, true, false);
    }

    public final void trackWaitingScreenCompletion(String taskType, String reason) {
        Intrinsics.checkNotNullParameter(taskType, "taskType");
        Intrinsics.checkNotNullParameter(reason, "reason");
        this.waitingScreenTracker.trackWaitingScreenCompletion(taskType, reason);
    }
}
