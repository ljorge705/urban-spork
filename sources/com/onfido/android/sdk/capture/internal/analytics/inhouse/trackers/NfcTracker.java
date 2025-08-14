package com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers;

import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.OnfidoAnalytics;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.NfcFlowType;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.NfcEvents;
import com.onfido.android.sdk.capture.internal.nfc.PassportAuthAccess;
import com.onfido.android.sdk.capture.model.NFCOptions;
import com.onfido.android.sdk.capture.utils.CountryCode;
import com.onfido.api.client.data.DocumentFeatures;
import com.onfido.javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0011\b\u0017\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0015\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0010¢\u0006\u0002\b\tJ\u001d\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0010¢\u0006\u0002\b\u000fJ\u0015\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u0012H\u0010¢\u0006\u0002\b\u0013J\u0010\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J9\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00182\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0010¢\u0006\u0002\b J\u001d\u0010!\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\"\u001a\u00020#H\u0010¢\u0006\u0002\b$J\r\u0010%\u001a\u00020\u0006H\u0010¢\u0006\u0002\b&J\r\u0010'\u001a\u00020\u0006H\u0010¢\u0006\u0002\b(J\r\u0010)\u001a\u00020\u0006H\u0010¢\u0006\u0002\b*J\r\u0010+\u001a\u00020\u0006H\u0010¢\u0006\u0002\b,J\r\u0010-\u001a\u00020\u0006H\u0010¢\u0006\u0002\b.J\r\u0010/\u001a\u00020\u0006H\u0010¢\u0006\u0002\b0J9\u00101\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u00102\u001a\u00020\f2\u0006\u00103\u001a\u000204H\u0010¢\u0006\u0002\b5J\r\u00106\u001a\u00020\u0006H\u0010¢\u0006\u0002\b7J)\u00108\u001a\u00020\u00062\u0006\u00109\u001a\u00020\b2\b\u0010:\u001a\u0004\u0018\u00010\b2\b\u0010;\u001a\u0004\u0018\u00010\bH\u0010¢\u0006\u0002\b<J\u0017\u0010=\u001a\u00020\u00062\b\u00109\u001a\u0004\u0018\u00010\bH\u0010¢\u0006\u0002\b>J\u0015\u0010?\u001a\u00020\u00062\u0006\u00103\u001a\u000204H\u0010¢\u0006\u0002\b@J%\u0010A\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u00102\u001a\u00020\f2\u0006\u00103\u001a\u000204H\u0010¢\u0006\u0002\bBJ)\u0010C\u001a\u00020\u00062\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u0011\u001a\u00020\u0012H\u0010¢\u0006\u0002\bDR\u000e\u0010\u0002\u001a\u00020\u0003X\u0092\u0004¢\u0006\u0002\n\u0000¨\u0006E"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/NfcTracker;", "", "onfidoAnalytics", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/OnfidoAnalytics;", "(Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/OnfidoAnalytics;)V", "trackBacAuthenticationError", "", "bacAuthError", "", "trackBacAuthenticationError$onfido_capture_sdk_core_release", "trackCanEntryCompleted", "attempts", "", "durationInMs", "", "trackCanEntryCompleted$onfido_capture_sdk_core_release", "trackContinueToNfcScanSelected", "nfcFlowType", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/NfcFlowType;", "trackContinueToNfcScanSelected$onfido_capture_sdk_core_release", "trackDataUploadCompleted", "trackDataUploadStarted", "trackDocumentNfcSupported", "isNfcSupported", "", "hasNfcKey", "documentType", "Lcom/onfido/android/sdk/capture/DocumentType;", RemoteConfigConstants.RequestFieldKey.COUNTRY_CODE, "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "documentFeatures", "Lcom/onfido/api/client/data/DocumentFeatures;", "trackDocumentNfcSupported$onfido_capture_sdk_core_release", "trackNfcChipAuthenticated", "authAccess", "Lcom/onfido/android/sdk/capture/internal/nfc/PassportAuthAccess;", "trackNfcChipAuthenticated$onfido_capture_sdk_core_release", "trackNfcChipConnectionLost", "trackNfcChipConnectionLost$onfido_capture_sdk_core_release", "trackNfcChipDiscovered", "trackNfcChipDiscovered$onfido_capture_sdk_core_release", "trackNfcChooseAnotherDocumentClicked", "trackNfcChooseAnotherDocumentClicked$onfido_capture_sdk_core_release", "trackNfcExitVerificationClicked", "trackNfcExitVerificationClicked$onfido_capture_sdk_core_release", "trackNfcNoCanClicked", "trackNfcNoCanClicked$onfido_capture_sdk_core_release", "trackNfcOpenSettingsClicked", "trackNfcOpenSettingsClicked$onfido_capture_sdk_core_release", "trackNfcRetryScanSelected", "countAttempt", "nfcOptions", "Lcom/onfido/android/sdk/capture/model/NFCOptions$Enabled;", "trackNfcRetryScanSelected$onfido_capture_sdk_core_release", "trackNfcSkipAtManualCanEntryClicked", "trackNfcSkipAtManualCanEntryClicked$onfido_capture_sdk_core_release", "trackPaceErrorEvent", "error", "cardAccessFileString", "usedSecurityInfoString", "trackPaceErrorEvent$onfido_capture_sdk_core_release", "trackPropertiesError", "trackPropertiesError$onfido_capture_sdk_core_release", "trackSkipNfcScanAtInitialPrompt", "trackSkipNfcScanAtInitialPrompt$onfido_capture_sdk_core_release", "trackSkipNfcScanAtRetry", "trackSkipNfcScanAtRetry$onfido_capture_sdk_core_release", "trackStartNfcScanSelected", "trackStartNfcScanSelected$onfido_capture_sdk_core_release", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class NfcTracker {
    private final OnfidoAnalytics onfidoAnalytics;

    @Inject
    public NfcTracker(OnfidoAnalytics onfidoAnalytics) {
        Intrinsics.checkNotNullParameter(onfidoAnalytics, "onfidoAnalytics");
        this.onfidoAnalytics = onfidoAnalytics;
    }

    public void trackBacAuthenticationError$onfido_capture_sdk_core_release(String bacAuthError) {
        Intrinsics.checkNotNullParameter(bacAuthError, "bacAuthError");
        this.onfidoAnalytics.track(new NfcEvents.NfcBACError(bacAuthError));
    }

    public void trackCanEntryCompleted$onfido_capture_sdk_core_release(int attempts, long durationInMs) {
        this.onfidoAnalytics.track(new NfcEvents.NfcCanEntryCompleted(attempts, durationInMs));
    }

    public void trackContinueToNfcScanSelected$onfido_capture_sdk_core_release(NfcFlowType nfcFlowType) {
        Intrinsics.checkNotNullParameter(nfcFlowType, "nfcFlowType");
        this.onfidoAnalytics.track(new NfcEvents.NfcContinueToScanSelected(nfcFlowType));
    }

    public void trackDataUploadCompleted(NfcFlowType nfcFlowType) {
        Intrinsics.checkNotNullParameter(nfcFlowType, "nfcFlowType");
        this.onfidoAnalytics.track(new NfcEvents.NfcDataUploadCompleted(nfcFlowType));
    }

    public void trackDataUploadStarted(NfcFlowType nfcFlowType) {
        Intrinsics.checkNotNullParameter(nfcFlowType, "nfcFlowType");
        this.onfidoAnalytics.track(new NfcEvents.NfcDataUploadStarted(nfcFlowType));
    }

    public void trackDocumentNfcSupported$onfido_capture_sdk_core_release(boolean isNfcSupported, boolean hasNfcKey, DocumentType documentType, CountryCode countryCode, DocumentFeatures documentFeatures) {
        Intrinsics.checkNotNullParameter(documentFeatures, "documentFeatures");
        this.onfidoAnalytics.track(new NfcEvents.DocumentNfcSupported(isNfcSupported, hasNfcKey, documentType, countryCode, documentFeatures));
    }

    public void trackNfcChipAuthenticated$onfido_capture_sdk_core_release(NfcFlowType nfcFlowType, PassportAuthAccess authAccess) {
        Intrinsics.checkNotNullParameter(nfcFlowType, "nfcFlowType");
        Intrinsics.checkNotNullParameter(authAccess, "authAccess");
        this.onfidoAnalytics.track(new NfcEvents.NfcChipAuthenticated(nfcFlowType, authAccess));
    }

    public void trackNfcChipConnectionLost$onfido_capture_sdk_core_release() {
        this.onfidoAnalytics.track(NfcEvents.NfcChipConnectionLost.INSTANCE);
    }

    public void trackNfcChipDiscovered$onfido_capture_sdk_core_release() {
        this.onfidoAnalytics.track(NfcEvents.NfcChipDiscovered.INSTANCE);
    }

    public void trackNfcChooseAnotherDocumentClicked$onfido_capture_sdk_core_release() {
        this.onfidoAnalytics.track(NfcEvents.NfcChooseAnotherDocumentClicked.INSTANCE);
    }

    public void trackNfcExitVerificationClicked$onfido_capture_sdk_core_release() {
        this.onfidoAnalytics.track(NfcEvents.NfcExitVerificationClicked.INSTANCE);
    }

    public void trackNfcNoCanClicked$onfido_capture_sdk_core_release() {
        this.onfidoAnalytics.track(NfcEvents.NfcNoCanClicked.INSTANCE);
    }

    public void trackNfcOpenSettingsClicked$onfido_capture_sdk_core_release() {
        this.onfidoAnalytics.track(NfcEvents.NfcOpenSettingsClicked.INSTANCE);
    }

    public void trackNfcRetryScanSelected$onfido_capture_sdk_core_release(NfcFlowType nfcFlowType, DocumentType documentType, CountryCode countryCode, int countAttempt, NFCOptions.Enabled nfcOptions) {
        Intrinsics.checkNotNullParameter(nfcFlowType, "nfcFlowType");
        Intrinsics.checkNotNullParameter(nfcOptions, "nfcOptions");
        this.onfidoAnalytics.track(new NfcEvents.NfcRetryScanSelected(nfcFlowType, documentType, countryCode, countAttempt, nfcOptions));
    }

    public void trackNfcSkipAtManualCanEntryClicked$onfido_capture_sdk_core_release() {
        this.onfidoAnalytics.track(NfcEvents.NfcSkipAtManualCanEntry.INSTANCE);
    }

    public void trackPaceErrorEvent$onfido_capture_sdk_core_release(String error, String cardAccessFileString, String usedSecurityInfoString) {
        Intrinsics.checkNotNullParameter(error, "error");
        this.onfidoAnalytics.track(new NfcEvents.NfcPaceError(error, cardAccessFileString, usedSecurityInfoString));
    }

    public void trackPropertiesError$onfido_capture_sdk_core_release(String error) {
        this.onfidoAnalytics.track(new NfcEvents.NfcPropertiesError(error));
    }

    public void trackSkipNfcScanAtInitialPrompt$onfido_capture_sdk_core_release(NFCOptions.Enabled nfcOptions) {
        Intrinsics.checkNotNullParameter(nfcOptions, "nfcOptions");
        this.onfidoAnalytics.track(new NfcEvents.NfcSkipAtInitialPrompt(nfcOptions));
    }

    public void trackSkipNfcScanAtRetry$onfido_capture_sdk_core_release(NfcFlowType nfcFlowType, int countAttempt, NFCOptions.Enabled nfcOptions) {
        Intrinsics.checkNotNullParameter(nfcFlowType, "nfcFlowType");
        Intrinsics.checkNotNullParameter(nfcOptions, "nfcOptions");
        this.onfidoAnalytics.track(new NfcEvents.NfcSkipAtRetry(nfcFlowType, countAttempt, nfcOptions));
    }

    public void trackStartNfcScanSelected$onfido_capture_sdk_core_release(DocumentType documentType, CountryCode countryCode, NfcFlowType nfcFlowType) {
        Intrinsics.checkNotNullParameter(nfcFlowType, "nfcFlowType");
        this.onfidoAnalytics.track(new NfcEvents.NfcStartScanSelected(documentType, countryCode, nfcFlowType));
    }
}
