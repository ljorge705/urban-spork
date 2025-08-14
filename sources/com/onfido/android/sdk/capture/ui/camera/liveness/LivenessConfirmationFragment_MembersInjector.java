package com.onfido.android.sdk.capture.ui.camera.liveness;

import com.onfido.android.sdk.capture.ui.camera.liveness.challenges.LivenessChallengesDrawer;
import com.onfido.dagger.MembersInjector;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class LivenessConfirmationFragment_MembersInjector implements MembersInjector<LivenessConfirmationFragment> {
    private final Provider<LivenessChallengesDrawer.Factory> livenessChallengesDrawerFactoryProvider;
    private final Provider<LivenessConfirmationPresenter> presenterProvider;

    public LivenessConfirmationFragment_MembersInjector(Provider<LivenessConfirmationPresenter> provider, Provider<LivenessChallengesDrawer.Factory> provider2) {
        this.presenterProvider = provider;
        this.livenessChallengesDrawerFactoryProvider = provider2;
    }

    public static MembersInjector<LivenessConfirmationFragment> create(Provider<LivenessConfirmationPresenter> provider, Provider<LivenessChallengesDrawer.Factory> provider2) {
        return new LivenessConfirmationFragment_MembersInjector(provider, provider2);
    }

    public static void injectLivenessChallengesDrawerFactory(LivenessConfirmationFragment livenessConfirmationFragment, LivenessChallengesDrawer.Factory factory) {
        livenessConfirmationFragment.livenessChallengesDrawerFactory = factory;
    }

    public static void injectPresenter(LivenessConfirmationFragment livenessConfirmationFragment, LivenessConfirmationPresenter livenessConfirmationPresenter) {
        livenessConfirmationFragment.presenter = livenessConfirmationPresenter;
    }

    @Override // com.onfido.dagger.MembersInjector
    public void injectMembers(LivenessConfirmationFragment livenessConfirmationFragment) {
        injectPresenter(livenessConfirmationFragment, this.presenterProvider.get());
        injectLivenessChallengesDrawerFactory(livenessConfirmationFragment, this.livenessChallengesDrawerFactoryProvider.get());
    }
}
