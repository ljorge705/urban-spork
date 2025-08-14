package com.onfido.android.sdk.capture.detector.rectangle;

import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.ui.camera.DocumentDetectionFrame;
import com.onfido.android.sdk.capture.ui.camera.OverlayMetrics;
import io.reactivex.rxjava3.core.Observable;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b`\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J4\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00052\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00052\b\b\u0002\u0010\u000b\u001a\u00020\fH&¨\u0006\r"}, d2 = {"Lcom/onfido/android/sdk/capture/detector/rectangle/RectangleDetector;", "", Constants.KEY_HIDE_CLOSE, "", "observeRectDetection", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/onfido/android/sdk/capture/detector/rectangle/RectDetectionResult;", "overlayMetricsObservable", "Lcom/onfido/android/sdk/capture/ui/camera/OverlayMetrics;", "documentFrameObservable", "Lcom/onfido/android/sdk/capture/ui/camera/DocumentDetectionFrame;", "shouldUseFaceDetection", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface RectangleDetector {

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class DefaultImpls {
        public static /* synthetic */ Observable observeRectDetection$default(RectangleDetector rectangleDetector, Observable observable, Observable observable2, boolean z, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: observeRectDetection");
            }
            if ((i & 4) != 0) {
                z = false;
            }
            return rectangleDetector.observeRectDetection(observable, observable2, z);
        }
    }

    void close();

    Observable<RectDetectionResult> observeRectDetection(Observable<OverlayMetrics> overlayMetricsObservable, Observable<DocumentDetectionFrame> documentFrameObservable, boolean shouldUseFaceDetection);
}
