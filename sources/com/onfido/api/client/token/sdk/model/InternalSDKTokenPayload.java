package com.onfido.api.client.token.sdk.model;

import com.clevertap.android.sdk.Constants;
import com.nimbusds.jwt.JWTClaimNames;
import com.onfido.api.client.JsonParserFactoryKt;
import com.onfido.api.client.token.sdk.InternalSDKTokenExtractor;
import io.sentry.SentryReplayEvent;
import io.sentry.protocol.DebugImage;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import kotlinx.serialization.json.Json;

/* compiled from: InternalSDKTokenPayload.kt */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 62\u00020\u0001:\u000256BI\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0001\u0010\n\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0002\u0010\rB1\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\u000eJ\u000b\u0010#\u001a\u0004\u0018\u00010\u0005HÂ\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\t\u0010%\u001a\u00020\tHÂ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0007HÂ\u0003J7\u0010'\u001a\u00020\u00002\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0013\u0010(\u001a\u00020\u001b2\b\u0010)\u001a\u0004\u0018\u00010*HÖ\u0003J\t\u0010+\u001a\u00020\u0003HÖ\u0001J\t\u0010,\u001a\u00020\u0007HÖ\u0001J&\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020\u00002\u0006\u00100\u001a\u0002012\u0006\u00102\u001a\u000203HÁ\u0001¢\u0006\u0002\b4R\u0011\u0010\u000f\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u00078F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0011R\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u00078F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0011R\u0011\u0010\u0016\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0011R\u0013\u0010\u0018\u001a\u0004\u0018\u00010\u00078F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0011R\u0011\u0010\u001a\u001a\u00020\u001b8F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001cR\u0018\u0010\n\u001a\u0004\u0018\u00010\u00078\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u001d\u0010\u001eR\u0016\u0010\b\u001a\u00020\t8\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u001f\u0010\u001eR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b \u0010\u001eR\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b!\u0010\u001e\u001a\u0004\b\"\u0010\u0011¨\u00067"}, d2 = {"Lcom/onfido/api/client/token/sdk/model/InternalSDKTokenPayload;", "Ljava/io/Serializable;", "seen1", "", SentryReplayEvent.JsonKeys.URLS, "Lcom/onfido/api/client/token/sdk/model/InternalSDKTokenUrl;", DebugImage.JsonKeys.UUID, "", "payload", "Lcom/onfido/api/client/token/sdk/model/InternalSDKTokenPayloadField;", "issuer", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/onfido/api/client/token/sdk/model/InternalSDKTokenUrl;Ljava/lang/String;Lcom/onfido/api/client/token/sdk/model/InternalSDKTokenPayloadField;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Lcom/onfido/api/client/token/sdk/model/InternalSDKTokenUrl;Ljava/lang/String;Lcom/onfido/api/client/token/sdk/model/InternalSDKTokenPayloadField;Ljava/lang/String;)V", "applicantUuid", "getApplicantUuid", "()Ljava/lang/String;", "authUrl", "getAuthUrl", "baseUrl", "getBaseUrl", "clientUuid", "getClientUuid", "customerUserHash", "getCustomerUserHash", "isStudioToken", "", "()Z", "getIssuer$annotations", "()V", "getPayload$annotations", "getUrls$annotations", "getUuid$annotations", "getUuid", "component1", "component2", "component3", "component4", Constants.COPY_TYPE, "equals", "other", "", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_api_client", "$serializer", "Companion", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Serializable
/* loaded from: classes6.dex */
public final /* data */ class InternalSDKTokenPayload implements java.io.Serializable {
    private static final String ISS_STUDIO = "studio";
    private final String issuer;
    private final InternalSDKTokenPayloadField payload;
    private final InternalSDKTokenUrl urls;
    private final String uuid;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Json jsonParser = JsonParserFactoryKt.getJsonParserInstance();

    /* renamed from: component1, reason: from getter */
    private final InternalSDKTokenUrl getUrls() {
        return this.urls;
    }

    /* renamed from: component3, reason: from getter */
    private final InternalSDKTokenPayloadField getPayload() {
        return this.payload;
    }

    /* renamed from: component4, reason: from getter */
    private final String getIssuer() {
        return this.issuer;
    }

    public static /* synthetic */ InternalSDKTokenPayload copy$default(InternalSDKTokenPayload internalSDKTokenPayload, InternalSDKTokenUrl internalSDKTokenUrl, String str, InternalSDKTokenPayloadField internalSDKTokenPayloadField, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            internalSDKTokenUrl = internalSDKTokenPayload.urls;
        }
        if ((i & 2) != 0) {
            str = internalSDKTokenPayload.uuid;
        }
        if ((i & 4) != 0) {
            internalSDKTokenPayloadField = internalSDKTokenPayload.payload;
        }
        if ((i & 8) != 0) {
            str2 = internalSDKTokenPayload.issuer;
        }
        return internalSDKTokenPayload.copy(internalSDKTokenUrl, str, internalSDKTokenPayloadField, str2);
    }

    @SerialName(JWTClaimNames.ISSUER)
    private static /* synthetic */ void getIssuer$annotations() {
    }

    @SerialName("payload")
    private static /* synthetic */ void getPayload$annotations() {
    }

    @SerialName(SentryReplayEvent.JsonKeys.URLS)
    private static /* synthetic */ void getUrls$annotations() {
    }

    @SerialName(DebugImage.JsonKeys.UUID)
    public static /* synthetic */ void getUuid$annotations() {
    }

    @JvmStatic
    public static final InternalSDKTokenPayload parseSDKTokenPayload(String str) {
        return INSTANCE.parseSDKTokenPayload(str);
    }

    /* renamed from: component2, reason: from getter */
    public final String getUuid() {
        return this.uuid;
    }

    public final InternalSDKTokenPayload copy(InternalSDKTokenUrl urls, String uuid, InternalSDKTokenPayloadField payload, String issuer) {
        Intrinsics.checkNotNullParameter(payload, "payload");
        return new InternalSDKTokenPayload(urls, uuid, payload, issuer);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof InternalSDKTokenPayload)) {
            return false;
        }
        InternalSDKTokenPayload internalSDKTokenPayload = (InternalSDKTokenPayload) other;
        return Intrinsics.areEqual(this.urls, internalSDKTokenPayload.urls) && Intrinsics.areEqual(this.uuid, internalSDKTokenPayload.uuid) && Intrinsics.areEqual(this.payload, internalSDKTokenPayload.payload) && Intrinsics.areEqual(this.issuer, internalSDKTokenPayload.issuer);
    }

    public final String getUuid() {
        return this.uuid;
    }

    public int hashCode() {
        InternalSDKTokenUrl internalSDKTokenUrl = this.urls;
        int iHashCode = (internalSDKTokenUrl == null ? 0 : internalSDKTokenUrl.hashCode()) * 31;
        String str = this.uuid;
        int iHashCode2 = (((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + this.payload.hashCode()) * 31;
        String str2 = this.issuer;
        return iHashCode2 + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "InternalSDKTokenPayload(urls=" + this.urls + ", uuid=" + this.uuid + ", payload=" + this.payload + ", issuer=" + this.issuer + ")";
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ InternalSDKTokenPayload(int i, @SerialName(SentryReplayEvent.JsonKeys.URLS) InternalSDKTokenUrl internalSDKTokenUrl, @SerialName(DebugImage.JsonKeys.UUID) String str, @SerialName("payload") InternalSDKTokenPayloadField internalSDKTokenPayloadField, @SerialName(JWTClaimNames.ISSUER) String str2, SerializationConstructorMarker serializationConstructorMarker) {
        if (4 != (i & 4)) {
            PluginExceptionsKt.throwMissingFieldException(i, 4, InternalSDKTokenPayload$$serializer.INSTANCE.getDescriptor());
        }
        if ((i & 1) == 0) {
            this.urls = null;
        } else {
            this.urls = internalSDKTokenUrl;
        }
        if ((i & 2) == 0) {
            this.uuid = null;
        } else {
            this.uuid = str;
        }
        this.payload = internalSDKTokenPayloadField;
        if ((i & 8) == 0) {
            this.issuer = null;
        } else {
            this.issuer = str2;
        }
    }

    public InternalSDKTokenPayload(InternalSDKTokenUrl internalSDKTokenUrl, String str, InternalSDKTokenPayloadField payload, String str2) {
        Intrinsics.checkNotNullParameter(payload, "payload");
        this.urls = internalSDKTokenUrl;
        this.uuid = str;
        this.payload = payload;
        this.issuer = str2;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$onfido_api_client(InternalSDKTokenPayload self, CompositeEncoder output, SerialDescriptor serialDesc) {
        if (output.shouldEncodeElementDefault(serialDesc, 0) || self.urls != null) {
            output.encodeNullableSerializableElement(serialDesc, 0, InternalSDKTokenUrl$$serializer.INSTANCE, self.urls);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 1) || self.uuid != null) {
            output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.uuid);
        }
        output.encodeSerializableElement(serialDesc, 2, InternalSDKTokenPayloadField$$serializer.INSTANCE, self.payload);
        if (!output.shouldEncodeElementDefault(serialDesc, 3) && self.issuer == null) {
            return;
        }
        output.encodeNullableSerializableElement(serialDesc, 3, StringSerializer.INSTANCE, self.issuer);
    }

    public /* synthetic */ InternalSDKTokenPayload(InternalSDKTokenUrl internalSDKTokenUrl, String str, InternalSDKTokenPayloadField internalSDKTokenPayloadField, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : internalSDKTokenUrl, (i & 2) != 0 ? null : str, internalSDKTokenPayloadField, (i & 8) != 0 ? null : str2);
    }

    public final String getBaseUrl() {
        InternalSDKTokenUrl internalSDKTokenUrl = this.urls;
        if (internalSDKTokenUrl != null) {
            return internalSDKTokenUrl.getBaseUrl();
        }
        return null;
    }

    public final String getAuthUrl() {
        InternalSDKTokenUrl internalSDKTokenUrl = this.urls;
        if (internalSDKTokenUrl != null) {
            return internalSDKTokenUrl.getAuthUrl();
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

    public final String getCustomerUserHash() {
        return this.payload.getCustomerUserHash();
    }

    /* compiled from: InternalSDKTokenPayload.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\u0004H\u0007J\u000f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u000bHÆ\u0001R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/onfido/api/client/token/sdk/model/InternalSDKTokenPayload$Companion;", "", "()V", "ISS_STUDIO", "", "jsonParser", "Lkotlinx/serialization/json/Json;", "parseSDKTokenPayload", "Lcom/onfido/api/client/token/sdk/model/InternalSDKTokenPayload;", "sdkToken", "serializer", "Lkotlinx/serialization/KSerializer;", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<InternalSDKTokenPayload> serializer() {
            return InternalSDKTokenPayload$$serializer.INSTANCE;
        }

        @JvmStatic
        public final InternalSDKTokenPayload parseSDKTokenPayload(String sdkToken) {
            String strDecodePayload;
            String str = sdkToken;
            if (str == null || StringsKt.isBlank(str) || (strDecodePayload = InternalSDKTokenExtractor.decodePayload(sdkToken)) == null) {
                return null;
            }
            Json json = InternalSDKTokenPayload.jsonParser;
            return (InternalSDKTokenPayload) json.decodeFromString(SerializersKt.serializer(json.getSerializersModule(), Reflection.typeOf(InternalSDKTokenPayload.class)), strDecodePayload);
        }
    }
}
