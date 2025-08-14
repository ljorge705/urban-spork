package com.onfido.api.client.token.sdk.model;

import com.clevertap.android.sdk.Constants;
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
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

/* compiled from: InternalSDKTokenUrl.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 #2\u00020\u0001:\u0002\"#B1\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tB\u001d\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\nJ\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0005HÆ\u0003J!\u0010\u0013\u001a\u00020\u00002\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0005HÖ\u0001J&\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 HÁ\u0001¢\u0006\u0002\b!R\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000f\u0010\f\u001a\u0004\b\u0010\u0010\u000e¨\u0006$"}, d2 = {"Lcom/onfido/api/client/token/sdk/model/InternalSDKTokenUrl;", "Ljava/io/Serializable;", "seen1", "", "baseUrl", "", "authUrl", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/lang/String;Ljava/lang/String;)V", "getAuthUrl$annotations", "()V", "getAuthUrl", "()Ljava/lang/String;", "getBaseUrl$annotations", "getBaseUrl", "component1", "component2", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_api_client", "$serializer", "Companion", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Serializable
/* loaded from: classes6.dex */
public final /* data */ class InternalSDKTokenUrl implements java.io.Serializable {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String authUrl;
    private final String baseUrl;

    /* JADX WARN: Multi-variable type inference failed */
    public InternalSDKTokenUrl() {
        this((String) null, (String) (0 == true ? 1 : 0), 3, (DefaultConstructorMarker) (0 == true ? 1 : 0));
    }

    public static /* synthetic */ InternalSDKTokenUrl copy$default(InternalSDKTokenUrl internalSDKTokenUrl, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = internalSDKTokenUrl.baseUrl;
        }
        if ((i & 2) != 0) {
            str2 = internalSDKTokenUrl.authUrl;
        }
        return internalSDKTokenUrl.copy(str, str2);
    }

    @SerialName("auth_url")
    public static /* synthetic */ void getAuthUrl$annotations() {
    }

    @SerialName("onfido_api_url")
    public static /* synthetic */ void getBaseUrl$annotations() {
    }

    /* renamed from: component1, reason: from getter */
    public final String getBaseUrl() {
        return this.baseUrl;
    }

    /* renamed from: component2, reason: from getter */
    public final String getAuthUrl() {
        return this.authUrl;
    }

    public final InternalSDKTokenUrl copy(String baseUrl, String authUrl) {
        return new InternalSDKTokenUrl(baseUrl, authUrl);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof InternalSDKTokenUrl)) {
            return false;
        }
        InternalSDKTokenUrl internalSDKTokenUrl = (InternalSDKTokenUrl) other;
        return Intrinsics.areEqual(this.baseUrl, internalSDKTokenUrl.baseUrl) && Intrinsics.areEqual(this.authUrl, internalSDKTokenUrl.authUrl);
    }

    public final String getAuthUrl() {
        return this.authUrl;
    }

    public final String getBaseUrl() {
        return this.baseUrl;
    }

    public int hashCode() {
        String str = this.baseUrl;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.authUrl;
        return iHashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "InternalSDKTokenUrl(baseUrl=" + this.baseUrl + ", authUrl=" + this.authUrl + ")";
    }

    /* compiled from: InternalSDKTokenUrl.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/api/client/token/sdk/model/InternalSDKTokenUrl$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/api/client/token/sdk/model/InternalSDKTokenUrl;", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<InternalSDKTokenUrl> serializer() {
            return InternalSDKTokenUrl$$serializer.INSTANCE;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ InternalSDKTokenUrl(int i, @SerialName("onfido_api_url") String str, @SerialName("auth_url") String str2, SerializationConstructorMarker serializationConstructorMarker) {
        if ((i & 1) == 0) {
            this.baseUrl = null;
        } else {
            this.baseUrl = str;
        }
        if ((i & 2) == 0) {
            this.authUrl = null;
        } else {
            this.authUrl = str2;
        }
    }

    public InternalSDKTokenUrl(String str, String str2) {
        this.baseUrl = str;
        this.authUrl = str2;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$onfido_api_client(InternalSDKTokenUrl self, CompositeEncoder output, SerialDescriptor serialDesc) {
        if (output.shouldEncodeElementDefault(serialDesc, 0) || self.baseUrl != null) {
            output.encodeNullableSerializableElement(serialDesc, 0, StringSerializer.INSTANCE, self.baseUrl);
        }
        if (!output.shouldEncodeElementDefault(serialDesc, 1) && self.authUrl == null) {
            return;
        }
        output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.authUrl);
    }

    public /* synthetic */ InternalSDKTokenUrl(String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2);
    }
}
