package com.onfido.android.sdk.capture.internal.nfc;

import android.nfc.NfcAdapter;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.NfcTracker;
import com.onfido.android.sdk.capture.utils.TimeProvider;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class NfcInteractorImpl_Factory implements Factory<NfcInteractorImpl> {
    private final Provider<NfcAdapter> nfcAdapterProvider;
    private final Provider<NfcTimeouts> nfcTimeoutsProvider;
    private final Provider<NfcTracker> nfcTrackerProvider;
    private final Provider<PassportNfcReader> passportReaderProvider;
    private final Provider<TimeProvider> timeProvider;

    public NfcInteractorImpl_Factory(Provider<NfcAdapter> provider, Provider<PassportNfcReader> provider2, Provider<NfcTracker> provider3, Provider<NfcTimeouts> provider4, Provider<TimeProvider> provider5) {
        this.nfcAdapterProvider = provider;
        this.passportReaderProvider = provider2;
        this.nfcTrackerProvider = provider3;
        this.nfcTimeoutsProvider = provider4;
        this.timeProvider = provider5;
    }

    public static NfcInteractorImpl_Factory create(Provider<NfcAdapter> provider, Provider<PassportNfcReader> provider2, Provider<NfcTracker> provider3, Provider<NfcTimeouts> provider4, Provider<TimeProvider> provider5) {
        return new NfcInteractorImpl_Factory(provider, provider2, provider3, provider4, provider5);
    }

    public static NfcInteractorImpl newInstance(NfcAdapter nfcAdapter, PassportNfcReader passportNfcReader, NfcTracker nfcTracker, NfcTimeouts nfcTimeouts, TimeProvider timeProvider) {
        return new NfcInteractorImpl(nfcAdapter, passportNfcReader, nfcTracker, nfcTimeouts, timeProvider);
    }

    @Override // com.onfido.javax.inject.Provider
    public NfcInteractorImpl get() {
        return newInstance(this.nfcAdapterProvider.get(), this.passportReaderProvider.get(), this.nfcTrackerProvider.get(), this.nfcTimeoutsProvider.get(), this.timeProvider.get());
    }
}
