package com.onfido.workflow.internal.di;

import com.onfido.android.sdk.capture.internal.navigation.Navigator;
import com.onfido.android.sdk.capture.internal.navigation.OnfidoNavigation;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;
import com.onfido.javax.inject.Provider;

/* loaded from: classes6.dex */
public final class WorkflowNavigationModule_ProvideNavigatorFactory implements Factory<Navigator> {
    private final Provider<OnfidoNavigation> onfidoNavigationProvider;

    public WorkflowNavigationModule_ProvideNavigatorFactory(Provider<OnfidoNavigation> provider) {
        this.onfidoNavigationProvider = provider;
    }

    @Override // com.onfido.javax.inject.Provider
    public Navigator get() {
        return provideNavigator(this.onfidoNavigationProvider.get());
    }

    public static WorkflowNavigationModule_ProvideNavigatorFactory create(Provider<OnfidoNavigation> provider) {
        return new WorkflowNavigationModule_ProvideNavigatorFactory(provider);
    }

    public static Navigator provideNavigator(OnfidoNavigation onfidoNavigation) {
        return (Navigator) Preconditions.checkNotNullFromProvides(WorkflowNavigationModule.INSTANCE.provideNavigator(onfidoNavigation));
    }
}
