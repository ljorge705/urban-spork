package com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers;

import com.onfido.android.sdk.capture.internal.analytics.inhouse.OnfidoAnalytics;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class NfcTracker_Factory implements Factory<NfcTracker> {
    private final Provider<OnfidoAnalytics> onfidoAnalyticsProvider;

    public NfcTracker_Factory(Provider<OnfidoAnalytics> provider) {
        this.onfidoAnalyticsProvider = provider;
    }

    public static NfcTracker_Factory create(Provider<OnfidoAnalytics> provider) {
        return new NfcTracker_Factory(provider);
    }

    public static NfcTracker newInstance(OnfidoAnalytics onfidoAnalytics) {
        return new NfcTracker(onfidoAnalytics);
    }

    @Override // com.onfido.javax.inject.Provider
    public NfcTracker get() {
        return newInstance(this.onfidoAnalyticsProvider.get());
    }
}
