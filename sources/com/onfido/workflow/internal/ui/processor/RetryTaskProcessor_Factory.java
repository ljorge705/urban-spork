package com.onfido.workflow.internal.ui.processor;

import com.onfido.android.sdk.capture.internal.navigation.Navigator;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;
import com.onfido.workflow.internal.workflow.SubmitTaskCompletionUseCase;

/* loaded from: classes6.dex */
public final class RetryTaskProcessor_Factory implements Factory<RetryTaskProcessor> {
    private final Provider<SubmitTaskCompletionUseCase> completionUseCaseProvider;
    private final Provider<Navigator> navigatorProvider;

    public RetryTaskProcessor_Factory(Provider<Navigator> provider, Provider<SubmitTaskCompletionUseCase> provider2) {
        this.navigatorProvider = provider;
        this.completionUseCaseProvider = provider2;
    }

    @Override // com.onfido.javax.inject.Provider
    public RetryTaskProcessor get() {
        return newInstance(this.navigatorProvider.get(), this.completionUseCaseProvider.get());
    }

    public static RetryTaskProcessor_Factory create(Provider<Navigator> provider, Provider<SubmitTaskCompletionUseCase> provider2) {
        return new RetryTaskProcessor_Factory(provider, provider2);
    }

    public static RetryTaskProcessor newInstance(Navigator navigator, SubmitTaskCompletionUseCase submitTaskCompletionUseCase) {
        return new RetryTaskProcessor(navigator, submitTaskCompletionUseCase);
    }
}
