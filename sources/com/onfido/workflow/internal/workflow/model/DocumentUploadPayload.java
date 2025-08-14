package com.onfido.workflow.internal.workflow.model;

import com.onfido.android.sdk.capture.ui.nfc.model.NfcProperties;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DocumentUploadPayload.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bp\u0018\u00002\u00020\u0001:\u0002\b\tR\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005\u0082\u0001\u0002\n\u000b¨\u0006\f"}, d2 = {"Lcom/onfido/workflow/internal/workflow/model/DocumentUploadPayload;", "", "documentId", "", "getDocumentId", "()Ljava/lang/String;", "documentVideoId", "getDocumentVideoId", "ScannedNfcPayload", "UploadedArtifactPayload", "Lcom/onfido/workflow/internal/workflow/model/DocumentUploadPayload$ScannedNfcPayload;", "Lcom/onfido/workflow/internal/workflow/model/DocumentUploadPayload$UploadedArtifactPayload;", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public interface DocumentUploadPayload {
    String getDocumentId();

    String getDocumentVideoId();

    /* compiled from: DocumentUploadPayload.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\t"}, d2 = {"Lcom/onfido/workflow/internal/workflow/model/DocumentUploadPayload$UploadedArtifactPayload;", "Lcom/onfido/workflow/internal/workflow/model/DocumentUploadPayload;", "documentId", "", "documentVideoId", "(Ljava/lang/String;Ljava/lang/String;)V", "getDocumentId", "()Ljava/lang/String;", "getDocumentVideoId", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class UploadedArtifactPayload implements DocumentUploadPayload {
        private final String documentId;
        private final String documentVideoId;

        @Override // com.onfido.workflow.internal.workflow.model.DocumentUploadPayload
        public String getDocumentId() {
            return this.documentId;
        }

        @Override // com.onfido.workflow.internal.workflow.model.DocumentUploadPayload
        public String getDocumentVideoId() {
            return this.documentVideoId;
        }

        public UploadedArtifactPayload(String documentId, String documentVideoId) {
            Intrinsics.checkNotNullParameter(documentId, "documentId");
            Intrinsics.checkNotNullParameter(documentVideoId, "documentVideoId");
            this.documentId = documentId;
            this.documentVideoId = documentVideoId;
        }
    }

    /* compiled from: DocumentUploadPayload.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lcom/onfido/workflow/internal/workflow/model/DocumentUploadPayload$ScannedNfcPayload;", "Lcom/onfido/workflow/internal/workflow/model/DocumentUploadPayload;", "documentId", "", "documentVideoId", "nfcProperties", "Lcom/onfido/android/sdk/capture/ui/nfc/model/NfcProperties;", "nfcSupported", "", "(Ljava/lang/String;Ljava/lang/String;Lcom/onfido/android/sdk/capture/ui/nfc/model/NfcProperties;Z)V", "getDocumentId", "()Ljava/lang/String;", "getDocumentVideoId", "getNfcProperties", "()Lcom/onfido/android/sdk/capture/ui/nfc/model/NfcProperties;", "getNfcSupported", "()Z", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class ScannedNfcPayload implements DocumentUploadPayload {
        private final String documentId;
        private final String documentVideoId;
        private final NfcProperties nfcProperties;
        private final boolean nfcSupported;

        @Override // com.onfido.workflow.internal.workflow.model.DocumentUploadPayload
        public String getDocumentId() {
            return this.documentId;
        }

        @Override // com.onfido.workflow.internal.workflow.model.DocumentUploadPayload
        public String getDocumentVideoId() {
            return this.documentVideoId;
        }

        public final NfcProperties getNfcProperties() {
            return this.nfcProperties;
        }

        public final boolean getNfcSupported() {
            return this.nfcSupported;
        }

        public ScannedNfcPayload(String documentId, String documentVideoId, NfcProperties nfcProperties, boolean z) {
            Intrinsics.checkNotNullParameter(documentId, "documentId");
            Intrinsics.checkNotNullParameter(documentVideoId, "documentVideoId");
            Intrinsics.checkNotNullParameter(nfcProperties, "nfcProperties");
            this.documentId = documentId;
            this.documentVideoId = documentVideoId;
            this.nfcProperties = nfcProperties;
            this.nfcSupported = z;
        }
    }
}
