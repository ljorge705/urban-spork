package com.onfido.android.sdk.capture.detector.face;

import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.ui.camera.FaceDetectionFrame;
import com.onfido.javax.inject.Inject;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.subjects.PublishSubject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0007\b\u0007¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\u000e\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0016J\u000e\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0016¨\u0006\u000b"}, d2 = {"Lcom/onfido/android/sdk/capture/detector/face/FaceDetectorEmpty;", "Lcom/onfido/android/sdk/capture/detector/face/FaceDetector;", "()V", Constants.KEY_HIDE_CLOSE, "", "getFaceDetectionSubject", "Lio/reactivex/rxjava3/subjects/PublishSubject;", "Lcom/onfido/android/sdk/capture/ui/camera/FaceDetectionFrame;", "observeFaceTracking", "Lio/reactivex/rxjava3/core/Flowable;", "Lcom/onfido/android/sdk/capture/detector/face/FaceDetectionResult;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FaceDetectorEmpty implements FaceDetector {
    @Inject
    public FaceDetectorEmpty() {
    }

    @Override // com.onfido.android.sdk.capture.detector.face.FaceDetector
    public void close() {
    }

    @Override // com.onfido.android.sdk.capture.detector.face.FaceDetector
    public PublishSubject<FaceDetectionFrame> getFaceDetectionSubject() {
        PublishSubject<FaceDetectionFrame> publishSubjectCreate = PublishSubject.create();
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate, "create(...)");
        return publishSubjectCreate;
    }

    @Override // com.onfido.android.sdk.capture.detector.face.FaceDetector
    public Flowable<FaceDetectionResult> observeFaceTracking() {
        Flowable<FaceDetectionResult> flowableEmpty = Flowable.empty();
        Intrinsics.checkNotNullExpressionValue(flowableEmpty, "empty(...)");
        return flowableEmpty;
    }
}
