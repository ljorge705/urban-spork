package com.onfido.android.sdk.capture.ui.camera.liveness.video_view;

import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.ui.camera.liveness.challenges.LivenessChallenge;
import io.sentry.rrweb.RRWebSpanEvent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/liveness/video_view/LivenessReviewChallenge;", "", "livenessChallenge", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessChallenge;", RRWebSpanEvent.JsonKeys.END_TIMESTAMP, "", "(Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessChallenge;J)V", "getEndTimestamp", "()J", "getLivenessChallenge", "()Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessChallenge;", "component1", "component2", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class LivenessReviewChallenge {
    private final long endTimestamp;
    private final LivenessChallenge livenessChallenge;

    public LivenessReviewChallenge(LivenessChallenge livenessChallenge, long j) {
        Intrinsics.checkNotNullParameter(livenessChallenge, "livenessChallenge");
        this.livenessChallenge = livenessChallenge;
        this.endTimestamp = j;
    }

    public static /* synthetic */ LivenessReviewChallenge copy$default(LivenessReviewChallenge livenessReviewChallenge, LivenessChallenge livenessChallenge, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            livenessChallenge = livenessReviewChallenge.livenessChallenge;
        }
        if ((i & 2) != 0) {
            j = livenessReviewChallenge.endTimestamp;
        }
        return livenessReviewChallenge.copy(livenessChallenge, j);
    }

    /* renamed from: component1, reason: from getter */
    public final LivenessChallenge getLivenessChallenge() {
        return this.livenessChallenge;
    }

    /* renamed from: component2, reason: from getter */
    public final long getEndTimestamp() {
        return this.endTimestamp;
    }

    public final LivenessReviewChallenge copy(LivenessChallenge livenessChallenge, long endTimestamp) {
        Intrinsics.checkNotNullParameter(livenessChallenge, "livenessChallenge");
        return new LivenessReviewChallenge(livenessChallenge, endTimestamp);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LivenessReviewChallenge)) {
            return false;
        }
        LivenessReviewChallenge livenessReviewChallenge = (LivenessReviewChallenge) other;
        return Intrinsics.areEqual(this.livenessChallenge, livenessReviewChallenge.livenessChallenge) && this.endTimestamp == livenessReviewChallenge.endTimestamp;
    }

    public final long getEndTimestamp() {
        return this.endTimestamp;
    }

    public final LivenessChallenge getLivenessChallenge() {
        return this.livenessChallenge;
    }

    public int hashCode() {
        return (this.livenessChallenge.hashCode() * 31) + Long.hashCode(this.endTimestamp);
    }

    public String toString() {
        return "LivenessReviewChallenge(livenessChallenge=" + this.livenessChallenge + ", endTimestamp=" + this.endTimestamp + ')';
    }
}
