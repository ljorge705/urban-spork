package com.onfido.android.sdk.capture.common.di;

import android.os.ResultReceiver;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.mappers.PublicEventMapper;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.utils.EventCache;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.performance.repository.PerformanceAnalyticsNetwork;
import com.onfido.android.sdk.capture.internal.performance.repository.PerformanceAnalyticsRepository;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class SdkModule_ProvidePerformanceAnalyticsNetwork$onfido_capture_sdk_core_releaseFactory implements Factory<PerformanceAnalyticsNetwork> {
    private final Provider<EventCache> eventCacheProvider;
    private final SdkModule module;
    private final Provider<ResultReceiver> onfidoAnalyticsEventListenerProvider;
    private final Provider<PerformanceAnalyticsRepository> performanceAnalyticsRepositoryProvider;
    private final Provider<PublicEventMapper> publicEventMapperProvider;
    private final Provider<OnfidoRemoteConfig> remoteConfigProvider;
    private final Provider<SchedulersProvider> schedulersProvider;

    public SdkModule_ProvidePerformanceAnalyticsNetwork$onfido_capture_sdk_core_releaseFactory(SdkModule sdkModule, Provider<PerformanceAnalyticsRepository> provider, Provider<SchedulersProvider> provider2, Provider<EventCache> provider3, Provider<OnfidoRemoteConfig> provider4, Provider<PublicEventMapper> provider5, Provider<ResultReceiver> provider6) {
        this.module = sdkModule;
        this.performanceAnalyticsRepositoryProvider = provider;
        this.schedulersProvider = provider2;
        this.eventCacheProvider = provider3;
        this.remoteConfigProvider = provider4;
        this.publicEventMapperProvider = provider5;
        this.onfidoAnalyticsEventListenerProvider = provider6;
    }

    public static SdkModule_ProvidePerformanceAnalyticsNetwork$onfido_capture_sdk_core_releaseFactory create(SdkModule sdkModule, Provider<PerformanceAnalyticsRepository> provider, Provider<SchedulersProvider> provider2, Provider<EventCache> provider3, Provider<OnfidoRemoteConfig> provider4, Provider<PublicEventMapper> provider5, Provider<ResultReceiver> provider6) {
        return new SdkModule_ProvidePerformanceAnalyticsNetwork$onfido_capture_sdk_core_releaseFactory(sdkModule, provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static PerformanceAnalyticsNetwork providePerformanceAnalyticsNetwork$onfido_capture_sdk_core_release(SdkModule sdkModule, PerformanceAnalyticsRepository performanceAnalyticsRepository, SchedulersProvider schedulersProvider, EventCache eventCache, OnfidoRemoteConfig onfidoRemoteConfig, PublicEventMapper publicEventMapper, ResultReceiver resultReceiver) {
        return (PerformanceAnalyticsNetwork) Preconditions.checkNotNullFromProvides(sdkModule.providePerformanceAnalyticsNetwork$onfido_capture_sdk_core_release(performanceAnalyticsRepository, schedulersProvider, eventCache, onfidoRemoteConfig, publicEventMapper, resultReceiver));
    }

    @Override // com.onfido.javax.inject.Provider
    public PerformanceAnalyticsNetwork get() {
        return providePerformanceAnalyticsNetwork$onfido_capture_sdk_core_release(this.module, this.performanceAnalyticsRepositoryProvider.get(), this.schedulersProvider.get(), this.eventCacheProvider.get(), this.remoteConfigProvider.get(), this.publicEventMapperProvider.get(), this.onfidoAnalyticsEventListenerProvider.get());
    }
}
