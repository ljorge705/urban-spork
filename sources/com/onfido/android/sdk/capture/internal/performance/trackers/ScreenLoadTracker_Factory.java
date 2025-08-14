package com.onfido.android.sdk.capture.internal.performance.trackers;

import com.onfido.android.sdk.capture.internal.performance.AggregatedPerformanceAnalytics;
import com.onfido.android.sdk.capture.utils.TimeProvider;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class ScreenLoadTracker_Factory implements Factory<ScreenLoadTracker> {
    private final Provider<AggregatedPerformanceAnalytics> performanceAnalyticsProvider;
    private final Provider<TimeProvider> timeProvider;

    public ScreenLoadTracker_Factory(Provider<AggregatedPerformanceAnalytics> provider, Provider<TimeProvider> provider2) {
        this.performanceAnalyticsProvider = provider;
        this.timeProvider = provider2;
    }

    public static ScreenLoadTracker_Factory create(Provider<AggregatedPerformanceAnalytics> provider, Provider<TimeProvider> provider2) {
        return new ScreenLoadTracker_Factory(provider, provider2);
    }

    public static ScreenLoadTracker newInstance(AggregatedPerformanceAnalytics aggregatedPerformanceAnalytics, TimeProvider timeProvider) {
        return new ScreenLoadTracker(aggregatedPerformanceAnalytics, timeProvider);
    }

    @Override // com.onfido.javax.inject.Provider
    public ScreenLoadTracker get() {
        return newInstance(this.performanceAnalyticsProvider.get(), this.timeProvider.get());
    }
}
