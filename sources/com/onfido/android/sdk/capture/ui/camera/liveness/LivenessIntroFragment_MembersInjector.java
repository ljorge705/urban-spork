package com.onfido.android.sdk.capture.ui.camera.liveness;

import com.onfido.dagger.MembersInjector;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class LivenessIntroFragment_MembersInjector implements MembersInjector<LivenessIntroFragment> {
    private final Provider<LivenessIntroPresenter> presenterProvider;

    public LivenessIntroFragment_MembersInjector(Provider<LivenessIntroPresenter> provider) {
        this.presenterProvider = provider;
    }

    public static MembersInjector<LivenessIntroFragment> create(Provider<LivenessIntroPresenter> provider) {
        return new LivenessIntroFragment_MembersInjector(provider);
    }

    public static void injectPresenter(LivenessIntroFragment livenessIntroFragment, LivenessIntroPresenter livenessIntroPresenter) {
        livenessIntroFragment.presenter = livenessIntroPresenter;
    }

    @Override // com.onfido.dagger.MembersInjector
    public void injectMembers(LivenessIntroFragment livenessIntroFragment) {
        injectPresenter(livenessIntroFragment, this.presenterProvider.get());
    }
}
