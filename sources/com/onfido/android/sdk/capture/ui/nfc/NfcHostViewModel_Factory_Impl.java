package com.onfido.android.sdk.capture.ui.nfc;

import androidx.lifecycle.SavedStateHandle;
import com.onfido.android.sdk.capture.ui.nfc.NfcHostViewModel;
import com.onfido.dagger.internal.InstanceFactory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class NfcHostViewModel_Factory_Impl implements NfcHostViewModel.Factory {
    private final C0697NfcHostViewModel_Factory delegateFactory;

    NfcHostViewModel_Factory_Impl(C0697NfcHostViewModel_Factory c0697NfcHostViewModel_Factory) {
        this.delegateFactory = c0697NfcHostViewModel_Factory;
    }

    @Override // com.onfido.android.sdk.capture.ui.nfc.NfcHostViewModel.Factory
    public NfcHostViewModel create(SavedStateHandle savedStateHandle) {
        return this.delegateFactory.get(savedStateHandle);
    }

    public static Provider<NfcHostViewModel.Factory> create(C0697NfcHostViewModel_Factory c0697NfcHostViewModel_Factory) {
        return InstanceFactory.create(new NfcHostViewModel_Factory_Impl(c0697NfcHostViewModel_Factory));
    }
}
