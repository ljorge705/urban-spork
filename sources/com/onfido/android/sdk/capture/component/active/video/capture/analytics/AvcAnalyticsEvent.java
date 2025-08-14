package com.onfido.android.sdk.capture.component.active.video.capture.analytics;

import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.analytics.OnfidoAnalyticsEventType;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.AnalyticsEvent;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.AnalyticsFlowStep;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.AnalyticsPropertyKeys;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.Event;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.EventNames;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.EventType;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0018\bÀ\u0002\u0018\u00002\u00020\u0001:\u0016\u0003\u0004\u0005\u0006\u0007\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0019"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/analytics/AvcAnalyticsEvent;", "", "()V", "CameraError", "ConnectionError", "ConnectionErrorRestartRecording", "ConnectionErrorRetryUpload", "FaceAlignment", "FaceAlignmentStatus", "FaceCapture", "FaceCaptureErrorTimeout", "FaceCaptureErrorTimeoutRestartCapture", "FaceCaptureErrorTooFast", "FaceCaptureErrorTooFastRestartCapture", "FaceCaptureStatus", "Intro", "IntroReadyButtonClicked", "MLKitError", "MotionCamera", "MotionFaceDetector", "NoFaceDetected", "NoFaceDetectedRestartCapture", "PlayServicesError", "Upload", "UploadCompleted", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class AvcAnalyticsEvent {
    public static final AvcAnalyticsEvent INSTANCE = new AvcAnalyticsEvent();

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0080\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/analytics/AvcAnalyticsEvent$CameraError;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsEvent;", "errorMessage", "", "(Ljava/lang/String;)V", "getErrorMessage", "()Ljava/lang/String;", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class CameraError extends AnalyticsEvent {
        private final String errorMessage;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public CameraError(String errorMessage) {
            super(new Event(EventNames.Motion.FACE_LIVENESS_CAMERA_ERROR, EventType.VIEW, null, null, 12, null), MapsKt.mapOf(TuplesKt.to(AnalyticsPropertyKeys.STEP, AnalyticsFlowStep.FACE), TuplesKt.to(AnalyticsPropertyKeys.ERROR_MESSAGE, errorMessage)), null, 4, null);
            Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
            this.errorMessage = errorMessage;
        }

        public static /* synthetic */ CameraError copy$default(CameraError cameraError, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = cameraError.errorMessage;
            }
            return cameraError.copy(str);
        }

        /* renamed from: component1, reason: from getter */
        public final String getErrorMessage() {
            return this.errorMessage;
        }

        public final CameraError copy(String errorMessage) {
            Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
            return new CameraError(errorMessage);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof CameraError) && Intrinsics.areEqual(this.errorMessage, ((CameraError) other).errorMessage);
        }

        public final String getErrorMessage() {
            return this.errorMessage;
        }

        public int hashCode() {
            return this.errorMessage.hashCode();
        }

        public String toString() {
            return "CameraError(errorMessage=" + this.errorMessage + ')';
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/analytics/AvcAnalyticsEvent$ConnectionError;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsEvent;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class ConnectionError extends AnalyticsEvent {
        public static final ConnectionError INSTANCE = new ConnectionError();

        private ConnectionError() {
            super(new Event(EventNames.Motion.FACE_LIVENESS_CONNECTION_ERROR, EventType.SCREEN, EventNames.PublicNames.Face.FACE_MOTION_CONNECTION_ERROR, OnfidoAnalyticsEventType.SCREEN), MapsKt.mapOf(TuplesKt.to(AnalyticsPropertyKeys.STEP, AnalyticsFlowStep.FACE)), null, 4, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/analytics/AvcAnalyticsEvent$ConnectionErrorRestartRecording;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsEvent;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class ConnectionErrorRestartRecording extends AnalyticsEvent {
        public static final ConnectionErrorRestartRecording INSTANCE = new ConnectionErrorRestartRecording();

        private ConnectionErrorRestartRecording() {
            super(new Event(EventNames.Motion.FACE_LIVENESS_CONNECTION_ERROR_RESTART_CLICKED, EventType.ACTION, null, null, 12, null), MapsKt.mapOf(TuplesKt.to(AnalyticsPropertyKeys.STEP, AnalyticsFlowStep.FACE)), null, 4, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/analytics/AvcAnalyticsEvent$ConnectionErrorRetryUpload;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsEvent;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class ConnectionErrorRetryUpload extends AnalyticsEvent {
        public static final ConnectionErrorRetryUpload INSTANCE = new ConnectionErrorRetryUpload();

        private ConnectionErrorRetryUpload() {
            super(new Event(EventNames.Motion.FACE_LIVENESS_CONNECTION_ERROR_RETRY_CLICKED, EventType.ACTION, null, null, 12, null), MapsKt.mapOf(TuplesKt.to(AnalyticsPropertyKeys.STEP, AnalyticsFlowStep.FACE)), null, 4, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/analytics/AvcAnalyticsEvent$FaceAlignment;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsEvent;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class FaceAlignment extends AnalyticsEvent {
        public static final FaceAlignment INSTANCE = new FaceAlignment();

        private FaceAlignment() {
            super(new Event(EventNames.Motion.FACE_LIVENESS_ALIGNMENT, EventType.SCREEN, EventNames.PublicNames.Face.FACE_MOTION_ALIGNMENT, OnfidoAnalyticsEventType.SCREEN), MapsKt.mapOf(TuplesKt.to(AnalyticsPropertyKeys.STEP, AnalyticsFlowStep.FACE)), null, 4, null);
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0080\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/analytics/AvcAnalyticsEvent$FaceAlignmentStatus;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsEvent;", "status", "", "(Ljava/lang/String;)V", "getStatus", "()Ljava/lang/String;", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class FaceAlignmentStatus extends AnalyticsEvent {
        private final String status;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public FaceAlignmentStatus(String status) {
            super(new Event(EventNames.Motion.FACE_LIVENESS_ALIGNMENT_STATUS, EventType.VIEW, null, null, 12, null), MapsKt.mapOf(TuplesKt.to(AnalyticsPropertyKeys.STEP, AnalyticsFlowStep.FACE), TuplesKt.to(AnalyticsPropertyKeys.AVC_ALIGNMENT_STATUS, status)), null, 4, null);
            Intrinsics.checkNotNullParameter(status, "status");
            this.status = status;
        }

        public static /* synthetic */ FaceAlignmentStatus copy$default(FaceAlignmentStatus faceAlignmentStatus, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = faceAlignmentStatus.status;
            }
            return faceAlignmentStatus.copy(str);
        }

        /* renamed from: component1, reason: from getter */
        public final String getStatus() {
            return this.status;
        }

        public final FaceAlignmentStatus copy(String status) {
            Intrinsics.checkNotNullParameter(status, "status");
            return new FaceAlignmentStatus(status);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof FaceAlignmentStatus) && Intrinsics.areEqual(this.status, ((FaceAlignmentStatus) other).status);
        }

        public final String getStatus() {
            return this.status;
        }

        public int hashCode() {
            return this.status.hashCode();
        }

        public String toString() {
            return "FaceAlignmentStatus(status=" + this.status + ')';
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/analytics/AvcAnalyticsEvent$FaceCapture;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsEvent;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class FaceCapture extends AnalyticsEvent {
        public static final FaceCapture INSTANCE = new FaceCapture();

        private FaceCapture() {
            super(new Event(EventNames.Motion.FACE_LIVENESS_CAPTURE, EventType.SCREEN, EventNames.PublicNames.Face.FACE_MOTION_CAPTURE, OnfidoAnalyticsEventType.SCREEN), MapsKt.mapOf(TuplesKt.to(AnalyticsPropertyKeys.STEP, AnalyticsFlowStep.FACE)), null, 4, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/analytics/AvcAnalyticsEvent$FaceCaptureErrorTimeout;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsEvent;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class FaceCaptureErrorTimeout extends AnalyticsEvent {
        public static final FaceCaptureErrorTimeout INSTANCE = new FaceCaptureErrorTimeout();

        private FaceCaptureErrorTimeout() {
            super(new Event(EventNames.Motion.FACE_LIVENESS_CAPTURE_ERROR_TIMEOUT, EventType.VIEW, EventNames.PublicNames.Face.FACE_MOTION_CAPTURE_ERROR_TIMEOUT, OnfidoAnalyticsEventType.VIEW), MapsKt.mapOf(TuplesKt.to(AnalyticsPropertyKeys.STEP, AnalyticsFlowStep.FACE)), null, 4, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/analytics/AvcAnalyticsEvent$FaceCaptureErrorTimeoutRestartCapture;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsEvent;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class FaceCaptureErrorTimeoutRestartCapture extends AnalyticsEvent {
        public static final FaceCaptureErrorTimeoutRestartCapture INSTANCE = new FaceCaptureErrorTimeoutRestartCapture();

        private FaceCaptureErrorTimeoutRestartCapture() {
            super(new Event(EventNames.Motion.FACE_LIVENESS_CAPTURE_ERROR_TIMEOUT_RESTART_CLICKED, EventType.ACTION, null, null, 12, null), MapsKt.mapOf(TuplesKt.to(AnalyticsPropertyKeys.STEP, AnalyticsFlowStep.FACE)), null, 4, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/analytics/AvcAnalyticsEvent$FaceCaptureErrorTooFast;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsEvent;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class FaceCaptureErrorTooFast extends AnalyticsEvent {
        public static final FaceCaptureErrorTooFast INSTANCE = new FaceCaptureErrorTooFast();

        private FaceCaptureErrorTooFast() {
            super(new Event(EventNames.Motion.FACE_LIVENESS_CAPTURE_ERROR_TOO_FAST, EventType.VIEW, EventNames.PublicNames.Face.FACE_MOTION_CAPTURE_ERROR_TOO_FAST, OnfidoAnalyticsEventType.VIEW), MapsKt.mapOf(TuplesKt.to(AnalyticsPropertyKeys.STEP, AnalyticsFlowStep.FACE)), null, 4, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/analytics/AvcAnalyticsEvent$FaceCaptureErrorTooFastRestartCapture;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsEvent;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class FaceCaptureErrorTooFastRestartCapture extends AnalyticsEvent {
        public static final FaceCaptureErrorTooFastRestartCapture INSTANCE = new FaceCaptureErrorTooFastRestartCapture();

        private FaceCaptureErrorTooFastRestartCapture() {
            super(new Event(EventNames.Motion.FACE_LIVENESS_CAPTURE_ERROR_TOO_FAST_RESTART_CLICKED, EventType.ACTION, null, null, 12, null), MapsKt.mapOf(TuplesKt.to(AnalyticsPropertyKeys.STEP, AnalyticsFlowStep.FACE)), null, 4, null);
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0080\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/analytics/AvcAnalyticsEvent$FaceCaptureStatus;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsEvent;", "status", "", "(Ljava/lang/String;)V", "getStatus", "()Ljava/lang/String;", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class FaceCaptureStatus extends AnalyticsEvent {
        private final String status;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public FaceCaptureStatus(String status) {
            super(new Event(EventNames.Motion.FACE_LIVENESS_CAPTURE_STATUS, EventType.VIEW, null, null, 12, null), MapsKt.mapOf(TuplesKt.to(AnalyticsPropertyKeys.STEP, AnalyticsFlowStep.FACE), TuplesKt.to(AnalyticsPropertyKeys.AVC_CAPTURE_STATUS, status)), null, 4, null);
            Intrinsics.checkNotNullParameter(status, "status");
            this.status = status;
        }

        public static /* synthetic */ FaceCaptureStatus copy$default(FaceCaptureStatus faceCaptureStatus, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = faceCaptureStatus.status;
            }
            return faceCaptureStatus.copy(str);
        }

        /* renamed from: component1, reason: from getter */
        public final String getStatus() {
            return this.status;
        }

        public final FaceCaptureStatus copy(String status) {
            Intrinsics.checkNotNullParameter(status, "status");
            return new FaceCaptureStatus(status);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof FaceCaptureStatus) && Intrinsics.areEqual(this.status, ((FaceCaptureStatus) other).status);
        }

        public final String getStatus() {
            return this.status;
        }

        public int hashCode() {
            return this.status.hashCode();
        }

        public String toString() {
            return "FaceCaptureStatus(status=" + this.status + ')';
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/analytics/AvcAnalyticsEvent$Intro;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsEvent;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Intro extends AnalyticsEvent {
        public static final Intro INSTANCE = new Intro();

        private Intro() {
            super(new Event(EventNames.Motion.FACE_LIVENESS_INTRO, EventType.SCREEN, EventNames.PublicNames.Face.FACE_MOTION_INTRO, OnfidoAnalyticsEventType.SCREEN), MapsKt.mapOf(TuplesKt.to(AnalyticsPropertyKeys.STEP, AnalyticsFlowStep.FACE)), null, 4, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/analytics/AvcAnalyticsEvent$IntroReadyButtonClicked;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsEvent;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class IntroReadyButtonClicked extends AnalyticsEvent {
        public static final IntroReadyButtonClicked INSTANCE = new IntroReadyButtonClicked();

        private IntroReadyButtonClicked() {
            super(new Event(EventNames.Motion.FACE_LIVENESS_INTRO_READY_CLICKED, EventType.ACTION, null, null, 12, null), MapsKt.mapOf(TuplesKt.to(AnalyticsPropertyKeys.STEP, AnalyticsFlowStep.FACE)), null, 4, null);
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0080\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/analytics/AvcAnalyticsEvent$MLKitError;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsEvent;", "errorMessage", "", "(Ljava/lang/String;)V", "getErrorMessage", "()Ljava/lang/String;", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class MLKitError extends AnalyticsEvent {
        private final String errorMessage;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public MLKitError(String errorMessage) {
            super(new Event(EventNames.Motion.FACE_LIVENESS_MLKIT_ERROR, EventType.VIEW, null, null, 12, null), MapsKt.mapOf(TuplesKt.to(AnalyticsPropertyKeys.STEP, AnalyticsFlowStep.FACE), TuplesKt.to(AnalyticsPropertyKeys.ERROR_MESSAGE, errorMessage)), null, 4, null);
            Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
            this.errorMessage = errorMessage;
        }

        public static /* synthetic */ MLKitError copy$default(MLKitError mLKitError, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = mLKitError.errorMessage;
            }
            return mLKitError.copy(str);
        }

        /* renamed from: component1, reason: from getter */
        public final String getErrorMessage() {
            return this.errorMessage;
        }

        public final MLKitError copy(String errorMessage) {
            Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
            return new MLKitError(errorMessage);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof MLKitError) && Intrinsics.areEqual(this.errorMessage, ((MLKitError) other).errorMessage);
        }

        public final String getErrorMessage() {
            return this.errorMessage;
        }

        public int hashCode() {
            return this.errorMessage.hashCode();
        }

        public String toString() {
            return "MLKitError(errorMessage=" + this.errorMessage + ')';
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0080\b\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/analytics/AvcAnalyticsEvent$MotionCamera;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsEvent;", "cameraType", "", "(Ljava/lang/String;)V", "getCameraType", "()Ljava/lang/String;", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class MotionCamera extends AnalyticsEvent {
        public static final String CAMERA_2 = "Camera2";
        public static final String CAMERA_X = "CameraX";
        private final String cameraType;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public MotionCamera(String cameraType) {
            super(new Event(EventNames.Motion.FACE_LIVENESS_CAMERA, EventType.ACTION, null, null, 12, null), MapsKt.mapOf(TuplesKt.to(AnalyticsPropertyKeys.STEP, AnalyticsFlowStep.FACE), TuplesKt.to(AnalyticsPropertyKeys.AVC_CAMERA_TYPE, cameraType)), null, 4, null);
            Intrinsics.checkNotNullParameter(cameraType, "cameraType");
            this.cameraType = cameraType;
        }

        public static /* synthetic */ MotionCamera copy$default(MotionCamera motionCamera, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = motionCamera.cameraType;
            }
            return motionCamera.copy(str);
        }

        /* renamed from: component1, reason: from getter */
        public final String getCameraType() {
            return this.cameraType;
        }

        public final MotionCamera copy(String cameraType) {
            Intrinsics.checkNotNullParameter(cameraType, "cameraType");
            return new MotionCamera(cameraType);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof MotionCamera) && Intrinsics.areEqual(this.cameraType, ((MotionCamera) other).cameraType);
        }

        public final String getCameraType() {
            return this.cameraType;
        }

        public int hashCode() {
            return this.cameraType.hashCode();
        }

        public String toString() {
            return "MotionCamera(cameraType=" + this.cameraType + ')';
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0080\b\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/analytics/AvcAnalyticsEvent$MotionFaceDetector;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsEvent;", "faceDetectorType", "", "(Ljava/lang/String;)V", "getFaceDetectorType", "()Ljava/lang/String;", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class MotionFaceDetector extends AnalyticsEvent {
        public static final String TYPE_MLKit = "MLKit";
        public static final String TYPE_TFLite = "TFLite";
        private final String faceDetectorType;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public MotionFaceDetector(String faceDetectorType) {
            super(new Event(EventNames.Motion.FACE_LIVENESS_FACE_DETECTOR, EventType.ACTION, null, null, 12, null), MapsKt.mapOf(TuplesKt.to(AnalyticsPropertyKeys.STEP, AnalyticsFlowStep.FACE), TuplesKt.to(AnalyticsPropertyKeys.AVC_FACE_DETECTOR_TYPE, faceDetectorType)), null, 4, null);
            Intrinsics.checkNotNullParameter(faceDetectorType, "faceDetectorType");
            this.faceDetectorType = faceDetectorType;
        }

        public static /* synthetic */ MotionFaceDetector copy$default(MotionFaceDetector motionFaceDetector, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = motionFaceDetector.faceDetectorType;
            }
            return motionFaceDetector.copy(str);
        }

        /* renamed from: component1, reason: from getter */
        public final String getFaceDetectorType() {
            return this.faceDetectorType;
        }

        public final MotionFaceDetector copy(String faceDetectorType) {
            Intrinsics.checkNotNullParameter(faceDetectorType, "faceDetectorType");
            return new MotionFaceDetector(faceDetectorType);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof MotionFaceDetector) && Intrinsics.areEqual(this.faceDetectorType, ((MotionFaceDetector) other).faceDetectorType);
        }

        public final String getFaceDetectorType() {
            return this.faceDetectorType;
        }

        public int hashCode() {
            return this.faceDetectorType.hashCode();
        }

        public String toString() {
            return "MotionFaceDetector(faceDetectorType=" + this.faceDetectorType + ')';
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/analytics/AvcAnalyticsEvent$NoFaceDetected;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsEvent;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class NoFaceDetected extends AnalyticsEvent {
        public static final NoFaceDetected INSTANCE = new NoFaceDetected();

        private NoFaceDetected() {
            super(new Event(EventNames.Motion.FACE_LIVENESS_NO_FACE_DETECTED, EventType.SCREEN, EventNames.PublicNames.Face.FACE_MOTION_NO_FACE_DETECTED, OnfidoAnalyticsEventType.SCREEN), MapsKt.mapOf(TuplesKt.to(AnalyticsPropertyKeys.STEP, AnalyticsFlowStep.FACE)), null, 4, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/analytics/AvcAnalyticsEvent$NoFaceDetectedRestartCapture;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsEvent;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class NoFaceDetectedRestartCapture extends AnalyticsEvent {
        public static final NoFaceDetectedRestartCapture INSTANCE = new NoFaceDetectedRestartCapture();

        private NoFaceDetectedRestartCapture() {
            super(new Event(EventNames.Motion.FACE_LIVENESS_NO_FACE_DETECTED_RESTART_CLICKED, EventType.ACTION, null, null, 12, null), MapsKt.mapOf(TuplesKt.to(AnalyticsPropertyKeys.STEP, AnalyticsFlowStep.FACE)), null, 4, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/analytics/AvcAnalyticsEvent$PlayServicesError;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsEvent;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class PlayServicesError extends AnalyticsEvent {
        public static final PlayServicesError INSTANCE = new PlayServicesError();

        private PlayServicesError() {
            super(new Event(EventNames.Motion.FACE_LIVENESS_PLAY_SERVICES_ERROR, EventType.VIEW, null, null, 12, null), MapsKt.mapOf(TuplesKt.to(AnalyticsPropertyKeys.STEP, AnalyticsFlowStep.FACE)), null, 4, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/analytics/AvcAnalyticsEvent$Upload;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsEvent;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Upload extends AnalyticsEvent {
        public static final Upload INSTANCE = new Upload();

        private Upload() {
            super(new Event(EventNames.Motion.FACE_LIVENESS_UPLOAD, EventType.SCREEN, EventNames.PublicNames.Face.FACE_MOTION_UPLOAD, OnfidoAnalyticsEventType.SCREEN), MapsKt.mapOf(TuplesKt.to(AnalyticsPropertyKeys.STEP, AnalyticsFlowStep.FACE)), null, 4, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/analytics/AvcAnalyticsEvent$UploadCompleted;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsEvent;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class UploadCompleted extends AnalyticsEvent {
        public static final UploadCompleted INSTANCE = new UploadCompleted();

        private UploadCompleted() {
            super(new Event(EventNames.Motion.FACE_LIVENESS_UPLOAD_COMPLETED, EventType.VIEW, EventNames.PublicNames.Face.FACE_MOTION_UPLOAD_COMPLETED, OnfidoAnalyticsEventType.VIEW), MapsKt.mapOf(TuplesKt.to(AnalyticsPropertyKeys.STEP, AnalyticsFlowStep.FACE)), null, 4, null);
        }
    }

    private AvcAnalyticsEvent() {
    }
}
