package com.onfido.android.sdk.capture.internal.ui.countryselection;

import com.onfido.dagger.MembersInjector;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class CountrySelectionFragment_MembersInjector implements MembersInjector<CountrySelectionFragment> {
    private final Provider<CountrySelectionPresenter> presenterProvider;

    public CountrySelectionFragment_MembersInjector(Provider<CountrySelectionPresenter> provider) {
        this.presenterProvider = provider;
    }

    public static MembersInjector<CountrySelectionFragment> create(Provider<CountrySelectionPresenter> provider) {
        return new CountrySelectionFragment_MembersInjector(provider);
    }

    public static void injectPresenter(CountrySelectionFragment countrySelectionFragment, CountrySelectionPresenter countrySelectionPresenter) {
        countrySelectionFragment.presenter = countrySelectionPresenter;
    }

    @Override // com.onfido.dagger.MembersInjector
    public void injectMembers(CountrySelectionFragment countrySelectionFragment) {
        injectPresenter(countrySelectionFragment, this.presenterProvider.get());
    }
}
