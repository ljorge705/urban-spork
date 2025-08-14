package com.onfido.android.sdk.capture.component.active.video.capture.presentation.intro;

import com.onfido.android.sdk.capture.common.preferences.SharedPreferencesDataSource;
import com.onfido.android.sdk.capture.component.active.video.capture.domain.repository.ActiveVideoCaptureRepository;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.OnfidoAnalytics;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.dagger.MembersInjector;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class MotionIntroFragment_MembersInjector implements MembersInjector<MotionIntroFragment> {
    private final Provider<ActiveVideoCaptureRepository> activeVideoCaptureRepositoryProvider;
    private final Provider<OnfidoAnalytics> analyticsProvider;
    private final Provider<SchedulersProvider> schedulersProvider;
    private final Provider<SharedPreferencesDataSource> storageProvider;

    public MotionIntroFragment_MembersInjector(Provider<OnfidoAnalytics> provider, Provider<ActiveVideoCaptureRepository> provider2, Provider<SchedulersProvider> provider3, Provider<SharedPreferencesDataSource> provider4) {
        this.analyticsProvider = provider;
        this.activeVideoCaptureRepositoryProvider = provider2;
        this.schedulersProvider = provider3;
        this.storageProvider = provider4;
    }

    public static MembersInjector<MotionIntroFragment> create(Provider<OnfidoAnalytics> provider, Provider<ActiveVideoCaptureRepository> provider2, Provider<SchedulersProvider> provider3, Provider<SharedPreferencesDataSource> provider4) {
        return new MotionIntroFragment_MembersInjector(provider, provider2, provider3, provider4);
    }

    public static void injectActiveVideoCaptureRepository(MotionIntroFragment motionIntroFragment, ActiveVideoCaptureRepository activeVideoCaptureRepository) {
        motionIntroFragment.activeVideoCaptureRepository = activeVideoCaptureRepository;
    }

    public static void injectAnalytics(MotionIntroFragment motionIntroFragment, OnfidoAnalytics onfidoAnalytics) {
        motionIntroFragment.analytics = onfidoAnalytics;
    }

    public static void injectSchedulersProvider(MotionIntroFragment motionIntroFragment, SchedulersProvider schedulersProvider) {
        motionIntroFragment.schedulersProvider = schedulersProvider;
    }

    public static void injectStorage(MotionIntroFragment motionIntroFragment, SharedPreferencesDataSource sharedPreferencesDataSource) {
        motionIntroFragment.storage = sharedPreferencesDataSource;
    }

    @Override // com.onfido.dagger.MembersInjector
    public void injectMembers(MotionIntroFragment motionIntroFragment) {
        injectAnalytics(motionIntroFragment, this.analyticsProvider.get());
        injectActiveVideoCaptureRepository(motionIntroFragment, this.activeVideoCaptureRepositoryProvider.get());
        injectSchedulersProvider(motionIntroFragment, this.schedulersProvider.get());
        injectStorage(motionIntroFragment, this.storageProvider.get());
    }
}
