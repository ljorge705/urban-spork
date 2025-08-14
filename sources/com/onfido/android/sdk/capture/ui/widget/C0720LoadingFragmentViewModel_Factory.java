package com.onfido.android.sdk.capture.ui.widget;

import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.WaitingScreenTracker;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.android.sdk.capture.utils.LoadingFragment;
import com.onfido.javax.inject.Provider;

/* renamed from: com.onfido.android.sdk.capture.ui.widget.LoadingFragmentViewModel_Factory, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0720LoadingFragmentViewModel_Factory {
    private final Provider<OnfidoRemoteConfig> remoteConfigProvider;
    private final Provider<SchedulersProvider> schedulersProvider;
    private final Provider<WaitingScreenTracker> waitingScreenTrackerProvider;

    public C0720LoadingFragmentViewModel_Factory(Provider<OnfidoRemoteConfig> provider, Provider<SchedulersProvider> provider2, Provider<WaitingScreenTracker> provider3) {
        this.remoteConfigProvider = provider;
        this.schedulersProvider = provider2;
        this.waitingScreenTrackerProvider = provider3;
    }

    public static C0720LoadingFragmentViewModel_Factory create(Provider<OnfidoRemoteConfig> provider, Provider<SchedulersProvider> provider2, Provider<WaitingScreenTracker> provider3) {
        return new C0720LoadingFragmentViewModel_Factory(provider, provider2, provider3);
    }

    public static LoadingFragmentViewModel newInstance(OnfidoRemoteConfig onfidoRemoteConfig, SchedulersProvider schedulersProvider, WaitingScreenTracker waitingScreenTracker, LoadingFragment.Companion.DialogMode dialogMode) {
        return new LoadingFragmentViewModel(onfidoRemoteConfig, schedulersProvider, waitingScreenTracker, dialogMode);
    }

    public LoadingFragmentViewModel get(LoadingFragment.Companion.DialogMode dialogMode) {
        return newInstance(this.remoteConfigProvider.get(), this.schedulersProvider.get(), this.waitingScreenTrackerProvider.get(), dialogMode);
    }
}
