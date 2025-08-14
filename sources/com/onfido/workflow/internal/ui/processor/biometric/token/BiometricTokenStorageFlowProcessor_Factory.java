package com.onfido.workflow.internal.ui.processor.biometric.token;

import com.onfido.android.sdk.capture.internal.analytics.inhouse.OnfidoAnalytics;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;
import com.onfido.workflow.internal.network.BiometricTokenApi;
import com.onfido.workflow.internal.workflow.SubmitTaskCompletionUseCase;

/* loaded from: classes6.dex */
public final class BiometricTokenStorageFlowProcessor_Factory implements Factory<BiometricTokenStorageFlowProcessor> {
    private final Provider<OnfidoAnalytics> analyticsProvider;
    private final Provider<BiometricTokenApi> biometricTokenApiProvider;
    private final Provider<BiometricTokenRepository> biometricTokenRepositoryProvider;
    private final Provider<String> customerUserHashProvider;
    private final Provider<SubmitTaskCompletionUseCase> submitTaskCompletionUseCaseProvider;

    public BiometricTokenStorageFlowProcessor_Factory(Provider<BiometricTokenApi> provider, Provider<SubmitTaskCompletionUseCase> provider2, Provider<String> provider3, Provider<BiometricTokenRepository> provider4, Provider<OnfidoAnalytics> provider5) {
        this.biometricTokenApiProvider = provider;
        this.submitTaskCompletionUseCaseProvider = provider2;
        this.customerUserHashProvider = provider3;
        this.biometricTokenRepositoryProvider = provider4;
        this.analyticsProvider = provider5;
    }

    @Override // com.onfido.javax.inject.Provider
    public BiometricTokenStorageFlowProcessor get() {
        return newInstance(this.biometricTokenApiProvider.get(), this.submitTaskCompletionUseCaseProvider.get(), this.customerUserHashProvider.get(), this.biometricTokenRepositoryProvider.get(), this.analyticsProvider.get());
    }

    public static BiometricTokenStorageFlowProcessor_Factory create(Provider<BiometricTokenApi> provider, Provider<SubmitTaskCompletionUseCase> provider2, Provider<String> provider3, Provider<BiometricTokenRepository> provider4, Provider<OnfidoAnalytics> provider5) {
        return new BiometricTokenStorageFlowProcessor_Factory(provider, provider2, provider3, provider4, provider5);
    }

    public static BiometricTokenStorageFlowProcessor newInstance(BiometricTokenApi biometricTokenApi, SubmitTaskCompletionUseCase submitTaskCompletionUseCase, String str, BiometricTokenRepository biometricTokenRepository, OnfidoAnalytics onfidoAnalytics) {
        return new BiometricTokenStorageFlowProcessor(biometricTokenApi, submitTaskCompletionUseCase, str, biometricTokenRepository, onfidoAnalytics);
    }
}
