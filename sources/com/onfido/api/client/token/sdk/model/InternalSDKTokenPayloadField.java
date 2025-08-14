package com.onfido.api.client.token.sdk.model;

import com.clevertap.android.sdk.Constants;
import io.sentry.protocol.App;
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
import kotlinx.serialization.internal.StringSerializer;

/* compiled from: InternalSDKTokenPayloadField.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 '2\u00020\u0001:\u0002&'B=\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nB\u001f\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0005HÆ\u0003J)\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bHÖ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001d\u001a\u00020\u0005HÖ\u0001J&\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u00002\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$HÁ\u0001¢\u0006\u0002\b%R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\r\u001a\u0004\b\u0011\u0010\u000fR\u001e\u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\r\u001a\u0004\b\u0013\u0010\u000f¨\u0006("}, d2 = {"Lcom/onfido/api/client/token/sdk/model/InternalSDKTokenPayloadField;", "Ljava/io/Serializable;", "seen1", "", "applicantId", "", "clientUuid", "customerUserHash", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getApplicantId$annotations", "()V", "getApplicantId", "()Ljava/lang/String;", "getClientUuid$annotations", "getClientUuid", "getCustomerUserHash$annotations", "getCustomerUserHash", "component1", "component2", "component3", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_api_client", "$serializer", "Companion", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Serializable
/* loaded from: classes6.dex */
public final /* data */ class InternalSDKTokenPayloadField implements java.io.Serializable {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String applicantId;
    private final String clientUuid;
    private final String customerUserHash;

    public static /* synthetic */ InternalSDKTokenPayloadField copy$default(InternalSDKTokenPayloadField internalSDKTokenPayloadField, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = internalSDKTokenPayloadField.applicantId;
        }
        if ((i & 2) != 0) {
            str2 = internalSDKTokenPayloadField.clientUuid;
        }
        if ((i & 4) != 0) {
            str3 = internalSDKTokenPayloadField.customerUserHash;
        }
        return internalSDKTokenPayloadField.copy(str, str2, str3);
    }

    @SerialName(App.TYPE)
    public static /* synthetic */ void getApplicantId$annotations() {
    }

    @SerialName("client_uuid")
    public static /* synthetic */ void getClientUuid$annotations() {
    }

    @SerialName("customer_user_hash")
    public static /* synthetic */ void getCustomerUserHash$annotations() {
    }

    /* renamed from: component1, reason: from getter */
    public final String getApplicantId() {
        return this.applicantId;
    }

    /* renamed from: component2, reason: from getter */
    public final String getClientUuid() {
        return this.clientUuid;
    }

    /* renamed from: component3, reason: from getter */
    public final String getCustomerUserHash() {
        return this.customerUserHash;
    }

    public final InternalSDKTokenPayloadField copy(String applicantId, String clientUuid, String customerUserHash) {
        Intrinsics.checkNotNullParameter(applicantId, "applicantId");
        Intrinsics.checkNotNullParameter(clientUuid, "clientUuid");
        return new InternalSDKTokenPayloadField(applicantId, clientUuid, customerUserHash);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof InternalSDKTokenPayloadField)) {
            return false;
        }
        InternalSDKTokenPayloadField internalSDKTokenPayloadField = (InternalSDKTokenPayloadField) other;
        return Intrinsics.areEqual(this.applicantId, internalSDKTokenPayloadField.applicantId) && Intrinsics.areEqual(this.clientUuid, internalSDKTokenPayloadField.clientUuid) && Intrinsics.areEqual(this.customerUserHash, internalSDKTokenPayloadField.customerUserHash);
    }

    public final String getApplicantId() {
        return this.applicantId;
    }

    public final String getClientUuid() {
        return this.clientUuid;
    }

    public final String getCustomerUserHash() {
        return this.customerUserHash;
    }

    public int hashCode() {
        int iHashCode = ((this.applicantId.hashCode() * 31) + this.clientUuid.hashCode()) * 31;
        String str = this.customerUserHash;
        return iHashCode + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        return "InternalSDKTokenPayloadField(applicantId=" + this.applicantId + ", clientUuid=" + this.clientUuid + ", customerUserHash=" + this.customerUserHash + ")";
    }

    /* compiled from: InternalSDKTokenPayloadField.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/api/client/token/sdk/model/InternalSDKTokenPayloadField$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/api/client/token/sdk/model/InternalSDKTokenPayloadField;", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<InternalSDKTokenPayloadField> serializer() {
            return InternalSDKTokenPayloadField$$serializer.INSTANCE;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ InternalSDKTokenPayloadField(int i, @SerialName(App.TYPE) String str, @SerialName("client_uuid") String str2, @SerialName("customer_user_hash") String str3, SerializationConstructorMarker serializationConstructorMarker) {
        if (7 != (i & 7)) {
            PluginExceptionsKt.throwMissingFieldException(i, 7, InternalSDKTokenPayloadField$$serializer.INSTANCE.getDescriptor());
        }
        this.applicantId = str;
        this.clientUuid = str2;
        this.customerUserHash = str3;
    }

    public InternalSDKTokenPayloadField(String applicantId, String clientUuid, String str) {
        Intrinsics.checkNotNullParameter(applicantId, "applicantId");
        Intrinsics.checkNotNullParameter(clientUuid, "clientUuid");
        this.applicantId = applicantId;
        this.clientUuid = clientUuid;
        this.customerUserHash = str;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$onfido_api_client(InternalSDKTokenPayloadField self, CompositeEncoder output, SerialDescriptor serialDesc) {
        output.encodeStringElement(serialDesc, 0, self.applicantId);
        output.encodeStringElement(serialDesc, 1, self.clientUuid);
        output.encodeNullableSerializableElement(serialDesc, 2, StringSerializer.INSTANCE, self.customerUserHash);
    }
}
