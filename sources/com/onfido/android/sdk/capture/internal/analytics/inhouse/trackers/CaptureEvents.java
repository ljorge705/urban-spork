package com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers;

import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.analytics.OnfidoAnalyticsEventType;
import com.onfido.android.sdk.capture.flow.CaptureStepDataBundle;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.AnalyticsEvent;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.AnalyticsFlowStep;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.AnalyticsPropertyKeys;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.CaptureFormat;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.Event;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.EventNames;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.EventType;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.PublicPropertyKey;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.UiAlerts;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.WarningOrigin;
import com.onfido.android.sdk.capture.internal.nfc.NfcNonCapabilityReason;
import com.onfido.android.sdk.capture.ui.OnfidoActivity;
import com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment;
import com.onfido.android.sdk.capture.upload.ErrorType;
import com.onfido.android.sdk.capture.utils.mlmodel.OnfidoMlModels;
import com.onfido.api.client.data.DocSide;
import io.sentry.protocol.Device;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\r\b\u0000\u0018\u0000 \u00032\u00020\u0001:\u000b\u0003\u0004\u0005\u0006\u0007\b\t\n\u000b\f\rB\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u000e"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/CaptureEvents;", "", "()V", "Companion", DocumentCaptureFragment.KEY_DOCUMENT_CAPTURE_FRAGMENT_TAG, "DocumentCaptureError", "DocumentCaptureFlowCompleted", "DocumentCaptureUploadCompleted", "DocumentCaptureUploadStarted", "DocumentConfirmation", "DocumentConfirmationError", "DocumentConfirmationWarning", "DocumentValidMRZExtracted", "OnfidoMlModelReady", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class CaptureEvents {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u0004\u0018\u00010\u0004*\u00020\u0005H\u0002¨\u0006\u0006"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/CaptureEvents$Companion;", "", "()V", "toUiAlertsOrNull", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/UiAlerts;", "Lcom/onfido/android/sdk/capture/upload/ErrorType;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final UiAlerts toUiAlertsOrNull(ErrorType errorType) {
            if (errorType instanceof ErrorType.Document) {
                return new UiAlerts(null, null, null, null, UiAlerts.UiAlertType.ERROR, null, null, null, null, 495, null);
            }
            if (errorType instanceof ErrorType.Glare) {
                return new UiAlerts(null, null, null, null, null, UiAlerts.UiAlertType.ERROR, null, null, null, 479, null);
            }
            if (errorType instanceof ErrorType.Cutoff) {
                return new UiAlerts(null, null, null, null, null, null, null, null, UiAlerts.UiAlertType.ERROR, 255, null);
            }
            if (errorType instanceof ErrorType.Blur) {
                return new UiAlerts(null, null, null, null, null, null, UiAlerts.UiAlertType.ERROR, null, null, OnfidoActivity.RESULT_EXIT_MISSING_ONFIDO_CONFIG, null);
            }
            if (errorType instanceof ErrorType.Barcode) {
                return new UiAlerts(null, null, null, null, null, null, null, UiAlerts.UiAlertType.ERROR, null, 383, null);
            }
            return null;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00052\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\n¨\u0006\u0016"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/CaptureEvents$DocumentCapture;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsEvent;", "documentData", "Lcom/onfido/android/sdk/capture/flow/CaptureStepDataBundle;", "isPortrait", "", "isAutoCaptureEnabled", "(Lcom/onfido/android/sdk/capture/flow/CaptureStepDataBundle;ZZ)V", "getDocumentData", "()Lcom/onfido/android/sdk/capture/flow/CaptureStepDataBundle;", "()Z", "component1", "component2", "component3", Constants.COPY_TYPE, "equals", "other", "", "hashCode", "", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class DocumentCapture extends AnalyticsEvent {
        private final CaptureStepDataBundle documentData;
        private final boolean isAutoCaptureEnabled;
        private final boolean isPortrait;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public DocumentCapture(CaptureStepDataBundle documentData, boolean z, boolean z2) {
            super(new Event("DOCUMENT_CAPTURE", EventType.SCREEN, null, OnfidoAnalyticsEventType.SCREEN, 4, null), MapsKt.mapOf(TuplesKt.to(AnalyticsPropertyKeys.STEP, AnalyticsFlowStep.DOCUMENT), TuplesKt.to(AnalyticsPropertyKeys.DOCUMENT_TYPE, documentData.getDocumentType()), TuplesKt.to(AnalyticsPropertyKeys.DOCUMENT_SIDE, documentData.getDocSide()), TuplesKt.to("country_code", documentData.getCountryCode()), TuplesKt.to(AnalyticsPropertyKeys.DOCUMENT_FORMAT, documentData.getDocumentFormat()), TuplesKt.to(AnalyticsPropertyKeys.IS_AUTO_CAPTURE_ENABLED, Boolean.valueOf(z2)), TuplesKt.to(AnalyticsPropertyKeys.GENERIC_DOCUMENT_TITLE, documentData.getGenericDocTitle()), TuplesKt.to(AnalyticsPropertyKeys.GENERIC_DOCUMENT_PAGES, documentData.getGenericDocPages())), MapsKt.mapOf(TuplesKt.to(PublicPropertyKey.IS_PORTRAIT, Boolean.valueOf(z))));
            Intrinsics.checkNotNullParameter(documentData, "documentData");
            this.documentData = documentData;
            this.isPortrait = z;
            this.isAutoCaptureEnabled = z2;
        }

        public static /* synthetic */ DocumentCapture copy$default(DocumentCapture documentCapture, CaptureStepDataBundle captureStepDataBundle, boolean z, boolean z2, int i, Object obj) {
            if ((i & 1) != 0) {
                captureStepDataBundle = documentCapture.documentData;
            }
            if ((i & 2) != 0) {
                z = documentCapture.isPortrait;
            }
            if ((i & 4) != 0) {
                z2 = documentCapture.isAutoCaptureEnabled;
            }
            return documentCapture.copy(captureStepDataBundle, z, z2);
        }

        /* renamed from: component1, reason: from getter */
        public final CaptureStepDataBundle getDocumentData() {
            return this.documentData;
        }

        /* renamed from: component2, reason: from getter */
        public final boolean getIsPortrait() {
            return this.isPortrait;
        }

        /* renamed from: component3, reason: from getter */
        public final boolean getIsAutoCaptureEnabled() {
            return this.isAutoCaptureEnabled;
        }

        public final DocumentCapture copy(CaptureStepDataBundle documentData, boolean isPortrait, boolean isAutoCaptureEnabled) {
            Intrinsics.checkNotNullParameter(documentData, "documentData");
            return new DocumentCapture(documentData, isPortrait, isAutoCaptureEnabled);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DocumentCapture)) {
                return false;
            }
            DocumentCapture documentCapture = (DocumentCapture) other;
            return Intrinsics.areEqual(this.documentData, documentCapture.documentData) && this.isPortrait == documentCapture.isPortrait && this.isAutoCaptureEnabled == documentCapture.isAutoCaptureEnabled;
        }

        public final CaptureStepDataBundle getDocumentData() {
            return this.documentData;
        }

        public int hashCode() {
            return (((this.documentData.hashCode() * 31) + Boolean.hashCode(this.isPortrait)) * 31) + Boolean.hashCode(this.isAutoCaptureEnabled);
        }

        public final boolean isAutoCaptureEnabled() {
            return this.isAutoCaptureEnabled;
        }

        public final boolean isPortrait() {
            return this.isPortrait;
        }

        public String toString() {
            return "DocumentCapture(documentData=" + this.documentData + ", isPortrait=" + this.isPortrait + ", isAutoCaptureEnabled=" + this.isAutoCaptureEnabled + ')';
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u001b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/CaptureEvents$DocumentCaptureError;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsEvent;", "documentSide", "Lcom/onfido/api/client/data/DocSide;", "errorMessage", "", "(Lcom/onfido/api/client/data/DocSide;Ljava/lang/String;)V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class DocumentCaptureError extends AnalyticsEvent {
        public DocumentCaptureError(DocSide docSide, String str) {
            super(new Event(EventNames.Document.DOCUMENT_CAPTURE_ERROR, EventType.VIEW, null, null, 12, null), MapsKt.mapOf(TuplesKt.to(AnalyticsPropertyKeys.STEP, AnalyticsFlowStep.DOCUMENT), TuplesKt.to(AnalyticsPropertyKeys.DOCUMENT_SIDE, docSide), TuplesKt.to(AnalyticsPropertyKeys.ERROR_MESSAGE, str), TuplesKt.to(AnalyticsPropertyKeys.UI_ALERTS, str != null ? new UiAlerts(null, null, null, UiAlerts.UiAlertType.ERROR, null, null, null, null, null, 503, null) : null)), null, 4, null);
        }

        public /* synthetic */ DocumentCaptureError(DocSide docSide, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(docSide, (i & 2) != 0 ? null : str);
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/CaptureEvents$DocumentCaptureFlowCompleted;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsEvent;", "documentData", "Lcom/onfido/android/sdk/capture/flow/CaptureStepDataBundle;", "(Lcom/onfido/android/sdk/capture/flow/CaptureStepDataBundle;)V", "getDocumentData", "()Lcom/onfido/android/sdk/capture/flow/CaptureStepDataBundle;", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class DocumentCaptureFlowCompleted extends AnalyticsEvent {
        private final CaptureStepDataBundle documentData;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public DocumentCaptureFlowCompleted(CaptureStepDataBundle documentData) {
            super(new Event(EventNames.Document.DOCUMENT_CAPTURE_FLOW_COMPLETED, EventType.ACTION, null, OnfidoAnalyticsEventType.ACTION, 4, null), MapsKt.mapOf(TuplesKt.to(AnalyticsPropertyKeys.STEP, AnalyticsFlowStep.DOCUMENT), TuplesKt.to(AnalyticsPropertyKeys.DOCUMENT_TYPE, documentData.getDocumentType()), TuplesKt.to(AnalyticsPropertyKeys.DOCUMENT_SIDE, documentData.getDocSide()), TuplesKt.to("country_code", documentData.getCountryCode()), TuplesKt.to(AnalyticsPropertyKeys.DOCUMENT_FORMAT, documentData.getDocumentFormat()), TuplesKt.to(AnalyticsPropertyKeys.GENERIC_DOCUMENT_TITLE, documentData.getGenericDocTitle()), TuplesKt.to(AnalyticsPropertyKeys.GENERIC_DOCUMENT_PAGES, documentData.getGenericDocPages())), null, 4, null);
            Intrinsics.checkNotNullParameter(documentData, "documentData");
            this.documentData = documentData;
        }

        public static /* synthetic */ DocumentCaptureFlowCompleted copy$default(DocumentCaptureFlowCompleted documentCaptureFlowCompleted, CaptureStepDataBundle captureStepDataBundle, int i, Object obj) {
            if ((i & 1) != 0) {
                captureStepDataBundle = documentCaptureFlowCompleted.documentData;
            }
            return documentCaptureFlowCompleted.copy(captureStepDataBundle);
        }

        /* renamed from: component1, reason: from getter */
        public final CaptureStepDataBundle getDocumentData() {
            return this.documentData;
        }

        public final DocumentCaptureFlowCompleted copy(CaptureStepDataBundle documentData) {
            Intrinsics.checkNotNullParameter(documentData, "documentData");
            return new DocumentCaptureFlowCompleted(documentData);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof DocumentCaptureFlowCompleted) && Intrinsics.areEqual(this.documentData, ((DocumentCaptureFlowCompleted) other).documentData);
        }

        public final CaptureStepDataBundle getDocumentData() {
            return this.documentData;
        }

        public int hashCode() {
            return this.documentData.hashCode();
        }

        public String toString() {
            return "DocumentCaptureFlowCompleted(documentData=" + this.documentData + ')';
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/CaptureEvents$DocumentCaptureUploadCompleted;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsEvent;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class DocumentCaptureUploadCompleted extends AnalyticsEvent {
        public DocumentCaptureUploadCompleted() {
            super(new Event(EventNames.Document.DOCUMENT_UPLOAD_COMPLETED, EventType.ACTION, null, null, 12, null), MapsKt.mapOf(TuplesKt.to(AnalyticsPropertyKeys.STEP, AnalyticsFlowStep.DOCUMENT)), null, 4, null);
        }
    }

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001BQ\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\b\u0012\u0006\u0010\f\u001a\u00020\b\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0002\u0010\u000fJ\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001c\u001a\u00020\bHÆ\u0003J\t\u0010\u001d\u001a\u00020\bHÆ\u0003J\t\u0010\u001e\u001a\u00020\bHÆ\u0003J\t\u0010\u001f\u001a\u00020\bHÆ\u0003J\t\u0010 \u001a\u00020\bHÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u000eHÆ\u0003Je\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000eHÆ\u0001J\u0013\u0010#\u001a\u00020\b2\b\u0010$\u001a\u0004\u0018\u00010%HÖ\u0003J\t\u0010&\u001a\u00020\u0005HÖ\u0001J\t\u0010'\u001a\u00020(HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\n\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0013R\u0011\u0010\t\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0013R\u0011\u0010\u000b\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0013R\u0011\u0010\f\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0013R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0015¨\u0006)"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/CaptureEvents$DocumentCaptureUploadStarted;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsEvent;", "documentData", "Lcom/onfido/android/sdk/capture/flow/CaptureStepDataBundle;", "takenPhotoCount", "", "maxRetryCount", "isAutoCaptureEnabled", "", "isAutoCaptureUsed", "hasMrzLines", "isDeviceNfcCapable", "isNfcFeatureEnabled", "nfcNonCapabilityReason", "Lcom/onfido/android/sdk/capture/internal/nfc/NfcNonCapabilityReason;", "(Lcom/onfido/android/sdk/capture/flow/CaptureStepDataBundle;IIZZZZZLcom/onfido/android/sdk/capture/internal/nfc/NfcNonCapabilityReason;)V", "getDocumentData", "()Lcom/onfido/android/sdk/capture/flow/CaptureStepDataBundle;", "getHasMrzLines", "()Z", "getMaxRetryCount", "()I", "getNfcNonCapabilityReason", "()Lcom/onfido/android/sdk/capture/internal/nfc/NfcNonCapabilityReason;", "getTakenPhotoCount", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", Constants.COPY_TYPE, "equals", "other", "", "hashCode", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class DocumentCaptureUploadStarted extends AnalyticsEvent {
        private final CaptureStepDataBundle documentData;
        private final boolean hasMrzLines;
        private final boolean isAutoCaptureEnabled;
        private final boolean isAutoCaptureUsed;
        private final boolean isDeviceNfcCapable;
        private final boolean isNfcFeatureEnabled;
        private final int maxRetryCount;
        private final NfcNonCapabilityReason nfcNonCapabilityReason;
        private final int takenPhotoCount;

        public DocumentCaptureUploadStarted(CaptureStepDataBundle documentData, int i, int i2, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, NfcNonCapabilityReason nfcNonCapabilityReason) {
            String lowerCase;
            String strName;
            Intrinsics.checkNotNullParameter(documentData, "documentData");
            Event event = new Event(EventNames.Document.DOCUMENT_UPLOAD_STARTED, EventType.ACTION, EventNames.PublicNames.Other.DOCUMENT_UPLOAD_STARTED, OnfidoAnalyticsEventType.SCREEN);
            Pair pair = TuplesKt.to(AnalyticsPropertyKeys.STEP, AnalyticsFlowStep.DOCUMENT);
            Pair pair2 = TuplesKt.to(AnalyticsPropertyKeys.DOCUMENT_TYPE, documentData.getDocumentType());
            Pair pair3 = TuplesKt.to(AnalyticsPropertyKeys.DOCUMENT_SIDE, documentData.getDocSide());
            Pair pair4 = TuplesKt.to(AnalyticsPropertyKeys.DOCUMENT_FORMAT, documentData.getDocumentFormat());
            Pair pair5 = TuplesKt.to("country_code", documentData.getCountryCode());
            Pair pair6 = TuplesKt.to(AnalyticsPropertyKeys.COUNT_ATTEMPT, Integer.valueOf(i));
            Pair pair7 = TuplesKt.to(AnalyticsPropertyKeys.MAX_RETRY_COUNT, Integer.valueOf(i2));
            Pair pair8 = TuplesKt.to(AnalyticsPropertyKeys.CAPTURE_FORMAT, documentData.getCaptureType().isVideo() ? CaptureFormat.VIDEO : CaptureFormat.IMAGE);
            Pair pair9 = TuplesKt.to(AnalyticsPropertyKeys.DID_EXTRACT_MRZ, Boolean.valueOf(z3));
            Pair pair10 = TuplesKt.to(AnalyticsPropertyKeys.IS_AUTO_CAPTURE_ENABLED, Boolean.valueOf(z));
            Pair pair11 = TuplesKt.to(AnalyticsPropertyKeys.IS_AUTO_CAPTURE_USED, Boolean.valueOf(z2));
            Pair pair12 = TuplesKt.to(AnalyticsPropertyKeys.IS_DEVICE_NFC_CAPABLE, Boolean.valueOf(z4));
            Pair pair13 = TuplesKt.to(AnalyticsPropertyKeys.IS_NFC_FEATURE_ENABLED, Boolean.valueOf(z5));
            if (nfcNonCapabilityReason == null || (strName = nfcNonCapabilityReason.name()) == null) {
                lowerCase = null;
            } else {
                lowerCase = strName.toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            }
            super(event, MapsKt.mapOf(pair, pair2, pair3, pair4, pair5, pair6, pair7, pair8, pair9, pair10, pair11, pair12, pair13, TuplesKt.to(AnalyticsPropertyKeys.NFC_NON_CAPABILITY_REASON, lowerCase), TuplesKt.to(AnalyticsPropertyKeys.GENERIC_DOCUMENT_TITLE, documentData.getGenericDocTitle()), TuplesKt.to(AnalyticsPropertyKeys.GENERIC_DOCUMENT_PAGES, documentData.getGenericDocPages())), null, 4, null);
            this.documentData = documentData;
            this.takenPhotoCount = i;
            this.maxRetryCount = i2;
            this.isAutoCaptureEnabled = z;
            this.isAutoCaptureUsed = z2;
            this.hasMrzLines = z3;
            this.isDeviceNfcCapable = z4;
            this.isNfcFeatureEnabled = z5;
            this.nfcNonCapabilityReason = nfcNonCapabilityReason;
        }

        /* renamed from: component1, reason: from getter */
        public final CaptureStepDataBundle getDocumentData() {
            return this.documentData;
        }

        /* renamed from: component2, reason: from getter */
        public final int getTakenPhotoCount() {
            return this.takenPhotoCount;
        }

        /* renamed from: component3, reason: from getter */
        public final int getMaxRetryCount() {
            return this.maxRetryCount;
        }

        /* renamed from: component4, reason: from getter */
        public final boolean getIsAutoCaptureEnabled() {
            return this.isAutoCaptureEnabled;
        }

        /* renamed from: component5, reason: from getter */
        public final boolean getIsAutoCaptureUsed() {
            return this.isAutoCaptureUsed;
        }

        /* renamed from: component6, reason: from getter */
        public final boolean getHasMrzLines() {
            return this.hasMrzLines;
        }

        /* renamed from: component7, reason: from getter */
        public final boolean getIsDeviceNfcCapable() {
            return this.isDeviceNfcCapable;
        }

        /* renamed from: component8, reason: from getter */
        public final boolean getIsNfcFeatureEnabled() {
            return this.isNfcFeatureEnabled;
        }

        /* renamed from: component9, reason: from getter */
        public final NfcNonCapabilityReason getNfcNonCapabilityReason() {
            return this.nfcNonCapabilityReason;
        }

        public final DocumentCaptureUploadStarted copy(CaptureStepDataBundle documentData, int takenPhotoCount, int maxRetryCount, boolean isAutoCaptureEnabled, boolean isAutoCaptureUsed, boolean hasMrzLines, boolean isDeviceNfcCapable, boolean isNfcFeatureEnabled, NfcNonCapabilityReason nfcNonCapabilityReason) {
            Intrinsics.checkNotNullParameter(documentData, "documentData");
            return new DocumentCaptureUploadStarted(documentData, takenPhotoCount, maxRetryCount, isAutoCaptureEnabled, isAutoCaptureUsed, hasMrzLines, isDeviceNfcCapable, isNfcFeatureEnabled, nfcNonCapabilityReason);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DocumentCaptureUploadStarted)) {
                return false;
            }
            DocumentCaptureUploadStarted documentCaptureUploadStarted = (DocumentCaptureUploadStarted) other;
            return Intrinsics.areEqual(this.documentData, documentCaptureUploadStarted.documentData) && this.takenPhotoCount == documentCaptureUploadStarted.takenPhotoCount && this.maxRetryCount == documentCaptureUploadStarted.maxRetryCount && this.isAutoCaptureEnabled == documentCaptureUploadStarted.isAutoCaptureEnabled && this.isAutoCaptureUsed == documentCaptureUploadStarted.isAutoCaptureUsed && this.hasMrzLines == documentCaptureUploadStarted.hasMrzLines && this.isDeviceNfcCapable == documentCaptureUploadStarted.isDeviceNfcCapable && this.isNfcFeatureEnabled == documentCaptureUploadStarted.isNfcFeatureEnabled && this.nfcNonCapabilityReason == documentCaptureUploadStarted.nfcNonCapabilityReason;
        }

        public final CaptureStepDataBundle getDocumentData() {
            return this.documentData;
        }

        public final boolean getHasMrzLines() {
            return this.hasMrzLines;
        }

        public final int getMaxRetryCount() {
            return this.maxRetryCount;
        }

        public final NfcNonCapabilityReason getNfcNonCapabilityReason() {
            return this.nfcNonCapabilityReason;
        }

        public final int getTakenPhotoCount() {
            return this.takenPhotoCount;
        }

        public int hashCode() {
            int iHashCode = ((((((((((((((this.documentData.hashCode() * 31) + Integer.hashCode(this.takenPhotoCount)) * 31) + Integer.hashCode(this.maxRetryCount)) * 31) + Boolean.hashCode(this.isAutoCaptureEnabled)) * 31) + Boolean.hashCode(this.isAutoCaptureUsed)) * 31) + Boolean.hashCode(this.hasMrzLines)) * 31) + Boolean.hashCode(this.isDeviceNfcCapable)) * 31) + Boolean.hashCode(this.isNfcFeatureEnabled)) * 31;
            NfcNonCapabilityReason nfcNonCapabilityReason = this.nfcNonCapabilityReason;
            return iHashCode + (nfcNonCapabilityReason == null ? 0 : nfcNonCapabilityReason.hashCode());
        }

        public final boolean isAutoCaptureEnabled() {
            return this.isAutoCaptureEnabled;
        }

        public final boolean isAutoCaptureUsed() {
            return this.isAutoCaptureUsed;
        }

        public final boolean isDeviceNfcCapable() {
            return this.isDeviceNfcCapable;
        }

        public final boolean isNfcFeatureEnabled() {
            return this.isNfcFeatureEnabled;
        }

        public String toString() {
            return "DocumentCaptureUploadStarted(documentData=" + this.documentData + ", takenPhotoCount=" + this.takenPhotoCount + ", maxRetryCount=" + this.maxRetryCount + ", isAutoCaptureEnabled=" + this.isAutoCaptureEnabled + ", isAutoCaptureUsed=" + this.isAutoCaptureUsed + ", hasMrzLines=" + this.hasMrzLines + ", isDeviceNfcCapable=" + this.isDeviceNfcCapable + ", isNfcFeatureEnabled=" + this.isNfcFeatureEnabled + ", nfcNonCapabilityReason=" + this.nfcNonCapabilityReason + ')';
        }

        public /* synthetic */ DocumentCaptureUploadStarted(CaptureStepDataBundle captureStepDataBundle, int i, int i2, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, NfcNonCapabilityReason nfcNonCapabilityReason, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this(captureStepDataBundle, i, i2, z, (i3 & 16) != 0 ? false : z2, z3, z4, z5, nfcNonCapabilityReason);
        }
    }

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0013\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0080\b\u0018\u00002\u00020\u0001BA\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\f¢\u0006\u0002\u0010\u000eJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\u0015\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bHÆ\u0003J\t\u0010\u001b\u001a\u00020\fHÆ\u0003J\t\u0010\u001c\u001a\u00020\fHÆ\u0003JQ\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\fHÆ\u0001J\u0013\u0010\u001e\u001a\u00020\f2\b\u0010\u001f\u001a\u0004\u0018\u00010 HÖ\u0003J\t\u0010!\u001a\u00020\u0005HÖ\u0001J\t\u0010\"\u001a\u00020\tHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\r\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0011R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u001d\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006#"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/CaptureEvents$DocumentConfirmation;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsEvent;", "documentData", "Lcom/onfido/android/sdk/capture/flow/CaptureStepDataBundle;", "takenPhotoCount", "", "maxRetryCount", AnalyticsPropertyKeys.WARNINGS, "", "", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/UiAlerts$UiAlertType;", "isRemoteOrigin", "", "isAutoCaptured", "(Lcom/onfido/android/sdk/capture/flow/CaptureStepDataBundle;IILjava/util/Map;ZZ)V", "getDocumentData", "()Lcom/onfido/android/sdk/capture/flow/CaptureStepDataBundle;", "()Z", "getMaxRetryCount", "()I", "getTakenPhotoCount", "getWarnings", "()Ljava/util/Map;", "component1", "component2", "component3", "component4", "component5", "component6", Constants.COPY_TYPE, "equals", "other", "", "hashCode", "toString", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class DocumentConfirmation extends AnalyticsEvent {
        private final CaptureStepDataBundle documentData;
        private final boolean isAutoCaptured;
        private final boolean isRemoteOrigin;
        private final int maxRetryCount;
        private final int takenPhotoCount;
        private final Map<String, UiAlerts.UiAlertType> warnings;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public DocumentConfirmation(CaptureStepDataBundle documentData, int i, int i2, Map<String, ? extends UiAlerts.UiAlertType> warnings, boolean z, boolean z2) {
            super(new Event(EventNames.Document.DOCUMENT_CONFIRMATION, EventType.SCREEN, null, OnfidoAnalyticsEventType.SCREEN, 4, null), MapsKt.mapOf(TuplesKt.to(AnalyticsPropertyKeys.STEP, AnalyticsFlowStep.DOCUMENT), TuplesKt.to(AnalyticsPropertyKeys.DOCUMENT_TYPE, documentData.getDocumentType()), TuplesKt.to(AnalyticsPropertyKeys.DOCUMENT_SIDE, documentData.getDocSide()), TuplesKt.to("country_code", documentData.getCountryCode()), TuplesKt.to(AnalyticsPropertyKeys.DOCUMENT_FORMAT, documentData.getDocumentFormat()), TuplesKt.to(AnalyticsPropertyKeys.COUNT_ATTEMPT, Integer.valueOf(i)), TuplesKt.to(AnalyticsPropertyKeys.MAX_RETRY_COUNT, Integer.valueOf(i2)), TuplesKt.to(AnalyticsPropertyKeys.WARNINGS, warnings), TuplesKt.to(AnalyticsPropertyKeys.WARNING_ORIGIN, z ? WarningOrigin.REMOTE : WarningOrigin.DEVICE), TuplesKt.to(AnalyticsPropertyKeys.IS_AUTO_CAPTURE_USED, Boolean.valueOf(z2)), TuplesKt.to(AnalyticsPropertyKeys.GENERIC_DOCUMENT_TITLE, documentData.getGenericDocTitle()), TuplesKt.to(AnalyticsPropertyKeys.GENERIC_DOCUMENT_PAGES, documentData.getGenericDocPages())), null, 4, null);
            Intrinsics.checkNotNullParameter(documentData, "documentData");
            Intrinsics.checkNotNullParameter(warnings, "warnings");
            this.documentData = documentData;
            this.takenPhotoCount = i;
            this.maxRetryCount = i2;
            this.warnings = warnings;
            this.isRemoteOrigin = z;
            this.isAutoCaptured = z2;
        }

        public static /* synthetic */ DocumentConfirmation copy$default(DocumentConfirmation documentConfirmation, CaptureStepDataBundle captureStepDataBundle, int i, int i2, Map map, boolean z, boolean z2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                captureStepDataBundle = documentConfirmation.documentData;
            }
            if ((i3 & 2) != 0) {
                i = documentConfirmation.takenPhotoCount;
            }
            int i4 = i;
            if ((i3 & 4) != 0) {
                i2 = documentConfirmation.maxRetryCount;
            }
            int i5 = i2;
            if ((i3 & 8) != 0) {
                map = documentConfirmation.warnings;
            }
            Map map2 = map;
            if ((i3 & 16) != 0) {
                z = documentConfirmation.isRemoteOrigin;
            }
            boolean z3 = z;
            if ((i3 & 32) != 0) {
                z2 = documentConfirmation.isAutoCaptured;
            }
            return documentConfirmation.copy(captureStepDataBundle, i4, i5, map2, z3, z2);
        }

        /* renamed from: component1, reason: from getter */
        public final CaptureStepDataBundle getDocumentData() {
            return this.documentData;
        }

        /* renamed from: component2, reason: from getter */
        public final int getTakenPhotoCount() {
            return this.takenPhotoCount;
        }

        /* renamed from: component3, reason: from getter */
        public final int getMaxRetryCount() {
            return this.maxRetryCount;
        }

        public final Map<String, UiAlerts.UiAlertType> component4() {
            return this.warnings;
        }

        /* renamed from: component5, reason: from getter */
        public final boolean getIsRemoteOrigin() {
            return this.isRemoteOrigin;
        }

        /* renamed from: component6, reason: from getter */
        public final boolean getIsAutoCaptured() {
            return this.isAutoCaptured;
        }

        public final DocumentConfirmation copy(CaptureStepDataBundle documentData, int takenPhotoCount, int maxRetryCount, Map<String, ? extends UiAlerts.UiAlertType> warnings, boolean isRemoteOrigin, boolean isAutoCaptured) {
            Intrinsics.checkNotNullParameter(documentData, "documentData");
            Intrinsics.checkNotNullParameter(warnings, "warnings");
            return new DocumentConfirmation(documentData, takenPhotoCount, maxRetryCount, warnings, isRemoteOrigin, isAutoCaptured);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DocumentConfirmation)) {
                return false;
            }
            DocumentConfirmation documentConfirmation = (DocumentConfirmation) other;
            return Intrinsics.areEqual(this.documentData, documentConfirmation.documentData) && this.takenPhotoCount == documentConfirmation.takenPhotoCount && this.maxRetryCount == documentConfirmation.maxRetryCount && Intrinsics.areEqual(this.warnings, documentConfirmation.warnings) && this.isRemoteOrigin == documentConfirmation.isRemoteOrigin && this.isAutoCaptured == documentConfirmation.isAutoCaptured;
        }

        public final CaptureStepDataBundle getDocumentData() {
            return this.documentData;
        }

        public final int getMaxRetryCount() {
            return this.maxRetryCount;
        }

        public final int getTakenPhotoCount() {
            return this.takenPhotoCount;
        }

        public final Map<String, UiAlerts.UiAlertType> getWarnings() {
            return this.warnings;
        }

        public int hashCode() {
            return (((((((((this.documentData.hashCode() * 31) + Integer.hashCode(this.takenPhotoCount)) * 31) + Integer.hashCode(this.maxRetryCount)) * 31) + this.warnings.hashCode()) * 31) + Boolean.hashCode(this.isRemoteOrigin)) * 31) + Boolean.hashCode(this.isAutoCaptured);
        }

        public final boolean isAutoCaptured() {
            return this.isAutoCaptured;
        }

        public final boolean isRemoteOrigin() {
            return this.isRemoteOrigin;
        }

        public String toString() {
            return "DocumentConfirmation(documentData=" + this.documentData + ", takenPhotoCount=" + this.takenPhotoCount + ", maxRetryCount=" + this.maxRetryCount + ", warnings=" + this.warnings + ", isRemoteOrigin=" + this.isRemoteOrigin + ", isAutoCaptured=" + this.isAutoCaptured + ')';
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/CaptureEvents$DocumentConfirmationError;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsEvent;", "errorType", "Lcom/onfido/android/sdk/capture/upload/ErrorType;", "documentSide", "Lcom/onfido/api/client/data/DocSide;", "(Lcom/onfido/android/sdk/capture/upload/ErrorType;Lcom/onfido/api/client/data/DocSide;)V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class DocumentConfirmationError extends AnalyticsEvent {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public DocumentConfirmationError(ErrorType errorType, DocSide docSide) {
            super(new Event(EventNames.Document.DOCUMENT_CONFIRMATION_ERROR, EventType.VIEW, null, null, 12, null), MapsKt.mapOf(TuplesKt.to(AnalyticsPropertyKeys.STEP, AnalyticsFlowStep.DOCUMENT), TuplesKt.to(AnalyticsPropertyKeys.DOCUMENT_SIDE, docSide), TuplesKt.to(AnalyticsPropertyKeys.UI_ALERTS, CaptureEvents.INSTANCE.toUiAlertsOrNull(errorType))), null, 4, null);
            Intrinsics.checkNotNullParameter(errorType, "errorType");
        }
    }

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0014\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0080\b\u0018\u00002\u00020\u0001BC\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\u0015\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bHÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\tHÆ\u0003J\t\u0010\u001e\u001a\u00020\rHÆ\u0003JS\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\f\u001a\u00020\rHÆ\u0001J\u0013\u0010 \u001a\u00020\r2\b\u0010!\u001a\u0004\u0018\u00010\"HÖ\u0003J\t\u0010#\u001a\u00020\u0005HÖ\u0001J\t\u0010$\u001a\u00020\tHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u001d\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018¨\u0006%"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/CaptureEvents$DocumentConfirmationWarning;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsEvent;", "documentData", "Lcom/onfido/android/sdk/capture/flow/CaptureStepDataBundle;", "takenPhotoCount", "", "maxRetryCount", AnalyticsPropertyKeys.WARNINGS, "", "", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/UiAlerts$UiAlertType;", "warningShown", "isRemoteWarning", "", "(Lcom/onfido/android/sdk/capture/flow/CaptureStepDataBundle;IILjava/util/Map;Ljava/lang/String;Z)V", "getDocumentData", "()Lcom/onfido/android/sdk/capture/flow/CaptureStepDataBundle;", "()Z", "getMaxRetryCount", "()I", "getTakenPhotoCount", "getWarningShown", "()Ljava/lang/String;", "getWarnings", "()Ljava/util/Map;", "component1", "component2", "component3", "component4", "component5", "component6", Constants.COPY_TYPE, "equals", "other", "", "hashCode", "toString", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class DocumentConfirmationWarning extends AnalyticsEvent {
        private final CaptureStepDataBundle documentData;
        private final boolean isRemoteWarning;
        private final int maxRetryCount;
        private final int takenPhotoCount;
        private final String warningShown;
        private final Map<String, UiAlerts.UiAlertType> warnings;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public DocumentConfirmationWarning(CaptureStepDataBundle documentData, int i, int i2, Map<String, ? extends UiAlerts.UiAlertType> warnings, String str, boolean z) {
            super(new Event(EventNames.Document.DOCUMENT_CONFIRMATION_WARNING, EventType.VIEW, null, null, 12, null), MapsKt.mapOf(TuplesKt.to(AnalyticsPropertyKeys.STEP, AnalyticsFlowStep.DOCUMENT), TuplesKt.to(AnalyticsPropertyKeys.DOCUMENT_TYPE, documentData.getDocumentType()), TuplesKt.to(AnalyticsPropertyKeys.DOCUMENT_SIDE, documentData.getDocSide()), TuplesKt.to(AnalyticsPropertyKeys.DOCUMENT_FORMAT, documentData.getDocumentFormat()), TuplesKt.to("country_code", documentData.getCountryCode()), TuplesKt.to(AnalyticsPropertyKeys.COUNT_ATTEMPT, Integer.valueOf(i)), TuplesKt.to(AnalyticsPropertyKeys.MAX_RETRY_COUNT, Integer.valueOf(i2)), TuplesKt.to(AnalyticsPropertyKeys.IS_BLOCKING, Boolean.valueOf(i > i2)), TuplesKt.to(AnalyticsPropertyKeys.WARNINGS, warnings), TuplesKt.to(AnalyticsPropertyKeys.WARNING_SHOWN, str), TuplesKt.to(AnalyticsPropertyKeys.WARNING_ORIGIN, z ? WarningOrigin.REMOTE : WarningOrigin.DEVICE)), null, 4, null);
            Intrinsics.checkNotNullParameter(documentData, "documentData");
            Intrinsics.checkNotNullParameter(warnings, "warnings");
            this.documentData = documentData;
            this.takenPhotoCount = i;
            this.maxRetryCount = i2;
            this.warnings = warnings;
            this.warningShown = str;
            this.isRemoteWarning = z;
        }

        public static /* synthetic */ DocumentConfirmationWarning copy$default(DocumentConfirmationWarning documentConfirmationWarning, CaptureStepDataBundle captureStepDataBundle, int i, int i2, Map map, String str, boolean z, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                captureStepDataBundle = documentConfirmationWarning.documentData;
            }
            if ((i3 & 2) != 0) {
                i = documentConfirmationWarning.takenPhotoCount;
            }
            int i4 = i;
            if ((i3 & 4) != 0) {
                i2 = documentConfirmationWarning.maxRetryCount;
            }
            int i5 = i2;
            if ((i3 & 8) != 0) {
                map = documentConfirmationWarning.warnings;
            }
            Map map2 = map;
            if ((i3 & 16) != 0) {
                str = documentConfirmationWarning.warningShown;
            }
            String str2 = str;
            if ((i3 & 32) != 0) {
                z = documentConfirmationWarning.isRemoteWarning;
            }
            return documentConfirmationWarning.copy(captureStepDataBundle, i4, i5, map2, str2, z);
        }

        /* renamed from: component1, reason: from getter */
        public final CaptureStepDataBundle getDocumentData() {
            return this.documentData;
        }

        /* renamed from: component2, reason: from getter */
        public final int getTakenPhotoCount() {
            return this.takenPhotoCount;
        }

        /* renamed from: component3, reason: from getter */
        public final int getMaxRetryCount() {
            return this.maxRetryCount;
        }

        public final Map<String, UiAlerts.UiAlertType> component4() {
            return this.warnings;
        }

        /* renamed from: component5, reason: from getter */
        public final String getWarningShown() {
            return this.warningShown;
        }

        /* renamed from: component6, reason: from getter */
        public final boolean getIsRemoteWarning() {
            return this.isRemoteWarning;
        }

        public final DocumentConfirmationWarning copy(CaptureStepDataBundle documentData, int takenPhotoCount, int maxRetryCount, Map<String, ? extends UiAlerts.UiAlertType> warnings, String warningShown, boolean isRemoteWarning) {
            Intrinsics.checkNotNullParameter(documentData, "documentData");
            Intrinsics.checkNotNullParameter(warnings, "warnings");
            return new DocumentConfirmationWarning(documentData, takenPhotoCount, maxRetryCount, warnings, warningShown, isRemoteWarning);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DocumentConfirmationWarning)) {
                return false;
            }
            DocumentConfirmationWarning documentConfirmationWarning = (DocumentConfirmationWarning) other;
            return Intrinsics.areEqual(this.documentData, documentConfirmationWarning.documentData) && this.takenPhotoCount == documentConfirmationWarning.takenPhotoCount && this.maxRetryCount == documentConfirmationWarning.maxRetryCount && Intrinsics.areEqual(this.warnings, documentConfirmationWarning.warnings) && Intrinsics.areEqual(this.warningShown, documentConfirmationWarning.warningShown) && this.isRemoteWarning == documentConfirmationWarning.isRemoteWarning;
        }

        public final CaptureStepDataBundle getDocumentData() {
            return this.documentData;
        }

        public final int getMaxRetryCount() {
            return this.maxRetryCount;
        }

        public final int getTakenPhotoCount() {
            return this.takenPhotoCount;
        }

        public final String getWarningShown() {
            return this.warningShown;
        }

        public final Map<String, UiAlerts.UiAlertType> getWarnings() {
            return this.warnings;
        }

        public int hashCode() {
            int iHashCode = ((((((this.documentData.hashCode() * 31) + Integer.hashCode(this.takenPhotoCount)) * 31) + Integer.hashCode(this.maxRetryCount)) * 31) + this.warnings.hashCode()) * 31;
            String str = this.warningShown;
            return ((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + Boolean.hashCode(this.isRemoteWarning);
        }

        public final boolean isRemoteWarning() {
            return this.isRemoteWarning;
        }

        public String toString() {
            return "DocumentConfirmationWarning(documentData=" + this.documentData + ", takenPhotoCount=" + this.takenPhotoCount + ", maxRetryCount=" + this.maxRetryCount + ", warnings=" + this.warnings + ", warningShown=" + this.warningShown + ", isRemoteWarning=" + this.isRemoteWarning + ')';
        }
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0006HÆ\u0003J1\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0006HÖ\u0001J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\n¨\u0006\u001b"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/CaptureEvents$DocumentValidMRZExtracted;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsEvent;", "extractionDuration", "", "frameProcessDuration", "emittedFramesCount", "", "processedFramesCount", "(JJII)V", "getEmittedFramesCount", "()I", "getExtractionDuration", "()J", "getFrameProcessDuration", "getProcessedFramesCount", "component1", "component2", "component3", "component4", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class DocumentValidMRZExtracted extends AnalyticsEvent {
        private final int emittedFramesCount;
        private final long extractionDuration;
        private final long frameProcessDuration;
        private final int processedFramesCount;

        public DocumentValidMRZExtracted(long j, long j2, int i, int i2) {
            super(new Event(EventNames.Document.DOCUMENT_VALID_MRZ_EXTRACTED, EventType.ACTION, null, null, 12, null), MapsKt.mapOf(TuplesKt.to(AnalyticsPropertyKeys.EXTRACTION_DURATION, Long.valueOf(j)), TuplesKt.to(AnalyticsPropertyKeys.FRAME_PROCESS_DURATION, Long.valueOf(j2)), TuplesKt.to(AnalyticsPropertyKeys.EMITTED_FRAME_COUNT, Integer.valueOf(i)), TuplesKt.to(AnalyticsPropertyKeys.PROCESSED_FRAME_COUNT, Integer.valueOf(i2))), null, 4, null);
            this.extractionDuration = j;
            this.frameProcessDuration = j2;
            this.emittedFramesCount = i;
            this.processedFramesCount = i2;
        }

        public static /* synthetic */ DocumentValidMRZExtracted copy$default(DocumentValidMRZExtracted documentValidMRZExtracted, long j, long j2, int i, int i2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                j = documentValidMRZExtracted.extractionDuration;
            }
            long j3 = j;
            if ((i3 & 2) != 0) {
                j2 = documentValidMRZExtracted.frameProcessDuration;
            }
            long j4 = j2;
            if ((i3 & 4) != 0) {
                i = documentValidMRZExtracted.emittedFramesCount;
            }
            int i4 = i;
            if ((i3 & 8) != 0) {
                i2 = documentValidMRZExtracted.processedFramesCount;
            }
            return documentValidMRZExtracted.copy(j3, j4, i4, i2);
        }

        /* renamed from: component1, reason: from getter */
        public final long getExtractionDuration() {
            return this.extractionDuration;
        }

        /* renamed from: component2, reason: from getter */
        public final long getFrameProcessDuration() {
            return this.frameProcessDuration;
        }

        /* renamed from: component3, reason: from getter */
        public final int getEmittedFramesCount() {
            return this.emittedFramesCount;
        }

        /* renamed from: component4, reason: from getter */
        public final int getProcessedFramesCount() {
            return this.processedFramesCount;
        }

        public final DocumentValidMRZExtracted copy(long extractionDuration, long frameProcessDuration, int emittedFramesCount, int processedFramesCount) {
            return new DocumentValidMRZExtracted(extractionDuration, frameProcessDuration, emittedFramesCount, processedFramesCount);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DocumentValidMRZExtracted)) {
                return false;
            }
            DocumentValidMRZExtracted documentValidMRZExtracted = (DocumentValidMRZExtracted) other;
            return this.extractionDuration == documentValidMRZExtracted.extractionDuration && this.frameProcessDuration == documentValidMRZExtracted.frameProcessDuration && this.emittedFramesCount == documentValidMRZExtracted.emittedFramesCount && this.processedFramesCount == documentValidMRZExtracted.processedFramesCount;
        }

        public final int getEmittedFramesCount() {
            return this.emittedFramesCount;
        }

        public final long getExtractionDuration() {
            return this.extractionDuration;
        }

        public final long getFrameProcessDuration() {
            return this.frameProcessDuration;
        }

        public final int getProcessedFramesCount() {
            return this.processedFramesCount;
        }

        public int hashCode() {
            return (((((Long.hashCode(this.extractionDuration) * 31) + Long.hashCode(this.frameProcessDuration)) * 31) + Integer.hashCode(this.emittedFramesCount)) * 31) + Integer.hashCode(this.processedFramesCount);
        }

        public String toString() {
            return "DocumentValidMRZExtracted(extractionDuration=" + this.extractionDuration + ", frameProcessDuration=" + this.frameProcessDuration + ", emittedFramesCount=" + this.emittedFramesCount + ", processedFramesCount=" + this.processedFramesCount + ')';
        }
    }

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0016"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/CaptureEvents$OnfidoMlModelReady;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsEvent;", Device.JsonKeys.MODEL, "Lcom/onfido/android/sdk/capture/utils/mlmodel/OnfidoMlModels;", "preparationDuration", "", "(Lcom/onfido/android/sdk/capture/utils/mlmodel/OnfidoMlModels;J)V", "getModel", "()Lcom/onfido/android/sdk/capture/utils/mlmodel/OnfidoMlModels;", "getPreparationDuration", "()J", "component1", "component2", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class OnfidoMlModelReady extends AnalyticsEvent {
        private final OnfidoMlModels model;
        private final long preparationDuration;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public OnfidoMlModelReady(OnfidoMlModels model, long j) {
            super(new Event(EventNames.Document.ONFIDO_ML_MODEL_READY, EventType.ACTION, null, null, 12, null), MapsKt.mapOf(TuplesKt.to(AnalyticsPropertyKeys.PREPARATION_DURATION, Long.valueOf(j)), TuplesKt.to(AnalyticsPropertyKeys.ONFIDO_ML_MODEL_NAME, model.name())), null, 4, null);
            Intrinsics.checkNotNullParameter(model, "model");
            this.model = model;
            this.preparationDuration = j;
        }

        public static /* synthetic */ OnfidoMlModelReady copy$default(OnfidoMlModelReady onfidoMlModelReady, OnfidoMlModels onfidoMlModels, long j, int i, Object obj) {
            if ((i & 1) != 0) {
                onfidoMlModels = onfidoMlModelReady.model;
            }
            if ((i & 2) != 0) {
                j = onfidoMlModelReady.preparationDuration;
            }
            return onfidoMlModelReady.copy(onfidoMlModels, j);
        }

        /* renamed from: component1, reason: from getter */
        public final OnfidoMlModels getModel() {
            return this.model;
        }

        /* renamed from: component2, reason: from getter */
        public final long getPreparationDuration() {
            return this.preparationDuration;
        }

        public final OnfidoMlModelReady copy(OnfidoMlModels model, long preparationDuration) {
            Intrinsics.checkNotNullParameter(model, "model");
            return new OnfidoMlModelReady(model, preparationDuration);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof OnfidoMlModelReady)) {
                return false;
            }
            OnfidoMlModelReady onfidoMlModelReady = (OnfidoMlModelReady) other;
            return this.model == onfidoMlModelReady.model && this.preparationDuration == onfidoMlModelReady.preparationDuration;
        }

        public final OnfidoMlModels getModel() {
            return this.model;
        }

        public final long getPreparationDuration() {
            return this.preparationDuration;
        }

        public int hashCode() {
            return (this.model.hashCode() * 31) + Long.hashCode(this.preparationDuration);
        }

        public String toString() {
            return "OnfidoMlModelReady(model=" + this.model + ", preparationDuration=" + this.preparationDuration + ')';
        }
    }
}
