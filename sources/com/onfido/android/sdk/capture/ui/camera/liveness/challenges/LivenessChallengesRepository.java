package com.onfido.android.sdk.capture.ui.camera.liveness.challenges;

import com.onfido.android.sdk.capture.network.OnfidoApiService;
import com.onfido.api.client.data.LiveVideoChallenges;
import com.onfido.javax.inject.Inject;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Function;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0010\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0092\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0092\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessChallengesRepository;", "", "onfidoAPI", "Lcom/onfido/android/sdk/capture/network/OnfidoApiService;", "livenessChallengesTransformer", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessChallengesTransformer;", "(Lcom/onfido/android/sdk/capture/network/OnfidoApiService;Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessChallengesTransformer;)V", "get", "Lio/reactivex/rxjava3/core/Single;", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessChallengesViewModel;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class LivenessChallengesRepository {
    private final LivenessChallengesTransformer livenessChallengesTransformer;
    private final OnfidoApiService onfidoAPI;

    @Inject
    public LivenessChallengesRepository(OnfidoApiService onfidoAPI, LivenessChallengesTransformer livenessChallengesTransformer) {
        Intrinsics.checkNotNullParameter(onfidoAPI, "onfidoAPI");
        Intrinsics.checkNotNullParameter(livenessChallengesTransformer, "livenessChallengesTransformer");
        this.onfidoAPI = onfidoAPI;
        this.livenessChallengesTransformer = livenessChallengesTransformer;
    }

    public Single<LivenessChallengesViewModel> get() {
        Single map = this.onfidoAPI.liveVideoChallenges$onfido_capture_sdk_core_release().map(new Function() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.challenges.LivenessChallengesRepository.get.1
            @Override // io.reactivex.rxjava3.functions.Function
            public final LivenessChallengesViewModel apply(LiveVideoChallenges it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return LivenessChallengesRepository.this.livenessChallengesTransformer.transform(it);
            }
        });
        Intrinsics.checkNotNullExpressionValue(map, "map(...)");
        return map;
    }
}
