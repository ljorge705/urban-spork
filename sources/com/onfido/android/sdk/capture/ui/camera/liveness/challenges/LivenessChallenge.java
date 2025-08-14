package com.onfido.android.sdk.capture.ui.camera.liveness.challenges;

import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0002\u0007\b¨\u0006\t"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessChallenge;", "Ljava/io/Serializable;", "type", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessChallengeType;", "(Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessChallengeType;)V", "getType", "()Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessChallengeType;", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/MovementLivenessChallenge;", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/ReciteLivenessChallenge;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public abstract class LivenessChallenge implements Serializable {
    private final LivenessChallengeType type;

    private LivenessChallenge(LivenessChallengeType livenessChallengeType) {
        this.type = livenessChallengeType;
    }

    public final LivenessChallengeType getType() {
        return this.type;
    }

    public /* synthetic */ LivenessChallenge(LivenessChallengeType livenessChallengeType, DefaultConstructorMarker defaultConstructorMarker) {
        this(livenessChallengeType);
    }
}
