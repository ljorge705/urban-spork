package com.onfido.api.client.data;

import com.clevertap.android.sdk.Constants;
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
import kotlinx.serialization.internal.StringSerializer;
import org.spongycastle.cms.CMSAttributeTableGenerator;

/* compiled from: DocumentMediaUploadResponse.kt */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 22\u00020\u0001:\u000212B[\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0010\b\u0001\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t\u0012\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\n\b\u0001\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\u0002\u0010\u0011BC\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0002\u0010\u0012J\t\u0010\u001d\u001a\u00020\u0005HÂ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u0011\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tHÂ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\fHÂ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u000eHÆ\u0003JI\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000eHÆ\u0001J\u0013\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010&\u001a\u00020\u0003HÖ\u0001J\u0006\u0010'\u001a\u00020\fJ\t\u0010(\u001a\u00020\fHÖ\u0001J&\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\u00002\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/HÁ\u0001¢\u0006\u0002\b0R\u0018\u0010\u000b\u001a\u0004\u0018\u00010\f8\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0013\u0010\u0014R\u0016\u0010\u0004\u001a\u00020\u00058\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0015\u0010\u0014R\u001e\u0010\r\u001a\u0004\u0018\u00010\u000e8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0016\u0010\u0014\u001a\u0004\b\u0017\u0010\u0018R\u001e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t8\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0019\u0010\u0014R\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001a\u0010\u0014\u001a\u0004\b\u001b\u0010\u001c¨\u00063"}, d2 = {"Lcom/onfido/api/client/data/DocumentMediaUploadResponse;", "", "seen1", "", "documentBinaryMedia", "Lcom/onfido/api/client/data/DocumentBinaryMedia;", "warningsBundle", "Lcom/onfido/api/client/data/DocumentValidationWarningsBundle;", "documentFields", "", "Lcom/onfido/api/client/data/DocumentField;", CMSAttributeTableGenerator.CONTENT_TYPE, "", "documentFeatures", "Lcom/onfido/api/client/data/DocumentFeatures;", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/onfido/api/client/data/DocumentBinaryMedia;Lcom/onfido/api/client/data/DocumentValidationWarningsBundle;Ljava/util/List;Ljava/lang/String;Lcom/onfido/api/client/data/DocumentFeatures;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Lcom/onfido/api/client/data/DocumentBinaryMedia;Lcom/onfido/api/client/data/DocumentValidationWarningsBundle;Ljava/util/List;Ljava/lang/String;Lcom/onfido/api/client/data/DocumentFeatures;)V", "getContentType$annotations", "()V", "getDocumentBinaryMedia$annotations", "getDocumentFeatures$annotations", "getDocumentFeatures", "()Lcom/onfido/api/client/data/DocumentFeatures;", "getDocumentFields$annotations", "getWarningsBundle$annotations", "getWarningsBundle", "()Lcom/onfido/api/client/data/DocumentValidationWarningsBundle;", "component1", "component2", "component3", "component4", "component5", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "mediaId", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_api_client", "$serializer", "Companion", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Serializable
/* loaded from: classes6.dex */
public final /* data */ class DocumentMediaUploadResponse {
    private final String contentType;
    private final DocumentBinaryMedia documentBinaryMedia;
    private final DocumentFeatures documentFeatures;
    private final List<DocumentField> documentFields;
    private final DocumentValidationWarningsBundle warningsBundle;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final KSerializer<Object>[] $childSerializers = {null, null, new ArrayListSerializer(DocumentField$$serializer.INSTANCE), null, null};

    /* renamed from: component1, reason: from getter */
    private final DocumentBinaryMedia getDocumentBinaryMedia() {
        return this.documentBinaryMedia;
    }

    private final List<DocumentField> component3() {
        return this.documentFields;
    }

    /* renamed from: component4, reason: from getter */
    private final String getContentType() {
        return this.contentType;
    }

    public static /* synthetic */ DocumentMediaUploadResponse copy$default(DocumentMediaUploadResponse documentMediaUploadResponse, DocumentBinaryMedia documentBinaryMedia, DocumentValidationWarningsBundle documentValidationWarningsBundle, List list, String str, DocumentFeatures documentFeatures, int i, Object obj) {
        if ((i & 1) != 0) {
            documentBinaryMedia = documentMediaUploadResponse.documentBinaryMedia;
        }
        if ((i & 2) != 0) {
            documentValidationWarningsBundle = documentMediaUploadResponse.warningsBundle;
        }
        DocumentValidationWarningsBundle documentValidationWarningsBundle2 = documentValidationWarningsBundle;
        if ((i & 4) != 0) {
            list = documentMediaUploadResponse.documentFields;
        }
        List list2 = list;
        if ((i & 8) != 0) {
            str = documentMediaUploadResponse.contentType;
        }
        String str2 = str;
        if ((i & 16) != 0) {
            documentFeatures = documentMediaUploadResponse.documentFeatures;
        }
        return documentMediaUploadResponse.copy(documentBinaryMedia, documentValidationWarningsBundle2, list2, str2, documentFeatures);
    }

    @SerialName("content_type")
    private static /* synthetic */ void getContentType$annotations() {
    }

    @SerialName("binary_media")
    private static /* synthetic */ void getDocumentBinaryMedia$annotations() {
    }

    @SerialName("document_features")
    public static /* synthetic */ void getDocumentFeatures$annotations() {
    }

    @SerialName("document_fields")
    private static /* synthetic */ void getDocumentFields$annotations() {
    }

    @SerialName("sdk_warnings")
    public static /* synthetic */ void getWarningsBundle$annotations() {
    }

    /* renamed from: component2, reason: from getter */
    public final DocumentValidationWarningsBundle getWarningsBundle() {
        return this.warningsBundle;
    }

    /* renamed from: component5, reason: from getter */
    public final DocumentFeatures getDocumentFeatures() {
        return this.documentFeatures;
    }

    public final DocumentMediaUploadResponse copy(DocumentBinaryMedia documentBinaryMedia, DocumentValidationWarningsBundle warningsBundle, List<DocumentField> documentFields, String contentType, DocumentFeatures documentFeatures) {
        Intrinsics.checkNotNullParameter(documentBinaryMedia, "documentBinaryMedia");
        return new DocumentMediaUploadResponse(documentBinaryMedia, warningsBundle, documentFields, contentType, documentFeatures);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DocumentMediaUploadResponse)) {
            return false;
        }
        DocumentMediaUploadResponse documentMediaUploadResponse = (DocumentMediaUploadResponse) other;
        return Intrinsics.areEqual(this.documentBinaryMedia, documentMediaUploadResponse.documentBinaryMedia) && Intrinsics.areEqual(this.warningsBundle, documentMediaUploadResponse.warningsBundle) && Intrinsics.areEqual(this.documentFields, documentMediaUploadResponse.documentFields) && Intrinsics.areEqual(this.contentType, documentMediaUploadResponse.contentType) && Intrinsics.areEqual(this.documentFeatures, documentMediaUploadResponse.documentFeatures);
    }

    public final DocumentFeatures getDocumentFeatures() {
        return this.documentFeatures;
    }

    public final DocumentValidationWarningsBundle getWarningsBundle() {
        return this.warningsBundle;
    }

    public int hashCode() {
        int iHashCode = this.documentBinaryMedia.hashCode() * 31;
        DocumentValidationWarningsBundle documentValidationWarningsBundle = this.warningsBundle;
        int iHashCode2 = (iHashCode + (documentValidationWarningsBundle == null ? 0 : documentValidationWarningsBundle.hashCode())) * 31;
        List<DocumentField> list = this.documentFields;
        int iHashCode3 = (iHashCode2 + (list == null ? 0 : list.hashCode())) * 31;
        String str = this.contentType;
        int iHashCode4 = (iHashCode3 + (str == null ? 0 : str.hashCode())) * 31;
        DocumentFeatures documentFeatures = this.documentFeatures;
        return iHashCode4 + (documentFeatures != null ? documentFeatures.hashCode() : 0);
    }

    public String toString() {
        return "DocumentMediaUploadResponse(documentBinaryMedia=" + this.documentBinaryMedia + ", warningsBundle=" + this.warningsBundle + ", documentFields=" + this.documentFields + ", contentType=" + this.contentType + ", documentFeatures=" + this.documentFeatures + ")";
    }

    /* compiled from: DocumentMediaUploadResponse.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/api/client/data/DocumentMediaUploadResponse$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/api/client/data/DocumentMediaUploadResponse;", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<DocumentMediaUploadResponse> serializer() {
            return DocumentMediaUploadResponse$$serializer.INSTANCE;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ DocumentMediaUploadResponse(int i, @SerialName("binary_media") DocumentBinaryMedia documentBinaryMedia, @SerialName("sdk_warnings") DocumentValidationWarningsBundle documentValidationWarningsBundle, @SerialName("document_fields") List list, @SerialName("content_type") String str, @SerialName("document_features") DocumentFeatures documentFeatures, SerializationConstructorMarker serializationConstructorMarker) {
        if (1 != (i & 1)) {
            PluginExceptionsKt.throwMissingFieldException(i, 1, DocumentMediaUploadResponse$$serializer.INSTANCE.getDescriptor());
        }
        this.documentBinaryMedia = documentBinaryMedia;
        if ((i & 2) == 0) {
            this.warningsBundle = null;
        } else {
            this.warningsBundle = documentValidationWarningsBundle;
        }
        if ((i & 4) == 0) {
            this.documentFields = null;
        } else {
            this.documentFields = list;
        }
        if ((i & 8) == 0) {
            this.contentType = null;
        } else {
            this.contentType = str;
        }
        if ((i & 16) == 0) {
            this.documentFeatures = null;
        } else {
            this.documentFeatures = documentFeatures;
        }
    }

    public DocumentMediaUploadResponse(DocumentBinaryMedia documentBinaryMedia, DocumentValidationWarningsBundle documentValidationWarningsBundle, List<DocumentField> list, String str, DocumentFeatures documentFeatures) {
        Intrinsics.checkNotNullParameter(documentBinaryMedia, "documentBinaryMedia");
        this.documentBinaryMedia = documentBinaryMedia;
        this.warningsBundle = documentValidationWarningsBundle;
        this.documentFields = list;
        this.contentType = str;
        this.documentFeatures = documentFeatures;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$onfido_api_client(DocumentMediaUploadResponse self, CompositeEncoder output, SerialDescriptor serialDesc) {
        KSerializer<Object>[] kSerializerArr = $childSerializers;
        output.encodeSerializableElement(serialDesc, 0, DocumentBinaryMedia$$serializer.INSTANCE, self.documentBinaryMedia);
        if (output.shouldEncodeElementDefault(serialDesc, 1) || self.warningsBundle != null) {
            output.encodeNullableSerializableElement(serialDesc, 1, DocumentValidationWarningsBundle$$serializer.INSTANCE, self.warningsBundle);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 2) || self.documentFields != null) {
            output.encodeNullableSerializableElement(serialDesc, 2, kSerializerArr[2], self.documentFields);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 3) || self.contentType != null) {
            output.encodeNullableSerializableElement(serialDesc, 3, StringSerializer.INSTANCE, self.contentType);
        }
        if (!output.shouldEncodeElementDefault(serialDesc, 4) && self.documentFeatures == null) {
            return;
        }
        output.encodeNullableSerializableElement(serialDesc, 4, DocumentFeatures$$serializer.INSTANCE, self.documentFeatures);
    }

    public /* synthetic */ DocumentMediaUploadResponse(DocumentBinaryMedia documentBinaryMedia, DocumentValidationWarningsBundle documentValidationWarningsBundle, List list, String str, DocumentFeatures documentFeatures, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(documentBinaryMedia, (i & 2) != 0 ? null : documentValidationWarningsBundle, (i & 4) != 0 ? null : list, (i & 8) != 0 ? null : str, (i & 16) != 0 ? null : documentFeatures);
    }

    public final String mediaId() {
        return this.documentBinaryMedia.getMediaId();
    }
}
