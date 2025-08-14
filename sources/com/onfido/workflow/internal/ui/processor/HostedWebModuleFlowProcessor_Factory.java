package com.onfido.workflow.internal.ui.processor;

import com.onfido.android.sdk.capture.common.data.ThemeDataSource;
import com.onfido.android.sdk.capture.internal.navigation.Navigator;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;
import com.onfido.workflow.internal.workflow.SubmitTaskCompletionUseCase;

/* loaded from: classes6.dex */
public final class HostedWebModuleFlowProcessor_Factory implements Factory<HostedWebModuleFlowProcessor> {
    private final Provider<Navigator> navigatorProvider;
    private final Provider<SubmitTaskCompletionUseCase> submitTaskCompletionUseCaseProvider;
    private final Provider<ThemeDataSource> themeDataSourceProvider;

    public HostedWebModuleFlowProcessor_Factory(Provider<Navigator> provider, Provider<SubmitTaskCompletionUseCase> provider2, Provider<ThemeDataSource> provider3) {
        this.navigatorProvider = provider;
        this.submitTaskCompletionUseCaseProvider = provider2;
        this.themeDataSourceProvider = provider3;
    }

    @Override // com.onfido.javax.inject.Provider
    public HostedWebModuleFlowProcessor get() {
        return newInstance(this.navigatorProvider.get(), this.submitTaskCompletionUseCaseProvider.get(), this.themeDataSourceProvider.get());
    }

    public static HostedWebModuleFlowProcessor_Factory create(Provider<Navigator> provider, Provider<SubmitTaskCompletionUseCase> provider2, Provider<ThemeDataSource> provider3) {
        return new HostedWebModuleFlowProcessor_Factory(provider, provider2, provider3);
    }

    public static HostedWebModuleFlowProcessor newInstance(Navigator navigator, SubmitTaskCompletionUseCase submitTaskCompletionUseCase, ThemeDataSource themeDataSource) {
        return new HostedWebModuleFlowProcessor(navigator, submitTaskCompletionUseCase, themeDataSource);
    }
}
