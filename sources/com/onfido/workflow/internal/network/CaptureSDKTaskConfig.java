package com.onfido.workflow.internal.network;

import com.clevertap.android.sdk.Constants;
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
import kotlinx.serialization.internal.StringSerializer;

/* compiled from: WorkflowResponse.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0001\u0018\u0000  2\u00020\u0001:\u0003\u001e\u001f B1\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nB\u0005¢\u0006\u0002\u0010\u000bJ&\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cHÁ\u0001¢\u0006\u0002\b\u001dR\u0018\u0010\u0004\u001a\u00020\u00058\u0002@\u0002X\u0083.¢\u0006\b\n\u0000\u0012\u0004\b\f\u0010\u000bR&\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\r\u0010\u000b\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0012\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u000fR\u0011\u0010\u0014\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u000f¨\u0006!"}, d2 = {"Lcom/onfido/workflow/internal/network/CaptureSDKTaskConfig;", "", "seen1", "", "captureModule", "Lcom/onfido/workflow/internal/network/CaptureSDKTaskConfig$CaptureModule;", "countryOfOperation", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/onfido/workflow/internal/network/CaptureSDKTaskConfig$CaptureModule;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "()V", "getCaptureModule$annotations", "getCountryOfOperation$annotations", "getCountryOfOperation", "()Ljava/lang/String;", "setCountryOfOperation", "(Ljava/lang/String;)V", "moduleName", "getModuleName", "type", "getType", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_workflow_release", "$serializer", "CaptureModule", "Companion", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Serializable
/* loaded from: classes6.dex */
public final class CaptureSDKTaskConfig {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private CaptureModule captureModule;
    private String countryOfOperation;

    @SerialName("capture_module")
    private static /* synthetic */ void getCaptureModule$annotations() {
    }

    @SerialName("country_of_operation")
    public static /* synthetic */ void getCountryOfOperation$annotations() {
    }

    public final String getCountryOfOperation() {
        return this.countryOfOperation;
    }

    public final void setCountryOfOperation(String str) {
        this.countryOfOperation = str;
    }

    /* compiled from: WorkflowResponse.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/workflow/internal/network/CaptureSDKTaskConfig$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/workflow/internal/network/CaptureSDKTaskConfig;", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<CaptureSDKTaskConfig> serializer() {
            return CaptureSDKTaskConfig$$serializer.INSTANCE;
        }
    }

    public CaptureSDKTaskConfig() {
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ CaptureSDKTaskConfig(int i, @SerialName("capture_module") CaptureModule captureModule, @SerialName("country_of_operation") String str, SerializationConstructorMarker serializationConstructorMarker) {
        if (1 != (i & 1)) {
            PluginExceptionsKt.throwMissingFieldException(i, 1, CaptureSDKTaskConfig$$serializer.INSTANCE.getDescriptor());
        }
        this.captureModule = captureModule;
        if ((i & 2) == 0) {
            this.countryOfOperation = null;
        } else {
            this.countryOfOperation = str;
        }
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$onfido_workflow_release(CaptureSDKTaskConfig self, CompositeEncoder output, SerialDescriptor serialDesc) {
        CaptureSDKTaskConfig$CaptureModule$$serializer captureSDKTaskConfig$CaptureModule$$serializer = CaptureSDKTaskConfig$CaptureModule$$serializer.INSTANCE;
        CaptureModule captureModule = self.captureModule;
        if (captureModule == null) {
            Intrinsics.throwUninitializedPropertyAccessException("captureModule");
            captureModule = null;
        }
        output.encodeSerializableElement(serialDesc, 0, captureSDKTaskConfig$CaptureModule$$serializer, captureModule);
        if (!output.shouldEncodeElementDefault(serialDesc, 1) && self.countryOfOperation == null) {
            return;
        }
        output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.countryOfOperation);
    }

    public final String getModuleName() {
        CaptureModule captureModule = this.captureModule;
        if (captureModule == null) {
            Intrinsics.throwUninitializedPropertyAccessException("captureModule");
            captureModule = null;
        }
        return captureModule.getModuleName();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: WorkflowResponse.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0083\b\u0018\u0000 \"2\u00020\u0001:\u0002!\"B1\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tB\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\nJ\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0005HÖ\u0001J&\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fHÁ\u0001¢\u0006\u0002\b R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u001c\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000f\u0010\f\u001a\u0004\b\u0010\u0010\u000e¨\u0006#"}, d2 = {"Lcom/onfido/workflow/internal/network/CaptureSDKTaskConfig$CaptureModule;", "", "seen1", "", "moduleName", "", "type", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/lang/String;Ljava/lang/String;)V", "getModuleName$annotations", "()V", "getModuleName", "()Ljava/lang/String;", "getType$annotations", "getType", "component1", "component2", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_workflow_release", "$serializer", "Companion", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    @Serializable
    static final /* data */ class CaptureModule {

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final String moduleName;
        private final String type;

        public static /* synthetic */ CaptureModule copy$default(CaptureModule captureModule, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = captureModule.moduleName;
            }
            if ((i & 2) != 0) {
                str2 = captureModule.type;
            }
            return captureModule.copy(str, str2);
        }

