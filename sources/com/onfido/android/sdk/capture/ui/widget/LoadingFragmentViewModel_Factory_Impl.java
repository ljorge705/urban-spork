package com.onfido.android.sdk.capture.ui.widget;

import com.onfido.android.sdk.capture.ui.widget.LoadingFragmentViewModel;
import com.onfido.android.sdk.capture.utils.LoadingFragment;
import com.onfido.dagger.internal.InstanceFactory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class LoadingFragmentViewModel_Factory_Impl implements LoadingFragmentViewModel.Factory {
    private final C0720LoadingFragmentViewModel_Factory delegateFactory;

    LoadingFragmentViewModel_Factory_Impl(C0720LoadingFragmentViewModel_Factory c0720LoadingFragmentViewModel_Factory) {
        this.delegateFactory = c0720LoadingFragmentViewModel_Factory;
    }

    @Override // com.onfido.android.sdk.capture.ui.widget.LoadingFragmentViewModel.Factory
    public LoadingFragmentViewModel create(LoadingFragment.Companion.DialogMode dialogMode) {
        return this.delegateFactory.get(dialogMode);
    }

    public static Provider<LoadingFragmentViewModel.Factory> create(C0720LoadingFragmentViewModel_Factory c0720LoadingFragmentViewModel_Factory) {
        return InstanceFactory.create(new LoadingFragmentViewModel_Factory_Impl(c0720LoadingFragmentViewModel_Factory));
    }
}
