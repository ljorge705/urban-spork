package com.onfido.api.client.data;

import com.clevertap.android.sdk.Constants;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.onfido.android.sdk.capture.core.OnfidoLauncher;
import com.onfido.api.client.data.PhotoDetection;
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
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

/* compiled from: SdkUploadMetaData.kt */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\"\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 B2\u00020\u0001:\u0002ABBi\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010¢\u0006\u0002\u0010\u0012Bm\b\u0011\u0012\u0006\u0010\u0013\u001a\u00020\f\u0012\n\b\u0001\u0010\u0014\u001a\u0004\u0018\u00010\u0015\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\n\u0012\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\n\b\u0001\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0001\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0001\u0010\u0011\u001a\u0004\u0018\u00010\u0010\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017¢\u0006\u0002\u0010\u0018BK\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010¢\u0006\u0002\u0010\u0019J\u000b\u0010+\u001a\u0004\u0018\u00010\u0015HÆ\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\bHÂ\u0003J\u000b\u0010-\u001a\u0004\u0018\u00010\nHÂ\u0003J\u0010\u0010.\u001a\u0004\u0018\u00010\fHÂ\u0003¢\u0006\u0002\u0010/J\u0010\u00100\u001a\u0004\u0018\u00010\u000eHÆ\u0003¢\u0006\u0002\u0010\u001fJ\u000b\u00101\u001a\u0004\u0018\u00010\u0010HÆ\u0003J\u000b\u00102\u001a\u0004\u0018\u00010\u0010HÆ\u0003Jb\u00103\u001a\u00020\u00002\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0010HÆ\u0001¢\u0006\u0002\u00104J\u0013\u00105\u001a\u00020\u000e2\b\u00106\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00107\u001a\u00020\fHÖ\u0001J\t\u00108\u001a\u00020\u0010HÖ\u0001J&\u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020\u00002\u0006\u0010<\u001a\u00020=2\u0006\u0010>\u001a\u00020?HÁ\u0001¢\u0006\u0002\b@R\u0018\u0010\t\u001a\u0004\u0018\u00010\n8\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u001a\u0010\u001bR\u0018\u0010\u0007\u001a\u0004\u0018\u00010\b8\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u001c\u0010\u001bR \u0010\r\u001a\u0004\u0018\u00010\u000e8\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010 \u0012\u0004\b\u001d\u0010\u001b\u001a\u0004\b\u001e\u0010\u001fR\u001e\u0010\u0014\u001a\u0004\u0018\u00010\u00158\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b!\u0010\u001b\u001a\u0004\b\"\u0010#R\u001a\u0010\u000b\u001a\u0004\u0018\u00010\f8\u0002X\u0083\u0004¢\u0006\n\n\u0002\u0010%\u0012\u0004\b$\u0010\u001bR\u001e\u0010\u000f\u001a\u0004\u0018\u00010\u00108\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b&\u0010\u001b\u001a\u0004\b'\u0010(R\u001e\u0010\u0011\u001a\u0004\u0018\u00010\u00108\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b)\u0010\u001b\u001a\u0004\b*\u0010(¨\u0006C"}, d2 = {"Lcom/onfido/api/client/data/SdkUploadMetaData;", "", "wasGlareDetected", "Lcom/onfido/api/client/data/PhotoDetection$ProcessingResult;", "blurDetection", "barcodeDetected", "mrzDetection", "deviceMetadata", "Lcom/onfido/api/client/data/DeviceMetadata;", OnfidoLauncher.KEY_CONFIG, "Lcom/onfido/api/client/data/SdkConfiguration;", "photoTakenCount", "", "hasEnvironmentIntegrity", "", "sdkSource", "", RemoteConfigConstants.RequestFieldKey.SDK_VERSION, "(Lcom/onfido/api/client/data/PhotoDetection$ProcessingResult;Lcom/onfido/api/client/data/PhotoDetection$ProcessingResult;Lcom/onfido/api/client/data/PhotoDetection$ProcessingResult;Lcom/onfido/api/client/data/PhotoDetection$ProcessingResult;Lcom/onfido/api/client/data/DeviceMetadata;Lcom/onfido/api/client/data/SdkConfiguration;Ljava/lang/Integer;ZLjava/lang/String;Ljava/lang/String;)V", "seen1", "photoDetection", "Lcom/onfido/api/client/data/PhotoDetection;", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/onfido/api/client/data/PhotoDetection;Lcom/onfido/api/client/data/DeviceMetadata;Lcom/onfido/api/client/data/SdkConfiguration;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Lcom/onfido/api/client/data/PhotoDetection;Lcom/onfido/api/client/data/DeviceMetadata;Lcom/onfido/api/client/data/SdkConfiguration;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;)V", "getConfiguration$annotations", "()V", "getDeviceMetadata$annotations", "getHasEnvironmentIntegrity$annotations", "getHasEnvironmentIntegrity", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getPhotoDetection$annotations", "getPhotoDetection", "()Lcom/onfido/api/client/data/PhotoDetection;", "getPhotoTakenCount$annotations", "Ljava/lang/Integer;", "getSdkSource$annotations", "getSdkSource", "()Ljava/lang/String;", "getSdkVersion$annotations", "getSdkVersion", "component1", "component2", "component3", "component4", "()Ljava/lang/Integer;", "component5", "component6", "component7", Constants.COPY_TYPE, "(Lcom/onfido/api/client/data/PhotoDetection;Lcom/onfido/api/client/data/DeviceMetadata;Lcom/onfido/api/client/data/SdkConfiguration;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;)Lcom/onfido/api/client/data/SdkUploadMetaData;", "equals", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_api_client", "$serializer", "Companion", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Serializable
/* loaded from: classes6.dex */
public final /* data */ class SdkUploadMetaData {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final SdkConfiguration configuration;
    private final DeviceMetadata deviceMetadata;
    private final Boolean hasEnvironmentIntegrity;
    private final PhotoDetection photoDetection;
    private final Integer photoTakenCount;
    private final String sdkSource;
    private final String sdkVersion;

    /* renamed from: component2, reason: from getter */
    private final DeviceMetadata getDeviceMetadata() {
        return this.deviceMetadata;
    }

    /* renamed from: component3, reason: from getter */
    private final SdkConfiguration getConfiguration() {
        return this.configuration;
    }

    /* renamed from: component4, reason: from getter */
    private final Integer getPhotoTakenCount() {
        return this.photoTakenCount;
    }

    public static /* synthetic */ SdkUploadMetaData copy$default(SdkUploadMetaData sdkUploadMetaData, PhotoDetection photoDetection, DeviceMetadata deviceMetadata, SdkConfiguration sdkConfiguration, Integer num, Boolean bool, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            photoDetection = sdkUploadMetaData.photoDetection;
        }
        if ((i & 2) != 0) {
            deviceMetadata = sdkUploadMetaData.deviceMetadata;
        }
        DeviceMetadata deviceMetadata2 = deviceMetadata;
        if ((i & 4) != 0) {
            sdkConfiguration = sdkUploadMetaData.configuration;
        }
        SdkConfiguration sdkConfiguration2 = sdkConfiguration;
        if ((i & 8) != 0) {
            num = sdkUploadMetaData.photoTakenCount;
        }
        Integer num2 = num;
        if ((i & 16) != 0) {
            bool = sdkUploadMetaData.hasEnvironmentIntegrity;
        }
        Boolean bool2 = bool;
        if ((i & 32) != 0) {
            str = sdkUploadMetaData.sdkSource;
        }
        String str3 = str;
        if ((i & 64) != 0) {
            str2 = sdkUploadMetaData.sdkVersion;
        }
        return sdkUploadMetaData.copy(photoDetection, deviceMetadata2, sdkConfiguration2, num2, bool2, str3, str2);
    }

    @SerialName(OnfidoLauncher.KEY_CONFIG)
    private static /* synthetic */ void getConfiguration$annotations() {
    }

    @SerialName("system")
    private static /* synthetic */ void getDeviceMetadata$annotations() {
    }

    @SerialName("environment_integrity")
    public static /* synthetic */ void getHasEnvironmentIntegrity$annotations() {
    }

    @SerialName("on_device_processing_results")
    public static /* synthetic */ void getPhotoDetection$annotations() {
    }

    @SerialName("take_number")
    private static /* synthetic */ void getPhotoTakenCount$annotations() {
    }

    @SerialName("sdk_source")
    public static /* synthetic */ void getSdkSource$annotations() {
    }

    @SerialName("sdk_version")
    public static /* synthetic */ void getSdkVersion$annotations() {
    }

    /* renamed from: component1, reason: from getter */
    public final PhotoDetection getPhotoDetection() {
        return this.photoDetection;
    }

    /* renamed from: component5, reason: from getter */
    public final Boolean getHasEnvironmentIntegrity() {
        return this.hasEnvironmentIntegrity;
    }

    /* renamed from: component6, reason: from getter */
    public final String getSdkSource() {
        return this.sdkSource;
    }

    /* renamed from: component7, reason: from getter */
    public final String getSdkVersion() {
        return this.sdkVersion;
    }

    public final SdkUploadMetaData copy(PhotoDetection photoDetection, DeviceMetadata deviceMetadata, SdkConfiguration configuration, Integer photoTakenCount, Boolean hasEnvironmentIntegrity, String sdkSource, String sdkVersion) {
        return new SdkUploadMetaData(photoDetection, deviceMetadata, configuration, photoTakenCount, hasEnvironmentIntegrity, sdkSource, sdkVersion);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SdkUploadMetaData)) {
            return false;
        }
        SdkUploadMetaData sdkUploadMetaData = (SdkUploadMetaData) other;
        return Intrinsics.areEqual(this.photoDetection, sdkUploadMetaData.photoDetection) && Intrinsics.areEqual(this.deviceMetadata, sdkUploadMetaData.deviceMetadata) && Intrinsics.areEqual(this.configuration, sdkUploadMetaData.configuration) && Intrinsics.areEqual(this.photoTakenCount, sdkUploadMetaData.photoTakenCount) && Intrinsics.areEqual(this.hasEnvironmentIntegrity, sdkUploadMetaData.hasEnvironmentIntegrity) && Intrinsics.areEqual(this.sdkSource, sdkUploadMetaData.sdkSource) && Intrinsics.areEqual(this.sdkVersion, sdkUploadMetaData.sdkVersion);
    }

    public final Boolean getHasEnvironmentIntegrity() {
        return this.hasEnvironmentIntegrity;
    }

    public final PhotoDetection getPhotoDetection() {
        return this.photoDetection;
    }

    public final String getSdkSource() {
        return this.sdkSource;
    }

    public final String getSdkVersion() {
        return this.sdkVersion;
    }

    public int hashCode() {
        PhotoDetection photoDetection = this.photoDetection;
        int iHashCode = (photoDetection == null ? 0 : photoDetection.hashCode()) * 31;
        DeviceMetadata deviceMetadata = this.deviceMetadata;
        int iHashCode2 = (iHashCode + (deviceMetadata == null ? 0 : deviceMetadata.hashCode())) * 31;
        SdkConfiguration sdkConfiguration = this.configuration;
        int iHashCode3 = (iHashCode2 + (sdkConfiguration == null ? 0 : sdkConfiguration.hashCode())) * 31;
        Integer num = this.photoTakenCount;
        int iHashCode4 = (iHashCode3 + (num == null ? 0 : num.hashCode())) * 31;
        Boolean bool = this.hasEnvironmentIntegrity;
        int iHashCode5 = (iHashCode4 + (bool == null ? 0 : bool.hashCode())) * 31;
        String str = this.sdkSource;
        int iHashCode6 = (iHashCode5 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.sdkVersion;
        return iHashCode6 + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "SdkUploadMetaData(photoDetection=" + this.photoDetection + ", deviceMetadata=" + this.deviceMetadata + ", configuration=" + this.configuration + ", photoTakenCount=" + this.photoTakenCount + ", hasEnvironmentIntegrity=" + this.hasEnvironmentIntegrity + ", sdkSource=" + this.sdkSource + ", sdkVersion=" + this.sdkVersion + ")";
    }

    /* compiled from: SdkUploadMetaData.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/api/client/data/SdkUploadMetaData$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/api/client/data/SdkUploadMetaData;", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<SdkUploadMetaData> serializer() {
            return SdkUploadMetaData$$serializer.INSTANCE;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ SdkUploadMetaData(int i, @SerialName("on_device_processing_results") PhotoDetection photoDetection, @SerialName("system") DeviceMetadata deviceMetadata, @SerialName(OnfidoLauncher.KEY_CONFIG) SdkConfiguration sdkConfiguration, @SerialName("take_number") Integer num, @SerialName("environment_integrity") Boolean bool, @SerialName("sdk_source") String str, @SerialName("sdk_version") String str2, SerializationConstructorMarker serializationConstructorMarker) {
        if (127 != (i & 127)) {
            PluginExceptionsKt.throwMissingFieldException(i, 127, SdkUploadMetaData$$serializer.INSTANCE.getDescriptor());
        }
        this.photoDetection = photoDetection;
        this.deviceMetadata = deviceMetadata;
        this.configuration = sdkConfiguration;
        this.photoTakenCount = num;
        this.hasEnvironmentIntegrity = bool;
        this.sdkSource = str;
        this.sdkVersion = str2;
    }

    public SdkUploadMetaData(PhotoDetection photoDetection, DeviceMetadata deviceMetadata, SdkConfiguration sdkConfiguration, Integer num, Boolean bool, String str, String str2) {
        this.photoDetection = photoDetection;
        this.deviceMetadata = deviceMetadata;
        this.configuration = sdkConfiguration;
        this.photoTakenCount = num;
        this.hasEnvironmentIntegrity = bool;
        this.sdkSource = str;
        this.sdkVersion = str2;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$onfido_api_client(SdkUploadMetaData self, CompositeEncoder output, SerialDescriptor serialDesc) {
        output.encodeNullableSerializableElement(serialDesc, 0, PhotoDetection$$serializer.INSTANCE, self.photoDetection);
        output.encodeNullableSerializableElement(serialDesc, 1, DeviceMetadata$$serializer.INSTANCE, self.deviceMetadata);
        output.encodeNullableSerializableElement(serialDesc, 2, SdkConfiguration$$serializer.INSTANCE, self.configuration);
        output.encodeNullableSerializableElement(serialDesc, 3, IntSerializer.INSTANCE, self.photoTakenCount);
        output.encodeNullableSerializableElement(serialDesc, 4, BooleanSerializer.INSTANCE, self.hasEnvironmentIntegrity);
        output.encodeNullableSerializableElement(serialDesc, 5, StringSerializer.INSTANCE, self.sdkSource);
        output.encodeNullableSerializableElement(serialDesc, 6, StringSerializer.INSTANCE, self.sdkVersion);
    }

    public SdkUploadMetaData(PhotoDetection.ProcessingResult processingResult, PhotoDetection.ProcessingResult processingResult2, PhotoDetection.ProcessingResult processingResult3, PhotoDetection.ProcessingResult processingResult4, DeviceMetadata deviceMetadata, SdkConfiguration sdkConfiguration, Integer num, boolean z, String str, String str2) {
        this(new PhotoDetection(processingResult, processingResult2, processingResult3, processingResult4), deviceMetadata, sdkConfiguration, num, Boolean.valueOf(z), str, str2);
    }
}
