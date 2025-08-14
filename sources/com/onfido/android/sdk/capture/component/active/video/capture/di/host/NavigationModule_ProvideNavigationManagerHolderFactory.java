package com.onfido.android.sdk.capture.component.active.video.capture.di.host;

import com.onfido.android.sdk.capture.internal.navigation.OnfidoNavigation;
import com.onfido.android.sdk.capture.internal.navigation.navigator.NavigationManagerHolder;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class NavigationModule_ProvideNavigationManagerHolderFactory implements Factory<NavigationManagerHolder> {
    private final Provider<OnfidoNavigation> onfidoNavigationProvider;

    public NavigationModule_ProvideNavigationManagerHolderFactory(Provider<OnfidoNavigation> provider) {
        this.onfidoNavigationProvider = provider;
    }

    public static NavigationModule_ProvideNavigationManagerHolderFactory create(Provider<OnfidoNavigation> provider) {
        return new NavigationModule_ProvideNavigationManagerHolderFactory(provider);
    }

    public static NavigationManagerHolder provideNavigationManagerHolder(OnfidoNavigation onfidoNavigation) {
        return (NavigationManagerHolder) Preconditions.checkNotNullFromProvides(NavigationModule.INSTANCE.provideNavigationManagerHolder(onfidoNavigation));
    }

    @Override // com.onfido.javax.inject.Provider
    public NavigationManagerHolder get() {
        return provideNavigationManagerHolder(this.onfidoNavigationProvider.get());
    }
}
