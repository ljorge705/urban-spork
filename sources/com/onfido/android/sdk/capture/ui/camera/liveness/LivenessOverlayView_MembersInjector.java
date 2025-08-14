package com.onfido.android.sdk.capture.ui.camera.liveness;

import com.onfido.android.sdk.capture.ui.camera.liveness.challenges.LivenessChallengesDrawer;
import com.onfido.dagger.MembersInjector;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class LivenessOverlayView_MembersInjector implements MembersInjector<LivenessOverlayView> {
    private final Provider<LivenessChallengesDrawer.Factory> livenessChallengesDrawerFactoryProvider;
    private final Provider<LivenessOverlayPresenter> presenterProvider;

    public LivenessOverlayView_MembersInjector(Provider<LivenessChallengesDrawer.Factory> provider, Provider<LivenessOverlayPresenter> provider2) {
        this.livenessChallengesDrawerFactoryProvider = provider;
        this.presenterProvider = provider2;
    }

    public static MembersInjector<LivenessOverlayView> create(Provider<LivenessChallengesDrawer.Factory> provider, Provider<LivenessOverlayPresenter> provider2) {
        return new LivenessOverlayView_MembersInjector(provider, provider2);
    }

    public static void injectLivenessChallengesDrawerFactory(LivenessOverlayView livenessOverlayView, LivenessChallengesDrawer.Factory factory) {
        livenessOverlayView.livenessChallengesDrawerFactory = factory;
    }

    public static void injectPresenter(LivenessOverlayView livenessOverlayView, LivenessOverlayPresenter livenessOverlayPresenter) {
        livenessOverlayView.presenter = livenessOverlayPresenter;
    }

    @Override // com.onfido.dagger.MembersInjector
    public void injectMembers(LivenessOverlayView livenessOverlayView) {
        injectLivenessChallengesDrawerFactory(livenessOverlayView, this.livenessChallengesDrawerFactoryProvider.get());
        injectPresenter(livenessOverlayView, this.presenterProvider.get());
    }
}
