package com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers;

import com.onfido.android.sdk.capture.internal.analytics.inhouse.OnfidoAnalytics;
import com.onfido.android.sdk.capture.internal.nfc.NfcInteractor;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class CaptureTracker_Factory implements Factory<CaptureTracker> {
    private final Provider<NfcInteractor> nfcInteractorProvider;
    private final Provider<OnfidoAnalytics> onfidoAnalyticsProvider;

    public CaptureTracker_Factory(Provider<OnfidoAnalytics> provider, Provider<NfcInteractor> provider2) {
        this.onfidoAnalyticsProvider = provider;
        this.nfcInteractorProvider = provider2;
    }

    public static CaptureTracker_Factory create(Provider<OnfidoAnalytics> provider, Provider<NfcInteractor> provider2) {
        return new CaptureTracker_Factory(provider, provider2);
    }

    public static CaptureTracker newInstance(OnfidoAnalytics onfidoAnalytics, NfcInteractor nfcInteractor) {
        return new CaptureTracker(onfidoAnalytics, nfcInteractor);
    }

    @Override // com.onfido.javax.inject.Provider
    public CaptureTracker get() {
        return newInstance(this.onfidoAnalyticsProvider.get(), this.nfcInteractorProvider.get());
    }
}
