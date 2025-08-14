package com.onfido.android.sdk.workflow.internal.workflow.tasks.documentupload;

import com.onfido.dagger.internal.Factory;

/* loaded from: classes2.dex */
public final class WorkflowSupportedDocumentsRepository_Factory implements Factory<WorkflowSupportedDocumentsRepository> {

    private static final class InstanceHolder {
        private static final WorkflowSupportedDocumentsRepository_Factory INSTANCE = new WorkflowSupportedDocumentsRepository_Factory();

        private InstanceHolder() {
        }
    }

    public static WorkflowSupportedDocumentsRepository_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static WorkflowSupportedDocumentsRepository newInstance() {
        return new WorkflowSupportedDocumentsRepository();
    }

    @Override // com.onfido.javax.inject.Provider
    public WorkflowSupportedDocumentsRepository get() {
        return newInstance();
    }
}
