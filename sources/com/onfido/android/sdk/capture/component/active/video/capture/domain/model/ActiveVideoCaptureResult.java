package com.onfido.android.sdk.capture.component.active.video.capture.domain.model;

import com.onfido.android.sdk.capture.common.result.FailureReason;
import com.onfido.android.sdk.capture.internal.model.UploadedArtifact;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bp\u0018\u00002\u00020\u0001:\u0002\u0002\u0003\u0082\u0001\u0002\u0004\u0005¨\u0006\u0006"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/domain/model/ActiveVideoCaptureResult;", "", "Error", "Success", "Lcom/onfido/android/sdk/capture/component/active/video/capture/domain/model/ActiveVideoCaptureResult$Error;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/domain/model/ActiveVideoCaptureResult$Success;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface ActiveVideoCaptureResult {

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/domain/model/ActiveVideoCaptureResult$Error;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/domain/model/ActiveVideoCaptureResult;", "reason", "Lcom/onfido/android/sdk/capture/common/result/FailureReason;", "(Lcom/onfido/android/sdk/capture/common/result/FailureReason;)V", "getReason", "()Lcom/onfido/android/sdk/capture/common/result/FailureReason;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Error implements ActiveVideoCaptureResult {
        private final FailureReason reason;

        public Error(FailureReason reason) {
            Intrinsics.checkNotNullParameter(reason, "reason");
            this.reason = reason;
        }

        public final FailureReason getReason() {
            return this.reason;
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/domain/model/ActiveVideoCaptureResult$Success;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/domain/model/ActiveVideoCaptureResult;", "uploadedArtifact", "Lcom/onfido/android/sdk/capture/internal/model/UploadedArtifact;", "(Lcom/onfido/android/sdk/capture/internal/model/UploadedArtifact;)V", "getUploadedArtifact", "()Lcom/onfido/android/sdk/capture/internal/model/UploadedArtifact;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Success implements ActiveVideoCaptureResult {
        private final UploadedArtifact uploadedArtifact;

        public Success(UploadedArtifact uploadedArtifact) {
            Intrinsics.checkNotNullParameter(uploadedArtifact, "uploadedArtifact");
            this.uploadedArtifact = uploadedArtifact;
        }

        public final UploadedArtifact getUploadedArtifact() {
            return this.uploadedArtifact;
        }
    }
}
