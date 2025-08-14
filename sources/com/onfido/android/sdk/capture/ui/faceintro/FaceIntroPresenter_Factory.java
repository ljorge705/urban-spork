package com.onfido.android.sdk.capture.ui.faceintro;

import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.LivenessTracker;
import com.onfido.android.sdk.capture.internal.performance.trackers.ScreenLoadTracker;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class FaceIntroPresenter_Factory implements Factory<FaceIntroPresenter> {
    private final Provider<LivenessTracker> livenessTrackerProvider;
    private final Provider<ScreenLoadTracker> screenLoadTrackerProvider;

    public FaceIntroPresenter_Factory(Provider<LivenessTracker> provider, Provider<ScreenLoadTracker> provider2) {
        this.livenessTrackerProvider = provider;
        this.screenLoadTrackerProvider = provider2;
    }

    public static FaceIntroPresenter_Factory create(Provider<LivenessTracker> provider, Provider<ScreenLoadTracker> provider2) {
        return new FaceIntroPresenter_Factory(provider, provider2);
    }

    public static FaceIntroPresenter newInstance(LivenessTracker livenessTracker, ScreenLoadTracker screenLoadTracker) {
        return new FaceIntroPresenter(livenessTracker, screenLoadTracker);
    }

    @Override // com.onfido.javax.inject.Provider
    public FaceIntroPresenter get() {
        return newInstance(this.livenessTrackerProvider.get(), this.screenLoadTrackerProvider.get());
    }
}
