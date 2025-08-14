package com.onfido.android.sdk.capture.ui.nfc.permission;

import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.ScreenTracker;
import com.onfido.dagger.MembersInjector;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class NfcPermissionFragment_MembersInjector implements MembersInjector<NfcPermissionFragment> {
    private final Provider<ScreenTracker> screenTrackerProvider;

    public NfcPermissionFragment_MembersInjector(Provider<ScreenTracker> provider) {
        this.screenTrackerProvider = provider;
    }

    public static MembersInjector<NfcPermissionFragment> create(Provider<ScreenTracker> provider) {
        return new NfcPermissionFragment_MembersInjector(provider);
    }

    public static void injectScreenTracker(NfcPermissionFragment nfcPermissionFragment, ScreenTracker screenTracker) {
        nfcPermissionFragment.screenTracker = screenTracker;
    }

    @Override // com.onfido.dagger.MembersInjector
    public void injectMembers(NfcPermissionFragment nfcPermissionFragment) {
        injectScreenTracker(nfcPermissionFragment, this.screenTrackerProvider.get());
    }
}
