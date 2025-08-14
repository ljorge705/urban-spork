package com.onfido.workflow.internal.ui.processor;

import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.navigation.Navigator;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;
import com.onfido.workflow.internal.workflow.SubmitTaskCompletionUseCase;

/* loaded from: classes6.dex */
public final class FaceLivenessFlowHelper_Factory implements Factory<FaceLivenessFlowHelper> {
    private final Provider<Navigator> navigatorProvider;
    private final Provider<OnfidoRemoteConfig> onfidoRemoteConfigProvider;
    private final Provider<PermissionsFlowHelper> permissionsFlowHelperProvider;
    private final Provider<SubmitTaskCompletionUseCase> submitTaskCompletionUseCaseProvider;

    public FaceLivenessFlowHelper_Factory(Provider<Navigator> provider, Provider<SubmitTaskCompletionUseCase> provider2, Provider<PermissionsFlowHelper> provider3, Provider<OnfidoRemoteConfig> provider4) {
        this.navigatorProvider = provider;
        this.submitTaskCompletionUseCaseProvider = provider2;
        this.permissionsFlowHelperProvider = provider3;
        this.onfidoRemoteConfigProvider = provider4;
    }

    @Override // com.onfido.javax.inject.Provider
    public FaceLivenessFlowHelper get() {
        return newInstance(this.navigatorProvider.get(), this.submitTaskCompletionUseCaseProvider.get(), this.permissionsFlowHelperProvider.get(), this.onfidoRemoteConfigProvider.get());
    }

    public static FaceLivenessFlowHelper_Factory create(Provider<Navigator> provider, Provider<SubmitTaskCompletionUseCase> provider2, Provider<PermissionsFlowHelper> provider3, Provider<OnfidoRemoteConfig> provider4) {
        return new FaceLivenessFlowHelper_Factory(provider, provider2, provider3, provider4);
    }

    public static FaceLivenessFlowHelper newInstance(Navigator navigator, SubmitTaskCompletionUseCase submitTaskCompletionUseCase, PermissionsFlowHelper permissionsFlowHelper, OnfidoRemoteConfig onfidoRemoteConfig) {
        return new FaceLivenessFlowHelper(navigator, submitTaskCompletionUseCase, permissionsFlowHelper, onfidoRemoteConfig);
    }
}
