package com.onfido.android.sdk.capture.ui.nfc.scan;

import androidx.lifecycle.SavedStateHandle;
import com.onfido.android.sdk.capture.ui.nfc.scan.NfcCanEntryViewModel;
import com.onfido.dagger.internal.InstanceFactory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class NfcCanEntryViewModel_Factory_Impl implements NfcCanEntryViewModel.Factory {
    private final C0698NfcCanEntryViewModel_Factory delegateFactory;

    NfcCanEntryViewModel_Factory_Impl(C0698NfcCanEntryViewModel_Factory c0698NfcCanEntryViewModel_Factory) {
        this.delegateFactory = c0698NfcCanEntryViewModel_Factory;
    }

    @Override // com.onfido.android.sdk.capture.ui.nfc.scan.NfcCanEntryViewModel.Factory
    public NfcCanEntryViewModel create(SavedStateHandle savedStateHandle) {
        return this.delegateFactory.get(savedStateHandle);
    }

    public static Provider<NfcCanEntryViewModel.Factory> create(C0698NfcCanEntryViewModel_Factory c0698NfcCanEntryViewModel_Factory) {
        return InstanceFactory.create(new NfcCanEntryViewModel_Factory_Impl(c0698NfcCanEntryViewModel_Factory));
    }
}
