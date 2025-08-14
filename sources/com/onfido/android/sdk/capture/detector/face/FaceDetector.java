package com.onfido.android.sdk.capture.detector.face;

import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.ui.camera.FaceDetectionFrame;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.subjects.PublishSubject;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u000e\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\u000e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH&¨\u0006\n"}, d2 = {"Lcom/onfido/android/sdk/capture/detector/face/FaceDetector;", "", Constants.KEY_HIDE_CLOSE, "", "getFaceDetectionSubject", "Lio/reactivex/rxjava3/subjects/PublishSubject;", "Lcom/onfido/android/sdk/capture/ui/camera/FaceDetectionFrame;", "observeFaceTracking", "Lio/reactivex/rxjava3/core/Flowable;", "Lcom/onfido/android/sdk/capture/detector/face/FaceDetectionResult;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface FaceDetector {
    void close();

    PublishSubject<FaceDetectionFrame> getFaceDetectionSubject();

    Flowable<FaceDetectionResult> observeFaceTracking();
}
