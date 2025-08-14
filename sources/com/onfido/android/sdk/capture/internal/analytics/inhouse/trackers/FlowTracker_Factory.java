package com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers;

import com.onfido.android.sdk.capture.internal.analytics.inhouse.OnfidoAnalytics;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class FlowTracker_Factory implements Factory<FlowTracker> {
    private final Provider<OnfidoAnalytics> onfidoAnalyticsProvider;

    public FlowTracker_Factory(Provider<OnfidoAnalytics> provider) {
        this.onfidoAnalyticsProvider = provider;
    }

    public static FlowTracker_Factory create(Provider<OnfidoAnalytics> provider) {
        return new FlowTracker_Factory(provider);
    }

    public static FlowTracker newInstance(OnfidoAnalytics onfidoAnalytics) {
        return new FlowTracker(onfidoAnalytics);
    }

    @Override // com.onfido.javax.inject.Provider
    public FlowTracker get() {
        return newInstance(this.onfidoAnalyticsProvider.get());
    }
}
