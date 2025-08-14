package com.onfido.api.client.data;

import com.clevertap.android.sdk.Constants;
import io.sentry.SentryEvent;
import io.sentry.protocol.Device;
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

/* compiled from: DeviceMetadata.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b$\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 B2\u00020\u0001:\u0002ABB\u0091\u0001\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\u0002\u0010\u0011BY\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u0006\u0010\f\u001a\u00020\u0005\u0012\b\b\u0002\u0010\r\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0005¢\u0006\u0002\u0010\u0012J\t\u0010)\u001a\u00020\u0005HÆ\u0003J\t\u0010*\u001a\u00020\u0005HÆ\u0003J\t\u0010+\u001a\u00020\u0005HÆ\u0003J\t\u0010,\u001a\u00020\u0005HÆ\u0003J\t\u0010-\u001a\u00020\u0005HÆ\u0003J\t\u0010.\u001a\u00020\u0005HÆ\u0003J\t\u0010/\u001a\u00020\u0005HÆ\u0003J\t\u00100\u001a\u00020\u0005HÆ\u0003J\t\u00101\u001a\u00020\u0005HÆ\u0003J\t\u00102\u001a\u00020\u0005HÆ\u0003Jm\u00103\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u00052\b\b\u0002\u0010\r\u001a\u00020\u00052\b\b\u0002\u0010\u000e\u001a\u00020\u0005HÆ\u0001J\u0013\u00104\u001a\u0002052\b\u00106\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00107\u001a\u00020\u0003HÖ\u0001J\t\u00108\u001a\u00020\u0005HÖ\u0001J&\u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020\u00002\u0006\u0010<\u001a\u00020=2\u0006\u0010>\u001a\u00020?HÁ\u0001¢\u0006\u0002\b@R\u001c\u0010\u000b\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u001c\u0010\f\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0017\u0010\u0014\u001a\u0004\b\u0018\u0010\u0016R\u001c\u0010\b\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0019\u0010\u0014\u001a\u0004\b\u001a\u0010\u0016R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001b\u0010\u0014\u001a\u0004\b\u001c\u0010\u0016R\u001c\u0010\n\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001d\u0010\u0014\u001a\u0004\b\u001e\u0010\u0016R\u001c\u0010\u0007\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001f\u0010\u0014\u001a\u0004\b \u0010\u0016R\u001c\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b!\u0010\u0014\u001a\u0004\b\"\u0010\u0016R\u001c\u0010\r\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b#\u0010\u0014\u001a\u0004\b$\u0010\u0016R\u001c\u0010\u000e\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b%\u0010\u0014\u001a\u0004\b&\u0010\u0016R\u001c\u0010\t\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b'\u0010\u0014\u001a\u0004\b(\u0010\u0016¨\u0006C"}, d2 = {"Lcom/onfido/api/client/data/DeviceMetadata;", "", "seen1", "", SentryEvent.JsonKeys.FINGERPRINT, "", Device.JsonKeys.MODEL, Device.JsonKeys.MANUFACTURER, Device.JsonKeys.BRAND, "product", "hardware", "androidApiLevel", "androidVersionNumber", OperatingSystem.TYPE, "osVersion", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAndroidApiLevel$annotations", "()V", "getAndroidApiLevel", "()Ljava/lang/String;", "getAndroidVersionNumber$annotations", "getAndroidVersionNumber", "getBrand$annotations", "getBrand", "getFingerprint$annotations", "getFingerprint", "getHardware$annotations", "getHardware", "getManufacturer$annotations", "getManufacturer", "getModel$annotations", "getModel", "getOs$annotations", "getOs", "getOsVersion$annotations", "getOsVersion", "getProduct$annotations", "getProduct", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_api_client", "$serializer", "Companion", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Serializable
/* loaded from: classes6.dex */
public final /* data */ class DeviceMetadata {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String androidApiLevel;
    private final String androidVersionNumber;
    private final String brand;
    private final String fingerprint;
    private final String hardware;
    private final String manufacturer;
    private final String model;
    private final String os;
    private final String osVersion;
    private final String product;

    @SerialName("androidApiLevel")
    public static /* synthetic */ void getAndroidApiLevel$annotations() {
    }

    @SerialName("androidVersionNumber")
    public static /* synthetic */ void getAndroidVersionNumber$annotations() {
    }

    @SerialName(Device.JsonKeys.BRAND)
    public static /* synthetic */ void getBrand$annotations() {
    }

    @SerialName(SentryEvent.JsonKeys.FINGERPRINT)
    public static /* synthetic */ void getFingerprint$annotations() {
    }

    @SerialName("hardware")
    public static /* synthetic */ void getHardware$annotations() {
    }

    @SerialName(Device.JsonKeys.MANUFACTURER)
    public static /* synthetic */ void getManufacturer$annotations() {
    }

    @SerialName(Device.JsonKeys.MODEL)
    public static /* synthetic */ void getModel$annotations() {
    }

    @SerialName(OperatingSystem.TYPE)
    public static /* synthetic */ void getOs$annotations() {
    }

    @SerialName("os_version")
    public static /* synthetic */ void getOsVersion$annotations() {
    }

    @SerialName("product")
    public static /* synthetic */ void getProduct$annotations() {
    }

    /* renamed from: component1, reason: from getter */
    public final String getFingerprint() {
        return this.fingerprint;
    }

    /* renamed from: component10, reason: from getter */
    public final String getOsVersion() {
        return this.osVersion;
    }

    /* renamed from: component2, reason: from getter */
    public final String getModel() {
        return this.model;
    }

    /* renamed from: component3, reason: from getter */
    public final String getManufacturer() {
        return this.manufacturer;
    }

    /* renamed from: component4, reason: from getter */
    public final String getBrand() {
        return this.brand;
    }

    /* renamed from: component5, reason: from getter */
    public final String getProduct() {
        return this.product;
    }

    /* renamed from: component6, reason: from getter */
    public final String getHardware() {
        return this.hardware;
    }

    /* renamed from: component7, reason: from getter */
    public final String getAndroidApiLevel() {
        return this.androidApiLevel;
    }

    /* renamed from: component8, reason: from getter */
    public final String getAndroidVersionNumber() {
        return this.androidVersionNumber;
    }

    /* renamed from: component9, reason: from getter */
    public final String getOs() {
        return this.os;
    }

    public final DeviceMetadata copy(String fingerprint, String model, String manufacturer, String brand, String product, String hardware, String androidApiLevel, String androidVersionNumber, String os, String osVersion) {
        Intrinsics.checkNotNullParameter(fingerprint, "fingerprint");
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(manufacturer, "manufacturer");
        Intrinsics.checkNotNullParameter(brand, "brand");
        Intrinsics.checkNotNullParameter(product, "product");
        Intrinsics.checkNotNullParameter(hardware, "hardware");
        Intrinsics.checkNotNullParameter(androidApiLevel, "androidApiLevel");
        Intrinsics.checkNotNullParameter(androidVersionNumber, "androidVersionNumber");
        Intrinsics.checkNotNullParameter(os, "os");
        Intrinsics.checkNotNullParameter(osVersion, "osVersion");
        return new DeviceMetadata(fingerprint, model, manufacturer, brand, product, hardware, androidApiLevel, androidVersionNumber, os, osVersion);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DeviceMetadata)) {
            return false;
        }
        DeviceMetadata deviceMetadata = (DeviceMetadata) other;
        return Intrinsics.areEqual(this.fingerprint, deviceMetadata.fingerprint) && Intrinsics.areEqual(this.model, deviceMetadata.model) && Intrinsics.areEqual(this.manufacturer, deviceMetadata.manufacturer) && Intrinsics.areEqual(this.brand, deviceMetadata.brand) && Intrinsics.areEqual(this.product, deviceMetadata.product) && Intrinsics.areEqual(this.hardware, deviceMetadata.hardware) && Intrinsics.areEqual(this.androidApiLevel, deviceMetadata.androidApiLevel) && Intrinsics.areEqual(this.androidVersionNumber, deviceMetadata.androidVersionNumber) && Intrinsics.areEqual(this.os, deviceMetadata.os) && Intrinsics.areEqual(this.osVersion, deviceMetadata.osVersion);
    }

    public final String getAndroidApiLevel() {
        return this.androidApiLevel;
    }

    public final String getAndroidVersionNumber() {
        return this.androidVersionNumber;
    }

    public final String getBrand() {
        return this.brand;
    }

    public final String getFingerprint() {
        return this.fingerprint;
    }

    public final String getHardware() {
        return this.hardware;
    }

    public final String getManufacturer() {
        return this.manufacturer;
    }

    public final String getModel() {
        return this.model;
    }

    public final String getOs() {
        return this.os;
    }

    public final String getOsVersion() {
        return this.osVersion;
    }

    public final String getProduct() {
        return this.product;
    }

    public int hashCode() {
        return (((((((((((((((((this.fingerprint.hashCode() * 31) + this.model.hashCode()) * 31) + this.manufacturer.hashCode()) * 31) + this.brand.hashCode()) * 31) + this.product.hashCode()) * 31) + this.hardware.hashCode()) * 31) + this.androidApiLevel.hashCode()) * 31) + this.androidVersionNumber.hashCode()) * 31) + this.os.hashCode()) * 31) + this.osVersion.hashCode();
    }

    public String toString() {
        return "DeviceMetadata(fingerprint=" + this.fingerprint + ", model=" + this.model + ", manufacturer=" + this.manufacturer + ", brand=" + this.brand + ", product=" + this.product + ", hardware=" + this.hardware + ", androidApiLevel=" + this.androidApiLevel + ", androidVersionNumber=" + this.androidVersionNumber + ", os=" + this.os + ", osVersion=" + this.osVersion + ")";
    }

    /* compiled from: DeviceMetadata.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/api/client/data/DeviceMetadata$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/api/client/data/DeviceMetadata;", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<DeviceMetadata> serializer() {
            return DeviceMetadata$$serializer.INSTANCE;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ DeviceMetadata(int i, @SerialName(SentryEvent.JsonKeys.FINGERPRINT) String str, @SerialName(Device.JsonKeys.MODEL) String str2, @SerialName(Device.JsonKeys.MANUFACTURER) String str3, @SerialName(Device.JsonKeys.BRAND) String str4, @SerialName("product") String str5, @SerialName("hardware") String str6, @SerialName("androidApiLevel") String str7, @SerialName("androidVersionNumber") String str8, @SerialName(OperatingSystem.TYPE) String str9, @SerialName("os_version") String str10, SerializationConstructorMarker serializationConstructorMarker) {
        if (255 != (i & 255)) {
            PluginExceptionsKt.throwMissingFieldException(i, 255, DeviceMetadata$$serializer.INSTANCE.getDescriptor());
        }
        this.fingerprint = str;
        this.model = str2;
        this.manufacturer = str3;
        this.brand = str4;
        this.product = str5;
        this.hardware = str6;
        this.androidApiLevel = str7;
        this.androidVersionNumber = str8;
        if ((i & 256) == 0) {
            this.os = Constants.KEY_ANDROID;
        } else {
            this.os = str9;
        }
        if ((i & 512) == 0) {
            this.osVersion = str8;
        } else {
            this.osVersion = str10;
        }
    }

    public DeviceMetadata(String fingerprint, String model, String manufacturer, String brand, String product, String hardware, String androidApiLevel, String androidVersionNumber, String os, String osVersion) {
        Intrinsics.checkNotNullParameter(fingerprint, "fingerprint");
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(manufacturer, "manufacturer");
        Intrinsics.checkNotNullParameter(brand, "brand");
        Intrinsics.checkNotNullParameter(product, "product");
        Intrinsics.checkNotNullParameter(hardware, "hardware");
        Intrinsics.checkNotNullParameter(androidApiLevel, "androidApiLevel");
        Intrinsics.checkNotNullParameter(androidVersionNumber, "androidVersionNumber");
        Intrinsics.checkNotNullParameter(os, "os");
        Intrinsics.checkNotNullParameter(osVersion, "osVersion");
        this.fingerprint = fingerprint;
        this.model = model;
        this.manufacturer = manufacturer;
        this.brand = brand;
        this.product = product;
        this.hardware = hardware;
        this.androidApiLevel = androidApiLevel;
        this.androidVersionNumber = androidVersionNumber;
        this.os = os;
        this.osVersion = osVersion;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$onfido_api_client(DeviceMetadata self, CompositeEncoder output, SerialDescriptor serialDesc) {
        output.encodeStringElement(serialDesc, 0, self.fingerprint);
        output.encodeStringElement(serialDesc, 1, self.model);
        output.encodeStringElement(serialDesc, 2, self.manufacturer);
        output.encodeStringElement(serialDesc, 3, self.brand);
        output.encodeStringElement(serialDesc, 4, self.product);
        output.encodeStringElement(serialDesc, 5, self.hardware);
        output.encodeStringElement(serialDesc, 6, self.androidApiLevel);
        output.encodeStringElement(serialDesc, 7, self.androidVersionNumber);
        if (output.shouldEncodeElementDefault(serialDesc, 8) || !Intrinsics.areEqual(self.os, Constants.KEY_ANDROID)) {
            output.encodeStringElement(serialDesc, 8, self.os);
        }
        if (!output.shouldEncodeElementDefault(serialDesc, 9) && Intrinsics.areEqual(self.osVersion, self.androidVersionNumber)) {
            return;
        }
        output.encodeStringElement(serialDesc, 9, self.osVersion);
    }

    public /* synthetic */ DeviceMetadata(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, str5, str6, str7, str8, (i & 256) != 0 ? Constants.KEY_ANDROID : str9, (i & 512) != 0 ? str8 : str10);
    }
}
