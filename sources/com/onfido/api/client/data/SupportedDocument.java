package com.onfido.api.client.data;

import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.analytics.OnfidoAnalyticsEventResultReceiver;
import java.util.List;
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
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;

/* compiled from: SupportedDocument.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 )2\u00020\u0001:\u0002()BC\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\u0010\b\u0001\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0002\u0010\fB#\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\u0010\rJ\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\u000f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\t0\bHÆ\u0003J-\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bHÆ\u0001J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001f\u001a\u00020\u0005HÖ\u0001J&\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u00002\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&HÁ\u0001¢\u0006\u0002\b'R\u001c\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u000f\u001a\u0004\b\u0013\u0010\u0011R\"\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0014\u0010\u000f\u001a\u0004\b\u0015\u0010\u0016¨\u0006*"}, d2 = {"Lcom/onfido/api/client/data/SupportedDocument;", "", "seen1", "", "issuerCountry", "", "docType", OnfidoAnalyticsEventResultReceiver.KEY_PROPERTIES, "", "Lcom/onfido/api/client/data/DocumentProperty;", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/util/List;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "getDocType$annotations", "()V", "getDocType", "()Ljava/lang/String;", "getIssuerCountry$annotations", "getIssuerCountry", "getProperties$annotations", "getProperties", "()Ljava/util/List;", "component1", "component2", "component3", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_api_client", "$serializer", "Companion", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Serializable
/* loaded from: classes6.dex */
public final /* data */ class SupportedDocument {
    private final String docType;
    private final String issuerCountry;
    private final List<DocumentProperty> properties;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final KSerializer<Object>[] $childSerializers = {null, null, new ArrayListSerializer(DocumentProperty$$serializer.INSTANCE)};

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ SupportedDocument copy$default(SupportedDocument supportedDocument, String str, String str2, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = supportedDocument.issuerCountry;
        }
        if ((i & 2) != 0) {
            str2 = supportedDocument.docType;
        }
        if ((i & 4) != 0) {
            list = supportedDocument.properties;
        }
        return supportedDocument.copy(str, str2, list);
    }

    @SerialName("type")
    public static /* synthetic */ void getDocType$annotations() {
    }

    @SerialName("issuer")
    public static /* synthetic */ void getIssuerCountry$annotations() {
    }

    @SerialName(OnfidoAnalyticsEventResultReceiver.KEY_PROPERTIES)
    public static /* synthetic */ void getProperties$annotations() {
    }

    /* renamed from: component1, reason: from getter */
    public final String getIssuerCountry() {
        return this.issuerCountry;
    }

    /* renamed from: component2, reason: from getter */
    public final String getDocType() {
        return this.docType;
    }

    public final List<DocumentProperty> component3() {
        return this.properties;
    }

    public final SupportedDocument copy(String issuerCountry, String docType, List<DocumentProperty> properties) {
        Intrinsics.checkNotNullParameter(issuerCountry, "issuerCountry");
        Intrinsics.checkNotNullParameter(docType, "docType");
        Intrinsics.checkNotNullParameter(properties, "properties");
        return new SupportedDocument(issuerCountry, docType, properties);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SupportedDocument)) {
            return false;
        }
        SupportedDocument supportedDocument = (SupportedDocument) other;
        return Intrinsics.areEqual(this.issuerCountry, supportedDocument.issuerCountry) && Intrinsics.areEqual(this.docType, supportedDocument.docType) && Intrinsics.areEqual(this.properties, supportedDocument.properties);
    }

    public final String getDocType() {
        return this.docType;
    }

    public final String getIssuerCountry() {
        return this.issuerCountry;
    }

    public final List<DocumentProperty> getProperties() {
        return this.properties;
    }

    public int hashCode() {
        return (((this.issuerCountry.hashCode() * 31) + this.docType.hashCode()) * 31) + this.properties.hashCode();
    }

    public String toString() {
        return "SupportedDocument(issuerCountry=" + this.issuerCountry + ", docType=" + this.docType + ", properties=" + this.properties + ")";
    }

    /* compiled from: SupportedDocument.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/api/client/data/SupportedDocument$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/api/client/data/SupportedDocument;", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<SupportedDocument> serializer() {
            return SupportedDocument$$serializer.INSTANCE;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ SupportedDocument(int i, @SerialName("issuer") String str, @SerialName("type") String str2, @SerialName(OnfidoAnalyticsEventResultReceiver.KEY_PROPERTIES) List list, SerializationConstructorMarker serializationConstructorMarker) {
        if (7 != (i & 7)) {
            PluginExceptionsKt.throwMissingFieldException(i, 7, SupportedDocument$$serializer.INSTANCE.getDescriptor());
        }
        this.issuerCountry = str;
        this.docType = str2;
        this.properties = list;
    }

    public SupportedDocument(String issuerCountry, String docType, List<DocumentProperty> properties) {
        Intrinsics.checkNotNullParameter(issuerCountry, "issuerCountry");
        Intrinsics.checkNotNullParameter(docType, "docType");
        Intrinsics.checkNotNullParameter(properties, "properties");
        this.issuerCountry = issuerCountry;
        this.docType = docType;
        this.properties = properties;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$onfido_api_client(SupportedDocument self, CompositeEncoder output, SerialDescriptor serialDesc) {
        KSerializer<Object>[] kSerializerArr = $childSerializers;
        output.encodeStringElement(serialDesc, 0, self.issuerCountry);
        output.encodeStringElement(serialDesc, 1, self.docType);
        output.encodeSerializableElement(serialDesc, 2, kSerializerArr[2], self.properties);
    }
}
