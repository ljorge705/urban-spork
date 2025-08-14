package com.onfido.android.sdk.capture.ui.camera.document;

import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.CaptureTracker;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.NfcTracker;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.WaitingScreenTracker;
import com.onfido.android.sdk.capture.internal.performance.AggregatedPerformanceAnalytics;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class DocumentCaptureTracker_Factory implements Factory<DocumentCaptureTracker> {
    private final Provider<CaptureTracker> captureTrackerProvider;
    private final Provider<NfcTracker> nfcTrackerProvider;
    private final Provider<AggregatedPerformanceAnalytics> performanceAnalyticsProvider;
    private final Provider<WaitingScreenTracker> waitingScreenTrackerProvider;

    public DocumentCaptureTracker_Factory(Provider<CaptureTracker> provider, Provider<WaitingScreenTracker> provider2, Provider<NfcTracker> provider3, Provider<AggregatedPerformanceAnalytics> provider4) {
        this.captureTrackerProvider = provider;
        this.waitingScreenTrackerProvider = provider2;
        this.nfcTrackerProvider = provider3;
        this.performanceAnalyticsProvider = provider4;
    }

    public static DocumentCaptureTracker_Factory create(Provider<CaptureTracker> provider, Provider<WaitingScreenTracker> provider2, Provider<NfcTracker> provider3, Provider<AggregatedPerformanceAnalytics> provider4) {
        return new DocumentCaptureTracker_Factory(provider, provider2, provider3, provider4);
    }

    public static DocumentCaptureTracker newInstance(CaptureTracker captureTracker, WaitingScreenTracker waitingScreenTracker, NfcTracker nfcTracker, AggregatedPerformanceAnalytics aggregatedPerformanceAnalytics) {
        return new DocumentCaptureTracker(captureTracker, waitingScreenTracker, nfcTracker, aggregatedPerformanceAnalytics);
    }

    @Override // com.onfido.javax.inject.Provider
    public DocumentCaptureTracker get() {
        return newInstance(this.captureTrackerProvider.get(), this.waitingScreenTrackerProvider.get(), this.nfcTrackerProvider.get(), this.performanceAnalyticsProvider.get());
    }
}
