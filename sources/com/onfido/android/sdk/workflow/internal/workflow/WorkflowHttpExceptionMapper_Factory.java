package com.onfido.android.sdk.workflow.internal.workflow;

import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;
import kotlinx.serialization.json.Json;

/* loaded from: classes2.dex */
public final class WorkflowHttpExceptionMapper_Factory implements Factory<WorkflowHttpExceptionMapper> {
    private final Provider<Json> jsonParserProvider;

    public WorkflowHttpExceptionMapper_Factory(Provider<Json> provider) {
        this.jsonParserProvider = provider;
    }

    @Override // com.onfido.javax.inject.Provider
    public WorkflowHttpExceptionMapper get() {
        return newInstance(this.jsonParserProvider.get());
    }

    public static WorkflowHttpExceptionMapper_Factory create(Provider<Json> provider) {
        return new WorkflowHttpExceptionMapper_Factory(provider);
    }

    public static WorkflowHttpExceptionMapper newInstance(Json json) {
        return new WorkflowHttpExceptionMapper(json);
    }
}
