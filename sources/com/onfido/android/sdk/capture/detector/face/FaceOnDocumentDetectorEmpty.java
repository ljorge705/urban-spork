package com.onfido.android.sdk.capture.detector.face;

import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.ui.camera.DocumentDetectionFrame;
import com.onfido.android.sdk.capture.validation.device.FaceOnDocumentValidationResult;
import com.onfido.javax.inject.Inject;
import io.reactivex.rxjava3.core.Single;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0007\b\u0007¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\n"}, d2 = {"Lcom/onfido/android/sdk/capture/detector/face/FaceOnDocumentDetectorEmpty;", "Lcom/onfido/android/sdk/capture/detector/face/FaceOnDocumentDetector;", "()V", Constants.KEY_HIDE_CLOSE, "", "detect", "Lio/reactivex/rxjava3/core/Single;", "Lcom/onfido/android/sdk/capture/validation/device/FaceOnDocumentValidationResult;", "documentFrame", "Lcom/onfido/android/sdk/capture/ui/camera/DocumentDetectionFrame;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FaceOnDocumentDetectorEmpty implements FaceOnDocumentDetector {
    @Inject
    public FaceOnDocumentDetectorEmpty() {
    }

    @Override // com.onfido.android.sdk.capture.detector.face.FaceOnDocumentDetector
    public void close() {
    }

    @Override // com.onfido.android.sdk.capture.detector.face.FaceOnDocumentDetector
    public Single<FaceOnDocumentValidationResult> detect(DocumentDetectionFrame documentFrame) {
        Intrinsics.checkNotNullParameter(documentFrame, "documentFrame");
        Single<FaceOnDocumentValidationResult> singleJust = Single.just(new FaceOnDocumentValidationResult(Boolean.TRUE, true));
        Intrinsics.checkNotNullExpressionValue(singleJust, "just(...)");
        return singleJust;
    }
}
