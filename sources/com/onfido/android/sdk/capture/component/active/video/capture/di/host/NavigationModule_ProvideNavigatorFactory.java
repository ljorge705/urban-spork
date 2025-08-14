package com.onfido.android.sdk.capture.component.active.video.capture.di.host;

import com.onfido.android.sdk.capture.internal.navigation.Navigator;
import com.onfido.android.sdk.capture.internal.navigation.OnfidoNavigation;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class NavigationModule_ProvideNavigatorFactory implements Factory<Navigator> {
    private final Provider<OnfidoNavigation> onfidoNavigationProvider;

    public NavigationModule_ProvideNavigatorFactory(Provider<OnfidoNavigation> provider) {
        this.onfidoNavigationProvider = provider;
    }

    public static NavigationModule_ProvideNavigatorFactory create(Provider<OnfidoNavigation> provider) {
        return new NavigationModule_ProvideNavigatorFactory(provider);
    }

    public static Navigator provideNavigator(OnfidoNavigation onfidoNavigation) {
        return (Navigator) Preconditions.checkNotNullFromProvides(NavigationModule.INSTANCE.provideNavigator(onfidoNavigation));
    }

    @Override // com.onfido.javax.inject.Provider
    public Navigator get() {
        return provideNavigator(this.onfidoNavigationProvider.get());
    }
}
