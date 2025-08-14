package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.util;

import com.onfido.android.sdk.capture.internal.util.OnfidoRectF;
import com.onfido.javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0007\b\u0007¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0004¨\u0006\b"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/facedetector/util/CoordinatesTransformer;", "", "()V", "toPreviewCoordinates", "Lcom/onfido/android/sdk/capture/internal/util/OnfidoRectF;", "rect", "faceDetectionRect", "previewRect", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class CoordinatesTransformer {
    @Inject
    public CoordinatesTransformer() {
    }

    public final OnfidoRectF toPreviewCoordinates(OnfidoRectF rect, OnfidoRectF faceDetectionRect, OnfidoRectF previewRect) {
        Intrinsics.checkNotNullParameter(rect, "rect");
        Intrinsics.checkNotNullParameter(faceDetectionRect, "faceDetectionRect");
        Intrinsics.checkNotNullParameter(previewRect, "previewRect");
        float fHeight = previewRect.height() / faceDetectionRect.height();
        float f = 2;
        float fWidth = (previewRect.width() - (faceDetectionRect.width() * fHeight)) / f;
        float fHeight2 = (previewRect.height() - (faceDetectionRect.height() * fHeight)) / f;
        return new OnfidoRectF((rect.getLeft() * fHeight) + fWidth, (rect.getTop() * fHeight) + fHeight2, (rect.getRight() * fHeight) + fWidth, (rect.getBottom() * fHeight) + fHeight2);
    }
}
