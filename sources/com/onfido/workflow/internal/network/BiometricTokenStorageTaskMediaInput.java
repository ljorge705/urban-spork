package com.onfido.workflow.internal.network;

import com.clevertap.android.sdk.Constants;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;

/* compiled from: WorkflowResponse.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0081\b\u0018\u0000 \u001e2\u00020\u0001:\u0002\u001d\u001eB%\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bB\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\tJ\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\u0013\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0005HÖ\u0001J&\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bHÁ\u0001¢\u0006\u0002\b\u001cR\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\u001f"}, d2 = {"Lcom/onfido/workflow/internal/network/BiometricTokenStorageTaskMediaInput;", "", "seen1", "", "mediaUuid", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/lang/String;)V", "getMediaUuid$annotations", "()V", "getMediaUuid", "()Ljava/lang/String;", "component1", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_workflow_release", "$serializer", "Companion", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Serializable
/* loaded from: classes6.dex */
public final /* data */ class BiometricTokenStorageTaskMediaInput {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String mediaUuid;

    public static /* synthetic */ BiometricTokenStorageTaskMediaInput copy$default(BiometricTokenStorageTaskMediaInput biometricTokenStorageTaskMediaInput, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = biometricTokenStorageTaskMediaInput.mediaUuid;
        }
        return biometricTokenStorageTaskMediaInput.copy(str);
    }

    @SerialName("id")
    public static /* synthetic */ void getMediaUuid$annotations() {
    }

    /* renamed from: component1, reason: from getter */
    public final String getMediaUuid() {
        return this.mediaUuid;
    }

    public final BiometricTokenStorageTaskMediaInput copy(String mediaUuid) {
        Intrinsics.checkNotNullParameter(mediaUuid, "mediaUuid");
        return new BiometricTokenStorageTaskMediaInput(mediaUuid);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof BiometricTokenStorageTaskMediaInput) && Intrinsics.areEqual(this.mediaUuid, ((BiometricTokenStorageTaskMediaInput) other).mediaUuid);
    }

    public final String getMediaUuid() {
        return this.mediaUuid;
    }

    public int hashCode() {
        return this.mediaUuid.hashCode();
    }

    public String toString() {
        return "BiometricTokenStorageTaskMediaInput(mediaUuid=" + this.mediaUuid + ")";
    }

    /* compiled from: WorkflowResponse.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/workflow/internal/network/BiometricTokenStorageTaskMediaInput$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/workflow/internal/network/BiometricTokenStorageTaskMediaInput;", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<BiometricTokenStorageTaskMediaInput> serializer() {
            return BiometricTokenStorageTaskMediaInput$$serializer.INSTANCE;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ BiometricTokenStorageTaskMediaInput(int i, @SerialName("id") String str, SerializationConstructorMarker serializationConstructorMarker) {
        if (1 != (i & 1)) {
            PluginExceptionsKt.throwMissingFieldException(i, 1, BiometricTokenStorageTaskMediaInput$$serializer.INSTANCE.getDescriptor());
        }
        this.mediaUuid = str;
    }

    public BiometricTokenStorageTaskMediaInput(String mediaUuid) {
        Intrinsics.checkNotNullParameter(mediaUuid, "mediaUuid");
        this.mediaUuid = mediaUuid;
    }
}
