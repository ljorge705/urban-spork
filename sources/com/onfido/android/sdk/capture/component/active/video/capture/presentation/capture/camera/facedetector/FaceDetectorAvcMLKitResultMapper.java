package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector;

import android.graphics.Rect;
import com.google.mlkit.vision.face.Face;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.FaceDetectorResult;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.util.CoordinatesTransformer;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.util.MovingAverage;
import com.onfido.android.sdk.capture.core.OnfidoLauncher;
import com.onfido.android.sdk.capture.internal.util.OnfidoRectF;
import com.onfido.javax.inject.Inject;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 \u000f2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u000fB\u000f\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J&\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/facedetector/FaceDetectorAvcMLKitResultMapper;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/facedetector/FaceDetectorAvcResultMapper;", "Lcom/google/mlkit/vision/face/Face;", "coordinatesTransformer", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/facedetector/util/CoordinatesTransformer;", "(Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/facedetector/util/CoordinatesTransformer;)V", "yawAngleMovingAverage", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/facedetector/util/MovingAverage;", "mapToFaceDetectorResult", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/facedetector/FaceDetectorResult;", OnfidoLauncher.KEY_RESULT, "", "faceDetectionRect", "Lcom/onfido/android/sdk/capture/internal/util/OnfidoRectF;", "previewRect", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FaceDetectorAvcMLKitResultMapper implements FaceDetectorAvcResultMapper<Face> {
    private static final float FACE_RECT_SCALE_FACTOR = 0.84f;
    private static final int YAW_ANGLE_MOVING_AVERAGE_SIZE = 3;
    private final CoordinatesTransformer coordinatesTransformer;
    private final MovingAverage yawAngleMovingAverage;

    @Inject
    public FaceDetectorAvcMLKitResultMapper(CoordinatesTransformer coordinatesTransformer) {
        Intrinsics.checkNotNullParameter(coordinatesTransformer, "coordinatesTransformer");
        this.coordinatesTransformer = coordinatesTransformer;
        this.yawAngleMovingAverage = new MovingAverage(3);
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.FaceDetectorAvcResultMapper
    public FaceDetectorResult mapToFaceDetectorResult(List<? extends Face> result, OnfidoRectF faceDetectionRect, OnfidoRectF previewRect) {
        Intrinsics.checkNotNullParameter(result, "result");
        Intrinsics.checkNotNullParameter(faceDetectionRect, "faceDetectionRect");
        Intrinsics.checkNotNullParameter(previewRect, "previewRect");
        if (!(!result.isEmpty())) {
            return FaceDetectorResult.FaceNotDetected.INSTANCE;
        }
        Face face = (Face) CollectionsKt.first((List) result);
        CoordinatesTransformer coordinatesTransformer = this.coordinatesTransformer;
        OnfidoRectF.Companion companion = OnfidoRectF.INSTANCE;
        Rect boundingBox = face.getBoundingBox();
        Intrinsics.checkNotNullExpressionValue(boundingBox, "getBoundingBox(...)");
        return new FaceDetectorResult.FaceDetected(coordinatesTransformer.toPreviewCoordinates(companion.toOnfidoRectF(boundingBox).scaleAboutCenter(FACE_RECT_SCALE_FACTOR), faceDetectionRect, previewRect), this.yawAngleMovingAverage.add(face.getHeadEulerAngleY()), null, 4, null);
    }
}
