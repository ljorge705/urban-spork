package com.onfido.android.sdk.capture.ui.nfc.scan;

import androidx.lifecycle.SavedStateHandle;
import com.onfido.android.sdk.capture.ui.nfc.scan.NfcCanEntryFragment;
import com.onfido.dagger.internal.InstanceFactory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class NfcCanEntryFragment_Factory_Impl implements NfcCanEntryFragment.Factory {
    private final C0698NfcCanEntryViewModel_Factory delegateFactory;

    NfcCanEntryFragment_Factory_Impl(C0698NfcCanEntryViewModel_Factory c0698NfcCanEntryViewModel_Factory) {
        this.delegateFactory = c0698NfcCanEntryViewModel_Factory;
    }

    @Override // com.onfido.android.sdk.capture.ui.nfc.scan.NfcCanEntryFragment.Factory
    public NfcCanEntryViewModel create(SavedStateHandle savedStateHandle) {
        return this.delegateFactory.get(savedStateHandle);
    }

    public static Provider<NfcCanEntryFragment.Factory> create(C0698NfcCanEntryViewModel_Factory c0698NfcCanEntryViewModel_Factory) {
        return InstanceFactory.create(new NfcCanEntryFragment_Factory_Impl(c0698NfcCanEntryViewModel_Factory));
    }
}
