package com.onfido.android.sdk.capture.ui.documentselection.host;

import androidx.lifecycle.SavedStateHandle;
import com.onfido.android.sdk.capture.ui.documentselection.host.DocumentSelectionHostViewModel;
import com.onfido.dagger.internal.InstanceFactory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class DocumentSelectionHostViewModel_Factory_Impl implements DocumentSelectionHostViewModel.Factory {
    private final C0695DocumentSelectionHostViewModel_Factory delegateFactory;

    DocumentSelectionHostViewModel_Factory_Impl(C0695DocumentSelectionHostViewModel_Factory c0695DocumentSelectionHostViewModel_Factory) {
        this.delegateFactory = c0695DocumentSelectionHostViewModel_Factory;
    }

    @Override // com.onfido.android.sdk.capture.ui.documentselection.host.DocumentSelectionHostViewModel.Factory
    public DocumentSelectionHostViewModel create(SavedStateHandle savedStateHandle) {
        return this.delegateFactory.get(savedStateHandle);
    }

    public static Provider<DocumentSelectionHostViewModel.Factory> create(C0695DocumentSelectionHostViewModel_Factory c0695DocumentSelectionHostViewModel_Factory) {
        return InstanceFactory.create(new DocumentSelectionHostViewModel_Factory_Impl(c0695DocumentSelectionHostViewModel_Factory));
    }
}
