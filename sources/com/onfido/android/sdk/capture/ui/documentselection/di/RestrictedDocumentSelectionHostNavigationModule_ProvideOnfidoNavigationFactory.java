package com.onfido.android.sdk.capture.ui.documentselection.di;

import com.onfido.android.sdk.capture.internal.navigation.OnfidoNavigation;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class RestrictedDocumentSelectionHostNavigationModule_ProvideOnfidoNavigationFactory implements Factory<OnfidoNavigation> {
    private final RestrictedDocumentSelectionHostNavigationModule module;
    private final Provider<SchedulersProvider> schedulersProvider;

    public RestrictedDocumentSelectionHostNavigationModule_ProvideOnfidoNavigationFactory(RestrictedDocumentSelectionHostNavigationModule restrictedDocumentSelectionHostNavigationModule, Provider<SchedulersProvider> provider) {
        this.module = restrictedDocumentSelectionHostNavigationModule;
        this.schedulersProvider = provider;
    }

    public static RestrictedDocumentSelectionHostNavigationModule_ProvideOnfidoNavigationFactory create(RestrictedDocumentSelectionHostNavigationModule restrictedDocumentSelectionHostNavigationModule, Provider<SchedulersProvider> provider) {
        return new RestrictedDocumentSelectionHostNavigationModule_ProvideOnfidoNavigationFactory(restrictedDocumentSelectionHostNavigationModule, provider);
    }

    public static OnfidoNavigation provideOnfidoNavigation(RestrictedDocumentSelectionHostNavigationModule restrictedDocumentSelectionHostNavigationModule, SchedulersProvider schedulersProvider) {
        return (OnfidoNavigation) Preconditions.checkNotNullFromProvides(restrictedDocumentSelectionHostNavigationModule.provideOnfidoNavigation(schedulersProvider));
    }

    @Override // com.onfido.javax.inject.Provider
    public OnfidoNavigation get() {
        return provideOnfidoNavigation(this.module, this.schedulersProvider.get());
    }
}
