package com.onfido.android.sdk.capture.flow;

import com.clevertap.android.sdk.Constants;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.onfido.android.sdk.capture.DocumentFormat;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.document.DocumentPages;
import com.onfido.android.sdk.capture.ui.CaptureType;
import com.onfido.android.sdk.capture.utils.CountryCode;
import com.onfido.api.client.data.DocSide;
import com.onfido.reactnative.sdk.ReactNativeBridgeUtiles;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BU\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\u0002\u0010\u0010J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\u000bHÆ\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\rHÆ\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\u000fHÆ\u0003J[\u0010&\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÆ\u0001J\u0013\u0010'\u001a\u00020(2\b\u0010)\u001a\u0004\u0018\u00010*HÖ\u0003J\t\u0010+\u001a\u00020,HÖ\u0001J\t\u0010-\u001a\u00020\rHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0013\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001e¨\u0006."}, d2 = {"Lcom/onfido/android/sdk/capture/flow/CaptureStepDataBundle;", "Ljava/io/Serializable;", ReactNativeBridgeUtiles.KEY_CAPTURE_TYPE, "Lcom/onfido/android/sdk/capture/ui/CaptureType;", "documentType", "Lcom/onfido/android/sdk/capture/DocumentType;", RemoteConfigConstants.RequestFieldKey.COUNTRY_CODE, "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "documentFormat", "Lcom/onfido/android/sdk/capture/DocumentFormat;", "docSide", "Lcom/onfido/api/client/data/DocSide;", "genericDocTitle", "", "genericDocPages", "Lcom/onfido/android/sdk/capture/document/DocumentPages;", "(Lcom/onfido/android/sdk/capture/ui/CaptureType;Lcom/onfido/android/sdk/capture/DocumentType;Lcom/onfido/android/sdk/capture/utils/CountryCode;Lcom/onfido/android/sdk/capture/DocumentFormat;Lcom/onfido/api/client/data/DocSide;Ljava/lang/String;Lcom/onfido/android/sdk/capture/document/DocumentPages;)V", "getCaptureType", "()Lcom/onfido/android/sdk/capture/ui/CaptureType;", "getCountryCode", "()Lcom/onfido/android/sdk/capture/utils/CountryCode;", "getDocSide", "()Lcom/onfido/api/client/data/DocSide;", "getDocumentFormat", "()Lcom/onfido/android/sdk/capture/DocumentFormat;", "getDocumentType", "()Lcom/onfido/android/sdk/capture/DocumentType;", "getGenericDocPages", "()Lcom/onfido/android/sdk/capture/document/DocumentPages;", "getGenericDocTitle", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class CaptureStepDataBundle implements Serializable {
    private final CaptureType captureType;
    private final CountryCode countryCode;
    private final DocSide docSide;
    private final DocumentFormat documentFormat;
    private final DocumentType documentType;
    private final DocumentPages genericDocPages;
    private final String genericDocTitle;

    public CaptureStepDataBundle(CaptureType captureType, DocumentType documentType, CountryCode countryCode, DocumentFormat documentFormat, DocSide docSide, String str, DocumentPages documentPages) {
        Intrinsics.checkNotNullParameter(captureType, "captureType");
        this.captureType = captureType;
        this.documentType = documentType;
        this.countryCode = countryCode;
        this.documentFormat = documentFormat;
        this.docSide = docSide;
        this.genericDocTitle = str;
        this.genericDocPages = documentPages;
    }

    public static /* synthetic */ CaptureStepDataBundle copy$default(CaptureStepDataBundle captureStepDataBundle, CaptureType captureType, DocumentType documentType, CountryCode countryCode, DocumentFormat documentFormat, DocSide docSide, String str, DocumentPages documentPages, int i, Object obj) {
        if ((i & 1) != 0) {
            captureType = captureStepDataBundle.captureType;
        }
        if ((i & 2) != 0) {
            documentType = captureStepDataBundle.documentType;
        }
        DocumentType documentType2 = documentType;
        if ((i & 4) != 0) {
            countryCode = captureStepDataBundle.countryCode;
        }
        CountryCode countryCode2 = countryCode;
        if ((i & 8) != 0) {
            documentFormat = captureStepDataBundle.documentFormat;
        }
        DocumentFormat documentFormat2 = documentFormat;
        if ((i & 16) != 0) {
            docSide = captureStepDataBundle.docSide;
        }
        DocSide docSide2 = docSide;
        if ((i & 32) != 0) {
            str = captureStepDataBundle.genericDocTitle;
        }
        String str2 = str;
        if ((i & 64) != 0) {
            documentPages = captureStepDataBundle.genericDocPages;
        }
        return captureStepDataBundle.copy(captureType, documentType2, countryCode2, documentFormat2, docSide2, str2, documentPages);
    }

    /* renamed from: component1, reason: from getter */
    public final CaptureType getCaptureType() {
        return this.captureType;
    }

    /* renamed from: component2, reason: from getter */
    public final DocumentType getDocumentType() {
        return this.documentType;
    }

    /* renamed from: component3, reason: from getter */
    public final CountryCode getCountryCode() {
        return this.countryCode;
    }

    /* renamed from: component4, reason: from getter */
    public final DocumentFormat getDocumentFormat() {
        return this.documentFormat;
    }

    /* renamed from: component5, reason: from getter */
    public final DocSide getDocSide() {
        return this.docSide;
    }

    /* renamed from: component6, reason: from getter */
    public final String getGenericDocTitle() {
        return this.genericDocTitle;
    }

    /* renamed from: component7, reason: from getter */
    public final DocumentPages getGenericDocPages() {
        return this.genericDocPages;
    }

    public final CaptureStepDataBundle copy(CaptureType captureType, DocumentType documentType, CountryCode countryCode, DocumentFormat documentFormat, DocSide docSide, String genericDocTitle, DocumentPages genericDocPages) {
        Intrinsics.checkNotNullParameter(captureType, "captureType");
        return new CaptureStepDataBundle(captureType, documentType, countryCode, documentFormat, docSide, genericDocTitle, genericDocPages);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CaptureStepDataBundle)) {
            return false;
        }
        CaptureStepDataBundle captureStepDataBundle = (CaptureStepDataBundle) other;
        return this.captureType == captureStepDataBundle.captureType && this.documentType == captureStepDataBundle.documentType && this.countryCode == captureStepDataBundle.countryCode && this.documentFormat == captureStepDataBundle.documentFormat && this.docSide == captureStepDataBundle.docSide && Intrinsics.areEqual(this.genericDocTitle, captureStepDataBundle.genericDocTitle) && this.genericDocPages == captureStepDataBundle.genericDocPages;
    }

    public final CaptureType getCaptureType() {
        return this.captureType;
    }

    public final CountryCode getCountryCode() {
        return this.countryCode;
    }

    public final DocSide getDocSide() {
        return this.docSide;
    }

    public final DocumentFormat getDocumentFormat() {
        return this.documentFormat;
    }

    public final DocumentType getDocumentType() {
        return this.documentType;
    }

    public final DocumentPages getGenericDocPages() {
        return this.genericDocPages;
    }

    public final String getGenericDocTitle() {
        return this.genericDocTitle;
    }

    public int hashCode() {
        int iHashCode = this.captureType.hashCode() * 31;
        DocumentType documentType = this.documentType;
        int iHashCode2 = (iHashCode + (documentType == null ? 0 : documentType.hashCode())) * 31;
        CountryCode countryCode = this.countryCode;
        int iHashCode3 = (iHashCode2 + (countryCode == null ? 0 : countryCode.hashCode())) * 31;
        DocumentFormat documentFormat = this.documentFormat;
        int iHashCode4 = (iHashCode3 + (documentFormat == null ? 0 : documentFormat.hashCode())) * 31;
        DocSide docSide = this.docSide;
        int iHashCode5 = (iHashCode4 + (docSide == null ? 0 : docSide.hashCode())) * 31;
        String str = this.genericDocTitle;
        int iHashCode6 = (iHashCode5 + (str == null ? 0 : str.hashCode())) * 31;
        DocumentPages documentPages = this.genericDocPages;
        return iHashCode6 + (documentPages != null ? documentPages.hashCode() : 0);
    }

    public String toString() {
        return "CaptureStepDataBundle(captureType=" + this.captureType + ", documentType=" + this.documentType + ", countryCode=" + this.countryCode + ", documentFormat=" + this.documentFormat + ", docSide=" + this.docSide + ", genericDocTitle=" + this.genericDocTitle + ", genericDocPages=" + this.genericDocPages + ')';
    }

    public /* synthetic */ CaptureStepDataBundle(CaptureType captureType, DocumentType documentType, CountryCode countryCode, DocumentFormat documentFormat, DocSide docSide, String str, DocumentPages documentPages, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(captureType, (i & 2) != 0 ? null : documentType, (i & 4) != 0 ? null : countryCode, (i & 8) != 0 ? null : documentFormat, (i & 16) != 0 ? null : docSide, (i & 32) != 0 ? null : str, (i & 64) == 0 ? documentPages : null);
    }
}
