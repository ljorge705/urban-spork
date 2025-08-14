package com.onfido.workflow.internal.ui.processor;

import com.onfido.android.sdk.capture.internal.navigation.Navigator;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;
import com.onfido.workflow.internal.workflow.SubmitTaskCompletionUseCase;

/* loaded from: classes6.dex */
public final class PoaFlowProcessor_Factory implements Factory<PoaFlowProcessor> {
    private final Provider<SubmitTaskCompletionUseCase> completionUseCaseProvider;
    private final Provider<Navigator> navigatorProvider;

    public PoaFlowProcessor_Factory(Provider<Navigator> provider, Provider<SubmitTaskCompletionUseCase> provider2) {
        this.navigatorProvider = provider;
        this.completionUseCaseProvider = provider2;
    }

    @Override // com.onfido.javax.inject.Provider
    public PoaFlowProcessor get() {
        return newInstance(this.navigatorProvider.get(), this.completionUseCaseProvider.get());
    }

    public static PoaFlowProcessor_Factory create(Provider<Navigator> provider, Provider<SubmitTaskCompletionUseCase> provider2) {
        return new PoaFlowProcessor_Factory(provider, provider2);
    }

    public static PoaFlowProcessor newInstance(Navigator navigator, SubmitTaskCompletionUseCase submitTaskCompletionUseCase) {
        return new PoaFlowProcessor(navigator, submitTaskCompletionUseCase);
    }
}
