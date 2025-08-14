package com.onfido.android.sdk.capture.ui.proofOfAddress.verifyAddress;

import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.ScreenTracker;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class PoaVerifyAddressViewModel_Factory implements Factory<PoaVerifyAddressViewModel> {
    private final Provider<ScreenTracker> screenTrackerProvider;

    public PoaVerifyAddressViewModel_Factory(Provider<ScreenTracker> provider) {
        this.screenTrackerProvider = provider;
    }

    public static PoaVerifyAddressViewModel_Factory create(Provider<ScreenTracker> provider) {
        return new PoaVerifyAddressViewModel_Factory(provider);
    }

    public static PoaVerifyAddressViewModel newInstance(ScreenTracker screenTracker) {
        return new PoaVerifyAddressViewModel(screenTracker);
    }

    @Override // com.onfido.javax.inject.Provider
    public PoaVerifyAddressViewModel get() {
        return newInstance(this.screenTrackerProvider.get());
    }
}
