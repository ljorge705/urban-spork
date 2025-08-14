package com.onfido.workflow.internal.ui.processor.biometric.token;

import com.onfido.android.sdk.capture.internal.analytics.inhouse.OnfidoAnalytics;
import com.onfido.android.sdk.capture.internal.navigation.Navigator;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;
import com.onfido.workflow.internal.workflow.SubmitTaskCompletionUseCase;

/* loaded from: classes6.dex */
public final class BiometricTokenRetrievalFlowProcessor_Factory implements Factory<BiometricTokenRetrievalFlowProcessor> {
    private final Provider<OnfidoAnalytics> analyticsProvider;
    private final Provider<BiometricTokenRepository> biometricTokenRepositoryProvider;
    private final Provider<String> customerUserHashProvider;
    private final Provider<Navigator> navigatorProvider;
    private final Provider<SubmitTaskCompletionUseCase> submitTaskCompletionUseCaseProvider;

    public BiometricTokenRetrievalFlowProcessor_Factory(Provider<Navigator> provider, Provider<SubmitTaskCompletionUseCase> provider2, Provider<String> provider3, Provider<BiometricTokenRepository> provider4, Provider<OnfidoAnalytics> provider5) {
        this.navigatorProvider = provider;
        this.submitTaskCompletionUseCaseProvider = provider2;
        this.customerUserHashProvider = provider3;
        this.biometricTokenRepositoryProvider = provider4;
        this.analyticsProvider = provider5;
    }

    @Override // com.onfido.javax.inject.Provider
    public BiometricTokenRetrievalFlowProcessor get() {
        return newInstance(this.navigatorProvider.get(), this.submitTaskCompletionUseCaseProvider.get(), this.customerUserHashProvider.get(), this.biometricTokenRepositoryProvider.get(), this.analyticsProvider.get());
    }

    public static BiometricTokenRetrievalFlowProcessor_Factory create(Provider<Navigator> provider, Provider<SubmitTaskCompletionUseCase> provider2, Provider<String> provider3, Provider<BiometricTokenRepository> provider4, Provider<OnfidoAnalytics> provider5) {
        return new BiometricTokenRetrievalFlowProcessor_Factory(provider, provider2, provider3, provider4, provider5);
    }

    public static BiometricTokenRetrievalFlowProcessor newInstance(Navigator navigator, SubmitTaskCompletionUseCase submitTaskCompletionUseCase, String str, BiometricTokenRepository biometricTokenRepository, OnfidoAnalytics onfidoAnalytics) {
        return new BiometricTokenRetrievalFlowProcessor(navigator, submitTaskCompletionUseCase, str, biometricTokenRepository, onfidoAnalytics);
    }
}
