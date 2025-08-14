package com.onfido.android.sdk.capture.internal.performance.repository;

import com.onfido.android.sdk.capture.internal.analytics.inhouse.OnfidoAnalyticsMapper;
import com.onfido.android.sdk.capture.network.error.ErrorHandler;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class PerformanceAnalyticsRepository_Factory implements Factory<PerformanceAnalyticsRepository> {
    private final Provider<ErrorHandler> errorHandlerProvider;
    private final Provider<OnfidoAnalyticsMapper> onfidoAnalyticsMapperProvider;
    private final Provider<PerformanceAnalyticsApi> performanceAnalyticsApiProvider;

    public PerformanceAnalyticsRepository_Factory(Provider<PerformanceAnalyticsApi> provider, Provider<OnfidoAnalyticsMapper> provider2, Provider<ErrorHandler> provider3) {
        this.performanceAnalyticsApiProvider = provider;
        this.onfidoAnalyticsMapperProvider = provider2;
        this.errorHandlerProvider = provider3;
    }

    public static PerformanceAnalyticsRepository_Factory create(Provider<PerformanceAnalyticsApi> provider, Provider<OnfidoAnalyticsMapper> provider2, Provider<ErrorHandler> provider3) {
        return new PerformanceAnalyticsRepository_Factory(provider, provider2, provider3);
    }

    public static PerformanceAnalyticsRepository newInstance(PerformanceAnalyticsApi performanceAnalyticsApi, OnfidoAnalyticsMapper onfidoAnalyticsMapper, ErrorHandler errorHandler) {
        return new PerformanceAnalyticsRepository(performanceAnalyticsApi, onfidoAnalyticsMapper, errorHandler);
    }

    @Override // com.onfido.javax.inject.Provider
    public PerformanceAnalyticsRepository get() {
        return newInstance(this.performanceAnalyticsApiProvider.get(), this.onfidoAnalyticsMapperProvider.get(), this.errorHandlerProvider.get());
    }
}