        @SerialName("module")
        public static /* synthetic */ void getModuleName$annotations() {
        }

        @SerialName("type")
        public static /* synthetic */ void getType$annotations() {
        }

        /* renamed from: component1, reason: from getter */
        public final String getModuleName() {
            return this.moduleName;
        }

        /* renamed from: component2, reason: from getter */
        public final String getType() {
            return this.type;
        }

        public final CaptureModule copy(String moduleName, String type) {
            Intrinsics.checkNotNullParameter(moduleName, "moduleName");
            Intrinsics.checkNotNullParameter(type, "type");
            return new CaptureModule(moduleName, type);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CaptureModule)) {
                return false;
            }
            CaptureModule captureModule = (CaptureModule) other;
            return Intrinsics.areEqual(this.moduleName, captureModule.moduleName) && Intrinsics.areEqual(this.type, captureModule.type);
        }

        public final String getModuleName() {
            return this.moduleName;
        }

        public final String getType() {
            return this.type;
        }

        public int hashCode() {
            return (this.moduleName.hashCode() * 31) + this.type.hashCode();
        }

        public String toString() {
            return "CaptureModule(moduleName=" + this.moduleName + ", type=" + this.type + ")";
        }

        /* compiled from: WorkflowResponse.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/workflow/internal/network/CaptureSDKTaskConfig$CaptureModule$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/workflow/internal/network/CaptureSDKTaskConfig$CaptureModule;", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<CaptureModule> serializer() {
                return CaptureSDKTaskConfig$CaptureModule$$serializer.INSTANCE;
            }
        }

        @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
        public /* synthetic */ CaptureModule(int i, @SerialName("module") String str, @SerialName("type") String str2, SerializationConstructorMarker serializationConstructorMarker) {
            if (3 != (i & 3)) {
                PluginExceptionsKt.throwMissingFieldException(i, 3, CaptureSDKTaskConfig$CaptureModule$$serializer.INSTANCE.getDescriptor());
            }
            this.moduleName = str;
            this.type = str2;
        }

        public CaptureModule(String moduleName, String type) {
            Intrinsics.checkNotNullParameter(moduleName, "moduleName");
            Intrinsics.checkNotNullParameter(type, "type");
            this.moduleName = moduleName;
            this.type = type;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$onfido_workflow_release(CaptureModule self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeStringElement(serialDesc, 0, self.moduleName);
            output.encodeStringElement(serialDesc, 1, self.type);
        }
    }

    public final String getType() {
        CaptureModule captureModule = this.captureModule;
        if (captureModule == null) {
            Intrinsics.throwUninitializedPropertyAccessException("captureModule");
            captureModule = null;
        }
        return captureModule.getType();
    }
}
