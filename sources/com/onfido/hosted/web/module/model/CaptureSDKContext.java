package com.onfido.hosted.web.module.model;

import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.core.OnfidoLauncher;
import io.sentry.protocol.OperatingSystem;
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

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0081\b\u0018\u0000 42\u00020\u0001:\u000234BU\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\n\u0012\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0002\u0010\u000fB/\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\u0010J\t\u0010 \u001a\u00020\u0005HÆ\u0003J\t\u0010!\u001a\u00020\u0005HÆ\u0003J\t\u0010\"\u001a\u00020\bHÆ\u0003J\t\u0010#\u001a\u00020\nHÆ\u0003J\t\u0010$\u001a\u00020\fHÆ\u0003J;\u0010%\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\fHÆ\u0001J\u0013\u0010&\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010)\u001a\u00020\u0003HÖ\u0001J\t\u0010*\u001a\u00020\u0005HÖ\u0001J&\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\u00002\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u000201HÁ\u0001¢\u0006\u0002\b2R\u001c\u0010\t\u001a\u00020\n8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0015\u0010\u0012\u001a\u0004\b\u0016\u0010\u0017R\u001c\u0010\u000b\u001a\u00020\f8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0018\u0010\u0012\u001a\u0004\b\u0019\u0010\u001aR\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001b\u0010\u0012\u001a\u0004\b\u001c\u0010\u001dR\u001c\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001e\u0010\u0012\u001a\u0004\b\u001f\u0010\u001d¨\u00065"}, d2 = {"Lcom/onfido/hosted/web/module/model/CaptureSDKContext;", "", "seen1", "", "platform", "", "version", OperatingSystem.TYPE, "Lcom/onfido/hosted/web/module/model/CaptureSDKOS;", OnfidoLauncher.KEY_CONFIG, "Lcom/onfido/hosted/web/module/model/CaptureSDKContextConfiguration;", "permission", "Lcom/onfido/hosted/web/module/model/CaptureSDKContextPermission;", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Lcom/onfido/hosted/web/module/model/CaptureSDKOS;Lcom/onfido/hosted/web/module/model/CaptureSDKContextConfiguration;Lcom/onfido/hosted/web/module/model/CaptureSDKContextPermission;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/lang/String;Ljava/lang/String;Lcom/onfido/hosted/web/module/model/CaptureSDKOS;Lcom/onfido/hosted/web/module/model/CaptureSDKContextConfiguration;Lcom/onfido/hosted/web/module/model/CaptureSDKContextPermission;)V", "getConfiguration$annotations", "()V", "getConfiguration", "()Lcom/onfido/hosted/web/module/model/CaptureSDKContextConfiguration;", "getOs$annotations", "getOs", "()Lcom/onfido/hosted/web/module/model/CaptureSDKOS;", "getPermission$annotations", "getPermission", "()Lcom/onfido/hosted/web/module/model/CaptureSDKContextPermission;", "getPlatform$annotations", "getPlatform", "()Ljava/lang/String;", "getVersion$annotations", "getVersion", "component1", "component2", "component3", "component4", "component5", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_capture_sdk_core_release", "$serializer", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Serializable
/* loaded from: classes6.dex */
public final /* data */ class CaptureSDKContext {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final CaptureSDKContextConfiguration configuration;
    private final CaptureSDKOS os;
    private final CaptureSDKContextPermission permission;
    private final String platform;
    private final String version;

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/hosted/web/module/model/CaptureSDKContext$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/hosted/web/module/model/CaptureSDKContext;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public final KSerializer<CaptureSDKContext> serializer() {
            return CaptureSDKContext$$serializer.INSTANCE;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ CaptureSDKContext(int i, @SerialName("platform") String str, @SerialName("version") String str2, @SerialName(OperatingSystem.TYPE) CaptureSDKOS captureSDKOS, @SerialName(OnfidoLauncher.KEY_CONFIG) CaptureSDKContextConfiguration captureSDKContextConfiguration, @SerialName("permission") CaptureSDKContextPermission captureSDKContextPermission, SerializationConstructorMarker serializationConstructorMarker) {
        if (15 != (i & 15)) {
            PluginExceptionsKt.throwMissingFieldException(i, 15, CaptureSDKContext$$serializer.INSTANCE.getDescriptor());
        }
        this.platform = str;
        this.version = str2;
        this.os = captureSDKOS;
        this.configuration = captureSDKContextConfiguration;
        if ((i & 16) == 0) {
            this.permission = new CaptureSDKContextPermission(false, 1, (DefaultConstructorMarker) null);
        } else {
            this.permission = captureSDKContextPermission;
        }
    }

    public static /* synthetic */ CaptureSDKContext copy$default(CaptureSDKContext captureSDKContext, String str, String str2, CaptureSDKOS captureSDKOS, CaptureSDKContextConfiguration captureSDKContextConfiguration, CaptureSDKContextPermission captureSDKContextPermission, int i, Object obj) {
        if ((i & 1) != 0) {
            str = captureSDKContext.platform;
        }
        if ((i & 2) != 0) {
            str2 = captureSDKContext.version;
        }
        String str3 = str2;
        if ((i & 4) != 0) {
            captureSDKOS = captureSDKContext.os;
        }
        CaptureSDKOS captureSDKOS2 = captureSDKOS;
        if ((i & 8) != 0) {
            captureSDKContextConfiguration = captureSDKContext.configuration;
        }
        CaptureSDKContextConfiguration captureSDKContextConfiguration2 = captureSDKContextConfiguration;
        if ((i & 16) != 0) {
            captureSDKContextPermission = captureSDKContext.permission;
        }
        return captureSDKContext.copy(str, str3, captureSDKOS2, captureSDKContextConfiguration2, captureSDKContextPermission);
    }

    @SerialName(OnfidoLauncher.KEY_CONFIG)
    public static /* synthetic */ void getConfiguration$annotations() {
    }

    @SerialName(OperatingSystem.TYPE)
    public static /* synthetic */ void getOs$annotations() {
    }

    @SerialName("permission")
    public static /* synthetic */ void getPermission$annotations() {
    }

    @SerialName("platform")
    public static /* synthetic */ void getPlatform$annotations() {
    }

    @SerialName("version")
    public static /* synthetic */ void getVersion$annotations() {
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$onfido_capture_sdk_core_release(CaptureSDKContext self, CompositeEncoder output, SerialDescriptor serialDesc) {
        output.encodeStringElement(serialDesc, 0, self.platform);
        output.encodeStringElement(serialDesc, 1, self.version);
        output.encodeSerializableElement(serialDesc, 2, CaptureSDKOS$$serializer.INSTANCE, self.os);
        output.encodeSerializableElement(serialDesc, 3, CaptureSDKContextConfiguration$$serializer.INSTANCE, self.configuration);
        if (!output.shouldEncodeElementDefault(serialDesc, 4) && Intrinsics.areEqual(self.permission, new CaptureSDKContextPermission(false, 1, (DefaultConstructorMarker) null))) {
            return;
        }
        output.encodeSerializableElement(serialDesc, 4, CaptureSDKContextPermission$$serializer.INSTANCE, self.permission);
    }

    /* renamed from: component1, reason: from getter */
    public final String getPlatform() {
        return this.platform;
    }

    /* renamed from: component2, reason: from getter */
    public final String getVersion() {
        return this.version;
    }

    /* renamed from: component3, reason: from getter */
    public final CaptureSDKOS getOs() {
        return this.os;
    }

    /* renamed from: component4, reason: from getter */
    public final CaptureSDKContextConfiguration getConfiguration() {
        return this.configuration;
    }

    /* renamed from: component5, reason: from getter */
    public final CaptureSDKContextPermission getPermission() {
        return this.permission;
    }

    public final CaptureSDKContext copy(String platform, String version, CaptureSDKOS os, CaptureSDKContextConfiguration configuration, CaptureSDKContextPermission permission) {
        Intrinsics.checkNotNullParameter(platform, "platform");
        Intrinsics.checkNotNullParameter(version, "version");
        Intrinsics.checkNotNullParameter(os, "os");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(permission, "permission");
        return new CaptureSDKContext(platform, version, os, configuration, permission);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CaptureSDKContext)) {
            return false;
        }
        CaptureSDKContext captureSDKContext = (CaptureSDKContext) other;
        return Intrinsics.areEqual(this.platform, captureSDKContext.platform) && Intrinsics.areEqual(this.version, captureSDKContext.version) && Intrinsics.areEqual(this.os, captureSDKContext.os) && Intrinsics.areEqual(this.configuration, captureSDKContext.configuration) && Intrinsics.areEqual(this.permission, captureSDKContext.permission);
    }

    public final CaptureSDKContextConfiguration getConfiguration() {
        return this.configuration;
    }

    public final CaptureSDKOS getOs() {
        return this.os;
    }

    public final CaptureSDKContextPermission getPermission() {
        return this.permission;
    }

    public final String getPlatform() {
        return this.platform;
    }

    public final String getVersion() {
        return this.version;
    }

    public int hashCode() {
        return (((((((this.platform.hashCode() * 31) + this.version.hashCode()) * 31) + this.os.hashCode()) * 31) + this.configuration.hashCode()) * 31) + this.permission.hashCode();
    }

    public String toString() {
        return "CaptureSDKContext(platform=" + this.platform + ", version=" + this.version + ", os=" + this.os + ", configuration=" + this.configuration + ", permission=" + this.permission + ')';
    }

    public CaptureSDKContext(String platform, String version, CaptureSDKOS os, CaptureSDKContextConfiguration configuration, CaptureSDKContextPermission permission) {
        Intrinsics.checkNotNullParameter(platform, "platform");
        Intrinsics.checkNotNullParameter(version, "version");
        Intrinsics.checkNotNullParameter(os, "os");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(permission, "permission");
        this.platform = platform;
        this.version = version;
        this.os = os;
        this.configuration = configuration;
        this.permission = permission;
    }

    public /* synthetic */ CaptureSDKContext(String str, String str2, CaptureSDKOS captureSDKOS, CaptureSDKContextConfiguration captureSDKContextConfiguration, CaptureSDKContextPermission captureSDKContextPermission, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, captureSDKOS, captureSDKContextConfiguration, (i & 16) != 0 ? new CaptureSDKContextPermission(false, 1, (DefaultConstructorMarker) null) : captureSDKContextPermission);
    }
}
