package com.onfido.api.client.token.sdk;

import com.clevertap.android.sdk.Constants;
import com.onfido.api.client.token.sdk.TokenPayload;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONObject;

/* compiled from: SDKTokenPayload.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 \"2\u00020\u0001:\u0001\"B3\b\u0002\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\tJ\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÂ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0007HÂ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0005HÂ\u0003J7\u0010\u001b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u001c\u001a\u00020\u00142\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eHÖ\u0003J\t\u0010\u001f\u001a\u00020 HÖ\u0001J\t\u0010!\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\n\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\r\u001a\u0004\u0018\u00010\u00058F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\fR\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u00058F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\fR\u0011\u0010\u0011\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\fR\u0011\u0010\u0013\u001a\u00020\u00148F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0015R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\f¨\u0006#"}, d2 = {"Lcom/onfido/api/client/token/sdk/SDKTokenPayload;", "Ljava/io/Serializable;", "urls", "Lcom/onfido/api/client/token/sdk/SDKTokenUrl;", "uuid", "", "payload", "Lcom/onfido/api/client/token/sdk/TokenPayload;", "issuer", "(Lcom/onfido/api/client/token/sdk/SDKTokenUrl;Ljava/lang/String;Lcom/onfido/api/client/token/sdk/TokenPayload;Ljava/lang/String;)V", "applicantUuid", "getApplicantUuid", "()Ljava/lang/String;", "authUrl", "getAuthUrl", "baseUrl", "getBaseUrl", "clientUuid", "getClientUuid", "isStudioToken", "", "()Z", "getUuid", "component1", "component2", "component3", "component4", Constants.COPY_TYPE, "equals", "other", "", "hashCode", "", "toString", "Companion", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final /* data */ class SDKTokenPayload implements Serializable {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String ISS_STUDIO = "studio";
    private static final String KEY_ISSUER = "iss";
    private static final String KEY_PAYLOAD = "payload";
    private static final String KEY_URLS = "urls";
    private static final String KEY_UUID = "uuid";
    private final String issuer;
    private final TokenPayload payload;
    private final SDKTokenUrl urls;
    private final String uuid;

    public /* synthetic */ SDKTokenPayload(SDKTokenUrl sDKTokenUrl, String str, TokenPayload tokenPayload, String str2, DefaultConstructorMarker defaultConstructorMarker) {
        this(sDKTokenUrl, str, tokenPayload, str2);
    }

    /* renamed from: component1, reason: from getter */
    private final SDKTokenUrl getUrls() {
        return this.urls;
    }

    /* renamed from: component3, reason: from getter */
    private final TokenPayload getPayload() {
        return this.payload;
    }

    /* renamed from: component4, reason: from getter */
    private final String getIssuer() {
        return this.issuer;
    }

    public static /* synthetic */ SDKTokenPayload copy$default(SDKTokenPayload sDKTokenPayload, SDKTokenUrl sDKTokenUrl, String str, TokenPayload tokenPayload, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            sDKTokenUrl = sDKTokenPayload.urls;
        }
        if ((i & 2) != 0) {
            str = sDKTokenPayload.uuid;
        }
        if ((i & 4) != 0) {
            tokenPayload = sDKTokenPayload.payload;
        }
        if ((i & 8) != 0) {
            str2 = sDKTokenPayload.issuer;
        }
        return sDKTokenPayload.copy(sDKTokenUrl, str, tokenPayload, str2);
    }

    @JvmStatic
    public static final SDKTokenPayload parseSDKTokenPayload(String str) {
        return INSTANCE.parseSDKTokenPayload(str);
    }

    /* renamed from: component2, reason: from getter */
    public final String getUuid() {
        return this.uuid;
    }

    public final SDKTokenPayload copy(SDKTokenUrl urls, String uuid, TokenPayload payload, String issuer) {
        Intrinsics.checkNotNullParameter(payload, "payload");
        return new SDKTokenPayload(urls, uuid, payload, issuer);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SDKTokenPayload)) {
            return false;
        }
        SDKTokenPayload sDKTokenPayload = (SDKTokenPayload) other;
        return Intrinsics.areEqual(this.urls, sDKTokenPayload.urls) && Intrinsics.areEqual(this.uuid, sDKTokenPayload.uuid) && Intrinsics.areEqual(this.payload, sDKTokenPayload.payload) && Intrinsics.areEqual(this.issuer, sDKTokenPayload.issuer);
    }

    public final String getUuid() {
        return this.uuid;
    }

    public int hashCode() {
        SDKTokenUrl sDKTokenUrl = this.urls;
        int iHashCode = (sDKTokenUrl == null ? 0 : sDKTokenUrl.hashCode()) * 31;
        String str = this.uuid;
        int iHashCode2 = (((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + this.payload.hashCode()) * 31;
        String str2 = this.issuer;
        return iHashCode2 + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "SDKTokenPayload(urls=" + this.urls + ", uuid=" + this.uuid + ", payload=" + this.payload + ", issuer=" + this.issuer + ")";
    }

    private SDKTokenPayload(SDKTokenUrl sDKTokenUrl, String str, TokenPayload tokenPayload, String str2) {
        this.urls = sDKTokenUrl;
        this.uuid = str;
        this.payload = tokenPayload;
        this.issuer = str2;
    }

    /* synthetic */ SDKTokenPayload(SDKTokenUrl sDKTokenUrl, String str, TokenPayload tokenPayload, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : sDKTokenUrl, (i & 2) != 0 ? null : str, tokenPayload, (i & 8) != 0 ? null : str2);
    }

    public final String getBaseUrl() {
        SDKTokenUrl sDKTokenUrl = this.urls;
        if (sDKTokenUrl != null) {
            return sDKTokenUrl.getBaseUrl();
        }
        return null;
    }

    public final String getAuthUrl() {
        SDKTokenUrl sDKTokenUrl = this.urls;
        if (sDKTokenUrl != null) {
            return sDKTokenUrl.getAuthUrl();
        }
        return null;
    }

    public final String getApplicantUuid() {
        return this.payload.getApplicantId();
    }

    public final String getClientUuid() {
        return this.payload.getClientUuid();
    }

    public final boolean isStudioToken() {
        return Intrinsics.areEqual(this.issuer, ISS_STUDIO);
    }

    /* compiled from: SDKTokenPayload.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\u0004H\u0002J\u0014\u0010\f\u001a\u0004\u0018\u00010\n2\b\u0010\r\u001a\u0004\u0018\u00010\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/onfido/api/client/token/sdk/SDKTokenPayload$Companion;", "", "()V", "ISS_STUDIO", "", "KEY_ISSUER", "KEY_PAYLOAD", "KEY_URLS", "KEY_UUID", "parseJson", "Lcom/onfido/api/client/token/sdk/SDKTokenPayload;", "json", "parseSDKTokenPayload", "sdkToken", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final SDKTokenPayload parseSDKTokenPayload(String sdkToken) {
            String strDecodePayload;
            String str = sdkToken;
            if (str == null || StringsKt.isBlank(str) || (strDecodePayload = SDKTokenExtractor.decodePayload(sdkToken)) == null) {
                return null;
            }
            return parseJson(strDecodePayload);
        }

        private final SDKTokenPayload parseJson(String json) {
            Object objM6095constructorimpl;
            String string;
            try {
                Result.Companion companion = Result.INSTANCE;
                Companion companion2 = this;
                JSONObject jSONObject = new JSONObject(json);
                JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("urls");
                SDKTokenUrl json$onfido_public_api_release = (jSONObjectOptJSONObject == null || (string = jSONObjectOptJSONObject.toString()) == null) ? null : SDKTokenUrl.INSTANCE.parseJson$onfido_public_api_release(string);
                String strOptString = jSONObject.optString("uuid");
                TokenPayload.Companion companion3 = TokenPayload.INSTANCE;
                String string2 = jSONObject.getJSONObject("payload").toString();
                Intrinsics.checkNotNullExpressionValue(string2, "toString(...)");
                TokenPayload json$onfido_public_api_release2 = companion3.parseJson$onfido_public_api_release(string2);
                JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject("iss");
                objM6095constructorimpl = Result.m6095constructorimpl(new SDKTokenPayload(json$onfido_public_api_release, strOptString, json$onfido_public_api_release2, jSONObjectOptJSONObject2 != null ? jSONObjectOptJSONObject2.toString() : null, null));
            } catch (Throwable th) {
                Result.Companion companion4 = Result.INSTANCE;
                objM6095constructorimpl = Result.m6095constructorimpl(ResultKt.createFailure(th));
            }
            return (SDKTokenPayload) (Result.m6101isFailureimpl(objM6095constructorimpl) ? null : objM6095constructorimpl);
        }
    }
}
