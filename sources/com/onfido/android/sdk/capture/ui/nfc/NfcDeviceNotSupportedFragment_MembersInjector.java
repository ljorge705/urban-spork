package com.onfido.android.sdk.capture.ui.nfc;

import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.NfcTracker;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.ScreenTracker;
import com.onfido.dagger.MembersInjector;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class NfcDeviceNotSupportedFragment_MembersInjector implements MembersInjector<NfcDeviceNotSupportedFragment> {
    private final Provider<NfcTracker> nfcTrackerProvider;
    private final Provider<ScreenTracker> screenTrackerProvider;

    public NfcDeviceNotSupportedFragment_MembersInjector(Provider<ScreenTracker> provider, Provider<NfcTracker> provider2) {
        this.screenTrackerProvider = provider;
        this.nfcTrackerProvider = provider2;
    }

    public static MembersInjector<NfcDeviceNotSupportedFragment> create(Provider<ScreenTracker> provider, Provider<NfcTracker> provider2) {
        return new NfcDeviceNotSupportedFragment_MembersInjector(provider, provider2);
    }

    public static void injectNfcTracker(NfcDeviceNotSupportedFragment nfcDeviceNotSupportedFragment, NfcTracker nfcTracker) {
        nfcDeviceNotSupportedFragment.nfcTracker = nfcTracker;
    }

    public static void injectScreenTracker(NfcDeviceNotSupportedFragment nfcDeviceNotSupportedFragment, ScreenTracker screenTracker) {
        nfcDeviceNotSupportedFragment.screenTracker = screenTracker;
    }

    @Override // com.onfido.dagger.MembersInjector
    public void injectMembers(NfcDeviceNotSupportedFragment nfcDeviceNotSupportedFragment) {
        injectScreenTracker(nfcDeviceNotSupportedFragment, this.screenTrackerProvider.get());
        injectNfcTracker(nfcDeviceNotSupportedFragment, this.nfcTrackerProvider.get());
    }
}
