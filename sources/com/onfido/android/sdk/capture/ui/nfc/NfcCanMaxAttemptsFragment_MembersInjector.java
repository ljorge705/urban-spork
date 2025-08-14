package com.onfido.android.sdk.capture.ui.nfc;

import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.ScreenTracker;
import com.onfido.dagger.MembersInjector;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class NfcCanMaxAttemptsFragment_MembersInjector implements MembersInjector<NfcCanMaxAttemptsFragment> {
    private final Provider<ScreenTracker> screenTrackerProvider;

    public NfcCanMaxAttemptsFragment_MembersInjector(Provider<ScreenTracker> provider) {
        this.screenTrackerProvider = provider;
    }

    public static MembersInjector<NfcCanMaxAttemptsFragment> create(Provider<ScreenTracker> provider) {
        return new NfcCanMaxAttemptsFragment_MembersInjector(provider);
    }

    public static void injectScreenTracker(NfcCanMaxAttemptsFragment nfcCanMaxAttemptsFragment, ScreenTracker screenTracker) {
        nfcCanMaxAttemptsFragment.screenTracker = screenTracker;
    }

    @Override // com.onfido.dagger.MembersInjector
    public void injectMembers(NfcCanMaxAttemptsFragment nfcCanMaxAttemptsFragment) {
        injectScreenTracker(nfcCanMaxAttemptsFragment, this.screenTrackerProvider.get());
    }
}
