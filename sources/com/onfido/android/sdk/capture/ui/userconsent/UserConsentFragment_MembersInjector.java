package com.onfido.android.sdk.capture.ui.userconsent;

import com.onfido.dagger.MembersInjector;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class UserConsentFragment_MembersInjector implements MembersInjector<UserConsentFragment> {
    private final Provider<UserConsentViewModel> viewModelProvider;

    public UserConsentFragment_MembersInjector(Provider<UserConsentViewModel> provider) {
        this.viewModelProvider = provider;
    }

    public static MembersInjector<UserConsentFragment> create(Provider<UserConsentViewModel> provider) {
        return new UserConsentFragment_MembersInjector(provider);
    }

    public static void injectViewModelProvider(UserConsentFragment userConsentFragment, Provider<UserConsentViewModel> provider) {
        userConsentFragment.viewModelProvider = provider;
    }

    @Override // com.onfido.dagger.MembersInjector
    public void injectMembers(UserConsentFragment userConsentFragment) {
        injectViewModelProvider(userConsentFragment, this.viewModelProvider);
    }
}
