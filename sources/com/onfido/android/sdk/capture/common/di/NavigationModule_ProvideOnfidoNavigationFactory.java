package com.onfido.android.sdk.capture.common.di;

import com.onfido.android.sdk.capture.internal.navigation.OnfidoNavigation;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class NavigationModule_ProvideOnfidoNavigationFactory implements Factory<OnfidoNavigation> {
    private final Provider<SchedulersProvider> schedulersProvider;

    public NavigationModule_ProvideOnfidoNavigationFactory(Provider<SchedulersProvider> provider) {
        this.schedulersProvider = provider;
    }

    public static NavigationModule_ProvideOnfidoNavigationFactory create(Provider<SchedulersProvider> provider) {
        return new NavigationModule_ProvideOnfidoNavigationFactory(provider);
    }

    public static OnfidoNavigation provideOnfidoNavigation(SchedulersProvider schedulersProvider) {
        return (OnfidoNavigation) Preconditions.checkNotNullFromProvides(NavigationModule.INSTANCE.provideOnfidoNavigation(schedulersProvider));
    }

    @Override // com.onfido.javax.inject.Provider
    public OnfidoNavigation get() {
        return provideOnfidoNavigation(this.schedulersProvider.get());
    }
}
