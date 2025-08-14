package com.onfido.hosted.web.module;

import com.onfido.android.sdk.capture.OnfidoConfig;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.OnfidoAnalytics;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.FlowTracker;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;
import kotlinx.serialization.json.Json;

/* loaded from: classes6.dex */
public final class HostedWebModuleViewModel_Factory implements Factory<HostedWebModuleViewModel> {
    private final Provider<OnfidoAnalytics> analyticsProvider;
    private final Provider<FlowTracker> flowTrackerProvider;
    private final Provider<OnfidoConfig> onfidoConfigProvider;
    private final Provider<Json> parserProvider;
    private final Provider<OnfidoRemoteConfig> remoteConfigProvider;

    public HostedWebModuleViewModel_Factory(Provider<OnfidoConfig> provider, Provider<Json> provider2, Provider<OnfidoRemoteConfig> provider3, Provider<FlowTracker> provider4, Provider<OnfidoAnalytics> provider5) {
        this.onfidoConfigProvider = provider;
        this.parserProvider = provider2;
        this.remoteConfigProvider = provider3;
        this.flowTrackerProvider = provider4;
        this.analyticsProvider = provider5;
    }

    public static HostedWebModuleViewModel_Factory create(Provider<OnfidoConfig> provider, Provider<Json> provider2, Provider<OnfidoRemoteConfig> provider3, Provider<FlowTracker> provider4, Provider<OnfidoAnalytics> provider5) {
        return new HostedWebModuleViewModel_Factory(provider, provider2, provider3, provider4, provider5);
    }

    public static HostedWebModuleViewModel newInstance(OnfidoConfig onfidoConfig, Json json, OnfidoRemoteConfig onfidoRemoteConfig, FlowTracker flowTracker, OnfidoAnalytics onfidoAnalytics) {
        return new HostedWebModuleViewModel(onfidoConfig, json, onfidoRemoteConfig, flowTracker, onfidoAnalytics);
    }

    @Override // com.onfido.javax.inject.Provider
    public HostedWebModuleViewModel get() {
        return newInstance(this.onfidoConfigProvider.get(), this.parserProvider.get(), this.remoteConfigProvider.get(), this.flowTrackerProvider.get(), this.analyticsProvider.get());
    }
}
