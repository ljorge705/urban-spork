package com.onfido.android.sdk.capture.internal.performance;

import android.content.Context;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class AggregatedPerformanceAnalytics_Factory implements Factory<AggregatedPerformanceAnalytics> {
    private final Provider<Context> contextProvider;
    private final Provider<PerformanceAnalyticsImpl> performanceAnalyticsImplProvider;

    public AggregatedPerformanceAnalytics_Factory(Provider<PerformanceAnalyticsImpl> provider, Provider<Context> provider2) {
        this.performanceAnalyticsImplProvider = provider;
        this.contextProvider = provider2;
    }

    public static AggregatedPerformanceAnalytics_Factory create(Provider<PerformanceAnalyticsImpl> provider, Provider<Context> provider2) {
        return new AggregatedPerformanceAnalytics_Factory(provider, provider2);
    }

    public static AggregatedPerformanceAnalytics newInstance(PerformanceAnalyticsImpl performanceAnalyticsImpl, Context context) {
        return new AggregatedPerformanceAnalytics(performanceAnalyticsImpl, context);
    }

    @Override // com.onfido.javax.inject.Provider
    public AggregatedPerformanceAnalytics get() {
        return newInstance(this.performanceAnalyticsImplProvider.get(), this.contextProvider.get());
    }
}
