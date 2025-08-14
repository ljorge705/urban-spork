package com.onfido.android.sdk.capture.internal.usecase;

import com.onfido.android.sdk.capture.detector.rectangle.RectDetectionResult;
import com.onfido.android.sdk.capture.internal.usecase.DocumentPosition;
import com.onfido.android.sdk.capture.internal.util.OnfidoRectF;
import com.onfido.javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\b\u0000\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u0007\b\u0007¢\u0006\u0002\u0010\u0002J\u0016\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0080\u0002¢\u0006\u0002\b\fJ&\u0010\b\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000eH\u0080\u0002¢\u0006\u0002\b\fJ\u0010\u0010\u0011\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0014\u0010\u0012\u001a\u00020\u0004*\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u000eH\u0002J\u0014\u0010\u0014\u001a\u00020\u0004*\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u000eH\u0002J\u0014\u0010\u0015\u001a\u00020\u0004*\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u000eH\u0002J\u0014\u0010\u0016\u001a\u00020\u0004*\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u000eH\u0002J\u0014\u0010\u0017\u001a\u00020\u0004*\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u000eH\u0002J\u0014\u0010\u0018\u001a\u00020\u0004*\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u000eH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/usecase/DocumentPositionUseCase;", "", "()V", "isOnBottom", "", "isOnLeft", "isOnRight", "isOnTop", "invoke", "Lcom/onfido/android/sdk/capture/internal/usecase/DocumentPosition;", "rectDetectionResult", "Lcom/onfido/android/sdk/capture/detector/rectangle/RectDetectionResult;", "invoke$onfido_capture_sdk_core_release", "documentBoundingBox", "Lcom/onfido/android/sdk/capture/internal/util/OnfidoRectF;", "minimumCaptureArea", "maximumCaptureArea", "mapToDocumentPosition", "checkBottom", "outerPreviewLimit", "checkLeft", "checkRight", "checkTop", "isTooBottom", "isTooTop", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DocumentPositionUseCase {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int INNER_OFFSET_DP = -70;
    private static final float OFFSET_FOR_SIDES_DP = 15.0f;
    private static final int OFFSET_TOP_LIMIT_DP = 25;
    private static final int OUTER_OFFSET_DP = 10;
    private boolean isOnBottom;
    private boolean isOnLeft;
    private boolean isOnRight;
    private boolean isOnTop;

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\t\u001a\u00020\n*\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0004H\u0002J\u0014\u0010\f\u001a\u00020\r*\u00020\n2\u0006\u0010\u000e\u001a\u00020\nH\u0002J\u0014\u0010\u000f\u001a\u00020\r*\u00020\n2\u0006\u0010\u0010\u001a\u00020\nH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/usecase/DocumentPositionUseCase$Companion;", "", "()V", "INNER_OFFSET_DP", "", "OFFSET_FOR_SIDES_DP", "", "OFFSET_TOP_LIMIT_DP", "OUTER_OFFSET_DP", "addOffset", "Lcom/onfido/android/sdk/capture/internal/util/OnfidoRectF;", "offset", "isClose", "", "outerPreviewLimit", "isFar", "innerPreviewLimit", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final OnfidoRectF addOffset(OnfidoRectF onfidoRectF, int i) {
            float f = i;
            return new OnfidoRectF(onfidoRectF.getLeft() - f, onfidoRectF.getTop() - f, onfidoRectF.getRight() + f, onfidoRectF.getBottom() + f);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean isClose(OnfidoRectF onfidoRectF, OnfidoRectF onfidoRectF2) {
            return onfidoRectF.area() > onfidoRectF2.area();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean isFar(OnfidoRectF onfidoRectF, OnfidoRectF onfidoRectF2) {
            return onfidoRectF.area() < onfidoRectF2.area();
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Inject
    public DocumentPositionUseCase() {
    }

    private final boolean checkBottom(OnfidoRectF onfidoRectF, OnfidoRectF onfidoRectF2) {
        boolean z = onfidoRectF.getBottom() >= onfidoRectF2.getBottom() - (this.isOnBottom ? OFFSET_FOR_SIDES_DP : 0.0f);
        this.isOnBottom = z;
        return z;
    }

    private final boolean checkLeft(OnfidoRectF onfidoRectF, OnfidoRectF onfidoRectF2) {
        boolean z = onfidoRectF.getLeft() <= onfidoRectF2.getLeft() + (this.isOnLeft ? OFFSET_FOR_SIDES_DP : 0.0f);
        this.isOnLeft = z;
        return z;
    }

    private final boolean checkRight(OnfidoRectF onfidoRectF, OnfidoRectF onfidoRectF2) {
        boolean z = onfidoRectF.getRight() >= onfidoRectF2.getRight() - (this.isOnRight ? OFFSET_FOR_SIDES_DP : 0.0f);
        this.isOnRight = z;
        return z;
    }

    private final boolean checkTop(OnfidoRectF onfidoRectF, OnfidoRectF onfidoRectF2) {
        boolean z = onfidoRectF.getTop() <= onfidoRectF2.getTop() + (this.isOnTop ? OFFSET_FOR_SIDES_DP : 0.0f);
        this.isOnTop = z;
        return z;
    }

    private final boolean isTooBottom(OnfidoRectF onfidoRectF, OnfidoRectF onfidoRectF2) {
        return onfidoRectF.getBottom() > onfidoRectF2.getBottom() + ((float) 25);
    }

    private final boolean isTooTop(OnfidoRectF onfidoRectF, OnfidoRectF onfidoRectF2) {
        return onfidoRectF.getTop() < onfidoRectF2.getTop() - ((float) 25);
    }

    private final DocumentPosition mapToDocumentPosition(RectDetectionResult rectDetectionResult) {
        if (!(rectDetectionResult instanceof RectDetectionResult.RectDetected)) {
            return DocumentPosition.DocumentNotFound.INSTANCE;
        }
        Companion companion = INSTANCE;
        RectDetectionResult.RectDetected rectDetected = (RectDetectionResult.RectDetected) rectDetectionResult;
        OnfidoRectF onfidoRectFAddOffset = companion.addOffset(rectDetected.getInnerPreviewLimit(), INNER_OFFSET_DP);
        OnfidoRectF onfidoRectFAddOffset2 = companion.addOffset(rectDetected.getOuterPreviewLimit(), 10);
        boolean zCheckLeft = checkLeft(rectDetected.getRect(), onfidoRectFAddOffset2);
        boolean zCheckRight = checkRight(rectDetected.getRect(), onfidoRectFAddOffset2);
        return (companion.isClose(rectDetected.getRect(), onfidoRectFAddOffset2) || (zCheckLeft && zCheckRight)) ? DocumentPosition.DocumentClose.INSTANCE : zCheckRight ? DocumentPosition.DocumentTooRight.INSTANCE : zCheckLeft ? DocumentPosition.DocumentTooLeft.INSTANCE : isTooBottom(rectDetected.getRect(), onfidoRectFAddOffset2) ? DocumentPosition.DocumentTooBottom.INSTANCE : isTooTop(rectDetected.getRect(), onfidoRectFAddOffset2) ? DocumentPosition.DocumentTooTop.INSTANCE : checkBottom(rectDetected.getRect(), onfidoRectFAddOffset2) ? DocumentPosition.DocumentBottom.INSTANCE : checkTop(rectDetected.getRect(), onfidoRectFAddOffset2) ? DocumentPosition.DocumentTop.INSTANCE : companion.isFar(rectDetected.getRect(), onfidoRectFAddOffset) ? DocumentPosition.DocumentFar.INSTANCE : DocumentPosition.DocumentNormal.INSTANCE;
    }

    public final DocumentPosition invoke$onfido_capture_sdk_core_release(RectDetectionResult rectDetectionResult) {
        Intrinsics.checkNotNullParameter(rectDetectionResult, "rectDetectionResult");
        return mapToDocumentPosition(rectDetectionResult);
    }

    public final DocumentPosition invoke$onfido_capture_sdk_core_release(OnfidoRectF documentBoundingBox, OnfidoRectF minimumCaptureArea, OnfidoRectF maximumCaptureArea) {
        Intrinsics.checkNotNullParameter(documentBoundingBox, "documentBoundingBox");
        Intrinsics.checkNotNullParameter(minimumCaptureArea, "minimumCaptureArea");
        Intrinsics.checkNotNullParameter(maximumCaptureArea, "maximumCaptureArea");
        return mapToDocumentPosition(new RectDetectionResult.RectDetected(documentBoundingBox, minimumCaptureArea, maximumCaptureArea));
    }
}
