package com.onfido.workflow.internal.network;

import com.onfido.android.sdk.capture.core.OnfidoLauncher;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonObjectSerializer;

/* compiled from: WorkflowResponse.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u0000 \"2\u00020\u0001:\u0002!\"BI\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\b\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0002\u0010\fB+\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\rJ&\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fHÁ\u0001¢\u0006\u0002\b R\u001c\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u000f\u001a\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0015\u0010\u000f\u001a\u0004\b\u0016\u0010\u0014R\u001e\u0010\t\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0017\u0010\u000f\u001a\u0004\b\u0018\u0010\u0011¨\u0006#"}, d2 = {"Lcom/onfido/workflow/internal/network/CurrentApplicantTask;", "", "seen1", "", "id", "", "taskDefId", OnfidoLauncher.KEY_CONFIG, "Lkotlinx/serialization/json/JsonObject;", "taskInput", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Lkotlinx/serialization/json/JsonObject;Lkotlinx/serialization/json/JsonObject;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/lang/String;Ljava/lang/String;Lkotlinx/serialization/json/JsonObject;Lkotlinx/serialization/json/JsonObject;)V", "getConfiguration$annotations", "()V", "getConfiguration", "()Lkotlinx/serialization/json/JsonObject;", "getId$annotations", "getId", "()Ljava/lang/String;", "getTaskDefId$annotations", "getTaskDefId", "getTaskInput$annotations", "getTaskInput", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_workflow_release", "$serializer", "Companion", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Serializable
/* loaded from: classes6.dex */
public final class CurrentApplicantTask {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final JsonObject configuration;
    private final String id;
    private final String taskDefId;
    private final JsonObject taskInput;

    @SerialName("config")
    public static /* synthetic */ void getConfiguration$annotations() {
    }

    @SerialName("id")
    public static /* synthetic */ void getId$annotations() {
    }

    @SerialName("task_def_id")
    public static /* synthetic */ void getTaskDefId$annotations() {
    }

    @SerialName("input")
    public static /* synthetic */ void getTaskInput$annotations() {
    }

    public final JsonObject getConfiguration() {
        return this.configuration;
    }

    public final String getId() {
        return this.id;
    }

    public final String getTaskDefId() {
        return this.taskDefId;
    }

    public final JsonObject getTaskInput() {
        return this.taskInput;
    }

    /* compiled from: WorkflowResponse.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/workflow/internal/network/CurrentApplicantTask$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/workflow/internal/network/CurrentApplicantTask;", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<CurrentApplicantTask> serializer() {
            return CurrentApplicantTask$$serializer.INSTANCE;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ CurrentApplicantTask(int i, @SerialName("id") String str, @SerialName("task_def_id") String str2, @SerialName("config") JsonObject jsonObject, @SerialName("input") JsonObject jsonObject2, SerializationConstructorMarker serializationConstructorMarker) {
        if (3 != (i & 3)) {
            PluginExceptionsKt.throwMissingFieldException(i, 3, CurrentApplicantTask$$serializer.INSTANCE.getDescriptor());
        }
        this.id = str;
        this.taskDefId = str2;
        if ((i & 4) == 0) {
            this.configuration = new JsonObject(MapsKt.emptyMap());
        } else {
            this.configuration = jsonObject;
        }
        if ((i & 8) == 0) {
            this.taskInput = new JsonObject(MapsKt.emptyMap());
        } else {
            this.taskInput = jsonObject2;
        }
    }

    public CurrentApplicantTask(String id, String taskDefId, JsonObject configuration, JsonObject jsonObject) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(taskDefId, "taskDefId");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        this.id = id;
        this.taskDefId = taskDefId;
        this.configuration = configuration;
        this.taskInput = jsonObject;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$onfido_workflow_release(CurrentApplicantTask self, CompositeEncoder output, SerialDescriptor serialDesc) {
        output.encodeStringElement(serialDesc, 0, self.id);
        output.encodeStringElement(serialDesc, 1, self.taskDefId);
        if (output.shouldEncodeElementDefault(serialDesc, 2) || !Intrinsics.areEqual(self.configuration, new JsonObject(MapsKt.emptyMap()))) {
            output.encodeSerializableElement(serialDesc, 2, JsonObjectSerializer.INSTANCE, self.configuration);
        }
        if (!output.shouldEncodeElementDefault(serialDesc, 3) && Intrinsics.areEqual(self.taskInput, new JsonObject(MapsKt.emptyMap()))) {
            return;
        }
        output.encodeNullableSerializableElement(serialDesc, 3, JsonObjectSerializer.INSTANCE, self.taskInput);
    }

    public /* synthetic */ CurrentApplicantTask(String str, String str2, JsonObject jsonObject, JsonObject jsonObject2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i & 4) != 0 ? new JsonObject(MapsKt.emptyMap()) : jsonObject, (i & 8) != 0 ? new JsonObject(MapsKt.emptyMap()) : jsonObject2);
    }
}
