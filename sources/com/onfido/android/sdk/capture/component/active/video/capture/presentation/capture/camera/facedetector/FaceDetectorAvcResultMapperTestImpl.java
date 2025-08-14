package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector;

import androidx.exifinterface.media.ExifInterface;
import com.onfido.android.sdk.capture.common.di.FragmentScope;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.FaceDetectorResult;
import com.onfido.android.sdk.capture.core.OnfidoLauncher;
import com.onfido.android.sdk.capture.internal.util.OnfidoRectF;
import com.onfido.javax.inject.Inject;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@FragmentScope
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0003\b\u0001\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0007\b\u0007¢\u0006\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\n\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\u000b\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ&\u0010\f\u001a\u00020\u00052\f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\u000e2\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\tH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/facedetector/FaceDetectorAvcResultMapperTestImpl;", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/facedetector/FaceDetectorAvcResultMapper;", "()V", "faceDetectorResult", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/facedetector/FaceDetectorResult;", "alignFace", "", "ovalRect", "Lcom/onfido/android/sdk/capture/internal/util/OnfidoRectF;", "completeLeftTurn", "completeRightTurn", "mapToFaceDetectorResult", OnfidoLauncher.KEY_RESULT, "", "faceDetectionRect", "previewRect", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FaceDetectorAvcResultMapperTestImpl<T> implements FaceDetectorAvcResultMapper<T> {
    private FaceDetectorResult faceDetectorResult = FaceDetectorResult.FaceNotDetected.INSTANCE;

    @Inject
    public FaceDetectorAvcResultMapperTestImpl() {
    }

    public final void alignFace(OnfidoRectF ovalRect) {
        Intrinsics.checkNotNullParameter(ovalRect, "ovalRect");
        this.faceDetectorResult = new FaceDetectorResult.FaceDetected(ovalRect, 0.0f, null, 4, null);
    }

    public final void completeLeftTurn(OnfidoRectF ovalRect) {
        Intrinsics.checkNotNullParameter(ovalRect, "ovalRect");
        this.faceDetectorResult = new FaceDetectorResult.FaceDetected(ovalRect, -30.0f, null, 4, null);
    }

    public final void completeRightTurn(OnfidoRectF ovalRect) {
        Intrinsics.checkNotNullParameter(ovalRect, "ovalRect");
        this.faceDetectorResult = new FaceDetectorResult.FaceDetected(ovalRect, 30.0f, null, 4, null);
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.FaceDetectorAvcResultMapper
    public FaceDetectorResult mapToFaceDetectorResult(List<? extends T> result, OnfidoRectF faceDetectionRect, OnfidoRectF previewRect) {
        Intrinsics.checkNotNullParameter(result, "result");
        Intrinsics.checkNotNullParameter(faceDetectionRect, "faceDetectionRect");
        Intrinsics.checkNotNullParameter(previewRect, "previewRect");
        return this.faceDetectorResult;
    }
}
