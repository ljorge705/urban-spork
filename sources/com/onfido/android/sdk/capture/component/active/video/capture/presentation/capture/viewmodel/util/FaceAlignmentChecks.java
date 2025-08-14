package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.util;

import com.onfido.android.sdk.capture.internal.util.OnfidoRectF;
import com.onfido.javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u0000 \u00152\u00020\u0001:\u0002\u0014\u0015B\u0007\b\u0007¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH\u0002J\u0010\u0010\n\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH\u0002J\u000e\u0010\u000b\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tJ\u001e\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0010\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\u0011\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tJ\u001e\u0010\u0012\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0013\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\t¨\u0006\u0016"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/util/FaceAlignmentChecks;", "", "()V", "getWidthAlignmentPercentage", "", "isRecording", "", "isCenterXAligned", "delta", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/util/FaceAlignmentChecks$CenterAlignmentDelta;", "isCenterYAligned", "isFaceCenterAligned", "isFaceTooBig", "ovalRect", "Lcom/onfido/android/sdk/capture/internal/util/OnfidoRectF;", "faceRect", "isFaceTooLeft", "isFaceTooRight", "isFaceTooSmall", "isFaceTooUp", "CenterAlignmentDelta", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FaceAlignmentChecks {
    public static final float FACE_AND_OVAL_CENTER_ALIGNMENT_DELTA_MULTIPLIER = 2.0f;
    public static final float FACE_AND_OVAL_CENTER_X_ALIGNMENT_DELTA = 100.0f;
    public static final float FACE_AND_OVAL_RECT_WIDTH_ALIGNMENT_PERCENTAGE = 0.1f;
    public static final float FACE_AND_OVAL_RECT_WIDTH_ALIGNMENT_PERCENTAGE_WHILE_RECORDING = 0.5f;
    public static final int FACE_Y_ROTATION_MULTIPLIER = 4;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0010\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0018\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0018\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u0006H\u0002R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\fR\u0011\u0010\r\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0010\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u000e\u0010\u0012\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000bR\u0011\u0010\u0014\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000fR\u0011\u0010\u0016\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000f¨\u0006\u0018"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/util/FaceAlignmentChecks$CenterAlignmentDelta;", "", "ovalRect", "Lcom/onfido/android/sdk/capture/internal/util/OnfidoRectF;", "faceRect", "faceYRotation", "", "isRecording", "", "(Lcom/onfido/android/sdk/capture/internal/util/OnfidoRectF;Lcom/onfido/android/sdk/capture/internal/util/OnfidoRectF;FZ)V", "getFaceRect", "()Lcom/onfido/android/sdk/capture/internal/util/OnfidoRectF;", "()Z", "maxDeltaX", "getMaxDeltaX", "()F", "maxDeltaY", "getMaxDeltaY", "ovalAspectRatio", "getOvalRect", "x", "getX", "y", "getY", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class CenterAlignmentDelta {
        private final OnfidoRectF faceRect;
        private final boolean isRecording;
        private final float maxDeltaX;
        private final float maxDeltaY;
        private final float ovalAspectRatio;
        private final OnfidoRectF ovalRect;
        private final float x;
        private final float y;

        public CenterAlignmentDelta(OnfidoRectF ovalRect, OnfidoRectF faceRect, float f, boolean z) {
            Intrinsics.checkNotNullParameter(ovalRect, "ovalRect");
            Intrinsics.checkNotNullParameter(faceRect, "faceRect");
            this.ovalRect = ovalRect;
            this.faceRect = faceRect;
            this.isRecording = z;
            float fHeight = ovalRect.height() / ovalRect.width();
            this.ovalAspectRatio = fHeight;
            this.x = ovalRect.centerX() - faceRect.centerX();
            this.y = ovalRect.centerY() - faceRect.centerY();
            this.maxDeltaX = getMaxDeltaX(f, z);
            this.maxDeltaY = getMaxDeltaY(z, fHeight);
        }

        private final float getMaxDeltaY(boolean isRecording, float ovalAspectRatio) {
            float f = ovalAspectRatio * 100.0f;
            return isRecording ? f * 2.0f : f;
        }

        public final OnfidoRectF getFaceRect() {
            return this.faceRect;
        }

        public final float getMaxDeltaX() {
            return this.maxDeltaX;
        }

        public final float getMaxDeltaY() {
            return this.maxDeltaY;
        }

        public final OnfidoRectF getOvalRect() {
            return this.ovalRect;
        }

        public final float getX() {
            return this.x;
        }

        public final float getY() {
            return this.y;
        }

        /* renamed from: isRecording, reason: from getter */
        public final boolean getIsRecording() {
            return this.isRecording;
        }

        private final float getMaxDeltaX(float faceYRotation, boolean isRecording) {
            if (isRecording) {
                return (Math.abs(faceYRotation) * 4) + 200.0f;
            }
            return 100.0f;
        }
    }

    @Inject
    public FaceAlignmentChecks() {
    }

    private final float getWidthAlignmentPercentage(boolean isRecording) {
        return isRecording ? 0.5f : 0.1f;
    }

    private final boolean isCenterXAligned(CenterAlignmentDelta delta) {
        return Math.abs(delta.getX()) < delta.getMaxDeltaX();
    }

    private final boolean isCenterYAligned(CenterAlignmentDelta delta) {
        return Math.abs(delta.getY()) < delta.getMaxDeltaY();
    }

    public final boolean isFaceCenterAligned(CenterAlignmentDelta delta) {
        Intrinsics.checkNotNullParameter(delta, "delta");
        return isCenterXAligned(delta) && isCenterYAligned(delta);
    }

    public final boolean isFaceTooBig(OnfidoRectF ovalRect, OnfidoRectF faceRect, boolean isRecording) {
        Intrinsics.checkNotNullParameter(ovalRect, "ovalRect");
        Intrinsics.checkNotNullParameter(faceRect, "faceRect");
        return faceRect.width() > ovalRect.width() * (((float) 1) + getWidthAlignmentPercentage(isRecording));
    }

    public final boolean isFaceTooLeft(CenterAlignmentDelta delta) {
        Intrinsics.checkNotNullParameter(delta, "delta");
        return isCenterYAligned(delta) && !isCenterXAligned(delta) && delta.getX() > 0.0f;
    }

    public final boolean isFaceTooRight(CenterAlignmentDelta delta) {
        Intrinsics.checkNotNullParameter(delta, "delta");
        return isCenterYAligned(delta) && !isCenterXAligned(delta) && delta.getX() < 0.0f;
    }

    public final boolean isFaceTooSmall(OnfidoRectF ovalRect, OnfidoRectF faceRect, boolean isRecording) {
        Intrinsics.checkNotNullParameter(ovalRect, "ovalRect");
        Intrinsics.checkNotNullParameter(faceRect, "faceRect");
        return faceRect.width() < ovalRect.width() * (((float) 1) - getWidthAlignmentPercentage(isRecording));
    }

    public final boolean isFaceTooUp(CenterAlignmentDelta delta) {
        Intrinsics.checkNotNullParameter(delta, "delta");
        return !isCenterYAligned(delta) && delta.getY() > 0.0f;
    }
}
