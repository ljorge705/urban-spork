package com.onfido.android.sdk.capture.ui.documentselection.di;

import com.onfido.android.sdk.capture.internal.navigation.OnfidoNavigation;
import com.onfido.android.sdk.capture.internal.navigation.navigator.NavigationManagerHolder;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class RestrictedDocumentSelectionHostNavigationModule_ProvideNavigationManagerHolderFactory implements Factory<NavigationManagerHolder> {
    private final RestrictedDocumentSelectionHostNavigationModule module;
    private final Provider<OnfidoNavigation> onfidoNavigationProvider;

    public RestrictedDocumentSelectionHostNavigationModule_ProvideNavigationManagerHolderFactory(RestrictedDocumentSelectionHostNavigationModule restrictedDocumentSelectionHostNavigationModule, Provider<OnfidoNavigation> provider) {
        this.module = restrictedDocumentSelectionHostNavigationModule;
        this.onfidoNavigationProvider = provider;
    }

    public static RestrictedDocumentSelectionHostNavigationModule_ProvideNavigationManagerHolderFactory create(RestrictedDocumentSelectionHostNavigationModule restrictedDocumentSelectionHostNavigationModule, Provider<OnfidoNavigation> provider) {
        return new RestrictedDocumentSelectionHostNavigationModule_ProvideNavigationManagerHolderFactory(restrictedDocumentSelectionHostNavigationModule, provider);
    }

    public static NavigationManagerHolder provideNavigationManagerHolder(RestrictedDocumentSelectionHostNavigationModule restrictedDocumentSelectionHostNavigationModule, OnfidoNavigation onfidoNavigation) {
        return (NavigationManagerHolder) Preconditions.checkNotNullFromProvides(restrictedDocumentSelectionHostNavigationModule.provideNavigationManagerHolder(onfidoNavigation));
    }

    @Override // com.onfido.javax.inject.Provider
    public NavigationManagerHolder get() {
        return provideNavigationManagerHolder(this.module, this.onfidoNavigationProvider.get());
    }
}
