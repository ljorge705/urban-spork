package com.onfido.android.sdk.capture.ui.camera.liveness.challenges.loading;

import com.onfido.dagger.MembersInjector;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class LivenessChallengesLoadingView_MembersInjector implements MembersInjector<LivenessChallengesLoadingView> {
    private final Provider<LivenessChallengesLoadingPresenter> presenterProvider;

    public LivenessChallengesLoadingView_MembersInjector(Provider<LivenessChallengesLoadingPresenter> provider) {
        this.presenterProvider = provider;
    }

    public static MembersInjector<LivenessChallengesLoadingView> create(Provider<LivenessChallengesLoadingPresenter> provider) {
        return new LivenessChallengesLoadingView_MembersInjector(provider);
    }

    public static void injectPresenter(LivenessChallengesLoadingView livenessChallengesLoadingView, LivenessChallengesLoadingPresenter livenessChallengesLoadingPresenter) {
        livenessChallengesLoadingView.presenter = livenessChallengesLoadingPresenter;
    }

    @Override // com.onfido.dagger.MembersInjector
    public void injectMembers(LivenessChallengesLoadingView livenessChallengesLoadingView) {
        injectPresenter(livenessChallengesLoadingView, this.presenterProvider.get());
    }
}
