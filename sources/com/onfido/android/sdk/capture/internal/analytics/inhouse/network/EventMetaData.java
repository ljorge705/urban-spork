package com.onfido.android.sdk.capture.internal.analytics.inhouse.network;

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

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b*\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0081\b\u0018\u0000 J2\u00020\u0001:\u0002IJB©\u0001\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u000f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0010\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012¢\u0006\u0002\u0010\u0013Be\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u0006\u0010\f\u001a\u00020\u0005\u0012\u0006\u0010\r\u001a\u00020\u0005\u0012\u0006\u0010\u000e\u001a\u00020\u0005\u0012\u0006\u0010\u000f\u001a\u00020\u0005\u0012\u0006\u0010\u0010\u001a\u00020\u0005¢\u0006\u0002\u0010\u0014J\t\u0010/\u001a\u00020\u0005HÆ\u0003J\t\u00100\u001a\u00020\u0005HÆ\u0003J\t\u00101\u001a\u00020\u0005HÆ\u0003J\t\u00102\u001a\u00020\u0005HÆ\u0003J\t\u00103\u001a\u00020\u0005HÆ\u0003J\t\u00104\u001a\u00020\u0005HÆ\u0003J\t\u00105\u001a\u00020\u0005HÆ\u0003J\t\u00106\u001a\u00020\u0005HÆ\u0003J\t\u00107\u001a\u00020\u0005HÆ\u0003J\t\u00108\u001a\u00020\u0005HÆ\u0003J\t\u00109\u001a\u00020\u0005HÆ\u0003J\t\u0010:\u001a\u00020\u0005HÆ\u0003J\u0081\u0001\u0010;\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u00052\b\b\u0002\u0010\r\u001a\u00020\u00052\b\b\u0002\u0010\u000e\u001a\u00020\u00052\b\b\u0002\u0010\u000f\u001a\u00020\u00052\b\b\u0002\u0010\u0010\u001a\u00020\u0005HÆ\u0001J\u0013\u0010<\u001a\u00020=2\b\u0010>\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010?\u001a\u00020\u0003HÖ\u0001J\t\u0010@\u001a\u00020\u0005HÖ\u0001J&\u0010A\u001a\u00020B2\u0006\u0010C\u001a\u00020\u00002\u0006\u0010D\u001a\u00020E2\u0006\u0010F\u001a\u00020GHÁ\u0001¢\u0006\u0002\bHR\u001c\u0010\u000e\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u001c\u0010\u0010\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0019\u0010\u0016\u001a\u0004\b\u001a\u0010\u0018R\u001c\u0010\u000f\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001b\u0010\u0016\u001a\u0004\b\u001c\u0010\u0018R\u001c\u0010\u000b\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001d\u0010\u0016\u001a\u0004\b\u001e\u0010\u0018R\u001c\u0010\b\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001f\u0010\u0016\u001a\u0004\b \u0010\u0018R\u001c\u0010\r\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b!\u0010\u0016\u001a\u0004\b\"\u0010\u0018R\u001c\u0010\n\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b#\u0010\u0016\u001a\u0004\b$\u0010\u0018R\u001c\u0010\u0007\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b%\u0010\u0016\u001a\u0004\b&\u0010\u0018R\u001c\u0010\t\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b'\u0010\u0016\u001a\u0004\b(\u0010\u0018R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b)\u0010\u0016\u001a\u0004\b*\u0010\u0018R\u001c\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b+\u0010\u0016\u001a\u0004\b,\u0010\u0018R\u001c\u0010\f\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b-\u0010\u0016\u001a\u0004\b.\u0010\u0018¨\u0006K"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/network/EventMetaData;", "", "seen1", "", OperatingSystem.TYPE, "", "osVersion", "minimumOsVersion", SentryEvent.JsonKeys.FINGERPRINT, Device.JsonKeys.MODEL, Device.JsonKeys.MANUFACTURER, Device.JsonKeys.BRAND, "product", "hardware", "androidApiLevel", "applicationVersion", "applicationId", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAndroidApiLevel$annotations", "()V", "getAndroidApiLevel", "()Ljava/lang/String;", "getApplicationId$annotations", "getApplicationId", "getApplicationVersion$annotations", "getApplicationVersion", "getBrand$annotations", "getBrand", "getFingerprint$annotations", "getFingerprint", "getHardware$annotations", "getHardware", "getManufacturer$annotations", "getManufacturer", "getMinimumOsVersion$annotations", "getMinimumOsVersion", "getModel$annotations", "getModel", "getOs$annotations", "getOs", "getOsVersion$annotations", "getOsVersion", "getProduct$annotations", "getProduct", "component1", "component10", "component11", "component12", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_capture_sdk_core_release", "$serializer", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Serializable
/* loaded from: classes2.dex */
public final /* data */ class EventMetaData {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String androidApiLevel;
    private final String applicationId;
    private final String applicationVersion;
    private final String brand;
    private final String fingerprint;
    private final String hardware;
    private final String manufacturer;
    private final String minimumOsVersion;
    private final String model;
    private final String os;
    private final String osVersion;
    private final String product;

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/network/EventMetaData$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/network/EventMetaData;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public final KSerializer<EventMetaData> serializer() {
            return EventMetaData$$serializer.INSTANCE;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ EventMetaData(int i, @SerialName(OperatingSystem.TYPE) String str, @SerialName("os_version") String str2, @SerialName("minimum_os_version") String str3, @SerialName(SentryEvent.JsonKeys.FINGERPRINT) String str4, @SerialName(Device.JsonKeys.MODEL) String str5, @SerialName(Device.JsonKeys.MANUFACTURER) String str6, @SerialName(Device.JsonKeys.BRAND) String str7, @SerialName("product") String str8, @SerialName("hardware") String str9, @SerialName("androidApiLevel") String str10, @SerialName("application_version") String str11, @SerialName("application_id") String str12, SerializationConstructorMarker serializationConstructorMarker) {
        if (4095 != (i & 4095)) {
            PluginExceptionsKt.throwMissingFieldException(i, 4095, EventMetaData$$serializer.INSTANCE.getDescriptor());
        }
        this.os = str;
        this.osVersion = str2;
        this.minimumOsVersion = str3;
        this.fingerprint = str4;
        this.model = str5;
        this.manufacturer = str6;
        this.brand = str7;
        this.product = str8;
        this.hardware = str9;
        this.androidApiLevel = str10;
        this.applicationVersion = str11;
        this.applicationId = str12;
    }

    @SerialName("androidApiLevel")
    public static /* synthetic */ void getAndroidApiLevel$annotations() {
    }

    @SerialName("application_id")
    public static /* synthetic */ void getApplicationId$annotations() {
    }

    @SerialName("application_version")
    public static /* synthetic */ void getApplicationVersion$annotations() {
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

    @SerialName("minimum_os_version")
    public static /* synthetic */ void getMinimumOsVersion$annotations() {
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

    @JvmStatic
    public static final /* synthetic */ void write$Self$onfido_capture_sdk_core_release(EventMetaData self, CompositeEncoder output, SerialDescriptor serialDesc) {
        output.encodeStringElement(serialDesc, 0, self.os);
        output.encodeStringElement(serialDesc, 1, self.osVersion);
        output.encodeStringElement(serialDesc, 2, self.minimumOsVersion);
        output.encodeStringElement(serialDesc, 3, self.fingerprint);
        output.encodeStringElement(serialDesc, 4, self.model);
        output.encodeStringElement(serialDesc, 5, self.manufacturer);
        output.encodeStringElement(serialDesc, 6, self.brand);
        output.encodeStringElement(serialDesc, 7, self.product);
        output.encodeStringElement(serialDesc, 8, self.hardware);
        output.encodeStringElement(serialDesc, 9, self.androidApiLevel);
        output.encodeStringElement(serialDesc, 10, self.applicationVersion);
        output.encodeStringElement(serialDesc, 11, self.applicationId);
    }

    /* renamed from: component1, reason: from getter */
    public final String getOs() {
        return this.os;
    }

    /* renamed from: component10, reason: from getter */
    public final String getAndroidApiLevel() {
        return this.androidApiLevel;
    }

    /* renamed from: component11, reason: from getter */
    public final String getApplicationVersion() {
        return this.applicationVersion;
    }

    /* renamed from: component12, reason: from getter */
    public final String getApplicationId() {
        return this.applicationId;
    }

    /* renamed from: component2, reason: from getter */
    public final String getOsVersion() {
        return this.osVersion;
    }

    /* renamed from: component3, reason: from getter */
    public final String getMinimumOsVersion() {
        return this.minimumOsVersion;
    }

    /* renamed from: component4, reason: from getter */
    public final String getFingerprint() {
        return this.fingerprint;
    }

    /* renamed from: component5, reason: from getter */
    public final String getModel() {
        return this.model;
    }

    /* renamed from: component6, reason: from getter */
    public final String getManufacturer() {
        return this.manufacturer;
    }

    /* renamed from: component7, reason: from getter */
    public final String getBrand() {
        return this.brand;
    }

    /* renamed from: component8, reason: from getter */
    public final String getProduct() {
        return this.product;
    }

    /* renamed from: component9, reason: from getter */
    public final String getHardware() {
        return this.hardware;
    }

    public final EventMetaData copy(String os, String osVersion, String minimumOsVersion, String fingerprint, String model, String manufacturer, String brand, String product, String hardware, String androidApiLevel, String applicationVersion, String applicationId) {
        Intrinsics.checkNotNullParameter(os, "os");
        Intrinsics.checkNotNullParameter(osVersion, "osVersion");
        Intrinsics.checkNotNullParameter(minimumOsVersion, "minimumOsVersion");
        Intrinsics.checkNotNullParameter(fingerprint, "fingerprint");
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(manufacturer, "manufacturer");
        Intrinsics.checkNotNullParameter(brand, "brand");
        Intrinsics.checkNotNullParameter(product, "product");
        Intrinsics.checkNotNullParameter(hardware, "hardware");
        Intrinsics.checkNotNullParameter(androidApiLevel, "androidApiLevel");
        Intrinsics.checkNotNullParameter(applicationVersion, "applicationVersion");
        Intrinsics.checkNotNullParameter(applicationId, "applicationId");
        return new EventMetaData(os, osVersion, minimumOsVersion, fingerprint, model, manufacturer, brand, product, hardware, androidApiLevel, applicationVersion, applicationId);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EventMetaData)) {
            return false;
        }
        EventMetaData eventMetaData = (EventMetaData) other;
        return Intrinsics.areEqual(this.os, eventMetaData.os) && Intrinsics.areEqual(this.osVersion, eventMetaData.osVersion) && Intrinsics.areEqual(this.minimumOsVersion, eventMetaData.minimumOsVersion) && Intrinsics.areEqual(this.fingerprint, eventMetaData.fingerprint) && Intrinsics.areEqual(this.model, eventMetaData.model) && Intrinsics.areEqual(this.manufacturer, eventMetaData.manufacturer) && Intrinsics.areEqual(this.brand, eventMetaData.brand) && Intrinsics.areEqual(this.product, eventMetaData.product) && Intrinsics.areEqual(this.hardware, eventMetaData.hardware) && Intrinsics.areEqual(this.androidApiLevel, eventMetaData.androidApiLevel) && Intrinsics.areEqual(this.applicationVersion, eventMetaData.applicationVersion) && Intrinsics.areEqual(this.applicationId, eventMetaData.applicationId);
    }

    public final String getAndroidApiLevel() {
        return this.androidApiLevel;
    }

    public final String getApplicationId() {
        return this.applicationId;
    }

    public final String getApplicationVersion() {
        return this.applicationVersion;
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

    public final String getMinimumOsVersion() {
        return this.minimumOsVersion;
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
        return (((((((((((((((((((((this.os.hashCode() * 31) + this.osVersion.hashCode()) * 31) + this.minimumOsVersion.hashCode()) * 31) + this.fingerprint.hashCode()) * 31) + this.model.hashCode()) * 31) + this.manufacturer.hashCode()) * 31) + this.brand.hashCode()) * 31) + this.product.hashCode()) * 31) + this.hardware.hashCode()) * 31) + this.androidApiLevel.hashCode()) * 31) + this.applicationVersion.hashCode()) * 31) + this.applicationId.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("EventMetaData(os=");
        sb.append(this.os).append(", osVersion=").append(this.osVersion).append(", minimumOsVersion=").append(this.minimumOsVersion).append(", fingerprint=").append(this.fingerprint).append(", model=").append(this.model).append(", manufacturer=").append(this.manufacturer).append(", brand=").append(this.brand).append(", product=").append(this.product).append(", hardware=").append(this.hardware).append(", androidApiLevel=").append(this.androidApiLevel).append(", applicationVersion=").append(this.applicationVersion).append(", applicationId=");
        sb.append(this.applicationId).append(')');
        return sb.toString();
    }

    public EventMetaData(String os, String osVersion, String minimumOsVersion, String fingerprint, String model, String manufacturer, String brand, String product, String hardware, String androidApiLevel, String applicationVersion, String applicationId) {
        Intrinsics.checkNotNullParameter(os, "os");
        Intrinsics.checkNotNullParameter(osVersion, "osVersion");
        Intrinsics.checkNotNullParameter(minimumOsVersion, "minimumOsVersion");
        Intrinsics.checkNotNullParameter(fingerprint, "fingerprint");
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(manufacturer, "manufacturer");
        Intrinsics.checkNotNullParameter(brand, "brand");
        Intrinsics.checkNotNullParameter(product, "product");
        Intrinsics.checkNotNullParameter(hardware, "hardware");
        Intrinsics.checkNotNullParameter(androidApiLevel, "androidApiLevel");
        Intrinsics.checkNotNullParameter(applicationVersion, "applicationVersion");
        Intrinsics.checkNotNullParameter(applicationId, "applicationId");
        this.os = os;
        this.osVersion = osVersion;
        this.minimumOsVersion = minimumOsVersion;
        this.fingerprint = fingerprint;
        this.model = model;
        this.manufacturer = manufacturer;
        this.brand = brand;
        this.product = product;
        this.hardware = hardware;
        this.androidApiLevel = androidApiLevel;
        this.applicationVersion = applicationVersion;
        this.applicationId = applicationId;
    }
}
