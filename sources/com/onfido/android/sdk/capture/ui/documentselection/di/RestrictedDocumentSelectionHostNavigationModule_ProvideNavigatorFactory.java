package com.onfido.android.sdk.capture.ui.documentselection.di;

import com.onfido.android.sdk.capture.internal.navigation.Navigator;
import com.onfido.android.sdk.capture.internal.navigation.OnfidoNavigation;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class RestrictedDocumentSelectionHostNavigationModule_ProvideNavigatorFactory implements Factory<Navigator> {
    private final RestrictedDocumentSelectionHostNavigationModule module;
    private final Provider<OnfidoNavigation> onfidoNavigationProvider;

    public RestrictedDocumentSelectionHostNavigationModule_ProvideNavigatorFactory(RestrictedDocumentSelectionHostNavigationModule restrictedDocumentSelectionHostNavigationModule, Provider<OnfidoNavigation> provider) {
        this.module = restrictedDocumentSelectionHostNavigationModule;
        this.onfidoNavigationProvider = provider;
    }

    public static RestrictedDocumentSelectionHostNavigationModule_ProvideNavigatorFactory create(RestrictedDocumentSelectionHostNavigationModule restrictedDocumentSelectionHostNavigationModule, Provider<OnfidoNavigation> provider) {
        return new RestrictedDocumentSelectionHostNavigationModule_ProvideNavigatorFactory(restrictedDocumentSelectionHostNavigationModule, provider);
    }

    public static Navigator provideNavigator(RestrictedDocumentSelectionHostNavigationModule restrictedDocumentSelectionHostNavigationModule, OnfidoNavigation onfidoNavigation) {
        return (Navigator) Preconditions.checkNotNullFromProvides(restrictedDocumentSelectionHostNavigationModule.provideNavigator(onfidoNavigation));
    }

    @Override // com.onfido.javax.inject.Provider
    public Navigator get() {
        return provideNavigator(this.module, this.onfidoNavigationProvider.get());
    }
}
