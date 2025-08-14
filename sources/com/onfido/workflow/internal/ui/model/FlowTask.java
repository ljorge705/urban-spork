package com.onfido.workflow.internal.ui.model;

import com.clevertap.android.sdk.Constants;
import com.onfido.workflow.internal.workflow.WorkflowTask;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FlowTask.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u00002\u00020\u0001:\u0004\u0003\u0004\u0005\u0006B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0004\u0007\b\t\n¨\u0006\u000b"}, d2 = {"Lcom/onfido/workflow/internal/ui/model/FlowTask;", "", "()V", "AwaitingNextTask", "InteractiveTask", "NoRunningTask", "NonInteractiveTask", "Lcom/onfido/workflow/internal/ui/model/FlowTask$AwaitingNextTask;", "Lcom/onfido/workflow/internal/ui/model/FlowTask$InteractiveTask;", "Lcom/onfido/workflow/internal/ui/model/FlowTask$NoRunningTask;", "Lcom/onfido/workflow/internal/ui/model/FlowTask$NonInteractiveTask;", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public abstract class FlowTask {
    public /* synthetic */ FlowTask(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* compiled from: FlowTask.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/workflow/internal/ui/model/FlowTask$NoRunningTask;", "Lcom/onfido/workflow/internal/ui/model/FlowTask;", "()V", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class NoRunningTask extends FlowTask {
        public static final NoRunningTask INSTANCE = new NoRunningTask();

        private NoRunningTask() {
            super(null);
        }
    }

    private FlowTask() {
    }

    /* compiled from: FlowTask.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/onfido/workflow/internal/ui/model/FlowTask$InteractiveTask;", "Lcom/onfido/workflow/internal/ui/model/FlowTask;", "workflowTask", "Lcom/onfido/workflow/internal/workflow/WorkflowTask;", "(Lcom/onfido/workflow/internal/workflow/WorkflowTask;)V", "getWorkflowTask", "()Lcom/onfido/workflow/internal/workflow/WorkflowTask;", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class InteractiveTask extends FlowTask {
        private final WorkflowTask workflowTask;

        public static /* synthetic */ InteractiveTask copy$default(InteractiveTask interactiveTask, WorkflowTask workflowTask, int i, Object obj) {
            if ((i & 1) != 0) {
                workflowTask = interactiveTask.workflowTask;
            }
            return interactiveTask.copy(workflowTask);
        }

        /* renamed from: component1, reason: from getter */
        public final WorkflowTask getWorkflowTask() {
            return this.workflowTask;
        }

        public final InteractiveTask copy(WorkflowTask workflowTask) {
            Intrinsics.checkNotNullParameter(workflowTask, "workflowTask");
            return new InteractiveTask(workflowTask);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof InteractiveTask) && Intrinsics.areEqual(this.workflowTask, ((InteractiveTask) other).workflowTask);
        }

        public final WorkflowTask getWorkflowTask() {
            return this.workflowTask;
        }

        public int hashCode() {
            return this.workflowTask.hashCode();
        }

        public String toString() {
            return "InteractiveTask(workflowTask=" + this.workflowTask + ")";
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public InteractiveTask(WorkflowTask workflowTask) {
            super(null);
            Intrinsics.checkNotNullParameter(workflowTask, "workflowTask");
            this.workflowTask = workflowTask;
        }
    }

    /* compiled from: FlowTask.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/workflow/internal/ui/model/FlowTask$NonInteractiveTask;", "Lcom/onfido/workflow/internal/ui/model/FlowTask;", "()V", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class NonInteractiveTask extends FlowTask {
        public static final NonInteractiveTask INSTANCE = new NonInteractiveTask();

        private NonInteractiveTask() {
            super(null);
        }
    }

    /* compiled from: FlowTask.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/workflow/internal/ui/model/FlowTask$AwaitingNextTask;", "Lcom/onfido/workflow/internal/ui/model/FlowTask;", "()V", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class AwaitingNextTask extends FlowTask {
        public static final AwaitingNextTask INSTANCE = new AwaitingNextTask();

        private AwaitingNextTask() {
            super(null);
        }
    }
}
