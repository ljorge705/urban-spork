package com.onfido.android.sdk.capture.ui.camera.document;

import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.flow.CaptureStepDataBundle;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.AnalyticsPropertyKeys;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.UiAlerts;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.CaptureTracker;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.NfcTracker;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.WaitingScreenTracker;
import com.onfido.android.sdk.capture.internal.performance.AggregatedPerformanceAnalytics;
import com.onfido.android.sdk.capture.internal.performance.trackers.PerformanceEvents;
import com.onfido.android.sdk.capture.ui.CaptureType;
import com.onfido.android.sdk.capture.upload.ErrorType;
import com.onfido.android.sdk.capture.utils.CountryCode;
import com.onfido.api.client.data.DocSide;
import com.onfido.api.client.data.DocumentFeatures;
import com.onfido.javax.inject.Inject;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\n\b\u0000\u0018\u00002\u00020\u0001B'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ'\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0000¢\u0006\u0002\b\u0012J \u0010\u0013\u001a\u00020\f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eJ\u001e\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0016J\u0010\u0010\u001a\u001a\u00020\f2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cJ\u000e\u0010\u001d\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\u0018J:\u0010\u001f\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010 \u001a\u00020\u000e2\u0006\u0010!\u001a\u00020\u00162\u0012\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020$0#J\u0018\u0010%\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cJ<\u0010&\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010 \u001a\u00020\u000e2\u0012\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020$0#2\b\u0010'\u001a\u0004\u0018\u00010\u0011J2\u0010(\u001a\u00020\f2\u0006\u0010)\u001a\u00020\u00162\u0006\u0010*\u001a\u00020\u00162\b\u0010+\u001a\u0004\u0018\u00010,2\b\u0010-\u001a\u0004\u0018\u00010.2\u0006\u0010/\u001a\u000200J\u0006\u00101\u001a\u00020\fJ&\u00102\u001a\u00020\f2\u0006\u00103\u001a\u0002042\u0006\u00105\u001a\u0002042\u0006\u00106\u001a\u00020\u000e2\u0006\u00107\u001a\u00020\u000eJ\u0006\u00108\u001a\u00020\fJ\u0010\u00109\u001a\u00020\f2\b\u0010:\u001a\u0004\u0018\u00010;J\u0006\u0010<\u001a\u00020\fJ>\u0010=\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010 \u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u00162\u0006\u0010!\u001a\u00020\u00162\u0006\u0010>\u001a\u00020\u00162\u0006\u0010?\u001a\u00020\u0016J\u0006\u0010@\u001a\u00020\fJ\u0006\u0010A\u001a\u00020\fJ\u0016\u0010B\u001a\u00020\f2\u0006\u0010C\u001a\u00020;2\u0006\u0010D\u001a\u00020;R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006E"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/document/DocumentCaptureTracker;", "", "captureTracker", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/CaptureTracker;", "waitingScreenTracker", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/WaitingScreenTracker;", "nfcTracker", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/NfcTracker;", "performanceAnalytics", "Lcom/onfido/android/sdk/capture/internal/performance/AggregatedPerformanceAnalytics;", "(Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/CaptureTracker;Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/WaitingScreenTracker;Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/NfcTracker;Lcom/onfido/android/sdk/capture/internal/performance/AggregatedPerformanceAnalytics;)V", "trackCaptureBackButtonClicked", "", "takenPhotoCount", "", "rejectionCount", "errorType", "Lcom/onfido/android/sdk/capture/upload/ErrorType;", "trackCaptureBackButtonClicked$onfido_capture_sdk_core_release", "trackCaptureButtonClicked", "trackDocumentCapture", "isPortrait", "", "documentData", "Lcom/onfido/android/sdk/capture/flow/CaptureStepDataBundle;", "isAutoCaptureEnabled", "trackDocumentCaptureError", "documentSide", "Lcom/onfido/api/client/data/DocSide;", "trackDocumentCaptureFlowCompleted", "captureStepDataBundle", "trackDocumentConfirmation", "maxRetryCount", "isAutoCaptured", AnalyticsPropertyKeys.WARNINGS, "", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/UiAlerts$UiAlertType;", "trackDocumentConfirmationError", "trackDocumentConfirmationWarning", "warningShown", "trackDocumentNfcSupported", "isNfcSupported", "hasNfcKey", "documentType", "Lcom/onfido/android/sdk/capture/DocumentType;", RemoteConfigConstants.RequestFieldKey.COUNTRY_CODE, "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "documentFeatures", "Lcom/onfido/api/client/data/DocumentFeatures;", "trackDocumentUploadCompleted", "trackDocumentValidMRZExtracted", "extractionDuration", "", "frameProcessDuration", "emittedFramesCount", "processedFramesCount", "trackEndTracingPerformance", "trackPropertiesError", "error", "", "trackStartTracingPerformance", "trackUploadStarted", "hasMrzLines", "isNfcFeatureEnabled", "trackVideoCaptureTimeout", "trackVideoTimeoutRetryButtonClicked", "trackWaitingScreenCompletion", "taskType", "reason", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DocumentCaptureTracker {
    private final CaptureTracker captureTracker;
    private final NfcTracker nfcTracker;
    private final AggregatedPerformanceAnalytics performanceAnalytics;
    private final WaitingScreenTracker waitingScreenTracker;

    @Inject
    public DocumentCaptureTracker(CaptureTracker captureTracker, WaitingScreenTracker waitingScreenTracker, NfcTracker nfcTracker, AggregatedPerformanceAnalytics performanceAnalytics) {
        Intrinsics.checkNotNullParameter(captureTracker, "captureTracker");
        Intrinsics.checkNotNullParameter(waitingScreenTracker, "waitingScreenTracker");
        Intrinsics.checkNotNullParameter(nfcTracker, "nfcTracker");
        Intrinsics.checkNotNullParameter(performanceAnalytics, "performanceAnalytics");
        this.captureTracker = captureTracker;
        this.waitingScreenTracker = waitingScreenTracker;
        this.nfcTracker = nfcTracker;
        this.performanceAnalytics = performanceAnalytics;
    }

    public final void trackCaptureBackButtonClicked$onfido_capture_sdk_core_release(int takenPhotoCount, int rejectionCount, ErrorType errorType) {
        this.captureTracker.trackBackButtonClicked$onfido_capture_sdk_core_release(CaptureType.DOCUMENT, errorType, takenPhotoCount, rejectionCount);
    }

    public final void trackCaptureButtonClicked(ErrorType errorType, int takenPhotoCount, int rejectionCount) {
        this.captureTracker.trackCaptureButtonClicked$onfido_capture_sdk_core_release(CaptureType.DOCUMENT, errorType, takenPhotoCount, rejectionCount);
    }

    public final void trackDocumentCapture(boolean isPortrait, CaptureStepDataBundle documentData, boolean isAutoCaptureEnabled) {
        Intrinsics.checkNotNullParameter(documentData, "documentData");
        this.captureTracker.trackDocumentCapture(isPortrait, documentData, isAutoCaptureEnabled);
    }

    public final void trackDocumentCaptureError(DocSide documentSide) {
        this.captureTracker.trackDocumentCaptureError$onfido_capture_sdk_core_release(documentSide);
    }

    public final void trackDocumentCaptureFlowCompleted(CaptureStepDataBundle captureStepDataBundle) {
        Intrinsics.checkNotNullParameter(captureStepDataBundle, "captureStepDataBundle");
        this.captureTracker.trackDocumentCaptureFlowCompleted$onfido_capture_sdk_core_release(captureStepDataBundle);
    }

    public final void trackDocumentConfirmation(CaptureStepDataBundle documentData, int takenPhotoCount, int maxRetryCount, boolean isAutoCaptured, Map<ErrorType, ? extends UiAlerts.UiAlertType> warnings) {
        Intrinsics.checkNotNullParameter(documentData, "documentData");
        Intrinsics.checkNotNullParameter(warnings, "warnings");
        this.captureTracker.trackDocumentConfirmation(documentData, takenPhotoCount, maxRetryCount, isAutoCaptured, warnings);
    }

    public final void trackDocumentConfirmationError(ErrorType errorType, DocSide documentSide) {
        Intrinsics.checkNotNullParameter(errorType, "errorType");
        this.captureTracker.trackDocumentConfirmationError$onfido_capture_sdk_core_release(errorType, documentSide);
    }

    public final void trackDocumentConfirmationWarning(CaptureStepDataBundle documentData, int takenPhotoCount, int maxRetryCount, Map<ErrorType, ? extends UiAlerts.UiAlertType> warnings, ErrorType warningShown) {
        Intrinsics.checkNotNullParameter(documentData, "documentData");
        Intrinsics.checkNotNullParameter(warnings, "warnings");
        this.captureTracker.trackDocumentConfirmationWarning$onfido_capture_sdk_core_release(documentData, takenPhotoCount, maxRetryCount, warnings, warningShown);
    }

    public final void trackDocumentNfcSupported(boolean isNfcSupported, boolean hasNfcKey, DocumentType documentType, CountryCode countryCode, DocumentFeatures documentFeatures) {
        Intrinsics.checkNotNullParameter(documentFeatures, "documentFeatures");
        this.nfcTracker.trackDocumentNfcSupported$onfido_capture_sdk_core_release(isNfcSupported, hasNfcKey, documentType, countryCode, documentFeatures);
    }

    public final void trackDocumentUploadCompleted() {
        this.captureTracker.trackDocumentUploadCompleted();
    }

    public final void trackDocumentValidMRZExtracted(long extractionDuration, long frameProcessDuration, int emittedFramesCount, int processedFramesCount) {
        this.captureTracker.trackDocumentValidMRZExtracted$onfido_capture_sdk_core_release(extractionDuration, frameProcessDuration, emittedFramesCount, processedFramesCount);
    }

    public final void trackEndTracingPerformance() {
        this.performanceAnalytics.trackStart(new PerformanceEvents.TraceEnd("document_capture"));
    }

    public final void trackPropertiesError(String error) {
        this.nfcTracker.trackPropertiesError$onfido_capture_sdk_core_release(error);
    }

    public final void trackStartTracingPerformance() {
        this.performanceAnalytics.trackStart(new PerformanceEvents.TraceStart("document_capture"));
    }

    public final void trackUploadStarted(CaptureStepDataBundle documentData, int takenPhotoCount, int maxRetryCount, boolean isAutoCaptureEnabled, boolean isAutoCaptured, boolean hasMrzLines, boolean isNfcFeatureEnabled) {
        Intrinsics.checkNotNullParameter(documentData, "documentData");
        this.captureTracker.trackUploadStarted(documentData, takenPhotoCount, maxRetryCount, isAutoCaptureEnabled, isAutoCaptured, hasMrzLines, isNfcFeatureEnabled);
    }

    public final void trackVideoCaptureTimeout() {
        this.captureTracker.trackVideoCaptureTimeout$onfido_capture_sdk_core_release();
    }

    public final void trackVideoTimeoutRetryButtonClicked() {
        this.captureTracker.trackVideoTimeoutRetryButtonClicked$onfido_capture_sdk_core_release();
    }

    public final void trackWaitingScreenCompletion(String taskType, String reason) {
        Intrinsics.checkNotNullParameter(taskType, "taskType");
        Intrinsics.checkNotNullParameter(reason, "reason");
        this.waitingScreenTracker.trackWaitingScreenCompletion(taskType, reason);
    }
}
