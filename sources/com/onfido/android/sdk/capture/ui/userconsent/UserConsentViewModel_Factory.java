package com.onfido.android.sdk.capture.ui.userconsent;

import com.onfido.android.sdk.capture.OnfidoConfig;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.FlowTracker;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.ScreenTracker;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.WaitingScreenTracker;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class UserConsentViewModel_Factory implements Factory<UserConsentViewModel> {
    private final Provider<FlowTracker> flowTrackerProvider;
    private final Provider<OnfidoConfig> onfidoConfigProvider;
    private final Provider<OnfidoRemoteConfig> remoteConfigProvider;
    private final Provider<SchedulersProvider> schedulersProvider;
    private final Provider<ScreenTracker> screenTrackerProvider;
    private final Provider<UserConsentRepository> userConsentRepositoryProvider;
    private final Provider<WaitingScreenTracker> waitingScreenTrackerProvider;

    public UserConsentViewModel_Factory(Provider<UserConsentRepository> provider, Provider<ScreenTracker> provider2, Provider<SchedulersProvider> provider3, Provider<WaitingScreenTracker> provider4, Provider<OnfidoRemoteConfig> provider5, Provider<OnfidoConfig> provider6, Provider<FlowTracker> provider7) {
        this.userConsentRepositoryProvider = provider;
        this.screenTrackerProvider = provider2;
        this.schedulersProvider = provider3;
        this.waitingScreenTrackerProvider = provider4;
        this.remoteConfigProvider = provider5;
        this.onfidoConfigProvider = provider6;
        this.flowTrackerProvider = provider7;
    }

    public static UserConsentViewModel_Factory create(Provider<UserConsentRepository> provider, Provider<ScreenTracker> provider2, Provider<SchedulersProvider> provider3, Provider<WaitingScreenTracker> provider4, Provider<OnfidoRemoteConfig> provider5, Provider<OnfidoConfig> provider6, Provider<FlowTracker> provider7) {
        return new UserConsentViewModel_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    public static UserConsentViewModel newInstance(UserConsentRepository userConsentRepository, ScreenTracker screenTracker, SchedulersProvider schedulersProvider, WaitingScreenTracker waitingScreenTracker, OnfidoRemoteConfig onfidoRemoteConfig, OnfidoConfig onfidoConfig, FlowTracker flowTracker) {
        return new UserConsentViewModel(userConsentRepository, screenTracker, schedulersProvider, waitingScreenTracker, onfidoRemoteConfig, onfidoConfig, flowTracker);
    }

    @Override // com.onfido.javax.inject.Provider
    public UserConsentViewModel get() {
        return newInstance(this.userConsentRepositoryProvider.get(), this.screenTrackerProvider.get(), this.schedulersProvider.get(), this.waitingScreenTrackerProvider.get(), this.remoteConfigProvider.get(), this.onfidoConfigProvider.get(), this.flowTrackerProvider.get());
    }
}
