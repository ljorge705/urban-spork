package com.onfido.android.sdk.capture.internal.analytics.inhouse.network;

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
import kotlinx.serialization.internal.BooleanSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0081\b\u0018\u0000 -2\u00020\u0001:\u0002,-BI\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0002\u0010\fB)\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\rJ\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010\u001d\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010\u0011J:\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tHÆ\u0001¢\u0006\u0002\u0010\u001fJ\u0013\u0010 \u001a\u00020\t2\b\u0010!\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\"\u001a\u00020\u0003HÖ\u0001J\t\u0010#\u001a\u00020\u0005HÖ\u0001J&\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u00002\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*HÁ\u0001¢\u0006\u0002\b+R \u0010\b\u001a\u0004\u0018\u00010\t8\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u0012\u0012\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0013\u0010\u000f\u001a\u0004\b\u0014\u0010\u0015R\u001e\u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0016\u0010\u000f\u001a\u0004\b\u0017\u0010\u0015R\u001c\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0018\u0010\u000f\u001a\u0004\b\u0019\u0010\u0015¨\u0006."}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/network/SourceMetaData;", "", "seen1", "", "platform", "", "version", "sdkEnvironment", "hasEnvironmentIntegrity", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)V", "getHasEnvironmentIntegrity$annotations", "()V", "getHasEnvironmentIntegrity", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getPlatform$annotations", "getPlatform", "()Ljava/lang/String;", "getSdkEnvironment$annotations", "getSdkEnvironment", "getVersion$annotations", "getVersion", "component1", "component2", "component3", "component4", Constants.COPY_TYPE, "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/network/SourceMetaData;", "equals", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_capture_sdk_core_release", "$serializer", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Serializable
/* loaded from: classes2.dex */
public final /* data */ class SourceMetaData {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Boolean hasEnvironmentIntegrity;
    private final String platform;
    private final String sdkEnvironment;
    private final String version;

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/network/SourceMetaData$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/network/SourceMetaData;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public final KSerializer<SourceMetaData> serializer() {
            return SourceMetaData$$serializer.INSTANCE;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ SourceMetaData(int i, @SerialName("platform") String str, @SerialName("version") String str2, @SerialName("sdk_environment") String str3, @SerialName("environment_integrity") Boolean bool, SerializationConstructorMarker serializationConstructorMarker) {
        if (15 != (i & 15)) {
            PluginExceptionsKt.throwMissingFieldException(i, 15, SourceMetaData$$serializer.INSTANCE.getDescriptor());
        }
        this.platform = str;
        this.version = str2;
        this.sdkEnvironment = str3;
        this.hasEnvironmentIntegrity = bool;
    }

    public static /* synthetic */ SourceMetaData copy$default(SourceMetaData sourceMetaData, String str, String str2, String str3, Boolean bool, int i, Object obj) {
        if ((i & 1) != 0) {
            str = sourceMetaData.platform;
        }
        if ((i & 2) != 0) {
            str2 = sourceMetaData.version;
        }
        if ((i & 4) != 0) {
            str3 = sourceMetaData.sdkEnvironment;
        }
        if ((i & 8) != 0) {
            bool = sourceMetaData.hasEnvironmentIntegrity;
        }
        return sourceMetaData.copy(str, str2, str3, bool);
    }

    @SerialName("environment_integrity")
    public static /* synthetic */ void getHasEnvironmentIntegrity$annotations() {
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
    public static final /* synthetic */ void write$Self$onfido_capture_sdk_core_release(SourceMetaData self, CompositeEncoder output, SerialDescriptor serialDesc) {
        output.encodeStringElement(serialDesc, 0, self.platform);
        output.encodeStringElement(serialDesc, 1, self.version);
        output.encodeNullableSerializableElement(serialDesc, 2, StringSerializer.INSTANCE, self.sdkEnvironment);
        output.encodeNullableSerializableElement(serialDesc, 3, BooleanSerializer.INSTANCE, self.hasEnvironmentIntegrity);
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

    /* renamed from: component4, reason: from getter */
    public final Boolean getHasEnvironmentIntegrity() {
        return this.hasEnvironmentIntegrity;
    }

    public final SourceMetaData copy(String platform, String version, String sdkEnvironment, Boolean hasEnvironmentIntegrity) {
        Intrinsics.checkNotNullParameter(platform, "platform");
        Intrinsics.checkNotNullParameter(version, "version");
        return new SourceMetaData(platform, version, sdkEnvironment, hasEnvironmentIntegrity);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SourceMetaData)) {
            return false;
        }
        SourceMetaData sourceMetaData = (SourceMetaData) other;
        return Intrinsics.areEqual(this.platform, sourceMetaData.platform) && Intrinsics.areEqual(this.version, sourceMetaData.version) && Intrinsics.areEqual(this.sdkEnvironment, sourceMetaData.sdkEnvironment) && Intrinsics.areEqual(this.hasEnvironmentIntegrity, sourceMetaData.hasEnvironmentIntegrity);
    }

    public final Boolean getHasEnvironmentIntegrity() {
        return this.hasEnvironmentIntegrity;
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
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        Boolean bool = this.hasEnvironmentIntegrity;
        return iHashCode2 + (bool != null ? bool.hashCode() : 0);
    }

    public String toString() {
        return "SourceMetaData(platform=" + this.platform + ", version=" + this.version + ", sdkEnvironment=" + this.sdkEnvironment + ", hasEnvironmentIntegrity=" + this.hasEnvironmentIntegrity + ')';
    }

    public SourceMetaData(String platform, String version, String str, Boolean bool) {
        Intrinsics.checkNotNullParameter(platform, "platform");
        Intrinsics.checkNotNullParameter(version, "version");
        this.platform = platform;
        this.version = version;
        this.sdkEnvironment = str;
        this.hasEnvironmentIntegrity = bool;
    }
}
