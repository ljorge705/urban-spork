package com.onfido.workflow.internal.workflow.model;

import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.flow.CaptureStepDataBundle;
import com.onfido.android.sdk.capture.internal.model.UploadedArtifact;
import com.onfido.android.sdk.capture.ui.camera.exception.UnknownCameraException;
import com.onfido.android.sdk.capture.ui.camera.liveness.challenges.LivenessPerformedChallenges;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CaptureResult.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u00002\u00020\u0001:\u0006\u0003\u0004\u0005\u0006\u0007\bB\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0006\t\n\u000b\f\r\u000e¨\u0006\u000f"}, d2 = {"Lcom/onfido/workflow/internal/workflow/model/CaptureResult;", "", "()V", "Cancelled", "DocumentUpload", "ErrorResult", "Exit", "FaceUploadPhoto", "FaceUploadVideo", "Lcom/onfido/workflow/internal/workflow/model/CaptureResult$Cancelled;", "Lcom/onfido/workflow/internal/workflow/model/CaptureResult$DocumentUpload;", "Lcom/onfido/workflow/internal/workflow/model/CaptureResult$ErrorResult;", "Lcom/onfido/workflow/internal/workflow/model/CaptureResult$Exit;", "Lcom/onfido/workflow/internal/workflow/model/CaptureResult$FaceUploadPhoto;", "Lcom/onfido/workflow/internal/workflow/model/CaptureResult$FaceUploadVideo;", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public abstract class CaptureResult {
    public /* synthetic */ CaptureResult(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* compiled from: CaptureResult.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/onfido/workflow/internal/workflow/model/CaptureResult$FaceUploadPhoto;", "Lcom/onfido/workflow/internal/workflow/model/CaptureResult;", "uploadArtifact", "Lcom/onfido/android/sdk/capture/internal/model/UploadedArtifact;", "(Lcom/onfido/android/sdk/capture/internal/model/UploadedArtifact;)V", "getUploadArtifact", "()Lcom/onfido/android/sdk/capture/internal/model/UploadedArtifact;", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class FaceUploadPhoto extends CaptureResult {
        private final UploadedArtifact uploadArtifact;

        public static /* synthetic */ FaceUploadPhoto copy$default(FaceUploadPhoto faceUploadPhoto, UploadedArtifact uploadedArtifact, int i, Object obj) {
            if ((i & 1) != 0) {
                uploadedArtifact = faceUploadPhoto.uploadArtifact;
            }
            return faceUploadPhoto.copy(uploadedArtifact);
        }

        /* renamed from: component1, reason: from getter */
        public final UploadedArtifact getUploadArtifact() {
            return this.uploadArtifact;
        }

        public final FaceUploadPhoto copy(UploadedArtifact uploadArtifact) {
            Intrinsics.checkNotNullParameter(uploadArtifact, "uploadArtifact");
            return new FaceUploadPhoto(uploadArtifact);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof FaceUploadPhoto) && Intrinsics.areEqual(this.uploadArtifact, ((FaceUploadPhoto) other).uploadArtifact);
        }

        public final UploadedArtifact getUploadArtifact() {
            return this.uploadArtifact;
        }

        public int hashCode() {
            return this.uploadArtifact.hashCode();
        }

        public String toString() {
            return "FaceUploadPhoto(uploadArtifact=" + this.uploadArtifact + ")";
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public FaceUploadPhoto(UploadedArtifact uploadArtifact) {
            super(null);
            Intrinsics.checkNotNullParameter(uploadArtifact, "uploadArtifact");
            this.uploadArtifact = uploadArtifact;
        }
    }

    private CaptureResult() {
    }

    /* compiled from: CaptureResult.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Lcom/onfido/workflow/internal/workflow/model/CaptureResult$FaceUploadVideo;", "Lcom/onfido/workflow/internal/workflow/model/CaptureResult;", "videoPath", "", "livenessChallenges", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessPerformedChallenges;", "(Ljava/lang/String;Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessPerformedChallenges;)V", "getLivenessChallenges", "()Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessPerformedChallenges;", "getVideoPath", "()Ljava/lang/String;", "component1", "component2", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class FaceUploadVideo extends CaptureResult {
        private final LivenessPerformedChallenges livenessChallenges;
        private final String videoPath;

        public static /* synthetic */ FaceUploadVideo copy$default(FaceUploadVideo faceUploadVideo, String str, LivenessPerformedChallenges livenessPerformedChallenges, int i, Object obj) {
            if ((i & 1) != 0) {
                str = faceUploadVideo.videoPath;
            }
            if ((i & 2) != 0) {
                livenessPerformedChallenges = faceUploadVideo.livenessChallenges;
            }
            return faceUploadVideo.copy(str, livenessPerformedChallenges);
        }

        /* renamed from: component1, reason: from getter */
        public final String getVideoPath() {
            return this.videoPath;
        }

        /* renamed from: component2, reason: from getter */
        public final LivenessPerformedChallenges getLivenessChallenges() {
            return this.livenessChallenges;
        }

        public final FaceUploadVideo copy(String videoPath, LivenessPerformedChallenges livenessChallenges) {
            Intrinsics.checkNotNullParameter(videoPath, "videoPath");
            Intrinsics.checkNotNullParameter(livenessChallenges, "livenessChallenges");
            return new FaceUploadVideo(videoPath, livenessChallenges);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof FaceUploadVideo)) {
                return false;
            }
            FaceUploadVideo faceUploadVideo = (FaceUploadVideo) other;
            return Intrinsics.areEqual(this.videoPath, faceUploadVideo.videoPath) && Intrinsics.areEqual(this.livenessChallenges, faceUploadVideo.livenessChallenges);
        }

        public final LivenessPerformedChallenges getLivenessChallenges() {
            return this.livenessChallenges;
        }

        public final String getVideoPath() {
            return this.videoPath;
        }

        public int hashCode() {
            return (this.videoPath.hashCode() * 31) + this.livenessChallenges.hashCode();
        }

        public String toString() {
            return "FaceUploadVideo(videoPath=" + this.videoPath + ", livenessChallenges=" + this.livenessChallenges + ")";
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public FaceUploadVideo(String videoPath, LivenessPerformedChallenges livenessChallenges) {
            super(null);
            Intrinsics.checkNotNullParameter(videoPath, "videoPath");
            Intrinsics.checkNotNullParameter(livenessChallenges, "livenessChallenges");
            this.videoPath = videoPath;
            this.livenessChallenges = livenessChallenges;
        }
    }

    /* compiled from: CaptureResult.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0016"}, d2 = {"Lcom/onfido/workflow/internal/workflow/model/CaptureResult$DocumentUpload;", "Lcom/onfido/workflow/internal/workflow/model/CaptureResult;", "captureStepDataBundle", "Lcom/onfido/android/sdk/capture/flow/CaptureStepDataBundle;", "documentUploadPayload", "Lcom/onfido/workflow/internal/workflow/model/DocumentUploadPayload;", "(Lcom/onfido/android/sdk/capture/flow/CaptureStepDataBundle;Lcom/onfido/workflow/internal/workflow/model/DocumentUploadPayload;)V", "getCaptureStepDataBundle", "()Lcom/onfido/android/sdk/capture/flow/CaptureStepDataBundle;", "getDocumentUploadPayload", "()Lcom/onfido/workflow/internal/workflow/model/DocumentUploadPayload;", "component1", "component2", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class DocumentUpload extends CaptureResult {
        private final CaptureStepDataBundle captureStepDataBundle;
        private final DocumentUploadPayload documentUploadPayload;

        public static /* synthetic */ DocumentUpload copy$default(DocumentUpload documentUpload, CaptureStepDataBundle captureStepDataBundle, DocumentUploadPayload documentUploadPayload, int i, Object obj) {
            if ((i & 1) != 0) {
                captureStepDataBundle = documentUpload.captureStepDataBundle;
            }
            if ((i & 2) != 0) {
                documentUploadPayload = documentUpload.documentUploadPayload;
            }
            return documentUpload.copy(captureStepDataBundle, documentUploadPayload);
        }

        /* renamed from: component1, reason: from getter */
        public final CaptureStepDataBundle getCaptureStepDataBundle() {
            return this.captureStepDataBundle;
        }

        /* renamed from: component2, reason: from getter */
        public final DocumentUploadPayload getDocumentUploadPayload() {
            return this.documentUploadPayload;
        }

        public final DocumentUpload copy(CaptureStepDataBundle captureStepDataBundle, DocumentUploadPayload documentUploadPayload) {
            Intrinsics.checkNotNullParameter(captureStepDataBundle, "captureStepDataBundle");
            Intrinsics.checkNotNullParameter(documentUploadPayload, "documentUploadPayload");
            return new DocumentUpload(captureStepDataBundle, documentUploadPayload);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DocumentUpload)) {
                return false;
            }
            DocumentUpload documentUpload = (DocumentUpload) other;
            return Intrinsics.areEqual(this.captureStepDataBundle, documentUpload.captureStepDataBundle) && Intrinsics.areEqual(this.documentUploadPayload, documentUpload.documentUploadPayload);
        }

        public final CaptureStepDataBundle getCaptureStepDataBundle() {
            return this.captureStepDataBundle;
        }

        public final DocumentUploadPayload getDocumentUploadPayload() {
            return this.documentUploadPayload;
        }

        public int hashCode() {
            return (this.captureStepDataBundle.hashCode() * 31) + this.documentUploadPayload.hashCode();
        }

        public String toString() {
            return "DocumentUpload(captureStepDataBundle=" + this.captureStepDataBundle + ", documentUploadPayload=" + this.documentUploadPayload + ")";
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public DocumentUpload(CaptureStepDataBundle captureStepDataBundle, DocumentUploadPayload documentUploadPayload) {
            super(null);
            Intrinsics.checkNotNullParameter(captureStepDataBundle, "captureStepDataBundle");
            Intrinsics.checkNotNullParameter(documentUploadPayload, "documentUploadPayload");
            this.captureStepDataBundle = captureStepDataBundle;
            this.documentUploadPayload = documentUploadPayload;
        }
    }

    /* compiled from: CaptureResult.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0003\u0006\u0007\b¨\u0006\t"}, d2 = {"Lcom/onfido/workflow/internal/workflow/model/CaptureResult$ErrorResult;", "Lcom/onfido/workflow/internal/workflow/model/CaptureResult;", "()V", "InvalidSSLCertificate", "OnTokenExpired", "UnknownCamera", "Lcom/onfido/workflow/internal/workflow/model/CaptureResult$ErrorResult$InvalidSSLCertificate;", "Lcom/onfido/workflow/internal/workflow/model/CaptureResult$ErrorResult$OnTokenExpired;", "Lcom/onfido/workflow/internal/workflow/model/CaptureResult$ErrorResult$UnknownCamera;", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static abstract class ErrorResult extends CaptureResult {
        public /* synthetic */ ErrorResult(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* compiled from: CaptureResult.kt */
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/onfido/workflow/internal/workflow/model/CaptureResult$ErrorResult$UnknownCamera;", "Lcom/onfido/workflow/internal/workflow/model/CaptureResult$ErrorResult;", "cause", "Lcom/onfido/android/sdk/capture/ui/camera/exception/UnknownCameraException;", "(Lcom/onfido/android/sdk/capture/ui/camera/exception/UnknownCameraException;)V", "getCause", "()Lcom/onfido/android/sdk/capture/ui/camera/exception/UnknownCameraException;", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class UnknownCamera extends ErrorResult {
            private final UnknownCameraException cause;

            public final UnknownCameraException getCause() {
                return this.cause;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public UnknownCamera(UnknownCameraException cause) {
                super(null);
                Intrinsics.checkNotNullParameter(cause, "cause");
                this.cause = cause;
            }
        }

        private ErrorResult() {
            super(null);
        }

        /* compiled from: CaptureResult.kt */
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/onfido/workflow/internal/workflow/model/CaptureResult$ErrorResult$InvalidSSLCertificate;", "Lcom/onfido/workflow/internal/workflow/model/CaptureResult$ErrorResult;", "message", "", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class InvalidSSLCertificate extends ErrorResult {
            private final String message;

            public final String getMessage() {
                return this.message;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public InvalidSSLCertificate(String message) {
                super(null);
                Intrinsics.checkNotNullParameter(message, "message");
                this.message = message;
            }
        }

        /* compiled from: CaptureResult.kt */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/workflow/internal/workflow/model/CaptureResult$ErrorResult$OnTokenExpired;", "Lcom/onfido/workflow/internal/workflow/model/CaptureResult$ErrorResult;", "()V", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class OnTokenExpired extends ErrorResult {
            public static final OnTokenExpired INSTANCE = new OnTokenExpired();

            private OnTokenExpired() {
                super(null);
            }
        }
    }

    /* compiled from: CaptureResult.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/workflow/internal/workflow/model/CaptureResult$Cancelled;", "Lcom/onfido/workflow/internal/workflow/model/CaptureResult;", "()V", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Cancelled extends CaptureResult {
        public static final Cancelled INSTANCE = new Cancelled();

        private Cancelled() {
            super(null);
        }
    }

    /* compiled from: CaptureResult.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/workflow/internal/workflow/model/CaptureResult$Exit;", "Lcom/onfido/workflow/internal/workflow/model/CaptureResult;", "()V", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Exit extends CaptureResult {
        public static final Exit INSTANCE = new Exit();

        private Exit() {
            super(null);
        }
    }
}
