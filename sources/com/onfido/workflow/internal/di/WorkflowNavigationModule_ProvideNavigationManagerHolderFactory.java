package com.onfido.workflow.internal.di;

import com.onfido.android.sdk.capture.internal.navigation.OnfidoNavigation;
import com.onfido.android.sdk.capture.internal.navigation.navigator.NavigationManagerHolder;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;
import com.onfido.javax.inject.Provider;

/* loaded from: classes6.dex */
public final class WorkflowNavigationModule_ProvideNavigationManagerHolderFactory implements Factory<NavigationManagerHolder> {
    private final Provider<OnfidoNavigation> onfidoNavigationProvider;

    public WorkflowNavigationModule_ProvideNavigationManagerHolderFactory(Provider<OnfidoNavigation> provider) {
        this.onfidoNavigationProvider = provider;
    }

    @Override // com.onfido.javax.inject.Provider
    public NavigationManagerHolder get() {
        return provideNavigationManagerHolder(this.onfidoNavigationProvider.get());
    }

    public static WorkflowNavigationModule_ProvideNavigationManagerHolderFactory create(Provider<OnfidoNavigation> provider) {
        return new WorkflowNavigationModule_ProvideNavigationManagerHolderFactory(provider);
    }

    public static NavigationManagerHolder provideNavigationManagerHolder(OnfidoNavigation onfidoNavigation) {
        return (NavigationManagerHolder) Preconditions.checkNotNullFromProvides(WorkflowNavigationModule.INSTANCE.provideNavigationManagerHolder(onfidoNavigation));
    }
}
