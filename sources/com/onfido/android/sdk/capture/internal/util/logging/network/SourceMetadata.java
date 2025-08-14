package com.onfido.android.sdk.capture.internal.util.logging.network;

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

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0081\b\u0018\u0000 &2\u00020\u0001:\u0002%&B=\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nB\u001f\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0005HÆ\u0003J)\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001c\u001a\u00020\u0005HÖ\u0001J&\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u00002\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#HÁ\u0001¢\u0006\u0002\b$R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\r\u001a\u0004\b\u0011\u0010\u000fR\u001c\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\r\u001a\u0004\b\u0013\u0010\u000f¨\u0006'"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/util/logging/network/SourceMetadata;", "", "seen1", "", "platform", "", "version", "sdkEnvironment", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getPlatform$annotations", "()V", "getPlatform", "()Ljava/lang/String;", "getSdkEnvironment$annotations", "getSdkEnvironment", "getVersion$annotations", "getVersion", "component1", "component2", "component3", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_capture_sdk_core_release", "$serializer", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Serializable
/* loaded from: classes2.dex */
public final /* data */ class SourceMetadata {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String platform;
    private final String sdkEnvironment;
    private final String version;

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/util/logging/network/SourceMetadata$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/android/sdk/capture/internal/util/logging/network/SourceMetadata;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public final KSerializer<SourceMetadata> serializer() {
            return SourceMetadata$$serializer.INSTANCE;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ SourceMetadata(int i, @SerialName("platform") String str, @SerialName("version") String str2, @SerialName("sdk_environment") String str3, SerializationConstructorMarker serializationConstructorMarker) {
        if (7 != (i & 7)) {
            PluginExceptionsKt.throwMissingFieldException(i, 7, SourceMetadata$$serializer.INSTANCE.getDescriptor());
        }
        this.platform = str;
        this.version = str2;
        this.sdkEnvironment = str3;
    }

    public static /* synthetic */ SourceMetadata copy$default(SourceMetadata sourceMetadata, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = sourceMetadata.platform;
        }
        if ((i & 2) != 0) {
            str2 = sourceMetadata.version;
        }
        if ((i & 4) != 0) {
            str3 = sourceMetadata.sdkEnvironment;
        }
        return sourceMetadata.copy(str, str2, str3);
    }

    @SerialName("platform")
    public static /* synthetic */ void getPlatform$annotations() {
    }

    @SerialName("sdk_environment")
    public static /* synthetic */ void getSdkEnvironment$annotations() {
    }

    @SerialName("version")
    public static /* synthetic */ void getVersion$annotations() {
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$onfido_capture_sdk_core_release(SourceMetadata self, CompositeEncoder output, SerialDescriptor serialDesc) {
        output.encodeStringElement(serialDesc, 0, self.platform);
        output.encodeStringElement(serialDesc, 1, self.version);
        output.encodeNullableSerializableElement(serialDesc, 2, StringSerializer.INSTANCE, self.sdkEnvironment);
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
    public final String getSdkEnvironment() {
        return this.sdkEnvironment;
    }

    public final SourceMetadata copy(String platform, String version, String sdkEnvironment) {
        Intrinsics.checkNotNullParameter(platform, "platform");
        Intrinsics.checkNotNullParameter(version, "version");
        return new SourceMetadata(platform, version, sdkEnvironment);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SourceMetadata)) {
            return false;
        }
        SourceMetadata sourceMetadata = (SourceMetadata) other;
        return Intrinsics.areEqual(this.platform, sourceMetadata.platform) && Intrinsics.areEqual(this.version, sourceMetadata.version) && Intrinsics.areEqual(this.sdkEnvironment, sourceMetadata.sdkEnvironment);
    }

    public final String getPlatform() {
        return this.platform;
    }

    public final String getSdkEnvironment() {
        return this.sdkEnvironment;
    }

    public final String getVersion() {
        return this.version;
    }

    public int hashCode() {
        int iHashCode = ((this.platform.hashCode() * 31) + this.version.hashCode()) * 31;
        String str = this.sdkEnvironment;
        return iHashCode + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        return "SourceMetadata(platform=" + this.platform + ", version=" + this.version + ", sdkEnvironment=" + this.sdkEnvironment + ')';
    }

    public SourceMetadata(String platform, String version, String str) {
        Intrinsics.checkNotNullParameter(platform, "platform");
        Intrinsics.checkNotNullParameter(version, "version");
        this.platform = platform;
        this.version = version;
        this.sdkEnvironment = str;
    }
}
