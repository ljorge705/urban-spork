package com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers;

import com.onfido.android.sdk.capture.internal.analytics.inhouse.OnfidoAnalytics;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class ScreenTracker_Factory implements Factory<ScreenTracker> {
    private final Provider<OnfidoAnalytics> onfidoAnalyticsProvider;

    public ScreenTracker_Factory(Provider<OnfidoAnalytics> provider) {
        this.onfidoAnalyticsProvider = provider;
    }

    public static ScreenTracker_Factory create(Provider<OnfidoAnalytics> provider) {
        return new ScreenTracker_Factory(provider);
    }

    public static ScreenTracker newInstance(OnfidoAnalytics onfidoAnalytics) {
        return new ScreenTracker(onfidoAnalytics);
    }

    @Override // com.onfido.javax.inject.Provider
    public ScreenTracker get() {
        return newInstance(this.onfidoAnalyticsProvider.get());
    }
}
