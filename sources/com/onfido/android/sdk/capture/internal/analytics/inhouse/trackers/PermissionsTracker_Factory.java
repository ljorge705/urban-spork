package com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers;

import com.onfido.android.sdk.capture.internal.analytics.inhouse.OnfidoAnalytics;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class PermissionsTracker_Factory implements Factory<PermissionsTracker> {
    private final Provider<OnfidoAnalytics> onfidoAnalyticsProvider;

    public PermissionsTracker_Factory(Provider<OnfidoAnalytics> provider) {
        this.onfidoAnalyticsProvider = provider;
    }

    public static PermissionsTracker_Factory create(Provider<OnfidoAnalytics> provider) {
        return new PermissionsTracker_Factory(provider);
    }

    public static PermissionsTracker newInstance(OnfidoAnalytics onfidoAnalytics) {
        return new PermissionsTracker(onfidoAnalytics);
    }

    @Override // com.onfido.javax.inject.Provider
    public PermissionsTracker get() {
        return newInstance(this.onfidoAnalyticsProvider.get());
    }
}
