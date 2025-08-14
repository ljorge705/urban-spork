package com.onfido.android.sdk.capture.internal.usecase.validation;

import com.clevertap.android.sdk.Constants;
import com.google.common.net.HttpHeaders;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.api.client.data.DocSide;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bp\u0018\u00002\u00020\u0001:\u0003\u0002\u0003\u0004\u0082\u0001\u0003\u0005\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/usecase/validation/DocumentValidationResult;", "", "Hold", "Success", HttpHeaders.WARNING, "Lcom/onfido/android/sdk/capture/internal/usecase/validation/DocumentValidationResult$Hold;", "Lcom/onfido/android/sdk/capture/internal/usecase/validation/DocumentValidationResult$Success;", "Lcom/onfido/android/sdk/capture/internal/usecase/validation/DocumentValidationResult$Warning;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface DocumentValidationResult {

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0013\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÖ\u0003J\t\u0010\u0007\u001a\u00020\bHÖ\u0001J\t\u0010\t\u001a\u00020\nHÖ\u0001¨\u0006\u000b"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/usecase/validation/DocumentValidationResult$Hold;", "Lcom/onfido/android/sdk/capture/internal/usecase/validation/DocumentValidationResult;", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Hold implements DocumentValidationResult {
        public static final Hold INSTANCE = new Hold();

        private Hold() {
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof Hold);
        }

        public int hashCode() {
            return 1039599104;
        }

        public String toString() {
            return "Hold";
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0013\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÖ\u0003J\t\u0010\u0007\u001a\u00020\bHÖ\u0001J\t\u0010\t\u001a\u00020\nHÖ\u0001¨\u0006\u000b"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/usecase/validation/DocumentValidationResult$Success;", "Lcom/onfido/android/sdk/capture/internal/usecase/validation/DocumentValidationResult;", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Success implements DocumentValidationResult {
        public static final Success INSTANCE = new Success();

        private Success() {
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof Success);
        }

        public int hashCode() {
            return 1023875874;
        }

        public String toString() {
            return "Success";
        }
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\b\u0002\u0003\u0004\u0005\u0006\u0007\b\t\u0082\u0001\b\n\u000b\f\r\u000e\u000f\u0010\u0011¨\u0006\u0012"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/usecase/validation/DocumentValidationResult$Warning;", "Lcom/onfido/android/sdk/capture/internal/usecase/validation/DocumentValidationResult;", "Blur", "CutOff", "DocumentTooClose", "DocumentTooFar", "Glare", "NoDocument", "WrongDocument", "WrongSide", "Lcom/onfido/android/sdk/capture/internal/usecase/validation/DocumentValidationResult$Warning$Blur;", "Lcom/onfido/android/sdk/capture/internal/usecase/validation/DocumentValidationResult$Warning$CutOff;", "Lcom/onfido/android/sdk/capture/internal/usecase/validation/DocumentValidationResult$Warning$DocumentTooClose;", "Lcom/onfido/android/sdk/capture/internal/usecase/validation/DocumentValidationResult$Warning$DocumentTooFar;", "Lcom/onfido/android/sdk/capture/internal/usecase/validation/DocumentValidationResult$Warning$Glare;", "Lcom/onfido/android/sdk/capture/internal/usecase/validation/DocumentValidationResult$Warning$NoDocument;", "Lcom/onfido/android/sdk/capture/internal/usecase/validation/DocumentValidationResult$Warning$WrongDocument;", "Lcom/onfido/android/sdk/capture/internal/usecase/validation/DocumentValidationResult$Warning$WrongSide;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface Warning extends DocumentValidationResult {

        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0013\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÖ\u0003J\t\u0010\u0007\u001a\u00020\bHÖ\u0001J\t\u0010\t\u001a\u00020\nHÖ\u0001¨\u0006\u000b"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/usecase/validation/DocumentValidationResult$Warning$Blur;", "Lcom/onfido/android/sdk/capture/internal/usecase/validation/DocumentValidationResult$Warning;", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class Blur implements Warning {
            public static final Blur INSTANCE = new Blur();

            private Blur() {
            }

            public boolean equals(Object other) {
                return this == other || (other instanceof Blur);
            }

            public int hashCode() {
                return -846147622;
            }

            public String toString() {
                return "Blur";
            }
        }

        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0013\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÖ\u0003J\t\u0010\u0007\u001a\u00020\bHÖ\u0001J\t\u0010\t\u001a\u00020\nHÖ\u0001¨\u0006\u000b"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/usecase/validation/DocumentValidationResult$Warning$CutOff;", "Lcom/onfido/android/sdk/capture/internal/usecase/validation/DocumentValidationResult$Warning;", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class CutOff implements Warning {
            public static final CutOff INSTANCE = new CutOff();

            private CutOff() {
            }

            public boolean equals(Object other) {
                return this == other || (other instanceof CutOff);
            }

            public int hashCode() {
                return -1362165120;
            }

            public String toString() {
                return "CutOff";
            }
        }

        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0013\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÖ\u0003J\t\u0010\u0007\u001a\u00020\bHÖ\u0001J\t\u0010\t\u001a\u00020\nHÖ\u0001¨\u0006\u000b"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/usecase/validation/DocumentValidationResult$Warning$DocumentTooClose;", "Lcom/onfido/android/sdk/capture/internal/usecase/validation/DocumentValidationResult$Warning;", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class DocumentTooClose implements Warning {
            public static final DocumentTooClose INSTANCE = new DocumentTooClose();

            private DocumentTooClose() {
            }

            public boolean equals(Object other) {
                return this == other || (other instanceof DocumentTooClose);
            }

            public int hashCode() {
                return -1333864910;
            }

            public String toString() {
                return "DocumentTooClose";
            }
        }

        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0013\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÖ\u0003J\t\u0010\u0007\u001a\u00020\bHÖ\u0001J\t\u0010\t\u001a\u00020\nHÖ\u0001¨\u0006\u000b"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/usecase/validation/DocumentValidationResult$Warning$DocumentTooFar;", "Lcom/onfido/android/sdk/capture/internal/usecase/validation/DocumentValidationResult$Warning;", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class DocumentTooFar implements Warning {
            public static final DocumentTooFar INSTANCE = new DocumentTooFar();

            private DocumentTooFar() {
            }

            public boolean equals(Object other) {
                return this == other || (other instanceof DocumentTooFar);
            }

            public int hashCode() {
                return 1196378577;
            }

            public String toString() {
                return "DocumentTooFar";
            }
        }

        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0013\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÖ\u0003J\t\u0010\u0007\u001a\u00020\bHÖ\u0001J\t\u0010\t\u001a\u00020\nHÖ\u0001¨\u0006\u000b"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/usecase/validation/DocumentValidationResult$Warning$Glare;", "Lcom/onfido/android/sdk/capture/internal/usecase/validation/DocumentValidationResult$Warning;", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class Glare implements Warning {
            public static final Glare INSTANCE = new Glare();

            private Glare() {
            }

            public boolean equals(Object other) {
                return this == other || (other instanceof Glare);
            }

            public int hashCode() {
                return -456174020;
            }

            public String toString() {
                return "Glare";
            }
        }

        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0013\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÖ\u0003J\t\u0010\u0007\u001a\u00020\bHÖ\u0001J\t\u0010\t\u001a\u00020\nHÖ\u0001¨\u0006\u000b"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/usecase/validation/DocumentValidationResult$Warning$NoDocument;", "Lcom/onfido/android/sdk/capture/internal/usecase/validation/DocumentValidationResult$Warning;", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class NoDocument implements Warning {
            public static final NoDocument INSTANCE = new NoDocument();

            private NoDocument() {
            }

            public boolean equals(Object other) {
                return this == other || (other instanceof NoDocument);
            }

            public int hashCode() {
                return -158470353;
            }

            public String toString() {
                return "NoDocument";
            }
        }

        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\u000b\u0010\t\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0003J!\u0010\u000b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0014"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/usecase/validation/DocumentValidationResult$Warning$WrongDocument;", "Lcom/onfido/android/sdk/capture/internal/usecase/validation/DocumentValidationResult$Warning;", "expectedDocumentType", "Lcom/onfido/android/sdk/capture/DocumentType;", "actualDocumentType", "(Lcom/onfido/android/sdk/capture/DocumentType;Lcom/onfido/android/sdk/capture/DocumentType;)V", "getActualDocumentType", "()Lcom/onfido/android/sdk/capture/DocumentType;", "getExpectedDocumentType", "component1", "component2", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class WrongDocument implements Warning {
            private final DocumentType actualDocumentType;
            private final DocumentType expectedDocumentType;

            public WrongDocument(DocumentType documentType, DocumentType documentType2) {
                this.expectedDocumentType = documentType;
                this.actualDocumentType = documentType2;
            }

            public static /* synthetic */ WrongDocument copy$default(WrongDocument wrongDocument, DocumentType documentType, DocumentType documentType2, int i, Object obj) {
                if ((i & 1) != 0) {
                    documentType = wrongDocument.expectedDocumentType;
                }
                if ((i & 2) != 0) {
                    documentType2 = wrongDocument.actualDocumentType;
                }
                return wrongDocument.copy(documentType, documentType2);
            }

            /* renamed from: component1, reason: from getter */
            public final DocumentType getExpectedDocumentType() {
                return this.expectedDocumentType;
            }

            /* renamed from: component2, reason: from getter */
            public final DocumentType getActualDocumentType() {
                return this.actualDocumentType;
            }

            public final WrongDocument copy(DocumentType expectedDocumentType, DocumentType actualDocumentType) {
                return new WrongDocument(expectedDocumentType, actualDocumentType);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof WrongDocument)) {
                    return false;
                }
                WrongDocument wrongDocument = (WrongDocument) other;
                return this.expectedDocumentType == wrongDocument.expectedDocumentType && this.actualDocumentType == wrongDocument.actualDocumentType;
            }

            public final DocumentType getActualDocumentType() {
                return this.actualDocumentType;
            }

            public final DocumentType getExpectedDocumentType() {
                return this.expectedDocumentType;
            }

            public int hashCode() {
                DocumentType documentType = this.expectedDocumentType;
                int iHashCode = (documentType == null ? 0 : documentType.hashCode()) * 31;
                DocumentType documentType2 = this.actualDocumentType;
                return iHashCode + (documentType2 != null ? documentType2.hashCode() : 0);
            }

            public String toString() {
                return "WrongDocument(expectedDocumentType=" + this.expectedDocumentType + ", actualDocumentType=" + this.actualDocumentType + ')';
            }

            public /* synthetic */ WrongDocument(DocumentType documentType, DocumentType documentType2, int i, DefaultConstructorMarker defaultConstructorMarker) {
                this(documentType, (i & 2) != 0 ? null : documentType2);
            }
        }

        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\u000b\u0010\t\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0003J!\u0010\u000b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0014"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/usecase/validation/DocumentValidationResult$Warning$WrongSide;", "Lcom/onfido/android/sdk/capture/internal/usecase/validation/DocumentValidationResult$Warning;", "expectedSide", "Lcom/onfido/api/client/data/DocSide;", "actualSide", "(Lcom/onfido/api/client/data/DocSide;Lcom/onfido/api/client/data/DocSide;)V", "getActualSide", "()Lcom/onfido/api/client/data/DocSide;", "getExpectedSide", "component1", "component2", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class WrongSide implements Warning {
            private final DocSide actualSide;
            private final DocSide expectedSide;

            public WrongSide(DocSide docSide, DocSide docSide2) {
                this.expectedSide = docSide;
                this.actualSide = docSide2;
            }

            public static /* synthetic */ WrongSide copy$default(WrongSide wrongSide, DocSide docSide, DocSide docSide2, int i, Object obj) {
                if ((i & 1) != 0) {
                    docSide = wrongSide.expectedSide;
                }
                if ((i & 2) != 0) {
                    docSide2 = wrongSide.actualSide;
                }
                return wrongSide.copy(docSide, docSide2);
            }

            /* renamed from: component1, reason: from getter */
            public final DocSide getExpectedSide() {
                return this.expectedSide;
            }

            /* renamed from: component2, reason: from getter */
            public final DocSide getActualSide() {
                return this.actualSide;
            }

            public final WrongSide copy(DocSide expectedSide, DocSide actualSide) {
                return new WrongSide(expectedSide, actualSide);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof WrongSide)) {
                    return false;
                }
                WrongSide wrongSide = (WrongSide) other;
                return this.expectedSide == wrongSide.expectedSide && this.actualSide == wrongSide.actualSide;
            }

            public final DocSide getActualSide() {
                return this.actualSide;
            }

            public final DocSide getExpectedSide() {
                return this.expectedSide;
            }

            public int hashCode() {
                DocSide docSide = this.expectedSide;
                int iHashCode = (docSide == null ? 0 : docSide.hashCode()) * 31;
                DocSide docSide2 = this.actualSide;
                return iHashCode + (docSide2 != null ? docSide2.hashCode() : 0);
            }

            public String toString() {
                return "WrongSide(expectedSide=" + this.expectedSide + ", actualSide=" + this.actualSide + ')';
            }
        }
    }
}
