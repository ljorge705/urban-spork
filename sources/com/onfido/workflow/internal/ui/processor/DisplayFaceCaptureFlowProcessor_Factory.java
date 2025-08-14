package com.onfido.workflow.internal.ui.processor;

import android.content.Context;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.navigation.Navigator;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;
import com.onfido.workflow.internal.workflow.SubmitTaskCompletionUseCase;

/* loaded from: classes6.dex */
public final class DisplayFaceCaptureFlowProcessor_Factory implements Factory<DisplayFaceCaptureFlowProcessor> {
    private final Provider<Context> contextProvider;
    private final Provider<FaceLivenessFlowHelper> faceLivenessFlowHelperProvider;
    private final Provider<Navigator> navigatorProvider;
    private final Provider<OnfidoRemoteConfig> onfidoRemoteConfigProvider;
    private final Provider<PermissionsFlowHelper> permissionsFlowHelperProvider;
    private final Provider<SubmitTaskCompletionUseCase> submitTaskCompletionUseCaseProvider;

    public DisplayFaceCaptureFlowProcessor_Factory(Provider<Context> provider, Provider<FaceLivenessFlowHelper> provider2, Provider<PermissionsFlowHelper> provider3, Provider<Navigator> provider4, Provider<SubmitTaskCompletionUseCase> provider5, Provider<OnfidoRemoteConfig> provider6) {
        this.contextProvider = provider;
        this.faceLivenessFlowHelperProvider = provider2;
        this.permissionsFlowHelperProvider = provider3;
        this.navigatorProvider = provider4;
        this.submitTaskCompletionUseCaseProvider = provider5;
        this.onfidoRemoteConfigProvider = provider6;
    }

    @Override // com.onfido.javax.inject.Provider
    public DisplayFaceCaptureFlowProcessor get() {
        return newInstance(this.contextProvider.get(), this.faceLivenessFlowHelperProvider.get(), this.permissionsFlowHelperProvider.get(), this.navigatorProvider.get(), this.submitTaskCompletionUseCaseProvider.get(), this.onfidoRemoteConfigProvider.get());
    }

    public static DisplayFaceCaptureFlowProcessor_Factory create(Provider<Context> provider, Provider<FaceLivenessFlowHelper> provider2, Provider<PermissionsFlowHelper> provider3, Provider<Navigator> provider4, Provider<SubmitTaskCompletionUseCase> provider5, Provider<OnfidoRemoteConfig> provider6) {
        return new DisplayFaceCaptureFlowProcessor_Factory(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static DisplayFaceCaptureFlowProcessor newInstance(Context context, FaceLivenessFlowHelper faceLivenessFlowHelper, PermissionsFlowHelper permissionsFlowHelper, Navigator navigator, SubmitTaskCompletionUseCase submitTaskCompletionUseCase, OnfidoRemoteConfig onfidoRemoteConfig) {
        return new DisplayFaceCaptureFlowProcessor(context, faceLivenessFlowHelper, permissionsFlowHelper, navigator, submitTaskCompletionUseCase, onfidoRemoteConfig);
    }
}
