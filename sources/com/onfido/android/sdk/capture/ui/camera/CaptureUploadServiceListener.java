package com.onfido.android.sdk.capture.ui.camera;

import com.onfido.android.sdk.capture.upload.ErrorType;
import com.onfido.api.client.data.LivePhotoUpload;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH&¨\u0006\f"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/CaptureUploadServiceListener;", "", "onDocumentVideoUploaded", "", "documentVideoId", "", "onLivePhotoUploaded", "photoUpload", "Lcom/onfido/api/client/data/LivePhotoUpload;", "onUploadError", "errorType", "Lcom/onfido/android/sdk/capture/upload/ErrorType;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface CaptureUploadServiceListener {

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class DefaultImpls {
        public static void onDocumentVideoUploaded(CaptureUploadServiceListener captureUploadServiceListener, String documentVideoId) {
            Intrinsics.checkNotNullParameter(documentVideoId, "documentVideoId");
        }

        public static void onLivePhotoUploaded(CaptureUploadServiceListener captureUploadServiceListener, LivePhotoUpload photoUpload) {
            Intrinsics.checkNotNullParameter(photoUpload, "photoUpload");
        }
    }

    void onDocumentVideoUploaded(String documentVideoId);

    void onLivePhotoUploaded(LivePhotoUpload photoUpload);

    void onUploadError(ErrorType errorType);
}
