package com.onfido.workflow.internal.workflow;

import android.content.Context;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes6.dex */
public final class WorkflowPollerLocaleProvider_Factory implements Factory<WorkflowPollerLocaleProvider> {
    private final Provider<Context> contextProvider;

    public WorkflowPollerLocaleProvider_Factory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    @Override // com.onfido.javax.inject.Provider
    public WorkflowPollerLocaleProvider get() {
        return newInstance(this.contextProvider.get());
    }

    public static WorkflowPollerLocaleProvider_Factory create(Provider<Context> provider) {
        return new WorkflowPollerLocaleProvider_Factory(provider);
    }

    public static WorkflowPollerLocaleProvider newInstance(Context context) {
        return new WorkflowPollerLocaleProvider(context);
    }
}
