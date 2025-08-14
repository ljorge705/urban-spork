package com.onfido.workflow.internal.network;

import com.clevertap.android.sdk.Constants;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;

/* compiled from: WorkflowResponse.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0081\b\u0018\u0000 \"2\u00020\u0001:\u0002!\"B-\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tB\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\nJ\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00052\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J&\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fHÁ\u0001¢\u0006\u0002\b R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u001c\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000f\u0010\f\u001a\u0004\b\u0010\u0010\u000e¨\u0006#"}, d2 = {"Lcom/onfido/workflow/internal/network/FaceVideoTaskConfig;", "", "seen1", "", "showIntro", "", "showVideoConfirmation", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IZZLkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(ZZ)V", "getShowIntro$annotations", "()V", "getShowIntro", "()Z", "getShowVideoConfirmation$annotations", "getShowVideoConfirmation", "component1", "component2", Constants.COPY_TYPE, "equals", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_workflow_release", "$serializer", "Companion", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Serializable
/* loaded from: classes6.dex */
public final /* data */ class FaceVideoTaskConfig {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final boolean showIntro;
    private final boolean showVideoConfirmation;

    public static /* synthetic */ FaceVideoTaskConfig copy$default(FaceVideoTaskConfig faceVideoTaskConfig, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = faceVideoTaskConfig.showIntro;
        }
        if ((i & 2) != 0) {
            z2 = faceVideoTaskConfig.showVideoConfirmation;
        }
        return faceVideoTaskConfig.copy(z, z2);
    }

    @SerialName("show_intro")
    public static /* synthetic */ void getShowIntro$annotations() {
    }

    @SerialName("show_video_confirmation")
    public static /* synthetic */ void getShowVideoConfirmation$annotations() {
    }

    /* renamed from: component1, reason: from getter */
    public final boolean getShowIntro() {
        return this.showIntro;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getShowVideoConfirmation() {
        return this.showVideoConfirmation;
    }

    public final FaceVideoTaskConfig copy(boolean showIntro, boolean showVideoConfirmation) {
        return new FaceVideoTaskConfig(showIntro, showVideoConfirmation);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FaceVideoTaskConfig)) {
            return false;
        }
        FaceVideoTaskConfig faceVideoTaskConfig = (FaceVideoTaskConfig) other;
        return this.showIntro == faceVideoTaskConfig.showIntro && this.showVideoConfirmation == faceVideoTaskConfig.showVideoConfirmation;
    }

    public final boolean getShowIntro() {
        return this.showIntro;
    }

    public final boolean getShowVideoConfirmation() {
        return this.showVideoConfirmation;
    }

    public int hashCode() {
        return (Boolean.hashCode(this.showIntro) * 31) + Boolean.hashCode(this.showVideoConfirmation);
    }

    public String toString() {
        return "FaceVideoTaskConfig(showIntro=" + this.showIntro + ", showVideoConfirmation=" + this.showVideoConfirmation + ")";
    }

    /* compiled from: WorkflowResponse.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/workflow/internal/network/FaceVideoTaskConfig$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/workflow/internal/network/FaceVideoTaskConfig;", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<FaceVideoTaskConfig> serializer() {
            return FaceVideoTaskConfig$$serializer.INSTANCE;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ FaceVideoTaskConfig(int i, @SerialName("show_intro") boolean z, @SerialName("show_video_confirmation") boolean z2, SerializationConstructorMarker serializationConstructorMarker) {
        if (3 != (i & 3)) {
            PluginExceptionsKt.throwMissingFieldException(i, 3, FaceVideoTaskConfig$$serializer.INSTANCE.getDescriptor());
        }
        this.showIntro = z;
        this.showVideoConfirmation = z2;
    }

    public FaceVideoTaskConfig(boolean z, boolean z2) {
        this.showIntro = z;
        this.showVideoConfirmation = z2;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$onfido_workflow_release(FaceVideoTaskConfig self, CompositeEncoder output, SerialDescriptor serialDesc) {
        output.encodeBooleanElement(serialDesc, 0, self.showIntro);
        output.encodeBooleanElement(serialDesc, 1, self.showVideoConfirmation);
    }
}
