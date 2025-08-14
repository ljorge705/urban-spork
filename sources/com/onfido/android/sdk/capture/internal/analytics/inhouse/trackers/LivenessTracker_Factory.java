package com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers;

import com.onfido.android.sdk.capture.internal.analytics.inhouse.OnfidoAnalytics;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class LivenessTracker_Factory implements Factory<LivenessTracker> {
    private final Provider<OnfidoAnalytics> onfidoAnalyticsProvider;

    public LivenessTracker_Factory(Provider<OnfidoAnalytics> provider) {
        this.onfidoAnalyticsProvider = provider;
    }

    public static LivenessTracker_Factory create(Provider<OnfidoAnalytics> provider) {
        return new LivenessTracker_Factory(provider);
    }

    public static LivenessTracker newInstance(OnfidoAnalytics onfidoAnalytics) {
        return new LivenessTracker(onfidoAnalytics);
    }

    @Override // com.onfido.javax.inject.Provider
    public LivenessTracker get() {
        return newInstance(this.onfidoAnalyticsProvider.get());
    }
}
