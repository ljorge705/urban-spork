package com.onfido.android.sdk.capture.ui.nfc;

import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.ScreenTracker;
import com.onfido.dagger.MembersInjector;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class NfcDocumentNotSupportedFragment_MembersInjector implements MembersInjector<NfcDocumentNotSupportedFragment> {
    private final Provider<ScreenTracker> screenTrackerProvider;

    public NfcDocumentNotSupportedFragment_MembersInjector(Provider<ScreenTracker> provider) {
        this.screenTrackerProvider = provider;
    }

    public static MembersInjector<NfcDocumentNotSupportedFragment> create(Provider<ScreenTracker> provider) {
        return new NfcDocumentNotSupportedFragment_MembersInjector(provider);
    }

    public static void injectScreenTracker(NfcDocumentNotSupportedFragment nfcDocumentNotSupportedFragment, ScreenTracker screenTracker) {
        nfcDocumentNotSupportedFragment.screenTracker = screenTracker;
    }

    @Override // com.onfido.dagger.MembersInjector
    public void injectMembers(NfcDocumentNotSupportedFragment nfcDocumentNotSupportedFragment) {
        injectScreenTracker(nfcDocumentNotSupportedFragment, this.screenTrackerProvider.get());
    }
}
