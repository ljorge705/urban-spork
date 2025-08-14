package com.onfido.android.sdk.capture.ui.nfc.scan;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatButton;
import com.clevertap.android.sdk.Constants;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.databinding.OnfidoViewNfcScanningBinding;
import com.onfido.android.sdk.capture.internal.service.AnnouncementService;
import com.onfido.android.sdk.capture.ui.camera.view.AutoPlayVideoView;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u0000 \"2\u00020\u0001:\u0004\"#$%B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u0006\u0010\u0016\u001a\u00020\u0017J\u0006\u0010\u0018\u001a\u00020\u0017J\u0006\u0010\u0019\u001a\u00020\u0017J\u0006\u0010\u001a\u001a\u00020\u0017J\u000e\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u0012J\u0006\u0010\u001d\u001a\u00020\u0017J\u0010\u0010\u001e\u001a\u00020\u00172\u0006\u0010\u001f\u001a\u00020 H\u0002J\u0010\u0010!\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u0012H\u0002R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/nfc/scan/NfcScanBottomDialog;", "", "context", "Landroid/content/Context;", "documentType", "Lcom/onfido/android/sdk/capture/DocumentType;", "nfcScanActionListener", "Lcom/onfido/android/sdk/capture/ui/nfc/scan/NfcScanBottomDialog$NfcScanBottomActions;", "announcementService", "Lcom/onfido/android/sdk/capture/internal/service/AnnouncementService;", "animationUrl", "", "(Landroid/content/Context;Lcom/onfido/android/sdk/capture/DocumentType;Lcom/onfido/android/sdk/capture/ui/nfc/scan/NfcScanBottomDialog$NfcScanBottomActions;Lcom/onfido/android/sdk/capture/internal/service/AnnouncementService;Ljava/lang/String;)V", "binding", "Lcom/onfido/android/sdk/capture/databinding/OnfidoViewNfcScanningBinding;", "bottomDialog", "Lcom/google/android/material/bottomsheet/BottomSheetDialog;", "indicatorColorDeterminate", "", "indicatorColorIndeterminate", "trackColorDeterminate", "trackColorIndeterminate", "dismissDialog", "", "showConnectionLost", "showNfcScanReadyView", "showNfcScanRetry", "showNfcScanningView", "progress", "showNfcSuccessView", "updateBottomState", "state", "Lcom/onfido/android/sdk/capture/ui/nfc/scan/NfcScanBottomDialog$State;", "updateScanningProgress", "Companion", "NfcScanBottomActions", "State", "ViewState", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class NfcScanBottomDialog {
    private static final Companion Companion = new Companion(null);

    @Deprecated
    public static final long PROGRESS_ANIMATION_DURATION = 300;

    @Deprecated
    public static final String PROGRESS_BAR_PROGRESS_PROPERTY_NAME = "progress";
    private final String animationUrl;
    private final AnnouncementService announcementService;
    private final OnfidoViewNfcScanningBinding binding;
    private final BottomSheetDialog bottomDialog;
    private final DocumentType documentType;
    private final int indicatorColorDeterminate;
    private final int indicatorColorIndeterminate;
    private final NfcScanBottomActions nfcScanActionListener;
    private final int trackColorDeterminate;
    private final int trackColorIndeterminate;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/nfc/scan/NfcScanBottomDialog$Companion;", "", "()V", "PROGRESS_ANIMATION_DURATION", "", "PROGRESS_BAR_PROGRESS_PROPERTY_NAME", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/nfc/scan/NfcScanBottomDialog$NfcScanBottomActions;", "", "onNfcDialogDismissedByDialog", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface NfcScanBottomActions {
        void onNfcDialogDismissedByDialog();
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b2\u0018\u00002\u00020\u0001:\u0005\u0007\b\t\n\u000bB\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0005\f\r\u000e\u000f\u0010¨\u0006\u0011"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/nfc/scan/NfcScanBottomDialog$State;", "", "viewState", "Lcom/onfido/android/sdk/capture/ui/nfc/scan/NfcScanBottomDialog$ViewState;", "(Lcom/onfido/android/sdk/capture/ui/nfc/scan/NfcScanBottomDialog$ViewState;)V", "getViewState", "()Lcom/onfido/android/sdk/capture/ui/nfc/scan/NfcScanBottomDialog$ViewState;", "ConnectionLost", "Ready", "Retry", "Scanning", "Success", "Lcom/onfido/android/sdk/capture/ui/nfc/scan/NfcScanBottomDialog$State$ConnectionLost;", "Lcom/onfido/android/sdk/capture/ui/nfc/scan/NfcScanBottomDialog$State$Ready;", "Lcom/onfido/android/sdk/capture/ui/nfc/scan/NfcScanBottomDialog$State$Retry;", "Lcom/onfido/android/sdk/capture/ui/nfc/scan/NfcScanBottomDialog$State$Scanning;", "Lcom/onfido/android/sdk/capture/ui/nfc/scan/NfcScanBottomDialog$State$Success;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static abstract class State {
        private final ViewState viewState;

        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/nfc/scan/NfcScanBottomDialog$State$ConnectionLost;", "Lcom/onfido/android/sdk/capture/ui/nfc/scan/NfcScanBottomDialog$State;", "documentType", "Lcom/onfido/android/sdk/capture/DocumentType;", "(Lcom/onfido/android/sdk/capture/DocumentType;)V", "getDocumentType", "()Lcom/onfido/android/sdk/capture/DocumentType;", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class ConnectionLost extends State {
            private final DocumentType documentType;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public ConnectionLost(DocumentType documentType) {
                super(new ViewState(R.string.onfido_nfc_scan_doc_realign_title, Integer.valueOf(documentType == DocumentType.PASSPORT ? R.string.onfido_nfc_capture_scan_doc_realign_body : R.string.onfido_nfc_capture_scan_card_realign_body), 0, false, false, false, false, true, false, 108, null), null);
                Intrinsics.checkNotNullParameter(documentType, "documentType");
                this.documentType = documentType;
            }

            public static /* synthetic */ ConnectionLost copy$default(ConnectionLost connectionLost, DocumentType documentType, int i, Object obj) {
                if ((i & 1) != 0) {
                    documentType = connectionLost.documentType;
                }
                return connectionLost.copy(documentType);
            }

            /* renamed from: component1, reason: from getter */
            public final DocumentType getDocumentType() {
                return this.documentType;
            }

            public final ConnectionLost copy(DocumentType documentType) {
                Intrinsics.checkNotNullParameter(documentType, "documentType");
                return new ConnectionLost(documentType);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof ConnectionLost) && this.documentType == ((ConnectionLost) other).documentType;
            }

            public final DocumentType getDocumentType() {
                return this.documentType;
            }

            public int hashCode() {
                return this.documentType.hashCode();
            }

            public String toString() {
                return "ConnectionLost(documentType=" + this.documentType + ')';
            }
        }

        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/nfc/scan/NfcScanBottomDialog$State$Ready;", "Lcom/onfido/android/sdk/capture/ui/nfc/scan/NfcScanBottomDialog$State;", "documentType", "Lcom/onfido/android/sdk/capture/DocumentType;", "(Lcom/onfido/android/sdk/capture/DocumentType;)V", "getDocumentType", "()Lcom/onfido/android/sdk/capture/DocumentType;", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class Ready extends State {
            private final DocumentType documentType;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public Ready(DocumentType documentType) {
                super(new ViewState(R.string.onfido_nfc_sheet_ready_subtitle, Integer.valueOf(documentType == DocumentType.PASSPORT ? R.string.onfido_nfc_capture_scan_intro_passport_scan_guide_android_2 : R.string.onfido_nfc_capture_scan_intro_card_scan_guide_android), 0, false, false, false, false, true, false, 380, null), null);
                Intrinsics.checkNotNullParameter(documentType, "documentType");
                this.documentType = documentType;
            }

            public static /* synthetic */ Ready copy$default(Ready ready, DocumentType documentType, int i, Object obj) {
                if ((i & 1) != 0) {
                    documentType = ready.documentType;
                }
                return ready.copy(documentType);
            }

            /* renamed from: component1, reason: from getter */
            public final DocumentType getDocumentType() {
                return this.documentType;
            }

            public final Ready copy(DocumentType documentType) {
                Intrinsics.checkNotNullParameter(documentType, "documentType");
                return new Ready(documentType);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof Ready) && this.documentType == ((Ready) other).documentType;
            }

            public final DocumentType getDocumentType() {
                return this.documentType;
            }

            public int hashCode() {
                return this.documentType.hashCode();
            }

            public String toString() {
                return "Ready(documentType=" + this.documentType + ')';
            }
        }

        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/nfc/scan/NfcScanBottomDialog$State$Retry;", "Lcom/onfido/android/sdk/capture/ui/nfc/scan/NfcScanBottomDialog$State;", "documentType", "Lcom/onfido/android/sdk/capture/DocumentType;", "(Lcom/onfido/android/sdk/capture/DocumentType;)V", "getDocumentType", "()Lcom/onfido/android/sdk/capture/DocumentType;", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class Retry extends State {
            private final DocumentType documentType;

            /* JADX WARN: Illegal instructions before constructor call */
            public Retry(DocumentType documentType) {
                Intrinsics.checkNotNullParameter(documentType, "documentType");
                DocumentType documentType2 = DocumentType.PASSPORT;
                super(new ViewState(documentType == documentType2 ? R.string.onfido_nfc_intro_sheet_fail_subtitle_passport : R.string.onfido_nfc_intro_sheet_fail_subtitle_card, Integer.valueOf(documentType == documentType2 ? R.string.onfido_nfc_intro_sheet_fail_instructions_passport : R.string.onfido_nfc_intro_sheet_fail_instructions_card), 0, false, false, false, false, true, false, 364, null), null);
                this.documentType = documentType;
            }

            public static /* synthetic */ Retry copy$default(Retry retry, DocumentType documentType, int i, Object obj) {
                if ((i & 1) != 0) {
                    documentType = retry.documentType;
                }
                return retry.copy(documentType);
            }

            /* renamed from: component1, reason: from getter */
            public final DocumentType getDocumentType() {
                return this.documentType;
            }

            public final Retry copy(DocumentType documentType) {
                Intrinsics.checkNotNullParameter(documentType, "documentType");
                return new Retry(documentType);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof Retry) && this.documentType == ((Retry) other).documentType;
            }

            public final DocumentType getDocumentType() {
                return this.documentType;
            }

            public int hashCode() {
                return this.documentType.hashCode();
            }

            public String toString() {
                return "Retry(documentType=" + this.documentType + ')';
            }
        }

        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u0003HÖ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/nfc/scan/NfcScanBottomDialog$State$Scanning;", "Lcom/onfido/android/sdk/capture/ui/nfc/scan/NfcScanBottomDialog$State;", "progress", "", "(I)V", "getProgress", "()I", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class Scanning extends State {
            private final int progress;

            public Scanning(int i) {
                super(new ViewState(R.string.onfido_nfc_intro_sheet_scanning_subtitle, Integer.valueOf(R.string.onfido_nfc_sheet_scanning_instruction), 0, false, true, false, false, false, true, 108, null), null);
                this.progress = i;
            }

            public static /* synthetic */ Scanning copy$default(Scanning scanning, int i, int i2, Object obj) {
                if ((i2 & 1) != 0) {
                    i = scanning.progress;
                }
                return scanning.copy(i);
            }

            /* renamed from: component1, reason: from getter */
            public final int getProgress() {
                return this.progress;
            }

            public final Scanning copy(int progress) {
                return new Scanning(progress);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof Scanning) && this.progress == ((Scanning) other).progress;
            }

            public final int getProgress() {
                return this.progress;
            }

            public int hashCode() {
                return Integer.hashCode(this.progress);
            }

            public String toString() {
                return "Scanning(progress=" + this.progress + ')';
            }
        }

        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/nfc/scan/NfcScanBottomDialog$State$Success;", "Lcom/onfido/android/sdk/capture/ui/nfc/scan/NfcScanBottomDialog$State;", "documentType", "Lcom/onfido/android/sdk/capture/DocumentType;", "(Lcom/onfido/android/sdk/capture/DocumentType;)V", "getDocumentType", "()Lcom/onfido/android/sdk/capture/DocumentType;", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class Success extends State {
            private final DocumentType documentType;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public Success(DocumentType documentType) {
                super(new ViewState(R.string.onfido_nfc_sheet_success_subtitle, null, 0, false, true, false, true, false, false, 6, null), null);
                Intrinsics.checkNotNullParameter(documentType, "documentType");
                this.documentType = documentType;
            }

            public static /* synthetic */ Success copy$default(Success success, DocumentType documentType, int i, Object obj) {
                if ((i & 1) != 0) {
                    documentType = success.documentType;
                }
                return success.copy(documentType);
            }

            /* renamed from: component1, reason: from getter */
            public final DocumentType getDocumentType() {
                return this.documentType;
            }

            public final Success copy(DocumentType documentType) {
                Intrinsics.checkNotNullParameter(documentType, "documentType");
                return new Success(documentType);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof Success) && this.documentType == ((Success) other).documentType;
            }

            public final DocumentType getDocumentType() {
                return this.documentType;
            }

            public int hashCode() {
                return this.documentType.hashCode();
            }

            public String toString() {
                return "Success(documentType=" + this.documentType + ')';
            }
        }

        private State(ViewState viewState) {
            this.viewState = viewState;
        }

        public final ViewState getViewState() {
            return this.viewState;
        }

        public /* synthetic */ State(ViewState viewState, DefaultConstructorMarker defaultConstructorMarker) {
            this(viewState);
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u001c\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001Ba\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0003\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\u0007\u0012\b\b\u0002\u0010\n\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\f\u001a\u00020\u0007¢\u0006\u0002\u0010\rJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0012J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0007HÆ\u0003Jj\u0010\u001e\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\u00072\b\b\u0002\u0010\f\u001a\u00020\u0007HÆ\u0001¢\u0006\u0002\u0010\u001fJ\u0013\u0010 \u001a\u00020\u00072\b\u0010!\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\"\u001a\u00020\u0003HÖ\u0001J\t\u0010#\u001a\u00020$HÖ\u0001R\u0011\u0010\u000b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u000eR\u0011\u0010\f\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000eR\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u000eR\u0011\u0010\t\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u000eR\u0011\u0010\n\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010¨\u0006%"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/nfc/scan/NfcScanBottomDialog$ViewState;", "", "titleResId", "", "subtitleResId", "secondaryActionResId", "isProgressBarVisible", "", "isProgressBarHorizontalVisible", "isSecondaryActionVisible", "isSuccessIconVisible", "isInstructionVisible", "isNfcIconVisible", "(ILjava/lang/Integer;IZZZZZZ)V", "()Z", "getSecondaryActionResId", "()I", "getSubtitleResId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getTitleResId", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", Constants.COPY_TYPE, "(ILjava/lang/Integer;IZZZZZZ)Lcom/onfido/android/sdk/capture/ui/nfc/scan/NfcScanBottomDialog$ViewState;", "equals", "other", "hashCode", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final /* data */ class ViewState {
        private final boolean isInstructionVisible;
        private final boolean isNfcIconVisible;
        private final boolean isProgressBarHorizontalVisible;
        private final boolean isProgressBarVisible;
        private final boolean isSecondaryActionVisible;
        private final boolean isSuccessIconVisible;
        private final int secondaryActionResId;
        private final Integer subtitleResId;
        private final int titleResId;

        public ViewState(int i, Integer num, int i2, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6) {
            this.titleResId = i;
            this.subtitleResId = num;
            this.secondaryActionResId = i2;
            this.isProgressBarVisible = z;
            this.isProgressBarHorizontalVisible = z2;
            this.isSecondaryActionVisible = z3;
            this.isSuccessIconVisible = z4;
            this.isInstructionVisible = z5;
            this.isNfcIconVisible = z6;
        }

        /* renamed from: component1, reason: from getter */
        public final int getTitleResId() {
            return this.titleResId;
        }

        /* renamed from: component2, reason: from getter */
        public final Integer getSubtitleResId() {
            return this.subtitleResId;
        }

        /* renamed from: component3, reason: from getter */
        public final int getSecondaryActionResId() {
            return this.secondaryActionResId;
        }

        /* renamed from: component4, reason: from getter */
        public final boolean getIsProgressBarVisible() {
            return this.isProgressBarVisible;
        }

        /* renamed from: component5, reason: from getter */
        public final boolean getIsProgressBarHorizontalVisible() {
            return this.isProgressBarHorizontalVisible;
        }

        /* renamed from: component6, reason: from getter */
        public final boolean getIsSecondaryActionVisible() {
            return this.isSecondaryActionVisible;
        }

        /* renamed from: component7, reason: from getter */
        public final boolean getIsSuccessIconVisible() {
            return this.isSuccessIconVisible;
        }

        /* renamed from: component8, reason: from getter */
        public final boolean getIsInstructionVisible() {
            return this.isInstructionVisible;
        }

        /* renamed from: component9, reason: from getter */
        public final boolean getIsNfcIconVisible() {
            return this.isNfcIconVisible;
        }

        public final ViewState copy(int titleResId, Integer subtitleResId, int secondaryActionResId, boolean isProgressBarVisible, boolean isProgressBarHorizontalVisible, boolean isSecondaryActionVisible, boolean isSuccessIconVisible, boolean isInstructionVisible, boolean isNfcIconVisible) {
            return new ViewState(titleResId, subtitleResId, secondaryActionResId, isProgressBarVisible, isProgressBarHorizontalVisible, isSecondaryActionVisible, isSuccessIconVisible, isInstructionVisible, isNfcIconVisible);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ViewState)) {
                return false;
            }
            ViewState viewState = (ViewState) other;
            return this.titleResId == viewState.titleResId && Intrinsics.areEqual(this.subtitleResId, viewState.subtitleResId) && this.secondaryActionResId == viewState.secondaryActionResId && this.isProgressBarVisible == viewState.isProgressBarVisible && this.isProgressBarHorizontalVisible == viewState.isProgressBarHorizontalVisible && this.isSecondaryActionVisible == viewState.isSecondaryActionVisible && this.isSuccessIconVisible == viewState.isSuccessIconVisible && this.isInstructionVisible == viewState.isInstructionVisible && this.isNfcIconVisible == viewState.isNfcIconVisible;
        }

        public final int getSecondaryActionResId() {
            return this.secondaryActionResId;
        }

        public final Integer getSubtitleResId() {
            return this.subtitleResId;
        }

        public final int getTitleResId() {
            return this.titleResId;
        }

        public int hashCode() {
            int iHashCode = Integer.hashCode(this.titleResId) * 31;
            Integer num = this.subtitleResId;
            return ((((((((((((((iHashCode + (num == null ? 0 : num.hashCode())) * 31) + Integer.hashCode(this.secondaryActionResId)) * 31) + Boolean.hashCode(this.isProgressBarVisible)) * 31) + Boolean.hashCode(this.isProgressBarHorizontalVisible)) * 31) + Boolean.hashCode(this.isSecondaryActionVisible)) * 31) + Boolean.hashCode(this.isSuccessIconVisible)) * 31) + Boolean.hashCode(this.isInstructionVisible)) * 31) + Boolean.hashCode(this.isNfcIconVisible);
        }

        public final boolean isInstructionVisible() {
            return this.isInstructionVisible;
        }

        public final boolean isNfcIconVisible() {
            return this.isNfcIconVisible;
        }

        public final boolean isProgressBarHorizontalVisible() {
            return this.isProgressBarHorizontalVisible;
        }

        public final boolean isProgressBarVisible() {
            return this.isProgressBarVisible;
        }

        public final boolean isSecondaryActionVisible() {
            return this.isSecondaryActionVisible;
        }

        public final boolean isSuccessIconVisible() {
            return this.isSuccessIconVisible;
        }

        public String toString() {
            return "ViewState(titleResId=" + this.titleResId + ", subtitleResId=" + this.subtitleResId + ", secondaryActionResId=" + this.secondaryActionResId + ", isProgressBarVisible=" + this.isProgressBarVisible + ", isProgressBarHorizontalVisible=" + this.isProgressBarHorizontalVisible + ", isSecondaryActionVisible=" + this.isSecondaryActionVisible + ", isSuccessIconVisible=" + this.isSuccessIconVisible + ", isInstructionVisible=" + this.isInstructionVisible + ", isNfcIconVisible=" + this.isNfcIconVisible + ')';
        }

        public /* synthetic */ ViewState(int i, Integer num, int i2, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this(i, (i3 & 2) != 0 ? null : num, (i3 & 4) != 0 ? R.string.onfido_nfc_sheet_button_secondary : i2, (i3 & 8) != 0 ? false : z, (i3 & 16) != 0 ? false : z2, (i3 & 32) != 0 ? true : z3, (i3 & 64) != 0 ? false : z4, (i3 & 128) == 0 ? z5 : true, (i3 & 256) == 0 ? z6 : false);
        }
    }

    public NfcScanBottomDialog(Context context, DocumentType documentType, NfcScanBottomActions nfcScanActionListener, AnnouncementService announcementService, String animationUrl) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(documentType, "documentType");
        Intrinsics.checkNotNullParameter(nfcScanActionListener, "nfcScanActionListener");
        Intrinsics.checkNotNullParameter(announcementService, "announcementService");
        Intrinsics.checkNotNullParameter(animationUrl, "animationUrl");
        this.documentType = documentType;
        this.nfcScanActionListener = nfcScanActionListener;
        this.announcementService = announcementService;
        this.animationUrl = animationUrl;
        int i = R.attr.onfidoColorProgressIndicator;
        int i2 = R.attr.onfidoColorProgressTrack;
        int i3 = R.attr.onfidoColorProgressIndicatorIndeterminate;
        int i4 = R.attr.onfidoColorProgressTrackIndeterminate;
        int[] iArr = {i, i2, i3, i4};
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(iArr);
        Intrinsics.checkNotNullExpressionValue(typedArrayObtainStyledAttributes, "obtainStyledAttributes(...)");
        this.indicatorColorIndeterminate = typedArrayObtainStyledAttributes.getColor(ArraysKt.indexOf(iArr, i3), 0);
        this.trackColorIndeterminate = typedArrayObtainStyledAttributes.getColor(ArraysKt.indexOf(iArr, i4), 0);
        this.indicatorColorDeterminate = typedArrayObtainStyledAttributes.getColor(ArraysKt.indexOf(iArr, i), 0);
        this.trackColorDeterminate = typedArrayObtainStyledAttributes.getColor(ArraysKt.indexOf(iArr, i2), 0);
        typedArrayObtainStyledAttributes.recycle();
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
        bottomSheetDialog.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.onfido.android.sdk.capture.ui.nfc.scan.NfcScanBottomDialog$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface dialogInterface) {
                NfcScanBottomDialog.bottomDialog$lambda$1$lambda$0(this.f$0, dialogInterface);
            }
        });
        bottomSheetDialog.setCanceledOnTouchOutside(false);
        this.bottomDialog = bottomSheetDialog;
        OnfidoViewNfcScanningBinding onfidoViewNfcScanningBindingInflate = OnfidoViewNfcScanningBinding.inflate(bottomSheetDialog.getLayoutInflater(), null, false);
        bottomSheetDialog.setContentView(onfidoViewNfcScanningBindingInflate.getRoot());
        onfidoViewNfcScanningBindingInflate.secondaryAction.setOnClickListener(new View.OnClickListener() { // from class: com.onfido.android.sdk.capture.ui.nfc.scan.NfcScanBottomDialog$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                NfcScanBottomDialog.binding$lambda$3$lambda$2(this.f$0, view);
            }
        });
        Intrinsics.checkNotNullExpressionValue(onfidoViewNfcScanningBindingInflate, "apply(...)");
        this.binding = onfidoViewNfcScanningBindingInflate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void binding$lambda$3$lambda$2(NfcScanBottomDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.nfcScanActionListener.onNfcDialogDismissedByDialog();
        this$0.bottomDialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bottomDialog$lambda$1$lambda$0(NfcScanBottomDialog this$0, DialogInterface dialogInterface) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.nfcScanActionListener.onNfcDialogDismissedByDialog();
    }

    private final void updateBottomState(State state) throws Resources.NotFoundException {
        OnfidoViewNfcScanningBinding onfidoViewNfcScanningBinding = this.binding;
        TextView title = onfidoViewNfcScanningBinding.title;
        Intrinsics.checkNotNullExpressionValue(title, "title");
        ViewGroup.LayoutParams layoutParams = title.getLayoutParams();
        if (layoutParams == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        marginLayoutParams.bottomMargin = 0;
        title.setLayoutParams(marginLayoutParams);
        AutoPlayVideoView instructionVideoView = onfidoViewNfcScanningBinding.instructionVideoView;
        Intrinsics.checkNotNullExpressionValue(instructionVideoView, "instructionVideoView");
        instructionVideoView.setVisibility(state.getViewState().isInstructionVisible() ? 0 : 8);
        CircularProgressIndicator progressBarScanning = onfidoViewNfcScanningBinding.progressBarScanning;
        Intrinsics.checkNotNullExpressionValue(progressBarScanning, "progressBarScanning");
        progressBarScanning.setVisibility(state.getViewState().isProgressBarHorizontalVisible() ^ true ? 4 : 0);
        ImageView successIcon = onfidoViewNfcScanningBinding.successIcon;
        Intrinsics.checkNotNullExpressionValue(successIcon, "successIcon");
        successIcon.setVisibility(state.getViewState().isSuccessIconVisible() ? 0 : 8);
        AppCompatButton secondaryAction = onfidoViewNfcScanningBinding.secondaryAction;
        Intrinsics.checkNotNullExpressionValue(secondaryAction, "secondaryAction");
        secondaryAction.setVisibility(state.getViewState().isSecondaryActionVisible() ^ true ? 4 : 0);
        Resources resources = this.binding.getRoot().getResources();
        String string = resources.getString(state.getViewState().getTitleResId());
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        String string2 = state.getViewState().getSubtitleResId() != null ? resources.getString(state.getViewState().getSubtitleResId().intValue()) : "";
        Intrinsics.checkNotNull(string2);
        onfidoViewNfcScanningBinding.title.setText(string);
        onfidoViewNfcScanningBinding.subtitle.setText(string2);
        TextView subtitle = onfidoViewNfcScanningBinding.subtitle;
        Intrinsics.checkNotNullExpressionValue(subtitle, "subtitle");
        subtitle.setVisibility(string2.length() != 0 ? 0 : 4);
        onfidoViewNfcScanningBinding.secondaryAction.setText(state.getViewState().getSecondaryActionResId());
        ImageView nfcIcon = onfidoViewNfcScanningBinding.nfcIcon;
        Intrinsics.checkNotNullExpressionValue(nfcIcon, "nfcIcon");
        nfcIcon.setVisibility(state.getViewState().isNfcIconVisible() ? 0 : 8);
        if (state instanceof State.Success) {
            TextView title2 = onfidoViewNfcScanningBinding.title;
            Intrinsics.checkNotNullExpressionValue(title2, "title");
            ViewGroup.LayoutParams layoutParams2 = title2.getLayoutParams();
            if (layoutParams2 == null) {
                throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
            }
            ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) layoutParams2;
            marginLayoutParams2.bottomMargin = (int) this.binding.title.getContext().getResources().getDimension(R.dimen.onfido_nfc_scanning_bottom_dialog_subtitle_space_bottom);
            title2.setLayoutParams(marginLayoutParams2);
        }
        if (state instanceof State.Scanning) {
            updateScanningProgress(((State.Scanning) state).getProgress());
        }
        String str = string + ". " + string2;
        onfidoViewNfcScanningBinding.titleContainer.setContentDescription(str);
        if (!this.announcementService.isEnabled() || (state instanceof State.Ready)) {
            return;
        }
        this.announcementService.announceString(new String[]{str}, true).blockingAwait();
    }

    private final void updateScanningProgress(int progress) {
        boolean z = progress == 0;
        int i = z ? this.indicatorColorIndeterminate : this.indicatorColorDeterminate;
        int i2 = z ? this.trackColorIndeterminate : this.trackColorDeterminate;
        CircularProgressIndicator circularProgressIndicator = this.binding.progressBarScanning;
        circularProgressIndicator.setIndeterminate(z);
        circularProgressIndicator.setIndicatorColor(i);
        circularProgressIndicator.setTrackColor(i2);
        if (progress == 0) {
            this.binding.progressBarScanning.setProgress(0);
        } else {
            this.binding.progressBarScanning.setProgress(progress, true);
        }
    }

    public final void dismissDialog() {
        this.bottomDialog.dismiss();
    }

    public final void showConnectionLost() {
        updateBottomState(new State.ConnectionLost(this.documentType));
    }

    public final void showNfcScanReadyView() {
        updateBottomState(new State.Ready(this.documentType));
        this.bottomDialog.show();
        AutoPlayVideoView autoPlayVideoView = this.binding.instructionVideoView;
        autoPlayVideoView.setLoading(true);
        if (autoPlayVideoView.hasVideo()) {
            autoPlayVideoView.start();
        } else {
            autoPlayVideoView.setVideoUrl(this.animationUrl);
        }
    }

    public final void showNfcScanRetry() {
        updateBottomState(new State.Retry(this.documentType));
    }

    public final void showNfcScanningView(int progress) {
        updateBottomState(new State.Scanning(progress));
    }

    public final void showNfcSuccessView() {
        updateBottomState(new State.Success(this.documentType));
    }
}
