package com.onfido.api.client.token.sdk;

import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.onfido.api.client.JsonParserFactoryKt;
import com.onfido.api.client.exception.InternalEnterpriseFeaturesNotAuthorizedException;
import com.onfido.api.client.token.InternalToken;
import com.onfido.api.client.token.sdk.model.InternalSDKTokenPayload;
import com.onfido.api.client.token.sdk.url.ApiUrlCreator;
import com.onfido.api.client.token.sdk.url.ApiUrlCreatorImpl;
import io.sentry.protocol.DebugImage;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KTypeProjection;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonElementKt;
import kotlinx.serialization.json.JsonObject;

/* compiled from: InternalSDKToken.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB#\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\u001d\u001a\u00020\u0003H\u0016J\n\u0010\u001e\u001a\u0004\u0018\u00010\u001aH\u0002R\u0011\u0010\b\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u001d\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00110\u00108F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0014\u001a\u00020\u00158F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\u001b\u001a\u0004\u0018\u00010\u00038F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u000e¨\u0006 "}, d2 = {"Lcom/onfido/api/client/token/sdk/InternalSDKToken;", "Lcom/onfido/api/client/token/InternalToken;", "tokenValue", "", RemoteConfigConstants.RequestFieldKey.APP_ID, "urlCreator", "Lcom/onfido/api/client/token/sdk/url/ApiUrlCreator;", "(Ljava/lang/String;Ljava/lang/String;Lcom/onfido/api/client/token/sdk/url/ApiUrlCreator;)V", "applicantId", "Lcom/onfido/api/client/token/sdk/ApplicantId;", "getApplicantId", "()Lcom/onfido/api/client/token/sdk/ApplicantId;", "customerUserHash", "getCustomerUserHash", "()Ljava/lang/String;", "enterpriseFeatures", "", "Lkotlinx/serialization/json/JsonElement;", "getEnterpriseFeatures", "()Ljava/util/Map;", "isStudioToken", "", "()Z", "jsonParser", "Lkotlinx/serialization/json/Json;", "sdkTokenPayload", "Lcom/onfido/api/client/token/sdk/model/InternalSDKTokenPayload;", DebugImage.JsonKeys.UUID, "getUuid", "buildUrl", "ensureSDKTokenPayload", "Companion", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class InternalSDKToken extends InternalToken {
    private static final String ENTERPRISE_FEATURES = "enterprise_features";
    private final transient Json jsonParser;
    private InternalSDKTokenPayload sdkTokenPayload;
    private final ApiUrlCreator urlCreator;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public InternalSDKToken(String tokenValue, String str) {
        this(tokenValue, str, null, 4, null);
        Intrinsics.checkNotNullParameter(tokenValue, "tokenValue");
    }

    public /* synthetic */ InternalSDKToken(String str, String str2, ApiUrlCreatorImpl apiUrlCreatorImpl, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i & 4) != 0 ? new ApiUrlCreatorImpl() : apiUrlCreatorImpl);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public InternalSDKToken(String tokenValue, String str, ApiUrlCreator urlCreator) {
        super(tokenValue, null, 2, null);
        Intrinsics.checkNotNullParameter(tokenValue, "tokenValue");
        Intrinsics.checkNotNullParameter(urlCreator, "urlCreator");
        this.jsonParser = JsonParserFactoryKt.getJsonParserInstance();
        setAppId(str);
        this.urlCreator = urlCreator;
    }

    public final Map<String, JsonElement> getEnterpriseFeatures() {
        JsonObject jsonObject;
        Map<String, JsonElement> map;
        String strDecodePayload = InternalSDKTokenExtractor.decodePayload(getTokenValue());
        Json json = this.jsonParser;
        Intrinsics.checkNotNull(strDecodePayload);
        Map map2 = (Map) json.decodeFromString(SerializersKt.serializer(json.getSerializersModule(), Reflection.typeOf(Map.class, KTypeProjection.INSTANCE.invariant(Reflection.typeOf(String.class)), KTypeProjection.INSTANCE.invariant(Reflection.typeOf(JsonElement.class)))), strDecodePayload);
        if (!map2.containsKey(ENTERPRISE_FEATURES)) {
            throw new InternalEnterpriseFeaturesNotAuthorizedException();
        }
        JsonElement jsonElement = (JsonElement) map2.get(ENTERPRISE_FEATURES);
        return (jsonElement == null || (jsonObject = JsonElementKt.getJsonObject(jsonElement)) == null || (map = MapsKt.toMap(jsonObject)) == null) ? MapsKt.emptyMap() : map;
    }

    public final String getUuid() {
        InternalSDKTokenPayload internalSDKTokenPayloadEnsureSDKTokenPayload = ensureSDKTokenPayload();
        if (internalSDKTokenPayloadEnsureSDKTokenPayload != null) {
            return internalSDKTokenPayloadEnsureSDKTokenPayload.getUuid();
        }
        return null;
    }

    public final ApplicantId getApplicantId() {
        String applicantUuid;
        if (getIsDemoToken()) {
            return new ApplicantId(getTokenValue());
        }
        InternalSDKTokenPayload internalSDKTokenPayloadEnsureSDKTokenPayload = ensureSDKTokenPayload();
        if (internalSDKTokenPayloadEnsureSDKTokenPayload == null || (applicantUuid = internalSDKTokenPayloadEnsureSDKTokenPayload.getApplicantUuid()) == null) {
            applicantUuid = "";
        }
        return new ApplicantId(applicantUuid);
    }

    public final boolean isStudioToken() {
        InternalSDKTokenPayload internalSDKTokenPayloadEnsureSDKTokenPayload = ensureSDKTokenPayload();
        if (internalSDKTokenPayloadEnsureSDKTokenPayload != null) {
            return internalSDKTokenPayloadEnsureSDKTokenPayload.isStudioToken();
        }
        return false;
    }

    public final String getCustomerUserHash() {
        InternalSDKTokenPayload internalSDKTokenPayloadEnsureSDKTokenPayload = ensureSDKTokenPayload();
        String customerUserHash = internalSDKTokenPayloadEnsureSDKTokenPayload != null ? internalSDKTokenPayloadEnsureSDKTokenPayload.getCustomerUserHash() : null;
        return customerUserHash == null ? "" : customerUserHash;
    }

    @Override // com.onfido.api.client.token.InternalToken
    public String buildUrl() {
        String strCreateApiUrl = this.urlCreator.createApiUrl(getTokenValue());
        Intrinsics.checkNotNullExpressionValue(strCreateApiUrl, "createApiUrl(...)");
        return strCreateApiUrl;
    }

    private final synchronized InternalSDKTokenPayload ensureSDKTokenPayload() {
        if (this.sdkTokenPayload == null) {
            this.sdkTokenPayload = InternalSDKTokenPayload.INSTANCE.parseSDKTokenPayload(getTokenValue());
        }
        return this.sdkTokenPayload;
    }
}
