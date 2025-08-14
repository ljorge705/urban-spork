package com.onfido.workflow.internal.data;

import com.onfido.javax.inject.Inject;
import com.onfido.workflow.internal.di.WorkflowScope;
import com.onfido.workflow.internal.ui.model.FlowTask;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WorkflowTaskDataSource.kt */
@WorkflowScope
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001B\u0007\b\u0007¢\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/onfido/workflow/internal/data/WorkflowTaskDataSource;", "", "()V", "flowTask", "Lio/reactivex/rxjava3/subjects/BehaviorSubject;", "Lcom/onfido/workflow/internal/ui/model/FlowTask;", "getFlowTask", "()Lio/reactivex/rxjava3/subjects/BehaviorSubject;", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class WorkflowTaskDataSource {
    private final BehaviorSubject<FlowTask> flowTask;

    public final BehaviorSubject<FlowTask> getFlowTask() {
        return this.flowTask;
    }

    @Inject
    public WorkflowTaskDataSource() {
        BehaviorSubject<FlowTask> behaviorSubjectCreateDefault = BehaviorSubject.createDefault(FlowTask.NoRunningTask.INSTANCE);
        Intrinsics.checkNotNullExpressionValue(behaviorSubjectCreateDefault, "createDefault(...)");
        this.flowTask = behaviorSubjectCreateDefault;
    }
}
