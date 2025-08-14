package com.onfido.workflow.internal.utils;

import androidx.activity.result.ActivityResultRegistry;
import com.onfido.dagger.internal.InstanceFactory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes6.dex */
public final class WorkflowIntentLauncherFactory_Impl extends WorkflowIntentLauncherFactory {
    private final WorkflowIntentLauncherImpl_Factory delegateFactory;

    WorkflowIntentLauncherFactory_Impl(WorkflowIntentLauncherImpl_Factory workflowIntentLauncherImpl_Factory) {
        this.delegateFactory = workflowIntentLauncherImpl_Factory;
    }

    @Override // com.onfido.workflow.internal.utils.WorkflowIntentLauncher.Factory
    public WorkflowIntentLauncherImpl create(ActivityResultRegistry activityResultRegistry) {
        return this.delegateFactory.get(activityResultRegistry);
    }

    public static Provider<WorkflowIntentLauncherFactory> create(WorkflowIntentLauncherImpl_Factory workflowIntentLauncherImpl_Factory) {
        return InstanceFactory.create(new WorkflowIntentLauncherFactory_Impl(workflowIntentLauncherImpl_Factory));
    }
}
