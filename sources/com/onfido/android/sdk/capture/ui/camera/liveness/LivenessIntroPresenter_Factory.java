package com.onfido.android.sdk.capture.ui.camera.liveness;

import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.LivenessTracker;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.android.sdk.capture.ui.camera.liveness.intro.LivenessIntroVideoRepository;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class LivenessIntroPresenter_Factory implements Factory<LivenessIntroPresenter> {
    private final Provider<LivenessIntroVideoRepository> livenessIntroVideoRepositoryProvider;
    private final Provider<LivenessTracker> livenessTrackerProvider;
    private final Provider<SchedulersProvider> schedulersProvider;

    public LivenessIntroPresenter_Factory(Provider<LivenessTracker> provider, Provider<LivenessIntroVideoRepository> provider2, Provider<SchedulersProvider> provider3) {
        this.livenessTrackerProvider = provider;
        this.livenessIntroVideoRepositoryProvider = provider2;
        this.schedulersProvider = provider3;
    }

    public static LivenessIntroPresenter_Factory create(Provider<LivenessTracker> provider, Provider<LivenessIntroVideoRepository> provider2, Provider<SchedulersProvider> provider3) {
        return new LivenessIntroPresenter_Factory(provider, provider2, provider3);
    }

    public static LivenessIntroPresenter newInstance(LivenessTracker livenessTracker, LivenessIntroVideoRepository livenessIntroVideoRepository, SchedulersProvider schedulersProvider) {
        return new LivenessIntroPresenter(livenessTracker, livenessIntroVideoRepository, schedulersProvider);
    }

    @Override // com.onfido.javax.inject.Provider
    public LivenessIntroPresenter get() {
        return newInstance(this.livenessTrackerProvider.get(), this.livenessIntroVideoRepositoryProvider.get(), this.schedulersProvider.get());
    }
}
