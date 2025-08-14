package com.onfido.android.sdk.capture.ui.proofOfAddress.host;

import androidx.lifecycle.SavedStateHandle;
import com.onfido.android.sdk.capture.ui.proofOfAddress.host.PoaHostViewModel;
import com.onfido.dagger.internal.InstanceFactory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class PoaHostViewModel_Factory_Impl implements PoaHostViewModel.Factory {
    private final C0714PoaHostViewModel_Factory delegateFactory;

    PoaHostViewModel_Factory_Impl(C0714PoaHostViewModel_Factory c0714PoaHostViewModel_Factory) {
        this.delegateFactory = c0714PoaHostViewModel_Factory;
    }

    @Override // com.onfido.android.sdk.capture.ui.proofOfAddress.host.PoaHostViewModel.Factory
    public PoaHostViewModel create(SavedStateHandle savedStateHandle) {
        return this.delegateFactory.get(savedStateHandle);
    }

    public static Provider<PoaHostViewModel.Factory> create(C0714PoaHostViewModel_Factory c0714PoaHostViewModel_Factory) {
        return InstanceFactory.create(new PoaHostViewModel_Factory_Impl(c0714PoaHostViewModel_Factory));
    }
}
