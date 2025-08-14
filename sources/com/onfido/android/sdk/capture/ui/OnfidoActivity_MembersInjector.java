package com.onfido.android.sdk.capture.ui;

import com.onfido.android.sdk.capture.OnfidoConfig;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.OnfidoAnalytics;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.FlowTracker;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.WaitingScreenTracker;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.nfc.NfcInteractor;
import com.onfido.android.sdk.capture.internal.performance.AggregatedPerformanceAnalytics;
import com.onfido.android.sdk.capture.ui.OnfidoPresenter;
import com.onfido.dagger.MembersInjector;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class OnfidoActivity_MembersInjector implements MembersInjector<OnfidoActivity> {
    private final Provider<FlowTracker> flowTrackerProvider;
    private final Provider<NfcInteractor> nfcInteractorProvider;
    private final Provider<OnfidoAnalytics> onfidoAnalyticsProvider;
    private final Provider<OnfidoConfig> onfidoConfigProvider;
    private final Provider<AggregatedPerformanceAnalytics> performanceAnalyticsProvider;
    private final Provider<OnfidoPresenter.Factory> presenterFactoryProvider;
    private final Provider<OnfidoRemoteConfig> remoteConfigProvider;
    private final Provider<WaitingScreenTracker> waitingScreenTrackerProvider;

    public OnfidoActivity_MembersInjector(Provider<AggregatedPerformanceAnalytics> provider, Provider<OnfidoConfig> provider2, Provider<WaitingScreenTracker> provider3, Provider<OnfidoRemoteConfig> provider4, Provider<FlowTracker> provider5, Provider<OnfidoPresenter.Factory> provider6, Provider<NfcInteractor> provider7, Provider<OnfidoAnalytics> provider8) {
        this.performanceAnalyticsProvider = provider;
        this.onfidoConfigProvider = provider2;
        this.waitingScreenTrackerProvider = provider3;
        this.remoteConfigProvider = provider4;
        this.flowTrackerProvider = provider5;
        this.presenterFactoryProvider = provider6;
        this.nfcInteractorProvider = provider7;
        this.onfidoAnalyticsProvider = provider8;
    }

    public static MembersInjector<OnfidoActivity> create(Provider<AggregatedPerformanceAnalytics> provider, Provider<OnfidoConfig> provider2, Provider<WaitingScreenTracker> provider3, Provider<OnfidoRemoteConfig> provider4, Provider<FlowTracker> provider5, Provider<OnfidoPresenter.Factory> provider6, Provider<NfcInteractor> provider7, Provider<OnfidoAnalytics> provider8) {
        return new OnfidoActivity_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8);
    }

    public static void injectNfcInteractor(OnfidoActivity onfidoActivity, NfcInteractor nfcInteractor) {
        onfidoActivity.nfcInteractor = nfcInteractor;
    }

    public static void injectOnfidoAnalytics(OnfidoActivity onfidoActivity, OnfidoAnalytics onfidoAnalytics) {
        onfidoActivity.onfidoAnalytics = onfidoAnalytics;
    }

    public static void injectPresenterFactory(OnfidoActivity onfidoActivity, OnfidoPresenter.Factory factory) {
        onfidoActivity.presenterFactory = factory;
    }

    @Override // com.onfido.dagger.MembersInjector
    public void injectMembers(OnfidoActivity onfidoActivity) {
        BaseActivity_MembersInjector.injectPerformanceAnalytics(onfidoActivity, this.performanceAnalyticsProvider.get());
        BaseActivity_MembersInjector.injectOnfidoConfig(onfidoActivity, this.onfidoConfigProvider.get());
        BaseActivity_MembersInjector.injectWaitingScreenTracker(onfidoActivity, this.waitingScreenTrackerProvider.get());
        BaseActivity_MembersInjector.injectRemoteConfig(onfidoActivity, this.remoteConfigProvider.get());
        BaseActivity_MembersInjector.injectFlowTracker(onfidoActivity, this.flowTrackerProvider.get());
        injectPresenterFactory(onfidoActivity, this.presenterFactoryProvider.get());
        injectNfcInteractor(onfidoActivity, this.nfcInteractorProvider.get());
        injectOnfidoAnalytics(onfidoActivity, this.onfidoAnalyticsProvider.get());
    }
}
