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

/* compiled from: WorkflowResponse.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0081\b\u0018\u0000 !2\u00020\u0001:\u0002 !B5\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0010\b\u0001\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000bB\u0013\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\fJ\u000f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÂ\u0003J\u0019\u0010\u0012\u001a\u00020\u00002\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0017\u001a\u00020\bHÖ\u0001J&\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eHÁ\u0001¢\u0006\u0002\b\u001fR\u001e\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0002@\u0002X\u0083\u000e¢\u0006\b\n\u0000\u0012\u0004\b\r\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\""}, d2 = {"Lcom/onfido/workflow/internal/network/BiometricTokenStorageTaskInput;", "", "seen1", "", "ids", "", "Lcom/onfido/workflow/internal/network/BiometricTokenStorageTaskMediaInput;", "mediaUuid", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/util/List;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/util/List;)V", "getIds$annotations", "()V", "getMediaUuid", "()Ljava/lang/String;", "component1", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_workflow_release", "$serializer", "Companion", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Serializable
/* loaded from: classes6.dex */
public final /* data */ class BiometricTokenStorageTaskInput {
    private List<BiometricTokenStorageTaskMediaInput> ids;
    private final String mediaUuid;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final KSerializer<Object>[] $childSerializers = {new ArrayListSerializer(BiometricTokenStorageTaskMediaInput$$serializer.INSTANCE), null};

    private final List<BiometricTokenStorageTaskMediaInput> component1() {
        return this.ids;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ BiometricTokenStorageTaskInput copy$default(BiometricTokenStorageTaskInput biometricTokenStorageTaskInput, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = biometricTokenStorageTaskInput.ids;
        }
        return biometricTokenStorageTaskInput.copy(list);
    }

    @SerialName("media_id")
    private static /* synthetic */ void getIds$annotations() {
    }

    public final BiometricTokenStorageTaskInput copy(List<BiometricTokenStorageTaskMediaInput> ids) {
        Intrinsics.checkNotNullParameter(ids, "ids");
        return new BiometricTokenStorageTaskInput(ids);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof BiometricTokenStorageTaskInput) && Intrinsics.areEqual(this.ids, ((BiometricTokenStorageTaskInput) other).ids);
    }

    public final String getMediaUuid() {
        return this.mediaUuid;
    }

    public int hashCode() {
        return this.ids.hashCode();
    }

    public String toString() {
        return "BiometricTokenStorageTaskInput(ids=" + this.ids + ")";
    }

    /* compiled from: WorkflowResponse.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/workflow/internal/network/BiometricTokenStorageTaskInput$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/workflow/internal/network/BiometricTokenStorageTaskInput;", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<BiometricTokenStorageTaskInput> serializer() {
            return BiometricTokenStorageTaskInput$$serializer.INSTANCE;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ BiometricTokenStorageTaskInput(int i, @SerialName("media_id") List list, String str, SerializationConstructorMarker serializationConstructorMarker) {
        if (1 != (i & 1)) {
            PluginExceptionsKt.throwMissingFieldException(i, 1, BiometricTokenStorageTaskInput$$serializer.INSTANCE.getDescriptor());
        }
        this.ids = list;
        if ((i & 2) == 0) {
            this.mediaUuid = ((BiometricTokenStorageTaskMediaInput) list.get(0)).getMediaUuid();
        } else {
            this.mediaUuid = str;
        }
    }

    public BiometricTokenStorageTaskInput(List<BiometricTokenStorageTaskMediaInput> ids) {
        Intrinsics.checkNotNullParameter(ids, "ids");
        this.ids = ids;
        this.mediaUuid = ids.get(0).getMediaUuid();
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$onfido_workflow_release(BiometricTokenStorageTaskInput self, CompositeEncoder output, SerialDescriptor serialDesc) {
        output.encodeSerializableElement(serialDesc, 0, $childSerializers[0], self.ids);
        if (!output.shouldEncodeElementDefault(serialDesc, 1) && Intrinsics.areEqual(self.mediaUuid, self.ids.get(0).getMediaUuid())) {
            return;
        }
        output.encodeStringElement(serialDesc, 1, self.mediaUuid);
    }
}
