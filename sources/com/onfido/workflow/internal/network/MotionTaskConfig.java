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
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0081\b\u0018\u0000 \"2\u00020\u0001:\u0002!\"B-\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tB\u0017\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\nJ\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00052\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J&\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fHÁ\u0001¢\u0006\u0002\b R\u001c\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000f\u0010\f\u001a\u0004\b\u0010\u0010\u000e¨\u0006#"}, d2 = {"Lcom/onfido/workflow/internal/network/MotionTaskConfig;", "", "seen1", "", "showIntro", "", "audioEnabled", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IZZLkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(ZZ)V", "getAudioEnabled$annotations", "()V", "getAudioEnabled", "()Z", "getShowIntro$annotations", "getShowIntro", "component1", "component2", Constants.COPY_TYPE, "equals", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_workflow_release", "$serializer", "Companion", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Serializable
/* loaded from: classes6.dex */
public final /* data */ class MotionTaskConfig {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final boolean audioEnabled;
    private final boolean showIntro;

    public static /* synthetic */ MotionTaskConfig copy$default(MotionTaskConfig motionTaskConfig, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = motionTaskConfig.showIntro;
        }
        if ((i & 2) != 0) {
            z2 = motionTaskConfig.audioEnabled;
        }
        return motionTaskConfig.copy(z, z2);
    }

    @SerialName("record_audio")
    public static /* synthetic */ void getAudioEnabled$annotations() {
    }

    @SerialName("show_intro")
    public static /* synthetic */ void getShowIntro$annotations() {
    }

    /* renamed from: component1, reason: from getter */
    public final boolean getShowIntro() {
        return this.showIntro;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getAudioEnabled() {
        return this.audioEnabled;
    }

    public final MotionTaskConfig copy(boolean showIntro, boolean audioEnabled) {
        return new MotionTaskConfig(showIntro, audioEnabled);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MotionTaskConfig)) {
            return false;
        }
        MotionTaskConfig motionTaskConfig = (MotionTaskConfig) other;
        return this.showIntro == motionTaskConfig.showIntro && this.audioEnabled == motionTaskConfig.audioEnabled;
    }

    public final boolean getAudioEnabled() {
        return this.audioEnabled;
    }

    public final boolean getShowIntro() {
        return this.showIntro;
    }

    public int hashCode() {
        return (Boolean.hashCode(this.showIntro) * 31) + Boolean.hashCode(this.audioEnabled);
    }

    public String toString() {
        return "MotionTaskConfig(showIntro=" + this.showIntro + ", audioEnabled=" + this.audioEnabled + ")";
    }

    /* compiled from: WorkflowResponse.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/workflow/internal/network/MotionTaskConfig$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/workflow/internal/network/MotionTaskConfig;", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<MotionTaskConfig> serializer() {
            return MotionTaskConfig$$serializer.INSTANCE;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ MotionTaskConfig(int i, @SerialName("show_intro") boolean z, @SerialName("record_audio") boolean z2, SerializationConstructorMarker serializationConstructorMarker) {
        if (2 != (i & 2)) {
            PluginExceptionsKt.throwMissingFieldException(i, 2, MotionTaskConfig$$serializer.INSTANCE.getDescriptor());
        }
        if ((i & 1) == 0) {
            this.showIntro = true;
        } else {
            this.showIntro = z;
        }
        this.audioEnabled = z2;
    }

    public MotionTaskConfig(boolean z, boolean z2) {
        this.showIntro = z;
        this.audioEnabled = z2;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$onfido_workflow_release(MotionTaskConfig self, CompositeEncoder output, SerialDescriptor serialDesc) {
        if (output.shouldEncodeElementDefault(serialDesc, 0) || !self.showIntro) {
            output.encodeBooleanElement(serialDesc, 0, self.showIntro);
        }
        output.encodeBooleanElement(serialDesc, 1, self.audioEnabled);
    }

    public /* synthetic */ MotionTaskConfig(boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? true : z, z2);
    }
}
