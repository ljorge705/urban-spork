package com.onfido.workflow.internal.data;

import com.onfido.dagger.internal.Factory;

/* loaded from: classes6.dex */
public final class WorkflowTaskDataSource_Factory implements Factory<WorkflowTaskDataSource> {
    @Override // com.onfido.javax.inject.Provider
    public WorkflowTaskDataSource get() {
        return newInstance();
    }

    public static WorkflowTaskDataSource_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static WorkflowTaskDataSource newInstance() {
        return new WorkflowTaskDataSource();
    }

    private static final class InstanceHolder {
        private static final WorkflowTaskDataSource_Factory INSTANCE = new WorkflowTaskDataSource_Factory();

        private InstanceHolder() {
        }
    }
}
