package com.onfido.workflow.internal.ui.model;

import com.clevertap.android.sdk.Constants;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.onfido.android.sdk.capture.DocumentFormat;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.flow.NfcArguments;
import com.onfido.android.sdk.capture.utils.CountryCode;
import com.onfido.api.client.data.DocSide;
import com.onfido.workflow.OnfidoWorkflow;
import io.sentry.SentryEvent;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: OneOffUIEvent.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u00002\u00020\u0001:\u0005\u0003\u0004\u0005\u0006\u0007B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0005\b\t\n\u000b\f¨\u0006\r"}, d2 = {"Lcom/onfido/workflow/internal/ui/model/OneOffUIEvent;", "", "()V", "DoNothing", "FinishFlow", "NavigateToDocumentFlow", "NavigateToFaceUploadFlow", "NavigateToFaceVideoFlow", "Lcom/onfido/workflow/internal/ui/model/OneOffUIEvent$DoNothing;", "Lcom/onfido/workflow/internal/ui/model/OneOffUIEvent$FinishFlow;", "Lcom/onfido/workflow/internal/ui/model/OneOffUIEvent$NavigateToDocumentFlow;", "Lcom/onfido/workflow/internal/ui/model/OneOffUIEvent$NavigateToFaceUploadFlow;", "Lcom/onfido/workflow/internal/ui/model/OneOffUIEvent$NavigateToFaceVideoFlow;", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public abstract class OneOffUIEvent {
    public /* synthetic */ OneOffUIEvent(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* compiled from: OneOffUIEvent.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/workflow/internal/ui/model/OneOffUIEvent$NavigateToFaceUploadFlow;", "Lcom/onfido/workflow/internal/ui/model/OneOffUIEvent;", "()V", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class NavigateToFaceUploadFlow extends OneOffUIEvent {
        public static final NavigateToFaceUploadFlow INSTANCE = new NavigateToFaceUploadFlow();

        private NavigateToFaceUploadFlow() {
            super(null);
        }
    }

    private OneOffUIEvent() {
    }

    /* compiled from: OneOffUIEvent.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/workflow/internal/ui/model/OneOffUIEvent$NavigateToFaceVideoFlow;", "Lcom/onfido/workflow/internal/ui/model/OneOffUIEvent;", "()V", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class NavigateToFaceVideoFlow extends OneOffUIEvent {
        public static final NavigateToFaceVideoFlow INSTANCE = new NavigateToFaceVideoFlow();

        private NavigateToFaceVideoFlow() {
            super(null);
        }
    }

    /* compiled from: OneOffUIEvent.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0007HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\tHÆ\u0003J\t\u0010\u001b\u001a\u00020\u000bHÆ\u0003J?\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\n\u001a\u00020\u000bHÆ\u0001J\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 HÖ\u0003J\t\u0010!\u001a\u00020\"HÖ\u0001J\t\u0010#\u001a\u00020$HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006%"}, d2 = {"Lcom/onfido/workflow/internal/ui/model/OneOffUIEvent$NavigateToDocumentFlow;", "Lcom/onfido/workflow/internal/ui/model/OneOffUIEvent;", "documentType", "Lcom/onfido/android/sdk/capture/DocumentType;", RemoteConfigConstants.RequestFieldKey.COUNTRY_CODE, "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "docSide", "Lcom/onfido/api/client/data/DocSide;", "documentFormat", "Lcom/onfido/android/sdk/capture/DocumentFormat;", "nfcArguments", "Lcom/onfido/android/sdk/capture/flow/NfcArguments;", "(Lcom/onfido/android/sdk/capture/DocumentType;Lcom/onfido/android/sdk/capture/utils/CountryCode;Lcom/onfido/api/client/data/DocSide;Lcom/onfido/android/sdk/capture/DocumentFormat;Lcom/onfido/android/sdk/capture/flow/NfcArguments;)V", "getCountryCode", "()Lcom/onfido/android/sdk/capture/utils/CountryCode;", "getDocSide", "()Lcom/onfido/api/client/data/DocSide;", "getDocumentFormat", "()Lcom/onfido/android/sdk/capture/DocumentFormat;", "getDocumentType", "()Lcom/onfido/android/sdk/capture/DocumentType;", "getNfcArguments", "()Lcom/onfido/android/sdk/capture/flow/NfcArguments;", "component1", "component2", "component3", "component4", "component5", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class NavigateToDocumentFlow extends OneOffUIEvent {
        private final CountryCode countryCode;
        private final DocSide docSide;
        private final DocumentFormat documentFormat;
        private final DocumentType documentType;
        private final NfcArguments nfcArguments;

        public static /* synthetic */ NavigateToDocumentFlow copy$default(NavigateToDocumentFlow navigateToDocumentFlow, DocumentType documentType, CountryCode countryCode, DocSide docSide, DocumentFormat documentFormat, NfcArguments nfcArguments, int i, Object obj) {
            if ((i & 1) != 0) {
                documentType = navigateToDocumentFlow.documentType;
            }
            if ((i & 2) != 0) {
                countryCode = navigateToDocumentFlow.countryCode;
            }
            CountryCode countryCode2 = countryCode;
            if ((i & 4) != 0) {
                docSide = navigateToDocumentFlow.docSide;
            }
            DocSide docSide2 = docSide;
            if ((i & 8) != 0) {
                documentFormat = navigateToDocumentFlow.documentFormat;
            }
            DocumentFormat documentFormat2 = documentFormat;
            if ((i & 16) != 0) {
                nfcArguments = navigateToDocumentFlow.nfcArguments;
            }
            return navigateToDocumentFlow.copy(documentType, countryCode2, docSide2, documentFormat2, nfcArguments);
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
        public final DocSide getDocSide() {
            return this.docSide;
        }

        /* renamed from: component4, reason: from getter */
        public final DocumentFormat getDocumentFormat() {
            return this.documentFormat;
        }

        /* renamed from: component5, reason: from getter */
        public final NfcArguments getNfcArguments() {
            return this.nfcArguments;
        }

        public final NavigateToDocumentFlow copy(DocumentType documentType, CountryCode countryCode, DocSide docSide, DocumentFormat documentFormat, NfcArguments nfcArguments) {
            Intrinsics.checkNotNullParameter(documentType, "documentType");
            Intrinsics.checkNotNullParameter(docSide, "docSide");
            Intrinsics.checkNotNullParameter(nfcArguments, "nfcArguments");
            return new NavigateToDocumentFlow(documentType, countryCode, docSide, documentFormat, nfcArguments);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof NavigateToDocumentFlow)) {
                return false;
            }
            NavigateToDocumentFlow navigateToDocumentFlow = (NavigateToDocumentFlow) other;
            return this.documentType == navigateToDocumentFlow.documentType && this.countryCode == navigateToDocumentFlow.countryCode && this.docSide == navigateToDocumentFlow.docSide && this.documentFormat == navigateToDocumentFlow.documentFormat && Intrinsics.areEqual(this.nfcArguments, navigateToDocumentFlow.nfcArguments);
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

        public final NfcArguments getNfcArguments() {
            return this.nfcArguments;
        }

        public int hashCode() {
            int iHashCode = this.documentType.hashCode() * 31;
            CountryCode countryCode = this.countryCode;
            int iHashCode2 = (((iHashCode + (countryCode == null ? 0 : countryCode.hashCode())) * 31) + this.docSide.hashCode()) * 31;
            DocumentFormat documentFormat = this.documentFormat;
            return ((iHashCode2 + (documentFormat != null ? documentFormat.hashCode() : 0)) * 31) + this.nfcArguments.hashCode();
        }

        public String toString() {
            return "NavigateToDocumentFlow(documentType=" + this.documentType + ", countryCode=" + this.countryCode + ", docSide=" + this.docSide + ", documentFormat=" + this.documentFormat + ", nfcArguments=" + this.nfcArguments + ")";
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public NavigateToDocumentFlow(DocumentType documentType, CountryCode countryCode, DocSide docSide, DocumentFormat documentFormat, NfcArguments nfcArguments) {
            super(null);
            Intrinsics.checkNotNullParameter(documentType, "documentType");
            Intrinsics.checkNotNullParameter(docSide, "docSide");
            Intrinsics.checkNotNullParameter(nfcArguments, "nfcArguments");
            this.documentType = documentType;
            this.countryCode = countryCode;
            this.docSide = docSide;
            this.documentFormat = documentFormat;
            this.nfcArguments = nfcArguments;
        }
    }

    /* compiled from: OneOffUIEvent.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0007\b\tB\u0007\b\u0004¢\u0006\u0002\u0010\u0002R\u0012\u0010\u0003\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0003\n\u000b\f¨\u0006\r"}, d2 = {"Lcom/onfido/workflow/internal/ui/model/OneOffUIEvent$FinishFlow;", "Lcom/onfido/workflow/internal/ui/model/OneOffUIEvent;", "()V", "resultCode", "", "getResultCode", "()I", "Error", "Exit", "Success", "Lcom/onfido/workflow/internal/ui/model/OneOffUIEvent$FinishFlow$Error;", "Lcom/onfido/workflow/internal/ui/model/OneOffUIEvent$FinishFlow$Exit;", "Lcom/onfido/workflow/internal/ui/model/OneOffUIEvent$FinishFlow$Success;", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static abstract class FinishFlow extends OneOffUIEvent {
        public /* synthetic */ FinishFlow(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public abstract int getResultCode();

        private FinishFlow() {
            super(null);
        }

        /* compiled from: OneOffUIEvent.kt */
        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u0003HÖ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/onfido/workflow/internal/ui/model/OneOffUIEvent$FinishFlow$Success;", "Lcom/onfido/workflow/internal/ui/model/OneOffUIEvent$FinishFlow;", "resultCode", "", "(I)V", "getResultCode", "()I", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "toString", "", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class Success extends FinishFlow {
            private final int resultCode;

            public static /* synthetic */ Success copy$default(Success success, int i, int i2, Object obj) {
                if ((i2 & 1) != 0) {
                    i = success.resultCode;
                }
                return success.copy(i);
            }

            /* renamed from: component1, reason: from getter */
            public final int getResultCode() {
                return this.resultCode;
            }

            public final Success copy(int resultCode) {
                return new Success(resultCode);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof Success) && this.resultCode == ((Success) other).resultCode;
            }

            @Override // com.onfido.workflow.internal.ui.model.OneOffUIEvent.FinishFlow
            public int getResultCode() {
                return this.resultCode;
            }

            public int hashCode() {
                return Integer.hashCode(this.resultCode);
            }

            public String toString() {
                return "Success(resultCode=" + this.resultCode + ")";
            }

            public Success(int i) {
                super(null);
                this.resultCode = i;
            }
        }

        /* compiled from: OneOffUIEvent.kt */
        @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÖ\u0003J\t\u0010\u0011\u001a\u00020\bHÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\bX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lcom/onfido/workflow/internal/ui/model/OneOffUIEvent$FinishFlow$Error;", "Lcom/onfido/workflow/internal/ui/model/OneOffUIEvent$FinishFlow;", SentryEvent.JsonKeys.EXCEPTION, "Lcom/onfido/workflow/OnfidoWorkflow$WorkflowException;", "(Lcom/onfido/workflow/OnfidoWorkflow$WorkflowException;)V", "getException", "()Lcom/onfido/workflow/OnfidoWorkflow$WorkflowException;", "resultCode", "", "getResultCode", "()I", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "toString", "", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class Error extends FinishFlow {
            private final OnfidoWorkflow.WorkflowException exception;
            private final int resultCode;

            public static /* synthetic */ Error copy$default(Error error, OnfidoWorkflow.WorkflowException workflowException, int i, Object obj) {
                if ((i & 1) != 0) {
                    workflowException = error.exception;
                }
                return error.copy(workflowException);
            }

            /* renamed from: component1, reason: from getter */
            public final OnfidoWorkflow.WorkflowException getException() {
                return this.exception;
            }

            public final Error copy(OnfidoWorkflow.WorkflowException exception) {
                Intrinsics.checkNotNullParameter(exception, "exception");
                return new Error(exception);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof Error) && Intrinsics.areEqual(this.exception, ((Error) other).exception);
            }

            public final OnfidoWorkflow.WorkflowException getException() {
                return this.exception;
            }

            @Override // com.onfido.workflow.internal.ui.model.OneOffUIEvent.FinishFlow
            public int getResultCode() {
                return this.resultCode;
            }

            public int hashCode() {
                return this.exception.hashCode();
            }

            public String toString() {
                return "Error(exception=" + this.exception + ")";
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public Error(OnfidoWorkflow.WorkflowException exception) {
                super(null);
                Intrinsics.checkNotNullParameter(exception, "exception");
                this.exception = exception;
                this.resultCode = -2;
            }
        }

        /* compiled from: OneOffUIEvent.kt */
        @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001:\u0001\u0014B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÖ\u0003J\t\u0010\u0011\u001a\u00020\bHÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\bX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Lcom/onfido/workflow/internal/ui/model/OneOffUIEvent$FinishFlow$Exit;", "Lcom/onfido/workflow/internal/ui/model/OneOffUIEvent$FinishFlow;", "reason", "Lcom/onfido/workflow/internal/ui/model/OneOffUIEvent$FinishFlow$Exit$Reason;", "(Lcom/onfido/workflow/internal/ui/model/OneOffUIEvent$FinishFlow$Exit$Reason;)V", "getReason", "()Lcom/onfido/workflow/internal/ui/model/OneOffUIEvent$FinishFlow$Exit$Reason;", "resultCode", "", "getResultCode", "()I", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "toString", "", "Reason", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class Exit extends FinishFlow {
            private final Reason reason;
            private final int resultCode;

            public static /* synthetic */ Exit copy$default(Exit exit, Reason reason, int i, Object obj) {
                if ((i & 1) != 0) {
                    reason = exit.reason;
                }
                return exit.copy(reason);
            }

            /* renamed from: component1, reason: from getter */
            public final Reason getReason() {
                return this.reason;
            }

            public final Exit copy(Reason reason) {
                Intrinsics.checkNotNullParameter(reason, "reason");
                return new Exit(reason);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof Exit) && this.reason == ((Exit) other).reason;
            }

            public final Reason getReason() {
                return this.reason;
            }

            @Override // com.onfido.workflow.internal.ui.model.OneOffUIEvent.FinishFlow
            public int getResultCode() {
                return this.resultCode;
            }

            public int hashCode() {
                return this.reason.hashCode();
            }

            public String toString() {
                return "Exit(reason=" + this.reason + ")";
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public Exit(Reason reason) {
                super(null);
                Intrinsics.checkNotNullParameter(reason, "reason");
                this.reason = reason;
                this.resultCode = -2;
            }

            /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
            /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
            /* compiled from: OneOffUIEvent.kt */
            @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lcom/onfido/workflow/internal/ui/model/OneOffUIEvent$FinishFlow$Exit$Reason;", "", "(Ljava/lang/String;I)V", "EXIT_NFC_REQUIRED_FLOW", "USER_EXIT_FLOW", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
            public static final class Reason {
                private static final /* synthetic */ EnumEntries $ENTRIES;
                private static final /* synthetic */ Reason[] $VALUES;
                public static final Reason EXIT_NFC_REQUIRED_FLOW = new Reason("EXIT_NFC_REQUIRED_FLOW", 0);
                public static final Reason USER_EXIT_FLOW = new Reason("USER_EXIT_FLOW", 1);

                private static final /* synthetic */ Reason[] $values() {
                    return new Reason[]{EXIT_NFC_REQUIRED_FLOW, USER_EXIT_FLOW};
                }

                public static EnumEntries<Reason> getEntries() {
                    return $ENTRIES;
                }

                public static Reason valueOf(String str) {
                    return (Reason) Enum.valueOf(Reason.class, str);
                }

                public static Reason[] values() {
                    return (Reason[]) $VALUES.clone();
                }

                private Reason(String str, int i) {
                }

                static {
                    Reason[] reasonArr$values = $values();
                    $VALUES = reasonArr$values;
                    $ENTRIES = EnumEntriesKt.enumEntries(reasonArr$values);
                }
            }
        }
    }

    /* compiled from: OneOffUIEvent.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/workflow/internal/ui/model/OneOffUIEvent$DoNothing;", "Lcom/onfido/workflow/internal/ui/model/OneOffUIEvent;", "()V", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class DoNothing extends OneOffUIEvent {
        public static final DoNothing INSTANCE = new DoNothing();

        private DoNothing() {
            super(null);
        }
    }
}
