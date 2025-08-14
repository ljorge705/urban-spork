package com.onfido.workflow.internal.utils;

import androidx.activity.result.ActivityResultRegistry;
import com.onfido.dagger.assisted.AssistedFactory;
import com.onfido.workflow.internal.utils.WorkflowIntentLauncher;
import kotlin.Metadata;

/* compiled from: WorkflowIntentLauncherFactory.kt */
@AssistedFactory
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b!\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H&¨\u0006\u0007"}, d2 = {"Lcom/onfido/workflow/internal/utils/WorkflowIntentLauncherFactory;", "Lcom/onfido/workflow/internal/utils/WorkflowIntentLauncher$Factory;", "()V", "create", "Lcom/onfido/workflow/internal/utils/WorkflowIntentLauncherImpl;", "activityResultRegistry", "Landroidx/activity/result/ActivityResultRegistry;", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public abstract class WorkflowIntentLauncherFactory implements WorkflowIntentLauncher.Factory {
    @Override // com.onfido.workflow.internal.utils.WorkflowIntentLauncher.Factory
    public abstract WorkflowIntentLauncherImpl create(ActivityResultRegistry activityResultRegistry);
}
