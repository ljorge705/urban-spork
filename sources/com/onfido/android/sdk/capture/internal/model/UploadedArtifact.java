package com.onfido.android.sdk.capture.internal.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.clevertap.android.sdk.Constants;
import com.onfido.api.client.data.DocType;
import com.onfido.reactnative.sdk.ReactNativeBridgeUtiles;
import io.sentry.protocol.DebugImage;
import io.sentry.rrweb.RRWebMetaEvent;
import java.util.Date;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bw\u0018\u00002\u00020\u0001:\u0004\u0016\u0017\u0018\u0019R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\tR\u0012\u0010\f\u001a\u00020\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0012\u0010\u0010\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\tR\u0012\u0010\u0012\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\tR\u0012\u0010\u0014\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\t\u0082\u0001\u0004\u001a\u001b\u001c\u001d¨\u0006\u001e"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/model/UploadedArtifact;", "Landroid/os/Parcelable;", "createdAt", "Ljava/util/Date;", "getCreatedAt", "()Ljava/util/Date;", "downloadHref", "", "getDownloadHref", "()Ljava/lang/String;", ReactNativeBridgeUtiles.KEY_FILE_NAME, "getFileName", "fileSize", "", "getFileSize", "()J", ReactNativeBridgeUtiles.KEY_FILE_TYPE, "getFileType", RRWebMetaEvent.JsonKeys.HREF, "getHref", "id", "getId", "AVCVideo", "Document", "LiveVideo", "Photo", "Lcom/onfido/android/sdk/capture/internal/model/UploadedArtifact$AVCVideo;", "Lcom/onfido/android/sdk/capture/internal/model/UploadedArtifact$Document;", "Lcom/onfido/android/sdk/capture/internal/model/UploadedArtifact$LiveVideo;", "Lcom/onfido/android/sdk/capture/internal/model/UploadedArtifact$Photo;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface UploadedArtifact extends Parcelable {

    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\u0013\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!HÖ\u0003J\t\u0010\"\u001a\u00020\u001dHÖ\u0001J\t\u0010#\u001a\u00020\u0003HÖ\u0001J\u0019\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020\u001dHÖ\u0001R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\u0003X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\u0003X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0014\u0010\u000e\u001a\u00020\u000fX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u0003X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000bR\u0014\u0010\u0014\u001a\u00020\u0003X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000bR\u0014\u0010\u0016\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u000b¨\u0006)"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/model/UploadedArtifact$AVCVideo;", "Lcom/onfido/android/sdk/capture/internal/model/UploadedArtifact;", DebugImage.JsonKeys.UUID, "", "createdAt", "Ljava/util/Date;", "(Ljava/lang/String;Ljava/util/Date;)V", "getCreatedAt", "()Ljava/util/Date;", "downloadHref", "getDownloadHref", "()Ljava/lang/String;", ReactNativeBridgeUtiles.KEY_FILE_NAME, "getFileName", "fileSize", "", "getFileSize", "()J", ReactNativeBridgeUtiles.KEY_FILE_TYPE, "getFileType", RRWebMetaEvent.JsonKeys.HREF, "getHref", "id", "getId", "getUuid", "component1", "component2", Constants.COPY_TYPE, "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class AVCVideo implements UploadedArtifact {
        public static final Parcelable.Creator<AVCVideo> CREATOR = new Creator();
        private final Date createdAt;
        private final String downloadHref;
        private final String fileName;
        private final long fileSize;
        private final String fileType;
        private final String href;
        private final String id;
        private final String uuid;

        @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<AVCVideo> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final AVCVideo createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new AVCVideo(parcel.readString(), (Date) parcel.readSerializable());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final AVCVideo[] newArray(int i) {
                return new AVCVideo[i];
            }
        }

        public AVCVideo(String uuid, Date createdAt) {
            Intrinsics.checkNotNullParameter(uuid, "uuid");
            Intrinsics.checkNotNullParameter(createdAt, "createdAt");
            this.uuid = uuid;
            this.createdAt = createdAt;
            this.id = uuid;
            this.fileName = "";
            this.fileType = "";
            this.href = "";
            this.downloadHref = "";
        }

        public static /* synthetic */ AVCVideo copy$default(AVCVideo aVCVideo, String str, Date date, int i, Object obj) {
            if ((i & 1) != 0) {
                str = aVCVideo.uuid;
            }
            if ((i & 2) != 0) {
                date = aVCVideo.createdAt;
            }
            return aVCVideo.copy(str, date);
        }

        /* renamed from: component1, reason: from getter */
        public final String getUuid() {
            return this.uuid;
        }

        /* renamed from: component2, reason: from getter */
        public final Date getCreatedAt() {
            return this.createdAt;
        }

        public final AVCVideo copy(String uuid, Date createdAt) {
            Intrinsics.checkNotNullParameter(uuid, "uuid");
            Intrinsics.checkNotNullParameter(createdAt, "createdAt");
            return new AVCVideo(uuid, createdAt);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof AVCVideo)) {
                return false;
            }
            AVCVideo aVCVideo = (AVCVideo) other;
            return Intrinsics.areEqual(this.uuid, aVCVideo.uuid) && Intrinsics.areEqual(this.createdAt, aVCVideo.createdAt);
        }

        @Override // com.onfido.android.sdk.capture.internal.model.UploadedArtifact
        public Date getCreatedAt() {
            return this.createdAt;
        }

        @Override // com.onfido.android.sdk.capture.internal.model.UploadedArtifact
        public String getDownloadHref() {
            return this.downloadHref;
        }

        @Override // com.onfido.android.sdk.capture.internal.model.UploadedArtifact
        public String getFileName() {
            return this.fileName;
        }

        @Override // com.onfido.android.sdk.capture.internal.model.UploadedArtifact
        public long getFileSize() {
            return this.fileSize;
        }

        @Override // com.onfido.android.sdk.capture.internal.model.UploadedArtifact
        public String getFileType() {
            return this.fileType;
        }

        @Override // com.onfido.android.sdk.capture.internal.model.UploadedArtifact
        public String getHref() {
            return this.href;
        }

        @Override // com.onfido.android.sdk.capture.internal.model.UploadedArtifact
        public String getId() {
            return this.id;
        }

        public final String getUuid() {
            return this.uuid;
        }

        public int hashCode() {
            return (this.uuid.hashCode() * 31) + this.createdAt.hashCode();
        }

        public String toString() {
            return "AVCVideo(uuid=" + this.uuid + ", createdAt=" + this.createdAt + ')';
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int flags) {
            Intrinsics.checkNotNullParameter(parcel, "out");
            parcel.writeString(this.uuid);
            parcel.writeSerializable(this.createdAt);
        }
    }

    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u001e\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001Ba\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0003¢\u0006\u0002\u0010\u0011J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u000fHÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0005HÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\t\u0010'\u001a\u00020\tHÆ\u0003J\t\u0010(\u001a\u00020\u0003HÆ\u0003J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\u0003HÆ\u0003J{\u0010,\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u0003HÆ\u0001J\t\u0010-\u001a\u00020.HÖ\u0001J\u0013\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u000102HÖ\u0003J\t\u00103\u001a\u00020.HÖ\u0001J\t\u00104\u001a\u00020\u0003HÖ\u0001J\u0019\u00105\u001a\u0002062\u0006\u00107\u001a\u0002082\u0006\u00109\u001a\u00020.HÖ\u0001R\u0011\u0010\u0010\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\n\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013R\u0014\u0010\u0006\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0013R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0014\u0010\u0007\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0013R\u0014\u0010\u000b\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0013R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0013R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0013R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0013R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 ¨\u0006:"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/model/UploadedArtifact$Document;", "Lcom/onfido/android/sdk/capture/internal/model/UploadedArtifact;", "id", "", "createdAt", "Ljava/util/Date;", ReactNativeBridgeUtiles.KEY_FILE_NAME, ReactNativeBridgeUtiles.KEY_FILE_TYPE, "fileSize", "", "downloadHref", RRWebMetaEvent.JsonKeys.HREF, ReactNativeBridgeUtiles.KEY_DOCUMENT_SIDE, ReactNativeBridgeUtiles.KEY_DOCUMENT_ISSUING_COUNTRY, "type", "Lcom/onfido/api/client/data/DocType;", "applicantId", "(Ljava/lang/String;Ljava/util/Date;Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/onfido/api/client/data/DocType;Ljava/lang/String;)V", "getApplicantId", "()Ljava/lang/String;", "getCreatedAt", "()Ljava/util/Date;", "getDownloadHref", "getFileName", "getFileSize", "()J", "getFileType", "getHref", "getId", "getIssuingCountry", "getSide", "getType", "()Lcom/onfido/api/client/data/DocType;", "component1", "component10", "component11", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", Constants.COPY_TYPE, "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Document implements UploadedArtifact {
        public static final Parcelable.Creator<Document> CREATOR = new Creator();
        private final String applicantId;
        private final Date createdAt;
        private final String downloadHref;
        private final String fileName;
        private final long fileSize;
        private final String fileType;
        private final String href;
        private final String id;
        private final String issuingCountry;
        private final String side;
        private final DocType type;

        @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<Document> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final Document createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new Document(parcel.readString(), (Date) parcel.readSerializable(), parcel.readString(), parcel.readString(), parcel.readLong(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), DocType.valueOf(parcel.readString()), parcel.readString());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final Document[] newArray(int i) {
                return new Document[i];
            }
        }

        public Document(String id, Date createdAt, String fileName, String fileType, long j, String downloadHref, String href, String str, String str2, DocType type, String applicantId) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(createdAt, "createdAt");
            Intrinsics.checkNotNullParameter(fileName, "fileName");
            Intrinsics.checkNotNullParameter(fileType, "fileType");
            Intrinsics.checkNotNullParameter(downloadHref, "downloadHref");
            Intrinsics.checkNotNullParameter(href, "href");
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(applicantId, "applicantId");
            this.id = id;
            this.createdAt = createdAt;
            this.fileName = fileName;
            this.fileType = fileType;
            this.fileSize = j;
            this.downloadHref = downloadHref;
            this.href = href;
            this.side = str;
            this.issuingCountry = str2;
            this.type = type;
            this.applicantId = applicantId;
        }

        /* renamed from: component1, reason: from getter */
        public final String getId() {
            return this.id;
        }

        /* renamed from: component10, reason: from getter */
        public final DocType getType() {
            return this.type;
        }

        /* renamed from: component11, reason: from getter */
        public final String getApplicantId() {
            return this.applicantId;
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
        public final String getDownloadHref() {
            return this.downloadHref;
        }

        /* renamed from: component7, reason: from getter */
        public final String getHref() {
            return this.href;
        }

        /* renamed from: component8, reason: from getter */
        public final String getSide() {
            return this.side;
        }

        /* renamed from: component9, reason: from getter */
        public final String getIssuingCountry() {
            return this.issuingCountry;
        }

        public final Document copy(String id, Date createdAt, String fileName, String fileType, long fileSize, String downloadHref, String href, String side, String issuingCountry, DocType type, String applicantId) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(createdAt, "createdAt");
            Intrinsics.checkNotNullParameter(fileName, "fileName");
            Intrinsics.checkNotNullParameter(fileType, "fileType");
            Intrinsics.checkNotNullParameter(downloadHref, "downloadHref");
            Intrinsics.checkNotNullParameter(href, "href");
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(applicantId, "applicantId");
            return new Document(id, createdAt, fileName, fileType, fileSize, downloadHref, href, side, issuingCountry, type, applicantId);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Document)) {
                return false;
            }
            Document document = (Document) other;
            return Intrinsics.areEqual(this.id, document.id) && Intrinsics.areEqual(this.createdAt, document.createdAt) && Intrinsics.areEqual(this.fileName, document.fileName) && Intrinsics.areEqual(this.fileType, document.fileType) && this.fileSize == document.fileSize && Intrinsics.areEqual(this.downloadHref, document.downloadHref) && Intrinsics.areEqual(this.href, document.href) && Intrinsics.areEqual(this.side, document.side) && Intrinsics.areEqual(this.issuingCountry, document.issuingCountry) && this.type == document.type && Intrinsics.areEqual(this.applicantId, document.applicantId);
        }

        public final String getApplicantId() {
            return this.applicantId;
        }

        @Override // com.onfido.android.sdk.capture.internal.model.UploadedArtifact
        public Date getCreatedAt() {
            return this.createdAt;
        }

        @Override // com.onfido.android.sdk.capture.internal.model.UploadedArtifact
        public String getDownloadHref() {
            return this.downloadHref;
        }

        @Override // com.onfido.android.sdk.capture.internal.model.UploadedArtifact
        public String getFileName() {
            return this.fileName;
        }

        @Override // com.onfido.android.sdk.capture.internal.model.UploadedArtifact
        public long getFileSize() {
            return this.fileSize;
        }

        @Override // com.onfido.android.sdk.capture.internal.model.UploadedArtifact
        public String getFileType() {
            return this.fileType;
        }

        @Override // com.onfido.android.sdk.capture.internal.model.UploadedArtifact
        public String getHref() {
            return this.href;
        }

        @Override // com.onfido.android.sdk.capture.internal.model.UploadedArtifact
        public String getId() {
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

        public int hashCode() {
            int iHashCode = ((((((((((((this.id.hashCode() * 31) + this.createdAt.hashCode()) * 31) + this.fileName.hashCode()) * 31) + this.fileType.hashCode()) * 31) + Long.hashCode(this.fileSize)) * 31) + this.downloadHref.hashCode()) * 31) + this.href.hashCode()) * 31;
            String str = this.side;
            int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.issuingCountry;
            return ((((iHashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31) + this.type.hashCode()) * 31) + this.applicantId.hashCode();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("Document(id=");
            sb.append(this.id).append(", createdAt=").append(this.createdAt).append(", fileName=").append(this.fileName).append(", fileType=").append(this.fileType).append(", fileSize=").append(this.fileSize).append(", downloadHref=").append(this.downloadHref).append(", href=").append(this.href).append(", side=").append(this.side).append(", issuingCountry=").append(this.issuingCountry).append(", type=").append(this.type).append(", applicantId=").append(this.applicantId).append(')');
            return sb.toString();
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int flags) {
            Intrinsics.checkNotNullParameter(parcel, "out");
            parcel.writeString(this.id);
            parcel.writeSerializable(this.createdAt);
            parcel.writeString(this.fileName);
            parcel.writeString(this.fileType);
            parcel.writeLong(this.fileSize);
            parcel.writeString(this.downloadHref);
            parcel.writeString(this.href);
            parcel.writeString(this.side);
            parcel.writeString(this.issuingCountry);
            parcel.writeString(this.type.name());
            parcel.writeString(this.applicantId);
        }
    }

    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0016\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003¢\u0006\u0002\u0010\fJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\tHÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003JO\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u0003HÆ\u0001J\t\u0010\u001f\u001a\u00020 HÖ\u0001J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010$HÖ\u0003J\t\u0010%\u001a\u00020 HÖ\u0001J\t\u0010&\u001a\u00020\u0003HÖ\u0001J\u0019\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020 HÖ\u0001R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000b\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0006\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0007\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010R\u0014\u0010\n\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0010R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0010¨\u0006,"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/model/UploadedArtifact$LiveVideo;", "Lcom/onfido/android/sdk/capture/internal/model/UploadedArtifact;", "id", "", "createdAt", "Ljava/util/Date;", ReactNativeBridgeUtiles.KEY_FILE_NAME, ReactNativeBridgeUtiles.KEY_FILE_TYPE, "fileSize", "", RRWebMetaEvent.JsonKeys.HREF, "downloadHref", "(Ljava/lang/String;Ljava/util/Date;Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;)V", "getCreatedAt", "()Ljava/util/Date;", "getDownloadHref", "()Ljava/lang/String;", "getFileName", "getFileSize", "()J", "getFileType", "getHref", "getId", "component1", "component2", "component3", "component4", "component5", "component6", "component7", Constants.COPY_TYPE, "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class LiveVideo implements UploadedArtifact {
        public static final Parcelable.Creator<LiveVideo> CREATOR = new Creator();
        private final Date createdAt;
        private final String downloadHref;
        private final String fileName;
        private final long fileSize;
        private final String fileType;
        private final String href;
        private final String id;

        @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<LiveVideo> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final LiveVideo createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new LiveVideo(parcel.readString(), (Date) parcel.readSerializable(), parcel.readString(), parcel.readString(), parcel.readLong(), parcel.readString(), parcel.readString());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final LiveVideo[] newArray(int i) {
                return new LiveVideo[i];
            }
        }

        public LiveVideo(String id, Date createdAt, String fileName, String fileType, long j, String href, String downloadHref) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(createdAt, "createdAt");
            Intrinsics.checkNotNullParameter(fileName, "fileName");
            Intrinsics.checkNotNullParameter(fileType, "fileType");
            Intrinsics.checkNotNullParameter(href, "href");
            Intrinsics.checkNotNullParameter(downloadHref, "downloadHref");
            this.id = id;
            this.createdAt = createdAt;
            this.fileName = fileName;
            this.fileType = fileType;
            this.fileSize = j;
            this.href = href;
            this.downloadHref = downloadHref;
        }

        /* renamed from: component1, reason: from getter */
        public final String getId() {
            return this.id;
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
        public final String getHref() {
            return this.href;
        }

        /* renamed from: component7, reason: from getter */
        public final String getDownloadHref() {
            return this.downloadHref;
        }

        public final LiveVideo copy(String id, Date createdAt, String fileName, String fileType, long fileSize, String href, String downloadHref) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(createdAt, "createdAt");
            Intrinsics.checkNotNullParameter(fileName, "fileName");
            Intrinsics.checkNotNullParameter(fileType, "fileType");
            Intrinsics.checkNotNullParameter(href, "href");
            Intrinsics.checkNotNullParameter(downloadHref, "downloadHref");
            return new LiveVideo(id, createdAt, fileName, fileType, fileSize, href, downloadHref);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof LiveVideo)) {
                return false;
            }
            LiveVideo liveVideo = (LiveVideo) other;
            return Intrinsics.areEqual(this.id, liveVideo.id) && Intrinsics.areEqual(this.createdAt, liveVideo.createdAt) && Intrinsics.areEqual(this.fileName, liveVideo.fileName) && Intrinsics.areEqual(this.fileType, liveVideo.fileType) && this.fileSize == liveVideo.fileSize && Intrinsics.areEqual(this.href, liveVideo.href) && Intrinsics.areEqual(this.downloadHref, liveVideo.downloadHref);
        }

        @Override // com.onfido.android.sdk.capture.internal.model.UploadedArtifact
        public Date getCreatedAt() {
            return this.createdAt;
        }

        @Override // com.onfido.android.sdk.capture.internal.model.UploadedArtifact
        public String getDownloadHref() {
            return this.downloadHref;
        }

        @Override // com.onfido.android.sdk.capture.internal.model.UploadedArtifact
        public String getFileName() {
            return this.fileName;
        }

        @Override // com.onfido.android.sdk.capture.internal.model.UploadedArtifact
        public long getFileSize() {
            return this.fileSize;
        }

        @Override // com.onfido.android.sdk.capture.internal.model.UploadedArtifact
        public String getFileType() {
            return this.fileType;
        }

        @Override // com.onfido.android.sdk.capture.internal.model.UploadedArtifact
        public String getHref() {
            return this.href;
        }

        @Override // com.onfido.android.sdk.capture.internal.model.UploadedArtifact
        public String getId() {
            return this.id;
        }

        public int hashCode() {
            return (((((((((((this.id.hashCode() * 31) + this.createdAt.hashCode()) * 31) + this.fileName.hashCode()) * 31) + this.fileType.hashCode()) * 31) + Long.hashCode(this.fileSize)) * 31) + this.href.hashCode()) * 31) + this.downloadHref.hashCode();
        }

        public String toString() {
            return "LiveVideo(id=" + this.id + ", createdAt=" + this.createdAt + ", fileName=" + this.fileName + ", fileType=" + this.fileType + ", fileSize=" + this.fileSize + ", href=" + this.href + ", downloadHref=" + this.downloadHref + ')';
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int flags) {
            Intrinsics.checkNotNullParameter(parcel, "out");
            parcel.writeString(this.id);
            parcel.writeSerializable(this.createdAt);
            parcel.writeString(this.fileName);
            parcel.writeString(this.fileType);
            parcel.writeLong(this.fileSize);
            parcel.writeString(this.href);
            parcel.writeString(this.downloadHref);
        }
    }

    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0016\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003¢\u0006\u0002\u0010\fJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\tHÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003JO\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u0003HÆ\u0001J\t\u0010\u001f\u001a\u00020 HÖ\u0001J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010$HÖ\u0003J\t\u0010%\u001a\u00020 HÖ\u0001J\t\u0010&\u001a\u00020\u0003HÖ\u0001J\u0019\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020 HÖ\u0001R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\n\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0006\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0007\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010R\u0014\u0010\u000b\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0010R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0010¨\u0006,"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/model/UploadedArtifact$Photo;", "Lcom/onfido/android/sdk/capture/internal/model/UploadedArtifact;", "id", "", "createdAt", "Ljava/util/Date;", ReactNativeBridgeUtiles.KEY_FILE_NAME, ReactNativeBridgeUtiles.KEY_FILE_TYPE, "fileSize", "", "downloadHref", RRWebMetaEvent.JsonKeys.HREF, "(Ljava/lang/String;Ljava/util/Date;Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;)V", "getCreatedAt", "()Ljava/util/Date;", "getDownloadHref", "()Ljava/lang/String;", "getFileName", "getFileSize", "()J", "getFileType", "getHref", "getId", "component1", "component2", "component3", "component4", "component5", "component6", "component7", Constants.COPY_TYPE, "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Photo implements UploadedArtifact {
        public static final Parcelable.Creator<Photo> CREATOR = new Creator();
        private final Date createdAt;
        private final String downloadHref;
        private final String fileName;
        private final long fileSize;
        private final String fileType;
        private final String href;
        private final String id;

        @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<Photo> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final Photo createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new Photo(parcel.readString(), (Date) parcel.readSerializable(), parcel.readString(), parcel.readString(), parcel.readLong(), parcel.readString(), parcel.readString());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final Photo[] newArray(int i) {
                return new Photo[i];
            }
        }

        public Photo(String id, Date createdAt, String fileName, String fileType, long j, String downloadHref, String href) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(createdAt, "createdAt");
            Intrinsics.checkNotNullParameter(fileName, "fileName");
            Intrinsics.checkNotNullParameter(fileType, "fileType");
            Intrinsics.checkNotNullParameter(downloadHref, "downloadHref");
            Intrinsics.checkNotNullParameter(href, "href");
            this.id = id;
            this.createdAt = createdAt;
            this.fileName = fileName;
            this.fileType = fileType;
            this.fileSize = j;
            this.downloadHref = downloadHref;
            this.href = href;
        }

        /* renamed from: component1, reason: from getter */
        public final String getId() {
            return this.id;
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
        public final String getDownloadHref() {
            return this.downloadHref;
        }

        /* renamed from: component7, reason: from getter */
        public final String getHref() {
            return this.href;
        }

        public final Photo copy(String id, Date createdAt, String fileName, String fileType, long fileSize, String downloadHref, String href) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(createdAt, "createdAt");
            Intrinsics.checkNotNullParameter(fileName, "fileName");
            Intrinsics.checkNotNullParameter(fileType, "fileType");
            Intrinsics.checkNotNullParameter(downloadHref, "downloadHref");
            Intrinsics.checkNotNullParameter(href, "href");
            return new Photo(id, createdAt, fileName, fileType, fileSize, downloadHref, href);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Photo)) {
                return false;
            }
            Photo photo = (Photo) other;
            return Intrinsics.areEqual(this.id, photo.id) && Intrinsics.areEqual(this.createdAt, photo.createdAt) && Intrinsics.areEqual(this.fileName, photo.fileName) && Intrinsics.areEqual(this.fileType, photo.fileType) && this.fileSize == photo.fileSize && Intrinsics.areEqual(this.downloadHref, photo.downloadHref) && Intrinsics.areEqual(this.href, photo.href);
        }

        @Override // com.onfido.android.sdk.capture.internal.model.UploadedArtifact
        public Date getCreatedAt() {
            return this.createdAt;
        }

        @Override // com.onfido.android.sdk.capture.internal.model.UploadedArtifact
        public String getDownloadHref() {
            return this.downloadHref;
        }

        @Override // com.onfido.android.sdk.capture.internal.model.UploadedArtifact
        public String getFileName() {
            return this.fileName;
        }

        @Override // com.onfido.android.sdk.capture.internal.model.UploadedArtifact
        public long getFileSize() {
            return this.fileSize;
        }

        @Override // com.onfido.android.sdk.capture.internal.model.UploadedArtifact
        public String getFileType() {
            return this.fileType;
        }

        @Override // com.onfido.android.sdk.capture.internal.model.UploadedArtifact
        public String getHref() {
            return this.href;
        }

        @Override // com.onfido.android.sdk.capture.internal.model.UploadedArtifact
        public String getId() {
            return this.id;
        }

        public int hashCode() {
            return (((((((((((this.id.hashCode() * 31) + this.createdAt.hashCode()) * 31) + this.fileName.hashCode()) * 31) + this.fileType.hashCode()) * 31) + Long.hashCode(this.fileSize)) * 31) + this.downloadHref.hashCode()) * 31) + this.href.hashCode();
        }

        public String toString() {
            return "Photo(id=" + this.id + ", createdAt=" + this.createdAt + ", fileName=" + this.fileName + ", fileType=" + this.fileType + ", fileSize=" + this.fileSize + ", downloadHref=" + this.downloadHref + ", href=" + this.href + ')';
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int flags) {
            Intrinsics.checkNotNullParameter(parcel, "out");
            parcel.writeString(this.id);
            parcel.writeSerializable(this.createdAt);
            parcel.writeString(this.fileName);
            parcel.writeString(this.fileType);
            parcel.writeLong(this.fileSize);
            parcel.writeString(this.downloadHref);
            parcel.writeString(this.href);
        }
    }

    Date getCreatedAt();

    String getDownloadHref();

    String getFileName();

    long getFileSize();

    String getFileType();

    String getHref();

    String getId();
}
