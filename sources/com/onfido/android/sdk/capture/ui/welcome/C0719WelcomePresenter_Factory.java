package com.onfido.android.sdk.capture.ui.welcome;

import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.ScreenTracker;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.performance.trackers.ScreenLoadTracker;
import com.onfido.android.sdk.capture.ui.options.WelcomeScreenOptions;
import com.onfido.javax.inject.Provider;

/* renamed from: com.onfido.android.sdk.capture.ui.welcome.WelcomePresenter_Factory, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0719WelcomePresenter_Factory {
    private final Provider<OnfidoRemoteConfig> onfidoRemoteConfigProvider;
    private final Provider<ScreenLoadTracker> screenLoadTrackerProvider;
    private final Provider<ScreenTracker> screenTrackerProvider;

    public C0719WelcomePresenter_Factory(Provider<OnfidoRemoteConfig> provider, Provider<ScreenTracker> provider2, Provider<ScreenLoadTracker> provider3) {
        this.onfidoRemoteConfigProvider = provider;
        this.screenTrackerProvider = provider2;
        this.screenLoadTrackerProvider = provider3;
    }

    public static C0719WelcomePresenter_Factory create(Provider<OnfidoRemoteConfig> provider, Provider<ScreenTracker> provider2, Provider<ScreenLoadTracker> provider3) {
        return new C0719WelcomePresenter_Factory(provider, provider2, provider3);
    }

    public static WelcomePresenter newInstance(OnfidoRemoteConfig onfidoRemoteConfig, ScreenTracker screenTracker, ScreenLoadTracker screenLoadTracker, WelcomeScreenOptions welcomeScreenOptions) {
        return new WelcomePresenter(onfidoRemoteConfig, screenTracker, screenLoadTracker, welcomeScreenOptions);
    }

    public WelcomePresenter get(WelcomeScreenOptions welcomeScreenOptions) {
        return newInstance(this.onfidoRemoteConfigProvider.get(), this.screenTrackerProvider.get(), this.screenLoadTrackerProvider.get(), welcomeScreenOptions);
    }
}
