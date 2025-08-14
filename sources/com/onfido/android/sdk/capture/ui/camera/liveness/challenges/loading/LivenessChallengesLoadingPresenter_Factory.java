package com.onfido.android.sdk.capture.ui.camera.liveness.challenges.loading;

import com.onfido.android.sdk.capture.internal.service.AnnouncementService;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.android.sdk.capture.ui.camera.liveness.challenges.LivenessChallengesRepository;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class LivenessChallengesLoadingPresenter_Factory implements Factory<LivenessChallengesLoadingPresenter> {
    private final Provider<AnnouncementService> announcementServiceProvider;
    private final Provider<LivenessChallengesRepository> repositoryProvider;
    private final Provider<SchedulersProvider> schedulersProvider;

    public LivenessChallengesLoadingPresenter_Factory(Provider<LivenessChallengesRepository> provider, Provider<AnnouncementService> provider2, Provider<SchedulersProvider> provider3) {
        this.repositoryProvider = provider;
        this.announcementServiceProvider = provider2;
        this.schedulersProvider = provider3;
    }

    public static LivenessChallengesLoadingPresenter_Factory create(Provider<LivenessChallengesRepository> provider, Provider<AnnouncementService> provider2, Provider<SchedulersProvider> provider3) {
        return new LivenessChallengesLoadingPresenter_Factory(provider, provider2, provider3);
    }

    public static LivenessChallengesLoadingPresenter newInstance(LivenessChallengesRepository livenessChallengesRepository, AnnouncementService announcementService, SchedulersProvider schedulersProvider) {
        return new LivenessChallengesLoadingPresenter(livenessChallengesRepository, announcementService, schedulersProvider);
    }

    @Override // com.onfido.javax.inject.Provider
    public LivenessChallengesLoadingPresenter get() {
        return newInstance(this.repositoryProvider.get(), this.announcementServiceProvider.get(), this.schedulersProvider.get());
    }
}
