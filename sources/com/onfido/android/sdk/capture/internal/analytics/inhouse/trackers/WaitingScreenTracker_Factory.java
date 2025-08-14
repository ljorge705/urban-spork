package com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers;

import com.onfido.android.sdk.capture.internal.analytics.inhouse.OnfidoAnalytics;
import com.onfido.android.sdk.capture.utils.TimeProvider;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class WaitingScreenTracker_Factory implements Factory<WaitingScreenTracker> {
    private final Provider<OnfidoAnalytics> onfidoAnalyticsProvider;
    private final Provider<TimeProvider> timeProvider;

    public WaitingScreenTracker_Factory(Provider<OnfidoAnalytics> provider, Provider<TimeProvider> provider2) {
        this.onfidoAnalyticsProvider = provider;
        this.timeProvider = provider2;
    }

    public static WaitingScreenTracker_Factory create(Provider<OnfidoAnalytics> provider, Provider<TimeProvider> provider2) {
        return new WaitingScreenTracker_Factory(provider, provider2);
    }

    public static WaitingScreenTracker newInstance(OnfidoAnalytics onfidoAnalytics, TimeProvider timeProvider) {
        return new WaitingScreenTracker(onfidoAnalytics, timeProvider);
    }

    @Override // com.onfido.javax.inject.Provider
    public WaitingScreenTracker get() {
        return newInstance(this.onfidoAnalyticsProvider.get(), this.timeProvider.get());
    }
}
