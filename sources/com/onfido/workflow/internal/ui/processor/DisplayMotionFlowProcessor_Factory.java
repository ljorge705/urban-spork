package com.onfido.workflow.internal.ui.processor;

import com.onfido.android.sdk.capture.internal.navigation.Navigator;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;
import com.onfido.workflow.internal.workflow.SubmitTaskCompletionUseCase;

/* loaded from: classes6.dex */
public final class DisplayMotionFlowProcessor_Factory implements Factory<DisplayMotionFlowProcessor> {
    private final Provider<Navigator> navigatorProvider;
    private final Provider<SubmitTaskCompletionUseCase> submitTaskCompletionUseCaseProvider;

    public DisplayMotionFlowProcessor_Factory(Provider<Navigator> provider, Provider<SubmitTaskCompletionUseCase> provider2) {
        this.navigatorProvider = provider;
        this.submitTaskCompletionUseCaseProvider = provider2;
    }

    @Override // com.onfido.javax.inject.Provider
    public DisplayMotionFlowProcessor get() {
        return newInstance(this.navigatorProvider.get(), this.submitTaskCompletionUseCaseProvider.get());
    }

    public static DisplayMotionFlowProcessor_Factory create(Provider<Navigator> provider, Provider<SubmitTaskCompletionUseCase> provider2) {
        return new DisplayMotionFlowProcessor_Factory(provider, provider2);
    }

    public static DisplayMotionFlowProcessor newInstance(Navigator navigator, SubmitTaskCompletionUseCase submitTaskCompletionUseCase) {
        return new DisplayMotionFlowProcessor(navigator, submitTaskCompletionUseCase);
    }
}
