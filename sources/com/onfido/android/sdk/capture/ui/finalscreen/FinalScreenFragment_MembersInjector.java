package com.onfido.android.sdk.capture.ui.finalscreen;

import com.onfido.dagger.MembersInjector;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class FinalScreenFragment_MembersInjector implements MembersInjector<FinalScreenFragment> {
    private final Provider<FinalScreenPresenter> presenterProvider;

    public FinalScreenFragment_MembersInjector(Provider<FinalScreenPresenter> provider) {
        this.presenterProvider = provider;
    }

    public static MembersInjector<FinalScreenFragment> create(Provider<FinalScreenPresenter> provider) {
        return new FinalScreenFragment_MembersInjector(provider);
    }

    public static void injectPresenter(FinalScreenFragment finalScreenFragment, FinalScreenPresenter finalScreenPresenter) {
        finalScreenFragment.presenter = finalScreenPresenter;
    }

    @Override // com.onfido.dagger.MembersInjector
    public void injectMembers(FinalScreenFragment finalScreenFragment) {
        injectPresenter(finalScreenFragment, this.presenterProvider.get());
    }
}
