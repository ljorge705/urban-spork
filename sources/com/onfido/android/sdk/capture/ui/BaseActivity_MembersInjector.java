package com.onfido.android.sdk.capture.ui;

import com.onfido.android.sdk.capture.OnfidoConfig;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.FlowTracker;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.WaitingScreenTracker;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.performance.AggregatedPerformanceAnalytics;
import com.onfido.dagger.MembersInjector;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class BaseActivity_MembersInjector implements MembersInjector<BaseActivity> {
    private final Provider<FlowTracker> flowTrackerProvider;
    private final Provider<OnfidoConfig> onfidoConfigProvider;
    private final Provider<AggregatedPerformanceAnalytics> performanceAnalyticsProvider;
    private final Provider<OnfidoRemoteConfig> remoteConfigProvider;
    private final Provider<WaitingScreenTracker> waitingScreenTrackerProvider;

    public BaseActivity_MembersInjector(Provider<AggregatedPerformanceAnalytics> provider, Provider<OnfidoConfig> provider2, Provider<WaitingScreenTracker> provider3, Provider<OnfidoRemoteConfig> provider4, Provider<FlowTracker> provider5) {
        this.performanceAnalyticsProvider = provider;
        this.onfidoConfigProvider = provider2;
        this.waitingScreenTrackerProvider = provider3;
        this.remoteConfigProvider = provider4;
        this.flowTrackerProvider = provider5;
    }

    public static MembersInjector<BaseActivity> create(Provider<AggregatedPerformanceAnalytics> provider, Provider<OnfidoConfig> provider2, Provider<WaitingScreenTracker> provider3, Provider<OnfidoRemoteConfig> provider4, Provider<FlowTracker> provider5) {
        return new BaseActivity_MembersInjector(provider, provider2, provider3, provider4, provider5);
    }

    public static void injectFlowTracker(BaseActivity baseActivity, FlowTracker flowTracker) {
        baseActivity.flowTracker = flowTracker;
    }

    public static void injectOnfidoConfig(BaseActivity baseActivity, OnfidoConfig onfidoConfig) {
        baseActivity.onfidoConfig = onfidoConfig;
    }

    public static void injectPerformanceAnalytics(BaseActivity baseActivity, AggregatedPerformanceAnalytics aggregatedPerformanceAnalytics) {
        baseActivity.performanceAnalytics = aggregatedPerformanceAnalytics;
    }

    public static void injectRemoteConfig(BaseActivity baseActivity, OnfidoRemoteConfig onfidoRemoteConfig) {
        baseActivity.remoteConfig = onfidoRemoteConfig;
    }

    public static void injectWaitingScreenTracker(BaseActivity baseActivity, WaitingScreenTracker waitingScreenTracker) {
        baseActivity.waitingScreenTracker = waitingScreenTracker;
    }

    @Override // com.onfido.dagger.MembersInjector
    public void injectMembers(BaseActivity baseActivity) {
        injectPerformanceAnalytics(baseActivity, this.performanceAnalyticsProvider.get());
        injectOnfidoConfig(baseActivity, this.onfidoConfigProvider.get());
        injectWaitingScreenTracker(baseActivity, this.waitingScreenTrackerProvider.get());
        injectRemoteConfig(baseActivity, this.remoteConfigProvider.get());
        injectFlowTracker(baseActivity, this.flowTrackerProvider.get());
    }
}
