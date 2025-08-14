package com.onfido.android.sdk.capture.ui.options;

import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.DocumentFormat;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.config.MediaCallbackResultReceiver;
import com.onfido.android.sdk.capture.document.DocumentPages;
import com.onfido.android.sdk.capture.ui.country_selection.CountryAlternatives;
import com.onfido.android.sdk.capture.utils.CountryCode;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CaptureScreenOptions.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BI\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0002\u0010\u000eJ\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u000bHÆ\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\rHÆ\u0003JO\u0010#\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\rHÆ\u0001J\u0013\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010'H\u0096\u0002J\b\u0010(\u001a\u00020)H\u0016J\t\u0010*\u001a\u00020\u000bHÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u000b8F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0013\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0015¨\u0006+"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/options/CaptureScreenOptions;", "Lcom/onfido/android/sdk/capture/ui/options/BaseOptions;", "documentType", "Lcom/onfido/android/sdk/capture/DocumentType;", MediaCallbackResultReceiver.KEY_COUNTRY, "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "countryAlternatives", "Lcom/onfido/android/sdk/capture/ui/country_selection/CountryAlternatives;", "documentFormat", "Lcom/onfido/android/sdk/capture/DocumentFormat;", "genericDocTitle", "", "genericDocPages", "Lcom/onfido/android/sdk/capture/document/DocumentPages;", "(Lcom/onfido/android/sdk/capture/DocumentType;Lcom/onfido/android/sdk/capture/utils/CountryCode;Lcom/onfido/android/sdk/capture/ui/country_selection/CountryAlternatives;Lcom/onfido/android/sdk/capture/DocumentFormat;Ljava/lang/String;Lcom/onfido/android/sdk/capture/document/DocumentPages;)V", "getCountry", "()Lcom/onfido/android/sdk/capture/utils/CountryCode;", "getCountryAlternatives", "()Lcom/onfido/android/sdk/capture/ui/country_selection/CountryAlternatives;", "countryString", "getCountryString", "()Ljava/lang/String;", "getDocumentFormat", "()Lcom/onfido/android/sdk/capture/DocumentFormat;", "getDocumentType", "()Lcom/onfido/android/sdk/capture/DocumentType;", "getGenericDocPages", "()Lcom/onfido/android/sdk/capture/document/DocumentPages;", "getGenericDocTitle", "component1", "component2", "component3", "component4", "component5", "component6", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class CaptureScreenOptions extends BaseOptions {
    private final CountryCode country;
    private final CountryAlternatives countryAlternatives;
    private final DocumentFormat documentFormat;
    private final DocumentType documentType;
    private final DocumentPages genericDocPages;
    private final String genericDocTitle;

    public static /* synthetic */ CaptureScreenOptions copy$default(CaptureScreenOptions captureScreenOptions, DocumentType documentType, CountryCode countryCode, CountryAlternatives countryAlternatives, DocumentFormat documentFormat, String str, DocumentPages documentPages, int i, Object obj) {
        if ((i & 1) != 0) {
            documentType = captureScreenOptions.documentType;
        }
        if ((i & 2) != 0) {
            countryCode = captureScreenOptions.country;
        }
        CountryCode countryCode2 = countryCode;
        if ((i & 4) != 0) {
            countryAlternatives = captureScreenOptions.countryAlternatives;
        }
        CountryAlternatives countryAlternatives2 = countryAlternatives;
        if ((i & 8) != 0) {
            documentFormat = captureScreenOptions.documentFormat;
        }
        DocumentFormat documentFormat2 = documentFormat;
        if ((i & 16) != 0) {
            str = captureScreenOptions.genericDocTitle;
        }
        String str2 = str;
        if ((i & 32) != 0) {
            documentPages = captureScreenOptions.genericDocPages;
        }
        return captureScreenOptions.copy(documentType, countryCode2, countryAlternatives2, documentFormat2, str2, documentPages);
    }

    /* renamed from: component1, reason: from getter */
    public final DocumentType getDocumentType() {
        return this.documentType;
    }

    /* renamed from: component2, reason: from getter */
    public final CountryCode getCountry() {
        return this.country;
    }

    /* renamed from: component3, reason: from getter */
    public final CountryAlternatives getCountryAlternatives() {
        return this.countryAlternatives;
    }

    /* renamed from: component4, reason: from getter */
    public final DocumentFormat getDocumentFormat() {
        return this.documentFormat;
    }

    /* renamed from: component5, reason: from getter */
    public final String getGenericDocTitle() {
        return this.genericDocTitle;
    }

    /* renamed from: component6, reason: from getter */
    public final DocumentPages getGenericDocPages() {
        return this.genericDocPages;
    }

    public final CaptureScreenOptions copy(DocumentType documentType, CountryCode country, CountryAlternatives countryAlternatives, DocumentFormat documentFormat, String genericDocTitle, DocumentPages genericDocPages) {
        Intrinsics.checkNotNullParameter(documentType, "documentType");
        return new CaptureScreenOptions(documentType, country, countryAlternatives, documentFormat, genericDocTitle, genericDocPages);
    }

    public final CountryCode getCountry() {
        return this.country;
    }

    public final CountryAlternatives getCountryAlternatives() {
        return this.countryAlternatives;
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

    public String toString() {
        return "CaptureScreenOptions(documentType=" + this.documentType + ", country=" + this.country + ", countryAlternatives=" + this.countryAlternatives + ", documentFormat=" + this.documentFormat + ", genericDocTitle=" + this.genericDocTitle + ", genericDocPages=" + this.genericDocPages + ")";
    }

    public /* synthetic */ CaptureScreenOptions(DocumentType documentType, CountryCode countryCode, CountryAlternatives countryAlternatives, DocumentFormat documentFormat, String str, DocumentPages documentPages, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(documentType, (i & 2) != 0 ? null : countryCode, (i & 4) != 0 ? null : countryAlternatives, (i & 8) != 0 ? null : documentFormat, (i & 16) != 0 ? null : str, (i & 32) == 0 ? documentPages : null);
    }

    public CaptureScreenOptions(DocumentType documentType, CountryCode countryCode, CountryAlternatives countryAlternatives, DocumentFormat documentFormat, String str, DocumentPages documentPages) {
        Intrinsics.checkNotNullParameter(documentType, "documentType");
        this.documentType = documentType;
        this.country = countryCode;
        this.countryAlternatives = countryAlternatives;
        this.documentFormat = documentFormat;
        this.genericDocTitle = str;
        this.genericDocPages = documentPages;
    }

    public final String getCountryString() {
        String strName;
        CountryAlternatives countryAlternatives = this.countryAlternatives;
        if (countryAlternatives != null && (strName = countryAlternatives.name()) != null) {
            return strName;
        }
        CountryCode countryCode = this.country;
        if (countryCode != null) {
            return countryCode.name();
        }
        return null;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CaptureScreenOptions)) {
            return false;
        }
        CaptureScreenOptions captureScreenOptions = (CaptureScreenOptions) other;
        return this.documentType == captureScreenOptions.documentType && this.country == captureScreenOptions.country && this.countryAlternatives == captureScreenOptions.countryAlternatives && this.documentFormat == captureScreenOptions.documentFormat && this.genericDocPages == captureScreenOptions.genericDocPages;
    }

    public int hashCode() {
        int iHashCode = this.documentType.hashCode();
        CountryCode countryCode = this.country;
        if (countryCode != null) {
            iHashCode = (iHashCode * 31) + countryCode.hashCode();
        }
        CountryAlternatives countryAlternatives = this.countryAlternatives;
        if (countryAlternatives != null) {
            iHashCode = (iHashCode * 31) + countryAlternatives.hashCode();
        }
        DocumentFormat documentFormat = this.documentFormat;
        if (documentFormat != null) {
            iHashCode = (iHashCode * 31) + documentFormat.hashCode();
        }
        DocumentPages documentPages = this.genericDocPages;
        return (iHashCode * 31) + (documentPages != null ? documentPages.hashCode() : 0);
    }
}
