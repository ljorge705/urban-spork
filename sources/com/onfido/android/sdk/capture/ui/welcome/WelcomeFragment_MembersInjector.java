package com.onfido.android.sdk.capture.ui.welcome;

import com.onfido.android.sdk.capture.ui.welcome.WelcomePresenter;
import com.onfido.dagger.MembersInjector;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class WelcomeFragment_MembersInjector implements MembersInjector<WelcomeFragment> {
    private final Provider<WelcomePresenter.Factory> presenterFactoryProvider;

    public WelcomeFragment_MembersInjector(Provider<WelcomePresenter.Factory> provider) {
        this.presenterFactoryProvider = provider;
    }

    public static MembersInjector<WelcomeFragment> create(Provider<WelcomePresenter.Factory> provider) {
        return new WelcomeFragment_MembersInjector(provider);
    }

    public static void injectPresenterFactory(WelcomeFragment welcomeFragment, WelcomePresenter.Factory factory) {
        welcomeFragment.presenterFactory = factory;
    }

    @Override // com.onfido.dagger.MembersInjector
    public void injectMembers(WelcomeFragment welcomeFragment) {
        injectPresenterFactory(welcomeFragment, this.presenterFactoryProvider.get());
    }
}
