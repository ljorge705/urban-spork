package com.onfido.android.sdk.capture.ui.camera.liveness.challenges;

import com.onfido.dagger.internal.Factory;

/* loaded from: classes2.dex */
public final class LivenessChallengesTransformer_Factory implements Factory<LivenessChallengesTransformer> {

    private static final class InstanceHolder {
        private static final LivenessChallengesTransformer_Factory INSTANCE = new LivenessChallengesTransformer_Factory();

        private InstanceHolder() {
        }
    }

    public static LivenessChallengesTransformer_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static LivenessChallengesTransformer newInstance() {
        return new LivenessChallengesTransformer();
    }

    @Override // com.onfido.javax.inject.Provider
    public LivenessChallengesTransformer get() {
        return newInstance();
    }
}
