package com.onfido.workflow.internal.network;

import com.clevertap.android.sdk.Constants;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;

/* compiled from: WorkflowResponse.kt */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0081\b\u0018\u0000 ,2\u00020\u0001:\u0002+,BC\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0010\b\u0001\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0002\u0010\rB'\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0002\u0010\u000eJ\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0007HÆ\u0003J\u000f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\n0\tHÆ\u0003J/\u0010\u001c\u001a\u00020\u00002\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tHÆ\u0001J\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010 \u001a\u00020\u0003HÖ\u0001J\t\u0010!\u001a\u00020\"HÖ\u0001J&\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u00002\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)HÁ\u0001¢\u0006\u0002\b*R\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0013\u0010\u0010\u001a\u0004\b\u0014\u0010\u0015R\"\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0016\u0010\u0010\u001a\u0004\b\u0017\u0010\u0018¨\u0006-"}, d2 = {"Lcom/onfido/workflow/internal/network/WorkflowResponse;", "", "seen1", "", "currentTask", "Lcom/onfido/workflow/internal/network/CurrentApplicantTask;", "interactiveTaskStatus", "Lcom/onfido/workflow/internal/network/InteractiveTaskStatus;", "remainingApplicantTasks", "", "Lcom/onfido/workflow/internal/network/RemainingApplicantTask;", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/onfido/workflow/internal/network/CurrentApplicantTask;Lcom/onfido/workflow/internal/network/InteractiveTaskStatus;Ljava/util/List;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Lcom/onfido/workflow/internal/network/CurrentApplicantTask;Lcom/onfido/workflow/internal/network/InteractiveTaskStatus;Ljava/util/List;)V", "getCurrentTask$annotations", "()V", "getCurrentTask", "()Lcom/onfido/workflow/internal/network/CurrentApplicantTask;", "getInteractiveTaskStatus$annotations", "getInteractiveTaskStatus", "()Lcom/onfido/workflow/internal/network/InteractiveTaskStatus;", "getRemainingApplicantTasks$annotations", "getRemainingApplicantTasks", "()Ljava/util/List;", "component1", "component2", "component3", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_workflow_release", "$serializer", "Companion", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Serializable
/* loaded from: classes6.dex */
public final /* data */ class WorkflowResponse {
    private final CurrentApplicantTask currentTask;
    private final InteractiveTaskStatus interactiveTaskStatus;
    private final List<RemainingApplicantTask> remainingApplicantTasks;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final KSerializer<Object>[] $childSerializers = {null, null, new ArrayListSerializer(RemainingApplicantTask$$serializer.INSTANCE)};

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ WorkflowResponse copy$default(WorkflowResponse workflowResponse, CurrentApplicantTask currentApplicantTask, InteractiveTaskStatus interactiveTaskStatus, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            currentApplicantTask = workflowResponse.currentTask;
        }
        if ((i & 2) != 0) {
            interactiveTaskStatus = workflowResponse.interactiveTaskStatus;
        }
        if ((i & 4) != 0) {
            list = workflowResponse.remainingApplicantTasks;
        }
        return workflowResponse.copy(currentApplicantTask, interactiveTaskStatus, list);
    }

    @SerialName("current_applicant_task")
    public static /* synthetic */ void getCurrentTask$annotations() {
    }

    @SerialName("interactive_tasks_status")
    public static /* synthetic */ void getInteractiveTaskStatus$annotations() {
    }

    @SerialName("remaining_applicant_tasks")
    public static /* synthetic */ void getRemainingApplicantTasks$annotations() {
    }

    /* renamed from: component1, reason: from getter */
    public final CurrentApplicantTask getCurrentTask() {
        return this.currentTask;
    }

    /* renamed from: component2, reason: from getter */
    public final InteractiveTaskStatus getInteractiveTaskStatus() {
        return this.interactiveTaskStatus;
    }

    public final List<RemainingApplicantTask> component3() {
        return this.remainingApplicantTasks;
    }

    public final WorkflowResponse copy(CurrentApplicantTask currentTask, InteractiveTaskStatus interactiveTaskStatus, List<RemainingApplicantTask> remainingApplicantTasks) {
        Intrinsics.checkNotNullParameter(interactiveTaskStatus, "interactiveTaskStatus");
        Intrinsics.checkNotNullParameter(remainingApplicantTasks, "remainingApplicantTasks");
        return new WorkflowResponse(currentTask, interactiveTaskStatus, remainingApplicantTasks);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof WorkflowResponse)) {
            return false;
        }
        WorkflowResponse workflowResponse = (WorkflowResponse) other;
        return Intrinsics.areEqual(this.currentTask, workflowResponse.currentTask) && this.interactiveTaskStatus == workflowResponse.interactiveTaskStatus && Intrinsics.areEqual(this.remainingApplicantTasks, workflowResponse.remainingApplicantTasks);
    }

    public final CurrentApplicantTask getCurrentTask() {
        return this.currentTask;
    }

    public final InteractiveTaskStatus getInteractiveTaskStatus() {
        return this.interactiveTaskStatus;
    }

    public final List<RemainingApplicantTask> getRemainingApplicantTasks() {
        return this.remainingApplicantTasks;
    }

    public int hashCode() {
        CurrentApplicantTask currentApplicantTask = this.currentTask;
        return ((((currentApplicantTask == null ? 0 : currentApplicantTask.hashCode()) * 31) + this.interactiveTaskStatus.hashCode()) * 31) + this.remainingApplicantTasks.hashCode();
    }

    public String toString() {
        return "WorkflowResponse(currentTask=" + this.currentTask + ", interactiveTaskStatus=" + this.interactiveTaskStatus + ", remainingApplicantTasks=" + this.remainingApplicantTasks + ")";
    }

    /* compiled from: WorkflowResponse.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/workflow/internal/network/WorkflowResponse$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/workflow/internal/network/WorkflowResponse;", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<WorkflowResponse> serializer() {
            return WorkflowResponse$$serializer.INSTANCE;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ WorkflowResponse(int i, @SerialName("current_applicant_task") CurrentApplicantTask currentApplicantTask, @SerialName("interactive_tasks_status") InteractiveTaskStatus interactiveTaskStatus, @SerialName("remaining_applicant_tasks") List list, SerializationConstructorMarker serializationConstructorMarker) {
        if (3 != (i & 3)) {
            PluginExceptionsKt.throwMissingFieldException(i, 3, WorkflowResponse$$serializer.INSTANCE.getDescriptor());
        }
        this.currentTask = currentApplicantTask;
        this.interactiveTaskStatus = interactiveTaskStatus;
        if ((i & 4) == 0) {
            this.remainingApplicantTasks = CollectionsKt.emptyList();
        } else {
            this.remainingApplicantTasks = list;
        }
    }

    public WorkflowResponse(CurrentApplicantTask currentApplicantTask, InteractiveTaskStatus interactiveTaskStatus, List<RemainingApplicantTask> remainingApplicantTasks) {
        Intrinsics.checkNotNullParameter(interactiveTaskStatus, "interactiveTaskStatus");
        Intrinsics.checkNotNullParameter(remainingApplicantTasks, "remainingApplicantTasks");
        this.currentTask = currentApplicantTask;
        this.interactiveTaskStatus = interactiveTaskStatus;
        this.remainingApplicantTasks = remainingApplicantTasks;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$onfido_workflow_release(WorkflowResponse self, CompositeEncoder output, SerialDescriptor serialDesc) {
        KSerializer<Object>[] kSerializerArr = $childSerializers;
        output.encodeNullableSerializableElement(serialDesc, 0, CurrentApplicantTask$$serializer.INSTANCE, self.currentTask);
        output.encodeSerializableElement(serialDesc, 1, InteractiveTaskStatus$$serializer.INSTANCE, self.interactiveTaskStatus);
        if (!output.shouldEncodeElementDefault(serialDesc, 2) && Intrinsics.areEqual(self.remainingApplicantTasks, CollectionsKt.emptyList())) {
            return;
        }
        output.encodeSerializableElement(serialDesc, 2, kSerializerArr[2], self.remainingApplicantTasks);
    }

    public /* synthetic */ WorkflowResponse(CurrentApplicantTask currentApplicantTask, InteractiveTaskStatus interactiveTaskStatus, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(currentApplicantTask, interactiveTaskStatus, (i & 4) != 0 ? CollectionsKt.emptyList() : list);
    }
}
