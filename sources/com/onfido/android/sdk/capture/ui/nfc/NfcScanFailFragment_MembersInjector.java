package com.onfido.android.sdk.capture.ui.nfc;

import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.ScreenTracker;
import com.onfido.dagger.MembersInjector;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class NfcScanFailFragment_MembersInjector implements MembersInjector<NfcScanFailFragment> {
    private final Provider<ScreenTracker> screenTrackerProvider;

    public NfcScanFailFragment_MembersInjector(Provider<ScreenTracker> provider) {
        this.screenTrackerProvider = provider;
    }

    public static MembersInjector<NfcScanFailFragment> create(Provider<ScreenTracker> provider) {
        return new NfcScanFailFragment_MembersInjector(provider);
    }

    public static void injectScreenTracker(NfcScanFailFragment nfcScanFailFragment, ScreenTracker screenTracker) {
        nfcScanFailFragment.screenTracker = screenTracker;
    }

    @Override // com.onfido.dagger.MembersInjector
    public void injectMembers(NfcScanFailFragment nfcScanFailFragment) {
        injectScreenTracker(nfcScanFailFragment, this.screenTrackerProvider.get());
    }
}
