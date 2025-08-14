package com.onfido.android.sdk.capture.ui.camera.liveness.challenges;

import com.clevertap.android.sdk.Constants;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0016"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessPerformedChallenge;", "Ljava/io/Serializable;", "livenessChallenge", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessChallenge;", "endChallengeTimestamp", "", "(Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessChallenge;J)V", "getEndChallengeTimestamp", "()J", "getLivenessChallenge", "()Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessChallenge;", "component1", "component2", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class LivenessPerformedChallenge implements Serializable {
    private final long endChallengeTimestamp;
    private final LivenessChallenge livenessChallenge;

    public LivenessPerformedChallenge(LivenessChallenge livenessChallenge, long j) {
        Intrinsics.checkNotNullParameter(livenessChallenge, "livenessChallenge");
        this.livenessChallenge = livenessChallenge;
        this.endChallengeTimestamp = j;
    }

    public static /* synthetic */ LivenessPerformedChallenge copy$default(LivenessPerformedChallenge livenessPerformedChallenge, LivenessChallenge livenessChallenge, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            livenessChallenge = livenessPerformedChallenge.livenessChallenge;
        }
        if ((i & 2) != 0) {
            j = livenessPerformedChallenge.endChallengeTimestamp;
        }
        return livenessPerformedChallenge.copy(livenessChallenge, j);
    }

    /* renamed from: component1, reason: from getter */
    public final LivenessChallenge getLivenessChallenge() {
        return this.livenessChallenge;
    }

    /* renamed from: component2, reason: from getter */
    public final long getEndChallengeTimestamp() {
        return this.endChallengeTimestamp;
    }

    public final LivenessPerformedChallenge copy(LivenessChallenge livenessChallenge, long endChallengeTimestamp) {
        Intrinsics.checkNotNullParameter(livenessChallenge, "livenessChallenge");
        return new LivenessPerformedChallenge(livenessChallenge, endChallengeTimestamp);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LivenessPerformedChallenge)) {
            return false;
        }
        LivenessPerformedChallenge livenessPerformedChallenge = (LivenessPerformedChallenge) other;
        return Intrinsics.areEqual(this.livenessChallenge, livenessPerformedChallenge.livenessChallenge) && this.endChallengeTimestamp == livenessPerformedChallenge.endChallengeTimestamp;
    }

    public final long getEndChallengeTimestamp() {
        return this.endChallengeTimestamp;
    }

    public final LivenessChallenge getLivenessChallenge() {
        return this.livenessChallenge;
    }

    public int hashCode() {
        return (this.livenessChallenge.hashCode() * 31) + Long.hashCode(this.endChallengeTimestamp);
    }

    public String toString() {
        return "LivenessPerformedChallenge(livenessChallenge=" + this.livenessChallenge + ", endChallengeTimestamp=" + this.endChallengeTimestamp + ')';
    }

    public /* synthetic */ LivenessPerformedChallenge(LivenessChallenge livenessChallenge, long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(livenessChallenge, (i & 2) != 0 ? 0L : j);
    }
}
