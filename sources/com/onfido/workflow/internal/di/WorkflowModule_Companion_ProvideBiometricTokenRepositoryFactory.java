package com.onfido.workflow.internal.di;

import com.onfido.android.sdk.capture.OnfidoConfig;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;
import com.onfido.javax.inject.Provider;
import com.onfido.workflow.internal.ui.processor.biometric.token.BiometricTokenExternalRepository;
import com.onfido.workflow.internal.ui.processor.biometric.token.BiometricTokenInternalRepository;
import com.onfido.workflow.internal.ui.processor.biometric.token.BiometricTokenRepository;

/* loaded from: classes6.dex */
public final class WorkflowModule_Companion_ProvideBiometricTokenRepositoryFactory implements Factory<BiometricTokenRepository> {
    private final Provider<BiometricTokenExternalRepository> externalRepositoryProvider;
    private final Provider<BiometricTokenInternalRepository> internalRepositoryProvider;
    private final Provider<OnfidoConfig> onfidoConfigProvider;

    public WorkflowModule_Companion_ProvideBiometricTokenRepositoryFactory(Provider<OnfidoConfig> provider, Provider<BiometricTokenInternalRepository> provider2, Provider<BiometricTokenExternalRepository> provider3) {
        this.onfidoConfigProvider = provider;
        this.internalRepositoryProvider = provider2;
        this.externalRepositoryProvider = provider3;
    }

    @Override // com.onfido.javax.inject.Provider
    public BiometricTokenRepository get() {
        return provideBiometricTokenRepository(this.onfidoConfigProvider.get(), this.internalRepositoryProvider.get(), this.externalRepositoryProvider.get());
    }

    public static WorkflowModule_Companion_ProvideBiometricTokenRepositoryFactory create(Provider<OnfidoConfig> provider, Provider<BiometricTokenInternalRepository> provider2, Provider<BiometricTokenExternalRepository> provider3) {
        return new WorkflowModule_Companion_ProvideBiometricTokenRepositoryFactory(provider, provider2, provider3);
    }

    public static BiometricTokenRepository provideBiometricTokenRepository(OnfidoConfig onfidoConfig, BiometricTokenInternalRepository biometricTokenInternalRepository, BiometricTokenExternalRepository biometricTokenExternalRepository) {
        return (BiometricTokenRepository) Preconditions.checkNotNullFromProvides(WorkflowModule.INSTANCE.provideBiometricTokenRepository(onfidoConfig, biometricTokenInternalRepository, biometricTokenExternalRepository));
    }
}
