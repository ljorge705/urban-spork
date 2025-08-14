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

/* compiled from: LivePhotoUpload.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 :2\u00020\u0001:\u00029:Bk\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0001\u0010\n\u001a\u00020\u000b\u0012\n\b\u0001\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\u0002\u0010\u0010B=\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u0005\u0012\u0006\u0010\r\u001a\u00020\u0005¢\u0006\u0002\u0010\u0011J\t\u0010$\u001a\u00020\u0005HÆ\u0003J\t\u0010%\u001a\u00020\u0007HÆ\u0003J\t\u0010&\u001a\u00020\u0005HÆ\u0003J\t\u0010'\u001a\u00020\u0005HÆ\u0003J\t\u0010(\u001a\u00020\u000bHÆ\u0003J\t\u0010)\u001a\u00020\u0005HÆ\u0003J\t\u0010*\u001a\u00020\u0005HÆ\u0003JO\u0010+\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u00052\b\b\u0002\u0010\r\u001a\u00020\u0005HÆ\u0001J\u0013\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010/\u001a\u00020\u0003HÖ\u0001J\t\u00100\u001a\u00020\u0005HÖ\u0001J&\u00101\u001a\u0002022\u0006\u00103\u001a\u00020\u00002\u0006\u00104\u001a\u0002052\u0006\u00106\u001a\u000207HÁ\u0001¢\u0006\u0002\b8R\u001c\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u001c\u0010\r\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0016\u0010\u0013\u001a\u0004\b\u0017\u0010\u0018R\u001c\u0010\b\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0019\u0010\u0013\u001a\u0004\b\u001a\u0010\u0018R\u001c\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001b\u0010\u0013\u001a\u0004\b\u001c\u0010\u001dR\u001c\u0010\t\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001e\u0010\u0013\u001a\u0004\b\u001f\u0010\u0018R\u001c\u0010\f\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b \u0010\u0013\u001a\u0004\b!\u0010\u0018R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\"\u0010\u0013\u001a\u0004\b#\u0010\u0018¨\u0006;"}, d2 = {"Lcom/onfido/api/client/data/LivePhotoUpload;", "", "seen1", "", "id", "", "createdAt", "Ljava/util/Date;", ReactNativeBridgeUtiles.KEY_FILE_NAME, ReactNativeBridgeUtiles.KEY_FILE_TYPE, "fileSize", "", RRWebMetaEvent.JsonKeys.HREF, "downloadHref", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/util/Date;Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/lang/String;Ljava/util/Date;Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;)V", "getCreatedAt$annotations", "()V", "getCreatedAt", "()Ljava/util/Date;", "getDownloadHref$annotations", "getDownloadHref", "()Ljava/lang/String;", "getFileName$annotations", "getFileName", "getFileSize$annotations", "getFileSize", "()J", "getFileType$annotations", "getFileType", "getHref$annotations", "getHref", "getId$annotations", "getId", "component1", "component2", "component3", "component4", "component5", "component6", "component7", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_api_client", "$serializer", "Companion", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Serializable
/* loaded from: classes6.dex */
public final /* data */ class LivePhotoUpload {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Date createdAt;
    private final String downloadHref;
    private final String fileName;
    private final long fileSize;
    private final String fileType;
    private final String href;
    private final String id;

    @SerialName(Column.CREATED_AT)
    @Serializable(with = DateAsStringSerializer.class)
    public static /* synthetic */ void getCreatedAt$annotations() {
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

    public final LivePhotoUpload copy(String id, Date createdAt, String fileName, String fileType, long fileSize, String href, String downloadHref) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(createdAt, "createdAt");
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        Intrinsics.checkNotNullParameter(fileType, "fileType");
        Intrinsics.checkNotNullParameter(href, "href");
        Intrinsics.checkNotNullParameter(downloadHref, "downloadHref");
        return new LivePhotoUpload(id, createdAt, fileName, fileType, fileSize, href, downloadHref);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LivePhotoUpload)) {
            return false;
        }
        LivePhotoUpload livePhotoUpload = (LivePhotoUpload) other;
        return Intrinsics.areEqual(this.id, livePhotoUpload.id) && Intrinsics.areEqual(this.createdAt, livePhotoUpload.createdAt) && Intrinsics.areEqual(this.fileName, livePhotoUpload.fileName) && Intrinsics.areEqual(this.fileType, livePhotoUpload.fileType) && this.fileSize == livePhotoUpload.fileSize && Intrinsics.areEqual(this.href, livePhotoUpload.href) && Intrinsics.areEqual(this.downloadHref, livePhotoUpload.downloadHref);
    }

    public final Date getCreatedAt() {
        return this.createdAt;
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

    public int hashCode() {
        return (((((((((((this.id.hashCode() * 31) + this.createdAt.hashCode()) * 31) + this.fileName.hashCode()) * 31) + this.fileType.hashCode()) * 31) + Long.hashCode(this.fileSize)) * 31) + this.href.hashCode()) * 31) + this.downloadHref.hashCode();
    }

    public String toString() {
        return "LivePhotoUpload(id=" + this.id + ", createdAt=" + this.createdAt + ", fileName=" + this.fileName + ", fileType=" + this.fileType + ", fileSize=" + this.fileSize + ", href=" + this.href + ", downloadHref=" + this.downloadHref + ")";
    }

    /* compiled from: LivePhotoUpload.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/api/client/data/LivePhotoUpload$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/api/client/data/LivePhotoUpload;", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<LivePhotoUpload> serializer() {
            return LivePhotoUpload$$serializer.INSTANCE;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ LivePhotoUpload(int i, @SerialName("id") String str, @SerialName(Column.CREATED_AT) @Serializable(with = DateAsStringSerializer.class) Date date, @SerialName(MediaCallbackResultReceiver.KEY_FILE_NAME) String str2, @SerialName(MediaCallbackResultReceiver.KEY_FILE_TYPE) String str3, @SerialName("file_size") long j, @SerialName(RRWebMetaEvent.JsonKeys.HREF) String str4, @SerialName("download_href") String str5, SerializationConstructorMarker serializationConstructorMarker) {
        if (127 != (i & 127)) {
            PluginExceptionsKt.throwMissingFieldException(i, 127, LivePhotoUpload$$serializer.INSTANCE.getDescriptor());
        }
        this.id = str;
        this.createdAt = date;
        this.fileName = str2;
        this.fileType = str3;
        this.fileSize = j;
        this.href = str4;
        this.downloadHref = str5;
    }

    public LivePhotoUpload(String id, Date createdAt, String fileName, String fileType, long j, String href, String downloadHref) {
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

    @JvmStatic
    public static final /* synthetic */ void write$Self$onfido_api_client(LivePhotoUpload self, CompositeEncoder output, SerialDescriptor serialDesc) {
        output.encodeStringElement(serialDesc, 0, self.id);
        output.encodeSerializableElement(serialDesc, 1, DateAsStringSerializer.INSTANCE, self.createdAt);
        output.encodeStringElement(serialDesc, 2, self.fileName);
        output.encodeStringElement(serialDesc, 3, self.fileType);
        output.encodeLongElement(serialDesc, 4, self.fileSize);
        output.encodeStringElement(serialDesc, 5, self.href);
        output.encodeStringElement(serialDesc, 6, self.downloadHref);
    }
}
