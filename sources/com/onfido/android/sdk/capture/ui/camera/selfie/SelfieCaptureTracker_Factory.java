package com.onfido.android.sdk.capture.ui.camera.selfie;

import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.CaptureTracker;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.LivenessTracker;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.WaitingScreenTracker;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class SelfieCaptureTracker_Factory implements Factory<SelfieCaptureTracker> {
    private final Provider<CaptureTracker> captureTrackerProvider;
    private final Provider<LivenessTracker> livenessTrackerProvider;
    private final Provider<OnfidoRemoteConfig> onfidoRemoteConfigProvider;
    private final Provider<WaitingScreenTracker> waitingScreenTrackerProvider;

    public SelfieCaptureTracker_Factory(Provider<CaptureTracker> provider, Provider<LivenessTracker> provider2, Provider<WaitingScreenTracker> provider3, Provider<OnfidoRemoteConfig> provider4) {
        this.captureTrackerProvider = provider;
        this.livenessTrackerProvider = provider2;
        this.waitingScreenTrackerProvider = provider3;
        this.onfidoRemoteConfigProvider = provider4;
    }

    public static SelfieCaptureTracker_Factory create(Provider<CaptureTracker> provider, Provider<LivenessTracker> provider2, Provider<WaitingScreenTracker> provider3, Provider<OnfidoRemoteConfig> provider4) {
        return new SelfieCaptureTracker_Factory(provider, provider2, provider3, provider4);
    }

    public static SelfieCaptureTracker newInstance(CaptureTracker captureTracker, LivenessTracker livenessTracker, WaitingScreenTracker waitingScreenTracker, OnfidoRemoteConfig onfidoRemoteConfig) {
        return new SelfieCaptureTracker(captureTracker, livenessTracker, waitingScreenTracker, onfidoRemoteConfig);
    }

    @Override // com.onfido.javax.inject.Provider
    public SelfieCaptureTracker get() {
        return newInstance(this.captureTrackerProvider.get(), this.livenessTrackerProvider.get(), this.waitingScreenTrackerProvider.get(), this.onfidoRemoteConfigProvider.get());
    }
}
