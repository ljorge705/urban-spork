package com.onfido.android.sdk.capture.ui.finalscreen;

import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.ScreenTracker;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class FinalScreenPresenter_Factory implements Factory<FinalScreenPresenter> {
    private final Provider<ScreenTracker> screenTrackerProvider;

    public FinalScreenPresenter_Factory(Provider<ScreenTracker> provider) {
        this.screenTrackerProvider = provider;
    }

    public static FinalScreenPresenter_Factory create(Provider<ScreenTracker> provider) {
        return new FinalScreenPresenter_Factory(provider);
    }

    public static FinalScreenPresenter newInstance(ScreenTracker screenTracker) {
        return new FinalScreenPresenter(screenTracker);
    }

    @Override // com.onfido.javax.inject.Provider
    public FinalScreenPresenter get() {
        return newInstance(this.screenTrackerProvider.get());
    }
}
