package com.onfido.workflow.internal.di;

import com.onfido.android.sdk.capture.internal.navigation.OnfidoNavigation;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;
import com.onfido.javax.inject.Provider;

/* loaded from: classes6.dex */
public final class WorkflowNavigationModule_ProvideOnfidoNavigationFactory implements Factory<OnfidoNavigation> {
    private final Provider<SchedulersProvider> schedulersProvider;

    public WorkflowNavigationModule_ProvideOnfidoNavigationFactory(Provider<SchedulersProvider> provider) {
        this.schedulersProvider = provider;
    }

    @Override // com.onfido.javax.inject.Provider
    public OnfidoNavigation get() {
        return provideOnfidoNavigation(this.schedulersProvider.get());
    }

    public static WorkflowNavigationModule_ProvideOnfidoNavigationFactory create(Provider<SchedulersProvider> provider) {
        return new WorkflowNavigationModule_ProvideOnfidoNavigationFactory(provider);
    }

    public static OnfidoNavigation provideOnfidoNavigation(SchedulersProvider schedulersProvider) {
        return (OnfidoNavigation) Preconditions.checkNotNullFromProvides(WorkflowNavigationModule.INSTANCE.provideOnfidoNavigation(schedulersProvider));
    }
}
