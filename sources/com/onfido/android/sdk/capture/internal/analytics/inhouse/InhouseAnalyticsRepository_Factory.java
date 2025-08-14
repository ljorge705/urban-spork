package com.onfido.android.sdk.capture.internal.analytics.inhouse;

import com.onfido.android.sdk.capture.internal.analytics.inhouse.network.OnfidoAnalyticsApi;
import com.onfido.android.sdk.capture.network.error.ErrorHandler;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class InhouseAnalyticsRepository_Factory implements Factory<InhouseAnalyticsRepository> {
    private final Provider<OnfidoAnalyticsApi> analyticsApiProvider;
    private final Provider<ErrorHandler> errorHandlerProvider;
    private final Provider<OnfidoAnalyticsMapper> onfidoAnalyticsMapperProvider;

    public InhouseAnalyticsRepository_Factory(Provider<OnfidoAnalyticsApi> provider, Provider<OnfidoAnalyticsMapper> provider2, Provider<ErrorHandler> provider3) {
        this.analyticsApiProvider = provider;
        this.onfidoAnalyticsMapperProvider = provider2;
        this.errorHandlerProvider = provider3;
    }

    public static InhouseAnalyticsRepository_Factory create(Provider<OnfidoAnalyticsApi> provider, Provider<OnfidoAnalyticsMapper> provider2, Provider<ErrorHandler> provider3) {
        return new InhouseAnalyticsRepository_Factory(provider, provider2, provider3);
    }

    public static InhouseAnalyticsRepository newInstance(OnfidoAnalyticsApi onfidoAnalyticsApi, OnfidoAnalyticsMapper onfidoAnalyticsMapper, ErrorHandler errorHandler) {
        return new InhouseAnalyticsRepository(onfidoAnalyticsApi, onfidoAnalyticsMapper, errorHandler);
    }

    @Override // com.onfido.javax.inject.Provider
    public InhouseAnalyticsRepository get() {
        return newInstance(this.analyticsApiProvider.get(), this.onfidoAnalyticsMapperProvider.get(), this.errorHandlerProvider.get());
    }
}
