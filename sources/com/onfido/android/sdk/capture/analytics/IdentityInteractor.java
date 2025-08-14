package com.onfido.android.sdk.capture.analytics;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.facebook.hermes.intl.Constants;
import com.onfido.android.sdk.capture.BuildConfig;
import com.onfido.android.sdk.capture.native_detector.NativeDetector;
import com.onfido.android.sdk.capture.utils.DeviceMetadataProvider;
import com.onfido.android.sdk.capture.utils.yearclass.YearClass;
import com.onfido.api.client.data.DeviceInfo;
import com.onfido.api.client.data.DeviceMetadata;
import com.onfido.javax.inject.Inject;
import io.sentry.protocol.SentryStackFrame;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0017\u0018\u0000 ,2\u00020\u0001:\u0001,B!\b\u0001\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0015\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\nH\u0010¢\u0006\u0002\b\fJ\r\u0010\r\u001a\u00020\nH\u0010¢\u0006\u0002\b\u000eJ\r\u0010\u000f\u001a\u00020\u0010H\u0010¢\u0006\u0002\b\u0011J\r\u0010\u0012\u001a\u00020\u0013H\u0010¢\u0006\u0002\b\u0014J\r\u0010\u0015\u001a\u00020\u0016H\u0010¢\u0006\u0002\b\u0017J\r\u0010\u0018\u001a\u00020\nH\u0010¢\u0006\u0002\b\u0019J\r\u0010\u001a\u001a\u00020\nH\u0010¢\u0006\u0002\b\u001bJ\r\u0010\u001c\u001a\u00020\nH\u0010¢\u0006\u0002\b\u001dJ\r\u0010\u001e\u001a\u00020\nH\u0010¢\u0006\u0002\b\u001fJ\r\u0010 \u001a\u00020\nH\u0010¢\u0006\u0002\b!J\r\u0010\"\u001a\u00020\nH\u0010¢\u0006\u0002\b#J\r\u0010$\u001a\u00020%H\u0010¢\u0006\u0002\b&J\b\u0010'\u001a\u00020%H\u0016J\u001e\u0010(\u001a\u00020\n2\u0006\u0010)\u001a\u00020\n2\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\n0+H\u0012R\u000e\u0010\u0002\u001a\u00020\u0003X\u0092\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0092\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0092\u0004¢\u0006\u0002\n\u0000¨\u0006-"}, d2 = {"Lcom/onfido/android/sdk/capture/analytics/IdentityInteractor;", "", "context", "Landroid/content/Context;", "nativeDetector", "Lcom/onfido/android/sdk/capture/native_detector/NativeDetector;", "deviceMetadataProvider", "Lcom/onfido/android/sdk/capture/utils/DeviceMetadataProvider;", "(Landroid/content/Context;Lcom/onfido/android/sdk/capture/native_detector/NativeDetector;Lcom/onfido/android/sdk/capture/utils/DeviceMetadataProvider;)V", "getApplicationId", "", "kotlin.jvm.PlatformType", "getApplicationId$onfido_capture_sdk_core_release", "getApplicationVersion", "getApplicationVersion$onfido_capture_sdk_core_release", "getDeviceInfo", "Lcom/onfido/api/client/data/DeviceInfo;", "getDeviceInfo$onfido_capture_sdk_core_release", "getDeviceSystem", "Lcom/onfido/api/client/data/DeviceMetadata;", "getDeviceSystem$onfido_capture_sdk_core_release", "getGooglePlayServicesVersion", "", "getGooglePlayServicesVersion$onfido_capture_sdk_core_release", "getMinOsVersion", "getMinOsVersion$onfido_capture_sdk_core_release", "getSdkSource", "getSdkSource$onfido_capture_sdk_core_release", "getSdkVariant", "getSdkVariant$onfido_capture_sdk_core_release", "getSdkVersion", "getSdkVersion$onfido_capture_sdk_core_release", "getWrapperSdkPlatform", "getWrapperSdkPlatform$onfido_capture_sdk_core_release", "getWrapperSdkVersion", "getWrapperSdkVersion$onfido_capture_sdk_core_release", "isDebugBuild", "", "isDebugBuild$onfido_capture_sdk_core_release", "isDeviceHighEnd", "readMetadata", "metadataKey", Constants.COLLATION_DEFAULT, "Lkotlin/Function0;", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class IdentityInteractor {
    public static final String CORE_SDK_VERSION_SUFFIX = "core";
    public static final String FULL_SDK_VERSION_SUFFIX = "full";
    private static final int MINIMUM_HIGH_END_YEAR = 2014;
    private static final String ONFIDO_INTEGRATION_NAME = "onfido_integration_name";
    private static final String ONFIDO_INTEGRATION_NAME_DEFAULT = "native";
    private static final String ONFIDO_INTEGRATION_VERSION = "onfido_integration_version";
    private final Context context;
    private final DeviceMetadataProvider deviceMetadataProvider;
    private final NativeDetector nativeDetector;

    @Inject
    public IdentityInteractor(Context context, NativeDetector nativeDetector, DeviceMetadataProvider deviceMetadataProvider) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(nativeDetector, "nativeDetector");
        Intrinsics.checkNotNullParameter(deviceMetadataProvider, "deviceMetadataProvider");
        this.context = context;
        this.nativeDetector = nativeDetector;
        this.deviceMetadataProvider = deviceMetadataProvider;
    }

    private String readMetadata(String metadataKey, Function0<String> function0) {
        Bundle metaData = this.context.getPackageManager().getApplicationInfo(this.context.getPackageName(), 128).metaData;
        Intrinsics.checkNotNullExpressionValue(metaData, "metaData");
        String string = metaData.getString(metadataKey);
        return string == null ? function0.invoke() : string;
    }

    public String getApplicationId$onfido_capture_sdk_core_release() {
        return this.context.getApplicationInfo().packageName;
    }

    public String getApplicationVersion$onfido_capture_sdk_core_release() {
        return String.valueOf(this.context.getPackageManager().getPackageInfo(this.context.getPackageName(), 0).versionCode);
    }

    public DeviceInfo getDeviceInfo$onfido_capture_sdk_core_release() {
        return new DeviceInfo(getDeviceSystem$onfido_capture_sdk_core_release());
    }

    public DeviceMetadata getDeviceSystem$onfido_capture_sdk_core_release() {
        return this.deviceMetadataProvider.provideDeviceMetadata();
    }

    public int getGooglePlayServicesVersion$onfido_capture_sdk_core_release() {
        try {
            return this.context.getPackageManager().getPackageInfo("com.google.android.gms", 0).versionCode;
        } catch (PackageManager.NameNotFoundException unused) {
            return -1;
        }
    }

    public String getMinOsVersion$onfido_capture_sdk_core_release() {
        return String.valueOf(this.context.getApplicationInfo().minSdkVersion);
    }

    public String getSdkSource$onfido_capture_sdk_core_release() {
        return BuildConfig.SDK_SOURCE;
    }

    public String getSdkVariant$onfido_capture_sdk_core_release() {
        return this.nativeDetector.hasNativeLibrary() ? FULL_SDK_VERSION_SUFFIX : CORE_SDK_VERSION_SUFFIX;
    }

    public String getSdkVersion$onfido_capture_sdk_core_release() {
        return "22.7.0";
    }

    public String getWrapperSdkPlatform$onfido_capture_sdk_core_release() {
        return readMetadata(ONFIDO_INTEGRATION_NAME, new Function0<String>() { // from class: com.onfido.android.sdk.capture.analytics.IdentityInteractor$getWrapperSdkPlatform$1
            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                return SentryStackFrame.JsonKeys.NATIVE;
            }
        });
    }

    public String getWrapperSdkVersion$onfido_capture_sdk_core_release() {
        return readMetadata(ONFIDO_INTEGRATION_VERSION, new Function0<String>() { // from class: com.onfido.android.sdk.capture.analytics.IdentityInteractor$getWrapperSdkVersion$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                return this.this$0.getSdkVersion$onfido_capture_sdk_core_release();
            }
        });
    }

    public boolean isDebugBuild$onfido_capture_sdk_core_release() {
        return (this.context.getApplicationInfo().flags & 2) != 0;
    }

    public boolean isDeviceHighEnd() {
        return YearClass.get(this.context) >= 2014;
    }
}
