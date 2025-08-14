package com.onfido.workflow.internal.network;

import com.clevertap.android.sdk.Constants;
import java.util.List;
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
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

/* compiled from: CompleteTaskRequest.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0081\b\u0018\u0000  2\u00020\u0001:\u0003\u001f !B+\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0010\b\u0001\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tB\u0013\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\nJ\u000f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\u0019\u0010\u0010\u001a\u00020\u00002\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J&\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dHÁ\u0001¢\u0006\u0002\b\u001eR\"\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006\""}, d2 = {"Lcom/onfido/workflow/internal/network/CompleteTaskRequest;", "", "seen1", "", "data", "", "Lcom/onfido/workflow/internal/network/CompleteTaskRequest$CompletionMetaData;", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/util/List;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/util/List;)V", "getData$annotations", "()V", "getData", "()Ljava/util/List;", "component1", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_workflow_release", "$serializer", "Companion", "CompletionMetaData", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Serializable
/* loaded from: classes6.dex */
public final /* data */ class CompleteTaskRequest {
    private final List<CompletionMetaData> data;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final KSerializer<Object>[] $childSerializers = {new ArrayListSerializer(CompleteTaskRequest$CompletionMetaData$$serializer.INSTANCE)};

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ CompleteTaskRequest copy$default(CompleteTaskRequest completeTaskRequest, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = completeTaskRequest.data;
        }
        return completeTaskRequest.copy(list);
    }

    @SerialName("data")
    public static /* synthetic */ void getData$annotations() {
    }

    public final List<CompletionMetaData> component1() {
        return this.data;
    }

    public final CompleteTaskRequest copy(List<CompletionMetaData> data) {
        Intrinsics.checkNotNullParameter(data, "data");
        return new CompleteTaskRequest(data);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof CompleteTaskRequest) && Intrinsics.areEqual(this.data, ((CompleteTaskRequest) other).data);
    }

    public final List<CompletionMetaData> getData() {
        return this.data;
    }

    public int hashCode() {
        return this.data.hashCode();
    }

    public String toString() {
        return "CompleteTaskRequest(data=" + this.data + ")";
    }

    /* compiled from: CompleteTaskRequest.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/workflow/internal/network/CompleteTaskRequest$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/workflow/internal/network/CompleteTaskRequest;", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<CompleteTaskRequest> serializer() {
            return CompleteTaskRequest$$serializer.INSTANCE;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ CompleteTaskRequest(int i, @SerialName("data") List list, SerializationConstructorMarker serializationConstructorMarker) {
        if (1 != (i & 1)) {
            PluginExceptionsKt.throwMissingFieldException(i, 1, CompleteTaskRequest$$serializer.INSTANCE.getDescriptor());
        }
        this.data = list;
    }

    public CompleteTaskRequest(List<CompletionMetaData> data) {
        Intrinsics.checkNotNullParameter(data, "data");
        this.data = data;
    }

    /* compiled from: CompleteTaskRequest.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 \"2\u00020\u0001:\u0002!\"B1\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tB\u0019\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\nJ\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0005HÖ\u0001J&\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fHÁ\u0001¢\u0006\u0002\b R\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000f\u0010\f\u001a\u0004\b\u0010\u0010\u000e¨\u0006#"}, d2 = {"Lcom/onfido/workflow/internal/network/CompleteTaskRequest$CompletionMetaData;", "", "seen1", "", "uploadId", "", "type", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/lang/String;Ljava/lang/String;)V", "getType$annotations", "()V", "getType", "()Ljava/lang/String;", "getUploadId$annotations", "getUploadId", "component1", "component2", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_workflow_release", "$serializer", "Companion", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    @Serializable
    public static final /* data */ class CompletionMetaData {

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final String type;
        private final String uploadId;

        public static /* synthetic */ CompletionMetaData copy$default(CompletionMetaData completionMetaData, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = completionMetaData.uploadId;
            }
            if ((i & 2) != 0) {
                str2 = completionMetaData.type;
            }
            return completionMetaData.copy(str, str2);
        }

        @SerialName("type")
        public static /* synthetic */ void getType$annotations() {
        }

        @SerialName("id")
        public static /* synthetic */ void getUploadId$annotations() {
        }

        /* renamed from: component1, reason: from getter */
        public final String getUploadId() {
            return this.uploadId;
        }

        /* renamed from: component2, reason: from getter */
        public final String getType() {
            return this.type;
        }

        public final CompletionMetaData copy(String uploadId, String type) {
            Intrinsics.checkNotNullParameter(uploadId, "uploadId");
            return new CompletionMetaData(uploadId, type);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CompletionMetaData)) {
                return false;
            }
            CompletionMetaData completionMetaData = (CompletionMetaData) other;
            return Intrinsics.areEqual(this.uploadId, completionMetaData.uploadId) && Intrinsics.areEqual(this.type, completionMetaData.type);
        }

        public final String getType() {
            return this.type;
        }

        public final String getUploadId() {
            return this.uploadId;
        }

        public int hashCode() {
            int iHashCode = this.uploadId.hashCode() * 31;
            String str = this.type;
            return iHashCode + (str == null ? 0 : str.hashCode());
        }

        public String toString() {
            return "CompletionMetaData(uploadId=" + this.uploadId + ", type=" + this.type + ")";
        }

        /* compiled from: CompleteTaskRequest.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/workflow/internal/network/CompleteTaskRequest$CompletionMetaData$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/workflow/internal/network/CompleteTaskRequest$CompletionMetaData;", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<CompletionMetaData> serializer() {
                return CompleteTaskRequest$CompletionMetaData$$serializer.INSTANCE;
            }
        }

        @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
        public /* synthetic */ CompletionMetaData(int i, @SerialName("id") String str, @SerialName("type") String str2, SerializationConstructorMarker serializationConstructorMarker) {
            if (1 != (i & 1)) {
                PluginExceptionsKt.throwMissingFieldException(i, 1, CompleteTaskRequest$CompletionMetaData$$serializer.INSTANCE.getDescriptor());
            }
            this.uploadId = str;
            if ((i & 2) == 0) {
                this.type = null;
            } else {
                this.type = str2;
            }
        }

        public CompletionMetaData(String uploadId, String str) {
            Intrinsics.checkNotNullParameter(uploadId, "uploadId");
            this.uploadId = uploadId;
            this.type = str;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$onfido_workflow_release(CompletionMetaData self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeStringElement(serialDesc, 0, self.uploadId);
            if (!output.shouldEncodeElementDefault(serialDesc, 1) && self.type == null) {
                return;
            }
            output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.type);
        }

        public /* synthetic */ CompletionMetaData(String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i & 2) != 0 ? null : str2);
        }
    }
}
