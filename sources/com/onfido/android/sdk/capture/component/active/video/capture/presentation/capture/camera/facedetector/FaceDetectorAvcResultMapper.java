package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector;

import androidx.exifinterface.media.ExifInterface;
import com.onfido.android.sdk.capture.core.OnfidoLauncher;
import com.onfido.android.sdk.capture.internal.util.OnfidoRectF;
import java.util.List;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b`\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J&\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH&¨\u0006\n"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/facedetector/FaceDetectorAvcResultMapper;", ExifInterface.GPS_DIRECTION_TRUE, "", "mapToFaceDetectorResult", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/facedetector/FaceDetectorResult;", OnfidoLauncher.KEY_RESULT, "", "faceDetectionRect", "Lcom/onfido/android/sdk/capture/internal/util/OnfidoRectF;", "previewRect", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface FaceDetectorAvcResultMapper<T> {
    FaceDetectorResult mapToFaceDetectorResult(List<? extends T> result, OnfidoRectF faceDetectionRect, OnfidoRectF previewRect);
}
