package com.onfido.android.sdk.capture.upload;

import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.DocumentType;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Captures.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 +2\u00020\u0001:\u0001+BA\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000bJ\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\bHÆ\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\nHÆ\u0003JE\u0010#\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nHÆ\u0001J\u0013\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010'HÖ\u0003J\t\u0010(\u001a\u00020)HÖ\u0001J\t\u0010*\u001a\u00020\u0006HÖ\u0001R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\r\"\u0004\b\u0011\u0010\u000fR\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001d¨\u0006,"}, d2 = {"Lcom/onfido/android/sdk/capture/upload/Document;", "Ljava/io/Serializable;", "front", "Lcom/onfido/android/sdk/capture/upload/DocumentSide;", "back", "nfcMediaUUID", "", "type", "Lcom/onfido/android/sdk/capture/DocumentType;", "video", "Lcom/onfido/android/sdk/capture/upload/DocumentVideo;", "(Lcom/onfido/android/sdk/capture/upload/DocumentSide;Lcom/onfido/android/sdk/capture/upload/DocumentSide;Ljava/lang/String;Lcom/onfido/android/sdk/capture/DocumentType;Lcom/onfido/android/sdk/capture/upload/DocumentVideo;)V", "getBack", "()Lcom/onfido/android/sdk/capture/upload/DocumentSide;", "setBack", "(Lcom/onfido/android/sdk/capture/upload/DocumentSide;)V", "getFront", "setFront", "getNfcMediaUUID", "()Ljava/lang/String;", "setNfcMediaUUID", "(Ljava/lang/String;)V", "getType", "()Lcom/onfido/android/sdk/capture/DocumentType;", "setType", "(Lcom/onfido/android/sdk/capture/DocumentType;)V", "getVideo", "()Lcom/onfido/android/sdk/capture/upload/DocumentVideo;", "setVideo", "(Lcom/onfido/android/sdk/capture/upload/DocumentVideo;)V", "component1", "component2", "component3", "component4", "component5", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "Companion", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class Document implements Serializable {
    private static final long serialVersionUID = 1;
    private DocumentSide back;
    private DocumentSide front;
    private String nfcMediaUUID;
    private DocumentType type;
    private DocumentVideo video;

    public Document() {
        this(null, null, null, null, null, 31, null);
    }

    public static /* synthetic */ Document copy$default(Document document, DocumentSide documentSide, DocumentSide documentSide2, String str, DocumentType documentType, DocumentVideo documentVideo, int i, Object obj) {
        if ((i & 1) != 0) {
            documentSide = document.front;
        }
        if ((i & 2) != 0) {
            documentSide2 = document.back;
        }
        DocumentSide documentSide3 = documentSide2;
        if ((i & 4) != 0) {
            str = document.nfcMediaUUID;
        }
        String str2 = str;
        if ((i & 8) != 0) {
            documentType = document.type;
        }
        DocumentType documentType2 = documentType;
        if ((i & 16) != 0) {
            documentVideo = document.video;
        }
        return document.copy(documentSide, documentSide3, str2, documentType2, documentVideo);
    }

    /* renamed from: component1, reason: from getter */
    public final DocumentSide getFront() {
        return this.front;
    }

    /* renamed from: component2, reason: from getter */
    public final DocumentSide getBack() {
        return this.back;
    }

    /* renamed from: component3, reason: from getter */
    public final String getNfcMediaUUID() {
        return this.nfcMediaUUID;
    }

    /* renamed from: component4, reason: from getter */
    public final DocumentType getType() {
        return this.type;
    }

    /* renamed from: component5, reason: from getter */
    public final DocumentVideo getVideo() {
        return this.video;
    }

    public final Document copy(DocumentSide front, DocumentSide back, String nfcMediaUUID, DocumentType type, DocumentVideo video) {
        return new Document(front, back, nfcMediaUUID, type, video);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Document)) {
            return false;
        }
        Document document = (Document) other;
        return Intrinsics.areEqual(this.front, document.front) && Intrinsics.areEqual(this.back, document.back) && Intrinsics.areEqual(this.nfcMediaUUID, document.nfcMediaUUID) && this.type == document.type && Intrinsics.areEqual(this.video, document.video);
    }

    public final DocumentSide getBack() {
        return this.back;
    }

    public final DocumentSide getFront() {
        return this.front;
    }

    public final String getNfcMediaUUID() {
        return this.nfcMediaUUID;
    }

    public final DocumentType getType() {
        return this.type;
    }

    public final DocumentVideo getVideo() {
        return this.video;
    }

    public int hashCode() {
        DocumentSide documentSide = this.front;
        int iHashCode = (documentSide == null ? 0 : documentSide.hashCode()) * 31;
        DocumentSide documentSide2 = this.back;
        int iHashCode2 = (iHashCode + (documentSide2 == null ? 0 : documentSide2.hashCode())) * 31;
        String str = this.nfcMediaUUID;
        int iHashCode3 = (iHashCode2 + (str == null ? 0 : str.hashCode())) * 31;
        DocumentType documentType = this.type;
        int iHashCode4 = (iHashCode3 + (documentType == null ? 0 : documentType.hashCode())) * 31;
        DocumentVideo documentVideo = this.video;
        return iHashCode4 + (documentVideo != null ? documentVideo.hashCode() : 0);
    }

    public final void setBack(DocumentSide documentSide) {
        this.back = documentSide;
    }

    public final void setFront(DocumentSide documentSide) {
        this.front = documentSide;
    }

    public final void setNfcMediaUUID(String str) {
        this.nfcMediaUUID = str;
    }

    public final void setType(DocumentType documentType) {
        this.type = documentType;
    }

    public final void setVideo(DocumentVideo documentVideo) {
        this.video = documentVideo;
    }

    public String toString() {
        return "Document(front=" + this.front + ", back=" + this.back + ", nfcMediaUUID=" + this.nfcMediaUUID + ", type=" + this.type + ", video=" + this.video + ")";
    }

    public Document(DocumentSide documentSide, DocumentSide documentSide2, String str, DocumentType documentType, DocumentVideo documentVideo) {
        this.front = documentSide;
        this.back = documentSide2;
        this.nfcMediaUUID = str;
        this.type = documentType;
        this.video = documentVideo;
    }

    public /* synthetic */ Document(DocumentSide documentSide, DocumentSide documentSide2, String str, DocumentType documentType, DocumentVideo documentVideo, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : documentSide, (i & 2) != 0 ? null : documentSide2, (i & 4) != 0 ? null : str, (i & 8) != 0 ? null : documentType, (i & 16) != 0 ? null : documentVideo);
    }
}
