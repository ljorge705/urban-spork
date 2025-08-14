package com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers;

import com.clevertap.android.sdk.Constants;
import com.google.mlkit.common.sdkinternal.OptionalModuleUtils;
import com.onfido.android.sdk.capture.flow.CaptureStepDataBundle;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.OnfidoAnalytics;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.AnalyticsEvent;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.AnalyticsPropertyKeys;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.UiAlerts;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.CaptureEvents;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.LivenessEvents;
import com.onfido.android.sdk.capture.internal.nfc.NfcInteractor;
import com.onfido.android.sdk.capture.internal.nfc.NfcNonCapabilityReason;
import com.onfido.android.sdk.capture.ui.CaptureType;
import com.onfido.android.sdk.capture.ui.camera.liveness.challenges.LivenessChallengeType;
import com.onfido.android.sdk.capture.upload.ErrorType;
import com.onfido.android.sdk.capture.utils.mlmodel.OnfidoMlModels;
import com.onfido.api.client.data.DocSide;
import com.onfido.javax.inject.Inject;
import com.onfido.reactnative.sdk.ReactNativeBridgeUtiles;
import io.sentry.protocol.Device;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\b\u0010\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0012J/\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0010¢\u0006\u0002\b\u0013J/\u0010\u0014\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0010¢\u0006\u0002\b\u0015J \u0010\u0016\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\bH\u0016J\u0017\u0010\u001b\u001a\u00020\f2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0010¢\u0006\u0002\b\u001eJ\u0015\u0010\u001f\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u0019H\u0010¢\u0006\u0002\b J<\u0010!\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\"\u001a\u00020\u00112\u0006\u0010#\u001a\u00020\b2\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020&0%H\u0016J\u001f\u0010'\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\n2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0010¢\u0006\u0002\b(JC\u0010)\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\"\u001a\u00020\u00112\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020&0%2\b\u0010*\u001a\u0004\u0018\u00010\nH\u0010¢\u0006\u0002\b+J\b\u0010,\u001a\u00020\fH\u0016J-\u0010-\u001a\u00020\f2\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020/2\u0006\u00101\u001a\u00020\u00112\u0006\u00102\u001a\u00020\u0011H\u0010¢\u0006\u0002\b3J/\u00104\u001a\u00020\f2\u0006\u00105\u001a\u00020/2\b\u0010\u000f\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0010¢\u0006\u0002\b6J/\u00107\u001a\u00020\f2\u0006\u00105\u001a\u00020/2\b\u0010\u000f\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0010¢\u0006\u0002\b8J\u001d\u00109\u001a\u00020\f2\u0006\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u00020/H\u0010¢\u0006\u0002\b=J@\u0010>\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\"\u001a\u00020\u00112\u0006\u0010\u001a\u001a\u00020\b2\u0006\u0010#\u001a\u00020\b2\u0006\u0010?\u001a\u00020\b2\u0006\u0010@\u001a\u00020\bH\u0016J\r\u0010A\u001a\u00020\fH\u0010¢\u0006\u0002\bBJ\u0015\u0010C\u001a\u00020\f2\u0006\u0010D\u001a\u00020EH\u0010¢\u0006\u0002\bFJ\u0015\u0010G\u001a\u00020\f2\u0006\u0010D\u001a\u00020EH\u0010¢\u0006\u0002\bHJ\r\u0010I\u001a\u00020\fH\u0010¢\u0006\u0002\bJJ\u0015\u0010K\u001a\u00020\f2\u0006\u00105\u001a\u00020/H\u0010¢\u0006\u0002\bLJ\r\u0010M\u001a\u00020\fH\u0010¢\u0006\u0002\bNJ\u0015\u0010O\u001a\u00020\f2\u0006\u00105\u001a\u00020/H\u0010¢\u0006\u0002\bPJ\r\u0010Q\u001a\u00020\fH\u0010¢\u0006\u0002\bRJ\u000e\u0010S\u001a\u0004\u0018\u00010T*\u00020\u0005H\u0012R\u000e\u0010\u0004\u001a\u00020\u0005X\u0092\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0092\u0004¢\u0006\u0002\n\u0000¨\u0006U"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/CaptureTracker;", "", "onfidoAnalytics", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/OnfidoAnalytics;", "nfcInteractor", "Lcom/onfido/android/sdk/capture/internal/nfc/NfcInteractor;", "(Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/OnfidoAnalytics;Lcom/onfido/android/sdk/capture/internal/nfc/NfcInteractor;)V", "isOdpError", "", Constants.KEY_KEY, "Lcom/onfido/android/sdk/capture/upload/ErrorType;", "trackBackButtonClicked", "", ReactNativeBridgeUtiles.KEY_CAPTURE_TYPE, "Lcom/onfido/android/sdk/capture/ui/CaptureType;", "errorType", "takenPhotoCount", "", "rejectionCount", "trackBackButtonClicked$onfido_capture_sdk_core_release", "trackCaptureButtonClicked", "trackCaptureButtonClicked$onfido_capture_sdk_core_release", "trackDocumentCapture", "isPortrait", "documentData", "Lcom/onfido/android/sdk/capture/flow/CaptureStepDataBundle;", "isAutoCaptureEnabled", "trackDocumentCaptureError", "documentSide", "Lcom/onfido/api/client/data/DocSide;", "trackDocumentCaptureError$onfido_capture_sdk_core_release", "trackDocumentCaptureFlowCompleted", "trackDocumentCaptureFlowCompleted$onfido_capture_sdk_core_release", "trackDocumentConfirmation", "maxRetryCount", "isAutoCaptured", AnalyticsPropertyKeys.WARNINGS, "", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/UiAlerts$UiAlertType;", "trackDocumentConfirmationError", "trackDocumentConfirmationError$onfido_capture_sdk_core_release", "trackDocumentConfirmationWarning", "warningShown", "trackDocumentConfirmationWarning$onfido_capture_sdk_core_release", "trackDocumentUploadCompleted", "trackDocumentValidMRZExtracted", "extractionDuration", "", "frameProcessDuration", "emittedFramesCount", "processedFramesCount", "trackDocumentValidMRZExtracted$onfido_capture_sdk_core_release", "trackFaceSelfieConfirmationUploadStatus", "duration", "trackFaceSelfieConfirmationUploadStatus$onfido_capture_sdk_core_release", "trackFaceSelfieUploadCompleted", "trackFaceSelfieUploadCompleted$onfido_capture_sdk_core_release", "trackOnfidoMlModelReady", Device.JsonKeys.MODEL, "Lcom/onfido/android/sdk/capture/utils/mlmodel/OnfidoMlModels;", "preparationDuration", "trackOnfidoMlModelReady$onfido_capture_sdk_core_release", "trackUploadStarted", "hasMrzLines", "isNfcFeatureEnabled", "trackVideoAutoRecordingStarted", "trackVideoAutoRecordingStarted$onfido_capture_sdk_core_release", "trackVideoCaptureFirstChallenge", "challengeType", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessChallengeType;", "trackVideoCaptureFirstChallenge$onfido_capture_sdk_core_release", "trackVideoCaptureSecondChallenge", "trackVideoCaptureSecondChallenge$onfido_capture_sdk_core_release", "trackVideoCaptureTimeout", "trackVideoCaptureTimeout$onfido_capture_sdk_core_release", "trackVideoFinishButtonClicked", "trackVideoFinishButtonClicked$onfido_capture_sdk_core_release", "trackVideoManualRecordingStarted", "trackVideoManualRecordingStarted$onfido_capture_sdk_core_release", "trackVideoNextButtonClicked", "trackVideoNextButtonClicked$onfido_capture_sdk_core_release", "trackVideoTimeoutRetryButtonClicked", "trackVideoTimeoutRetryButtonClicked$onfido_capture_sdk_core_release", "getNfcNonCapabilityReason", "Lcom/onfido/android/sdk/capture/internal/nfc/NfcNonCapabilityReason;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class CaptureTracker {
    private final NfcInteractor nfcInteractor;
    private final OnfidoAnalytics onfidoAnalytics;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[CaptureType.values().length];
            try {
                iArr[CaptureType.DOCUMENT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[CaptureType.FACE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[CaptureType.VIDEO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Inject
    public CaptureTracker(OnfidoAnalytics onfidoAnalytics, NfcInteractor nfcInteractor) {
        Intrinsics.checkNotNullParameter(onfidoAnalytics, "onfidoAnalytics");
        Intrinsics.checkNotNullParameter(nfcInteractor, "nfcInteractor");
        this.onfidoAnalytics = onfidoAnalytics;
        this.nfcInteractor = nfcInteractor;
    }

    private NfcNonCapabilityReason getNfcNonCapabilityReason(NfcInteractor nfcInteractor) {
        if (!nfcInteractor.isNfcSupported()) {
            return NfcNonCapabilityReason.DEVICE_WITHOUT_NFC_HARDWARE;
        }
        if (nfcInteractor.isNfcEnabled()) {
            return null;
        }
        return NfcNonCapabilityReason.DEVICE_NFC_SETTINGS_DISABLED;
    }

    private boolean isOdpError(ErrorType key) {
        return SetsKt.setOf((Object[]) new ErrorType[]{ErrorType.PhotoOfScreen.INSTANCE, ErrorType.Screenshot.INSTANCE, ErrorType.Photocopy.INSTANCE, ErrorType.Scan.INSTANCE}).contains(key);
    }

    public void trackBackButtonClicked$onfido_capture_sdk_core_release(CaptureType captureType, ErrorType errorType, int takenPhotoCount, int rejectionCount) {
        Intrinsics.checkNotNullParameter(captureType, "captureType");
        int i = WhenMappings.$EnumSwitchMapping$0[captureType.ordinal()];
        if (i == 2) {
            this.onfidoAnalytics.track(new LivenessEvents.FaceSelfieCaptureBackButtonClicked(errorType, takenPhotoCount, rejectionCount));
        } else {
            if (i != 3) {
                return;
            }
            this.onfidoAnalytics.track(new LivenessEvents.FaceVideoCaptureBackButtonClicked());
        }
    }

    public void trackCaptureButtonClicked$onfido_capture_sdk_core_release(CaptureType captureType, ErrorType errorType, int takenPhotoCount, int rejectionCount) {
        Intrinsics.checkNotNullParameter(captureType, "captureType");
        if (captureType == CaptureType.FACE) {
            this.onfidoAnalytics.track(new LivenessEvents.FaceSelfieCaptureCaptureButtonClicked(errorType, takenPhotoCount, rejectionCount));
        }
    }

    public void trackDocumentCapture(boolean isPortrait, CaptureStepDataBundle documentData, boolean isAutoCaptureEnabled) {
        Intrinsics.checkNotNullParameter(documentData, "documentData");
        this.onfidoAnalytics.track(new CaptureEvents.DocumentCapture(documentData, isPortrait, isAutoCaptureEnabled));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void trackDocumentCaptureError$onfido_capture_sdk_core_release(DocSide documentSide) {
        this.onfidoAnalytics.track(new CaptureEvents.DocumentCaptureError(documentSide, null, 2, 0 == true ? 1 : 0));
    }

    public void trackDocumentCaptureFlowCompleted$onfido_capture_sdk_core_release(CaptureStepDataBundle documentData) {
        Intrinsics.checkNotNullParameter(documentData, "documentData");
        this.onfidoAnalytics.track(new CaptureEvents.DocumentCaptureFlowCompleted(documentData));
    }

    public void trackDocumentConfirmation(CaptureStepDataBundle documentData, int takenPhotoCount, int maxRetryCount, boolean isAutoCaptured, Map<ErrorType, ? extends UiAlerts.UiAlertType> warnings) {
        Intrinsics.checkNotNullParameter(documentData, "documentData");
        Intrinsics.checkNotNullParameter(warnings, "warnings");
        LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(warnings.size()));
        Iterator<T> it = warnings.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            linkedHashMap.put(entry.getKey() instanceof ErrorType.Generic ? "mrz" : ((ErrorType) entry.getKey()).getKey(), entry.getValue());
        }
        this.onfidoAnalytics.track(new CaptureEvents.DocumentConfirmation(documentData, takenPhotoCount, maxRetryCount, linkedHashMap, false, isAutoCaptured));
    }

    public void trackDocumentConfirmationError$onfido_capture_sdk_core_release(ErrorType errorType, DocSide documentSide) {
        Intrinsics.checkNotNullParameter(errorType, "errorType");
        this.onfidoAnalytics.track(new CaptureEvents.DocumentConfirmationError(errorType, documentSide));
    }

    public void trackDocumentConfirmationWarning$onfido_capture_sdk_core_release(CaptureStepDataBundle documentData, int takenPhotoCount, int maxRetryCount, Map<ErrorType, ? extends UiAlerts.UiAlertType> warnings, ErrorType warningShown) {
        Intrinsics.checkNotNullParameter(documentData, "documentData");
        Intrinsics.checkNotNullParameter(warnings, "warnings");
        LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(warnings.size()));
        Iterator<T> it = warnings.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            linkedHashMap.put(((entry.getKey() instanceof ErrorType.NoFace) || (entry.getKey() instanceof ErrorType.MultipleFaces)) ? OptionalModuleUtils.FACE : isOdpError((ErrorType) entry.getKey()) ? "odp" : ((ErrorType) entry.getKey()).getKey(), entry.getValue());
        }
        this.onfidoAnalytics.track(new CaptureEvents.DocumentConfirmationWarning(documentData, takenPhotoCount, maxRetryCount, linkedHashMap, warningShown != null ? warningShown.getKey() : null, true));
    }

    public void trackDocumentUploadCompleted() {
        this.onfidoAnalytics.track(new CaptureEvents.DocumentCaptureUploadCompleted());
    }

    public void trackDocumentValidMRZExtracted$onfido_capture_sdk_core_release(long extractionDuration, long frameProcessDuration, int emittedFramesCount, int processedFramesCount) {
        this.onfidoAnalytics.track(new CaptureEvents.DocumentValidMRZExtracted(extractionDuration, frameProcessDuration, emittedFramesCount, processedFramesCount));
    }

    public void trackFaceSelfieConfirmationUploadStatus$onfido_capture_sdk_core_release(long duration, ErrorType errorType, int takenPhotoCount, int rejectionCount) {
        this.onfidoAnalytics.track(new LivenessEvents.FaceSelfieConfirmationUploadStatus(duration, errorType, takenPhotoCount, rejectionCount));
    }

    public void trackFaceSelfieUploadCompleted$onfido_capture_sdk_core_release(long duration, ErrorType errorType, int takenPhotoCount, int rejectionCount) {
        this.onfidoAnalytics.track(new LivenessEvents.FaceSelfieUploadCompleted(duration, errorType, takenPhotoCount, rejectionCount));
    }

    public void trackOnfidoMlModelReady$onfido_capture_sdk_core_release(OnfidoMlModels model, long preparationDuration) {
        Intrinsics.checkNotNullParameter(model, "model");
        this.onfidoAnalytics.track(new CaptureEvents.OnfidoMlModelReady(model, preparationDuration));
    }

    public void trackUploadStarted(CaptureStepDataBundle documentData, int takenPhotoCount, int maxRetryCount, boolean isAutoCaptureEnabled, boolean isAutoCaptured, boolean hasMrzLines, boolean isNfcFeatureEnabled) {
        OnfidoAnalytics onfidoAnalytics;
        AnalyticsEvent analyticsEvent;
        Intrinsics.checkNotNullParameter(documentData, "documentData");
        int i = WhenMappings.$EnumSwitchMapping$0[documentData.getCaptureType().ordinal()];
        if (i == 1) {
            this.onfidoAnalytics.track(new CaptureEvents.DocumentCaptureUploadStarted(documentData, takenPhotoCount, maxRetryCount, isAutoCaptureEnabled, isAutoCaptured, hasMrzLines, this.nfcInteractor.isNfcSupported(), isNfcFeatureEnabled, getNfcNonCapabilityReason(this.nfcInteractor)));
            return;
        }
        if (i == 2) {
            onfidoAnalytics = this.onfidoAnalytics;
            analyticsEvent = LivenessEvents.FaceSelfieUploadStarted.INSTANCE;
        } else {
            if (i != 3) {
                return;
            }
            onfidoAnalytics = this.onfidoAnalytics;
            analyticsEvent = LivenessEvents.FaceVideoUploadStarted.INSTANCE;
        }
        onfidoAnalytics.track(analyticsEvent);
    }

    public void trackVideoAutoRecordingStarted$onfido_capture_sdk_core_release() {
        this.onfidoAnalytics.track(new LivenessEvents.FaceVideoAutoRecordingStarted());
    }

    public void trackVideoCaptureFirstChallenge$onfido_capture_sdk_core_release(LivenessChallengeType challengeType) {
        Intrinsics.checkNotNullParameter(challengeType, "challengeType");
        this.onfidoAnalytics.track(new LivenessEvents.FaceVideoCaptureFirstChallenge(challengeType));
    }

    public void trackVideoCaptureSecondChallenge$onfido_capture_sdk_core_release(LivenessChallengeType challengeType) {
        Intrinsics.checkNotNullParameter(challengeType, "challengeType");
        this.onfidoAnalytics.track(new LivenessEvents.FaceVideoCaptureSecondChallenge(challengeType));
    }

    public void trackVideoCaptureTimeout$onfido_capture_sdk_core_release() {
        this.onfidoAnalytics.track(new LivenessEvents.FaceVideoCaptureTimeout());
    }

    public void trackVideoFinishButtonClicked$onfido_capture_sdk_core_release(long duration) {
        this.onfidoAnalytics.track(new LivenessEvents.FaceVideoCaptureFinishButtonClicked(duration));
    }

    public void trackVideoManualRecordingStarted$onfido_capture_sdk_core_release() {
        this.onfidoAnalytics.track(new LivenessEvents.FaceVideoManualRecordingStarted());
    }

    public void trackVideoNextButtonClicked$onfido_capture_sdk_core_release(long duration) {
        this.onfidoAnalytics.track(new LivenessEvents.FaceVideoCaptureNextButtonClicked(duration));
    }

    public void trackVideoTimeoutRetryButtonClicked$onfido_capture_sdk_core_release() {
        this.onfidoAnalytics.track(new LivenessEvents.FaceVideoCaptureTimeoutRetryButtonClicked());
    }
}
