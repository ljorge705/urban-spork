package com.onfido.api.client.data;

import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.db.Column;
import com.onfido.android.sdk.capture.config.MediaCallbackResultReceiver;
import com.onfido.api.client.serializers.DateAsStringSerializer;
import com.onfido.reactnative.sdk.ReactNativeBridgeUtiles;
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
import org.spongycastle.crypto.tls.CipherSuite;

/* compiled from: PoaDocumentUpload.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u001f\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 >2\u00020\u0001:\u0002=>Bw\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0001\u0010\u000b\u001a\u00020\f\u0012\n\b\u0001\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\u0002\u0010\u0011BI\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u000e\u001a\u00020\u0005¢\u0006\u0002\u0010\u0012J\t\u0010&\u001a\u00020\u0005HÆ\u0003J\t\u0010'\u001a\u00020\u0005HÆ\u0003J\t\u0010(\u001a\u00020\bHÆ\u0003J\t\u0010)\u001a\u00020\u0005HÆ\u0003J\t\u0010*\u001a\u00020\u0005HÆ\u0003J\t\u0010+\u001a\u00020\fHÆ\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\u0005HÂ\u0003J\t\u0010-\u001a\u00020\u0005HÆ\u0003J[\u0010.\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u000e\u001a\u00020\u0005HÆ\u0001J\u0013\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\b\u00102\u001a\u0004\u0018\u00010\u0005J\t\u00103\u001a\u00020\u0003HÖ\u0001J\t\u00104\u001a\u00020\u0005HÖ\u0001J&\u00105\u001a\u0002062\u0006\u00107\u001a\u00020\u00002\u0006\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020;HÁ\u0001¢\u0006\u0002\b<R\u001c\u0010\u000e\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u001c\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0017\u0010\u0014\u001a\u0004\b\u0018\u0010\u0019R\u001c\u0010\t\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001a\u0010\u0014\u001a\u0004\b\u001b\u0010\u0016R\u001c\u0010\u000b\u001a\u00020\f8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001c\u0010\u0014\u001a\u0004\b\u001d\u0010\u001eR\u001c\u0010\n\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001f\u0010\u0014\u001a\u0004\b \u0010\u0016R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b!\u0010\u0014\u001a\u0004\b\"\u0010\u0016R\u0018\u0010\r\u001a\u0004\u0018\u00010\u00058\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b#\u0010\u0014R\u001c\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b$\u0010\u0014\u001a\u0004\b%\u0010\u0016¨\u0006?"}, d2 = {"Lcom/onfido/api/client/data/PoaDocumentUpload;", "", "seen1", "", "id", "", "type", "createdAt", "Ljava/util/Date;", ReactNativeBridgeUtiles.KEY_FILE_NAME, ReactNativeBridgeUtiles.KEY_FILE_TYPE, "fileSize", "", ReactNativeBridgeUtiles.KEY_DOCUMENT_ISSUING_COUNTRY, "applicantId", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/util/Date;Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/Date;Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;)V", "getApplicantId$annotations", "()V", "getApplicantId", "()Ljava/lang/String;", "getCreatedAt$annotations", "getCreatedAt", "()Ljava/util/Date;", "getFileName$annotations", "getFileName", "getFileSize$annotations", "getFileSize", "()J", "getFileType$annotations", "getFileType", "getId$annotations", "getId", "getIssuingCountry$annotations", "getType$annotations", "getType", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", Constants.COPY_TYPE, "equals", "", "other", "getIssuingCountry", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_api_client", "$serializer", "Companion", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Serializable
/* loaded from: classes6.dex */
public final /* data */ class PoaDocumentUpload {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String applicantId;
    private final Date createdAt;
    private final String fileName;
    private final long fileSize;
    private final String fileType;
    private final String id;
    private final String issuingCountry;
    private final String type;

    /* renamed from: component7, reason: from getter */
    private final String getIssuingCountry() {
        return this.issuingCountry;
    }

    @SerialName("applicant_id")
    public static /* synthetic */ void getApplicantId$annotations() {
    }

    @SerialName(Column.CREATED_AT)
    @Serializable(with = DateAsStringSerializer.class)
    public static /* synthetic */ void getCreatedAt$annotations() {
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

    @SerialName("id")
    public static /* synthetic */ void getId$annotations() {
    }

    @SerialName("issuing_country")
    private static /* synthetic */ void getIssuingCountry$annotations() {
    }

    @SerialName("type")
    public static /* synthetic */ void getType$annotations() {
    }

    /* renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* renamed from: component2, reason: from getter */
    public final String getType() {
        return this.type;
    }

    /* renamed from: component3, reason: from getter */
    public final Date getCreatedAt() {
        return this.createdAt;
    }

    /* renamed from: component4, reason: from getter */
    public final String getFileName() {
        return this.fileName;
    }

    /* renamed from: component5, reason: from getter */
    public final String getFileType() {
        return this.fileType;
    }

    /* renamed from: component6, reason: from getter */
    public final long getFileSize() {
        return this.fileSize;
    }

    /* renamed from: component8, reason: from getter */
    public final String getApplicantId() {
        return this.applicantId;
    }

    public final PoaDocumentUpload copy(String id, String type, Date createdAt, String fileName, String fileType, long fileSize, String issuingCountry, String applicantId) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(createdAt, "createdAt");
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        Intrinsics.checkNotNullParameter(fileType, "fileType");
        Intrinsics.checkNotNullParameter(applicantId, "applicantId");
        return new PoaDocumentUpload(id, type, createdAt, fileName, fileType, fileSize, issuingCountry, applicantId);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PoaDocumentUpload)) {
            return false;
        }
        PoaDocumentUpload poaDocumentUpload = (PoaDocumentUpload) other;
        return Intrinsics.areEqual(this.id, poaDocumentUpload.id) && Intrinsics.areEqual(this.type, poaDocumentUpload.type) && Intrinsics.areEqual(this.createdAt, poaDocumentUpload.createdAt) && Intrinsics.areEqual(this.fileName, poaDocumentUpload.fileName) && Intrinsics.areEqual(this.fileType, poaDocumentUpload.fileType) && this.fileSize == poaDocumentUpload.fileSize && Intrinsics.areEqual(this.issuingCountry, poaDocumentUpload.issuingCountry) && Intrinsics.areEqual(this.applicantId, poaDocumentUpload.applicantId);
    }

    public final String getApplicantId() {
        return this.applicantId;
    }

    public final Date getCreatedAt() {
        return this.createdAt;
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

    public final String getId() {
        return this.id;
    }

    public final String getIssuingCountry() {
        return this.issuingCountry;
    }

    public final String getType() {
        return this.type;
    }

    public int hashCode() {
        int iHashCode = ((((((((((this.id.hashCode() * 31) + this.type.hashCode()) * 31) + this.createdAt.hashCode()) * 31) + this.fileName.hashCode()) * 31) + this.fileType.hashCode()) * 31) + Long.hashCode(this.fileSize)) * 31;
        String str = this.issuingCountry;
        return ((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + this.applicantId.hashCode();
    }

    public String toString() {
        return "PoaDocumentUpload(id=" + this.id + ", type=" + this.type + ", createdAt=" + this.createdAt + ", fileName=" + this.fileName + ", fileType=" + this.fileType + ", fileSize=" + this.fileSize + ", issuingCountry=" + this.issuingCountry + ", applicantId=" + this.applicantId + ")";
    }

    /* compiled from: PoaDocumentUpload.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/api/client/data/PoaDocumentUpload$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/api/client/data/PoaDocumentUpload;", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<PoaDocumentUpload> serializer() {
            return PoaDocumentUpload$$serializer.INSTANCE;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ PoaDocumentUpload(int i, @SerialName("id") String str, @SerialName("type") String str2, @SerialName(Column.CREATED_AT) @Serializable(with = DateAsStringSerializer.class) Date date, @SerialName(MediaCallbackResultReceiver.KEY_FILE_NAME) String str3, @SerialName(MediaCallbackResultReceiver.KEY_FILE_TYPE) String str4, @SerialName("file_size") long j, @SerialName("issuing_country") String str5, @SerialName("applicant_id") String str6, SerializationConstructorMarker serializationConstructorMarker) {
        if (191 != (i & CipherSuite.TLS_DH_anon_WITH_CAMELLIA_128_CBC_SHA256)) {
            PluginExceptionsKt.throwMissingFieldException(i, CipherSuite.TLS_DH_anon_WITH_CAMELLIA_128_CBC_SHA256, PoaDocumentUpload$$serializer.INSTANCE.getDescriptor());
        }
        this.id = str;
        this.type = str2;
        this.createdAt = date;
        this.fileName = str3;
        this.fileType = str4;
        this.fileSize = j;
        if ((i & 64) == 0) {
            this.issuingCountry = null;
        } else {
            this.issuingCountry = str5;
        }
        this.applicantId = str6;
    }

    public PoaDocumentUpload(String id, String type, Date createdAt, String fileName, String fileType, long j, String str, String applicantId) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(createdAt, "createdAt");
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        Intrinsics.checkNotNullParameter(fileType, "fileType");
        Intrinsics.checkNotNullParameter(applicantId, "applicantId");
        this.id = id;
        this.type = type;
        this.createdAt = createdAt;
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileSize = j;
        this.issuingCountry = str;
        this.applicantId = applicantId;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$onfido_api_client(PoaDocumentUpload self, CompositeEncoder output, SerialDescriptor serialDesc) {
        output.encodeStringElement(serialDesc, 0, self.id);
        output.encodeStringElement(serialDesc, 1, self.type);
        output.encodeSerializableElement(serialDesc, 2, DateAsStringSerializer.INSTANCE, self.createdAt);
        output.encodeStringElement(serialDesc, 3, self.fileName);
        output.encodeStringElement(serialDesc, 4, self.fileType);
        output.encodeLongElement(serialDesc, 5, self.fileSize);
        if (output.shouldEncodeElementDefault(serialDesc, 6) || self.issuingCountry != null) {
            output.encodeNullableSerializableElement(serialDesc, 6, StringSerializer.INSTANCE, self.issuingCountry);
        }
        output.encodeStringElement(serialDesc, 7, self.applicantId);
    }

    public /* synthetic */ PoaDocumentUpload(String str, String str2, Date date, String str3, String str4, long j, String str5, String str6, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, date, str3, str4, j, (i & 64) != 0 ? null : str5, str6);
    }
}
