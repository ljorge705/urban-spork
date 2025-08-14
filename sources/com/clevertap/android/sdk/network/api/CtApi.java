package com.clevertap.android.sdk.network.api;

import android.net.Uri;
import com.clevertap.android.sdk.CTXtensions;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.network.http.CtHttpClient;
import com.clevertap.android.sdk.network.http.Request;
import com.clevertap.android.sdk.network.http.Response;
import com.clevertap.android.sdk.product_config.CTProductConfigConstants;
import com.google.common.net.HttpHeaders;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import io.sentry.protocol.Geo;
import io.sentry.protocol.OperatingSystem;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: CtApi.kt */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010$\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 C2\u00020\u0001:\u0001CBy\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\f\u001a\u00020\u0005\u0012\u0006\u0010\r\u001a\u00020\u0005\u0012\u0006\u0010\u000e\u001a\u00020\u0005\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0005¢\u0006\u0002\u0010\u0012JB\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\u00052\u0006\u0010.\u001a\u00020\u00052\b\u0010/\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u00100\u001a\u0002012\u0014\b\u0002\u00102\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\"H\u0002J\u000e\u00103\u001a\u0002042\u0006\u0010/\u001a\u000205J\u000e\u00106\u001a\u0002042\u0006\u0010/\u001a\u000207J\u0010\u00108\u001a\u0004\u0018\u00010\u00052\u0006\u00109\u001a\u000201J\u000e\u0010:\u001a\u00020\u00052\u0006\u00109\u001a\u000201J \u0010;\u001a\u00020<2\u0006\u0010-\u001a\u00020\u00052\u0006\u0010.\u001a\u00020\u00052\u0006\u00100\u001a\u000201H\u0002J\u000e\u0010=\u001a\u0002012\u0006\u00109\u001a\u000201J\u000e\u0010>\u001a\u0002042\u0006\u00109\u001a\u000201J\u0016\u0010?\u001a\u0002042\u0006\u00109\u001a\u0002012\u0006\u0010/\u001a\u000207J\f\u0010@\u001a\u00020A*\u00020AH\u0002J\f\u0010B\u001a\u00020A*\u00020AH\u0002R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0014\"\u0004\b\u0018\u0010\u0016R\u001e\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0019\u001a\u00020\u001a@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0014\"\u0004\b\u001f\u0010\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0014R\u001a\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u0014\"\u0004\b%\u0010\u0016R\u001c\u0010\b\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u0014\"\u0004\b'\u0010\u0016R\u001c\u0010\n\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0014\"\u0004\b)\u0010\u0016R\u000e\u0010*\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000¨\u0006D"}, d2 = {"Lcom/clevertap/android/sdk/network/api/CtApi;", "", "httpClient", "Lcom/clevertap/android/sdk/network/http/CtHttpClient;", "defaultDomain", "", "cachedDomain", "cachedSpikyDomain", Geo.JsonKeys.REGION, Constants.KEY_PROXY_DOMAIN, Constants.KEY_SPIKY_PROXY_DOMAIN, Constants.KEY_CUSTOM_HANDSHAKE_DOMAIN, Constants.KEY_ACCOUNT_ID, Constants.KEY_ACCOUNT_TOKEN, RemoteConfigConstants.RequestFieldKey.SDK_VERSION, "logger", "Lcom/clevertap/android/sdk/Logger;", "logTag", "(Lcom/clevertap/android/sdk/network/http/CtHttpClient;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/clevertap/android/sdk/Logger;Ljava/lang/String;)V", "getCachedDomain", "()Ljava/lang/String;", "setCachedDomain", "(Ljava/lang/String;)V", "getCachedSpikyDomain", "setCachedSpikyDomain", "<set-?>", "", "currentRequestTimestampSeconds", "getCurrentRequestTimestampSeconds", "()I", "getCustomHandshakeDomain", "setCustomHandshakeDomain", "getDefaultDomain", "defaultHeaders", "", "defaultQueryParams", "getProxyDomain", "setProxyDomain", "getRegion", "setRegion", "getSpikyProxyDomain", "setSpikyProxyDomain", "spikyRegionSuffix", "createRequest", "Lcom/clevertap/android/sdk/network/http/Request;", "baseUrl", "relativeUrl", "body", "includeTs", "", "headers", "defineTemplates", "Lcom/clevertap/android/sdk/network/http/Response;", "Lcom/clevertap/android/sdk/network/api/DefineTemplatesRequestBody;", "defineVars", "Lcom/clevertap/android/sdk/network/api/SendQueueRequestBody;", "getActualDomain", "isViewedEvent", "getHandshakeDomain", "getUriForPath", "Landroid/net/Uri;", "needsHandshake", "performHandshakeForDomain", "sendQueue", "appendDefaultQueryParams", "Landroid/net/Uri$Builder;", "appendTsQueryParam", "Companion", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class CtApi {
    public static final String DEFAULT_CONTENT_TYPE = "application/json; charset=utf-8";
    public static final String DEFAULT_QUERY_PARAM_OS = "Android";
    public static final String HEADER_CUSTOM_HANDSHAKE = "X-CleverTap-Handshake-Domain";
    private String cachedDomain;
    private String cachedSpikyDomain;
    private int currentRequestTimestampSeconds;
    private String customHandshakeDomain;
    private final String defaultDomain;
    private final Map<String, String> defaultHeaders;
    private final Map<String, String> defaultQueryParams;
    private final CtHttpClient httpClient;
    private final String logTag;
    private final Logger logger;
    private String proxyDomain;
    private String region;
    private String spikyProxyDomain;
    private final String spikyRegionSuffix;

    public final String getCachedDomain() {
        return this.cachedDomain;
    }

    public final String getCachedSpikyDomain() {
        return this.cachedSpikyDomain;
    }

    public final int getCurrentRequestTimestampSeconds() {
        return this.currentRequestTimestampSeconds;
    }

    public final String getCustomHandshakeDomain() {
        return this.customHandshakeDomain;
    }

    public final String getDefaultDomain() {
        return this.defaultDomain;
    }

    public final String getProxyDomain() {
        return this.proxyDomain;
    }

    public final String getRegion() {
        return this.region;
    }

    public final String getSpikyProxyDomain() {
        return this.spikyProxyDomain;
    }

    public final void setCachedDomain(String str) {
        this.cachedDomain = str;
    }

    public final void setCachedSpikyDomain(String str) {
        this.cachedSpikyDomain = str;
    }

    public final void setCustomHandshakeDomain(String str) {
        this.customHandshakeDomain = str;
    }

    public final void setProxyDomain(String str) {
        this.proxyDomain = str;
    }

    public final void setRegion(String str) {
        this.region = str;
    }

    public final void setSpikyProxyDomain(String str) {
        this.spikyProxyDomain = str;
    }

    public CtApi(CtHttpClient httpClient, String defaultDomain, String str, String str2, String str3, String str4, String str5, String str6, String accountId, String accountToken, String sdkVersion, Logger logger, String logTag) {
        Intrinsics.checkNotNullParameter(httpClient, "httpClient");
        Intrinsics.checkNotNullParameter(defaultDomain, "defaultDomain");
        Intrinsics.checkNotNullParameter(accountId, "accountId");
        Intrinsics.checkNotNullParameter(accountToken, "accountToken");
        Intrinsics.checkNotNullParameter(sdkVersion, "sdkVersion");
        Intrinsics.checkNotNullParameter(logger, "logger");
        Intrinsics.checkNotNullParameter(logTag, "logTag");
        this.httpClient = httpClient;
        this.defaultDomain = defaultDomain;
        this.cachedDomain = str;
        this.cachedSpikyDomain = str2;
        this.region = str3;
        this.proxyDomain = str4;
        this.spikyProxyDomain = str5;
        this.customHandshakeDomain = str6;
        this.logger = logger;
        this.logTag = logTag;
        this.defaultHeaders = MapsKt.mapOf(TuplesKt.to(HttpHeaders.CONTENT_TYPE, DEFAULT_CONTENT_TYPE), TuplesKt.to("X-CleverTap-Account-ID", accountId), TuplesKt.to("X-CleverTap-Token", accountToken));
        this.defaultQueryParams = MapsKt.mapOf(TuplesKt.to(OperatingSystem.TYPE, "Android"), TuplesKt.to("t", sdkVersion), TuplesKt.to("z", accountId));
        this.spikyRegionSuffix = "-spiky";
    }

    public final Response sendQueue(boolean isViewedEvent, SendQueueRequestBody body) {
        Intrinsics.checkNotNullParameter(body, "body");
        CtHttpClient ctHttpClient = this.httpClient;
        String actualDomain = getActualDomain(isViewedEvent);
        if (actualDomain == null) {
            actualDomain = this.defaultDomain;
        }
        return ctHttpClient.execute(createRequest$default(this, actualDomain, "a1", body.toString(), false, null, 24, null));
    }

    public final Response performHandshakeForDomain(boolean isViewedEvent) {
        Map<String, String> mapPlus;
        String handshakeDomain = getHandshakeDomain(isViewedEvent);
        if (CTXtensions.isNotNullAndBlank(this.customHandshakeDomain) && Intrinsics.areEqual(handshakeDomain, this.customHandshakeDomain)) {
            Map<String, String> map = this.defaultHeaders;
            String str = this.customHandshakeDomain;
            Intrinsics.checkNotNull(str);
            mapPlus = MapsKt.plus(map, TuplesKt.to(HEADER_CUSTOM_HANDSHAKE, str));
        } else {
            mapPlus = this.defaultHeaders;
        }
        Request requestCreateRequest = createRequest(handshakeDomain, "hello", null, false, mapPlus);
        this.logger.verbose(this.logTag, "Performing handshake with " + requestCreateRequest.getUrl());
        return this.httpClient.execute(requestCreateRequest);
    }

    public final Response defineVars(SendQueueRequestBody body) {
        Intrinsics.checkNotNullParameter(body, "body");
        CtHttpClient ctHttpClient = this.httpClient;
        String actualDomain = getActualDomain(false);
        if (actualDomain == null) {
            actualDomain = this.defaultDomain;
        }
        return ctHttpClient.execute(createRequest$default(this, actualDomain, "defineVars", body.toString(), false, null, 24, null));
    }

    public final Response defineTemplates(DefineTemplatesRequestBody body) {
        Intrinsics.checkNotNullParameter(body, "body");
        CtHttpClient ctHttpClient = this.httpClient;
        String actualDomain = getActualDomain(false);
        if (actualDomain == null) {
            actualDomain = this.defaultDomain;
        }
        return ctHttpClient.execute(createRequest$default(this, actualDomain, "defineTemplates", body.toString(), false, null, 24, null));
    }

    public final String getActualDomain(boolean isViewedEvent) {
        if (!CTXtensions.isNotNullAndBlank(this.region)) {
            String str = isViewedEvent ? this.spikyProxyDomain : this.proxyDomain;
            return CTXtensions.isNotNullAndBlank(str) ? str : isViewedEvent ? this.cachedSpikyDomain : this.cachedDomain;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.region);
        sb.append(isViewedEvent ? this.spikyRegionSuffix : "");
        sb.append(".");
        sb.append(this.defaultDomain);
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    public final String getHandshakeDomain(boolean isViewedEvent) {
        if (CTXtensions.isNotNullAndBlank(this.region)) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.region);
            sb.append(isViewedEvent ? this.spikyRegionSuffix : "");
            sb.append(".");
            sb.append(this.defaultDomain);
            String string = sb.toString();
            Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
            return string;
        }
        String str = isViewedEvent ? this.spikyProxyDomain : this.proxyDomain;
        if (CTXtensions.isNotNullAndBlank(str)) {
            return str;
        }
        if (!CTXtensions.isNotNullAndBlank(this.customHandshakeDomain)) {
            String str2 = isViewedEvent ? this.cachedSpikyDomain : this.cachedDomain;
            return CTXtensions.isNotNullAndBlank(str2) ? str2 : this.defaultDomain;
        }
        String str3 = this.customHandshakeDomain;
        Intrinsics.checkNotNull(str3);
        return str3;
    }

    public final boolean needsHandshake(boolean isViewedEvent) {
        if (CTXtensions.isNotNullAndBlank(this.region)) {
            return false;
        }
        if (CTXtensions.isNotNullAndBlank(isViewedEvent ? this.spikyProxyDomain : this.proxyDomain)) {
            return false;
        }
        String str = isViewedEvent ? this.cachedSpikyDomain : this.cachedDomain;
        return str == null || StringsKt.isBlank(str);
    }

    static /* synthetic */ Request createRequest$default(CtApi ctApi, String str, String str2, String str3, boolean z, Map map, int i, Object obj) {
        if ((i & 8) != 0) {
            z = true;
        }
        boolean z2 = z;
        if ((i & 16) != 0) {
            map = ctApi.defaultHeaders;
        }
        return ctApi.createRequest(str, str2, str3, z2, map);
    }

    private final Request createRequest(String baseUrl, String relativeUrl, String body, boolean includeTs, Map<String, String> headers) {
        return new Request(getUriForPath(baseUrl, relativeUrl, includeTs), headers, body);
    }

    private final Uri getUriForPath(String baseUrl, String relativeUrl, boolean includeTs) {
        Uri.Builder builderAppendPath = new Uri.Builder().scheme("https").authority(baseUrl).appendPath(relativeUrl);
        Intrinsics.checkNotNullExpressionValue(builderAppendPath, "Builder()\n            .s… .appendPath(relativeUrl)");
        Uri.Builder builderAppendDefaultQueryParams = appendDefaultQueryParams(builderAppendPath);
        if (includeTs) {
            appendTsQueryParam(builderAppendDefaultQueryParams);
        }
        Uri uriBuild = builderAppendDefaultQueryParams.build();
        Intrinsics.checkNotNullExpressionValue(uriBuild, "builder.build()");
        return uriBuild;
    }

    private final Uri.Builder appendDefaultQueryParams(Uri.Builder builder) {
        for (Map.Entry<String, String> entry : this.defaultQueryParams.entrySet()) {
            builder.appendQueryParameter(entry.getKey(), entry.getValue());
        }
        return builder;
    }

    private final Uri.Builder appendTsQueryParam(Uri.Builder builder) {
        int iCurrentTimeMillis = (int) (System.currentTimeMillis() / 1000);
        this.currentRequestTimestampSeconds = iCurrentTimeMillis;
        Uri.Builder builderAppendQueryParameter = builder.appendQueryParameter(CTProductConfigConstants.KEY_LAST_FETCHED_TIMESTAMP, String.valueOf(iCurrentTimeMillis));
        Intrinsics.checkNotNullExpressionValue(builderAppendQueryParameter, "appendQueryParameter(\"ts…estampSeconds.toString())");
        return builderAppendQueryParameter;
    }
}
