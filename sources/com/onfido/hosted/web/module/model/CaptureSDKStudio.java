package com.onfido.hosted.web.module.model;

import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.core.OnfidoLauncher;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
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

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0081\b\u0018\u0000 *2\u00020\u0001:\u0002)*B=\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0002\u0010\fB\u001d\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\rJ\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001a\u001a\u00020\tHÆ\u0003J'\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÖ\u0001J\t\u0010 \u001a\u00020\u0005HÖ\u0001J&\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u00002\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'HÁ\u0001¢\u0006\u0002\b(R\u001c\u0010\b\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u000f\u001a\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0015\u0010\u000f\u001a\u0004\b\u0016\u0010\u0017¨\u0006+"}, d2 = {"Lcom/onfido/hosted/web/module/model/CaptureSDKStudio;", "", "seen1", "", "workflowRunId", "", "task", "Lcom/onfido/hosted/web/module/model/CaptureSDKStudioTask;", OnfidoLauncher.KEY_CONFIG, "Lkotlinx/serialization/json/JsonObject;", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Lcom/onfido/hosted/web/module/model/CaptureSDKStudioTask;Lkotlinx/serialization/json/JsonObject;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/lang/String;Lcom/onfido/hosted/web/module/model/CaptureSDKStudioTask;Lkotlinx/serialization/json/JsonObject;)V", "getConfiguration$annotations", "()V", "getConfiguration", "()Lkotlinx/serialization/json/JsonObject;", "getTask$annotations", "getTask", "()Lcom/onfido/hosted/web/module/model/CaptureSDKStudioTask;", "getWorkflowRunId$annotations", "getWorkflowRunId", "()Ljava/lang/String;", "component1", "component2", "component3", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_capture_sdk_core_release", "$serializer", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Serializable
/* loaded from: classes6.dex */
public final /* data */ class CaptureSDKStudio {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final JsonObject configuration;
    private final CaptureSDKStudioTask task;
    private final String workflowRunId;

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/hosted/web/module/model/CaptureSDKStudio$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/hosted/web/module/model/CaptureSDKStudio;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public final KSerializer<CaptureSDKStudio> serializer() {
            return CaptureSDKStudio$$serializer.INSTANCE;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ CaptureSDKStudio(int i, @SerialName("workflowRunId") String str, @SerialName("task") CaptureSDKStudioTask captureSDKStudioTask, @SerialName(OnfidoLauncher.KEY_CONFIG) JsonObject jsonObject, SerializationConstructorMarker serializationConstructorMarker) {
        if (7 != (i & 7)) {
            PluginExceptionsKt.throwMissingFieldException(i, 7, CaptureSDKStudio$$serializer.INSTANCE.getDescriptor());
        }
        this.workflowRunId = str;
        this.task = captureSDKStudioTask;
        this.configuration = jsonObject;
    }

    public static /* synthetic */ CaptureSDKStudio copy$default(CaptureSDKStudio captureSDKStudio, String str, CaptureSDKStudioTask captureSDKStudioTask, JsonObject jsonObject, int i, Object obj) {
        if ((i & 1) != 0) {
            str = captureSDKStudio.workflowRunId;
        }
        if ((i & 2) != 0) {
            captureSDKStudioTask = captureSDKStudio.task;
        }
        if ((i & 4) != 0) {
            jsonObject = captureSDKStudio.configuration;
        }
        return captureSDKStudio.copy(str, captureSDKStudioTask, jsonObject);
    }

    @SerialName(OnfidoLauncher.KEY_CONFIG)
    public static /* synthetic */ void getConfiguration$annotations() {
    }

    @SerialName("task")
    public static /* synthetic */ void getTask$annotations() {
    }

    @SerialName("workflowRunId")
    public static /* synthetic */ void getWorkflowRunId$annotations() {
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$onfido_capture_sdk_core_release(CaptureSDKStudio self, CompositeEncoder output, SerialDescriptor serialDesc) {
        output.encodeStringElement(serialDesc, 0, self.workflowRunId);
        output.encodeSerializableElement(serialDesc, 1, CaptureSDKStudioTask$$serializer.INSTANCE, self.task);
        output.encodeSerializableElement(serialDesc, 2, JsonObjectSerializer.INSTANCE, self.configuration);
    }

    /* renamed from: component1, reason: from getter */
    public final String getWorkflowRunId() {
        return this.workflowRunId;
    }

    /* renamed from: component2, reason: from getter */
    public final CaptureSDKStudioTask getTask() {
        return this.task;
    }

    /* renamed from: component3, reason: from getter */
    public final JsonObject getConfiguration() {
        return this.configuration;
    }

    public final CaptureSDKStudio copy(String workflowRunId, CaptureSDKStudioTask task, JsonObject configuration) {
        Intrinsics.checkNotNullParameter(workflowRunId, "workflowRunId");
        Intrinsics.checkNotNullParameter(task, "task");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        return new CaptureSDKStudio(workflowRunId, task, configuration);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CaptureSDKStudio)) {
            return false;
        }
        CaptureSDKStudio captureSDKStudio = (CaptureSDKStudio) other;
        return Intrinsics.areEqual(this.workflowRunId, captureSDKStudio.workflowRunId) && Intrinsics.areEqual(this.task, captureSDKStudio.task) && Intrinsics.areEqual(this.configuration, captureSDKStudio.configuration);
    }

    public final JsonObject getConfiguration() {
        return this.configuration;
    }

    public final CaptureSDKStudioTask getTask() {
        return this.task;
    }

    public final String getWorkflowRunId() {
        return this.workflowRunId;
    }

    public int hashCode() {
        return (((this.workflowRunId.hashCode() * 31) + this.task.hashCode()) * 31) + this.configuration.hashCode();
    }

    public String toString() {
        return "CaptureSDKStudio(workflowRunId=" + this.workflowRunId + ", task=" + this.task + ", configuration=" + this.configuration + ')';
    }

    public CaptureSDKStudio(String workflowRunId, CaptureSDKStudioTask task, JsonObject configuration) {
        Intrinsics.checkNotNullParameter(workflowRunId, "workflowRunId");
        Intrinsics.checkNotNullParameter(task, "task");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        this.workflowRunId = workflowRunId;
        this.task = task;
        this.configuration = configuration;
    }
}
