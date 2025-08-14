package com.onfido.android.sdk.capture.ui.camera.liveness.challenges;

import io.reactivex.rxjava3.core.Single;
import kotlin.Metadata;
import retrofit2.http.GET;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H'¨\u0006\u0005"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessChallengesAPI;", "", "getLivenessChallenges", "Lio/reactivex/rxjava3/core/Single;", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessChallengesViewModel;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface LivenessChallengesAPI {
    @GET
    Single<LivenessChallengesViewModel> getLivenessChallenges();
}
