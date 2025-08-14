package com.onfido.api.client.data;

import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.db.Column;
import com.onfido.android.sdk.capture.config.MediaCallbackResultReceiver;
import com.onfido.api.client.serializers.DateAsStringSerializer;
import com.onfido.reactnative.sdk.ReactNativeBridgeUtiles;
import io.sentry.rrweb.RRWebMetaEvent;
import java.util.Date;
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

/* compiled from: DocumentUpload.kt */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b2\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 X2\u00020\u0001:\u0002WXB³\u0001\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0001\u0010\n\u001a\u00020\u000b\u0012\n\b\u0001\u0010\f\u001a\u0004\u0018\u00010\r\u0012\n\b\u0001\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0001\u0010\u0011\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0012\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0013\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0014\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0015\u001a\u0004\u0018\u00010\u0016\u0012\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018¢\u0006\u0002\u0010\u0019B}\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0012\u001a\u00020\u0005\u0012\u0006\u0010\u0013\u001a\u00020\u0005\u0012\u0006\u0010\u0014\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0016¢\u0006\u0002\u0010\u001aJ\t\u0010<\u001a\u00020\u0005HÆ\u0003J\t\u0010=\u001a\u00020\u0005HÆ\u0003J\t\u0010>\u001a\u00020\u0005HÆ\u0003J\t\u0010?\u001a\u00020\u0005HÆ\u0003J\u000b\u0010@\u001a\u0004\u0018\u00010\u0016HÆ\u0003J\t\u0010A\u001a\u00020\u0007HÆ\u0003J\t\u0010B\u001a\u00020\u0005HÆ\u0003J\t\u0010C\u001a\u00020\u0005HÆ\u0003J\t\u0010D\u001a\u00020\u000bHÆ\u0003J\t\u0010E\u001a\u00020\rHÆ\u0003J\u000b\u0010F\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010G\u001a\u0004\u0018\u00010\u0010HÆ\u0003J\u000b\u0010H\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0093\u0001\u0010I\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0012\u001a\u00020\u00052\b\b\u0002\u0010\u0013\u001a\u00020\u00052\b\b\u0002\u0010\u0014\u001a\u00020\u00052\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0016HÆ\u0001J\u0013\u0010J\u001a\u00020K2\b\u0010L\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010M\u001a\u00020\u0003HÖ\u0001J\t\u0010N\u001a\u00020\u0005HÖ\u0001J&\u0010O\u001a\u00020P2\u0006\u0010Q\u001a\u00020\u00002\u0006\u0010R\u001a\u00020S2\u0006\u0010T\u001a\u00020UHÁ\u0001¢\u0006\u0002\bVR\u001c\u0010\u0014\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u001c\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001f\u0010\u001c\u001a\u0004\b \u0010!R\u001e\u0010\u0015\u001a\u0004\u0018\u00010\u00168\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\"\u0010\u001c\u001a\u0004\b#\u0010$R\u001c\u0010\u0013\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b%\u0010\u001c\u001a\u0004\b&\u0010\u001eR\u001c\u0010\b\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b'\u0010\u001c\u001a\u0004\b(\u0010\u001eR\u001c\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b)\u0010\u001c\u001a\u0004\b*\u0010+R\u001c\u0010\t\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b,\u0010\u001c\u001a\u0004\b-\u0010\u001eR\u001c\u0010\u0012\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b.\u0010\u001c\u001a\u0004\b/\u0010\u001eR\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b0\u0010\u001c\u001a\u0004\b1\u0010\u001eR\u001e\u0010\u0011\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b2\u0010\u001c\u001a\u0004\b3\u0010\u001eR\u001e\u0010\u000e\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b4\u0010\u001c\u001a\u0004\b5\u0010\u001eR\u001c\u0010\f\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b6\u0010\u001c\u001a\u0004\b7\u00108R\u001e\u0010\u000f\u001a\u0004\u0018\u00010\u00108\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b9\u0010\u001c\u001a\u0004\b:\u0010;¨\u0006Y"}, d2 = {"Lcom/onfido/api/client/data/DocumentUpload;", "", "seen1", "", "id", "", "createdAt", "Ljava/util/Date;", ReactNativeBridgeUtiles.KEY_FILE_NAME, ReactNativeBridgeUtiles.KEY_FILE_TYPE, "fileSize", "", "type", "Lcom/onfido/api/client/data/DocType;", ReactNativeBridgeUtiles.KEY_DOCUMENT_SIDE, "warningsBundle", "Lcom/onfido/api/client/data/DocumentValidationWarningsBundle;", ReactNativeBridgeUtiles.KEY_DOCUMENT_ISSUING_COUNTRY, RRWebMetaEvent.JsonKeys.HREF, "downloadHref", "applicantId", "documentFeatures", "Lcom/onfido/api/client/data/DocumentFeatures;", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/util/Date;Ljava/lang/String;Ljava/lang/String;JLcom/onfido/api/client/data/DocType;Ljava/lang/String;Lcom/onfido/api/client/data/DocumentValidationWarningsBundle;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/onfido/api/client/data/DocumentFeatures;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/lang/String;Ljava/util/Date;Ljava/lang/String;Ljava/lang/String;JLcom/onfido/api/client/data/DocType;Ljava/lang/String;Lcom/onfido/api/client/data/DocumentValidationWarningsBundle;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/onfido/api/client/data/DocumentFeatures;)V", "getApplicantId$annotations", "()V", "getApplicantId", "()Ljava/lang/String;", "getCreatedAt$annotations", "getCreatedAt", "()Ljava/util/Date;", "getDocumentFeatures$annotations", "getDocumentFeatures", "()Lcom/onfido/api/client/data/DocumentFeatures;", "getDownloadHref$annotations", "getDownloadHref", "getFileName$annotations", "getFileName", "getFileSize$annotations", "getFileSize", "()J", "getFileType$annotations", "getFileType", "getHref$annotations", "getHref", "getId$annotations", "getId", "getIssuingCountry$annotations", "getIssuingCountry", "getSide$annotations", "getSide", "getType$annotations", "getType", "()Lcom/onfido/api/client/data/DocType;", "getWarningsBundle$annotations", "getWarningsBundle", "()Lcom/onfido/api/client/data/DocumentValidationWarningsBundle;", "component1", "component10", "component11", "component12", "component13", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_api_client", "$serializer", "Companion", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Serializable
/* loaded from: classes6.dex */
public final /* data */ class DocumentUpload {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String applicantId;
    private final Date createdAt;
    private final DocumentFeatures documentFeatures;
    private final String downloadHref;
    private final String fileName;
    private final long fileSize;
    private final String fileType;
    private final String href;
    private final String id;
    private final String issuingCountry;
    private final String side;
    private final DocType type;
    private final DocumentValidationWarningsBundle warningsBundle;

    @SerialName("applicant_id")
    public static /* synthetic */ void getApplicantId$annotations() {
    }

    @SerialName(Column.CREATED_AT)
    @Serializable(with = DateAsStringSerializer.class)
    public static /* synthetic */ void getCreatedAt$annotations() {
    }

    @SerialName("document_features")
    public static /* synthetic */ void getDocumentFeatures$annotations() {
    }

    @SerialName("download_href")
    public static /* synthetic */ void getDownloadHref$annotations() {
    }

    @SerialName(MediaCallbackResultReceiver.KEY_FILE_NAME)
    public static /* synthetic */ void getFileName$annotations() {
    }

    @SerialName("file_size")
    public static /* synthetic */ void getFileSize$annotations() {
    }

    @SerialName(MediaCallbackResultReceiver.KEY_FILE_TYPE)
    public static /* synthetic */ void getFileType$annotations() {
    }

    @SerialName(RRWebMetaEvent.JsonKeys.HREF)
    public static /* synthetic */ void getHref$annotations() {
    }

    @SerialName("id")
    public static /* synthetic */ void getId$annotations() {
    }

    @SerialName("issuing_country")
    public static /* synthetic */ void getIssuingCountry$annotations() {
    }

    @SerialName(ReactNativeBridgeUtiles.KEY_DOCUMENT_SIDE)
    public static /* synthetic */ void getSide$annotations() {
    }

    @SerialName("type")
    public static /* synthetic */ void getType$annotations() {
    }

    @SerialName("sdk_warnings")
    public static /* synthetic */ void getWarningsBundle$annotations() {
    }

    /* renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* renamed from: component10, reason: from getter */
    public final String getHref() {
        return this.href;
    }

    /* renamed from: component11, reason: from getter */
    public final String getDownloadHref() {
        return this.downloadHref;
    }

    /* renamed from: component12, reason: from getter */
    public final String getApplicantId() {
        return this.applicantId;
    }

    /* renamed from: component13, reason: from getter */
    public final DocumentFeatures getDocumentFeatures() {
        return this.documentFeatures;
    }

    /* renamed from: component2, reason: from getter */
    public final Date getCreatedAt() {
        return this.createdAt;
    }

    /* renamed from: component3, reason: from getter */
    public final String getFileName() {
        return this.fileName;
    }

    /* renamed from: component4, reason: from getter */
    public final String getFileType() {
        return this.fileType;
    }

    /* renamed from: component5, reason: from getter */
    public final long getFileSize() {
        return this.fileSize;
    }

    /* renamed from: component6, reason: from getter */
    public final DocType getType() {
        return this.type;
    }

    /* renamed from: component7, reason: from getter */
    public final String getSide() {
        return this.side;
    }

    /* renamed from: component8, reason: from getter */
    public final DocumentValidationWarningsBundle getWarningsBundle() {
        return this.warningsBundle;
    }

    /* renamed from: component9, reason: from getter */
    public final String getIssuingCountry() {
        return this.issuingCountry;
    }

    public final DocumentUpload copy(String id, Date createdAt, String fileName, String fileType, long fileSize, DocType type, String side, DocumentValidationWarningsBundle warningsBundle, String issuingCountry, String href, String downloadHref, String applicantId, DocumentFeatures documentFeatures) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(createdAt, "createdAt");
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        Intrinsics.checkNotNullParameter(fileType, "fileType");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(href, "href");
        Intrinsics.checkNotNullParameter(downloadHref, "downloadHref");
        Intrinsics.checkNotNullParameter(applicantId, "applicantId");
        return new DocumentUpload(id, createdAt, fileName, fileType, fileSize, type, side, warningsBundle, issuingCountry, href, downloadHref, applicantId, documentFeatures);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DocumentUpload)) {
            return false;
        }
        DocumentUpload documentUpload = (DocumentUpload) other;
        return Intrinsics.areEqual(this.id, documentUpload.id) && Intrinsics.areEqual(this.createdAt, documentUpload.createdAt) && Intrinsics.areEqual(this.fileName, documentUpload.fileName) && Intrinsics.areEqual(this.fileType, documentUpload.fileType) && this.fileSize == documentUpload.fileSize && this.type == documentUpload.type && Intrinsics.areEqual(this.side, documentUpload.side) && Intrinsics.areEqual(this.warningsBundle, documentUpload.warningsBundle) && Intrinsics.areEqual(this.issuingCountry, documentUpload.issuingCountry) && Intrinsics.areEqual(this.href, documentUpload.href) && Intrinsics.areEqual(this.downloadHref, documentUpload.downloadHref) && Intrinsics.areEqual(this.applicantId, documentUpload.applicantId) && Intrinsics.areEqual(this.documentFeatures, documentUpload.documentFeatures);
    }

    public final String getApplicantId() {
        return this.applicantId;
    }

    public final Date getCreatedAt() {
        return this.createdAt;
    }

    public final DocumentFeatures getDocumentFeatures() {
        return this.documentFeatures;
    }

    public final String getDownloadHref() {
        return this.downloadHref;
    }

    public final String getFileName() {
        return this.fileName;
    }

    public final long getFileSize() {
        return this.fileSize;
    }

    public final String getFileType() {
        return this.fileType;
    }

    public final String getHref() {
        return this.href;
    }

    public final String getId() {
        return this.id;
    }

    public final String getIssuingCountry() {
        return this.issuingCountry;
    }

    public final String getSide() {
        return this.side;
    }

    public final DocType getType() {
        return this.type;
    }

    public final DocumentValidationWarningsBundle getWarningsBundle() {
        return this.warningsBundle;
    }

    public int hashCode() {
        int iHashCode = ((((((((((this.id.hashCode() * 31) + this.createdAt.hashCode()) * 31) + this.fileName.hashCode()) * 31) + this.fileType.hashCode()) * 31) + Long.hashCode(this.fileSize)) * 31) + this.type.hashCode()) * 31;
        String str = this.side;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        DocumentValidationWarningsBundle documentValidationWarningsBundle = this.warningsBundle;
        int iHashCode3 = (iHashCode2 + (documentValidationWarningsBundle == null ? 0 : documentValidationWarningsBundle.hashCode())) * 31;
        String str2 = this.issuingCountry;
        int iHashCode4 = (((((((iHashCode3 + (str2 == null ? 0 : str2.hashCode())) * 31) + this.href.hashCode()) * 31) + this.downloadHref.hashCode()) * 31) + this.applicantId.hashCode()) * 31;
        DocumentFeatures documentFeatures = this.documentFeatures;
        return iHashCode4 + (documentFeatures != null ? documentFeatures.hashCode() : 0);
    }

    public String toString() {
        return "DocumentUpload(id=" + this.id + ", createdAt=" + this.createdAt + ", fileName=" + this.fileName + ", fileType=" + this.fileType + ", fileSize=" + this.fileSize + ", type=" + this.type + ", side=" + this.side + ", warningsBundle=" + this.warningsBundle + ", issuingCountry=" + this.issuingCountry + ", href=" + this.href + ", downloadHref=" + this.downloadHref + ", applicantId=" + this.applicantId + ", documentFeatures=" + this.documentFeatures + ")";
    }

    /* compiled from: DocumentUpload.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/api/client/data/DocumentUpload$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/api/client/data/DocumentUpload;", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<DocumentUpload> serializer() {
            return DocumentUpload$$serializer.INSTANCE;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ DocumentUpload(int i, @SerialName("id") String str, @SerialName(Column.CREATED_AT) @Serializable(with = DateAsStringSerializer.class) Date date, @SerialName(MediaCallbackResultReceiver.KEY_FILE_NAME) String str2, @SerialName(MediaCallbackResultReceiver.KEY_FILE_TYPE) String str3, @SerialName("file_size") long j, @SerialName("type") DocType docType, @SerialName(ReactNativeBridgeUtiles.KEY_DOCUMENT_SIDE) String str4, @SerialName("sdk_warnings") DocumentValidationWarningsBundle documentValidationWarningsBundle, @SerialName("issuing_country") String str5, @SerialName(RRWebMetaEvent.JsonKeys.HREF) String str6, @SerialName("download_href") String str7, @SerialName("applicant_id") String str8, @SerialName("document_features") DocumentFeatures documentFeatures, SerializationConstructorMarker serializationConstructorMarker) {
        if (3647 != (i & 3647)) {
            PluginExceptionsKt.throwMissingFieldException(i, 3647, DocumentUpload$$serializer.INSTANCE.getDescriptor());
        }
        this.id = str;
        this.createdAt = date;
        this.fileName = str2;
        this.fileType = str3;
        this.fileSize = j;
        this.type = docType;
        if ((i & 64) == 0) {
            this.side = null;
        } else {
            this.side = str4;
        }
        if ((i & 128) == 0) {
            this.warningsBundle = null;
        } else {
            this.warningsBundle = documentValidationWarningsBundle;
        }
        if ((i & 256) == 0) {
            this.issuingCountry = null;
        } else {
            this.issuingCountry = str5;
        }
        this.href = str6;
        this.downloadHref = str7;
        this.applicantId = str8;
        if ((i & 4096) == 0) {
            this.documentFeatures = null;
        } else {
            this.documentFeatures = documentFeatures;
        }
    }

    public DocumentUpload(String id, Date createdAt, String fileName, String fileType, long j, DocType type, String str, DocumentValidationWarningsBundle documentValidationWarningsBundle, String str2, String href, String downloadHref, String applicantId, DocumentFeatures documentFeatures) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(createdAt, "createdAt");
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        Intrinsics.checkNotNullParameter(fileType, "fileType");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(href, "href");
        Intrinsics.checkNotNullParameter(downloadHref, "downloadHref");
        Intrinsics.checkNotNullParameter(applicantId, "applicantId");
        this.id = id;
        this.createdAt = createdAt;
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileSize = j;
        this.type = type;
        this.side = str;
        this.warningsBundle = documentValidationWarningsBundle;
        this.issuingCountry = str2;
        this.href = href;
        this.downloadHref = downloadHref;
        this.applicantId = applicantId;
        this.documentFeatures = documentFeatures;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$onfido_api_client(DocumentUpload self, CompositeEncoder output, SerialDescriptor serialDesc) {
        output.encodeStringElement(serialDesc, 0, self.id);
        output.encodeSerializableElement(serialDesc, 1, DateAsStringSerializer.INSTANCE, self.createdAt);
        output.encodeStringElement(serialDesc, 2, self.fileName);
        output.encodeStringElement(serialDesc, 3, self.fileType);
        output.encodeLongElement(serialDesc, 4, self.fileSize);
        output.encodeSerializableElement(serialDesc, 5, DocType$$serializer.INSTANCE, self.type);
        if (output.shouldEncodeElementDefault(serialDesc, 6) || self.side != null) {
            output.encodeNullableSerializableElement(serialDesc, 6, StringSerializer.INSTANCE, self.side);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 7) || self.warningsBundle != null) {
            output.encodeNullableSerializableElement(serialDesc, 7, DocumentValidationWarningsBundle$$serializer.INSTANCE, self.warningsBundle);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 8) || self.issuingCountry != null) {
            output.encodeNullableSerializableElement(serialDesc, 8, StringSerializer.INSTANCE, self.issuingCountry);
        }
        output.encodeStringElement(serialDesc, 9, self.href);
        output.encodeStringElement(serialDesc, 10, self.downloadHref);
        output.encodeStringElement(serialDesc, 11, self.applicantId);
        if (!output.shouldEncodeElementDefault(serialDesc, 12) && self.documentFeatures == null) {
            return;
        }
        output.encodeNullableSerializableElement(serialDesc, 12, DocumentFeatures$$serializer.INSTANCE, self.documentFeatures);
    }

    public /* synthetic */ DocumentUpload(String str, Date date, String str2, String str3, long j, DocType docType, String str4, DocumentValidationWarningsBundle documentValidationWarningsBundle, String str5, String str6, String str7, String str8, DocumentFeatures documentFeatures, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, date, str2, str3, j, docType, (i & 64) != 0 ? null : str4, (i & 128) != 0 ? null : documentValidationWarningsBundle, (i & 256) != 0 ? null : str5, str6, str7, str8, (i & 4096) != 0 ? null : documentFeatures);
    }
}
