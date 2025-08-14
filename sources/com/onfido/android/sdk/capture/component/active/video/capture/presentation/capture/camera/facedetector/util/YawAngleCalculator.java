package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.util;

import com.onfido.android.sdk.capture.internal.util.OnfidoPointF;
import com.onfido.javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u0007\b\u0007¢\u0006\u0002\u0010\u0002J&\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/facedetector/util/YawAngleCalculator;", "", "()V", "yawAngleMovingAverage", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/facedetector/util/MovingAverage;", "calculate", "", "leftEye", "Lcom/onfido/android/sdk/capture/internal/util/OnfidoPointF;", "rightEye", "leftEar", "rightEar", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class YawAngleCalculator {
    private static final int YAW_ANGLE_MOVING_AVERAGE_SIZE = 3;
    private static final float YAW_ANGLE_MULTIPLIER = 0.65f;
    private final MovingAverage yawAngleMovingAverage = new MovingAverage(3);

    @Inject
    public YawAngleCalculator() {
    }

    public final float calculate(OnfidoPointF leftEye, OnfidoPointF rightEye, OnfidoPointF leftEar, OnfidoPointF rightEar) {
        Intrinsics.checkNotNullParameter(leftEye, "leftEye");
        Intrinsics.checkNotNullParameter(rightEye, "rightEye");
        Intrinsics.checkNotNullParameter(leftEar, "leftEar");
        Intrinsics.checkNotNullParameter(rightEar, "rightEar");
        float x = leftEye.getX() - leftEar.getX();
        float x2 = rightEar.getX() - rightEye.getX();
        return this.yawAngleMovingAverage.add((float) Math.toDegrees(Math.atan((x - x2) / (x + x2)))) * YAW_ANGLE_MULTIPLIER;
    }
}
