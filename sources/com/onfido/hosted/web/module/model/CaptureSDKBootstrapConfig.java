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

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0081\b\u0018\u0000 12\u00020\u0001:\u000201BI\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0001\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0002\u0010\u000eB-\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\u000fJ\t\u0010\u001d\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\tHÆ\u0003J\t\u0010 \u001a\u00020\u000bHÆ\u0003J5\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\n\u001a\u00020\u000bHÆ\u0001J\u0013\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010%\u001a\u00020\u0003HÖ\u0001J\t\u0010&\u001a\u00020'HÖ\u0001J&\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020\u00002\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.HÁ\u0001¢\u0006\u0002\b/R\u001e\u0010\b\u001a\u0004\u0018\u00010\t8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0014\u0010\u0011\u001a\u0004\b\u0015\u0010\u0016R\u001c\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0017\u0010\u0011\u001a\u0004\b\u0018\u0010\u0019R\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001a\u0010\u0011\u001a\u0004\b\u001b\u0010\u001c¨\u00062"}, d2 = {"Lcom/onfido/hosted/web/module/model/CaptureSDKBootstrapConfig;", "", "seen1", "", "clientConfiguration", "Lcom/onfido/hosted/web/module/model/CaptureSDKClientConfiguration;", "studio", "Lcom/onfido/hosted/web/module/model/CaptureSDKStudio;", "classic", "Lcom/onfido/hosted/web/module/model/CaptureSDKClassic;", OnfidoLauncher.KEY_CONFIG, "Lkotlinx/serialization/json/JsonObject;", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/onfido/hosted/web/module/model/CaptureSDKClientConfiguration;Lcom/onfido/hosted/web/module/model/CaptureSDKStudio;Lcom/onfido/hosted/web/module/model/CaptureSDKClassic;Lkotlinx/serialization/json/JsonObject;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Lcom/onfido/hosted/web/module/model/CaptureSDKClientConfiguration;Lcom/onfido/hosted/web/module/model/CaptureSDKStudio;Lcom/onfido/hosted/web/module/model/CaptureSDKClassic;Lkotlinx/serialization/json/JsonObject;)V", "getClassic$annotations", "()V", "getClassic", "()Lcom/onfido/hosted/web/module/model/CaptureSDKClassic;", "getClientConfiguration$annotations", "getClientConfiguration", "()Lcom/onfido/hosted/web/module/model/CaptureSDKClientConfiguration;", "getConfiguration$annotations", "getConfiguration", "()Lkotlinx/serialization/json/JsonObject;", "getStudio$annotations", "getStudio", "()Lcom/onfido/hosted/web/module/model/CaptureSDKStudio;", "component1", "component2", "component3", "component4", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_capture_sdk_core_release", "$serializer", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Serializable
/* loaded from: classes6.dex */
public final /* data */ class CaptureSDKBootstrapConfig {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final CaptureSDKClassic classic;
    private final CaptureSDKClientConfiguration clientConfiguration;
    private final JsonObject configuration;
    private final CaptureSDKStudio studio;

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/hosted/web/module/model/CaptureSDKBootstrapConfig$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/hosted/web/module/model/CaptureSDKBootstrapConfig;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public final KSerializer<CaptureSDKBootstrapConfig> serializer() {
            return CaptureSDKBootstrapConfig$$serializer.INSTANCE;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ CaptureSDKBootstrapConfig(int i, @SerialName("clientConfiguration") CaptureSDKClientConfiguration captureSDKClientConfiguration, @SerialName("studio") CaptureSDKStudio captureSDKStudio, @SerialName("module") CaptureSDKClassic captureSDKClassic, @SerialName(OnfidoLauncher.KEY_CONFIG) JsonObject jsonObject, SerializationConstructorMarker serializationConstructorMarker) {
        if (9 != (i & 9)) {
            PluginExceptionsKt.throwMissingFieldException(i, 9, CaptureSDKBootstrapConfig$$serializer.INSTANCE.getDescriptor());
        }
        this.clientConfiguration = captureSDKClientConfiguration;
        if ((i & 2) == 0) {
            this.studio = null;
        } else {
            this.studio = captureSDKStudio;
        }
        if ((i & 4) == 0) {
            this.classic = null;
        } else {
            this.classic = captureSDKClassic;
        }
        this.configuration = jsonObject;
    }

    public static /* synthetic */ CaptureSDKBootstrapConfig copy$default(CaptureSDKBootstrapConfig captureSDKBootstrapConfig, CaptureSDKClientConfiguration captureSDKClientConfiguration, CaptureSDKStudio captureSDKStudio, CaptureSDKClassic captureSDKClassic, JsonObject jsonObject, int i, Object obj) {
        if ((i & 1) != 0) {
            captureSDKClientConfiguration = captureSDKBootstrapConfig.clientConfiguration;
        }
        if ((i & 2) != 0) {
            captureSDKStudio = captureSDKBootstrapConfig.studio;
        }
        if ((i & 4) != 0) {
            captureSDKClassic = captureSDKBootstrapConfig.classic;
        }
        if ((i & 8) != 0) {
            jsonObject = captureSDKBootstrapConfig.configuration;
        }
        return captureSDKBootstrapConfig.copy(captureSDKClientConfiguration, captureSDKStudio, captureSDKClassic, jsonObject);
    }

    @SerialName("module")
    public static /* synthetic */ void getClassic$annotations() {
    }

    @SerialName("clientConfiguration")
    public static /* synthetic */ void getClientConfiguration$annotations() {
    }

    @SerialName(OnfidoLauncher.KEY_CONFIG)
    public static /* synthetic */ void getConfiguration$annotations() {
    }

    @SerialName("studio")
    public static /* synthetic */ void getStudio$annotations() {
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$onfido_capture_sdk_core_release(CaptureSDKBootstrapConfig self, CompositeEncoder output, SerialDescriptor serialDesc) {
        output.encodeSerializableElement(serialDesc, 0, CaptureSDKClientConfiguration$$serializer.INSTANCE, self.clientConfiguration);
        if (output.shouldEncodeElementDefault(serialDesc, 1) || self.studio != null) {
            output.encodeNullableSerializableElement(serialDesc, 1, CaptureSDKStudio$$serializer.INSTANCE, self.studio);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 2) || self.classic != null) {
            output.encodeNullableSerializableElement(serialDesc, 2, CaptureSDKClassic$$serializer.INSTANCE, self.classic);
        }
        output.encodeSerializableElement(serialDesc, 3, JsonObjectSerializer.INSTANCE, self.configuration);
    }

    /* renamed from: component1, reason: from getter */
    public final CaptureSDKClientConfiguration getClientConfiguration() {
        return this.clientConfiguration;
    }

    /* renamed from: component2, reason: from getter */
    public final CaptureSDKStudio getStudio() {
        return this.studio;
    }

    /* renamed from: component3, reason: from getter */
    public final CaptureSDKClassic getClassic() {
        return this.classic;
    }

    /* renamed from: component4, reason: from getter */
    public final JsonObject getConfiguration() {
        return this.configuration;
    }

    public final CaptureSDKBootstrapConfig copy(CaptureSDKClientConfiguration clientConfiguration, CaptureSDKStudio studio, CaptureSDKClassic classic, JsonObject configuration) {
        Intrinsics.checkNotNullParameter(clientConfiguration, "clientConfiguration");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        return new CaptureSDKBootstrapConfig(clientConfiguration, studio, classic, configuration);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CaptureSDKBootstrapConfig)) {
            return false;
        }
        CaptureSDKBootstrapConfig captureSDKBootstrapConfig = (CaptureSDKBootstrapConfig) other;
        return Intrinsics.areEqual(this.clientConfiguration, captureSDKBootstrapConfig.clientConfiguration) && Intrinsics.areEqual(this.studio, captureSDKBootstrapConfig.studio) && Intrinsics.areEqual(this.classic, captureSDKBootstrapConfig.classic) && Intrinsics.areEqual(this.configuration, captureSDKBootstrapConfig.configuration);
    }

    public final CaptureSDKClassic getClassic() {
        return this.classic;
    }

    public final CaptureSDKClientConfiguration getClientConfiguration() {
        return this.clientConfiguration;
    }

    public final JsonObject getConfiguration() {
        return this.configuration;
    }

    public final CaptureSDKStudio getStudio() {
        return this.studio;
    }

    public int hashCode() {
        int iHashCode = this.clientConfiguration.hashCode() * 31;
        CaptureSDKStudio captureSDKStudio = this.studio;
        int iHashCode2 = (iHashCode + (captureSDKStudio == null ? 0 : captureSDKStudio.hashCode())) * 31;
        CaptureSDKClassic captureSDKClassic = this.classic;
        return ((iHashCode2 + (captureSDKClassic != null ? captureSDKClassic.hashCode() : 0)) * 31) + this.configuration.hashCode();
    }

    public String toString() {
        return "CaptureSDKBootstrapConfig(clientConfiguration=" + this.clientConfiguration + ", studio=" + this.studio + ", classic=" + this.classic + ", configuration=" + this.configuration + ')';
    }

    public CaptureSDKBootstrapConfig(CaptureSDKClientConfiguration clientConfiguration, CaptureSDKStudio captureSDKStudio, CaptureSDKClassic captureSDKClassic, JsonObject configuration) {
        Intrinsics.checkNotNullParameter(clientConfiguration, "clientConfiguration");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        this.clientConfiguration = clientConfiguration;
        this.studio = captureSDKStudio;
        this.classic = captureSDKClassic;
        this.configuration = configuration;
    }

    public /* synthetic */ CaptureSDKBootstrapConfig(CaptureSDKClientConfiguration captureSDKClientConfiguration, CaptureSDKStudio captureSDKStudio, CaptureSDKClassic captureSDKClassic, JsonObject jsonObject, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(captureSDKClientConfiguration, (i & 2) != 0 ? null : captureSDKStudio, (i & 4) != 0 ? null : captureSDKClassic, jsonObject);
    }
}
