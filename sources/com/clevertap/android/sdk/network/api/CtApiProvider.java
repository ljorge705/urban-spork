package com.clevertap.android.sdk.network.api;

import android.content.Context;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.DeviceInfo;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.StorageHelper;
import com.clevertap.android.sdk.network.http.UrlConnectionHttpClient;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CtApiWrapper.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J%\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0001¢\u0006\u0002\b\u000b¨\u0006\f"}, d2 = {"Lcom/clevertap/android/sdk/network/api/CtApiProvider;", "", "()V", "provideDefaultCtApi", "Lcom/clevertap/android/sdk/network/api/CtApi;", "context", "Landroid/content/Context;", "config", "Lcom/clevertap/android/sdk/CleverTapInstanceConfig;", "deviceInfo", "Lcom/clevertap/android/sdk/DeviceInfo;", "provideDefaultCtApi$clevertap_core_release", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class CtApiProvider {
    public static final CtApiProvider INSTANCE = new CtApiProvider();

    private CtApiProvider() {
    }

    public final CtApi provideDefaultCtApi$clevertap_core_release(Context context, CleverTapInstanceConfig config, DeviceInfo deviceInfo) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(deviceInfo, "deviceInfo");
        boolean zIsSslPinningEnabled = config.isSslPinningEnabled();
        Logger logger = config.getLogger();
        Intrinsics.checkNotNullExpressionValue(logger, "config.logger");
        String accountId = config.getAccountId();
        Intrinsics.checkNotNullExpressionValue(accountId, "config.accountId");
        UrlConnectionHttpClient urlConnectionHttpClient = new UrlConnectionHttpClient(zIsSslPinningEnabled, logger, accountId);
        String stringFromPrefs = StorageHelper.getStringFromPrefs(context, config, Constants.KEY_DOMAIN_NAME, null);
        String stringFromPrefs2 = StorageHelper.getStringFromPrefs(context, config, Constants.SPIKY_KEY_DOMAIN_NAME, null);
        String accountRegion = config.getAccountRegion();
        String proxyDomain = config.getProxyDomain();
        String spikyProxyDomain = config.getSpikyProxyDomain();
        String customHandshakeDomain = config.getCustomHandshakeDomain();
        String accountId2 = config.getAccountId();
        Intrinsics.checkNotNullExpressionValue(accountId2, "config.accountId");
        String accountToken = config.getAccountToken();
        Intrinsics.checkNotNullExpressionValue(accountToken, "config.accountToken");
        String strValueOf = String.valueOf(deviceInfo.getSdkVersion());
        Logger logger2 = config.getLogger();
        Intrinsics.checkNotNullExpressionValue(logger2, "config.logger");
        String accountId3 = config.getAccountId();
        Intrinsics.checkNotNullExpressionValue(accountId3, "config.accountId");
        return new CtApi(urlConnectionHttpClient, Constants.PRIMARY_DOMAIN, stringFromPrefs, stringFromPrefs2, accountRegion, proxyDomain, spikyProxyDomain, customHandshakeDomain, accountId2, accountToken, strValueOf, logger2, accountId3);
    }
}
