package com.onfido.android.sdk.capture.ui.camera.liveness.challenges;

import android.content.Context;
import com.onfido.android.sdk.capture.ui.camera.liveness.challenges.LivenessChallengesDrawer;
import com.onfido.dagger.internal.InstanceFactory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class LivenessChallengesDrawer_Factory_Impl implements LivenessChallengesDrawer.Factory {
    private final C0675LivenessChallengesDrawer_Factory delegateFactory;

    LivenessChallengesDrawer_Factory_Impl(C0675LivenessChallengesDrawer_Factory c0675LivenessChallengesDrawer_Factory) {
        this.delegateFactory = c0675LivenessChallengesDrawer_Factory;
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.liveness.challenges.LivenessChallengesDrawer.Factory
    public LivenessChallengesDrawer create(Context context) {
        return this.delegateFactory.get(context);
    }

    public static Provider<LivenessChallengesDrawer.Factory> create(C0675LivenessChallengesDrawer_Factory c0675LivenessChallengesDrawer_Factory) {
        return InstanceFactory.create(new LivenessChallengesDrawer_Factory_Impl(c0675LivenessChallengesDrawer_Factory));
    }
}
