package com.onfido.android.sdk.capture.internal.model;

import com.onfido.android.sdk.capture.component.active.video.capture.data.remote.model.AVCUploadResponse;
import com.onfido.android.sdk.capture.internal.model.UploadedArtifact;
import com.onfido.api.client.data.DocumentUpload;
import com.onfido.api.client.data.LivePhotoUpload;
import com.onfido.api.client.data.LiveVideoUpload;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0003H\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0004H\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0005¨\u0006\u0006"}, d2 = {"toUploadedArtifact", "Lcom/onfido/android/sdk/capture/internal/model/UploadedArtifact;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/data/remote/model/AVCUploadResponse;", "Lcom/onfido/api/client/data/DocumentUpload;", "Lcom/onfido/api/client/data/LivePhotoUpload;", "Lcom/onfido/api/client/data/LiveVideoUpload;", "onfido-capture-sdk-core_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class UploadedArtifactKt {
    public static final UploadedArtifact toUploadedArtifact(AVCUploadResponse aVCUploadResponse) {
        Intrinsics.checkNotNullParameter(aVCUploadResponse, "<this>");
        return new UploadedArtifact.AVCVideo(aVCUploadResponse.getUuid(), aVCUploadResponse.getInsertedAt());
    }

    public static final UploadedArtifact toUploadedArtifact(DocumentUpload documentUpload) {
        Intrinsics.checkNotNullParameter(documentUpload, "<this>");
        return new UploadedArtifact.Document(documentUpload.getId(), documentUpload.getCreatedAt(), documentUpload.getFileName(), documentUpload.getFileType(), documentUpload.getFileSize(), documentUpload.getDownloadHref(), documentUpload.getHref(), documentUpload.getSide(), documentUpload.getIssuingCountry(), documentUpload.getType(), documentUpload.getApplicantId());
    }

    public static final UploadedArtifact toUploadedArtifact(LivePhotoUpload livePhotoUpload) {
        Intrinsics.checkNotNullParameter(livePhotoUpload, "<this>");
        String id = livePhotoUpload.getId();
        String downloadHref = livePhotoUpload.getDownloadHref();
        String href = livePhotoUpload.getHref();
        long fileSize = livePhotoUpload.getFileSize();
        String fileType = livePhotoUpload.getFileType();
        return new UploadedArtifact.Photo(id, livePhotoUpload.getCreatedAt(), livePhotoUpload.getFileName(), fileType, fileSize, downloadHref, href);
    }

    public static final UploadedArtifact toUploadedArtifact(LiveVideoUpload liveVideoUpload) {
        Intrinsics.checkNotNullParameter(liveVideoUpload, "<this>");
        return new UploadedArtifact.LiveVideo(liveVideoUpload.getId(), liveVideoUpload.getCreatedAt(), liveVideoUpload.getFileName(), liveVideoUpload.getFileType(), liveVideoUpload.getFileSize(), liveVideoUpload.getHref(), liveVideoUpload.getDownloadHref());
    }
}
