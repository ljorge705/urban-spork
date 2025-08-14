package com.onfido.api.client.token.sdk;

import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.onfido.api.client.exception.EnterpriseFeaturesNotAuthorizedException;
import com.onfido.api.client.token.Token;
import com.onfido.api.client.token.sdk.TokenEnterpriseFeatures;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: SDKToken.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\n\u0010\u0017\u001a\u0004\u0018\u00010\u0016H\u0002R\u0011\u0010\u0006\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\n\u0010\bR\u001a\u0010\u000b\u001a\u00020\f8@X\u0080\u0004¢\u0006\f\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u00128FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0013\u0010\u000e\u001a\u0004\b\u0011\u0010\u0014R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/onfido/api/client/token/sdk/SDKToken;", "Lcom/onfido/api/client/token/Token;", "tokenValue", "", RemoteConfigConstants.RequestFieldKey.APP_ID, "(Ljava/lang/String;Ljava/lang/String;)V", "applicantUuid", "getApplicantUuid", "()Ljava/lang/String;", "clientUuid", "getClientUuid", "enterpriseFeatures", "Lcom/onfido/api/client/token/sdk/TokenEnterpriseFeatures;", "getEnterpriseFeatures$onfido_public_api_release$annotations", "()V", "getEnterpriseFeatures$onfido_public_api_release", "()Lcom/onfido/api/client/token/sdk/TokenEnterpriseFeatures;", "isStudioToken", "", "isStudioToken$annotations", "()Z", "sdkTokenPayload", "Lcom/onfido/api/client/token/sdk/SDKTokenPayload;", "ensureSDKTokenPayload", "Companion", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class SDKToken extends Token {
    private static final Companion Companion = new Companion(null);
    private static final String ENTERPRISE_FEATURES = "enterprise_features";
    private static final String ONFIDO_DEMO_TOKEN = "demo";
    private SDKTokenPayload sdkTokenPayload;

    public static /* synthetic */ void getEnterpriseFeatures$onfido_public_api_release$annotations() {
    }

    public static /* synthetic */ void isStudioToken$annotations() {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SDKToken(String tokenValue, String str) {
        super(tokenValue, str);
        Intrinsics.checkNotNullParameter(tokenValue, "tokenValue");
    }

    public final TokenEnterpriseFeatures getEnterpriseFeatures$onfido_public_api_release() {
        try {
            JSONObject jSONObject = new JSONObject(SDKTokenExtractor.decodePayload(getTokenValue()));
            if (!jSONObject.has(ENTERPRISE_FEATURES)) {
                throw new EnterpriseFeaturesNotAuthorizedException();
            }
            TokenEnterpriseFeatures.Companion companion = TokenEnterpriseFeatures.INSTANCE;
            String string = jSONObject.getJSONObject(ENTERPRISE_FEATURES).toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            return companion.parseJson$onfido_public_api_release(string);
        } catch (JSONException unused) {
            throw new EnterpriseFeaturesNotAuthorizedException();
        }
    }

    public final String getApplicantUuid() {
        if (Intrinsics.areEqual(getTokenValue(), ONFIDO_DEMO_TOKEN)) {
            return getTokenValue();
        }
        SDKTokenPayload sDKTokenPayloadEnsureSDKTokenPayload = ensureSDKTokenPayload();
        String applicantUuid = sDKTokenPayloadEnsureSDKTokenPayload != null ? sDKTokenPayloadEnsureSDKTokenPayload.getApplicantUuid() : null;
        return applicantUuid == null ? "" : applicantUuid;
    }

    public final String getClientUuid() {
        SDKTokenPayload sDKTokenPayloadEnsureSDKTokenPayload = ensureSDKTokenPayload();
        String clientUuid = sDKTokenPayloadEnsureSDKTokenPayload != null ? sDKTokenPayloadEnsureSDKTokenPayload.getClientUuid() : null;
        return clientUuid == null ? "" : clientUuid;
    }

    public final boolean isStudioToken() {
        SDKTokenPayload sDKTokenPayloadEnsureSDKTokenPayload = ensureSDKTokenPayload();
        if (sDKTokenPayloadEnsureSDKTokenPayload != null) {
            return sDKTokenPayloadEnsureSDKTokenPayload.isStudioToken();
        }
        return false;
    }

    private final synchronized SDKTokenPayload ensureSDKTokenPayload() {
        if (this.sdkTokenPayload == null) {
            this.sdkTokenPayload = SDKTokenPayload.INSTANCE.parseSDKTokenPayload(getTokenValue());
        }
        return this.sdkTokenPayload;
    }

    /* compiled from: SDKToken.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/onfido/api/client/token/sdk/SDKToken$Companion;", "", "()V", "ENTERPRISE_FEATURES", "", "ONFIDO_DEMO_TOKEN", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
