package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.headturn;

import com.onfido.android.sdk.capture.internal.analytics.inhouse.OnfidoAnalytics;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class HeadTurnGuidanceViewModelImpl_Factory implements Factory<HeadTurnGuidanceViewModelImpl> {
    private final Provider<OnfidoAnalytics> analyticsProvider;
    private final Provider<SchedulersProvider> schedulersProvider;

    public HeadTurnGuidanceViewModelImpl_Factory(Provider<OnfidoAnalytics> provider, Provider<SchedulersProvider> provider2) {
        this.analyticsProvider = provider;
        this.schedulersProvider = provider2;
    }

    public static HeadTurnGuidanceViewModelImpl_Factory create(Provider<OnfidoAnalytics> provider, Provider<SchedulersProvider> provider2) {
        return new HeadTurnGuidanceViewModelImpl_Factory(provider, provider2);
    }

    public static HeadTurnGuidanceViewModelImpl newInstance(OnfidoAnalytics onfidoAnalytics, SchedulersProvider schedulersProvider) {
        return new HeadTurnGuidanceViewModelImpl(onfidoAnalytics, schedulersProvider);
    }

    @Override // com.onfido.javax.inject.Provider
    public HeadTurnGuidanceViewModelImpl get() {
        return newInstance(this.analyticsProvider.get(), this.schedulersProvider.get());
    }
}
