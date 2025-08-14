package com.onfido.android.sdk.capture.ui.camera;

import com.clevertap.android.sdk.Constants;
import com.onfido.api.client.data.DocumentFeatures;
import com.onfido.api.client.data.DocumentValidationWarningsBundle;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0080\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0007HÆ\u0003J+\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0019"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/DocumentUploadResult;", "", "mediaId", "", "warningsBundle", "Lcom/onfido/api/client/data/DocumentValidationWarningsBundle;", "documentFeatures", "Lcom/onfido/api/client/data/DocumentFeatures;", "(Ljava/lang/String;Lcom/onfido/api/client/data/DocumentValidationWarningsBundle;Lcom/onfido/api/client/data/DocumentFeatures;)V", "getDocumentFeatures", "()Lcom/onfido/api/client/data/DocumentFeatures;", "getMediaId", "()Ljava/lang/String;", "getWarningsBundle", "()Lcom/onfido/api/client/data/DocumentValidationWarningsBundle;", "component1", "component2", "component3", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "", "toString", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class DocumentUploadResult {
    private final DocumentFeatures documentFeatures;
    private final String mediaId;
    private final DocumentValidationWarningsBundle warningsBundle;

    public DocumentUploadResult(String mediaId, DocumentValidationWarningsBundle documentValidationWarningsBundle, DocumentFeatures documentFeatures) {
        Intrinsics.checkNotNullParameter(mediaId, "mediaId");
        this.mediaId = mediaId;
        this.warningsBundle = documentValidationWarningsBundle;
        this.documentFeatures = documentFeatures;
    }

    public static /* synthetic */ DocumentUploadResult copy$default(DocumentUploadResult documentUploadResult, String str, DocumentValidationWarningsBundle documentValidationWarningsBundle, DocumentFeatures documentFeatures, int i, Object obj) {
        if ((i & 1) != 0) {
            str = documentUploadResult.mediaId;
        }
        if ((i & 2) != 0) {
            documentValidationWarningsBundle = documentUploadResult.warningsBundle;
        }
        if ((i & 4) != 0) {
            documentFeatures = documentUploadResult.documentFeatures;
        }
        return documentUploadResult.copy(str, documentValidationWarningsBundle, documentFeatures);
    }

    /* renamed from: component1, reason: from getter */
    public final String getMediaId() {
        return this.mediaId;
    }

    /* renamed from: component2, reason: from getter */
    public final DocumentValidationWarningsBundle getWarningsBundle() {
        return this.warningsBundle;
    }

    /* renamed from: component3, reason: from getter */
    public final DocumentFeatures getDocumentFeatures() {
        return this.documentFeatures;
    }

    public final DocumentUploadResult copy(String mediaId, DocumentValidationWarningsBundle warningsBundle, DocumentFeatures documentFeatures) {
        Intrinsics.checkNotNullParameter(mediaId, "mediaId");
        return new DocumentUploadResult(mediaId, warningsBundle, documentFeatures);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DocumentUploadResult)) {
            return false;
        }
        DocumentUploadResult documentUploadResult = (DocumentUploadResult) other;
        return Intrinsics.areEqual(this.mediaId, documentUploadResult.mediaId) && Intrinsics.areEqual(this.warningsBundle, documentUploadResult.warningsBundle) && Intrinsics.areEqual(this.documentFeatures, documentUploadResult.documentFeatures);
    }

    public final DocumentFeatures getDocumentFeatures() {
        return this.documentFeatures;
    }

    public final String getMediaId() {
        return this.mediaId;
    }

    public final DocumentValidationWarningsBundle getWarningsBundle() {
        return this.warningsBundle;
    }

    public int hashCode() {
        int iHashCode = this.mediaId.hashCode() * 31;
        DocumentValidationWarningsBundle documentValidationWarningsBundle = this.warningsBundle;
        int iHashCode2 = (iHashCode + (documentValidationWarningsBundle == null ? 0 : documentValidationWarningsBundle.hashCode())) * 31;
        DocumentFeatures documentFeatures = this.documentFeatures;
        return iHashCode2 + (documentFeatures != null ? documentFeatures.hashCode() : 0);
    }

    public String toString() {
        return "DocumentUploadResult(mediaId=" + this.mediaId + ", warningsBundle=" + this.warningsBundle + ", documentFeatures=" + this.documentFeatures + ')';
    }
}
