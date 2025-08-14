package com.onfido.android.sdk.capture.detector.rectangle;

import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.ui.camera.DocumentDetectionFrame;
import com.onfido.android.sdk.capture.ui.camera.OverlayMetrics;
import com.onfido.javax.inject.Inject;
import io.reactivex.rxjava3.core.Observable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0007\b\u0007¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J2\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00062\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00062\u0006\u0010\f\u001a\u00020\rH\u0016¨\u0006\u000e"}, d2 = {"Lcom/onfido/android/sdk/capture/detector/rectangle/RectangleDetectorEmpty;", "Lcom/onfido/android/sdk/capture/detector/rectangle/RectangleDetector;", "()V", Constants.KEY_HIDE_CLOSE, "", "observeRectDetection", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/onfido/android/sdk/capture/detector/rectangle/RectDetectionResult;", "overlayMetricsObservable", "Lcom/onfido/android/sdk/capture/ui/camera/OverlayMetrics;", "documentFrameObservable", "Lcom/onfido/android/sdk/capture/ui/camera/DocumentDetectionFrame;", "shouldUseFaceDetection", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class RectangleDetectorEmpty implements RectangleDetector {
    @Inject
    public RectangleDetectorEmpty() {
    }

    @Override // com.onfido.android.sdk.capture.detector.rectangle.RectangleDetector
    public void close() {
    }

    @Override // com.onfido.android.sdk.capture.detector.rectangle.RectangleDetector
    public Observable<RectDetectionResult> observeRectDetection(Observable<OverlayMetrics> overlayMetricsObservable, Observable<DocumentDetectionFrame> documentFrameObservable, boolean shouldUseFaceDetection) {
        Intrinsics.checkNotNullParameter(overlayMetricsObservable, "overlayMetricsObservable");
        Intrinsics.checkNotNullParameter(documentFrameObservable, "documentFrameObservable");
        Observable<RectDetectionResult> observableEmpty = Observable.empty();
        Intrinsics.checkNotNullExpressionValue(observableEmpty, "empty(...)");
        return observableEmpty;
    }
}
