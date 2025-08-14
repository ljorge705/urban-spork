package com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers;

import com.onfido.android.sdk.capture.internal.analytics.inhouse.OnfidoAnalytics;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.AnalyticsEvent;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.CaptureStatus;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.LivenessEvents;
import com.onfido.android.sdk.capture.ui.CaptureType;
import com.onfido.android.sdk.capture.upload.ErrorType;
import com.onfido.javax.inject.Inject;
import com.onfido.reactnative.sdk.ReactNativeBridgeUtiles;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\b\b\u0010\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004JE\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u0011H\u0010¢\u0006\u0002\b\u0013J2\u0010\u0014\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00020\n2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0012J/\u0010\u0015\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0010¢\u0006\u0002\b\u0016J2\u0010\u0017\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00020\n2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0012J/\u0010\u0018\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0010¢\u0006\u0002\b\u0019J/\u0010\u001a\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0010¢\u0006\u0002\b\u001bJ\r\u0010\u001c\u001a\u00020\bH\u0010¢\u0006\u0002\b\u001dJ\u0015\u0010\u001e\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\rH\u0010¢\u0006\u0002\b\u001fJ\r\u0010 \u001a\u00020\bH\u0010¢\u0006\u0002\b!J\r\u0010\"\u001a\u00020\bH\u0010¢\u0006\u0002\b#J\r\u0010$\u001a\u00020\bH\u0010¢\u0006\u0002\b%J\r\u0010&\u001a\u00020\bH\u0010¢\u0006\u0002\b'J\r\u0010(\u001a\u00020\bH\u0010¢\u0006\u0002\b)J\r\u0010*\u001a\u00020\bH\u0010¢\u0006\u0002\b+J\u001d\u0010,\u001a\u00020\b2\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u000200H\u0010¢\u0006\u0002\b1J\r\u00102\u001a\u00020\bH\u0010¢\u0006\u0002\b3J\u001d\u00104\u001a\u00020\b2\u0006\u00105\u001a\u00020\u00112\u0006\u00106\u001a\u000207H\u0010¢\u0006\u0002\b8J\u0015\u00109\u001a\u00020\b2\u0006\u0010/\u001a\u000200H\u0010¢\u0006\u0002\b:J\r\u0010;\u001a\u00020\bH\u0010¢\u0006\u0002\b<J\r\u0010=\u001a\u00020\bH\u0010¢\u0006\u0002\b>R\u0014\u0010\u0002\u001a\u00020\u0003X\u0090\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006?"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/LivenessTracker;", "", "onfidoAnalytics", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/OnfidoAnalytics;", "(Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/OnfidoAnalytics;)V", "getOnfidoAnalytics$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/OnfidoAnalytics;", "trackFaceCapture", "", "isConfirmation", "", "isPortrait", ReactNativeBridgeUtiles.KEY_CAPTURE_TYPE, "Lcom/onfido/android/sdk/capture/ui/CaptureType;", "errorType", "Lcom/onfido/android/sdk/capture/upload/ErrorType;", "takenPhotoCount", "", "rejectionCount", "trackFaceCapture$onfido_capture_sdk_core_release", "trackFaceCaptureInhouse", "trackFaceConfirmationBackButtonClicked", "trackFaceConfirmationBackButtonClicked$onfido_capture_sdk_core_release", "trackFaceConfirmationInhouse", "trackFaceConfirmationRetakeButtonClicked", "trackFaceConfirmationRetakeButtonClicked$onfido_capture_sdk_core_release", "trackFaceConfirmationUploadButtonClicked", "trackFaceConfirmationUploadButtonClicked$onfido_capture_sdk_core_release", "trackFaceConfirmationVideoError", "trackFaceConfirmationVideoError$onfido_capture_sdk_core_release", "trackFaceIntro", "trackFaceIntro$onfido_capture_sdk_core_release", "trackFaceIntroTakeSelfieButtonClicked", "trackFaceIntroTakeSelfieButtonClicked$onfido_capture_sdk_core_release", "trackFaceSelfieIntroBackButtonClicked", "trackFaceSelfieIntroBackButtonClicked$onfido_capture_sdk_core_release", "trackFaceVideoCaptureBackButtonClicked", "trackFaceVideoCaptureBackButtonClicked$onfido_capture_sdk_core_release", "trackFaceVideoConfirmationBackButtonClicked", "trackFaceVideoConfirmationBackButtonClicked$onfido_capture_sdk_core_release", "trackFaceVideoConfirmationRetakeButtonClicked", "trackFaceVideoConfirmationRetakeButtonClicked$onfido_capture_sdk_core_release", "trackFaceVideoConfirmationUploadButtonClicked", "trackFaceVideoConfirmationUploadButtonClicked$onfido_capture_sdk_core_release", "trackFaceVideoConfirmationUploadStatus", "captureStatus", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/CaptureStatus;", "duration", "", "trackFaceVideoConfirmationUploadStatus$onfido_capture_sdk_core_release", "trackFaceVideoIntroBackButtonClicked", "trackFaceVideoIntroBackButtonClicked$onfido_capture_sdk_core_release", "trackLivenessChallenge", "challengeStep", "challengeType", "", "trackLivenessChallenge$onfido_capture_sdk_core_release", "trackUploadCompleted", "trackUploadCompleted$onfido_capture_sdk_core_release", "trackUploadStarted", "trackUploadStarted$onfido_capture_sdk_core_release", "trackVideoIntroRecordVideoButtonClicked", "trackVideoIntroRecordVideoButtonClicked$onfido_capture_sdk_core_release", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class LivenessTracker {
    private final OnfidoAnalytics onfidoAnalytics;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[CaptureType.values().length];
            try {
                iArr[CaptureType.FACE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[CaptureType.VIDEO.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Inject
    public LivenessTracker(OnfidoAnalytics onfidoAnalytics) {
        Intrinsics.checkNotNullParameter(onfidoAnalytics, "onfidoAnalytics");
        this.onfidoAnalytics = onfidoAnalytics;
    }

    public static /* synthetic */ void trackFaceCapture$onfido_capture_sdk_core_release$default(LivenessTracker livenessTracker, boolean z, boolean z2, CaptureType captureType, ErrorType errorType, int i, int i2, int i3, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: trackFaceCapture");
        }
        if ((i3 & 8) != 0) {
            errorType = null;
        }
        livenessTracker.trackFaceCapture$onfido_capture_sdk_core_release(z, z2, captureType, errorType, (i3 & 16) != 0 ? 0 : i, (i3 & 32) != 0 ? 0 : i2);
    }

    private void trackFaceCaptureInhouse(CaptureType captureType, boolean isPortrait, ErrorType errorType, int takenPhotoCount, int rejectionCount) {
        int i = WhenMappings.$EnumSwitchMapping$0[captureType.ordinal()];
        if (i == 1) {
            getOnfidoAnalytics().track(new LivenessEvents.FaceSelfieCapture(isPortrait, errorType, takenPhotoCount, rejectionCount));
        } else {
            if (i != 2) {
                return;
            }
            getOnfidoAnalytics().track(new LivenessEvents.FaceVideoCapture(isPortrait));
        }
    }

    private void trackFaceConfirmationInhouse(CaptureType captureType, boolean isPortrait, ErrorType errorType, int takenPhotoCount, int rejectionCount) {
        int i = WhenMappings.$EnumSwitchMapping$0[captureType.ordinal()];
        if (i == 1) {
            getOnfidoAnalytics().track(new LivenessEvents.FaceSelfieConfirmation(isPortrait, errorType, takenPhotoCount, rejectionCount));
        } else {
            if (i != 2) {
                return;
            }
            getOnfidoAnalytics().track(new LivenessEvents.FaceVideoConfirmation(isPortrait));
        }
    }

    /* renamed from: getOnfidoAnalytics$onfido_capture_sdk_core_release, reason: from getter */
    public OnfidoAnalytics getOnfidoAnalytics() {
        return this.onfidoAnalytics;
    }

    public void trackFaceCapture$onfido_capture_sdk_core_release(boolean isConfirmation, boolean isPortrait, CaptureType captureType, ErrorType errorType, int takenPhotoCount, int rejectionCount) {
        Intrinsics.checkNotNullParameter(captureType, "captureType");
        if (isConfirmation) {
            trackFaceConfirmationInhouse(captureType, isPortrait, errorType, takenPhotoCount, rejectionCount);
        } else {
            trackFaceCaptureInhouse(captureType, isPortrait, errorType, takenPhotoCount + 1, rejectionCount);
        }
    }

    public void trackFaceConfirmationBackButtonClicked$onfido_capture_sdk_core_release(CaptureType captureType, ErrorType errorType, int takenPhotoCount, int rejectionCount) {
        Intrinsics.checkNotNullParameter(captureType, "captureType");
        if (captureType == CaptureType.FACE) {
            getOnfidoAnalytics().track(new LivenessEvents.FaceSelfieConfirmationBackButtonClicked(errorType, takenPhotoCount, rejectionCount));
        }
    }

    public void trackFaceConfirmationRetakeButtonClicked$onfido_capture_sdk_core_release(CaptureType captureType, ErrorType errorType, int takenPhotoCount, int rejectionCount) {
        Intrinsics.checkNotNullParameter(captureType, "captureType");
        if (captureType == CaptureType.FACE) {
            getOnfidoAnalytics().track(new LivenessEvents.FaceSelfieConfirmationRetakeButtonClicked(errorType, takenPhotoCount, rejectionCount));
        }
    }

    public void trackFaceConfirmationUploadButtonClicked$onfido_capture_sdk_core_release(CaptureType captureType, ErrorType errorType, int takenPhotoCount, int rejectionCount) {
        Intrinsics.checkNotNullParameter(captureType, "captureType");
        if (captureType == CaptureType.FACE) {
            getOnfidoAnalytics().track(new LivenessEvents.FaceSelfieConfirmationUploadButtonClicked(errorType, takenPhotoCount, rejectionCount));
        }
    }

    public void trackFaceConfirmationVideoError$onfido_capture_sdk_core_release() {
        getOnfidoAnalytics().track(new LivenessEvents.FaceConfirmationVideoError());
    }

    public void trackFaceIntro$onfido_capture_sdk_core_release(CaptureType captureType) {
        OnfidoAnalytics onfidoAnalytics;
        AnalyticsEvent analyticsEvent;
        Intrinsics.checkNotNullParameter(captureType, "captureType");
        int i = WhenMappings.$EnumSwitchMapping$0[captureType.ordinal()];
        if (i == 1) {
            onfidoAnalytics = getOnfidoAnalytics();
            analyticsEvent = LivenessEvents.FaceSelfieIntro.INSTANCE;
        } else {
            if (i != 2) {
                return;
            }
            onfidoAnalytics = getOnfidoAnalytics();
            analyticsEvent = LivenessEvents.FaceVideoIntro.INSTANCE;
        }
        onfidoAnalytics.track(analyticsEvent);
    }

    public void trackFaceIntroTakeSelfieButtonClicked$onfido_capture_sdk_core_release() {
        getOnfidoAnalytics().track(LivenessEvents.FaceSelfieIntroTakeSelfieButtonClicked.INSTANCE);
    }

    public void trackFaceSelfieIntroBackButtonClicked$onfido_capture_sdk_core_release() {
        getOnfidoAnalytics().track(LivenessEvents.FaceSelfieIntroBackButtonClicked.INSTANCE);
    }

    public void trackFaceVideoCaptureBackButtonClicked$onfido_capture_sdk_core_release() {
        getOnfidoAnalytics().track(new LivenessEvents.FaceVideoCaptureBackButtonClicked());
    }

    public void trackFaceVideoConfirmationBackButtonClicked$onfido_capture_sdk_core_release() {
        getOnfidoAnalytics().track(new LivenessEvents.FaceVideoConfirmationBackButtonClicked());
    }

    public void trackFaceVideoConfirmationRetakeButtonClicked$onfido_capture_sdk_core_release() {
        getOnfidoAnalytics().track(new LivenessEvents.FaceVideoConfirmationRetakeButtonClicked());
    }

    public void trackFaceVideoConfirmationUploadButtonClicked$onfido_capture_sdk_core_release() {
        getOnfidoAnalytics().track(new LivenessEvents.FaceVideoConfirmationUploadButtonClicked());
    }

    public void trackFaceVideoConfirmationUploadStatus$onfido_capture_sdk_core_release(CaptureStatus captureStatus, long duration) {
        Intrinsics.checkNotNullParameter(captureStatus, "captureStatus");
        getOnfidoAnalytics().track(new LivenessEvents.FaceVideoConfirmationUploadStatus(captureStatus, duration));
    }

    public void trackFaceVideoIntroBackButtonClicked$onfido_capture_sdk_core_release() {
        getOnfidoAnalytics().track(LivenessEvents.FaceVideoIntroBackButtonClicked.INSTANCE);
    }

    public void trackLivenessChallenge$onfido_capture_sdk_core_release(int challengeStep, String challengeType) {
        Intrinsics.checkNotNullParameter(challengeType, "challengeType");
        getOnfidoAnalytics().track(new LivenessEvents.LivenessChallenge(challengeStep, challengeType));
    }

    public void trackUploadCompleted$onfido_capture_sdk_core_release(long duration) {
        getOnfidoAnalytics().track(new LivenessEvents.FaceVideoUploadCompleted(duration));
    }

    public void trackUploadStarted$onfido_capture_sdk_core_release() {
        getOnfidoAnalytics().track(LivenessEvents.FaceVideoUploadStarted.INSTANCE);
    }

    public void trackVideoIntroRecordVideoButtonClicked$onfido_capture_sdk_core_release() {
        getOnfidoAnalytics().track(LivenessEvents.FaceVideoIntroRecordVideoButtonClicked.INSTANCE);
    }
}
