package com.onfido.android.sdk.capture.ui.camera.liveness.challenges;

import com.onfido.android.sdk.capture.network.OnfidoApiService;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class LivenessChallengesRepository_Factory implements Factory<LivenessChallengesRepository> {
    private final Provider<LivenessChallengesTransformer> livenessChallengesTransformerProvider;
    private final Provider<OnfidoApiService> onfidoAPIProvider;

    public LivenessChallengesRepository_Factory(Provider<OnfidoApiService> provider, Provider<LivenessChallengesTransformer> provider2) {
        this.onfidoAPIProvider = provider;
        this.livenessChallengesTransformerProvider = provider2;
    }

    public static LivenessChallengesRepository_Factory create(Provider<OnfidoApiService> provider, Provider<LivenessChallengesTransformer> provider2) {
        return new LivenessChallengesRepository_Factory(provider, provider2);
    }

    public static LivenessChallengesRepository newInstance(OnfidoApiService onfidoApiService, LivenessChallengesTransformer livenessChallengesTransformer) {
        return new LivenessChallengesRepository(onfidoApiService, livenessChallengesTransformer);
    }

    @Override // com.onfido.javax.inject.Provider
    public LivenessChallengesRepository get() {
        return newInstance(this.onfidoAPIProvider.get(), this.livenessChallengesTransformerProvider.get());
    }
}
