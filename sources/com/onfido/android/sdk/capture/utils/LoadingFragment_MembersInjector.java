package com.onfido.android.sdk.capture.utils;

import com.onfido.android.sdk.capture.common.preferences.SharedPreferencesDataSource;
import com.onfido.android.sdk.capture.ui.widget.LoadingFragmentViewModel;
import com.onfido.dagger.MembersInjector;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class LoadingFragment_MembersInjector implements MembersInjector<LoadingFragment> {
    private final Provider<LoadingFragmentViewModel.Factory> loadingViewModelFactoryProvider;
    private final Provider<SharedPreferencesDataSource> storageProvider;

    public LoadingFragment_MembersInjector(Provider<SharedPreferencesDataSource> provider, Provider<LoadingFragmentViewModel.Factory> provider2) {
        this.storageProvider = provider;
        this.loadingViewModelFactoryProvider = provider2;
    }

    public static MembersInjector<LoadingFragment> create(Provider<SharedPreferencesDataSource> provider, Provider<LoadingFragmentViewModel.Factory> provider2) {
        return new LoadingFragment_MembersInjector(provider, provider2);
    }

    public static void injectLoadingViewModelFactory(LoadingFragment loadingFragment, LoadingFragmentViewModel.Factory factory) {
        loadingFragment.loadingViewModelFactory = factory;
    }

    public static void injectStorage(LoadingFragment loadingFragment, SharedPreferencesDataSource sharedPreferencesDataSource) {
        loadingFragment.storage = sharedPreferencesDataSource;
    }

    @Override // com.onfido.dagger.MembersInjector
    public void injectMembers(LoadingFragment loadingFragment) {
        injectStorage(loadingFragment, this.storageProvider.get());
        injectLoadingViewModelFactory(loadingFragment, this.loadingViewModelFactoryProvider.get());
    }
}
