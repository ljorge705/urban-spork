package com.onfido.android.sdk.capture.internal.usecase;

import com.onfido.android.sdk.capture.detector.face.FaceDetectionResult;
import com.onfido.android.sdk.capture.detector.face.FaceDetector;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.javax.inject.Inject;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.functions.Predicate;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0002J\r\u0010\n\u001a\u00020\u000bH\u0000¢\u0006\u0002\b\fJ\f\u0010\r\u001a\u00020\u000e*\u00020\u000fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/usecase/FaceProcessingUseCase;", "", "faceDetectorGoogle", "Lcom/onfido/android/sdk/capture/detector/face/FaceDetector;", "schedulersProvider", "Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;", "(Lcom/onfido/android/sdk/capture/detector/face/FaceDetector;Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;)V", "getFaceDetectionFlowable", "Lio/reactivex/rxjava3/core/Flowable;", "Lcom/onfido/android/sdk/capture/detector/face/FaceDetectionResult;", "observeFaceOut", "Lio/reactivex/rxjava3/core/Completable;", "observeFaceOut$onfido_capture_sdk_core_release", "isInsideVideoFrame", "", "Lcom/onfido/android/sdk/capture/detector/face/FaceDetectionResult$FaceDetected;", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FaceProcessingUseCase {
    private static final Companion Companion = new Companion(null);

    @Deprecated
    public static final long FACE_OUT_TIMEOUT_S = 3;

    @Deprecated
    public static final float MINIMUM_RATIO_DIFFERENCE_THRESHOLD = 0.1f;
    private final FaceDetector faceDetectorGoogle;
    private final SchedulersProvider schedulersProvider;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/usecase/FaceProcessingUseCase$Companion;", "", "()V", "FACE_OUT_TIMEOUT_S", "", "MINIMUM_RATIO_DIFFERENCE_THRESHOLD", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Inject
    public FaceProcessingUseCase(FaceDetector faceDetectorGoogle, SchedulersProvider schedulersProvider) {
        Intrinsics.checkNotNullParameter(faceDetectorGoogle, "faceDetectorGoogle");
        Intrinsics.checkNotNullParameter(schedulersProvider, "schedulersProvider");
        this.faceDetectorGoogle = faceDetectorGoogle;
        this.schedulersProvider = schedulersProvider;
    }

    private final Flowable<FaceDetectionResult> getFaceDetectionFlowable() {
        return this.faceDetectorGoogle.observeFaceTracking();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isInsideVideoFrame(FaceDetectionResult.FaceDetected faceDetected) {
        return faceDetected.getFrameRect().contains$onfido_capture_sdk_core_release(faceDetected.getFaceRect()) && ((Math.abs(((float) 1) - (((float) faceDetected.getFaceRect().width$onfido_capture_sdk_core_release()) / ((float) faceDetected.getFaceRect().height$onfido_capture_sdk_core_release()))) > 0.1f ? 1 : (Math.abs(((float) 1) - (((float) faceDetected.getFaceRect().width$onfido_capture_sdk_core_release()) / ((float) faceDetected.getFaceRect().height$onfido_capture_sdk_core_release()))) == 0.1f ? 0 : -1)) < 0);
    }

    public final Completable observeFaceOut$onfido_capture_sdk_core_release() {
        Flowable<U> flowableOfType = getFaceDetectionFlowable().ofType(FaceDetectionResult.FaceDetected.class);
        Intrinsics.checkNotNullExpressionValue(flowableOfType, "ofType(...)");
        Completable completableIgnoreElement = flowableOfType.filter(new Predicate() { // from class: com.onfido.android.sdk.capture.internal.usecase.FaceProcessingUseCase$observeFaceOut$1
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(FaceDetectionResult.FaceDetected it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return this.this$0.isInsideVideoFrame(it);
            }
        }).throttleWithTimeout(3L, TimeUnit.SECONDS, this.schedulersProvider.getTimer()).firstElement().ignoreElement();
        Intrinsics.checkNotNullExpressionValue(completableIgnoreElement, "ignoreElement(...)");
        return completableIgnoreElement;
    }
}
