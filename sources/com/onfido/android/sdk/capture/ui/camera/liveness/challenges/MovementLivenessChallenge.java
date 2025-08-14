package com.onfido.android.sdk.capture.ui.camera.liveness.challenges;

import com.clevertap.android.sdk.Constants;
import com.google.android.gms.actions.SearchIntents;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/MovementLivenessChallenge;", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessChallenge;", SearchIntents.EXTRA_QUERY, "Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/MovementType;", "(Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/MovementType;)V", "getQuery", "()Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/MovementType;", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class MovementLivenessChallenge extends LivenessChallenge {
    private final MovementType query;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MovementLivenessChallenge(MovementType query) {
        super(LivenessChallengeType.MOVEMENT, null);
        Intrinsics.checkNotNullParameter(query, "query");
        this.query = query;
    }

    public static /* synthetic */ MovementLivenessChallenge copy$default(MovementLivenessChallenge movementLivenessChallenge, MovementType movementType, int i, Object obj) {
        if ((i & 1) != 0) {
            movementType = movementLivenessChallenge.query;
        }
        return movementLivenessChallenge.copy(movementType);
    }

    /* renamed from: component1, reason: from getter */
    public final MovementType getQuery() {
        return this.query;
    }

    public final MovementLivenessChallenge copy(MovementType query) {
        Intrinsics.checkNotNullParameter(query, "query");
        return new MovementLivenessChallenge(query);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof MovementLivenessChallenge) && this.query == ((MovementLivenessChallenge) other).query;
    }

    public final MovementType getQuery() {
        return this.query;
    }

    public int hashCode() {
        return this.query.hashCode();
    }

    public String toString() {
        return "MovementLivenessChallenge(query=" + this.query + ')';
    }
}
