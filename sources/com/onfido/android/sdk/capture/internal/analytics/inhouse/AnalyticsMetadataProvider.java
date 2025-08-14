package com.onfido.android.sdk.capture.internal.analytics.inhouse;

import android.os.Build;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.onfido.android.sdk.capture.BuildConfig;
import com.onfido.javax.inject.Inject;
import io.sentry.protocol.OperatingSystem;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u0001B\u0007\b\u0007¢\u0006\u0002\u0010\u0002J\u0006\u0010\u000b\u001a\u00020\u0004R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006¨\u0006\f"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/AnalyticsMetadataProvider;", "", "()V", OperatingSystem.TYPE, "", "getOs", "()Ljava/lang/String;", "sdkName", "getSdkName", RemoteConfigConstants.RequestFieldKey.SDK_VERSION, "getSdkVersion", "getOSVersion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class AnalyticsMetadataProvider {
    private final String sdkName = BuildConfig.SDK_SOURCE;
    private final String sdkVersion = "22.7.0";
    private final String os = "Android";

    @Inject
    public AnalyticsMetadataProvider() {
    }

    public final String getOSVersion() {
        return Build.VERSION.RELEASE.toString();
    }

    public final String getOs() {
        return this.os;
    }

    public final String getSdkName() {
        return this.sdkName;
    }

    public final String getSdkVersion() {
        return this.sdkVersion;
    }
}
