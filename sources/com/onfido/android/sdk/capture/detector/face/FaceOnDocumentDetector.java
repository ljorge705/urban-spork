package com.onfido.android.sdk.capture.detector.face;

import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.ui.camera.DocumentDetectionFrame;
import com.onfido.android.sdk.capture.validation.device.FaceOnDocumentValidationResult;
import io.reactivex.rxjava3.core.Single;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b`\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\bH&¨\u0006\t"}, d2 = {"Lcom/onfido/android/sdk/capture/detector/face/FaceOnDocumentDetector;", "", Constants.KEY_HIDE_CLOSE, "", "detect", "Lio/reactivex/rxjava3/core/Single;", "Lcom/onfido/android/sdk/capture/validation/device/FaceOnDocumentValidationResult;", "documentFrame", "Lcom/onfido/android/sdk/capture/ui/camera/DocumentDetectionFrame;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface FaceOnDocumentDetector {
    void close();

    Single<FaceOnDocumentValidationResult> detect(DocumentDetectionFrame documentFrame);
}
