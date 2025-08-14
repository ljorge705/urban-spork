package com.onfido.android.sdk.capture.ui.documentselection;

import com.clevertap.android.sdk.Constants;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.document.DocumentPages;
import com.onfido.android.sdk.capture.utils.CountryCode;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bp\u0018\u00002\u00020\u0001:\u0004\u0002\u0003\u0004\u0005\u0082\u0001\u0004\u0006\u0007\b\t¨\u0006\n"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/documentselection/DocumentTypeSelectionState;", "", "CountrySelected", "DocumentTypeSelected", "LoadingDocumentsFailed", "NoCountrySelected", "Lcom/onfido/android/sdk/capture/ui/documentselection/DocumentTypeSelectionState$CountrySelected;", "Lcom/onfido/android/sdk/capture/ui/documentselection/DocumentTypeSelectionState$DocumentTypeSelected;", "Lcom/onfido/android/sdk/capture/ui/documentselection/DocumentTypeSelectionState$LoadingDocumentsFailed;", "Lcom/onfido/android/sdk/capture/ui/documentselection/DocumentTypeSelectionState$NoCountrySelected;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface DocumentTypeSelectionState {

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/documentselection/DocumentTypeSelectionState$CountrySelected;", "Lcom/onfido/android/sdk/capture/ui/documentselection/DocumentTypeSelectionState;", RemoteConfigConstants.RequestFieldKey.COUNTRY_CODE, "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "(Lcom/onfido/android/sdk/capture/utils/CountryCode;)V", "getCountryCode", "()Lcom/onfido/android/sdk/capture/utils/CountryCode;", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class CountrySelected implements DocumentTypeSelectionState {
        private final CountryCode countryCode;

        public CountrySelected(CountryCode countryCode) {
            Intrinsics.checkNotNullParameter(countryCode, "countryCode");
            this.countryCode = countryCode;
        }

        public static /* synthetic */ CountrySelected copy$default(CountrySelected countrySelected, CountryCode countryCode, int i, Object obj) {
            if ((i & 1) != 0) {
                countryCode = countrySelected.countryCode;
            }
            return countrySelected.copy(countryCode);
        }

        /* renamed from: component1, reason: from getter */
        public final CountryCode getCountryCode() {
            return this.countryCode;
        }

        public final CountrySelected copy(CountryCode countryCode) {
            Intrinsics.checkNotNullParameter(countryCode, "countryCode");
            return new CountrySelected(countryCode);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof CountrySelected) && this.countryCode == ((CountrySelected) other).countryCode;
        }

        public final CountryCode getCountryCode() {
            return this.countryCode;
        }

        public int hashCode() {
            return this.countryCode.hashCode();
        }

        public String toString() {
            return "CountrySelected(countryCode=" + this.countryCode + ')';
        }
    }

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\tHÆ\u0003J5\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tHÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bHÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\u0007HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001f"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/documentselection/DocumentTypeSelectionState$DocumentTypeSelected;", "Lcom/onfido/android/sdk/capture/ui/documentselection/DocumentTypeSelectionState;", RemoteConfigConstants.RequestFieldKey.COUNTRY_CODE, "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "documentType", "Lcom/onfido/android/sdk/capture/DocumentType;", "genericDocTitle", "", "genericDocPages", "Lcom/onfido/android/sdk/capture/document/DocumentPages;", "(Lcom/onfido/android/sdk/capture/utils/CountryCode;Lcom/onfido/android/sdk/capture/DocumentType;Ljava/lang/String;Lcom/onfido/android/sdk/capture/document/DocumentPages;)V", "getCountryCode", "()Lcom/onfido/android/sdk/capture/utils/CountryCode;", "getDocumentType", "()Lcom/onfido/android/sdk/capture/DocumentType;", "getGenericDocPages", "()Lcom/onfido/android/sdk/capture/document/DocumentPages;", "getGenericDocTitle", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class DocumentTypeSelected implements DocumentTypeSelectionState {
        private final CountryCode countryCode;
        private final DocumentType documentType;
        private final DocumentPages genericDocPages;
        private final String genericDocTitle;

        public DocumentTypeSelected(CountryCode countryCode, DocumentType documentType, String str, DocumentPages documentPages) {
            Intrinsics.checkNotNullParameter(countryCode, "countryCode");
            Intrinsics.checkNotNullParameter(documentType, "documentType");
            this.countryCode = countryCode;
            this.documentType = documentType;
            this.genericDocTitle = str;
            this.genericDocPages = documentPages;
        }

        public static /* synthetic */ DocumentTypeSelected copy$default(DocumentTypeSelected documentTypeSelected, CountryCode countryCode, DocumentType documentType, String str, DocumentPages documentPages, int i, Object obj) {
            if ((i & 1) != 0) {
                countryCode = documentTypeSelected.countryCode;
            }
            if ((i & 2) != 0) {
                documentType = documentTypeSelected.documentType;
            }
            if ((i & 4) != 0) {
                str = documentTypeSelected.genericDocTitle;
            }
            if ((i & 8) != 0) {
                documentPages = documentTypeSelected.genericDocPages;
            }
            return documentTypeSelected.copy(countryCode, documentType, str, documentPages);
        }

        /* renamed from: component1, reason: from getter */
        public final CountryCode getCountryCode() {
            return this.countryCode;
        }

        /* renamed from: component2, reason: from getter */
        public final DocumentType getDocumentType() {
            return this.documentType;
        }

        /* renamed from: component3, reason: from getter */
        public final String getGenericDocTitle() {
            return this.genericDocTitle;
        }

        /* renamed from: component4, reason: from getter */
        public final DocumentPages getGenericDocPages() {
            return this.genericDocPages;
        }

        public final DocumentTypeSelected copy(CountryCode countryCode, DocumentType documentType, String genericDocTitle, DocumentPages genericDocPages) {
            Intrinsics.checkNotNullParameter(countryCode, "countryCode");
            Intrinsics.checkNotNullParameter(documentType, "documentType");
            return new DocumentTypeSelected(countryCode, documentType, genericDocTitle, genericDocPages);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DocumentTypeSelected)) {
                return false;
            }
            DocumentTypeSelected documentTypeSelected = (DocumentTypeSelected) other;
            return this.countryCode == documentTypeSelected.countryCode && this.documentType == documentTypeSelected.documentType && Intrinsics.areEqual(this.genericDocTitle, documentTypeSelected.genericDocTitle) && this.genericDocPages == documentTypeSelected.genericDocPages;
        }

        public final CountryCode getCountryCode() {
            return this.countryCode;
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
            int iHashCode = ((this.countryCode.hashCode() * 31) + this.documentType.hashCode()) * 31;
            String str = this.genericDocTitle;
            int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
            DocumentPages documentPages = this.genericDocPages;
            return iHashCode2 + (documentPages != null ? documentPages.hashCode() : 0);
        }

        public String toString() {
            return "DocumentTypeSelected(countryCode=" + this.countryCode + ", documentType=" + this.documentType + ", genericDocTitle=" + this.genericDocTitle + ", genericDocPages=" + this.genericDocPages + ')';
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/documentselection/DocumentTypeSelectionState$LoadingDocumentsFailed;", "Lcom/onfido/android/sdk/capture/ui/documentselection/DocumentTypeSelectionState;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class LoadingDocumentsFailed implements DocumentTypeSelectionState {
        public static final LoadingDocumentsFailed INSTANCE = new LoadingDocumentsFailed();

        private LoadingDocumentsFailed() {
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/documentselection/DocumentTypeSelectionState$NoCountrySelected;", "Lcom/onfido/android/sdk/capture/ui/documentselection/DocumentTypeSelectionState;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class NoCountrySelected implements DocumentTypeSelectionState {
        public static final NoCountrySelected INSTANCE = new NoCountrySelected();

        private NoCountrySelected() {
        }
    }
}
