package com.onfido.android.sdk.capture.ui.nfc.scan;

import androidx.lifecycle.SavedStateHandle;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.NfcTracker;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.ScreenTracker;
import com.onfido.javax.inject.Provider;

/* renamed from: com.onfido.android.sdk.capture.ui.nfc.scan.NfcCanEntryViewModel_Factory, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0698NfcCanEntryViewModel_Factory {
    private final Provider<NfcTracker> nfcTrackerProvider;
    private final Provider<ScreenTracker> screenTrackerProvider;

    public C0698NfcCanEntryViewModel_Factory(Provider<ScreenTracker> provider, Provider<NfcTracker> provider2) {
        this.screenTrackerProvider = provider;
        this.nfcTrackerProvider = provider2;
    }

    public static C0698NfcCanEntryViewModel_Factory create(Provider<ScreenTracker> provider, Provider<NfcTracker> provider2) {
        return new C0698NfcCanEntryViewModel_Factory(provider, provider2);
    }

    public static NfcCanEntryViewModel newInstance(ScreenTracker screenTracker, NfcTracker nfcTracker, SavedStateHandle savedStateHandle) {
        return new NfcCanEntryViewModel(screenTracker, nfcTracker, savedStateHandle);
    }

    public NfcCanEntryViewModel get(SavedStateHandle savedStateHandle) {
        return newInstance(this.screenTrackerProvider.get(), this.nfcTrackerProvider.get(), savedStateHandle);
    }
}
