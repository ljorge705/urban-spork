package com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers;

import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.OnfidoAnalytics;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.AnalyticsEvent;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.AnalyticsPropertyKeys;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.NfcFlowType;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.ScreenEvents;
import com.onfido.android.sdk.capture.model.NFCOptions;
import com.onfido.android.sdk.capture.utils.CountryCode;
import com.onfido.javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0011\b\u0010\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0017\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bH\u0010¢\u0006\u0002\b\tJ\r\u0010\n\u001a\u00020\u0006H\u0010¢\u0006\u0002\b\u000bJ\r\u0010\f\u001a\u00020\u0006H\u0010¢\u0006\u0002\b\rJ\r\u0010\u000e\u001a\u00020\u0006H\u0010¢\u0006\u0002\b\u000fJ%\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013H\u0010¢\u0006\u0002\b\u0015J\r\u0010\u0016\u001a\u00020\u0006H\u0010¢\u0006\u0002\b\u0017J\r\u0010\u0018\u001a\u00020\u0006H\u0010¢\u0006\u0002\b\u0019J\r\u0010\u001a\u001a\u00020\u0006H\u0010¢\u0006\u0002\b\u001bJ1\u0010\u001c\u001a\u00020\u00062\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0010¢\u0006\u0002\b%J%\u0010&\u001a\u00020\u00062\u0006\u0010'\u001a\u00020(2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0010¢\u0006\u0002\b)J-\u0010*\u001a\u00020\u00062\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\u00132\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0010¢\u0006\u0002\b.J\r\u0010/\u001a\u00020\u0006H\u0010¢\u0006\u0002\b0J\r\u00101\u001a\u00020\u0006H\u0010¢\u0006\u0002\b2J\r\u00103\u001a\u00020\u0006H\u0010¢\u0006\u0002\b4J\r\u00105\u001a\u00020\u0006H\u0010¢\u0006\u0002\b6J\r\u00107\u001a\u00020\u0006H\u0010¢\u0006\u0002\b8J\r\u00109\u001a\u00020\u0006H\u0010¢\u0006\u0002\b:J\r\u0010;\u001a\u00020\u0006H\u0010¢\u0006\u0002\b<R\u000e\u0010\u0002\u001a\u00020\u0003X\u0092\u0004¢\u0006\u0002\n\u0000¨\u0006="}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/ScreenTracker;", "", "onfidoAnalytics", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/OnfidoAnalytics;", "(Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/OnfidoAnalytics;)V", "trackCountrySelection", "", "isForProofOfAddress", "", "trackCountrySelection$onfido_capture_sdk_core_release", "trackDocumentListFetchRetried", "trackDocumentListFetchRetried$onfido_capture_sdk_core_release", "trackDocumentTypeSelection", "trackDocumentTypeSelection$onfido_capture_sdk_core_release", "trackFinalScreen", "trackFinalScreen$onfido_capture_sdk_core_release", "trackNfcCanEntry", AnalyticsPropertyKeys.PREFILLED, "canLength", "", "attempts", "trackNfcCanEntry$onfido_capture_sdk_core_release", "trackNfcCanMaxAttemptsReached", "trackNfcCanMaxAttemptsReached$onfido_capture_sdk_core_release", "trackNfcDeviceNotSupported", "trackNfcDeviceNotSupported$onfido_capture_sdk_core_release", "trackNfcDocumentNotSupported", "trackNfcDocumentNotSupported$onfido_capture_sdk_core_release", "trackNfcIntro", "documentType", "Lcom/onfido/android/sdk/capture/DocumentType;", RemoteConfigConstants.RequestFieldKey.COUNTRY_CODE, "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "nfcFlowType", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/NfcFlowType;", "nfcOptions", "Lcom/onfido/android/sdk/capture/model/NFCOptions$Enabled;", "trackNfcIntro$onfido_capture_sdk_core_release", "trackNfcReadError", "error", "", "trackNfcReadError$onfido_capture_sdk_core_release", "trackNfcReadSuccess", "duration", "", "retryCount", "trackNfcReadSuccess$onfido_capture_sdk_core_release", "trackNfcSettingsIntro", "trackNfcSettingsIntro$onfido_capture_sdk_core_release", "trackPoaDocumentDetails", "trackPoaDocumentDetails$onfido_capture_sdk_core_release", "trackPoaDocumentSubmission", "trackPoaDocumentSubmission$onfido_capture_sdk_core_release", "trackPoaDocumentTypeSelection", "trackPoaDocumentTypeSelection$onfido_capture_sdk_core_release", "trackPoaVerifyAddress", "trackPoaVerifyAddress$onfido_capture_sdk_core_release", "trackUserConsent", "trackUserConsent$onfido_capture_sdk_core_release", "trackWelcome", "trackWelcome$onfido_capture_sdk_core_release", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class ScreenTracker {
    private final OnfidoAnalytics onfidoAnalytics;

    @Inject
    public ScreenTracker(OnfidoAnalytics onfidoAnalytics) {
        Intrinsics.checkNotNullParameter(onfidoAnalytics, "onfidoAnalytics");
        this.onfidoAnalytics = onfidoAnalytics;
    }

    public static /* synthetic */ void trackCountrySelection$onfido_capture_sdk_core_release$default(ScreenTracker screenTracker, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: trackCountrySelection");
        }
        if ((i & 1) != 0) {
            z = false;
        }
        screenTracker.trackCountrySelection$onfido_capture_sdk_core_release(z);
    }

    public void trackCountrySelection$onfido_capture_sdk_core_release(boolean isForProofOfAddress) {
        OnfidoAnalytics onfidoAnalytics;
        AnalyticsEvent analyticsEvent;
        if (isForProofOfAddress) {
            onfidoAnalytics = this.onfidoAnalytics;
            analyticsEvent = ScreenEvents.PoaCountrySelection.INSTANCE;
        } else {
            onfidoAnalytics = this.onfidoAnalytics;
            analyticsEvent = ScreenEvents.CountrySelection.INSTANCE;
        }
        onfidoAnalytics.track(analyticsEvent);
    }

    public void trackDocumentListFetchRetried$onfido_capture_sdk_core_release() {
        this.onfidoAnalytics.track(ScreenEvents.DocumentListFetchRetried.INSTANCE);
    }

    public void trackDocumentTypeSelection$onfido_capture_sdk_core_release() {
        this.onfidoAnalytics.track(ScreenEvents.DocumentTypeSelection.INSTANCE);
    }

    public void trackFinalScreen$onfido_capture_sdk_core_release() {
        this.onfidoAnalytics.track(ScreenEvents.Final.INSTANCE);
    }

    public void trackNfcCanEntry$onfido_capture_sdk_core_release(boolean prefilled, int canLength, int attempts) {
        this.onfidoAnalytics.track(new ScreenEvents.NfcCanEntry(prefilled, canLength, attempts));
    }

    public void trackNfcCanMaxAttemptsReached$onfido_capture_sdk_core_release() {
        this.onfidoAnalytics.track(ScreenEvents.NfcCanMaxAttemptsReached.INSTANCE);
    }

    public void trackNfcDeviceNotSupported$onfido_capture_sdk_core_release() {
        this.onfidoAnalytics.track(ScreenEvents.NfcDeviceNotSupported.INSTANCE);
    }

    public void trackNfcDocumentNotSupported$onfido_capture_sdk_core_release() {
        this.onfidoAnalytics.track(ScreenEvents.NfcDocumentNotSupported.INSTANCE);
    }

    public void trackNfcIntro$onfido_capture_sdk_core_release(DocumentType documentType, CountryCode countryCode, NfcFlowType nfcFlowType, NFCOptions.Enabled nfcOptions) {
        Intrinsics.checkNotNullParameter(nfcFlowType, "nfcFlowType");
        Intrinsics.checkNotNullParameter(nfcOptions, "nfcOptions");
        this.onfidoAnalytics.track(new ScreenEvents.NfcIntro(countryCode, documentType, nfcFlowType, nfcOptions));
    }

    public void trackNfcReadError$onfido_capture_sdk_core_release(String error, NfcFlowType nfcFlowType, NFCOptions.Enabled nfcOptions) {
        Intrinsics.checkNotNullParameter(error, "error");
        Intrinsics.checkNotNullParameter(nfcFlowType, "nfcFlowType");
        Intrinsics.checkNotNullParameter(nfcOptions, "nfcOptions");
        this.onfidoAnalytics.track(new ScreenEvents.NfcReadError(error, nfcFlowType, nfcOptions));
    }

    public void trackNfcReadSuccess$onfido_capture_sdk_core_release(long duration, int retryCount, NfcFlowType nfcFlowType, NFCOptions.Enabled nfcOptions) {
        Intrinsics.checkNotNullParameter(nfcFlowType, "nfcFlowType");
        Intrinsics.checkNotNullParameter(nfcOptions, "nfcOptions");
        this.onfidoAnalytics.track(new ScreenEvents.NfcRead(duration, retryCount, nfcFlowType, nfcOptions));
    }

    public void trackNfcSettingsIntro$onfido_capture_sdk_core_release() {
        this.onfidoAnalytics.track(ScreenEvents.NfcSettingsIntro.INSTANCE);
    }

    public void trackPoaDocumentDetails$onfido_capture_sdk_core_release() {
        this.onfidoAnalytics.track(ScreenEvents.PoaDocumentDetails.INSTANCE);
    }

    public void trackPoaDocumentSubmission$onfido_capture_sdk_core_release() {
        this.onfidoAnalytics.track(ScreenEvents.PoaDocumentSubmission.INSTANCE);
    }

    public void trackPoaDocumentTypeSelection$onfido_capture_sdk_core_release() {
        this.onfidoAnalytics.track(ScreenEvents.PoaDocumentTypeSelection.INSTANCE);
    }

    public void trackPoaVerifyAddress$onfido_capture_sdk_core_release() {
        this.onfidoAnalytics.track(ScreenEvents.PoaVerifyAddress.INSTANCE);
    }

    public void trackUserConsent$onfido_capture_sdk_core_release() {
        this.onfidoAnalytics.track(ScreenEvents.UserConsent.INSTANCE);
    }

    public void trackWelcome$onfido_capture_sdk_core_release() {
        this.onfidoAnalytics.track(ScreenEvents.Welcome.INSTANCE);
    }
}
