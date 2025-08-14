package com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers;

import com.clevertap.android.sdk.Constants;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.AnalyticsEvent;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.AnalyticsFlowStep;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.AnalyticsPropertyKeys;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.Event;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.EventNames;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.EventType;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.NfcFlowType;
import com.onfido.android.sdk.capture.internal.nfc.PassportAuthAccess;
import com.onfido.android.sdk.capture.model.NFCOptions;
import com.onfido.android.sdk.capture.utils.CountryCode;
import com.onfido.api.client.data.DocumentFeatures;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0016\b\u0000\u0018\u00002\u00020\u0001:\u0014\u0003\u0004\u0005\u0006\u0007\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0017"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/NfcEvents;", "", "()V", "DocumentNfcSupported", "NfcBACError", "NfcCanEntryCompleted", "NfcChipAuthenticated", "NfcChipConnectionLost", "NfcChipDiscovered", "NfcChooseAnotherDocumentClicked", "NfcContinueToScanSelected", "NfcDataUploadCompleted", "NfcDataUploadStarted", "NfcExitVerificationClicked", "NfcNoCanClicked", "NfcOpenSettingsClicked", "NfcPaceError", "NfcPropertiesError", "NfcRetryScanSelected", "NfcSkipAtInitialPrompt", "NfcSkipAtManualCanEntry", "NfcSkipAtRetry", "NfcStartScanSelected", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class NfcEvents {

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\bHÆ\u0003J\t\u0010\u0018\u001a\u00020\nHÆ\u0003J?\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u00032\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cHÖ\u0003J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\t\u0010\u001f\u001a\u00020 HÖ\u0001R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0013¨\u0006!"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/NfcEvents$DocumentNfcSupported;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsEvent;", "isNfcSupported", "", "hasNfcKey", "documentType", "Lcom/onfido/android/sdk/capture/DocumentType;", RemoteConfigConstants.RequestFieldKey.COUNTRY_CODE, "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "documentFeatures", "Lcom/onfido/api/client/data/DocumentFeatures;", "(ZZLcom/onfido/android/sdk/capture/DocumentType;Lcom/onfido/android/sdk/capture/utils/CountryCode;Lcom/onfido/api/client/data/DocumentFeatures;)V", "getCountryCode", "()Lcom/onfido/android/sdk/capture/utils/CountryCode;", "getDocumentFeatures", "()Lcom/onfido/api/client/data/DocumentFeatures;", "getDocumentType", "()Lcom/onfido/android/sdk/capture/DocumentType;", "getHasNfcKey", "()Z", "component1", "component2", "component3", "component4", "component5", Constants.COPY_TYPE, "equals", "other", "", "hashCode", "", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class DocumentNfcSupported extends AnalyticsEvent {
        private final CountryCode countryCode;
        private final DocumentFeatures documentFeatures;
        private final DocumentType documentType;
        private final boolean hasNfcKey;
        private final boolean isNfcSupported;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public DocumentNfcSupported(boolean z, boolean z2, DocumentType documentType, CountryCode countryCode, DocumentFeatures documentFeatures) {
            super(new Event(EventNames.Nfc.DOCUMENT_NFC_SUPPORTED, EventType.ACTION, null, null, 12, null), MapsKt.mapOf(TuplesKt.to(AnalyticsPropertyKeys.STEP, AnalyticsFlowStep.NFC), TuplesKt.to(AnalyticsPropertyKeys.HAS_NFC_KEY, Boolean.valueOf(z2)), TuplesKt.to(AnalyticsPropertyKeys.IS_NFC_SUPPORTED, Boolean.valueOf(z)), TuplesKt.to(AnalyticsPropertyKeys.DOCUMENT_TYPE, documentType), TuplesKt.to("country_code", countryCode), TuplesKt.to(AnalyticsPropertyKeys.HAS_CAN, Boolean.valueOf(documentFeatures.getHasCan())), TuplesKt.to(AnalyticsPropertyKeys.HAS_PIN, Boolean.valueOf(documentFeatures.getHasPin())), TuplesKt.to(AnalyticsPropertyKeys.CAN_LENGTH, Integer.valueOf(documentFeatures.getCanLength())), TuplesKt.to(AnalyticsPropertyKeys.PIN_LENGTH, Integer.valueOf(documentFeatures.getPinLength()))), null, 4, null);
            Intrinsics.checkNotNullParameter(documentFeatures, "documentFeatures");
            this.isNfcSupported = z;
            this.hasNfcKey = z2;
            this.documentType = documentType;
            this.countryCode = countryCode;
            this.documentFeatures = documentFeatures;
        }

        public static /* synthetic */ DocumentNfcSupported copy$default(DocumentNfcSupported documentNfcSupported, boolean z, boolean z2, DocumentType documentType, CountryCode countryCode, DocumentFeatures documentFeatures, int i, Object obj) {
            if ((i & 1) != 0) {
                z = documentNfcSupported.isNfcSupported;
            }
            if ((i & 2) != 0) {
                z2 = documentNfcSupported.hasNfcKey;
            }
            boolean z3 = z2;
            if ((i & 4) != 0) {
                documentType = documentNfcSupported.documentType;
            }
            DocumentType documentType2 = documentType;
            if ((i & 8) != 0) {
                countryCode = documentNfcSupported.countryCode;
            }
            CountryCode countryCode2 = countryCode;
            if ((i & 16) != 0) {
                documentFeatures = documentNfcSupported.documentFeatures;
            }
            return documentNfcSupported.copy(z, z3, documentType2, countryCode2, documentFeatures);
        }

        /* renamed from: component1, reason: from getter */
        public final boolean getIsNfcSupported() {
            return this.isNfcSupported;
        }

        /* renamed from: component2, reason: from getter */
        public final boolean getHasNfcKey() {
            return this.hasNfcKey;
        }

        /* renamed from: component3, reason: from getter */
        public final DocumentType getDocumentType() {
            return this.documentType;
        }

        /* renamed from: component4, reason: from getter */
        public final CountryCode getCountryCode() {
            return this.countryCode;
        }

        /* renamed from: component5, reason: from getter */
        public final DocumentFeatures getDocumentFeatures() {
            return this.documentFeatures;
        }

        public final DocumentNfcSupported copy(boolean isNfcSupported, boolean hasNfcKey, DocumentType documentType, CountryCode countryCode, DocumentFeatures documentFeatures) {
            Intrinsics.checkNotNullParameter(documentFeatures, "documentFeatures");
            return new DocumentNfcSupported(isNfcSupported, hasNfcKey, documentType, countryCode, documentFeatures);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DocumentNfcSupported)) {
                return false;
            }
            DocumentNfcSupported documentNfcSupported = (DocumentNfcSupported) other;
            return this.isNfcSupported == documentNfcSupported.isNfcSupported && this.hasNfcKey == documentNfcSupported.hasNfcKey && this.documentType == documentNfcSupported.documentType && this.countryCode == documentNfcSupported.countryCode && Intrinsics.areEqual(this.documentFeatures, documentNfcSupported.documentFeatures);
        }

        public final CountryCode getCountryCode() {
            return this.countryCode;
        }

        public final DocumentFeatures getDocumentFeatures() {
            return this.documentFeatures;
        }

        public final DocumentType getDocumentType() {
            return this.documentType;
        }

        public final boolean getHasNfcKey() {
            return this.hasNfcKey;
        }

        public int hashCode() {
            int iHashCode = ((Boolean.hashCode(this.isNfcSupported) * 31) + Boolean.hashCode(this.hasNfcKey)) * 31;
            DocumentType documentType = this.documentType;
            int iHashCode2 = (iHashCode + (documentType == null ? 0 : documentType.hashCode())) * 31;
            CountryCode countryCode = this.countryCode;
            return ((iHashCode2 + (countryCode != null ? countryCode.hashCode() : 0)) * 31) + this.documentFeatures.hashCode();
        }

        public final boolean isNfcSupported() {
            return this.isNfcSupported;
        }

        public String toString() {
            return "DocumentNfcSupported(isNfcSupported=" + this.isNfcSupported + ", hasNfcKey=" + this.hasNfcKey + ", documentType=" + this.documentType + ", countryCode=" + this.countryCode + ", documentFeatures=" + this.documentFeatures + ')';
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0080\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/NfcEvents$NfcBACError;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsEvent;", "error", "", "(Ljava/lang/String;)V", "getError", "()Ljava/lang/String;", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class NfcBACError extends AnalyticsEvent {
        private final String error;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public NfcBACError(String error) {
            super(new Event(EventNames.Nfc.NFC_BAC_ERROR, EventType.ACTION, null, null, 12, null), MapsKt.mapOf(TuplesKt.to(AnalyticsPropertyKeys.STEP, AnalyticsFlowStep.NFC), TuplesKt.to("error", error)), null, 4, null);
            Intrinsics.checkNotNullParameter(error, "error");
            this.error = error;
        }

        public static /* synthetic */ NfcBACError copy$default(NfcBACError nfcBACError, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = nfcBACError.error;
            }
            return nfcBACError.copy(str);
        }

        /* renamed from: component1, reason: from getter */
        public final String getError() {
            return this.error;
        }

        public final NfcBACError copy(String error) {
            Intrinsics.checkNotNullParameter(error, "error");
            return new NfcBACError(error);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof NfcBACError) && Intrinsics.areEqual(this.error, ((NfcBACError) other).error);
        }

        public final String getError() {
            return this.error;
        }

        public int hashCode() {
            return this.error.hashCode();
        }

        public String toString() {
            return "NfcBACError(error=" + this.error + ')';
        }
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/NfcEvents$NfcCanEntryCompleted;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsEvent;", "attempts", "", "durationInMs", "", "(IJ)V", "getAttempts", "()I", "getDurationInMs", "()J", "component1", "component2", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class NfcCanEntryCompleted extends AnalyticsEvent {
        private final int attempts;
        private final long durationInMs;

        public NfcCanEntryCompleted(int i, long j) {
            super(new Event(EventNames.Nfc.NFC_CAN_ENTRY_COMPLETED, EventType.ACTION, null, null, 12, null), MapsKt.mapOf(TuplesKt.to(AnalyticsPropertyKeys.STEP, AnalyticsFlowStep.NFC), TuplesKt.to(AnalyticsPropertyKeys.ENTRY_ATTEMPTS, Integer.valueOf(i)), TuplesKt.to("duration", Long.valueOf(j))), null, 4, null);
            this.attempts = i;
            this.durationInMs = j;
        }

        public static /* synthetic */ NfcCanEntryCompleted copy$default(NfcCanEntryCompleted nfcCanEntryCompleted, int i, long j, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = nfcCanEntryCompleted.attempts;
            }
            if ((i2 & 2) != 0) {
                j = nfcCanEntryCompleted.durationInMs;
            }
            return nfcCanEntryCompleted.copy(i, j);
        }

        /* renamed from: component1, reason: from getter */
        public final int getAttempts() {
            return this.attempts;
        }

        /* renamed from: component2, reason: from getter */
        public final long getDurationInMs() {
            return this.durationInMs;
        }

        public final NfcCanEntryCompleted copy(int attempts, long durationInMs) {
            return new NfcCanEntryCompleted(attempts, durationInMs);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof NfcCanEntryCompleted)) {
                return false;
            }
            NfcCanEntryCompleted nfcCanEntryCompleted = (NfcCanEntryCompleted) other;
            return this.attempts == nfcCanEntryCompleted.attempts && this.durationInMs == nfcCanEntryCompleted.durationInMs;
        }

        public final int getAttempts() {
            return this.attempts;
        }

        public final long getDurationInMs() {
            return this.durationInMs;
        }

        public int hashCode() {
            return (Integer.hashCode(this.attempts) * 31) + Long.hashCode(this.durationInMs);
        }

        public String toString() {
            return "NfcCanEntryCompleted(attempts=" + this.attempts + ", durationInMs=" + this.durationInMs + ')';
        }
    }

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0016"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/NfcEvents$NfcChipAuthenticated;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsEvent;", "nfcFlowType", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/NfcFlowType;", "authAccess", "Lcom/onfido/android/sdk/capture/internal/nfc/PassportAuthAccess;", "(Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/NfcFlowType;Lcom/onfido/android/sdk/capture/internal/nfc/PassportAuthAccess;)V", "getAuthAccess", "()Lcom/onfido/android/sdk/capture/internal/nfc/PassportAuthAccess;", "getNfcFlowType", "()Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/NfcFlowType;", "component1", "component2", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class NfcChipAuthenticated extends AnalyticsEvent {
        private final PassportAuthAccess authAccess;
        private final NfcFlowType nfcFlowType;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public NfcChipAuthenticated(NfcFlowType nfcFlowType, PassportAuthAccess authAccess) {
            super(new Event(EventNames.Nfc.NFC_CHIP_AUTHENTICATED, EventType.ACTION, null, null, 12, null), MapsKt.mapOf(TuplesKt.to(AnalyticsPropertyKeys.NFC_FLOW_TYPE, nfcFlowType), TuplesKt.to(AnalyticsPropertyKeys.CARD_ACCESS_FILE, authAccess.getCardAccessFileString()), TuplesKt.to(AnalyticsPropertyKeys.SECURITY_INFO_USED, authAccess.getUsedSecurityInfoString())), null, 4, null);
            Intrinsics.checkNotNullParameter(nfcFlowType, "nfcFlowType");
            Intrinsics.checkNotNullParameter(authAccess, "authAccess");
            this.nfcFlowType = nfcFlowType;
            this.authAccess = authAccess;
        }

        public static /* synthetic */ NfcChipAuthenticated copy$default(NfcChipAuthenticated nfcChipAuthenticated, NfcFlowType nfcFlowType, PassportAuthAccess passportAuthAccess, int i, Object obj) {
            if ((i & 1) != 0) {
                nfcFlowType = nfcChipAuthenticated.nfcFlowType;
            }
            if ((i & 2) != 0) {
                passportAuthAccess = nfcChipAuthenticated.authAccess;
            }
            return nfcChipAuthenticated.copy(nfcFlowType, passportAuthAccess);
        }

        /* renamed from: component1, reason: from getter */
        public final NfcFlowType getNfcFlowType() {
            return this.nfcFlowType;
        }

        /* renamed from: component2, reason: from getter */
        public final PassportAuthAccess getAuthAccess() {
            return this.authAccess;
        }

        public final NfcChipAuthenticated copy(NfcFlowType nfcFlowType, PassportAuthAccess authAccess) {
            Intrinsics.checkNotNullParameter(nfcFlowType, "nfcFlowType");
            Intrinsics.checkNotNullParameter(authAccess, "authAccess");
            return new NfcChipAuthenticated(nfcFlowType, authAccess);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof NfcChipAuthenticated)) {
                return false;
            }
            NfcChipAuthenticated nfcChipAuthenticated = (NfcChipAuthenticated) other;
            return this.nfcFlowType == nfcChipAuthenticated.nfcFlowType && Intrinsics.areEqual(this.authAccess, nfcChipAuthenticated.authAccess);
        }

        public final PassportAuthAccess getAuthAccess() {
            return this.authAccess;
        }

        public final NfcFlowType getNfcFlowType() {
            return this.nfcFlowType;
        }

        public int hashCode() {
            return (this.nfcFlowType.hashCode() * 31) + this.authAccess.hashCode();
        }

        public String toString() {
            return "NfcChipAuthenticated(nfcFlowType=" + this.nfcFlowType + ", authAccess=" + this.authAccess + ')';
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/NfcEvents$NfcChipConnectionLost;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsEvent;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class NfcChipConnectionLost extends AnalyticsEvent {
        public static final NfcChipConnectionLost INSTANCE = new NfcChipConnectionLost();

        private NfcChipConnectionLost() {
            super(new Event(EventNames.Nfc.NFC_CHIP_CONNECTION_LOST, EventType.ACTION, null, null, 12, null), null, null, 6, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/NfcEvents$NfcChipDiscovered;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsEvent;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class NfcChipDiscovered extends AnalyticsEvent {
        public static final NfcChipDiscovered INSTANCE = new NfcChipDiscovered();

        private NfcChipDiscovered() {
            super(new Event(EventNames.Nfc.NFC_CHIP_DISCOVERED, EventType.ACTION, null, null, 12, null), null, null, 6, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/NfcEvents$NfcChooseAnotherDocumentClicked;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsEvent;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class NfcChooseAnotherDocumentClicked extends AnalyticsEvent {
        public static final NfcChooseAnotherDocumentClicked INSTANCE = new NfcChooseAnotherDocumentClicked();

        private NfcChooseAnotherDocumentClicked() {
            super(new Event(EventNames.Nfc.NFC_CHOOSE_ANOTHER_DOCUMENT_CLICKED, EventType.ACTION, null, null, 12, null), null, null, 6, null);
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/NfcEvents$NfcContinueToScanSelected;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsEvent;", "nfcFlowType", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/NfcFlowType;", "(Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/NfcFlowType;)V", "getNfcFlowType", "()Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/NfcFlowType;", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class NfcContinueToScanSelected extends AnalyticsEvent {
        private final NfcFlowType nfcFlowType;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public NfcContinueToScanSelected(NfcFlowType nfcFlowType) {
            super(new Event(EventNames.Nfc.NFC_CONTINUE_AT_INITIAL_PROMPT, EventType.ACTION, null, null, 12, null), MapsKt.mapOf(TuplesKt.to(AnalyticsPropertyKeys.STEP, AnalyticsFlowStep.NFC), TuplesKt.to(AnalyticsPropertyKeys.NFC_FLOW_TYPE, nfcFlowType)), null, 4, null);
            Intrinsics.checkNotNullParameter(nfcFlowType, "nfcFlowType");
            this.nfcFlowType = nfcFlowType;
        }

        public static /* synthetic */ NfcContinueToScanSelected copy$default(NfcContinueToScanSelected nfcContinueToScanSelected, NfcFlowType nfcFlowType, int i, Object obj) {
            if ((i & 1) != 0) {
                nfcFlowType = nfcContinueToScanSelected.nfcFlowType;
            }
            return nfcContinueToScanSelected.copy(nfcFlowType);
        }

        /* renamed from: component1, reason: from getter */
        public final NfcFlowType getNfcFlowType() {
            return this.nfcFlowType;
        }

        public final NfcContinueToScanSelected copy(NfcFlowType nfcFlowType) {
            Intrinsics.checkNotNullParameter(nfcFlowType, "nfcFlowType");
            return new NfcContinueToScanSelected(nfcFlowType);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof NfcContinueToScanSelected) && this.nfcFlowType == ((NfcContinueToScanSelected) other).nfcFlowType;
        }

        public final NfcFlowType getNfcFlowType() {
            return this.nfcFlowType;
        }

        public int hashCode() {
            return this.nfcFlowType.hashCode();
        }

        public String toString() {
            return "NfcContinueToScanSelected(nfcFlowType=" + this.nfcFlowType + ')';
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/NfcEvents$NfcDataUploadCompleted;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsEvent;", "nfcFlowType", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/NfcFlowType;", "(Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/NfcFlowType;)V", "getNfcFlowType", "()Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/NfcFlowType;", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class NfcDataUploadCompleted extends AnalyticsEvent {
        private final NfcFlowType nfcFlowType;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public NfcDataUploadCompleted(NfcFlowType nfcFlowType) {
            super(new Event(EventNames.Nfc.NFC_FLOW_DATA_UPLOAD_COMPLETED, EventType.ACTION, null, null, 12, null), MapsKt.mapOf(TuplesKt.to(AnalyticsPropertyKeys.STEP, AnalyticsFlowStep.NFC), TuplesKt.to(AnalyticsPropertyKeys.NFC_FLOW_TYPE, nfcFlowType)), null, 4, null);
            Intrinsics.checkNotNullParameter(nfcFlowType, "nfcFlowType");
            this.nfcFlowType = nfcFlowType;
        }

        public static /* synthetic */ NfcDataUploadCompleted copy$default(NfcDataUploadCompleted nfcDataUploadCompleted, NfcFlowType nfcFlowType, int i, Object obj) {
            if ((i & 1) != 0) {
                nfcFlowType = nfcDataUploadCompleted.nfcFlowType;
            }
            return nfcDataUploadCompleted.copy(nfcFlowType);
        }

        /* renamed from: component1, reason: from getter */
        public final NfcFlowType getNfcFlowType() {
            return this.nfcFlowType;
        }

        public final NfcDataUploadCompleted copy(NfcFlowType nfcFlowType) {
            Intrinsics.checkNotNullParameter(nfcFlowType, "nfcFlowType");
            return new NfcDataUploadCompleted(nfcFlowType);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof NfcDataUploadCompleted) && this.nfcFlowType == ((NfcDataUploadCompleted) other).nfcFlowType;
        }

        public final NfcFlowType getNfcFlowType() {
            return this.nfcFlowType;
        }

        public int hashCode() {
            return this.nfcFlowType.hashCode();
        }

        public String toString() {
            return "NfcDataUploadCompleted(nfcFlowType=" + this.nfcFlowType + ')';
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/NfcEvents$NfcDataUploadStarted;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsEvent;", "nfcFlowType", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/NfcFlowType;", "(Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/NfcFlowType;)V", "getNfcFlowType", "()Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/NfcFlowType;", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class NfcDataUploadStarted extends AnalyticsEvent {
        private final NfcFlowType nfcFlowType;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public NfcDataUploadStarted(NfcFlowType nfcFlowType) {
            super(new Event(EventNames.Nfc.NFC_FLOW_DATA_UPLOAD_STARTED, EventType.ACTION, null, null, 12, null), MapsKt.mapOf(TuplesKt.to(AnalyticsPropertyKeys.STEP, AnalyticsFlowStep.NFC), TuplesKt.to(AnalyticsPropertyKeys.NFC_FLOW_TYPE, nfcFlowType)), null, 4, null);
            Intrinsics.checkNotNullParameter(nfcFlowType, "nfcFlowType");
            this.nfcFlowType = nfcFlowType;
        }

        public static /* synthetic */ NfcDataUploadStarted copy$default(NfcDataUploadStarted nfcDataUploadStarted, NfcFlowType nfcFlowType, int i, Object obj) {
            if ((i & 1) != 0) {
                nfcFlowType = nfcDataUploadStarted.nfcFlowType;
            }
            return nfcDataUploadStarted.copy(nfcFlowType);
        }

        /* renamed from: component1, reason: from getter */
        public final NfcFlowType getNfcFlowType() {
            return this.nfcFlowType;
        }

        public final NfcDataUploadStarted copy(NfcFlowType nfcFlowType) {
            Intrinsics.checkNotNullParameter(nfcFlowType, "nfcFlowType");
            return new NfcDataUploadStarted(nfcFlowType);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof NfcDataUploadStarted) && this.nfcFlowType == ((NfcDataUploadStarted) other).nfcFlowType;
        }

        public final NfcFlowType getNfcFlowType() {
            return this.nfcFlowType;
        }

        public int hashCode() {
            return this.nfcFlowType.hashCode();
        }

        public String toString() {
            return "NfcDataUploadStarted(nfcFlowType=" + this.nfcFlowType + ')';
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/NfcEvents$NfcExitVerificationClicked;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsEvent;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class NfcExitVerificationClicked extends AnalyticsEvent {
        public static final NfcExitVerificationClicked INSTANCE = new NfcExitVerificationClicked();

        private NfcExitVerificationClicked() {
            super(new Event(EventNames.Nfc.NFC_EXIT_VERIFICATION_CLICKED, EventType.ACTION, null, null, 12, null), null, null, 6, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/NfcEvents$NfcNoCanClicked;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsEvent;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class NfcNoCanClicked extends AnalyticsEvent {
        public static final NfcNoCanClicked INSTANCE = new NfcNoCanClicked();

        private NfcNoCanClicked() {
            super(new Event(EventNames.Nfc.NFC_NO_CAN_CLICKED, EventType.ACTION, null, null, 12, null), null, null, 6, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/NfcEvents$NfcOpenSettingsClicked;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsEvent;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class NfcOpenSettingsClicked extends AnalyticsEvent {
        public static final NfcOpenSettingsClicked INSTANCE = new NfcOpenSettingsClicked();

        private NfcOpenSettingsClicked() {
            super(new Event(EventNames.Nfc.NFC_OPEN_SETTINGS_CLICKED, EventType.ACTION, null, null, 12, null), null, null, 6, null);
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0080\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J+\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0016"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/NfcEvents$NfcPaceError;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsEvent;", "error", "", "cardAccessFileString", "usedSecurityInfoString", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getCardAccessFileString", "()Ljava/lang/String;", "getError", "getUsedSecurityInfoString", "component1", "component2", "component3", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class NfcPaceError extends AnalyticsEvent {
        private final String cardAccessFileString;
        private final String error;
        private final String usedSecurityInfoString;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public NfcPaceError(String error, String str, String str2) {
            super(new Event(EventNames.Nfc.NFC_PACE_ERROR, EventType.ACTION, null, null, 12, null), MapsKt.mapOf(TuplesKt.to(AnalyticsPropertyKeys.STEP, AnalyticsFlowStep.NFC), TuplesKt.to("error", error), TuplesKt.to(AnalyticsPropertyKeys.CARD_ACCESS_FILE, str), TuplesKt.to(AnalyticsPropertyKeys.SECURITY_INFO_USED, str2)), null, 4, null);
            Intrinsics.checkNotNullParameter(error, "error");
            this.error = error;
            this.cardAccessFileString = str;
            this.usedSecurityInfoString = str2;
        }

        public static /* synthetic */ NfcPaceError copy$default(NfcPaceError nfcPaceError, String str, String str2, String str3, int i, Object obj) {
            if ((i & 1) != 0) {
                str = nfcPaceError.error;
            }
            if ((i & 2) != 0) {
                str2 = nfcPaceError.cardAccessFileString;
            }
            if ((i & 4) != 0) {
                str3 = nfcPaceError.usedSecurityInfoString;
            }
            return nfcPaceError.copy(str, str2, str3);
        }

        /* renamed from: component1, reason: from getter */
        public final String getError() {
            return this.error;
        }

        /* renamed from: component2, reason: from getter */
        public final String getCardAccessFileString() {
            return this.cardAccessFileString;
        }

        /* renamed from: component3, reason: from getter */
        public final String getUsedSecurityInfoString() {
            return this.usedSecurityInfoString;
        }

        public final NfcPaceError copy(String error, String cardAccessFileString, String usedSecurityInfoString) {
            Intrinsics.checkNotNullParameter(error, "error");
            return new NfcPaceError(error, cardAccessFileString, usedSecurityInfoString);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof NfcPaceError)) {
                return false;
            }
            NfcPaceError nfcPaceError = (NfcPaceError) other;
            return Intrinsics.areEqual(this.error, nfcPaceError.error) && Intrinsics.areEqual(this.cardAccessFileString, nfcPaceError.cardAccessFileString) && Intrinsics.areEqual(this.usedSecurityInfoString, nfcPaceError.usedSecurityInfoString);
        }

        public final String getCardAccessFileString() {
            return this.cardAccessFileString;
        }

        public final String getError() {
            return this.error;
        }

        public final String getUsedSecurityInfoString() {
            return this.usedSecurityInfoString;
        }

        public int hashCode() {
            int iHashCode = this.error.hashCode() * 31;
            String str = this.cardAccessFileString;
            int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.usedSecurityInfoString;
            return iHashCode2 + (str2 != null ? str2.hashCode() : 0);
        }

        public String toString() {
            return "NfcPaceError(error=" + this.error + ", cardAccessFileString=" + this.cardAccessFileString + ", usedSecurityInfoString=" + this.usedSecurityInfoString + ')';
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0080\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u000b\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/NfcEvents$NfcPropertiesError;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsEvent;", "error", "", "(Ljava/lang/String;)V", "getError", "()Ljava/lang/String;", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class NfcPropertiesError extends AnalyticsEvent {
        private final String error;

        public NfcPropertiesError(String str) {
            super(new Event(EventNames.Nfc.NFC_PROPERTIES_ERROR, EventType.ACTION, null, null, 12, null), MapsKt.mapOf(TuplesKt.to(AnalyticsPropertyKeys.STEP, AnalyticsFlowStep.NFC), TuplesKt.to("error", str)), null, 4, null);
            this.error = str;
        }

        public static /* synthetic */ NfcPropertiesError copy$default(NfcPropertiesError nfcPropertiesError, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = nfcPropertiesError.error;
            }
            return nfcPropertiesError.copy(str);
        }

        /* renamed from: component1, reason: from getter */
        public final String getError() {
            return this.error;
        }

        public final NfcPropertiesError copy(String error) {
            return new NfcPropertiesError(error);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof NfcPropertiesError) && Intrinsics.areEqual(this.error, ((NfcPropertiesError) other).error);
        }

        public final String getError() {
            return this.error;
        }

        public int hashCode() {
            String str = this.error;
            if (str == null) {
                return 0;
            }
            return str.hashCode();
        }

        public String toString() {
            return "NfcPropertiesError(error=" + this.error + ')';
        }
    }

    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\t\u0010\u001a\u001a\u00020\tHÆ\u0003J\t\u0010\u001b\u001a\u00020\u000bHÆ\u0003J?\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000bHÆ\u0001J\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 HÖ\u0003J\t\u0010!\u001a\u00020\tHÖ\u0001J\t\u0010\"\u001a\u00020#HÖ\u0001R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006$"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/NfcEvents$NfcRetryScanSelected;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsEvent;", "nfcFlowType", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/NfcFlowType;", "documentType", "Lcom/onfido/android/sdk/capture/DocumentType;", RemoteConfigConstants.RequestFieldKey.COUNTRY_CODE, "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "countAttempt", "", "nfcOptions", "Lcom/onfido/android/sdk/capture/model/NFCOptions$Enabled;", "(Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/NfcFlowType;Lcom/onfido/android/sdk/capture/DocumentType;Lcom/onfido/android/sdk/capture/utils/CountryCode;ILcom/onfido/android/sdk/capture/model/NFCOptions$Enabled;)V", "getCountAttempt", "()I", "getCountryCode", "()Lcom/onfido/android/sdk/capture/utils/CountryCode;", "getDocumentType", "()Lcom/onfido/android/sdk/capture/DocumentType;", "getNfcFlowType", "()Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/NfcFlowType;", "getNfcOptions", "()Lcom/onfido/android/sdk/capture/model/NFCOptions$Enabled;", "component1", "component2", "component3", "component4", "component5", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class NfcRetryScanSelected extends AnalyticsEvent {
        private final int countAttempt;
        private final CountryCode countryCode;
        private final DocumentType documentType;
        private final NfcFlowType nfcFlowType;
        private final NFCOptions.Enabled nfcOptions;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public NfcRetryScanSelected(NfcFlowType nfcFlowType, DocumentType documentType, CountryCode countryCode, int i, NFCOptions.Enabled nfcOptions) {
            super(new Event(EventNames.Nfc.NFC_RETRY_SELECTED, EventType.ACTION, null, null, 12, null), MapsKt.mapOf(TuplesKt.to(AnalyticsPropertyKeys.STEP, AnalyticsFlowStep.NFC), TuplesKt.to(AnalyticsPropertyKeys.NFC_FLOW_TYPE, nfcFlowType), TuplesKt.to(AnalyticsPropertyKeys.COUNT_ATTEMPT, Integer.valueOf(i)), TuplesKt.to(AnalyticsPropertyKeys.DOCUMENT_TYPE, documentType), TuplesKt.to("country_code", countryCode), TuplesKt.to(AnalyticsPropertyKeys.NFC_PROCESSING_OPTION, nfcOptions.getName())), null, 4, null);
            Intrinsics.checkNotNullParameter(nfcFlowType, "nfcFlowType");
            Intrinsics.checkNotNullParameter(nfcOptions, "nfcOptions");
            this.nfcFlowType = nfcFlowType;
            this.documentType = documentType;
            this.countryCode = countryCode;
            this.countAttempt = i;
            this.nfcOptions = nfcOptions;
        }

        public static /* synthetic */ NfcRetryScanSelected copy$default(NfcRetryScanSelected nfcRetryScanSelected, NfcFlowType nfcFlowType, DocumentType documentType, CountryCode countryCode, int i, NFCOptions.Enabled enabled, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                nfcFlowType = nfcRetryScanSelected.nfcFlowType;
            }
            if ((i2 & 2) != 0) {
                documentType = nfcRetryScanSelected.documentType;
            }
            DocumentType documentType2 = documentType;
            if ((i2 & 4) != 0) {
                countryCode = nfcRetryScanSelected.countryCode;
            }
            CountryCode countryCode2 = countryCode;
            if ((i2 & 8) != 0) {
                i = nfcRetryScanSelected.countAttempt;
            }
            int i3 = i;
            if ((i2 & 16) != 0) {
                enabled = nfcRetryScanSelected.nfcOptions;
            }
            return nfcRetryScanSelected.copy(nfcFlowType, documentType2, countryCode2, i3, enabled);
        }

        /* renamed from: component1, reason: from getter */
        public final NfcFlowType getNfcFlowType() {
            return this.nfcFlowType;
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
        public final int getCountAttempt() {
            return this.countAttempt;
        }

        /* renamed from: component5, reason: from getter */
        public final NFCOptions.Enabled getNfcOptions() {
            return this.nfcOptions;
        }

        public final NfcRetryScanSelected copy(NfcFlowType nfcFlowType, DocumentType documentType, CountryCode countryCode, int countAttempt, NFCOptions.Enabled nfcOptions) {
            Intrinsics.checkNotNullParameter(nfcFlowType, "nfcFlowType");
            Intrinsics.checkNotNullParameter(nfcOptions, "nfcOptions");
            return new NfcRetryScanSelected(nfcFlowType, documentType, countryCode, countAttempt, nfcOptions);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof NfcRetryScanSelected)) {
                return false;
            }
            NfcRetryScanSelected nfcRetryScanSelected = (NfcRetryScanSelected) other;
            return this.nfcFlowType == nfcRetryScanSelected.nfcFlowType && this.documentType == nfcRetryScanSelected.documentType && this.countryCode == nfcRetryScanSelected.countryCode && this.countAttempt == nfcRetryScanSelected.countAttempt && Intrinsics.areEqual(this.nfcOptions, nfcRetryScanSelected.nfcOptions);
        }

        public final int getCountAttempt() {
            return this.countAttempt;
        }

        public final CountryCode getCountryCode() {
            return this.countryCode;
        }

        public final DocumentType getDocumentType() {
            return this.documentType;
        }

        public final NfcFlowType getNfcFlowType() {
            return this.nfcFlowType;
        }

        public final NFCOptions.Enabled getNfcOptions() {
            return this.nfcOptions;
        }

        public int hashCode() {
            int iHashCode = this.nfcFlowType.hashCode() * 31;
            DocumentType documentType = this.documentType;
            int iHashCode2 = (iHashCode + (documentType == null ? 0 : documentType.hashCode())) * 31;
            CountryCode countryCode = this.countryCode;
            return ((((iHashCode2 + (countryCode != null ? countryCode.hashCode() : 0)) * 31) + Integer.hashCode(this.countAttempt)) * 31) + this.nfcOptions.hashCode();
        }

        public String toString() {
            return "NfcRetryScanSelected(nfcFlowType=" + this.nfcFlowType + ", documentType=" + this.documentType + ", countryCode=" + this.countryCode + ", countAttempt=" + this.countAttempt + ", nfcOptions=" + this.nfcOptions + ')';
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/NfcEvents$NfcSkipAtInitialPrompt;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsEvent;", "nfcOptions", "Lcom/onfido/android/sdk/capture/model/NFCOptions$Enabled;", "(Lcom/onfido/android/sdk/capture/model/NFCOptions$Enabled;)V", "getNfcOptions", "()Lcom/onfido/android/sdk/capture/model/NFCOptions$Enabled;", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class NfcSkipAtInitialPrompt extends AnalyticsEvent {
        private final NFCOptions.Enabled nfcOptions;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public NfcSkipAtInitialPrompt(NFCOptions.Enabled nfcOptions) {
            super(new Event(EventNames.Nfc.NFC_SKIP_AT_INITIAL_PROMPT, EventType.ACTION, null, null, 12, null), MapsKt.mapOf(TuplesKt.to(AnalyticsPropertyKeys.STEP, AnalyticsFlowStep.NFC), TuplesKt.to(AnalyticsPropertyKeys.NFC_PROCESSING_OPTION, nfcOptions.getName())), null, 4, null);
            Intrinsics.checkNotNullParameter(nfcOptions, "nfcOptions");
            this.nfcOptions = nfcOptions;
        }

        public static /* synthetic */ NfcSkipAtInitialPrompt copy$default(NfcSkipAtInitialPrompt nfcSkipAtInitialPrompt, NFCOptions.Enabled enabled, int i, Object obj) {
            if ((i & 1) != 0) {
                enabled = nfcSkipAtInitialPrompt.nfcOptions;
            }
            return nfcSkipAtInitialPrompt.copy(enabled);
        }

        /* renamed from: component1, reason: from getter */
        public final NFCOptions.Enabled getNfcOptions() {
            return this.nfcOptions;
        }

        public final NfcSkipAtInitialPrompt copy(NFCOptions.Enabled nfcOptions) {
            Intrinsics.checkNotNullParameter(nfcOptions, "nfcOptions");
            return new NfcSkipAtInitialPrompt(nfcOptions);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof NfcSkipAtInitialPrompt) && Intrinsics.areEqual(this.nfcOptions, ((NfcSkipAtInitialPrompt) other).nfcOptions);
        }

        public final NFCOptions.Enabled getNfcOptions() {
            return this.nfcOptions;
        }

        public int hashCode() {
            return this.nfcOptions.hashCode();
        }

        public String toString() {
            return "NfcSkipAtInitialPrompt(nfcOptions=" + this.nfcOptions + ')';
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/NfcEvents$NfcSkipAtManualCanEntry;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsEvent;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class NfcSkipAtManualCanEntry extends AnalyticsEvent {
        public static final NfcSkipAtManualCanEntry INSTANCE = new NfcSkipAtManualCanEntry();

        private NfcSkipAtManualCanEntry() {
            super(new Event(EventNames.Nfc.NFC_SKIP_AT_MANUAL_CAN_ENTRY, EventType.ACTION, null, null, 12, null), null, null, 6, null);
        }
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0007HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001a"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/NfcEvents$NfcSkipAtRetry;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsEvent;", "nfcFlowType", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/NfcFlowType;", "countAttempt", "", "nfcOptions", "Lcom/onfido/android/sdk/capture/model/NFCOptions$Enabled;", "(Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/NfcFlowType;ILcom/onfido/android/sdk/capture/model/NFCOptions$Enabled;)V", "getCountAttempt", "()I", "getNfcFlowType", "()Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/NfcFlowType;", "getNfcOptions", "()Lcom/onfido/android/sdk/capture/model/NFCOptions$Enabled;", "component1", "component2", "component3", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class NfcSkipAtRetry extends AnalyticsEvent {
        private final int countAttempt;
        private final NfcFlowType nfcFlowType;
        private final NFCOptions.Enabled nfcOptions;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public NfcSkipAtRetry(NfcFlowType nfcFlowType, int i, NFCOptions.Enabled nfcOptions) {
            super(new Event(EventNames.Nfc.NFC_SKIP_AT_RETRY, EventType.ACTION, null, null, 12, null), MapsKt.mapOf(TuplesKt.to(AnalyticsPropertyKeys.STEP, AnalyticsFlowStep.NFC), TuplesKt.to(AnalyticsPropertyKeys.NFC_FLOW_TYPE, nfcFlowType), TuplesKt.to(AnalyticsPropertyKeys.COUNT_ATTEMPT, Integer.valueOf(i)), TuplesKt.to(AnalyticsPropertyKeys.NFC_PROCESSING_OPTION, nfcOptions.getName())), null, 4, null);
            Intrinsics.checkNotNullParameter(nfcFlowType, "nfcFlowType");
            Intrinsics.checkNotNullParameter(nfcOptions, "nfcOptions");
            this.nfcFlowType = nfcFlowType;
            this.countAttempt = i;
            this.nfcOptions = nfcOptions;
        }

        public static /* synthetic */ NfcSkipAtRetry copy$default(NfcSkipAtRetry nfcSkipAtRetry, NfcFlowType nfcFlowType, int i, NFCOptions.Enabled enabled, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                nfcFlowType = nfcSkipAtRetry.nfcFlowType;
            }
            if ((i2 & 2) != 0) {
                i = nfcSkipAtRetry.countAttempt;
            }
            if ((i2 & 4) != 0) {
                enabled = nfcSkipAtRetry.nfcOptions;
            }
            return nfcSkipAtRetry.copy(nfcFlowType, i, enabled);
        }

        /* renamed from: component1, reason: from getter */
        public final NfcFlowType getNfcFlowType() {
            return this.nfcFlowType;
        }

        /* renamed from: component2, reason: from getter */
        public final int getCountAttempt() {
            return this.countAttempt;
        }

        /* renamed from: component3, reason: from getter */
        public final NFCOptions.Enabled getNfcOptions() {
            return this.nfcOptions;
        }

        public final NfcSkipAtRetry copy(NfcFlowType nfcFlowType, int countAttempt, NFCOptions.Enabled nfcOptions) {
            Intrinsics.checkNotNullParameter(nfcFlowType, "nfcFlowType");
            Intrinsics.checkNotNullParameter(nfcOptions, "nfcOptions");
            return new NfcSkipAtRetry(nfcFlowType, countAttempt, nfcOptions);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof NfcSkipAtRetry)) {
                return false;
            }
            NfcSkipAtRetry nfcSkipAtRetry = (NfcSkipAtRetry) other;
            return this.nfcFlowType == nfcSkipAtRetry.nfcFlowType && this.countAttempt == nfcSkipAtRetry.countAttempt && Intrinsics.areEqual(this.nfcOptions, nfcSkipAtRetry.nfcOptions);
        }

        public final int getCountAttempt() {
            return this.countAttempt;
        }

        public final NfcFlowType getNfcFlowType() {
            return this.nfcFlowType;
        }

        public final NFCOptions.Enabled getNfcOptions() {
            return this.nfcOptions;
        }

        public int hashCode() {
            return (((this.nfcFlowType.hashCode() * 31) + Integer.hashCode(this.countAttempt)) * 31) + this.nfcOptions.hashCode();
        }

        public String toString() {
            return "NfcSkipAtRetry(nfcFlowType=" + this.nfcFlowType + ", countAttempt=" + this.countAttempt + ", nfcOptions=" + this.nfcOptions + ')';
        }
    }

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B!\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0007HÆ\u0003J+\u0010\u0012\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001b"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/NfcEvents$NfcStartScanSelected;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsEvent;", "documentType", "Lcom/onfido/android/sdk/capture/DocumentType;", RemoteConfigConstants.RequestFieldKey.COUNTRY_CODE, "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "nfcFlowType", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/NfcFlowType;", "(Lcom/onfido/android/sdk/capture/DocumentType;Lcom/onfido/android/sdk/capture/utils/CountryCode;Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/NfcFlowType;)V", "getCountryCode", "()Lcom/onfido/android/sdk/capture/utils/CountryCode;", "getDocumentType", "()Lcom/onfido/android/sdk/capture/DocumentType;", "getNfcFlowType", "()Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/NfcFlowType;", "component1", "component2", "component3", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class NfcStartScanSelected extends AnalyticsEvent {
        private final CountryCode countryCode;
        private final DocumentType documentType;
        private final NfcFlowType nfcFlowType;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public NfcStartScanSelected(DocumentType documentType, CountryCode countryCode, NfcFlowType nfcFlowType) {
            super(new Event(EventNames.Nfc.NFC_START_SCANNING_SELECTED, EventType.ACTION, null, null, 12, null), MapsKt.mapOf(TuplesKt.to(AnalyticsPropertyKeys.STEP, AnalyticsFlowStep.NFC), TuplesKt.to(AnalyticsPropertyKeys.DOCUMENT_TYPE, documentType), TuplesKt.to("country_code", countryCode), TuplesKt.to(AnalyticsPropertyKeys.NFC_FLOW_TYPE, nfcFlowType)), null, 4, null);
            Intrinsics.checkNotNullParameter(nfcFlowType, "nfcFlowType");
            this.documentType = documentType;
            this.countryCode = countryCode;
            this.nfcFlowType = nfcFlowType;
        }

        public static /* synthetic */ NfcStartScanSelected copy$default(NfcStartScanSelected nfcStartScanSelected, DocumentType documentType, CountryCode countryCode, NfcFlowType nfcFlowType, int i, Object obj) {
            if ((i & 1) != 0) {
                documentType = nfcStartScanSelected.documentType;
            }
            if ((i & 2) != 0) {
                countryCode = nfcStartScanSelected.countryCode;
            }
            if ((i & 4) != 0) {
                nfcFlowType = nfcStartScanSelected.nfcFlowType;
            }
            return nfcStartScanSelected.copy(documentType, countryCode, nfcFlowType);
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
        public final NfcFlowType getNfcFlowType() {
            return this.nfcFlowType;
        }

        public final NfcStartScanSelected copy(DocumentType documentType, CountryCode countryCode, NfcFlowType nfcFlowType) {
            Intrinsics.checkNotNullParameter(nfcFlowType, "nfcFlowType");
            return new NfcStartScanSelected(documentType, countryCode, nfcFlowType);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof NfcStartScanSelected)) {
                return false;
            }
            NfcStartScanSelected nfcStartScanSelected = (NfcStartScanSelected) other;
            return this.documentType == nfcStartScanSelected.documentType && this.countryCode == nfcStartScanSelected.countryCode && this.nfcFlowType == nfcStartScanSelected.nfcFlowType;
        }

        public final CountryCode getCountryCode() {
            return this.countryCode;
        }

        public final DocumentType getDocumentType() {
            return this.documentType;
        }

        public final NfcFlowType getNfcFlowType() {
            return this.nfcFlowType;
        }

        public int hashCode() {
            DocumentType documentType = this.documentType;
            int iHashCode = (documentType == null ? 0 : documentType.hashCode()) * 31;
            CountryCode countryCode = this.countryCode;
            return ((iHashCode + (countryCode != null ? countryCode.hashCode() : 0)) * 31) + this.nfcFlowType.hashCode();
        }

        public String toString() {
            return "NfcStartScanSelected(documentType=" + this.documentType + ", countryCode=" + this.countryCode + ", nfcFlowType=" + this.nfcFlowType + ')';
        }
    }
}
