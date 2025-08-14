package com.onfido.android.sdk.capture.common.di;

import android.os.ResultReceiver;
import com.onfido.android.sdk.capture.OnfidoConfig;
import com.onfido.android.sdk.capture.config.EnterpriseConfig;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.InhouseAnalyticsRepository;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.OnfidoAnalytics;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.mappers.PublicEventMapper;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.utils.EventCache;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class SdkModule_ProvideOnfidoAnalytics$onfido_capture_sdk_core_releaseFactory implements Factory<OnfidoAnalytics> {
    private final Provider<InhouseAnalyticsRepository> analyticsRepositoryProvider;
    private final Provider<EnterpriseConfig> enterpriseConfigProvider;
    private final Provider<EventCache> eventCacheProvider;
    private final SdkModule module;
    private final Provider<ResultReceiver> onfidoAnalyticsEventListenerProvider;
    private final Provider<OnfidoConfig> onfidoConfigProvider;
    private final Provider<PublicEventMapper> publicEventMapperProvider;
    private final Provider<OnfidoRemoteConfig> remoteConfigProvider;
    private final Provider<SchedulersProvider> schedulersProvider;

    public SdkModule_ProvideOnfidoAnalytics$onfido_capture_sdk_core_releaseFactory(SdkModule sdkModule, Provider<InhouseAnalyticsRepository> provider, Provider<OnfidoConfig> provider2, Provider<SchedulersProvider> provider3, Provider<EventCache> provider4, Provider<EnterpriseConfig> provider5, Provider<OnfidoRemoteConfig> provider6, Provider<PublicEventMapper> provider7, Provider<ResultReceiver> provider8) {
        this.module = sdkModule;
        this.analyticsRepositoryProvider = provider;
        this.onfidoConfigProvider = provider2;
        this.schedulersProvider = provider3;
        this.eventCacheProvider = provider4;
        this.enterpriseConfigProvider = provider5;
        this.remoteConfigProvider = provider6;
        this.publicEventMapperProvider = provider7;
        this.onfidoAnalyticsEventListenerProvider = provider8;
    }

    public static SdkModule_ProvideOnfidoAnalytics$onfido_capture_sdk_core_releaseFactory create(SdkModule sdkModule, Provider<InhouseAnalyticsRepository> provider, Provider<OnfidoConfig> provider2, Provider<SchedulersProvider> provider3, Provider<EventCache> provider4, Provider<EnterpriseConfig> provider5, Provider<OnfidoRemoteConfig> provider6, Provider<PublicEventMapper> provider7, Provider<ResultReceiver> provider8) {
        return new SdkModule_ProvideOnfidoAnalytics$onfido_capture_sdk_core_releaseFactory(sdkModule, provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8);
    }

    public static OnfidoAnalytics provideOnfidoAnalytics$onfido_capture_sdk_core_release(SdkModule sdkModule, InhouseAnalyticsRepository inhouseAnalyticsRepository, OnfidoConfig onfidoConfig, SchedulersProvider schedulersProvider, EventCache eventCache, EnterpriseConfig enterpriseConfig, OnfidoRemoteConfig onfidoRemoteConfig, PublicEventMapper publicEventMapper, ResultReceiver resultReceiver) {
        return (OnfidoAnalytics) Preconditions.checkNotNullFromProvides(sdkModule.provideOnfidoAnalytics$onfido_capture_sdk_core_release(inhouseAnalyticsRepository, onfidoConfig, schedulersProvider, eventCache, enterpriseConfig, onfidoRemoteConfig, publicEventMapper, resultReceiver));
    }

    @Override // com.onfido.javax.inject.Provider
    public OnfidoAnalytics get() {
        return provideOnfidoAnalytics$onfido_capture_sdk_core_release(this.module, this.analyticsRepositoryProvider.get(), this.onfidoConfigProvider.get(), this.schedulersProvider.get(), this.eventCacheProvider.get(), this.enterpriseConfigProvider.get(), this.remoteConfigProvider.get(), this.publicEventMapperProvider.get(), this.onfidoAnalyticsEventListenerProvider.get());
    }
}
