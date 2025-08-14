package com.onfido.android.sdk.capture.ui.camera.liveness.challenges;

import com.clevertap.android.sdk.Constants;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessChallengesViewModel;", "", "id", "", "challenges", "", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessChallenge;", "(Ljava/lang/String;Ljava/util/List;)V", "getChallenges", "()Ljava/util/List;", "getId", "()Ljava/lang/String;", "component1", "component2", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "", "toString", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class LivenessChallengesViewModel {
    private final List<LivenessChallenge> challenges;
    private final String id;

    /* JADX WARN: Multi-variable type inference failed */
    public LivenessChallengesViewModel(String id, List<? extends LivenessChallenge> challenges) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(challenges, "challenges");
        this.id = id;
        this.challenges = challenges;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ LivenessChallengesViewModel copy$default(LivenessChallengesViewModel livenessChallengesViewModel, String str, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = livenessChallengesViewModel.id;
        }
        if ((i & 2) != 0) {
            list = livenessChallengesViewModel.challenges;
        }
        return livenessChallengesViewModel.copy(str, list);
    }

    /* renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    public final List<LivenessChallenge> component2() {
        return this.challenges;
    }

    public final LivenessChallengesViewModel copy(String id, List<? extends LivenessChallenge> challenges) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(challenges, "challenges");
        return new LivenessChallengesViewModel(id, challenges);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LivenessChallengesViewModel)) {
            return false;
        }
        LivenessChallengesViewModel livenessChallengesViewModel = (LivenessChallengesViewModel) other;
        return Intrinsics.areEqual(this.id, livenessChallengesViewModel.id) && Intrinsics.areEqual(this.challenges, livenessChallengesViewModel.challenges);
    }

    public final List<LivenessChallenge> getChallenges() {
        return this.challenges;
    }

    public final String getId() {
        return this.id;
    }

    public int hashCode() {
        return (this.id.hashCode() * 31) + this.challenges.hashCode();
    }

    public String toString() {
        return "LivenessChallengesViewModel(id=" + this.id + ", challenges=" + this.challenges + ')';
    }
}
