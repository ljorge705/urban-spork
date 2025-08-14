package com.onfido.android.sdk.capture.ui.camera.liveness.challenges;

import com.clevertap.android.sdk.Constants;
import com.google.android.gms.fido.u2f.api.common.ClientData;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0007HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00072\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\r¨\u0006\u0017"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessChallengeInfo;", "", "index", "", ClientData.KEY_CHALLENGE, "Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessChallenge;", "isLastChallenge", "", "(ILcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessChallenge;Z)V", "getChallenge", "()Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessChallenge;", "getIndex", "()I", "()Z", "component1", "component2", "component3", Constants.COPY_TYPE, "equals", "other", "hashCode", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class LivenessChallengeInfo {
    private final LivenessChallenge challenge;
    private final int index;
    private final boolean isLastChallenge;

    public LivenessChallengeInfo(int i, LivenessChallenge challenge, boolean z) {
        Intrinsics.checkNotNullParameter(challenge, "challenge");
        this.index = i;
        this.challenge = challenge;
        this.isLastChallenge = z;
    }

    public static /* synthetic */ LivenessChallengeInfo copy$default(LivenessChallengeInfo livenessChallengeInfo, int i, LivenessChallenge livenessChallenge, boolean z, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = livenessChallengeInfo.index;
        }
        if ((i2 & 2) != 0) {
            livenessChallenge = livenessChallengeInfo.challenge;
        }
        if ((i2 & 4) != 0) {
            z = livenessChallengeInfo.isLastChallenge;
        }
        return livenessChallengeInfo.copy(i, livenessChallenge, z);
    }

    /* renamed from: component1, reason: from getter */
    public final int getIndex() {
        return this.index;
    }

    /* renamed from: component2, reason: from getter */
    public final LivenessChallenge getChallenge() {
        return this.challenge;
    }

    /* renamed from: component3, reason: from getter */
    public final boolean getIsLastChallenge() {
        return this.isLastChallenge;
    }

    public final LivenessChallengeInfo copy(int index, LivenessChallenge challenge, boolean isLastChallenge) {
        Intrinsics.checkNotNullParameter(challenge, "challenge");
        return new LivenessChallengeInfo(index, challenge, isLastChallenge);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LivenessChallengeInfo)) {
            return false;
        }
        LivenessChallengeInfo livenessChallengeInfo = (LivenessChallengeInfo) other;
        return this.index == livenessChallengeInfo.index && Intrinsics.areEqual(this.challenge, livenessChallengeInfo.challenge) && this.isLastChallenge == livenessChallengeInfo.isLastChallenge;
    }

    public final LivenessChallenge getChallenge() {
        return this.challenge;
    }

    public final int getIndex() {
        return this.index;
    }

    public int hashCode() {
        return (((Integer.hashCode(this.index) * 31) + this.challenge.hashCode()) * 31) + Boolean.hashCode(this.isLastChallenge);
    }

    public final boolean isLastChallenge() {
        return this.isLastChallenge;
    }

    public String toString() {
        return "LivenessChallengeInfo(index=" + this.index + ", challenge=" + this.challenge + ", isLastChallenge=" + this.isLastChallenge + ')';
    }
}
