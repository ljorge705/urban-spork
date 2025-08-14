package com.onfido.workflow.internal.di;

import com.onfido.api.client.OnfidoFetcher;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;
import com.onfido.javax.inject.Provider;
import com.onfido.workflow.internal.network.BiometricTokenApi;

/* loaded from: classes6.dex */
public final class WorkflowModule_Companion_ProvideBiometricTokenApiFactory implements Factory<BiometricTokenApi> {
    private final Provider<OnfidoFetcher> onfidoFetcherProvider;

    public WorkflowModule_Companion_ProvideBiometricTokenApiFactory(Provider<OnfidoFetcher> provider) {
        this.onfidoFetcherProvider = provider;
    }

    @Override // com.onfido.javax.inject.Provider
    public BiometricTokenApi get() {
        return provideBiometricTokenApi(this.onfidoFetcherProvider.get());
    }

    public static WorkflowModule_Companion_ProvideBiometricTokenApiFactory create(Provider<OnfidoFetcher> provider) {
        return new WorkflowModule_Companion_ProvideBiometricTokenApiFactory(provider);
    }

    public static BiometricTokenApi provideBiometricTokenApi(OnfidoFetcher onfidoFetcher) {
        return (BiometricTokenApi) Preconditions.checkNotNullFromProvides(WorkflowModule.INSTANCE.provideBiometricTokenApi(onfidoFetcher));
    }
}
