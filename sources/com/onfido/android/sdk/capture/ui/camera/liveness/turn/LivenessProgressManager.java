package com.onfido.android.sdk.capture.ui.camera.liveness.turn;

import com.BV.LinearGradient.LinearGradientManager;
import com.onfido.android.sdk.capture.ui.camera.liveness.challenges.MovementType;
import com.onfido.javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0016\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0007\b\u0007¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\u0004H\u0016J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0006H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0092\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/liveness/turn/LivenessProgressManager;", "", "()V", "isFirstMovementHalf", "", "angleToProgress", "", LinearGradientManager.PROP_ANGLE, "movementType", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/MovementType;", "isFirstHalf", "updateStateIfNeeded", "", "progress", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class LivenessProgressManager {
    private static final int ANGLE_MULTIPLIER = 2;
    public static final float FIRST_HALF_TURN_LEFT_MAX_ANGLE = -60.0f;
    public static final float FIRST_HALF_TURN_RIGHT_MAX_ANGLE = 60.0f;
    private static final float HALF_MAX_PROGRESS = 1.0f;
    private static final float SECOND_HALF_TURN_LEFT_MAX_ANGLE = -30.0f;
    private static final float SECOND_HALF_TURN_RIGHT_MAX_ANGLE = 30.0f;
    private boolean isFirstMovementHalf = true;

    @Inject
    public LivenessProgressManager() {
    }

    public float angleToProgress(float angle, MovementType movementType) {
        float f;
        float fMax;
        float f2;
        Intrinsics.checkNotNullParameter(movementType, "movementType");
        boolean z = this.isFirstMovementHalf;
        if (z && movementType == MovementType.TURN_RIGHT) {
            f2 = (angle * 2) / 60.0f;
        } else {
            if (!z || movementType != MovementType.TURN_LEFT) {
                if (!z && movementType == MovementType.TURN_RIGHT) {
                    float fMin = 60.0f - Math.min(angle * 2, 60.0f);
                    f = 30.0f;
                    fMax = Math.min(fMin, 30.0f);
                } else {
                    if (z || movementType != MovementType.TURN_LEFT) {
                        return 0.0f;
                    }
                    float fMax2 = (-60.0f) - Math.max(angle * 2, -60.0f);
                    f = SECOND_HALF_TURN_LEFT_MAX_ANGLE;
                    fMax = Math.max(fMax2, SECOND_HALF_TURN_LEFT_MAX_ANGLE);
                }
                return Math.min(fMax / f, 1.0f);
            }
            f2 = (angle * 2) / (-60.0f);
        }
        return Math.min(f2, 1.0f);
    }

    /* renamed from: isFirstHalf, reason: from getter */
    public boolean getIsFirstMovementHalf() {
        return this.isFirstMovementHalf;
    }

    public void updateStateIfNeeded(float progress) {
        if (progress == 1.0f && getIsFirstMovementHalf()) {
            this.isFirstMovementHalf = false;
        }
    }
}
