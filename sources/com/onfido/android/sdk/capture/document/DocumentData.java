package com.onfido.android.sdk.capture.document;

import com.clevertap.android.sdk.Constants;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.utils.CountryCode;
import com.onfido.api.client.data.DocSide;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0007HÆ\u0003J+\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001a"}, d2 = {"Lcom/onfido/android/sdk/capture/document/DocumentData;", "", "documentType", "Lcom/onfido/android/sdk/capture/DocumentType;", RemoteConfigConstants.RequestFieldKey.COUNTRY_CODE, "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "documentSide", "Lcom/onfido/api/client/data/DocSide;", "(Lcom/onfido/android/sdk/capture/DocumentType;Lcom/onfido/android/sdk/capture/utils/CountryCode;Lcom/onfido/api/client/data/DocSide;)V", "getCountryCode", "()Lcom/onfido/android/sdk/capture/utils/CountryCode;", "getDocumentSide", "()Lcom/onfido/api/client/data/DocSide;", "getDocumentType", "()Lcom/onfido/android/sdk/capture/DocumentType;", "component1", "component2", "component3", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class DocumentData {
    private final CountryCode countryCode;
    private final DocSide documentSide;
    private final DocumentType documentType;

    public DocumentData(DocumentType documentType, CountryCode countryCode, DocSide docSide) {
        Intrinsics.checkNotNullParameter(documentType, "documentType");
        this.documentType = documentType;
        this.countryCode = countryCode;
        this.documentSide = docSide;
    }

    public static /* synthetic */ DocumentData copy$default(DocumentData documentData, DocumentType documentType, CountryCode countryCode, DocSide docSide, int i, Object obj) {
        if ((i & 1) != 0) {
            documentType = documentData.documentType;
        }
        if ((i & 2) != 0) {
            countryCode = documentData.countryCode;
        }
        if ((i & 4) != 0) {
            docSide = documentData.documentSide;
        }
        return documentData.copy(documentType, countryCode, docSide);
    }

    /* renamed from: component1, reason: from getter */
    public final DocumentType getDocumentType() {
        return this.documentType;
    }

    /* renamed from: component2, reason: from getter */
    public final CountryCode getCountryCode() {
        return this.countryCode;
    }

    /* renamed from: component3, reason: from getter */
    public final DocSide getDocumentSide() {
        return this.documentSide;
    }

    public final DocumentData copy(DocumentType documentType, CountryCode countryCode, DocSide documentSide) {
        Intrinsics.checkNotNullParameter(documentType, "documentType");
        return new DocumentData(documentType, countryCode, documentSide);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DocumentData)) {
            return false;
        }
        DocumentData documentData = (DocumentData) other;
        return this.documentType == documentData.documentType && this.countryCode == documentData.countryCode && this.documentSide == documentData.documentSide;
    }

    public final CountryCode getCountryCode() {
        return this.countryCode;
    }

    public final DocSide getDocumentSide() {
        return this.documentSide;
    }

    public final DocumentType getDocumentType() {
        return this.documentType;
    }

    public int hashCode() {
        int iHashCode = this.documentType.hashCode() * 31;
        CountryCode countryCode = this.countryCode;
        int iHashCode2 = (iHashCode + (countryCode == null ? 0 : countryCode.hashCode())) * 31;
        DocSide docSide = this.documentSide;
        return iHashCode2 + (docSide != null ? docSide.hashCode() : 0);
    }

    public String toString() {
        return "DocumentData(documentType=" + this.documentType + ", countryCode=" + this.countryCode + ", documentSide=" + this.documentSide + ')';
    }
}
