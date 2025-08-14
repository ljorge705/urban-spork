package com.onfido.android.sdk.capture.detector.rectangle;

import com.onfido.android.sdk.capture.internal.camera.OnfidoImage;
import com.onfido.android.sdk.capture.internal.util.OnfidoRectF;
import com.onfido.javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0007¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0080\u0002¢\u0006\u0002\b\b¨\u0006\t"}, d2 = {"Lcom/onfido/android/sdk/capture/detector/rectangle/RectangleTransformer;", "", "()V", "invoke", "Lcom/onfido/android/sdk/capture/internal/util/OnfidoRectF;", "rectF", "cropRect", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoImage$CropRect;", "invoke$onfido_capture_sdk_core_release", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class RectangleTransformer {
    @Inject
    public RectangleTransformer() {
    }

    public final OnfidoRectF invoke$onfido_capture_sdk_core_release(OnfidoRectF rectF, OnfidoImage.CropRect cropRect) {
        Intrinsics.checkNotNullParameter(rectF, "rectF");
        Intrinsics.checkNotNullParameter(cropRect, "cropRect");
        float zoomFactor = cropRect.getZoomFactor();
        int verticalOffset = cropRect.getVerticalOffset();
        float horizontalOffset = cropRect.getHorizontalOffset();
        float f = verticalOffset;
        return new OnfidoRectF((rectF.getLeft() - horizontalOffset) * zoomFactor, (rectF.getTop() - f) * zoomFactor, (rectF.getRight() - horizontalOffset) * zoomFactor, (rectF.getBottom() - f) * zoomFactor);
    }
}
